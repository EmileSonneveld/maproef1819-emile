/*   */ package classes.lrg.insider.plugins.properties.memoria.classes;
/*   */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*   */ import lrg.insider.plugins.groups.memoria.NewlyAddedServices;
/*   */ import lrg.insider.plugins.properties.memoria.classes.NAS;
/*   */ 
/*   */ public class NAS extends PropertyComputer {
/*   */   public NAS() {
/* 8 */     super("NAS", "Number of Added Services", "class", "numerical");
/* 9 */     basedOnGroup(new NewlyAddedServices());
/*   */   }
/*   */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\NAS.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */