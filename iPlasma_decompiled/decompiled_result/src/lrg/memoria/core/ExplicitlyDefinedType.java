/*     */ package lrg.memoria.core;
/*     */ 
/*     */ public abstract class ExplicitlyDefinedType extends NamedModelElement implements Type {
/*   4 */   private Location location = Location.getUnknownLocation();
/*   5 */   private Package containingPackage = Package.getUnknownPackage();
/*   6 */   private Scope scope = Namespace.getUnknownNamespace();
/*     */   
/*     */   protected ExplicitlyDefinedType(ExplicitlyDefinedType st) {
/*   9 */     super(st);
/*  10 */     this.containingPackage = st.containingPackage;
/*  11 */     this.scope = st.scope;
/*  12 */     this.location = st.location;
/*     */   }
/*     */ 
/*     */   
/*  16 */   public ExplicitlyDefinedType(String name) { super(name); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  23 */   public void setLocation(Location loc) { this.location = loc; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  30 */   public Location getLocation() { return this.location; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  37 */   public void setPackage(Package pack) { this.containingPackage = pack; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  45 */   public Package getPackage() { return this.containingPackage; }
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
/*  56 */   public void setScope(Scope scope) { this.scope = scope; }
/*     */ 
/*     */   
/*     */   public Scope getScope() {
/*  60 */     if (this.scope instanceof CodeStripe)
/*  61 */       return ((CodeStripe)this.scope).getParentBody(); 
/*  62 */     return this.scope;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFullName() {
/*  69 */     if (this.scope instanceof Namespace) {
/*  70 */       return String.valueOf(this.scope.getFullName()) + "." + this.name;
/*     */     }
/*  72 */     if (this.scope instanceof CodeStripe) {
/*  73 */       Body scp = ((CodeStripe)this.scope).getParentBody();
/*  74 */       if (scp instanceof FunctionBody) {
/*  75 */         return String.valueOf(scp.getScope().getScope().getFullName()) + "$" + this.name;
/*     */       }
/*  77 */       if (scp instanceof InitializerBody)
/*  78 */         return String.valueOf(scp.getScope().getFullName()) + "$" + this.name; 
/*     */     } 
/*  80 */     return String.valueOf(this.scope.getFullName()) + "$" + this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   public String getName() { return this.name; }
/*     */ 
/*     */   
/*     */   public ModelElementList<ExplicitlyDefinedType> getContainedClasses() {
/*  91 */     ModelElementList<ExplicitlyDefinedType> containedClasses = new ModelElementList<ExplicitlyDefinedType>();
/*  92 */     ModelElementList<Type> typesList = getPackage().getAllTypesList();
/*  93 */     for (Type currentType : typesList) {
/*  94 */       if (currentType instanceof Class) {
/*  95 */         Scope scope = ((DataAbstraction)currentType).getScope();
/*  96 */         while (!(scope instanceof Namespace)) {
/*  97 */           if (scope == this)
/*  98 */             containedClasses.add((DataAbstraction)currentType); 
/*  99 */           scope = scope.getScope();
/*     */         } 
/*     */       } 
/*     */     } 
/* 103 */     return containedClasses;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\ExplicitlyDefinedType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */