/*    */ package classes.lrg.insider.plugins.core.properties.hismo.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.metamodel.Address;
/*    */ import lrg.memoria.hismo.core.ClassHistory;
/*    */ 
/*    */ public class Address extends PropertyComputer {
/* 10 */   public Address() { super("Address", "The address of the class history", "class history", "string"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 14 */     if (!(anEntity instanceof ClassHistory)) {
/* 15 */       return null;
/*    */     }
/* 17 */     return new ResultEntity(Address.buildFor((ClassHistory)anEntity));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\hismo\classes\Address.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */