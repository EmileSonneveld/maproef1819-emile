package be.emilesonneveld

import java.io.File
import java.sql._
import java.util.Date

import slick.jdbc.SQLiteProfile.api._
import be.emilesonneveld.slickEmileProfile.Tables
import be.emilesonneveld.slickEmileProfile.Tables._

import scala.concurrent.{Await, duration}

object LargeScaleDb {

  val db = Database.forConfig("slickEmileProfile")
  // Should call this if database must close: finally db.close

  val detected_smell_scala = TableQuery[Tables.DetectedSmell]
  val detected_smell_java = TableQuery[Tables.DetectedSmellJava]
  val java_iplasma_pyramid = TableQuery[Tables.JavaIplasmaPyramid]
  val pyramid_stats_java = TableQuery[Tables.PyramidStatsJava]
  val pyramid_stats_scala = TableQuery[Tables.PyramidStatsScala]
  val sbt_build_tries = TableQuery[Tables.SbtBuildTries]

  //private var conn: Connection = _
  //try {
  //  Class.forName("org.sqlite.JDBC")
  //  conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\emill\\Dropbox\\slimmerWorden\\2018-2019-Semester2\\THESIS\\out\\LargeScaleDb.sqlite")
  //} catch {
  //  case e: Exception =>
  //    System.err.println(e.getClass.getName + ": " + e.getMessage)
  //    System.exit(0)
  //}

  def insertBuildTry(buildPath: File, stdOutput: String, buildType: String): Unit = {
    var f = db.run(sbt_build_tries += SbtBuildTriesRow(0, new Timestamp(new Date().getTime).toString, buildPath.toString, stdOutput, null))
    Await.result(f, duration.Duration(10, "sec"))
  }

  def insertJavaIplasmaPyramidTry(buildPath: File, stdOutput: String): Unit = {
    var f = db.run(java_iplasma_pyramid += JavaIplasmaPyramidRow(0, new Timestamp(new Date().getTime).toString, buildPath.toString, stdOutput))
    Await.result(f, duration.Duration(10, "sec"))
  }

  def getJavaIplasmaPyramidTry(path: File): Seq[Tables.JavaIplasmaPyramidRow] = {
    var query = java_iplasma_pyramid.filter(_.path === (path.toString))
    var f = db.run(query.result)
    Await.result(f, duration.Duration(10, "sec"))
  }

  def getSuccesfullProjects: Seq[Tables.SbtBuildTriesRow] = {
    var query = sbt_build_tries.filter(_.stdoutput.like("%[success]%"))
    var f = db.run(query.result)
    Await.result(f, duration.Duration(10, "sec"))
  }

  def getBuildTry(buildPath: File): Seq[Tables.SbtBuildTriesRow] = {
    var query = sbt_build_tries.filter(_.buildpath.like(buildPath.toString))
    var f = db.run(query.result)
    Await.result(f, duration.Duration(10, "sec"))
  }

  def getAllJavaIPlasma: Seq[Tables.JavaIplasmaPyramidRow] = {
    var query = java_iplasma_pyramid
    var f = db.run(query.result)
    Await.result(f, duration.Duration(10, "sec"))
  }

  def getAllGoodJavaProjects: Seq[Tables.PyramidStatsJavaRow] = {
    var query = pyramid_stats_java.filter(_.nom > 0).filter(_.cc > 0)
    var f = db.run(query.result)
    var tmp = Await.result(f, duration.Duration(10, "sec"))
    tmp.filter(_.andc != 0)
  }

  def getPyramidStatJava(projectPath: String): Seq[Tables.PyramidStatsJavaRow] = {
    var query = pyramid_stats_java.filter(_.project === projectPath)
    var f = db.run(query.result)
    Await.result(f, duration.Duration(10, "sec"))
  }

  def hadSuccesfullBuild(buildPath: File): Boolean = {
    var builds = getBuildTry(buildPath)
    builds.exists(x => x.stdoutput.contains("[success]"))
  }

  def hadSuccesfullBuild(buildPath: File, buildType: String): Boolean = {
    var builds = getBuildTry(buildPath)
    builds.exists(x => x.stdoutput.contains("[success]") && x.buildtype == buildType)
  }

  def getPyramidStatsForProj(project: String): Seq[Tables.PyramidStatsScalaRow] = {
    var query = pyramid_stats_scala.filter(_.project.like(project))
    var f = db.run(query.result)
    Await.result(f, duration.Duration(10, "sec"))
  }

  def getDistinctCommits: Seq[String] = {
    var query = pyramid_stats_scala.map(_.commithash).distinct
    var f = db.run(query.result)
    Await.result(f, duration.Duration(10, "sec"))
  }

  def getCommitRows(commitHash: String): Seq[Tables.PyramidStatsScalaRow] = {
    var query = pyramid_stats_scala.filter(_.commithash === commitHash)
    var f = db.run(query.result)
    Await.result(f, duration.Duration(10, "sec"))
  }

  def removePyramidRow(id: Int): Unit = {
    var query = pyramid_stats_scala.filter(_.id === id).delete
    var f = db.run(query)
    Await.result(f, duration.Duration(10, "sec"))
  }

  def commitWorthRetaking(commitHash: String): Boolean = {
    {
      var query = pyramid_stats_scala.filter(_.commithash.like(commitHash)).filter(_.noc === 0).filter(_.cc === 0)
      var f = db.run(query.result)
      val res = Await.result(f, duration.Duration(10, "sec"))
      if (res.length > 0)
        return false // We already got a failed try, not worth repeating
    }
    {
      // Should best filter on NULL here:
      var query = pyramid_stats_scala.filter(_.commithash.like(commitHash))
      var f = db.run(query.result)
      val res = Await.result(f, duration.Duration(10, "sec"))
      val res2 = res.filter(x => x.calls != Option.empty && x.ahh != Option.empty)
      res2.length == 0 // Not yet full result, so worth repeating!
    }
  }

  def insertPyramidStats(row: Tables.PyramidStatsScalaRow): Unit = {
    var query = (pyramid_stats_scala += row)
    var f = db.run(query)
    Await.result(f, duration.Duration(5, "sec"))
  }


  def insertPyramidStatsJava(row: Tables.PyramidStatsJavaRow): Unit = {
    var query = (pyramid_stats_java += row)
    var f = db.run(query)
    Await.result(f, duration.Duration(5, "sec"))
  }

  def updatePyramidStats(row: Tables.PyramidStatsScalaRow): Unit = {
    val res = pyramid_stats_scala.filter(_.id === row.id)
    val action = res.update(row)
    var f = db.run(action)
    Await.result(f, duration.Duration(5, "sec"))
  }

  def insertRow(row: Tables.DetectedSmellRow): Unit = {
    var query = (detected_smell_scala += row)
    var f = db.run(query)
    Await.result(f, duration.Duration(5, "sec"))
  }

  def insertRow(row: Tables.DetectedSmellJavaRow): Unit = {
    var query = (detected_smell_java += row)
    var f = db.run(query)
    Await.result(f, duration.Duration(5, "sec"))
  }

  def removeDetectedSmellsForCommit(commitHash: String): Unit = {
    var query = detected_smell_scala.filter(_.commit === commitHash).delete
    var f = db.run(query)
    Await.result(f, duration.Duration(10, "sec"))
  }

  def getDetectedSmellsForCommit(commitHash: String): Seq[Tables.DetectedSmellRow] = {
    var query = detected_smell_scala.filter(_.commit === commitHash)
    var f = db.run(query.result)
    Await.result(f, duration.Duration(10, "sec"))
  }

  def removeDetectedSmellsJavaForCommit(commitHash: String): Unit = {
    var query = detected_smell_java.filter(_.commit === commitHash).delete
    var f = db.run(query)
    Await.result(f, duration.Duration(10, "sec"))
  }

  def getDetectedSmellsJavaForCommit(commitHash: String): Seq[Tables.DetectedSmellJavaRow] = {
    var query = detected_smell_java.filter(_.commit === commitHash)
    var f = db.run(query.result)
    Await.result(f, duration.Duration(10, "sec"))
  }

}
