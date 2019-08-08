/*    */ package lrg.memoria.hismo.core;
/*    */ 
/*    */ public class LocalVariableHistoryGroup
/*    */   extends AbstractHistoryGroup
/*    */ {
/*  6 */   public AbstractHistory createHistory(VersionsList versionsList) { return new LocalVariableHistory(versionsList); }
/*    */ 
/*    */ 
/*    */   
/* 10 */   public AbstractHistoryGroup createHistoryGroup() { return new LocalVariableHistoryGroup(); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\hismo\core\LocalVariableHistoryGroup.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */