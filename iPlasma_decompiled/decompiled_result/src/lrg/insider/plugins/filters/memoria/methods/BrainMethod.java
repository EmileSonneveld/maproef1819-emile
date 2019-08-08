/*    */ package classes.lrg.insider.plugins.filters.memoria.methods;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.common.abstractions.plugins.filters.composed.AndComposedFilteringRule;
/*    */ import lrg.insider.plugins.filters.Threshold;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BrainMethod
/*    */   extends FilteringRule
/*    */ {
/* 18 */   public BrainMethod() { super(new Descriptor("Brain Method", "method")); }
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface anEntity) {
/* 22 */     FilteringRule highLOC = new FilteringRule("LOC", ">", "method", Threshold.LOC_VERYHIGH);
/* 23 */     FilteringRule highCYCLO = new FilteringRule("CYCLO", ">=", "method", 4.0D);
/* 24 */     FilteringRule highNESTING = new FilteringRule("MAXNESTING", ">=", "method", 3.0D);
/* 25 */     FilteringRule manyVariablesAccessed = new FilteringRule("NOAV", ">=", "method", 7.0D);
/*    */     
/* 27 */     AndComposedFilteringRule andComposedFilteringRule = new AndComposedFilteringRule(highNESTING, 
/* 28 */         new AndComposedFilteringRule(highCYCLO, highLOC));
/*    */     
/* 30 */     if (andComposedFilteringRule.applyFilter(anEntity) && 
/* 31 */       manyVariablesAccessed.applyFilter(anEntity)) return true; 
/*    */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\methods\BrainMethod.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */