import java.io.File

import MeasureProject.{externalProperties, internalProperties}
import main.calculationsOnProjectWrap
import org.scalatest.FunSuite
import scalafix.SemanticDB
import scalafix.v1._

import scala.collection.mutable
import scala.io.Source
import scala.meta.{Defn, Tree}

class TypesTest extends FunSuite {
  test("test1") {

    val semanticDB = new SemanticDB(new File("C:\\Users\\emill\\dev\\testScala"))
    for (doc <- semanticDB.documents) {
      println("\n Doc: " + doc.tdoc.uri)

      def handleTraitOrClass(c:Tree) = {
        {
          println("Class: " + c.toString())
          println("getAllParentSymbs: " + MeasureProject.getAllParentSymbs(semanticDB, c.symbol(doc.sdoc).value.toString))

          c.collect {
            case d@Defn.Def(_, name, _, _, _, _) =>
              println("Def: " + d.name.value)

              val methodExternalPropsSet = externalProperties(c, d, semanticDB, doc.sdoc)
              println("methodExternalPropsSet: " + methodExternalPropsSet)

              val methodInternalPropsSet = internalProperties(c, d, semanticDB, doc.sdoc)
              println("methodInternalPropsSet: " + methodInternalPropsSet)
          }
        }
      }
      doc.sdoc.tree.collect {
        case c: Defn.Class => handleTraitOrClass(c)
      }
    }
  }
}
