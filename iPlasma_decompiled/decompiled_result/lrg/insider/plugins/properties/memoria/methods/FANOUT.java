/*    */ package classes.lrg.insider.plugins.properties.memoria.methods;
/*    */ 
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.plugins.core.groups.memoria.uses.OperationCalls;
/*    */ import lrg.insider.plugins.properties.memoria.methods.FANOUT;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FANOUT
/*    */   extends PropertyComputer
/*    */ {
/*    */   public FANOUT() {
/* 15 */     super("FANOUT", "Import Coupling", new String[] { "method", "global function" }, "numerical");
/* 16 */     basedOnDistinctGroup(new OperationCalls());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\methods\FANOUT.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */