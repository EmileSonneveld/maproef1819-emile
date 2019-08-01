/*    */ package classes.lrg.insider.plugins.filters.memoria.classes;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.common.abstractions.plugins.filters.composed.AndComposedFilteringRule;
/*    */ import lrg.common.abstractions.plugins.filters.composed.NotComposedFilteringRule;
/*    */ import lrg.insider.plugins.filters.memoria.classes.IsAbstract;
/*    */ import lrg.insider.plugins.filters.memoria.classes.IsInterface;
/*    */ import lrg.memoria.core.Class;
/*    */ 
/*    */ public class FutileAbstractPipeline extends FilteringRule {
/* 13 */   public FutileAbstractPipeline() { super(new Descriptor("Futile Abstract Pipeline", "class")); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface aClass) {
/* 19 */     if (!(aClass instanceof Class)) return false; 
/* 20 */     if (!((Class)aClass).isAbstract()) return false;
/*    */     
/* 22 */     NotComposedFilteringRule notComposedFilteringRule = new NotComposedFilteringRule(new IsInterface());
/* 23 */     AndComposedFilteringRule andComposedFilteringRule = new AndComposedFilteringRule(notComposedFilteringRule, new IsAbstract());
/*    */     
/* 25 */     GroupEntity modelAbstractAncestors = aClass.uses("all ancestors").applyFilter("model class").applyFilter(andComposedFilteringRule);
/* 26 */     if (modelAbstractAncestors.size() != 1) return false; 
/* 27 */     return (aClass.isUsed("all descendants").size() == 1);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\classes\FutileAbstractPipeline.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */