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
/*    */ 
/*    */ 
/*    */ public class NumberOfClientPackages
/*    */   extends PackageMeasure
/*    */ {
/*    */   public Result measure(Package pck) {
/* 36 */     ModelElementList modelElementList = pck.getScopedElements(); ArrayList cll = new ArrayList();
/*    */ 
/*    */ 
/*    */     
/* 40 */     HashSet hs = new HashSet();
/*    */     int i;
/* 42 */     for (i = 0; i < modelElementList.size(); i++) {
/*    */       Class crtcl; try {
/* 44 */         crtcl = (Class)modelElementList.get(i);
/* 45 */       } catch (ClassCastException e) {}
/*    */ 
/*    */       
/* 48 */       cll.add(crtcl);
/*    */     } 
/*    */     
/* 51 */     for (i = 0; i < cll.size(); i++) {
/* 52 */       Class crtcl = (Class)cll.get(i);
/*    */       
/* 54 */       ModelElementList modelElementList1 = crtcl.getAttributeList(); int j;
/* 55 */       for (j = 0; j < modelElementList1.size(); j++) {
/* 56 */         ModelElementList modelElementList3 = ((Attribute)modelElementList1.get(j)).getAccessList();
/* 57 */         for (int k = 0; k < modelElementList3.size(); k++) {
/*    */           Package crtp; try {
/* 59 */             crtp = 
/* 60 */               (Package)((Access)modelElementList3.get(k)).getScope().getScope().getScope().getScope();
/* 61 */           } catch (ClassCastException e) {}
/*    */ 
/*    */           
/* 64 */           if (crtp != pck) hs.add(crtp);
/*    */         
/*    */         } 
/*    */       } 
/* 68 */       ModelElementList modelElementList2 = crtcl.getMethodList();
/* 69 */       for (j = 0; j < modelElementList2.size(); j++) {
/* 70 */         ModelElementList modelElementList3 = ((Method)modelElementList2.get(j)).getCallList();
/* 71 */         for (int k = 0; k < modelElementList3.size(); k++) {
/*    */           Package crtp; try {
/* 73 */             crtp = 
/* 74 */               (Package)((Call)modelElementList3.get(k)).getScope().getScope().getScope().getScope();
/* 75 */           } catch (ClassCastException e) {}
/*    */ 
/*    */           
/* 78 */           if (crtp != pck) hs.add(crtp);
/*    */         
/*    */         } 
/*    */       } 
/*    */     } 
/* 83 */     return new NumericalResult(pck, hs.size());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\packages\NumberOfClientPackages.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */