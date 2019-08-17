import java.io.{File, FileWriter, IOException}
import java.nio.charset.StandardCharsets
import java.nio.file._
import java.util
import java.util.{ArrayList, List}
import java.util.stream.{Collectors, Stream}
import java.lang.reflect.{Field, Modifier}

//import net.bytebuddy.dynamic.scaffold.TypeInitializer.None

import scala.collection.mutable.ListBuffer


object Utils {

  def escapeGraphVizName(name: String): String = {
    name
      .replace("\\", "\\\\") // escape escaper
      .replace("\"", "\\\"")
      .replace("\n", "\\n")
      .replace("\r", "")
  }

  def readFile(path: File): String = {
    scala.io.Source.fromFile(path).getLines.mkString("\n")
  }

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
        || dir.contains("C:/github_download/")
        || dir.contains("D:/dev/")
        || dir.contains("C:/Users/emill/Dropbox/slimmerWorden/2018-2019-Semester2/THESIS/out/")) {
        directory.mkdirs
      }
    }

    Files.write(path, content.getBytes(StandardCharsets.UTF_8))
  }


  def getSrcMainPaths(folder: File): ListBuffer[File] = {
    var currentDepth: Int = 0

    var results = new ListBuffer[File]

    def itteration(folder: File): Unit = {
      val childs = folder.listFiles()
      if (currentDepth <= 5) {
        val childFolders = childs.filter(_.isDirectory)
        currentDepth += 1
        for (childFolder <- childFolders) {
          itteration(childFolder)
        }
        currentDepth -= 1
      }
      if (folder.getName.endsWith("src") || Files.exists(new File(folder.getAbsolutePath + "/main").toPath))
        results += folder
    }

    itteration(folder)
    results
  }

  def recursiveGetFoldersThatContainFile(f: File, name: String, currentDepth: Int = 0): Array[File] = {
    if (currentDepth >= 3) return Array()
    val childs = f.listFiles()
    var these: Array[File] = childs.filter(_.isDirectory).flatMap(ff => recursiveGetFoldersThatContainFile(ff, name, currentDepth + 1))
    if (f.listFiles.exists(a => a.getName.endsWith(name)))
      these :+= f
    these
  }

  def recursiveGetFiles(folder: File, name: String): ListBuffer[File] = {
    var currentDepth: Int = 0

    var results = new ListBuffer[File]

    def itteration(folder: File): Unit = {
      val childs = folder.listFiles()
      if (currentDepth <= 12) {
        val childFolders = childs.filter(_.isDirectory)
        currentDepth += 1
        for (childFolder <- childFolders) {
          itteration(childFolder)
        }
        currentDepth -= 1
      }
      val childFiles = childs.filter(_.isFile)
      results ++= childFiles.filter(_.getName.endsWith(name))
    }

    itteration(folder)
    results
  }

  def getFilesFromDirectory(d: File): scala.List[File] = {
    if (d.exists && d.isDirectory) {
      d.listFiles.filter(_.isFile).toList
    } else {
      scala.List.empty
    }
  }

  def getFoldersFromDirectory(d: File): scala.List[File] = {
    if (d.exists && d.isDirectory) {
      d.listFiles.filter(_.isDirectory).toList
    } else {
      scala.List.empty
    }
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

  /*
    def parseCsvFromFile(file: File): immutable.Seq[Array[String]] = {
      var result = scala.List[Array[String]]()
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
  */
  private val LEAVES = util.Arrays.asList(classOf[Boolean], classOf[Character], classOf[Byte], classOf[Short], classOf[Integer], classOf[Long], classOf[Float], classOf[Double], classOf[Void], classOf[String])

  def isValueType(clazz: Class[_]): Boolean = {
    if (clazz == null) return false
    // classOf[AnyVal].toString <- This contains a bug in scala
    if (clazz.toString.equals("class scala.AnyVal")
      || clazz.getTypeName.equals("java.lang.Number"))
      return true
    return isValueType(clazz.getSuperclass)
  }

  def getAllFields(clazz: Class[_], fields: ListBuffer[Field] = ListBuffer()): ListBuffer[Field] = {
    fields.appendAll(clazz.getDeclaredFields)
    if (clazz.getSuperclass != null) getAllFields(clazz.getSuperclass, fields)
    fields
  }

  def toStringRecursive(inst: Any): String = {
    val sb = new StringBuilder
    var depth = 0

    def recurse(o: Any): Unit = {
      if (o == null) sb.append("null")
      else if (depth >= 7) sb.append("too deep!")
      else if (isValueType(o.getClass)) sb.append(o.toString)
      else if (LEAVES.contains(o.getClass))
        sb.append(o.toString)
      else {
        sb.append("  " * depth).append(o.getClass.getName).append(": [\n")
        depth += 1
        var fields = getAllFields(o.getClass)
        for (f <- fields) {
          if (!Modifier.isStatic(f.getModifiers)) {
            f.setAccessible(true)
            sb.append("  " * depth).append(f.getName).append(": ")
            recurse(f.get(o))
            sb.append("\n")
          }
        }
        depth -= 1
        sb.append("  " * depth).append("]")
      }
    }

    recurse(inst)
    return sb.toString
  }

}
