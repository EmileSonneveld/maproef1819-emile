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
/*    */ 
/*    */ 
/*    */ public class LocalVariables
/*    */   extends MethodMeasure
/*    */ {
/*    */   public Result measure(Method m) {
/* 35 */     int count = 0;
/*    */     
/* 37 */     FunctionBody mb = m.getBody();
/* 38 */     if (mb != null) {
/* 39 */       ModelElementList modelElementList = mb.getLocalVarList();
/* 40 */       int size = modelElementList.size();
/* 41 */       for (int i = 0; i < size; i++) {
/* 42 */         LocalVariable act_var = (LocalVariable)modelElementList.get(i);
/* 43 */         if (!act_var.isFinal())
/* 44 */           count++; 
/*    */       } 
/*    */     } 
/* 47 */     return new NumericalResult(m, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\methods\LocalVariables.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */