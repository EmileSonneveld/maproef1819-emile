/*    */ package lrg.memoria.hismo.core;
/*    */ 
/*    */ import lrg.memoria.core.Class;
/*    */ import lrg.memoria.core.GlobalFunction;
/*    */ import lrg.memoria.core.GlobalVariable;
/*    */ import lrg.memoria.core.ModelElementList;
/*    */ import lrg.memoria.core.Type;
/*    */ 
/*    */ public class NamespaceHistory
/*    */   extends AbstractHistory {
/*    */   ClassHistoryGroup classHistories;
/*    */   
/* 13 */   public NamespaceHistory() { initializeInnerHistories(); }
/*    */   GlobalFunctionHistoryGroup globalFunctionHistories;
/*    */   GlobalVariableHistoryGroup globalVariableHistories;
/*    */   
/* 17 */   public NamespaceHistory(VersionsList versions) { super(versions); }
/*    */ 
/*    */   
/*    */   protected void initializeInnerHistories() {
/* 21 */     this.classHistories = new ClassHistoryGroup();
/* 22 */     this.globalFunctionHistories = new GlobalFunctionHistoryGroup();
/* 23 */     this.globalVariableHistories = new GlobalVariableHistoryGroup();
/*    */   }
/*    */ 
/*    */   
/* 27 */   public ClassHistoryGroup getClassHistories() { return this.classHistories; }
/*    */ 
/*    */ 
/*    */   
/* 31 */   public GlobalFunctionHistoryGroup getGlobalFunctionHistories() { return this.globalFunctionHistories; }
/*    */ 
/*    */ 
/*    */   
/* 35 */   public GlobalVariableHistoryGroup getGlobalVariableHistories() { return this.globalVariableHistories; }
/*    */ 
/*    */   
/*    */   protected void updateInnerHistories(AbstractVersion version) {
/* 39 */     updateTypesHistories((NamespaceVersion)version);
/* 40 */     updateGlobalFunctionHistories((NamespaceVersion)version);
/* 41 */     updateGlobalVariableHistories((NamespaceVersion)version);
/*    */   }
/*    */   
/*    */   private void updateGlobalFunctionHistories(NamespaceVersion version) {
/* 45 */     ModelElementList<GlobalFunction> currentGlobalFunctions = version.getGlobalFunctionsList();
/*    */ 
/*    */ 
/*    */     
/* 49 */     for (GlobalFunction currentGlobalFunction : currentGlobalFunctions) {
/* 50 */       GlobalFunctionVersion currentGlobalFunctionVersion = new GlobalFunctionVersion(version.versionName(), currentGlobalFunction);
/* 51 */       String currentGlobalFunctionName = currentGlobalFunction.getFullName();
/* 52 */       AbstractHistory currentHistory = this.globalFunctionHistories.get(currentGlobalFunctionName);
/* 53 */       if (currentHistory == null) {
/* 54 */         currentHistory = new GlobalFunctionHistory(this);
/* 55 */         currentHistory.addVersion(currentGlobalFunctionVersion);
/* 56 */         this.globalFunctionHistories.put(currentHistory); continue;
/*    */       } 
/* 58 */       currentHistory.addVersion(currentGlobalFunctionVersion);
/*    */     } 
/*    */   }
/*    */   
/*    */   private void updateGlobalVariableHistories(NamespaceVersion version) {
/* 63 */     ModelElementList<GlobalVariable> currentGlobalVariables = version.getGlobalVariablesList();
/*    */ 
/*    */ 
/*    */     
/* 67 */     for (GlobalVariable currentGlobalVariable : currentGlobalVariables) {
/* 68 */       GlobalVariableVersion currentGlobalVariableVersion = new GlobalVariableVersion(version.versionName(), currentGlobalVariable);
/* 69 */       String currentGlobalVariableName = currentGlobalVariable.getFullName();
/* 70 */       AbstractHistory currentHistory = this.globalVariableHistories.get(currentGlobalVariableName);
/* 71 */       if (currentHistory == null) {
/* 72 */         currentHistory = new GlobalVariableHistory(this);
/* 73 */         currentHistory.addVersion(currentGlobalVariableVersion);
/* 74 */         this.globalVariableHistories.put(currentHistory); continue;
/*    */       } 
/* 76 */       currentHistory.addVersion(currentGlobalVariableVersion);
/*    */     } 
/*    */   }
/*    */   
/*    */   private void updateTypesHistories(NamespaceVersion version) {
/* 81 */     ModelElementList<Type> currentTypes = version.getAllTypesList();
/*    */ 
/*    */ 
/*    */     
/* 85 */     for (Type currentType : currentTypes) {
/* 86 */       if (currentType instanceof Class) {
/* 87 */         Class currentClass = (Class)currentType;
/* 88 */         ClassVersion currentClassVersion = new ClassVersion(version.versionName(), currentClass);
/* 89 */         String currentClassName = currentClass.getFullName();
/* 90 */         AbstractHistory currentHistory = this.classHistories.get(currentClassName);
/* 91 */         if (currentHistory == null) {
/* 92 */           currentHistory = new ClassHistory(this);
/* 93 */           currentHistory.addVersion(currentClassVersion);
/* 94 */           this.classHistories.put(currentHistory); continue;
/*    */         } 
/* 96 */         currentHistory.addVersion(currentClassVersion);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\hismo\core\NamespaceHistory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */