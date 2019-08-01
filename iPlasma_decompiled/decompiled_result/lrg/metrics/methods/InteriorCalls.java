/*    */ package lrg.metrics.methods;
/*    */ 
/*    */ import lrg.memoria.core.Call;
/*    */ import lrg.memoria.core.FunctionBody;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InteriorCalls
/*    */   extends MethodMeasure
/*    */ {
/*    */   public Result measure(Method actMethod) {
/* 38 */     FunctionBody mb = actMethod.getBody();
/*    */ 
/*    */     
/* 41 */     int count = 0;
/* 42 */     if (mb != null) {
/* 43 */       ModelElementList modelElementList = mb.getCallList();
/* 44 */       int size = modelElementList.size();
/* 45 */       for (int i = 0; i < size; i++) {
/* 46 */         Call currentCall = (Call)modelElementList.get(i);
/* 47 */         Method calledMethod = currentCall.getMethod();
/* 48 */         String calledClassName = calledMethod.getScope().getFullName();
/* 49 */         String actClassName = actMethod.getScope().getFullName();
/* 50 */         if (calledClassName.equals(actClassName))
/* 51 */           count += currentCall.getCount(); 
/*    */       } 
/*    */     } 
/* 54 */     return new NumericalResult(actMethod, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\methods\InteriorCalls.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */