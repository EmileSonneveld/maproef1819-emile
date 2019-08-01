/*    */ package lrg.memoria.core;
/*    */ 
/*    */ public class PointerToFunction
/*    */   extends Function
/*    */ {
/*  6 */   public PointerToFunction(String name) { super(name); }
/*    */ 
/*    */ 
/*    */   
/* 10 */   public FunctionPointer getScope() { return (FunctionPointer)super.getScope(); }
/*    */ 
/*    */ 
/*    */   
/* 14 */   public void accept(ModelVisitor mv) { mv.visitPointerToFunction(this); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\PointerToFunction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */