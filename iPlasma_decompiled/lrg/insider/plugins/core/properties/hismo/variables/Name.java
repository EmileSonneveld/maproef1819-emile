/*    */ package classes.lrg.insider.plugins.core.properties.hismo.variables;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ 
/*    */ public class Name
/*    */   extends PropertyComputer
/*    */ {
/* 10 */   public Name() { super("Name", "The name of the variable history", new String[] { "global variable history", "attribute history" }, "string"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 15 */     if (!(anEntity instanceof lrg.memoria.hismo.core.VariableHistory)) {
/* 16 */       return null;
/*    */     }
/* 18 */     return new ResultEntity(anEntity.getName());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\hismo\variables\Name.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */