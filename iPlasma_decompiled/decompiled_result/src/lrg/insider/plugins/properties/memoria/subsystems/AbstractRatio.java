/*    */ package classes.lrg.insider.plugins.properties.memoria.subsystems;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AbstractRatio
/*    */   extends PropertyComputer
/*    */ {
/* 16 */   public AbstractRatio() { super("AR", "Abstract Ratio", "subsystem", "numerical"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 21 */     double noc = ((Double)anEntity.getGroup("package group").getProperty("NOC").aggregate("sum").getValue()).doubleValue();
/*    */     
/* 23 */     if (noc == 0.0D) return new ResultEntity(1.0D); 
/* 24 */     double abstractClasses = anEntity.getGroup("package group").getGroup("class group").applyFilter("is abstract").size();
/*    */     
/* 26 */     return new ResultEntity(abstractClasses / noc);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\subsystems\AbstractRatio.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */