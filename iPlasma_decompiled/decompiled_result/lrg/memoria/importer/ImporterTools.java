/*    */ package lrg.memoria.importer;
/*    */ 
/*    */ public class ImporterTools
/*    */ {
/*    */   public static String getFileName(String fullFileName) {
/*  6 */     int index = fullFileName.lastIndexOf("\\");
/*  7 */     if (index < 0)
/*  8 */       index = fullFileName.lastIndexOf("/"); 
/*  9 */     if (index < 0)
/* 10 */       return fullFileName; 
/* 11 */     return fullFileName.substring(index + 1, fullFileName.length());
/*    */   }
/*    */   
/*    */   public static String getPathName(String fullFileName) {
/* 15 */     int index = fullFileName.lastIndexOf("\\");
/* 16 */     if (index < 0)
/* 17 */       index = fullFileName.lastIndexOf("/"); 
/* 18 */     if (index < 0)
/* 19 */       return "."; 
/* 20 */     return fullFileName.substring(0, index);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\ImporterTools.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */