/*    */ package lrg.memoria.core;
/*    */ 
/*    */ public class UnnamedNamespace extends Namespace {
/*    */   private File file;
/*    */   
/*    */   public UnnamedNamespace(File file) {
/*  7 */     super(file.getFullName());
/*  8 */     this.file = file;
/*    */   }
/*    */ 
/*    */   
/* 12 */   public File getFile() { return this.file; }
/*    */ 
/*    */ 
/*    */   
/* 16 */   public String toString() { return "UnnamedNamespace: \n\t File - " + this.file.getFullName(); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\UnnamedNamespace.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */