/*    */ package classes.lrg.insider.plugins.properties.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.filters.composed.NotComposedFilteringRule;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.plugins.filters.memoria.variables.IsConstant;
/*    */ 
/*    */ public class NOPA
/*    */   extends PropertyComputer
/*    */ {
/* 12 */   public NOPA() { super("NOPA", "Number of Public Attributes", "class", "numerical"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 17 */     if (!(anEntity instanceof lrg.memoria.core.Class)) {
/* 18 */       return null;
/*    */     }
/* 20 */     NotComposedFilteringRule notComposedFilteringRule = new NotComposedFilteringRule(new IsConstant());
/*    */     
/* 22 */     return new ResultEntity(anEntity.getGroup("attribute group").applyFilter(notComposedFilteringRule).applyFilter("not encapsulated").size());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\NOPA.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */