import java.io.File
import java.text.{DecimalFormat, DecimalFormatSymbols}
import java.util.Locale

import slickEmileProfile.Tables
import slick.jdbc.SQLiteProfile.api._

import scala.meta._

//val df = new DecimalFormat("000.")

val otherSymbols = new DecimalFormatSymbols(Locale.getDefault())
otherSymbols.setDecimalSeparator('.')
val df = new DecimalFormat("0.##", otherSymbols)


df.format(0)
df.format(3)
df.format(10)
df.format(13)
df.format(134)
df.format(1348000)


df.format(2.0)
df.format(2.66666666666)
df.format(2.8888)


val base = new File("C:\\Users\\emill\\dev\\scalafixtemplate\\src\\main")
val path = new File("C:\\Users\\emill\\dev\\ScalafixTemplate\\src\\main\\scala\\TestCFG.scala")

val relative = base.toPath.relativize(path.toPath)

path.toPath.getParent

q"function(argument)".structure

val db = Database.forConfig("slickEmileProfile")
try {
  val build_tries: TableQuery[Tables.BuildTries] = TableQuery[Tables.BuildTries]
  var test = build_tries.findBy(_.stdoutput.like("%[success]%"))
  for (lol <- test) {
    println(lol)
  }

  // val resultFuture: Future[_] = { ... }
  //Await.result(resultFuture, Duration.Inf)
  //lines.foreach(Predef.println _)
} finally db.close
