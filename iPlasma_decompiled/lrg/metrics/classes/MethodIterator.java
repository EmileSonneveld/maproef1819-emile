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
/*    */ public abstract class MethodIterator
/*    */   extends ClassMeasure
/*    */ {
/*    */   protected abstract boolean check(Method paramMethod);
/*    */   
/*    */   public Result measure(Class cls) {
/* 22 */     ModelElementList modelElementList = cls.getMethodList();
/*    */     
/* 24 */     int size = modelElementList.size();
/* 25 */     int count = 0;
/* 26 */     for (int i = 0; i < size; i++) {
/* 27 */       Method act_method = (Method)modelElementList.get(i);
/* 28 */       if (check(act_method))
/* 29 */         count++; 
/*    */     } 
/* 31 */     return new NumericalResult(cls, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\MethodIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */