/*    */ package classes.lrg.insider.plugins.properties.memoria.methods;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.common.abstractions.plugins.filters.composed.NotComposedFilteringRule;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.memoria.core.Method;
/*    */ 
/*    */ public class PureCodeReuse
/*    */   extends PropertyComputer
/*    */ {
/* 14 */   public PureCodeReuse() { super("PCR", "Pure Code Reuse", "method", "numerical"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface m) {
/* 19 */     if (!(m instanceof Method)) {
/* 20 */       return new ResultEntity(-1.0D);
/*    */     }
/*    */     
/* 23 */     Method theMethod = (Method)m;
/*    */     
/* 25 */     GroupEntity allConcreteDescendants = theMethod.belongsTo("class").getGroup("all descendants").applyFilter(
/* 26 */         new NotComposedFilteringRule(
/* 27 */           new FilteringRule("is abstract", "is true", "class", null)));
/*    */     
/* 29 */     GroupEntity allConcretePure = theMethod.getGroup("applies-to group").applyFilter(
/* 30 */         new NotComposedFilteringRule(
/* 31 */           new FilteringRule("is abstract", "is true", "class", null)));
/* 32 */     allConcretePure = allConcretePure.exclude(theMethod.belongsTo("class"));
/*    */     
/* 34 */     if (allConcreteDescendants.size() == 0) {
/* 35 */       return new ResultEntity(-1.0D);
/*    */     }
/*    */     
/* 38 */     if (theMethod.isConstructor() || theMethod.isPrivate() || theMethod.isStatic() || theMethod.isProtected() || theMethod.isPackage()) {
/* 39 */       return new ResultEntity(-1.0D);
/*    */     }
/*    */     
/* 42 */     if (theMethod.isAbstract()) {
/* 43 */       return new ResultEntity(0.0D);
/*    */     }
/*    */     
/* 46 */     return new ResultEntity(allConcretePure.size() / allConcreteDescendants.size());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\methods\PureCodeReuse.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */