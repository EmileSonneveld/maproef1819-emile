/*    */ package lrg.metrics.methods;
/*    */ 
/*    */ import lrg.memoria.core.Method;
/*    */ import lrg.memoria.core.ModelElementList;
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
/*    */ public class RaisedExceptions
/*    */   extends MethodMeasure
/*    */ {
/*    */   public Result measure(Method m) {
/* 32 */     ModelElementList modelElementList = m.getExceptionList();
/* 33 */     return new NumericalResult(m, modelElementList.size());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\methods\RaisedExceptions.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */