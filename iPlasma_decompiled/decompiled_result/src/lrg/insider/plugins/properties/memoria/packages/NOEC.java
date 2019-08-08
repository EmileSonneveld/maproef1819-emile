/*   */ package classes.lrg.insider.plugins.properties.memoria.packages;
/*   */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*   */ import lrg.insider.plugins.groups.memoria.EfferentCoupling;
/*   */ import lrg.insider.plugins.properties.memoria.packages.NOEC;
/*   */ 
/*   */ public class NOEC extends PropertyComputer {
/*   */   public NOEC() {
/* 8 */     super("NOEC", "Number of Efferent Coupling", "package", "numerical");
/* 9 */     basedOnGroup(new EfferentCoupling());
/*   */   }
/*   */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\packages\NOEC.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */