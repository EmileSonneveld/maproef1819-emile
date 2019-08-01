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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NumberOfAccessorMethods
/*    */   extends ClassMeasure
/*    */ {
/*    */   public Result measure(Class c) {
/* 45 */     int count = 0;
/* 46 */     ModelElementList modelElementList = c.getMethodList();
/*    */     
/* 48 */     for (int i = 0; i < modelElementList.size(); i++) {
/* 49 */       if (((Method)modelElementList.get(i)).isAccessor() && (
/* 50 */         (Method)modelElementList.get(i)).isPublic()) {
/* 51 */         count++;
/*    */       }
/*    */     } 
/* 54 */     return new NumericalResult(c, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\NumberOfAccessorMethods.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */