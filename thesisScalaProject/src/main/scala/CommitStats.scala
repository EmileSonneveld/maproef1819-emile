class CommitStats {
  var commitHash: String = ""

  var nop_set: Set[String] = Set[String]()
  var noc_set: Set[String] = Set[String]()
  var nom_set: Set[String] = Set[String]()
  var cc_set: Set[Double] = Set[Double]()
  var fanout = 0
  var calls = 0
  var loc = 0
}