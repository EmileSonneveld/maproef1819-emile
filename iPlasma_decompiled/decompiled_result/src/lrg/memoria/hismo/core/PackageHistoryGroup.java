/*    */ package lrg.memoria.hismo.core;
/*    */ 
/*    */ 
/*    */ public class PackageHistoryGroup
/*    */   extends AbstractHistoryGroup
/*    */ {
/*  7 */   public AbstractHistory createHistory(VersionsList versionsList) { return new PackageHistory(versionsList); }
/*    */ 
/*    */ 
/*    */   
/* 11 */   public AbstractHistoryGroup createHistoryGroup() { return new PackageHistoryGroup(); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\hismo\core\PackageHistoryGroup.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */