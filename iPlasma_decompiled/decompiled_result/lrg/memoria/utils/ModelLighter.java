/*     */ package lrg.memoria.utils;
/*     */ import java.util.HashMap;
/*     */ import lrg.memoria.core.Class;
/*     */ import lrg.memoria.core.Function;
/*     */ import lrg.memoria.core.GlobalVariable;
/*     */ import lrg.memoria.core.LocalVariable;
/*     */ import lrg.memoria.core.Method;
/*     */ import lrg.memoria.core.ModelElement;
/*     */ import lrg.memoria.core.ModelElementList;
/*     */ import lrg.memoria.core.ModelElementsRepository;
/*     */ import lrg.memoria.core.Package;
/*     */ import lrg.memoria.core.Parameter;
/*     */ import lrg.memoria.core.System;
/*     */ import lrg.memoria.core.Type;
/*     */ import lrg.memoria.core.Union;
/*     */ 
/*     */ public class ModelLighter extends ModelVisitor {
/*     */   public static final int ENTIRE_MODEL = 0;
/*     */   public static final int ONLY_TYPES = 1;
/*     */   
/*  21 */   public ModelLighter(System sys) { this.currentSystem = sys; }
/*     */   public static final int UP_TO_METHOD_LEVEL = 2; private System currentSystem; private int level;
/*     */   
/*     */   public void lightenModel(int level) {
/*  25 */     this.level = level;
/*  26 */     MEMORIABreadthIterator mbi = new MEMORIABreadthIterator(this.currentSystem);
/*  27 */     cleanFailedDepandencies();
/*  28 */     while (mbi.hasNext()) {
/*  29 */       mbi.next().accept(this);
/*     */     }
/*  31 */     if (level != 0) {
/*  32 */       ModelElementsRepository mer = ModelElementsRepository.getCurrentModelElementsRepository();
/*  33 */       HashMap<Long, ModelElement> newElements = new HashMap<Long, ModelElement>();
/*  34 */       cleanModelElementsRepository(mer, newElements);
/*  35 */       mer.setModelElements(newElements);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void cleanFailedDepandencies() {
/*  40 */     ModelElementList<ModelElement> newFDL = new ModelElementList<ModelElement>();
/*  41 */     for (ModelElement me : this.currentSystem.getFailedDepElementList()) {
/*  42 */       if (entityShouldRemain(me))
/*  43 */         newFDL.add(me); 
/*     */     } 
/*  45 */     this.currentSystem.setFailedDepElementList(newFDL);
/*     */   }
/*     */   
/*     */   private void cleanModelElementsRepository(ModelElementsRepository mer, HashMap newElements) {
/*  49 */     HashMap<Long, ModelElement> oldElements = mer.getModelElements();
/*  50 */     long elementsCount = mer.getElementCount();
/*  51 */     long currentID = 0L;
/*     */     
/*  53 */     for (long i = 0L; i < elementsCount; i++) {
/*  54 */       ModelElement current = (ModelElement)oldElements.get(new Long(i));
/*  55 */       if (entityShouldRemain(current)) {
/*  56 */         current.setElementID(new Long(currentID));
/*  57 */         newElements.put(new Long(currentID), current);
/*  58 */         currentID++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean entityShouldRemain(ModelElement current) {
/*  64 */     if (current instanceof Type || current instanceof Package || current instanceof System)
/*  65 */       return true; 
/*  66 */     if (this.level == 1)
/*  67 */       return false; 
/*  68 */     if (this.level == 2 && (
/*  69 */       current instanceof Function || current instanceof Variable))
/*  70 */       return true; 
/*  71 */     return false;
/*     */   }
/*     */ 
/*     */   
/*  75 */   public void visitNamespace(Namespace n) { visitPackage(n); }
/*     */ 
/*     */   
/*     */   public void visitPackage(Package p) {
/*  79 */     if (this.level == 1) {
/*  80 */       p.setGlobalFunctions(new ModelElementList());
/*  81 */       p.setGlobalVariables(new ModelElementList());
/*  82 */       ModelElementList<Type> types = p.getAllTypesList();
/*  83 */       ModelElementList<Type> nonInnerTypes = new ModelElementList<Type>();
/*  84 */       for (Type t : types) {
/*  85 */         if (t.getScope() instanceof Namespace || t.getScope() instanceof lrg.memoria.core.DataAbstraction)
/*  86 */           nonInnerTypes.add(t); 
/*     */       } 
/*  88 */       p.setAllTypesList(nonInnerTypes);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void visitClass(Class c) {
/*  93 */     if (this.level == 1) {
/*  94 */       c.setAttributes(new ModelElementList());
/*  95 */       c.setMethods(new ModelElementList());
/*     */     } 
/*  97 */     c.setInitializersList(new ModelElementList());
/*     */   }
/*     */   
/*     */   public void visitUnion(Union u) {
/* 101 */     if (this.level == 1) {
/* 102 */       u.setAttributes(new ModelElementList());
/* 103 */       u.setMethods(new ModelElementList());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 108 */   public void visitMethod(Method m) { visitFunction(m); }
/*     */ 
/*     */ 
/*     */   
/* 112 */   public void visitGlobalFunction(GlobalFunction gf) { visitFunction(gf); }
/*     */ 
/*     */   
/*     */   private void visitFunction(Function f) {
/* 116 */     if (this.level == 2) {
/* 117 */       f.setCallList(new ModelElementList());
/* 118 */       f.setFunctionBody((FunctionBody)Body.getUnkonwnBody());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 123 */   public void visitAttribute(Attribute a) { visitVariable(a); }
/*     */ 
/*     */ 
/*     */   
/* 127 */   public void visitGlobalVariable(GlobalVariable gv) { visitVariable(gv); }
/*     */ 
/*     */ 
/*     */   
/* 131 */   public void visitParameter(Parameter par) { visitVariable(par); }
/*     */ 
/*     */ 
/*     */   
/* 135 */   public void visitLocalVar(LocalVariable lv) { visitVariable(lv); }
/*     */ 
/*     */   
/*     */   private void visitVariable(Variable v) {
/* 139 */     if (this.level == 2) {
/* 140 */       v.setAccessesList(new ModelElementList());
/*     */     }
/*     */   }
/*     */   
/*     */   public static void main(String[] args) throws Exception {
/* 145 */     if (args.length != 3) {
/* 146 */       System.out.println("Usage: java ModelLighter java_sources_path_list cache_path loading_level");
/*     */     }
/*     */     
/* 149 */     int loadingLevel = Integer.parseInt(args[2]);
/*     */     
/* 151 */     System sys = (new JavaModelLoader(args[0], null, null)).getSystem();
/*     */     
/* 153 */     System.out.println("Making the model lighter ...");
/*     */     
/* 155 */     ModelLighter currentLighter = new ModelLighter(sys);
/*     */     
/* 157 */     if (loadingLevel != 0) {
/* 158 */       currentLighter.lightenModel(loadingLevel);
/* 159 */       sys.setLoadingLevel(loadingLevel);
/*     */     } 
/*     */     
/* 162 */     System.out.println("Saving the light model into cache ...");
/*     */     
/* 164 */     System.gc();
/* 165 */     System.serializeToFile(new File(args[1]), sys);
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memori\\utils\ModelLighter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */