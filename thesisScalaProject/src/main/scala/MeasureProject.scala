import java.io.File
import java.text._

import scalafix.{CfgPerMethod, SemanticDB}

import scala.language.postfixOps
import scala.meta._
//import scala.xml._

object MeasureProject {

  def fillInPyramidTemplate(file: String, commitStats: CommitStats, projectName: String): String = {
    var svg = file
    val nop = commitStats.nop_set.size
    val noc = commitStats.noc_set.size
    val nom = commitStats.nom_set.size
    val loc = commitStats.loc
    val cc = commitStats.cc_set.sum

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
    svg = svg.replaceAll("%FANOUT%", "") //commitStats.fanout.toString

    svg = svg.replaceAll("%NOC/NOP%", df.format(noc.toDouble / nop))
    svg = svg.replaceAll("%NOM/NOC%", df.format(nom.toDouble / noc))
    svg = svg.replaceAll("%LOC/NOM%", df.format(loc.toDouble / nom))
    svg = svg.replaceAll("%CYCLO/LOC%", df.format(cc.toDouble / loc))
    svg = svg.replaceAll("%CALLS/NOM%", df.format(commitStats.calls.toDouble / nom))
    svg = svg.replaceAll("%FANOUT/CALLS%", "") // df.format(commitStats.fanout.toDouble / commitStats.calls))


    val redStr = "fill:#ff2700;"
    val greenStr = "fill:#008f00;"
    val blueStr = "fill:#0091ff;"

    var doc = XmlUtil.parseXmlFromString(svg)

    // Based on statistical foundinfs in java
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

  def consumeFile(commitStats: CommitStats, tree: Tree) = {

    val packageCollection = tree.collect {
      case q: Pkg => q.name
    }
    packageCollection.foreach(x => commitStats.nop_set += x.toString)

    val classCollection = tree.collect {
      case q: Defn.Class => q.name
      case q: Defn.Object => q.name
    }
    classCollection.foreach(x => commitStats.noc_set += x.toString)

    var applysForFanout = 0
    tree.collect {
      case c: Defn.Class =>
        var set = Set.empty[String]
        c.collect {
          case a: Term.Apply => set += a.fun.toString()
        }
        applysForFanout += set.size
      case c: Defn.Object =>
        var set = Set.empty[String]
        c.collect {
          case a: Term.Apply => set += a.fun.toString()
        }
        applysForFanout += set.size
    }
    commitStats.calls += applysForFanout

    val functionCollection = tree.collect {
      case q: Defn.Def => q.name
    }
    functionCollection.foreach(x => commitStats.nom_set += x.toString)

    commitStats.loc += tree.toString.count(x => x == '\n')
  }

  def doStatsForProject(projectPath: File, projectName: String): CommitStats = {

    val commitStats = new CommitStats

    /*
    val scalaFiles = Utils.recursiveListFiles(projectPath)

    for (f <- scalaFiles) {
      if (f.toString.endsWith(".scala")) {
        //println(f)

        val bytes = java.nio.file.Files.readAllBytes(f.toPath)
        val text = new String(bytes, "UTF-8")
        val input = Input.VirtualFile(f.toString, text)
        val tree = input.parse[Source].get

        consumeFile(commitStats, tree)

        val methodMap = CfgPerMethod.compute(tree)

        val relative = projectPath.toPath.relativize(f.toPath)
        val gvPath = "C:\\Users\\emill\\Dropbox\\slimmerWorden\\2018-2019-Semester2\\THESIS\\gv\\" + projectName + "\\" + relative + ".gv"
        Utils.writeFile(gvPath, CfgPerMethod.MethodMapToGraphViz(methodMap))

        for (pair <- methodMap) {
          val CC = CfgPerMethod.calculateCC(pair._2)
          if (CC > 20) {
            println("Big CC found: " + pair._1 + " -> " + CC)
            println("\n\n" + CfgPerMethod.nodesToGraphViz(pair._2))
          }
          //println(pair._1 + ": CC= " + CC)
          commitStats.cc_set += CC
        }
      }
    }
    */
    var scalaRoot = Utils.normalizeDirectoryPath(projectPath.getAbsolutePath)
    if (scalaRoot.endsWith("src/main/scala/"))
      scalaRoot = scalaRoot.substring(0, scalaRoot.length - "src/main/scala/".length)

    //Utils.execCommand("cd " + getGitTopLevel(new File(scalaRoot)) + " && sbt semanticdb").trim

    val semanticDB = new SemanticDB(new File(scalaRoot))
    val documents = semanticDB.documents

    val th = new TypeHiarchy
    for (main_doc <- documents) {
      println("\n Doc: " + main_doc.tdoc.uri)
      val doc = semanticDB.reload(main_doc.tdoc.uri)

      consumeFile(commitStats, doc.sdoc.tree)

      th.calculate(doc, semanticDB.symbolTable)

      //val methodMap = CfgPerMethod.compute(doc.sdoc.tree)
      //
      //for (pair <- methodMap) {
      //  val CC = CfgPerMethod.calculateCC(pair._2)
      //  println(pair._1 + ": CC= " + CC)
      //  commitStats.cc_set += CC
      //}
    }
    commitStats.andc = th.calculateANDC()
    commitStats.ahh = th.calculateAHH()
    Utils.writeFile("C:\\Users\\emill\\Dropbox\\slimmerWorden\\2018-2019-Semester2\\THESIS\\gv\\types_" + projectName + ".gv", th.getGvString())


    commitStats
  }
}