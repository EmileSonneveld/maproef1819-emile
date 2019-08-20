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
      implicit val sdlifhbduogdfhg: SemanticDocument = doc.sdoc
      println("\nDoc: " + doc.tdoc.uri)


    }
  }
}
