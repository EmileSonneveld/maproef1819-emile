/*     */ package lrg.memoria.importer.mcc.loader;
/*     */ import java.util.HashMap;
/*     */ import lrg.memoria.core.File;
/*     */ import lrg.memoria.core.Function;
/*     */ import lrg.memoria.core.FunctionBody;
/*     */ import lrg.memoria.core.Namespace;
/*     */ import lrg.memoria.core.Package;
/*     */ import lrg.memoria.core.System;
/*     */ import lrg.memoria.core.Type;
/*     */ import lrg.memoria.core.UnnamedNamespace;
/*     */ import lrg.memoria.core.Variable;
/*     */ 
/*     */ public class Loader {
/*  14 */   private static Loader loader = null;
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
/*  30 */   public void setLazyFuncsScopesStack(Stack lazyFuncsScopesStack) { this.lazyFuncsScopesStack = lazyFuncsScopesStack; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  37 */   protected HashMap<Integer, Package> packagesMap = new HashMap();
/*  38 */   protected HashMap<Integer, Namespace> namespacesMap = new HashMap();
/*  39 */   protected HashMap<Integer, Type> typesMap = new HashMap();
/*  40 */   protected HashMap<Integer, Primitive> primitiveMap = new HashMap();
/*  41 */   protected HashMap<Integer, Body> bodiesMap = new HashMap();
/*  42 */   protected HashMap<Integer, Variable> variablesMap = new HashMap();
/*  43 */   protected HashMap<Integer, Function> functionsMap = new HashMap();
/*  44 */   protected HashMap<LocalVariable, Integer> localVars2BodiesID = new HashMap();
/*  45 */   private HashMap<Integer, Type> tp2t = new HashMap();
/*  46 */   protected HashMap<String, File> projectFiles = new HashMap();
/*  47 */   protected HashMap<String, UnnamedNamespace> unnamedNamespaces = new HashMap();
/*  48 */   protected HashMap<FunctionBody, Package> bodyToPackageMap = new HashMap();
/*     */   private System system;
/*     */   private Stack lazyFuncsScopesStack;
/*     */   
/*     */   public static Loader getInstance() {
/*  53 */     if (loader == null) {
/*  54 */       loader = new Loader();
/*     */     }
/*  56 */     return loader;
/*     */   }
/*     */   
/*     */   public void setSystemName(String name) {
/*  60 */     Integer id = ModelElementsRepository.addNewModelElementsRepository();
/*  61 */     this.system = new System(name);
/*  62 */     this.system.setSystemId(id);
/*     */   }
/*     */   
/*     */   public void addPackage(Integer key, Package pack) {
/*  66 */     this.system.addPackage(pack);
/*  67 */     this.packagesMap.put(key, pack);
/*     */   }
/*     */   
/*     */   public void addNamespace(Integer key, Namespace namespace) {
/*  71 */     this.system.addNamespace(namespace);
/*  72 */     this.namespacesMap.put(key, namespace);
/*     */   }
/*     */ 
/*     */   
/*  76 */   public Namespace getNamespace(Integer key) { return (Namespace)this.namespacesMap.get(key); }
/*     */ 
/*     */   
/*     */   public UnnamedNamespace getUnnamedNamespace(String key) {
/*  80 */     UnnamedNamespace temp = (UnnamedNamespace)this.unnamedNamespaces.get(key);
/*  81 */     int statute = 3;
/*  82 */     if (temp == null) {
/*  83 */       File file = (File)this.projectFiles.get(key);
/*  84 */       if (file == null) {
/*  85 */         file = File.getUnknownFile();
/*     */       } else {
/*  87 */         statute = 1;
/*  88 */       }  temp = new UnnamedNamespace(file);
/*  89 */       temp.setStatute(statute);
/*  90 */       this.unnamedNamespaces.put(key, temp);
/*  91 */       this.system.addNamespace(temp);
/*     */     } 
/*  93 */     return temp;
/*     */   }
/*     */ 
/*     */   
/*  97 */   public void addType(Integer key, Type type) { this.typesMap.put(key, type); }
/*     */ 
/*     */ 
/*     */   
/* 101 */   public Type getType(Integer key) { return (Type)this.typesMap.get(key); }
/*     */ 
/*     */ 
/*     */   
/* 105 */   public void addBody(Integer key, FunctionBody body) { this.bodiesMap.put(key, body); }
/*     */ 
/*     */ 
/*     */   
/* 109 */   public FunctionBody getBody(Integer key) { return (FunctionBody)this.bodiesMap.get(key); }
/*     */ 
/*     */ 
/*     */   
/* 113 */   public Package getPackage(Integer key) { return (Package)this.packagesMap.get(key); }
/*     */ 
/*     */ 
/*     */   
/* 117 */   public System getSystem() { return this.system; }
/*     */ 
/*     */ 
/*     */   
/* 121 */   public void addVariable(Integer key, Variable variable) { this.variablesMap.put(key, variable); }
/*     */ 
/*     */ 
/*     */   
/* 125 */   public Variable getVariable(Integer key) { return (Variable)this.variablesMap.get(key); }
/*     */ 
/*     */ 
/*     */   
/* 129 */   public void addFunction(Integer key, Function function) { this.functionsMap.put(key, function); }
/*     */ 
/*     */ 
/*     */   
/* 133 */   public Function getFunction(Integer key) { return (Function)this.functionsMap.get(key); }
/*     */ 
/*     */ 
/*     */   
/* 137 */   public void addLocalVar2BodyMapEntry(LocalVariable localVar, Integer bodyScopeId) { this.localVars2BodiesID.put(localVar, bodyScopeId); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createLazyLinks() {}
/*     */ 
/*     */ 
/*     */   
/* 146 */   public void addTemplateParameterToType(Integer id, Type type) { this.tp2t.put(id, type); }
/*     */ 
/*     */ 
/*     */   
/* 150 */   public Type getTemplateParameterType(Integer id) { return (Type)this.tp2t.get(id); }
/*     */ 
/*     */   
/*     */   public File getFileByName(String fileFullName) {
/* 154 */     String fileName = ImporterTools.getFileName(fileFullName);
/* 155 */     String pathName = ImporterTools.getPathName(fileFullName);
/* 156 */     File temp = (File)this.projectFiles.get(fileFullName);
/* 157 */     if (temp == null) {
/* 158 */       temp = new File(pathName, fileName);
/* 159 */       this.projectFiles.put(fileFullName, temp);
/*     */     } 
/* 161 */     return temp;
/*     */   }
/*     */ 
/*     */   
/* 165 */   public void addBodyToPackage(FunctionBody currentBody, Package currentPackage) { this.bodyToPackageMap.put(currentBody, currentPackage); }
/*     */ 
/*     */ 
/*     */   
/* 169 */   public Package getPackageForBody(FunctionBody currentBody) { return (Package)this.bodyToPackageMap.get(currentBody); }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\mcc\loader\Loader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */