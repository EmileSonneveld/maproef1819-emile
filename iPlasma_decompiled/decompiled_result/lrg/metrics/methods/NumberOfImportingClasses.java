/*    */ package lrg.metrics.methods;
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
/*    */ 
/*    */ public class NumberOfImportingClasses
/*    */   extends MethodMeasure
/*    */ {
/*    */   public Result measure(Method m) {
/* 39 */     int count = 0;
/* 40 */     ModelElementList modelElementList = m.getBody().getAccessList();
/* 41 */     HashSet classl = new HashSet();
/*    */ 
/*    */ 
/*    */     
/* 45 */     for (int i = 0; i < modelElementList.size(); i++) {
/*    */       Attribute crtatt; try {
/* 47 */         crtatt = 
/* 48 */           (Attribute)((Access)modelElementList.get(i)).getVariable();
/* 49 */       } catch (ClassCastException e) {}
/*    */ 
/*    */       
/* 52 */       Class crtcls = (Class)crtatt.getScope();
/* 53 */       if (crtcls.getStatute() != 2 && 
/* 54 */         crtcls != (Class)m.getScope() && 
/*    */         
/* 56 */         !((Class)m.getScope()).getAncestorsList().contains(crtcls) && 
/* 57 */         !classl.contains(crtcls)) {
/* 58 */         classl.add(crtcls);
/* 59 */         count++;
/*    */       } 
/*    */     } 
/* 62 */     return new NumericalResult(m, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\methods\NumberOfImportingClasses.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */