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
/*    */ public class NumberOfParameters
/*    */   extends MethodMeasure
/*    */ {
/* 28 */   public Result measure(Method m) { return new NumericalResult(m, m.getParameterList().size()); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\methods\NumberOfParameters.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */