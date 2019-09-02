package be.emilesonneveld

import java.text.SimpleDateFormat
import java.util.Calendar

import slickEmileProfile.Tables
import slickEmileProfile.Tables.PyramidStatsScalaRow

import scala.collection.mutable.ListBuffer

class CommitStats {
  var projectName: String = "<projectName>"
  var commitHash: String = "<commitHash>"

  var nop_set: Set[String] = Set[String]()
  var noc_set: Set[String] = Set[String]()
  var nom_set: ListBuffer[String] = new ListBuffer[String]()
  var cc: Int = 0
  var fanout = 0
  var calls = 0
  var loc = 0
  var andc = 0.0
  var ahh = 0.0

  var powershell_LOC = 0 // For seeking correlations with loc
  var regexDefMatches = 0 // For seeking correlations with nom

  def toPyramidStats: Tables.PyramidStatsScalaRow = {
    val nop = this.nop_set.size
    val noc = this.noc_set.size
    val nom = this.nom_set.size
    val timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance.getTime)
    PyramidStatsScalaRow(0, projectName, commitHash, timeStamp, nop, noc, nom, loc, cc,
      Option(andc), Option(ahh), Option(calls), Option(fanout),
      Option(powershell_LOC), Option(regexDefMatches), Option.empty)
  }

  /*
    def toCsvLine: String = {
      val nop = this.nop_set.size
      val noc = this.noc_set.size
      val nom = this.nom_set.size
      val loc = this.loc
      //val avg_cc = this.cc_set.sum / this.cc_set.size
      val timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance.getTime)

      commitHash + ", " +
        nop + ", " +
        noc + ", " +
        nom + ", " +
        loc + ", " +
        cc + ", " +
        andc + ", " +
        ahh + ", " +
        timeStamp + "\n"
    }*/

}

object CommitStats {
  /*
    /**
      * This is a lossy conversion
      */
    def fromCsvLine(line: Array[String]): CommitStats = {
      val n = new CommitStats
      n.commitHash = line(0)
      //n.nop= line(1)
      //n.noc= line(2)
      //n.nom = line(3)
      n.loc = line(4).toInt
      n.cc = line(5).toInt
      //n.andc = line(6).toDouble
      //n.ahh = line(7).toDouble
      //timeStamp + "\n"
      n
    }
    */
}