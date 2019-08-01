/*    */ package classes.lrg.insider.plugins.core.properties.memoria.functions;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.metamodel.Address;
/*    */ import lrg.memoria.core.Function;
/*    */ 
/*    */ public class Address extends PropertyComputer {
/*  9 */   public Address() { super("Address", "", new String[] { "method", "global function" }, "string"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 13 */     if (!(anEntity instanceof Function)) {
/* 14 */       return null;
/*    */     }
/* 16 */     return new ResultEntity(Address.buildFor((Function)anEntity));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\memoria\functions\Address.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */