/*    */ package lrg.memoria.core;
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
/*    */ public class FunctionBody
/*    */   extends Body
/*    */ {
/* 19 */   public FunctionBody(Method scope) { super(scope); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 26 */   public FunctionBody() { super(Method.getUnknownMethod()); }
/*    */ 
/*    */ 
/*    */   
/* 30 */   public Function getScope() { return (Function)super.getScope(); }
/*    */ 
/*    */ 
/*    */   
/* 34 */   public void accept(ModelVisitor v) { v.visitFunctionBody(this); }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 38 */     StringBuffer myStr = new StringBuffer("\n\t\t\t - function body:");
/* 39 */     myStr.append(super.toString());
/* 40 */     return new String(myStr);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\FunctionBody.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */