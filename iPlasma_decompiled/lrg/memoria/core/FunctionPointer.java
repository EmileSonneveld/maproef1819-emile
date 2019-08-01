/*    */ package lrg.memoria.core;
/*    */ 
/*    */ public class FunctionPointer
/*    */   extends NamedModelElement
/*    */   implements Type, Scope
/*    */ {
/*  7 */   private Scope scope = Namespace.getUnknownNamespace();
/*    */   
/*    */   private PointerToFunction functionSide;
/*    */   
/* 11 */   public FunctionPointer(String name) { super(name); }
/*    */ 
/*    */ 
/*    */   
/* 15 */   public String getFullName() { return String.valueOf(this.scope.getFullName()) + "." + getName(); }
/*    */ 
/*    */ 
/*    */   
/* 19 */   public Scope getScope() { return this.scope; }
/*    */ 
/*    */ 
/*    */   
/* 23 */   public void setScope(Scope scope) { this.scope = scope; }
/*    */ 
/*    */   
/*    */   public void addScopedElement(Scopable element) {
/* 27 */     if (element instanceof PointerToFunction)
/* 28 */       setFunctionSide((PointerToFunction)element); 
/*    */   }
/*    */   
/*    */   public ModelElementList getScopedElements() {
/* 32 */     ModelElementList scopedElements = new ModelElementList();
/* 33 */     scopedElements.add(this.functionSide);
/* 34 */     return scopedElements;
/*    */   }
/*    */ 
/*    */   
/* 38 */   public void setFunctionSide(PointerToFunction pointerToFunction) { this.functionSide = pointerToFunction; }
/*    */ 
/*    */ 
/*    */   
/* 42 */   public PointerToFunction getFunctionSide() { return this.functionSide; }
/*    */ 
/*    */ 
/*    */   
/* 46 */   public void accept(ModelVisitor mv) { mv.visitFunctionPointer(this); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\FunctionPointer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */