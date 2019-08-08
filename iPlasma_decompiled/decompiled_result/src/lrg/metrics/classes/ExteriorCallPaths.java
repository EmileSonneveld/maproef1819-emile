/*    */ package lrg.metrics.classes;
/*    */ 
/*    */ import lrg.memoria.core.Class;
/*    */ import lrg.memoria.core.Method;
/*    */ import lrg.memoria.core.ModelElementList;
/*    */ import lrg.metrics.NumericalResult;
/*    */ import lrg.metrics.Result;
/*    */ import lrg.metrics.methods.ExteriorCallPaths;
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
/*    */ public class ExteriorCallPaths
/*    */   extends ClassMeasure
/*    */ {
/*    */   public Result measure(Class c) {
/* 37 */     double count = 0.0D;
/* 38 */     ModelElementList modelElementList = c.getMethodList();
/*    */     
/* 40 */     ExteriorCallPaths mpe = new ExteriorCallPaths();
/*    */     
/* 42 */     int size = modelElementList.size();
/* 43 */     for (int i = 0; i < size; i++) {
/* 44 */       Method act_method = (Method)modelElementList.get(i);
/* 45 */       if (act_method.getScope() == c) {
/* 46 */         NumericalResult temp_result = (NumericalResult)mpe.measure(act_method);
/* 47 */         count += temp_result.getValue();
/*    */       } 
/*    */     } 
/* 50 */     return new NumericalResult(c, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\ExteriorCallPaths.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */