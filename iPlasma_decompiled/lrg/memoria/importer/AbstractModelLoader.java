/*     */ package lrg.memoria.importer;
/*     */ 
/*     */ import java.io.File;
/*     */ import lrg.common.utils.ProgressObserver;
/*     */ import lrg.memoria.core.Class;
/*     */ import lrg.memoria.core.DataAbstraction;
/*     */ import lrg.memoria.core.ModelElementList;
/*     */ import lrg.memoria.core.Namespace;
/*     */ import lrg.memoria.core.Package;
/*     */ import lrg.memoria.core.System;
/*     */ import lrg.memoria.core.Type;
/*     */ 
/*     */ public abstract class AbstractModelLoader
/*     */   extends CacheModelLoader
/*     */ {
/*     */   protected ProgressObserver loadingProgressObserver;
/*     */   
/*  18 */   protected AbstractModelLoader(ProgressObserver observer) { this.loadingProgressObserver = observer; }
/*     */ 
/*     */   
/*     */   protected void resolveParticularNamespacesAndPackages() {
/*  22 */     Package anonymousPackage = Package.getAnonymousPackage();
/*  23 */     Package unknownPackage = Package.getUnknownPackage();
/*  24 */     anonymousPackage.setSystem(this.system);
/*  25 */     unknownPackage.setSystem(this.system);
/*  26 */     this.system.addPackage(unknownPackage);
/*  27 */     this.system.addPackage(anonymousPackage);
/*  28 */     Namespace anonymousNamespace = Namespace.getAnonymousNamespace();
/*  29 */     Namespace globalNamespace = Namespace.getGlobalNamespace();
/*  30 */     Namespace unknownNamespace = Namespace.getUnknownNamespace();
/*  31 */     anonymousNamespace.setSystem(this.system);
/*  32 */     this.system.addNamespace(anonymousNamespace);
/*  33 */     globalNamespace.setSystem(this.system);
/*  34 */     this.system.addNamespace(globalNamespace);
/*  35 */     unknownNamespace.setSystem(this.system);
/*  36 */     this.system.addNamespace(unknownNamespace);
/*     */   }
/*     */ 
/*     */   
/*  40 */   public System loadModel(String sourcePathList, String cachePath) throws Exception { return loadModelFromCacheOrFromSources(cachePath, sourcePathList); }
/*     */ 
/*     */   
/*     */   protected System loadModelFromCacheOrFromSources(String cachePath, String sourcePathList) throws Exception {
/*  44 */     File serialized = getCacheFile(sourcePathList, cachePath);
/*  45 */     if (!serialized.exists()) {
/*  46 */       System.out.println("Loading the model from source files ... ");
/*  47 */       System.out.println("The source path list is: " + sourcePathList);
/*  48 */       loadModelFromSources(sourcePathList);
/*  49 */       resolveParticularNamespacesAndPackages();
/*  50 */       correctScopeNames();
/*  51 */       System.serializeToFile(serialized, this.system);
/*     */     } else {
/*  53 */       loadModelFromCache(serialized);
/*     */     } 
/*  55 */     return this.system;
/*     */   }
/*     */   
/*     */   private void correctScopeNames() {
/*  59 */     ModelElementList<Package> allPackages = this.system.getPackages();
/*  60 */     ModelElementList<Namespace> allNamespaces = this.system.getNamespaces();
/*  61 */     int counter = 0;
/*  62 */     int pacCounter = 0;
/*     */     
/*  64 */     for (Namespace crtNamespace : allNamespaces) {
/*  65 */       ModelElementList<Type> allClasses = crtNamespace.getAllTypesList();
/*  66 */       for (Type dataAbstraction : allClasses) {
/*  67 */         if (dataAbstraction instanceof Class) {
/*  68 */           ((Class)dataAbstraction).setScope(crtNamespace);
/*  69 */           counter++;
/*     */         } 
/*     */       } 
/*     */     } 
/*  73 */     for (Package pack : allPackages) {
/*  74 */       ModelElementList<DataAbstraction> allClasses = pack.getAbstractDataTypes();
/*  75 */       for (DataAbstraction dataAbstraction : allClasses) {
/*  76 */         if (dataAbstraction instanceof Class) {
/*  77 */           setCorrectScopeForInnerClasses((Class)dataAbstraction);
/*  78 */           pacCounter++;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setCorrectScopeForInnerClasses(Class dataAbstraction) {
/*  87 */     ModelElementList<Type> innerTypes = dataAbstraction.getInnerList();
/*  88 */     for (Type type : innerTypes) {
/*  89 */       if (type instanceof Class) ((Class)type).setScope(dataAbstraction); 
/*     */     } 
/*     */   }
/*     */   protected abstract void loadModelFromSources(String paramString) throws Exception;
/*     */   
/*     */   private File getCacheFile(String sourcePathList, String cachePath) {
/*     */     File serialized;
/*  96 */     if (cachePath == null) {
/*  97 */       String noPathSeparator = sourcePathList.replaceAll(File.pathSeparator, "__");
/*  98 */       String normalized = noPathSeparator.replace(File.separator.charAt(0), '_');
/*  99 */       normalized = normalized.replace(':', '#');
/* 100 */       File f = new File("temp" + File.separator + "cache" + File.separator + sourcePathList.hashCode());
/* 101 */       f.mkdirs();
/* 102 */       serialized = new File(String.valueOf(f.toString()) + File.separator + "cached_model_from_" + normalized + ".dat");
/*     */     } else {
/* 104 */       serialized = new File(cachePath);
/*     */     } 
/* 106 */     return serialized;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\AbstractModelLoader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */