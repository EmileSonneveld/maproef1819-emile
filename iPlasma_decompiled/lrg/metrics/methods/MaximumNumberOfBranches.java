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
/*    */ public class MaximumNumberOfBranches
/*    */   extends MethodMeasure
/*    */ {
/*    */   public Result measure(Method m) {
/* 28 */     int count = 0;
/* 29 */     FunctionBody mb = m.getBody();
/* 30 */     if (mb != null && mb.getNumberOfStatements() > 0) {
/* 31 */       count = mb.getCyclomaticNumber();
/*    */     }
/* 33 */     return new NumericalResult(m, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\methods\MaximumNumberOfBranches.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */