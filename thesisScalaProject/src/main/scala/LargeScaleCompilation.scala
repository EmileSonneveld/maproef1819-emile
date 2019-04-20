import java.io.File
import java.nio.file._

object LargeScaleCompilation {

  def main(args: Array[String]): Unit = {
    var github_path = new File("D:\\github_download")
    var projects = Utils.getFoldersFromDirectory(github_path)

    for (p <- projects) {
      Cmd.execCommand("cd " + p + " && java -jar \"C:\\Program Files (x86)\\sbt\\bin\\sbt-launch.jar\"")
    }
  }
}
