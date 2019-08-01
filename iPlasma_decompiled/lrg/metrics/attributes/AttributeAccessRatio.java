/*    */ package lrg.metrics.attributes;
/*    */ 
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
/*    */ 
/*    */ 
/*    */ public class AttributeAccessRatio
/*    */   extends AttributeMeasure
/*    */ {
/*    */   public Result measure(Attribute att) {
/* 40 */     double temp, method_number = 0.0D, no_of_accessors = 0.0D;
/*    */ 
/*    */     
/* 43 */     Class cls = (Class)att.getScope();
/*    */     
/* 45 */     if (!att.isPrivate() && !att.isProtected()) {
/* 46 */       temp = 0.0D;
/*    */     } else {
/* 48 */       ModelElementList modelElementList = att.getAccessList();
/*    */ 
/*    */ 
/*    */       
/* 52 */       method_number = cls.getMethodList().size();
/*    */       
/* 54 */       for (int i = 0; i < modelElementList.size(); i++) {
/* 55 */         Method crt_method; Access crt_access = (Access)modelElementList.get(i);
/*    */         try {
/* 57 */           crt_method = (Method)crt_access.getScope().getScope();
/* 58 */         } catch (ClassCastException e) {}
/*    */ 
/*    */         
/* 61 */         if (crt_method.getScope().equals(cls))
/* 62 */           if (crt_method.isConstructor()) {
/* 63 */             method_number--;
/*    */           } else {
/* 65 */             no_of_accessors++;
/*    */           }  
/*    */       } 
/* 68 */       if (method_number != 0.0D) {
/* 69 */         temp = no_of_accessors / method_number;
/* 70 */       } else if (no_of_accessors == 0.0D) {
/* 71 */         temp = 0.0D;
/*    */       } else {
/* 73 */         temp = 2.147483647E9D;
/*    */       } 
/* 75 */     }  return new NumericalResult(cls, temp);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\attributes\AttributeAccessRatio.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */