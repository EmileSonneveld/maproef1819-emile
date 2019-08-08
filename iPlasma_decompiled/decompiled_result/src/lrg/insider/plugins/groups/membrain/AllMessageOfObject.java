/*    */ package classes.lrg.insider.plugins.groups.membrain;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.common.abstractions.plugins.filters.composed.NotComposedFilteringRule;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ 
/*    */ 
/*    */ public class AllMessageOfObject
/*    */   extends GroupBuilder
/*    */ {
/* 14 */   public AllMessageOfObject() { super("virtual methods of object", "All methods that can be invoked on an instance", "class"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface entity) {
/* 19 */     GroupEntity allMethods = entity.getGroup("all ancestors").applyFilter("model class").getGroup("method group").distinct();
/* 20 */     allMethods.addAllDistinct(entity.getGroup("method group"));
/* 21 */     allMethods = allMethods.applyFilter(new NotComposedFilteringRule(new FilteringRule("is constructor", "is true", "method")));
/* 22 */     allMethods = allMethods.applyFilter(new NotComposedFilteringRule(new FilteringRule("is private", "is true", "method")));
/* 23 */     allMethods = allMethods.applyFilter(new NotComposedFilteringRule(new FilteringRule("is static", "is true", "method")));
/* 24 */     return allMethods.getElements();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\membrain\AllMessageOfObject.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */