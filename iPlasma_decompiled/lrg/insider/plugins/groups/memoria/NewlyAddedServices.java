/*    */ package classes.lrg.insider.plugins.groups.memoria;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.filters.composed.NotComposedFilteringRule;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.insider.plugins.filters.memoria.methods.IsAccessor;
/*    */ import lrg.insider.plugins.filters.memoria.methods.IsConstructor;
/*    */ import lrg.insider.plugins.filters.memoria.methods.IsOverriden;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NewlyAddedServices
/*    */   extends GroupBuilder
/*    */ {
/* 23 */   public NewlyAddedServices() { super("Newly Added Methods (NAS)", "", "class"); }
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface measuredClass) {
/* 27 */     GroupEntity allAncestors = measuredClass.uses("all ancestors").applyFilter("model class");
/* 28 */     if (allAncestors.size() == 0) return new ArrayList();
/*    */     
/* 30 */     NotComposedFilteringRule notComposedFilteringRule1 = new NotComposedFilteringRule(new IsOverriden());
/* 31 */     NotComposedFilteringRule notComposedFilteringRule2 = new NotComposedFilteringRule(new IsConstructor());
/* 32 */     NotComposedFilteringRule notComposedFilteringRule3 = new NotComposedFilteringRule(new IsAccessor());
/*    */ 
/*    */     
/* 35 */     GroupEntity myPublicMethods = measuredClass.contains("method group").applyFilter("is public");
/*    */ 
/*    */     
/* 38 */     return myPublicMethods.applyFilter(notComposedFilteringRule2).applyFilter(notComposedFilteringRule1).applyFilter(notComposedFilteringRule3).getElements();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\memoria\NewlyAddedServices.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */