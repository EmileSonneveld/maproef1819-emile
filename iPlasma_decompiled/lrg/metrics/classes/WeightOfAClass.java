/*    */ package lrg.metrics.classes;
/*    */ 
/*    */ import lrg.memoria.core.Attribute;
/*    */ import lrg.memoria.core.Class;
/*    */ import lrg.memoria.core.Method;
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
/*    */ public class WeightOfAClass
/*    */   extends ClassMeasure
/*    */ {
/*    */   public Result measure(Class cls) {
/* 40 */     double temp, den = 0.0D, nom = 0.0D;
/*    */     
/* 42 */     ModelElementList modelElementList1 = cls.getMethodList(), modelElementList2 = cls.getAttributeList();
/*    */     
/*    */     int i;
/* 45 */     for (i = 0; i < modelElementList1.size(); i++) {
/* 46 */       Method crtmeth = (Method)modelElementList1.get(i);
/* 47 */       if (crtmeth.isPublic() && !crtmeth.isConstructor()) {
/* 48 */         nom++;
/* 49 */         if (!crtmeth.isAccessor()) {
/* 50 */           den++;
/*    */         }
/*    */       } 
/*    */     } 
/* 54 */     for (i = 0; i < modelElementList2.size(); i++) {
/* 55 */       if (((Attribute)modelElementList2.get(i)).isPublic()) {
/* 56 */         nom++;
/*    */       }
/*    */     } 
/* 59 */     if (nom != 0.0D) {
/* 60 */       temp = den / nom;
/*    */     }
/* 62 */     else if (den == 0.0D) {
/* 63 */       temp = 0.0D;
/*    */     } else {
/* 65 */       temp = 2.147483647E9D;
/*    */     } 
/* 67 */     return new NumericalResult(cls, temp);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\WeightOfAClass.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */