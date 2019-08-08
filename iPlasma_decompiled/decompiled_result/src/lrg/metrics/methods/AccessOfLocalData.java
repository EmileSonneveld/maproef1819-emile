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
/*    */ public class AccessOfLocalData
/*    */   extends MethodMeasure
/*    */ {
/*    */   public Result measure(Method m) {
/* 35 */     int count = 0;
/* 36 */     Class crt_class = (Class)m.getScope();
/* 37 */     ModelElementList modelElementList1 = m.getBody().getAccessList();
/*    */     
/* 39 */     HashSet acc = new HashSet();
/*    */     
/* 41 */     ModelElementList modelElementList2 = m.getBody().getCallList();
/*    */     
/*    */     int i;
/*    */     
/* 45 */     for (i = 0; i < modelElementList2.size(); i++) {
/*    */       Method crt_method; try {
/* 47 */         crt_method = (Method)((Call)modelElementList2.get(i)).getFunction();
/* 48 */       } catch (ClassCastException e) {}
/*    */ 
/*    */       
/* 51 */       if (crt_method.isAccessor() && crt_method.getScope() == crt_class) {
/* 52 */         ModelElementList modelElementList = crt_method.getBody().getAccessList();
/* 53 */         for (int j = 0; j < modelElementList.size(); j++) {
/* 54 */           modelElementList1.add(modelElementList.get(j));
/*    */         }
/*    */       } 
/*    */     } 
/* 58 */     for (i = 0; i < modelElementList1.size(); i++) {
/* 59 */       Attribute crt_attribute; Access crt_access = (Access)modelElementList1.get(i);
/*    */       try {
/* 61 */         crt_attribute = (Attribute)crt_access.getVariable();
/* 62 */       } catch (ClassCastException e) {}
/*    */ 
/*    */       
/* 65 */       if (!acc.contains(crt_attribute) && crt_attribute.getScope() == crt_class) {
/* 66 */         acc.add(crt_attribute);
/* 67 */         count++;
/*    */       } 
/*    */     } 
/* 70 */     return new NumericalResult(m, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\methods\AccessOfLocalData.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */