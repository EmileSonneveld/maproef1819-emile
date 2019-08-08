/*    */ package lrg.metrics.packages;
/*    */ 
/*    */ import lrg.memoria.core.Package;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PackageUsageRatio
/*    */   extends PackageMeasure
/*    */ {
/*    */   public Result measure(Package pck) {
/*    */     double temp;
/* 49 */     PackageSize psMetric = new PackageSize();
/* 50 */     PackageInterfaceSize pisMetric = new PackageInterfaceSize();
/*    */     
/* 52 */     double pis = ((NumericalResult)pisMetric.measure(pck)).getValue();
/* 53 */     double ps = ((NumericalResult)psMetric.measure(pck)).getValue();
/* 54 */     if (pis == 0.0D) { temp = 0.0D; }
/* 55 */     else { temp = pis / ps; }
/* 56 */      return new NumericalResult(pck, temp);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\packages\PackageUsageRatio.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */