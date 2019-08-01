/*    */ package lrg.common.abstractions.plugins.filters.composed;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ 
/*    */ public class NotComposedFilteringRule
/*    */   extends FilteringRule {
/*    */   private FilteringRule firstFilter;
/*    */   
/*    */   public NotComposedFilteringRule(FilteringRule oneFilter) {
/* 12 */     super(new Descriptor("(not " + oneFilter.getDescriptorObject().getName() + ")", oneFilter.getDescriptorObject().getEntityTypeName()));
/*    */     
/* 14 */     this.firstFilter = oneFilter;
/*    */   }
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface anEntity) {
/* 18 */     if (this.firstFilter == null) return false; 
/* 19 */     return !this.firstFilter.applyFilter(anEntity);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\common.jar!\lrg\common\abstractions\plugins\filters\composed\NotComposedFilteringRule.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */