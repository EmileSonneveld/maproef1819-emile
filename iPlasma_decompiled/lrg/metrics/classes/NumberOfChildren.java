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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NumberOfChildren
/*    */   extends ClassMeasure
/*    */ {
/*    */   public Result measure(Class cls) {
/* 36 */     int count = 0;
/* 37 */     ModelElementList modelElementList = cls.getDescendants();
/*    */     
/* 39 */     int size = modelElementList.size();
/* 40 */     for (int i = 0; i < size; i++) {
/* 41 */       Class act_desc = (Class)modelElementList.get(i);
/* 42 */       if (!act_desc.isInterface())
/* 43 */         count++; 
/*    */     } 
/* 45 */     return new NumericalResult(cls, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\NumberOfChildren.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */