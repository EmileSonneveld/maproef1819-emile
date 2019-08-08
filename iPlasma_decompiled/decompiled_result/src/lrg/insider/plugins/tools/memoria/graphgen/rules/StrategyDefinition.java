/*    */ package classes.lrg.insider.plugins.tools.memoria.graphgen.rules;
/*    */ 
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.rules.StrategyDefinition;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StrategyDefinition
/*    */ {
/*    */   private Class initialStrategy;
/*    */   private String initialStrategyParameter;
/*    */   private Class levelTwoStrategy;
/*    */   private String edgeColor;
/*    */   private boolean edgeDirectedOutward;
/*    */   
/*    */   public StrategyDefinition(String edgeColor, boolean edgeDirectedOutward, Class initialStrategy, String initialStrategyParameter, Class levelTwoStrategy, String levelTwoStrategyParameter) {
/* 17 */     this.edgeColor = edgeColor;
/* 18 */     this.initialStrategy = initialStrategy;
/* 19 */     this.initialStrategyParameter = initialStrategyParameter;
/* 20 */     this.levelTwoStrategy = levelTwoStrategy;
/* 21 */     this.edgeDirectedOutward = edgeDirectedOutward;
/*    */   }
/*    */ 
/*    */   
/* 25 */   public Class getInitialStrategy() { return this.initialStrategy; }
/*    */ 
/*    */ 
/*    */   
/* 29 */   public String getInitialStrategyParameter() { return this.initialStrategyParameter; }
/*    */ 
/*    */ 
/*    */   
/* 33 */   public Class getLevelTwoStrategy() { return this.levelTwoStrategy; }
/*    */ 
/*    */ 
/*    */   
/* 37 */   public String getEdgeColor() { return this.edgeColor; }
/*    */ 
/*    */ 
/*    */   
/* 41 */   public boolean isEdgeDirectedOutward() { return this.edgeDirectedOutward; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\memoria\graphgen\rules\StrategyDefinition.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */