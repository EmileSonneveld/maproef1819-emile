import java.io.{File, FileWriter}
import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Path, Paths}
import java.text._
import java.util.Calendar

import scalafix.CfgPerMethod

import scala.language.postfixOps
import scala.meta._
import scala.sys.process._

object main extends App {

  def getProjectName(path: Path): String = {
    var tmp = path.toString
    tmp = tmp.replace("C:\\Users\\emill\\dev\\", "")
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


  def goTroughAllCommits(projectPath: File): Any = {
    var gitTopLevel = getGitTopLevel(projectPath)

    val projectName = getProjectName(projectPath.toPath)
    val result = Utils.parseCsvFromFile(new File("C:\\Users\\emill\\Dropbox\\slimmerWorden\\2018-2019-Semester2\\THESIS\\csv\\log_" + projectName + ".csv"))
    val hashesFromSvg: Seq[String] = result.map(x => x(1))

    execCommand("cd " + gitTopLevel + " && git reset .")
    clearGitRepo(new File(gitTopLevel))


    val hashes = getCommitHashesFromLog(gitTopLevel)
    for (hash <- hashes) {
      var commitStats: CommitStats = null
      try {
        if (!hashesFromSvg.contains(hash)) {
          execCommand("cd " + gitTopLevel + " && git clean -f") // for untracked files
          execCommand("cd " + gitTopLevel + " && git checkout .") // for modified files
          execCommand("cd " + gitTopLevel + " && git checkout " + hash)
          println(hash)
          commitStats = MeasureProject.doStatsForProject(projectPath, projectName)
        }
        // Keep commitStats null if it was calculated before
      } catch {
        case x: Throwable => {
          println("Exception while calculating stats. Manually delete all files and start again, plz.")
          commitStats = new CommitStats // Add empty line to avoid calculating this commit in the future
          clearGitRepo(new File(gitTopLevel))
        }
      }

      if(commitStats != null)
      {
        commitStats.commitHash = hash

        val filename = "..\\svg\\pyramid.svg"
        var svg = scala.io.Source.fromFile(filename).getLines.mkString("\n")

        svg = MeasureProject.fillInPyramidTemplate(svg, commitStats, projectName)
        Files.write(Paths.get("..\\svg\\pyramid_" + projectName + ".svg"),
          svg.getBytes(StandardCharsets.UTF_8))


        logToCsv(commitStats, projectName)
      }
    }
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
      println(f)
      if (!f.getAbsolutePath.contains(".git")) {
        println("deleting this.")
        //f.delete()
        deleteRecursively(f)
      }
    }
    execCommand("cd " + gitTopLevel + " && git checkout master")
    execCommand("cd " + gitTopLevel + " && git checkout .")
  }


  def logToCsv(commitStats: CommitStats, projectName: String) = {
    val nop = commitStats.nop_set.size
    val noc = commitStats.noc_set.size
    val nom = commitStats.nom_set.size
    val loc = commitStats.loc
    val avg_cc = commitStats.cc_set.sum / commitStats.cc_set.size
    val timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance.getTime)

    var str = projectName + ", " + commitStats.commitHash + ", " + nop + ", " + noc + ", " + nom + ", " + loc + ", " + avg_cc + ", " + timeStamp + "\n"
    val fw = new FileWriter("C:\\Users\\emill\\Dropbox\\slimmerWorden\\2018-2019-Semester2\\THESIS\\csv\\log_" + projectName + ".csv", true)
    try {
      fw.write(str)
    }
    finally fw.close()
  }


  //goTroughAllCommits(new File("C:\\Users\\emill\\dev\\scalafixtemplate\\src\\main"))
  goTroughAllCommits(new File("C:\\Users\\emill\\dev\\CTT-editor\\src\\main"))
  //goTroughAllCommits(new File("C:\\Users\\emill\\dev\\MoVE\\src\\main"))
  //goTroughAllCommits(new File("C:\\Users\\emill\\dev\\playframework\\framework\\src\\play\\src\\main"))
}
