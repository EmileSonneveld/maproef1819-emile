/*   */ package classes.lrg.insider.plugins.properties.memoria.variable;
/*   */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*   */ import lrg.insider.plugins.core.groups.memoria.uses.VariableIsAccessed;
/*   */ import lrg.insider.plugins.properties.memoria.variable.NMAV;
/*   */ 
/*   */ public class NMAV extends PropertyComputer {
/*   */   public NMAV() {
/* 8 */     super("NMAV", "Number of Methods Accessing a variable", new String[] { "global variable", "attribute", "local variable", "parameter" }, "numerical");
/* 9 */     basedOnDistinctGroup(new VariableIsAccessed());
/*   */   }
/*   */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\variable\NMAV.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */