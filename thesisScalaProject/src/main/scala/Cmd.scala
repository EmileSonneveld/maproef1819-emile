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


  def getProjectName(path: Path): String = {
    var tmp = path.toString
    tmp = tmp.replace("C:\\Users\\emill\\dev\\", "")
    tmp = tmp.replace("D:\\github_download\\", "")
    tmp = tmp.replace("C:\\Users\\emill\\Desktop\\github_download\\", "")
    tmp = tmp.replace("D:\\dev\\", "")
    var firstSlah = Math.max(tmp.indexOf('\\'), tmp.indexOf('/'))
    if (firstSlah == -1) return tmp
    else return tmp.substring(0, firstSlah)
  }

  def getCurrentCommitHash(gitTopLevel: File): String = {
    val logString = execCommandWithTimeout("git rev-parse HEAD", gitTopLevel)
    logString.trim
  }

  def getCommitHashesFromLog(gitTopLevel: File): List[String] = {
    val logString = execCommandWithTimeout("git log --format=\"commit %H\"", gitTopLevel)
    val re = """commit ([\w]+)""".r
    re.findAllIn(logString).map(x => x.substring(7)).toList
  }

  def getGitTopLevel(path: File): File = {
    val res = execCommandWithTimeout("git rev-parse --show-toplevel", path).trim
    new File(res)
  }

  import java.lang.reflect.Field

  /*
    private def killWinProcess(process: Process) = {
      var exitValue = 0
      try {
        val f = process.getClass.getDeclaredField("handle")
        f.setAccessible(true)
        val hndl = f.getLong(process)
        val kernel = Kernel32.INSTANCE
        val handle = new Kernel32 {}
        handle.setPointer(Pointer.createConstant(hndl))
        val pid = kernel.GetProcessId(handle)
        killPID("" + pid)
        exitValue = waitForProcessDeath(process, 10000)
      } catch {
        case ex: Throwable =>
          log.warning("Process refused to die after 10 seconds, and couldn't killall it")
          ex.printStackTrace()
          throw new RuntimeException("Process refused to die after 10 seconds, and couldn't killall it: " + ex.getMessage, ex)
      }
      exitValue
    }*/
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
    val JAVA_HOME = "C:\\Program Files\\Java\\jdk1.8.0_191" // C:\\Program Files\\Java\\jdk1.8.0_131"
    val p = Process(command, cd,
      "JAVA_HOME" -> JAVA_HOME, // SBT works best with java 1.8
      "SBT_OPTS" -> "-Xms512M -Xmx1024M -Xss2M -XX:MaxMetaspaceSize=1024M", // SBT runs out of memory for some larger projects
      "PATH" -> (sys.env("Path") + ";" + JAVA_HOME + "\\bin")).run(ProcessLogger(str => {
      outputBuffer append str
      if (str.contains("Project loading failed: (r)etry, (q)uit, (l)ast, or (i)gnore?")) {
        // No use to go further
        //p.destroy()
      }
    })) // start asynchronously
    val f = Future(blocking(p.exitValue())) // wrap in Future
    try {
      val exitValue: Int = Await.result(f, duration.Duration(2 * 60, "sec"))
      if (exitValue != 0)
        println("exitValue: " + exitValue)

    } catch {
      case _: TimeoutException =>
        outputBuffer.append("TIMEOUT!")
        println("TIMEOUT!")
        //p.destroy()
        killProccesHiarchy(p)
      //ProcessUtils.killProcess(p.)
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
    execCommandWithTimeout("git checkout " + commitHash, gitTopLevel)
    execCommandWithTimeout("git checkout .", gitTopLevel)
  }
}
