/*    */ package classes.lrg.insider.plugins.core.properties.memoria.component;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.metamodel.Address;
/*    */ import lrg.memoria.core.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Address
/*    */   extends PropertyComputer
/*    */ {
/* 16 */   public Address() { super("Address", "The address of the component", "component", "string"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 20 */     if (!(anEntity instanceof Component)) return null;
/*    */     
/* 22 */     return new ResultEntity(Address.buildFor((Component)anEntity));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\memoria\component\Address.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */