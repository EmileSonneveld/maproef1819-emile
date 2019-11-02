package be.emilesonneveld

import java.io.File

import be.emilesonneveld.slickEmileProfile.Tables
import be.emilesonneveld.slickEmileProfile.Tables._

trait LargeScaleDbInterface {

  def insertBuildTry(buildPath: File, stdOutput: String, buildType: String): Unit

  def insertJavaIplasmaPyramidTry(buildPath: File, stdOutput: String): Unit

  def getJavaIplasmaPyramidTry(path: File): Seq[Tables.JavaIplasmaPyramidRow]

  def getSuccesfullProjects: Seq[Tables.SbtBuildTriesRow]

  def getBuildTry(buildPath: File): Seq[Tables.SbtBuildTriesRow]

  def getAllJavaIPlasma: Seq[Tables.JavaIplasmaPyramidRow]

  def getAllGoodJavaProjects: Seq[Tables.PyramidStatsJavaRow]

  def getPyramidStatJava(projectPath: String): Seq[Tables.PyramidStatsJavaRow]

  def hadSuccesfullBuild(buildPath: File): Boolean

  def hadSuccesfullBuild(buildPath: File, buildType: String): Boolean

  def getPyramidStatsForProj(project: String): Seq[Tables.PyramidStatsScalaRow]

  def getDistinctCommits: Seq[String]

  def getCommitRows(commitHash: String): Seq[Tables.PyramidStatsScalaRow]

  def removePyramidRow(id: Int): Unit

  def commitWorthRetaking(commitHash: String): Boolean

  def insertPyramidStats(row: Tables.PyramidStatsScalaRow): Unit

  def insertPyramidStatsJava(row: Tables.PyramidStatsJavaRow): Unit

  def updatePyramidStats(row: Tables.PyramidStatsScalaRow): Unit

  def insertRow(row: Tables.DetectedSmellRow): Unit

  def insertRow(row: Tables.DetectedSmellJavaRow): Unit

  def removeDetectedSmellsForCommit(commitHash: String): Unit

  def getDetectedSmellsForCommit(commitHash: String): Seq[Tables.DetectedSmellRow]

  def removeDetectedSmellsJavaForCommit(commitHash: String): Unit

  def getDetectedSmellsJavaForCommit(commitHash: String): Seq[Tables.DetectedSmellJavaRow]
}
