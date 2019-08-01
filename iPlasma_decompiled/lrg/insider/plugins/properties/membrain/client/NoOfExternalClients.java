/*    */ package classes.lrg.insider.plugins.properties.membrain.client;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ 
/*    */ public class NoOfExternalClients
/*    */   extends PropertyComputer
/*    */ {
/* 10 */   public NoOfExternalClients() { super("NoOfExternalClients", "NoOfExternalClients", "method", "numerical"); }
/*    */ 
/*    */ 
/*    */   
/* 14 */   public ResultEntity compute(AbstractEntityInterface anEntity) { return new ResultEntity(anEntity.getGroup("external clients group").size()); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\membrain\client\NoOfExternalClients.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */