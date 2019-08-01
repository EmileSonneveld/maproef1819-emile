/*    */ package lrg.memoria.core;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class File
/*    */   extends ModelElement
/*    */ {
/*    */   private String pathName;
/*    */   private String fileName;
/*    */   private static File unknownFile;
/*    */   
/*    */   public static File getUnknownFile() {
/* 14 */     if (unknownFile == null)
/* 15 */       unknownFile = new File("_UNKNOWN_PATH_", "_UNKNOWN_FILE_"); 
/* 16 */     return unknownFile;
/*    */   }
/*    */   
/*    */   public File(String path, String name) {
/* 20 */     this.fileName = name;
/* 21 */     this.pathName = path;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getFullName() {
/* 35 */     if (this.pathName.indexOf("/") > 0) {
/* 36 */       return String.valueOf(this.pathName) + "/" + this.fileName;
/*    */     }
/* 38 */     return String.valueOf(this.pathName) + "\\" + this.fileName;
/*    */   }
/*    */ 
/*    */   
/* 42 */   public String getName() { return this.fileName; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 67 */   public void accept(ModelVisitor v) { v.visitFile(this); }
/*    */ 
/*    */ 
/*    */   
/* 71 */   public String toString() { return new String(getFullName()); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\File.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */