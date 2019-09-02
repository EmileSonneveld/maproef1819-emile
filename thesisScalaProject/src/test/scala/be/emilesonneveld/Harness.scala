package be.emilesonneveld

import org.scalatest.FunSuite


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
