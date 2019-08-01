/*     */ package lrg.memoria.core;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Class
/*     */   extends DataAbstraction
/*     */ {
/*     */   public static final String UNKNOWN_CLASS_NAME = "_UNKNOWN_CLASS_";
/*     */   private ModelElementList<InitializerBody> initializers;
/*     */   
/*  12 */   public static DataAbstraction getUnknownClass() { return ModelElementsRepository.getCurrentModelElementsRepository().getUnknownClass(); }
/*     */ 
/*     */ 
/*     */   
/*  16 */   public static void setHierarchyRootClass(DataAbstraction cls) { ModelElementsRepository.getCurrentModelElementsRepository().setHierarchyRootClass(cls); }
/*     */ 
/*     */   
/*  19 */   public static DataAbstraction getHierarchyRootClass() { return ModelElementsRepository.getCurrentModelElementsRepository().getHierarchyRootClass(); }
/*     */ 
/*     */   
/*     */   private boolean isStructure = false, isFinal = false;
/*     */   
/*     */   private boolean isAbstract = false;
/*     */   private boolean isInterface = false;
/*     */   private boolean isStatic = false;
/*  27 */   private int accessMode = 3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  33 */   public Class(String name) { super(name); }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Class(Class oldClass) {
/*  38 */     super(oldClass);
/*  39 */     this.initializers = oldClass.initializers;
/*  40 */     this.isFinal = oldClass.isFinal;
/*  41 */     this.isAbstract = oldClass.isAbstract;
/*  42 */     this.isInterface = oldClass.isInterface;
/*  43 */     this.isStatic = oldClass.isStatic;
/*  44 */     this.accessMode = oldClass.accessMode;
/*  45 */     this.isStructure = oldClass.isStructure;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  50 */   public void setName(String newName) { this.name = newName; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   public void setFinal() { this.isFinal = true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   public void setAbstract() { this.isAbstract = true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   public void setInterface() { this.isInterface = true; }
/*     */ 
/*     */ 
/*     */   
/*  76 */   public void setStatic() { this.isStatic = true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   public void setAccessMode(int accMode) { this.accessMode = accMode; }
/*     */ 
/*     */ 
/*     */   
/*  87 */   public int getAccessMode() { return this.accessMode; }
/*     */ 
/*     */ 
/*     */   
/*  91 */   public void setStructure() { this.isStructure = true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  98 */   public boolean isStructure() { return this.isStructure; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 106 */   public boolean isFinal() { return this.isFinal; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 113 */   public boolean isAbstract() { return this.isAbstract; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 121 */   public boolean isInterface() { return this.isInterface; }
/*     */ 
/*     */ 
/*     */   
/* 125 */   public boolean isStatic() { return this.isStatic; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 133 */   public boolean isPublic() { return (this.accessMode == 1); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 140 */   public boolean isPrivate() { return (this.accessMode == 4); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 147 */   public boolean isProtected() { return (this.accessMode == 2); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 154 */   public boolean isPackage() { return (this.accessMode == 3); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addInterface(DataAbstraction interf) {
/* 161 */     if (interf != null) {
/* 162 */       getAncestorsList().add(interf);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addInitializer(InitializerBody initializer) {
/* 169 */     if (this.initializers == null)
/* 170 */       this.initializers = new ModelElementList(); 
/* 171 */     this.initializers.add(initializer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 178 */   public void setInitializersList(ModelElementList<InitializerBody> il) { this.initializers = il; }
/*     */ 
/*     */   
/*     */   public void addScopedElement(Scopable element) {
/* 182 */     if (element instanceof InitializerBody)
/* 183 */       addInitializer((InitializerBody)element); 
/* 184 */     super.addScopedElement(element);
/*     */   }
/*     */   
/*     */   public ModelElementList getScopedElements() {
/* 188 */     ModelElementList scopedElements = super.getScopedElements();
/* 189 */     if (this.initializers != null)
/* 190 */       scopedElements.addAll(this.initializers); 
/* 191 */     return scopedElements;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ModelElementList<InitializerBody> getInitializerList() {
/* 198 */     if (this.initializers == null)
/* 199 */       this.initializers = new ModelElementList(); 
/* 200 */     return this.initializers;
/*     */   }
/*     */ 
/*     */   
/* 204 */   public void accept(ModelVisitor v) { v.visitClass(this); }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 209 */     StringBuffer myStr = new StringBuffer("\t\t");
/* 210 */     if (isInterface()) {
/* 211 */       myStr.append("Interface: ");
/*     */     } else {
/* 213 */       myStr.append("Class: ");
/* 214 */     }  myStr.append(getFullName()).append("\n\t\t - statute: ");
/* 215 */     myStr.append(Statute.convertToString(getStatute()));
/* 216 */     myStr.append("\n\t\t - location: ").append(getLocation());
/* 217 */     if (getStatute() != 3) {
/* 218 */       myStr.append("\n\t\t - access mode: ");
/* 219 */       if (isPublic())
/* 220 */         myStr.append("public"); 
/* 221 */       if (isProtected())
/* 222 */         myStr.append("protected"); 
/* 223 */       if (isPrivate())
/* 224 */         myStr.append("private"); 
/* 225 */       if (isPackage())
/* 226 */         myStr.append("package"); 
/* 227 */       myStr.append("\n\t\t - flags: ");
/* 228 */       if (isFinal())
/* 229 */         myStr.append("final, "); 
/* 230 */       if (isAbstract()) {
/* 231 */         myStr.append("abstract.");
/* 232 */       } else if (isFinal()) {
/* 233 */         int tmp = myStr.length();
/* 234 */         myStr.delete(tmp - 2, tmp);
/* 235 */         myStr.append(".");
/*     */       } 
/* 237 */       myStr.append("\n\t\t - package: ").append(getPackage().getName());
/* 238 */       myStr.append("\n\t\t - scope: ");
/* 239 */       if (getScope() != null)
/* 240 */         myStr.append(getScope().getName()); 
/* 241 */       myStr.append("\n\t\t - supertypes: ");
/* 242 */       int i = 0;
/* 243 */       for (DataAbstraction currentSupertype : getAncestorsList()) {
/* 244 */         myStr.append(currentSupertype.getName());
/* 245 */         myStr.append(",");
/* 246 */         i++;
/*     */       } 
/* 248 */       if (i > 0) {
/* 249 */         int tmp = myStr.length();
/* 250 */         myStr.delete(tmp - 2, tmp).append(".");
/*     */       } 
/* 252 */       myStr.append("\n\t\t - descendants: ");
/* 253 */       i = 0;
/* 254 */       for (DataAbstraction currentDescendant : getDescendants()) {
/* 255 */         myStr.append(currentDescendant.getName()).append(", ");
/* 256 */         i++;
/*     */       } 
/* 258 */       if (i > 0) {
/* 259 */         int tmp = myStr.length();
/* 260 */         myStr.delete(tmp - 2, tmp).append(".");
/*     */       } 
/* 262 */       if (this.initializers != null) {
/* 263 */         myStr.append("\n\t\t - initializers: ");
/* 264 */         for (i = 0; i < this.initializers.size(); i++)
/* 265 */           myStr.append(this.initializers.get(i)); 
/*     */       } 
/* 267 */       myStr.append("\n\t\t - attributes:\n");
/* 268 */       for (i = 0; i < this.attributes.size(); i++)
/* 269 */         myStr.append((Attribute)this.attributes.get(i)); 
/* 270 */       myStr.append("\t\t - methods:\n");
/* 271 */       for (Method currentMethod : this.methods)
/* 272 */         myStr.append(currentMethod); 
/* 273 */       if (this.annotations != null) {
/* 274 */         myStr.append("\n\t\t\t - annotations: ");
/* 275 */         for (AnnotationInstance ai : this.annotations) {
/* 276 */           myStr.append("\n\t\t\t  - " + ai.getAnnotation().getFullName());
/* 277 */           for (i = 0; i < ai.getPropertyValuePairs().size(); i++) {
/* 278 */             myStr.append("\n\t\t\t\t");
/* 279 */             myStr.append(((AnnotationPropertyValuePair)ai.getPropertyValuePairs().get(i)).getAp().getName());
/* 280 */             myStr.append(" = ").append(((AnnotationPropertyValuePair)ai.getPropertyValuePairs().get(i)).getValue());
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } else {
/* 285 */       myStr.append("\n");
/* 286 */     }  return new String(myStr);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean restore() {
/* 292 */     if (super.restore()) {
/* 293 */       if (this.initializers != null)
/* 294 */         this.initializers.restore(); 
/* 295 */       if (this.annotations != null)
/* 296 */         this.annotations.restore(); 
/* 297 */       return true;
/*     */     } 
/* 299 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\Class.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */