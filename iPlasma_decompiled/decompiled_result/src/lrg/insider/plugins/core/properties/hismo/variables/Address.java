/*    */ package classes.lrg.insider.plugins.core.properties.hismo.variables;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.metamodel.Address;
/*    */ import lrg.memoria.hismo.core.VariableHistory;
/*    */ 
/*    */ public class Address extends PropertyComputer {
/* 10 */   public Address() { super("Address", "The address of the variable history", new String[] { "global variable history", "attribute history" }, "string"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 14 */     if (!(anEntity instanceof VariableHistory)) {
/* 15 */       return null;
/*    */     }
/* 17 */     return new ResultEntity(Address.buildFor((VariableHistory)anEntity));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\hismo\variables\Address.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */