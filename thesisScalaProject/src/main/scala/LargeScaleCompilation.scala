import java.awt.Color
import java.io.File
import java.nio.file._

//#imports
// Use H2Profile to connect to an H2 database
import slick.jdbc.H2Profile.api._

import scala.concurrent.ExecutionContext.Implicits.global
//#imports
import scala.concurrent.Await
import scala.concurrent.duration.Duration

import scala.collection.mutable.ArrayBuffer

object LargeScaleCompilation {

  def main(args: Array[String]): Unit = {
    val db = Database.forConfig("h2mem1")
    try {
      // val resultFuture: Future[_] = { ... }
      //Await.result(resultFuture, Duration.Inf)
      //lines.foreach(Predef.println _)
    } finally db.close

    /*
    /*var s_console = Enigma.getConsole("Hellow World!")
    val attrs = new Nothing(Color.BLUE, Color.WHITE)
    s_console.setTextAttributes(attrs)
    System.out.println("Hello World!")*/
    var github_path = new File("C:/github_download")
    //var projects = Utils.getFoldersFromDirectory(github_path)
    //var projects = List(new File("D:\\github_download\\100x.io"))
    var projects = Utils.recursiveListFilesWithName(github_path, "build.sbt")
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
    }*/
  }
}
