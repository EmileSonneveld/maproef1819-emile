/*    */ package lrg.metrics.classes;
/*    */ 
/*    */ import lrg.memoria.core.Class;
/*    */ import lrg.memoria.core.Method;
/*    */ import lrg.memoria.core.ModelElementList;
/*    */ import lrg.metrics.NumericalResult;
/*    */ import lrg.metrics.Result;
/*    */ import lrg.metrics.methods.InteriorCallPaths;
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
/*    */ public class InteriorCallPaths
/*    */   extends ClassMeasure
/*    */ {
/*    */   public Result measure(Class c) {
/* 38 */     double count = 0.0D;
/* 39 */     ModelElementList modelElementList = c.getMethodList();
/*    */     
/* 41 */     InteriorCallPaths mpi = new InteriorCallPaths();
/*    */     
/* 43 */     int size = modelElementList.size();
/* 44 */     for (int i = 0; i < size; i++) {
/* 45 */       Method act_method = (Method)modelElementList.get(i);
/* 46 */       if (act_method.getScope() == c) {
/* 47 */         NumericalResult temp_result = (NumericalResult)mpi.measure(act_method);
/* 48 */         count += temp_result.getValue();
/*    */       } 
/*    */     } 
/* 51 */     return new NumericalResult(c, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\InteriorCallPaths.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */