/*    */ package classes.lrg.insider.plugins.core.properties.memoria.packages;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.metamodel.Address;
/*    */ import lrg.memoria.core.Package;
/*    */ 
/*    */ public class Address extends PropertyComputer {
/*  9 */   public Address() { super("Address", "The address of the package", "package", "string"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 13 */     if (!(anEntity instanceof Package)) {
/* 14 */       return null;
/*    */     }
/* 16 */     return new ResultEntity(Address.buildFor((Package)anEntity));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\memoria\packages\Address.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */