/*    */ package classes.lrg.insider.plugins.filters.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntity;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.common.abstractions.plugins.filters.composed.AndComposedFilteringRule;
/*    */ import lrg.common.abstractions.plugins.filters.composed.NotComposedFilteringRule;
/*    */ import lrg.insider.plugins.core.filters.memoria.ModelClassFilter;
/*    */ import lrg.insider.plugins.filters.memoria.classes.IsInterface;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class HeavyHierarchy
/*    */   extends FilteringRule
/*    */ {
/* 21 */   public HeavyHierarchy() { super(new Descriptor("Heavy Hierarchy", "class")); }
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface aClass) {
/* 25 */     AndComposedFilteringRule andComposedFilteringRule = new AndComposedFilteringRule(new ModelClassFilter(), 
/* 26 */         new NotComposedFilteringRule(new IsInterface()));
/*    */     
/* 28 */     if (!andComposedFilteringRule.applyFilter(aClass)) return false;
/*    */     
/* 30 */     double nrOfDescendants = ((Double)aClass.getProperty("NOD").getValue()).doubleValue();
/* 31 */     GroupEntity allClasses = aClass.getGroup("all descendants").union((AbstractEntity)aClass);
/*    */     
/* 33 */     double avgWMC = ((Double)allClasses.getProperty("WMC").aggregate("avg").getValue()).doubleValue();
/*    */     
/* 35 */     return (nrOfDescendants >= 4.0D && avgWMC >= 30.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\classes\HeavyHierarchy.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */