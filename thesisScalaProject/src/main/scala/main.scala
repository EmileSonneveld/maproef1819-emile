import java.io.{File, FileWriter}
import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Path, Paths}
import java.text._
import java.util.Calendar

import scalafix.CfgPerMethod

import scala.language.postfixOps
import scala.meta._
import scala.sys.process._

object main extends App {

  def getProjectName(path: Path): String = {
    var tmp = path.toString
    tmp = tmp.replace("C:\\Users\\emill\\dev\\", "")
    tmp = tmp.replace("D:\\github_download\\", "")
    tmp = tmp.replace("D:\\dev\\", "")
    val firstSlah = Math.max(tmp.indexOf('\\'), tmp.indexOf('/'))
    return tmp.substring(0, firstSlah)
  }

  def getCommitHashesFromLog(gitTopLevel: String): Iterator[String] = {
    val logString = execCommand("cd " + gitTopLevel + " && git log --format=\"commit %H\"")
    val re = """commit ([\w]+)""".r
    re.findAllIn(logString).map(x => x.substring(7))
  }

  def getGitTopLevel(path: File) = {
    execCommand("cd " + path + " && git rev-parse --show-toplevel").trim
  }

  def execCommand(command: String): String = {
    println("> " + command)
    return ("cmd /C " + command) !!
  }

  // In case of complicated git mistakes
  def deleteRecursively(file: File): Unit = {
    if (file.isDirectory)
      file.listFiles.foreach(deleteRecursively)
    if (file.exists && !file.delete)
      throw new Exception(s"Unable to delete ${file.getAbsolutePath}")
  }

  def clearGitRepo(gitTopLevel: File) = {
    val filesAndFolders: Array[File] = gitTopLevel.listFiles //isFile to find files
    for (f <- filesAndFolders) {
      if (!f.getAbsolutePath.contains(".git")) {
        println("deleting " + f)
        //f.delete()
        deleteRecursively(f)
      }
    }
    execCommand("cd " + gitTopLevel + " && git checkout master")
    execCommand("cd " + gitTopLevel + " && git checkout .")
  }


  def calculationsOnProject(projectPath: File): Any = {
    var gitTopLevel = getGitTopLevel(projectPath)

    val projectName = getProjectName(projectPath.toPath)

    if (true) {
      var commitStats = MeasureProject.doStatsForProject(projectPath, projectName)
      var svg = Utils.readFile("..\\svg\\pyramid.svg")
      svg = MeasureProject.fillInPyramidTemplate(svg, commitStats, projectName)
      Utils.writeFile("..\\svg\\pyramid_" + projectName + ".svg", svg)
    }
    else {
      val svgPath = "C:\\Users\\emill\\Dropbox\\slimmerWorden\\2018-2019-Semester2\\THESIS\\csv\\log_" + projectName + ".csv"
      val result = Utils.parseCsvFromFile(new File(svgPath))
      val hashesFromSvg: Seq[String] = result.map(x => x(1))

      execCommand("cd " + gitTopLevel + " && git reset .")
      clearGitRepo(new File(gitTopLevel))


      val hashes = getCommitHashesFromLog(gitTopLevel)
      for (hash <- hashes) {
        var commitStats: CommitStats = null
        try {
          if (!hashesFromSvg.contains(hash)) {
            execCommand("cd " + gitTopLevel + " && git clean -f") // for untracked files
            execCommand("cd " + gitTopLevel + " && git checkout .") // for modified files
            execCommand("cd " + gitTopLevel + " && git checkout " + hash)
            println(hash)
            commitStats = MeasureProject.doStatsForProject(projectPath, projectName)
          }
          // Keep commitStats null if it was calculated before
        } catch {
          case x: Throwable => {
            println("Exception while calculating stats. Will clearGitRepo and checkout master.")
            commitStats = new CommitStats // Add empty line to avoid calculating this commit in the future
            clearGitRepo(new File(gitTopLevel))
          }
        }

        if (commitStats != null) {
          commitStats.commitHash = hash

          val str = commitStats.toSvgLine
          Utils.appendToFile(svgPath, str)
        }
      }
    }
  }


  //calculationsOnProject(new File("C:\\Users\\emill\\dev\\testScala\\src\\main\\scala"))

  calculationsOnProject(new File("C:\\Users\\emill\\dev\\CTT-editor\\src\\main\\scala"))
  //calculationsOnProject(new File("C:\\Users\\emill\\dev\\scalafixtemplate\\src\\main\\scala"))
  //calculationsOnProject(new File("C:\\Users\\emill\\dev\\scalastyle\\src\\main\\scala"))
  //calculationsOnProject(new File("C:\\Users\\emill\\dev\\MoVE\\src\\main\\scala"))
  /*
  calculationsOnProject(new File("C:\\Users\\emill\\dev\\playframework\\core\\play\\src\\main\\scala"))
  calculationsOnProject(new File("D:\\github_download\\beakerx\\kernel\\scala\\src\\main\\scala"))
  calculationsOnProject(new File("D:\\github_download\\BigDL\\spark\\spark-version\\1.5-plus\\src\\main\\scala"))
  calculationsOnProject(new File("D:\\github_download\\circe\\modules\\core\\shared\\src\\main\\scala"))
  calculationsOnProject(new File("D:\\github_download\\deeplearning4j\\scalnet\\src\\main\\scala"))
  calculationsOnProject(new File("D:\\github_download\\dotty\\library\\src\\scala"))
  calculationsOnProject(new File("D:\\github_download\\finagle\\finagle-core\\src\\main\\scala"))
  calculationsOnProject(new File("D:\\github_download\\gitbucket\\src\\main\\scala"))
  calculationsOnProject(new File("D:\\github_download\\incubator-openwhisk\\common\\scala\\src\\main\\scala"))
  calculationsOnProject(new File("D:\\github_download\\lila\\app"))
  calculationsOnProject(new File("D:\\github_download\\sbt\\main\\src\\main\\scala"))
  calculationsOnProject(new File("D:\\github_download\\scala\\src\\reflect\\scala\\reflect"))
  calculationsOnProject(new File("D:\\github_download\\scalastyle\\src\\main\\scala"))
  calculationsOnProject(new File("D:\\github_download\\scaloid\\scaloid-common\\src\\main\\scala"))
  calculationsOnProject(new File("D:\\github_download\\shapeless\\core\\src\\main\\scala"))
  */
}
