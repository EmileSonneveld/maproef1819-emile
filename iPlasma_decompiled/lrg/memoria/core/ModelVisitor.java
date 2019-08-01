package lrg.memoria.core;

public abstract class ModelVisitor {
  public void visitAnnotation(Annotation a) {}
  
  public void visitAnnotationInstance(AnnotationInstance a) {}
  
  public void visitAnnotationProperty(AnnotationProperty a) {}
  
  public void visitSubsystem(Subsystem s) {}
  
  public void visitInheritanceRelation(InheritanceRelation r) {}
  
  public void visitSystem(System s) {}
  
  public void visitFile(File f) {}
  
  public void visitPackage(Package p) {}
  
  public void visitComponent(Component c) {}
  
  public void visitNamespace(Namespace p) {}
  
  public void visitClass(Class c) {}
  
  public void visitPrimitive(Primitive p) {}
  
  public void visitUnion(Union u) {}
  
  public void visitTemplateParameter(TemplateParameterType u) {}
  
  public void visitPointerDecorator(PointerDecorator pd) {}
  
  public void visitArrayDecorator(ArrayDecorator ad) {}
  
  public void visitReferenceDecorator(ReferenceDecorator rd) {}
  
  public void visitTypedefDecorator(TypedefDecorator td) {}
  
  public void visitFunctionPointer(FunctionPointer fp) {}
  
  public void visitPointerToFunction(PointerToFunction ptf) {}
  
  public void visitGlobalVar(GlobalVariable v) {}
  
  public void visitAttribute(Attribute a) {}
  
  public void visitParameter(Parameter p) {}
  
  public void visitLocalVar(LocalVariable l) {}
  
  public void visitMethod(Method m) {}
  
  public void visitGlobalFunction(GlobalFunction m) {}
  
  public void visitFunctionBody(FunctionBody m) {}
  
  public void visitInitializerBody(InitializerBody i) {}
  
  public void visitCodeStripe(CodeStripe cs) {}
  
  public void visitCall(Call c) {}
  
  public void visitAccess(Access a) {}
  
  public void visitTemplateInstance(TemplateInstance templateInstanceDecorator) {}
}


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\ModelVisitor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */