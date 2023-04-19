package be.emilesonneveld

import java.io.File
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file.{Files, Paths}
import java.util.concurrent.TimeUnit

import scala.collection.Iterable

object main {


  def calculationsOnProject(projectPath: File): Any = {
    if (projectPath.toString.contains("emill\\dev") || projectPath.toString.contains("emill/dev")) {
      println("It is dangerous to alnalyse the dev folder! This tools messes with GIT and builds and stuff.")
      System.exit(-1)
    }

    var gitTopLevel = Cmd.getGitTopLevel(projectPath)
    val projectName = Cmd.getProjectName(projectPath.toPath)
    val hashesFromDb = LargeScaleDb.getPyramidStatsForProj(projectName)

    if (true) {
      // We a assume that the semanticdb files are already present in all projects
      //Cmd.makeCommitStateClean(gitTopLevel) Doesn't matter, as we calculate on the semantiddb files

      val b = projectNeedsSemanticDb(projectPath)
      println("projectNeedsSemanticDb: " + b)
      if (b) {
        val log = Cmd.execSbtSemanticDB(projectPath)
        println(log)
      }

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
            Files.createDirectories(Paths.get("out/svg_pyramid"))
            val svg = MeasureProject.makePyramydSvg(commitStats)
            Utils.writeFile("out/svg_pyramid/" + projectName + ".svg", svg)
          } catch {
            case x: Throwable => {
              println("Exception while calculating stats:\n" + x)
            }
          }
          val row = commitStats.toPyramidStats
          println(MeasureProject.asciiOverviewPyramid(row))
          LargeScaleDb.insertPyramidStats(row)
        }
      }
      var smells2 = LargeScaleDb.getDetectedSmellsForCommit(commitStats.commitHash);
      println(smells2.mkString("\n"))
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

            var returnString = Cmd.execSbtSemanticDB(gitTopLevel)
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


  def getNewestDate(files: Iterable[File]): Long = {
    val maxModified = files.map(_.lastModified).max
    val maxCreated = files.map(
      x => Files.readAttributes(Paths.get(x.toString), classOf[BasicFileAttributes]).creationTime.to(TimeUnit.MILLISECONDS)).max

    Math.max(maxModified, maxCreated) // TODO: Test. Test empty list too
  }

  def touch(file: File): Unit = {
    val timestamp = System.currentTimeMillis
    file.setLastModified(timestamp)
  }

  /**
    * When an other commit is checked out, the semanticdb files need to be regenerated.
    * This is quite a dirty function.
    */
  def projectNeedsSemanticDb(projectPath: File): Boolean = {
    var semanticdbFiles = Utils.recursiveGetFiles(projectPath, ".semanticdb")
    if (semanticdbFiles.isEmpty) return true
    val latestSemanticDbDate = getNewestDate(semanticdbFiles)


    var srcFiles = Utils.recursiveGetFiles(projectPath, ".scala")
    srcFiles ++= Utils.recursiveGetFiles(projectPath, ".sbt")
    var latestSrcDate = getNewestDate(srcFiles)

    var gitTopLevel = Cmd.getGitTopLevel(projectPath)
    val relevantGitFiles = List(".git/FETCH_HEAD", ".git/HEAD", ".git/index", ".git/ORIG_HEAD")
    val gitFiles = relevantGitFiles
      .map(x => new File(gitTopLevel + "/" + x))
      .filter(_.exists())

    val latestGitModificationDate = getNewestDate(gitFiles)
    if (gitFiles.nonEmpty) latestSrcDate = Math.max(latestSrcDate, latestGitModificationDate)


    val b = latestSemanticDbDate < latestSrcDate
    b
  }

  def main(args: Array[String]): Unit = {
    if (args.length == 1) {
      calculationsOnProjectWrap(new File(args(0)))
      return
    } else {
      println("Should pass path to project as first argument")
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
      calculationsOnProjectWrap(new File(PathsConfig.analyzedProjectPath))
      //calculationsOnProjectWrap(new File("C:\\github_download\\SHotDraw\\SHotDraw"))
      //calculationsOnProjectWrap(new File("C:\\github_download\\maproef1819-emile\\testScala"))
      //calculationsOnProjectWrap(new File("C:\\github_download\\maproef1819-emile\\thesisScalaProject")) //Does not compile
      //calculationsOnProjectWrap(new File("C:\\github_download\\Leo-III"))
      //calculationsOnProjectWrap(new File("C:\\github_download\\CTT-editor"))
      //calculationsOnProjectWrap(new File("C:\\github_download\\scalameta")) Infinite loop?4
      //calculationsOnProjectWrap(new File("C:\\github_download\\MoVE"))
      //calculationsOnProjectWrap(new File("C:\\github_download\\100x.io"))
    }
  }
}
