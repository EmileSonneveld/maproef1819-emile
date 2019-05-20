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
      Utils.writeFile("C:\\Users\\emill\\Dropbox\\slimmerWorden\\2018-2019-Semester2\\THESIS\\out\\svg\\pyramid_" + projectName + ".svg", svg)
    }
    else {
      val csvPath = "C:\\Users\\emill\\Dropbox\\slimmerWorden\\2018-2019-Semester2\\THESIS\\out\\csv\\log_" + projectName + ".csv"
      val result = Utils.parseCsvFromFile(new File(csvPath))
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
          Utils.appendToFile(csvPath, str)
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

/*
  var projects = LargeScaleDb.getSuccesfullProjects()
    .map(x => x.buildPath)
    .distinct
    .filter(x => Utils.normalizeDirectoryPath(x.toString).count(x => x == '/') <= 3) // assume only one project per reposetory
    .filter(x => new File(Utils.normalizeDirectoryPath(x.toString) + "src\\main\\scala").exists()) // Only do standard paths

  for (file <- projects) {
    calculationsOnProjectWrap(file)
  }*/
  //calculationsOnProjectWrap(new File("D:\\github_download\\SHotDraw\\SHotDraw"))
  calculationsOnProjectWrap(new File("C:\\Users\\emill\\Desktop\\github_download\\SHotDraw\\SHotDraw"))
}
