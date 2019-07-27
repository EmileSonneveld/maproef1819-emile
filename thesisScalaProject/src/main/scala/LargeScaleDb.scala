import java.io.File
import java.util.{ArrayList, Date}
import java.security.PublicKey
import java.sql._
import java.time.ZonedDateTime
import java.util

object LargeScaleDb {

  private var conn: Connection = _

  try {
    Class.forName("org.sqlite.JDBC")
    conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\emill\\Dropbox\\slimmerWorden\\2018-2019-Semester2\\THESIS\\out\\LargeScaleDb.sqlite")
  } catch {
    case e: Exception =>
      System.err.println(e.getClass.getName + ": " + e.getMessage)
      System.exit(0)
  }

  def insertBuildTry(buildPath: File, stdOutput: String, buildType: String): Unit = {
    val pstmt = conn.prepareStatement("INSERT INTO build_tries VALUES (NULL, ?, ?, ?, ?)")
    var i = 0
    pstmt.setTimestamp({
      i += 1
      i
    }, new Timestamp(new Date().getTime))
    pstmt.setString({
      i += 1
      i
    }, buildPath.toString)
    pstmt.setString({
      i += 1
      i
    }, stdOutput)
    pstmt.setString({
      i += 1
      i
    }, buildType)
    pstmt.executeUpdate
  }

  def getSuccesfullProjects(): List[BuildTry] = {

    val pstmt = conn.prepareStatement("SELECT * from  build_tries where stdOutput LIKE \"%[success]%\"")
    //pstmt.setString(1, buildType)
    val result = pstmt.executeQuery()

    var aggregate: List[BuildTry] = List[BuildTry]()
    if (!result.isClosed) {
      while ( {
        result.next
      }) {
        aggregate :+= rowToBuildTry(result)
      }
    }
    aggregate
  }

  def getBuildTry(buildPath: File): List[BuildTry] = {
    val pstmt = conn.prepareStatement("SELECT * from build_tries WHERE buildPath=?")
    pstmt.setString(1, buildPath.toString)
    val result = pstmt.executeQuery()

    var aggregate: List[BuildTry] = List[BuildTry]()
    if (!result.isClosed) {
      while ( {
        result.next
      }) {
        aggregate :+= rowToBuildTry(result)
      }
    }
    aggregate
  }

  def hadSed(buildPath: File): Boolean = {
    var builds = getBuildTry(buildPath)
    builds.exists(x => x.stdOutput.contains("[success]"))
  }

  def hadSuccesfullBuild(buildPath: File): Boolean = {
    var builds = getBuildTry(buildPath)
    builds.exists(x => x.stdOutput.contains("[success]"))
  }

  def hadSuccesfullBuild(buildPath: File, buildType: String): Boolean = {
    var builds = getBuildTry(buildPath)
    builds.exists(x => x.stdOutput.contains("[success]") && x.buildType == buildType)
  }

  def rowToBuildTry(result: ResultSet): BuildTry = {
    BuildTry(
      result.getLong("id"),
      new File(result.getString("buildPath")),
      result.getLong("buildDateTime"),
      result.getString("stdOutput"),
      result.getString("buildType"),
    )
  }
}


case class BuildTry(id: Long, buildPath: File, buildDateTime: Long, stdOutput: String, buildType: String)