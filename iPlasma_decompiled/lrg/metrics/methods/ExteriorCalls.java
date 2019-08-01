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
/*    */ public class ExteriorCalls
/*    */   extends MethodMeasure
/*    */ {
/*    */   public Result measure(Method actMethod) {
/* 37 */     FunctionBody mb = actMethod.getBody();
/*    */     
/* 39 */     int count = 0;
/*    */     
/* 41 */     if (mb != null) {
/* 42 */       ModelElementList modelElementList = mb.getCallList();
/* 43 */       for (int i = 0; i < modelElementList.size(); i++) {
/* 44 */         Call currentCall = (Call)modelElementList.get(i);
/* 45 */         Method calledMethod = currentCall.getMethod();
/* 46 */         String calledClassName = calledMethod.getScope().getFullName();
/* 47 */         String actClassName = actMethod.getScope().getFullName();
/* 48 */         if (!calledClassName.equals(actClassName))
/* 49 */           count += currentCall.getCount(); 
/*    */       } 
/*    */     } 
/* 52 */     return new NumericalResult(actMethod, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\methods\ExteriorCalls.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */