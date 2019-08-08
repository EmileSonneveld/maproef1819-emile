/*    */ package lrg.metrics.classes;
/*    */ 
/*    */ import java.util.TreeSet;
/*    */ import lrg.memoria.core.Call;
/*    */ import lrg.memoria.core.Class;
/*    */ import lrg.memoria.core.Function;
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
/*    */ public class ResponseForAClass
/*    */   extends ClassMeasure
/*    */ {
/*    */   public Result measure(Class act_class) {
/* 42 */     ModelElementList modelElementList = act_class.getMethodList();
/*    */ 
/*    */     
/* 45 */     TreeSet calledMethodNamesSet = new TreeSet();
/*    */ 
/*    */ 
/*    */     
/* 49 */     for (int j = 0; j < modelElementList.size(); j++) {
/* 50 */       Function currentMethod = (Function)modelElementList.get(j);
/* 51 */       if (currentMethod.getStatute() == 1) {
/* 52 */         calledMethodNamesSet.add(currentMethod.getFullName());
/*    */       }
/*    */     } 
/* 55 */     int size = modelElementList.size();
/* 56 */     for (int i = 0; i < size; i++) {
/* 57 */       Function act_method = (Function)modelElementList.get(i);
/* 58 */       FunctionBody act_body = act_method.getBody();
/*    */       
/* 60 */       if (act_body != null) {
/* 61 */         ModelElementList modelElementList1 = act_body.getCallList();
/*    */ 
/*    */         
/* 64 */         for (int j = 0; j < modelElementList1.size(); j++) {
/* 65 */           Call currentCall = (Call)modelElementList1.get(j);
/* 66 */           Method method = currentCall.getMethod();
/*    */           
/* 68 */           calledMethodNamesSet.add(method.getFullName());
/*    */         } 
/*    */       } 
/*    */     } 
/* 72 */     return new NumericalResult(act_class, calledMethodNamesSet.size());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\classes\ResponseForAClass.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */