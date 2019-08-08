/*    */ package classes.lrg.insider.plugins.core.properties.hismo.functions;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.metamodel.Address;
/*    */ import lrg.memoria.hismo.core.FunctionHistory;
/*    */ 
/*    */ public class Address extends PropertyComputer {
/* 10 */   public Address() { super("Address", "", new String[] { "method history", "global function history" }, "string"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 14 */     if (!(anEntity instanceof FunctionHistory)) {
/* 15 */       return null;
/*    */     }
/* 17 */     return new ResultEntity(Address.buildFor((FunctionHistory)anEntity));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\hismo\functions\Address.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */