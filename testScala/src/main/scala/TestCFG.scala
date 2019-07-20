class TestCFG {

  def def_method: String = {
    var i = 1
    i += 1

    if (i < 2) {
      "inside if"
    }
    i += 1
    "last statement"
  }

  // CC of 3 according to emile and scalastyle
  def def_method_nested_if: Int = {
    val i = 1

    if (i < 2) {
      1
    } else {
      4

      if (i > 3) {
        2
      } else {
        3
      }
    }
  }

  // CC of 5 according to scalastyle
  // CC of 4 according to emile
  def def_method_match: String = {
    val i = 1

    i match {
      case 1 => "One"
      case 2 => {
        if (true) {
          "Two"
        }
        "Twooooo"
      }
      case 3 => "Tree"
    }
  }

  def def_method_return: Int = {
    val i = 1

    if (i < 2) {
      1
    } else {
      return 5
    }
    7
  }

  //noinspection SimplifyBoolean
  def lazy_binary_ops: Boolean = {
    false || false || true
  }

  def special_if: String = {
    if ( {
      if (false) {
        true
      } else {
        false
      }
    }) {
      "in strange if"
    }
    "default"
  }

  // CC of 3 according to scalastyle
  def def_method_for: String = {
    for (i <- List(4, 5, 6)) {
      println(i)
      if (i == 5)
        return "List contains 5"
    }
    "Default return"
  }

  def def_method_partial: PartialFunction[Double, Double] = {
    case d: Double if d > 0 => Math.sqrt(d)
    case d: Double if d < 0 => Math.sqrt(-d) // Random functionality
  }

  // This method has a cyclomatic complexity of 12 accoring to PMD and scalastyls
  // Only 7 according to emile
  def example(): Unit = {
    var x = 0
    val y = 1
    val z = 2
    val t = 2
    val a = false
    val b = true
    val c = false
    val d = true
    if (a && b || b && d) {
      if (y == z) x = 2
      else if (y == t && !d) x = 2
      else x = 2
    } else if (c && d) while ( {
      z < y
    }) x = 2
    else {
      var n = 0
      while ( {
        n < t
      }) {
        x = 2

        {
          n += 1;
          n - 1
        }
      }
    }
  }

  val val_method: String = {
    "return string"
  }
  var var_method: String = {
    "return string"
  }

  def def_datamember = 9

  val val_datamember = 9
  var var_datamember = 9

}
