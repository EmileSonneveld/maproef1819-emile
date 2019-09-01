import java.io.File

import MeasureProject.{externalProperties, internalProperties}
import main.calculationsOnProjectWrap
import org.scalatest.{Assertion, FunSuite}
import scalafix.SemanticDB
import scalafix.v1._

import scala.collection.mutable
import scala.io.Source
import scala.meta.{Defn, Tree}

class TypesTest extends FunSuite {
  def EmileAssert(b: Boolean): Assertion = {
    if (!b) {
      println("Oops!")
    }
    assert(b)
  }

  test("test1") {

    val semanticDB = new SemanticDB(new File("..\\testScala"))
    for (doc <- semanticDB.documents) {
      implicit val sdlifhbduogdfhg: SemanticDocument = doc.sdoc
      println("\nDoc: " + doc.tdoc.uri)

      def handleTraitOrClass(c: Tree) = {
        {
          val className = MeasureProject.traitOrClassName(c)
          println("Class: " + className)
          //println("getAllParentSymbs: " + MeasureProject.getAllParentSymbs(semanticDB, c.symbol(doc.sdoc).value.toString))

          {
            val externalPropertiesSet = externalProperties(c.symbol.value, c, semanticDB, doc.sdoc)
            //println("externalPropertiesSet: " + externalPropertiesSet)

            val internalPropertiesSet = internalProperties(c.symbol.value, c, semanticDB, doc.sdoc)
            println("internalPropertiesSet: " + internalPropertiesSet)
            assert(externalPropertiesSet.intersect(internalPropertiesSet).size == 0)

            className match {
              case "C" =>
                assert(externalPropertiesSet.exists(p => p.contains("property_ObjectType")))
                assert(internalPropertiesSet.exists(p => p.contains("propA")))
                assert(!internalPropertiesSet.exists(p => p.contains("#this")))
              case "D" =>
                assert(externalPropertiesSet.exists(p => p.contains("property_ObjectType")))
                assert(internalPropertiesSet.exists(p => p.contains("propA")))
                assert(internalPropertiesSet.exists(p => p.contains("propD")))
                assert(internalPropertiesSet.exists(p => p.contains("#this")))
              case _ => // ignore
            }
          }

          c.collect {
            case d@Defn.Def(_, name, _, _, _, _) =>
              println("Def: " + d.name.value)

              val methodExternalPropsSet = externalProperties(c.symbol.value, d, semanticDB, doc.sdoc)

              val methodInternalPropsSet = internalProperties(c.symbol.value, d, semanticDB, doc.sdoc)

              (className, d.name.value) match {
                case ("D", "methodInD") =>
                  println("methodExternalPropsSet: " + methodExternalPropsSet)
                  println("methodInternalPropsSet: " + methodInternalPropsSet)
                  EmileAssert(methodExternalPropsSet.exists(p => p.contains("property_ObjectType")))
                  EmileAssert(methodInternalPropsSet.exists(p => p.contains("propA")))
                  EmileAssert(methodInternalPropsSet.exists(p => p.contains("propD")))
                  EmileAssert(methodInternalPropsSet.exists(p => !p.contains("external")))
                  EmileAssert(methodInternalPropsSet.exists(p => !p.contains("totallyLocal")))
                  EmileAssert(!methodInternalPropsSet.contains(""))
                case _ => // ignore
              }
          }
        }
      }

      doc.sdoc.tree.collect {
        case c: Defn.Class => handleTraitOrClass(c)
        case c: Defn.Trait => handleTraitOrClass(c)
      }
    }
  }
}
