/*    */ package lrg.common.abstractions.plugins.filters.composed;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ 
/*    */ public class AndComposedFilteringRule extends FilteringRule {
/*    */   private FilteringRule firstFilter;
/*    */   private FilteringRule secondFilter;
/*    */   
/*    */   public AndComposedFilteringRule(FilteringRule oneFilter, FilteringRule otherFilter) {
/* 12 */     super(new Descriptor("(" + oneFilter.getDescriptorObject().getName() + " and " + otherFilter.getDescriptorObject().getName() + ")", oneFilter.getIntersectionofEntityTypeNames(otherFilter)));
/*    */     
/* 14 */     this.firstFilter = oneFilter;
/* 15 */     this.secondFilter = otherFilter;
/*    */   }
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface anEntity) {
/* 19 */     if (this.firstFilter == null || this.secondFilter == null) return false; 
/* 20 */     return (this.firstFilter.applyFilter(anEntity) && this.secondFilter.applyFilter(anEntity));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\common.jar!\lrg\common\abstractions\plugins\filters\composed\AndComposedFilteringRule.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */