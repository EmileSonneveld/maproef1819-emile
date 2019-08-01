/*     */ package lrg.memoria.utils;
/*     */ import java.util.Iterator;
/*     */ import lrg.memoria.core.Body;
/*     */ import lrg.memoria.core.DataAbstraction;
/*     */ import lrg.memoria.core.Function;
/*     */ import lrg.memoria.core.FunctionBody;
/*     */ import lrg.memoria.core.GlobalFunction;
/*     */ import lrg.memoria.core.InitializerBody;
/*     */ import lrg.memoria.core.ModelElement;
/*     */ import lrg.memoria.core.ModelElementList;
/*     */ import lrg.memoria.core.Package;
/*     */ import lrg.memoria.core.Parameter;
/*     */ import lrg.memoria.core.System;
/*     */ import lrg.memoria.core.Type;
/*     */ 
/*     */ public class MEMORIABreadthIterator implements Iterator {
/*     */   private System currentSystem;
/*     */   private Iterator packagesIterator;
/*     */   private Iterator typesIterator;
/*     */   private Iterator attributesIterator;
/*     */   private Iterator globalFunctionsIterator;
/*     */   private Iterator methodsIterator;
/*     */   private Iterator initializerBodiesIterator;
/*     */   private Iterator bodiesIterator;
/*     */   private Iterator parametersIterator;
/*     */   private Iterator globalVariablesIterator;
/*     */   private Iterator localVariablesIterator;
/*     */   private Iterator accessesIterator;
/*     */   private Iterator callsIterator;
/*     */   private Iterator annotationIterator;
/*     */   
/*     */   public MEMORIABreadthIterator(System s) {
/*  33 */     this.currentSystem = s;
/*  34 */     this.packagesIterator = this.currentSystem.getPackages().iterator();
/*     */     
/*  36 */     this.typesList = new ModelElementList();
/*  37 */     this.typesIterator = this.typesList.iterator();
/*  38 */     this.methodsList = new ModelElementList();
/*  39 */     this.methodsIterator = this.methodsList.iterator();
/*  40 */     this.globalFunctionsList = new ModelElementList();
/*  41 */     this.globalFunctionsIterator = this.globalFunctionsList.iterator();
/*  42 */     this.bodiesList = new ModelElementList();
/*  43 */     this.bodiesIterator = this.bodiesList.iterator();
/*  44 */     this.attributesList = new ModelElementList();
/*  45 */     this.attributesIterator = this.attributesList.iterator();
/*  46 */     this.initializerBodiesList = new ModelElementList();
/*  47 */     this.initializerBodiesIterator = this.initializerBodiesList.iterator();
/*  48 */     this.parametersList = new ModelElementList();
/*  49 */     this.parametersIterator = this.parametersList.iterator();
/*  50 */     this.localVariablesList = new ModelElementList();
/*  51 */     this.localVariablesIterator = this.localVariablesList.iterator();
/*  52 */     this.globalVariablesList = new ModelElementList();
/*  53 */     this.globalVariablesIterator = this.globalVariablesList.iterator();
/*  54 */     this.accessesList = new ModelElementList();
/*  55 */     this.accessesIterator = this.accessesList.iterator();
/*  56 */     this.callsList = new ModelElementList();
/*  57 */     this.callsIterator = this.callsList.iterator();
/*     */     
/*  59 */     this.annotationList = new ModelElementList();
/*  60 */     this.annotationIterator = this.annotationList.iterator();
/*  61 */     this.annotationInstanceList = new ModelElementList();
/*  62 */     this.annotationInstanceIterator = this.annotationInstanceList.iterator();
/*     */   }
/*     */   private Iterator annotationInstanceIterator; private ModelElementList typesList; private ModelElementList globalFunctionsList; private ModelElementList methodsList; private ModelElementList attributesList; private ModelElementList initializerBodiesList; private ModelElementList bodiesList; private ModelElementList parametersList; private ModelElementList globalVariablesList; private ModelElementList localVariablesList; private ModelElementList accessesList; private ModelElementList callsList; private ModelElementList annotationList; private ModelElementList annotationInstanceList;
/*     */   public boolean hasNext() {
/*  66 */     if (!this.packagesIterator.hasNext())
/*     */     {
/*  68 */       if (!this.typesIterator.hasNext() && !this.globalVariablesIterator.hasNext() && 
/*  69 */         !this.globalFunctionsIterator.hasNext() && !this.initializerBodiesIterator.hasNext() && 
/*  70 */         !this.attributesIterator.hasNext() && !this.methodsIterator.hasNext() && 
/*  71 */         !this.parametersIterator.hasNext() && !this.bodiesIterator.hasNext() && !this.localVariablesIterator.hasNext() && 
/*  72 */         !this.accessesIterator.hasNext() && !this.callsIterator.hasNext() && 
/*  73 */         !this.annotationIterator.hasNext() && !this.annotationInstanceIterator.hasNext()) return false;  } 
/*     */     return true;
/*     */   }
/*     */   public ModelElement next() {
/*  77 */     if (this.packagesIterator.hasNext()) {
/*  78 */       return nextPackage();
/*     */     }
/*     */     
/*  81 */     if (this.annotationIterator.hasNext())
/*  82 */       return nextAnnotation(); 
/*  83 */     if (this.typesIterator.hasNext()) {
/*  84 */       return nextType();
/*     */     }
/*  86 */     if (this.globalVariablesIterator.hasNext())
/*  87 */       return nextGlobalVariable(); 
/*  88 */     if (this.globalFunctionsIterator.hasNext())
/*  89 */       return nextGlobalFunction(); 
/*  90 */     if (this.initializerBodiesIterator.hasNext())
/*  91 */       return nextInitializerBody(); 
/*  92 */     if (this.attributesIterator.hasNext())
/*  93 */       return nextAttribute(); 
/*  94 */     if (this.methodsIterator.hasNext())
/*  95 */       return nextMethod(); 
/*  96 */     if (this.parametersIterator.hasNext())
/*  97 */       return nextParameter(); 
/*  98 */     if (this.bodiesIterator.hasNext())
/*  99 */       return nextBody(); 
/* 100 */     if (this.localVariablesIterator.hasNext()) {
/* 101 */       return nextLocalVar();
/*     */     }
/* 103 */     if (this.annotationInstanceIterator.hasNext()) {
/* 104 */       return nextAnnotationInstance();
/*     */     }
/* 106 */     if (this.accessesIterator.hasNext())
/* 107 */       return nextAccess(); 
/* 108 */     if (this.callsIterator.hasNext())
/* 109 */       return nextCall(); 
/* 110 */     throw new NoSuchElementException();
/*     */   }
/*     */ 
/*     */   
/* 114 */   private ModelElement nextAnnotationInstance() { return (ModelElement)this.annotationInstanceIterator.next(); }
/*     */ 
/*     */ 
/*     */   
/* 118 */   private ModelElement nextAnnotation() { return (ModelElement)this.annotationIterator.next(); }
/*     */ 
/*     */ 
/*     */   
/* 122 */   public void remove() { throw new UnsupportedOperationException(); }
/*     */ 
/*     */   
/*     */   private Package nextPackage() {
/* 126 */     Package pack = (Package)this.packagesIterator.next();
/* 127 */     this.typesList.addAll(pack.getAllTypesList());
/* 128 */     this.globalFunctionsList.addAll(pack.getGlobalFunctionsList());
/* 129 */     this.globalVariablesList.addAll(pack.getGlobalVariablesList());
/* 130 */     this.annotationList.addAll(pack.getAnnotationsList());
/* 131 */     if (!this.packagesIterator.hasNext()) {
/* 132 */       this.typesList.addAll(Namespace.getGlobalNamespace().getTypesList());
/* 133 */       this.typesIterator = this.typesList.iterator();
/* 134 */       this.annotationIterator = this.annotationList.iterator();
/* 135 */       this.globalVariablesIterator = this.globalVariablesList.iterator();
/* 136 */       this.globalFunctionsIterator = this.globalFunctionsList.iterator();
/*     */     } 
/* 138 */     return pack;
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
/*     */   private ModelElement nextType() {
/* 166 */     Type currentType = (Type)this.typesIterator.next();
/* 167 */     if (currentType instanceof DataAbstraction) {
/* 168 */       DataAbstraction currentDataAbstraction = (DataAbstraction)currentType;
/* 169 */       this.methodsList.addAll(currentDataAbstraction.getMethodList());
/* 170 */       this.attributesList.addAll(currentDataAbstraction.getAttributeList());
/* 171 */       this.annotationInstanceList.addAll(currentDataAbstraction.getAnnotations());
/* 172 */       if (currentDataAbstraction instanceof Class)
/* 173 */         this.initializerBodiesList.addAll(((Class)currentDataAbstraction).getInitializerList()); 
/*     */     } 
/* 175 */     if (currentType instanceof FunctionPointer)
/* 176 */       this.methodsList.add(((FunctionPointer)currentType).getFunctionSide()); 
/* 177 */     if (!this.typesIterator.hasNext()) {
/* 178 */       this.attributesIterator = this.attributesList.iterator();
/* 179 */       this.methodsIterator = this.methodsList.iterator();
/* 180 */       this.initializerBodiesIterator = this.initializerBodiesList.iterator();
/* 181 */       this.annotationInstanceIterator = this.annotationInstanceList.iterator();
/*     */     } 
/* 183 */     return (ModelElement)currentType;
/*     */   }
/*     */ 
/*     */   
/* 187 */   private GlobalVariable nextGlobalVariable() { return (GlobalVariable)this.globalVariablesIterator.next(); }
/*     */ 
/*     */   
/*     */   private GlobalFunction nextGlobalFunction() {
/* 191 */     GlobalFunction gf = (GlobalFunction)this.globalFunctionsIterator.next();
/* 192 */     this.parametersList.addAll(gf.getParameterList());
/* 193 */     FunctionBody functionBody = gf.getBody();
/* 194 */     if (functionBody != null)
/* 195 */       this.bodiesList.add(functionBody); 
/* 196 */     return gf;
/*     */   }
/*     */   
/*     */   private InitializerBody nextInitializerBody() {
/* 200 */     InitializerBody ib = (InitializerBody)this.initializerBodiesIterator.next();
/* 201 */     this.localVariablesList.addAll(ib.getLocalVarList());
/* 202 */     this.accessesList.addAll(ib.getAccessList());
/* 203 */     this.callsList.addAll(ib.getCallList());
/* 204 */     return ib;
/*     */   }
/*     */ 
/*     */   
/* 208 */   private Attribute nextAttribute() { return (Attribute)this.attributesIterator.next(); }
/*     */ 
/*     */   
/*     */   private Function nextMethod() {
/* 212 */     Function method = (Function)this.methodsIterator.next();
/* 213 */     this.parametersList.addAll(method.getParameterList());
/* 214 */     this.annotationInstanceList.addAll(method.getAnnotations());
/* 215 */     FunctionBody functionBody = method.getBody();
/* 216 */     if (functionBody != null)
/* 217 */       this.bodiesList.add(functionBody); 
/* 218 */     if (!this.methodsIterator.hasNext()) {
/* 219 */       this.parametersIterator = this.parametersList.iterator();
/* 220 */       this.bodiesIterator = this.bodiesList.iterator();
/* 221 */       this.annotationInstanceIterator = this.annotationInstanceList.iterator();
/*     */     } 
/* 223 */     return method;
/*     */   }
/*     */ 
/*     */   
/* 227 */   private Parameter nextParameter() { return (Parameter)this.parametersIterator.next(); }
/*     */ 
/*     */   
/*     */   private Body nextBody() {
/* 231 */     Body body = (Body)this.bodiesIterator.next();
/* 232 */     this.localVariablesList.addAll(body.getLocalVarList());
/* 233 */     this.accessesList.addAll(body.getAccessList());
/* 234 */     this.callsList.addAll(body.getCallList());
/* 235 */     if (!this.bodiesIterator.hasNext()) {
/* 236 */       this.localVariablesIterator = this.localVariablesList.iterator();
/* 237 */       this.accessesIterator = this.accessesList.iterator();
/* 238 */       this.callsIterator = this.callsList.iterator();
/*     */     } 
/* 240 */     return body;
/*     */   }
/*     */ 
/*     */   
/* 244 */   private LocalVariable nextLocalVar() { return (LocalVariable)this.localVariablesIterator.next(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 249 */   private Access nextAccess() { return (Access)this.accessesIterator.next(); }
/*     */ 
/*     */ 
/*     */   
/* 253 */   private Call nextCall() { return (Call)this.callsIterator.next(); }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memori\\utils\MEMORIABreadthIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */