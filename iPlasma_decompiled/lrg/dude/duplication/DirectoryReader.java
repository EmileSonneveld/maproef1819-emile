/*    */ package lrg.dude.duplication;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DirectoryReader
/*    */ {
/*    */   private File root;
/*    */   private ArrayList files;
/*    */   
/*    */   public DirectoryReader(String rootName) {
/*    */     try {
/* 19 */       this.root = new File(rootName);
/* 20 */     } catch (NullPointerException npe) {
/* 21 */       System.out.println("Error: empty String as path!");
/*    */     } 
/* 23 */     this.files = new ArrayList();
/*    */   }
/*    */   
/*    */   public ArrayList getFilesRecursive() {
/* 27 */     File[] list = this.root.listFiles();
/* 28 */     if (list == null) {
/* 29 */       System.out.println("Error: no files here!");
/* 30 */       return null;
/*    */     } 
/* 32 */     for (int i = 0; i < list.length; i++) {
/* 33 */       if (list[i].isFile()) {
/* 34 */         this.files.add(list[i]);
/* 35 */       } else if (list[i].isDirectory()) {
/* 36 */         DirectoryReader dir = new DirectoryReader(list[i].getAbsolutePath());
/* 37 */         this.files.addAll(dir.getFilesRecursive());
/*    */       } 
/*    */     } 
/* 40 */     return this.files;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\dude.jar!\lrg\dude\duplication\DirectoryReader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */