/*    */ package lrg.metrics.methods;
/*    */ 
/*    */ import lrg.memoria.core.FunctionBody;
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
/*    */ public class NumberOfLoops
/*    */   extends MethodMeasure
/*    */ {
/*    */   public Result measure(Method m) {
/* 30 */     int count = 0;
/* 31 */     FunctionBody mb = m.getBody();
/* 32 */     if (mb != null)
/* 33 */       count = mb.getNumberOfLoops(); 
/* 34 */     return new NumericalResult(m, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\methods\NumberOfLoops.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */