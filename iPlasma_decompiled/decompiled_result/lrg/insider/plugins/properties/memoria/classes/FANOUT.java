/*    */ package classes.lrg.insider.plugins.properties.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.plugins.groups.memoria.ClassExternalServiceProviders;
/*    */ import lrg.insider.plugins.properties.memoria.classes.FANOUT;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FANOUT
/*    */   extends PropertyComputer
/*    */ {
/*    */   public FANOUT() {
/* 15 */     super("FANOUT", "FANOUT (Dependency Intensity) aggregated at class level", "class", "numerical");
/* 16 */     basedOnGroup(new ClassExternalServiceProviders());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\FANOUT.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */