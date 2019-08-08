/*    */ package lrg.metrics.classes;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import lrg.memoria.core.Access;
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
/*    */ public class TightClassCohesion
/*    */   extends ClassMeasure
/*    */ {
/*    */   public Result measure(Class cls) {
/* 38 */     double temp, den = 0.0D, nom = 0.0D;
/*    */     
/* 40 */     ModelElementList modelElementList = cls.getMethodList();
/*    */     
/* 42 */     HashSet al1 = new HashSet();
/*    */ 
/*    */ 
/*    */     
/* 46 */     int nm = modelElementList.size();
/* 47 */     for (int i = 0; i < nm - 1; i++) {
/* 48 */       for (int j = i + 1; j < nm; j++) {
/* 49 */         Method m1 = (Method)modelElementList.get(i);
/* 50 */         Method m2 = (Method)modelElementList.get(j);
/* 51 */         if (!m1.isConstructor() && !m2.isConstructor()) {
/*    */           int k;
/* 53 */           for (k = 0; k < m1.getBody().getAccessList().size(); k++)
/* 54 */             al1.add(((Access)m1.getBody().getAccessList().get(k)).getVariable()); 
/* 55 */           ModelElementList modelElementList1 = m2.getBody().getAccessList();
/* 56 */           for (k = 0; k < modelElementList1.size(); k++) {
/*    */             Attribute crtatt; try {
/* 58 */               crtatt = 
/* 59 */                 (Attribute)((Access)modelElementList1.get(k)).getVariable();
/* 60 */             } catch (ClassCastException e) {}
/*    */ 
/*    */             
/* 63 */             if (al1.contains(((Access)modelElementList1.get(k)).getVariable()) && 
/* 64 */               crtatt.getScope() == cls && !crtatt.isStatic()) {
/* 65 */               den++;
/*    */               break;
/*    */             } 
/*    */           } 
/* 69 */           nom++;
/*    */         } 
/*    */       } 
/* 72 */     }  if (den == 0.0D) { temp = 0.0D; }
/* 73 */     else { temp = den / nom; }
/*    */     
/* 75 */     return new NumericalResult(cls, temp);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\TightClassCohesion.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */