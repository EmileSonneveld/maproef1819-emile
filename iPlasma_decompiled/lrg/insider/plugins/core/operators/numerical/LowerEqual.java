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
/*    */ public class LowerEqual
/*    */   extends FilteringOperatorWithThresholds
/*    */ {
/* 15 */   public LowerEqual() { super("<=", "numerical"); }
/*    */ 
/*    */   
/*    */   public boolean apply(ResultEntity theResult, Object threshold) {
/* 19 */     if (!(theResult.getValue() instanceof Double)) return false; 
/* 20 */     if (!(threshold instanceof Double)) return false;
/*    */     
/* 22 */     return (((Double)theResult.getValue()).compareTo((Double)threshold) <= 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\operators\numerical\LowerEqual.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */