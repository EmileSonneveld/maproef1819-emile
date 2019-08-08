/*    */ package classes.lrg.insider.plugins.properties.memoria.methods;
/*    */ 
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.plugins.properties.memoria.methods.CM;
/*    */ import lrg.insider.plugins.properties.memoria.methods.ChangingMethods;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CM
/*    */   extends PropertyComputer
/*    */ {
/*    */   public CM() {
/* 13 */     super("CM", "Changing Methods", "method", "numerical");
/* 14 */     basedOnDistinctGroup(new ChangingMethods());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\methods\CM.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */