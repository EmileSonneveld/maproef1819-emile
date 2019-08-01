/*    */ package lrg.memoria.hismo.core;
/*    */ 
/*    */ import lrg.memoria.core.Class;
/*    */ import lrg.memoria.core.ModelElementList;
/*    */ import lrg.memoria.core.Type;
/*    */ 
/*    */ public class PackageHistory
/*    */   extends AbstractHistory
/*    */ {
/*    */   ClassHistoryGroup classHistories;
/*    */   
/* 12 */   public PackageHistory() { initializeInnerHistories(); }
/*    */ 
/*    */ 
/*    */   
/* 16 */   public PackageHistory(VersionsList versions) { super(versions); }
/*    */ 
/*    */ 
/*    */   
/* 20 */   protected void initializeInnerHistories() { this.classHistories = new ClassHistoryGroup(); }
/*    */ 
/*    */ 
/*    */   
/* 24 */   public ClassHistoryGroup getClassHistories() { return this.classHistories; }
/*    */ 
/*    */   
/*    */   protected void updateInnerHistories(AbstractVersion version) {
/* 28 */     ModelElementList<Type> currentTypes = ((PackageVersion)version).getAllTypesList();
/*    */ 
/*    */ 
/*    */     
/* 32 */     for (Type currentType : currentTypes) {
/* 33 */       if (currentType instanceof Class) {
/* 34 */         Class currentClass = (Class)currentType;
/* 35 */         ClassVersion currentClassVersion = new ClassVersion(version.versionName(), currentClass);
/* 36 */         String currentClassName = currentClass.getFullName();
/* 37 */         AbstractHistory currentHistory = this.classHistories.get(currentClassName);
/* 38 */         if (currentHistory == null) {
/* 39 */           currentHistory = new ClassHistory();
/* 40 */           currentHistory.addVersion(currentClassVersion);
/* 41 */           this.classHistories.put(currentHistory); continue;
/*    */         } 
/* 43 */         currentHistory.addVersion(currentClassVersion);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\hismo\core\PackageHistory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */