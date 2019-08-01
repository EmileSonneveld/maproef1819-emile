/*    */ package classes.lrg.insider.plugins.core.properties.memoria.annotation;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.metamodel.Address;
/*    */ import lrg.memoria.core.Annotation;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Address
/*    */   extends PropertyComputer
/*    */ {
/* 16 */   public Address() { super("Address", "The address of the component", "annotation", "string"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 20 */     if (!(anEntity instanceof Annotation)) return null;
/*    */     
/* 22 */     return new ResultEntity(Address.buildFor((Annotation)anEntity));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\memoria\annotation\Address.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */