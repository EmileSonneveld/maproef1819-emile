import java.io.File
import java.nio.file._


import scala.language.postfixOps

object main extends App {

  def calculationsOnProject(projectPath: File): Any = {
    var gitTopLevel = Cmd.getGitTopLevel(projectPath)
    val projectName = Cmd.getProjectName(projectPath.toPath)

    if (true) {
      val commitHash = Cmd.getCurrentCommitHash(projectPath)
      Cmd.makeCommitStateClean(gitTopLevel)

      var commitStats = MeasureProject.doStatsForProject(projectPath, projectName)
      commitStats.commitHash = commitHash
      commitStats.projectName += "_BETTER_CALLS"
      println(commitStats.nom_set)
      var svg = Utils.readFile("..\\svg\\pyramid.svg")
      svg = MeasureProject.fillInPyramidTemplate(svg, commitStats)
      Utils.writeFile("C:\\Users\\emill\\Dropbox\\slimmerWorden\\2018-2019-Semester2\\THESIS\\out\\svg_pyramid\\" + projectName + ".svg", svg)
      LargeScaleDb.insertPyramidStats(commitStats.toPyramidStats)
    }
    else {
      // Go trough history
      val hashesFromDb =
        LargeScaleDb.getPyramidStatsForProj(projectName)
      //.map(_.commithash)

      Cmd.execCommandWithTimeout("git reset .", gitTopLevel) // Clear stash away?
      Cmd.execCommandWithTimeout("git checkout master", gitTopLevel) // To get a good git log

      var hashes = Cmd.getCommitHashesFromLog(gitTopLevel)
      hashes = hashes.slice(0, Math.min(2, hashes.length))
      //hashes = List("821b2307a02cf68fd608d098be807876fd320563")
      for (hash <- hashes) {
        var commitStats: CommitStats = null
        try {
          if (!hashesFromDb.exists(_.commithash == hash) || LargeScaleDb.commitWorthRetaking(hash)) {
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
          commitStats.projectName = projectName
          commitStats.commitHash = hash
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
      .filter(x => Utils.normalizeDirectoryPath(x.toString).count(x => x == '/') <= 3) // assume only one project per repository
      .filter(x => new File(Utils.normalizeDirectoryPath(x.toString) + "src\\main\\scala").exists()) // Only do standard paths

    for (file <- projects) {
      calculationsOnProjectWrap(file)
    }
  } else {
    // Don't mess in the /dev folder!
    calculationsOnProjectWrap(new File("C:\\github_download\\SHotDraw\\SHotDraw"))
    //calculationsOnProjectWrap(new File("C:\\github_download\\Leo-III"))
    //calculationsOnProjectWrap(new File("C:\\github_download\\CTT-editor"))
    //calculationsOnProjectWrap(new File("C:\\github_download\\scalameta"))
    //calculationsOnProjectWrap(new File("C:\\github_download\\MoVE"))
    //calculationsOnProjectWrap(new File("C:\\github_download\\SHotDraw\\SHotDraw"))
    //calculationsOnProjectWrap(new File("C:\\github_download\\100x.io"))
  }
}
