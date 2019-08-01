/*     */ package lrg.memoria.importer.mcc.loader;
/*     */ import java.util.ArrayList;
/*     */ import lrg.memoria.core.Class;
/*     */ import lrg.memoria.core.DataAbstraction;
/*     */ import lrg.memoria.core.ExplicitlyDefinedType;
/*     */ import lrg.memoria.core.FunctionPointer;
/*     */ import lrg.memoria.core.GenericClass;
/*     */ import lrg.memoria.core.Namespace;
/*     */ import lrg.memoria.core.Primitive;
/*     */ import lrg.memoria.core.Scope;
/*     */ import lrg.memoria.core.TemplateInstance;
/*     */ import lrg.memoria.core.TemplateParameterType;
/*     */ import lrg.memoria.core.Type;
/*     */ import lrg.memoria.core.Union;
/*     */ 
/*     */ public class DefaultTypeVisitor extends DefaultVisitorRoot implements TypeVisitor {
/*     */   private Integer id;
/*     */   private String name;
/*     */   private Location location;
/*     */   private String kind;
/*     */   private Package currentPackage;
/*  22 */   private static Stack lazyTypeScopesStack = new Stack(); private Namespace currentNamespace; private boolean isAbstract; private boolean isInterface; private boolean isGeneric; private int scopeId; private int decoratedTypeID;
/*  23 */   private static Stack lazyFuncsScopesStack = new Stack();
/*     */ 
/*     */   
/*  26 */   public void setId(Integer id) { this.id = id; }
/*     */ 
/*     */   
/*     */   public void setLocation(String fileFullName, String startPosition, String stopPosition) {
/*  30 */     if (fileFullName.compareTo("NULL") == 0) {
/*  31 */       this.location = Location.getUnknownLocation();
/*     */     } else {
/*  33 */       File file = Loader.getInstance().getFileByName(fileFullName);
/*  34 */       this.location = new Location(file);
/*  35 */       this.location.setStartLine((new Integer(startPosition)).intValue());
/*  36 */       if (stopPosition.compareTo("NULL") != 0) {
/*  37 */         this.location.setEndLine((new Integer(stopPosition)).intValue());
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*  42 */   public void setName(String name) { this.name = name; }
/*     */ 
/*     */ 
/*     */   
/*  46 */   public void setKind(String kind) { this.kind = kind; }
/*     */ 
/*     */   
/*     */   public void setPackageId(String packageId) {
/*  50 */     this.currentPackage = null;
/*  51 */     if (!packageId.equals("NULL")) {
/*  52 */       this.currentPackage = Loader.getInstance().getPackage(new Integer(packageId));
/*     */     }
/*  54 */     if (this.currentPackage == null) {
/*  55 */       this.currentPackage = Package.getUnknownPackage();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setNamespaceId(String namespaceId) {
/*  60 */     this.currentNamespace = null;
/*  61 */     if (namespaceId.equals("<NO_ONE>")) {
/*  62 */       this.currentNamespace = Namespace.getAnonymousNamespace();
/*     */       
/*     */       return;
/*     */     } 
/*  66 */     if (namespaceId.equals("NULL") && this.kind.indexOf("library") >= 0)
/*  67 */       this.currentNamespace = Namespace.getAnonymousNamespace(); 
/*  68 */     if (!namespaceId.equals("<ERROR>") && !namespaceId.equals("NULL"))
/*  69 */       this.currentNamespace = Loader.getInstance().getNamespace(new Integer(namespaceId)); 
/*  70 */     if (this.currentNamespace == null) {
/*  71 */       this.currentNamespace = Namespace.getUnknownNamespace();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setIsAbstract(String isAbstract) {
/*  76 */     if (isAbstract.compareTo("1") == 0) {
/*  77 */       this.isAbstract = true;
/*     */     } else {
/*  79 */       this.isAbstract = false;
/*     */     } 
/*     */   }
/*     */   public void setIsInterface(String isInterface) {
/*  83 */     if (isInterface.compareTo("1") == 0) {
/*  84 */       this.isInterface = true;
/*     */     } else {
/*  86 */       this.isInterface = false;
/*     */     } 
/*     */   }
/*     */   public void setIsGeneric(String isGeneric) {
/*  90 */     if (isGeneric.equals("1")) {
/*  91 */       this.isGeneric = true;
/*     */     } else {
/*  93 */       this.isGeneric = false;
/*     */     } 
/*     */   }
/*     */   public void setScopeId(String scopeId) {
/*  97 */     if (!scopeId.equals("NULL") && !scopeId.equals("<NO_ONE>") && !scopeId.equals("<ERROR>")) {
/*  98 */       this.scopeId = Integer.parseInt(scopeId);
/*     */     } else {
/* 100 */       this.scopeId = -1;
/*     */     } 
/*     */   }
/*     */   public void setDecoratedType(String decoratedType) {
/* 104 */     if (!decoratedType.equals("NULL") && !decoratedType.equals("<NO_ONE>") && !decoratedType.equals("<NOT_INIT>")) {
/* 105 */       this.decoratedTypeID = Integer.parseInt(decoratedType);
/*     */     } else {
/* 107 */       this.decoratedTypeID = -1;
/*     */     } 
/*     */   }
/*     */   public void addType() {
/* 111 */     Type currentType = null;
/* 112 */     if (this.kind.compareTo("primitive") == 0)
/* 113 */       Primitive primitive = (Primitive)addPrimitiveType(); 
/* 114 */     if (this.kind.indexOf("class") >= 0) {
/* 115 */       DataAbstraction dataAbstraction = addClassType();
/* 116 */       ((DataAbstraction)dataAbstraction).setStatute(1);
/*     */     } 
/* 118 */     if (this.kind.indexOf("struct") >= 0) {
/* 119 */       DataAbstraction dataAbstraction = addClassType();
/* 120 */       ((Class)dataAbstraction).setStructure();
/* 121 */       ((DataAbstraction)dataAbstraction).setStatute(1);
/*     */     } 
/* 123 */     if (this.kind.indexOf("union") >= 0)
/* 124 */       Union union = addUnionType(); 
/* 125 */     if (this.kind.compareTo("template-par") == 0)
/* 126 */       TemplateParameterType templateParameterType = addTemplateParType(); 
/* 127 */     if (this.kind.compareTo("template-instance") == 0) {
/* 128 */       TemplateInstance templateInstance = addTemplateInstance();
/* 129 */       if (templateInstance == null)
/* 130 */         return;  ((TemplateInstance)templateInstance).setStatute(1);
/*     */     } 
/* 132 */     if (this.kind.compareTo("library-type") == 0) {
/* 133 */       DataAbstraction dataAbstraction = addClassType();
/* 134 */       ((ExplicitlyDefinedType)dataAbstraction).setStatute(2);
/*     */     } 
/* 136 */     if (this.kind.indexOf("decorator") > 0)
/* 137 */       currentType = addDecorator(); 
/* 138 */     if (this.kind.compareTo("pointer-to-function") == 0) {
/* 139 */       currentType = addFunctionPointer();
/*     */     }
/* 141 */     if (currentType == null)
/* 142 */       return;  Loader.getInstance().addType(this.id, currentType);
/* 143 */     if (this.scopeId > 0) {
/* 144 */       if (this.kind.indexOf("in-func") < 0) {
/* 145 */         ArrayList pair = new ArrayList();
/* 146 */         pair.add(currentType);
/* 147 */         pair.add(new Integer(this.scopeId));
/* 148 */         lazyTypeScopesStack.push(pair);
/*     */       } else {
/* 150 */         FunctionBody functionBody = Loader.getInstance().getBody(new Integer(this.scopeId));
/* 151 */         assert functionBody != null : "DefaultTypeVisitor ERROR: could not find the body scope with ID=" + this.scopeId + " for type: " + currentType.getName();
/* 152 */         ((ExplicitlyDefinedType)currentType).setScope(functionBody);
/* 153 */         functionBody.addScopedElement(currentType);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private TemplateInstance addTemplateInstance() throws ClassCastException {
/* 159 */     TemplateInstance ti = null;
/* 160 */     if (this.decoratedTypeID > 0) {
/* 161 */       Type t = Loader.getInstance().getType(new Integer(this.decoratedTypeID));
/* 162 */       if (t instanceof GenericClass) {
/* 163 */         GenericClass generic = (GenericClass)t;
/* 164 */         ti = new TemplateInstance(generic, this.name);
/* 165 */         generic.addTemplateInstance(ti);
/* 166 */         generic.getScope().addScopedElement(ti);
/*     */       } else {
/*     */         
/* 169 */         System.out.println("ERROR " + this.name + "is not a generic Class and has template-instances");
/*     */       } 
/* 171 */     }  return ti;
/*     */   }
/*     */   
/*     */   public void typesEOF() {
/* 175 */     Loader lo = Loader.getInstance();
/*     */     
/* 177 */     while (!lazyTypeScopesStack.empty()) {
/* 178 */       ArrayList pair = (ArrayList)lazyTypeScopesStack.pop();
/* 179 */       if (pair.get(0) instanceof ExplicitlyDefinedType) {
/* 180 */         ExplicitlyDefinedType scoped = (ExplicitlyDefinedType)pair.get(0);
/* 181 */         Integer scopeId = (Integer)pair.get(1);
/* 182 */         Scope scope = (Scope)lo.getType(scopeId);
/* 183 */         assert scope != null : "DefaultTypesVisitor ERROR: scope is null for: " + scoped.getFullName();
/* 184 */         scoped.setScope(scope);
/* 185 */         scope.addScopedElement(scoped);
/* 186 */         Scope nsp = scope.getScope();
/* 187 */         while (!(nsp instanceof Namespace))
/* 188 */           nsp = nsp.getScope(); 
/* 189 */         ((Namespace)nsp).addType(scoped);
/* 190 */         if (scoped instanceof TemplateParameterType) {
/* 191 */           ((GenericClass)scope).addTemplateParameters((TemplateParameterType)scoped);
/*     */         }
/*     */       } 
/*     */     } 
/* 195 */     lo.setLazyFuncsScopesStack(lazyFuncsScopesStack);
/* 196 */     lazyTypeScopesStack.clear();
/*     */   }
/*     */   private Type addDecorator() {
/*     */     DataAbstraction dataAbstraction;
/* 200 */     TypedefDecorator typedefDecorator = null;
/*     */     
/* 202 */     if (this.decoratedTypeID > 0) {
/* 203 */       dataAbstraction = Loader.getInstance().getType(new Integer(this.decoratedTypeID));
/*     */     } else {
/* 205 */       dataAbstraction = Class.getUnknownClass();
/*     */     } 
/* 207 */     if (dataAbstraction == null) return null;
/*     */     
/* 209 */     if (this.kind.indexOf("ptr") >= 0)
/* 210 */       PointerDecorator pointerDecorator = new PointerDecorator(dataAbstraction); 
/* 211 */     if (this.kind.indexOf("array") >= 0)
/* 212 */       ArrayDecorator arrayDecorator = new ArrayDecorator(dataAbstraction); 
/* 213 */     if (this.kind.indexOf("ref") >= 0)
/* 214 */       ReferenceDecorator referenceDecorator = new ReferenceDecorator(dataAbstraction); 
/* 215 */     if (this.kind.indexOf("typedef") >= 0) {
/* 216 */       typedefDecorator = new TypedefDecorator(dataAbstraction, this.name);
/* 217 */       setScopeAndLocation((ExplicitlyDefinedType)typedefDecorator);
/*     */     } else {
/* 219 */       dataAbstraction.getScope().addScopedElement(typedefDecorator);
/*     */     } 
/* 221 */     return typedefDecorator;
/*     */   }
/*     */   
/*     */   private Type addPrimitiveType() {
/* 225 */     Primitive pr = new Primitive(this.name);
/* 226 */     pr.setStatute(2);
/* 227 */     Namespace ansp = Namespace.getGlobalNamespace();
/* 228 */     ansp.addType(pr);
/* 229 */     return pr;
/*     */   }
/*     */   
/*     */   private Type addFunctionPointer() {
/* 233 */     FunctionPointer fp = new FunctionPointer(this.name);
/* 234 */     fp.setStatute(1);
/* 235 */     Namespace ansp = Namespace.getAnonymousNamespace();
/* 236 */     fp.setScope(Namespace.getAnonymousNamespace());
/* 237 */     ansp.addType(fp);
/* 238 */     return fp;
/*     */   }
/*     */   
/*     */   private TemplateParameterType addTemplateParType() {
/* 242 */     TemplateParameterType tpt = new TemplateParameterType(this.name);
/* 243 */     setScopeAndLocation(tpt);
/* 244 */     return tpt;
/*     */   }
/*     */   
/*     */   private DataAbstraction addClassType() {
/*     */     Class cls;
/* 249 */     if (this.isGeneric) {
/* 250 */       cls = new GenericClass(this.name);
/*     */     } else {
/* 252 */       cls = new Class(this.name);
/* 253 */     }  setScopeAndLocation(cls);
/* 254 */     if (this.isAbstract)
/* 255 */       cls.setAbstract(); 
/* 256 */     if (this.isInterface)
/* 257 */       cls.setAbstract(); 
/* 258 */     return cls;
/*     */   }
/*     */   
/*     */   private void setScopeAndLocation(ExplicitlyDefinedType st) {
/* 262 */     st.setLocation(this.location);
/* 263 */     st.setPackage(this.currentPackage);
/* 264 */     this.currentPackage.addType(st);
/* 265 */     st.setScope(this.currentNamespace);
/* 266 */     this.currentNamespace.addType(st);
/*     */   }
/*     */   
/*     */   private Union addUnionType() {
/* 270 */     Union ut = new Union(this.name);
/* 271 */     setScopeAndLocation(ut);
/* 272 */     ut.setStatute(1);
/* 273 */     return ut;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\mcc\loader\DefaultTypeVisitor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */