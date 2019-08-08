/*    */ package classes.lrg.insider.plugins.properties.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ 
/*    */ public class NOA
/*    */   extends PropertyComputer {
/*  9 */   public NOA() { super("NOA", "Number of Attributes", "class", "numerical"); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 14 */   public ResultEntity compute(AbstractEntityInterface anEntity) { return new ResultEntity(anEntity.contains("attribute group").size()); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\classes\NOA.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */