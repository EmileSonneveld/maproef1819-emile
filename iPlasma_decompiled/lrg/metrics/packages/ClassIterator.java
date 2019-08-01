/*    */ package lrg.metrics.packages;
/*    */ 
/*    */ import lrg.memoria.core.Class;
/*    */ import lrg.memoria.core.ModelElementList;
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
/*    */ public abstract class ClassIterator
/*    */   extends PackageMeasure
/*    */ {
/*    */   protected abstract boolean check(Class paramClass);
/*    */   
/*    */   public Result measure(Package pack) {
/* 22 */     ModelElementList modelElementList = pack.getAllTypesList();
/* 23 */     int size = modelElementList.size();
/* 24 */     int count = 0;
/* 25 */     for (int i = 0; i < size; i++) {
/* 26 */       if (check((Class)modelElementList.get(i)))
/* 27 */         count++; 
/* 28 */     }  return new NumericalResult(pack, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\packages\ClassIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */