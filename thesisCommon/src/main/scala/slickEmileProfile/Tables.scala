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
  lazy val schema: profile.SchemaDescription = Array(DetectedSmell.schema, DetectedSmellJava.schema, JavaIplasmaPyramid.schema, PyramidStatsJava.schema, PyramidStatsScala.schema, SbtBuildTries.schema).reduceLeft(_ ++ _)
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table DetectedSmell
   *  @param id Database column id SqlType(INTEGER), AutoInc, PrimaryKey
   *  @param commit Database column commit SqlType(TEXT)
   *  @param fullyQualifiedName Database column fully_qualified_name SqlType(TEXT)
   *  @param `type` Database column type SqlType(TEXT)
   *  @param log Database column log SqlType(TEXT) */
  case class DetectedSmellRow(id: Int, commit: String, fullyQualifiedName: String, `type`: String, log: String)
  /** GetResult implicit for fetching DetectedSmellRow objects using plain SQL queries */
  implicit def GetResultDetectedSmellRow(implicit e0: GR[Int], e1: GR[String]): GR[DetectedSmellRow] = GR{
    prs => import prs._
    DetectedSmellRow.tupled((<<[Int], <<[String], <<[String], <<[String], <<[String]))
  }
  /** Table description of table detected_smell. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala keywords and were escaped: type */
  class DetectedSmell(_tableTag: Tag) extends profile.api.Table[DetectedSmellRow](_tableTag, "detected_smell") {
    def * = (id, commit, fullyQualifiedName, `type`, log) <> (DetectedSmellRow.tupled, DetectedSmellRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(commit), Rep.Some(fullyQualifiedName), Rep.Some(`type`), Rep.Some(log))).shaped.<>({r=>import r._; _1.map(_=> DetectedSmellRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INTEGER), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column commit SqlType(TEXT) */
    val commit: Rep[String] = column[String]("commit")
    /** Database column fully_qualified_name SqlType(TEXT) */
    val fullyQualifiedName: Rep[String] = column[String]("fully_qualified_name")
    /** Database column type SqlType(TEXT)
     *  NOTE: The name was escaped because it collided with a Scala keyword. */
    val `type`: Rep[String] = column[String]("type")
    /** Database column log SqlType(TEXT) */
    val log: Rep[String] = column[String]("log")
  }
  /** Collection-like TableQuery object for table DetectedSmell */
  lazy val DetectedSmell = new TableQuery(tag => new DetectedSmell(tag))

  /** Entity class storing rows of table DetectedSmellJava
   *  @param id Database column id SqlType(INTEGER), AutoInc, PrimaryKey
   *  @param commit Database column commit SqlType(TEXT)
   *  @param javaFile Database column java_file SqlType(TEXT)
   *  @param line Database column line SqlType(INTEGER)
   *  @param `type` Database column type SqlType(TEXT)
   *  @param wmc Database column WMC SqlType(INTEGER)
   *  @param atfd Database column ATFD SqlType(INTEGER)
   *  @param tcc Database column TCC SqlType(REAL) */
  case class DetectedSmellJavaRow(id: Int, commit: String, javaFile: String, line: Int, `type`: String, wmc: Int, atfd: Int, tcc: Double)
  /** GetResult implicit for fetching DetectedSmellJavaRow objects using plain SQL queries */
  implicit def GetResultDetectedSmellJavaRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Double]): GR[DetectedSmellJavaRow] = GR{
    prs => import prs._
    DetectedSmellJavaRow.tupled((<<[Int], <<[String], <<[String], <<[Int], <<[String], <<[Int], <<[Int], <<[Double]))
  }
  /** Table description of table detected_smell_java. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala keywords and were escaped: type */
  class DetectedSmellJava(_tableTag: Tag) extends profile.api.Table[DetectedSmellJavaRow](_tableTag, "detected_smell_java") {
    def * = (id, commit, javaFile, line, `type`, wmc, atfd, tcc) <> (DetectedSmellJavaRow.tupled, DetectedSmellJavaRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(commit), Rep.Some(javaFile), Rep.Some(line), Rep.Some(`type`), Rep.Some(wmc), Rep.Some(atfd), Rep.Some(tcc))).shaped.<>({r=>import r._; _1.map(_=> DetectedSmellJavaRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INTEGER), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column commit SqlType(TEXT) */
    val commit: Rep[String] = column[String]("commit")
    /** Database column java_file SqlType(TEXT) */
    val javaFile: Rep[String] = column[String]("java_file")
    /** Database column line SqlType(INTEGER) */
    val line: Rep[Int] = column[Int]("line")
    /** Database column type SqlType(TEXT)
     *  NOTE: The name was escaped because it collided with a Scala keyword. */
    val `type`: Rep[String] = column[String]("type")
    /** Database column WMC SqlType(INTEGER) */
    val wmc: Rep[Int] = column[Int]("WMC")
    /** Database column ATFD SqlType(INTEGER) */
    val atfd: Rep[Int] = column[Int]("ATFD")
    /** Database column TCC SqlType(REAL) */
    val tcc: Rep[Double] = column[Double]("TCC")
  }
  /** Collection-like TableQuery object for table DetectedSmellJava */
  lazy val DetectedSmellJava = new TableQuery(tag => new DetectedSmellJava(tag))

  /** Entity class storing rows of table JavaIplasmaPyramid
   *  @param id Database column id SqlType(INTEGER), AutoInc, PrimaryKey
   *  @param datetime Database column dateTime SqlType(TEXT)
   *  @param path Database column path SqlType(TEXT)
   *  @param output Database column output SqlType(TEXT) */
  case class JavaIplasmaPyramidRow(id: Int, datetime: String, path: String, output: String)
  /** GetResult implicit for fetching JavaIplasmaPyramidRow objects using plain SQL queries */
  implicit def GetResultJavaIplasmaPyramidRow(implicit e0: GR[Int], e1: GR[String]): GR[JavaIplasmaPyramidRow] = GR{
    prs => import prs._
    JavaIplasmaPyramidRow.tupled((<<[Int], <<[String], <<[String], <<[String]))
  }
  /** Table description of table java_iplasma_pyramid. Objects of this class serve as prototypes for rows in queries. */
  class JavaIplasmaPyramid(_tableTag: Tag) extends profile.api.Table[JavaIplasmaPyramidRow](_tableTag, "java_iplasma_pyramid") {
    def * = (id, datetime, path, output) <> (JavaIplasmaPyramidRow.tupled, JavaIplasmaPyramidRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(datetime), Rep.Some(path), Rep.Some(output))).shaped.<>({r=>import r._; _1.map(_=> JavaIplasmaPyramidRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INTEGER), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column dateTime SqlType(TEXT) */
    val datetime: Rep[String] = column[String]("dateTime")
    /** Database column path SqlType(TEXT) */
    val path: Rep[String] = column[String]("path")
    /** Database column output SqlType(TEXT) */
    val output: Rep[String] = column[String]("output")

    /** Index over (path) (database name path_index) */
    val index1 = index("path_index", path)
  }
  /** Collection-like TableQuery object for table JavaIplasmaPyramid */
  lazy val JavaIplasmaPyramid = new TableQuery(tag => new JavaIplasmaPyramid(tag))

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
   *  @param powershellLoc Database column powershell_LOC SqlType(INTEGER)
   *  @param detectedSmells Database column detected_smells SqlType(INTEGER) */
  case class PyramidStatsJavaRow(id: Int, project: String, commithash: String, timestamp: String, nop: Int, noc: Int, nom: Int, loc: Int, cc: Int, andc: Double, ahh: Double, calls: Int, fanout: Int, powershellLoc: Option[Int], detectedSmells: Option[Int])
  /** GetResult implicit for fetching PyramidStatsJavaRow objects using plain SQL queries */
  implicit def GetResultPyramidStatsJavaRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Double], e3: GR[Option[Int]]): GR[PyramidStatsJavaRow] = GR{
    prs => import prs._
    PyramidStatsJavaRow.tupled((<<[Int], <<[String], <<[String], <<[String], <<[Int], <<[Int], <<[Int], <<[Int], <<[Int], <<[Double], <<[Double], <<[Int], <<[Int], <<?[Int], <<?[Int]))
  }
  /** Table description of table pyramid_stats_java. Objects of this class serve as prototypes for rows in queries. */
  class PyramidStatsJava(_tableTag: Tag) extends profile.api.Table[PyramidStatsJavaRow](_tableTag, "pyramid_stats_java") {
    def * = (id, project, commithash, timestamp, nop, noc, nom, loc, cc, andc, ahh, calls, fanout, powershellLoc, detectedSmells) <> (PyramidStatsJavaRow.tupled, PyramidStatsJavaRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(project), Rep.Some(commithash), Rep.Some(timestamp), Rep.Some(nop), Rep.Some(noc), Rep.Some(nom), Rep.Some(loc), Rep.Some(cc), Rep.Some(andc), Rep.Some(ahh), Rep.Some(calls), Rep.Some(fanout), powershellLoc, detectedSmells)).shaped.<>({r=>import r._; _1.map(_=> PyramidStatsJavaRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get, _10.get, _11.get, _12.get, _13.get, _14, _15)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

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
    /** Database column detected_smells SqlType(INTEGER) */
    val detectedSmells: Rep[Option[Int]] = column[Option[Int]]("detected_smells")
  }
  /** Collection-like TableQuery object for table PyramidStatsJava */
  lazy val PyramidStatsJava = new TableQuery(tag => new PyramidStatsJava(tag))

  /** Entity class storing rows of table PyramidStatsScala
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
   *  @param regexdefmatches Database column regexDefMatches SqlType(INTEGER)
   *  @param detectedSmells Database column detected_smells SqlType(INTEGER) */
  case class PyramidStatsScalaRow(id: Int, project: String, commithash: String, timestamp: String, nop: Int, noc: Int, nom: Int, loc: Int, cc: Int, andc: Option[Double], ahh: Option[Double], calls: Option[Int], fanout: Option[Int], powershellLoc: Option[Int], regexdefmatches: Option[Int], detectedSmells: Option[Int])
  /** GetResult implicit for fetching PyramidStatsScalaRow objects using plain SQL queries */
  implicit def GetResultPyramidStatsScalaRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[Double]], e3: GR[Option[Int]]): GR[PyramidStatsScalaRow] = GR{
    prs => import prs._
    PyramidStatsScalaRow.tupled((<<[Int], <<[String], <<[String], <<[String], <<[Int], <<[Int], <<[Int], <<[Int], <<[Int], <<?[Double], <<?[Double], <<?[Int], <<?[Int], <<?[Int], <<?[Int], <<?[Int]))
  }
  /** Table description of table pyramid_stats_scala. Objects of this class serve as prototypes for rows in queries. */
  class PyramidStatsScala(_tableTag: Tag) extends profile.api.Table[PyramidStatsScalaRow](_tableTag, "pyramid_stats_scala") {
    def * = (id, project, commithash, timestamp, nop, noc, nom, loc, cc, andc, ahh, calls, fanout, powershellLoc, regexdefmatches, detectedSmells) <> (PyramidStatsScalaRow.tupled, PyramidStatsScalaRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(project), Rep.Some(commithash), Rep.Some(timestamp), Rep.Some(nop), Rep.Some(noc), Rep.Some(nom), Rep.Some(loc), Rep.Some(cc), andc, ahh, calls, fanout, powershellLoc, regexdefmatches, detectedSmells)).shaped.<>({r=>import r._; _1.map(_=> PyramidStatsScalaRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get, _10, _11, _12, _13, _14, _15, _16)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

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
    /** Database column detected_smells SqlType(INTEGER) */
    val detectedSmells: Rep[Option[Int]] = column[Option[Int]]("detected_smells")
  }
  /** Collection-like TableQuery object for table PyramidStatsScala */
  lazy val PyramidStatsScala = new TableQuery(tag => new PyramidStatsScala(tag))

  /** Entity class storing rows of table SbtBuildTries
   *  @param id Database column id SqlType(INTEGER), AutoInc, PrimaryKey
   *  @param builddatetime Database column buildDateTime SqlType(TEXT)
   *  @param buildpath Database column buildPath SqlType(TEXT)
   *  @param stdoutput Database column stdOutput SqlType(TEXT)
   *  @param buildtype Database column buildType SqlType(TEXT) */
  case class SbtBuildTriesRow(id: Int, builddatetime: String, buildpath: String, stdoutput: String, buildtype: Option[String])
  /** GetResult implicit for fetching SbtBuildTriesRow objects using plain SQL queries */
  implicit def GetResultSbtBuildTriesRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[String]]): GR[SbtBuildTriesRow] = GR{
    prs => import prs._
    SbtBuildTriesRow.tupled((<<[Int], <<[String], <<[String], <<[String], <<?[String]))
  }
  /** Table description of table sbt_build_tries. Objects of this class serve as prototypes for rows in queries. */
  class SbtBuildTries(_tableTag: Tag) extends profile.api.Table[SbtBuildTriesRow](_tableTag, "sbt_build_tries") {
    def * = (id, builddatetime, buildpath, stdoutput, buildtype) <> (SbtBuildTriesRow.tupled, SbtBuildTriesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(builddatetime), Rep.Some(buildpath), Rep.Some(stdoutput), buildtype)).shaped.<>({r=>import r._; _1.map(_=> SbtBuildTriesRow.tupled((_1.get, _2.get, _3.get, _4.get, _5)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

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
  /** Collection-like TableQuery object for table SbtBuildTries */
  lazy val SbtBuildTries = new TableQuery(tag => new SbtBuildTries(tag))
}
