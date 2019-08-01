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
/*    */ public class NumberOfDecisions
/*    */   extends MethodMeasure
/*    */ {
/*    */   public Result measure(Method m) {
/* 29 */     int count = 0;
/* 30 */     FunctionBody mb = m.getBody();
/* 31 */     if (mb != null)
/* 32 */       count = mb.getNumberOfDecisions(); 
/* 33 */     return new NumericalResult(m, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\methods\NumberOfDecisions.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */