/*     */ package lrg.memoria.importer.mcc.loader;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import lrg.memoria.core.Attribute;
/*     */ import lrg.memoria.core.Class;
/*     */ import lrg.memoria.core.File;
/*     */ import lrg.memoria.core.Function;
/*     */ import lrg.memoria.core.FunctionBody;
/*     */ import lrg.memoria.core.GlobalVariable;
/*     */ import lrg.memoria.core.LocalVariable;
/*     */ import lrg.memoria.core.Location;
/*     */ import lrg.memoria.core.Namespace;
/*     */ import lrg.memoria.core.Package;
/*     */ import lrg.memoria.core.Parameter;
/*     */ import lrg.memoria.core.UnnamedNamespace;
/*     */ import lrg.memoria.core.Variable;
/*     */ 
/*     */ public class DefaultVariableVisitor extends DefaultVisitorRoot implements VariableVisitor {
/*     */   private Integer varId;
/*     */   private Location location;
/*     */   private String varName;
/*     */   private int accessMode;
/*     */   private boolean isStatic;
/*     */   private boolean isConst;
/*  26 */   private HashMap<Integer, GlobalVariable> refersToMap = new HashMap(); private Type type; private String kindOf; private DataAbstraction typeScope; private Package packageScope; private Namespace namespaceScope; private Integer bodyScopeId; private Integer refersToVariable;
/*     */   private File currentFile;
/*     */   
/*     */   public void setId(Integer id) {
/*  30 */     this.varId = id;
/*  31 */     this.accessMode = 0;
/*     */   }
/*     */   
/*     */   public void setFileName(String fileFullName) {
/*  35 */     if (!fileFullName.equals("NULL")) {
/*  36 */       this.currentFile = Loader.getInstance().getFileByName(fileFullName);
/*  37 */       this.location = new Location(this.currentFile);
/*     */     } else {
/*  39 */       this.location = new Location(File.getUnknownFile());
/*     */     } 
/*     */   }
/*     */   
/*  43 */   public void setDeclarationLine(Integer declarationLine) { this.location.setStartLine(declarationLine.intValue()); }
/*     */ 
/*     */   
/*     */   public void setVariableName(String variableName) {
/*  47 */     if (variableName.indexOf("NO_NAME") >= 0) {
/*  48 */       this.varName = "";
/*     */     } else {
/*  50 */       this.varName = variableName;
/*     */     } 
/*     */   }
/*     */   
/*  54 */   public void setKindOf(String kindOf) { this.kindOf = kindOf; }
/*     */ 
/*     */   
/*     */   public void setTypeId(String tId) {
/*  58 */     if (tId.indexOf("<ERROR>") >= 0 || tId.indexOf("<NO_ONE>") >= 0) {
/*  59 */       this.type = Class.getUnknownClass();
/*     */     } else {
/*  61 */       int temp = Integer.parseInt(tId);
/*  62 */       this.type = Loader.getInstance().getType(new Integer(temp));
/*  63 */       if (this.type == null) {
/*  64 */         this.type = Class.getUnknownClass();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setAccess(String access) {
/*  70 */     if (access.indexOf("public") >= 0)
/*  71 */       this.accessMode = 1; 
/*  72 */     if (access.indexOf("private") >= 0)
/*  73 */       this.accessMode = 4; 
/*  74 */     if (access.indexOf("protected") >= 0)
/*  75 */       this.accessMode = 2; 
/*     */   }
/*     */   
/*     */   public void setClassId(String cId) {
/*  79 */     if (cId.indexOf("<ERROR>") >= 0 || cId.indexOf("NULL") >= 0) {
/*  80 */       this.typeScope = Class.getUnknownClass();
/*     */     } else {
/*  82 */       int temp = Integer.parseInt(cId);
/*  83 */       this.typeScope = (DataAbstraction)Loader.getInstance().getType(new Integer(temp));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setBodyId(String bId) {
/*  88 */     if (bId.indexOf("<ERROR>") >= 0 || bId.indexOf("NULL") >= 0) {
/*  89 */       this.bodyScopeId = new Integer(-1);
/*     */     } else {
/*  91 */       this.bodyScopeId = new Integer(bId);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setPackageId(String pId) {
/*  96 */     if (pId.indexOf("NULL") < 0) {
/*  97 */       int temp = Integer.parseInt(pId);
/*  98 */       this.packageScope = Loader.getInstance().getPackage(new Integer(temp));
/*     */     } 
/* 100 */     if (this.packageScope == null)
/* 101 */       this.packageScope = Package.getUnknownPackage(); 
/*     */   }
/*     */   
/*     */   public void setNamespaceId(String namespaceId) {
/* 105 */     if (namespaceId.indexOf("<NO_ONE>") >= 0) {
/* 106 */       this.namespaceScope = Namespace.getAnonymousNamespace();
/*     */       return;
/*     */     } 
/* 109 */     if (namespaceId.indexOf("<ERROR>") < 0 && namespaceId.indexOf("NULL") < 0) {
/* 110 */       int temp = Integer.parseInt(namespaceId);
/* 111 */       this.namespaceScope = Loader.getInstance().getNamespace(new Integer(temp));
/*     */     } 
/* 113 */     if (this.namespaceScope == null)
/* 114 */       this.namespaceScope = Namespace.getUnknownNamespace(); 
/*     */   }
/*     */   
/*     */   public void setIsStatic(Integer isSt) {
/* 118 */     if (isSt.intValue() == 1) {
/* 119 */       this.isStatic = true;
/*     */     } else {
/* 121 */       this.isStatic = false;
/*     */     } 
/*     */   }
/*     */   public void setIsConst(Integer isCt) {
/* 125 */     if (isCt.intValue() == 1) {
/* 126 */       this.isConst = true;
/*     */     } else {
/* 128 */       this.isConst = false;
/*     */     } 
/*     */   }
/*     */   public void setRefersTo(String refersTo) {
/* 132 */     if (refersTo.indexOf("<ERROR>") >= 0 || refersTo.indexOf("NULL") >= 0 || 
/* 133 */       refersTo.indexOf("<NOT_INIT>") >= 0 || refersTo.indexOf("<NO_ONE>") >= 0) {
/* 134 */       this.refersToVariable = new Integer(0);
/*     */     } else {
/* 136 */       this.refersToVariable = new Integer(refersTo);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void addVariable() {
/* 142 */     Variable var = null;
/* 143 */     if (this.kindOf.equals("global"))
/* 144 */       var = addGlobalVariable(); 
/* 145 */     if (this.kindOf.equals("attribute"))
/* 146 */       var = addAttribute(); 
/* 147 */     if (this.kindOf.equals("parameter"))
/* 148 */       Parameter parameter = addParameter(); 
/* 149 */     if (this.kindOf.equals("local"))
/* 150 */       var = addLocalVariable(); 
/* 151 */     var.setLocation(this.location);
/* 152 */     var.setType(this.type);
/* 153 */     if (this.isConst)
/* 154 */       var.setFinal(); 
/* 155 */     if (this.refersToVariable.intValue() > 0) {
/* 156 */       assert var instanceof GlobalVariable : "ERROR: The variable that refers an extern variable should be global !";
/* 157 */       if (var instanceof GlobalVariable)
/* 158 */         this.refersToMap.put(this.refersToVariable, (GlobalVariable)var); 
/*     */     } 
/* 160 */     var.setStatute(1);
/* 161 */     Loader.getInstance().addVariable(this.varId, var);
/*     */   }
/*     */   
/*     */   public void variablesEOF() {
/* 165 */     Set<Map.Entry<Integer, GlobalVariable>> keys = this.refersToMap.entrySet();
/*     */ 
/*     */     
/* 168 */     for (Map.Entry<Integer, GlobalVariable> key : keys) {
/* 169 */       Integer refersToID = (Integer)key.getKey();
/* 170 */       GlobalVariable var = (GlobalVariable)key.getValue();
/* 171 */       GlobalVariable refersTo = (GlobalVariable)Loader.getInstance().getVariable(refersToID);
/* 172 */       var.setRefersTo(refersTo);
/*     */     } 
/*     */   }
/*     */   
/*     */   private Variable addGlobalVariable() {
/* 177 */     GlobalVariable gv = new GlobalVariable(this.varName);
/* 178 */     if (this.isStatic) {
/* 179 */       UnnamedNamespace unsp = Loader.getInstance().getUnnamedNamespace(this.currentFile.getFullName());
/* 180 */       gv.setScope(unsp);
/* 181 */       unsp.addGlobalVariable(gv);
/*     */     } else {
/* 183 */       gv.setScope(this.namespaceScope);
/* 184 */       this.namespaceScope.addGlobalVariable(gv);
/*     */     } 
/* 186 */     gv.setPackage(this.packageScope);
/* 187 */     this.packageScope.addGlobalVariable(gv);
/* 188 */     if (this.isStatic)
/* 189 */       gv.setStatic(); 
/* 190 */     return gv;
/*     */   }
/*     */   
/*     */   private Variable addLocalVariable() {
/* 194 */     LocalVariable lv = new LocalVariable(this.varName);
/* 195 */     FunctionBody fb = Loader.getInstance().getBody(this.bodyScopeId);
/* 196 */     if (lv == null || fb == null) return lv;
/*     */     
/* 198 */     fb.addLocalVar(lv);
/* 199 */     lv.setScope(fb.getCodeStripe());
/* 200 */     if (this.isStatic)
/* 201 */       lv.setStatic(); 
/* 202 */     return lv;
/*     */   }
/*     */   
/*     */   private Variable addAttribute() {
/* 206 */     Attribute attr = new Attribute(this.varName);
/* 207 */     attr.setScope(this.typeScope);
/* 208 */     this.typeScope.addAttribute(attr);
/* 209 */     assert this.accessMode != 0 : "Error: could not identify the access mode for attribute *" + attr.getFullName() + "*";
/* 210 */     attr.setAccessMode(this.accessMode);
/* 211 */     if (this.isStatic)
/* 212 */       attr.setStatic(); 
/* 213 */     return attr;
/*     */   }
/*     */   
/*     */   private Parameter addParameter() {
/* 217 */     Parameter par = new Parameter(this.varName);
/* 218 */     FunctionBody fb = Loader.getInstance().getBody(this.bodyScopeId);
/* 219 */     if (fb == null) return par; 
/* 220 */     Function func = fb.getScope();
/* 221 */     if (func == null) return par; 
/* 222 */     func.addParameter(par);
/* 223 */     par.setScope(func);
/* 224 */     return par;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\mcc\loader\DefaultVariableVisitor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */