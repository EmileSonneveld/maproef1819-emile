/*    */ package lrg.metrics.packages;
/*    */ 
/*    */ import java.io.File;
/*    */ import lrg.memoria.core.ModelElementList;
/*    */ import lrg.memoria.core.Package;
/*    */ import lrg.memoria.core.System;
/*    */ import lrg.metrics.CollectionResult;
/*    */ import lrg.metrics.Result;
/*    */ import lrg.metrics.system.SystemMeasure;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class PackageMeasure
/*    */   extends SystemMeasure
/*    */ {
/*    */   public abstract Result measure(Package paramPackage);
/*    */   
/*    */   public Result measure(System sys) {
/* 20 */     ModelElementList modelElementList = sys.getNormalPackages();
/* 21 */     CollectionResult cr = new CollectionResult(String.valueOf(m_path) + File.separator + this.m_kind + File.separator + this.m_name + ".out", this.m_fullName);
/*    */     
/* 23 */     int size = modelElementList.size();
/* 24 */     for (int i = 0; i < size; i++) {
/* 25 */       Package act_pack = (Package)modelElementList.get(i);
/* 26 */       cr.add(measure(act_pack));
/*    */     } 
/* 28 */     return cr;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\packages\PackageMeasure.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */