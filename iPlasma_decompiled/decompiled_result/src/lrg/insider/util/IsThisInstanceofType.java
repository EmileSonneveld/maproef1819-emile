/*    */ package classes.lrg.insider.util;
/*    */ 
/*    */ import lrg.insider.util.IsThisInstanceofType;
/*    */ import lrg.membrain.representation.instructionSet.InstanceOf;
/*    */ import lrg.util.filters.ExpressionFilterVisitor;
/*    */ import lrg.util.filters.IsThisReference;
/*    */ 
/*    */ public class IsThisInstanceofType extends ExpressionFilterVisitor {
/*  9 */   private IsThisReference q1 = new IsThisReference();
/*    */ 
/*    */   
/* 12 */   public void visitInstanceOf(InstanceOf instr) { this.status = this.q1.compute(instr.getFirstArgument()); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\inside\\util\IsThisInstanceofType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */