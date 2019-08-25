import java.io.File
import java.nio.file._


import scala.language.postfixOps

object main extends App {

  def calculationsOnProject(projectPath: File): Any = {
    if (projectPath.toString.contains("emill\\dev") || projectPath.toString.contains("emill/dev")) {
      println("It is dangerous to alnalyse the dev folder! This tools messes with GIT and builds and stuff.")
      //System.exit(-1)
    }

    var gitTopLevel = Cmd.getGitTopLevel(projectPath)
    val projectName = Cmd.getProjectName(projectPath.toPath)
    val hashesFromDb = LargeScaleDb.getPyramidStatsForProj(projectName)

    if (true) {
      // We a assume that the semanticdb files are already present in all projects
      //Cmd.makeCommitStateClean(gitTopLevel) Doesn't matter, as we calculate on the semantiddb files
      //Cmd.execCommandWithTimeout("sbt.bat semanticdb", gitTopLevel)

      val commitStats = new CommitStats
      commitStats.projectName = projectName
      commitStats.commitHash = Cmd.getCurrentCommitHash(projectPath)

      var smells = LargeScaleDb.getDetectedSmellsForCommit(commitStats.commitHash);
      //if (smells.length <= 0) // only where no smells where detected
      {
        //if (!hashesFromDb.exists(_.commithash == commitStats.commitHash) || LargeScaleDb.commitWorthRetaking(commitStats.commitHash))
        {
          try {
            MeasureProject.doStatsForProject(projectPath, commitStats)
            var svg = Utils.readFile("..\\svg\\pyramid.svg")
            svg = MeasureProject.fillInPyramidTemplate(svg, commitStats)
            Utils.writeFile("C:\\Users\\emill\\Dropbox\\slimmerWorden\\2018-2019-Semester2\\THESIS\\out\\svg_pyramid\\" + projectName + ".svg", svg)
          } catch {
            case x: Throwable => {
              println("Exception while calculating stats:\n"+x.getMessage)
            }
          }
          LargeScaleDb.insertPyramidStats(commitStats.toPyramidStats)
        }
      }
    }
    else {
      // Go trough history

      Cmd.execCommandWithTimeout("git reset .", gitTopLevel) // Clear stash away?
      Cmd.execCommandWithTimeout("git checkout master", gitTopLevel) // To get a good git log
      var hashes = Cmd.getCommitHashesFromLog(gitTopLevel)
      hashes = hashes.slice(0, Math.min(2, hashes.length)) // only take first 2

      for (hash <- hashes) {
        val commitStats: CommitStats = new CommitStats
        commitStats.projectName = projectName
        commitStats.commitHash = hash
        if (!hashesFromDb.exists(_.commithash == hash) || LargeScaleDb.commitWorthRetaking(hash)) {
          try {
            Cmd.gitForceCheckout(hash, gitTopLevel)

            var returnString = Cmd.execCommandWithTimeout("sbt.bat semanticdb", gitTopLevel)
            if (!returnString.contains("[success]")) {
              println("Could not make semanticdb for commit")
            }
            MeasureProject.doStatsForProject(projectPath, commitStats)
          } catch {
            case x: Throwable => {
              println("Exception while calculating stats.")
            }
          }

          LargeScaleDb.insertPyramidStats(commitStats.toPyramidStats)
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


  if (false) {
    var projects = LargeScaleDb.getSuccesfullProjects
      .map(x => new File(x.buildpath))
      .distinct
      .filter(x => Utils.normalizeDirectoryPath(x.toString).count(x => x == '/') <= 4) // assume only one project per repository
      .filter(x => new File(Utils.normalizeDirectoryPath(x.toString) + "src\\main\\scala").exists()) // Only do standard paths

    for (file <- projects) {
      calculationsOnProjectWrap(file)
    }
  } else {

    // Don't mess in the /dev folder!
    //calculationsOnProjectWrap(new File("C:\\Users\\emill\\dev\\SHotDraw\\SHotDraw"))
    calculationsOnProjectWrap(new File("C:\\github_download\\SHotDraw\\SHotDraw"))
    //calculationsOnProjectWrap(new File("C:\\github_download\\maproef1819-emile\\testScala"))
    //calculationsOnProjectWrap(new File("C:\\github_download\\maproef1819-emile\\thesisScalaProject")) Does not compile
    //calculationsOnProjectWrap(new File("C:\\github_download\\Leo-III"))
    //calculationsOnProjectWrap(new File("C:\\github_download\\CTT-editor"))
    //calculationsOnProjectWrap(new File("C:\\github_download\\scalameta")) Infinite loop?
    //calculationsOnProjectWrap(new File("C:\\github_download\\MoVE"))
    //calculationsOnProjectWrap(new File("C:\\github_download\\100x.io"))
  }
}
