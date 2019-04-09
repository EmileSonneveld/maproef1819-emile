import java.text.SimpleDateFormat
import java.util.Calendar

class CommitStats {
  var commitHash: String = "<commitHash>"

  var nop_set: Set[String] = Set[String]()
  var noc_set: Set[String] = Set[String]()
  var nom_set: Set[String] = Set[String]()
  var cc_set: Set[Double] = Set[Double]()
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
    val avg_cc = this.cc_set.sum / this.cc_set.size
    val timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance.getTime)

    this.commitHash + ", " + nop + ", " + noc + ", " + nom + ", " + loc + ", " + avg_cc + ", " + timeStamp + "\n"
  }
}
