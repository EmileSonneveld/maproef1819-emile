/*   */ package classes.lrg.insider.plugins.properties.memoria.classes;
/*   */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*   */ import lrg.insider.plugins.groups.memoria.ClassWithOverwritingMethods;
/*   */ import lrg.insider.plugins.properties.memoria.classes.BOvM;
/*   */ 
/*   */ public class BOvM extends PropertyComputer {
/*   */   public BOvM() {
/* 8 */     super("BOvM", "Baseclass Overwriting Methods", "class", "numerical");
/* 9 */     basedOnGroup(new ClassWithOverwritingMethods());
/*   */   }
/*   */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\BOvM.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */