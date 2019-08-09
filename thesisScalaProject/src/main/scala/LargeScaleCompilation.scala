import java.awt.Color
import java.io.{File, IOException}
import java.nio.file._
import java.sql.Timestamp
import java.util
import java.util.{ArrayList, Date, List}
import java.util.stream.{Collectors, Stream}

import Cmd.killProccesHiarchy
import slickEmileProfile.Tables

import scala.concurrent.{TimeoutException, duration}

// Use H2Profile to connect to an H2 database
import slick.jdbc.SQLiteProfile.api._

import scala.concurrent.Await

object LargeScaleCompilation {

  def main(args: Array[String]): Unit = {
    //runJavaIPlasma()
    collectJavaResults()
    //doScala()
  }

  def runJavaIPlasma(): Unit = {
    val outputPath = "C:\\Users\\emill\\Dropbox\\slimmerWorden\\2018-2019-Semester2\\THESIS\\out\\java_pyramid\\"
    val alreadyDone = Utils.getFilesFromDirectory(new File(outputPath))
    val folders = Utils.getSrcPaths(new File("D:\\github_java\\SHotDraw"))
    for (p <- folders) {
      val slug = Slug.apply(p.getAbsolutePath)
      if (!alreadyDone.contains(new File(outputPath + slug + ".html"))) {
        println(p)
        var returnString = Cmd.execCommandWithTimeout(
          "\"C:\\Program Files\\Java\\jdk1.8.0_191\\bin\\java\" -DSAIL_PATH=\"./res/SAILMetrics\" -DFOLDER_LIST=\";\" -DFOLDER_SEPARATOR=\"\\\\\" -DPROJECT_TYPE=\"Java\" -Xms256m -Xmx1536m -classpath \"C:\\Program Files\\Java\\jdk1.8.0_191\\lib\\tools.jar\";\"C:\\Program Files\\Java\\jdk1.8.0_191\\lib\\rt.jar\";./classes;liquidlnf.jar;intellij.jar;memoria.jar;metrics.jar;common.jar;dude.jar;java2html.jar;recoder.jar;jmondrian.jar lrg.insider.gui.InsiderTextMain " + p.getAbsolutePath + " OverviewPyramid",
          new File("C:\\Users\\emill\\dev\\maproef1819-emile\\iPlasma_decompiled\\iPlasma_original\\iPlasma"))

        LargeScaleDb.insertJavaPyramidTry(p, returnString)
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
        val powershellLoc = Cmd.getPowershellLoc(new File(iplasma.path), ".java")
        var tmp = Tables.PyramidStatsJavaRow(0, iplasma.path, commitHash, new Timestamp(new Date().getTime).toString, 0, 0, 0, 0, 0, 0, 0, 0, 0, Option(powershellLoc))
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
      if (!LargeScaleDb.hadSuccesfullBuild(p)) {
        //if (LargeScaleDb.getBuildTry(p).length == 0) {
        println("Compiling: " + p)
        //Cmd.execCommand("cd " + p )//+ " && java -jar \"C:/Program Files (x86)/sbt/bin/sbt-launch.jar\"")
        //var gitTopLevel = Cmd.getGitTopLevel(p)
        //Cmd.gitSoftClean(gitTopLevel)
        var returnString = Cmd.execCommandWithTimeout("sbt.bat semanticdb", p)

        LargeScaleDb.insertBuildTry(p, returnString, "maybeBothJDKs_bigList")
        //Cmd.execCommandWithTimeout("simple_console.exe", new File(p))
      } else {
        println("Skipped: " + p)
      }
    }
  }

}
