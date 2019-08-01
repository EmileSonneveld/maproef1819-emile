/*    */ package classes.lrg.insider.plugins.properties.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.filters.composed.NotComposedFilteringRule;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.plugins.filters.memoria.methods.IsConstructor;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WOC
/*    */   extends PropertyComputer
/*    */ {
/* 20 */   public WOC() { super("WOC", "Weight of a Class", "class", "numerical"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 24 */     if (!(anEntity instanceof lrg.memoria.core.Class)) {
/* 25 */       return null;
/*    */     }
/*    */ 
/*    */     
/* 29 */     NotComposedFilteringRule notComposedFilteringRule = new NotComposedFilteringRule(new IsConstructor());
/*    */     
/* 31 */     GroupEntity publicMethods = anEntity.contains("method group").applyFilter("is public").applyFilter(notComposedFilteringRule);
/* 32 */     GroupEntity publicAttr = anEntity.contains("attribute group").applyFilter("not encapsulated");
/*    */ 
/*    */     
/* 35 */     GroupEntity accessorMethods = publicMethods.applyFilter("is accessor");
/*    */     
/* 37 */     double accessorM = (accessorMethods.size() + publicAttr.size()) + 0.0D;
/* 38 */     double publicM = (publicMethods.size() + publicAttr.size()) + 0.0D;
/*    */ 
/*    */     
/* 41 */     if (publicM == 0.0D) return new ResultEntity(0.0D);
/*    */ 
/*    */     
/* 44 */     return new ResultEntity(1.0D - accessorM / publicM);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\WOC.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */