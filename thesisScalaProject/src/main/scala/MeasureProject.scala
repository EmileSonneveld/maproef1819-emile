import java.io.File
import java.text._

import org.apache.commons.lang3.StringUtils
import scalafix.v1.SemanticDocument
import scalafix.{DocumentTuple, SemanticDB}
import scalafix.v1._ // for the symbol magic

import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import scala.language.postfixOps
import scala.meta._
import scala.meta.internal.semanticdb
import scala.meta.internal.semanticdb.SymbolInformation


trait ClassOrTrait extends Tree {}

object MeasureProject {

  def fillInPyramidTemplate(file: String, commitStats: CommitStats, projectName: String): String = {
    var svg = file
    val nop = commitStats.nop_set.size
    val noc = commitStats.noc_set.size
    val nom = commitStats.nom_set.size
    val loc = commitStats.loc
    val cc = commitStats.cc

    val df = new DecimalFormat(".##")

    svg = svg.replaceAll("%PROJECT%", projectName)

    svg = svg.replaceAll("%NOP%", nop.toString)
    svg = svg.replaceAll("%NOC%", noc.toString)
    svg = svg.replaceAll("%NOM%", nom.toString)
    svg = svg.replaceAll("%LOC%", loc.toString)
    svg = svg.replaceAll("%CYCLO%", cc.toString)
    svg = svg.replaceAll("%CALLS%", commitStats.calls.toString)
    svg = svg.replaceAll("%ANDC%", df.format(commitStats.andc))
    svg = svg.replaceAll("%AHH%", df.format(commitStats.ahh))
    svg = svg.replaceAll("%FANOUT%", commitStats.fanout.toString)

    svg = svg.replaceAll("%NOC/NOP%", df.format(noc.toDouble / nop))
    svg = svg.replaceAll("%NOM/NOC%", df.format(nom.toDouble / noc))
    svg = svg.replaceAll("%LOC/NOM%", df.format(loc.toDouble / nom))
    svg = svg.replaceAll("%CYCLO/LOC%", df.format(cc.toDouble / loc))
    svg = svg.replaceAll("%CALLS/NOM%", df.format(commitStats.calls.toDouble / nom))
    svg = svg.replaceAll("%FANOUT/CALLS%", df.format(commitStats.fanout.toDouble / commitStats.calls))

    svg = svg.replaceAll("%commitHash%", commitStats.commitHash)

    val redStr = "fill:#ff2700;"
    val greenStr = "fill:#008f00;"
    val blueStr = "fill:#0091ff;"

    var doc = XmlUtil.parseXmlFromString(svg)

    // Based on statistical foundings in java
    {
      val attr = XmlUtil.xpathGetNode(doc, "//rect[@label=\"bg_CycloPerLoc\"]/@style").get
      if (cc.toDouble / loc < 0.16)
        attr.setTextContent(attr.getTextContent.replace(greenStr, blueStr))
      else if (cc.toDouble / loc > 0.24)
        attr.setTextContent(attr.getTextContent.replace(greenStr, redStr))
    }
    {
      val attr = XmlUtil.xpathGetNode(doc, "//rect[@label=\"bg_NomPerNoc\"]/@style").get
      if (nom.toDouble / noc < 4)
        attr.setTextContent(attr.getTextContent.replace(greenStr, blueStr))
      else if (nom.toDouble / noc > 10)
        attr.setTextContent(attr.getTextContent.replace(greenStr, redStr))
    }
    {
      val attr = XmlUtil.xpathGetNode(doc, "//rect[@label=\"bg_LocPerNom\"]/@style").get
      if (loc.toDouble / nom < 7)
        attr.setTextContent(attr.getTextContent.replace(greenStr, blueStr))
      else if (loc.toDouble / nom > 13)
        attr.setTextContent(attr.getTextContent.replace(greenStr, redStr))
    }

    return XmlUtil.documentToString(doc)
  }


  private def consumeFile(commitStats: CommitStats, sDb: SemanticDB, sdoc: SemanticDocument): Unit = {
    val tree = sdoc.tree


    val packageCollection = tree.collect {
      case q: Pkg => q.name
    }
    packageCollection.foreach(x => commitStats.nop_set += x.toString)


    val classCollection = tree.collect {
      case q: Defn.Class => q.name
      case q: Defn.Object => q.name
    }
    classCollection.foreach(x => commitStats.noc_set += x.toString)


    val functionCollection = tree.collect {
      case q: Defn.Def => q.name
    }
    functionCollection.foreach(x => commitStats.nom_set += x.toString)


    commitStats.loc += tree.toString.count(x => x == '\n')


    var uniqueCallsPerFunction = 0
    var uniqueCalledClassesPerFunction = 0
    tree.collect {
      case clazz: Defn.Def =>
        var calls = Set.empty[String]
        var classes = Set.empty[String]
        //var defSymbol = sDb.getFromSymbolTable(clazz, sdoc)
        //println("\ndefSymbol: " + defSymbol)
        clazz.collect {
          case a: Term.Apply => {
            implicit var implicit_sdoc: SemanticDocument = sdoc

            var s = sDb.getFromSymbolTable(a.fun, sdoc)

            try {
              //var symInfo = sDb.symbolTable.info(s.value.toString)
              if (doWeOwnThisClass(s.value)) {
                classes += s.owner.toString()
              }
            } catch {
              case ex: Throwable => println("EXCEPTION: " + ex)
            }
            calls += a.fun.toString()
          }
        }
        //println("classes: \n" + classes.mkString("\n"))
        uniqueCalledClassesPerFunction += classes.size
        uniqueCallsPerFunction += calls.size
    }
    commitStats.calls += uniqueCallsPerFunction
    commitStats.fanout += uniqueCalledClassesPerFunction
  }

  /**
    * Todo: Use project data.
    */
  def doWeOwnThisClass(classUri: String): Boolean = {
    !classUri.startsWith("java/lang") &&
      !classUri.startsWith("javax/") &&
      !classUri.startsWith("scala/")
  }

  def doStatsForProject(projectPath: File, projectName: String): CommitStats = {
    val commitStats = new CommitStats

    var scalaRoot = Utils.normalizeDirectoryPath(projectPath.getAbsolutePath)
    if (scalaRoot.endsWith("src/main/scala/"))
      scalaRoot = scalaRoot.substring(0, scalaRoot.length - "src/main/scala/".length)

    //Utils.execCommand("cd " + getGitTopLevel(new File(scalaRoot)) + " && sbt semanticdb").trim

    val semanticDB = new SemanticDB(new File(scalaRoot))

    if (true) {
      val th = new TypeHiarchy(semanticDB)
      for (doc <- semanticDB.documents) {
        th.absorb(doc)
      }
      Utils.writeFile("C:\\Users\\emill\\Dropbox\\slimmerWorden\\2018-2019-Semester2\\THESIS\\out\\gv\\types_" + projectName + ".gv", th.getGvString())

      commitStats.andc = th.calculateANDC()
      commitStats.ahh = th.calculateAHH()
    }

    for (doc <- semanticDB.documents) {
      implicit val implicit_ksjndflidbfkurhgb: SemanticDocument = doc.sdoc
      println("\n Doc: " + doc.tdoc.uri)

      if (true) {
        consumeFile(commitStats, semanticDB, doc.sdoc)
      }

      if (true) { // CC and Code Flow Graph
        val methodMap = CfgPerMethod.compute(doc.sdoc.tree)

        val relative = projectPath.toPath.relativize(doc.sdoc.input.asInstanceOf[Input.File].path.toNIO)
        val gvPath = "C:\\Users\\emill\\Dropbox\\slimmerWorden\\2018-2019-Semester2\\THESIS\\out\\gv\\" + projectName + "\\" + relative + ".gv"
        Utils.writeFile(gvPath, CfgPerMethod.MethodMapToGraphViz(methodMap))

        for (pair <- methodMap) {
          val CC = CfgPerMethod.calculateCC(pair._2)
          //if (CC >= 2) {
          //  println("Big CC found: " + pair._1 + " -> " + CC)
          //  println("\n\n" + CfgPerMethod.nodesToGraphViz(pair._2))
          //}
          //println(pair._1 + ": CC= " + CC)
          commitStats.cc += CC
        }
      }

      if (true) { // Check design smells
        doc.sdoc.tree.collect {

          case clazz: Defn.Object => {

            val className = MeasureProject.traitOrClassName(clazz)
            if (className.contains("PolygonFigure")) {
              var clazzSymbol = clazz.symbol
              var symInfo = semanticDB.symbolTable.info(clazzSymbol.value).get
              print("")
            }
          }
          case clazz: Defn.Class => {
            val className = MeasureProject.traitOrClassName(clazz)
            if (!className.endsWith("Test") // Naming convension for test classes
              && !clazz.symbol.isLocal
              && !clazz.symbol.isNone) { // Scala meta doesn't keep semantic information about inline classes :(
              println("Class: " + clazz.name.value)

              var cc = 0
              val methodMap = CfgPerMethod.compute(clazz) //doc.sdoc.tree)
              for (pair <- methodMap) {
                cc += CfgPerMethod.calculateCC(pair._2)
              }
              val classExternalPropsSet = externalProperties(clazz.symbol.value, clazz, semanticDB, doc.sdoc)
              val classExternalProps = classExternalPropsSet.size
              val cohesion = calculateCohesion(clazz, semanticDB, doc.sdoc)
              if (className.contains("PolygonFigure")) {
                print("")
              }

              {
                println("classExternalPropsSet[" + classExternalPropsSet.size + "]: " + classExternalPropsSet)
              }
              //if (classExternalProps > 10
              //  && cc > 30
              //  && cohesion < 0.3)

              {
                println("\nGodclass detected! " + clazz.name.toString()
                  + " cc: " + cc
                  + " classExternalProps: " + classExternalProps
                  + " cohesion: " + cohesion)
              }

              clazz.collect {
                case d: Defn.Def =>
                  println("Def: " + d.name.value)

                  val methodExternalPropsSet = externalProperties(clazz.symbol.value, d, semanticDB, doc.sdoc)

                  val methodExternalProps = methodExternalPropsSet.size
                  val methodExternalPropsClasses = {
                    var set: Set[String] = Set.empty[String]
                    for (e <- methodExternalPropsSet) {
                      set += propGetOwnerClass(e)
                    }
                    set
                  }
                  val methodInternalPropsSet = internalProperties(clazz.symbol.value, d, semanticDB, doc.sdoc)
                  val methodInternalProps = methodInternalPropsSet.size
                  //println("methodInternalPropsSet: " + methodInternalPropsSet)

                  if (methodExternalProps > 11
                    && methodExternalProps > methodInternalProps * 3
                    && methodExternalPropsClasses.size <= 6) {

                    println("\nFeatureEnvy detected! " + d.name.toString())
                    println("    methodExternalProps: " + methodExternalProps)
                    println("    methodInternalProps: " + methodInternalProps)
                    println("    methodExternalPropsClasses.size: " + methodExternalPropsClasses.size)
                  }

                  val methodNodes = CfgPerMethod.compute(d).head._2
                  val methodCc = CfgPerMethod.calculateCC(methodNodes)

                  val loc = d.toString.count(x => x == '\n')

                  val methodLocalPropsSet = localProperties(clazz, d, semanticDB, doc.sdoc)
                  val totalVars = methodLocalPropsSet.size + methodInternalProps + methodExternalProps

                  if (loc > 50 // arbitrary number
                    && methodCc > 10
                    // TODO: Nesting level
                    && totalVars > 10
                  ) {

                    println("\nBrainMethod detected! " + d.name.toString())
                    println("    loc: " + loc)
                    println("    methodCc: " + methodCc)
                    println("    totalVars: " + totalVars)
                  }
              }
            }
          }
        }
      }
    }


    commitStats
  }

  def propGetOwnerClass(e: String): String = {
    var idx = e.lastIndexOf("#")
    if (!e.endsWith(".") || idx == -1)
      idx = math.max(idx, e.lastIndexOf("."))
    return e.substring(0, idx)
  }

  /*
    def externalProperties(c: Tree, semanticDB: SemanticDB, sdoc: SemanticDocument): Int = {
      var externalProps: Set[String] = Set.empty[String]

      c.collect({
        case d: Defn.Def => {
          externalProps ++= externalProperties(c, d, semanticDB, sdoc)
        }
      })
      //println("externalProperties in class: \n\t" + externalProps.mkString("\n\t"))
      externalProps.size
    }
  */

  def getParents(semanticDB: SemanticDB, symbolString: String): Seq[String] = {
    if (symbolString == "io/x100/colstore/ColumnarStoreSpec#") {
      println()
    }
    var symInfo = semanticDB.getInfo(symbolString)
    //var symInfo = semanticDB.symbolTable.info(symbolString).get
    if (symInfo == null || !symInfo.signature.isInstanceOf[scala.meta.internal.semanticdb.ClassSignature]) return Seq.empty // Gave an error with "final case class RuntimeException"
    var parents = symInfo.signature.asInstanceOf[scala.meta.internal.semanticdb.ClassSignature].parents
    parents.map(x => x.asInstanceOf[semanticdb.TypeRef].symbol)
  }

  def getAllParentSymbs(semanticDB: SemanticDB, symbolString: String): Set[String] = {
    var collectedParents = Set.empty[String]

    var depth = 0

    def collectParentsRecurse(currentSymbol: String): Unit = {
      if (currentSymbol != null && currentSymbol != "" && !collectedParents.contains(currentSymbol)) {
        collectedParents += currentSymbol
        //println(" " * depth + currentSymbol)

        val parents = getParents(semanticDB, currentSymbol)
        depth += 1
        for (parentSymbolString <- parents) {
          collectParentsRecurse(parentSymbolString)
        }
        depth -= 1
      }
    }

    collectParentsRecurse(symbolString)

    collectedParents
  }

  def traitOrClassName(tree: Tree) = {
    tree match {
      case value: Defn.Trait => value.name.value
      case value: Defn.Class => value.name.value
      case value: Defn.Object => value.name.value
      case _ => throw new Exception("Not a trait Or Class Name.")
    }
  }

  def symbolInParentHiarchy(semanticDB: SemanticDB, className: String, termString: String) = {
    var parents = getAllParentSymbs(semanticDB, className)
    parents = parents.map(p => {
      assert(p.endsWith("#"));
      p.substring(0, p.length - 1)
    })
    parents.exists(p => termString.startsWith(p))
  }

  def isGetterSetterCall(methodOrAttributeName: String): Boolean = {
    return methodOrAttributeName != null && (StringUtils.startsWithAny(methodOrAttributeName, "get", "is", "set")
      || StringUtils.endsWith(methodOrAttributeName, "_=") // Needed for org/shotdraw/application/DrawApplication#`fDefaultToolButton_=`().
      //                                                              Probably becouse var is private, it gets wrapped
      )
  }

  def externalProperties(className: String, tree: Tree, semanticDB: SemanticDB, sdoc: SemanticDocument) = {
    var collectedProperties: ListBuffer[String] = ListBuffer.empty[String]

    tree.collect({
      case term: Term.Name => {
        val termSymbol = term.symbol(sdoc)
        if (!termSymbol.isNone && !term.isDefinition) {
          if (!termSymbol.isLocal && !symbolInParentHiarchy(semanticDB, className, termSymbol.value)) {
            val decodedPropName = termSymbol.value

            // TODO: Ignore properies from nested classes
            //if (doWeOwnThisClass(decodedPropName))
            {
              val info = semanticDB.getInfo(termSymbol.value)
              if (info != null) {
                if (info.kind == SymbolInformation.Kind.FIELD) {
                  collectedProperties += decodedPropName
                }
                else if (info.kind == SymbolInformation.Kind.METHOD) {
                  val params = info.signature.asInstanceOf[semanticdb.MethodSignature].parameterLists
                  val noBraces = params == scala.collection.immutable.Nil

                  if (noBraces || isGetterSetterCall(info.displayName)) {
                    collectedProperties += decodedPropName
                  }
                }
              }
            }
          }
        }
      }
    })
    collectedProperties
  }

  // Shares many LOC with externalProperties. Only symbolInParentHiarchy isn't negated
  // Pure internal properties, local variables are not counted
  def internalProperties(className: String, tree: Tree, semanticDB: SemanticDB, sdoc: SemanticDocument) = {
    var collectedProperties: ListBuffer[String] = ListBuffer.empty[String]
    if (className.contains("DrawApplication")) {
      if (tree.toString().contains("def setDefaultTool")) {
        println()
      }
    }
    tree.collect({
      case term: Term.Name => {
        val termSymbol = term.symbol(sdoc)
        if (className.contains("DrawApplication")) {
          if (tree.toString().contains("def setDefaultTool")) {
            println(termSymbol.value)
            if (termSymbol.value.contains("fDefaultToolButton")) {
              println()
            }
          }
        }
        if (!termSymbol.isNone && !term.isDefinition) {
          if (!termSymbol.isLocal && symbolInParentHiarchy(semanticDB, className, termSymbol.value)) {
            val decodedPropName = termSymbol.value

            // TODO: Ignore properies from nested classes
            //if (doWeOwnThisClass(decodedPropName))
            {
              val info = semanticDB.getInfo(termSymbol.value)
              if (info != null) {
                if (info.kind == SymbolInformation.Kind.FIELD) {
                  collectedProperties += decodedPropName
                }
                else if (info.kind == SymbolInformation.Kind.METHOD) {
                  val params = info.signature.asInstanceOf[semanticdb.MethodSignature].parameterLists
                  val noBraces = params == scala.collection.immutable.Nil

                  if (noBraces || isGetterSetterCall(info.displayName)) {
                    collectedProperties += decodedPropName
                  }
                }
              }
            }
          }
        }
      }
    })
    collectedProperties
  }

  // Shares many LOC with externalProperties
  def localProperties(clazz: Defn.Class, d: Defn.Def, semanticDB: SemanticDB, sdoc: SemanticDocument) = {
    val cSymbol = semanticDB.getFromSymbolTable(clazz, sdoc)
    var collectedProperties: Set[String] = Set.empty[String]

    d.body.collect({
      case term: Term.Name => {
        val termSymbol = semanticDB.getFromSymbolTable(term, sdoc)
        if (!termSymbol.isNone) {
          if (termSymbol.isLocal) {
            val decodedPropName = termSymbol.value
            // TODO: Check if in parent hiarchy
            // TODO: Ignore properies from nested classes
            //if (doWeOwnThisClass(decodedPropName)){
            val info = semanticDB.getInfo(termSymbol.value)
            if (info != null && info.kind != SymbolInformation.Kind.OBJECT && info.kind != SymbolInformation.Kind.PACKAGE) {
              collectedProperties += decodedPropName
            }
            //}
          }
        }
      }
    })
    collectedProperties
  }

  class MethodNodeWithUsages(val name: String) {
    var usesProperty: ListBuffer[String] = ListBuffer.empty[String]

    override def toString: String = {
      "MethodNodeWithUsages(" + name + ")\n" +
        usesProperty.mkString("\n")
    }
  }

  /**
    * TCC
    */
  def calculateCohesion(clazz: Defn.Class, semanticDB: SemanticDB, sdoc: SemanticDocument): Double = {

    implicit val implicit_ksjndflidbfkurhgb: SemanticDocument = sdoc
    val cSymbol = semanticDB.getFromSymbolTable(clazz, sdoc)


    val className = MeasureProject.traitOrClassName(clazz)
    var clazzSymbol = clazz.symbol
    assert(clazzSymbol.value.endsWith("#"))
    val compagnionRefString = clazzSymbol.value.substring(0, clazzSymbol.value.length - 1) + "."
    val compagnionTree = semanticDB.getClassTraitObjectTree(compagnionRefString)

    if (className.contains("PolygonFigure")) {
      /*var symInfoOpt = semanticDB.symbolTable.info(clazzSymbol.value)
      if (symInfoOpt.isDefined) {
        var symInfo = symInfoOpt.get
        val dbg = Utils.toStringRecursive(symInfo)
        //symInfo.withProperties()
        var comp = symInfo.companion
      }*/
      print("")
    }

    var methods = ListBuffer.empty[MethodNodeWithUsages]

    def collectDefn(d: Defn.Def) = {
      val dSymbol = semanticDB.getFromSymbolTable(d, sdoc)
      if (!dSymbol.isLocal) {
        var mNode = new MethodNodeWithUsages(dSymbol.value)

        val methodInternalPropsSet = internalProperties(clazz.symbol.value, d, semanticDB, sdoc)
        mNode.usesProperty = methodInternalPropsSet
        if (clazz.toString().contains("DrawApplication")) {
          if (d.toString().contains("def setDefaultTool")) {
            println(d.toString())
          }
        }
        /*d.body.collect({
        case term: Term.Name => {
          val termSymbol = semanticDB.getFromSymbolTable(term, sdoc)
          if (!termSymbol.isLocal && !symbolInParentHiarchy(semanticDB, clazz.symbol.value, termSymbol.value)) {
            val decodedPropName = termSymbol.value
              .replace(cSymbol.value + "`", "")
              .replace(cSymbol.value, "")
              .replace("_=`().", "")
              .replace("().", "")
            mNode.usesProperty += decodedPropName
          }
        }
      })*/
        methods += mNode
      }
    }

    clazz.collect({
      case d: Defn.Def => collectDefn(d)
    })
    if (compagnionTree != null) {
      compagnionTree.collect({
        case d: Defn.Def => collectDefn(d)
      })
    }
    println(("methods: \n" + methods.map(x => x.toString.replace("\n", "\n\t")).mkString("\n"))
      .replace("\n", "\n\t"))

    val pairsWithCommon = numMethodsRelatedByAttributeAccess(methods)
    val maxPairs = methods.size * (methods.size - 1) / 2

    var cohesion = pairsWithCommon.toDouble / maxPairs
    if (clazz.toString().contains("DrawApplication")) {
      println()
    }

    if (cohesion.isNaN || cohesion.isInfinite) cohesion = 0
    cohesion
  }

  def numMethodsRelatedByAttributeAccess(methods: ListBuffer[MethodNodeWithUsages]): Int = {
    var methodCount = methods.size
    var pairs = 0
    if (methodCount > 1) {
      for (i <- 0 until (methodCount - 1)) {
        for (j <- i + 1 until methodCount) {
          var firstMethod = methods(i)
          val secondMethod = methods(j)
          val intersection = firstMethod.usesProperty.intersect(secondMethod.usesProperty)
          if (intersection.size > 0)
            pairs += 1
        }
      }
    }
    pairs
  }
}
