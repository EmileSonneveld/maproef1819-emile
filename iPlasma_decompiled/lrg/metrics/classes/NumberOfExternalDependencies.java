/*    */ package lrg.metrics.classes;
/*    */ 
/*    */ import java.util.HashSet;
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
/*    */ public class NumberOfExternalDependencies
/*    */   extends ClassMeasure
/*    */ {
/*    */   public Result measure(Class act_class) {
/* 34 */     double nd = 0.0D;
/*    */     
/* 36 */     ModelElementList modelElementList = act_class.getMethodList();
/* 37 */     HashSet ua = new HashSet(), um = new HashSet();
/*    */ 
/*    */ 
/*    */     
/* 41 */     for (int i = 0; i < modelElementList.size(); i++) {
/* 42 */       Method crtm = (Method)modelElementList.get(i);
/* 43 */       if (!crtm.isConstructor()) {
/* 44 */         ModelElementList modelElementList1 = crtm.getBody().getAccessList(); int j;
/* 45 */         for (j = 0; j < modelElementList1.size(); j++) {
/*    */           Attribute crta; try {
/* 47 */             crta = 
/* 48 */               (Attribute)((Access)modelElementList1.get(j)).getVariable();
/* 49 */           } catch (ClassCastException e) {}
/*    */ 
/*    */           
/* 52 */           if (!ua.contains(crta) && crta.getScope().getScope() != act_class.getScope()) {
/* 53 */             ua.add(crta);
/* 54 */             nd++;
/*    */           } 
/*    */         } 
/*    */         
/* 58 */         ModelElementList modelElementList2 = crtm.getBody().getCallList();
/* 59 */         for (j = 0; j < modelElementList2.size(); j++) {
/*    */           Method crtcm; try {
/* 61 */             crtcm = 
/* 62 */               (Method)((Call)modelElementList2.get(j)).getFunction();
/* 63 */           } catch (ClassCastException e) {}
/*    */ 
/*    */           
/* 66 */           if (!crtcm.isConstructor() && !um.contains(crtcm) && 
/* 67 */             crtcm.getScope().getScope() != act_class.getScope()) {
/* 68 */             um.add(crtcm);
/* 69 */             nd++;
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/* 74 */     return new NumericalResult(act_class, nd);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\NumberOfExternalDependencies.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */