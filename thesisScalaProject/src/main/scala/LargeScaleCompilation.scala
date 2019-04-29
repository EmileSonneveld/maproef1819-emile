import java.awt.Color
import java.io.File
import java.nio.file._

object LargeScaleCompilation {

  def main(args: Array[String]): Unit = {

    /*var s_console = Enigma.getConsole("Hellow World!")
    val attrs = new Nothing(Color.BLUE, Color.WHITE)
    s_console.setTextAttributes(attrs)
    System.out.println("Hello World!")*/
    var github_path = new File("D:/github_download")
    //var projects = Utils.getFoldersFromDirectory(github_path)
    var projects = List(new File("D:\\github_download\\SHotDraw"))
    //var projects = Utils.recursiveListFilesWithName(github_path, "build.sbt")
    //var projects = LargeScaleDb.getSuccesfullProjects().map(x => x.buildPath).distinct
    println("Will examine following paths:")
    //println(projects.mkString("\n"))
    //projects = projects.reverse

    for (p <- projects) {
      //if (!LargeScaleDb.hadSuccesfullBuild(p))
      {
        println("Compiling: " + p)
        //Cmd.execCommand("cd " + p )//+ " && java -jar \"C:/Program Files (x86)/sbt/bin/sbt-launch.jar\"")
        //var gitTopLevel = Cmd.getGitTopLevel(p)
        //Cmd.gitSoftClean(gitTopLevel)
        var returnString = Cmd.execCommandWithTimeout("sbt.bat semanticdb", p)

        LargeScaleDb.insertBuildTry(p, returnString, "maybeBothJDKs")
        //Cmd.execCommandWithTimeout("simple_console.exe", new File(p))
      }/* else {
        println("Skipped: " + p)
      }*/
    }
  }
}
