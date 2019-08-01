/*    */ package lrg.metrics.attributes;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NumberOfAttributeAccesses
/*    */   extends AttributeMeasure
/*    */ {
/*    */   public Result measure(Attribute att) {
/* 42 */     Class cls = (Class)att.getScope();
/* 43 */     int temp = 0;
/* 44 */     ModelElementList modelElementList = att.getAccessList();
/*    */     
/* 46 */     HashSet methl = new HashSet();
/*    */     
/* 48 */     if (att.isPrivate() || att.isProtected()) {
/* 49 */       for (int i = 0; i < modelElementList.size(); i++) {
/*    */         Method crtmeth; try {
/* 51 */           crtmeth = 
/* 52 */             (Method)((Access)modelElementList.get(i)).getScope().getScope();
/* 53 */         } catch (ClassCastException e) {}
/*    */ 
/*    */         
/* 56 */         if (!crtmeth.isConstructor() && !methl.contains(crtmeth)) {
/* 57 */           methl.add(crtmeth);
/* 58 */           temp++;
/*    */         } 
/*    */       } 
/*    */     }
/*    */     
/* 63 */     return new NumericalResult(cls, temp);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\attributes\NumberOfAttributeAccesses.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */