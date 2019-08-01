/*    */ package classes.lrg.insider.util;
/*    */ import lrg.insider.util.IsEqualityTestBetweenServerFieldAndConstant;
/*    */ import lrg.membrain.representation.instructionSet.Equal;
/*    */ import lrg.util.filters.IsConstantValue;
/*    */ import lrg.util.filters.IsNonStaticFieldValue;
/*    */ import lrg.util.filters.IsStaticFinalFieldValue;
/*    */ 
/*    */ public class IsEqualityTestBetweenServerFieldAndConstant extends ExpressionFilterVisitor {
/*  9 */   private IsNonStaticFieldValue q1 = new IsNonStaticFieldValue();
/* 10 */   private IsConstantValue q2 = new IsConstantValue();
/* 11 */   private IsStaticFinalFieldValue q3 = new IsStaticFinalFieldValue();
/* 12 */   private IsThisReference q4 = new IsThisReference();
/*    */   private UNameReference ref;
/*    */   
/*    */   public void visitEqual(Equal instr) {
/* 16 */     if (this.q1.compute(instr.getFirstArgument()) && (this.q2.compute(instr.getSecondArgument()) || this.q3.compute(instr.getSecondArgument())) && 
/* 17 */       !this.q4.compute(this.q1.getServerObject())) {
/* 18 */       this.ref = this.q1.getField();
/* 19 */       this.status = true;
/*    */       
/*    */       return;
/*    */     } 
/* 23 */     if ((this.q2.compute(instr.getFirstArgument()) || this.q3.compute(instr.getSecondArgument())) && this.q1.compute(instr.getSecondArgument()) && 
/* 24 */       !this.q4.compute(this.q1.getServerObject())) {
/* 25 */       this.ref = this.q1.getField();
/* 26 */       this.status = true;
/*    */       return;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 33 */   public UNameReference getField() { return this.ref; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\inside\\util\IsEqualityTestBetweenServerFieldAndConstant.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */