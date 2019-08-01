/*    */ package lrg.metrics.methods;
/*    */ 
/*    */ import lrg.memoria.core.Method;
/*    */ import lrg.memoria.core.ModelElementList;
/*    */ import lrg.memoria.core.Parameter;
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
/*    */ public class NumberOfUsedParameters
/*    */   extends MethodMeasure
/*    */ {
/*    */   public Result measure(Method m) {
/* 34 */     ModelElementList modelElementList = m.getParameterList();
/* 35 */     int count = 0;
/* 36 */     int size = modelElementList.size();
/* 37 */     for (int i = 0; i < size; i++) {
/* 38 */       Parameter actParam = (Parameter)modelElementList.get(i);
/* 39 */       ModelElementList modelElementList1 = actParam.getAccessList();
/* 40 */       if (modelElementList1.size() != 0)
/* 41 */         count++; 
/*    */     } 
/* 43 */     return new NumericalResult(m, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\methods\NumberOfUsedParameters.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */