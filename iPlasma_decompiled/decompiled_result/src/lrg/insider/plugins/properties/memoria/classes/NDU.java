/*    */ package classes.lrg.insider.plugins.properties.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ 
/*    */ public class NDU
/*    */   extends PropertyComputer {
/*  9 */   public NDU() { super("NDU", "Number of Descendants Used", "class", "numerical"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 13 */     double resultValue = 0.0D;
/* 14 */     if (!(anEntity instanceof lrg.memoria.core.Class)) return new ResultEntity(resultValue);
/*    */     
/* 16 */     resultValue = anEntity.contains("method group").getGroup("subclasses dependencies").distinct().size();
/*    */     
/* 18 */     return new ResultEntity(resultValue);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\NDU.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */