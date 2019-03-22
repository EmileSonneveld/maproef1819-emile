package scalafix

import scalafix.v1._

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, Map}
import scala.meta.{Defn, Term, _}


object CfgPerMethod {

  class DirectedGraphNode(val debugString: String, val nodeId: String) {
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

  def nodesToGraphViz(methodMap: Map[String, ArrayBuffer[DirectedGraphNode]]): String = {
    var sb = new StringBuilder()
    sb ++= "# You can visualise this file here: http://webgraphviz.com\n"
    sb ++= "digraph {\n"
    sb ++= "	node [shape = rectangle, style=filled, color=\"0.650 0.200 1.000\"];\n"

    for (nodes <- methodMap.values) {
      for (node <- nodes) {
        // node.nodeId + " " +
        val n1 = node.debugString
          .replace("\\", "\\\\") // escape escaper
          .replace("\"", "\\\"")
          .replace("\n", "\\n")
          .replace("\r", "")
        //if (node.linksTo.length == 0)
        sb ++= "	\"" + node.nodeId + "\" [label=\"" + n1 + "\"]\n"
        for (next <- node.linksTo) {
          //val n2 = next.debugString.replace("\"", "\\\"")
          sb ++= "	\"" + node.nodeId + "\"->\"" + next.nodeId + "\"\n"
        }
      }
    }
    sb ++= "}\n"
    sb.toString()
  }

  def compute(tree:Tree): mutable.Map[String, ArrayBuffer[DirectedGraphNode]] = {
    println("Generating CFGs...")

    //val sdoc: SemanticDocument = doc.sdoc
    val methodMap = mutable.Map.empty[String, ArrayBuffer[DirectedGraphNode]]

    var nodeNr = 1

    def newNode(nodes: ArrayBuffer[DirectedGraphNode], debugString: String) = {
      val tmp = new DirectedGraphNode(debugString, "n_" + nodeNr)
      nodes += tmp
      nodeNr += 1
      tmp
    }


    // returns last created node
    def doBlock(nodes: ArrayBuffer[DirectedGraphNode], begin: DirectedGraphNode, returnPointsToThis: DirectedGraphNode, statements: scala.List[scala.meta.Stat]): DirectedGraphNode = {
      var lastNode = begin

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
            clickNewNode(t.toString())
          }
          case t@Term.Apply(term, args) => {
            clickNewNode(t.fun.toString()) // TODO: Expand
          }
          case t: scala.meta.Lit.Unit => {
            // Do nothing here
          }
          case t: Term.Name => {
            clickNewNode(t.toString())
          }
          case t: Term.Assign => {
            clickNewNode(t.toString())
          }
          case t: scala.meta.Lit => {
            clickNewNode(t.toString())
          }
          case t@Term.PartialFunction(cases) => {
            clickNewNode(t.toString()) // TODO: Exapnd
          }
          case Term.If(cond, cons, alt) => {
            clickNewNode("IF " + cond.toString()) // Todo: Expand
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

          case defnVar@Defn.Var(_, _, _, Some(rhs)) =>
          case defnVar@Defn.Val(_, _, _, rhs) => {
            clickNewNode(defnVar.toString())
          }

          case Term.Return(term) => {
            clickNewNode("RETURN " + term.toString()) // todo: Expand
            lastNode.linksTo += returnPointsToThis
            return new DirectedGraphNode("BOGUS NODE", "BOGUS NODE ID")
          }
          case d: Defn.Def => {
            // Ignore, as all 'def's are already passed by the tree.collect
            //doMethod(d)
          }
          case anyOtherStatement => {
            println("nothing for this statement: " + anyOtherStatement.toString())
          }
        }
      }
      //lastNode.linksTo += end
      lastNode
    }

    def doMethod(d: Defn.Def) = {
      val methodName = d.name.toString()

      if (methodMap.keySet.contains(methodName)) // Dont lnow why this happens
        println("Method name: " + methodName + " was already considered. Will clear result and calculate again.")
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

    tree.collect {
      case d@Defn.Def(_, name, _, _, _, body) =>
        doMethod(d)
    }
    //println("\n\n" + nodesToGraphViz())
    methodMap
  }
}
