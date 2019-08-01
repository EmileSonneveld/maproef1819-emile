/*    */ package classes.lrg.insider.plugins.properties.memoria.packages;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.plugins.properties.memoria.packages.ModelClassesInPackage;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AbstractRatio
/*    */   extends PropertyComputer
/*    */ {
/* 16 */   public AbstractRatio() { super("AR", "Abstract Ratio", "package", "numerical"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 20 */     double noc = ((Double)anEntity.getProperty("NOC").getValue()).doubleValue();
/*    */     
/* 22 */     if (noc == 0.0D) return new ResultEntity(1.0D); 
/* 23 */     double abstractClasses = (new ModelClassesInPackage()).buildGroupEntity(anEntity).applyFilter("is abstract").size();
/*    */     
/* 25 */     return new ResultEntity(abstractClasses / noc);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\packages\AbstractRatio.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */