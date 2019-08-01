/*    */ package lrg.memoria.core;
/*    */ 
/*    */ public class TemplateInstance extends NamedModelElement implements Type {
/*    */   private GenericClass templateType;
/*    */   private ModelElementList<Type> typeInstantiations;
/*    */   
/*    */   public TemplateInstance(GenericClass template, String name) {
/*  8 */     super(name);
/*  9 */     this.templateType = template;
/* 10 */     this.typeInstantiations = new ModelElementList();
/*    */   }
/*    */ 
/*    */   
/* 14 */   public String getFullName() { return String.valueOf(getScope().getFullName()) + "." + getName(); }
/*    */ 
/*    */ 
/*    */   
/* 18 */   public String getName() { return this.name; }
/*    */ 
/*    */ 
/*    */   
/* 22 */   public GenericClass getTemplateType() { return this.templateType; }
/*    */ 
/*    */ 
/*    */   
/* 26 */   public void addTypeInstantiation(Type t) { this.typeInstantiations.add(t); }
/*    */ 
/*    */ 
/*    */   
/* 30 */   public ModelElementList<Type> getTypeInstantiations() { return this.typeInstantiations; }
/*    */ 
/*    */ 
/*    */   
/* 34 */   public Scope getScope() { return this.templateType.getScope(); }
/*    */ 
/*    */ 
/*    */   
/* 38 */   public void accept(ModelVisitor mv) { mv.visitTemplateInstance(this); }
/*    */ 
/*    */   
/*    */   boolean restore() {
/* 42 */     if (super.restore()) {
/* 43 */       this.typeInstantiations.restore();
/* 44 */       return true;
/*    */     } 
/* 46 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\TemplateInstance.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */