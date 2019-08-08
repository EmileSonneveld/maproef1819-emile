/*    */ package classes.lrg.insider.util;
/*    */ import java.util.ArrayList;
/*    */ import lrg.analyses.reaching_definitions.ReachingDefinitionsComputedValue;
/*    */ import lrg.insider.util.IsEqualityTestBetweenAccessorOfThisAndConstant;
/*    */ import lrg.insider.util.MemoriaMembrainEntityEquivalence;
/*    */ import lrg.membrain.representation.instructionSet.Equal;
/*    */ import lrg.memoria.core.Method;
/*    */ import lrg.util.filters.IsCallToNonStaticMethod;
/*    */ import lrg.util.filters.IsConstantValue;
/*    */ import lrg.util.utilities.ReachingDefinitionUtilities;
/*    */ 
/*    */ public class IsEqualityTestBetweenAccessorOfThisAndConstant extends ExpressionFilterVisitor {
/* 13 */   private IsCallToNonStaticMethod q1 = new IsCallToNonStaticMethod();
/* 14 */   private IsConstantValue q2 = new IsConstantValue();
/* 15 */   private IsStaticFinalFieldValue q3 = new IsStaticFinalFieldValue();
/* 16 */   private IsThisReference q4 = new IsThisReference();
/* 17 */   private IsLocalVariableValue q5 = new IsLocalVariableValue();
/*    */   
/*    */   public void visitEqual(Equal instr) {
/* 20 */     if (this.q1.compute(instr.getFirstArgument()) && (this.q2.compute(instr.getSecondArgument()) || this.q3.compute(instr.getSecondArgument())) && 
/* 21 */       this.q4.compute(this.q1.getServerObject())) {
/* 22 */       Method theMethod = MemoriaMembrainEntityEquivalence.convertMembrainMethodToMemoriaMethod(this.q1.getInvokedMethod());
/* 23 */       if (theMethod != null && ((Boolean)theMethod.getProperty("is accessor").getValue()).booleanValue()) {
/* 24 */         this.status = true;
/*    */       }
/*    */       
/*    */       return;
/*    */     } 
/* 29 */     if ((this.q3.compute(instr.getSecondArgument()) || this.q2.compute(instr.getFirstArgument())) && this.q1.compute(instr.getSecondArgument()) && 
/* 30 */       this.q4.compute(this.q1.getServerObject())) {
/* 31 */       Method theMethod = MemoriaMembrainEntityEquivalence.convertMembrainMethodToMemoriaMethod(this.q1.getInvokedMethod());
/* 32 */       if (theMethod != null && ((Boolean)theMethod.getProperty("is accessor").getValue()).booleanValue()) {
/* 33 */         this.status = true;
/*    */       }
/*    */       
/*    */       return;
/*    */     } 
/* 38 */     if (this.q5.compute(instr.getFirstArgument()) && (this.q2.compute(instr.getSecondArgument()) || this.q3.compute(instr.getSecondArgument()))) {
/* 39 */       ArrayList<ReachingDefinitionsComputedValue> rd = ReachingDefinitionUtilities.definitionOf(instr, this.q5.getVariable());
/* 40 */       for (int i = 0; i < rd.size(); i++) {
/* 41 */         if (((ReachingDefinitionsComputedValue)rd.get(i)).getAssignedExpression() != null && this.q1.compute(((ReachingDefinitionsComputedValue)rd.get(i)).getAssignedExpression()) && this.q4.compute(this.q1.getServerObject())) {
/* 42 */           Method theMethod = MemoriaMembrainEntityEquivalence.convertMembrainMethodToMemoriaMethod(this.q1.getInvokedMethod());
/* 43 */           if (theMethod != null && ((Boolean)theMethod.getProperty("is accessor").getValue()).booleanValue()) {
/* 44 */             this.status = true;
/*    */           }
/*    */           return;
/*    */         } 
/*    */       } 
/*    */     } 
/* 50 */     if ((this.q3.compute(instr.getSecondArgument()) || this.q2.compute(instr.getFirstArgument())) && this.q5.compute(instr.getSecondArgument())) {
/* 51 */       ArrayList<ReachingDefinitionsComputedValue> rd = ReachingDefinitionUtilities.definitionOf(instr, this.q5.getVariable());
/* 52 */       for (int i = 0; i < rd.size(); i++) {
/* 53 */         if (((ReachingDefinitionsComputedValue)rd.get(i)).getAssignedExpression() != null && this.q1.compute(((ReachingDefinitionsComputedValue)rd.get(i)).getAssignedExpression()) && this.q4.compute(this.q1.getServerObject())) {
/* 54 */           Method theMethod = MemoriaMembrainEntityEquivalence.convertMembrainMethodToMemoriaMethod(this.q1.getInvokedMethod());
/* 55 */           if (theMethod != null && ((Boolean)theMethod.getProperty("is accessor").getValue()).booleanValue())
/* 56 */             this.status = true; 
/*    */           return;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\inside\\util\IsEqualityTestBetweenAccessorOfThisAndConstant.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */