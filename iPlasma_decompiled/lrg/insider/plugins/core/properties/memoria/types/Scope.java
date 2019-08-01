/*    */ package classes.lrg.insider.plugins.core.properties.memoria.types;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.memoria.core.Type;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Scope
/*    */   extends PropertyComputer
/*    */ {
/* 16 */   public Scope() { super("scope", "The scope of the type", "type", "entity"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 20 */     if (!(anEntity instanceof Type)) {
/* 21 */       return null;
/*    */     }
/* 23 */     return new ResultEntity(((Type)anEntity).getScope());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\memoria\types\Scope.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */