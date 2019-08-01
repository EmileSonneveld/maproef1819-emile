/*     */ package lrg.memoria.core;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import java.util.Hashtable;
/*     */ import lrg.memoria.core.factories.BodyFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModelElementsRepository
/*     */   implements Serializable
/*     */ {
/*     */   private static ModelElementsRepository currentModelElementsRepository;
/*  18 */   private static Hashtable<Integer, ModelElementsRepository> modelElementsRepositories = new Hashtable();
/*  19 */   private static int numberOfModels = 0;
/*     */ 
/*     */   
/*  22 */   public static ModelElementsRepository getCurrentModelElementsRepository() { return currentModelElementsRepository; }
/*     */ 
/*     */   
/*     */   public static void setCurrentModelElementsRepository(Integer num) {
/*  26 */     ModelElementsRepository temp = (ModelElementsRepository)modelElementsRepositories.get(num);
/*  27 */     if (currentModelElementsRepository == null) {
/*  28 */       java.lang.System.err.println("ModelElementsRepository ERROR: The requested ModelElementsRepository with number " + num + " does not exist !");
/*  29 */       java.lang.System.exit(1);
/*     */     } else {
/*  31 */       currentModelElementsRepository = temp;
/*     */     } 
/*     */   }
/*     */   public static Integer addNewModelElementsRepository() {
/*  35 */     numberOfModels++;
/*     */     
/*  37 */     for (tmp = 0; tmp < numberOfModels && 
/*  38 */       modelElementsRepositories.get(new Integer(tmp)) != null; tmp++);
/*     */     
/*  40 */     Integer currentId = new Integer(tmp);
/*  41 */     currentModelElementsRepository = new ModelElementsRepository();
/*  42 */     modelElementsRepositories.put(currentId, currentModelElementsRepository);
/*  43 */     return currentId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Integer deleteModelElementsRepository(Integer id) {
/*  53 */     ModelElementsRepository temp = (ModelElementsRepository)modelElementsRepositories.get(id);
/*     */     
/*  55 */     if (temp != null) {
/*  56 */       Integer newId; if (numberOfModels == 1) {
/*  57 */         currentModelElementsRepository = null;
/*  58 */         newId = new Integer(-1);
/*  59 */         numberOfModels--;
/*  60 */         modelElementsRepositories.remove(id);
/*  61 */         return newId;
/*     */       } 
/*  63 */       if (temp == currentModelElementsRepository)
/*  64 */       { if (id.intValue() == 1) {
/*  65 */           newId = new Integer(2);
/*     */         } else {
/*  67 */           newId = new Integer(1);
/*     */         }  }
/*  69 */       else { newId = ((System)currentModelElementsRepository.byElementID(new Long(0L))).getSystemId(); }
/*  70 */        modelElementsRepositories.remove(id);
/*  71 */       currentModelElementsRepository = (ModelElementsRepository)modelElementsRepositories.get(newId);
/*  72 */       numberOfModels--;
/*  73 */       return newId;
/*     */     } 
/*  75 */     return ((System)currentModelElementsRepository.byElementID(new Long(0L))).getSystemId();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static int addNewModelElementsRepository(ModelElementsRepository mer) {
/*  82 */     int id = ++numberOfModels;
/*  83 */     currentModelElementsRepository = mer;
/*  84 */     modelElementsRepositories.put(new Integer(id), currentModelElementsRepository);
/*  85 */     return id;
/*     */   }
/*     */   
/*     */   public static void cleanUp() {
/*  89 */     modelElementsRepositories = null;
/*  90 */     currentModelElementsRepository = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  96 */   private long elementCount = 0L;
/*  97 */   private HashMap<Long, ModelElement> modelElemets = new HashMap();
/*  98 */   private HashMap<String, Long> allClasses = new HashMap();
/*     */   private Package unknownPackage;
/*     */   private Package anonymousPackage;
/*     */   private Namespace unknonwnNamespace;
/*     */   private Namespace anonymousNamespace;
/*     */   
/* 104 */   public ModelElement byElementID(Long elementID) { return (ModelElement)this.modelElemets.get(elementID); }
/*     */   private Namespace globalNamespace;
/*     */   private DataAbstraction unknownClass;
/*     */   
/* 108 */   public HashMap<Long, ModelElement> getModelElements() { return this.modelElemets; }
/*     */   private DataAbstraction hierarchyRootClass; private GlobalFunction unknownFunction; private Method unknownMethod;
/*     */   
/*     */   public void setModelElements(HashMap<Long, ModelElement> mdlElems) {
/* 112 */     this.modelElemets = mdlElems;
/* 113 */     this.elementCount = this.modelElemets.keySet().size();
/*     */   }
/*     */ 
/*     */   
/* 117 */   public long getElementCount() { return this.elementCount; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Long addElement(ModelElement me) {
/* 134 */     Long newID = new Long(this.elementCount++);
/* 135 */     this.modelElemets.put(newID, me);
/* 136 */     return newID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Package getUnknownPackage() {
/* 144 */     if (this.unknownPackage == null) {
/* 145 */       this.unknownPackage = new Package("_UNKNOWN_PACKAGE_");
/* 146 */       this.unknownPackage.setStatute(3);
/*     */     } 
/* 148 */     return this.unknownPackage;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   Package getAnonymousPackage() {
/* 154 */     if (this.anonymousPackage == null) {
/* 155 */       this.anonymousPackage = new Package("_ANONYMOUS_PACKAGE_");
/* 156 */       this.anonymousPackage.setStatute(1);
/*     */     } 
/* 158 */     return this.anonymousPackage;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Namespace getUnknownNamespace() {
/* 164 */     if (this.unknonwnNamespace == null) {
/* 165 */       this.unknonwnNamespace = new Namespace("_UNKNOWN_NAMESPACE_");
/* 166 */       this.unknonwnNamespace.setStatute(3);
/*     */     } 
/* 168 */     return this.unknonwnNamespace;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Namespace getAnonymousNamespace() {
/* 174 */     if (this.anonymousNamespace == null) {
/* 175 */       this.anonymousNamespace = new Namespace("_ANONYMOUS_NAMESPACE_");
/* 176 */       this.anonymousNamespace.setStatute(1);
/*     */     } 
/* 178 */     return this.anonymousNamespace;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Namespace getGlobalNamespace() {
/* 184 */     if (this.globalNamespace == null) {
/* 185 */       this.globalNamespace = new Namespace("_GLOBAL_NAMESPACE_");
/*     */     }
/* 187 */     return this.globalNamespace;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   DataAbstraction getUnknownClass() {
/* 193 */     if (this.unknownClass == null) {
/* 194 */       this.unknownClass = new Class("_UNKNOWN_CLASS_");
/* 195 */       this.unknonwnNamespace.addType(this.unknownClass);
/* 196 */       this.unknownPackage.addType(this.unknownClass);
/*     */     } 
/* 198 */     return this.unknownClass;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void setHierarchyRootClass(DataAbstraction cls) {
/* 204 */     this.hierarchyRootClass = cls;
/* 205 */     this.hierarchyRootClass.setStatute(2);
/*     */   }
/*     */ 
/*     */   
/* 209 */   DataAbstraction getHierarchyRootClass() { return this.hierarchyRootClass; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Function getUnknownFunction() {
/* 215 */     if (this.unknownFunction == null) {
/* 216 */       this.unknownFunction = new GlobalFunction("unknown_function");
/* 217 */       this.unknonwnNamespace.addGlobalFunction(this.unknownFunction);
/* 218 */       this.unknownPackage.addGlobalFunction(this.unknownFunction);
/*     */     } 
/* 220 */     return this.unknownFunction;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Method getUnknownMethod() {
/* 226 */     if (this.unknownMethod == null) {
/* 227 */       this.unknownMethod = new Method("unknown_method");
/* 228 */       this.unknownMethod.setAccessMode(1);
/* 229 */       this.unknownMethod.setStatute(3);
/* 230 */       getUnknownClass().addMethod(this.unknownMethod);
/*     */     } 
/* 232 */     return this.unknownMethod;
/*     */   }
/*     */   
/* 235 */   private TemplateParameterType unknown_type = null; private GlobalVariable unknownVariable; private Body unknownBody;
/*     */   
/*     */   public TemplateParameterType getUnknownTemplateParameterType() {
/* 238 */     if (this.unknown_type == null) {
/* 239 */       this.unknown_type = new TemplateParameterType(TemplateParameterType.UNKNOWN_TPT_NAME);
/* 240 */       this.unknown_type.setScope(Namespace.getUnknownNamespace());
/* 241 */       this.unknown_type.setStatute(3);
/*     */     } 
/* 243 */     return this.unknown_type;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Variable getUnknownVariable() {
/* 249 */     if (this.unknownVariable == null) {
/* 250 */       this.unknownVariable = new GlobalVariable(Variable.UNKNOWN_VARIABLE_NAME);
/* 251 */       this.unknonwnNamespace.addGlobalVariable(this.unknownVariable);
/* 252 */       this.unknownPackage.addGlobalVariable(this.unknownVariable);
/*     */     } 
/* 254 */     return this.unknownVariable;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Body getUnknownBody() {
/* 260 */     if (this.unknownBody == null) {
/* 261 */       this.unknownBody = BodyFactory.getInstance().produceBody(Method.getUnknownMethod());
/*     */     }
/* 263 */     return this.unknownBody;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\ModelElementsRepository.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */