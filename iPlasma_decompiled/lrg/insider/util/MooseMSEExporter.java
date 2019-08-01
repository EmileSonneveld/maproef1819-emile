/*     */ package classes.lrg.insider.util;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.PrintStream;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import lrg.common.abstractions.entities.AbstractEntity;
/*     */ import lrg.insider.plugins.properties.memoria.classes.ATFD;
/*     */ import lrg.insider.util.MooseMSEExporter;
/*     */ import lrg.memoria.core.Access;
/*     */ import lrg.memoria.core.Annotation;
/*     */ import lrg.memoria.core.AnnotationInstance;
/*     */ import lrg.memoria.core.AnnotationProperty;
/*     */ import lrg.memoria.core.AnnotationPropertyValuePair;
/*     */ import lrg.memoria.core.Attribute;
/*     */ import lrg.memoria.core.Body;
/*     */ import lrg.memoria.core.Call;
/*     */ import lrg.memoria.core.Class;
/*     */ import lrg.memoria.core.DataAbstraction;
/*     */ import lrg.memoria.core.Function;
/*     */ import lrg.memoria.core.FunctionBody;
/*     */ import lrg.memoria.core.GlobalFunction;
/*     */ import lrg.memoria.core.GlobalVariable;
/*     */ import lrg.memoria.core.LocalVariable;
/*     */ import lrg.memoria.core.Location;
/*     */ import lrg.memoria.core.Method;
/*     */ import lrg.memoria.core.ModelElementList;
/*     */ import lrg.memoria.core.Package;
/*     */ import lrg.memoria.core.Parameter;
/*     */ import lrg.memoria.core.System;
/*     */ import lrg.memoria.core.Type;
/*     */ import lrg.memoria.importer.recoder.JavaModelLoader;
/*     */ import lrg.memoria.utils.Logger;
/*     */ import lrg.memoria.utils.MEMORIABreadthIterator;
/*     */ 
/*     */ public class MooseMSEExporter extends ModelVisitor {
/*     */   private System system;
/*     */   private PrintStream os;
/*     */   private long counter;
/*  41 */   private static long packageCounter = 0L; private ArrayList additionalPackages; private HashSet allIDs; private HashMap<String, Long> classMap;
/*  42 */   private static long classCounter = 0L;
/*  43 */   private static long functionCounter = 0L;
/*  44 */   private static long attributeCounter = 0L;
/*  45 */   private static long accessCounter = 0L;
/*  46 */   private static long callCounter = 0L;
/*  47 */   private static int annotationCounter = 0;
/*  48 */   private static int annotationInstanceCounter = 0;
/*  49 */   private static int parameterCounter = 0;
/*  50 */   private static int localVariableCounter = 0;
/*     */ 
/*     */   
/*     */   private static void printStatistics() {
/*  54 */     System.out.println("Packages: " + packageCounter);
/*  55 */     System.out.println("Classes: " + classCounter);
/*  56 */     System.out.println("Methods: " + functionCounter);
/*  57 */     System.out.println("Attributes: " + attributeCounter);
/*  58 */     System.out.println("Parameters: " + parameterCounter);
/*  59 */     System.out.println("Local Var: " + localVariableCounter);
/*  60 */     System.out.println("Annotations: " + annotationCounter);
/*  61 */     System.out.println("Annotation References: " + annotationInstanceCounter);
/*  62 */     System.out.println("Calls: " + callCounter);
/*  63 */     System.out.println("Accesses: " + accessCounter);
/*     */   }
/*     */   
/*     */   private String twoDecimalsConvert(Double DaDoubleNumberoubleNumber)
/*     */   {
/*  68 */     DecimalFormat twoDecimals = new DecimalFormat("#0.00");
/*  69 */     return twoDecimals.format(DaDoubleNumberoubleNumber).replaceAll(",", "."); } public MooseMSEExporter(System sys) {
/*     */     this.allIDs = new HashSet();
/*     */     this.classMap = new HashMap();
/*  72 */     this.system = sys;
/*  73 */     this.counter = ModelElementsRepository.getCurrentModelElementsRepository().getElementCount();
/*  74 */     this.additionalPackages = new ArrayList();
/*     */   }
/*     */   
/*     */   public void exportToStream(PrintStream os) {
/*  78 */     this.os = os;
/*  79 */     computeDuplication();
/*  80 */     printHeader();
/*  81 */     MEMORIABreadthIterator mEMORIABreadthIterator = new MEMORIABreadthIterator(this.system);
/*  82 */     while (mEMORIABreadthIterator.hasNext())
/*  83 */       ((ModelElement)mEMORIABreadthIterator.next()).accept(this); 
/*  84 */     printFooter();
/*  85 */     printStatistics();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void computeDuplication() {}
/*     */ 
/*     */ 
/*     */   
/*     */   private void printHeader() {
/*  95 */     System.out.println("Printing header ...");
/*  96 */     this.os.println("(Moose.Model (sourceLanguage '" + this.system.programmingLanguage + "') (entity ");
/*  97 */     this.os.println("");
/*     */   }
/*     */   
/*     */   private void printFooter() {
/* 101 */     System.out.println("Printing footer ...");
/* 102 */     this.os.println("))");
/*     */   }
/*     */ 
/*     */   
/* 106 */   private void printBelongsTo(Long idref) { this.os.println("\t(belongsTo (idref: " + idref + "))"); }
/*     */ 
/*     */   
/*     */   private String printScope(String packageName, ModelElementList allPackages) {
/* 110 */     String containerPackageName = "";
/* 111 */     int indexOfLastScopeSeparator = packageName.lastIndexOf("::");
/* 112 */     if (indexOfLastScopeSeparator <= 0) return containerPackageName;
/*     */     
/* 114 */     containerPackageName = packageName.substring(0, indexOfLastScopeSeparator);
/* 115 */     String containedNameInModel = containerPackageName.replaceAll("::", ".");
/*     */     
/* 117 */     for (Iterator it = allPackages.iterator(); it.hasNext();) {
/* 118 */       if (((ModelElement)it.next()).getName().compareTo(containedNameInModel) == 0)
/* 119 */         return containerPackageName; 
/*     */     } 
/* 121 */     for (Iterator it = this.additionalPackages.iterator(); it.hasNext();) {
/* 122 */       if (((String)it.next()).compareTo(containerPackageName) == 0)
/* 123 */         return containerPackageName; 
/*     */     } 
/* 125 */     this.additionalPackages.add(containerPackageName);
/* 126 */     String furtherContainerName = printScope(containerPackageName, allPackages);
/*     */     
/* 128 */     return containerPackageName;
/*     */   }
/*     */   
/*     */   private String getSimplePackageName(String fullname) {
/* 132 */     int index = fullname.lastIndexOf("::");
/* 133 */     if (index <= 0) return fullname; 
/* 134 */     return fullname.substring(index + 2);
/*     */   }
/*     */   
/*     */   private boolean isDuplicated(Long id) {
/* 138 */     boolean isDuplicated = this.allIDs.contains(id);
/* 139 */     if (!isDuplicated) this.allIDs.add(id);
/*     */     
/* 141 */     return isDuplicated;
/*     */   }
/*     */   
/*     */   public void visitPackage(Package pack) {
/* 145 */     if (isDuplicated(pack.getElementID()))
/*     */       return; 
/* 147 */     String packageName = pack.getName().replaceAll("\\Q.\\E", "::");
/* 148 */     ModelElementList allPackages = pack.getSystem().getPackages();
/* 149 */     String containerName = printScope(packageName, allPackages);
/* 150 */     packageCounter++;
/* 151 */     this.os.println("(FAMIX.Namespace ");
/* 152 */     this.os.println("\t(id: " + pack.getElementID() + ")");
/* 153 */     this.os.println("\t(name '" + packageName + "')");
/* 154 */     this.os.println(")");
/* 155 */     this.os.println();
/*     */   }
/*     */ 
/*     */   
/*     */   private String printMetricValue(PropertyComputer pc, Class c) {
/* 160 */     if (c.getStatute() != 1) return twoDecimalsConvert(Double.valueOf(0.0D)); 
/* 161 */     return twoDecimalsConvert((Double)pc.compute(c).getValue());
/*     */   }
/*     */   
/*     */   private String printFilterValue(FilteringRule fr, Class c) {
/* 165 */     if (c.getStatute() != 1) return "false"; 
/* 166 */     return (new StringBuilder(String.valueOf(fr.applyFilter(c)))).toString();
/*     */   }
/*     */   
/*     */   private String truncateFilename(String fullFilename) {
/* 170 */     String prefix = "FILE:" + this.system.getName() + "/";
/*     */     
/* 172 */     return fullFilename.replaceFirst(prefix, "");
/*     */   }
/*     */   
/*     */   public void visitClass(Class c) {
/* 176 */     if (isDuplicated(c.getElementID())) {
/*     */       return;
/*     */     }
/* 179 */     Package tp = c.getPackage();
/* 180 */     int len = tp.getFullName().length();
/* 181 */     classCounter++;
/* 182 */     System.out.println("Famix class: " + c.getFullName());
/*     */     
/* 184 */     this.classMap.put(c.getFullName(), c.getElementID());
/* 185 */     this.os.println("(FAMIX.Class ");
/* 186 */     this.os.println("\t(id: " + c.getElementID() + ")");
/* 187 */     this.os.println("\t(name '" + c.getName() + "')");
/* 188 */     printBelongsTo(tp.getElementID());
/* 189 */     this.os.println("\t(isAbstract " + Boolean.toString(c.isAbstract()) + ")");
/* 190 */     this.os.println("\t(isInterface " + Boolean.toString(c.isInterface()) + ")");
/* 191 */     this.os.println("\t(fileName '" + truncateFilename(c.getLocation().getFile().getFullName()) + "')");
/* 192 */     this.os.println("\t(startLine " + c.getLocation().getStartLine() + " )");
/* 193 */     this.os.println("\t(endLine " + c.getLocation().getEndLine() + " )");
/* 194 */     if (c.getStatute() != 1) this.os.println("\t(stub true)");
/*     */     
/* 196 */     this.os.println("\t(GodClass " + printFilterValue(new GodClass(), c) + ")");
/* 197 */     this.os.println("\t(DataClass " + printFilterValue(new DataClass(), c) + ")");
/* 198 */     this.os.println("\t(BrainClass " + printFilterValue(new BrainClass(), c) + ")");
/*     */     
/* 200 */     this.os.println("\t(RefusedParentBequest " + printFilterValue(new RefusedParentBequest(), c) + ")");
/* 201 */     this.os.println("\t(TraditionBreaker " + printFilterValue(new TraditionBreaker(), c) + ")");
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
/* 213 */     printMeasurement("WLOC", compute(c, "WLOC"));
/* 214 */     printMeasurement("WNOS", compute(c, "WNOS"));
/* 215 */     printMeasurement("WNOCond", compute(c, "WNOCond"));
/* 216 */     printMeasurement("WNOCmts", compute(c, "WNOCmts"));
/*     */     
/* 218 */     printMeasurement("WOC", printMetricValue(new WOC(), c));
/* 219 */     printMeasurement("ATFD", printMetricValue(new ATFD(), c));
/* 220 */     printMeasurement("WMC", printMetricValue(new WMC(), c));
/* 221 */     printMeasurement("TCC", printMetricValue(new TCC(), c));
/* 222 */     printMeasurement("CRIX", printMetricValue(new CRIX(), c));
/* 223 */     printMeasurement("NOAM", printMetricValue(new NOAM(), c));
/* 224 */     printMeasurement("NOPA", printMetricValue(new NOPA(), c));
/*     */     
/* 226 */     printMeasurement("BUR", printMetricValue(new BUR(), c));
/* 227 */     printMeasurement("BOvR", printMetricValue(new BOvM(), c));
/* 228 */     printMeasurement("AMW", printMetricValue(new AMW(), c));
/* 229 */     printMeasurement("NOM", printMetricValue(new NOM(), c));
/*     */     
/* 231 */     printMeasurement("NAS", printMetricValue(new NAS(), c));
/* 232 */     printMeasurement("PNAS", printMetricValue(new PNAS(), c));
/*     */     
/* 234 */     printMeasurement("LOC", printMetricValue(new LOC(), c));
/* 235 */     printMeasurement("NProtM", printMetricValue(new NProtM(), c));
/* 236 */     this.os.println(")");
/* 237 */     this.os.println();
/*     */     
/* 239 */     for (DataAbstraction superclass : c.getAncestorsList()) {
/* 240 */       if (!(superclass instanceof Class))
/*     */         continue; 
/* 242 */       this.os.println("(FAMIX.InheritanceDefinition ");
/* 243 */       this.os.println("\t(id: " + this.counter++ + ")");
/* 244 */       this.os.println("\t(subclass (idref: " + c.getElementID() + "))");
/*     */ 
/*     */       
/* 247 */       this.os.println("\t(superclass (idref: " + findCorrectSuperClassID(superclass) + "))");
/* 248 */       this.os.println(")");
/* 249 */       this.os.println();
/* 250 */       visitClass((Class)superclass);
/*     */     } 
/*     */   }
/*     */   
/*     */   private Long findCorrectSuperClassID(DataAbstraction theSuperClass) {
/* 255 */     Long classID = (Long)this.classMap.get(theSuperClass.getFullName());
/* 256 */     if (classID != null) {
/* 257 */       theSuperClass.setElementID(classID);
/* 258 */       return classID;
/*     */     } 
/*     */     
/* 261 */     return theSuperClass.getElementID();
/*     */   }
/*     */   
/*     */   private double compute(DataAbstraction c, String metricName) {
/* 265 */     ModelElementList<Method> ml = c.getMethodList();
/* 266 */     int temp = 0;
/*     */     
/* 268 */     for (Method currentMethod : ml) {
/* 269 */       FunctionBody functionBody = currentMethod.getBody();
/* 270 */       if (functionBody != null)
/* 271 */         temp += returnMetricValue(functionBody, metricName); 
/*     */     } 
/* 273 */     return temp;
/*     */   }
/*     */   
/*     */   private int returnMetricValue(Body b, String metricName) {
/* 277 */     if (metricName.equals("WLOC"))
/* 278 */       return b.getNumberOfLines(); 
/* 279 */     if (metricName.equals("WMC"))
/* 280 */       return b.getCyclomaticNumber(); 
/* 281 */     if (metricName.equals("WNOS"))
/* 282 */       return b.getNumberOfStatements(); 
/* 283 */     if (metricName.equals("WNOCond"))
/* 284 */       return b.getNumberOfDecisions(); 
/* 285 */     if (metricName.equals("WNOCmts"))
/* 286 */       return b.getNumberOfComments(); 
/* 287 */     return 0;
/*     */   }
/*     */ 
/*     */   
/* 291 */   private void printMeasurement(String measurementName, double value) { this.os.println("\t(" + measurementName + " " + value + ")"); }
/*     */ 
/*     */   
/*     */   private void printMeasurement(String measurementName, String value) {
/* 295 */     if (measurementName.compareTo("(default value)") == 0) measurementName = "defaultValue"; 
/* 296 */     this.os.println("\t(" + measurementName + " " + value + ")");
/*     */   }
/*     */   
/*     */   public void visitMethod(Method m) {
/* 300 */     if (isDuplicated(m.getElementID()))
/* 301 */       return;  if (!(m.getScope() instanceof Class)) {
/*     */       return;
/*     */     }
/*     */     
/* 305 */     functionCounter++;
/* 306 */     System.out.println("Famix method: " + m.getFullName());
/*     */     
/* 308 */     this.os.println("(FAMIX.Method");
/* 309 */     this.os.println("\t(id: " + m.getElementID() + ")");
/*     */     
/* 311 */     Location loc = m.getLocation();
/* 312 */     if (loc != null) { this.os.println("\t(fileName '" + truncateFilename(loc.getFile().getFullName()) + "')"); }
/* 313 */     else { this.os.println("\t(fileName library)"); }
/* 314 */      this.os.println("\t(startLine " + m.getLocation().getStartLine() + " )");
/* 315 */     this.os.println("\t(endLine " + m.getLocation().getEndLine() + " )");
/* 316 */     this.os.println("\t(name '" + m.getName() + "')");
/* 317 */     String fullName = m.getFullName();
/* 318 */     this.os.println("\t(accessControlQualifier " + accessQualifierToString(m) + ")");
/* 319 */     int index = fullName.substring(0, fullName.indexOf("(")).lastIndexOf(".");
/* 320 */     this.os.println("\t(signature '" + fullName.substring(index + 1).replaceAll("\\Q.\\E", "::") + "')");
/* 321 */     printBelongsTo(((DataAbstraction)m.getScope()).getElementID());
/* 322 */     this.os.println("\t(hasClassScope " + Boolean.valueOf(m.isStatic()).toString() + ")");
/* 323 */     this.os.println("\t(isAbstract " + Boolean.valueOf(m.isAbstract()).toString() + ")");
/* 324 */     this.os.println("\t(isConstructor " + Boolean.valueOf(m.isConstructor()).toString() + ")");
/* 325 */     this.os.println("\t(isPureAccessor " + Boolean.toString(isAccessor(m)) + ")");
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
/* 339 */     if (m.getBody() != null) {
/* 340 */       this.os.println("\t(FeatureEnvy " + (new FeatureEnvy()).applyFilter(m) + ")");
/* 341 */       this.os.println("\t(BrainMethod " + (new BrainMethod()).applyFilter(m) + ")");
/* 342 */       this.os.println("\t(IntensiveCoupling " + (new IntensiveCoupling()).applyFilter(m) + ")");
/* 343 */       this.os.println("\t(DispersedCoupling " + (new ExtensiveCoupling()).applyFilter(m) + ")");
/* 344 */       this.os.println("\t(ShotgunSurgery " + (new ShotgunSurgery()).applyFilter(m) + ")");
/*     */ 
/*     */       
/* 347 */       printMeasurement("NOS", m.getBody().getNumberOfStatements());
/* 348 */       printMeasurement("NOCond", m.getBody().getNumberOfDecisions());
/* 349 */       printMeasurement("NMAA", m.getBody().getAccessList().size());
/* 350 */       printMeasurement("NI", m.getBody().getCallList().size());
/* 351 */       printMeasurement("NOCmts", m.getBody().getNumberOfComments());
/* 352 */       printMeasurement("CYCLO", m.getBody().getCyclomaticNumber());
/*     */       
/* 354 */       printMeasurement("CINT", twoDecimalsConvert((Double)(new CINT()).compute(m).getValue()));
/* 355 */       printMeasurement("CDISP", twoDecimalsConvert((Double)(new CDISP()).compute(m).getValue()));
/* 356 */       printMeasurement("CM", twoDecimalsConvert((Double)(new CM()).compute(m).getValue()));
/* 357 */       printMeasurement("CC", twoDecimalsConvert((Double)(new CC()).compute(m).getValue()));
/* 358 */       printMeasurement("ATFD", twoDecimalsConvert((Double)(new ATFD()).compute(m).getValue()));
/* 359 */       printMeasurement("LAA", twoDecimalsConvert((Double)(new LAA()).compute(m).getValue()));
/* 360 */       printMeasurement("FDP", twoDecimalsConvert((Double)(new FDP()).compute(m).getValue()));
/* 361 */       printMeasurement("LOC", twoDecimalsConvert((Double)(new LOC()).compute(m).getValue()));
/* 362 */       printMeasurement("MAXNESTING", twoDecimalsConvert((Double)(new MAXNESTING()).compute(m).getValue()));
/* 363 */       printMeasurement("NOAV", twoDecimalsConvert((Double)(new NOAV()).compute(m).getValue()));
/*     */     } 
/*     */ 
/*     */     
/* 367 */     this.os.println(")");
/* 368 */     this.os.println();
/*     */   }
/*     */   
/*     */   public void visitAttribute(Attribute a) {
/* 372 */     if (isDuplicated(a.getElementID()))
/*     */       return; 
/* 374 */     attributeCounter++;
/* 375 */     this.os.println("(FAMIX.Attribute");
/* 376 */     this.os.println("\t(id: " + a.getElementID() + ")");
/* 377 */     this.os.println("\t(name '" + a.getName() + "')");
/* 378 */     printBelongsTo(a.getScope().getElementID());
/*     */ 
/*     */     
/* 381 */     this.os.println("\t(hasClassScope " + Boolean.valueOf(a.isStatic()).toString() + ")");
/* 382 */     this.os.println("\t(accessControlQualifier " + attributeAccessQualifierToString(a) + ")");
/*     */     
/* 384 */     ArrayList<String> propertComputers = a.getEntityType().nameAllPropertyComputers();
/* 385 */     for (String propertyName : propertComputers) {
/* 386 */       Object result = a.getProperty(propertyName).getValue();
/* 387 */       if (result instanceof Double) {
/* 388 */         double doubleResult = ((Double)result).doubleValue();
/* 389 */         printMeasurement(propertyName, doubleResult);
/*     */       } 
/*     */     } 
/* 392 */     this.os.println(")");
/* 393 */     this.os.println();
/*     */   }
/*     */   
/*     */   public void visitParameter(Parameter p) {
/* 397 */     if (isDuplicated(p.getElementID()))
/*     */       return; 
/* 399 */     parameterCounter++;
/* 400 */     this.os.println("(FAMIX.FormalParameter");
/* 401 */     this.os.println("\t(id: " + p.getElementID() + ")");
/* 402 */     this.os.println("\t(name " + p.getName() + ")");
/* 403 */     printBelongsTo(p.getScope().getElementID());
/*     */ 
/*     */     
/* 406 */     this.os.println("\t(position " + getParameterPosition(p) + ")");
/* 407 */     this.os.println(")");
/* 408 */     this.os.println();
/*     */   }
/*     */   
/*     */   public void visitGlobalFunction(GlobalFunction f) {
/* 412 */     if (isDuplicated(f.getElementID())) {
/*     */       return;
/*     */     }
/* 415 */     this.os.println("(FAMIX.Function");
/* 416 */     this.os.println("\t(id: " + f.getElementID() + ")");
/* 417 */     Location loc = f.getLocation();
/* 418 */     this.os.println("\t(name '" + f.getName() + "')");
/* 419 */     String fullName = f.getFullName();
/* 420 */     int index = fullName.substring(0, fullName.indexOf("(")).lastIndexOf(".");
/* 421 */     this.os.println("\t(signature '" + fullName.substring(index + 1).replaceAll("\\Q.\\E", "::") + "')");
/* 422 */     if (loc != null) {
/* 423 */       this.os.println("\t(sourceAnchor '" + loc.getFile().getFullName() + "')");
/*     */     } else {
/* 425 */       this.os.println("\t(sourceAnchor 'library')");
/* 426 */     }  printBelongsTo(((Namespace)f.getScope()).getElementID());
/*     */ 
/*     */     
/* 429 */     this.os.println("\t(accessControlQualifier public)");
/*     */     
/* 431 */     if (f.getBody() != null) {
/* 432 */       printMeasurement("LOC", f.getBody().getNumberOfLines());
/* 433 */       printMeasurement("CYCLO", f.getBody().getCyclomaticNumber());
/* 434 */       printMeasurement("NOS", f.getBody().getNumberOfStatements());
/* 435 */       printMeasurement("NOCond", f.getBody().getNumberOfDecisions());
/* 436 */       printMeasurement("NMAA", f.getBody().getAccessList().size());
/* 437 */       printMeasurement("NI", f.getBody().getCallList().size());
/* 438 */       printMeasurement("NOCmts", f.getBody().getNumberOfComments());
/*     */     } 
/* 440 */     printMeasurement("NOP", f.getParameterList().size());
/* 441 */     this.os.println(")");
/* 442 */     this.os.println();
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitLocalVar(LocalVariable l) {
/* 447 */     if (isDuplicated(l.getElementID()))
/*     */       return; 
/* 449 */     localVariableCounter++;
/* 450 */     this.os.println("(FAMIX.LocalVariable");
/* 451 */     this.os.println("\t(id: " + l.getElementID() + ")");
/* 452 */     this.os.println("\t(name '" + l.getName() + "')");
/*     */ 
/*     */     
/* 455 */     if ((Method)l.belongsTo("method") != null) {
/* 456 */       printBelongsTo(((Method)l.belongsTo("method")).getElementID());
/*     */     } else {
/* 458 */       AbstractEntity tmp = l.belongsTo("global function");
/* 459 */       if (tmp != null)
/* 460 */         printBelongsTo(((GlobalFunction)tmp).getElementID()); 
/*     */     } 
/* 462 */     this.os.println(")");
/* 463 */     this.os.println();
/*     */   }
/*     */   
/*     */   public void visitGlobalVar(GlobalVariable l) {
/* 467 */     if (isDuplicated(l.getElementID()))
/*     */       return; 
/* 469 */     this.os.println("(FAMIX.GlobalVariable");
/* 470 */     this.os.println("\t(id: " + l.getElementID() + ")");
/* 471 */     this.os.println("\t(name " + l.getName() + ")");
/*     */ 
/*     */     
/* 474 */     AbstractEntity tmp = l.belongsTo("namespace");
/* 475 */     if (tmp != null)
/* 476 */       printBelongsTo(((Namespace)tmp).getElementID()); 
/* 477 */     this.os.println(")");
/* 478 */     this.os.println();
/*     */   }
/*     */   public void visitAccess(Access a) {
/*     */     Long accessedIn;
/* 482 */     if (isDuplicated(a.getElementID()))
/*     */       return; 
/* 484 */     accessCounter++;
/* 485 */     this.os.println("(FAMIX.Access");
/* 486 */     this.os.println("\t(id: " + a.getElementID() + ")");
/* 487 */     this.os.println("\t(accesses (idref: " + a.getVariable().getElementID() + "))");
/* 488 */     Body body = a.getScope();
/*     */     
/* 490 */     if (body instanceof FunctionBody) {
/* 491 */       accessedIn = ((FunctionBody)body).getScope().getElementID();
/*     */     } else {
/* 493 */       accessedIn = ((InitializerBody)body).getScope().getElementID();
/* 494 */     }  this.os.println("\t(accessedIn (idref: " + accessedIn + "))");
/* 495 */     this.os.println(")");
/* 496 */     this.os.println();
/*     */   }
/*     */   
/*     */   public void visitCall(Call c) {
/* 500 */     if (isDuplicated(c.getElementID()))
/*     */       return; 
/* 502 */     if (c.getScope() instanceof FunctionBody && c.getFunction().getScope() != null) {
/* 503 */       Function scope = ((FunctionBody)c.getScope()).getScope();
/* 504 */       callCounter++;
/* 505 */       this.os.println("(FAMIX.Invocation");
/* 506 */       this.os.println("\t(id: " + c.getElementID() + ")");
/* 507 */       this.os.println("\t(invokedBy (idref: " + scope.getElementID() + "))");
/* 508 */       this.os.println("\t(candidate (idref: " + c.getFunction().getElementID() + "))");
/* 509 */       String methodFullName = convertFullMethodNameToCDIF(c.getFunction().getFullName());
/* 510 */       String methodName = methodFullName.substring(methodFullName.lastIndexOf(".") + 1);
/* 511 */       this.os.println("\t(invokes '" + methodName + "')");
/* 512 */       this.os.println(")");
/* 513 */       this.os.println();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitAnnotation(Annotation annotation) {
/* 519 */     if (isDuplicated(annotation.getElementID()))
/*     */       return; 
/* 521 */     annotationCounter++;
/* 522 */     this.os.println("(FAMIX.Annotation");
/* 523 */     this.os.println("\t(id: " + annotation.getElementID() + ")");
/* 524 */     this.os.println("\t(name '" + annotation.getName() + "')");
/* 525 */     printBelongsTo(annotation.getPackage().getElementID());
/* 526 */     ModelElementList<AnnotationProperty> listOfProperties = annotation.getAnnotationProperties();
/*     */     
/* 528 */     for (AnnotationProperty aProperty : listOfProperties) {
/* 529 */       Type propertyType = aProperty.getType();
/* 530 */       if (propertyType instanceof ExplicitlyDefinedType) {
/* 531 */         this.os.println("\t(" + aProperty.getName() + " (idref: " + ((ExplicitlyDefinedType)propertyType).getElementID() + "))"); continue;
/*     */       } 
/* 533 */       this.os.println("\t(" + aProperty.getName() + " " + propertyType.getFullName() + ")");
/*     */     } 
/*     */ 
/*     */     
/* 537 */     this.os.println(")");
/* 538 */     this.os.println();
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitAnnotationInstance(AnnotationInstance annotInstance) {
/* 543 */     if (isDuplicated(annotInstance.getElementID()))
/*     */       return; 
/* 545 */     annotationInstanceCounter++;
/* 546 */     this.os.println("(FAMIX.AnnotationInstance");
/* 547 */     this.os.println("\t(id: " + annotInstance.getElementID() + ")");
/* 548 */     this.os.println("\t(name '" + annotInstance.getName() + "')");
/* 549 */     printBelongsTo(annotInstance.getAnnotatedElement().getElementID());
/* 550 */     ModelElementList<AnnotationPropertyValuePair> listOfProperties = annotInstance.getPropertyValuePairs();
/*     */     
/* 552 */     for (AnnotationPropertyValuePair aPropertyPair : listOfProperties) {
/* 553 */       printMeasurement(aPropertyPair.getAp().getName(), aPropertyPair.getValue().replaceAll("\"", "'"));
/*     */     }
/* 555 */     this.os.println(")");
/* 556 */     this.os.println();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 562 */   private String convertFullClassNameToCDIF(String fullName) { return fullName.replaceAll("\\Q.\\E", "::"); }
/*     */ 
/*     */   
/*     */   private String convertFullMethodNameToCDIF(String fullName) {
/* 566 */     int index = fullName.substring(0, fullName.indexOf("(")).lastIndexOf(".");
/* 567 */     String signature = fullName.substring(index + 1).replaceAll("\\Q.\\E", "::");
/* 568 */     return String.valueOf(convertFullClassNameToCDIF(fullName.substring(0, index))) + "." + signature;
/*     */   }
/*     */   
/*     */   private int getParameterPosition(Parameter p) {
/* 572 */     Function m = p.getScope();
/* 573 */     ModelElementList modelElementList = m.getParameterList();
/* 574 */     int pos = 0;
/* 575 */     for (int i = 0; i < modelElementList.size() && 
/* 576 */       modelElementList.get(i) != p; i++) {
/* 577 */       pos++;
/*     */     }
/*     */     
/* 580 */     return pos;
/*     */   }
/*     */   
/*     */   private String accessQualifierToString(Method m) {
/* 584 */     if (m.isPublic())
/* 585 */       return "public"; 
/* 586 */     if (m.isPrivate())
/* 587 */       return "private"; 
/* 588 */     if (m.isProtected())
/* 589 */       return "protected"; 
/* 590 */     return "package";
/*     */   }
/*     */   
/*     */   private String attributeAccessQualifierToString(Attribute a) {
/* 594 */     if (a.isPublic())
/* 595 */       return "public"; 
/* 596 */     if (a.isPrivate())
/* 597 */       return "private"; 
/* 598 */     if (a.isProtected())
/* 599 */       return "protected"; 
/* 600 */     return "package";
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 604 */     if (args.length != 3) {
/* 605 */       System.err.println("Usage: MooseMSEExporter source_path cache_path mse_file_name");
/* 606 */       System.err.println(args.length);
/* 607 */       System.exit(1);
/*     */     } 
/*     */     
/* 610 */     String source_path = args[0];
/* 611 */     String cache_path = args[1];
/* 612 */     String additional_library_path = "";
/* 613 */     String cdif_file_name = args[2];
/* 614 */     System.setOut(System.err);
/* 615 */     String error_file = "errorfile";
/* 616 */     File err = new File(error_file);
/*     */     try {
/* 618 */       err.createNewFile();
/* 619 */       Logger errorLogger = new Logger(new FileOutputStream(err));
/* 620 */       System.setOut(errorLogger);
/* 621 */       System.setErr(errorLogger);
/* 622 */       System.err.println("Building: JavaModelLoader for source_path = " + source_path);
/* 623 */       JavaModelLoader model = new JavaModelLoader(source_path, cache_path, additional_library_path, null);
/*     */       
/* 625 */       System mySystem = model.getSystem();
/* 626 */       MooseMSEExporter exporter = new MooseMSEExporter(mySystem);
/* 627 */       File file = new File(cdif_file_name);
/* 628 */       System.out.println("Writing the AL file for the path: " + source_path);
/* 629 */       exporter.exportToStream(new PrintStream(new FileOutputStream(file)));
/* 630 */       errorLogger.close();
/* 631 */     } catch (Exception pex) {
/* 632 */       System.out.println("Error !!!\nAL file generation aborted !!!");
/* 633 */       pex.printStackTrace();
/* 634 */       System.exit(6);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static String readLine(BufferedReader br) {
/* 639 */     String line = null;
/*     */     try {
/* 641 */       line = br.readLine();
/* 642 */       while (line != null && line.startsWith("-"))
/* 643 */         line = br.readLine(); 
/* 644 */     } catch (IOException e) {
/* 645 */       System.out.println(e);
/*     */     } 
/* 647 */     return line;
/*     */   }
/*     */   
/*     */   private boolean isAccessor(Method aMethod) {
/* 651 */     if (!aMethod.getName().startsWith("get") && !aMethod.getName().startsWith("Get") && 
/* 652 */       !aMethod.getName().startsWith("set") && !aMethod.getName().startsWith("Set")) {
/* 653 */       return false;
/*     */     }
/* 655 */     if (aMethod.getBody() == null) return false;
/*     */     
/* 657 */     return (aMethod.getBody().getCyclomaticNumber() < 2);
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\inside\\util\MooseMSEExporter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */