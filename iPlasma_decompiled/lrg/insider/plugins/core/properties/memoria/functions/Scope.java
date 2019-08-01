/*    */ package classes.lrg.insider.plugins.core.properties.memoria.functions;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.memoria.core.Function;
/*    */ import lrg.memoria.core.GlobalFunction;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Scope
/*    */   extends PropertyComputer
/*    */ {
/* 17 */   public Scope() { super("scope", "The scope of the type", new String[] { "method", "global function" }, "entity"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 21 */     if (!(anEntity instanceof Function)) {
/* 22 */       return null;
/*    */     }
/* 24 */     if (anEntity instanceof GlobalFunction) {
/* 25 */       return new ResultEntity(((GlobalFunction)anEntity).getPackage());
/*    */     }
/*    */     
/* 28 */     return new ResultEntity(((Function)anEntity).getScope());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\memoria\functions\Scope.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */