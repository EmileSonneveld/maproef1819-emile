/*    */ package lrg.metrics.classes;
/*    */ 
/*    */ import lrg.memoria.core.Class;
/*    */ import lrg.memoria.core.DataAbstraction;
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
/*    */ public class DepthOfInheritanceTree
/*    */   extends ClassMeasure
/*    */ {
/*    */   public Result measure(Class act_class) {
/* 44 */     int count = 0;
/*    */     
/* 46 */     if (!act_class.isInterface()) {
/*    */       
/* 48 */       for (Class clazz = act_class; clazz != null && !clazz.getName().equals("Object"); dataAbstraction = clazz.getFirstAncestor()) {
/* 49 */         DataAbstraction dataAbstraction; count++;
/*    */       } 
/* 51 */       count--;
/*    */     } 
/* 53 */     return new NumericalResult(act_class, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\DepthOfInheritanceTree.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */