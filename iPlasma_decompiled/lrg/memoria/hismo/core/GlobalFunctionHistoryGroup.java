/*    */ package lrg.memoria.hismo.core;
/*    */ 
/*    */ public class GlobalFunctionHistoryGroup
/*    */   extends AbstractHistoryGroup
/*    */ {
/*  6 */   public AbstractHistory createHistory(VersionsList versionsList) { return new GlobalFunctionHistory(versionsList); }
/*    */ 
/*    */ 
/*    */   
/* 10 */   public AbstractHistoryGroup createHistoryGroup() { return new GlobalFunctionHistoryGroup(); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\hismo\core\GlobalFunctionHistoryGroup.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */