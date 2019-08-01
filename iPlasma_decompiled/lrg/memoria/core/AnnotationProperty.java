/*    */ package lrg.memoria.core;
/*    */ 
/*    */ public class AnnotationProperty
/*    */   extends NamedModelElement implements Scopable {
/*    */   private Type type;
/*    */   private Scope scope;
/*  7 */   private Location location = Location.getUnknownLocation();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 13 */   public AnnotationProperty(String name) { super(name); }
/*    */ 
/*    */   
/*    */   protected AnnotationProperty(AnnotationProperty ap) {
/* 17 */     super(ap);
/* 18 */     this.type = ap.type;
/* 19 */     this.scope = ap.scope;
/* 20 */     this.location = ap.location;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 25 */   public Location getLocation() { return this.location; }
/*    */ 
/*    */ 
/*    */   
/* 29 */   public void setLocation(Location location) { this.location = location; }
/*    */ 
/*    */ 
/*    */   
/* 33 */   public Type getType() { return this.type; }
/*    */ 
/*    */ 
/*    */   
/* 37 */   public void setType(Type type) { this.type = type; }
/*    */ 
/*    */ 
/*    */   
/* 41 */   public void setScope(Scope scope) { this.scope = scope; }
/*    */ 
/*    */ 
/*    */   
/* 45 */   public Scope getScope() { return this.scope; }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 49 */     StringBuffer myStr = new StringBuffer("\n\t\t\t - Annotation Property: ");
/* 50 */     myStr.append(getName());
/* 51 */     myStr.append("\n\t\t\t  - type: ");
/* 52 */     if (getType() != null) {
/* 53 */       myStr.append(getType().getFullName());
/*    */     } else {
/* 55 */       myStr.append("unknown");
/* 56 */     }  myStr.append("\n\t\t\t  - statute: ");
/* 57 */     myStr.append(Statute.convertToString(getStatute()));
/* 58 */     myStr.append("\n\t\t\t  - location: ");
/* 59 */     if (getLocation() != null)
/* 60 */       myStr.append(getLocation()); 
/* 61 */     myStr.append("\n\t\t\t  - scope: ");
/* 62 */     if (getScope() != null)
/* 63 */       myStr.append(getScope().getName()); 
/* 64 */     if (this.annotations != null) {
/* 65 */       myStr.append("\n\t\t\t  - annotations: ");
/* 66 */       for (AnnotationInstance ai : this.annotations) {
/* 67 */         myStr.append("\n\t\t\t\t - " + ai.getAnnotation().getFullName());
/* 68 */         for (int i = 0; i < ai.getPropertyValuePairs().size(); i++) {
/* 69 */           myStr.append("\n\t\t\t\t\t");
/* 70 */           myStr.append(((AnnotationPropertyValuePair)ai.getPropertyValuePairs().get(i)).getAp().getName());
/* 71 */           myStr.append(" = ").append(((AnnotationPropertyValuePair)ai.getPropertyValuePairs().get(i)).getValue());
/*    */         } 
/*    */       } 
/*    */     } 
/* 75 */     return new String(myStr);
/*    */   }
/*    */ 
/*    */   
/* 79 */   public void accept(ModelVisitor v) { v.visitAnnotationProperty(this); }
/*    */ 
/*    */   
/*    */   boolean restore() {
/* 83 */     if (super.restore()) {
/* 84 */       if (this.annotations != null)
/* 85 */         this.annotations.restore(); 
/* 86 */       return true;
/*    */     } 
/* 88 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\AnnotationProperty.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */