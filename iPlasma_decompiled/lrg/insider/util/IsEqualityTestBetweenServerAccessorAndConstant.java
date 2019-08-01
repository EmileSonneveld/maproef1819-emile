/*    */ package classes.lrg.insider.util;
/*    */ import java.util.ArrayList;
/*    */ import lrg.analyses.reaching_definitions.ReachingDefinitionsComputedValue;
/*    */ import lrg.insider.util.IsEqualityTestBetweenServerAccessorAndConstant;
/*    */ import lrg.insider.util.MemoriaMembrainEntityEquivalence;
/*    */ import lrg.membrain.representation.instructionSet.Equal;
/*    */ import lrg.memoria.core.Method;
/*    */ import lrg.util.filters.IsCallToNonStaticMethod;
/*    */ import lrg.util.filters.IsConstantValue;
/*    */ import lrg.util.filters.IsStaticFinalFieldValue;
/*    */ import lrg.util.filters.IsThisReference;
/*    */ import lrg.util.utilities.ReachingDefinitionUtilities;
/*    */ 
/*    */ public class IsEqualityTestBetweenServerAccessorAndConstant extends ExpressionFilterVisitor {
/* 15 */   private IsCallToNonStaticMethod q1 = new IsCallToNonStaticMethod();
/* 16 */   private IsConstantValue q2 = new IsConstantValue();
/* 17 */   private IsStaticFinalFieldValue q3 = new IsStaticFinalFieldValue();
/* 18 */   private IsThisReference q4 = new IsThisReference();
/* 19 */   private IsLocalVariableValue q5 = new IsLocalVariableValue();
/*    */   
/*    */   public void visitEqual(Equal instr) {
/* 22 */     if (this.q1.compute(instr.getFirstArgument()) && (this.q2.compute(instr.getSecondArgument()) || this.q3.compute(instr.getSecondArgument())) && 
/* 23 */       !this.q4.compute(this.q1.getServerObject())) {
/* 24 */       Method theMethod = MemoriaMembrainEntityEquivalence.convertMembrainMethodToMemoriaMethod(this.q1.getInvokedMethod());
/* 25 */       if (theMethod != null && ((Boolean)theMethod.getProperty("is accessor").getValue()).booleanValue()) {
/* 26 */         this.status = true;
/*    */       }
/*    */       
/*    */       return;
/*    */     } 
/* 31 */     if ((this.q3.compute(instr.getSecondArgument()) || this.q2.compute(instr.getFirstArgument())) && this.q1.compute(instr.getSecondArgument()) && 
/* 32 */       !this.q4.compute(this.q1.getServerObject())) {
/* 33 */       Method theMethod = MemoriaMembrainEntityEquivalence.convertMembrainMethodToMemoriaMethod(this.q1.getInvokedMethod());
/* 34 */       if (theMethod != null && ((Boolean)theMethod.getProperty("is accessor").getValue()).booleanValue()) {
/* 35 */         this.status = true;
/*    */       }
/*    */       
/*    */       return;
/*    */     } 
/* 40 */     if (this.q5.compute(instr.getFirstArgument()) && (this.q2.compute(instr.getSecondArgument()) || this.q3.compute(instr.getSecondArgument()))) {
/* 41 */       ArrayList<ReachingDefinitionsComputedValue> rd = ReachingDefinitionUtilities.definitionOf(instr, this.q5.getVariable());
/* 42 */       for (int i = 0; i < rd.size(); i++) {
/* 43 */         if (((ReachingDefinitionsComputedValue)rd.get(i)).getAssignedExpression() != null && this.q1.compute(((ReachingDefinitionsComputedValue)rd.get(i)).getAssignedExpression()) && !this.q4.compute(this.q1.getServerObject())) {
/* 44 */           Method theMethod = MemoriaMembrainEntityEquivalence.convertMembrainMethodToMemoriaMethod(this.q1.getInvokedMethod());
/* 45 */           if (theMethod != null && ((Boolean)theMethod.getProperty("is accessor").getValue()).booleanValue()) {
/* 46 */             this.status = true;
/*    */           }
/*    */           return;
/*    */         } 
/*    */       } 
/*    */     } 
/* 52 */     if ((this.q3.compute(instr.getSecondArgument()) || this.q2.compute(instr.getFirstArgument())) && this.q5.compute(instr.getSecondArgument())) {
/* 53 */       ArrayList<ReachingDefinitionsComputedValue> rd = ReachingDefinitionUtilities.definitionOf(instr, this.q5.getVariable());
/* 54 */       for (int i = 0; i < rd.size(); i++) {
/* 55 */         if (((ReachingDefinitionsComputedValue)rd.get(i)).getAssignedExpression() != null && this.q1.compute(((ReachingDefinitionsComputedValue)rd.get(i)).getAssignedExpression()) && !this.q4.compute(this.q1.getServerObject())) {
/* 56 */           Method theMethod = MemoriaMembrainEntityEquivalence.convertMembrainMethodToMemoriaMethod(this.q1.getInvokedMethod());
/* 57 */           if (theMethod != null && ((Boolean)theMethod.getProperty("is accessor").getValue()).booleanValue())
/* 58 */             this.status = true; 
/*    */           return;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\inside\\util\IsEqualityTestBetweenServerAccessorAndConstant.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */