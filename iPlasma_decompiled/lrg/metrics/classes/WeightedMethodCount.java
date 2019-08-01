/*    */ package lrg.metrics.classes;
/*    */ 
/*    */ import lrg.memoria.core.Class;
/*    */ import lrg.memoria.core.Method;
/*    */ import lrg.memoria.core.ModelElementList;
/*    */ import lrg.metrics.NumericalResult;
/*    */ import lrg.metrics.Result;
/*    */ import lrg.metrics.methods.MaximumNumberOfBranches;
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
/*    */ public class WeightedMethodCount
/*    */   extends ClassMeasure
/*    */ {
/*    */   public Result measure(Class cls) {
/* 38 */     double count = 0.0D;
/*    */     
/* 40 */     MaximumNumberOfBranches cyclo = new MaximumNumberOfBranches();
/* 41 */     ModelElementList modelElementList = cls.getMethodList();
/*    */ 
/*    */     
/* 44 */     int size = modelElementList.size();
/* 45 */     for (int i = 0; i < size; i++) {
/* 46 */       Method act_method = (Method)modelElementList.get(i);
/* 47 */       NumericalResult act_result = (NumericalResult)cyclo.measure(act_method);
/* 48 */       count += act_result.getValue();
/*    */     } 
/* 50 */     return new NumericalResult(cls, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\WeightedMethodCount.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */