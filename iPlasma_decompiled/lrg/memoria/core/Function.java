/*     */ package lrg.memoria.core;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public abstract class Function
/*     */   extends NamedModelElement implements Scopable, Scope {
/*     */   public static final String unknownFunctionName = "unknown_function";
/*     */   
/*  10 */   public static Function getUnknownFunction() { return ModelElementsRepository.getCurrentModelElementsRepository().getUnknownFunction(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  15 */   protected Location location = Location.getUnknownLocation();
/*     */   protected ModelElementList<Parameter> parametersList;
/*  17 */   protected Type returnType = Class.getUnknownClass();
/*     */   
/*     */   protected FunctionBody body;
/*     */   protected ModelElementList<Call> callsList;
/*     */   private Scope scope;
/*     */   private Package thePackage;
/*     */   
/*  24 */   public Function(String name) { super(name); }
/*     */ 
/*     */   
/*     */   protected Function(Function func) {
/*  28 */     super(func);
/*  29 */     this.location = func.location;
/*  30 */     this.scope = func.scope;
/*  31 */     this.returnType = func.returnType;
/*  32 */     this.body = func.body;
/*  33 */     this.parametersList = func.parametersList;
/*  34 */     this.callsList = func.callsList;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFullName() {
/*  39 */     fullName = String.valueOf(this.scope.getFullName()) + "." + this.name + "(";
/*  40 */     int j = 0;
/*  41 */     if (ModelElementsRepository.getCurrentModelElementsRepository().getUnknownFunction() != this && 
/*  42 */       ModelElementsRepository.getCurrentModelElementsRepository().getUnknownMethod() != this && this.parametersList != null) {
/*  43 */       for (Parameter param : this.parametersList) {
/*  44 */         if (j > 0)
/*  45 */           fullName = String.valueOf(fullName) + ","; 
/*  46 */         fullName = String.valueOf(fullName) + param.getType().getFullName();
/*  47 */         j++;
/*     */       } 
/*     */     }
/*     */     
/*  51 */     return String.valueOf(fullName) + ")";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  56 */   public void setLocation(Location loc) { this.location = loc; }
/*     */ 
/*     */ 
/*     */   
/*  60 */   public Location getLocation() { return this.location; }
/*     */ 
/*     */ 
/*     */   
/*  64 */   public Scope getScope() { return this.scope; }
/*     */ 
/*     */ 
/*     */   
/*  68 */   public void setScope(Scope scope) { this.scope = scope; }
/*     */ 
/*     */   
/*     */   public boolean isEqualSignature(Function otherFunction) {
/*  72 */     if (getName().compareTo(otherFunction.getName()) != 0) return false;
/*     */     
/*  74 */     ModelElementList<Parameter> thisParameters = getParameterList();
/*  75 */     ModelElementList<Parameter> othersParameters = otherFunction.getParameterList();
/*     */     
/*  77 */     if (thisParameters.size() != othersParameters.size()) return false;
/*     */     
/*  79 */     ArrayList paramTypes = new ArrayList();
/*  80 */     ArrayList paramTypesOfOtherMethod = new ArrayList();
/*     */     
/*  82 */     for (Iterator it = othersParameters.iterator(); it.hasNext();) {
/*  83 */       paramTypes.add(((Parameter)it.next()).getType());
/*     */     }
/*  85 */     for (Iterator it = othersParameters.iterator(); it.hasNext();) {
/*  86 */       paramTypesOfOtherMethod.add(((Parameter)it.next()).getType());
/*     */     }
/*     */     
/*  89 */     return paramTypes.containsAll(paramTypesOfOtherMethod);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addScopedElement(Scopable element) {
/*  94 */     if (element instanceof Parameter)
/*  95 */       addParameter((Parameter)element); 
/*  96 */     if (element instanceof Body)
/*  97 */       setFunctionBody((FunctionBody)element); 
/*     */   }
/*     */   
/*     */   public ModelElementList getScopedElements() {
/* 101 */     ModelElementList scopedElements = new ModelElementList();
/* 102 */     scopedElements.add(this.body);
/* 103 */     return scopedElements;
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
/* 116 */   public void setFunctionBody(FunctionBody body) { this.body = body; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addParameter(Parameter p) {
/* 123 */     if (this.parametersList == null)
/* 124 */       this.parametersList = new ModelElementList(); 
/* 125 */     this.parametersList.add(p);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ModelElementList<Parameter> getParameterList() {
/* 132 */     if (this.parametersList == null)
/* 133 */       this.parametersList = new ModelElementList(); 
/* 134 */     return this.parametersList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 141 */   public int getLine() { return this.location.getStartLine(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 148 */   public void setReturnType(Type returnType) { this.returnType = returnType; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 155 */   public Type getReturnType() { return this.returnType; }
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
/*     */   public FunctionBody getBody() {
/* 168 */     if (this.body == null)
/* 169 */       this.body = (FunctionBody)Body.getUnkonwnBody(); 
/* 170 */     return this.body;
/*     */   }
/*     */   
/*     */   public void addCall(Call c) {
/* 174 */     if (this.callsList == null)
/* 175 */       this.callsList = new ModelElementList(); 
/* 176 */     this.callsList.add(c);
/*     */   }
/*     */   
/*     */   public ModelElementList<Call> getCallList() {
/* 180 */     if (this.callsList == null)
/* 181 */       this.callsList = new ModelElementList(); 
/* 182 */     return this.callsList;
/*     */   }
/*     */ 
/*     */   
/* 186 */   public void setCallList(ModelElementList<Call> calls) { this.callsList = calls; }
/*     */ 
/*     */   
/*     */   boolean restore() {
/* 190 */     if (super.restore()) {
/* 191 */       if (this.callsList != null)
/* 192 */         this.callsList.restore(); 
/* 193 */       if (this.parametersList != null)
/* 194 */         this.parametersList.restore(); 
/* 195 */       if (this.body != null)
/* 196 */         this.body.restore(); 
/* 197 */       if (this.annotations != null)
/* 198 */         this.annotations.restore(); 
/* 199 */       return true;
/*     */     } 
/* 201 */     return false;
/*     */   }
/*     */ 
/*     */   
/* 205 */   public void setPackage(Package thePackage) { this.thePackage = thePackage; }
/*     */ 
/*     */ 
/*     */   
/* 209 */   public Package getPackage() { return this.thePackage; }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\Function.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */