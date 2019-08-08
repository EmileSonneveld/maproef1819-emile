/*    */ package classes.lrg.insider.plugins.properties.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.filters.composed.NotComposedFilteringRule;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.plugins.filters.memoria.methods.IsConstructor;
/*    */ import lrg.insider.plugins.filters.memoria.methods.IsOverriden;
/*    */ 
/*    */ public class PNAS
/*    */   extends PropertyComputer
/*    */ {
/* 14 */   public PNAS() { super("PNAS", "Percentage Number of Added Services", "class", "numerical"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface measuredClass) {
/* 18 */     GroupEntity allAncestors = measuredClass.uses("all ancestors").applyFilter("model class");
/*    */     
/* 20 */     if (allAncestors.size() == 0) return new ResultEntity(-1.0D);
/*    */     
/* 22 */     NotComposedFilteringRule notComposedFilteringRule1 = new NotComposedFilteringRule(new IsOverriden());
/* 23 */     NotComposedFilteringRule notComposedFilteringRule2 = new NotComposedFilteringRule(new IsConstructor());
/*    */     
/* 25 */     GroupEntity myPublicMethods = measuredClass.contains("method group").applyFilter("is public").applyFilter(notComposedFilteringRule2);
/*    */     
/* 27 */     double countAllPublic = myPublicMethods.size();
/* 28 */     if (countAllPublic == 0.0D) return new ResultEntity(0.0D);
/*    */     
/* 30 */     double countNotOverriden = myPublicMethods.applyFilter(notComposedFilteringRule1).size();
/*    */     
/* 32 */     return new ResultEntity(countNotOverriden / countAllPublic);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\PNAS.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */