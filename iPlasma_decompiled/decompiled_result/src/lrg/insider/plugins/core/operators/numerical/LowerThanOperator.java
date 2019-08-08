/*    */ package classes.lrg.insider.plugins.core.operators.numerical;
/*    */ 
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.operators.FilteringOperatorWithThresholds;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LowerThanOperator
/*    */   extends FilteringOperatorWithThresholds
/*    */ {
/* 17 */   public LowerThanOperator() { super("<", "numerical"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean apply(ResultEntity theResult, Object threshold) {
/* 22 */     if (!(theResult.getValue() instanceof Double)) return false; 
/* 23 */     if (!(threshold instanceof Double)) return false;
/*    */     
/* 25 */     return (((Double)theResult.getValue()).compareTo((Double)threshold) < 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\operators\numerical\LowerThanOperator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */