import java.io.{File, FileWriter}
import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Path, Paths}
import java.text._
import java.util.Calendar

import scalafix.CfgPerMethod

import scala.language.postfixOps
import scala.meta._
import scala.sys.process._

object MeasureProject {

  def fillInPyramidTemplate(file: String, commitStats: CommitStats, projectName: String): String = {
    var svg = file
    val nop = commitStats.nop_set.size
    val noc = commitStats.noc_set.size
    val nom = commitStats.nom_set.size
    val loc = commitStats.loc
    val cc = commitStats.cc_set.sum

    svg = svg.replaceAll("%PROJECT%", projectName)

    svg = svg.replaceAll("%NOP%", nop.toString)
    svg = svg.replaceAll("%NOC%", noc.toString)
    svg = svg.replaceAll("%NOM%", nom.toString)
    svg = svg.replaceAll("%LOC%", loc.toString)
    svg = svg.replaceAll("%CYCLO%", cc.toString)
    //svg = svg.replaceAll("%FANOUT%", commitStats.fanout.toString)

    val df = new DecimalFormat(".##")
    svg = svg.replaceAll("%NOC/NOP%", df.format(noc.toDouble / nop))
    svg = svg.replaceAll("%NOM/NOC%", df.format(nom.toDouble / noc))
    svg = svg.replaceAll("%LOC/NOM%", df.format(loc.toDouble / nom))
    svg = svg.replaceAll("%CYCLO/LOC%", df.format(cc.toDouble / loc))
    //svg = svg.replaceAll("%FANOUT/CALLS%", df.format(commitStats.fanout.toDouble / commitStats.calls))

    return svg
  }

  def consumeFile(commitStats: CommitStats, exampleTree: Tree) = {

    val packageCollection = exampleTree.collect {
      case q: Pkg => q.name
    }
    packageCollection.foreach(x => commitStats.nop_set += x.toString)

    val classCollection = exampleTree.collect {
      case q: Defn.Class => q.name
      case q: Defn.Object => q.name
    }
    classCollection.foreach(x => commitStats.noc_set += x.toString)

    val applysForFanout = exampleTree.collect {
      case q: Term.Apply => q.toString()
    }
    commitStats.fanout = applysForFanout.length

    val functionCollection = exampleTree.collect {
      case q: Defn.Def => q.name

    }
    functionCollection.foreach(x => commitStats.nom_set += x.toString)

    commitStats.loc += exampleTree.toString.count(x => x == '\n')
  }

  def doStatsForProject(projectPath: File, projectName: String): CommitStats = {

    val commitStats = new CommitStats


    val scalaFiles = Utils.recursiveListFiles(projectPath)

    for (f <- scalaFiles) {
      if (f.toString.endsWith(".scala")) {
        //println(f)

        val bytes = java.nio.file.Files.readAllBytes(f.toPath)
        val text = new String(bytes, "UTF-8")
        val input = Input.VirtualFile(f.toString, text)
        val exampleTree = input.parse[Source].get

        consumeFile(commitStats, exampleTree)

        val methodMap = CfgPerMethod.compute(exampleTree)

        for (pair <- methodMap) {
          val CC = CfgPerMethod.calculateCC(pair._2)
          //println(pair._1 + ": CC= " + CC)
          commitStats.cc_set += CC
        }
      }
    }
    /*
        var scalaRoot = projectPath.getAbsolutePath.replace("\\", "/")
        if (!scalaRoot.endsWith("/")) scalaRoot += "/"
        if (scalaRoot.endsWith("src/main/"))
          scalaRoot = scalaRoot.substring(0, scalaRoot.length - "src/main/".length)

        //execCommand("cd " + getGitTopLevel(new File(scalaRoot)) + " && sbt semanticdb").trim

        val semanticDB = new SemanticDB(new File(scalaRoot))
        val documents = semanticDB.documents

        for (main_doc <- documents) {
          println("\n Doc: " + main_doc.tdoc.uri)
          val doc = semanticDB.reload(main_doc.tdoc.uri)

          consumeFile(doc.sdoc.tree)

          val methodMap = CfgPerMethod.compute(doc.sdoc.tree)

          for (pair <- methodMap) {
            val CC = CfgPerMethod.calculateCC(pair._2)
            println(pair._1 + ": CC= " + CC)
            commitStats.cc_set += CC
          }
        }
    */
    commitStats
  }
}