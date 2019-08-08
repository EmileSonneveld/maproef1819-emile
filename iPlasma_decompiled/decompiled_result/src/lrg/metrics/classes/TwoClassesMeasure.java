/*    */ package lrg.metrics.classes;
/*    */ 
/*    */ import lrg.memoria.core.Class;
/*    */ import lrg.memoria.core.ModelElementList;
/*    */ import lrg.memoria.core.Package;
/*    */ import lrg.metrics.CollectionResult;
/*    */ import lrg.metrics.Result;
/*    */ import lrg.metrics.packages.PackageMeasure;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class TwoClassesMeasure
/*    */   extends PackageMeasure
/*    */ {
/*    */   public abstract Result measure(Class paramClass1, Class paramClass2);
/*    */   
/*    */   protected boolean filter(Class cls1, Class cls2) {
/* 23 */     if (cls1.getStatute() == 1 && 
/* 24 */       cls2.getStatute() == 1) return true; 
/*    */     return false;
/*    */   }
/*    */   
/*    */   public Result measure(Package pack) {
/* 29 */     ModelElementList modelElementList = pack.getAllTypesList();
/* 30 */     CollectionResult cr = new CollectionResult();
/*    */ 
/*    */     
/* 33 */     int size = modelElementList.size();
/* 34 */     for (int i = 0; i < size - 1; i++) {
/* 35 */       for (int j = i + 1; i < size; j++) {
/* 36 */         Class act1_class = (Class)modelElementList.get(i);
/* 37 */         Class act2_class = (Class)modelElementList.get(j);
/* 38 */         if (filter(act1_class, act2_class))
/* 39 */           cr.add(measure(act1_class, act2_class)); 
/*    */       } 
/* 41 */     }  return cr;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\TwoClassesMeasure.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */