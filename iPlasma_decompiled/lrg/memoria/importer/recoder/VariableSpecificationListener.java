/*     */ package lrg.memoria.importer.recoder;
/*     */ import lrg.memoria.core.LocalVariable;
/*     */ import lrg.memoria.core.Location;
/*     */ import lrg.memoria.core.Method;
/*     */ import lrg.memoria.core.Parameter;
/*     */ import lrg.memoria.core.Type;
/*     */ import recoder.java.ProgramElement;
/*     */ import recoder.java.declaration.VariableDeclaration;
/*     */ import recoder.java.declaration.VariableSpecification;
/*     */ import recoder.java.reference.TypeReference;
/*     */ 
/*     */ public class VariableSpecificationListener implements Listener {
/*     */   static  {
/*  14 */     ModelConstructor.addFactory("lrg.memoria.importer.recoder.VariableSpecificationListener", new Factory());
/*     */   }
/*     */   private static Listener listener;
/*     */   private VariableSpecificationListener() {}
/*     */   
/*     */   static class Factory implements IFactory { public Listener getListener() {
/*  20 */       if (
/*     */ 
/*     */ 
/*     */         
/*  24 */         listener != null) {
/*  25 */         return listener;
/*     */       }
/*  27 */       listener = new VariableSpecificationListener(null); return new VariableSpecificationListener(null);
/*     */     }
/*     */ 
/*     */     
/*  31 */     public void cleanUp() { listener = null; } }
/*     */ 
/*     */ 
/*     */   
/*     */   public void enterModelComponent(ProgramElement pe) {
/*  36 */     ModelRepository mr = DefaultModelRepository.getModelRepository(null);
/*  37 */     VariableSpecification vs = (VariableSpecification)pe;
/*  38 */     VariableDeclaration vd = vs.getParent();
/*  39 */     if (vd instanceof recoder.java.declaration.ParameterDeclaration) {
/*     */       Parameter parameter;
/*  41 */       if (vd.getASTParent() instanceof recoder.java.statement.Catch) {
/*  42 */         parameter = mr.addLocalVar(vs, vs.getName(), mr.getCurrentStripe());
/*  43 */         mr.getCurrentStripe().addLocalVar((LocalVariable)parameter);
/*  44 */         ((LocalVariable)parameter).setExParam();
/*  45 */         parameter.setLocation(mr.getCurrentStripe().getRelPosOf(getLocation(mr, vs)));
/*     */       } else {
/*  47 */         parameter = mr.addParameter(vs, vs.getName());
/*  48 */         Method method = mr.getCurrentMethod();
/*  49 */         ((Parameter)parameter).setScope(method);
/*  50 */         if (method != null)
/*  51 */           method.addParameter((Parameter)parameter); 
/*  52 */         parameter.setLocation(getLocation(mr, vs));
/*     */       } 
/*  54 */       parameter.setStatute(1);
/*     */       
/*  56 */       TypeReference tr = vs.getParent().getTypeReference();
/*  57 */       int arrayDimensions = vs.getDimensions() + ReferenceConverter.getArrayDimension(tr);
/*  58 */       Type baseType = ReferenceConverter.getTypeFromTypeReference(tr);
/*  59 */       if (arrayDimensions > 0) {
/*  60 */         parameter.setType(mr.getArrayType(baseType, arrayDimensions));
/*     */       } else {
/*  62 */         parameter.setType(baseType);
/*  63 */       }  if (vs.isFinal()) {
/*  64 */         parameter.setFinal();
/*     */       }
/*     */ 
/*     */       
/*  68 */       if (mr.getCurrentAnnotationInstance() != null) {
/*  69 */         parameter.addAnnotationInstance(mr.getCurrentAnnotationInstance());
/*  70 */         mr.getCurrentAnnotationInstance().setAnnotatedElement(parameter);
/*  71 */         mr.setCurrentAnnotationInstance(null);
/*     */       }
/*     */     
/*  74 */     } else if (vd instanceof recoder.java.declaration.LocalVariableDeclaration) {
/*  75 */       LocalVariable mmmlv = mr.addLocalVar(vs, vs.getName(), mr.getCurrentStripe());
/*  76 */       mr.getCurrentStripe().addLocalVar(mmmlv);
/*     */       
/*  78 */       mmmlv.setStatute(1);
/*  79 */       mmmlv.setLocation(mmmlv.getStripe().getRelPosOf(getLocation(mr, vs)));
/*     */       
/*  81 */       TypeReference tr = vs.getParent().getTypeReference();
/*  82 */       int arrayDim = ReferenceConverter.getArrayDimension(tr);
/*  83 */       Type baseType = ReferenceConverter.getTypeFromTypeReference(tr);
/*  84 */       if (arrayDim > 0) {
/*  85 */         mmmlv.setType(mr.getArrayType(baseType, arrayDim));
/*     */       } else {
/*  87 */         mmmlv.setType(baseType);
/*  88 */       }  if (vs.isFinal())
/*  89 */         mmmlv.setFinal(); 
/*  90 */       NonTerminalProgramElement nonTerminalProgramElement = vd.getASTParent().getASTParent();
/*  91 */       if (!(nonTerminalProgramElement instanceof recoder.java.declaration.MethodDeclaration) && !(nonTerminalProgramElement instanceof recoder.java.declaration.ClassInitializer)) {
/*  92 */         mmmlv.setBlock();
/*     */       }
/*     */ 
/*     */       
/*  96 */       if (mr.getCurrentAnnotationInstance() != null) {
/*  97 */         mmmlv.addAnnotationInstance(mr.getCurrentAnnotationInstance());
/*  98 */         mr.getCurrentAnnotationInstance().setAnnotatedElement(mmmlv);
/*  99 */         mr.setCurrentAnnotationInstance(null);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private Location getLocation(ModelRepository mr, VariableSpecification vs) {
/* 106 */     Location loc = new Location(mr.getCurrentFile());
/* 107 */     loc.setStartLine(vs.getFirstElement().getStartPosition().getLine());
/* 108 */     loc.setStartChar(vs.getFirstElement().getStartPosition().getColumn());
/* 109 */     loc.setEndLine(vs.getLastElement().getEndPosition().getLine());
/* 110 */     loc.setEndChar(vs.getLastElement().getEndPosition().getColumn());
/* 111 */     return loc;
/*     */   }
/*     */   
/*     */   public void leaveModelComponent(ProgramElement pe) {}
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\VariableSpecificationListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */