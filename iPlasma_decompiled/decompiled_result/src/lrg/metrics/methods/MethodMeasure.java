/*    */ package lrg.metrics.methods;
/*    */ 
/*    */ import lrg.memoria.core.Class;
/*    */ import lrg.memoria.core.Method;
/*    */ import lrg.memoria.core.ModelElementList;
/*    */ import lrg.metrics.CollectionResult;
/*    */ import lrg.metrics.Result;
/*    */ import lrg.metrics.classes.ClassMeasure;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class MethodMeasure
/*    */   extends ClassMeasure
/*    */ {
/* 21 */   protected boolean filter(Method m, Class cls) { return (m.getScope() == cls); }
/*    */ 
/*    */   
/*    */   public abstract Result measure(Method paramMethod);
/*    */ 
/*    */   
/*    */   public Result measure(Class cls) {
/* 28 */     CollectionResult cr = new CollectionResult();
/* 29 */     ModelElementList modelElementList = cls.getMethodList();
/*    */     
/* 31 */     int size = modelElementList.size();
/* 32 */     for (int i = 0; i < size; i++) {
/* 33 */       Method act_method = (Method)modelElementList.get(i);
/* 34 */       if (filter(act_method, cls))
/* 35 */         cr.add(measure(act_method)); 
/*    */     } 
/* 37 */     return cr;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\methods\MethodMeasure.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */