/*    */ package classes.lrg.insider.plugins.properties.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ 
/*    */ public class DAC
/*    */   extends PropertyComputer {
/* 10 */   public DAC() { super("DAC", "Data Abstraction Coupling", "class", "numerical"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface aClass) {
/* 14 */     GroupEntity resultGroup = aClass.contains("attribute group").applyFilter("is user defined");
/*    */     
/* 16 */     resultGroup = resultGroup.uses("type of variable").applyFilter("model class");
/* 17 */     return new ResultEntity(resultGroup.distinct().size());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\DAC.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */