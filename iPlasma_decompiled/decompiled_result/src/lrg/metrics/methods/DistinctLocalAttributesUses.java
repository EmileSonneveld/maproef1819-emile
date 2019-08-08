/*    */ package lrg.metrics.methods;
/*    */ 
/*    */ import java.util.HashSet;
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
/*    */ public class DistinctLocalAttributesUses
/*    */   extends MethodMeasure
/*    */ {
/*    */   public Result measure(Method m) {
/* 38 */     FunctionBody mb = m.getBody();
/*    */     
/* 40 */     HashSet usedAttributes = new HashSet();
/*    */ 
/*    */ 
/*    */     
/* 44 */     int count = 0;
/* 45 */     if (mb != null) {
/* 46 */       ModelElementList modelElementList = mb.getAccessList();
/* 47 */       int size = modelElementList.size();
/* 48 */       for (int i = 0; i < size; i++) {
/* 49 */         Access access = (Access)modelElementList.get(i);
/* 50 */         Variable var = access.getVariable();
/* 51 */         if (var instanceof Attribute && ((Attribute)var).getScope() == m.getScope() && 
/* 52 */           !usedAttributes.contains(var)) {
/* 53 */           usedAttributes.add(var);
/* 54 */           count++;
/*    */         } 
/*    */       } 
/*    */     } 
/* 58 */     return new NumericalResult(m, count);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\methods\DistinctLocalAttributesUses.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */