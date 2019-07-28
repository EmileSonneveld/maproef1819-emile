import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
import slick.jdbc.H2Profile.api._
import slick.jdbc.SQLiteProfile

import com.typesafe.config.ConfigFactory

/**
  * This generates ORM bindings from the database scheme to .scala code
  * http://slick.lightbend.com/doc/3.3.1/code-generation.html
  */
object SlickBinding {

  def main(args: Array[String]): Unit = {
    val applicationConfig = ConfigFactory.load()
    val profile = "slick.jdbc.SQLiteProfile"
    val jdbcDriver = applicationConfig.getString("slickEmileProfile.driver") //"org.sqlite.JDBC"
    val url = applicationConfig.getString("slickEmileProfile.url") // "jdbc:sqlite:C:\\Use...st.sqlite"
    val pkg = ""
    val outputFolder = "src\\main\\scala\\slickEmileProfile"

    slick.codegen.SourceCodeGenerator.main(
      Array(profile, jdbcDriver, url, outputFolder, pkg)
    )
  }
}
