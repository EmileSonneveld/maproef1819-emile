/*    */ package lrg.metrics.classes;
/*    */ 
/*    */ import lrg.memoria.core.Class;
/*    */ import lrg.memoria.core.Method;
/*    */ import lrg.memoria.core.ModelElementList;
/*    */ import lrg.metrics.NumericalResult;
/*    */ import lrg.metrics.Result;
/*    */ import lrg.metrics.methods.ExteriorCalls;
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
/*    */ public class MessageSends
/*    */   extends ClassMeasure
/*    */ {
/*    */   public Result measure(Class cls) {
/* 37 */     double count = 0.0D;
/* 38 */     ModelElementList modelElementList = cls.getMethodList();
/*    */     
/* 40 */     ExteriorCalls ec = new ExteriorCalls();
/* 41 */     int size = modelElementList.size();
/* 42 */     for (int i = 0; i < size; i++) {
/* 43 */       Method act_method = (Method)modelElementList.get(i);
/* 44 */       if (act_method.getScope() == cls)
/* 45 */         count += ((NumericalResult)ec.measure(act_method)).getValue(); 
/*    */     } 
/* 47 */     return new NumericalResult(cls, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\MessageSends.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */