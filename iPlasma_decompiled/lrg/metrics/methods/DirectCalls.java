/*    */ package lrg.metrics.methods;
/*    */ 
/*    */ import java.util.HashSet;
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
/*    */ public class DirectCalls
/*    */   extends MethodMeasure
/*    */ {
/*    */   public Result measure(Method actMethod) {
/* 35 */     FunctionBody mb = actMethod.getBody();
/*    */ 
/*    */     
/* 38 */     HashSet hs = new HashSet();
/* 39 */     int count = 0;
/* 40 */     if (mb != null) {
/* 41 */       ModelElementList modelElementList = mb.getCallList();
/* 42 */       for (int i = 0; i < modelElementList.size(); i++) {
/* 43 */         Method calledMethod = ((Call)modelElementList.get(i)).getMethod();
/*    */         
/* 45 */         if (!hs.contains(calledMethod)) {
/* 46 */           hs.add(calledMethod);
/* 47 */           count++;
/*    */         } 
/*    */       } 
/*    */     } 
/* 51 */     return new NumericalResult(actMethod, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\methods\DirectCalls.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */