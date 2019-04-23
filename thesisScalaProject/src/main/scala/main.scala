import java.io.File
import java.nio.file._

import scala.language.postfixOps

object main extends App {

  def calculationsOnProject(projectPath: File): Any = {
    var gitTopLevel = Cmd.getGitTopLevel(projectPath)

    val projectName = Cmd.getProjectName(projectPath.toPath)

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

      Cmd.execCommandWithTimeout("git reset .", gitTopLevel)
      Cmd.clearGitRepo(gitTopLevel)


      val hashes = Cmd.getCommitHashesFromLog(gitTopLevel)
      for (hash <- hashes) {
        var commitStats: CommitStats = null
        try {
          if (!hashesFromSvg.contains(hash)) {
            Cmd.gitSoftClean(gitTopLevel)
            Cmd.execCommandWithTimeout("git checkout " + hash, gitTopLevel)
            println(hash)
            commitStats = MeasureProject.doStatsForProject(projectPath, projectName)
          }
          // Keep commitStats null if it was calculated before
        } catch {
          case x: Throwable => {
            println("Exception while calculating stats. Will clearGitRepo and checkout master.")
            commitStats = new CommitStats // Add empty line to avoid calculating this commit in the future
            Cmd.clearGitRepo(gitTopLevel)
          }
        }

        if (commitStats != null) {
          commitStats.commitHash = hash

          val str = commitStats.toCsvLine
          Utils.appendToFile(svgPath, str)
        }
      }
    }
  }

  def calculationsOnProjectWrap(sbtProjectPath: File): Unit = {
    var f = new File(Utils.normalizeDirectoryPath(sbtProjectPath.toString) + "src\\main\\scala")
    if (f.exists())
      calculationsOnProject(f)
    else
      println("calculationsOnProjectWrap sbt path not standard")
  }

  calculationsOnProjectWrap(new File("D:/github_download/99_scala_problems"))
  //calculationsOnProjectWrap(new File("D:/github_download/akka-in-action/chapter-fault-tolerance"))
  //calculationsOnProjectWrap(new File("D:/github_download/akka-in-action/chapter-integration"))
  //calculationsOnProjectWrap(new File("D:/github_download/akka-in-action/chapter-perf"))
  //calculationsOnProjectWrap(new File("D:/github_download/akka-in-action/chapter-routing"))
  //calculationsOnProjectWrap(new File("D:/github_download/akka-in-action/chapter-state"))
  //calculationsOnProjectWrap(new File("D:/github_download/akka-in-action/chapter-structure"))
  //calculationsOnProjectWrap(new File("D:/github_download/akka-in-action/chapter-testdriven"))
  calculationsOnProjectWrap(new File("D:/github_download/akka-persistence-jdbc"))
  calculationsOnProjectWrap(new File("D:/github_download/alasc"))
  calculationsOnProjectWrap(new File("D:/github_download/algorithms"))
  calculationsOnProjectWrap(new File("D:/github_download/avrohugger"))
  calculationsOnProjectWrap(new File("D:/github_download/bacala"))
  calculationsOnProjectWrap(new File("D:/github_download/bezug-eventsourcing"))
  calculationsOnProjectWrap(new File("D:/github_download/blaze"))
  calculationsOnProjectWrap(new File("D:/github_download/bootzooka"))
  calculationsOnProjectWrap(new File("D:/github_download/chisel-tutorial"))
  calculationsOnProjectWrap(new File("D:/github_download/command-line-arguments"))
  calculationsOnProjectWrap(new File("D:/github_download/cspom"))
  calculationsOnProjectWrap(new File("D:/github_download/DieStats"))
  calculationsOnProjectWrap(new File("D:/github_download/dregex"))
  calculationsOnProjectWrap(new File("D:/github_download/FiloDB"))
  calculationsOnProjectWrap(new File("D:/github_download/FlashFry"))
  //calculationsOnProjectWrap(new File("D:/github_download/framian/framian-benchmarks"))
  calculationsOnProjectWrap(new File("D:/github_download/GameContentSolved"))
  //calculationsOnProjectWrap(new File("D:/github_download/gfc-concurrent/project"))
  calculationsOnProjectWrap(new File("D:/github_download/gfc-concurrent"))
  calculationsOnProjectWrap(new File("D:/github_download/gitReport"))
  calculationsOnProjectWrap(new File("D:/github_download/google-safebrowsing2"))
  calculationsOnProjectWrap(new File("D:/github_download/JMHC"))
  calculationsOnProjectWrap(new File("D:/github_download/josimtext"))
  calculationsOnProjectWrap(new File("D:/github_download/jsengine"))
  calculationsOnProjectWrap(new File("D:/github_download/lars"))
  calculationsOnProjectWrap(new File("D:/github_download/libanius"))
  calculationsOnProjectWrap(new File("D:/github_download/LoMRF"))
  calculationsOnProjectWrap(new File("D:/github_download/maxmind-geoip2-scala"))
  calculationsOnProjectWrap(new File("D:/github_download/metrics-scala"))
  calculationsOnProjectWrap(new File("D:/github_download/busymachines-commons"))
  calculationsOnProjectWrap(new File("D:/github_download/dregex/project"))
  calculationsOnProjectWrap(new File("D:/github_download/dropwizard-scala"))

}
