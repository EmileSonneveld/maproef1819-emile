/*    */ package classes.lrg.insider.plugins.properties.memoria.methods;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.filters.composed.NotComposedFilteringRule;
/*    */ import lrg.common.abstractions.plugins.groups.GroupEntityBuilder;
/*    */ import lrg.insider.plugins.filters.memoria.methods.IsAccessor;
/*    */ import lrg.insider.plugins.filters.memoria.variables.IsConstant;
/*    */ 
/*    */ 
/*    */ public class AccessedModelData
/*    */   extends GroupEntityBuilder
/*    */ {
/* 14 */   public AccessedModelData(String name, String descr) { super(name, descr, "method"); }
/*    */ 
/*    */ 
/*    */   
/* 18 */   public AccessedModelData() { this("accessed model data", "used attributes or called accessor methods"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public GroupEntity buildGroupEntity(AbstractEntityInterface aMethod) {
/* 23 */     IsAccessor isAccessor1 = new IsAccessor();
/* 24 */     NotComposedFilteringRule notComposedFilteringRule = new NotComposedFilteringRule(new IsConstant());
/*    */     
/* 26 */     allAccesses = aMethod.uses("variables accessed").applyFilter("is attribute").applyFilter(notComposedFilteringRule);
/*    */     
/* 28 */     return allAccesses.union(aMethod.uses("operations called").applyFilter(isAccessor1));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\methods\AccessedModelData.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */