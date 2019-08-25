import slickEmileProfile.Tables

import scala.collection.mutable.ListBuffer
import scala.util.matching.Regex

object HandleJavaData {


  def tryReadDouble(r: Regex, heystack: String): Double = {
    try {
      val matches = r.findAllIn(heystack)
      if (matches.groupCount == 0) return 0
      matches.group(1).toDouble
    } catch {
      case _: Throwable => return 0 // ignore weird errors
    }
  }

  def tryReadString(r: Regex, heystack: String): String = {
    try {
      val matches = r.findAllIn(heystack)
      if (matches.groupCount == 0) return ""
      matches.group(1)
    } catch {
      case _: Throwable => return "" // ignore weird errors
    }
  }

  def updatePyramidStatsJavaFromLog(row: Tables.PyramidStatsJavaRow, iplasmaLog: String): Tables.PyramidStatsJavaRow = {
    val ANDC = tryReadDouble("""ANDC\t([0-9.,]+)""".r, iplasmaLog)
    val AHH = tryReadDouble("""AHH\t([0-9.,]+)""".r, iplasmaLog)
    val NOP = tryReadDouble("""NOP\t([0-9.,]+)""".r, iplasmaLog).toInt
    val NOC = tryReadDouble("""NOC\t\t([0-9.,]+)""".r, iplasmaLog).toInt
    val NOM = tryReadDouble("""NOM\t\t\t([0-9.,]+)\t""".r, iplasmaLog).toInt
    val LOC = tryReadDouble("""LOC\t\t\t\t([0-9.,]+)\t""".r, iplasmaLog).toInt
    val CYCLO = tryReadDouble("""CYCLO\t\t\t\t\t([0-9.,]+)\t""".r, iplasmaLog).toInt
    val CALLS = tryReadDouble("""([0-9.,]+)\tCALLS""".r, iplasmaLog).toInt
    val FANOUT = tryReadDouble("""([0-9.,]+)\t\tFANOUT""".r, iplasmaLog).toInt
    row.copy(nop = NOP, noc = NOC, nom = NOM, loc = LOC, cc = CYCLO, andc = ANDC, ahh = AHH, calls = CALLS, fanout = FANOUT)
  }


  def parsePmdOutput(pmdOutput: String): ListBuffer[Tables.DetectedSmellJavaRow] = {
    var lines: Array[String] = pmdOutput.split("\n")
    var ret = new ListBuffer[Tables.DetectedSmellJavaRow]
    for (outputLine <- lines) {
      if (outputLine.contains(".java") && outputLine.contains("Possible")) {
        val java_file = tryReadString("""(.*?\.java):""".r, outputLine)
        val line = tryReadDouble("""\.java:([0-9]+):""".r, outputLine).toInt
        val tpe = tryReadString("""(Possible [\w ]+) \(""".r, outputLine)
        val WMC = tryReadDouble("""\(WMC=([0-9]+),""".r, outputLine).toInt
        val ATFD = tryReadDouble(""" ATFD=([0-9]+),""".r, outputLine).toInt
        val TCC = tryReadDouble("""TCC=([0-9.]+)%\)""".r, outputLine)
        val row = Tables.DetectedSmellJavaRow(0, "<commit>", java_file, line, tpe, WMC, ATFD, TCC)
        ret += row
      }
    }
    ret
  }
}
