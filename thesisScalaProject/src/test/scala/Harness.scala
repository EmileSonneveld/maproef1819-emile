import java.io.File

import MeasureProject.{externalProperties, internalProperties}
import main.calculationsOnProjectWrap
import org.scalatest.FunSuite
import scalafix.SemanticDB
import scalafix.v1._ // for the symbol magic

import scala.collection.mutable
import scala.io.Source
import scala.meta.{Defn, Tree}


class Harness extends FunSuite {
  test("MeasureProject.propGetOwnerClass") {
    assert(MeasureProject.propGetOwnerClass("org/shotdraw/util/VersionControlStrategy#assertCompatibleVersion().")
      == "org/shotdraw/util/VersionControlStrategy")
  }


  test("Utils.toStringRecursive") {

    class CoStWrap extends CommitStats
    val c = new CoStWrap()
    c.noc_set += "aaaaaaa"
    c.noc_set += "bbbbbbb"
    c.noc_set += "ccccccc"

    val output = Utils.toStringRecursive(c)
    assert(output.contains("aaaaaaa"))
    assert(output.contains("bbbbbbb"))
    println(output)
  }
}
