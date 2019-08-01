/*     */ package lrg.memoria.utils;
/*     */ import lrg.memoria.core.Attribute;
/*     */ import lrg.memoria.core.Class;
/*     */ import lrg.memoria.core.Function;
/*     */ import lrg.memoria.core.GlobalVariable;
/*     */ import lrg.memoria.core.LocalVariable;
/*     */ import lrg.memoria.core.ModelElementList;
/*     */ import lrg.memoria.core.Namespace;
/*     */ import lrg.memoria.core.Package;
/*     */ import lrg.memoria.core.Parameter;
/*     */ import lrg.memoria.core.System;
/*     */ import lrg.memoria.core.Type;
/*     */ import lrg.memoria.core.UnnamedNamespace;
/*     */ import lrg.memoria.core.Variable;
/*     */ 
/*     */ public class MEMORIAFacade {
/*  17 */   public MEMORIAFacade(System aSystem) { this.currentSystem = aSystem; }
/*     */   protected System currentSystem;
/*     */   protected MEMORIAFacade() {}
/*     */   public MEMORIAFacade(String pathList) {
/*     */     try {
/*  22 */       System.out.println("Loading the model from: " + pathList);
/*  23 */       JavaModelLoader model = new JavaModelLoader(pathList, null, null);
/*  24 */       this.currentSystem = model.getSystem();
/*  25 */     } catch (Exception e) {
/*  26 */       System.err.println(e);
/*  27 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*  32 */   public System getSystem() { return this.currentSystem; }
/*     */ 
/*     */ 
/*     */   
/*     */   public Package getPackageNamed(String packageName) {
/*  37 */     ModelElementList modelElementList = this.currentSystem.getPackages();
/*  38 */     for (int i = 0; i < modelElementList.size(); i++) {
/*  39 */       Package currentPackage = (Package)modelElementList.get(i);
/*  40 */       if (currentPackage.getName().equals(packageName))
/*  41 */         return currentPackage; 
/*     */     } 
/*  43 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Namespace getNamespaceNamed(String namespaceName) {
/*  48 */     ModelElementList modelElementList = this.currentSystem.getNamespaces();
/*  49 */     for (int i = 0; i < modelElementList.size(); i++) {
/*  50 */       Namespace currentNamespace = (Namespace)modelElementList.get(i);
/*  51 */       if (currentNamespace.getName().equals(namespaceName))
/*  52 */         return currentNamespace; 
/*     */     } 
/*  54 */     return null;
/*     */   }
/*     */   
/*     */   public UnnamedNamespace getUnnamedNamespaceFromFileNamed(String fileName) {
/*  58 */     ModelElementList<UnnamedNamespace> unspList = this.currentSystem.getUnnamedNamespaces();
/*  59 */     for (UnnamedNamespace currentNsp : unspList) {
/*  60 */       if (currentNsp.getFullName().equals(fileName))
/*  61 */         return currentNsp; 
/*     */     } 
/*  63 */     return null;
/*     */   }
/*     */   
/*     */   private Namespace searchNamespaceFromName(String name) {
/*  67 */     int index = name.lastIndexOf(".");
/*  68 */     if (index < 0)
/*  69 */       return null; 
/*  70 */     String nspName = name.substring(0, index);
/*  71 */     Namespace currentNamespace = getNamespaceNamed(nspName);
/*  72 */     if (currentNamespace != null) {
/*  73 */       return currentNamespace;
/*     */     }
/*  75 */     return searchNamespaceFromName(nspName);
/*     */   }
/*     */ 
/*     */   
/*  79 */   public Class getClassNamed(String classFullName) { return (Class)getTypeNamed(classFullName); }
/*     */ 
/*     */   
/*     */   public Type getTypeNamed(String typeFullName) {
/*  83 */     Namespace currentNamespace = searchNamespaceFromName(typeFullName);
/*  84 */     if (currentNamespace == null)
/*  85 */       return null; 
/*  86 */     ModelElementList modelElementList = currentNamespace.getAllTypesList();
/*     */     
/*  88 */     for (int i = 0; i < modelElementList.size(); i++) {
/*  89 */       Type currentType = (Type)modelElementList.get(i);
/*  90 */       if (currentType.getFullName().equals(typeFullName))
/*  91 */         return currentType; 
/*     */     } 
/*  93 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   public Method getMethodNamed(String methodFullName) { return (Method)getFunctionNamed(methodFullName); }
/*     */   
/*     */   public Function getFunctionNamed(String functionFullName) {
/*     */     ModelElementList modelElementList;
/* 104 */     int index = functionFullName.lastIndexOf("(");
/* 105 */     String temp = functionFullName.substring(0, index);
/* 106 */     int index1 = temp.lastIndexOf(".");
/* 107 */     String fullScopeName = temp.substring(0, index1);
/* 108 */     DataAbstraction currentClass = (DataAbstraction)getTypeNamed(fullScopeName);
/*     */     
/* 110 */     if (currentClass != null)
/* 111 */     { modelElementList = currentClass.getMethodList(); }
/*     */     
/* 113 */     else if (isFileName(fullScopeName))
/* 114 */     { UnnamedNamespace tmp = getUnnamedNamespaceFromFileNamed(fullScopeName);
/* 115 */       if (tmp != null) {
/* 116 */         modelElementList = tmp.getGlobalFunctionsList();
/*     */       } else {
/* 118 */         return null;
/*     */       }  }
/* 120 */     else { Namespace nsp = getNamespaceNamed(fullScopeName);
/* 121 */       if (nsp != null) {
/* 122 */         modelElementList = nsp.getGlobalFunctionsList();
/*     */       } else {
/* 124 */         return null;
/*     */       }  }
/*     */ 
/*     */     
/* 128 */     for (int i = 0; i < modelElementList.size(); i++) {
/* 129 */       Function currentFunction = (Function)modelElementList.get(i);
/* 130 */       if (currentFunction.getFullName().equals(functionFullName))
/* 131 */         return currentFunction; 
/*     */     } 
/* 133 */     return null;
/*     */   }
/*     */   
/*     */   public Variable getVariableNamed(String variableFullName) {
/*     */     Variable variable;
/* 138 */     if (variableFullName.lastIndexOf(")") > 0) {
/* 139 */       variable = getLocalVariableNamed(variableFullName);
/*     */     } else {
/* 141 */       variable = getGlobalVariableNamed(variableFullName);
/* 142 */       Variable refersTo = ((GlobalVariable)variable).getRefersTo();
/* 143 */       if (refersTo != null)
/* 144 */         variable = refersTo; 
/*     */     } 
/* 146 */     return variable;
/*     */   }
/*     */   private GlobalVariable getGlobalVariableNamed(String variableFullName) {
/*     */     ModelElementList modelElementList;
/* 150 */     int index = variableFullName.lastIndexOf(".");
/* 151 */     String variableName = variableFullName.substring(index + 1);
/* 152 */     String scopeName = variableFullName.substring(0, index);
/*     */     
/* 154 */     if (isFileName(scopeName)) {
/* 155 */       UnnamedNamespace unsp = getUnnamedNamespaceFromFileNamed(scopeName);
/* 156 */       if (unsp == null)
/* 157 */         return null; 
/* 158 */       modelElementList = unsp.getGlobalVariablesList();
/*     */     } else {
/* 160 */       Namespace currentNamespace = getNamespaceNamed(scopeName);
/* 161 */       if (currentNamespace == null)
/* 162 */         return null; 
/* 163 */       modelElementList = currentNamespace.getGlobalVariablesList();
/*     */     } 
/*     */     
/* 166 */     for (int i = 0; i < modelElementList.size(); i++) {
/* 167 */       GlobalVariable currentGlobalVar = (GlobalVariable)modelElementList.get(i);
/* 168 */       if (currentGlobalVar.getName().equals(variableName))
/* 169 */         return currentGlobalVar; 
/*     */     } 
/* 171 */     return null;
/*     */   }
/*     */ 
/*     */   
/* 175 */   private boolean isFileName(String scopeName) { return !(!scopeName.endsWith(".cc") && !scopeName.endsWith(".c") && !scopeName.endsWith(".cpp.")); }
/*     */ 
/*     */   
/*     */   private Variable getLocalVariableNamed(String variableFullName) {
/* 179 */     int index = variableFullName.lastIndexOf(")");
/* 180 */     String variableName = variableFullName.substring(index + 2);
/* 181 */     String functionFullName = variableFullName.substring(0, index + 1);
/* 182 */     Function currentMethod = getFunctionNamed(functionFullName);
/* 183 */     if (currentMethod == null) {
/* 184 */       return null;
/*     */     }
/* 186 */     ModelElementList<Parameter> parameterList = currentMethod.getParameterList();
/* 187 */     for (Parameter currentParameter : parameterList) {
/* 188 */       if (currentParameter.getName().equals(variableName)) {
/* 189 */         return currentParameter;
/*     */       }
/*     */     } 
/* 192 */     FunctionBody functionBody = currentMethod.getBody();
/* 193 */     if (functionBody == null) {
/* 194 */       return null;
/*     */     }
/* 196 */     ModelElementList<LocalVariable> localVarList = functionBody.getLocalVarList();
/* 197 */     for (LocalVariable currentLocalVar : localVarList) {
/* 198 */       if (currentLocalVar.getName().equals(variableName))
/* 199 */         return currentLocalVar; 
/*     */     } 
/* 201 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Attribute getAttributeNamed(String attributeFullName) {
/* 206 */     int index = attributeFullName.lastIndexOf(".");
/* 207 */     String fullClassName = attributeFullName.substring(0, index);
/* 208 */     String attName = attributeFullName.substring(index + 1, attributeFullName.length());
/* 209 */     Class clazz = getClassNamed(fullClassName);
/* 210 */     if (clazz == null)
/* 211 */       return null; 
/* 212 */     ModelElementList modelElementList = clazz.getAttributeList();
/* 213 */     for (int i = 0; i < modelElementList.size(); i++) {
/* 214 */       Attribute att = (Attribute)modelElementList.get(i);
/* 215 */       if (att.getName().equals(attName))
/* 216 */         return att; 
/*     */     } 
/* 218 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memori\\utils\MEMORIAFacade.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */