import java.io.File
import java.sql.Timestamp
import java.util.Date

import MeasureProject.{externalProperties, internalProperties}
import main.calculationsOnProjectWrap
import org.scalatest.FunSuite
import scalafix.SemanticDB
import scalafix.v1._
import slickEmileProfile.Tables

import scala.collection.mutable
import scala.io.Source
import scala.meta.{Defn, Tree}


class NestingLevelTest extends FunSuite {

  test("testNesting") {

    val semanticDB = new SemanticDB(new File("..\\testScala"))
    for (doc <- semanticDB.documents) {
      implicit val implicit_sdoc_fkurhgbsdf: SemanticDocument = doc.sdoc
      println("\nDoc: " + doc.tdoc.uri)

      doc.sdoc.tree.collect {
        case d: Defn.Def =>
          println("Def: " + d.name.value)
          if (d.name.value == "methodInD") assert(MeasureProject.calculateNestingLevel(d) == 0)
          if (d.name.value == "def_method") assert(MeasureProject.calculateNestingLevel(d) == 1)
          if (d.name.value == "def_method_nested_if") assert(MeasureProject.calculateNestingLevel(d) == 2)
          if (d.name.value == "example") assert(MeasureProject.calculateNestingLevel(d) == 3)
      }
    }
  }
}
