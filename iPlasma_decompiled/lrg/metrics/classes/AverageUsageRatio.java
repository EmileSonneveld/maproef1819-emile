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
/*    */ 
/*    */ public class AverageUsageRatio
/*    */   extends ClassMeasure
/*    */ {
/*    */   public Result measure(Class c) {
/* 46 */     double temp, tur = 0.0D;
/* 47 */     UsageRatio urMetric = new UsageRatio();
/*    */     
/* 49 */     ModelElementList modelElementList = c.getAncestorsList();
/* 50 */     double noa = modelElementList.size();
/*    */ 
/*    */     
/* 53 */     for (int i = 0; i < modelElementList.size(); i++) {
/*    */       Class crtClass; try {
/* 55 */         crtClass = (Class)modelElementList.get(i);
/* 56 */       } catch (ClassCastException e) {}
/*    */ 
/*    */       
/* 59 */       if (!crtClass.isPrivate()) {
/* 60 */         tur += ((NumericalResult)urMetric.measure(c, crtClass)).getValue();
/*    */       }
/*    */     } 
/* 63 */     if (noa != 0.0D) {
/* 64 */       temp = tur / noa;
/* 65 */     } else if (tur == 0.0D) {
/* 66 */       temp = 0.0D;
/*    */     } else {
/* 68 */       temp = 2.147483647E9D;
/*    */     } 
/* 70 */     return new NumericalResult(c, temp);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\AverageUsageRatio.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */