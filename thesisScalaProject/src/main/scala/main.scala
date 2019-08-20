import java.io.File
import java.nio.file._


import scala.language.postfixOps

object main extends App {

  def calculationsOnProject(projectPath: File): Any = {
    thesisCommon.main(Array(""))

    //if (projectPath.toString.contains("emill\\dev") || projectPath.toString.contains("emill/dev")) {
    //  println("Don't treat the dev folder as cannon flodder.")
    //  System.exit(-1)
    //}

    var gitTopLevel = Cmd.getGitTopLevel(projectPath)
    val projectName = Cmd.getProjectName(projectPath.toPath)

    if (true) {
      //Cmd.makeCommitStateClean(gitTopLevel) Doesn't matter, as we calculate on the semantiddb files

      val commitStats = new CommitStats
      commitStats.projectName = projectName
      commitStats.commitHash = Cmd.getCurrentCommitHash(projectPath)
      MeasureProject.doStatsForProject(projectPath, commitStats)
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
        val commitStats: CommitStats = new CommitStats
        commitStats.projectName = projectName
        commitStats.commitHash = hash
        try {
          if (!hashesFromDb.exists(_.commithash == hash) || LargeScaleDb.commitWorthRetaking(hash)) {
            Cmd.gitForceCheckout(hash, gitTopLevel)

            println(hash)
            var returnString = Cmd.execCommandWithTimeout("sbt.bat semanticdb", gitTopLevel)
            if (!returnString.contains("[success]")) {
              println("Could not make semanticdb for commit")
            }
            commitStats.projectName = projectName
            MeasureProject.doStatsForProject(projectPath, commitStats)
          }
          // Keep commitStats null if it was calculated before
        } catch {
          case x: Throwable => {
            println("Exception while calculating stats.")
          }
        }

        LargeScaleDb.insertPyramidStats(commitStats.toPyramidStats)
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
    calculationsOnProjectWrap(new File("C:\\Users\\emill\\dev\\SHotDraw\\SHotDraw"))
    //calculationsOnProjectWrap(new File("C:\\github_download\\SHotDraw\\SHotDraw"))
    //calculationsOnProjectWrap(new File("C:\\github_download\\maproef1819-emile\\testScala"))
    //calculationsOnProjectWrap(new File("C:\\github_download\\Leo-III"))
    //calculationsOnProjectWrap(new File("C:\\github_download\\CTT-editor"))
    //calculationsOnProjectWrap(new File("C:\\github_download\\scalameta"))
    //calculationsOnProjectWrap(new File("C:\\github_download\\MoVE"))
    //calculationsOnProjectWrap(new File("C:\\github_download\\100x.io"))
  }
}
