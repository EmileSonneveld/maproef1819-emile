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
/*    */ 
/*    */ public abstract class ClassMeasure
/*    */   extends PackageMeasure
/*    */ {
/*    */   public abstract Result measure(Class paramClass);
/*    */   
/* 23 */   protected boolean filter(Class cls) { return (cls.getStatute() == 1); }
/*    */ 
/*    */ 
/*    */   
/*    */   public Result measure(Package pack) {
/* 28 */     ModelElementList modelElementList = pack.getAllTypesList();
/* 29 */     CollectionResult cr = new CollectionResult();
/*    */     
/* 31 */     int size = modelElementList.size();
/* 32 */     for (int i = 0; i < size; i++) {
/* 33 */       Class act_class = (Class)modelElementList.get(i);
/* 34 */       if (filter(act_class))
/* 35 */         cr.add(measure(act_class)); 
/*    */     } 
/* 37 */     return cr;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\ClassMeasure.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */