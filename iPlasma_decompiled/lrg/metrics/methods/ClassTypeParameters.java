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
/*    */ public class ClassTypeParameters
/*    */   extends MethodMeasure
/*    */ {
/*    */   public Result measure(Method m) {
/* 33 */     int count = 0;
/* 34 */     ModelElementList modelElementList = m.getParameterList();
/*    */     
/* 36 */     if (modelElementList != null) {
/* 37 */       int size = modelElementList.size();
/* 38 */       for (int i = 0; i < size; i++) {
/* 39 */         Parameter p = (Parameter)modelElementList.get(i);
/* 40 */         if (p.getType() instanceof lrg.memoria.core.Class)
/* 41 */           count++; 
/*    */       } 
/*    */     } 
/* 44 */     if (m.getReturnType() instanceof lrg.memoria.core.Class)
/* 45 */       count++; 
/* 46 */     return new NumericalResult(m, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\methods\ClassTypeParameters.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */