package 
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.SQLiteProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = BuildTries.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table BuildTries
   *  @param id Database column id SqlType(INTEGER), AutoInc, PrimaryKey
   *  @param builddatetime Database column buildDateTime SqlType(TEXT)
   *  @param buildpath Database column buildPath SqlType(TEXT)
   *  @param stdoutput Database column stdOutput SqlType(TEXT)
   *  @param buildtype Database column buildType SqlType(TEXT) */
  case class BuildTriesRow(id: Int, builddatetime: String, buildpath: String, stdoutput: String, buildtype: Option[String])
  /** GetResult implicit for fetching BuildTriesRow objects using plain SQL queries */
  implicit def GetResultBuildTriesRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[String]]): GR[BuildTriesRow] = GR{
    prs => import prs._
    BuildTriesRow.tupled((<<[Int], <<[String], <<[String], <<[String], <<?[String]))
  }
  /** Table description of table build_tries. Objects of this class serve as prototypes for rows in queries. */
  class BuildTries(_tableTag: Tag) extends profile.api.Table[BuildTriesRow](_tableTag, "build_tries") {
    def * = (id, builddatetime, buildpath, stdoutput, buildtype) <> (BuildTriesRow.tupled, BuildTriesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(builddatetime), Rep.Some(buildpath), Rep.Some(stdoutput), buildtype)).shaped.<>({r=>import r._; _1.map(_=> BuildTriesRow.tupled((_1.get, _2.get, _3.get, _4.get, _5)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INTEGER), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column buildDateTime SqlType(TEXT) */
    val builddatetime: Rep[String] = column[String]("buildDateTime")
    /** Database column buildPath SqlType(TEXT) */
    val buildpath: Rep[String] = column[String]("buildPath")
    /** Database column stdOutput SqlType(TEXT) */
    val stdoutput: Rep[String] = column[String]("stdOutput")
    /** Database column buildType SqlType(TEXT) */
    val buildtype: Rep[Option[String]] = column[Option[String]]("buildType")
  }
  /** Collection-like TableQuery object for table BuildTries */
  lazy val BuildTries = new TableQuery(tag => new BuildTries(tag))
}
