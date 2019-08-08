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
/*    */ public class NumberOfLeaves
/*    */   extends ClassMeasure
/*    */ {
/*    */   private int getNoOfLeaves(Class actcls) {
/* 33 */     int nd = 0;
/* 34 */     ModelElementList modelElementList = actcls.getDescendants();
/*    */ 
/*    */     
/* 37 */     if (modelElementList.size() == 0) return 1;
/*    */     
/* 39 */     for (int i = 0; i < modelElementList.size(); i++) {
/*    */       Class crt; try {
/* 41 */         crt = (Class)modelElementList.get(i);
/* 42 */       } catch (ClassCastException e) {}
/*    */ 
/*    */       
/* 45 */       int aux = getNoOfLeaves(crt);
/* 46 */       nd += aux;
/*    */     } 
/*    */     
/* 49 */     return nd;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Result measure(Class act_class) {
/*    */     int count;
/* 56 */     if (act_class.getDescendants().size() == 0) {
/* 57 */       count = 0;
/*    */     } else {
/* 59 */       count = getNoOfLeaves(act_class);
/*    */     } 
/* 61 */     return new NumericalResult(act_class, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\NumberOfLeaves.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */