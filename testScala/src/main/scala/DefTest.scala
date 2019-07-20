class DefTest {
  var origVar = "origValue"

  def defWrapper: String = origVar
}

object Test {
  def main(args: Array[String]): Unit = {
    val t = new DefTest()
    println("defWrapper: " + t.defWrapper)
    //t.defWrapper = "new"
    println("defWrapper: " + t.defWrapper)
  }
}
