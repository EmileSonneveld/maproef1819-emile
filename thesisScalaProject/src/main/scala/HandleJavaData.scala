import slickEmileProfile.Tables

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
}
