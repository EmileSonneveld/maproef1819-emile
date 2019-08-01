/*    */ package lrg.metrics.classes;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import lrg.memoria.core.Class;
/*    */ import lrg.memoria.core.Method;
/*    */ import lrg.memoria.core.ModelElementList;
/*    */ import lrg.memoria.core.Parameter;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OverrideRatio
/*    */   extends TwoClassesMeasure
/*    */ {
/*    */   protected boolean filter(Class cls1, Class cls2) {
/* 50 */     if (cls1.getStatute() == 1 && 
/* 51 */       cls2.getStatute() == 1 && 
/* 52 */       cls1.getAncestorsList().contains(cls2)) return true; 
/*    */     return false;
/*    */   }
/*    */   public Result measure(Class child, Class ancestor) {
/* 56 */     double temp, nd = 0.0D;
/*    */ 
/*    */     
/* 59 */     ModelElementList modelElementList1 = child.getMethodList(), modelElementList2 = ancestor.getMethodList();
/* 60 */     HashSet um = new HashSet();
/*    */     
/*    */     int i;
/* 63 */     for (i = 0; i < modelElementList1.size(); i++) {
/* 64 */       Method crtm = (Method)modelElementList1.get(i);
/* 65 */       if (!crtm.isConstructor())
/*    */       {
/* 67 */         for (int j = 0; j < modelElementList2.size(); j++) {
/* 68 */           Method rewmeth = (Method)modelElementList2.get(j);
/* 69 */           if (rewmeth.getName() == crtm.getName() && !rewmeth.isPrivate()) {
/* 70 */             boolean bool; ModelElementList modelElementList3 = crtm.getParameterList();
/* 71 */             ModelElementList modelElementList4 = rewmeth.getParameterList();
/* 72 */             if (modelElementList3.size() == modelElementList4.size()) { bool = true; }
/* 73 */             else { bool = false; }
/* 74 */              for (int l = 0; l < modelElementList3.size(); l++) {
/* 75 */               if (((Parameter)modelElementList3.get(l)).getType() != (
/* 76 */                 (Parameter)modelElementList4.get(l)).getType())
/* 77 */                 bool = false; 
/* 78 */             }  if (bool) um.add(crtm); 
/*    */           } 
/*    */         } 
/*    */       }
/*    */     } 
/* 83 */     double na = um.size();
/* 84 */     for (i = 0; i < ancestor.getMethodList().size(); i++) {
/* 85 */       Method crtcm = (Method)ancestor.getMethodList().get(i);
/* 86 */       if (!crtcm.isPrivate()) nd++;
/*    */     
/*    */     } 
/* 89 */     if (na == 0.0D) { temp = 0.0D; }
/* 90 */     else if (nd == 0.0D) { temp = 2.147483647E9D; }
/* 91 */     else { temp = na / nd; }
/*    */     
/* 93 */     return new NumericalResult(child, temp);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\OverrideRatio.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */