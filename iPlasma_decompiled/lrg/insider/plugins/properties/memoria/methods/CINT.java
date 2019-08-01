/*   */ package classes.lrg.insider.plugins.properties.memoria.methods;
/*   */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*   */ import lrg.insider.plugins.groups.memoria.ExternalServiceProviders;
/*   */ import lrg.insider.plugins.properties.memoria.methods.CINT;
/*   */ 
/*   */ public class CINT extends PropertyComputer {
/*   */   public CINT() {
/* 8 */     super("CINT", "Coupling Intensity", new String[] { "method", "global function" }, "numerical");
/* 9 */     basedOnGroup(new ExternalServiceProviders());
/*   */   }
/*   */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\methods\CINT.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */