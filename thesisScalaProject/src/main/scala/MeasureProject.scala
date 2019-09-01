import java.io.File
import java.text._

import org.apache.commons.lang3.StringUtils
import scalafix.v1.SemanticDocument
import scalafix.{DocumentTuple, SemanticDB}
import scalafix.v1._
import slickEmileProfile.Tables

import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import scala.language.postfixOps
import scala.meta.Term.Block
import scala.meta._
import scala.meta.internal.semanticdb
import scala.meta.internal.semanticdb.SymbolInformation
import scala.meta.transversers.SimpleTraverser


trait ClassOrTrait extends Tree {}

object MeasureProject {

  def fillInPyramidTemplate(svgString: String, commitStats: CommitStats): String = {
    var svg = svgString
    val nop = commitStats.nop_set.size.toDouble
    val noc = commitStats.noc_set.size.toDouble
    val nom = commitStats.nom_set.size.toDouble
    val loc = commitStats.loc.toDouble
    val cc = commitStats.cc.toDouble
    val andc = commitStats.andc
    val ahh = commitStats.ahh
    val calls = commitStats.calls
    val fanout = commitStats.fanout

    val df = new DecimalFormat(".##")

    svg = svg.replaceAll("%PROJECT%", commitStats.projectName)

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

    /**
      * From iPlasma.
      * C:\Users\emill\dev\maproef1819-emile\iPlasma_decompiled\decompiled_result\src\lrg\insider\plugins\tools\OverviewPyramid.java
      */
    def getColor(value: Double, min: Double, avg: Double, max: Double): String = {
      val dist_min = Math.abs(value - min)
      val dist_avg = Math.abs(value - avg)
      val dist_max = Math.abs(value - max)
      if (dist_min < dist_avg) "#0000CC" // Blue
      else if (dist_avg < dist_max) "#006600" // Green
      else "#CC0000" // Red
    }

    def colorGradeIPLasma(xpath: String, value: Double, low: Double, high: Double, avg: Double): Unit = {
      val attr = XmlUtil.xpathGetNode(doc, xpath).get
      attr.setTextContent(attr.getTextContent.replace(greenStr, "fill:" + getColor(value, low, avg, high) + ";"))
    }

    def colorGrade(xpath: String, value: Double, low: Double, high: Double, ignore_avg: Double): Unit = {
      colorGradeIPLasma(xpath, value, low, high, ignore_avg)
      //val attr = XmlUtil.xpathGetNode(doc, xpath).get
      //if (value < low)
      //  attr.setTextContent(attr.getTextContent.replace(greenStr, blueStr))
      //else if (value > high)
      //  attr.setTextContent(attr.getTextContent.replace(greenStr, redStr))
    }

    // Scala tresholds from all_plots_scala.pdf
    // colorGrade("//rect[@label=\"bg_CycloPerLoc\"]/@style", cc / loc, 0.1, 0.38, 0.24)
    // colorGrade("//rect[@label=\"bg_LocPerNom\"]/@style", loc / nom, 4.96, 17.46, 11.21)
    // colorGrade("//rect[@label=\"bg_NomPerNoc\"]/@style", nom / noc, 1.9, 4.43, 3.17)
    // colorGrade("//rect[@label=\"bg_NocPerNop\"]/@style", noc / nop, 11.13, 26.39, 18.76)
    // colorGrade("//rect[@label=\"bg_CallsPerNom\"]/@style", calls / nom, 1.17, 2.47, 1.82)
    // colorGrade("//rect[@label=\"bg_FanoutPerCalls\"]/@style", fanout / calls, 1.03, 6.15, 2.09)
    // colorGrade("//rect[@label=\"bg_Andc\"]/@style", andc, 0.63, 1.81, 1.22)
    // colorGrade("//rect[@label=\"bg_Ahh\"]/@style", ahh, 0.10, 0.26, 0.18)

    // New Scala tresholds
    colorGrade("//rect[@label=\"bg_NocPerNop\"]/@style", noc / nop, 12.91, 32.71, 22.81)
    colorGrade("//rect[@label=\"bg_NomPerNoc\"]/@style", nom / noc, 1.91, 4.13, 3.02)
    colorGrade("//rect[@label=\"bg_LocPerNom\"]/@style", loc / nom, 4.71, 16.79, 10.75)
    colorGrade("//rect[@label=\"bg_CycloPerLoc\"]/@style", cc / loc, 0.1, 0.38, 0.24)
    colorGrade("//rect[@label=\"bg_CallsPerNom\"]/@style", calls / nom, 1.15, 2.42, 1.78)
    colorGrade("//rect[@label=\"bg_FanoutPerCalls\"]/@style", fanout / calls, 1.02, 3.06, 2.04)
    colorGrade("//rect[@label=\"bg_Andc\"]/@style", andc, 0.63, 1.77, 1.2)
    colorGrade("//rect[@label=\"bg_Ahh\"]/@style", ahh, 0.1, 0.25, 0.17)

    // Java tresholds
    //colorGrade("//rect[@label=\"bg_NocPerNop\"]/@style", noc / nop, 5.66, 12.41, 9.04)
    //colorGrade("//rect[@label=\"bg_NomPerNoc\"]/@style", nom / noc, 5.37, 17.92, 11.64)
    //colorGrade("//rect[@label=\"bg_LocPerNom\"]/@style", loc / nom, 3.73, 13.22, 8.48)
    //colorGrade("//rect[@label=\"bg_CycloPerLoc\"]/@style", cc / loc, 0.07, 0.34, 0.2)
    //colorGrade("//rect[@label=\"bg_CallsPerNom\"]/@style", calls / nom, 1.32, 3.41, 2.37)
    //colorGrade("//rect[@label=\"bg_FanoutPerCalls\"]/@style", fanout / calls, 0.11, 0.67, 0.39)
    //colorGrade("//rect[@label=\"bg_Andc\"]/@style", andc, 0.15, 0.28, 0.21)
    //colorGrade("//rect[@label=\"bg_Ahh\"]/@style", ahh, 0.07, 0.16, 0.12)

    return XmlUtil.documentToString(doc)
  }


  private def consumeFile(commitStats: CommitStats, sdoc: SemanticDocument, semanticDB: SemanticDB): Unit = {
    implicit val implicit_sdoc_fkurhgbsdf: SemanticDocument = sdoc

    val packageCollection = sdoc.tree.collect {
      case q: Pkg => q.name
    }
    packageCollection.foreach(x => commitStats.nop_set += x.toString)


    val classCollection = sdoc.tree.collect {
      case q: Defn.Class => q.name.toString()
      case q: Defn.Object => q.name.toString()
      //case q: Defn.Trait => q.name // TODO: Yes/No?
    }
    classCollection.foreach(x => commitStats.noc_set += x)


    def descendDefOrCtor(tree: Tree, symb: Symbol): Unit = {

      //!symb.isLocal && // probably local variables need to be counted too
      if (!symb.isNone)
        commitStats.nom_set += symb.value

      commitStats.loc += tree.toString().count(x => x == '\n')


      var calls = Set.empty[String] // if a method calls a function 3 times, it should only be mesured once
      var classes = Set.empty[String] // if a method refers a class 3 times, it should only be mesured once
      tree.collect {
        case a: Term.Name => {

          val s = SemanticDB.getFromSymbolTable(a, sdoc);
          if (!s.isNone && doWeOwnThisClass(s.value)) {
            val info = semanticDB.getInfo(s.value)
            if (info != null && info.kind == SymbolInformation.Kind.METHOD) {
              //print("CALL: " + s.value)
              //if (s.isLocal) println(" isLocal") // never gets triggered :(
              //else println()
              calls += s.value
            }
          }
        }

        case n: Term.New => {
          var tpe = n.init.tpe

          var s = SemanticDB.getFromSymbolTable(tpe, sdoc)
          try {
            if (doWeOwnThisClass(s.value)) {
              val classWeAreIn = symb.owner.value
              if (classWeAreIn != s.value)
                classes += s.value
            }
          } catch {
            case ex: Throwable =>
              println("EXCEPTION: " + ex)
          }

        }
        case a: Term.Apply => {

          var s = SemanticDB.getFromSymbolTable(a.fun, sdoc)

          try {
            if (doWeOwnThisClass(s.value)) {

              val info = semanticDB.getInfo(s.value)
              val tmp = if (info != null && info.kind == SymbolInformation.Kind.OBJECT) {
                s.value
              } else {
                s.owner.value
              }
              val classWeAreIn = symb.owner.value
              if (classWeAreIn != tmp)
                classes += tmp
            }
          } catch {
            case ex: Throwable => println("EXCEPTION: " + ex)
          }
        }
      }
      commitStats.calls += calls.size
      commitStats.fanout += classes.size
    }

    sdoc.tree.collect {
      //case a: Ctor.Primary => {
      //  descendDefOrCtor(a)
      //}
      case a: Ctor.Secondary =>
        val symb: Symbol = SemanticDB.getFromSymbolTable(a, sdoc)
        for (stat <- a.stats)
          descendDefOrCtor(stat, symb)

      case defn: Defn.Def =>
        val symb: Symbol = SemanticDB.getFromSymbolTable(defn, sdoc)
        descendDefOrCtor(defn.body, symb)
    }

    if (true) { // CC and Code Flow Graph
      val methodMap = CfgPerMethod.compute(sdoc.tree)

      //val relative = Cmd.getRelativePathToProject(sdoc.input.asInstanceOf[Input.File].path.toNIO)
      //val gvPath = "C:\\Users\\emill\\Dropbox\\slimmerWorden\\2018-2019-Semester2\\THESIS\\out\\gv\\" + relative + ".gv"
      //Utils.writeFile(gvPath, CfgPerMethod.MethodMapToGraphViz(methodMap))

      for (pair <- methodMap) {
        val CC = CfgPerMethod.calculateCC(pair._2)
        commitStats.cc += CC
      }

    }


    if (true) { // Check design smells
      sdoc.tree.collect {

        case clazz: Defn.Trait => {
          val className = MeasureProject.traitOrClassName(clazz)
          if (className.contains("Drawing")) {
            var clazzSymbol = clazz.symbol
            var symInfo = semanticDB.symbolTable.info(clazzSymbol.value).get
            print("")
          }
        }
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

            var locInClass = countLocInClass(clazz)

            var cc = 0
            val methodMap = CfgPerMethod.compute(clazz)
            for (pair <- methodMap) {
              cc += CfgPerMethod.calculateCC(pair._2)
            }
            val classExternalPropsSet = externalProperties(clazz.symbol.value, clazz, semanticDB, sdoc, areParentSymbolsConcideredInternal = false)
            val classExternalProps = classExternalPropsSet.size
            val cohesion = calculateCohesion(clazz, semanticDB, sdoc)

            val log = (""
              + " cc: " + cc + "\n"
              + " classExternalPropsSet: " + classExternalPropsSet + "\n"
              + " classExternalProps: " + classExternalProps + "\n"
              + " cohesion: " + cohesion)

            // Numbers come from PMD
            if (classExternalProps > 5 // Few means between 2 and 5. See: Lanza. Object-Oriented Metrics in Practice. Page 18.
              && cc > 45 // Very high threshold for WMC (Weighted Method Count). See: Lanza. Object-Oriented Metrics in Practice. Page 16.
              && cohesion < 1.0 / 3.0) {
              println(log)
              LargeScaleDb.insertRow(Tables.DetectedSmellRow(0, commitStats.commitHash, clazz.symbol.value, "GodClass", log))
            }

            clazz.collect {
              case d: Defn.Def =>
                println("Def: " + d.name.value)

                val methodExternalPropsSetAll = externalProperties(clazz.symbol.value, d, semanticDB, sdoc)
                val methodExternalPropsSet = methodExternalPropsSetAll.filter(doWeOwnThisClass) // We can envy the standard library, but we can't realy do anything about it

                val methodExternalProps = methodExternalPropsSet.size
                val methodExternalPropsClasses = {
                  var set: Set[String] = Set.empty[String]
                  for (e <- methodExternalPropsSet) {
                    set += propGetOwnerClass(e)
                  }
                  set
                }
                val methodInternalPropsSet = internalProperties(clazz.symbol.value, d, semanticDB, sdoc)
                val methodInternalProps = methodInternalPropsSet.size

                assert(methodExternalPropsSetAll.intersect(methodInternalPropsSet).size == 0)

                val LAA = {
                  if (methodExternalProps + methodInternalProps == 0) Double.PositiveInfinity
                  else methodInternalProps.toDouble / (methodExternalProps.toDouble + methodInternalProps.toDouble)
                }


                if (methodExternalProps > 4 // Few means between 2 and 5. See: Lanza. Object-Oriented Metrics in Practice. Page 18.
                  && LAA < 1.0 / 3.0
                  && methodExternalPropsClasses.size <= 4) { // Few means between 2 and 5. See: Lanza. Object-Oriented Metrics in Practice. Page 18.

                  val log = ("FeatureEnvy detected! " + d.name.toString() + "\n"
                    + "    methodExternalPropsSet: " + methodExternalPropsSet + "\n"
                    + "    methodExternalProps: " + methodExternalProps + "\n"
                    + "    methodInternalPropsSet: " + methodInternalPropsSet + "\n"
                    + "    methodInternalProps: " + methodInternalProps + "\n"
                    + "    LAA: " + LAA + "\n"
                    + "    methodExternalPropsClasses.size: " + methodExternalPropsClasses.size)
                  LargeScaleDb.insertRow(Tables.DetectedSmellRow(0, commitStats.commitHash, d.symbol.value, "FeatureEnvy", log))
                }

                val methodNodes = CfgPerMethod.compute(d).head._2
                val methodCc = CfgPerMethod.calculateCC(methodNodes)

                val loc = d.toString.count(x => x == '\n')

                val methodLocalPropsSet = localProperties(clazz, d, semanticDB, sdoc)
                val MAXNESTING = calculateNestingLevel(d)
                // Number Of Accessed Variables
                val NOAV = methodLocalPropsSet.size + methodInternalProps + methodExternalProps

                if (loc > locInClass.toDouble / 2
                  && methodCc > 10 // TODO: Verify if this indeed is a "VERY HIGH" CC
                  && MAXNESTING > 3 // SEVERAL means 2-5
                  && NOAV > 2 // TODO: Verify if this indeed is "MANY"
                ) {

                  val log = ("BrainMethod detected! " + d.name.toString() + "\n"
                    + "    loc: " + loc + "\n"
                    + "    locInClass: " + locInClass + "\n"
                    + "    methodCc: " + methodCc + "\n"
                    + "    MAXNESTING: " + MAXNESTING + "\n"
                    + "    NOAV: " + NOAV)
                  LargeScaleDb.insertRow(Tables.DetectedSmellRow(0, commitStats.commitHash, d.symbol.value, "BrainMethod", log))
                }
            }
          }
        }
      }
    }
  }

  /**
    * Todo: Use project data.
    */
  def doWeOwnThisClass(classUri: String): Boolean = {
    !classUri.startsWith("java/") &&
      !classUri.startsWith("javax/") &&
      !classUri.startsWith("scala/")
  }

  def doStatsForProject(projectPath: File, commitStats: CommitStats): CommitStats = {

    LargeScaleDb.removeDetectedSmellsForCommit(commitStats.commitHash)
    //commitStats.powershell_LOC = Cmd.getPowershellLoc(new File(projectPath.getAbsolutePath + "\\src\\main\\scala"), ".scala")
    //commitStats.regexDefMatches = MeasureProject.getRegexDefMatchesInFolder(projectPath)


    var scalaRoot = Utils.normalizeDirectoryPath(projectPath.getAbsolutePath)
    if (scalaRoot.endsWith("src/main/scala/"))
      scalaRoot = scalaRoot.substring(0, scalaRoot.length - "src/main/scala/".length)

    val semanticDB = new SemanticDB(new File(scalaRoot))

    if (true) {
      val th = new TypeHiarchy(semanticDB)
      for (doc <- semanticDB.documents) {
        th.absorb(doc)
      }
      Utils.writeFile("C:\\Users\\emill\\Dropbox\\slimmerWorden\\2018-2019-Semester2\\THESIS\\out\\gv\\types_" + commitStats.projectName + ".gv", th.getGvString())

      commitStats.andc = th.calculateANDC()
      commitStats.ahh = th.calculateAHH()
    }

    for (doc <- semanticDB.documents) {
      println("\nDoc: " + doc.tdoc.uri)

      consumeFile(commitStats, doc.sdoc, semanticDB)
    }

    commitStats
  }

  def calculateNestingLevel(methodTree: Tree): Int = {
    var maxDepth = 0
    var currentDepth = 0

    def rec(tree: Tree): Unit = {
      var goingDeeper = tree match {
        case _: Term.If => true
        case _: Term.Do => true
        case _: Term.For => true
        case _: Term.Match => true
        case _: Term.While => true
        case _ => false
      }
      if (goingDeeper) {
        currentDepth += 1
        if (currentDepth > maxDepth) maxDepth = currentDepth
      }

      tree.children.foreach(rec)

      if (goingDeeper)
        currentDepth -= 1
    }

    rec(methodTree)
    maxDepth
  }

  def countLocInClass(classTree: Tree): Int = {
    var loc = 0

    def descendDefOrCtor(tree: Tree): Unit = {
      loc += tree.toString().count(x => x == '\n')
    }

    classTree.collect {
      //case a: Ctor.Primary => {
      //  descendDefOrCtor(a)
      //}
      case a: Ctor.Secondary =>
        for (stat <- a.stats)
          descendDefOrCtor(stat)

      case defn: Defn.Def =>
        descendDefOrCtor(defn.body)
    }
    loc
  }

  def propGetOwnerClass(e: String): String = {
    var idx = e.lastIndexOf("#")
    if (!e.endsWith(".") || idx == -1)
      idx = math.max(idx, e.lastIndexOf("."))
    return e.substring(0, idx)
  }

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

  def traitOrClassName(tree: Tree): String = {
    tree match {
      case value: Defn.Trait => value.name.value
      case value: Defn.Class => value.name.value
      case value: Defn.Object => value.name.value
      case _ => throw new Exception("Not a trait Or Class Name.")
    }
  }

  def symbolInParentHiarchy(semanticDB: SemanticDB, className: String, termString: String): Boolean = {
    var parents = getAllParentSymbs(semanticDB, className)
    parents = parents.map(p => {
      assert(p.endsWith("#"))
      p.substring(0, p.length - 1)
    })
    parents.exists(p => termString.startsWith(p))
  }

  def symbolIsInThisClass(semanticDB: SemanticDB, className: String, termString: String): Boolean = {
    termString.startsWith(className)
  }

  def isGetterSetterCall(methodOrAttributeName: String): Boolean = {
    return methodOrAttributeName != null && (StringUtils.startsWithAny(methodOrAttributeName, "get", "is", "set")
      || StringUtils.endsWith(methodOrAttributeName, "_=") // Needed for org/shotdraw/application/DrawApplication#`fDefaultToolButton_=`().
      //                                                              Probably becouse var is private, it gets wrapped
      )
  }

  def externalProperties(className: String, tree: Tree, semanticDB: SemanticDB, sdoc: SemanticDocument, areParentSymbolsConcideredInternal: Boolean = true): Set[String] = {
    var collectedProperties: Set[String] = Set.empty[String] // Was ListBuffer, not sure why

    tree.collect({
      case term: Term.Name => {
        val termSymbol = term.symbol(sdoc)
        if (!termSymbol.isNone && !term.isDefinition) {
          if (!termSymbol.isLocal &&
            (if (areParentSymbolsConcideredInternal) !symbolInParentHiarchy(semanticDB, className, termSymbol.value)
            else !symbolIsInThisClass(semanticDB, className, termSymbol.value))
          ) {
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
                  info.signature match {
                    case sig: semanticdb.MethodSignature =>
                      val params = sig.parameterLists
                      val noBraces = params == scala.collection.immutable.Nil

                      if (noBraces || isGetterSetterCall(info.displayName)) {
                        collectedProperties += decodedPropName
                      }
                    case sig: semanticdb.Signature =>
                      println("ignoring unknown signature: " + sig.toString)
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
  def internalProperties(className: String, tree: Tree, semanticDB: SemanticDB, sdoc: SemanticDocument): Set[String] = {
    var collectedProperties: Set[String] = Set.empty[String] // Was ListBuffer, not sure why

    tree.collect({
      case term: Term.This => {
        val parent = term.parent.get
        parent match {
          case _: Term.Select =>
          // Cases like this.myProperty are ignored here.
          // They are already covered in the Term.Name case
          case _ =>
            val decodedPropName = className + "this."
            collectedProperties += decodedPropName
        }
      }
      case term: Term.Name => {
        val termSymbol = term.symbol(sdoc)

        if (!termSymbol.isNone && !term.isDefinition) {
          if (!termSymbol.isLocal && symbolInParentHiarchy(semanticDB, className, termSymbol.value)) {
            var decodedPropName = termSymbol.value
            if (decodedPropName.contains("=`().")) { // For example org/shotdraw/util/Bounds#`y1_=`().
              decodedPropName = decodedPropName.replace("_=`().", "().").replace("#`", "#")
            }

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
  def localProperties(clazz: Defn.Class, d: Defn.Def, semanticDB: SemanticDB, sdoc: SemanticDocument): Set[String] = {
    val cSymbol = SemanticDB.getFromSymbolTable(clazz, sdoc)
    var collectedProperties: Set[String] = Set.empty[String]

    d.body.collect({
      case term: Term.Name => {
        val termSymbol = SemanticDB.getFromSymbolTable(term, sdoc)
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
    var usesProperty: Set[String] = Set.empty[String]

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

    //val className = MeasureProject.traitOrClassName(clazz)
    var clazzSymbol = clazz.symbol
    assert(clazzSymbol.value.endsWith("#"))
    val compagnionRefString = clazzSymbol.value.substring(0, clazzSymbol.value.length - 1) + "."
    val compagnionTree = semanticDB.getClassTraitObjectTree(compagnionRefString)

    var methods = ListBuffer.empty[MethodNodeWithUsages]

    def collectDefn(d: Defn.Def) = {
      val dSymbol = SemanticDB.getFromSymbolTable(d, sdoc)
      if (!dSymbol.isLocal) {
        var mNode = new MethodNodeWithUsages(dSymbol.value)

        val methodInternalPropsSet = internalProperties(clazz.symbol.value, d, semanticDB, sdoc)
        mNode.usesProperty = methodInternalPropsSet
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

    val pairsWithCommon = numMethodsRelatedByAttributeAccess(methods)
    val maxPairs = methods.size * (methods.size - 1) / 2

    var cohesion = pairsWithCommon.toDouble / maxPairs

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

  def getRegexDefMatchesInFolder(projectPath: File): Int = {
    var regexDefMatches = 0
    var scalaFiles = Utils.recursiveGetFiles(projectPath, ".scala")
    for (file <- scalaFiles) {
      var content = Utils.readFile(file)
      var matches = """\bdef \w""".r.findAllIn(content)
      regexDefMatches += matches.length
    }
    regexDefMatches
  }
}
