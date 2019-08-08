/*    */ package classes.lrg.insider.plugins.properties.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.plugins.groups.memoria.ClassExternalServiceProviders;
/*    */ import lrg.insider.plugins.properties.memoria.classes.CBO;
/*    */ 
/*    */ public class CBO extends PropertyComputer {
/*    */   public CBO() {
/*  9 */     super("CBO", "Number of Coupled Classes", "class", "numerical");
/* 10 */     basedOnDistinctGroup(new ClassExternalServiceProviders());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\CBO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */