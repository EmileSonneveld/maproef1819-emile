package be.emilesonneveld

import java.sql.Timestamp
import java.util.Date

import be.emilesonneveld.slickEmileProfile.Tables
import org.scalatest.FunSuite


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

    var tmp = Tables.PyramidStatsJavaRow(0, "", "", new Timestamp(new Date().getTime).toString, 0, 0, 0, 0, 0, 0, 0, 0, 0, Option(0), Option(0))
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

  val pmd_output =
    """Aug 24, 2019 6:25:30 PM net.sourceforge.pmd.PMD processFiles
      |WARNING: This analysis could be faster, please consider using Incremental Analysis: https://pmd.github.io/pmd-6.16.0/pmd_userdocs_incremental_analysis.html
      |C:\Users\emill\dev\HotDraw\JHotDraw [Java]\src\org\jhotdraw\application\DrawApplication.java:104:   Possible God Class (WMC=130, ATFD=105, TCC=0.631%)
      |C:\Users\emill\dev\HotDraw\JHotDraw [Java]\src\org\jhotdraw\contrib\PolygonFigure.java:42:      Possible God Class (WMC=67, ATFD=64, TCC=1.136%)
      |C:\Users\emill\dev\HotDraw\JHotDraw [Java]\src\org\jhotdraw\figures\LineConnection.java:30:     Possible God Class (WMC=59, ATFD=13, TCC=0.300%)
      |C:\Users\emill\dev\HotDraw\JHotDraw [Java]\src\org\jhotdraw\figures\PolyLineFigure.java:42:     Possible God Class (WMC=65, ATFD=32, TCC=20.856%)
      |C:\Users\emill\dev\HotDraw\JHotDraw [Java]\src\org\jhotdraw\figures\TextFigure.java:49: Possible God Class (WMC=66, ATFD=48, TCC=3.737%)
      |C:\Users\emill\dev\HotDraw\JHotDraw [Java]\src\org\jhotdraw\standard\AbstractFigure.java:61:    Possible God Class (WMC=52, ATFD=11, TCC=0.854%)
      |C:\Users\emill\dev\HotDraw\JHotDraw [Java]\src\org\jhotdraw\standard\CompositeFigure.java:38:   Possible God Class (WMC=111, ATFD=24, TCC=13.506%)
      |C:\Users\emill\dev\HotDraw\JHotDraw [Java]\src\org\jhotdraw\standard\ConnectionTool.java:39:    Possible God Class (WMC=59, ATFD=13, TCC=2.154%)
      |C:\Users\emill\dev\HotDraw\JHotDraw [Java]\src\org\jhotdraw\standard\StandardDrawingView.java:72:   Possible God Class (WMC=158, ATFD=38, TCC=1.750%)
      |C:\Users\emill\dev\HotDraw\JHotDraw [Java]\src\org\jhotdraw\\util\Geom.java:23:  Possible God Class (WMC=53, ATFD=57, TCC=0.000%)"""
  test("test2") {
    println(pmd_output)
    val detectedList = HandleJavaData.parsePmdOutput(pmd_output)
    assert(detectedList.length == 10)
    assert(detectedList(0).wmc == 130.0)
    assert(detectedList(0).atfd == 105.0)
    assert(detectedList(8).tcc == 1.750)
  }

}
