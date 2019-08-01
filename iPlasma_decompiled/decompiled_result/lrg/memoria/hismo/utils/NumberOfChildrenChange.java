/*    */ package lrg.memoria.hismo.utils;
/*    */ 
/*    */ import lrg.memoria.core.DataAbstraction;
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
/*    */ public class NumberOfChildrenChange
/*    */   implements AbstractChangeProperty
/*    */ {
/*    */   public int getChangeValue(AbstractVersion previousVersion, AbstractVersion currentVersion) {
/* 20 */     int noc1 = 0, noc2 = 0;
/*    */     
/* 22 */     ClassVersion v1 = (ClassVersion)previousVersion;
/* 23 */     ModelElementList modelElementList1 = v1.getDescendants();
/* 24 */     for (int i = 0; i < modelElementList1.size(); i++) {
/* 25 */       if (((DataAbstraction)modelElementList1.get(i)).getStatute() == 1)
/* 26 */         noc1++; 
/*    */     } 
/* 28 */     ClassVersion v2 = (ClassVersion)currentVersion;
/* 29 */     ModelElementList modelElementList2 = v2.getDescendants();
/* 30 */     for (int i = 0; i < modelElementList2.size(); i++) {
/* 31 */       if (((DataAbstraction)modelElementList2.get(i)).getStatute() == 1)
/* 32 */         noc2++; 
/*    */     } 
/* 34 */     if (noc1 == noc2) {
/* 35 */       return 0;
/*    */     }
/* 37 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\hism\\utils\NumberOfChildrenChange.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */