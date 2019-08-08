/*    */ package classes.lrg.insider.plugins.core.properties.memoria.variables;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.memoria.core.Body;
/*    */ import lrg.memoria.core.GlobalVariable;
/*    */ import lrg.memoria.core.Scope;
/*    */ import lrg.memoria.core.Variable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Scope
/*    */   extends PropertyComputer
/*    */ {
/* 19 */   public Scope() { super("scope", "The scope of the variable", new String[] { "global variable", "attribute", "local variable", "parameter" }, "entity"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 23 */     if (!(anEntity instanceof Variable)) return null;
/*    */     
/* 25 */     if (anEntity instanceof GlobalVariable) {
/* 26 */       return new ResultEntity(((GlobalVariable)anEntity).getPackage());
/*    */     }
/* 28 */     Scope aScope = ((Variable)anEntity).getScope();
/* 29 */     if (aScope instanceof Body) aScope = ((Body)aScope).getScope(); 
/* 30 */     return new ResultEntity(aScope);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\memoria\variables\Scope.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */