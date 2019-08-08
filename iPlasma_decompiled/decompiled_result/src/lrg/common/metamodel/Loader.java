/*    */ package lrg.common.metamodel;
/*    */ import java.io.File;
/*    */ import java.net.URL;
/*    */ import java.net.URLClassLoader;
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.plugins.AbstractPlugin;
/*    */ 
/*    */ public class Loader {
/*    */   private ArrayList outsiderNames;
/*    */   
/*    */   public Loader(String outsiderPath) {
/* 12 */     this.outsiderPath = outsiderPath;
/*    */     
/* 14 */     this.outsiderNames = new ArrayList();
/*    */ 
/*    */     
/* 17 */     collectOutsiderNamesFromPath(outsiderPath, "");
/*    */   }
/*    */   private String outsiderPath;
/*    */   
/* 21 */   public ArrayList getNames() { return this.outsiderNames; }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractPlugin buildFrom(String propertyClassName, String directory) {
/* 26 */     if (propertyClassName.contains(directory))
/*    */     {
/* 28 */       return buildFrom(propertyClassName);
/*    */     }
/* 30 */     return null;
/*    */   }
/*    */   
/*    */   public AbstractPlugin buildFrom(String propertyClassName) {
/* 34 */     if (propertyClassName.contains("plugins")) {
/* 35 */       File file = new File(this.outsiderPath);
/*    */       try {
/* 37 */         URL url = file.toURL();
/* 38 */         URL[] urls = { url };
/* 39 */         ClassLoader classLoader = new URLClassLoader(urls);
/* 40 */         Class c = classLoader.loadClass(propertyClassName);
/* 41 */         return (AbstractPlugin)c.newInstance();
/*    */       
/*    */       }
/* 44 */       catch (Exception e) {
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 49 */         return null;
/* 50 */       } catch (Error e) {
/* 51 */         return null;
/*    */       } 
/*    */     } 
/* 54 */     return null;
/*    */   }
/*    */   
/*    */   private void collectOutsiderNamesFromPath(String path, String pathToAdd) {
/* 58 */     File thisPath = new File(path);
/* 59 */     File[] files = thisPath.listFiles();
/*    */     
/* 61 */     if (files == null)
/* 62 */       return;  for (int i = 0; i < files.length; i++) {
/* 63 */       if (files[i].getName().endsWith(".class") && files[i].isFile()) {
/* 64 */         this.outsiderNames.add(String.valueOf(pathToAdd) + files[i].getName().substring(0, files[i].getName().lastIndexOf(".")));
/*    */       }
/* 66 */       if (files[i].isDirectory())
/* 67 */         collectOutsiderNamesFromPath(String.valueOf(path) + File.separator + files[i].getName(), String.valueOf(pathToAdd) + files[i].getName() + "."); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\common.jar!\lrg\common\metamodel\Loader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */