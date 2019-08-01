/*    */ package lrg.metrics.classes;
/*    */ 
/*    */ import lrg.memoria.core.Method;
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
/*    */ public class ClassMethods
/*    */   extends MethodIterator
/*    */ {
/* 28 */   protected boolean check(Method m) { return m.isStatic(); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\ClassMethods.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */