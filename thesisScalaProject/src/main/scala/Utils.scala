import java.io.File

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
}
