/*    */ package lrg.memoria.hismo.core;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MethodHistoryGroup
/*    */   extends AbstractHistoryGroup
/*    */ {
/* 14 */   public AbstractHistory createHistory(VersionsList versionsList) { return new MethodHistory(versionsList); }
/*    */ 
/*    */ 
/*    */   
/* 18 */   public AbstractHistoryGroup createHistoryGroup() { return new MethodHistoryGroup(); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\hismo\core\MethodHistoryGroup.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */