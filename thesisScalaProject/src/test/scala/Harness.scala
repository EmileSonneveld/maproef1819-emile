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
  test("iopiopipoopipoi") {
    assert(MeasureProject.propGetOwnerClass("org/shotdraw/util/VersionControlStrategy#assertCompatibleVersion().")
      == "org/shotdraw/util/VersionControlStrategy")
  }
}
