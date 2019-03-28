import scalafix.DocumentTuple

import scala.meta.Defn
import scalafix.v1._

import scala.meta._
import scala.meta.internal.symtab.GlobalSymbolTable

object TypeHiarchy {


  def calculate(doc: DocumentTuple, symbolTable: GlobalSymbolTable) = {
    implicit var implicit_sdoc = doc.sdoc

    val tree = doc.sdoc.tree
    println("START TypeHiarchy.calculate")

    val test = doc.tdoc.symbols(0)

    val classCollection = tree.collect {
      // scala.Tuple5[scala.List[scala.meta.Mod], scala.meta.Type.Name, scala.List[scala.meta.Type.Param], scala.meta.Ctor.Primary, scala.meta.Template]
      case c@Defn.Class(mod, name, param, primary, template) => {
        if (c.name.toString() == "JoinNode") {
          val s = c.symbol(doc.sdoc)
          //symbolTable.info()
          println(c.name + ": " + s.value.toString)

        }
      }
      case option@Term.Select(Term.Name("Option"), Term.Name("apply")) => {
        println("synthetic = " + option.synthetic)
        println("structure = " + option.synthetic.structure)
      }

      //case q: Defn.Object => q.name
    }


    println("DONE TypeHiarchy.calculate")
  }

}