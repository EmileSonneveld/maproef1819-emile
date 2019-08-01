/*    */ package lrg.memoria.core;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Parameter
/*    */   extends Variable
/*    */ {
/*    */   public Parameter(String name) {
/* 14 */     super(name);
/* 15 */     setScope(Function.getUnknownFunction());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Parameter(String name, Type dataType, Function scope) {
/* 22 */     super(name, dataType);
/* 23 */     this.scope = scope;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 31 */   public Function getScope() { return (Function)this.scope; }
/*    */ 
/*    */ 
/*    */   
/* 35 */   public void accept(ModelVisitor v) { v.visitParameter(this); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\Parameter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */