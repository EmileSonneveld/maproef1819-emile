/*    */ package classes.lrg.insider.plugins.filters.memoria.methods;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.common.abstractions.plugins.filters.composed.NotComposedFilteringRule;
/*    */ import lrg.insider.plugins.filters.memoria.methods.IsAbstract;
/*    */ import lrg.insider.plugins.filters.memoria.methods.IsEmpty;
/*    */ import lrg.insider.plugins.filters.memoria.methods.IsOverriden;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LiskovViolator
/*    */   extends FilteringRule
/*    */ {
/* 17 */   public LiskovViolator() { super(new Descriptor("Liskov Violator", "method")); }
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface aMethod) {
/* 21 */     if (!(new IsEmpty()).applyFilter(aMethod)) return false; 
/* 22 */     if (!(new IsOverriden()).applyFilter(aMethod)) return false;
/*    */     
/* 24 */     NotComposedFilteringRule notComposedFilteringRule1 = new NotComposedFilteringRule(new IsAbstract());
/* 25 */     NotComposedFilteringRule notComposedFilteringRule2 = new NotComposedFilteringRule(new IsEmpty());
/*    */     
/* 27 */     return (aMethod.getGroup("methods overriden").applyFilter(notComposedFilteringRule1).applyFilter(notComposedFilteringRule2).size() > 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\methods\LiskovViolator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */