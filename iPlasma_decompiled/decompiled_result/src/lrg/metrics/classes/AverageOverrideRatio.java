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
/*    */ public class AverageOverrideRatio
/*    */   extends ClassMeasure
/*    */ {
/*    */   public Result measure(Class c) {
/* 42 */     double temp, tovr = 0.0D;
/* 43 */     OverrideRatio ovrMetric = new OverrideRatio();
/*    */     
/* 45 */     ModelElementList modelElementList = c.getDescendants();
/* 46 */     double noc = modelElementList.size();
/*    */ 
/*    */     
/* 49 */     for (int i = 0; i < modelElementList.size(); i++) {
/*    */       Class crtClass; try {
/* 51 */         crtClass = (Class)modelElementList.get(i);
/* 52 */       } catch (ClassCastException e) {}
/*    */ 
/*    */       
/* 55 */       if (!crtClass.isPrivate()) {
/* 56 */         tovr += ((NumericalResult)ovrMetric.measure(crtClass, c)).getValue();
/*    */       }
/*    */     } 
/* 59 */     if (noc != 0.0D) {
/* 60 */       temp = tovr / noc;
/* 61 */     } else if (tovr == 0.0D) {
/* 62 */       temp = 0.0D;
/*    */     } else {
/* 64 */       temp = 2.147483647E9D;
/*    */     } 
/* 66 */     return new NumericalResult(c, temp);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\AverageOverrideRatio.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */