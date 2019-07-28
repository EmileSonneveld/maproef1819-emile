import java.io.File
import java.nio.file._

import Cmd.execCommandWithTimeout

import scala.language.postfixOps

object main extends App {

  def calculationsOnProject(projectPath: File): Any = {
    var gitTopLevel = Cmd.getGitTopLevel(projectPath)

    val projectName = Cmd.getProjectName(projectPath.toPath)

    if (false) {
      var commitStats = MeasureProject.doStatsForProject(projectPath, projectName)
      commitStats.commitHash = Cmd.getCurrentCommitHash(projectPath)
      var svg = Utils.readFile("..\\svg\\pyramid.svg")
      svg = MeasureProject.fillInPyramidTemplate(svg, commitStats, projectName)
      Utils.writeFile("C:\\Users\\emill\\Dropbox\\slimmerWorden\\2018-2019-Semester2\\THESIS\\out\\svg_pyramid\\" + projectName + ".svg", svg)
    }
    else {
      val csvPath = "C:\\Users\\emill\\Dropbox\\slimmerWorden\\2018-2019-Semester2\\THESIS\\out\\csv_log\\" + projectName + ".csv"
      val result = Utils.parseCsvFromFile(new File(csvPath))
      val hashesFromCsv: Seq[String] = result.map(x => CommitStats.fromCsvLine(x).commitHash)

      Cmd.execCommandWithTimeout("git reset .", gitTopLevel) // Clear stash away?
      execCommandWithTimeout("git checkout master", gitTopLevel) // To get a good git log

      var hashes = Cmd.getCommitHashesFromLog(gitTopLevel)
      //hashes = hashes.slice(0, Math.min(2, hashes.length))
      //hashes = List("821b2307a02cf68fd608d098be807876fd320563")
      for (hash <- hashes) {
        var commitStats: CommitStats = null
        try {
          if (!hashesFromCsv.contains(hash)) {
            Cmd.gitForceCheckout(hash, gitTopLevel)

            println(hash)
            var returnString = Cmd.execCommandWithTimeout("sbt.bat semanticdb", gitTopLevel)
            if (!returnString.contains("[success]")) {
              println("Could not make semanticdb for commit")
            }
            commitStats = MeasureProject.doStatsForProject(projectPath, projectName)
          }
          // Keep commitStats null if it was calculated before
        } catch {
          case x: Throwable => {
            println("Exception while calculating stats.")
            commitStats = new CommitStats // Add empty line to avoid calculating this commit in the future
          }
        }

        if (commitStats != null) {
          commitStats.commitHash = hash

          val str = projectName + ", " + commitStats.toCsvLine
          Utils.appendToFile(csvPath, str)
        }
      }
    }
  }

  def calculationsOnProjectWrap(sbtProjectPath: File): Unit = {
    var f = new File(Utils.normalizeDirectoryPath(sbtProjectPath.toString)) // + "src\\main\\scala")
    if (f.exists())
      calculationsOnProject(f)
    else {
      println("calculationsOnProjectWrap sbt path not standard: " + sbtProjectPath.toString)
      //calculationsOnProject(sbtProjectPath)
    }
  }


  if (true) {
    var projects = LargeScaleDb.getSuccesfullProjects()
      .map(x => new File(x.buildpath))
      .distinct
      .filter(x => Utils.normalizeDirectoryPath(x.toString).count(x => x == '/') <= 3) // assume only one project per repository
      .filter(x => new File(Utils.normalizeDirectoryPath(x.toString) + "src\\main\\scala").exists()) // Only do standard paths

    for (file <- projects) {
      calculationsOnProjectWrap(file)
    }
  } else {
    calculationsOnProjectWrap(new File("C:\\Users\\emill\\dev\\HotDraw\\SHotDraw [Scala]"))
    //calculationsOnProjectWrap(new File("C:\\Users\\emill\\dev\\scalafixtemplate"))
    //calculationsOnProjectWrap(new File("C:\\Users\\emill\\dev\\testScala"))
    //calculationsOnProjectWrap(new File("C:\\Users\\emill\\dev\\maproef1819-emile\\thesisScalaProject")) // dangerous meta stuff
    //calculationsOnProjectWrap(new File("C:\\Users\\emill\\dev\\CTT-editor"))
    calculationsOnProjectWrap(new File("C:\\github_download\\CTT-editor"))
    calculationsOnProjectWrap(new File("C:\\github_download\\scalameta"))
    calculationsOnProjectWrap(new File("C:\\github_download\\scala-async"))
    calculationsOnProjectWrap(new File("C:\\github_download\\MoVE"))
    calculationsOnProjectWrap(new File("C:\\github_download\\SHotDraw\\SHotDraw"))
    calculationsOnProjectWrap(new File("C:\\github_download\\100x.io"))
  }
}
