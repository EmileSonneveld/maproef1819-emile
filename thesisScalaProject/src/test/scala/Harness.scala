import java.io.File

import MeasureProject.{externalProperties, internalProperties}
import main.calculationsOnProjectWrap
import org.scalatest.FunSuite
import scalafix.SemanticDB
import scalafix.v1._ // for the symbol magic

import scala.collection.mutable
import scala.io.Source
import scala.meta.{Defn, Tree}


class Wrapper(val underlying: CommitStats) extends AnyVal
class Harness extends FunSuite {
  test("MeasureProject.propGetOwnerClass") {
    assert(MeasureProject.propGetOwnerClass("org/shotdraw/util/VersionControlStrategy#assertCompatibleVersion().")
      == "org/shotdraw/util/VersionControlStrategy")
  }


  test("Utils.toStringRecursive") {

    class CoStWrap extends CommitStats
    val c = new CoStWrap()
    c.noc_set += "a"
    c.noc_set += "b"
    c.noc_set += "c"

    println(Utils.toStringRecursive(c))
  }
}
