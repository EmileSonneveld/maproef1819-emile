/*    */ package classes.lrg.insider.plugins.core.properties.memoria.inheritance;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.insider.metamodel.Address;
/*    */ import lrg.memoria.core.InheritanceRelation;
/*    */ 
/*    */ public class Address
/*    */   extends PropertyComputer {
/* 11 */   public Address() { super("Address", "The address of the inheritance relation", "inheritance relation", "string"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 16 */     if (!(anEntity instanceof InheritanceRelation)) {
/* 17 */       return null;
/*    */     }
/*    */     
/* 20 */     InheritanceRelation rel = (InheritanceRelation)anEntity;
/* 21 */     return new ResultEntity(String.valueOf(Address.buildFor(rel.getSuperClass())) + "<->" + Address.buildFor(rel.getSubClass()));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\memoria\inheritance\Address.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */