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
/*    */ public class ChildrenRatio
/*    */   extends ClassMeasure
/*    */ {
/*    */   public Result measure(Class c) {
/*    */     double temp;
/* 32 */     NumberOfDescendants nodMetric = new NumberOfDescendants();
/* 33 */     NumberOfChildren nocMetric = new NumberOfChildren();
/*    */ 
/*    */     
/* 36 */     double nod = ((NumericalResult)nodMetric.measure(c)).getValue();
/* 37 */     double noc = ((NumericalResult)nocMetric.measure(c)).getValue();
/*    */     
/* 39 */     if (nod != 0.0D) {
/* 40 */       temp = noc / nod;
/* 41 */     } else if (noc == 0.0D) {
/* 42 */       temp = 0.0D;
/*    */     } else {
/* 44 */       temp = 2.147483647E9D;
/*    */     } 
/* 46 */     return new NumericalResult(c, temp);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\ChildrenRatio.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */