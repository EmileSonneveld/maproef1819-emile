/*    */ package classes.lrg.insider.plugins.core.properties.membrain.call;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.metamodel.Address;
/*    */ import lrg.membrain.representation.instructionSet.Abstractions.MCall;
/*    */ 
/*    */ public class Address
/*    */   extends PropertyComputer {
/* 11 */   public Address() { super("Address", "The address of the call", "membrain call", "string"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 16 */     if (!(anEntity instanceof MCall)) {
/* 17 */       return null;
/*    */     }
/*    */     
/* 20 */     return new ResultEntity(Address.buildFor((MCall)anEntity));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\membrain\call\Address.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */