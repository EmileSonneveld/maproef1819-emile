import java.io.File

import scala.collection.immutable

object Utils {

  def stringify(s: String): String = s"${'"'}$s${'"'}"

  def escapeSyntax(syntax: String): String =  {
    stringify(syntax.toString.replaceAll("\n", "").replaceAll("\"", "'"))
  }

  // https://stackoverflow.com/a/2638109/1448736
  def recursiveListFiles(f: File): Array[File] = {
    val these = f.listFiles()
    these ++ these.filter(_.isDirectory).flatMap(recursiveListFiles)
  }

  def parseCsvFromFile(file: File): immutable.Seq[Array[String]] = {

    var result = List[Array[String]]()
    if (file.exists) {
      val bufferedSource = scala.io.Source.fromFile(file)
      for (line <- bufferedSource.getLines) {
        if(line != "") {
          val cols: Array[String] = line.split(",").map(_.trim)
          result = cols :: result
        }
      }
      bufferedSource.close
    }
    return result
  }
}
