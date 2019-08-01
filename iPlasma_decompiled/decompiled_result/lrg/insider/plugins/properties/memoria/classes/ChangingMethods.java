/*    */ package classes.lrg.insider.plugins.properties.memoria.classes;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.filters.composed.NotComposedFilteringRule;
/*    */ import lrg.common.abstractions.plugins.groups.GroupEntityBuilder;
/*    */ import lrg.insider.plugins.filters.memoria.methods.IsConstructor;
/*    */ import lrg.insider.plugins.filters.memoria.methods.IsStatic;
/*    */ import lrg.insider.plugins.properties.memoria.classes.ChangingMethods;
/*    */ 
/*    */ 
/*    */ 
/*    */ class ChangingMethods
/*    */   extends GroupEntityBuilder
/*    */ {
/* 17 */   public ChangingMethods() { super("group of changing methods", "methods that would be potentiall affected by a change in analyzed class", "class"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public GroupEntity buildGroupEntity(AbstractEntityInterface measuredClass) {
/* 22 */     NotComposedFilteringRule notComposedFilteringRule1 = new NotComposedFilteringRule(new IsStatic());
/* 23 */     NotComposedFilteringRule notComposedFilteringRule2 = new NotComposedFilteringRule(new IsConstructor());
/*    */     
/* 25 */     GroupEntity interestingMethods = measuredClass.getGroup("method group").applyFilter(notComposedFilteringRule2).applyFilter(notComposedFilteringRule1);
/* 26 */     GroupEntity clientMethods = interestingMethods.isUsed("operations calling me");
/*    */     
/* 28 */     GroupEntity relatedMethods = measuredClass.contains("method group");
/* 29 */     relatedMethods = relatedMethods.union(measuredClass.getGroup("all ancestors").applyFilter("model class").getGroup("method group"));
/* 30 */     relatedMethods = relatedMethods.union(measuredClass.getGroup("all descendants").applyFilter("model class").getGroup("method group"));
/* 31 */     return clientMethods.exclude(relatedMethods);
/*    */   }
/*    */ 
/*    */   
/* 35 */   public ArrayList buildGroup(AbstractEntityInterface measuredClass) { return buildGroupEntity(measuredClass).getElements(); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\ChangingMethods.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */