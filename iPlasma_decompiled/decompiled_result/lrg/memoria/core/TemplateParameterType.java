/*    */ package lrg.memoria.core;
/*    */ 
/*    */ public class TemplateParameterType extends DataAbstraction {
/*  4 */   public static String UNKNOWN_TPT_NAME = "unknown_tpt";
/*    */   
/*  6 */   public static TemplateParameterType getUnknownTemplateParameterType() { return ModelElementsRepository.getCurrentModelElementsRepository().getUnknownTemplateParameterType(); }
/*    */ 
/*    */ 
/*    */   
/*    */   private ModelElementList<Type> instantiationTypes;
/*    */ 
/*    */   
/*    */   public TemplateParameterType(String name) {
/* 14 */     super(name);
/* 15 */     this.instantiationTypes = new ModelElementList();
/*    */   }
/*    */ 
/*    */   
/* 19 */   public void addInstantiationType(Type it) { this.instantiationTypes.add(it); }
/*    */ 
/*    */ 
/*    */   
/* 23 */   public ModelElementList<Type> getInstantiationTypes() { return this.instantiationTypes; }
/*    */ 
/*    */ 
/*    */   
/* 27 */   public void accept(ModelVisitor mv) { mv.visitTemplateParameter(this); }
/*    */ 
/*    */ 
/*    */   
/* 31 */   public String toString() { return "TemplateParameterType \n\tname: " + this.name; }
/*    */ 
/*    */   
/*    */   boolean restore() {
/* 35 */     if (super.restore()) {
/* 36 */       this.instantiationTypes.restore();
/* 37 */       return true;
/*    */     } 
/* 39 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\TemplateParameterType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */