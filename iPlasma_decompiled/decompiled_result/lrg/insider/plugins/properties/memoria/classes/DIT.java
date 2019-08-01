/*    */ package classes.lrg.insider.plugins.properties.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.memoria.core.Class;
/*    */ import lrg.metrics.NumericalResult;
/*    */ import lrg.metrics.classes.DepthOfInheritanceTree;
/*    */ 
/*    */ public class DIT
/*    */   extends PropertyComputer
/*    */ {
/* 13 */   public DIT() { super("DIT", "Depth of Inheritance Tree", "class", "numerical"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 17 */     if (!(anEntity instanceof Class)) {
/* 18 */       return null;
/*    */     }
/* 20 */     NumericalResult aResult = (NumericalResult)(new DepthOfInheritanceTree()).measure((Class)anEntity);
/* 21 */     return new ResultEntity(aResult.getValue());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\DIT.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */