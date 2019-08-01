/*    */ package lrg.memoria.hismo.core;
/*    */ 
/*    */ 
/*    */ public class MethodHistory
/*    */   extends FunctionHistory
/*    */ {
/*    */   private ClassHistory classHistory;
/*    */   
/*  9 */   public MethodHistory(ClassHistory ch) { this.classHistory = ch; }
/*    */ 
/*    */ 
/*    */   
/* 13 */   public MethodHistory(VersionsList versions) { super(versions); }
/*    */ 
/*    */ 
/*    */   
/* 17 */   public ClassHistory getClassHistory() { return this.classHistory; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\hismo\core\MethodHistory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */