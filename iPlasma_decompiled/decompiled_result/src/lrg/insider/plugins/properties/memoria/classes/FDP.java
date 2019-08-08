/*   */ package classes.lrg.insider.plugins.properties.memoria.classes;
/*   */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*   */ import lrg.insider.plugins.groups.memoria.DataProvidersForClass;
/*   */ import lrg.insider.plugins.properties.memoria.classes.FDP;
/*   */ 
/*   */ public class FDP extends PropertyComputer {
/*   */   public FDP() {
/* 8 */     super("FDP", "Access of Import Data", "class", "numerical");
/* 9 */     basedOnDistinctGroup(new DataProvidersForClass());
/*   */   }
/*   */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\FDP.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */