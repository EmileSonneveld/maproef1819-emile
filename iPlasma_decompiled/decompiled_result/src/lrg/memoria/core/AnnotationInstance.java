/*    */ package lrg.memoria.core;
/*    */ 
/*    */ public class AnnotationInstance extends ModelElement {
/*    */   private NamedModelElement annotatedElement;
/*    */   private Annotation a;
/*    */   private ModelElementList<AnnotationPropertyValuePair> apv;
/*    */   
/*    */   public AnnotationInstance(Annotation a) {
/*  9 */     this.a = a;
/* 10 */     this.apv = new ModelElementList();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 17 */   public Annotation getAnnotation() { return this.a; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 25 */   public void setAnnotation(Annotation a) { this.a = a; }
/*    */ 
/*    */ 
/*    */   
/* 29 */   public String getName() { return this.a.getName(); }
/*    */ 
/*    */ 
/*    */   
/* 33 */   public String getFullName() { return this.a.getFullName(); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 40 */   public ModelElementList<AnnotationPropertyValuePair> getPropertyValuePairs() { return this.apv; }
/*    */ 
/*    */ 
/*    */   
/* 44 */   public void setApv(ModelElementList<AnnotationPropertyValuePair> apv) { this.apv = apv; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void addAnotationProperyValuePair(AnnotationProperty ap, String value) {
/* 54 */     AnnotationPropertyValuePair apvp = new AnnotationPropertyValuePair();
/* 55 */     apvp.setAp(ap);
/* 56 */     apvp.setValue(value);
/* 57 */     if (this.apv == null)
/* 58 */       this.apv = new ModelElementList(); 
/* 59 */     this.apv.add(apvp);
/*    */   }
/*    */ 
/*    */   
/* 63 */   public void accept(ModelVisitor v) { v.visitAnnotationInstance(this); }
/*    */ 
/*    */   
/*    */   boolean restore() {
/* 67 */     if (super.restore()) {
/* 68 */       if (this.apv != null)
/* 69 */         this.apv.restore(); 
/* 70 */       return true;
/*    */     } 
/* 72 */     return false;
/*    */   }
/*    */ 
/*    */   
/* 76 */   public void setAnnotatedElement(NamedModelElement annotatedElement) { this.annotatedElement = annotatedElement; }
/*    */ 
/*    */ 
/*    */   
/* 80 */   public NamedModelElement getAnnotatedElement() { return this.annotatedElement; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\AnnotationInstance.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */