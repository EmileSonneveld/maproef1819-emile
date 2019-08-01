/*    */ package classes.lrg.insider.plugins.properties.memoria.packages;
/*    */ 
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.plugins.properties.memoria.packages.ModelClassesInPackage;
/*    */ import lrg.insider.plugins.properties.memoria.packages.NumberOfClasses;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NumberOfClasses
/*    */   extends PropertyComputer
/*    */ {
/*    */   public NumberOfClasses() {
/* 20 */     super("NOC", "Number of Classes", "package", "numerical");
/* 21 */     basedOnGroup(new ModelClassesInPackage());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\packages\NumberOfClasses.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */