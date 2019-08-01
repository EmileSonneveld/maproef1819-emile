/*    */ package classes.lrg.insider.plugins.tools.memoria.graphgen.rules;
/*    */ 
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.rules.AbstractGraphBuildRule;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.rules.RMICalledHierarcyRule;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.rules.StrategyDefinition;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RMICalledHierarcyRule
/*    */   extends AbstractGraphBuildRule
/*    */ {
/*    */   public RMICalledHierarcyRule() {
/* 14 */     this.strategies.add(new StrategyDefinition("orange", true, lrg.insider.plugins.tools.memoria.graphgen.strategies.AllUsedMembers.class, null, 
/* 15 */           lrg.insider.plugins.tools.memoria.graphgen.strategies.UsedMembersLevelTwo.class, null));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 21 */   protected String fileNameSuffix() { return "-rmi-called-all"; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\memoria\graphgen\rules\RMICalledHierarcyRule.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */