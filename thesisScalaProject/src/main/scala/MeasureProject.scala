import java.io.File
import java.text._

import scalafix.v1.SemanticDocument
import scalafix.{DocumentTuple, SemanticDB}
import scalafix.v1._ // for the symbol magic

import scala.collection.mutable.ArrayBuffer
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
      case c: Defn.Def =>
        var calls = Set.empty[String]
        var classes = Set.empty[String]
        //var defSymbol = sDb.getFromSymbolTable(c, sdoc)
        //println("\ndefSymbol: " + defSymbol)
        c.collect {
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
    !classUri.startsWith("java/") &&
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
      implicit val semisdufjgdsifughsidf: SemanticDocument = doc.sdoc
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
          if (CC >= 2) {
            println("Big CC found: " + pair._1 + " -> " + CC)
            println("\n\n" + CfgPerMethod.nodesToGraphViz(pair._2))
          }
          //println(pair._1 + ": CC= " + CC)
          commitStats.cc += CC
        }
      }

      if (true) { // Check design smells
        doc.sdoc.tree.collect {
          case c: Defn.Class => {
            val className = MeasureProject.traitOrClassName(c)
            if (!className.endsWith("Test")  // Naming convension for test classes
              && !c.symbol.isLocal) { // Scala meta doesn't keep semantic onformation about inline classes :(
              println("Class: " + c.name.value)

              var cc = 0
              val methodMap = CfgPerMethod.compute(c) //doc.sdoc.tree)
              for (pair <- methodMap) {
                cc += CfgPerMethod.calculateCC(pair._2)
              }
              val classExternalPropsSet = externalProperties(c.symbol.value, c, semanticDB, doc.sdoc)
              val classExternalProps = classExternalPropsSet.size
              val cohesion = calculateCohesion(c, semanticDB, doc.sdoc)

              if (classExternalProps > 20
                && cc > 30
                && cohesion < 0.3) {
                println("\nGodclass detected! " + c.name.toString())
                println("    cc: " + cc)
                println("    classExternalProps: " + classExternalProps)
                println("    cohesion: " + cohesion)
              }

              c.collect {
                case d: Defn.Def =>
                  println("Def: " + d.name.value)

                  val methodExternalPropsSet = externalProperties(c.symbol.value, d, semanticDB, doc.sdoc)
                  println("methodExternalPropsSet: " + methodExternalPropsSet)
                  val methodExternalProps = methodExternalPropsSet.size
                  val methodExternalPropsClasses = {
                    var set: Set[String] = Set.empty[String]
                    for (e <- methodExternalPropsSet) {
                      set += propGetOwnerClass(e)
                    }
                    set
                  }
                  val methodInternalPropsSet = internalProperties(c.symbol.value, d, semanticDB, doc.sdoc)
                  val methodInternalProps = methodInternalPropsSet.size
                  println("methodInternalPropsSet: " + methodInternalPropsSet)

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

                  val methodLocalPropsSet = localProperties(c, d, semanticDB, doc.sdoc)
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
    var symInfo = semanticDB.symbolTable.info(symbolString).get
    if (!symInfo.signature.isInstanceOf[scala.meta.internal.semanticdb.ClassSignature]) return Seq.empty // Gave an error with "final case class RuntimeException"
    var parents = symInfo.signature.asInstanceOf[scala.meta.internal.semanticdb.ClassSignature].parents
    parents.map(x => x.asInstanceOf[semanticdb.TypeRef].symbol)
  }

  def getAllParentSymbs(semanticDB: SemanticDB, symbolString: String): Set[String] = {
    var collectedParents = Set.empty[String]

    var depth = 0

    def collectParentsRecurse(currentSymbol: String): Unit = {
      if (!collectedParents.contains(currentSymbol)) {
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
      case _ => throw new Exception("Not a trait Or Class Name.")
    }
  }

  def symbolInParentHiarchy(semanticDB: SemanticDB, className: String, termString: String) = {
    val parents = getAllParentSymbs(semanticDB, className)
    parents.exists(p => termString.startsWith(p))
  }

  def externalProperties(className: String, tree: Tree, semanticDB: SemanticDB, sdoc: SemanticDocument) = {
    var collectedProperties: Set[String] = Set.empty[String]

    tree.collect({
      case term: Term.Name => {
        val termSymbol = term.symbol(sdoc)
        if (!termSymbol.isNone && !term.isDefinition) {

          if (!termSymbol.isLocal && !symbolInParentHiarchy(semanticDB, className, termSymbol.value)) {
            val decodedPropName = termSymbol.value

            // TODO: Ignore properies from nested classes
            if (doWeOwnThisClass(decodedPropName)) {
              val info = semanticDB.getInfo(termSymbol, sdoc)
              if (info.kind != SymbolInformation.Kind.OBJECT) {
                collectedProperties += decodedPropName
              }
            }
          }

        }
      }
    })
    collectedProperties
  }

  // Shares many LOC with externalProperties
  // Pure internal properties, local variables are not counted
  def internalProperties(className: String, tree: Tree, semanticDB: SemanticDB, sdoc: SemanticDocument) = {
    var collectedProperties: Set[String] = Set.empty[String]

    tree.collect({
      case term: Term.Name => {
        val termSymbol = term.symbol(sdoc)
        if (!termSymbol.isNone && !term.isDefinition) {

          if (!termSymbol.isLocal && symbolInParentHiarchy(semanticDB, className, termSymbol.value)) {
            val decodedPropName = termSymbol.value

            // TODO: Ignore properies from nested classes
            if (doWeOwnThisClass(decodedPropName)) {

              val info = semanticDB.getInfo(termSymbol, sdoc)
              if (info.kind != SymbolInformation.Kind.OBJECT) {
                collectedProperties += decodedPropName
              }
            }
          }

        }
      }
    })
    collectedProperties
  }

  // Shares many LOC with externalProperties
  def localProperties(c: Defn.Class, d: Defn.Def, semanticDB: SemanticDB, sdoc: SemanticDocument) = {
    val cSymbol = semanticDB.getFromSymbolTable(c, sdoc)
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
            val info = semanticDB.getInfo(termSymbol, sdoc)
            if (info.kind != SymbolInformation.Kind.OBJECT) {
              collectedProperties += decodedPropName
            }
            //}
          }
        }
      }
    })
    collectedProperties
  }

  def calculateCohesion(c: Defn.Class, semanticDB: SemanticDB, sdoc: SemanticDocument): Double = {

    val cSymbol = semanticDB.getFromSymbolTable(c, sdoc)

    class MethodNodeWithUsages(val name: String) {
      var usesProperty: Set[String] = Set.empty[String]

      override def toString: String = {
        "MethodNodeWithUsages(" + name + ")\n" +
          usesProperty.mkString("\n")
      }
    }
    var methods = Set.empty[MethodNodeWithUsages]

    c.collect({
      case d: Defn.Def => {
        val dSymbol = semanticDB.getFromSymbolTable(d, sdoc)
        var mNode = new MethodNodeWithUsages(dSymbol.value)

        d.body.collect({
          case term: Term.Name => {
            val termSymbol = semanticDB.getFromSymbolTable(term, sdoc)
            if (termSymbol.owner == cSymbol) {
              val decodedPropName = termSymbol.value
                .replace(cSymbol.value + "`", "")
                .replace(cSymbol.value, "")
                .replace("_=`().", "")
                .replace("().", "")
              mNode.usesProperty += decodedPropName
            }
          }
        })
        methods += mNode
      }
    })
    //println(methods.mkString("\n\n"))

    var pairsWithCommon = 0
    for (m1 <- methods) {
      for (m2 <- methods) {
        if (m1 != m2) {
          val intersection = m1.usesProperty.intersect(m2.usesProperty)
          if (intersection.size > 0)
            pairsWithCommon += 1
        }
      }
    }
    var cohesion = pairsWithCommon.toDouble / (methods.size * (methods.size - 1))
    if (cohesion.isNaN || cohesion.isInfinite) cohesion = 0
    cohesion
  }
}
