/*   */ package classes.lrg.insider.plugins.properties.memoria.classes;
/*   */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*   */ import lrg.insider.plugins.groups.memoria.ExternalData;
/*   */ import lrg.insider.plugins.properties.memoria.classes.ATFD;
/*   */ 
/*   */ public class ATFD extends PropertyComputer {
/*   */   public ATFD() {
/* 8 */     super("ATFD", "Access to Foreign Data", "class", "numerical");
/* 9 */     basedOnGroup(new ExternalData());
/*   */   }
/*   */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\ATFD.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */