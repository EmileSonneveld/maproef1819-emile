/*    */ package lrg.metrics.methods;
/*    */ 
/*    */ import lrg.memoria.core.DataAbstraction;
/*    */ import lrg.memoria.core.FunctionBody;
/*    */ import lrg.memoria.core.Method;
/*    */ import lrg.memoria.core.ModelElementList;
/*    */ import lrg.memoria.core.Type;
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
/*    */ public class OtherClassTypeVariables
/*    */   extends MethodMeasure
/*    */ {
/*    */   public Result measure(Method m) {
/* 36 */     FunctionBody mb = m.getBody();
/*    */ 
/*    */     
/* 39 */     DataAbstraction actScope = (DataAbstraction)m.getScope();
/* 40 */     int count = 0;
/* 41 */     if (mb != null) {
/* 42 */       ModelElementList modelElementList = mb.getLocalVarList();
/* 43 */       int size = modelElementList.size();
/* 44 */       for (int i = 0; i < size; i++) {
/* 45 */         Variable var = (Variable)modelElementList.get(i);
/* 46 */         Type actType = var.getType();
/* 47 */         if (actType instanceof lrg.memoria.core.Class && actType != actScope)
/* 48 */           count++; 
/*    */       } 
/*    */     } 
/* 51 */     return new NumericalResult(m, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\methods\OtherClassTypeVariables.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */