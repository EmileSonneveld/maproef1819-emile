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


var files = Utils.recursiveGetFiles(
  new File("C:\\Users\\emill\\dev\\maproef1819-emile\\iPlasma_decompiled\\iPlasma_original\\iPlasma\\classes"),
  ".class")
println(files.mkString(" "))

