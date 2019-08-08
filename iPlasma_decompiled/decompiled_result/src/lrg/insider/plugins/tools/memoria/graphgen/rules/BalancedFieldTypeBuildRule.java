/*    */ package classes.lrg.insider.plugins.tools.memoria.graphgen.rules;
/*    */ 
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.rules.AbstractGraphBuildRule;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.rules.BalancedFieldTypeBuildRule;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.rules.StrategyDefinition;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BalancedFieldTypeBuildRule
/*    */   extends AbstractGraphBuildRule
/*    */ {
/*    */   public BalancedFieldTypeBuildRule(String helperClassParameter) {
/* 13 */     this.strategies.add(new StrategyDefinition("blue", false, lrg.insider.plugins.tools.memoria.graphgen.strategies.UsersOfMembersByFieldTypeBalanced.class, helperClassParameter, 
/* 14 */           lrg.insider.plugins.tools.memoria.graphgen.strategies.UsersOfMembersLevelTwo.class, null));
/* 15 */     this.strategies.add(new StrategyDefinition("orange", true, lrg.insider.plugins.tools.memoria.graphgen.strategies.AllUsedMembers.class, null, 
/* 16 */           lrg.insider.plugins.tools.memoria.graphgen.strategies.UsedMembersLevelTwo.class, null));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 22 */   protected String fileNameSuffix() { return "-balanced"; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\memoria\graphgen\rules\BalancedFieldTypeBuildRule.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */