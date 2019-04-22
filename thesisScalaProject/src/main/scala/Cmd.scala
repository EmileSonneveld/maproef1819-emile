import java.io._
import java.nio.file._

import Cmd.convertStreamToString

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

  def getCommitHashesFromLog(gitTopLevel: String): Iterator[String] = {
    val logString = execCommand("cd " + gitTopLevel + " && git log --format=\"commit %H\"")
    val re = """commit ([\w]+)""".r
    re.findAllIn(logString).map(x => x.substring(7))
  }

  def getGitTopLevel(path: File): String = {
    execCommand("cd " + path + " && git rev-parse --show-toplevel").trim
  }

  def convertStreamToString(is: InputStream): String = {
    def inner(reader: BufferedReader, sb: StringBuilder): String = {
      val line = reader.readLine()
      if (line != null) {
        try {
          inner(reader, sb.append(line + "\n"))
        } catch {
          case e: IOException => e.printStackTrace()
        } finally {
          try {
            is.close()
          } catch {
            case e: IOException => e.printStackTrace()
          }
        }

      }
      sb.toString()
    }

    inner(new BufferedReader(new InputStreamReader(is)), new StringBuilder())
  }


  def execCommand(command: String): String = {
    println("> " + command)
    return ("cmd /C " + command) !!
  }

  def execCommandWithTimeout(command: String, cd: File) = {
    println("> " + command)

    var in = "" // no input defined
    var stdOut = ""
    var stdErr = ""
    val io = new ProcessIO(
      stdin => {
        stdin.write(in.getBytes)
        stdin.close()
      },
      stdout => {
        val tmp = convertStreamToString(stdout)
        stdOut = tmp
        println(tmp)
        stdout.close()
      },
      stderr => {
        val tmp = convertStreamToString(stderr)
        stdErr = tmp
        println(tmp)
        stderr.close()
      })

    //val p: Process = command.run() // start asynchronously
    val p = Process(command, cd).run(io) // start asynchronously
    var alive = p.isAlive();
    val f = Future(blocking(p.exitValue())) // wrap in Future
    val exitValue: Int = try {
      Await.result(f, duration.Duration(60*5, "sec"))
    } catch {
      case _: TimeoutException =>
        println("TIMEOUT!")
        p.destroy()
        p.exitValue()
    }
    println("exitValue: " + exitValue)
    exitValue
    stdOut
  }

  // In case of complicated git mistakes
  def deleteRecursively(file: File): Unit = {
    if (file.isDirectory)
      file.listFiles.foreach(deleteRecursively)
    if (file.exists && !file.delete)
      throw new Exception(s"Unable to delete ${file.getAbsolutePath}")
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
    execCommand("cd " + gitTopLevel + " && git checkout master")
    execCommand("cd " + gitTopLevel + " && git checkout .")
  }
}
