/*    */ package lrg.common.abstractions.plugins.filters.composed;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ 
/*    */ public class OrComposedFilteringRule
/*    */   extends FilteringRule {
/*    */   private FilteringRule firstFilter;
/*    */   private FilteringRule secondFilter;
/*    */   
/*    */   public OrComposedFilteringRule(FilteringRule oneFilter, FilteringRule otherFilter) {
/* 13 */     super(new Descriptor("(" + oneFilter.getDescriptorObject().getName() + " or " + otherFilter.getDescriptorObject().getName() + ")", oneFilter.getUnionofEntityTypeNames(otherFilter)));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 22 */     this.firstFilter = oneFilter;
/* 23 */     this.secondFilter = otherFilter;
/*    */   }
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface anEntity) {
/* 27 */     if (this.firstFilter == null || this.secondFilter == null) return false; 
/* 28 */     return !(!this.firstFilter.applyFilter(anEntity) && !this.secondFilter.applyFilter(anEntity));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\common.jar!\lrg\common\abstractions\plugins\filters\composed\OrComposedFilteringRule.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */