/*    */ package lrg.metrics.methods;
/*    */ 
/*    */ import lrg.memoria.core.FunctionBody;
/*    */ import lrg.memoria.core.Method;
/*    */ import lrg.memoria.core.ModelElementList;
/*    */ import lrg.memoria.core.Variable;
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
/*    */ public class ClassTypeLocalVars
/*    */   extends MethodMeasure
/*    */ {
/*    */   public Result measure(Method m) {
/* 34 */     FunctionBody mb = m.getBody();
/*    */     
/* 36 */     int count = 0;
/* 37 */     if (mb != null) {
/* 38 */       ModelElementList modelElementList = mb.getLocalVarList();
/* 39 */       if (modelElementList != null) {
/* 40 */         int size = modelElementList.size();
/* 41 */         for (int i = 0; i < size; i++) {
/* 42 */           Variable v = (Variable)modelElementList.get(i);
/* 43 */           if (v.getType() instanceof lrg.memoria.core.Class)
/* 44 */             count++; 
/*    */         } 
/*    */       } 
/*    */     } 
/* 48 */     return new NumericalResult(m, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\methods\ClassTypeLocalVars.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */