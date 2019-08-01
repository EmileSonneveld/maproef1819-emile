/*    */ package lrg.memoria.hismo.core;
/*    */ 
/*    */ public class GlobalFunctionHistory
/*    */   extends FunctionHistory
/*    */ {
/*    */   private NamespaceHistory namespaceHistory;
/*    */   
/*  8 */   public GlobalFunctionHistory(NamespaceHistory nsh) { this.namespaceHistory = nsh; }
/*    */ 
/*    */ 
/*    */   
/* 12 */   public NamespaceHistory getNamespaceHistory() { return this.namespaceHistory; }
/*    */ 
/*    */ 
/*    */   
/* 16 */   public GlobalFunctionHistory(VersionsList versions) { super(versions); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\hismo\core\GlobalFunctionHistory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */