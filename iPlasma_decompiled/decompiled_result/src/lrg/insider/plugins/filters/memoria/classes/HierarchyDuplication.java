/*    */ package classes.lrg.insider.plugins.filters.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.common.abstractions.plugins.filters.composed.AndComposedFilteringRule;
/*    */ import lrg.insider.plugins.core.filters.memoria.ModelClassFilter;
/*    */ 
/*    */ public class HierarchyDuplication
/*    */   extends FilteringRule {
/* 11 */   public HierarchyDuplication() { super(new Descriptor("Hierarchy Duplication", "class")); }
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface aClass) {
/* 15 */     AndComposedFilteringRule andComposedFilteringRule = new AndComposedFilteringRule(new ModelClassFilter(), 
/* 16 */         new FilteringRule("HDUPCLS", ">", "class", 0.0D));
/*    */     
/* 18 */     return andComposedFilteringRule.applyFilter(aClass);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\classes\HierarchyDuplication.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */