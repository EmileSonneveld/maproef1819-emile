/*    */ package classes.lrg.insider.plugins.properties.memoria.methods;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ import lrg.memoria.core.Function;
/*    */ import lrg.memoria.core.FunctionBody;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MAXNESTING
/*    */   extends PropertyComputer
/*    */ {
/* 17 */   public MAXNESTING() { super("MAXNESTING", "MAXNESTING (complexity)", "method", "numerical"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 21 */     if (!(anEntity instanceof Function)) return null;
/*    */     
/* 23 */     FunctionBody functionBody = ((Function)anEntity).getBody();
/*    */     
/* 25 */     if (functionBody == null) return new ResultEntity(0.0D);
/*    */     
/* 27 */     return new ResultEntity(functionBody.getMaxNestingLevel());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\methods\MAXNESTING.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */