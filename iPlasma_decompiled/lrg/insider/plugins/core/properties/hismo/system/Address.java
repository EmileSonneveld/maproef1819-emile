/*    */ package classes.lrg.insider.plugins.core.properties.hismo.system;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ 
/*    */ 
/*    */ public class Address
/*    */   extends PropertyComputer
/*    */ {
/* 11 */   public Address() { super("Address", "The address of the system history", "system history", "string"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 16 */     if (!(anEntity instanceof lrg.memoria.hismo.core.SystemHistory)) {
/* 17 */       return null;
/*    */     }
/* 19 */     return new ResultEntity("~root");
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\hismo\system\Address.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */