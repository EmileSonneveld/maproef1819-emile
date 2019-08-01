/*     */ package lrg.memoria.core;
/*     */ 
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Attribute
/*     */   extends Variable
/*     */ {
/*  13 */   private int accessMode = 3;
/*     */   private boolean isStatic = false;
/*     */   
/*     */   protected Attribute(Attribute attr) {
/*  17 */     super(attr);
/*  18 */     this.accessMode = attr.accessMode;
/*  19 */     this.isStatic = attr.isStatic;
/*     */   }
/*     */   
/*     */   public Attribute(String name) {
/*  23 */     super(name);
/*  24 */     this.scope = Class.getUnknownClass();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Attribute(String name, Type dataType, DataAbstraction scope) {
/*  33 */     super(name, dataType);
/*  34 */     this.scope = scope;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Attribute(String name, Type dataType, DataAbstraction scope, int accMode) {
/*  43 */     this(name, dataType, scope);
/*  44 */     this.accessMode = accMode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   public DataAbstraction getScope() { return (DataAbstraction)this.scope; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   public void setStatic() { this.isStatic = true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   public boolean isStatic() { return this.isStatic; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   public void setAccessMode(int accMode) { this.accessMode = accMode; }
/*     */ 
/*     */ 
/*     */   
/*  79 */   public int getAccessMode() { return this.accessMode; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   public boolean isPublic() { return (this.accessMode == 1); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  93 */   public boolean isPrivate() { return (this.accessMode == 4); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   public boolean isProtected() { return (this.accessMode == 2); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 107 */   public boolean isPackage() { return (this.accessMode == 3); }
/*     */ 
/*     */ 
/*     */   
/* 111 */   public void accept(ModelVisitor v) { v.visitAttribute(this); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeXML(FileWriter output) throws IOException {}
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 120 */     StringBuffer myStr = new StringBuffer("\t\t\tAttribute: ");
/* 121 */     myStr.append(getName()).append("(").append(this.scope.getFullName()).append(")");
/* 122 */     myStr.append("\n\t\t\t - type: ").append(getType().getName());
/* 123 */     if (isArray())
/* 124 */       myStr.append("[]"); 
/* 125 */     myStr.append("\n\t\t\t - scope: ").append(this.scope.getName());
/* 126 */     myStr.append("\n\t\t\t - location: ").append(getLocation());
/* 127 */     myStr.append("\n\t\t\t - access mode: ");
/* 128 */     if (isPublic())
/* 129 */       myStr.append("public"); 
/* 130 */     if (isProtected())
/* 131 */       myStr.append("protected"); 
/* 132 */     if (isPrivate())
/* 133 */       myStr.append("private"); 
/* 134 */     if (isPackage())
/* 135 */       myStr.append("package"); 
/* 136 */     myStr.append("\n\t\t\t - flags: ");
/* 137 */     if (isFinal())
/* 138 */       myStr.append("final, "); 
/* 139 */     if (isStatic()) {
/* 140 */       myStr.append("static.");
/* 141 */     } else if (isFinal()) {
/* 142 */       int tmp = myStr.length();
/* 143 */       myStr.delete(tmp - 2, tmp);
/* 144 */       myStr.append(".");
/*     */     } 
/* 146 */     if (this.annotations != null) {
/* 147 */       myStr.append("\n\t\t\t - annotations: ");
/* 148 */       for (AnnotationInstance ai : this.annotations) {
/* 149 */         myStr.append("\n\t\t\t  -" + ai.getAnnotation().getFullName());
/* 150 */         for (int i = 0; i < ai.getPropertyValuePairs().size(); i++) {
/* 151 */           myStr.append("\n\t\t\t\t");
/* 152 */           myStr.append(((AnnotationPropertyValuePair)ai.getPropertyValuePairs().get(i)).getAp().getName());
/* 153 */           myStr.append(" = ").append(((AnnotationPropertyValuePair)ai.getPropertyValuePairs().get(i)).getValue());
/*     */         } 
/*     */       } 
/*     */     } 
/* 157 */     myStr.append("\n");
/* 158 */     return new String(myStr);
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\Attribute.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */