/*    */ package lrg.memoria.hismo.core;
/*    */ 
/*    */ public class LocalVariableHistory extends VariableHistory {
/*    */   private FunctionHistory functionHistory;
/*    */   
/*    */   public LocalVariableHistory(FunctionHistory fh) {
/*  7 */     this.functionHistory = fh;
/*  8 */     initializeInnerHistories();
/*    */   }
/*    */ 
/*    */   
/* 12 */   public LocalVariableHistory(VersionsList versions) { super(versions); }
/*    */   
/*    */   protected void initializeInnerHistories() {}
/*    */   
/*    */   protected void updateInnerHistories(AbstractVersion version) {}
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\hismo\core\LocalVariableHistory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */