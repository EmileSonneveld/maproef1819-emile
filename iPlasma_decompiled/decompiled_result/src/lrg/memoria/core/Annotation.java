/*     */ package lrg.memoria.core;
/*     */ 
/*     */ 
/*     */ public class Annotation
/*     */   extends ExplicitlyDefinedType
/*     */   implements Scope
/*     */ {
/*     */   private ModelElementList<AnnotationProperty> annotationProperties;
/*     */   
/*     */   public Annotation(String name) {
/*  11 */     super(name);
/*  12 */     this.annotationProperties = new ModelElementList();
/*     */   }
/*     */   
/*     */   protected Annotation(Annotation a) {
/*  16 */     super(a);
/*  17 */     this.annotationProperties = a.annotationProperties;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  22 */   public void accept(ModelVisitor v) { v.visitAnnotation(this); }
/*     */ 
/*     */ 
/*     */   
/*  26 */   public void setName(String newName) { this.name = newName; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addScopedElement(Scopable element) {
/*  33 */     if (element instanceof AnnotationProperty) {
/*  34 */       addAnnotationProperty((AnnotationProperty)element);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  42 */   public ModelElementList getScopedElements() { return this.annotationProperties; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAnnotationProperty(AnnotationProperty ap) {
/*  49 */     if (this.annotationProperties == null)
/*  50 */       this.annotationProperties = new ModelElementList(); 
/*  51 */     this.annotationProperties.add(ap);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   public ModelElementList<AnnotationProperty> getAnnotationProperties() { return this.annotationProperties; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   public void setAnnotationProperties(ModelElementList<AnnotationProperty> properties) { this.annotationProperties = properties; }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  69 */     StringBuffer myStr = new StringBuffer("\t\tAnnotation: ");
/*  70 */     myStr.append(getFullName()).append("\n\t\t - statute: ");
/*  71 */     myStr.append(Statute.convertToString(getStatute()));
/*  72 */     myStr.append("\n\t\t - location: ");
/*  73 */     if (getLocation() != null)
/*  74 */       myStr.append(getLocation()); 
/*  75 */     myStr.append("\n\t\t - scope: ");
/*  76 */     if (getScope() != null)
/*  77 */       myStr.append(getScope().getName()); 
/*  78 */     if (this.annotationProperties != null) {
/*  79 */       myStr.append("\n\t\t - properties: ");
/*  80 */       for (AnnotationProperty ap : this.annotationProperties) {
/*  81 */         myStr.append(ap);
/*     */       }
/*     */     } 
/*  84 */     if (this.annotations != null) {
/*  85 */       myStr.append("\n\t\t - meta-annotations: ");
/*  86 */       for (AnnotationInstance ai : this.annotations) {
/*  87 */         myStr.append("\n\t\t\t -" + ai.getAnnotation().getFullName());
/*  88 */         for (int i = 0; i < ai.getPropertyValuePairs().size(); i++) {
/*  89 */           myStr.append("\n\t\t\t\t");
/*  90 */           myStr.append(((AnnotationPropertyValuePair)ai.getPropertyValuePairs().get(i)).getAp().getName());
/*  91 */           myStr.append(" = ").append(((AnnotationPropertyValuePair)ai.getPropertyValuePairs().get(i)).getValue());
/*     */         } 
/*     */       } 
/*     */     } 
/*  95 */     return new String(myStr);
/*     */   }
/*     */   
/*     */   boolean restore() {
/*  99 */     if (super.restore()) {
/* 100 */       if (this.annotationProperties != null)
/* 101 */         this.annotationProperties.restore(); 
/* 102 */       if (this.annotations != null)
/* 103 */         this.annotations.restore(); 
/* 104 */       return true;
/*     */     } 
/* 106 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\Annotation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */