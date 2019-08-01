/*    */ package lrg.memoria.core;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ImplicitTypeDecorator
/*    */   extends NamedModelElement
/*    */   implements TypeDecorator
/*    */ {
/*    */   protected Type decoratedType;
/*    */   
/*    */   public ImplicitTypeDecorator(Type decorated) {
/* 12 */     super(decorated.getName());
/* 13 */     this.decoratedType = decorated;
/*    */   }
/*    */   
/*    */   public ImplicitTypeDecorator(Type decorated, String name) {
/* 17 */     super(name);
/* 18 */     this.decoratedType = decorated;
/*    */   }
/*    */   
/*    */   public Type getRootType() {
/* 22 */     if (this.decoratedType instanceof TypeDecorator) {
/* 23 */       return ((TypeDecorator)this.decoratedType).getRootType();
/*    */     }
/* 25 */     return this.decoratedType;
/*    */   }
/*    */ 
/*    */   
/* 29 */   public Type getDecoratedType() { return this.decoratedType; }
/*    */ 
/*    */ 
/*    */   
/* 33 */   public Scope getScope() { return this.decoratedType.getScope(); }
/*    */ 
/*    */   
/*    */   public boolean isPointer() {
/* 37 */     if (this.decoratedType instanceof TypeDecorator)
/* 38 */       return ((TypeDecorator)this.decoratedType).isPointer(); 
/* 39 */     return false;
/*    */   }
/*    */   
/*    */   public boolean isArray() {
/* 43 */     if (this.decoratedType instanceof TypeDecorator)
/* 44 */       return ((TypeDecorator)this.decoratedType).isArray(); 
/* 45 */     return false;
/*    */   }
/*    */   
/*    */   public boolean isTypedefAlias() {
/* 49 */     if (this.decoratedType instanceof TypeDecorator)
/* 50 */       return ((TypeDecorator)this.decoratedType).isTypedefAlias(); 
/* 51 */     return false;
/*    */   }
/*    */   
/*    */   public boolean isReference() {
/* 55 */     if (this.decoratedType instanceof TypeDecorator)
/* 56 */       return ((TypeDecorator)this.decoratedType).isReference(); 
/* 57 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\ImplicitTypeDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */