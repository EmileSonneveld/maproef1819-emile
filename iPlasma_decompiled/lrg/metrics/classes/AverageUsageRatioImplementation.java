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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AverageUsageRatioImplementation
/*    */   extends ClassMeasure
/*    */ {
/*    */   public Result measure(Class c) {
/* 45 */     double temp, turi = 0.0D;
/* 46 */     UsageRatioImplementation uriMetric = new UsageRatioImplementation();
/*    */     
/* 48 */     ModelElementList modelElementList = c.getAncestorsList();
/* 49 */     double noa = modelElementList.size();
/*    */ 
/*    */     
/* 52 */     for (int i = 0; i < modelElementList.size(); i++) {
/*    */       Class crtClass; try {
/* 54 */         crtClass = (Class)modelElementList.get(i);
/* 55 */       } catch (ClassCastException e) {}
/*    */ 
/*    */       
/* 58 */       if (!crtClass.isPrivate()) {
/* 59 */         turi += ((NumericalResult)uriMetric.measure(c, crtClass)).getValue();
/*    */       }
/*    */     } 
/* 62 */     if (noa != 0.0D) {
/* 63 */       temp = turi / noa;
/* 64 */     } else if (turi == 0.0D) {
/* 65 */       temp = 0.0D;
/*    */     } else {
/* 67 */       temp = 2.147483647E9D;
/*    */     } 
/* 69 */     return new NumericalResult(c, temp);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\AverageUsageRatioImplementation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */