/*    */ package classes.lrg.insider.plugins.properties.memoria.methods;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.memoria.core.Function;
/*    */ import lrg.memoria.core.FunctionBody;
/*    */ 
/*    */ public class Cyclo
/*    */   extends PropertyComputer {
/* 11 */   public Cyclo() { super("CYCLO", "Cyclomatic complexity", new String[] { "method", "global function" }, "numerical"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 16 */     if (!(anEntity instanceof Function)) {
/* 17 */       return null;
/*    */     }
/* 19 */     Function aFunction = (Function)anEntity;
/*    */     
/* 21 */     FunctionBody functionBody = aFunction.getBody();
/*    */     
/* 23 */     if (functionBody == null) return new ResultEntity(0.0D); 
/* 24 */     return new ResultEntity(functionBody.getCyclomaticNumber());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\methods\Cyclo.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */