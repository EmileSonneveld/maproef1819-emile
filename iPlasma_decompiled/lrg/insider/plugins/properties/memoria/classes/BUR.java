/*    */ package classes.lrg.insider.plugins.properties.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.filters.composed.NotComposedFilteringRule;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.plugins.filters.memoria.classes.IsInterface;
/*    */ import lrg.insider.plugins.filters.memoria.methods.IsConstructor;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BUR
/*    */   extends PropertyComputer
/*    */ {
/* 20 */   public BUR() { super("BUR", "Base Class Usage Ratio", "class", "numerical"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 24 */     double totalProtected = 0.0D, usedProtected = 0.0D;
/*    */ 
/*    */     
/* 27 */     GroupEntity baseClasses = anEntity.uses("base classes").applyFilter("model class");
/* 28 */     baseClasses.applyFilter(new NotComposedFilteringRule(new IsInterface()));
/* 29 */     if (baseClasses.size() == 0) return new ResultEntity(1.0D);
/*    */     
/* 31 */     GroupEntity protectedMethods = baseClasses.contains("method group").applyFilter("is protected");
/* 32 */     protectedMethods.applyFilter(new NotComposedFilteringRule(new IsConstructor()));
/* 33 */     GroupEntity protectedAttributes = baseClasses.contains("attribute group").applyFilter("is protected");
/*    */     
/* 35 */     totalProtected = (protectedAttributes.size() + protectedMethods.size());
/*    */     
/* 37 */     if (totalProtected < 3.0D) return new ResultEntity(1.0D);
/*    */     
/* 39 */     GroupEntity usedprotectedMethods = anEntity.uses("operations called").distinct().applyFilter("is protected");
/* 40 */     GroupEntity usedprotectedAttributes = anEntity.uses("variables accessed").distinct().applyFilter("is protected");
/*    */     
/* 42 */     usedProtected = (protectedAttributes.intersect(usedprotectedAttributes).size() + 
/* 43 */       protectedMethods.intersect(usedprotectedMethods).size() + 
/* 44 */       protectedMethods.intersect(anEntity.uses("methods overriden")).size());
/*    */     
/* 46 */     return new ResultEntity(usedProtected / totalProtected);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\BUR.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */