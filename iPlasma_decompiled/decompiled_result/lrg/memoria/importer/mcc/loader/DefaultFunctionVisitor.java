/*     */ package lrg.memoria.importer.mcc.loader;
/*     */ 
/*     */ import lrg.memoria.core.Class;
/*     */ import lrg.memoria.core.DataAbstraction;
/*     */ import lrg.memoria.core.File;
/*     */ import lrg.memoria.core.Function;
/*     */ import lrg.memoria.core.FunctionBody;
/*     */ import lrg.memoria.core.FunctionPointer;
/*     */ import lrg.memoria.core.GlobalFunction;
/*     */ import lrg.memoria.core.Method;
/*     */ import lrg.memoria.core.Namespace;
/*     */ import lrg.memoria.core.Package;
/*     */ import lrg.memoria.core.PointerToFunction;
/*     */ import lrg.memoria.core.Type;
/*     */ import lrg.memoria.core.TypedefDecorator;
/*     */ import lrg.memoria.core.Union;
/*     */ import lrg.memoria.core.UnnamedNamespace;
/*     */ 
/*     */ public class DefaultFunctionVisitor
/*     */   extends DefaultVisitorRoot implements FunctionVisitor {
/*     */   private Integer id;
/*     */   private String name;
/*     */   private String kind;
/*  24 */   private int statute = 3; private String signature; private Type returnType; private Type typeScope; private Namespace namespaceScope; private int accessMode;
/*     */   private boolean isStatic;
/*     */   
/*  27 */   public void setId(Integer id) { this.id = id; }
/*     */   private boolean isVirtual;
/*     */   private boolean isConstructor;
/*     */   
/*  31 */   public void setName(String name) { this.name = name; }
/*     */   private boolean isGlobalFunction;
/*     */   private FunctionBody functionBody;
/*     */   
/*  35 */   public void setKind(String kind) { this.kind = kind; }
/*     */ 
/*     */ 
/*     */   
/*  39 */   public void setSignature(String signature) { this.signature = signature; }
/*     */ 
/*     */   
/*     */   public void setReturnType(String returnType) {
/*  43 */     if (returnType.indexOf("<NO_ONE>") >= 0) {
/*  44 */       this.isConstructor = true;
/*     */       return;
/*     */     } 
/*  47 */     this.isConstructor = false;
/*  48 */     if (returnType.indexOf("<ERROR>") < 0)
/*  49 */       this.returnType = Loader.getInstance().getType(new Integer(returnType)); 
/*  50 */     if (this.returnType == null) {
/*  51 */       this.returnType = Class.getUnknownClass();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setScopeId(String scopeId) {
/*  56 */     this.typeScope = null;
/*  57 */     if (scopeId.indexOf("<ERROR>") < 0 && scopeId.indexOf("<NO_ONE>") < 0)
/*  58 */       this.typeScope = Loader.getInstance().getType(new Integer(scopeId)); 
/*  59 */     if (this.typeScope == null) {
/*  60 */       this.typeScope = Class.getUnknownClass();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setNamespaceId(String nId) {
/*  65 */     if (nId.equals("NULL")) {
/*  66 */       this.isGlobalFunction = false;
/*     */       return;
/*     */     } 
/*  69 */     this.isGlobalFunction = true;
/*  70 */     if (nId.equals("<NO_ONE>")) {
/*  71 */       this.namespaceScope = Namespace.getAnonymousNamespace();
/*     */       return;
/*     */     } 
/*  74 */     this.namespaceScope = null;
/*  75 */     if (!nId.equals("<ERROR>"))
/*  76 */       this.namespaceScope = Loader.getInstance().getNamespace(new Integer(nId)); 
/*  77 */     if (this.namespaceScope == null) {
/*  78 */       this.namespaceScope = Namespace.getUnknownNamespace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setAccess(String access) {
/*  83 */     if (access.equals("NULL"))
/*     */       return; 
/*  85 */     if (access.equals("public"))
/*  86 */       this.accessMode = 1; 
/*  87 */     if (access.equals("private"))
/*  88 */       this.accessMode = 4; 
/*  89 */     if (access.equals("protected"))
/*  90 */       this.accessMode = 2; 
/*     */   }
/*     */   
/*     */   public void setIsStatic(String isSt) {
/*  94 */     if (isSt.indexOf("1") >= 0) {
/*  95 */       this.isStatic = true;
/*     */     } else {
/*  97 */       this.isStatic = false;
/*     */     } 
/*     */   }
/*     */   public void setIsVirtual(String isVt) {
/* 101 */     if (isVt.indexOf("1") >= 0) {
/* 102 */       this.isVirtual = true;
/*     */     } else {
/* 104 */       this.isVirtual = false;
/*     */     } 
/*     */   }
/*     */   public void setBodyId(String bId) {
/* 108 */     if (bId.equals("<ERROR>") || bId.equals("<INIT_NULL_BODY>") || 
/* 109 */       bId.equals("<ONLY_DECLARED>") || bId.equals("NULL")) {
/* 110 */       this.functionBody = null;
/*     */     } else {
/* 112 */       this.functionBody = Loader.getInstance().getBody(new Integer(bId));
/*     */     } 
/*     */   }
/*     */   public void addFunction() {
/* 116 */     Method method = null;
/* 117 */     if (this.kind.equals("pointer-to-function")) {
/* 118 */       method = addPointerToFunction();
/* 119 */     } else if (this.isConstructor) {
/* 120 */       method = addConstructor();
/* 121 */     } else if (this.isGlobalFunction) {
/* 122 */       method = addGlobalFunction();
/*     */     } else {
/* 124 */       method = addMethod();
/* 125 */     }  if (!this.isConstructor)
/* 126 */       method.setReturnType(this.returnType); 
/* 127 */     if (this.functionBody != null) {
/* 128 */       method.setFunctionBody(this.functionBody);
/* 129 */       method.setLocation(this.functionBody.getLocation());
/* 130 */       this.functionBody.setScope(method);
/*     */     } 
/* 132 */     Loader.getInstance().addFunction(this.id, method);
/*     */   }
/*     */   
/*     */   private Function addPointerToFunction() {
/* 136 */     PointerToFunction fp = new PointerToFunction(this.name);
/* 137 */     fp.setScope((FunctionPointer)this.typeScope);
/* 138 */     return fp;
/*     */   }
/*     */   
/*     */   private Method addMethod() {
/* 142 */     Method meth = new Method(this.name);
/* 143 */     setStatute();
/* 144 */     meth.setStatute(this.statute);
/* 145 */     meth.setAccessMode(this.accessMode);
/* 146 */     if (this.typeScope instanceof TypedefDecorator) this.typeScope = ((TypedefDecorator)this.typeScope).getDecoratedType(); 
/* 147 */     meth.setScope((DataAbstraction)this.typeScope);
/* 148 */     ((DataAbstraction)this.typeScope).addMethod(meth);
/* 149 */     if (this.isVirtual)
/* 150 */       meth.setVirtual(); 
/* 151 */     if (this.isStatic)
/* 152 */       meth.setStatic(); 
/* 153 */     return meth;
/*     */   }
/*     */ 
/*     */   
/*     */   private Function addGlobalFunction() {
/* 158 */     GlobalFunction gf = new GlobalFunction(this.name);
/* 159 */     setStatute();
/* 160 */     gf.setStatute(this.statute);
/* 161 */     if (this.functionBody != null) {
/* 162 */       Package currentPackage = Loader.getInstance().getPackageForBody(this.functionBody);
/* 163 */       gf.setPackage(currentPackage);
/* 164 */       currentPackage.addGlobalFunction(gf);
/*     */     } 
/* 166 */     if (this.isStatic) {
/*     */       File temp;
/* 168 */       if (this.functionBody != null) {
/* 169 */         temp = this.functionBody.getLocation().getFile();
/*     */       } else {
/* 171 */         temp = File.getUnknownFile();
/* 172 */       }  UnnamedNamespace unsp = Loader.getInstance().getUnnamedNamespace(temp.getFullName());
/* 173 */       gf.setScope(unsp);
/* 174 */       gf.setStatute(unsp.getStatute());
/* 175 */       unsp.addGlobalFunction(gf);
/* 176 */       gf.setStatic();
/*     */     } else {
/* 178 */       gf.setScope(this.namespaceScope);
/* 179 */       this.namespaceScope.addGlobalFunction(gf);
/*     */     } 
/* 181 */     return gf;
/*     */   }
/*     */   
/*     */   private Function addConstructor() {
/* 185 */     Method cons = addMethod();
/* 186 */     cons.setKindOf(1);
/* 187 */     return cons;
/*     */   }
/*     */   
/*     */   private void setStatute() {
/* 191 */     if (this.typeScope != null)
/* 192 */       if (this.typeScope != Class.getUnknownClass()) {
/* 193 */         if (this.typeScope instanceof Class)
/* 194 */           this.statute = ((DataAbstraction)this.typeScope).getStatute(); 
/* 195 */         if (this.typeScope instanceof Union) {
/* 196 */           this.statute = ((Union)this.typeScope).getStatute();
/*     */         }
/*     */       }
/* 199 */       else if (this.namespaceScope != null) {
/* 200 */         this.statute = this.namespaceScope.getStatute();
/*     */       }  
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\mcc\loader\DefaultFunctionVisitor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */