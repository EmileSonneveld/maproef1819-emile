import java.text.{DecimalFormat, DecimalFormatSymbols}
import java.util.Locale

import scalafix.{DocumentTuple, SemanticDB}
import scalafix.v1._

import scala.collection.mutable.ArrayBuffer
import scala.meta.{Defn, _}
//import scala.meta.internal.semanticdb
import scala.meta.internal.symtab.GlobalSymbolTable

class TypeHiarchy(semanticDB: SemanticDB) {
  val symbolTable: GlobalSymbolTable = semanticDB.symbolTable

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

  def getPackageName(symbolString: String): String = {
    var slashIndex = symbolString.indexOf("/")
    if (slashIndex == -1) return symbolString // could be local class
    //slashIndex = symbolString.indexOf("/", slashIndex + 1)
    slashIndex = Integer.max(slashIndex, symbolString.indexOf("/", slashIndex + 1))
    symbolString.substring(0, slashIndex)
  }

  def getHlsColorForString(str: String): String = {
    val percent1 = ((str + "A").hashCode.doubleValue() / Integer.MAX_VALUE.doubleValue() + 1) / 2
    val percent2 = ((str + "B").hashCode.doubleValue() / Integer.MAX_VALUE.doubleValue() + 1) / 2

    val otherSymbols = new DecimalFormatSymbols(Locale.getDefault())
    otherSymbols.setDecimalSeparator('.')
    val df = new DecimalFormat("0.###", otherSymbols)
    df.format(percent1) + " " + df.format(0.15 + percent2 * 0.4) + " 1.0"
  }

  def nodesToGraphViz(nodes: ArrayBuffer[TypeGraphNode]): String = {
    var sb = new StringBuilder()
    sb ++= "// You can visualise this file here: http://webgraphviz.com\n"
    sb ++= "digraph {\n"
    sb ++= "  rankdir=BT;\n"
    sb ++= "	node [shape = rectangle, style=filled, color=\"0.650 0.200 1.000\"];\n"

    for (node <- nodes) {
      if (node.name != "scala/AnyRef#") {
        // node.nodeId + " " +
        val n1 = Utils.escapeGraphVizName(node.name)

        //if (node.linksTo.length == 0)
        var nam = getPackageName(node.name)
        sb ++= "	\"" + n1 + "\" [color=\"" + getHlsColorForString(nam) + "\" ]\n"
        for (next <- node.linksTo) {

          if (next.name != "scala/AnyRef#") {
            val n2 = Utils.escapeGraphVizName(next.name)
            sb ++= "	\"" + n1 + "\"->\"" + n2 + "\"\n"
          }
        }
      }

    }

    sb ++= "}\n"
    sb.toString()
  }


  def absorb(doc: DocumentTuple): Unit = {
    implicit var implicit_sdoc: SemanticDocument = doc.sdoc

    val tree = doc.sdoc.tree
    //println("START TypeHiarchy.calculate")

    //val test = doc.tdoc.symbols(0)

    def consumeTraitOrClass(c: Tree): Unit = {
      //if (c.name.toString() == "JoinNode" || c.name.toString() == "TestClass" || c.name.toString() == "TestParent")
      {
        try {
          val s = c.symbol
          if (!s.isLocal && !s.isNone) {
            val node = addOrReturnSymbol(s.value.toString)

            var parents = MeasureProject.getParents(semanticDB, s.value.toString)
            for (p <- parents) {
              node.linksTo += addOrReturnSymbol(p)
            }
            //println(parents)
          }
        } catch {
          case _: scala.meta.internal.classpath.MissingSymbolException =>
          // ignore
          //case ex: Throwable =>
          //  println("EX: " + ex)
        }
      }
    }

    tree.collect {
      case clazz: Defn.Class => consumeTraitOrClass(clazz)
      case clazz: Defn.Trait => consumeTraitOrClass(clazz)
      //case q: Defn.Object => q.name
    }

    //println("DONE TypeHiarchy.calculate")
  }

  def getGvString() = {
    nodesToGraphViz(symbolList)
  }

}
