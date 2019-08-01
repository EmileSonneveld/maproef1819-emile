/*    */ package classes.lrg.insider.plugins.core.operators.string;
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
/*    */ public class StartsWith
/*    */   extends FilteringOperatorWithThresholds
/*    */ {
/* 16 */   public StartsWith() { super("starts as", "string"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean apply(ResultEntity theResult, Object threshold) {
/* 21 */     if (!(theResult.getValue() instanceof String)) return false; 
/* 22 */     if (!(threshold instanceof String)) return false;
/*    */     
/* 24 */     return ((String)theResult.getValue()).toLowerCase().startsWith(((String)threshold).toLowerCase());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\operators\string\StartsWith.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */