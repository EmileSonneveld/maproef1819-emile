/*    */ package lrg.memoria.importer.mcc.loader;
/*    */ 
/*    */ import lrg.memoria.core.TemplateInstance;
/*    */ import lrg.memoria.core.Type;
/*    */ 
/*    */ public class DefaultChainVisitor
/*    */   extends DefaultVisitorRoot implements ChainVisitor {
/*    */   private Integer tid;
/*    */   private TemplateInstance templateInstance;
/*    */   private boolean errorOccured;
/*    */   
/* 12 */   public void setId(Integer id) { this.errorOccured = false; }
/*    */ 
/*    */   
/*    */   public void setT2TRelationId(String relationId) {
/* 16 */     if (relationId.equals("<ERROR>")) {
/* 17 */       this.errorOccured = true;
/*    */       return;
/*    */     } 
/* 20 */     this.tid = new Integer(relationId);
/*    */   }
/*    */   
/*    */   public void setTemplateInstanceId(String templateInstanceId) {
/* 24 */     if (templateInstanceId.equals("<ERROR>")) {
/* 25 */       this.errorOccured = true;
/*    */       return;
/*    */     } 
/* 28 */     if (Loader.getInstance().getType(new Integer(templateInstanceId)) instanceof TemplateInstance) {
/* 29 */       this.templateInstance = (TemplateInstance)Loader.getInstance().getType(new Integer(templateInstanceId));
/*    */     } else {
/* 31 */       this.errorOccured = true;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void addChain() {
/* 36 */     if (!this.errorOccured) {
/* 37 */       Type ti = Loader.getInstance().getTemplateParameterType(this.tid);
/* 38 */       this.templateInstance.addTypeInstantiation(ti);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\mcc\loader\DefaultChainVisitor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */