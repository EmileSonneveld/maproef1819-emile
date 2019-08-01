/*    */ package classes.lrg.insider.plugins.groups.memoria;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.filters.composed.AndComposedFilteringRule;
/*    */ import lrg.common.abstractions.plugins.filters.composed.NotComposedFilteringRule;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.insider.plugins.filters.memoria.methods.IsAbstract;
/*    */ import lrg.insider.plugins.filters.memoria.methods.IsOverriden;
/*    */ import lrg.insider.plugins.filters.memoria.methods.IsSpecialization;
/*    */ 
/*    */ public class ClassWithOverwritingMethods
/*    */   extends GroupBuilder
/*    */ {
/* 16 */   public ClassWithOverwritingMethods() { super("overwriting methods", "", "class"); }
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface derivedClass) {
/* 20 */     NotComposedFilteringRule notComposedFilteringRule1 = new NotComposedFilteringRule(new IsSpecialization());
/* 21 */     AndComposedFilteringRule andComposedFilteringRule = new AndComposedFilteringRule(notComposedFilteringRule1, new IsOverriden());
/*    */     
/* 23 */     NotComposedFilteringRule notComposedFilteringRule2 = new NotComposedFilteringRule(new IsAbstract());
/*    */     
/* 25 */     GroupEntity baseclassMethods = derivedClass.uses("base classes").applyFilter("model class")
/* 26 */       .contains("method group").applyFilter(notComposedFilteringRule2);
/*    */     
/* 28 */     if (baseclassMethods.size() < 1) return new ArrayList();
/*    */     
/* 30 */     GroupEntity notSpecializingMethods = derivedClass.getGroup("method group").applyFilter(andComposedFilteringRule);
/*    */     
/* 32 */     return notSpecializingMethods.uses("methods overriden").intersect(baseclassMethods).distinct().getElements();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\memoria\ClassWithOverwritingMethods.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */