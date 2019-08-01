/*   */ package classes.lrg.insider.plugins.properties.memoria.methods;
/*   */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*   */ import lrg.insider.plugins.core.groups.memoria.containment.OperationHasLocalVariables;
/*   */ import lrg.insider.plugins.properties.memoria.methods.NOLV;
/*   */ 
/*   */ public class NOLV extends PropertyComputer {
/*   */   public NOLV() {
/* 8 */     super("NOLV", "Number of Local Variables", new String[] { "method", "global function" }, "numerical");
/* 9 */     basedOnGroup(new OperationHasLocalVariables());
/*   */   }
/*   */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\methods\NOLV.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */