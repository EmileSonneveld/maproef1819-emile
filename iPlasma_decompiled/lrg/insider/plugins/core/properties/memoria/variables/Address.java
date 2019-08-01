/*    */ package classes.lrg.insider.plugins.core.properties.memoria.variables;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.metamodel.Address;
/*    */ import lrg.memoria.core.Variable;
/*    */ 
/*    */ public class Address extends PropertyComputer {
/*  9 */   public Address() { super("Address", "The address of the variable", new String[] { "global variable", "attribute", "local variable", "parameter" }, "string"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 13 */     if (!(anEntity instanceof Variable)) {
/* 14 */       return null;
/*    */     }
/* 16 */     return new ResultEntity(Address.buildFor((Variable)anEntity));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\memoria\variables\Address.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */