/*    */ package lrg.dude.duplication;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CompareToStrategy
/*    */   implements StringCompareStrategy
/*    */ {
/*    */   public boolean similar(String source, String target) {
/* 12 */     if (source.compareTo(target) == 0)
/* 13 */       return true; 
/* 14 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\dude.jar!\lrg\dude\duplication\CompareToStrategy.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */