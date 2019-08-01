/*    */ package classes.lrg.insider.plugins.core.operators.bool;
/*    */ 
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.operators.FilteringOperator;
/*    */ 
/*    */ public class IsTrue
/*    */   extends FilteringOperator {
/*  8 */   public IsTrue() { super("is true", "boolean"); }
/*    */ 
/*    */   
/*    */   public boolean apply(ResultEntity theResult) {
/* 12 */     if (theResult.getValue() instanceof Boolean) {
/* 13 */       return ((Boolean)theResult.getValue()).booleanValue();
/*    */     }
/* 15 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\operators\bool\IsTrue.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */