/*    */ package classes.lrg.insider.plugins.properties.memoria.system;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
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
/*    */ public class AVG_NDD
/*    */   extends PropertyComputer
/*    */ {
/* 19 */   public AVG_NDD() { super("AVG_NDD", "Average Number of Children for model classes", "system", "numerical"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 23 */     NotComposedFilteringRule notComposedFilteringRule = new NotComposedFilteringRule(new IsInterface());
/* 24 */     return anEntity.getGroup("class group").applyFilter("model class").applyFilter(notComposedFilteringRule).getProperty("NODD").aggregate("avg");
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\system\AVG_NDD.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */