/*    */ package lrg.memoria.importer.mcc.loader;
/*    */ 
/*    */ import lrg.memoria.core.Class;
/*    */ import lrg.memoria.core.TemplateParameterType;
/*    */ import lrg.memoria.core.Type;
/*    */ 
/*    */ public class DefaultTp2tVisitor extends DefaultVisitorRoot implements Tp2tVisitor {
/*  8 */   private Loader loaderInstance = Loader.getInstance();
/*    */   private Integer id;
/*    */   private TemplateParameterType templateParameterType;
/*    */   private Type instantiationType;
/*    */   
/* 13 */   public void setId(String id) { this.id = new Integer(id); }
/*    */ 
/*    */   
/*    */   public void setTemplateParamID(String tpi) {
/* 17 */     if (!tpi.equals("<UNKNOWN>") && !tpi.equals("<ERROR>"))
/* 18 */       this.templateParameterType = (TemplateParameterType)this.loaderInstance.getType(new Integer(tpi)); 
/* 19 */     if (this.templateParameterType == null) {
/* 20 */       this.templateParameterType = TemplateParameterType.getUnknownTemplateParameterType();
/*    */     }
/*    */   }
/*    */   
/*    */   public void setInstantiationTypeID(String iti) {
/* 25 */     if (!iti.equals("<ERROR>"))
/* 26 */       this.instantiationType = this.loaderInstance.getType(new Integer(iti)); 
/* 27 */     if (this.instantiationType == null) {
/* 28 */       this.instantiationType = Class.getUnknownClass();
/*    */     }
/*    */   }
/*    */   
/*    */   public void addInstantiation() {
/* 33 */     this.templateParameterType.addInstantiationType(this.instantiationType);
/* 34 */     Loader.getInstance().addTemplateParameterToType(this.id, this.instantiationType);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\mcc\loader\DefaultTp2tVisitor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */