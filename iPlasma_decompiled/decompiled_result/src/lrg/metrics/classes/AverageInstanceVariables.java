/*    */ package lrg.metrics.classes;
/*    */ 
/*    */ import lrg.memoria.core.Class;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AverageInstanceVariables
/*    */   extends ClassMeasure
/*    */ {
/*    */   public Result measure(Class cls) {
/* 37 */     double temp, method_number = 0.0D;
/*    */     
/* 39 */     ModelElementList modelElementList = cls.getMethodList();
/* 40 */     int size = modelElementList.size();
/* 41 */     for (int i = 0; i < size; i++) {
/* 42 */       if (((Method)modelElementList.get(i)).getScope() == cls)
/* 43 */         method_number++; 
/* 44 */     }  InstanceVariables iv = new InstanceVariables();
/* 45 */     NumericalResult n_result = (NumericalResult)iv.measure(cls);
/* 46 */     double var_number = n_result.getValue();
/* 47 */     if (method_number != 0.0D) {
/* 48 */       temp = var_number / method_number;
/*    */     }
/* 50 */     else if (var_number == 0.0D) {
/* 51 */       temp = 0.0D;
/*    */     } else {
/* 53 */       temp = 2.147483647E9D;
/* 54 */     }  return new NumericalResult(cls, temp);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\AverageInstanceVariables.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */