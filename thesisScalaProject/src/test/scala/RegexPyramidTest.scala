import java.io.File
import java.sql.Timestamp
import java.util.Date

import MeasureProject.{externalProperties, internalProperties}
import main.calculationsOnProjectWrap
import org.scalatest.FunSuite
import scalafix.SemanticDB
import scalafix.v1._
import slickEmileProfile.Tables

import scala.collection.mutable
import scala.io.Source
import scala.meta.{Defn, Tree}


class RegexPyramidTest extends FunSuite {

  val pyramid =
    """
      |				ANDC	0.62
      |				AHH	0.30
      |			12.91	NOP	12.0
      |		8.03	NOC		155.0
      |	5.40	NOM			1245.0	NOM	2.15
      |0.23	LOC				6735.0	2689.0	CALLS	0.68
      |CYCLO					1597.0	1838.0		FANOUT
      |"""
  test("test1") {
    println(pyramid)

    var tmp = Tables.PyramidStatsJavaRow(0, "", "", new Timestamp(new Date().getTime).toString, 0, 0, 0, 0, 0, 0, 0, 0, 0, Option(0))
    tmp = HandleJavaData.updatePyramidStatsJavaFromLog(tmp, pyramid)

    assert(tmp.andc == 0.62)
    assert(tmp.ahh == 0.30)
    assert(tmp.nop == 12.0)
    assert(tmp.noc == 155.0)
    assert(tmp.nom == 1245.0)
    assert(tmp.loc == 6735.0)
    assert(tmp.cc == 1597.0)
    assert(tmp.calls == 2689.0)
    assert(tmp.fanout == 1838.0)
  }
}
