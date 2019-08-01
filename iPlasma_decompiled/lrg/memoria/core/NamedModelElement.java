/*    */ package lrg.memoria.core;
/*    */ 
/*    */ public abstract class NamedModelElement
/*    */   extends ModelElement
/*    */ {
/*    */   protected String name;
/*    */   private int statute;
/*    */   protected ModelElementList<AnnotationInstance> annotations;
/*    */   
/*    */   public NamedModelElement(String name) {
/* 11 */     this.statute = 3;
/*    */ 
/*    */ 
/*    */     
/* 15 */     this.name = name;
/* 16 */     this.annotations = new ModelElementList();
/*    */   }
/*    */   protected NamedModelElement(NamedModelElement namedElement) {
/*    */     this.statute = 3;
/* 20 */     this.name = namedElement.name;
/* 21 */     this.statute = namedElement.statute;
/* 22 */     this.annotations = namedElement.annotations;
/*    */   }
/*    */ 
/*    */   
/* 26 */   public String getName() { return this.name; }
/*    */ 
/*    */ 
/*    */   
/* 30 */   public String getFullName() { return getName(); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 54 */   public void setStatute(int statute) { this.statute = statute; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 61 */   public int getStatute() { return this.statute; }
/*    */ 
/*    */   
/*    */   public void addAnnotationInstance(AnnotationInstance a) {
/* 65 */     if (this.annotations == null)
/* 66 */       this.annotations = new ModelElementList(); 
/* 67 */     this.annotations.add(a);
/*    */   }
/*    */ 
/*    */   
/* 71 */   public ModelElementList<AnnotationInstance> getAnnotations() { return this.annotations; }
/*    */ 
/*    */ 
/*    */   
/* 75 */   public void setAnnotations(ModelElementList<AnnotationInstance> annotations) { this.annotations = annotations; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\NamedModelElement.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */