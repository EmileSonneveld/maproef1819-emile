/*   */ package classes.lrg.insider.plugins.properties.memoria.methods;
/*   */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*   */ import lrg.insider.plugins.properties.memoria.methods.CC;
/*   */ import lrg.insider.plugins.properties.memoria.methods.ChangingClasses;
/*   */ 
/*   */ public class CC extends PropertyComputer {
/*   */   public CC() {
/* 8 */     super("CC", "Changing Classes", "method", "numerical");
/* 9 */     basedOnDistinctGroup(new ChangingClasses());
/*   */   }
/*   */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\methods\CC.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */