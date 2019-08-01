/*    */ package lrg.memoria.core;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ArrayDecorator
/*    */   extends ImplicitTypeDecorator
/*    */ {
/* 10 */   public ArrayDecorator(Type decorated) { super(decorated); }
/*    */ 
/*    */ 
/*    */   
/* 14 */   public String getFullName() { return String.valueOf(getScope().getFullName()) + "." + this.decoratedType.getName() + "[]"; }
/*    */ 
/*    */ 
/*    */   
/* 18 */   public String getName() { return String.valueOf(this.decoratedType.getName()) + "[]"; }
/*    */ 
/*    */ 
/*    */   
/* 22 */   public boolean isArray() { return true; }
/*    */ 
/*    */ 
/*    */   
/* 26 */   public void accept(ModelVisitor mv) { mv.visitArrayDecorator(this); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\ArrayDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */