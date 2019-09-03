package be.emilesonneveld

import java.io._
import java.nio.file._

import com.sun.jna.Pointer
import com.sun.jna.platform.win32.WinNT

import scala.collection.mutable.ListBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.language.postfixOps
import scala.sys.process._
import org.openqa.selenium.os._

/**
  * Interface with file system and other programs
  */
object Cmd {


  def getRelativePathToProject(path: Path): String = {
    var tmp = path.toString
    tmp = tmp.replace("C:\\Users\\emill\\dev\\", "")
    tmp = tmp.replace("E:\\github_download\\", "")
    tmp = tmp.replace("D:\\github_download\\", "")
    tmp = tmp.replace("C:\\github_download\\", "")
    tmp = tmp.replace("E:\\github_java\\", "")
    tmp = tmp.replace("D:\\github_java\\", "")
    tmp = tmp.replace("C:\\github_java\\", "")
    tmp = tmp.replace("C:\\Users\\emill\\Desktop\\github_download\\", "")
    tmp = tmp.replace("D:\\dev\\", "")
    tmp
  }

  def getProjectName(path: Path): String = {
    val tmp = getRelativePathToProject(path)
    var firstSlah = Math.max(tmp.indexOf('\\'), tmp.indexOf('/'))
    if (firstSlah == -1) return tmp
    else return tmp.substring(0, firstSlah)
  }

  def getCurrentCommitHash(gitTopLevel: File): String = {
    val logString = execCommandWithTimeout("git rev-parse HEAD", gitTopLevel)
    logString.trim
  }

  def makeCommitStateClean(gitTopLevel: File): String = {
    val commitHash = Cmd.getCurrentCommitHash(gitTopLevel)
    Cmd.execCommandWithTimeout("git reset .", gitTopLevel) // Clear stash away?
    Cmd.execCommandWithTimeout("git checkout " + commitHash, gitTopLevel)
    Cmd.execCommandWithTimeout("git checkout .", gitTopLevel)
  }

  def getCommitHashesFromLog(gitTopLevel: File): List[String] = {
    val logString = execCommandWithTimeout("git log --format=\"commit %H\"", gitTopLevel)
    val re = """commit ([\w]+)""".r
    re.findAllIn(logString).map(x => x.substring(7)).toList
  }

  def getPowershellLoc(projectPath: File, extention: String): Int = {
    try {
      var result = Cmd.execCommandWithTimeout("powershell \"dir -Recurse *" + extention + " | Get-Content | Measure-Object -Line\"", projectPath)
      var linesPos = result.indexOf("Lines")
      result = result.substring(linesPos)

      val re = """[0-9]+""".r
      re.findFirstIn(result).get.toInt
    } catch {
      case _: Throwable => 0
    }
  }

  def getGitTopLevel(path: File): File = {
    val res = execCommandWithTimeout("git rev-parse --show-toplevel", path).trim
    if (res.contains("fatal:") || res.contains("not a git repository"))
      throw new EmileException(res)
    new File(res)
  }

  def execCommandWithTimeout(command: String, cd: File): String = {
    println("> " + command)

    val outputBuffer = ListBuffer[String]()
    val JAVA_HOME = "C:\\Program Files\\Java\\jdk1.8.0_191" // C:\\Program Files\\Java\\jdk1.8.0_131"
    val p = Process(command, cd,
      "JAVA_HOME" -> JAVA_HOME, // SBT works best with java 1.8
      "SBT_OPTS" -> "-Xms512M -Xmx1024M -Xss2M -XX:MaxMetaspaceSize=1024M", // SBT runs out of memory for some larger projects
      "PATH" -> (sys.env("Path") + ";" + JAVA_HOME + "\\bin")).run(ProcessLogger(str => outputBuffer append str)) // start asynchronously
    val f = Future(blocking(p.exitValue())) // wrap in Future
    try {
      val exitValue: Int = Await.result(f, duration.Duration(1 * 60, "sec")) // 2 * 60
      if (exitValue != 0)
        println("exitValue: " + exitValue)

    } catch {
      case _: TimeoutException =>
        outputBuffer.append("TIMEOUT!")
        println("TIMEOUT!")
        //p.destroy()
        killProccesHiarchy(p)
    }
    outputBuffer.mkString("\n")
  }

  def killProccesHiarchy(process: Process): Unit = {
    val f1 = process.getClass.getDeclaredField("p")
    f1.setAccessible(true)
    val pImpl = f1.get(process)

    val f2 = pImpl.getClass.getDeclaredField("handle")
    f2.setAccessible(true)
    val hndl = f2.getLong(pImpl)

    val kernel = Kernel32.INSTANCE
    val handle = new WinNT.HANDLE
    handle.setPointer(Pointer.createConstant(hndl))
    val pid = kernel.GetProcessId(handle)

    WindowsUtils.killPID("" + pid)
  }

  // In case of complicated git mistakes
  def deleteRecursively(file: File): Unit = {
    if (file.isDirectory)
      file.listFiles.foreach(deleteRecursively)
    if (file.exists && !file.delete)
      throw new Exception(s"Unable to delete ${file.getAbsolutePath}")
  }


  //def gitSoftClean(gitTopLevel: File): Unit = {
  //  Cmd.execCommandWithTimeout("git clean -f", gitTopLevel) // for untracked files
  //  Cmd.execCommandWithTimeout("git checkout .", gitTopLevel) // for modified files
  //}

  def clearGitRepo(gitTopLevel: File): Unit = {
    val filesAndFolders: Array[File] = gitTopLevel.listFiles //isFile to find files
    for (f <- filesAndFolders) {
      if (!f.getAbsolutePath.contains(".git")) {
        println("deleting " + f)
        //f.delete()
        deleteRecursively(f)
      }
    }
  }

  def gitForceCheckout(commitHash: String, gitTopLevel: File): Unit = {
    clearGitRepo(gitTopLevel)
    var ret = execCommandWithTimeout("git checkout " + commitHash, gitTopLevel)
    if (ret.contains("fatal: not a git repository"))
      println("GIT problem:\n" + ret)
    execCommandWithTimeout("git checkout .", gitTopLevel)
  }
}
