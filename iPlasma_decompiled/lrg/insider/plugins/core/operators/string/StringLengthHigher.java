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
/*    */ public class StringLengthHigher
/*    */   extends FilteringOperatorWithThresholds
/*    */ {
/* 16 */   public StringLengthHigher() { super("length higher", "string"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean apply(ResultEntity theResult, Object threshold) {
/* 21 */     if (!(theResult.getValue() instanceof String)) return false;
/*    */     
/* 23 */     String temp = (String)theResult.getValue();
/* 24 */     Double thresholdValue = (Double)threshold;
/*    */     
/* 26 */     return (temp.length() > thresholdValue.intValue());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\operators\string\StringLengthHigher.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */