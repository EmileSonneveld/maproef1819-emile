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
/*    */ 
/*    */ public class AverageMethodWeight
/*    */   extends ClassMeasure
/*    */ {
/*    */   public Result measure(Class c) {
/*    */     double temp;
/* 40 */     MaximumNumberOfBranches cycloMetric = new MaximumNumberOfBranches();
/* 41 */     int count = c.getMethodList().size();
/* 42 */     int no_of_methods = count;
/* 43 */     double wmc = 0.0D;
/* 44 */     ModelElementList modelElementList = c.getMethodList();
/*    */ 
/*    */     
/* 47 */     for (int i = 0; i < count; i++) {
/* 48 */       Method crtMethod = (Method)modelElementList.get(i);
/* 49 */       if (crtMethod.isConstructor())
/* 50 */       { no_of_methods--; }
/* 51 */       else { wmc += ((NumericalResult)cycloMetric.measure(crtMethod)).getValue(); }
/*    */     
/* 53 */     }  if (no_of_methods != 0) {
/* 54 */       temp = wmc / no_of_methods;
/* 55 */     } else if (wmc == 0.0D) {
/* 56 */       temp = 0.0D;
/*    */     } else {
/* 58 */       temp = 2.147483647E9D;
/*    */     } 
/* 60 */     return new NumericalResult(c, temp);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\AverageMethodWeight.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */