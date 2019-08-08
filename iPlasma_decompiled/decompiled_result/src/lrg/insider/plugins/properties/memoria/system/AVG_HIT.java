/*    */ package classes.lrg.insider.plugins.properties.memoria.system;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.filters.composed.NotComposedFilteringRule;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.plugins.filters.memoria.classes.IsInterface;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AVG_HIT
/*    */   extends PropertyComputer
/*    */ {
/* 20 */   public AVG_HIT() { super("AVG_HIT", "Average Height of Inheritance for model classes", "system", "numerical"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 24 */     NotComposedFilteringRule notComposedFilteringRule = new NotComposedFilteringRule(new IsInterface());
/* 25 */     GroupEntity allClasses = anEntity.getGroup("class group");
/* 26 */     GroupEntity rootClasses = allClasses.applyFilter("is root-class");
/* 27 */     ResultEntity hitResults = rootClasses.getProperty("HIT");
/* 28 */     return hitResults.aggregate("avg");
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\system\AVG_HIT.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */