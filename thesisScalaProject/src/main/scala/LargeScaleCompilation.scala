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
    var projects = List(new File("C:/Users/emill/dev/CTT-editor"))
    for (p <- projects) {
      //Cmd.execCommand("cd " + p )//+ " && java -jar \"C:/Program Files (x86)/sbt/bin/sbt-launch.jar\"")
      var returnString = Cmd.execCommandWithTimeout("sbt.bat semanticdb", p)
      if (returnString.contains("[success]")) {

      } else if (returnString.contains("[error]")) {

      }
      LargeScaleDb.insertBuildTry(p, returnString)
      //Cmd.execCommandWithTimeout("simple_console.exe", new File(p))
    }
  }
}
