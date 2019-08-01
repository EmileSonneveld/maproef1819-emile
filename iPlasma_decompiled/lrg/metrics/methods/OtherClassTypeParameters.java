/*    */ package lrg.metrics.methods;
/*    */ 
/*    */ import lrg.memoria.core.DataAbstraction;
/*    */ import lrg.memoria.core.Method;
/*    */ import lrg.memoria.core.ModelElementList;
/*    */ import lrg.memoria.core.Parameter;
/*    */ import lrg.memoria.core.Type;
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
/*    */ public class OtherClassTypeParameters
/*    */   extends MethodMeasure
/*    */ {
/*    */   public Result measure(Method m) {
/* 33 */     int count = 0;
/* 34 */     ModelElementList modelElementList = m.getParameterList();
/*    */ 
/*    */     
/* 37 */     DataAbstraction actScope = (DataAbstraction)m.getScope();
/* 38 */     if (modelElementList != null)
/* 39 */       for (int i = 0; i < modelElementList.size(); i++) {
/* 40 */         Parameter param = (Parameter)modelElementList.get(i);
/* 41 */         Type para_type = param.getType();
/* 42 */         if (para_type instanceof lrg.memoria.core.Class && para_type != actScope)
/* 43 */           count++; 
/*    */       }  
/* 45 */     return new NumericalResult(m, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\methods\OtherClassTypeParameters.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */