/*    */ package lrg.memoria.core;
/*    */ 
/*    */ public class TypedefDecorator extends ExplicitlyDefinedType implements TypeDecorator {
/*    */   private Type decoratedType;
/*    */   
/*    */   public TypedefDecorator(Type decorated, String name) {
/*  7 */     super(name);
/*  8 */     this.decoratedType = decorated;
/*    */   }
/*    */ 
/*    */   
/* 12 */   public String getFullName() { return String.valueOf(getScope().getFullName()) + "." + getName(); }
/*    */ 
/*    */ 
/*    */   
/* 16 */   public String getName() { return this.name; }
/*    */ 
/*    */ 
/*    */   
/* 20 */   public Type getDecoratedType() { return this.decoratedType; }
/*    */ 
/*    */   
/*    */   public Type getRootType() {
/* 24 */     if (this.decoratedType instanceof TypeDecorator) {
/* 25 */       return ((TypeDecorator)this.decoratedType).getRootType();
/*    */     }
/* 27 */     return this.decoratedType;
/*    */   }
/*    */ 
/*    */   
/* 31 */   public boolean isTypedefAlias() { return true; }
/*    */ 
/*    */ 
/*    */   
/* 35 */   public boolean isPointer() { return false; }
/*    */ 
/*    */ 
/*    */   
/* 39 */   public boolean isArray() { return false; }
/*    */ 
/*    */ 
/*    */   
/* 43 */   public boolean isReference() { return false; }
/*    */ 
/*    */ 
/*    */   
/* 47 */   public void accept(ModelVisitor mv) { mv.visitTypedefDecorator(this); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\TypedefDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */