/*     */ package lrg.memoria.hismo.core;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import lrg.common.utils.ProgressObserver;
/*     */ import lrg.memoria.importer.CacheModelLoader;
/*     */ import lrg.memoria.importer.mcc.TablesLoader;
/*     */ import lrg.memoria.importer.recoder.JavaModelLoader;
/*     */ 
/*     */ public class HismoLoader
/*     */ {
/*  13 */   public static int JAVA_SOURCES = 1;
/*  14 */   public static int CPP_SOURCES = 2;
/*  15 */   public static int CACHE = 3;
/*     */   
/*     */   private SystemHistory systemHistory;
/*     */   protected ProgressObserver loadingProgressObserver;
/*     */   private CacheModelLoader model;
/*     */   private int kindOfSources;
/*     */   private String name;
/*     */   
/*     */   public HismoLoader(File sourceDirs, File cacheDirs, String name, ProgressObserver po, int kindOfSources) throws IOException {
/*  24 */     this.name = name;
/*  25 */     this.loadingProgressObserver = po;
/*  26 */     this.kindOfSources = kindOfSources;
/*  27 */     if (!sourceDirs.isDirectory() || (cacheDirs != null && !cacheDirs.isDirectory())) {
/*  28 */       System.out.println("Source dir or Cache dir is not a directory !");
/*     */       return;
/*     */     } 
/*  31 */     File[] versions = sourceDirs.listFiles();
/*  32 */     File[] caches = cacheDirs.listFiles();
/*  33 */     ArrayList nameList = new ArrayList();
/*  34 */     ArrayList pathList = new ArrayList();
/*  35 */     ArrayList cacheList = new ArrayList();
/*  36 */     for (int i = 0; i < versions.length; i++) {
/*  37 */       nameList.add(versions[i].getCanonicalPath());
/*  38 */       pathList.add(versions[i].getAbsolutePath());
/*  39 */       if (i > caches.length || caches[i] == null) {
/*  40 */         cacheList.add(String.valueOf(versions[i].getCanonicalPath()) + ".dat");
/*     */       } else {
/*  42 */         cacheList.add(caches[i].getAbsolutePath());
/*     */       } 
/*  44 */     }  loadHismo(nameList, pathList, cacheList);
/*     */   }
/*     */ 
/*     */   
/*     */   public HismoLoader(File cacheDirs, String name, ProgressObserver po) throws IOException {
/*  49 */     this.name = name;
/*  50 */     this.loadingProgressObserver = po;
/*  51 */     this.kindOfSources = CACHE;
/*  52 */     if (!cacheDirs.isDirectory()) {
/*  53 */       System.out.println("cacheDir is not a directory !");
/*     */       return;
/*     */     } 
/*  56 */     File[] caches = cacheDirs.listFiles();
/*  57 */     ArrayList nameList = new ArrayList();
/*  58 */     ArrayList cacheList = new ArrayList();
/*  59 */     for (int i = 0; i < caches.length; i++) {
/*  60 */       nameList.add(caches[i].getName().substring(0, caches[i].getName().length() - 4));
/*  61 */       cacheList.add(caches[i].getAbsolutePath());
/*     */     } 
/*  63 */     loadHismoFromCache(cacheList, nameList);
/*     */   }
/*     */ 
/*     */   
/*     */   public HismoLoader(ArrayList nameList, ArrayList pathList, ArrayList cachesList, String name, ProgressObserver po, int kindOfSources) {
/*  68 */     this.loadingProgressObserver = po;
/*  69 */     this.kindOfSources = kindOfSources;
/*  70 */     this.name = name;
/*  71 */     loadHismo(pathList, cachesList, nameList);
/*     */   }
/*     */   
/*     */   private void loadHismoFromCache(ArrayList cachesList, ArrayList nameList) {
/*  75 */     int versionsNumber = cachesList.size();
/*  76 */     if (this.loadingProgressObserver != null)
/*  77 */       this.loadingProgressObserver.setMaxValue(versionsNumber); 
/*     */     try {
/*  79 */       this.systemHistory = new SystemHistory(this.name);
/*     */ 
/*     */       
/*  82 */       for (int i = 0; i < versionsNumber; i++) {
/*  83 */         String currentCachePath = (String)cachesList.get(i);
/*  84 */         this.model = new CacheModelLoader();
/*  85 */         this.model.loadModel(currentCachePath);
/*     */         
/*  87 */         String versionName = (String)nameList.get(i);
/*     */         
/*  89 */         SystemVersion systemVersion = new SystemVersion(versionName, this.model.getSystem());
/*  90 */         this.systemHistory.addVersion(systemVersion);
/*     */         
/*  92 */         if (this.loadingProgressObserver != null)
/*  93 */           this.loadingProgressObserver.increment(); 
/*     */       } 
/*  95 */     } catch (Exception e) {
/*  96 */       System.err.println(e);
/*  97 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void loadHismo(ArrayList pathList, ArrayList cachesList, ArrayList nameList) {
/* 102 */     int versionsNumber = pathList.size();
/* 103 */     if (this.loadingProgressObserver != null)
/* 104 */       this.loadingProgressObserver.setMaxValue(versionsNumber); 
/*     */     try {
/* 106 */       this.systemHistory = new SystemHistory(this.name);
/*     */ 
/*     */       
/* 109 */       for (int i = 0; i < versionsNumber; i++) {
/* 110 */         String cacheFullPath, currentSourcePath = (String)pathList.get(i);
/* 111 */         String sourceFullPath = (new File(currentSourcePath)).getAbsolutePath();
/* 112 */         String currentCachePath = (String)cachesList.get(i);
/*     */         
/* 114 */         if (currentCachePath != null) {
/* 115 */           cacheFullPath = (new File(currentCachePath)).getAbsolutePath();
/*     */         } else {
/* 117 */           cacheFullPath = null;
/* 118 */         }  System.out.println("Loading the model from source path: " + sourceFullPath + " or from cache: " + cacheFullPath);
/* 119 */         if (this.kindOfSources == JAVA_SOURCES)
/* 120 */           this.model = new JavaModelLoader(sourceFullPath, cacheFullPath, null); 
/* 121 */         if (this.kindOfSources == CPP_SOURCES) {
/* 122 */           this.model = new TablesLoader(null, sourceFullPath, cacheFullPath);
/*     */         }
/* 124 */         String versionName = (String)nameList.get(i);
/*     */         
/* 126 */         SystemVersion systemVersion = new SystemVersion(versionName, this.model.getSystem());
/* 127 */         this.systemHistory.addVersion(systemVersion);
/*     */         
/* 129 */         if (this.loadingProgressObserver != null)
/* 130 */           this.loadingProgressObserver.increment(); 
/*     */       } 
/* 132 */     } catch (Exception e) {
/* 133 */       System.err.println(e);
/* 134 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 139 */   public SystemHistory getSystemHistory() { return this.systemHistory; }
/*     */ 
/*     */   
/*     */   public static void main(String[] args) throws IOException {
/* 143 */     if (args.length != 1) {
/* 144 */       System.out.println("Usage: javac HismoLoader caches_dir_path");
/*     */     }
/*     */     
/* 147 */     HismoLoader hl = new HismoLoader(new File(args[0]), "try", null);
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\hismo\core\HismoLoader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */