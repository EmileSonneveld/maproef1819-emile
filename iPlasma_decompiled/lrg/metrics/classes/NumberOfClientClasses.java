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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NumberOfClientClasses
/*    */   extends ClassMeasure
/*    */ {
/*    */   public Result measure(Class cls) {
/* 37 */     HashSet hs = new HashSet();
/*    */     
/* 39 */     ModelElementList modelElementList1 = cls.getAttributeList(); int j;
/* 40 */     for (j = 0; j < modelElementList1.size(); j++) {
/* 41 */       ModelElementList modelElementList = ((Attribute)modelElementList1.get(j)).getAccessList();
/* 42 */       for (int k = 0; k < modelElementList.size(); k++) {
/*    */         Class crtc; try {
/* 44 */           crtc = 
/* 45 */             (Class)((Access)modelElementList.get(k)).getScope().getScope().getScope();
/* 46 */         } catch (ClassCastException e) {}
/*    */ 
/*    */         
/* 49 */         if (crtc != cls) hs.add(crtc);
/*    */       
/*    */       } 
/*    */     } 
/* 53 */     ModelElementList modelElementList2 = cls.getMethodList();
/* 54 */     for (j = 0; j < modelElementList2.size(); j++) {
/* 55 */       ModelElementList modelElementList = ((Method)modelElementList2.get(j)).getCallList();
/* 56 */       for (int k = 0; k < modelElementList.size(); k++) {
/*    */         Class crtc; try {
/* 58 */           crtc = 
/* 59 */             (Class)((Call)modelElementList.get(k)).getScope().getScope().getScope();
/* 60 */         } catch (ClassCastException e) {}
/*    */ 
/*    */         
/* 63 */         if (crtc != cls) hs.add(crtc);
/*    */       
/*    */       } 
/*    */     } 
/* 67 */     return new NumericalResult(cls, hs.size());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\NumberOfClientClasses.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */