/*    */ package lrg.metrics.classes;
/*    */ 
/*    */ import lrg.memoria.core.Class;
/*    */ import lrg.metrics.NumericalResult;
/*    */ import lrg.metrics.Result;
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
/*    */ public class LeavesRatio
/*    */   extends ClassMeasure
/*    */ {
/*    */   public Result measure(Class c) {
/*    */     double temp;
/* 32 */     NumberOfDescendants nodMetric = new NumberOfDescendants();
/* 33 */     NumberOfLeaves nolMetric = new NumberOfLeaves();
/*    */ 
/*    */     
/* 36 */     double nod = ((NumericalResult)nodMetric.measure(c)).getValue();
/* 37 */     double nol = ((NumericalResult)nolMetric.measure(c)).getValue();
/*    */     
/* 39 */     if (nod != 0.0D) {
/* 40 */       temp = nol / nod;
/* 41 */     } else if (nol == 0.0D) {
/* 42 */       temp = 0.0D;
/*    */     } else {
/* 44 */       temp = 2.147483647E9D;
/*    */     } 
/* 46 */     return new NumericalResult(c, temp);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\LeavesRatio.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */