/*    */ package lrg.memoria.core;
/*    */ 
/*    */ public class GlobalVariable extends Variable {
/*    */   private Package currentPackage;
/*  5 */   private Variable refersTo = null;
/*    */ 
/*    */   
/*    */   private boolean isStatic = false;
/*    */ 
/*    */   
/*    */   public GlobalVariable(String name) {
/* 12 */     super(name);
/* 13 */     setScope(Namespace.getUnknownNamespace());
/* 14 */     setPackage(Package.getUnknownPackage());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public GlobalVariable(GlobalVariable gf) {
/* 21 */     super(gf);
/* 22 */     this.currentPackage = gf.currentPackage;
/* 23 */     this.refersTo = gf.refersTo;
/* 24 */     this.isStatic = gf.isStatic;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 33 */   public Namespace getScope() { return (Namespace)this.scope; }
/*    */ 
/*    */ 
/*    */   
/* 37 */   public Package getPackage() { return this.currentPackage; }
/*    */ 
/*    */ 
/*    */   
/* 41 */   public void setPackage(Package packageScope) { this.currentPackage = packageScope; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 48 */   public void setStatic() { this.isStatic = true; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 56 */   public boolean isStatic() { return this.isStatic; }
/*    */ 
/*    */ 
/*    */   
/* 60 */   public void setRefersTo(Variable refersTo) { this.refersTo = refersTo; }
/*    */ 
/*    */ 
/*    */   
/* 64 */   public Variable getRefersTo() { return this.refersTo; }
/*    */ 
/*    */ 
/*    */   
/* 68 */   public void accept(ModelVisitor mv) { mv.visitGlobalVar(this); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\GlobalVariable.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */