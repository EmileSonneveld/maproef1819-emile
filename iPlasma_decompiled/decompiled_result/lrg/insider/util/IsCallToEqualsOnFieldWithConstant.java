/*    */ package classes.lrg.insider.util;
/*    */ import lrg.insider.util.IsCallToEqualsOnFieldWithConstant;
/*    */ import lrg.insider.util.MemoriaMembrainEntityEquivalence;
/*    */ import lrg.membrain.representation.instructionSet.Param;
/*    */ import lrg.membrain.representation.instructionSet.VirtualCall;
/*    */ import lrg.memoria.core.Method;
/*    */ import lrg.util.filters.ExpressionFilterVisitor;
/*    */ import lrg.util.filters.IsNonStaticFieldValueOfThis;
/*    */ 
/*    */ public class IsCallToEqualsOnFieldWithConstant extends ExpressionFilterVisitor {
/* 11 */   private IsNonStaticFieldValueOfThis q1 = new IsNonStaticFieldValueOfThis();
/*    */   
/*    */   public void visitVirtualCall(VirtualCall instr) {
/* 14 */     Method method = MemoriaMembrainEntityEquivalence.convertMembrainMethodToMemoriaMethod(instr.getInvokedMethod());
/* 15 */     if (method == null)
/* 16 */       return;  if (method.getName().equals("equals") && 
/* 17 */       instr.getNumerOfParameters() == 1 && this.q1.compute(instr.getTarget().getFirstArgument())) {
/* 18 */       Param p = instr.getParameter(0);
/* 19 */       if (p.getFirstArgument().getUTypeReference().produceMemoriaAddress().equals("java.lang.String"))
/* 20 */         this.status = true; 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\inside\\util\IsCallToEqualsOnFieldWithConstant.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */