/*    */ package classes.lrg.insider.plugins.core.properties.memoria.annotationinstance;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.metamodel.Address;
/*    */ import lrg.memoria.core.AnnotationInstance;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Address
/*    */   extends PropertyComputer
/*    */ {
/* 16 */   public Address() { super("Address", "The address of the component", "annotation instance", "string"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 20 */     if (!(anEntity instanceof AnnotationInstance)) return null;
/*    */     
/* 22 */     return new ResultEntity(Address.buildFor((AnnotationInstance)anEntity));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\memoria\annotationinstance\Address.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */