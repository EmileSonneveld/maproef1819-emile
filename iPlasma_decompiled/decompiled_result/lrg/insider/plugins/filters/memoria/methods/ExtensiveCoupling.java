/*    */ package classes.lrg.insider.plugins.filters.memoria.methods;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.common.abstractions.plugins.filters.composed.AndComposedFilteringRule;
/*    */ import lrg.insider.plugins.filters.memoria.methods.ExtensiveCoupling;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ExtensiveCoupling
/*    */   extends FilteringRule
/*    */ {
/* 18 */   public ExtensiveCoupling() { super(new Descriptor("Extensive Coupling", "method")); }
/*    */ 
/*    */   
/*    */   private boolean functionCallsManyFromManyUnrelatedClasses(AbstractEntityInterface aMethod) {
/* 22 */     FilteringRule highIntensity = new FilteringRule("CINT", ">", "method", 7.0D);
/* 23 */     FilteringRule highDispersion = new FilteringRule("CDISP", ">", "method", 0.5D);
/*    */     
/* 25 */     return (new AndComposedFilteringRule(highIntensity, highDispersion)).applyFilter(aMethod);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface aMethod) {
/* 30 */     boolean deepNestingOfCalls = (new FilteringRule("MAXNESTING", ">", "method", 1.0D)).applyFilter(aMethod);
/*    */     
/* 32 */     return (deepNestingOfCalls && functionCallsManyFromManyUnrelatedClasses(aMethod));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\methods\ExtensiveCoupling.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */