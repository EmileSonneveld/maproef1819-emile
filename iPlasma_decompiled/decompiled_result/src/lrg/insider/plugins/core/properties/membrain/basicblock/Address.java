/*    */ package classes.lrg.insider.plugins.core.properties.membrain.basicblock;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.metamodel.Address;
/*    */ import lrg.membrain.representation.BasicBlock;
/*    */ 
/*    */ public class Address
/*    */   extends PropertyComputer
/*    */ {
/* 12 */   public Address() { super("Address", "The address of the basic block", "basic block", "string"); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 18 */     if (!(anEntity instanceof BasicBlock)) {
/* 19 */       return null;
/*    */     }
/*    */     
/* 22 */     return new ResultEntity(Address.buildFor((BasicBlock)anEntity));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\membrain\basicblock\Address.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */