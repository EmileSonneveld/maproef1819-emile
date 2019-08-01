/*     */ package lrg.memoria.exporter.cdif;
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Iterator;
/*     */ import lrg.common.abstractions.entities.AbstractEntity;
/*     */ import lrg.memoria.core.Attribute;
/*     */ import lrg.memoria.core.Body;
/*     */ import lrg.memoria.core.Call;
/*     */ import lrg.memoria.core.Class;
/*     */ import lrg.memoria.core.FunctionBody;
/*     */ import lrg.memoria.core.GlobalFunction;
/*     */ import lrg.memoria.core.GlobalVariable;
/*     */ import lrg.memoria.core.LocalVariable;
/*     */ import lrg.memoria.core.Method;
/*     */ import lrg.memoria.core.ModelElementList;
/*     */ import lrg.memoria.core.Namespace;
/*     */ import lrg.memoria.core.Parameter;
/*     */ import lrg.memoria.core.Scope;
/*     */ import lrg.memoria.core.Variable;
/*     */ 
/*     */ public class MooseCDIFExporter extends ModelVisitor {
/*     */   private static final int PACKAGE = 0;
/*     */   private static final int NAMESPACE = 0;
/*     */   private System system;
/*     */   
/*     */   public MooseCDIFExporter(System sys) {
/*  28 */     this.system = sys;
/*  29 */     this.counter = ModelElementsRepository.getCurrentModelElementsRepository().getElementCount();
/*  30 */     this.additionalPackages = new ArrayList();
/*     */   }
/*     */   private PrintStream os; private long counter; private ArrayList additionalPackages;
/*     */   public void exportToStream(PrintStream os) {
/*  34 */     this.os = os;
/*  35 */     computeDuplication();
/*  36 */     printCDIFHeader();
/*  37 */     MEMORIABreadthIterator mEMORIABreadthIterator = new MEMORIABreadthIterator(this.system);
/*  38 */     while (mEMORIABreadthIterator.hasNext())
/*  39 */       ((ModelElement)mEMORIABreadthIterator.next()).accept(this); 
/*  40 */     printCDIFFooter();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void computeDuplication() {}
/*     */ 
/*     */   
/*     */   private void printCDIFHeader() {
/*  49 */     System.out.println("Writing Header...");
/*  50 */     this.os.println("CDIF, SYNTAX \"SYNTAX.1\" \"02.00.00\", ENCODING \"ENCODING.1\" \"01.05.04\"");
/*  51 */     this.os.println("(:HEADER");
/*  52 */     this.os.println("\t(:SUMMARY ");
/*  53 */     this.os.println("\t\t(ExporterName  \"MEMORIA\")");
/*  54 */     this.os.println("\t\t(ExporterVersion  \"1.00\")");
/*  55 */     Calendar cld = Calendar.getInstance();
/*  56 */     this.os.println("\t\t(ExporterDate  \"" + cld.get(1) + "//" + cld.get(2) + "//" + cld.get(5) + "\")");
/*  57 */     this.os.println("\t\t(ExporterTime  \"" + cld.get(10) + "//" + cld.get(12) + "//" + cld.get(13) + "\")");
/*  58 */     this.os.println("\t\t(PublisherName  \"Unknown\")");
/*  59 */     this.os.println("\t)");
/*  60 */     this.os.println(")");
/*     */     
/*  62 */     this.os.println("(:META-MODEL");
/*  63 */     this.os.println("\t(:SUBJECTAREAREFERENCE Foundation");
/*  64 */     this.os.println("\t\t(:VERSIONNUMBER \"01.00\") ");
/*  65 */     this.os.println("\t)");
/*  66 */     this.os.println("\t(:SUBJECTAREAREFERENCE MEEMORIA");
/*  67 */     this.os.println("\t\t(:VERSIONNUMBER \"1.0\")");
/*  68 */     this.os.println("\t)");
/*  69 */     this.os.println(")");
/*     */     
/*  71 */     this.os.println("(:MODEL");
/*     */   }
/*     */ 
/*     */   
/*  75 */   private void printCDIFFooter() { this.os.println(")"); }
/*     */ 
/*     */   
/*     */   private String printScope(String packageName, ModelElementList allPackages) {
/*  79 */     String containerPackageName = "";
/*  80 */     int indexOfLastScopeSeparator = packageName.lastIndexOf("::");
/*  81 */     if (indexOfLastScopeSeparator <= 0) return containerPackageName;
/*     */     
/*  83 */     containerPackageName = packageName.substring(0, indexOfLastScopeSeparator);
/*  84 */     String containedNameInModel = containerPackageName.replaceAll("::", ".");
/*     */     
/*  86 */     for (Iterator it = allPackages.iterator(); it.hasNext();) {
/*  87 */       if (((ModelElement)it.next()).getName().compareTo(containedNameInModel) == 0)
/*  88 */         return containerPackageName; 
/*     */     } 
/*  90 */     for (Iterator it = this.additionalPackages.iterator(); it.hasNext();) {
/*  91 */       if (((String)it.next()).compareTo(containerPackageName) == 0)
/*  92 */         return containerPackageName; 
/*     */     } 
/*  94 */     this.additionalPackages.add(containerPackageName);
/*  95 */     String furtherContainerName = printScope(containerPackageName, allPackages);
/*     */     
/*  97 */     String aName = allPackages.get(0).getClass().getName();
/*  98 */     aName = aName.substring(aName.lastIndexOf(".") + 1);
/*  99 */     this.os.println("(" + aName + " " + this.counter++);
/* 100 */     this.os.println("\t(name \"" + getSimplePackageName(containerPackageName) + "\")");
/* 101 */     this.os.println("\t(statute 77 )");
/* 102 */     if (furtherContainerName.length() > 0)
/* 103 */       this.os.println("\t(packagedIn \"" + furtherContainerName + "\")"); 
/* 104 */     this.os.println(")");
/* 105 */     this.os.println();
/*     */ 
/*     */     
/* 108 */     return containerPackageName;
/*     */   }
/*     */   
/*     */   private String getSimplePackageName(String fullname) {
/* 112 */     int index = fullname.lastIndexOf("::");
/* 113 */     if (index <= 0) return fullname; 
/* 114 */     return fullname.substring(index + 2);
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
/*     */ 
/*     */   
/*     */   public void visitNamespace(Namespace nspace) {
/* 133 */     String namespaceName = nspace.getName().replaceAll("\\Q.\\E", "::");
/*     */     
/* 135 */     ModelElementList allNamespaces = nspace.getSystem().getNamespaces();
/* 136 */     String containerName = printScope(namespaceName, allNamespaces);
/* 137 */     this.os.println("(Namespace " + nspace.getElementID());
/* 138 */     this.os.println("\t(name \"" + getSimplePackageName(namespaceName) + "\")");
/* 139 */     this.os.println("\t(statute " + nspace.getStatute() + " )");
/* 140 */     if (containerName.length() > 0)
/* 141 */       this.os.println("\t(belongsTo \"" + containerName + "\")"); 
/* 142 */     this.os.println(")");
/* 143 */     this.os.println();
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitClass(Class c) {
/* 148 */     Scope temp = c.getScope();
/* 149 */     for (; !(temp instanceof Namespace); temp = temp.getScope());
/* 150 */     int len = ((Namespace)temp).getFullName().length();
/* 151 */     this.os.println("(Class " + c.getElementID());
/* 152 */     this.os.println("\t(name \"" + c.getFullName().substring(len + 1) + "\")");
/* 153 */     this.os.println("\t(uniqueName \"" + convertFullClassNameToCDIF(c.getFullName()) + "\")");
/* 154 */     this.os.println("\t(belongsTo \"" + convertFullClassNameToCDIF(((Namespace)temp).getFullName()) + "\")");
/*     */     
/* 156 */     this.os.println("\t(isAbstract -" + (new Boolean(c.isAbstract())).toString().toUpperCase() + "- )");
/* 157 */     this.os.println("\t(isInterface -" + (new Boolean(c.isInterface())).toString().toUpperCase() + "- )");
/* 158 */     this.os.println("\t(statute " + c.getStatute() + " )");
/* 159 */     this.os.println("\t(file_name \"" + c.getLocation().getFile().getFullName() + "\")");
/* 160 */     this.os.println("\t(start_line " + c.getLocation().getStartLine() + " )");
/* 161 */     this.os.println("\t(end_line " + c.getLocation().getEndLine() + " )");
/* 162 */     if (c.getStatute() != 1) {
/* 163 */       this.os.println("\t(stub -TRUE- )");
/*     */     }
/* 165 */     ArrayList<String> propertComputers = c.getEntityType().nameAllPropertyComputers();
/* 166 */     for (String propertyName : propertComputers) {
/* 167 */       Object result = c.getProperty(propertyName).getValue();
/* 168 */       if (result instanceof Double) {
/* 169 */         double doubleResult = ((Double)result).doubleValue();
/* 170 */         printClassMeasurement(c, propertyName.replace('#', '_'), doubleResult);
/*     */       } 
/*     */     } 
/*     */     
/* 174 */     printClassMeasurement(c, "WLOC", compute(c, "WLOC"));
/* 175 */     printClassMeasurement(c, "WNOS", compute(c, "WNOS"));
/* 176 */     printClassMeasurement(c, "WNOCond", compute(c, "WNOCond"));
/* 177 */     printClassMeasurement(c, "WNOCmts", compute(c, "WNOCmts"));
/*     */     
/* 179 */     this.os.println(")");
/* 180 */     this.os.println();
/*     */     
/* 182 */     for (Iterator it = c.getAncestorsList().iterator(); it.hasNext(); ) {
/* 183 */       Object anObject = it.next();
/* 184 */       if (!(anObject instanceof Class))
/*     */         continue; 
/* 186 */       this.os.println("(InheritanceDefinition " + this.counter++);
/* 187 */       this.os.println("\t(subclass \"" + convertFullClassNameToCDIF(c.getFullName()) + "\")");
/* 188 */       this.os.println("\t(superclass \"" + convertFullClassNameToCDIF(((DataAbstraction)anObject).getFullName()) + "\")");
/* 189 */       this.os.println(")");
/* 190 */       this.os.println();
/*     */     } 
/*     */   }
/*     */   
/*     */   private double compute(DataAbstraction c, String metricName) {
/* 195 */     ModelElementList<Method> ml = c.getMethodList();
/* 196 */     int temp = 0;
/*     */     
/* 198 */     for (Method currentMethod : ml) {
/* 199 */       FunctionBody functionBody = currentMethod.getBody();
/* 200 */       if (functionBody != null)
/* 201 */         temp += returnMetricValue(functionBody, metricName); 
/*     */     } 
/* 203 */     return temp;
/*     */   }
/*     */   
/*     */   private int returnMetricValue(Body b, String metricName) {
/* 207 */     if (metricName.equals("WLOC"))
/* 208 */       return b.getNumberOfLines(); 
/* 209 */     if (metricName.equals("WMC"))
/* 210 */       return b.getCyclomaticNumber(); 
/* 211 */     if (metricName.equals("WNOS"))
/* 212 */       return b.getNumberOfStatements(); 
/* 213 */     if (metricName.equals("WNOCond"))
/* 214 */       return b.getNumberOfDecisions(); 
/* 215 */     if (metricName.equals("WNOCmts"))
/* 216 */       return b.getNumberOfComments(); 
/* 217 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 222 */   private void printClassMeasurement(DataAbstraction currentClass, String measurementName, double value) { this.os.println("\t(" + measurementName + " " + "\"" + value + "\")"); }
/*     */ 
/*     */ 
/*     */   
/* 226 */   private void printMeasurement(String entityCDIFName, String measurementName, double value) { this.os.println("\t(" + measurementName + " " + "\"" + value + "\")"); }
/*     */ 
/*     */   
/*     */   public void visitMethod(Method m) {
/* 230 */     this.os.println("(Method " + m.getElementID());
/* 231 */     Location loc = m.getLocation();
/* 232 */     if (loc != null) {
/* 233 */       this.os.println("\t(sourceAnchor \"" + loc.getFile().getFullName() + "\")");
/*     */     } else {
/* 235 */       this.os.println("\t(sourceAnchor \"library\")");
/* 236 */     }  this.os.println("\t(name \"" + m.getName() + "\")");
/* 237 */     String fullName = m.getFullName();
/* 238 */     this.os.println("\t(uniqueName \"" + convertFullMethodNameToCDIF(m.getFullName()) + "\")");
/* 239 */     this.os.println("\t(accessControlQualifier \"" + accessQualifierToString(m) + "\")");
/* 240 */     int index = fullName.substring(0, fullName.indexOf("(")).lastIndexOf(".");
/* 241 */     this.os.println("\t(signature \"" + fullName.substring(index + 1).replaceAll("\\Q.\\E", "::") + "\")");
/* 242 */     this.os.println("\t(belongsTo \"" + convertFullClassNameToCDIF(m.getScope().getFullName()) + "\")");
/* 243 */     this.os.println("\t(hasClassScope -" + (new Boolean(m.isStatic())).toString().toUpperCase() + "- )");
/* 244 */     this.os.println("\t(isAbstract -" + (new Boolean(m.isAbstract())).toString().toUpperCase() + "-)");
/* 245 */     this.os.println("\t(isConstructor -" + (new Boolean(m.isConstructor())).toString().toUpperCase() + "-)");
/* 246 */     this.os.println("\t(isPureAccessor -" + (new Boolean(isAccessor(m))).toString().toUpperCase() + "-)");
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
/* 260 */     if (m.getBody() != null) {
/* 261 */       printMeasurement(convertFullMethodNameToCDIF(m.getFullName()), "LOC", m.getBody().getNumberOfLines());
/* 262 */       printMeasurement(convertFullMethodNameToCDIF(m.getFullName()), "NOS", m.getBody().getNumberOfStatements());
/* 263 */       printMeasurement(convertFullMethodNameToCDIF(m.getFullName()), "NOCond", m.getBody().getNumberOfDecisions());
/* 264 */       printMeasurement(convertFullMethodNameToCDIF(m.getFullName()), "NMAA", m.getBody().getAccessList().size());
/* 265 */       printMeasurement(convertFullMethodNameToCDIF(m.getFullName()), "NI", m.getBody().getCallList().size());
/* 266 */       printMeasurement(convertFullMethodNameToCDIF(m.getFullName()), "NOCmts", m.getBody().getNumberOfComments());
/* 267 */       printMeasurement(convertFullMethodNameToCDIF(m.getFullName()), "CYCLO", m.getBody().getCyclomaticNumber());
/*     */     } 
/* 269 */     printMeasurement(convertFullMethodNameToCDIF(m.getFullName()), "PCR", ((Double)m.getProperty("PCR").getValue()).doubleValue());
/* 270 */     printMeasurement(convertFullMethodNameToCDIF(m.getFullName()), "SCR", ((Double)m.getProperty("SCR").getValue()).doubleValue());
/* 271 */     printMeasurement(convertFullMethodNameToCDIF(m.getFullName()), "_TotalUniform_", ((Double)m.getProperty("#TotalUniform#").getValue()).doubleValue());
/* 272 */     printMeasurement(convertFullMethodNameToCDIF(m.getFullName()), "_PartialUniform_", ((Double)m.getProperty("#PartialUniform#").getValue()).doubleValue());
/* 273 */     printMeasurement(convertFullMethodNameToCDIF(m.getFullName()), "_TotalNonUniform_", ((Double)m.getProperty("#TotalNonUniform#").getValue()).doubleValue());
/*     */     
/* 275 */     this.os.println(")");
/* 276 */     this.os.println();
/*     */   }
/*     */   
/*     */   public void visitAttribute(Attribute a) {
/* 280 */     this.os.println("(Attribute " + a.getElementID());
/* 281 */     this.os.println("\t(name \"" + a.getName() + "\")");
/* 282 */     this.os.println("\t(uniqueName \"" + convertFullClassNameToCDIF(a.getScope().getFullName()) + "." + a.getName() + "\")");
/* 283 */     this.os.println("\t(declaredType \"" + a.getType().getName() + "\")");
/* 284 */     this.os.println("\t(declaredClass \"" + a.getType().getName() + "\")");
/* 285 */     this.os.println("\t(belongsTo \"" + convertFullClassNameToCDIF(a.getScope().getFullName()) + "\")");
/* 286 */     this.os.println("\t(hasClassScope -" + (new Boolean(a.isStatic())).toString().toUpperCase() + "- )");
/* 287 */     this.os.println("\t(accessControlQualifier \"" + attributeAccessQualifierToString(a) + "\")");
/*     */     
/* 289 */     ArrayList<String> propertComputers = a.getEntityType().nameAllPropertyComputers();
/* 290 */     for (String propertyName : propertComputers) {
/* 291 */       Object result = a.getProperty(propertyName).getValue();
/* 292 */       if (result instanceof Double) {
/* 293 */         double doubleResult = ((Double)result).doubleValue();
/* 294 */         printMeasurement(String.valueOf(convertFullClassNameToCDIF(a.getScope().getFullName())) + "." + a.getName(), propertyName, doubleResult);
/*     */       } 
/*     */     } 
/* 297 */     this.os.println(")");
/* 298 */     this.os.println();
/*     */   }
/*     */   
/*     */   public void visitParameter(Parameter p) {
/* 302 */     this.os.println("(FormalParameter " + p.getElementID());
/* 303 */     this.os.println("\t(name \"" + p.getName() + "\")");
/* 304 */     this.os.println("\t(uniqueName \"" + convertFullMethodNameToCDIF(p.getScope().getFullName()) + "." + p.getName() + "\")");
/* 305 */     this.os.println("\t(declaredType \"" + p.getType().getName() + "\")");
/* 306 */     this.os.println("\t(declaredClass \"" + p.getType().getName() + "\")");
/* 307 */     this.os.println("\t(belongsTo \"" + convertFullMethodNameToCDIF(p.getScope().getFullName()) + "\")");
/* 308 */     this.os.println("\t(position #d" + getParameterPosition(p) + ")");
/* 309 */     this.os.println(")");
/* 310 */     this.os.println();
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitGlobalFunction(GlobalFunction f) {
/* 315 */     this.os.println("(Function " + f.getElementID());
/* 316 */     Location loc = f.getLocation();
/* 317 */     this.os.println("\t(name \"" + f.getName() + "\")");
/* 318 */     String fullName = f.getFullName();
/* 319 */     this.os.println("\t(uniqueName \"" + convertFullGlobalFunctionNameToCDIF(f.getFullName()) + "\")");
/* 320 */     int index = fullName.substring(0, fullName.indexOf("(")).lastIndexOf(".");
/* 321 */     this.os.println("\t(signature \"" + fullName.substring(index + 1).replaceAll("\\Q.\\E", "::") + "\")");
/* 322 */     if (loc != null) {
/* 323 */       this.os.println("\t(sourceAnchor \"" + loc.getFile().getFullName() + "\")");
/*     */     } else {
/* 325 */       this.os.println("\t(sourceAnchor \"library\")");
/* 326 */     }  this.os.println("\t(packagedIn \"" + convertFullClassNameToCDIF(f.getPackage().getFullName()) + "\")");
/* 327 */     this.os.println("\t(declaredType \"" + f.getReturnType().getName() + "\")");
/* 328 */     this.os.println("\t(declaredClass \"" + f.getReturnType().getName() + "\")");
/* 329 */     this.os.println("\t(accessControlQualifier \"\")");
/* 330 */     this.os.println("\t(belongsTo \"" + convertFullClassNameToCDIF(f.getScope().getFullName()) + "\")");
/*     */ 
/*     */     
/* 333 */     if (f.getBody() != null) {
/* 334 */       printMeasurement(convertFullMethodNameToCDIF(f.getFullName()), "LOC", f.getBody().getNumberOfLines());
/* 335 */       printMeasurement(convertFullMethodNameToCDIF(f.getFullName()), "CYCLO", f.getBody().getCyclomaticNumber());
/* 336 */       printMeasurement(convertFullMethodNameToCDIF(f.getFullName()), "NOS", f.getBody().getNumberOfStatements());
/* 337 */       printMeasurement(convertFullMethodNameToCDIF(f.getFullName()), "NOCond", f.getBody().getNumberOfDecisions());
/* 338 */       printMeasurement(convertFullMethodNameToCDIF(f.getFullName()), "NMAA", f.getBody().getAccessList().size());
/* 339 */       printMeasurement(convertFullMethodNameToCDIF(f.getFullName()), "NI", f.getBody().getCallList().size());
/* 340 */       printMeasurement(convertFullMethodNameToCDIF(f.getFullName()), "NOCmts", f.getBody().getNumberOfComments());
/*     */     } 
/* 342 */     printMeasurement("", "NOP", f.getParameterList().size());
/* 343 */     this.os.println(")");
/* 344 */     this.os.println();
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitLocalVar(LocalVariable l) {
/* 349 */     this.os.println("(LocalVariable " + l.getElementID());
/* 350 */     this.os.println("\t(name \"" + l.getName() + "\")");
/* 351 */     this.os.println("\t(uniqueName \"" + l.getName() + "\")");
/* 352 */     this.os.println("\t(declaredType \"" + l.getType().getName() + "\")");
/* 353 */     this.os.println("\t(declaredClass \"" + l.getType().getName() + "\")");
/* 354 */     if ((Method)l.belongsTo("method") != null) {
/* 355 */       this.os.println("\t(belongsTo \"" + convertFullMethodNameToCDIF(((Method)l.belongsTo("method")).getFullName()) + "\")");
/* 356 */       this.os.println(")");
/* 357 */       this.os.println();
/*     */     } else {
/* 359 */       AbstractEntity tmp = l.belongsTo("global function");
/* 360 */       if (tmp != null)
/* 361 */         this.os.println("\t(belongsTo \"" + convertFullGlobalFunctionNameToCDIF(((GlobalFunction)tmp).getFullName()) + "\")"); 
/* 362 */       this.os.println(")");
/* 363 */       this.os.println();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void visitGlobalVar(GlobalVariable l) {
/* 368 */     this.os.println("(GlobalVariable " + l.getElementID());
/* 369 */     this.os.println("\t(name \"" + l.getName() + "\")");
/* 370 */     this.os.println("\t(uniqueName \"" + l.getName() + "\")");
/* 371 */     this.os.println("\t(declaredType \"" + l.getType().getName() + "\")");
/* 372 */     this.os.println("\t(declaredClass \"" + l.getType().getName() + "\")");
/* 373 */     AbstractEntity tmp = l.belongsTo("namespace");
/* 374 */     if (tmp != null)
/* 375 */       this.os.println("\t(belongsTo \"" + convertFullClassNameToCDIF(((Namespace)tmp).getFullName()) + "\")"); 
/* 376 */     this.os.println(")");
/* 377 */     this.os.println();
/*     */   }
/*     */   public void visitAccess(Access a) {
/*     */     String accessedIn, accesses;
/* 381 */     this.os.println("(Access " + a.getElementID());
/* 382 */     Variable accessedVariable = a.getVariable();
/*     */ 
/*     */     
/* 385 */     if (accessedVariable instanceof GlobalVariable) {
/* 386 */       Namespace theNamespace = ((GlobalVariable)accessedVariable).getScope();
/* 387 */       accesses = String.valueOf(theNamespace.getFullName()) + "." + accessedVariable.getName();
/* 388 */     } else if (accessedVariable instanceof Attribute) {
/* 389 */       accesses = String.valueOf(convertFullClassNameToCDIF(((Attribute)accessedVariable).getScope().getFullName())) + "." + accessedVariable.getName();
/*     */     }
/* 391 */     else if (accessedVariable instanceof Parameter) {
/* 392 */       accesses = String.valueOf(convertFullMethodNameToCDIF(((Parameter)accessedVariable).getScope().getFullName())) + "." + accessedVariable.getName();
/*     */     } else {
/* 394 */       Body body = ((LocalVariable)accessedVariable).getScope();
/* 395 */       if (body instanceof FunctionBody) {
/* 396 */         accesses = String.valueOf(convertFullMethodNameToCDIF(((FunctionBody)body).getScope().getFullName())) + "." + accessedVariable.getName();
/*     */       } else {
/* 398 */         accesses = String.valueOf(convertFullClassNameToCDIF(((InitializerBody)body).getScope().getFullName())) + "." + accessedVariable.getName();
/*     */       } 
/*     */     } 
/* 401 */     this.os.println("\t(accesses \"" + accesses + "\")");
/* 402 */     Body body = a.getScope();
/* 403 */     if (body instanceof FunctionBody) {
/* 404 */       accessedIn = convertFullMethodNameToCDIF(((FunctionBody)body).getScope().getFullName());
/*     */     } else {
/* 406 */       accessedIn = convertFullClassNameToCDIF(((InitializerBody)body).getScope().getFullName());
/* 407 */     }  this.os.println("\t(accessedIn \"" + accessedIn + "\")");
/* 408 */     this.os.println("\t(receivingClass \"" + convertFullClassNameToCDIF(a.getVariable().getType().getFullName()) + "\")");
/* 409 */     this.os.println(")");
/* 410 */     this.os.println();
/*     */   }
/*     */   
/*     */   public void visitCall(Call c) {
/* 414 */     if (c.getScope() instanceof FunctionBody && c.getFunction().getScope() != null) {
/* 415 */       Function scope = ((FunctionBody)c.getScope()).getScope();
/* 416 */       this.os.println("(Invocation " + c.getElementID());
/* 417 */       this.os.println("\t(invokedBy \"" + convertFullMethodNameToCDIF(scope.getFullName()) + "\")");
/* 418 */       String methodFullName = convertFullMethodNameToCDIF(c.getFunction().getFullName());
/* 419 */       String methodName = methodFullName.substring(methodFullName.lastIndexOf(".") + 1);
/* 420 */       this.os.println("\t(invokes \"" + methodName + "\")");
/* 421 */       this.os.println("\t(candidates #[" + methodFullName + "]#)");
/* 422 */       this.os.println(")");
/* 423 */       this.os.println();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 428 */   private String convertFullClassNameToCDIF(String fullName) { return fullName.replaceAll("\\Q.\\E", "::"); }
/*     */ 
/*     */   
/*     */   private String convertFullMethodNameToCDIF(String fullName) {
/* 432 */     int index = fullName.substring(0, fullName.indexOf("(")).lastIndexOf(".");
/* 433 */     String signature = fullName.substring(index + 1).replaceAll("\\Q.\\E", "::");
/* 434 */     return String.valueOf(convertFullClassNameToCDIF(fullName.substring(0, index))) + "." + signature;
/*     */   }
/*     */   
/*     */   private String convertFullGlobalFunctionNameToCDIF(String fullName) {
/* 438 */     int index = fullName.substring(0, fullName.indexOf("(")).lastIndexOf(".");
/* 439 */     String signature = fullName.substring(index + 1).replaceAll("\\Q.\\E", "::");
/* 440 */     return String.valueOf(convertFullClassNameToCDIF(fullName.substring(0, index))) + "." + signature;
/*     */   }
/*     */   
/*     */   private int getParameterPosition(Parameter p) {
/* 444 */     Function m = p.getScope();
/* 445 */     ModelElementList modelElementList = m.getParameterList();
/* 446 */     int pos = 0;
/* 447 */     for (int i = 0; i < modelElementList.size() && 
/* 448 */       modelElementList.get(i) != p; i++) {
/* 449 */       pos++;
/*     */     }
/*     */     
/* 452 */     return pos;
/*     */   }
/*     */   
/*     */   private String accessQualifierToString(Method m) {
/* 456 */     if (m.isPublic())
/* 457 */       return "public"; 
/* 458 */     if (m.isPrivate())
/* 459 */       return "private"; 
/* 460 */     if (m.isProtected())
/* 461 */       return "protected"; 
/* 462 */     return "package";
/*     */   }
/*     */   
/*     */   private String attributeAccessQualifierToString(Attribute a) {
/* 466 */     if (a.isPublic())
/* 467 */       return "public"; 
/* 468 */     if (a.isPrivate())
/* 469 */       return "private"; 
/* 470 */     if (a.isProtected())
/* 471 */       return "protected"; 
/* 472 */     return "package";
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 476 */     if (args.length != 5) {
/* 477 */       System.err.println("Usage: MooseCDIFExporter source_path cache_path additional_library_path cidf_file_name error_file");
/* 478 */       System.exit(1);
/*     */     } 
/*     */     
/* 481 */     String source_path = args[0];
/* 482 */     String cache_path = args[1];
/* 483 */     String additional_library_path = args[2];
/* 484 */     String cdif_file_name = args[3];
/* 485 */     System.setOut(System.err);
/* 486 */     String error_file = args[4];
/* 487 */     File err = new File(error_file);
/*     */     try {
/* 489 */       err.createNewFile();
/* 490 */       Logger errorLogger = new Logger(new FileOutputStream(err));
/* 491 */       System.setOut(errorLogger);
/* 492 */       System.setErr(errorLogger);
/* 493 */       System.err.println("Building: JavaModelLoader for source_path = " + source_path);
/* 494 */       JavaModelLoader model = new JavaModelLoader(source_path, cache_path, additional_library_path, null);
/*     */       
/* 496 */       System mySystem = model.getSystem();
/* 497 */       MooseCDIFExporter exporter = new MooseCDIFExporter(mySystem);
/* 498 */       File file = new File(cdif_file_name);
/* 499 */       System.out.println("Writing the CDIF file for the path: " + source_path);
/* 500 */       exporter.exportToStream(new PrintStream(new FileOutputStream(file)));
/* 501 */       errorLogger.close();
/* 502 */     } catch (Exception pex) {
/* 503 */       System.out.println("Error !!!\nCDIF file generation aborted !!!");
/* 504 */       pex.printStackTrace();
/* 505 */       System.exit(6);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static String readLine(BufferedReader br) {
/* 510 */     String line = null;
/*     */     try {
/* 512 */       line = br.readLine();
/* 513 */       while (line != null && line.startsWith("-"))
/* 514 */         line = br.readLine(); 
/* 515 */     } catch (IOException e) {
/* 516 */       System.out.println(e);
/*     */     } 
/* 518 */     return line;
/*     */   }
/*     */   
/*     */   private boolean isAccessor(Method aMethod) {
/* 522 */     if (!aMethod.getName().startsWith("get") && !aMethod.getName().startsWith("Get") && 
/* 523 */       !aMethod.getName().startsWith("set") && !aMethod.getName().startsWith("Set")) {
/* 524 */       return false;
/*     */     }
/* 526 */     if (aMethod.getBody() == null) return false;
/*     */     
/* 528 */     return (aMethod.getBody().getCyclomaticNumber() < 2);
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\exporter\cdif\MooseCDIFExporter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */