/*    */ package lrg.metrics.methods;
/*    */ 
/*    */ import lrg.memoria.core.Access;
/*    */ import lrg.memoria.core.Attribute;
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
/*    */ 
/*    */ public class ExternalAttributesUsed
/*    */   extends MethodMeasure
/*    */ {
/*    */   public Result measure(Method m) {
/* 37 */     FunctionBody mb = m.getBody();
/*    */ 
/*    */ 
/*    */     
/* 41 */     int count = 0;
/* 42 */     if (mb != null) {
/* 43 */       ModelElementList modelElementList = mb.getAccessList();
/* 44 */       int size = modelElementList.size();
/* 45 */       for (int i = 0; i < size; i++) {
/* 46 */         Access access = (Access)modelElementList.get(i);
/* 47 */         Variable var = access.getVariable();
/* 48 */         if (var instanceof Attribute && ((Attribute)var).getScope() != m.getScope())
/* 49 */           count += access.getCount(); 
/*    */       } 
/*    */     } 
/* 52 */     return new NumericalResult(m, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\methods\ExternalAttributesUsed.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */