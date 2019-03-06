import java.util

import scala.meta.Defn._
import scala.meta._
import java.io.File
import java.nio.file.{Files, Path, Paths}
import java.nio.charset.StandardCharsets
import java.text.DecimalFormat


object main extends App {

  def getProjectName(path: Path): String = {
    var tmp = path.toString
    tmp = tmp.replace("C:\\Users\\emill\\dev\\", "")
    var firstSlah = Math.max(tmp.indexOf('\\'), tmp.indexOf('/'))
    return tmp.substring(0, firstSlah)
  }

  def doStatsForProject(projectPath: File) {
    def recursiveListFiles(f: File): Array[File] = {
      val these = f.listFiles()
      these ++ these.filter(_.isDirectory).flatMap(recursiveListFiles)
    }

    val scalaFiles = recursiveListFiles(projectPath)

    var nop_set = Set[String]()
    var noc_set = Set[String]()
    var nom_set = Set[String]()
    var loc = 0

    def consumeFile(path: Path) = {


      val bytes = java.nio.file.Files.readAllBytes(path)
      val text = new String(bytes, "UTF-8")
      val input = Input.VirtualFile(path.toString, text)
      val exampleTree = input.parse[Source].get


      val packageCollection = exampleTree.collect {
        case q: Pkg => q.name

      }
      packageCollection.foreach(x => nop_set += x.toString)
      //println(packageCollection)

      //println(exampleTree)
      val classCollection = exampleTree.collect {
        case q: Defn.Class => q.name
        case q: Defn.Object => q.name
      }
      classCollection.foreach(x => noc_set += x.toString)
      //println(classCollection)

      val functionCollection = exampleTree.collect {
        case q: Defn.Def => q.name

      }
      functionCollection.foreach(x => nom_set += x.toString)
      //println(functionCollection)

      loc += exampleTree.toString.count(x => (x == '\n'))
    }


    for (f <- scalaFiles) {
      if (f.toString.endsWith(".scala")) {
        println(f)

        //val path = java.nio.file.Paths.get(f)
        consumeFile(f.toPath)
      }
    }

    val projectName = getProjectName(projectPath.toPath)
    var nop = nop_set.size
    var noc = noc_set.size
    var nom = nom_set.size

    {
      val filename = "..\\svg\\pyramid.svg"
      var svg = scala.io.Source.fromFile(filename).getLines.mkString("\n")
      svg = svg.replaceAll("%PROJECT%", projectName)

      svg = svg.replaceAll("%NOP%", nop.toString)
      svg = svg.replaceAll("%NOC%", noc.toString)
      svg = svg.replaceAll("%NOM%", nom.toString)
      svg = svg.replaceAll("%LOC%", loc.toString)

      val df = new DecimalFormat(".##")
      svg = svg.replaceAll("%NOC/NOP%", df.format(noc.toDouble / nop))
      svg = svg.replaceAll("%NOM/NOC%", df.format(nom.toDouble / noc))
      svg = svg.replaceAll("%LOC/NOM%", df.format(loc.toDouble / nom))


      Files.write(Paths.get("..\\svg\\pyramid_" + projectName + ".svg"), svg.getBytes(StandardCharsets.UTF_8))
    }
  }

  doStatsForProject(new File("C:\\Users\\emill\\dev\\CTT-editor\\src\\main"))
  doStatsForProject(new File("C:\\Users\\emill\\dev\\MoVE\\src\\main"))
  doStatsForProject(new File("C:\\Users\\emill\\dev\\playframework\\framework\\src\\play\\src\\main"))
}
