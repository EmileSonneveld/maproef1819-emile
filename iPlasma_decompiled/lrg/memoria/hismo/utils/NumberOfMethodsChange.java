/*    */ package lrg.memoria.hismo.utils;
/*    */ 
/*    */ import lrg.memoria.core.ModelElementList;
/*    */ import lrg.memoria.hismo.core.AbstractVersion;
/*    */ import lrg.memoria.hismo.core.ClassVersion;
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
/*    */ public class NumberOfMethodsChange
/*    */   implements AbstractChangeProperty
/*    */ {
/*    */   public int getChangeValue(AbstractVersion previousVersion, AbstractVersion currentVersion) {
/* 21 */     ClassVersion v1 = (ClassVersion)previousVersion;
/* 22 */     ModelElementList modelElementList1 = v1.getMethodList();
/* 23 */     int nom1 = modelElementList1.size();
/*    */     
/* 25 */     ClassVersion v2 = (ClassVersion)currentVersion;
/* 26 */     ModelElementList modelElementList2 = v2.getMethodList();
/* 27 */     int nom2 = modelElementList2.size();
/*    */     
/* 29 */     if (v1.getStatute() == 2) {
/* 30 */       return 0;
/*    */     }
/* 32 */     if (nom1 == nom2) {
/* 33 */       return 0;
/*    */     }
/* 35 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\hism\\utils\NumberOfMethodsChange.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */