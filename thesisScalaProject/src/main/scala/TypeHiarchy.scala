import scalafix.DocumentTuple
import scalafix.v1._

import scala.collection.mutable.ArrayBuffer
import scala.meta.{Defn, _}
import scala.meta.internal.semanticdb
import scala.meta.internal.symtab.GlobalSymbolTable

class TypeHiarchy(val symbolTable: GlobalSymbolTable) {

  class TypeGraphNode(val symbol: String) {
    def name: String = symbol.toString

    val linksTo: ArrayBuffer[TypeGraphNode] = ArrayBuffer.empty[TypeGraphNode]
  }

  private val symbolList = ArrayBuffer.empty[TypeGraphNode]

  //def GetNodeByPath(path: String) = {
  //  symbolList.find(_.name == path).get
  //}

  def calculateANDC(): Double = {
    var sum = 0.0
    var count = 0.0
    for (n <- symbolList) {
      sum += n.linksTo.length
      count += 1
    }
    return sum / count
  }

  def calculateAHH(): Double = {
    if (symbolList.length == 0)
      return 0
    var depthList = ArrayBuffer.empty[Int]
    for (_ <- symbolList.indices) {
      depthList += 0
    }
    for (i <- symbolList.indices) {
      rec(0, i)
    }

    def rec(depth: Int, nodeIdx: Int): Unit = {
      depthList(nodeIdx) = depth
      var node = symbolList(nodeIdx)
      for (node <- node.linksTo) {
        var childIdx = symbolList.indexOf(node)
        if (depthList(childIdx) < depth + 1)
          rec(depth + 1, childIdx)
      }
    }

    return depthList.sum.toDouble / depthList.length
  }

  private def addOrReturnSymbol(symbol: String): TypeGraphNode = {
    val node = symbolList.find(_.name == symbol.toString)
    node match {
      case Some(value) => value
      case _ => {
        val newNode = new TypeGraphNode(symbol)
        symbolList += newNode
        newNode
      }
    }
  }


  def nodesToGraphViz(nodes: ArrayBuffer[TypeGraphNode]): String = {
    var sb = new StringBuilder()
    sb ++= "// You can visualise this file here: http://webgraphviz.com\n"
    sb ++= "digraph {\n"
    sb ++= "  rankdir=BT;\n"
    sb ++= "	node [shape = rectangle, style=filled, color=\"0.650 0.200 1.000\"];\n"

    for (node <- nodes) {
      // node.nodeId + " " +
      val n1 = Utils.escapeGraphVizName(node.name)

      //if (node.linksTo.length == 0)
      sb ++= "	\"" + n1 + "\"\n" //+ "\" [label=\"" + n1 + "\"]\n"
      for (next <- node.linksTo) {
        val n2 = Utils.escapeGraphVizName(next.name)
        sb ++= "	\"" + n1 + "\"->\"" + n2 + "\"\n"
      }
    }

    sb ++= "}\n"
    sb.toString()
  }


  def absorb(doc: DocumentTuple) = {
    implicit var implicit_sdoc: SemanticDocument = doc.sdoc

    val tree = doc.sdoc.tree
    //println("START TypeHiarchy.calculate")

    //val test = doc.tdoc.symbols(0)

    val classCollection = tree.collect {
      // scala.Tuple5[scala.List[scala.meta.Mod], scala.meta.Type.Name, scala.List[scala.meta.Type.Param], scala.meta.Ctor.Primary, scala.meta.Template]
      case c@Defn.Class(mod, name, param, primary, template) => {
        //if (c.name.toString() == "JoinNode" || c.name.toString() == "TestClass" || c.name.toString() == "TestParent")
        {
          try {
            val s = c.symbol
            val node = addOrReturnSymbol(s.value.toString)
            //println(c.name + ": " + s.value.toString)

            var symInfo = symbolTable.info(s.value.toString)
            var parents = symInfo.get.signature.asInstanceOf[scala.meta.internal.semanticdb.ClassSignature].parents
            for (p <- parents) {
              node.linksTo += addOrReturnSymbol(p.asInstanceOf[semanticdb.TypeRef].symbol)
            }
            //println(parents)
          } catch {
            case ex: Throwable =>
              println("EX: " + ex)
          }
        }
      }
      case option@Term.Select(Term.Name("Option"), Term.Name("apply")) => {
        println("synthetic = " + option.synthetic)
        println("structure = " + option.synthetic.structure)
      }

      //case q: Defn.Object => q.name
    }

    //println("DONE TypeHiarchy.calculate")
  }

  def getGvString() = {
    nodesToGraphViz(symbolList)
  }

}
