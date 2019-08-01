/*     */ package lrg.memoria.core;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Method
/*     */   extends Function
/*     */ {
/*     */   public static final int CONSTRUCTOR = 1;
/*     */   public static final int ACCESSOR = 2;
/*     */   public static final int OTHER = 3;
/*     */   public static final String UNKNOWN_METHOD_NAME = "unknown_method";
/*     */   private ModelElementList<Class> throwsExceptionsList;
/*     */   
/*  16 */   public static Method getUnknownMethod() { return ModelElementsRepository.getCurrentModelElementsRepository().getUnknownMethod(); }
/*     */ 
/*     */   
/*     */   protected boolean isAbstract = false;
/*     */   
/*     */   protected boolean isVirtual = true;
/*     */   
/*     */   protected boolean isStatic = false;
/*     */   
/*  25 */   private int kindOf = 3;
/*  26 */   private int accessMode = 3;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Method(String name) {
/*  32 */     super(name);
/*  33 */     this.throwsExceptionsList = new ModelElementList();
/*  34 */     setScope(Class.getUnknownClass());
/*     */   }
/*     */   
/*     */   public Method(Method method) {
/*  38 */     super(method);
/*  39 */     this.location = method.location;
/*  40 */     this.returnType = method.returnType;
/*  41 */     this.body = method.body;
/*  42 */     this.throwsExceptionsList = method.throwsExceptionsList;
/*  43 */     this.isAbstract = method.isAbstract;
/*  44 */     this.isVirtual = method.isVirtual;
/*  45 */     this.isStatic = method.isStatic;
/*  46 */     this.kindOf = method.kindOf;
/*  47 */     this.accessMode = method.accessMode;
/*     */   }
/*     */ 
/*     */   
/*  51 */   public void setAccessMode(int accMode) { this.accessMode = accMode; }
/*     */ 
/*     */ 
/*     */   
/*  55 */   public int getAccessMode() { return this.accessMode; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   public void setAbstract() { this.isAbstract = true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  77 */   public void setAccessor() { this.kindOf = 2; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  84 */   public void addException(Class e) { this.throwsExceptionsList.add(e); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  91 */   public ModelElementList<Class> getExceptionList() { return this.throwsExceptionsList; }
/*     */ 
/*     */ 
/*     */   
/*  95 */   public void setKindOf(int k) { this.kindOf = k; }
/*     */ 
/*     */ 
/*     */   
/*  99 */   public int getKindOf() { return this.kindOf; }
/*     */ 
/*     */ 
/*     */   
/* 103 */   public boolean isVirtual() { return this.isVirtual; }
/*     */ 
/*     */ 
/*     */   
/* 107 */   public void setVirtual() { this.isVirtual = true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 114 */   public void setFinal() { this.isVirtual = false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 121 */   public boolean isFinal() { return !this.isVirtual; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 128 */   public boolean isConstructor() { return (this.kindOf == 1); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConstructor() {
/* 135 */     this.kindOf = 1;
/* 136 */     this.returnType = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 150 */   public boolean isAccessor() { return (this.kindOf == 2); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 158 */   public boolean isAbstract() { return this.isAbstract; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 165 */   public boolean isPublic() { return (this.accessMode == 1); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 172 */   public boolean isPrivate() { return (this.accessMode == 4); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 179 */   public boolean isPackage() { return (this.accessMode == 3); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 186 */   public boolean isProtected() { return (this.accessMode == 2); }
/*     */ 
/*     */ 
/*     */   
/* 190 */   public void accept(ModelVisitor v) { v.visitMethod(this); }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 195 */     StringBuffer myStr = new StringBuffer("\t\t\t");
/*     */     
/* 197 */     if (isConstructor()) {
/* 198 */       myStr.append("Constructor: ");
/*     */     } else {
/* 200 */       myStr.append("Method: ");
/* 201 */     }  myStr.append(this.name).append("(").append(getScope().getFullName()).append(")");
/* 202 */     myStr.append("\n\t\t\t - location: ").append(this.location);
/* 203 */     myStr.append("\n\t\t\t - statute: ");
/* 204 */     myStr.append(Statute.convertToString(getStatute()));
/* 205 */     if (getStatute() != 3) {
/* 206 */       myStr.append("\n\t\t\t - access mode: ");
/* 207 */       if (isPublic())
/* 208 */         myStr.append("public"); 
/* 209 */       if (isProtected())
/* 210 */         myStr.append("protected"); 
/* 211 */       if (isPrivate())
/* 212 */         myStr.append("private"); 
/* 213 */       if (isPackage())
/* 214 */         myStr.append("package"); 
/* 215 */       myStr.append("\n\t\t\t - flags: ");
/* 216 */       int count = 0;
/* 217 */       if (isFinal()) {
/* 218 */         count++;
/* 219 */         myStr.append("final, ");
/*     */       } 
/* 221 */       if (isAbstract()) {
/* 222 */         count++;
/* 223 */         myStr.append("abstract, ");
/*     */       } 
/* 225 */       if (isStatic()) {
/* 226 */         myStr.append("static.");
/* 227 */       } else if (count > 0) {
/* 228 */         int tmp = myStr.length();
/* 229 */         myStr.delete(tmp - 2, tmp).append(".");
/*     */       } 
/* 231 */       myStr.append("\n\t\t\t - exceptions: "); int i;
/* 232 */       for (i = 0; i < this.throwsExceptionsList.size(); i++) {
/* 233 */         DataAbstraction e = (DataAbstraction)this.throwsExceptionsList.get(i);
/* 234 */         myStr.append(String.valueOf(e.getName()) + ", ");
/*     */       } 
/* 236 */       if (i > 0) {
/* 237 */         int tmp = myStr.length();
/* 238 */         myStr.delete(tmp - 2, tmp).append(".");
/*     */       } 
/* 240 */       myStr.append("\n\t\t\t - scope: ");
/* 241 */       myStr.append(getScope().getName());
/* 242 */       myStr.append("\n\t\t\t - parameters: ");
/* 243 */       for (i = 0; i < getParameterList().size(); i++) {
/*     */         
/* 245 */         Parameter var = (Parameter)getParameterList().get(i);
/* 246 */         myStr.append(var.getName()).append("(" + var.getType().getName());
/* 247 */         if (var.getType() instanceof ArrayDecorator) myStr.append("[]"); 
/* 248 */         myStr.append(")").append(", ");
/*     */       } 
/* 250 */       if (i > 0) {
/* 251 */         int tmp = myStr.length();
/* 252 */         myStr.delete(tmp - 2, tmp).append(".");
/*     */       } 
/* 254 */       if (!isConstructor()) {
/* 255 */         myStr.append("\n\t\t\t - returns: ");
/* 256 */         myStr.append(this.returnType.getName());
/*     */       } 
/* 258 */       if (getStatute() == 1 && !isAbstract() && !((DataAbstraction)getScope()).isInterface()) myStr.append(this.body); 
/*     */     } 
/* 260 */     if (this.annotations != null) {
/* 261 */       myStr.append("\n\t\t\t - annotations: ");
/* 262 */       for (AnnotationInstance ai : this.annotations) {
/* 263 */         myStr.append("\n\t\t\t  -" + ai.getAnnotation().getFullName());
/* 264 */         for (int i = 0; i < ai.getPropertyValuePairs().size(); i++) {
/* 265 */           myStr.append("\n\t\t\t\t");
/* 266 */           myStr.append(((AnnotationPropertyValuePair)ai.getPropertyValuePairs().get(i)).getAp().getName());
/* 267 */           myStr.append(" = ").append(((AnnotationPropertyValuePair)ai.getPropertyValuePairs().get(i)).getValue());
/*     */         } 
/*     */       } 
/*     */     } 
/* 271 */     myStr.append("\n");
/* 272 */     return new String(myStr);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 280 */   public void setStatic() { this.isStatic = true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 288 */   public boolean isStatic() { return this.isStatic; }
/*     */ 
/*     */   
/*     */   boolean restore() {
/* 292 */     if (super.restore()) {
/* 293 */       this.throwsExceptionsList.restore();
/* 294 */       return true;
/*     */     } 
/* 296 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\Method.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */