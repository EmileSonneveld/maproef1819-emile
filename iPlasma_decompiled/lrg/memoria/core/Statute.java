/*    */ package lrg.memoria.core;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Statute
/*    */ {
/*    */   public static final int NORMAL = 1;
/*    */   public static final int LIBRARY = 2;
/*    */   public static final int FAILEDDEP = 3;
/*    */   
/*    */   public static String convertToString(int statute) {
/* 17 */     String result = "unknown";
/* 18 */     switch (statute) {
/*    */       case 1:
/* 20 */         result = "NORMAL";
/*    */         break;
/*    */       case 2:
/* 23 */         result = "LIBRARY";
/*    */         break;
/*    */       case 3:
/* 26 */         result = "FAILEDDEP";
/*    */         break;
/*    */     } 
/* 29 */     return result;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\Statute.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */