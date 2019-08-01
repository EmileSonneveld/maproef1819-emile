/*    */ package lrg.metrics.classes;
/*    */ 
/*    */ import lrg.memoria.core.Attribute;
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
/*    */ public class ClassVariables
/*    */   extends AttributeIterator
/*    */ {
/* 28 */   protected boolean check(Attribute act_attr) { return (!act_attr.isFinal() && act_attr.isStatic()); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\ClassVariables.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */