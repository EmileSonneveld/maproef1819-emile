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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AbstractClasses
/*    */   extends ClassIterator
/*    */ {
/* 29 */   protected boolean check(Class cls) { return (!cls.isInterface() && cls.isAbstract()); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\packages\AbstractClasses.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */