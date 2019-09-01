import java.io.File
import java.sql.Timestamp
import java.util.Date

import org.apache.commons.lang3.StringUtils
import slickEmileProfile.Tables

// Use H2Profile to connect to an H2 database

object LargeScaleCompilation {

  def main(args: Array[String]): Unit = {
    //runJavaIPlasma()
    //collectJavaResults()
    //runPMD()
    doScala()
  }

  def runPMD(): Unit = {
    var projectsTuples = LargeScaleDb.getAllGoodJavaProjects
    projectsTuples.filter(_.detectedSmells == scala.Option.empty).filter(_.loc > 500)

    for (p <- projectsTuples) {
      var path = new File(p.project.replace("D:\\github_java", "C:\\github_java"))
      println("Path: " + path)
      try {
        var projName = Cmd.getProjectName(path.toPath)
        var gitTopLevel = new File("C:\\github_java\\" + projName)
        if (gitTopLevel.exists()) {

          var existingSmells = LargeScaleDb.getDetectedSmellsJavaForCommit(p.commithash);
          //var gitTopLevel = Cmd.getGitTopLevel((path))
          val currentCommit = Cmd.getCurrentCommitHash(gitTopLevel)
          if (currentCommit != p.commithash) {
            println("Checking out")
            Cmd.gitForceCheckout(p.commithash, gitTopLevel);
          }
          assert((path).exists())

          if (existingSmells.size == 0) {
            LargeScaleDb.removeDetectedSmellsJavaForCommit(p.commithash)
            if ((path).exists()) {

              var pmdBinPath = "C:\\Users\\emill\\Dropbox\\slimmerWorden\\2018-2019-Semester2\\THESIS\\pmd-bin-6.16.0\\bin\\"
              Thread.sleep(1000) // Because SSD-USB connection can overheat :/
              var returnString = Cmd.execCommandWithTimeout(pmdBinPath + "pmd.bat -d " + path + " -R category/java/design.xml/GodClass", new File("."))
              var smells = HandleJavaData.parsePmdOutput(returnString)

              var pyrRow = LargeScaleDb.getPyramidStatJava(p.project).find(_.commithash == p.commithash).get
              pyrRow = pyrRow.copy(detectedSmells = Option(smells.length))
              LargeScaleDb.insertPyramidStatsJava(pyrRow)

              for (smell <- smells) {
                val sm = smell.copy(commit = p.commithash)
                LargeScaleDb.insertRow(sm)
              }
            }
          } else {
            var pyrRow = LargeScaleDb.getPyramidStatJava(p.project).find(_.commithash == p.commithash).get
            pyrRow = pyrRow.copy(detectedSmells = Option(existingSmells.length))
            LargeScaleDb.insertPyramidStatsJava(pyrRow)
          }
        }
      } catch {
        case x: Throwable =>
          println("EXCEPTION:\n" + x)
      }
    } // end for
  }

  def runJavaIPlasma(): Unit = {
    val outputPath = "C:\\Users\\emill\\Dropbox\\slimmerWorden\\2018-2019-Semester2\\THESIS\\out\\java_pyramid\\"
    //val alreadyDone = Utils.getFilesFromDirectory(new File(outputPath))
    //val folders = Utils.getSrcMainPaths(new File("D:\\github_java\\"))
    val folders = Utils.readFile(new File("C:\\Users\\emill\\Dropbox\\slimmerWorden\\2018-2019-Semester2\\THESIS\\github_scrape\\java\\src_files_depth_5.txt"))
      .split("\n")
      .filter(StringUtils.countMatches(_, "\\") < 4)
      //.filter(x => x.count(_ == "\\") < 4)
      .map(new File(_))
    for (p <- folders) {
      //val slug = Slug.apply(p.getAbsolutePath)
      //if (!alreadyDone.contains(new File(outputPath + slug + ".html")))
      var tries = LargeScaleDb.getJavaIplasmaPyramidTry(p)
      if (tries.size == 0) {
        println(p)
        var returnString = Cmd.execCommandWithTimeout(
          "\"C:\\Program Files\\Java\\jdk1.8.0_191\\bin\\java\" -DSAIL_PATH=\"./res/SAILMetrics\" -DFOLDER_LIST=\";\" -DFOLDER_SEPARATOR=\"\\\\\" -DPROJECT_TYPE=\"Java\" -Xms256m -Xmx1536m -classpath \"C:\\Program Files\\Java\\jdk1.8.0_191\\lib\\tools.jar\";\"C:\\Program Files\\Java\\jdk1.8.0_191\\lib\\rt.jar\";./classes;liquidlnf.jar;intellij.jar;memoria.jar;metrics.jar;common.jar;dude.jar;java2html.jar;recoder.jar;jmondrian.jar lrg.insider.gui.InsiderTextMain " + p.getAbsolutePath + " OverviewPyramid",
          new File("C:\\Users\\emill\\dev\\maproef1819-emile\\iPlasma_decompiled\\iPlasma_original\\iPlasma"))

        LargeScaleDb.insertJavaIplasmaPyramidTry(p, returnString)
      }
    }
  }

  def collectJavaResults(): Unit = {
    var all = LargeScaleDb.getAllJavaIPlasma
    for (iplasma <- all) {
      val alreadyDone = LargeScaleDb.getPyramidStatJava(iplasma.path)
      if (alreadyDone.length == 0) {

        var gitTopLevel = Cmd.getGitTopLevel(new File(iplasma.path))
        val commitHash = Cmd.getCurrentCommitHash(gitTopLevel)
        //Cmd.makeCommitStateClean(gitTopLevel)
        //(id, project, commithash, timestamp, nop, noc, nom, loc, cc, andc, ahh, calls, fanout, powershellLoc)
        val powershellLoc = 0 //Cmd.getPowershellLoc(new File(iplasma.path), ".java")
        var tmp = Tables.PyramidStatsJavaRow(0, iplasma.path, commitHash, new Timestamp(new Date().getTime).toString, 0, 0, 0, 0, 0, 0, 0, 0, 0, Option(powershellLoc), Option.empty)
        tmp = HandleJavaData.updatePyramidStatsJavaFromLog(tmp, iplasma.output)
        LargeScaleDb.insertPyramidStatsJava(tmp)
      }
    }
  }


  def doScala(): Unit = {
    var github_path = new File("C:/github_download")
    //var projects = Utils.getFoldersFromDirectory(github_path)
    //var projects = List(new File("D:\\github_download\\100x.io"))
    var projects = Utils.recursiveGetFoldersThatContainFile(github_path, "build.sbt")
    //var projects = LargeScaleDb.getSuccesfullProjects().map(x => x.buildPath).distinct
    println("Will examine following paths:")
    //println(projects.mkString("\n"))
    //projects = projects.reverse

    for (p <- projects) {
      //if (!LargeScaleDb.hadSuccesfullBuild(p)) {
      if (LargeScaleDb.getBuildTry(p).length == 0) {
        println("Compiling: " + p)
        //Cmd.execCommand("cd " + p )//+ " && java -jar \"C:/Program Files (x86)/sbt/bin/sbt-launch.jar\"")
        //var gitTopLevel = Cmd.getGitTopLevel(p)
        //Cmd.gitSoftClean(gitTopLevel)
        var returnString = Cmd.execCommandWithTimeout("sbt.bat semanticdb", p)

        LargeScaleDb.insertBuildTry(p, returnString, "fixedANDC and perhaps CALLS")
        //Cmd.execCommandWithTimeout("simple_console.exe", new File(p))
      } else {
        println("Skipped: " + p)
      }
    }
  }

}
