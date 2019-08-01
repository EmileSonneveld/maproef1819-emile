/*     */ package classes.lrg.insider.metamodel;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Set;
/*     */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*     */ import lrg.common.abstractions.managers.EntityTypeManager;
/*     */ import lrg.common.metamodel.ModelBuilder;
/*     */ import lrg.common.utils.ProgressObserver;
/*     */ import lrg.insider.metamodel.Address;
/*     */ import lrg.insider.metamodel.MemoriaModelBuilder;
/*     */ import lrg.insider.plugins.core.properties.memoria.inheritance.Scope;
/*     */ import lrg.memoria.core.Annotation;
/*     */ import lrg.memoria.core.AnnotationInstance;
/*     */ import lrg.memoria.core.Attribute;
/*     */ import lrg.memoria.core.Class;
/*     */ import lrg.memoria.core.DataAbstraction;
/*     */ import lrg.memoria.core.Function;
/*     */ import lrg.memoria.core.InheritanceRelation;
/*     */ import lrg.memoria.core.LocalVariable;
/*     */ import lrg.memoria.core.Method;
/*     */ import lrg.memoria.core.ModelElementList;
/*     */ import lrg.memoria.core.Namespace;
/*     */ import lrg.memoria.core.Package;
/*     */ import lrg.memoria.core.Parameter;
/*     */ import lrg.memoria.core.Subsystem;
/*     */ import lrg.memoria.core.System;
/*     */ import lrg.memoria.core.Type;
/*     */ import lrg.memoria.core.Variable;
/*     */ 
/*     */ public abstract class MemoriaModelBuilder extends ModelBuilder {
/*     */   protected String sourcePath;
/*     */   protected String cachePath;
/*     */   protected ProgressObserver progressObserver;
/*     */   protected System currentSystem;
/*     */   protected String programmingLanguage;
/*     */   
/*     */   public MemoriaModelBuilder(String sourcePath, String cachePath, ProgressObserver observer) {
/*  39 */     this.programmingLanguage = "";
/*     */ 
/*     */     
/*  42 */     this.sourcePath = sourcePath;
/*  43 */     this.cachePath = cachePath;
/*  44 */     this.progressObserver = observer;
/*     */   }
/*     */   public MemoriaModelBuilder(String sourcePath, String cachePath, ProgressObserver observer, String language) {
/*     */     this.programmingLanguage = "";
/*  48 */     this.sourcePath = sourcePath;
/*  49 */     this.cachePath = cachePath;
/*  50 */     this.progressObserver = observer;
/*  51 */     this.programmingLanguage = language;
/*     */   }
/*     */ 
/*     */   
/*  55 */   public System getCurrentSystem() { return this.currentSystem; }
/*     */ 
/*     */   
/*     */   public void buildModel() throws Exception {
/*  59 */     loadModel();
/*  60 */     createEntityTypes();
/*     */     
/*  62 */     attachSpecificEntityTypes();
/*     */     
/*  64 */     this.currentSystem.programmingLanguage = this.programmingLanguage;
/*     */   }
/*     */   
/*     */   public void cleanModel() throws Exception {
/*  68 */     System.unloadSystemFromMemory(this.currentSystem);
/*  69 */     this.currentSystem = null;
/*     */   }
/*     */   
/*     */   protected abstract void loadModel() throws Exception;
/*     */   
/*     */   private void attachSpecificEntityTypes() throws Exception {
/*  75 */     for (int i = 0; i < this.currentSystem.getNamespaces().size(); i++) {
/*  76 */       Namespace currentNamespace = (Namespace)this.currentSystem.getNamespaces().get(i);
/*     */       
/*  78 */       for (int j = 0; j < currentNamespace.getGlobalFunctionsList().size(); j++) {
/*  79 */         Function aFunction = (Function)currentNamespace.getGlobalFunctionsList().get(j);
/*  80 */         if (aFunction instanceof lrg.memoria.core.GlobalFunction) {
/*  81 */           registerLocalVariables(aFunction);
/*  82 */           registerParameters(aFunction);
/*  83 */           aFunction.setEntityType(EntityTypeManager.getEntityTypeForName("global function"));
/*  84 */           this.addressMap.put(Address.buildFor(aFunction), aFunction);
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/*  89 */       for (int j = 0; j < currentNamespace.getGlobalVariablesList().size(); j++) {
/*  90 */         Variable aVariable = (Variable)currentNamespace.getGlobalVariablesList().get(j);
/*  91 */         if (aVariable instanceof lrg.memoria.core.GlobalVariable) {
/*  92 */           aVariable.setEntityType(EntityTypeManager.getEntityTypeForName("global variable"));
/*  93 */           this.addressMap.put(Address.buildFor(aVariable), aVariable);
/*     */         } 
/*     */       } 
/*  96 */       for (Type currentType : currentNamespace.getAllTypesList()) {
/*  97 */         if (!(currentType instanceof Class)) {
/*  98 */           currentType.setEntityType(EntityTypeManager.getEntityTypeForName("type"));
/*  99 */           this.addressMap.put(Address.buildFor(currentType), currentType);
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 104 */       currentNamespace.setEntityType(EntityTypeManager.getEntityTypeForName("namespace"));
/* 105 */       this.addressMap.put(Address.buildFor(currentNamespace), currentNamespace);
/*     */     } 
/*     */ 
/*     */     
/* 109 */     int i = 0;
/* 110 */     for (Package currentPackage : this.currentSystem.getPackages()) {
/* 111 */       int j = 0;
/* 112 */       for (Type currentType : currentPackage.getAllTypesList()) {
/* 113 */         if (currentType instanceof Class) {
/* 114 */           currentType.setEntityType(EntityTypeManager.getEntityTypeForName("class"));
/* 115 */           this.addressMap.put(Address.buildFor(currentType), currentType);
/*     */           
/* 117 */           Class currentDataAbstraction = (Class)currentType;
/*     */           
/* 119 */           for (DataAbstraction aSuperType : currentDataAbstraction.getAncestorsList()) {
/* 120 */             aSuperType.setEntityType(EntityTypeManager.getEntityTypeForName("class"));
/* 121 */             this.addressMap.put(Address.buildFor(aSuperType), aSuperType);
/*     */           } 
/* 123 */           for (int k = 0; k < currentDataAbstraction.getMethodList().size(); k++) {
/* 124 */             Method currentMethod = (Method)currentDataAbstraction.getMethodList().get(k);
/* 125 */             registerLocalVariables(currentMethod);
/* 126 */             registerParameters(currentMethod);
/* 127 */             registerAnnotationInstances(currentMethod);
/* 128 */             currentMethod.setEntityType(EntityTypeManager.getEntityTypeForName("method"));
/* 129 */             this.addressMap.put(Address.buildFor(currentMethod), currentMethod);
/*     */           } 
/* 131 */           for (int k = 0; k < currentDataAbstraction.getAttributeList().size(); k++) {
/* 132 */             Attribute currentAttribute = (Attribute)currentDataAbstraction.getAttributeList().get(k);
/*     */             
/* 134 */             for (int l = 0; l < currentAttribute.getAnnotations().size(); l++) {
/* 135 */               AnnotationInstance currentAnnotationInstance = (AnnotationInstance)currentAttribute.getAnnotations().get(l);
/* 136 */               currentAnnotationInstance.setEntityType(EntityTypeManager.getEntityTypeForName("annotation instance"));
/* 137 */               this.addressMap.put(Address.buildFor(currentAnnotationInstance), currentAnnotationInstance);
/*     */             } 
/*     */             
/* 140 */             currentAttribute.setEntityType(EntityTypeManager.getEntityTypeForName("attribute"));
/* 141 */             this.addressMap.put(Address.buildFor(currentAttribute), currentAttribute);
/*     */           } 
/*     */ 
/*     */           
/* 145 */           for (int k = 0; k < currentDataAbstraction.getAnnotations().size(); k++) {
/* 146 */             AnnotationInstance currentAnnotationInstance = (AnnotationInstance)currentDataAbstraction.getAnnotations().get(k);
/* 147 */             currentAnnotationInstance.setEntityType(EntityTypeManager.getEntityTypeForName("annotation instance"));
/* 148 */             this.addressMap.put(Address.buildFor(currentAnnotationInstance), currentAnnotationInstance);
/*     */           } 
/*     */           continue;
/*     */         } 
/* 152 */         if (currentType instanceof lrg.memoria.core.TypedefDecorator || currentType instanceof DataAbstraction) {
/* 153 */           currentType.setEntityType(EntityTypeManager.getEntityTypeForName("type"));
/* 154 */           this.addressMap.put(Address.buildFor(currentType), currentType);
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 159 */       if (currentPackage.getAnnotationsList() != null) {
/* 160 */         for (Annotation currentAnnotation : currentPackage.getAnnotationsList()) {
/*     */ 
/*     */           
/* 163 */           for (int l = 0; l < currentAnnotation.getAnnotations().size(); l++) {
/* 164 */             AnnotationInstance currentAnnotationInstance = (AnnotationInstance)currentAnnotation.getAnnotations().get(l);
/* 165 */             currentAnnotationInstance.setEntityType(EntityTypeManager.getEntityTypeForName("annotation instance"));
/* 166 */             this.addressMap.put(Address.buildFor(currentAnnotationInstance), currentAnnotationInstance);
/*     */           } 
/*     */           
/* 169 */           currentAnnotation.setEntityType(EntityTypeManager.getEntityTypeForName("annotation"));
/* 170 */           this.addressMap.put(Address.buildFor(currentAnnotation), currentAnnotation);
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/* 175 */       for (int k = 0; k < currentPackage.getAnnotations().size(); k++) {
/* 176 */         AnnotationInstance currentAnnotationInstance = (AnnotationInstance)currentPackage.getAnnotations().get(k);
/* 177 */         currentAnnotationInstance.setEntityType(EntityTypeManager.getEntityTypeForName("annotation instance"));
/* 178 */         this.addressMap.put(Address.buildFor(currentAnnotationInstance), currentAnnotationInstance);
/*     */       } 
/*     */       
/* 181 */       currentPackage.setEntityType(EntityTypeManager.getEntityTypeForName("package"));
/* 182 */       this.addressMap.put(Address.buildFor(currentPackage), currentPackage);
/*     */     } 
/*     */     
/* 185 */     this.currentSystem.setEntityType(EntityTypeManager.getEntityTypeForName("system"));
/* 186 */     String systemName = this.currentSystem.getName();
/* 187 */     this.currentSystem.setName(systemName.substring(systemName.lastIndexOf("\\") + 1));
/* 188 */     this.addressMap.put(Address.buildForRoot(), this.currentSystem);
/*     */     
/* 190 */     Scope.clear();
/* 191 */     for (Package currentPackage : this.currentSystem.getPackages()) {
/* 192 */       for (Type currentType : currentPackage.getAllTypesList()) {
/* 193 */         if (currentType instanceof DataAbstraction) {
/* 194 */           DataAbstraction currentDataAbstraction = (DataAbstraction)currentType;
/* 195 */           ModelElementList modelElementList = currentDataAbstraction.getRelationAsSuperClass();
/* 196 */           for (int k = 0; k < modelElementList.size(); k++) {
/* 197 */             InheritanceRelation rel = (InheritanceRelation)modelElementList.get(k);
/* 198 */             Scope.registerScopeProperty(this.currentSystem, rel);
/* 199 */             rel.setEntityType(EntityTypeManager.getEntityTypeForName("inheritance relation"));
/* 200 */             this.addressMap.put(String.valueOf(Address.buildFor(currentDataAbstraction)) + "<->" + Address.buildFor(rel.getSubClass()), rel);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 205 */     registerSubsystems(this.currentSystem);
/*     */   }
/*     */   
/*     */   private void registerSubsystems(System currentSystem) {
/* 209 */     HashMap<String, ArrayList> theSubsystems = new HashMap<String, ArrayList>();
/* 210 */     int NESTING_LEVEL = 3;
/*     */     
/* 212 */     ModelElementList<Package> thePackages = currentSystem.getPackages();
/*     */     
/* 214 */     for (Package aPackage : thePackages) {
/* 215 */       if (aPackage.getStatute() != 1)
/* 216 */         continue;  String subsystemName = getSubsystemName(aPackage.getName(), NESTING_LEVEL);
/* 217 */       addPackageToSubsystemMap(subsystemName, aPackage, theSubsystems);
/*     */     } 
/*     */     
/* 220 */     Set<String> keys = theSubsystems.keySet();
/*     */     
/* 222 */     ArrayList<AbstractEntityInterface> groupOfSubsystems = new ArrayList<AbstractEntityInterface>();
/* 223 */     for (String subsystemName : keys) {
/* 224 */       Subsystem aSubsystem = new Subsystem(subsystemName, (ArrayList)theSubsystems.get(subsystemName));
/* 225 */       aSubsystem.setEntityType(EntityTypeManager.getEntityTypeForName("subsystem"));
/* 226 */       currentSystem.addSubsystem(aSubsystem);
/* 227 */       this.addressMap.put(Address.buildFor(aSubsystem), aSubsystem);
/* 228 */       groupOfSubsystems.add(aSubsystem);
/*     */     } 
/*     */     
/* 231 */     for (Subsystem aSubsystem : currentSystem.getSubsystems()) {
/* 232 */       for (Package aPackage : aSubsystem.getPackages())
/* 233 */         aPackage.setSubsystem(aSubsystem); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addPackageToSubsystemMap(String subsystemName, AbstractEntityInterface aPackage, HashMap<String, ArrayList> theSubsystems) {
/* 238 */     ArrayList<AbstractEntityInterface> thePackages = (ArrayList)theSubsystems.get(subsystemName);
/*     */     
/* 240 */     if (thePackages == null) {
/* 241 */       thePackages = new ArrayList<AbstractEntityInterface>();
/* 242 */       theSubsystems.put(subsystemName, thePackages);
/*     */     } 
/*     */     
/* 245 */     thePackages.add(aPackage);
/*     */   }
/*     */   
/*     */   private String getSubsystemName(String name, int nestingLevel) {
/* 249 */     int crtIndex = 0;
/*     */     
/* 251 */     for (int i = 0; i < nestingLevel; i++) {
/* 252 */       int crtDot = name.indexOf(".", crtIndex);
/* 253 */       if (crtDot == -1)
/* 254 */         return name; 
/* 255 */       crtIndex = crtDot + 1;
/*     */     } 
/*     */     
/* 258 */     return name.substring(0, crtIndex - 1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void registerAnnotationInstances(Method currentMethod) {
/* 264 */     for (int k = 0; k < currentMethod.getAnnotations().size(); k++) {
/* 265 */       AnnotationInstance currentAnnotationInstance = (AnnotationInstance)currentMethod.getAnnotations().get(k);
/* 266 */       currentAnnotationInstance.setEntityType(EntityTypeManager.getEntityTypeForName("annotation instance"));
/* 267 */       this.addressMap.put(Address.buildFor(currentAnnotationInstance), currentAnnotationInstance);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void registerLocalVariables(Function currentFunction) {
/* 272 */     for (LocalVariable lv : currentFunction.getBody().getLocalVarList()) {
/*     */ 
/*     */       
/* 275 */       for (int l = 0; l < lv.getAnnotations().size(); l++) {
/* 276 */         AnnotationInstance currentAnnotationInstance = (AnnotationInstance)lv.getAnnotations().get(l);
/* 277 */         currentAnnotationInstance.setEntityType(EntityTypeManager.getEntityTypeForName("annotation instance"));
/* 278 */         this.addressMap.put(Address.buildFor(currentAnnotationInstance), currentAnnotationInstance);
/*     */       } 
/*     */       
/* 281 */       lv.setEntityType(EntityTypeManager.getEntityTypeForName("local variable"));
/* 282 */       this.addressMap.put(Address.buildFor(lv), lv);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void registerParameters(Function currentFunction) {
/* 287 */     for (Parameter param : currentFunction.getParameterList()) {
/*     */ 
/*     */       
/* 290 */       for (int l = 0; l < param.getAnnotations().size(); l++) {
/* 291 */         AnnotationInstance currentAnnotationInstance = (AnnotationInstance)param.getAnnotations().get(l);
/* 292 */         currentAnnotationInstance.setEntityType(EntityTypeManager.getEntityTypeForName("annotation instance"));
/* 293 */         this.addressMap.put(Address.buildFor(currentAnnotationInstance), currentAnnotationInstance);
/*     */       } 
/*     */       
/* 296 */       param.setEntityType(EntityTypeManager.getEntityTypeForName("parameter"));
/* 297 */       this.addressMap.put(Address.buildFor(param), param);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void registerEntityTypes() throws Exception {
/* 302 */     EntityTypeManager.registerEntityType("result", "");
/* 303 */     EntityTypeManager.registerEntityType("numerical", "");
/* 304 */     EntityTypeManager.registerEntityType("boolean", "");
/* 305 */     EntityTypeManager.registerEntityType("string", "");
/*     */     
/* 307 */     EntityTypeManager.registerEntityType("group", "");
/*     */     
/* 309 */     EntityTypeManager.registerEntityType("system", "");
/* 310 */     EntityTypeManager.registerEntityType("subsystem", "system");
/* 311 */     EntityTypeManager.registerEntityType("package", "subsystem");
/*     */     
/* 313 */     EntityTypeManager.registerEntityType("class", "package");
/* 314 */     EntityTypeManager.registerEntityType("type", "namespace");
/* 315 */     EntityTypeManager.registerEntityType("method", "class");
/* 316 */     EntityTypeManager.registerEntityType("attribute", "class");
/* 317 */     EntityTypeManager.registerEntityType("local variable", "method");
/* 318 */     EntityTypeManager.registerEntityType("local variable", "global function");
/* 319 */     EntityTypeManager.registerEntityType("parameter", "method");
/* 320 */     EntityTypeManager.registerEntityType("parameter", "global function");
/*     */     
/* 322 */     EntityTypeManager.registerEntityType("global function", "package");
/* 323 */     EntityTypeManager.registerEntityType("global variable", "package");
/* 324 */     EntityTypeManager.registerEntityType("inheritance relation", "system");
/*     */ 
/*     */ 
/*     */     
/* 328 */     EntityTypeManager.registerEntityType("component", "");
/*     */ 
/*     */     
/* 331 */     EntityTypeManager.registerEntityType("annotation", "package");
/* 332 */     EntityTypeManager.registerEntityType("annotation instance", "");
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\metamodel\MemoriaModelBuilder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */