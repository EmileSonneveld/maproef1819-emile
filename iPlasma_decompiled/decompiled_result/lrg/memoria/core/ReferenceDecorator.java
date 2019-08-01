/*    */ package lrg.memoria.core;
/*    */ 
/*    */ public class ReferenceDecorator
/*    */   extends ImplicitTypeDecorator
/*    */ {
/*  6 */   public ReferenceDecorator(Type decorated) { super(decorated); }
/*    */ 
/*    */ 
/*    */   
/* 10 */   public String getFullName() { return String.valueOf(getScope().getFullName()) + "." + this.decoratedType.getName() + "&"; }
/*    */ 
/*    */ 
/*    */   
/* 14 */   public String getName() { return String.valueOf(this.decoratedType.getName()) + "&"; }
/*    */ 
/*    */ 
/*    */   
/* 18 */   public boolean isReference() { return true; }
/*    */ 
/*    */ 
/*    */   
/* 22 */   public void accept(ModelVisitor mv) { mv.visitReferenceDecorator(this); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\ReferenceDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */