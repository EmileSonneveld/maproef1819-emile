/*    */ package classes.lrg.insider.plugins.filters.memoria.methods;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntity;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.common.abstractions.plugins.filters.composed.NotComposedFilteringRule;
/*    */ import lrg.insider.plugins.filters.memoria.methods.IsAbstract;
/*    */ import lrg.insider.plugins.filters.memoria.methods.ShortcircuitingMethod;
/*    */ 
/*    */ public class ShortcircuitingMethod extends FilteringRule {
/* 13 */   public ShortcircuitingMethod() { super(new Descriptor("Shortcircuiting Method", "method")); }
/*    */ 
/*    */   
/*    */   private boolean isOverridingConcreteMethod(AbstractEntityInterface anEntity) {
/* 17 */     NotComposedFilteringRule notComposedFilteringRule = new NotComposedFilteringRule(new IsAbstract());
/* 18 */     return (anEntity.uses("methods overriden").applyFilter(notComposedFilteringRule).size() > 0);
/*    */   }
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface anEntity) {
/* 22 */     if (!(anEntity instanceof lrg.memoria.core.Method)) return false;
/*    */     
/* 24 */     if (!isOverridingConcreteMethod(anEntity)) return false; 
/* 25 */     GroupEntity myCallers = (GroupEntity)anEntity.getGroup("operations calling me")
/* 26 */       .belongsTo("class");
/*    */     
/* 28 */     AbstractEntity scopeClass = anEntity.belongsTo("class");
/* 29 */     GroupEntity allDescendants = scopeClass.getGroup("all descendants").union(scopeClass);
/*    */     
/* 31 */     return (myCallers.exclude(allDescendants).size() > 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\methods\ShortcircuitingMethod.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */