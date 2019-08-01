/*    */ package lrg.memoria.core;
/*    */ 
/*    */ public class GlobalFunction extends Function {
/*  4 */   private Package packageScope = Package.getUnknownPackage();
/*    */   protected boolean isStatic = false;
/*    */   
/*    */   public GlobalFunction(String name) {
/*  8 */     super(name);
/*  9 */     setScope(Namespace.getUnknownNamespace());
/* 10 */     setFunctionBody((FunctionBody)Body.getUnkonwnBody());
/*    */   }
/*    */   
/*    */   public GlobalFunction(GlobalFunction gf) {
/* 14 */     super(gf);
/* 15 */     this.packageScope = gf.packageScope;
/* 16 */     this.isStatic = gf.isStatic;
/*    */   }
/*    */ 
/*    */   
/* 20 */   public Package getPackage() { return this.packageScope; }
/*    */ 
/*    */ 
/*    */   
/* 24 */   public void setPackage(Package packageScope) { this.packageScope = packageScope; }
/*    */ 
/*    */ 
/*    */   
/* 28 */   public void accept(ModelVisitor mv) { mv.visitGlobalFunction(this); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 36 */   public void setStatic() { this.isStatic = true; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 44 */   public boolean isStatic() { return this.isStatic; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\GlobalFunction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */