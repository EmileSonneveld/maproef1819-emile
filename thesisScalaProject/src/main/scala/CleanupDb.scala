object CleanupDb {

  def main(args: Array[String]): Unit = {
    val commits = LargeScaleDb.getDistinctCommits
    for (commitHash <- commits) {
      var rows = LargeScaleDb.getCommitRows(commitHash)
      val goodRow = rows.find(x => x.fanout != Option.empty && x.ahh != Option.empty)
      if (goodRow.isDefined) {
        for (row <- rows) {
          if (row.id != goodRow.get.id)
            LargeScaleDb.removePyramidRow(row.id)
        }
      }
    }
  }
}
