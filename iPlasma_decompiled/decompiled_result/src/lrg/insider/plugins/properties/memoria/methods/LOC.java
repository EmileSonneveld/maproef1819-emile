/*    */ package classes.lrg.insider.plugins.properties.memoria.methods;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.memoria.core.Function;
/*    */ import lrg.memoria.core.FunctionBody;
/*    */ 
/*    */ public class LOC
/*    */   extends PropertyComputer
/*    */ {
/* 12 */   public LOC() { super("LOC", "Lines of Code", new String[] { "method", "global function" }, "numerical"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 17 */     if (!(anEntity instanceof Function)) {
/* 18 */       return null;
/*    */     }
/* 20 */     FunctionBody functionBody = ((Function)anEntity).getBody();
/* 21 */     if (functionBody == null) return new ResultEntity(0.0D);
/*    */     
/* 23 */     return new ResultEntity(functionBody.getNumberOfLines());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\methods\LOC.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */