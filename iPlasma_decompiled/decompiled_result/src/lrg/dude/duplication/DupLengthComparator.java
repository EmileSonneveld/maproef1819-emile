/*    */ package lrg.dude.duplication;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DupLengthComparator
/*    */   implements Comparator
/*    */ {
/*    */   public int compare(Object o1, Object o2) {
/* 14 */     Duplication d1 = (Duplication)o1;
/* 15 */     Duplication d2 = (Duplication)o2;
/* 16 */     if (d1.copiedLength() == d2.copiedLength())
/* 17 */       return 0; 
/* 18 */     if (d1.copiedLength() > d2.copiedLength()) {
/* 19 */       return 1;
/*    */     }
/* 21 */     return -1;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\dude.jar!\lrg\dude\duplication\DupLengthComparator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */