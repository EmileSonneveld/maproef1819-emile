/*    */ package lrg.memoria.hismo.core;
/*    */ 
/*    */ public class GlobalVariableHistory extends VariableHistory {
/*    */   private NamespaceHistory namespaceHistory;
/*    */   
/*    */   public GlobalVariableHistory(NamespaceHistory nsh) {
/*  7 */     this.namespaceHistory = nsh;
/*  8 */     initializeInnerHistories();
/*    */   }
/*    */ 
/*    */   
/* 12 */   public GlobalVariableHistory(VersionsList versions) { super(versions); }
/*    */ 
/*    */ 
/*    */   
/* 16 */   public NamespaceHistory getNamespaceHistory() { return this.namespaceHistory; }
/*    */   
/*    */   protected void initializeInnerHistories() {}
/*    */   
/*    */   protected void updateInnerHistories(AbstractVersion version) {}
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\hismo\core\GlobalVariableHistory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */