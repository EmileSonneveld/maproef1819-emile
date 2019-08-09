package slickEmileProfile
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
  lazy val schema: profile.SchemaDescription = BuildTries.schema ++ JavaPyramid.schema ++ PyramidStats.schema ++ PyramidStatsJava.schema
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

  /** Entity class storing rows of table JavaPyramid
   *  @param id Database column id SqlType(INTEGER), AutoInc, PrimaryKey
   *  @param datetime Database column dateTime SqlType(TEXT)
   *  @param path Database column path SqlType(TEXT)
   *  @param output Database column output SqlType(TEXT) */
  case class JavaPyramidRow(id: Int, datetime: String, path: String, output: String)
  /** GetResult implicit for fetching JavaPyramidRow objects using plain SQL queries */
  implicit def GetResultJavaPyramidRow(implicit e0: GR[Int], e1: GR[String]): GR[JavaPyramidRow] = GR{
    prs => import prs._
    JavaPyramidRow.tupled((<<[Int], <<[String], <<[String], <<[String]))
  }
  /** Table description of table java_pyramid. Objects of this class serve as prototypes for rows in queries. */
  class JavaPyramid(_tableTag: Tag) extends profile.api.Table[JavaPyramidRow](_tableTag, "java_pyramid") {
    def * = (id, datetime, path, output) <> (JavaPyramidRow.tupled, JavaPyramidRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(datetime), Rep.Some(path), Rep.Some(output))).shaped.<>({r=>import r._; _1.map(_=> JavaPyramidRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INTEGER), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column dateTime SqlType(TEXT) */
    val datetime: Rep[String] = column[String]("dateTime")
    /** Database column path SqlType(TEXT) */
    val path: Rep[String] = column[String]("path")
    /** Database column output SqlType(TEXT) */
    val output: Rep[String] = column[String]("output")
  }
  /** Collection-like TableQuery object for table JavaPyramid */
  lazy val JavaPyramid = new TableQuery(tag => new JavaPyramid(tag))

  /** Entity class storing rows of table PyramidStats
   *  @param id Database column id SqlType(INTEGER), AutoInc, PrimaryKey
   *  @param project Database column project SqlType(TEXT)
   *  @param commithash Database column commitHash SqlType(TEXT)
   *  @param timestamp Database column timestamp SqlType(TEXT)
   *  @param nop Database column nop SqlType(INTEGER)
   *  @param noc Database column noc SqlType(INTEGER)
   *  @param nom Database column nom SqlType(INTEGER)
   *  @param loc Database column loc SqlType(INTEGER)
   *  @param cc Database column cc SqlType(INTEGER)
   *  @param andc Database column andc SqlType(REAL)
   *  @param ahh Database column ahh SqlType(REAL)
   *  @param calls Database column calls SqlType(INTEGER)
   *  @param fanout Database column fanout SqlType(INTEGER)
   *  @param powershellLoc Database column powershell_LOC SqlType(INTEGER)
   *  @param regexdefmatches Database column regexDefMatches SqlType(INTEGER) */
  case class PyramidStatsRow(id: Int, project: String, commithash: String, timestamp: String, nop: Int, noc: Int, nom: Int, loc: Int, cc: Int, andc: Option[Double], ahh: Option[Double], calls: Option[Int], fanout: Option[Int], powershellLoc: Option[Int], regexdefmatches: Option[Int])
  /** GetResult implicit for fetching PyramidStatsRow objects using plain SQL queries */
  implicit def GetResultPyramidStatsRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[Double]], e3: GR[Option[Int]]): GR[PyramidStatsRow] = GR{
    prs => import prs._
    PyramidStatsRow.tupled((<<[Int], <<[String], <<[String], <<[String], <<[Int], <<[Int], <<[Int], <<[Int], <<[Int], <<?[Double], <<?[Double], <<?[Int], <<?[Int], <<?[Int], <<?[Int]))
  }
  /** Table description of table pyramid_stats. Objects of this class serve as prototypes for rows in queries. */
  class PyramidStats(_tableTag: Tag) extends profile.api.Table[PyramidStatsRow](_tableTag, "pyramid_stats") {
    def * = (id, project, commithash, timestamp, nop, noc, nom, loc, cc, andc, ahh, calls, fanout, powershellLoc, regexdefmatches) <> (PyramidStatsRow.tupled, PyramidStatsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(project), Rep.Some(commithash), Rep.Some(timestamp), Rep.Some(nop), Rep.Some(noc), Rep.Some(nom), Rep.Some(loc), Rep.Some(cc), andc, ahh, calls, fanout, powershellLoc, regexdefmatches)).shaped.<>({r=>import r._; _1.map(_=> PyramidStatsRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get, _10, _11, _12, _13, _14, _15)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INTEGER), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column project SqlType(TEXT) */
    val project: Rep[String] = column[String]("project")
    /** Database column commitHash SqlType(TEXT) */
    val commithash: Rep[String] = column[String]("commitHash")
    /** Database column timestamp SqlType(TEXT) */
    val timestamp: Rep[String] = column[String]("timestamp")
    /** Database column nop SqlType(INTEGER) */
    val nop: Rep[Int] = column[Int]("nop")
    /** Database column noc SqlType(INTEGER) */
    val noc: Rep[Int] = column[Int]("noc")
    /** Database column nom SqlType(INTEGER) */
    val nom: Rep[Int] = column[Int]("nom")
    /** Database column loc SqlType(INTEGER) */
    val loc: Rep[Int] = column[Int]("loc")
    /** Database column cc SqlType(INTEGER) */
    val cc: Rep[Int] = column[Int]("cc")
    /** Database column andc SqlType(REAL) */
    val andc: Rep[Option[Double]] = column[Option[Double]]("andc")
    /** Database column ahh SqlType(REAL) */
    val ahh: Rep[Option[Double]] = column[Option[Double]]("ahh")
    /** Database column calls SqlType(INTEGER) */
    val calls: Rep[Option[Int]] = column[Option[Int]]("calls")
    /** Database column fanout SqlType(INTEGER) */
    val fanout: Rep[Option[Int]] = column[Option[Int]]("fanout")
    /** Database column powershell_LOC SqlType(INTEGER) */
    val powershellLoc: Rep[Option[Int]] = column[Option[Int]]("powershell_LOC")
    /** Database column regexDefMatches SqlType(INTEGER) */
    val regexdefmatches: Rep[Option[Int]] = column[Option[Int]]("regexDefMatches")
  }
  /** Collection-like TableQuery object for table PyramidStats */
  lazy val PyramidStats = new TableQuery(tag => new PyramidStats(tag))

  /** Entity class storing rows of table PyramidStatsJava
   *  @param id Database column id SqlType(INTEGER), AutoInc, PrimaryKey
   *  @param project Database column project SqlType(TEXT)
   *  @param commithash Database column commitHash SqlType(TEXT)
   *  @param timestamp Database column timestamp SqlType(TEXT)
   *  @param nop Database column nop SqlType(INTEGER)
   *  @param noc Database column noc SqlType(INTEGER)
   *  @param nom Database column nom SqlType(INTEGER)
   *  @param loc Database column loc SqlType(INTEGER)
   *  @param cc Database column cc SqlType(INTEGER)
   *  @param andc Database column andc SqlType(REAL)
   *  @param ahh Database column ahh SqlType(REAL)
   *  @param calls Database column calls SqlType(INTEGER)
   *  @param fanout Database column fanout SqlType(INTEGER)
   *  @param powershellLoc Database column powershell_LOC SqlType(INTEGER) */
  case class PyramidStatsJavaRow(id: Int, project: String, commithash: String, timestamp: String, nop: Int, noc: Int, nom: Int, loc: Int, cc: Int, andc: Double, ahh: Double, calls: Int, fanout: Int, powershellLoc: Option[Int])
  /** GetResult implicit for fetching PyramidStatsJavaRow objects using plain SQL queries */
  implicit def GetResultPyramidStatsJavaRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Double], e3: GR[Option[Int]]): GR[PyramidStatsJavaRow] = GR{
    prs => import prs._
    PyramidStatsJavaRow.tupled((<<[Int], <<[String], <<[String], <<[String], <<[Int], <<[Int], <<[Int], <<[Int], <<[Int], <<[Double], <<[Double], <<[Int], <<[Int], <<?[Int]))
  }
  /** Table description of table pyramid_stats_java. Objects of this class serve as prototypes for rows in queries. */
  class PyramidStatsJava(_tableTag: Tag) extends profile.api.Table[PyramidStatsJavaRow](_tableTag, "pyramid_stats_java") {
    def * = (id, project, commithash, timestamp, nop, noc, nom, loc, cc, andc, ahh, calls, fanout, powershellLoc) <> (PyramidStatsJavaRow.tupled, PyramidStatsJavaRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(project), Rep.Some(commithash), Rep.Some(timestamp), Rep.Some(nop), Rep.Some(noc), Rep.Some(nom), Rep.Some(loc), Rep.Some(cc), Rep.Some(andc), Rep.Some(ahh), Rep.Some(calls), Rep.Some(fanout), powershellLoc)).shaped.<>({r=>import r._; _1.map(_=> PyramidStatsJavaRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get, _10.get, _11.get, _12.get, _13.get, _14)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INTEGER), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column project SqlType(TEXT) */
    val project: Rep[String] = column[String]("project")
    /** Database column commitHash SqlType(TEXT) */
    val commithash: Rep[String] = column[String]("commitHash")
    /** Database column timestamp SqlType(TEXT) */
    val timestamp: Rep[String] = column[String]("timestamp")
    /** Database column nop SqlType(INTEGER) */
    val nop: Rep[Int] = column[Int]("nop")
    /** Database column noc SqlType(INTEGER) */
    val noc: Rep[Int] = column[Int]("noc")
    /** Database column nom SqlType(INTEGER) */
    val nom: Rep[Int] = column[Int]("nom")
    /** Database column loc SqlType(INTEGER) */
    val loc: Rep[Int] = column[Int]("loc")
    /** Database column cc SqlType(INTEGER) */
    val cc: Rep[Int] = column[Int]("cc")
    /** Database column andc SqlType(REAL) */
    val andc: Rep[Double] = column[Double]("andc")
    /** Database column ahh SqlType(REAL) */
    val ahh: Rep[Double] = column[Double]("ahh")
    /** Database column calls SqlType(INTEGER) */
    val calls: Rep[Int] = column[Int]("calls")
    /** Database column fanout SqlType(INTEGER) */
    val fanout: Rep[Int] = column[Int]("fanout")
    /** Database column powershell_LOC SqlType(INTEGER) */
    val powershellLoc: Rep[Option[Int]] = column[Option[Int]]("powershell_LOC")
  }
  /** Collection-like TableQuery object for table PyramidStatsJava */
  lazy val PyramidStatsJava = new TableQuery(tag => new PyramidStatsJava(tag))
}
