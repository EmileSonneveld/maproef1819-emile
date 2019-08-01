/*    */ package lrg.metrics.methods;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import lrg.memoria.core.Call;
/*    */ import lrg.memoria.core.DataAbstraction;
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
/*    */ 
/*    */ public class ExteriorCallPaths
/*    */   extends MethodMeasure
/*    */ {
/*    */   public Result measure(Method actMethod) {
/* 44 */     FunctionBody mb = actMethod.getBody();
/*    */     
/* 46 */     HashSet hs = new HashSet();
/* 47 */     int count = 0;
/* 48 */     if (mb != null) {
/* 49 */       ModelElementList modelElementList = mb.getCallList();
/* 50 */       for (int i = 0; i < modelElementList.size(); i++) {
/* 51 */         Method calledMethod = ((Call)modelElementList.get(i)).getMethod();
/* 52 */         DataAbstraction calledClass = (DataAbstraction)calledMethod.getScope();
/* 53 */         DataAbstraction actClass = (DataAbstraction)actMethod.getScope();
/* 54 */         if (calledClass != actClass && 
/* 55 */           !hs.contains(calledMethod)) {
/* 56 */           hs.add(calledMethod);
/* 57 */           count++;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 62 */     return new NumericalResult(actMethod, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\methods\ExteriorCallPaths.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */