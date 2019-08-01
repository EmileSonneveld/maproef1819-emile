/*    */ package lrg.metrics.classes;
/*    */ 
/*    */ import lrg.memoria.core.Attribute;
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
/*    */ public abstract class AttributeIterator
/*    */   extends ClassMeasure
/*    */ {
/*    */   protected abstract boolean check(Attribute paramAttribute);
/*    */   
/*    */   public Result measure(Class act_class) {
/* 23 */     int count = 0;
/* 24 */     ModelElementList modelElementList = act_class.getAttributeList();
/*    */     
/* 26 */     int size = modelElementList.size();
/* 27 */     for (int i = 0; i < size; i++) {
/* 28 */       Attribute actAttribute = (Attribute)modelElementList.get(i);
/* 29 */       if (check(actAttribute))
/* 30 */         count++; 
/*    */     } 
/* 32 */     return new NumericalResult(act_class, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\AttributeIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */