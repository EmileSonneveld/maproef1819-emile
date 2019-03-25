import java.io.{File, FileWriter}
import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Path, Paths}

import scala.collection.immutable

object Utils {

  def readFile(path: String): String = {
    scala.io.Source.fromFile(path).getLines.mkString("\n")
  }

  def writeFile(path: String, content: String): Unit = {
    writeFile(Paths.get(path), content)
  }

  def writeFile(path: Path, content: String): Unit = {

    val dir = normalizeDirectoryPath(path.getParent.toString)
    val directory = new File(dir)
    if (!directory.exists) {

      // Only make leading directories in places we know
      if (dir.contains("C:/Users/emill/dev/")
        || dir.contains("D:/github_download/")
        || dir.contains("D:/dev/")
        || dir.contains("C:/Users/emill/Dropbox/slimmerWorden/2018-2019-Semester2/THESIS/")) {
        directory.mkdirs
      }
    }

    Files.write(path, content.getBytes(StandardCharsets.UTF_8))
  }

  def normalizeDirectoryPath(path: String): String = {
    var str = path.replace("\\", "/")
    if (!str.endsWith("/")) str += "/"
    str
  }

  def appendToFile(path: String, content: String): Unit = {
    appendToFile(new File(path), content)
  }

  def appendToFile(path: File, content: String): Unit = {
    val fw = new FileWriter(path, true)
    try {
      fw.write(content)
    }
    finally fw.close()
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
        if (line != "") {
          val cols: Array[String] = line.split(",").map(_.trim)
          result = cols :: result
        }
      }
      bufferedSource.close
    }
    return result
  }
}
