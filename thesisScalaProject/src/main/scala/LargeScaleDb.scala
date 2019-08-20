import java.io.File
import java.sql._
import java.util.Date

import slick.jdbc.SQLiteProfile.api._
import slickEmileProfile.Tables
import slickEmileProfile.Tables.{JavaIplasmaPyramidRow, SbtBuildTriesRow}

import scala.concurrent.{Await, duration}

object LargeScaleDb {

  val db = Database.forConfig("slickEmileProfile")
  // Should call this if database must close: finally db.close

  val build_tries = TableQuery[Tables.SbtBuildTries]
  val pyramid_stats = TableQuery[Tables.PyramidStatsScala]
  val java_pyramid = TableQuery[Tables.JavaIplasmaPyramid]
  val pyramid_stats_java = TableQuery[Tables.PyramidStatsJava]
  val detected_smell = TableQuery[Tables.DetectedSmell]

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
    var f = db.run(build_tries += SbtBuildTriesRow(0, new Timestamp(new Date().getTime).toString, buildPath.toString, stdOutput, null))
    Await.result(f, duration.Duration(10, "sec"))
  }

  def insertJavaIplasmaPyramidTry(buildPath: File, stdOutput: String): Unit = {
    var f = db.run(java_pyramid += JavaIplasmaPyramidRow(0, new Timestamp(new Date().getTime).toString, buildPath.toString, stdOutput))
    Await.result(f, duration.Duration(10, "sec"))
  }

  def getSuccesfullProjects: Seq[Tables.SbtBuildTriesRow] = {
    var query = build_tries.filter(_.stdoutput.like("%[success]%"))
    var f = db.run(query.result)
    Await.result(f, duration.Duration(10, "sec"))
  }

  def getBuildTry(buildPath: File): Seq[Tables.SbtBuildTriesRow] = {
    var query = build_tries.filter(_.buildpath.like(buildPath.toString))
    var f = db.run(query.result)
    Await.result(f, duration.Duration(10, "sec"))
  }

  def getAllJavaIPlasma: Seq[Tables.JavaIplasmaPyramidRow] = {
    var query = java_pyramid
    var f = db.run(query.result)
    Await.result(f, duration.Duration(10, "sec"))
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
    var query = pyramid_stats.filter(_.project.like(project))
    var f = db.run(query.result)
    Await.result(f, duration.Duration(10, "sec"))
  }

  def getDistinctCommits: Seq[String] = {
    var query = pyramid_stats.map(_.commithash).distinct
    var f = db.run(query.result)
    Await.result(f, duration.Duration(10, "sec"))
  }

  def getCommitRows(commitHash: String): Seq[Tables.PyramidStatsScalaRow] = {
    var query = pyramid_stats.filter(_.commithash === commitHash)
    var f = db.run(query.result)
    Await.result(f, duration.Duration(10, "sec"))
  }

  def removePyramidRow(id: Int): Unit = {
    var query = pyramid_stats.filter(_.id === id).delete
    var f = db.run(query)
    Await.result(f, duration.Duration(10, "sec"))
  }

  def commitWorthRetaking(commitHash: String): Boolean = {
    {
      var query = pyramid_stats.filter(_.commithash.like(commitHash)).filter(_.noc === 0).filter(_.cc === 0)
      var f = db.run(query.result)
      val res = Await.result(f, duration.Duration(10, "sec"))
      if (res.length > 0)
        return false // We already got a failed try, not worth repeating
    }
    {
      var query = pyramid_stats.filter(_.commithash.like(commitHash)) // Should best filter on NULL here
    var f = db.run(query.result)
      val res = Await.result(f, duration.Duration(10, "sec"))
      val res2 = res.filter(x => x.calls != Option.empty && x.ahh != Option.empty)
      res2.length == 0 // Not yet full result, so worth repeating!
    }
  }

  def insertPyramidStats(row: Tables.PyramidStatsScalaRow): Unit = {
    var query = (pyramid_stats += row)
    var f = db.run(query)
    Await.result(f, duration.Duration(5, "sec"))
  }


  def insertPyramidStatsJava(row: Tables.PyramidStatsJavaRow): Unit = {
    var query = (pyramid_stats_java += row)
    var f = db.run(query)
    Await.result(f, duration.Duration(5, "sec"))
  }

  def updatePyramidStats(row: Tables.PyramidStatsScalaRow): Unit = {
    val res = pyramid_stats.filter(_.id === row.id)
    val action = res.update(row)
    var f = db.run(action)
    Await.result(f, duration.Duration(5, "sec"))
  }

  def insertRow(row: Tables.DetectedSmellRow): Unit = {
    var query = (detected_smell += row)
    var f = db.run(query)
    Await.result(f, duration.Duration(5, "sec"))
  }

  def removeDetectedSmellsForCommit(commitHash: String): Unit = {
    var query = detected_smell.filter(_.commit === commitHash).delete
    var f = db.run(query)
    Await.result(f, duration.Duration(10, "sec"))
  }
}


//case class BuildTry(id: Long, buildPath: File, buildDateTime: Long, stdOutput: String, buildType: String)