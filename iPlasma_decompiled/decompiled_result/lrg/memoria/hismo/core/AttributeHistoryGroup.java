/*    */ package lrg.memoria.hismo.core;
/*    */ 
/*    */ 
/*    */ public class AttributeHistoryGroup
/*    */   extends AbstractHistoryGroup
/*    */ {
/*  7 */   public AbstractHistory createHistory(VersionsList versionsList) { return new AttributeHistory(versionsList); }
/*    */ 
/*    */ 
/*    */   
/* 11 */   public AbstractHistoryGroup createHistoryGroup() { return new AttributeHistoryGroup(); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\hismo\core\AttributeHistoryGroup.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */