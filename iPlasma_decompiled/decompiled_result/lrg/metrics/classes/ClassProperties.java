/*    */ package lrg.metrics.classes;
/*    */ 
/*    */ import lrg.memoria.core.Class;
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
/*    */ public class ClassProperties
/*    */   extends ClassMeasure
/*    */ {
/*    */   public Result measure(Class act_class) {
/* 33 */     int count = 0;
/*    */     
/* 35 */     ModelElementList modelElementList1 = act_class.getAttributeList();
/* 36 */     ModelElementList modelElementList2 = act_class.getMethodList();
/* 37 */     count = modelElementList1.size() + modelElementList2.size();
/* 38 */     return new NumericalResult(act_class, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\ClassProperties.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */