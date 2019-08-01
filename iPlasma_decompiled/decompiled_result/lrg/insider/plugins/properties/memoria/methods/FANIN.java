/*   */ package classes.lrg.insider.plugins.properties.memoria.methods;
/*   */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*   */ import lrg.insider.plugins.core.groups.memoria.uses.OperationIsCalled;
/*   */ import lrg.insider.plugins.properties.memoria.methods.FANIN;
/*   */ 
/*   */ public class FANIN extends PropertyComputer {
/*   */   public FANIN() {
/* 8 */     super("FANIN", "Export Coupling", new String[] { "method", "global function" }, "numerical");
/* 9 */     basedOnGroup(new OperationIsCalled());
/*   */   }
/*   */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\methods\FANIN.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */