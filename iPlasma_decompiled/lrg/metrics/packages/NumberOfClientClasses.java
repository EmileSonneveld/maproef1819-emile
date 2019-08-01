/*    */ package lrg.metrics.packages;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashSet;
/*    */ import lrg.memoria.core.Access;
/*    */ import lrg.memoria.core.Attribute;
/*    */ import lrg.memoria.core.Call;
/*    */ import lrg.memoria.core.Class;
/*    */ import lrg.memoria.core.Method;
/*    */ import lrg.memoria.core.ModelElementList;
/*    */ import lrg.memoria.core.Package;
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
/*    */ public class NumberOfClientClasses
/*    */   extends PackageMeasure
/*    */ {
/*    */   public Result measure(Package pck) {
/* 34 */     ModelElementList modelElementList = pck.getScopedElements(); ArrayList cll = new ArrayList();
/*    */ 
/*    */ 
/*    */     
/* 38 */     HashSet hs = new HashSet();
/*    */     int i;
/* 40 */     for (i = 0; i < modelElementList.size(); i++) {
/*    */       Class crtcl; try {
/* 42 */         crtcl = (Class)modelElementList.get(i);
/* 43 */       } catch (ClassCastException e) {}
/*    */ 
/*    */       
/* 46 */       cll.add(crtcl);
/*    */     } 
/*    */     
/* 49 */     for (i = 0; i < cll.size(); i++) {
/* 50 */       Class crtcl = (Class)cll.get(i);
/*    */       
/* 52 */       ModelElementList modelElementList1 = crtcl.getAttributeList(); int j;
/* 53 */       for (j = 0; j < modelElementList1.size(); j++) {
/* 54 */         ModelElementList modelElementList3 = ((Attribute)modelElementList1.get(j)).getAccessList();
/* 55 */         for (int k = 0; k < modelElementList3.size(); k++) {
/*    */           Class crtc; try {
/* 57 */             crtc = 
/* 58 */               (Class)((Access)modelElementList3.get(k)).getScope().getScope().getScope();
/* 59 */           } catch (ClassCastException e) {}
/*    */ 
/*    */           
/* 62 */           if (crtc.getScope() != pck) hs.add(crtc);
/*    */         
/*    */         } 
/*    */       } 
/* 66 */       ModelElementList modelElementList2 = crtcl.getMethodList();
/* 67 */       for (j = 0; j < modelElementList2.size(); j++) {
/* 68 */         ModelElementList modelElementList3 = ((Method)modelElementList2.get(j)).getCallList();
/* 69 */         for (int k = 0; k < modelElementList3.size(); k++) {
/*    */           Class crtc; try {
/* 71 */             crtc = 
/* 72 */               (Class)((Call)modelElementList3.get(k)).getScope().getScope().getScope();
/* 73 */           } catch (ClassCastException e) {}
/*    */ 
/*    */           
/* 76 */           if (crtc.getScope() != pck) hs.add(crtc);
/*    */         
/*    */         } 
/*    */       } 
/*    */     } 
/* 81 */     return new NumericalResult(pck, hs.size());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\packages\NumberOfClientClasses.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */