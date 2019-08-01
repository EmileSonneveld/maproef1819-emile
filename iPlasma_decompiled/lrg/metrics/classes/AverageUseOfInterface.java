/*    */ package lrg.metrics.classes;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.memoria.core.Access;
/*    */ import lrg.memoria.core.Attribute;
/*    */ import lrg.memoria.core.Call;
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
/*    */ public class AverageUseOfInterface
/*    */   extends ClassMeasure
/*    */ {
/*    */   public Result measure(Class c) {
/*    */     double temp;
/* 42 */     ModelElementList modelElementList1 = c.getMethodList(), modelElementList2 = c.getAttributeList(); ArrayList ul = new ArrayList();
/*    */ 
/*    */ 
/*    */     
/* 46 */     double den = 0.0D, nom = 0.0D;
/*    */     int i;
/* 48 */     for (i = 0; i < modelElementList1.size(); i++) {
/* 49 */       Method crtm = (Method)modelElementList1.get(i);
/* 50 */       if (crtm.isPublic()) {
/* 51 */         ModelElementList modelElementList = crtm.getCallList();
/* 52 */         for (int j = 0; j < modelElementList.size(); j++) {
/* 53 */           if (((Call)modelElementList.get(j)).getScope().getScope() != c) {
/* 54 */             den++; break;
/*    */           } 
/*    */         } 
/* 57 */         nom++;
/*    */       } 
/*    */     } 
/* 60 */     for (i = 0; i < modelElementList2.size(); i++) {
/* 61 */       Attribute crta = (Attribute)modelElementList2.get(i);
/* 62 */       if (crta.isPublic()) {
/* 63 */         ModelElementList modelElementList = crta.getAccessList();
/* 64 */         for (int j = 0; j < modelElementList.size(); j++) {
/* 65 */           if (((Access)modelElementList.get(j)).getScope().getScope() != c) {
/* 66 */             den++; break;
/*    */           } 
/*    */         } 
/* 69 */         nom++;
/*    */       } 
/*    */     } 
/* 72 */     if (den == 0.0D) {
/* 73 */       temp = 0.0D;
/* 74 */     } else if (nom == 0.0D) {
/* 75 */       temp = 2.147483647E9D;
/*    */     } else {
/* 77 */       temp = den / nom;
/*    */     } 
/* 79 */     return new NumericalResult(c, temp);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\AverageUseOfInterface.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */