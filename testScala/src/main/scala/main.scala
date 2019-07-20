

object main extends App {
  println(new E())
  println("main object")
}


/*
      A    B
     /  \ / \
    C    D   |
          \ /
           E
 */
trait A {
  val propA = "A"
}

trait B {}

trait C extends A {

  private val external = new ObjectType()
  println(propA + external.property_ObjectType)
}

class D extends A with B {
  private val propD = "D"

  def methodInD: String = {
    val external = new ObjectType()
    val totallyLocal = "local"
    propD + propA + totallyLocal + external.property_ObjectType
  }
}

class E extends D with B {}

//class TestClass() extends TestParent {
//}


class ClassType {
  var property_ClassType: String = "Hello"
  var javaString: java.lang.String = "java string here"
  var file: java.nio.file.Files = _

  def method1(): Unit = {
    property_ClassType = "settted by method1!"
    var localVar = "a"
    localVar = "b"
    println("javaString: " + javaString)
    println("property_ClassType: " + property_ClassType)
  }

  def method2(): Unit = {
    javaString = "settted by method2!"
    method1()
  }
}


class ObjectType {
  var property_ObjectType: String = "Hello"
}


//case class CaseClassType(property_CaseClassType: String)


object CoverPosition {
  def empty = CoverPosition(-1, -1, -1, -1)
}

case class CoverPosition(startLine: Int, startColumn: Int, endLine: Int, endColumn: Int) {

  // CC of 4 according to scalastyle
  def isIn(other: CoverPosition): Boolean = {
    other.startLine >= startLine && other.endLine <= endLine && other.startColumn >= startColumn && other.endColumn <= endColumn
  }

  override def toString: String = s"CoverPosition($startLine, $startColumn, $endLine, $endColumn)"
}


abstract class CoverageEvent {
  val uri: String
  val component: String
}


case class CoverStatement(uri: String, component: String, position: CoverPosition) extends CoverageEvent

case class CoverBranch(uri: String, component: String, ifposition: CoverPosition, position: CoverPosition, bd: Double) extends CoverageEvent

case class CoverMessage(uri: String, component: String, position: CoverPosition) extends CoverageEvent

case class CoverMethod(uri: String, component: String, method: String) extends CoverageEvent


