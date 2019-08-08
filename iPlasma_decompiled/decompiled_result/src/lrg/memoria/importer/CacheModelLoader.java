/*    */ package lrg.memoria.importer;
/*    */ 
/*    */ import java.io.File;
/*    */ import lrg.memoria.core.System;
/*    */ import lrg.memoria.utils.ModelLighter;
/*    */ 
/*    */ 
/*    */ public class CacheModelLoader
/*    */ {
/*    */   protected System system;
/*    */   
/* 12 */   public System getSystem() { return this.system; }
/*    */ 
/*    */ 
/*    */   
/* 16 */   public System loadModel(String cacheFileName) { return loadModelFromCache(new File(cacheFileName)); }
/*    */ 
/*    */   
/*    */   protected System loadModelFromCache(File cacheFile) {
/* 20 */     System.out.println("Loading the model from cache: " + cacheFile.toString());
/* 21 */     this.system = System.loadFromFile(cacheFile);
/* 22 */     return this.system;
/*    */   }
/*    */   
/*    */   public static void main(String[] args) {
/* 26 */     if (args.length != 1) {
/* 27 */       System.out.println("Usage: java CacheModelLoader cacheFileName");
/*    */     }
/* 29 */     CacheModelLoader cml = new CacheModelLoader();
/* 30 */     System sys = cml.loadModel(args[0]);
/* 31 */     ModelLighter ml = new ModelLighter(sys);
/* 32 */     ml.lightenModel(2);
/* 33 */     System.serializeToFile(new File("/home/ratiud/tmp/rnim/rnim_2.dat"), sys);
/* 34 */     System.unloadSystemFromMemory(sys);
/* 35 */     sys = null;
/* 36 */     cml.loadModel("/home/ratiud/tmp/rnim/rnim_2.dat");
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\CacheModelLoader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */