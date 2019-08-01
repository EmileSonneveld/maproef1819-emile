/*    */ package classes.lrg.insider.plugins.core.properties.memoria.variables;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.memoria.core.Variable;
/*    */ 
/*    */ public class Name extends PropertyComputer {
/*  9 */   public Name() { super("Name", "Name of the variable", new String[] { "global variable", "attribute", "local variable", "parameter" }, "string"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 14 */     if (!(anEntity instanceof Variable)) {
/* 15 */       return new ResultEntity("");
/*    */     }
/* 17 */     return new ResultEntity(((Variable)anEntity).getName());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\memoria\variables\Name.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */