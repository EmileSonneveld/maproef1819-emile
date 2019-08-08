/*     */ package lrg.memoria.core;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Variable
/*     */   extends NamedModelElement
/*     */   implements Scopable
/*     */ {
/*  10 */   public static String UNKNOWN_VARIABLE_NAME = "unknown_variable";
/*     */   
/*  12 */   public static Variable getUnknownVariable() { return ModelElementsRepository.getCurrentModelElementsRepository().getUnknownVariable(); }
/*     */ 
/*     */   
/*     */   protected Type type;
/*     */   
/*     */   protected Scope scope;
/*     */   
/*  19 */   private Location location = Location.getUnknownLocation();
/*     */   
/*     */   private ModelElementList<Access> accessesList;
/*     */   private boolean isFinal = false;
/*     */   
/*  24 */   protected Variable(String name) { this(name, Class.getUnknownClass()); }
/*     */ 
/*     */   
/*     */   protected Variable(Variable var) {
/*  28 */     super(var);
/*  29 */     this.type = var.type;
/*  30 */     this.accessesList = var.accessesList;
/*  31 */     this.location = var.location;
/*  32 */     this.scope = var.scope;
/*  33 */     this.isFinal = var.isFinal;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Variable(String name, Type dataType) {
/*  38 */     super(name);
/*  39 */     this.type = dataType;
/*     */   }
/*     */ 
/*     */   
/*  43 */   public void setType(Type type) { this.type = type; }
/*     */ 
/*     */ 
/*     */   
/*  47 */   public void setScope(Scope scope) { this.scope = scope; }
/*     */ 
/*     */ 
/*     */   
/*  51 */   public String getFullName() { return String.valueOf(this.scope.getFullName()) + "." + getName(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   public Type getType() { return this.type; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   public void setFinal() { this.isFinal = true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAccess(Access access) {
/*  74 */     if (this.accessesList == null)
/*  75 */       this.accessesList = new ModelElementList(); 
/*  76 */     this.accessesList.add(access);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ModelElementList<Access> getAccessList() {
/*  83 */     if (this.accessesList == null)
/*  84 */       this.accessesList = new ModelElementList(); 
/*  85 */     return this.accessesList;
/*     */   }
/*     */ 
/*     */   
/*  89 */   public void setAccessesList(ModelElementList<Access> accesses) { this.accessesList = accesses; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   public boolean hasPrimitiveType() { return this.type instanceof Primitive; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 105 */   public boolean isFinal() { return this.isFinal; }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 124 */   public boolean isArray() { return getType() instanceof ArrayDecorator; }
/*     */ 
/*     */   
/*     */   public abstract void accept(ModelVisitor paramModelVisitor);
/*     */ 
/*     */   
/* 130 */   public void setLocation(Location location) { this.location = location; }
/*     */ 
/*     */ 
/*     */   
/* 134 */   public Location getLocation() { return this.location; }
/*     */ 
/*     */   
/*     */   boolean restore() {
/* 138 */     if (super.restore()) {
/* 139 */       if (this.accessesList != null)
/* 140 */         this.accessesList.restore(); 
/* 141 */       if (this.annotations != null)
/* 142 */         this.annotations.restore(); 
/* 143 */       return true;
/*     */     } 
/* 145 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 150 */     StringBuffer myStr = new StringBuffer("\t\t\t Variable: ");
/*     */     
/* 152 */     myStr.append(this.name).append("\n\t\t\t - type: ").append(getType().getName());
/* 153 */     myStr.append("\n\t\t\t - location: ").append(getLocation());
/* 154 */     myStr.append("\n\t\t\t - flags: ");
/* 155 */     if (isFinal()) {
/* 156 */       myStr.append("final, ");
/* 157 */     } else if (isFinal()) {
/* 158 */       int tmp = myStr.length();
/* 159 */       myStr.delete(tmp - 2, tmp);
/*     */     } 
/* 161 */     if (this.annotations != null) {
/* 162 */       myStr.append("\n\t\t\t - annotations: ");
/* 163 */       for (AnnotationInstance ai : this.annotations) {
/* 164 */         myStr.append("\n\t\t\t  -" + ai.getAnnotation().getFullName());
/* 165 */         for (int i = 0; i < ai.getPropertyValuePairs().size(); i++) {
/* 166 */           myStr.append("\n\t\t\t\t");
/* 167 */           myStr.append(((AnnotationPropertyValuePair)ai.getPropertyValuePairs().get(i)).getAp().getName());
/* 168 */           myStr.append(" = ").append(((AnnotationPropertyValuePair)ai.getPropertyValuePairs().get(i)).getValue());
/*     */         } 
/*     */       } 
/*     */     } 
/* 172 */     myStr.append("\n");
/* 173 */     return new String(myStr);
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\Variable.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */