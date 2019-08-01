/*    */ package classes.lrg.insider.plugins.properties.membrain.client;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ 
/*    */ public class NoOfInternalClients
/*    */   extends PropertyComputer
/*    */ {
/* 10 */   public NoOfInternalClients() { super("NoOfInternalClients", "NoOfInternalClients", "method", "numerical"); }
/*    */ 
/*    */ 
/*    */   
/* 14 */   public ResultEntity compute(AbstractEntityInterface anEntity) { return new ResultEntity(anEntity.getGroup("internal clients group").size()); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\membrain\client\NoOfInternalClients.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */