/*     */ package classes.lrg.insider.metamodel;
/*     */ import lrg.membrain.representation.instructionSet.Abstractions.MCall;
/*     */ import lrg.memoria.core.AnnotationInstance;
/*     */ import lrg.memoria.core.Function;
/*     */ import lrg.memoria.core.Parameter;
/*     */ import lrg.memoria.core.Subsystem;
/*     */ import lrg.memoria.core.Variable;
/*     */ import lrg.memoria.hismo.core.GlobalFunctionHistory;
/*     */ import lrg.memoria.hismo.core.NamespaceHistory;
/*     */ 
/*     */ public class Address {
/*  12 */   public static String buildForRoot() { return "~root"; }
/*     */ 
/*     */ 
/*     */   
/*  16 */   public static String buildFor(Annotation anAnnotation) { return anAnnotation.getFullName(); }
/*     */ 
/*     */ 
/*     */   
/*  20 */   public static String buildFor(AnnotationInstance anAnnotation) { return String.valueOf(anAnnotation.getFullName()) + "{" + anAnnotation.getAnnotatedElement().getFullName() + "}"; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  25 */   public static String buildFor(Component aComponent) { return aComponent.getName(); }
/*     */ 
/*     */   
/*  28 */   public static String buildFor(Package aPackage) { return aPackage.getName(); }
/*     */ 
/*     */ 
/*     */   
/*  32 */   public static String buildFor(Type aType) { return aType.getFullName(); }
/*     */ 
/*     */ 
/*     */   
/*  36 */   public static String buildFor(Subsystem aSubsystem) { return aSubsystem.getName(); }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String buildFor(Variable aVariable) {
/*  41 */     String returnValue = "";
/*  42 */     String scope = "";
/*  43 */     if (aVariable instanceof GlobalVariable) {
/*  44 */       scope = ((GlobalVariable)aVariable).getScope().getName();
/*  45 */     } else if (aVariable instanceof Attribute) {
/*  46 */       scope = String.valueOf(((Attribute)aVariable).getScope().getScope().getName()) + "." + (
/*  47 */         (Attribute)aVariable).getScope().getName();
/*  48 */     } else if (aVariable instanceof Parameter) {
/*  49 */       scope = ((Parameter)aVariable).getScope().getFullName();
/*     */     } 
/*     */     
/*  52 */     return String.valueOf(scope) + "." + aVariable.getName();
/*     */   }
/*     */   
/*     */   public static String buildFor(Function aFunction) {
/*  56 */     returnValue = "";
/*  57 */     if (aFunction instanceof lrg.memoria.core.Method) {
/*  58 */       returnValue = String.valueOf(returnValue) + aFunction.getScope().getScope().getName() + ".";
/*     */     }
/*  60 */     returnValue = String.valueOf(returnValue) + aFunction.getScope().getName() + "." + aFunction.getName() + "(";
/*  61 */     for (int i = 0; i < aFunction.getParameterList().size() - 1; i++) {
/*  62 */       Parameter currentParameter = (Parameter)aFunction.getParameterList().get(i);
/*  63 */       returnValue = String.valueOf(returnValue) + currentParameter.getType().getFullName() + ",";
/*     */     } 
/*  65 */     if (aFunction.getParameterList().size() > 0) {
/*  66 */       Parameter lastParameter = (Parameter)aFunction.getParameterList().get(aFunction.getParameterList().size() - 1);
/*  67 */       returnValue = String.valueOf(returnValue) + lastParameter.getType().getFullName();
/*     */     } 
/*     */     
/*  70 */     return String.valueOf(returnValue) + ")";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   public static String buildFor(GlobalFunctionHistory currentGlobalFunctionHistory) { return currentGlobalFunctionHistory.getFullName(); }
/*     */ 
/*     */ 
/*     */   
/*  80 */   public static String buildFor(VariableHistory variableHistory) { return variableHistory.getFullName(); }
/*     */ 
/*     */ 
/*     */   
/*  84 */   public static String buildFor(FunctionHistory functionHistory) { return functionHistory.getFullName(); }
/*     */ 
/*     */ 
/*     */   
/*  88 */   public static String buildFor(ClassHistory currentClassHistory) { return currentClassHistory.getFullName(); }
/*     */ 
/*     */ 
/*     */   
/*  92 */   public static String buildFor(NamespaceHistory currentNamespaceHistory) { return currentNamespaceHistory.getFullName(); }
/*     */ 
/*     */ 
/*     */   
/*  96 */   public static String buildFor(BasicBlock bb) { return bb.produceAddress(); }
/*     */ 
/*     */ 
/*     */   
/* 100 */   public static String buildFor(GroupEntity aGroup) { return new String((String)aGroup.getProperty("Address").getValue()); }
/*     */ 
/*     */ 
/*     */   
/* 104 */   public static String buildFor(MCall c) { return c.produceAddress(); }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\metamodel\Address.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */