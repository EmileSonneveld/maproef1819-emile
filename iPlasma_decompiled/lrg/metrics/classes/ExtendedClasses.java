/*    */ package lrg.metrics.classes;
/*    */ 
/*    */ import lrg.memoria.core.Class;
/*    */ import lrg.memoria.core.DataAbstraction;
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
/*    */ public class ExtendedClasses
/*    */   extends ClassMeasure
/*    */ {
/*    */   public Result measure(Class c) {
/* 30 */     int count = 0;
/* 31 */     if (c.isInterface()) {
/*    */       
/* 33 */       ModelElementList modelElementList = c.getAncestorsList();
/* 34 */       count = modelElementList.size();
/*    */     }
/*    */     else {
/*    */       
/* 38 */       DataAbstraction ancestor = c.getFirstAncestor();
/* 39 */       if (ancestor != null && !ancestor.getName().equals("Object"))
/* 40 */         count = 1; 
/*    */     } 
/* 42 */     return new NumericalResult(c, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\ExtendedClasses.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */