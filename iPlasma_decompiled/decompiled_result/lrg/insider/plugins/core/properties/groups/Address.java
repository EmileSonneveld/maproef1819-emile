/*    */ package classes.lrg.insider.plugins.core.properties.groups;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ 
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
/* 17 */   public Address() { super("Address", "The address of the group", "group", "string"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 21 */     if (!(anEntity instanceof lrg.common.abstractions.entities.GroupEntity)) {
/* 22 */       return null;
/*    */     }
/* 24 */     String tmp = anEntity.getName();
/*    */     
/* 26 */     tmp = tmp.replaceAll(" ", ".");
/* 27 */     return new ResultEntity(tmp);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\groups\Address.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */