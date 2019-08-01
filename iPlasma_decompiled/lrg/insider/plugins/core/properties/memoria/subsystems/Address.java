/*    */ package classes.lrg.insider.plugins.core.properties.memoria.subsystems;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.metamodel.Address;
/*    */ import lrg.memoria.core.Subsystem;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Address
/*    */   extends PropertyComputer
/*    */ {
/* 18 */   public Address() { super("Address", "The address of the subsystem", "subsystem", "string"); }
/*    */ 
/*    */ 
/*    */   
/* 22 */   public ResultEntity compute(AbstractEntityInterface anEntity) { return new ResultEntity(Address.buildFor((Subsystem)anEntity)); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\memoria\subsystems\Address.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */