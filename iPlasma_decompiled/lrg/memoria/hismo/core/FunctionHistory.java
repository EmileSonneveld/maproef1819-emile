/*    */ package lrg.memoria.hismo.core;
/*    */ 
/*    */ import lrg.memoria.core.Function;
/*    */ import lrg.memoria.core.FunctionBody;
/*    */ import lrg.memoria.core.LocalVariable;
/*    */ import lrg.memoria.core.ModelElementList;
/*    */ 
/*    */ 
/*    */ public abstract class FunctionHistory
/*    */   extends AbstractHistory
/*    */ {
/*    */   LocalVariableHistoryGroup localVariableHistories;
/*    */   
/* 14 */   public FunctionHistory() { initializeInnerHistories(); }
/*    */ 
/*    */ 
/*    */   
/* 18 */   public FunctionHistory(VersionsList versions) { super(versions); }
/*    */ 
/*    */ 
/*    */   
/* 22 */   public LocalVariableHistoryGroup getLocalVariableHistories() { return this.localVariableHistories; }
/*    */ 
/*    */ 
/*    */   
/* 26 */   protected void initializeInnerHistories() { this.localVariableHistories = new LocalVariableHistoryGroup(); }
/*    */ 
/*    */   
/*    */   protected void updateInnerHistories(AbstractVersion version) {
/* 30 */     FunctionBody functionBody = ((Function)version).getBody();
/* 31 */     ModelElementList modelElementList = functionBody.getLocalVarList();
/*    */ 
/*    */ 
/*    */     
/* 35 */     for (LocalVariable lv : modelElementList) {
/* 36 */       LocalVariableVersion currentLocalVariableVersion = new LocalVariableVersion(version.versionName(), lv);
/* 37 */       String currentLocalVariableName = lv.getFullName();
/* 38 */       AbstractHistory currentHistory = this.localVariableHistories.get(currentLocalVariableName);
/* 39 */       if (currentHistory == null) {
/* 40 */         currentHistory = new LocalVariableHistory(this);
/* 41 */         currentHistory.addVersion(currentLocalVariableVersion);
/* 42 */         this.localVariableHistories.put(currentHistory); continue;
/*    */       } 
/* 44 */       currentHistory.addVersion(currentLocalVariableVersion);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\hismo\core\FunctionHistory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */