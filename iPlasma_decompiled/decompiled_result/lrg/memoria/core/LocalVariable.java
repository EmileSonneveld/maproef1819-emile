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
/*    */ public class LocalVariable
/*    */   extends Variable
/*    */ {
/*    */   private boolean hasBlockScope = false, isStatic = false;
/*    */   private boolean isExceptionParameter = false;
/*    */   
/*    */   public LocalVariable(String name) {
/* 19 */     super(name);
/* 20 */     setScope(Body.getUnkonwnBody().getCodeStripe());
/*    */   }
/*    */   
/*    */   public LocalVariable(String name, CodeStripe scope) {
/* 24 */     super(name);
/* 25 */     setScope(scope);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public LocalVariable(LocalVariable lv) {
/* 32 */     super(lv);
/* 33 */     this.hasBlockScope = lv.hasBlockScope;
/* 34 */     this.isStatic = lv.isStatic;
/* 35 */     this.isExceptionParameter = lv.isExceptionParameter;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 46 */   public Body getScope() { return ((CodeStripe)this.scope).getParentBody(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 51 */   public void setScope(Scope scope) { this.scope = (CodeStripe)scope; }
/*    */ 
/*    */ 
/*    */   
/* 55 */   public CodeStripe getStripe() { return (CodeStripe)this.scope; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 63 */   public boolean isBlock() { return this.hasBlockScope; }
/*    */ 
/*    */ 
/*    */   
/* 67 */   public void setBlock() { this.hasBlockScope = true; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 74 */   public void setStatic() { this.isStatic = true; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 83 */   public boolean isStatic() { return this.isStatic; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 90 */   public boolean isExParam() { return this.isExceptionParameter; }
/*    */ 
/*    */ 
/*    */   
/* 94 */   public void setExParam() { this.isExceptionParameter = true; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 99 */   public void accept(ModelVisitor v) { v.visitLocalVar(this); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\LocalVariable.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */