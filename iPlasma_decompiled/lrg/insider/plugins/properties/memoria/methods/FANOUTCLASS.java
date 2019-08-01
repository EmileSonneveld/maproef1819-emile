/*    */ package classes.lrg.insider.plugins.properties.memoria.methods;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FANOUTCLASS
/*    */   extends PropertyComputer
/*    */ {
/* 17 */   public FANOUTCLASS() { super("FANOUTCLASS", "Number of classes from which methods are called", new String[] { "method", "global function" }, "numerical"); }
/*    */ 
/*    */ 
/*    */   
/* 21 */   public ResultEntity compute(AbstractEntityInterface anEntity) { return new ResultEntity(((GroupEntity)anEntity.uses("operations called").belongsTo("class")).distinct().size()); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\methods\FANOUTCLASS.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */