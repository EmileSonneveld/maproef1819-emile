/*    */ package classes.lrg.insider.plugins.tools.memoria.graphgen.rules;
/*    */ 
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.rules.AbstractGraphBuildRule;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.rules.RMIBuildRule;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.rules.StrategyDefinition;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RMIBuildRule
/*    */   extends AbstractGraphBuildRule
/*    */ {
/*    */   public RMIBuildRule() {
/* 14 */     this.strategies.add(new StrategyDefinition("blue", false, lrg.insider.plugins.tools.memoria.graphgen.strategies.UsersOfAllMembers.class, null, 
/* 15 */           lrg.insider.plugins.tools.memoria.graphgen.strategies.UsersOfMembersLevelTwo.class, null));
/* 16 */     this.strategies.add(new StrategyDefinition("orange", true, lrg.insider.plugins.tools.memoria.graphgen.strategies.AllUsedMembers.class, null, 
/* 17 */           lrg.insider.plugins.tools.memoria.graphgen.strategies.UsedMembersLevelTwo.class, null));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 23 */   protected String fileNameSuffix() { return "-rmi-all"; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\memoria\graphgen\rules\RMIBuildRule.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */