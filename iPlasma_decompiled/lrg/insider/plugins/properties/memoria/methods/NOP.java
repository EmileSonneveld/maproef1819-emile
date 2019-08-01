/*   */ package classes.lrg.insider.plugins.properties.memoria.methods;
/*   */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*   */ import lrg.insider.plugins.core.groups.memoria.containment.OperationHasParameters;
/*   */ import lrg.insider.plugins.properties.memoria.methods.NOP;
/*   */ 
/*   */ public class NOP extends PropertyComputer {
/*   */   public NOP() {
/* 8 */     super("NOP", "Number of Parameters", new String[] { "method", "global function" }, "numerical");
/* 9 */     basedOnGroup(new OperationHasParameters());
/*   */   }
/*   */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\methods\NOP.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */