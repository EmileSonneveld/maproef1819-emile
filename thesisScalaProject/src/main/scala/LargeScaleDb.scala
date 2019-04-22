import java.io.File
import java.security.PublicKey
import java.sql.{Connection, DriverManager, PreparedStatement}

object LargeScaleDb {

  private var conn: Connection = null

  try {
    Class.forName("org.sqlite.JDBC")
    conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\emill\\Dropbox\\slimmerWorden\\2018-2019-Semester2\\THESIS\\LargeScaleDb.sqlite")
  } catch {
    case e: Exception =>
      System.err.println(e.getClass.getName + ": " + e.getMessage)
      System.exit(0)
  }

  def insertBuildTry(buildPath: File, stdOutput: String): Unit = {
    val pstmt = conn.prepareStatement("INSERT INTO build_tries VALUES (NULL, ?, ?)")
    var i = 0
    pstmt.setString({
      i += 1
      i
    }, buildPath.toString)
    pstmt.setString({
      i += 1
      i
    }, stdOutput)
    pstmt.executeUpdate
  }
}
