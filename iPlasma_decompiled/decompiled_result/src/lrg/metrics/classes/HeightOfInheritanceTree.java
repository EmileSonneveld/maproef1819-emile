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
/*    */ public class HeightOfInheritanceTree
/*    */   extends ClassMeasure
/*    */ {
/*    */   private int level(Class actcls) {
/* 32 */     int max = 0;
/* 33 */     ModelElementList modelElementList = actcls.getDescendants();
/*    */ 
/*    */ 
/*    */     
/* 37 */     if (modelElementList.size() == 0) return 0;
/*    */     
/* 39 */     for (int i = 0; i < modelElementList.size(); i++) {
/*    */       Class crt; try {
/* 41 */         crt = (Class)modelElementList.get(i);
/* 42 */       } catch (ClassCastException e) {}
/*    */ 
/*    */       
/* 45 */       int aux = level(crt);
/* 46 */       if (aux > max) max = aux;
/*    */     
/*    */     } 
/* 49 */     return max + 1;
/*    */   }
/*    */   
/*    */   public Result measure(Class act_class) {
/* 53 */     int count = level(act_class);
/*    */     
/* 55 */     return new NumericalResult(act_class, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\HeightOfInheritanceTree.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */