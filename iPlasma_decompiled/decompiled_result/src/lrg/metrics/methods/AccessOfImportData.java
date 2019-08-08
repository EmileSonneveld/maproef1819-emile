/*    */ package lrg.metrics.methods;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AccessOfImportData
/*    */   extends MethodMeasure
/*    */ {
/*    */   public Result measure(Method m) {
/* 39 */     int count = 0;
/* 40 */     Class crt_class = (Class)m.getScope();
/* 41 */     ModelElementList modelElementList1 = m.getBody().getAccessList();
/*    */     
/* 43 */     HashSet acc = new HashSet();
/*    */     
/* 45 */     ModelElementList modelElementList2 = m.getBody().getCallList();
/*    */     
/*    */     int i;
/*    */     
/* 49 */     for (i = 0; i < modelElementList2.size(); i++) {
/*    */       Method crt_method; try {
/* 51 */         crt_method = (Method)((Call)modelElementList2.get(i)).getFunction();
/* 52 */       } catch (ClassCastException e) {}
/*    */ 
/*    */ 
/*    */       
/* 56 */       if (crt_method.isAccessor()) {
/* 57 */         ModelElementList modelElementList = crt_method.getBody().getAccessList();
/* 58 */         for (int j = 0; j < modelElementList.size(); j++) {
/* 59 */           modelElementList1.add(modelElementList.get(j));
/*    */         }
/*    */       } 
/*    */     } 
/* 63 */     for (i = 0; i < modelElementList1.size(); i++) {
/* 64 */       Attribute crt_attribute; Access crt_access = (Access)modelElementList1.get(i);
/*    */       try {
/* 66 */         crt_attribute = (Attribute)crt_access.getVariable();
/* 67 */       } catch (ClassCastException e) {}
/*    */ 
/*    */       
/* 70 */       if (!acc.contains(crt_attribute) && 
/* 71 */         !crt_class.getAncestorsList().contains((Class)crt_attribute.getScope()) && 
/* 72 */         crt_attribute.getScope() != crt_class && (
/* 73 */         !(crt_attribute.getType() instanceof Class) || (
/* 74 */         (Class)crt_attribute.getType()).getStatute() != 2)) {
/*    */         
/* 76 */         acc.add(crt_attribute);
/* 77 */         count++;
/*    */       } 
/*    */     } 
/* 80 */     return new NumericalResult(m, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\methods\AccessOfImportData.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */