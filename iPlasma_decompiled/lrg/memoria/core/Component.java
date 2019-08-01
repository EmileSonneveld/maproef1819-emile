/*    */ package lrg.memoria.core;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Component
/*    */   extends NamedModelElement
/*    */   implements Scope
/*    */ {
/*    */   private ModelElementList<Class> containedClasses;
/*    */   private System theSystem;
/*    */   
/*    */   public Component(String name, System theScope) {
/* 14 */     super(name);
/* 15 */     this.containedClasses = new ModelElementList();
/* 16 */     this.theSystem = theScope;
/*    */   }
/*    */ 
/*    */   
/* 20 */   public void accept(ModelVisitor v) { v.visitComponent(this); }
/*    */ 
/*    */   
/*    */   public void addScopedElement(Scopable element) {
/* 24 */     if (!(element instanceof Class))
/* 25 */       return;  this.containedClasses.add((Class)element);
/*    */   }
/*    */   
/*    */   public void removeScopedElement(Scopable element) {
/* 29 */     if (!(element instanceof Class))
/* 30 */       return;  this.containedClasses.remove((DataAbstraction)element);
/*    */   }
/*    */ 
/*    */   
/* 34 */   public ModelElementList getScopedElements() { return this.containedClasses; }
/*    */ 
/*    */ 
/*    */   
/* 38 */   public Scope getScope() { return this; }
/*    */ 
/*    */ 
/*    */   
/* 42 */   public System getSystem() { return this.theSystem; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\Component.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */