/*     */ package lrg.memoria.importer.recoder;
/*     */ import java.io.File;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import lrg.memoria.core.Access;
/*     */ import lrg.memoria.core.Annotation;
/*     */ import lrg.memoria.core.AnnotationInstance;
/*     */ import lrg.memoria.core.AnnotationProperty;
/*     */ import lrg.memoria.core.ArrayDecorator;
/*     */ import lrg.memoria.core.Attribute;
/*     */ import lrg.memoria.core.Body;
/*     */ import lrg.memoria.core.Call;
/*     */ import lrg.memoria.core.Class;
/*     */ import lrg.memoria.core.CodeStripe;
/*     */ import lrg.memoria.core.DataAbstraction;
/*     */ import lrg.memoria.core.File;
/*     */ import lrg.memoria.core.Function;
/*     */ import lrg.memoria.core.FunctionBody;
/*     */ import lrg.memoria.core.LocalVariable;
/*     */ import lrg.memoria.core.Method;
/*     */ import lrg.memoria.core.ModelElementsRepository;
/*     */ import lrg.memoria.core.Namespace;
/*     */ import lrg.memoria.core.Package;
/*     */ import lrg.memoria.core.Parameter;
/*     */ import lrg.memoria.core.Primitive;
/*     */ import lrg.memoria.core.System;
/*     */ import lrg.memoria.core.Type;
/*     */ import lrg.memoria.core.Variable;
/*     */ import lrg.memoria.core.factories.BodyFactory;
/*     */ import recoder.abstraction.ClassType;
/*     */ import recoder.abstraction.Method;
/*     */ import recoder.abstraction.Package;
/*     */ import recoder.abstraction.ProgramModelElement;
/*     */ import recoder.abstraction.Type;
/*     */ import recoder.bytecode.ClassFile;
/*     */ import recoder.bytecode.FieldInfo;
/*     */ import recoder.bytecode.MethodInfo;
/*     */ import recoder.java.declaration.AnnotationDeclaration;
/*     */ import recoder.java.declaration.AnnotationUseSpecification;
/*     */ import recoder.java.reference.PackageReference;
/*     */ import recoder.java.reference.TypeReference;
/*     */ import recoder.parser.TokenMgrError;
/*     */ 
/*     */ public class DefaultModelRepository implements ModelRepository {
/*     */   private static DefaultModelRepository dmr;
/*     */   private HashMap fileMap;
/*     */   private HashMap classMap;
/*     */   private HashMap arraysMap;
/*     */   private HashMap packageMap;
/*     */   private HashMap namespacesMap;
/*     */   private HashMap methodMap;
/*     */   private HashMap primitiveMap;
/*     */   private HashMap variableMap;
/*     */   private HashMap accessMap;
/*     */   
/*     */   protected DefaultModelRepository(String systemName) {
/*  59 */     Integer id = ModelElementsRepository.addNewModelElementsRepository();
/*  60 */     this.system = new System(systemName);
/*  61 */     this.system.setSystemId(id);
/*  62 */     this.fileMap = new HashMap();
/*  63 */     this.classMap = new HashMap();
/*  64 */     this.arraysMap = new HashMap();
/*  65 */     this.packageMap = new HashMap();
/*  66 */     this.packageMap.put("_ANONYMOUS_PACKAGE_", Package.getAnonymousPackage());
/*  67 */     this.packageMap.put("_UNKNOWN_PACKAGE_", Package.getUnknownPackage());
/*  68 */     this.namespacesMap = new HashMap();
/*  69 */     this.methodMap = new HashMap();
/*  70 */     this.primitiveMap = new HashMap();
/*  71 */     this.variableMap = new HashMap();
/*  72 */     this.accessMap = new HashMap();
/*  73 */     this.callMap = new HashMap();
/*  74 */     this.annotationMap = new HashMap();
/*     */   }
/*     */   private HashMap callMap; private HashMap annotationMap; private Annotation currentAnnotation; private AnnotationInstance currentAnnotationInstance; private File currentFile; private System system; private Class currentMMMClass; private Method currentMMMMethod; private Package currentMMMPackage; private Body currentMMMBody; private CodeStripe currentMMMCodeStripe;
/*     */   public static DefaultModelRepository getModelRepository(String repositoryName) {
/*  78 */     if (dmr == null) {
/*  79 */       BodyFactory.cleanUp();
/*  80 */       dmr = new DefaultModelRepository(repositoryName);
/*     */     } 
/*  82 */     return dmr;
/*     */   }
/*     */   
/*     */   static void cleanUp() {
/*  86 */     if (dmr != null) {
/*  87 */       dmr.fileMap.clear();
/*  88 */       dmr.classMap.clear();
/*  89 */       dmr.arraysMap.clear();
/*  90 */       dmr.packageMap.clear();
/*  91 */       dmr.namespacesMap.clear();
/*  92 */       dmr.methodMap.clear();
/*  93 */       dmr.primitiveMap.clear();
/*  94 */       dmr.variableMap.clear();
/*  95 */       dmr.accessMap.clear();
/*  96 */       dmr.callMap.clear();
/*  97 */       dmr.annotationMap.clear();
/*     */     } 
/*  99 */     dmr = null;
/* 100 */     BodyFactory.cleanUp();
/* 101 */     System.gc();
/*     */   }
/*     */ 
/*     */   
/*     */   public Class addClass(Object key, String className) {
/* 106 */     if (key instanceof TypeReference) {
/* 107 */       return addClassFromTypeReference((TypeReference)key, className);
/*     */     }
/* 109 */     return addClassFromClassType((ClassType)key, className);
/*     */   }
/*     */ 
/*     */   
/*     */   private Class addClassFromClassType(ClassType clst, String className) {
/* 114 */     Class mmmc = (Class)this.classMap.get(clst);
/* 115 */     if (mmmc == null) {
/* 116 */       mmmc = new Class(className);
/* 117 */       this.classMap.put(clst, mmmc);
/*     */       
/* 119 */       Package tempPack = clst.getPackage();
/* 120 */       if (tempPack != null) {
/*     */         
/* 122 */         String packName = tempPack.getFullName();
/* 123 */         if (packName.equals("")) {
/* 124 */           packName = "_ANONYMOUS_PACKAGE_";
/*     */         }
/* 126 */         Package pack = addPackage(tempPack, packName);
/* 127 */         pack.addType(mmmc);
/* 128 */         mmmc.setPackage(pack);
/*     */         
/* 130 */         Namespace nsp = convertPackageToNamespace(pack);
/* 131 */         nsp.addType(mmmc);
/* 132 */         mmmc.setScope(nsp);
/*     */         
/* 134 */         if (clst instanceof ClassFile) {
/* 135 */           ClassFile ct = (ClassFile)clst;
/* 136 */           mmmc.setStatute(2);
/* 137 */           if (ct.isAbstract())
/* 138 */             mmmc.setAbstract(); 
/* 139 */           if (ct.isFinal())
/* 140 */             mmmc.setFinal(); 
/* 141 */           if (ct.isStatic())
/* 142 */             mmmc.setStatic(); 
/* 143 */           if (ct.isInterface())
/* 144 */             mmmc.setInterface(); 
/* 145 */           mmmc.setAccessMode(RecoderToMemojConverter.convertAccessMode(ct));
/* 146 */           List<ClassType> ctl = ct.getSupertypes();
/* 147 */           for (int i = 0; i < ctl.size(); i++) {
/* 148 */             ClassType clsType = (ClassType)ctl.get(i);
/* 149 */             Class clazz = ReferenceConverter.getMemoriaClass(clsType);
/* 150 */             if (clsType.isInterface()) {
/* 151 */               mmmc.addInterface(clazz);
/*     */             } else {
/* 153 */               mmmc.addAncestor(clazz);
/* 154 */             }  clazz.addDescendant(mmmc);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 159 */     return mmmc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Class addClassFromTypeReference(TypeReference tr, String className) {
/* 166 */     PackageReference pr = tr.getPackageReference();
/* 167 */     Class mmmc = null;
/* 168 */     String packFullName = "";
/* 169 */     if (pr != null) {
/*     */       do {
/* 171 */         packFullName = String.valueOf(pr.getName()) + "." + packFullName;
/* 172 */         pr = (PackageReference)pr.getReferencePrefix();
/* 173 */       } while (pr != null);
/* 174 */       packFullName = packFullName.substring(0, packFullName.length() - 1);
/* 175 */       mmmc = (Class)this.classMap.get(String.valueOf(packFullName) + "." + className);
/* 176 */       if (mmmc == null) {
/* 177 */         mmmc = (Class)this.classMap.get(className);
/* 178 */         if (mmmc == null) {
/* 179 */           mmmc = new Class(className);
/* 180 */           this.classMap.put(className, mmmc);
/*     */         } 
/* 182 */         setPackageForClassObtainedFromTypeReference(mmmc, packFullName);
/* 183 */         this.classMap.put(String.valueOf(packFullName) + "." + className, mmmc);
/*     */       } 
/*     */     } else {
/* 186 */       mmmc = (Class)this.classMap.get(className);
/* 187 */       if (mmmc == null) {
/* 188 */         mmmc = new Class(className);
/* 189 */         this.classMap.put(className, mmmc);
/* 190 */         setPackageForClassObtainedFromTypeReference(mmmc, "");
/*     */       } 
/*     */     } 
/* 193 */     return mmmc;
/*     */   }
/*     */   
/*     */   private void setPackageForClassObtainedFromTypeReference(DataAbstraction mmmc, String packFullName) {
/*     */     try {
/*     */       Package pack;
/* 199 */       if (!packFullName.equals("")) {
/* 200 */         pack = addPackage(null, packFullName);
/*     */       } else {
/* 202 */         pack = Package.getUnknownPackage();
/* 203 */       }  Namespace nsp = convertPackageToNamespace(pack);
/* 204 */       pack.addType(mmmc);
/* 205 */       nsp.addType(mmmc);
/* 206 */       mmmc.setPackage(pack);
/* 207 */       mmmc.setScope(nsp);
/* 208 */     } catch (IndexOutOfBoundsException ex) {
/* 209 */       this.system.addFailedDepElement(mmmc);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 218 */   public Class getClass(Object key) { return (Class)this.classMap.get(key); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Package addPackage(Object key, String packageName) {
/*     */     Package pack;
/* 226 */     if (key == null) {
/*     */       
/* 228 */       pack = (Package)this.packageMap.get(packageName);
/* 229 */       if (pack == null) {
/* 230 */         pack = new Package(packageName);
/* 231 */         this.packageMap.put(packageName, pack);
/* 232 */         pack.setSystem(this.system);
/* 233 */         this.system.addPackage(pack);
/*     */       } 
/*     */     } else {
/* 236 */       pack = (Package)this.packageMap.get(packageName);
/* 237 */       if (pack == null) {
/* 238 */         pack = new Package(packageName);
/* 239 */         if (pack.getStatute() == 3)
/* 240 */           pack.setStatute(2); 
/* 241 */         this.packageMap.put(packageName, pack);
/* 242 */         this.system.addPackage(pack);
/* 243 */         pack.setSystem(this.system);
/*     */       } 
/*     */     } 
/* 246 */     return pack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 253 */   public Package getPackage(Object key) { return (Package)this.packageMap.get(key); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Method addMethod(Object key, String methodName) {
/* 260 */     Object element = this.methodMap.get(key);
/* 261 */     if (element == null) {
/* 262 */       element = new Method(methodName);
/* 263 */       this.methodMap.put(key, element);
/* 264 */       Method met = (Method)element;
/* 265 */       if (key instanceof MethodInfo || key instanceof recoder.abstraction.DefaultConstructor) {
/* 266 */         Class clazz = ReferenceConverter.getMemoriaClass(((Method)key).getContainingClassType());
/* 267 */         met.setScope(clazz);
/* 268 */         clazz.addMethod(met);
/* 269 */         met.setStatute(2);
/*     */         
/* 271 */         List<Type> signature = ((Method)key).getSignature();
/*     */         
/* 273 */         for (int i = 0; i < signature.size(); i++) {
/* 274 */           Type currentType = (Type)signature.get(i);
/*     */           
/* 276 */           Type currentParamType = ReferenceConverter.getType(currentType);
/* 277 */           met.addParameter(new Parameter("par" + i + '\001', currentParamType, met));
/*     */         } 
/*     */         
/* 280 */         met.setAccessMode(RecoderToMemojConverter.convertAccessMode((Method)key));
/* 281 */         if (key instanceof recoder.abstraction.DefaultConstructor) {
/* 282 */           met.setConstructor();
/* 283 */           met.setFunctionBody((FunctionBody)BodyFactory.getInstance().produceBody(met));
/* 284 */         } else if (!(key instanceof recoder.bytecode.ConstructorInfo)) {
/* 285 */           MethodInfo metInf = (MethodInfo)key;
/* 286 */           met.setReturnType(ReferenceConverter.getType(metInf.getReturnType()));
/* 287 */           if (metInf.isStatic())
/* 288 */             met.setStatic(); 
/* 289 */           if (metInf.isFinal())
/* 290 */             met.setFinal(); 
/* 291 */           if (metInf.isAbstract())
/* 292 */             met.setAbstract(); 
/*     */         } 
/* 294 */       } else if (key instanceof recoder.java.reference.MethodReference || ((ProgramModelElement)key).getProgramModelInfo() == null) {
/* 295 */         this.system.addFailedDepElement(met);
/* 296 */         Class.getUnknownClass().addMethod(met);
/* 297 */         met.setScope(Class.getUnknownClass());
/* 298 */         met.setReturnType(Class.getUnknownClass());
/*     */       } 
/*     */     } 
/*     */     
/* 302 */     return (Method)element;
/*     */   }
/*     */   
/*     */   public ArrayDecorator getArrayType(Type type, int dim) {
/* 306 */     String fullName = type.getFullName();
/* 307 */     ArrayDecorator decorator = null;
/* 308 */     Type decorated = type;
/*     */     
/* 310 */     for (int i = 0; i < dim; i++) {
/* 311 */       fullName = String.valueOf(fullName) + "[]";
/* 312 */       decorator = (ArrayDecorator)this.arraysMap.get(fullName);
/* 313 */       if (decorator == null) {
/* 314 */         decorator = new ArrayDecorator(decorated);
/* 315 */         this.arraysMap.put(fullName, decorator);
/* 316 */         decorated.getScope().addScopedElement(decorator);
/*     */       } 
/* 318 */       ArrayDecorator arrayDecorator = decorator;
/*     */     } 
/* 320 */     return decorator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 328 */   public Function getMethod(Object key) { return (Function)this.methodMap.get(key); }
/*     */ 
/*     */   
/*     */   public Primitive addPrimitiveType(Object key, String typeName) {
/* 332 */     Object element = this.primitiveMap.get(key);
/* 333 */     if (element == null) {
/* 334 */       element = new Primitive(typeName);
/* 335 */       Namespace.getGlobalNamespace().addType((Type)element);
/* 336 */       this.primitiveMap.put(key, element);
/*     */     } 
/* 338 */     return (Primitive)element;
/*     */   }
/*     */ 
/*     */   
/* 342 */   public Primitive getPrimitiveType(Object key) { return (Primitive)this.primitiveMap.get(key); }
/*     */ 
/*     */   
/*     */   public LocalVariable addLocalVar(Object key, String varName, CodeStripe scope) {
/* 346 */     Object element = this.variableMap.get(key);
/* 347 */     if (element == null) {
/* 348 */       if (scope == null)
/* 349 */         throw new IllegalArgumentException("You must specify a valid scope for a LocalVariable!"); 
/* 350 */       if (key instanceof recoder.java.reference.VariableReference || ((ProgramModelElement)key).getProgramModelInfo() == null) {
/* 351 */         element = new LocalVariable(varName, scope);
/* 352 */         this.system.addFailedDepElement((LocalVariable)element);
/*     */       } else {
/* 354 */         element = new LocalVariable(varName, scope);
/* 355 */       }  this.variableMap.put(key, element);
/*     */     } 
/* 357 */     return (LocalVariable)element;
/*     */   }
/*     */ 
/*     */   
/* 361 */   public LocalVariable getLocalVar(Object key) { return (LocalVariable)this.variableMap.get(key); }
/*     */ 
/*     */   
/*     */   public Parameter addParameter(Object key, String varName) {
/* 365 */     Object element = this.variableMap.get(key);
/* 366 */     if (element == null) {
/* 367 */       element = new Parameter(varName);
/* 368 */       this.variableMap.put(key, element);
/*     */     } 
/* 370 */     return (Parameter)element;
/*     */   }
/*     */ 
/*     */   
/* 374 */   public Parameter getParameter(Object key) { return (Parameter)this.variableMap.get(key); }
/*     */ 
/*     */   
/*     */   public Attribute addAttribute(Object key, String varName) {
/* 378 */     Object element = this.variableMap.get(key);
/* 379 */     if (element == null) {
/* 380 */       element = new Attribute(varName);
/* 381 */       this.variableMap.put(key, element);
/* 382 */       Attribute attr = (Attribute)element;
/* 383 */       if (key instanceof FieldInfo) {
/* 384 */         FieldInfo fld = (FieldInfo)key;
/* 385 */         Class clazz = ReferenceConverter.getMemoriaClass(fld.getContainingClassType());
/* 386 */         attr.setScope(clazz);
/* 387 */         clazz.addAttribute(attr);
/* 388 */         attr.setType(ReferenceConverter.getType(fld.getType()));
/*     */         
/* 390 */         if (fld.getType() instanceof recoder.abstraction.ArrayType) {
/* 391 */           attr.setType(getArrayType(attr.getType(), ReferenceConverter.getArrayDimension(fld.getType())));
/*     */         }
/* 393 */         attr.setStatute(2);
/* 394 */         attr.setAccessMode(RecoderToMemojConverter.convertAccessMode(fld));
/* 395 */         if (fld.isStatic())
/* 396 */           attr.setStatic(); 
/* 397 */         if (fld.isFinal())
/* 398 */           attr.setFinal(); 
/* 399 */       } else if (key instanceof recoder.java.reference.FieldReference || ((ProgramModelElement)key).getProgramModelInfo() == null) {
/* 400 */         this.system.addFailedDepElement(attr);
/* 401 */         attr.setScope(Class.getUnknownClass());
/* 402 */         Class.getUnknownClass().addAttribute(attr);
/* 403 */         attr.setType(Class.getUnknownClass());
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 408 */     return (Attribute)element;
/*     */   }
/*     */ 
/*     */   
/* 412 */   public Attribute getAttribute(Object key) { return (Attribute)this.variableMap.get(key); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Access addAccess(Object key, Variable var, Body body) {
/* 419 */     Object element = this.accessMap.get(key);
/* 420 */     if (element == null) {
/* 421 */       element = new Access(var, body);
/* 422 */       this.accessMap.put(key, element);
/* 423 */       body.addAccess((Access)element);
/* 424 */       var.addAccess((Access)element);
/*     */     } 
/* 426 */     return (Access)element;
/*     */   }
/*     */   
/*     */   public Access addAccess(Object key, Variable var, CodeStripe stripe) {
/* 430 */     Object element = this.accessMap.get(key);
/* 431 */     if (element == null) {
/* 432 */       element = new Access(var, stripe);
/* 433 */       this.accessMap.put(key, element);
/* 434 */       stripe.addAccess((Access)element);
/* 435 */       var.addAccess((Access)element);
/*     */     } 
/* 437 */     return (Access)element;
/*     */   }
/*     */ 
/*     */   
/* 441 */   public Access getAccess(Object key) { return (Access)this.accessMap.get(key); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Call addCall(Object key, Method met, Body body) {
/* 448 */     Object element = this.callMap.get(key);
/* 449 */     if (element == null) {
/* 450 */       element = new Call(met, body);
/* 451 */       this.callMap.put(key, element);
/* 452 */       body.addCall((Call)element);
/* 453 */       met.addCall((Call)element);
/*     */     } 
/* 455 */     return (Call)element;
/*     */   }
/*     */   
/*     */   public Call addCall(Object key, Method met, CodeStripe stripe) {
/* 459 */     Object element = this.callMap.get(key);
/* 460 */     if (element == null) {
/* 461 */       element = new Call(met, stripe);
/* 462 */       this.callMap.put(key, element);
/* 463 */       stripe.addCall((Call)element);
/* 464 */       met.addCall((Call)element);
/*     */     } 
/* 466 */     return (Call)element;
/*     */   }
/*     */ 
/*     */   
/* 470 */   public Call getCall(Object key) { return (Call)this.callMap.get(key); }
/*     */ 
/*     */ 
/*     */   
/* 474 */   public Class getCurrentClass() { return this.currentMMMClass; }
/*     */ 
/*     */ 
/*     */   
/* 478 */   public void setCurrentClass(Class mmmc) { this.currentMMMClass = mmmc; }
/*     */ 
/*     */ 
/*     */   
/* 482 */   public Method getCurrentMethod() { return this.currentMMMMethod; }
/*     */ 
/*     */ 
/*     */   
/* 486 */   public void setCurrentMethod(Method mmmm) { this.currentMMMMethod = mmmm; }
/*     */ 
/*     */ 
/*     */   
/* 490 */   public Package getCurrentPackage() { return this.currentMMMPackage; }
/*     */ 
/*     */ 
/*     */   
/* 494 */   public void setCurrentPackage(Package mmmp) { this.currentMMMPackage = mmmp; }
/*     */ 
/*     */ 
/*     */   
/* 498 */   public Body getCurrentBody() { return this.currentMMMBody; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 504 */   public void setCurrentBody(Body body) { this.currentMMMBody = body; }
/*     */ 
/*     */ 
/*     */   
/* 508 */   public CodeStripe getCurrentStripe() { return this.currentMMMCodeStripe; }
/*     */ 
/*     */   
/*     */   public void setCurrentStripe(CodeStripe mmmcs) {
/* 512 */     this.accessMap.clear();
/* 513 */     this.callMap.clear();
/* 514 */     this.currentMMMCodeStripe = mmmcs;
/* 515 */     if (mmmcs != null) {
/* 516 */       for (Call c : mmmcs.getCallList()) this.callMap.put(c.getFunction(), c); 
/* 517 */       for (Access a : mmmcs.getAccessList()) this.accessMap.put(a.getVariable(), a); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Namespace convertPackageToNamespace(Package pack) {
/* 522 */     Namespace nsp = (Namespace)this.namespacesMap.get(pack);
/* 523 */     if (nsp == null) {
/* 524 */       if (pack == Package.getUnknownPackage()) {
/* 525 */         nsp = Namespace.getUnknownNamespace();
/*     */       } else {
/* 527 */         nsp = new Namespace(pack.getName(), pack.getStatute());
/* 528 */         nsp.setSystem(this.system);
/* 529 */         this.system.addNamespace(nsp);
/*     */       } 
/* 531 */       this.namespacesMap.put(pack, nsp);
/*     */     } 
/*     */     
/* 534 */     return nsp;
/*     */   }
/*     */ 
/*     */   
/* 538 */   public System getSystem() { return this.system; }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 542 */     String myStr = "";
/* 543 */     Collection values = this.packageMap.values();
/*     */     
/* 545 */     Iterator valIterator = values.iterator();
/* 546 */     if (valIterator != null) {
/*     */       do {
/* 548 */         Package actPack = (Package)valIterator.next();
/* 549 */         myStr = String.valueOf(myStr) + actPack.toString();
/* 550 */       } while (valIterator.hasNext());
/*     */     }
/* 552 */     return myStr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public File addFile(Object key, String name) {
/* 559 */     File element = (File)this.fileMap.get(key);
/* 560 */     if (element == null) {
/* 561 */       File temp = new File(name);
/* 562 */       if (temp.getParent() == null) {
/* 563 */         element = new File("", temp.getName());
/*     */       } else {
/* 565 */         element = new File(temp.getParent(), temp.getName());
/*     */       } 
/* 567 */       this.fileMap.put(key, element);
/*     */     } 
/* 569 */     return element;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 576 */   public File getFile(Object key) { return (File)this.fileMap.get(key); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 583 */   public File getCurrentFile() { return this.currentFile; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 590 */   public void setCurrentFile(File file) { this.currentFile = file; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 597 */   public Annotation getCurrentAnnotation() { return this.currentAnnotation; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 605 */   public void setCurrentAnnotation(Annotation currentAnnotation) { this.currentAnnotation = currentAnnotation; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 615 */   public Annotation getAnnotation(String key) { return (Annotation)this.annotationMap.get(key); }
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
/*     */   public Annotation addAnnotation(Object anot, String annotationFullName) {
/*     */     String annotationName;
/* 628 */     if (annotationFullName.contains(".")) {
/* 629 */       annotationName = annotationFullName.substring(annotationFullName.lastIndexOf('.') + 1);
/*     */     } else {
/* 631 */       annotationName = annotationFullName;
/* 632 */     }  Annotation mmma = (Annotation)this.annotationMap.get(annotationFullName);
/* 633 */     if (mmma == null) {
/*     */       
/* 635 */       mmma = new Annotation(annotationName);
/* 636 */       this.annotationMap.put(annotationFullName, mmma);
/* 637 */       if (anot instanceof AnnotationUseSpecification) {
/*     */         
/* 639 */         AnnotationUseSpecification aus = (AnnotationUseSpecification)anot;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 649 */         TypeReference tr = aus.getTypeReference();
/* 650 */         Type ct = null;
/*     */         try {
/* 652 */           ct = ReferenceConverter.getSrcInfo().getType(tr);
/* 653 */         } catch (Exception e) {
/* 654 */           System.out.println(e);
/* 655 */           e.printStackTrace();
/* 656 */           System.out.println("Failed Dependency Error; Exception occured while getting the type\"" + tr.getName() + "\" from TypeReference");
/* 657 */         } catch (TokenMgrError e) {
/* 658 */           System.out.println(e);
/* 659 */           e.printStackTrace();
/*     */         } 
/* 661 */         if (ct != null)
/*     */         {
/* 663 */           ClassType clst = (ClassType)ct;
/*     */           
/* 665 */           Package tempPack = clst.getPackage();
/* 666 */           if (tempPack != null)
/*     */           {
/* 668 */             String packName = tempPack.getFullName();
/* 669 */             if (packName.equals("")) {
/* 670 */               packName = "_ANONYMOUS_PACKAGE_";
/*     */             }
/* 672 */             Package pack = addPackage(tempPack, packName);
/* 673 */             pack.addAnnotation(mmma);
/* 674 */             mmma.setPackage(pack);
/* 675 */             Namespace nsp = convertPackageToNamespace(pack);
/* 676 */             mmma.setScope(nsp);
/*     */ 
/*     */             
/* 679 */             if (clst instanceof ClassFile) {
/* 680 */               ClassFile cf = (ClassFile)clst;
/* 681 */               mmma.setStatute(2);
/* 682 */               mmma.getAnnotationProperties().clear();
/* 683 */               for (Method f : cf.getMethods())
/*     */               {
/* 685 */                 AnnotationProperty ap = new AnnotationProperty(f.getName());
/* 686 */                 ap.setScope(mmma);
/* 687 */                 ap.setStatute(2);
/* 688 */                 ap.setType(ReferenceConverter.getType(f.getReturnType()));
/* 689 */                 mmma.addAnnotationProperty(ap);
/*     */ 
/*     */ 
/*     */               
/*     */               }
/*     */ 
/*     */ 
/*     */             
/*     */             }
/* 698 */             else if (clst instanceof AnnotationDeclaration) {
/* 699 */               addAnnotationDetailsFromItsDeclaration(clst, mmma);
/*     */             }
/*     */           
/*     */           }
/*     */         
/*     */         }
/*     */       
/* 706 */       } else if (anot instanceof AnnotationDeclaration) {
/*     */ 
/*     */         
/* 709 */         Package tempPack = ((AnnotationDeclaration)anot).getPackage();
/* 710 */         if (tempPack != null) {
/*     */           
/* 712 */           String packName = tempPack.getFullName();
/* 713 */           if (packName.equals("")) {
/* 714 */             packName = "_ANONYMOUS_PACKAGE_";
/*     */           }
/* 716 */           Package pack = addPackage(tempPack, packName);
/* 717 */           pack.addAnnotation(mmma);
/* 718 */           mmma.setPackage(pack);
/* 719 */           Namespace nsp = convertPackageToNamespace(pack);
/* 720 */           mmma.setScope(nsp);
/*     */         } 
/* 722 */         addAnnotationDetailsFromItsDeclaration(anot, mmma);
/*     */       } 
/*     */     } 
/* 725 */     return mmma;
/*     */   }
/*     */ 
/*     */   
/*     */   private void addAnnotationDetailsFromItsDeclaration(Object anot, Annotation mmma) {
/* 730 */     AnnotationDeclaration ad = (AnnotationDeclaration)anot;
/* 731 */     mmma.setStatute(1);
/* 732 */     mmma.getAnnotationProperties().clear();
/* 733 */     for (Method f : ad.getMethods()) {
/* 734 */       AnnotationProperty ap = new AnnotationProperty(f.getName());
/* 735 */       ap.setScope(mmma);
/* 736 */       ap.setStatute(1);
/* 737 */       ap.setType(ReferenceConverter.getType(f.getReturnType()));
/* 738 */       mmma.addAnnotationProperty(ap);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 747 */   public AnnotationInstance getCurrentAnnotationInstance() { return this.currentAnnotationInstance; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 756 */   public void setCurrentAnnotationInstance(AnnotationInstance a) { this.currentAnnotationInstance = a; }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\DefaultModelRepository.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */