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
/*    */ public class PublicBaseClasses
/*    */   extends ClassMeasure
/*    */ {
/*    */   public Result measure(Class c) {
/* 32 */     int count = 0;
/*    */     
/* 34 */     ModelElementList ancestors = c.getAncestorsList();
/* 35 */     for (int i = 0; i < ancestors.size(); i++) {
/* 36 */       Class currentAncestor = (Class)ancestors.get(i);
/* 37 */       if (currentAncestor != null && !currentAncestor.getName().equals("Object") && 
/* 38 */         !currentAncestor.isInterface() && currentAncestor.isPublic())
/* 39 */         count++; 
/*    */     } 
/* 41 */     return new NumericalResult(c, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\PublicBaseClasses.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */