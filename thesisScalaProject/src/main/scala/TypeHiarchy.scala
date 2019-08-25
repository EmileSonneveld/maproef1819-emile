import java.text.{DecimalFormat, DecimalFormatSymbols}
import java.util.Locale

import scalafix.{DocumentTuple, SemanticDB}
import scalafix.v1._

import scala.collection.immutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import scala.meta.{Defn, _}
//import scala.meta.internal.semanticdb
import scala.meta.internal.symtab.GlobalSymbolTable

class TypeHiarchy(semanticDB: SemanticDB) {

  //val symbolTable: GlobalSymbolTable = semanticDB.symbolTable

  class TypeGraphNode(val symbol: String) {
    def name: String = symbol.toString

    def addParent(parent: TypeGraphNode) = {
      parents += parent
      parent.children += this
    }

    val parents: ArrayBuffer[TypeGraphNode] = ArrayBuffer.empty[TypeGraphNode]
    val children: ArrayBuffer[TypeGraphNode] = ArrayBuffer.empty[TypeGraphNode]

    def numberOfChildren = children.length

    override def toString: String = name
  }

  private val symbolList = ArrayBuffer.empty[TypeGraphNode]

  //def GetNodeByPath(path: String) = {
  //  symbolList.find(_.name == path).get
  //}

  def calculateANDC(): Double = {
    var sum = 0.0
    var count = 0.0
    for (n <- symbolList.filter(x => ConsiderClassInHierarchy(x.name))) {
      sum += n.children.count(x => ConsiderClassInHierarchy(x.name))
      count += 1
    }
    return sum / count
  }

  def ConsiderClassInHierarchy(name: String): Boolean = {
    MeasureProject.doWeOwnThisClass(name) && !name.startsWith("test")
  }

  def calculateAHH(): Double = {
    if (symbolList.length == 0)
      return 0


    var clusterStarers = new ListBuffer[TypeGraphNode]

    // Get only one cluster starter per cluster
    {
      var currentClusterId = -1
      var nodeToClusterId = scala.collection.mutable.Map[TypeGraphNode, Int]()
      symbolList.foreach(nodeToClusterId += _ -> -666)

      def infectClusterWithId(currentNode: TypeGraphNode, id: Int): Unit = {
        if (!ConsiderClassInHierarchy(currentNode.name)) return
        if (nodeToClusterId(currentNode) >= 0) {
          assert(nodeToClusterId(currentNode) == id)
          return // Already infected
        }
        nodeToClusterId(currentNode) = id

        for (otherNode <- currentNode.children) {
          infectClusterWithId(otherNode, id)
        }
        for (otherNode <- currentNode.parents) {
          infectClusterWithId(otherNode, id)
        }
      }

      for (node <- symbolList) {
        var nodeClusterId = nodeToClusterId(node)
        if (nodeClusterId < 0 && ConsiderClassInHierarchy(node.name)) {
          currentClusterId += 1
          //println("New cluster starter: " + node.name)
          infectClusterWithId(node, currentClusterId)
          clusterStarers += node
        }
      }
    }


    var depthList = new ArrayBuffer[Int]()
    for (clusterStarter <- clusterStarers) {
      var minOffset = 0
      var maxOffset = 0

      var visitedList = scala.collection.mutable.Map[TypeGraphNode, Boolean]()
      symbolList.foreach(visitedList += _ -> false)

      var tabDepth = 0

      def searchExtremeOffsets(currentNode: TypeGraphNode, currentOffset: Int): Unit = {
        if (!visitedList(currentNode) && ConsiderClassInHierarchy(currentNode.name)) {
          visitedList(currentNode) = true
          //println("  " * tabDepth + currentNode.name)

          if (currentOffset < minOffset) minOffset = currentOffset
          if (currentOffset > maxOffset) maxOffset = currentOffset

          for (childNode <- currentNode.children) {
            tabDepth += 1
            searchExtremeOffsets(childNode, currentOffset + 1)
            tabDepth -= 1
          }
          for (childNode <- currentNode.parents) {
            tabDepth += 1
            searchExtremeOffsets(childNode, currentOffset - 1)
            tabDepth -= 1
          }
        }
      }

      searchExtremeOffsets(clusterStarter, 0)
      val clusterHeight = maxOffset - minOffset
      depthList += clusterHeight
    }

    /*
      var rootNodes = symbolList.filter(node =>
        ConsiderClassForAHH(node.name)
          && !node.parents.exists(parentNode => ConsiderClassForAHH(parentNode.name))
      )
      var depthList = List.fill(rootNodes.length)(-666).toArray


      for (rootNodeIndex <- rootNodes.indices) {
        var deepestDepth = -1 // no need to know what the deepest element is, only the depth

        def rec(currentNode: TypeGraphNode, depth: Int): Unit = {
          if (depth > deepestDepth)
            deepestDepth = depth
          for (childNode <- currentNode.children) {
            rec(childNode, depth + 1)
          }
        }

        rec(rootNodes(rootNodeIndex), 0)
        depthList(rootNodeIndex) = deepestDepth
      }
    */
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
    sb ++= "	node [shape = rectangle, style=filled, color=\"0.650 0.200 1.000\", fontname = \"arial\"];\n"

    for (node <- nodes) {
      if (node.name != "scala/AnyRef#" && MeasureProject.doWeOwnThisClass(node.name)) {
        // node.nodeId + " " +
        val n1 = Utils.escapeGraphVizName(node.name)

        //if (node.linksTo.length == 0)
        var nam = getPackageName(node.name)
        sb ++= "	\"" + n1 + "\" [color=\"" + getHlsColorForString(nam) + "\" ]\n"

        for (next <- node.parents) {
          if (next.name != "scala/AnyRef#" && MeasureProject.doWeOwnThisClass(next.name)) {
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
              node.addParent(addOrReturnSymbol(p))
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
