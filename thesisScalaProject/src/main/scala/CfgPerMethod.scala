import java.text.{DecimalFormat, DecimalFormatSymbols}
import java.util.Locale

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.meta._


object CfgPerMethod {

  class DirectedGraphNode(val debugString: String, val nodeId: String, val depth: Int) {
    val linksTo: ArrayBuffer[DirectedGraphNode] = ArrayBuffer.empty[DirectedGraphNode]
  }

  def calculateCC(nodes: ArrayBuffer[DirectedGraphNode]): Int = {
    var E = 0
    for (node <- nodes) {
      E += node.linksTo.length
    }
    val V = nodes.length
    val P = 1 // Number of connected components
    E - V + 2 * P
  }

  def MethodMapToGraphViz(methodMap: mutable.Map[String, ArrayBuffer[DirectedGraphNode]]): String = {

    var sb = new StringBuilder()
    sb ++= "// You can visualise this file here: http://webgraphviz.com\n"
    sb ++= "digraph {\n"
    sb ++= "	node [shape = rectangle, style=filled, color=\"0.650 0.200 1.000\"];\n"

    for (nodes <- methodMap.values) {
      sb ++= "\n"
      sb ++= methodToGraphviz(nodes)
    }

    sb ++= "}\n"
    sb.toString()
  }

  def nodesToGraphViz(nodes: ArrayBuffer[DirectedGraphNode]): String = {
    var sb = new StringBuilder()
    sb ++= "// You can visualise this file here: http://webgraphviz.com\n"
    sb ++= "digraph {\n"
    sb ++= "	node [shape = rectangle, style=filled, color=\"0.650 0.200 1.000\"];\n"

    sb ++= methodToGraphviz(nodes)

    sb ++= "}\n"
    sb.toString()
  }

  def depthToColor(depth: Int): String = {
    val percent = 1 - Math.min(1, depth.toDouble / 10)

    val otherSymbols = new DecimalFormatSymbols(Locale.getDefault())
    otherSymbols.setDecimalSeparator('.')
    val df = new DecimalFormat("0.###", otherSymbols)
    "0.650 0.200 " + df.format(0.25 + percent * 0.75)
  }

  private def methodToGraphviz(nodes: ArrayBuffer[DirectedGraphNode]): String = {
    var sb = new StringBuilder()
    for (node <- nodes) {
      val n1 = Utils.escapeGraphVizName(node.debugString)
      sb ++= "	\"" + node.nodeId + "\" [label=\"" + n1 + "\" color=\"" + depthToColor(node.depth) + "\" ]\n"
      for (next <- node.linksTo) {
        sb ++= "	\"" + node.nodeId + "\"->\"" + next.nodeId + "\"\n"
      }
    }
    sb.toString()
  }

  def numberOfLazyBinaryOperators(tree: Tree) = {
    var count = 0
    tree.collect {
      case t@Term.ApplyInfix(_, name, types, terms) => {
        if (name.value == "&&" || name.value == "||")
          count += 1
      }
    }
    count
  }

  def compute(tree: Tree): mutable.Map[String, ArrayBuffer[DirectedGraphNode]] = {
    //println("Generating CFGs...")

    val methodMap = mutable.Map.empty[String, ArrayBuffer[DirectedGraphNode]]

    var nodeNr = 1
    var depth = 0

    def newNode(nodes: ArrayBuffer[DirectedGraphNode], debugString: String) = {
      val tmp = new DirectedGraphNode(debugString, "n_" + nodeNr, depth)
      nodes += tmp
      nodeNr += 1
      tmp
    }

    // returns last created node
    def doBlock(nodes: ArrayBuffer[DirectedGraphNode], begin: DirectedGraphNode, returnPointsToThis: DirectedGraphNode, statements: scala.List[scala.meta.Stat]): DirectedGraphNode = {
      var lastNode = begin
      depth += 1

      def clickNewNode(debugString: String) = {
        var n = newNode(nodes, debugString)
        lastNode.linksTo += n
        lastNode = n
        n
      }

      for (stm <- statements) {
        stm match {
          case t@Term.Block(sts) => {
            //val after = newNode("AFTER BLOCK")
            lastNode = doBlock(nodes, lastNode, returnPointsToThis, sts)
          }
          case t@Term.ApplyInfix(_, name, types, terms) => {
            val tmp = clickNewNode(t.toString())
            var count = numberOfLazyBinaryOperators(t)
            if (count > 0) {
              clickNewNode("AFTER LAZY BINARY OPERATORS")
              for (_ <- 0 until count) {
                tmp.linksTo += lastNode
              }
            }
          }
          case t@Term.Apply(term, args) => {
            clickNewNode(t.fun.toString()) // TODO: Expand
          }
          case t: scala.meta.Lit.Unit => {
            // Do nothing here
          }
          case t: Term.Name => {
            clickNewNode("TERMNAME" + t.toString())
            //        lastNode.linksTo += n
          }
          case t: Term.Assign => {
            clickNewNode(t.toString())
          }
          case t: scala.meta.Lit => {
            clickNewNode(t.toString())
          }
          case t: Term.Throw => {
            clickNewNode(t.toString())
          }
          case t@Term.PartialFunction(cases) => {
            clickNewNode(t.toString()) // TODO: Exapnd
          }
          case Term.If(cond, cons, alt) => {
            //clickNewNode("IF " + cond.toString()) // Todo: Expand
            clickNewNode("IF")
            val afterConditional = newNode(nodes, "IF-BRANCHING-POINT")
            doBlock(nodes, lastNode, returnPointsToThis, scala.List[scala.meta.Stat](cond)).linksTo += afterConditional
            lastNode = afterConditional

            val after = newNode(nodes, "AFTER IF")
            doBlock(nodes, lastNode, returnPointsToThis, scala.List[scala.meta.Stat](cons)).linksTo += after
            doBlock(nodes, lastNode, returnPointsToThis, scala.List[scala.meta.Stat](alt)).linksTo += after
            lastNode = after
          }
          case Term.Match(term, cases) => {
            clickNewNode("MATCH " + term.toString()) // TODO: Exapnd
            val after = newNode(nodes, "AFTER MATCH")
            for (c <- cases) {
              doBlock(nodes, lastNode, returnPointsToThis, scala.List[scala.meta.Stat](c.body)).linksTo += after
            }
            lastNode = after
          }
          case Term.For(enum, term) => {
            clickNewNode("FOR " + enum.toString()) // TODO: Exapnd
            val prevLast = lastNode
            lastNode = doBlock(nodes, lastNode, returnPointsToThis, scala.List[scala.meta.Stat](term))
            lastNode.linksTo += prevLast
          }
          case t@Term.Try(term, cases, terms) => {
            clickNewNode("BEGIN TRY")

            val after = newNode(nodes, "AFTER TRY")
            doBlock(nodes, lastNode, returnPointsToThis, List(term)).linksTo += after
            for (c <- cases) {
              doBlock(nodes, lastNode, returnPointsToThis, scala.List[scala.meta.Stat](c.body)).linksTo += after
            }
            lastNode = after
          }

          case Term.While(term1, term2) => {
            lastNode = doBlock(nodes, lastNode, returnPointsToThis, List(term1))
            val beginNode = lastNode
            lastNode = doBlock(nodes, lastNode, returnPointsToThis, List(term2))
            lastNode.linksTo += beginNode
          }

          //case Term.NewAnonymous(template) => {
          //  clickNewNode(template.toString()) // Todo
          //}
          //case Term.Select(term, name) => {
          //  clickNewNode(term.toString()) // Todo
          //}
          case defnVar@Defn.Var(_, _, _, Some(rhs)) => {
            clickNewNode(defnVar.toString())
          }

          case defnVar@Defn.Val(_, _, _, rhs) => {
            clickNewNode(defnVar.toString())
          }

          case Term.Return(term) => {
            clickNewNode("RETURN " + term.toString()) // todo: Expand
            lastNode.linksTo += returnPointsToThis
            return new DirectedGraphNode("BOGUS NODE", "BOGUS NODE ID", depth)
          }
          case d: Defn.Def => {
            // Ignore, as all 'def's are already passed by the tree.collect
            //doMethod(d)
          }
          case anyOtherStatement => {
            //println("nothing for this statement") // + anyOtherStatement.toString())
            clickNewNode("UNSUPPORTED: " + anyOtherStatement.toString()) // Showing something in the graph
          }
        }
      }
      //lastNode.linksTo += end
      depth -= 1
      lastNode
    }

    def doMethod(d: Defn.Def) = {
      depth = 0 // Otherwise, there is an obscure bug.
      val methodName = d.name.toString()

      if (methodMap.keySet.contains(methodName)) // Dont know why this happens
        println("Method name: " + methodName + " was already considered.")
      methodMap(methodName) = ArrayBuffer.empty[DirectedGraphNode]
      val nodes = methodMap(methodName)


      val root = newNode(nodes, "DEF " + methodName)
      val end = newNode(nodes, "END DEF " + methodName)
      d.body match {
        case Term.Block(statements) => {
          val l = doBlock(nodes, root, end, statements)
          l.linksTo += end
        }
        case x =>
          val l = doBlock(nodes, root, end, scala.List[scala.meta.Stat](x))
          l.linksTo += end
      }
    }

    tree match {
      case d@Defn.Def(_, name, _, _, _, body) =>
        doMethod(d)
      case _ =>
        tree.collect {
          case d@Defn.Def(_, name, _, _, _, body) =>
            doMethod(d)
        }
    }

    //println("\n\n" + nodesToGraphViz(methodMap))
    methodMap
  }
}
