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
/*    */ public class ClassTypeAttributes
/*    */   extends AttributeIterator
/*    */ {
/*    */   protected boolean check(Attribute act_attr) {
/* 26 */     if (act_attr.getType() instanceof lrg.memoria.core.Class) {
/* 27 */       return true;
/*    */     }
/* 29 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\ClassTypeAttributes.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */