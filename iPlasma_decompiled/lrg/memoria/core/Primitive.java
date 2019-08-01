/*    */ package lrg.memoria.core;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Primitive
/*    */   extends NamedModelElement
/*    */   implements Type
/*    */ {
/* 12 */   private final Namespace scope = Namespace.getGlobalNamespace();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Primitive(String name) {
/* 18 */     super(name);
/* 19 */     setStatute(2);
/*    */   }
/*    */ 
/*    */   
/* 23 */   public String getFullName() { return String.valueOf(this.scope.getName()) + "." + this.name; }
/*    */ 
/*    */ 
/*    */   
/* 27 */   public Scope getScope() { return this.scope; }
/*    */ 
/*    */ 
/*    */   
/* 31 */   public void accept(ModelVisitor v) { v.visitPrimitive(this); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\Primitive.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */