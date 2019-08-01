/*    */ package classes.lrg.insider.plugins.core.properties.memoria.system;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.metamodel.Address;
/*    */ 
/*    */ public class Address extends PropertyComputer {
/*  9 */   public Address() { super("Address", "The address of the system", "system", "string"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 13 */     if (!(anEntity instanceof lrg.memoria.core.System)) {
/* 14 */       return null;
/*    */     }
/* 16 */     return new ResultEntity(Address.buildForRoot());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\memoria\system\Address.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */