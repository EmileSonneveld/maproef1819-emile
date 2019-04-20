import java.io.File
import java.nio.file._

import scala.language.postfixOps
import scala.sys.process._

/**
  * Interface with file system and other programs
  */
object Cmd {

  def getProjectName(path: Path): String = {
    var tmp = path.toString
    tmp = tmp.replace("C:\\Users\\emill\\dev\\", "")
    tmp = tmp.replace("D:\\github_download\\", "")
    tmp = tmp.replace("D:\\dev\\", "")
    val firstSlah = Math.max(tmp.indexOf('\\'), tmp.indexOf('/'))
    return tmp.substring(0, firstSlah)
  }

  def getCommitHashesFromLog(gitTopLevel: String): Iterator[String] = {
    val logString = execCommand("cd " + gitTopLevel + " && git log --format=\"commit %H\"")
    val re = """commit ([\w]+)""".r
    re.findAllIn(logString).map(x => x.substring(7))
  }

  def getGitTopLevel(path: File) = {
    execCommand("cd " + path + " && git rev-parse --show-toplevel").trim
  }

  def execCommand(command: String): String = {
    println("> " + command)
    return ("cmd /C " + command) !!
  }

  // In case of complicated git mistakes
  def deleteRecursively(file: File): Unit = {
    if (file.isDirectory)
      file.listFiles.foreach(deleteRecursively)
    if (file.exists && !file.delete)
      throw new Exception(s"Unable to delete ${file.getAbsolutePath}")
  }

  def clearGitRepo(gitTopLevel: File) = {
    val filesAndFolders: Array[File] = gitTopLevel.listFiles //isFile to find files
    for (f <- filesAndFolders) {
      if (!f.getAbsolutePath.contains(".git")) {
        println("deleting " + f)
        //f.delete()
        deleteRecursively(f)
      }
    }
    execCommand("cd " + gitTopLevel + " && git checkout master")
    execCommand("cd " + gitTopLevel + " && git checkout .")
  }
}
