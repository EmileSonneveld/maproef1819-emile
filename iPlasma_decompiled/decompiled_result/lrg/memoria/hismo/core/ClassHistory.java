/*    */ package lrg.memoria.hismo.core;
/*    */ 
/*    */ import lrg.memoria.core.Attribute;
/*    */ import lrg.memoria.core.Method;
/*    */ import lrg.memoria.core.ModelElementList;
/*    */ 
/*    */ 
/*    */ public class ClassHistory
/*    */   extends AbstractHistory
/*    */ {
/*    */   MethodHistoryGroup methodHistories;
/*    */   AttributeHistoryGroup attributeHistories;
/*    */   NamespaceHistory namespaceHistory;
/*    */   
/* 15 */   public ClassHistory() { initializeInnerHistories(); }
/*    */ 
/*    */   
/*    */   public ClassHistory(NamespaceHistory nsh) {
/* 19 */     this.namespaceHistory = nsh;
/* 20 */     initializeInnerHistories();
/*    */   }
/*    */ 
/*    */   
/* 24 */   public ClassHistory(VersionsList versions) { super(versions); }
/*    */ 
/*    */   
/*    */   protected void initializeInnerHistories() {
/* 28 */     this.methodHistories = new MethodHistoryGroup();
/* 29 */     this.attributeHistories = new AttributeHistoryGroup();
/*    */   }
/*    */ 
/*    */   
/* 33 */   public NamespaceHistory getNamespaceHistory() { return this.namespaceHistory; }
/*    */ 
/*    */ 
/*    */   
/* 37 */   public MethodHistoryGroup getMethodHistories() { return this.methodHistories; }
/*    */ 
/*    */ 
/*    */   
/* 41 */   public AttributeHistoryGroup getAttributeHistories() { return this.attributeHistories; }
/*    */ 
/*    */   
/*    */   protected void updateInnerHistories(AbstractVersion version) {
/* 45 */     updateMethodHistories(version);
/* 46 */     updateAttributeHistories(version);
/*    */   }
/*    */   
/*    */   private void updateMethodHistories(AbstractVersion version) {
/* 50 */     ModelElementList modelElementList = ((ClassVersion)version).getMethodList();
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 55 */     for (int i = 0; i < modelElementList.size(); i++) {
/* 56 */       Method currentMethod = (Method)modelElementList.get(i);
/* 57 */       MethodVersion currentMethodVersion = new MethodVersion(version.versionName(), currentMethod);
/* 58 */       String currentMethodName = currentMethod.getFullName();
/* 59 */       AbstractHistory currentHistory = this.methodHistories.get(currentMethodName);
/* 60 */       if (currentHistory == null) {
/* 61 */         currentHistory = new MethodHistory(this);
/* 62 */         currentHistory.addVersion(currentMethodVersion);
/* 63 */         this.methodHistories.put(currentHistory);
/*    */       } else {
/* 65 */         currentHistory.addVersion(currentMethodVersion);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   private void updateAttributeHistories(AbstractVersion version) {
/* 70 */     ModelElementList modelElementList = ((ClassVersion)version).getAttributeList();
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 75 */     for (int i = 0; i < modelElementList.size(); i++) {
/* 76 */       Attribute currentAttribute = (Attribute)modelElementList.get(i);
/* 77 */       AttributeVersion currentAttributeVersion = new AttributeVersion(version.versionName(), currentAttribute);
/* 78 */       String currentAttributeName = currentAttribute.getFullName();
/* 79 */       AbstractHistory currentHistory = this.attributeHistories.get(currentAttributeName);
/* 80 */       if (currentHistory == null) {
/* 81 */         currentHistory = new AttributeHistory(this);
/* 82 */         currentHistory.addVersion(currentAttributeVersion);
/* 83 */         this.attributeHistories.put(currentHistory);
/*    */       } else {
/* 85 */         currentHistory.addVersion(currentAttributeVersion);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\hismo\core\ClassHistory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */