import java.io._
import java.nio.file._

import scala.collection.mutable.ListBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
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

  def getCommitHashesFromLog(gitTopLevel: File): Iterator[String] = {
    val logString = execCommandWithTimeout("git log --format=\"commit %H\"", gitTopLevel)
    val re = """commit ([\w]+)""".r
    re.findAllIn(logString).map(x => x.substring(7))
  }

  def getGitTopLevel(path: File): File = {
    val res = execCommandWithTimeout("git rev-parse --show-toplevel", path).trim
    new File(res)
  }

  /**
    * Doesn't do cd right!
    */
  def execCommand(command: String): String = {
    println("> " + command)
    return ("cmd /C " + command) !!
  }

  def execCommandWithTimeout(command: String, cd: File): String = {
    println("> " + command)

    val outputBuffer = ListBuffer[String]()
    val p = Process(command, cd).run(ProcessLogger(outputBuffer append _)) // start asynchronously
    val f = Future(blocking(p.exitValue())) // wrap in Future
    try {
      val exitValue: Int = Await.result(f, duration.Duration(5 * 60, "sec"))
      if (exitValue != 0)
        println("exitValue: " + exitValue)
    } catch {
      case _: TimeoutException =>
        outputBuffer.append(outputBuffer mkString "\n")
        println("TIMEOUT!")
        p.destroy()
    }
    outputBuffer.mkString("\n")
  }

  // In case of complicated git mistakes
  def deleteRecursively(file: File): Unit = {
    if (file.isDirectory)
      file.listFiles.foreach(deleteRecursively)
    if (file.exists && !file.delete)
      throw new Exception(s"Unable to delete ${file.getAbsolutePath}")
  }


  def gitSoftClean(gitTopLevel: File): Unit = {
    Cmd.execCommandWithTimeout("git clean -f", gitTopLevel) // for untracked files
    Cmd.execCommandWithTimeout("git checkout .", gitTopLevel) // for modified files
  }

  def clearGitRepo(gitTopLevel: File): Unit = {
    val filesAndFolders: Array[File] = gitTopLevel.listFiles //isFile to find files
    for (f <- filesAndFolders) {
      if (!f.getAbsolutePath.contains(".git")) {
        println("deleting " + f)
        //f.delete()
        deleteRecursively(f)
      }
    }
    execCommandWithTimeout("git checkout master", gitTopLevel)
    execCommandWithTimeout("git checkout .", gitTopLevel)
  }
}
