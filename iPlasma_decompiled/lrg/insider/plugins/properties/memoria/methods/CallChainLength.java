/*    */ package classes.lrg.insider.plugins.properties.memoria.methods;
/*    */ 
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.plugins.groups.memoria.MaxCallPath;
/*    */ import lrg.insider.plugins.properties.memoria.methods.CallChainLength;
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
/*    */ public class CallChainLength
/*    */   extends PropertyComputer
/*    */ {
/*    */   public CallChainLength() {
/* 25 */     super("CCL", "Call Chain Length", "method", "numerical");
/* 26 */     basedOnGroup(new MaxCallPath());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\methods\CallChainLength.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */