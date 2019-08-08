/*     */ package lrg.memoria.core;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ 
/*     */ public class Package
/*     */   extends NamedModelElement
/*     */ {
/*     */   public static final String ANONYMOUS_PACKAGE_NAME = "_ANONYMOUS_PACKAGE_";
/*     */   public static final String UNKNOWN_PACKAGE_NAME = "_UNKNOWN_PACKAGE_";
/*     */   private ModelElementList<Type> typesList;
/*     */   private ModelElementList<GlobalVariable> globalVariablesList;
/*     */   private ModelElementList<GlobalFunction> globalFunctionsList;
/*     */   private ModelElementList<Annotation> annotationsList;
/*     */   private System system;
/*     */   private Subsystem subsystem;
/*     */   
/*  17 */   public static Package getAnonymousPackage() { return ModelElementsRepository.getCurrentModelElementsRepository().getAnonymousPackage(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  25 */   public static Package getUnknownPackage() { return ModelElementsRepository.getCurrentModelElementsRepository().getUnknownPackage(); }
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
/*     */   public void addAnnotation(Annotation a) {
/*  37 */     if (this.annotationsList == null)
/*  38 */       this.annotationsList = new ModelElementList(); 
/*  39 */     this.annotationsList.add(a);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Package(String name) {
/*  49 */     super(name);
/*  50 */     this.typesList = new ModelElementList();
/*  51 */     this.globalVariablesList = new ModelElementList();
/*  52 */     this.globalFunctionsList = new ModelElementList();
/*  53 */     this.annotationsList = new ModelElementList();
/*     */   }
/*     */   
/*     */   protected Package(Package pack) {
/*  57 */     super(pack);
/*  58 */     this.typesList = pack.typesList;
/*  59 */     this.globalVariablesList = pack.globalVariablesList;
/*  60 */     this.globalFunctionsList = pack.globalFunctionsList;
/*  61 */     this.annotationsList = pack.annotationsList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addType(Type type) {
/*  68 */     for (Type aType : this.typesList) { if (type.getName().compareTo(aType.getName()) == 0)
/*  69 */         return;  }  this.typesList.add(type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  77 */   public ModelElementList<Type> getAllTypesList() { return this.typesList; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  84 */   public void setAllTypesList(ModelElementList<Type> types) { this.typesList = types; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ModelElementList<Type> getTypesList() {
/*  93 */     ModelElementList<Type> temp = new ModelElementList<Type>();
/*  94 */     HashMap<String, Type> types = new HashMap<String, Type>();
/*     */     
/*  96 */     for (Type current : this.typesList) {
/*  97 */       if (current.getScope() instanceof Namespace && types.get(current.getFullName()) == null) {
/*  98 */         temp.add(current);
/*  99 */         types.put(current.getFullName(), current);
/*     */       } 
/* 101 */     }  return temp;
/*     */   }
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
/* 117 */   public void addGlobalVariable(GlobalVariable var) { this.globalVariablesList.add(var); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 124 */   public void setGlobalVariables(ModelElementList<GlobalVariable> globalVariables) { this.globalVariablesList = globalVariables; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 131 */   public ModelElementList<GlobalVariable> getGlobalVariablesList() { return this.globalVariablesList; }
/*     */ 
/*     */ 
/*     */   
/* 135 */   public ModelElementList<Annotation> getAnnotationsList() { return this.annotationsList; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 142 */   public ModelElementList<GlobalFunction> getGlobalFunctionsList() { return this.globalFunctionsList; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 149 */   public void addGlobalFunction(GlobalFunction func) { this.globalFunctionsList.add(func); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 156 */   public void setGlobalFunctions(ModelElementList<GlobalFunction> globalFunctions) { this.globalFunctionsList = globalFunctions; }
/*     */ 
/*     */ 
/*     */   
/* 160 */   public void setSystem(System s) { this.system = s; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 167 */   public System getSystem() { return this.system; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ModelElementList<DataAbstraction> getAbstractDataTypes() {
/* 174 */     ModelElementList<DataAbstraction> onlyAbstractDataTypes = new ModelElementList<DataAbstraction>();
/* 175 */     for (int i = 0; i < this.typesList.size(); i++) {
/* 176 */       if (this.typesList.get(i) instanceof DataAbstraction)
/* 177 */         onlyAbstractDataTypes.add((DataAbstraction)this.typesList.get(i)); 
/* 178 */     }  return onlyAbstractDataTypes;
/*     */   }
/*     */   
/*     */   public ModelElementList getScopedElements() {
/* 182 */     ModelElementList scopedElements = new ModelElementList();
/* 183 */     scopedElements.addAll(this.typesList);
/* 184 */     scopedElements.addAll(this.globalFunctionsList);
/* 185 */     scopedElements.addAll(this.globalVariablesList);
/* 186 */     scopedElements.addAll(this.annotationsList);
/* 187 */     return scopedElements;
/*     */   }
/*     */ 
/*     */   
/* 191 */   public void accept(ModelVisitor v) { v.visitPackage(this); }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 195 */     StringBuffer sir = new StringBuffer("\tPackage: ");
/* 196 */     sir.append(this.name);
/* 197 */     sir.append("\n\t - contained typesList:\n");
/* 198 */     for (Type type : this.typesList)
/* 199 */       sir.append(type); 
/* 200 */     sir.append("\n\t - contained globalVariablesList:\n");
/* 201 */     for (Variable var : this.globalVariablesList)
/* 202 */       sir.append(var); 
/* 203 */     sir.append("\n\t - contained annotationsList:\n");
/* 204 */     for (Annotation an : this.annotationsList)
/* 205 */       sir.append(an); 
/* 206 */     if (this.annotations != null) {
/* 207 */       sir.append("\n\t - annotated with annotations: ");
/* 208 */       for (AnnotationInstance ai : this.annotations) {
/* 209 */         sir.append("\n\t\t -" + ai.getAnnotation().getFullName());
/* 210 */         for (int i = 0; i < ai.getPropertyValuePairs().size(); i++) {
/* 211 */           sir.append("\n\t\t\t");
/* 212 */           sir.append(((AnnotationPropertyValuePair)ai.getPropertyValuePairs().get(i)).getAp().getName());
/* 213 */           sir.append(" = ").append(((AnnotationPropertyValuePair)ai.getPropertyValuePairs().get(i)).getValue());
/*     */         } 
/*     */       } 
/*     */     } 
/* 217 */     return new String(sir);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean restore() {
/* 223 */     if (super.restore()) {
/* 224 */       this.typesList.restore();
/* 225 */       this.globalVariablesList.restore();
/* 226 */       this.globalFunctionsList.restore();
/* 227 */       if (this.annotations != null)
/* 228 */         this.annotations.restore(); 
/* 229 */       if (this.annotationsList != null)
/* 230 */         this.annotationsList.restore(); 
/* 231 */       return true;
/*     */     } 
/* 233 */     return false;
/*     */   }
/*     */ 
/*     */   
/* 237 */   public Subsystem getSubsystem() { return this.subsystem; }
/*     */ 
/*     */ 
/*     */   
/* 241 */   public void setSubsystem(Subsystem subsystem) { this.subsystem = subsystem; }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\Package.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */