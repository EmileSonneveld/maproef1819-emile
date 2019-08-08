/*     */ package lrg.memoria.core;
/*     */ 
/*     */ public abstract class DataAbstraction
/*     */   extends ExplicitlyDefinedType
/*     */   implements Scope {
/*     */   protected ModelElementList<Attribute> attributes;
/*     */   protected ModelElementList<Method> methods;
/*     */   protected ModelElementList<DataAbstraction> supertypes;
/*     */   protected ModelElementList<InheritanceRelation> relationAsSuperType;
/*     */   protected ModelElementList<DataAbstraction> descendants;
/*     */   protected ModelElementList<InheritanceRelation> relationAsSubType;
/*     */   protected ModelElementList<Type> innerTypes;
/*     */   
/*     */   public DataAbstraction(String name) {
/*  15 */     super(name);
/*  16 */     this.attributes = new ModelElementList();
/*  17 */     this.methods = new ModelElementList();
/*     */   }
/*     */   
/*     */   protected DataAbstraction(DataAbstraction oldType) {
/*  21 */     super(oldType);
/*  22 */     this.innerTypes = oldType.innerTypes;
/*  23 */     this.attributes = oldType.attributes;
/*  24 */     this.methods = oldType.methods;
/*  25 */     this.descendants = oldType.descendants;
/*  26 */     this.supertypes = oldType.supertypes;
/*  27 */     this.relationAsSuperType = oldType.relationAsSuperType;
/*  28 */     this.relationAsSubType = oldType.relationAsSubType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  35 */   public void addAttribute(Attribute attribute) { this.attributes.add(attribute); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  42 */   public void setAttributes(ModelElementList<Attribute> attributes) { this.attributes = attributes; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   public void addMethod(Method method) { this.methods.add(method); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  56 */   public void setMethods(ModelElementList<Method> methods) { this.methods = methods; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addInnerType(Type type) {
/*  63 */     if (this.innerTypes == null)
/*  64 */       this.innerTypes = new ModelElementList(); 
/*  65 */     this.innerTypes.add(type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ModelElementList<Type> getInnerList() {
/*  72 */     if (this.innerTypes == null) return new ModelElementList(); 
/*  73 */     return this.innerTypes;
/*     */   }
/*     */ 
/*     */   
/*  77 */   public ModelElementList<Attribute> getAttributeList() { return this.attributes; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  84 */   public ModelElementList<Method> getMethodList() { return this.methods; }
/*     */ 
/*     */ 
/*     */   
/*  88 */   public boolean isInterface() { return false; }
/*     */ 
/*     */ 
/*     */   
/*  92 */   public boolean isUnion() { return false; }
/*     */ 
/*     */ 
/*     */   
/*  96 */   public boolean isStructure() { return false; }
/*     */ 
/*     */   
/*     */   public void addScopedElement(Scopable element) {
/* 100 */     if (element instanceof Attribute)
/* 101 */       addAttribute((Attribute)element); 
/* 102 */     if (element instanceof Method)
/* 103 */       addMethod((Method)element); 
/* 104 */     if (element instanceof Type)
/* 105 */       addInnerType((Type)element); 
/*     */   }
/*     */   
/*     */   public ModelElementList getScopedElements() {
/* 109 */     ModelElementList scopedElements = new ModelElementList();
/* 110 */     scopedElements.addAll(this.attributes);
/* 111 */     scopedElements.addAll(this.methods);
/* 112 */     if (this.innerTypes != null)
/* 113 */       scopedElements.addAll(this.innerTypes); 
/* 114 */     return scopedElements;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAncestor(DataAbstraction ancestor) {
/* 121 */     if (this.supertypes == null)
/* 122 */       this.supertypes = new ModelElementList(); 
/* 123 */     if (ancestor != null) {
/* 124 */       this.supertypes.add(ancestor);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public DataAbstraction getFirstAncestor() {
/* 131 */     if (this.supertypes == null)
/* 132 */       return null; 
/* 133 */     if (this.supertypes.size() > 0) {
/* 134 */       return (DataAbstraction)this.supertypes.get(0);
/*     */     }
/* 136 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ModelElementList<DataAbstraction> getAncestorsList() {
/* 143 */     if (this.supertypes == null)
/* 144 */       this.supertypes = new ModelElementList(); 
/* 145 */     return this.supertypes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addDescendant(DataAbstraction descendant) {
/* 152 */     if (this.descendants == null)
/* 153 */       this.descendants = new ModelElementList(); 
/* 154 */     this.descendants.add(descendant);
/*     */   }
/*     */   
/*     */   public void addRelationAsDescendent(InheritanceRelation rel) {
/* 158 */     if (this.relationAsSubType == null) {
/* 159 */       this.relationAsSubType = new ModelElementList();
/*     */     }
/* 161 */     this.relationAsSubType.add(rel);
/*     */   }
/*     */   
/*     */   public void addRelationAsAncestor(InheritanceRelation rel) {
/* 165 */     if (this.relationAsSuperType == null) {
/* 166 */       this.relationAsSuperType = new ModelElementList();
/*     */     }
/* 168 */     this.relationAsSuperType.add(rel);
/*     */   }
/*     */   
/*     */   public ModelElementList<InheritanceRelation> getRelationAsSubClass() {
/* 172 */     if (this.relationAsSubType == null) {
/* 173 */       this.relationAsSubType = new ModelElementList();
/*     */     }
/* 175 */     return this.relationAsSubType;
/*     */   }
/*     */   
/*     */   public ModelElementList<InheritanceRelation> getRelationAsSuperClass() {
/* 179 */     if (this.relationAsSuperType == null) {
/* 180 */       this.relationAsSuperType = new ModelElementList();
/*     */     }
/* 182 */     return this.relationAsSuperType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ModelElementList<Class> getInterfaces() {
/* 189 */     ModelElementList<Class> interfacesList = new ModelElementList<Class>();
/* 190 */     for (DataAbstraction currentDataAbstraction : getAncestorsList()) {
/* 191 */       if (currentDataAbstraction instanceof Class && 
/* 192 */         currentDataAbstraction.isInterface())
/* 193 */         interfacesList.add((Class)currentDataAbstraction); 
/*     */     } 
/* 195 */     return interfacesList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ModelElementList<DataAbstraction> getDescendants() {
/* 202 */     if (this.descendants == null)
/* 203 */       this.descendants = new ModelElementList(); 
/* 204 */     return this.descendants;
/*     */   }
/*     */   
/*     */   boolean restore() {
/* 208 */     if (super.restore()) {
/* 209 */       this.attributes.restore();
/* 210 */       if (this.descendants != null)
/* 211 */         this.descendants.restore(); 
/* 212 */       if (this.supertypes != null)
/* 213 */         this.supertypes.restore(); 
/* 214 */       if (this.relationAsSuperType != null)
/* 215 */         this.relationAsSuperType.restore(); 
/* 216 */       if (this.relationAsSubType != null)
/* 217 */         this.relationAsSubType.restore(); 
/* 218 */       this.methods.restore();
/* 219 */       if (this.innerTypes != null)
/* 220 */         this.innerTypes.restore(); 
/* 221 */       return true;
/*     */     } 
/* 223 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\DataAbstraction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */