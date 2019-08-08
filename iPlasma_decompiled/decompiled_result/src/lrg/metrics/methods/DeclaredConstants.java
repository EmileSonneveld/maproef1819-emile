/*    */ package lrg.metrics.methods;
/*    */ 
/*    */ import lrg.memoria.core.FunctionBody;
/*    */ import lrg.memoria.core.LocalVariable;
/*    */ import lrg.memoria.core.Method;
/*    */ import lrg.memoria.core.ModelElementList;
/*    */ import lrg.metrics.NumericalResult;
/*    */ import lrg.metrics.Result;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DeclaredConstants
/*    */   extends MethodMeasure
/*    */ {
/*    */   public Result measure(Method m) {
/* 33 */     int count = 0;
/*    */     
/* 35 */     FunctionBody mb = m.getBody(); ModelElementList modelElementList;
/* 36 */     if (mb != null && (
/* 37 */       modelElementList = mb.getLocalVarList()) != null) {
/* 38 */       int size = modelElementList.size();
/* 39 */       for (int i = 0; i < size; i++) {
/* 40 */         LocalVariable lv = (LocalVariable)modelElementList.get(i);
/* 41 */         if (lv.isFinal())
/* 42 */           count++; 
/*    */       } 
/*    */     } 
/* 45 */     return new NumericalResult(m, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\methods\DeclaredConstants.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */