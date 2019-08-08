/*    */ package lrg.metrics.methods;
/*    */ 
/*    */ import lrg.memoria.core.Method;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LinesOfCode
/*    */   extends MethodMeasure
/*    */ {
/*    */   public Result measure(Method m) {
/* 35 */     int count = 0;
/*    */     
/* 37 */     if (!m.isConstructor())
/* 38 */       count = m.getBody().getNumberOfLines(); 
/* 39 */     return new NumericalResult(m, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\methods\LinesOfCode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */