
class CallsMetric {
  println("print should be counted")

  def getNumber: Int = 108

  var numberToBeIgnored = 666

  def theImportantDef(): String = {
    print("yep")
    print("only count print once per function")
    getNumber // Should be counted as function call too
    numberToBeIgnored

    var ignoreMe = "ignoreMe"
    ignoreMe
  }
}
