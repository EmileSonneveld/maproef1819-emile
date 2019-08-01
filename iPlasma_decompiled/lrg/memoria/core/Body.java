/*     */ package lrg.memoria.core;
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
/*     */ public abstract class Body
/*     */   extends ModelElement
/*     */   implements Scopable, Scope
/*     */ {
/*     */   private String sourceCode;
/*     */   private Scope scope;
/*     */   private CodeStripe mainStripe;
/*     */   private int numberOfStatements;
/*     */   private int numberOfLines;
/*     */   private int numberOfComments;
/*     */   private int numberOfDecisions;
/*     */   private int numberOfLoops;
/*     */   private int numberOfExits;
/*     */   private int numberOfExceptions;
/*     */   private int maxNestingLevel;
/*     */   private int cyclomaticNumber;
/*     */   
/*  31 */   public static Body getUnkonwnBody() { return ModelElementsRepository.getCurrentModelElementsRepository().getUnknownBody(); } public Body(Scope scope) { this.mainStripe = null; this.numberOfStatements = 0; this.numberOfLines = 0; this.numberOfComments = 0; this.numberOfDecisions = 0; this.numberOfLoops = 0;
/*     */     this.numberOfExits = 0;
/*     */     this.maxNestingLevel = 0;
/*     */     this.cyclomaticNumber = 0;
/*  35 */     this.scope = scope; }
/*     */ 
/*     */   
/*     */   private void checkMainStripe() {
/*  39 */     if (this.mainStripe == null) {
/*  40 */       ((NamedModelElement)getScope()).getStatute();
/*     */ 
/*     */       
/*  43 */       this.mainStripe = new CodeStripe(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addScopedElement(Scopable element) {
/*  51 */     checkMainStripe();
/*  52 */     this.mainStripe.addScopedElement(element);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ModelElementList getScopedElements() {
/*  59 */     ModelElementList scoped = new ModelElementList();
/*  60 */     scoped.addAll(getLocalVarList());
/*  61 */     scoped.addAll(getInnerTypesList());
/*  62 */     return scoped;
/*     */   }
/*     */ 
/*     */   
/*  66 */   public Scope getScope() { return this.scope; }
/*     */ 
/*     */ 
/*     */   
/*  70 */   public void setScope(Scope scope) { this.scope = scope; }
/*     */ 
/*     */ 
/*     */   
/*  74 */   public String getName() { return this.scope.getName(); }
/*     */ 
/*     */ 
/*     */   
/*  78 */   public String getFullName() { return this.scope.getFullName(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addLocalVar(LocalVariable var) {
/*  86 */     checkMainStripe();
/*  87 */     this.mainStripe.addLocalVar(var);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addInnerType(Type type) {
/*  95 */     checkMainStripe();
/*  96 */     this.mainStripe.addInnerType(type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCall(Call c) {
/* 104 */     checkMainStripe();
/* 105 */     this.mainStripe.addCall(c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAccess(Access a) {
/* 114 */     checkMainStripe();
/* 115 */     this.mainStripe.addAccess(a);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ModelElementList<LocalVariable> getLocalVarList() {
/* 123 */     checkMainStripe();
/* 124 */     return this.mainStripe.flattenLocalVariables();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ModelElementList<Call> getCallList() {
/* 132 */     checkMainStripe();
/* 133 */     return this.mainStripe.flattenCalls();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ModelElementList<Access> getAccessList() {
/* 142 */     checkMainStripe();
/* 143 */     return this.mainStripe.flattenAccesses();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ModelElementList<Type> getInnerTypesList() {
/* 150 */     checkMainStripe();
/* 151 */     return this.mainStripe.flattenInnerTypes();
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
/* 162 */   public void setNumberOfStatements(int n) { this.numberOfStatements = n; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 170 */   public void setNumberOfLines(int n) { this.numberOfLines = n; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 178 */   public void setNumberOfComments(int n) { this.numberOfComments = n; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 186 */   public void setNumberOfDecisions(int n) { this.numberOfDecisions = n; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 194 */   public void setNumberOfLoops(int n) { this.numberOfLoops = n; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 202 */   public void setNumberOfExits(int n) { this.numberOfExits = n; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 212 */   public void setNumberOfExceptions(int n) { this.numberOfExceptions = n; }
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
/* 227 */   public void setCyclomaticNumber(int n) { this.cyclomaticNumber = n; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 235 */   public void setMaxNestingLevel(int n) { this.maxNestingLevel = n; }
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
/* 247 */   public int getNumberOfStatements() { return this.numberOfStatements; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 256 */   public int getNumberOfLines() { return this.numberOfLines; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 265 */   public int getNumberOfComments() { return this.numberOfComments; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 275 */   public int getNumberOfExceptions() { return this.numberOfExceptions; }
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
/* 290 */   public int getCyclomaticNumber() { return this.cyclomaticNumber; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 298 */   public int getNumberOfDecisions() { return this.numberOfDecisions; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 306 */   public int getNumberOfLoops() { return this.numberOfLoops; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 315 */   public int getNumberOfExits() { return this.numberOfExits; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 324 */   public int getMaxNestingLevel() { return this.maxNestingLevel; }
/*     */ 
/*     */   
/*     */   public abstract void accept(ModelVisitor paramModelVisitor);
/*     */   
/*     */   public String getSourceCode() {
/* 330 */     checkMainStripe();
/* 331 */     return this.mainStripe.getSourceCode();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 336 */   public void setSourceCode(String sourceCode) { this.sourceCode = sourceCode; }
/*     */ 
/*     */ 
/*     */   
/* 340 */   public CodeStripe getCodeStripe() { return this.mainStripe; }
/*     */ 
/*     */ 
/*     */   
/* 344 */   public void setCodeStripe(CodeStripe stripe) { this.mainStripe = stripe; }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 349 */     StringBuffer myStr = new StringBuffer();
/* 350 */     checkMainStripe();
/*     */     
/* 352 */     if (this.mainStripe.flattenLocalVariables().size() > 0) {
/* 353 */       myStr.append("\n\t\t\t\t - local variables: ");
/* 354 */       for (int i = 0; i < this.mainStripe.flattenLocalVariables().size(); i++) {
/*     */         
/* 356 */         LocalVariable var = (LocalVariable)this.mainStripe.flattenLocalVariables().get(i);
/* 357 */         myStr.append(var.getName()).append("(" + var.getType().getName());
/* 358 */         myStr.append(")").append(", ");
/*     */       } 
/* 360 */       if (i > 0) {
/* 361 */         int tmp = myStr.length();
/* 362 */         myStr.delete(tmp - 2, tmp).append(".");
/*     */       } 
/*     */     } 
/* 365 */     myStr.append("\n\t\t\t\t - accesses: "); int i;
/* 366 */     for (i = 0; i < this.mainStripe.flattenAccesses().size(); i++)
/* 367 */       myStr.append((Access)this.mainStripe.flattenAccesses().get(i)).append(", "); 
/* 368 */     if (i > 0) {
/* 369 */       int tmp = myStr.length();
/* 370 */       myStr.delete(tmp - 2, tmp).append(".");
/*     */     } 
/* 372 */     myStr.append("\n\t\t\t\t - calls: ");
/* 373 */     for (i = 0; i < this.mainStripe.flattenCalls().size(); i++)
/* 374 */       myStr.append((Call)this.mainStripe.flattenCalls().get(i)).append(", "); 
/* 375 */     if (i > 0) {
/* 376 */       int tmp = myStr.length();
/* 377 */       myStr.delete(tmp - 2, tmp).append(".");
/*     */     } 
/* 379 */     myStr.append("\n\t\t\t\t - metrics:");
/* 380 */     myStr.append("\n\t\t\t\t\t - Cyclomatic number: " + this.cyclomaticNumber);
/* 381 */     myStr.append("\n\t\t\t\t\t - Number of loops: " + this.numberOfLoops);
/* 382 */     myStr.append("\n\t\t\t\t\t - Number of exceptions: " + this.numberOfExceptions);
/* 383 */     myStr.append("\n\t\t\t\t\t - Number of decisions: " + this.numberOfDecisions);
/* 384 */     myStr.append("\n\t\t\t\t\t - Number of lines: " + this.numberOfLines);
/* 385 */     myStr.append("\n\t\t\t\t\t - Number of comments: " + this.numberOfComments);
/* 386 */     myStr.append("\n\t\t\t\t\t - Number of statements: " + this.numberOfStatements);
/* 387 */     myStr.append("\n\t\t\t\t\t - Number of exits: " + this.numberOfExits);
/* 388 */     myStr.append("\n\t\t\t\t\t - Max nesting level: " + this.maxNestingLevel);
/* 389 */     return new String(myStr);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Location getLocation() {
/* 396 */     checkMainStripe();
/* 397 */     return this.mainStripe.getLocation();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLocation(Location l) {
/* 404 */     checkMainStripe();
/* 405 */     this.mainStripe.setLocation(l);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean restore() {
/* 411 */     if (super.restore()) {
/* 412 */       if (this.mainStripe != null)
/* 413 */         this.mainStripe.restore(); 
/* 414 */       return true;
/*     */     } 
/* 416 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\Body.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */