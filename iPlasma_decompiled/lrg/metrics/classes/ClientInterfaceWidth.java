/*    */ package lrg.metrics.classes;
/*    */ 
/*    */ import lrg.memoria.core.Class;
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
/*    */ public class ClientInterfaceWidth
/*    */   extends ClassMeasure
/*    */ {
/*    */   public Result measure(Class c) {
/* 36 */     NumberOfPublicMethods nPubMMetric = new NumberOfPublicMethods();
/* 37 */     PublicAttributes paMetric = new PublicAttributes();
/*    */ 
/*    */     
/* 40 */     double npubm = ((NumericalResult)nPubMMetric.measure(c)).getValue();
/* 41 */     double pa = ((NumericalResult)paMetric.measure(c)).getValue();
/*    */     
/* 43 */     double temp = npubm + pa;
/*    */     
/* 45 */     return new NumericalResult(c, temp);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\ClientInterfaceWidth.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */