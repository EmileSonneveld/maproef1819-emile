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
/*    */ public class LocalAttributesUses
/*    */   extends MethodMeasure
/*    */ {
/*    */   public Result measure(Method m) {
/* 36 */     FunctionBody mb = m.getBody();
/*    */ 
/*    */ 
/*    */     
/* 40 */     int count = 0;
/* 41 */     if (mb != null) {
/* 42 */       ModelElementList modelElementList = mb.getAccessList();
/* 43 */       for (int i = 0; i < modelElementList.size(); i++) {
/* 44 */         Access access = (Access)modelElementList.get(i);
/* 45 */         Variable var = access.getVariable();
/* 46 */         if (var instanceof Attribute && ((Attribute)var).getScope() == m.getScope())
/* 47 */           count += access.getCount(); 
/*    */       } 
/*    */     } 
/* 50 */     return new NumericalResult(m, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\methods\LocalAttributesUses.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */