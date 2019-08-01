/*    */ package lrg.metrics.packages;
/*    */ 
/*    */ import lrg.memoria.core.Class;
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
/*    */ public class PackageSize
/*    */   extends ClassIterator
/*    */ {
/* 26 */   protected boolean check(Class cls) { return !cls.isInterface(); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\packages\PackageSize.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */