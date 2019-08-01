/*    */ package classes.lrg.insider.plugins.tools.memoria.graphgen.rules;
/*    */ 
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.rules.AbstractGraphBuildRule;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.rules.FieldTypeBuildRule;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.rules.StrategyDefinition;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FieldTypeBuildRule
/*    */   extends AbstractGraphBuildRule
/*    */ {
/*    */   public FieldTypeBuildRule(String helperClassParameter) {
/* 13 */     this.strategies.add(new StrategyDefinition("blue", false, lrg.insider.plugins.tools.memoria.graphgen.strategies.UsersOfMembersByFieldType.class, helperClassParameter, 
/* 14 */           lrg.insider.plugins.tools.memoria.graphgen.strategies.UsersOfMembersLevelTwo.class, null));
/* 15 */     this.strategies.add(new StrategyDefinition("orange", true, lrg.insider.plugins.tools.memoria.graphgen.strategies.AllUsedMembers.class, null, 
/* 16 */           lrg.insider.plugins.tools.memoria.graphgen.strategies.UsedMembersLevelTwo.class, null));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 22 */   protected String fileNameSuffix() { return "-byfield"; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\memoria\graphgen\rules\FieldTypeBuildRule.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */