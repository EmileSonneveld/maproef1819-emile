import java.text.SimpleDateFormat
import java.util.Calendar

class CommitStats {
  var commitHash: String = "<commitHash>"

  var nop_set: Set[String] = Set[String]()
  var noc_set: Set[String] = Set[String]()
  var nom_set: Set[String] = Set[String]()
  var cc: Int = 0
  var fanout = 0
  var calls = 0
  var loc = 0
  var andc = 0.0
  var ahh = 0.0

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
  }

}

object CommitStats {

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
}