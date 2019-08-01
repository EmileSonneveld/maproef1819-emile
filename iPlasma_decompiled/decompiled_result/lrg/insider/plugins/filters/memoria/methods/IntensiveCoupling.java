/*    */ package classes.lrg.insider.plugins.filters.memoria.methods;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.common.abstractions.plugins.filters.composed.AndComposedFilteringRule;
/*    */ import lrg.common.abstractions.plugins.filters.composed.OrComposedFilteringRule;
/*    */ import lrg.insider.plugins.filters.memoria.methods.IntensiveCoupling;
/*    */ 
/*    */ public class IntensiveCoupling
/*    */   extends FilteringRule {
/* 12 */   public IntensiveCoupling() { super(new Descriptor("Intensive Coupling", "method")); }
/*    */ 
/*    */ 
/*    */   
/*    */   private boolean functionCallsMannyFromUnrelatedClasses(AbstractEntityInterface aMethod) {
/* 17 */     FilteringRule veryhighIntensity = new FilteringRule("CINT", ">", "method", 7.0D);
/* 18 */     FilteringRule lowDispersion = new FilteringRule("CDISP", "<", "method", 0.5D);
/*    */     
/* 20 */     FilteringRule moreThanFewCalls = new FilteringRule("CINT", ">", "method", 2.0D);
/* 21 */     FilteringRule veryFocused = new FilteringRule("CDISP", "<", "method", 0.25D);
/*    */     
/* 23 */     return (new OrComposedFilteringRule(new AndComposedFilteringRule(veryhighIntensity, lowDispersion), 
/* 24 */         new AndComposedFilteringRule(moreThanFewCalls, veryFocused))).applyFilter(aMethod);
/*    */   }
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface aMethod) {
/* 28 */     boolean deepNestingOfCalls = (new FilteringRule("MAXNESTING", ">", "method", 1.0D)).applyFilter(aMethod);
/*    */     
/* 30 */     return (deepNestingOfCalls && functionCallsMannyFromUnrelatedClasses(aMethod));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\methods\IntensiveCoupling.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */