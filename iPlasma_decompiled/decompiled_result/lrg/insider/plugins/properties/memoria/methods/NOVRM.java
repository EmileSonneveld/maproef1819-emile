/*    */ package classes.lrg.insider.plugins.properties.memoria.methods;
/*    */ 
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.plugins.core.groups.memoria.uses.MethodOverrides;
/*    */ import lrg.insider.plugins.properties.memoria.methods.NOVRM;
/*    */ 
/*    */ public class NOVRM extends PropertyComputer {
/*    */   public NOVRM() {
/*  9 */     super("NOVRM", "Number of Overriding Methods", new String[] { "method", "global function" }, "numerical");
/* 10 */     basedOnGroup(new MethodOverrides());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\methods\NOVRM.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */