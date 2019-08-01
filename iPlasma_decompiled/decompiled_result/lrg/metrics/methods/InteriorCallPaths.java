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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InteriorCallPaths
/*    */   extends MethodMeasure
/*    */ {
/*    */   public Result measure(Method actMethod) {
/* 42 */     FunctionBody mb = actMethod.getBody();
/*    */     
/* 44 */     HashSet hs = new HashSet();
/* 45 */     int count = 0;
/* 46 */     if (mb != null) {
/* 47 */       ModelElementList modelElementList = mb.getCallList();
/* 48 */       int size = modelElementList.size();
/* 49 */       for (int i = 0; i < size; i++) {
/* 50 */         Method calledMethod = ((Call)modelElementList.get(i)).getMethod();
/* 51 */         String calledClassName = calledMethod.getScope().getFullName();
/* 52 */         String actClassName = actMethod.getScope().getFullName();
/* 53 */         if (calledClassName.equals(actClassName) && 
/* 54 */           !hs.contains(calledMethod)) {
/* 55 */           hs.add(calledMethod);
/* 56 */           count++;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 61 */     return new NumericalResult(actMethod, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\methods\InteriorCallPaths.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */