/*    */ package lrg.memoria.core;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Namespace
/*    */   extends Package
/*    */   implements Scope
/*    */ {
/*    */   public static final String ANONYMOUS_NAMESPACE_NAME = "_ANONYMOUS_NAMESPACE_";
/*    */   public static final String GLOBAL_NAMESPACE_NAME = "_GLOBAL_NAMESPACE_";
/*    */   public static final String UNKNOWN_NAMESPACE_NAME = "_UNKNOWN_NAMESPACE_";
/*    */   
/* 15 */   public static Namespace getAnonymousNamespace() { return ModelElementsRepository.getCurrentModelElementsRepository().getAnonymousNamespace(); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 25 */   public static Namespace getGlobalNamespace() { return ModelElementsRepository.getCurrentModelElementsRepository().getGlobalNamespace(); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 33 */   public static Namespace getUnknownNamespace() { return ModelElementsRepository.getCurrentModelElementsRepository().getUnknownNamespace(); }
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
/* 44 */   public Namespace(String name) { super(name); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Namespace(String name, int statute) {
/* 51 */     super(name);
/* 52 */     setStatute(statute);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 59 */   public Namespace(Namespace nsp) { super(nsp); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 66 */   public Scope getScope() { return this; }
/*    */ 
/*    */   
/*    */   public void addScopedElement(Scopable element) {
/* 70 */     if (element instanceof GlobalFunction)
/* 71 */       addGlobalFunction((GlobalFunction)element); 
/* 72 */     if (element instanceof GlobalVariable)
/* 73 */       addGlobalVariable((GlobalVariable)element); 
/* 74 */     if (element instanceof Type) {
/* 75 */       addType((Type)element);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 82 */   public void accept(ModelVisitor v) { v.visitNamespace(this); }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 86 */     StringBuffer sir = new StringBuffer("\tNamespace: ");
/* 87 */     sir.append(this.name);
/* 88 */     sir.append("\n\t - contained types:\n");
/* 89 */     for (Type type : getAllTypesList())
/* 90 */       sir.append(type); 
/* 91 */     sir.append("\n\t - contained global variables:\n");
/* 92 */     for (Variable var : getGlobalVariablesList())
/* 93 */       sir.append(var); 
/* 94 */     sir.append("\n\t - contained global functions:\n");
/* 95 */     for (Function func : getGlobalFunctionsList())
/* 96 */       sir.append(func); 
/* 97 */     return new String(sir);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\Namespace.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */