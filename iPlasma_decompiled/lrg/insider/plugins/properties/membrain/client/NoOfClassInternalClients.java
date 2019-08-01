/*    */ package classes.lrg.insider.plugins.properties.membrain.client;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ 
/*    */ public class NoOfClassInternalClients
/*    */   extends PropertyComputer
/*    */ {
/* 10 */   public NoOfClassInternalClients() { super("NoOfInternalClients", "NoOfInternalClients", "class", "numerical"); }
/*    */ 
/*    */ 
/*    */   
/* 14 */   public ResultEntity compute(AbstractEntityInterface anEntity) { return new ResultEntity(anEntity.getGroup("method group").getGroup("internal clients group").distinct().size()); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\membrain\client\NoOfClassInternalClients.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */