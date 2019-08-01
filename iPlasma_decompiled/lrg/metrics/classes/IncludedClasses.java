/*    */ package lrg.metrics.classes;
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
/*    */ public class IncludedClasses
/*    */   extends ClassMeasure
/*    */ {
/*    */   public Result measure(Class c) {
/* 31 */     int count = 0;
/* 32 */     Package actPackage = c.getPackage();
/* 33 */     ModelElementList modelElementList = actPackage.getAllTypesList();
/*    */     
/* 35 */     int size = modelElementList.size();
/* 36 */     for (int i = 0; i < size; i++) {
/* 37 */       Class actClass = (Class)modelElementList.get(i);
/* 38 */       if (actClass.getScope() == c)
/* 39 */         count++; 
/*    */     } 
/* 41 */     return new NumericalResult(c, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\IncludedClasses.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */