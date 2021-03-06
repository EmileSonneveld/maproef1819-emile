package be.emilesonneveld

import java.io.File

import org.scalatest.FunSuite
import scalafix.SemanticDB
import scalafix.v1._

import scala.meta.internal.semanticdb.SymbolInformation
import scala.meta.{Defn, Term}

class CallsMetricTest extends FunSuite {

  // Mostly broken test!
  test("CallsMetricTest_broken") {


    val semanticDB = new SemanticDB(new File("..\\testScala"))
    for (doc <- semanticDB.documents) {
      implicit val sdlifhbduogdfhg: SemanticDocument = doc.sdoc
      if (doc.tdoc.uri.contains("CallsMetric")) {
        println("\nDoc: " + doc.tdoc.uri)

        // Todo, find a way to capture function calls in class body

        doc.sdoc.tree.collect {
          case defn: Defn.Def => {
            var calls = Set.empty[String]
            defn.body.collect {
              //case a: Term.Apply => {
              //
              //  var s = SemanticDB.getFromSymbolTable(a.fun, doc.sdoc)
              //  calls += s.value
              //}
              case a: Term.Name => {

                var s = SemanticDB.getFromSymbolTable(a, doc.sdoc)
                if (!s.isLocal && !s.isNone) {
                  val info = semanticDB.getInfo(s.value)
                  if (info.kind == SymbolInformation.Kind.METHOD) {
                    calls += s.value
                  }
                }
              }
            }
            if (defn.toString().contains("theImportantDef")) {
              assert(calls.contains("scala/Predef.print()."))
              assert(!calls.contains("_empty_/CallsMetric#theImportantDef()."))
            }
          }
        }
      }
    }
  }
}