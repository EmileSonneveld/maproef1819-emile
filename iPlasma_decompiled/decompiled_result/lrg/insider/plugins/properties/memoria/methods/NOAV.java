/*   */ package classes.lrg.insider.plugins.properties.memoria.methods;
/*   */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*   */ import lrg.insider.plugins.core.groups.memoria.uses.OperationAccesses;
/*   */ import lrg.insider.plugins.properties.memoria.methods.NOAV;
/*   */ 
/*   */ public class NOAV extends PropertyComputer {
/*   */   public NOAV() {
/* 8 */     super("NOAV", "Number of Accessed Variables", new String[] { "method", "global function" }, "numerical");
/* 9 */     basedOnDistinctGroup(new OperationAccesses());
/*   */   }
/*   */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\methods\NOAV.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */