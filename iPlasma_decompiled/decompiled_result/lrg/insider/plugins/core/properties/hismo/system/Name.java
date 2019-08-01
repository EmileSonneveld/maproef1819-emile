/*    */ package classes.lrg.insider.plugins.core.properties.hismo.system;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Name
/*    */   extends PropertyComputer
/*    */ {
/* 12 */   public Name() { super("Name", "The name of the entity", "system history", "string"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 17 */     if (!(anEntity instanceof lrg.memoria.hismo.core.SystemHistory)) {
/* 18 */       return null;
/*    */     }
/* 20 */     return new ResultEntity(anEntity.getName());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\hismo\system\Name.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */