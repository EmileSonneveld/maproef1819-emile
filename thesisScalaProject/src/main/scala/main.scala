import java.io.File
import java.nio.file._

import scala.language.postfixOps

object main extends App {

  def calculationsOnProject(projectPath: File): Any = {
    var gitTopLevel = Cmd.getGitTopLevel(projectPath)

    val projectName = Cmd.getProjectName(projectPath.toPath)

    if (true) {
      var commitStats = MeasureProject.doStatsForProject(projectPath, projectName)
      var svg = Utils.readFile("..\\svg\\pyramid.svg")
      svg = MeasureProject.fillInPyramidTemplate(svg, commitStats, projectName)
      Utils.writeFile("..\\svg\\pyramid_" + projectName + ".svg", svg)
    }
    else {
      val svgPath = "C:\\Users\\emill\\Dropbox\\slimmerWorden\\2018-2019-Semester2\\THESIS\\csv\\log_" + projectName + ".csv"
      val result = Utils.parseCsvFromFile(new File(svgPath))
      val hashesFromSvg: Seq[String] = result.map(x => x(1))

      Cmd.execCommandWithTimeout("git reset .", gitTopLevel)
      Cmd.clearGitRepo(gitTopLevel)


      val hashes = Cmd.getCommitHashesFromLog(gitTopLevel)
      for (hash <- hashes) {
        var commitStats: CommitStats = null
        try {
          if (!hashesFromSvg.contains(hash)) {
            Cmd.gitSoftClean(gitTopLevel)
            Cmd.execCommandWithTimeout("git checkout " + hash, gitTopLevel)
            println(hash)
            commitStats = MeasureProject.doStatsForProject(projectPath, projectName)
          }
          // Keep commitStats null if it was calculated before
        } catch {
          case x: Throwable => {
            println("Exception while calculating stats. Will clearGitRepo and checkout master.")
            commitStats = new CommitStats // Add empty line to avoid calculating this commit in the future
            Cmd.clearGitRepo(gitTopLevel)
          }
        }

        if (commitStats != null) {
          commitStats.commitHash = hash

          val str = commitStats.toCsvLine
          Utils.appendToFile(svgPath, str)
        }
      }
    }
  }


  //calculationsOnProject(new File("C:\\Users\\emill\\dev\\testScala\\src\\main\\scala"))
  //calculationsOnProject(new File("C:\\Users\\emill\\dev\\CTT-editor\\src\\main\\scala"))
  //calculationsOnProject(new File("C:\\Users\\emill\\dev\\scalafixtemplate\\src\\main\\scala"))
  //calculationsOnProject(new File("C:\\Users\\emill\\dev\\scalastyle\\src\\main\\scala"))

  calculationsOnProject(new File("C:\\Users\\emill\\dev\\MoVE\\src\\main\\scala"))
  /*
    calculationsOnProject(new File("C:\\Users\\emill\\dev\\playframework\\core\\play\\src\\main\\scala"))
    calculationsOnProject(new File("D:\\github_download\\beakerx\\kernel\\scala\\src\\main\\scala"))
    calculationsOnProject(new File("D:\\github_download\\BigDL\\spark\\spark-version\\1.5-plus\\src\\main\\scala"))
    calculationsOnProject(new File("D:\\github_download\\circe\\modules\\core\\shared\\src\\main\\scala"))
    calculationsOnProject(new File("D:\\github_download\\deeplearning4j\\scalnet\\src\\main\\scala"))
    calculationsOnProject(new File("D:\\github_download\\dotty\\library\\src\\scala"))
    calculationsOnProject(new File("D:\\github_download\\finagle\\finagle-core\\src\\main\\scala"))
    calculationsOnProject(new File("D:\\github_download\\gitbucket\\src\\main\\scala"))
    calculationsOnProject(new File("D:\\github_download\\incubator-openwhisk\\common\\scala\\src\\main\\scala"))
    calculationsOnProject(new File("D:\\github_download\\lila\\app"))
    calculationsOnProject(new File("D:\\github_download\\sbt\\main\\src\\main\\scala"))
    calculationsOnProject(new File("D:\\github_download\\scala\\src\\reflect\\scala\\reflect"))
    calculationsOnProject(new File("D:\\github_download\\scalastyle\\src\\main\\scala"))
    calculationsOnProject(new File("D:\\github_download\\scaloid\\scaloid-common\\src\\main\\scala"))
    calculationsOnProject(new File("D:\\github_download\\shapeless\\core\\src\\main\\scala"))
  */
}
