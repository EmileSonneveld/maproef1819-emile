/*     */ package lrg.memoria.importer.recoder;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FilenameFilter;
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import lrg.common.utils.ProgressObserver;
/*     */ import lrg.memoria.core.Class;
/*     */ import lrg.memoria.core.ModelElementsRepository;
/*     */ import lrg.memoria.core.System;
/*     */ import lrg.memoria.importer.AbstractModelLoader;
/*     */ import lrg.memoria.importer.recoder.recoder.MeMoJCCrossReferenceServiceConfiguration;
/*     */ import lrg.memoria.importer.recoder.recoder.service.FailedDepErrorHandler;
/*     */ import recoder.CrossReferenceServiceConfiguration;
/*     */ import recoder.convenience.ASTIterator;
/*     */ import recoder.io.SourceFileRepository;
/*     */ import recoder.java.CompilationUnit;
/*     */ import recoder.service.CrossReferenceSourceInfo;
/*     */ import recoder.service.NameInfo;
/*     */ 
/*     */ 
/*     */ public class JavaModelLoader
/*     */   extends AbstractModelLoader
/*     */ {
/*     */   protected static CrossReferenceSourceInfo sourceInfo;
/*     */   public static CrossReferenceServiceConfiguration crsc;
/*     */   
/*  30 */   public static CrossReferenceSourceInfo getSourceInfo() { return sourceInfo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  36 */   public static CrossReferenceServiceConfiguration getCrossReferenceServiceConfiguration() { return crsc; }
/*     */ 
/*     */   
/*     */   public JavaModelLoader(String sourcePathList, String cachePath, ProgressObserver observer) throws Exception {
/*  40 */     super(observer);
/*  41 */     addJars(sourcePathList);
/*  42 */     loadModel(sourcePathList, cachePath);
/*     */   }
/*     */   
/*     */   public JavaModelLoader(String sourcePathList, String cachePath, String libraryPathList, ProgressObserver observer) throws Exception {
/*  46 */     super(observer);
/*     */     
/*  48 */     addJars(String.valueOf(sourcePathList) + File.pathSeparator + libraryPathList);
/*  49 */     loadModel(sourcePathList, cachePath);
/*     */   }
/*     */ 
/*     */   
/*     */   private void addJars(String pathList) {
/*  54 */     String newClassPath = System.getProperty("java.class.path");
/*  55 */     pathList = String.valueOf(System.getProperty("java.home")) + File.pathSeparator + pathList;
/*     */     
/*  57 */     StringTokenizer st = new StringTokenizer(pathList, File.pathSeparator);
/*     */     
/*  59 */     while (st.hasMoreTokens()) {
/*  60 */       String currentLibraryPath = st.nextToken();
/*  61 */       newClassPath = String.valueOf(newClassPath) + File.pathSeparator + findAllJarsFromPath(currentLibraryPath);
/*     */     } 
/*  63 */     System.setProperty("java.class.path", newClassPath);
/*     */   }
/*     */ 
/*     */   
/*     */   private String findAllJarsFromPath(String path) {
/*  68 */     String cp = new String();
/*  69 */     File f = new File(path);
/*  70 */     if (f.isDirectory())
/*  71 */     { String[] filesName = f.list();
/*  72 */       String dirName = f.getAbsolutePath();
/*  73 */       for (int i = 0; i < filesName.length; i++) {
/*  74 */         String name = String.valueOf(path) + File.separator + filesName[i];
/*  75 */         File testFile = new File(name);
/*  76 */         String cfn = "";
/*     */         
/*  78 */         try { cfn = testFile.getCanonicalPath(); }
/*  79 */         catch (IOException e) { e.printStackTrace(); }
/*     */         
/*  81 */         if (!dirName.startsWith(cfn)) {
/*     */           
/*  83 */           String cp1 = findAllJarsFromPath(String.valueOf(path) + File.separator + filesName[i]);
/*  84 */           if (!cp1.equals(""))
/*  85 */             if (cp.equals("")) {
/*  86 */               cp = cp1;
/*     */             } else {
/*  88 */               cp = String.valueOf(cp) + File.pathSeparator + cp1;
/*     */             }  
/*     */         } 
/*     */       }  }
/*  92 */     else { try { if (f.getCanonicalPath().endsWith(".jar"))
/*  93 */           cp = f.getAbsolutePath();  }
/*  94 */       catch (IOException e)
/*  95 */       { System.out.println(e); }
/*     */        }
/*     */     
/*  98 */     return cp;
/*     */   }
/*     */   
/*     */   protected void loadModelFromSources(String pathList) {
/* 102 */     HashMap prefixes = new HashMap();
/* 103 */     StringTokenizer st = new StringTokenizer(pathList, File.pathSeparator);
/* 104 */     String currentClassPath = System.getProperty("java.class.path");
/* 105 */     String path = "";
/* 106 */     while (st.hasMoreTokens()) {
/* 107 */       path = st.nextToken();
/* 108 */       prefixes.put(path, null);
/* 109 */       currentClassPath = String.valueOf(currentClassPath) + File.pathSeparator + path;
/*     */     } 
/* 111 */     System.setProperty("java.class.path", currentClassPath);
/* 112 */     System.out.println("CLASSPATH=" + currentClassPath);
/* 113 */     JavaFilenameFilter jfltr = new JavaFilenameFilter(prefixes);
/*     */ 
/*     */     
/* 116 */     ModelRepository mr = DefaultModelRepository.getModelRepository(pathList);
/* 117 */     this.system = mr.getSystem();
/*     */     
/* 119 */     crsc = new MeMoJCCrossReferenceServiceConfiguration();
/* 120 */     crsc.getProjectSettings().setErrorHandler(new FailedDepErrorHandler());
/*     */     
/* 122 */     crsc.getProjectSettings().setProperty("overwriteParsePositions", "false");
/* 123 */     crsc.getProjectSettings().setProperty("overwriteIndentation", "false");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 131 */     SourceFileRepository sfr = crsc.getSourceFileRepository();
/* 132 */     sourceInfo = (CrossReferenceSourceInfo)crsc.getSourceInfo();
/*     */     
/* 134 */     List<CompilationUnit> cul = sfr.getAllCompilationUnitsFromPath(jfltr);
/* 135 */     if (cul.size() == 0) {
/* 136 */       System.err.println("ERROR: The size of compilation units list is 0.");
/* 137 */       System.err.println("The model could not be loaded !");
/* 138 */       System.exit(1);
/*     */     } 
/*     */     
/* 141 */     NameInfo nameInfo = crsc.getNameInfo();
/* 142 */     Class clazz = mr.addClass(nameInfo.getJavaLangObject(), "Object");
/* 143 */     Class.setHierarchyRootClass(clazz);
/*     */ 
/*     */     
/* 146 */     ASTIterator asti = new ASTIterator();
/* 147 */     asti.setListener(new ModelConstructor());
/* 148 */     int size = cul.size();
/* 149 */     if (this.loadingProgressObserver != null)
/* 150 */       this.loadingProgressObserver.setMaxValue(size); 
/* 151 */     for (int i = 0; i < size; i++) {
/* 152 */       CompilationUnit cu = (CompilationUnit)cul.get(i);
/* 153 */       cu.toSource();
/*     */       
/* 155 */       try { asti.iterate(cu); }
/* 156 */       catch (Throwable e) { System.err.println("ERROR in parsing " + cu.getDataLocation().toString().substring(5)); }
/* 157 */        if (this.loadingProgressObserver != null) {
/* 158 */         this.loadingProgressObserver.increment();
/*     */       }
/* 160 */       System.err.println("File " + i + " - building model from " + cu.getDataLocation().toString().substring(5));
/*     */     } 
/* 162 */     cleanUp();
/* 163 */     System.out.println("CLASSPATH=" + currentClassPath);
/*     */   }
/*     */ 
/*     */   
/*     */   protected static void cleanUp() {
/* 168 */     ReferenceConverter.cleanUp();
/* 169 */     DefaultMetricRepository.cleanUp();
/* 170 */     DefaultModelRepository.cleanUp();
/* 171 */     ModelConstructor.cleanUp();
/* 172 */     crsc = null;
/* 173 */     sourceInfo = null;
/* 174 */     System.gc();
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 178 */     if (args.length != 2) {
/* 179 */       System.out.println("Usage: java lrg.memoria.importer.recoder.JavaModelLoader <path list> <cache>");
/*     */       return;
/*     */     } 
/*     */     try {
/* 183 */       String sourcePathList = args[0];
/* 184 */       System system = buildModel(sourcePathList, args[1]);
/*     */       
/* 186 */       system = null;
/* 187 */       ModelElementsRepository.cleanUp();
/* 188 */     } catch (Exception ex) {
/* 189 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static System buildModel(String sourcePathList, String cache) throws IOException, Exception {
/* 195 */     Runtime runtime = Runtime.getRuntime();
/*     */ 
/*     */     
/* 198 */     runtime.gc();
/* 199 */     long timeBefore = System.currentTimeMillis();
/* 200 */     long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
/*     */     
/* 202 */     JavaModelLoader currentModel = new JavaModelLoader(sourcePathList, cache, null);
/* 203 */     System system = currentModel.getSystem();
/*     */     
/* 205 */     printStatistics(memoryBefore, system, timeBefore);
/*     */     
/* 207 */     return system;
/*     */   }
/*     */   
/*     */   private static void printStatistics(long memoryBefore, System system, long timeBefore) {
/* 211 */     Runtime runtime = Runtime.getRuntime();
/*     */ 
/*     */     
/* 214 */     long timeAfter = System.currentTimeMillis();
/* 215 */     runtime.gc();
/* 216 */     long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
/* 217 */     long memorySize = memoryAfter - memoryBefore;
/* 218 */     System.out.println("\n\nFact extraction:");
/* 219 */     System.out.println("\n\t - execution time = " + String.valueOf(timeAfter - timeBefore) + " milliseconds");
/* 220 */     System.out.println("\n\t - source model size = " + String.valueOf(memorySize) + " bytes");
/* 221 */     System.out.println("\n\t - number of model objects = " + String.valueOf(ModelElementsRepository.getCurrentModelElementsRepository().getElementCount()));
/* 222 */     runtime.gc();
/*     */   }
/*     */   
/*     */   protected static class JavaFilenameFilter
/*     */     implements FilenameFilter {
/*     */     private HashMap m_prefixes;
/*     */     
/* 229 */     public JavaFilenameFilter(HashMap pref) { this.m_prefixes = pref; }
/*     */ 
/*     */     
/*     */     public boolean accept(File dir, String name) {
/* 233 */       File fi = dir;
/*     */       
/* 235 */       if (dir == null || name == null || !name.endsWith(".java")) return false;  boolean found; do {  }
/* 236 */       while (!(found = this.m_prefixes.containsKey(fi.getPath())) && (fi = fi.getParentFile()) != null);
/* 237 */       if (found) {
/* 238 */         return true;
/*     */       }
/* 240 */       return false;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\JavaModelLoader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */