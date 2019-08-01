/*     */ package lrg.memoria.exporter.cdif;
/*     */ import java.io.File;
/*     */ import java.util.Calendar;
/*     */ import java.util.Iterator;
/*     */ import lrg.memoria.core.Access;
/*     */ import lrg.memoria.core.ArrayDecorator;
/*     */ import lrg.memoria.core.Attribute;
/*     */ import lrg.memoria.core.Body;
/*     */ import lrg.memoria.core.Class;
/*     */ import lrg.memoria.core.FunctionBody;
/*     */ import lrg.memoria.core.InitializerBody;
/*     */ import lrg.memoria.core.LocalVariable;
/*     */ import lrg.memoria.core.Location;
/*     */ import lrg.memoria.core.Method;
/*     */ import lrg.memoria.core.Parameter;
/*     */ import lrg.memoria.core.Primitive;
/*     */ import lrg.memoria.core.System;
/*     */ import lrg.memoria.core.Variable;
/*     */ import lrg.memoria.utils.ConfigFileReader;
/*     */ import lrg.memoria.utils.Logger;
/*     */ 
/*     */ public class MemoriaCDIFExporter extends ModelVisitor {
/*     */   public static final String nullElement = "_NULL_";
/*     */   private System system;
/*     */   
/*     */   public MemoriaCDIFExporter(System sys) {
/*  27 */     this.system = sys;
/*  28 */     this.counter = ModelElementsRepository.getCurrentModelElementsRepository().getElementCount();
/*     */   }
/*     */   private PrintStream os; private long counter;
/*     */   public void exportToStream(PrintStream os) {
/*  32 */     this.os = os;
/*  33 */     printCDIFHeader();
/*  34 */     MEMORIABreadthIterator mEMORIABreadthIterator = new MEMORIABreadthIterator(this.system);
/*  35 */     while (mEMORIABreadthIterator.hasNext())
/*  36 */       ((ModelElement)mEMORIABreadthIterator.next()).accept(this); 
/*  37 */     printCDIFFooter();
/*     */   }
/*     */   
/*     */   private void printCDIFHeader() {
/*  41 */     this.os.println("CDIF, SYNTAX \"SYNTAX.1\" \"02.00.00\", ENCODING \"ENCODING.1\" \"01.05.04\"");
/*  42 */     this.os.println("(:HEADER");
/*  43 */     this.os.println("\t(:SUMMARY ");
/*  44 */     this.os.println("\t\t(ExporterName  \"MEMORIA\")");
/*  45 */     this.os.println("\t\t(ExporterVersion  \"1.00\")");
/*  46 */     Calendar cld = Calendar.getInstance();
/*  47 */     this.os.println("\t\t(ExporterDate  \"" + cld.get(1) + "//" + cld.get(2) + "//" + cld.get(5) + "\")");
/*  48 */     this.os.println("\t\t(ExporterTime  \"" + cld.get(10) + "//" + cld.get(12) + "//" + cld.get(13) + "\")");
/*  49 */     this.os.println("\t\t(PublisherName  \"Unknown\")");
/*  50 */     this.os.println("\t)");
/*  51 */     this.os.println(")");
/*     */     
/*  53 */     this.os.println("(:META-MODEL");
/*  54 */     this.os.println("\t(:SUBJECTAREAREFERENCE Foundation");
/*  55 */     this.os.println("\t\t(:VERSIONNUMBER \"01.00\") ");
/*  56 */     this.os.println("\t)");
/*  57 */     this.os.println("\t(:SUBJECTAREAREFERENCE MEEMORIA");
/*  58 */     this.os.println("\t\t(:VERSIONNUMBER \"1.0\")");
/*  59 */     this.os.println("\t)");
/*  60 */     this.os.println(")");
/*     */     
/*  62 */     this.os.println("(:MODEL");
/*     */   }
/*     */ 
/*     */   
/*  66 */   private void printCDIFFooter() { this.os.println(")"); }
/*     */ 
/*     */   
/*     */   public void visitPackage(Package p) {
/*  70 */     this.os.println("(Package " + p.getElementID());
/*  71 */     this.os.println("\t(getName \"" + p.getName() + "\")");
/*  72 */     this.os.println("\t(statute " + p.getStatute() + " )");
/*  73 */     this.os.println(")");
/*  74 */     this.os.println();
/*     */   }
/*     */   
/*     */   public void visitNamespace(Namespace n) {
/*  78 */     this.os.println("(Namespace " + n.getElementID());
/*  79 */     this.os.println("\t(getName \"" + n.getName() + "\")");
/*  80 */     this.os.println("\t(statute " + n.getStatute() + " )");
/*  81 */     this.os.println(")");
/*  82 */     this.os.println();
/*     */   }
/*     */   
/*     */   public void visitClass(Class c) {
/*  86 */     this.os.println("(Class " + c.getElementID());
/*  87 */     this.os.println("\t(getName \"" + c.getName() + "\")");
/*  88 */     this.os.println("\t(uniqueName \"" + c.getFullName() + "\")");
/*  89 */     this.os.println("\t(belongsToPackage \"" + c.getPackage().getFullName() + "\")");
/*  90 */     Scope temp = c.getScope();
/*  91 */     while (!(temp instanceof Namespace))
/*  92 */       temp = temp.getScope(); 
/*  93 */     this.os.println("\t(belongsToNamespace \"" + ((Namespace)temp).getFullName() + "\")");
/*  94 */     if (c.getScope() instanceof DataAbstraction)
/*  95 */       this.os.println("\t(belongsToClass \"" + c.getScope().getFullName() + "\")"); 
/*  96 */     if (c.getScope() instanceof Body)
/*  97 */       this.os.println("\t(belongsToBody \"" + ((Body)c.getScope()).getElementID() + "\")"); 
/*  98 */     this.os.println("\t(file_name \"" + c.getLocation().getFile().getFullName() + "\")");
/*  99 */     this.os.println("\t(start_line " + c.getLocation().getStartLine() + " )");
/* 100 */     this.os.println("\t(start_char " + c.getLocation().getStartChar() + " )");
/* 101 */     this.os.println("\t(end_line " + c.getLocation().getEndLine() + " )");
/* 102 */     this.os.println("\t(end_char " + c.getLocation().getEndChar() + " )");
/* 103 */     this.os.println("\t(isAbstract -" + (new Boolean(c.isAbstract())).toString().toUpperCase() + "- )");
/* 104 */     this.os.println("\t(isFinal -" + (new Boolean(c.isFinal())).toString().toUpperCase() + "- )");
/* 105 */     this.os.println("\t(isStatic -" + (new Boolean(c.isStatic())).toString().toUpperCase() + "- )");
/* 106 */     this.os.println("\t(isInterface -" + (new Boolean(c.isInterface())).toString().toUpperCase() + "- )");
/* 107 */     this.os.println("\t(statute " + c.getStatute() + " )");
/* 108 */     this.os.println("\t(access_mode " + c.getAccessMode() + " )");
/* 109 */     this.os.println(")");
/* 110 */     this.os.println();
/*     */     
/* 112 */     this.os.println("(InheritanceDefinition " + this.counter++);
/* 113 */     this.os.println("\t(subclass \"" + c.getFullName() + "\")");
/* 114 */     if (c.getFirstAncestor() != null) {
/* 115 */       this.os.println("\t(superclass \"" + c.getFirstAncestor().getFullName() + "\")");
/*     */     } else {
/* 117 */       this.os.println("\t(superclass \"_NULL_\")");
/* 118 */     }  this.os.println(")");
/* 119 */     this.os.println();
/*     */     
/* 121 */     for (Iterator it = c.getInterfaces().iterator(); it.hasNext(); ) {
/* 122 */       this.os.println("(ImplementsDefinition " + this.counter++);
/* 123 */       this.os.println("\t(subclass \"" + c.getFullName() + "\")");
/* 124 */       this.os.println("\t(interface \"" + ((DataAbstraction)it.next()).getFullName() + "\")");
/* 125 */       this.os.println(")");
/* 126 */       this.os.println();
/*     */     } 
/* 128 */     this.os.println();
/*     */   }
/*     */   
/*     */   public void visitArrayDecorator(ArrayDecorator ad) {
/* 132 */     this.os.println("(ArrayDecorator " + ad.getElementID());
/* 133 */     this.os.println("\t(getName \"" + ad.getName() + "\")");
/* 134 */     this.os.println("\t(uniqueName \"" + ad.getFullName() + "\")");
/* 135 */     this.os.println("\t(decoratedType \"" + ad.getDecoratedType().getFullName() + "\")");
/* 136 */     this.os.println(")");
/* 137 */     this.os.println();
/*     */   }
/*     */   
/*     */   public void visitPrimitive(Primitive p) {
/* 141 */     this.os.println("(PrimitiveType " + p.getElementID());
/* 142 */     this.os.println("\t(getName \"" + p.getName() + "\")");
/* 143 */     this.os.println("\t(uniqueName \"" + p.getFullName() + "\")");
/* 144 */     this.os.println("\t(belongsToNamespace \"" + p.getScope().getFullName() + "\")");
/* 145 */     this.os.println(")");
/* 146 */     this.os.println();
/*     */   }
/*     */   
/*     */   public void visitMethod(Method m) {
/* 150 */     this.os.println("(Method " + m.getElementID());
/* 151 */     this.os.println("\t(getName \"" + m.getName() + "\")");
/* 152 */     String fullName = m.getFullName();
/* 153 */     this.os.println("\t(uniqueName \"" + fullName + "\")");
/*     */ 
/*     */     
/* 156 */     Location loc = m.getLocation();
/* 157 */     printLocation(loc);
/* 158 */     this.os.println("\t(belongsTo \"" + m.getScope().getFullName() + "\")");
/* 159 */     if (m.getReturnType() != null) {
/* 160 */       this.os.println("\t(returnType \"" + m.getReturnType().getFullName() + "\")");
/*     */     } else {
/* 162 */       this.os.println("\t(returnType \"_NULL_\")");
/* 163 */     }  this.os.println("\t(access_mode " + m.getAccessMode() + " )");
/* 164 */     this.os.println("\t(isAbstract -" + (new Boolean(m.isAbstract())).toString().toUpperCase() + "- )");
/* 165 */     this.os.println("\t(isFinal -" + (new Boolean(m.isFinal())).toString().toUpperCase() + "- )");
/* 166 */     this.os.println("\t(isStatic -" + (new Boolean(m.isStatic())).toString().toUpperCase() + "- )");
/* 167 */     this.os.println("\t(statute " + m.getStatute() + " )");
/* 168 */     this.os.println("\t(kindOf " + m.getKindOf() + " )");
/* 169 */     this.os.println(")");
/* 170 */     this.os.println();
/*     */     
/* 172 */     for (Iterator it = m.getExceptionList().iterator(); it.hasNext(); ) {
/* 173 */       this.os.println("(ThrowsException " + this.counter++);
/* 174 */       this.os.println("\t(method \"" + m.getFullName() + "\")");
/* 175 */       this.os.println("\t(exception_name \"" + ((DataAbstraction)it.next()).getFullName() + "\")");
/* 176 */       this.os.println(")");
/* 177 */       this.os.println();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void printLocation(Location loc) {
/* 182 */     if (loc != null) {
/* 183 */       this.os.println("\t(file_name \"" + loc.getFile().getFullName() + "\")");
/* 184 */       this.os.println("\t(start_line " + loc.getStartLine() + " )");
/* 185 */       this.os.println("\t(start_char " + loc.getStartChar() + " )");
/* 186 */       this.os.println("\t(end_line " + loc.getEndLine() + " )");
/* 187 */       this.os.println("\t(end_char " + loc.getEndChar() + " )");
/*     */     } else {
/* 189 */       this.os.println("\t(file_name \"library\")");
/* 190 */       this.os.println("\t(start_line -1 )");
/* 191 */       this.os.println("\t(start_char -1 )");
/* 192 */       this.os.println("\t(end_line -1 )");
/* 193 */       this.os.println("\t(end_char -1 )");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void visitFunctionBody(FunctionBody mb) {
/* 198 */     this.os.println("(FunctionBody " + mb.getElementID());
/* 199 */     this.os.println("\t(belongsTo \"" + mb.getScope().getFullName() + "\")");
/* 200 */     this.os.println("\t(LOC " + mb.getNumberOfLines() + ")");
/* 201 */     this.os.println("\t(CYCLO " + mb.getCyclomaticNumber() + ")");
/* 202 */     this.os.println("\t(NOS " + mb.getNumberOfStatements() + ")");
/* 203 */     this.os.println("\t(NODec " + mb.getNumberOfDecisions() + ")");
/* 204 */     this.os.println("\t(NOCmt " + mb.getNumberOfComments() + ")");
/* 205 */     this.os.println("\t(NOExc " + mb.getNumberOfExceptions() + ")");
/* 206 */     this.os.println("\t(NOExits " + mb.getNumberOfExits() + ")");
/* 207 */     this.os.println("\t(NOL " + mb.getNumberOfLoops() + ")");
/* 208 */     this.os.println(")");
/* 209 */     this.os.println();
/*     */   }
/*     */   
/*     */   public void visitInitializerBody(InitializerBody ib) {
/* 213 */     this.os.println("(InitializerBody " + ib.getElementID());
/* 214 */     this.os.println("\t(belongsTo \"" + ib.getScope().getFullName() + "\")");
/* 215 */     this.os.println("\t(LOC " + ib.getNumberOfLines() + ")");
/* 216 */     this.os.println("\t(CYCLO" + ib.getCyclomaticNumber() + ")");
/* 217 */     this.os.println("\t(NOS" + ib.getNumberOfStatements() + ")");
/* 218 */     this.os.println("\t(NODec" + ib.getNumberOfDecisions() + ")");
/* 219 */     this.os.println("\t(NOCmt" + ib.getNumberOfComments() + ")");
/* 220 */     this.os.println("\t(NOExc" + ib.getNumberOfExceptions() + ")");
/* 221 */     this.os.println("\t(NOExits" + ib.getNumberOfExits() + ")");
/* 222 */     this.os.println("\t(NOL" + ib.getNumberOfLoops() + ")");
/* 223 */     this.os.println(")");
/* 224 */     this.os.println();
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitAttribute(Attribute a) {
/* 229 */     this.os.println("(Attribute " + a.getElementID());
/* 230 */     printVariableCharacteristics(a);
/* 231 */     this.os.println("\t(belongsTo \"" + a.getScope().getFullName() + "\")");
/* 232 */     this.os.println("\t(access_mode " + a.getAccessMode() + " )");
/* 233 */     this.os.println(")");
/* 234 */     this.os.println();
/*     */   }
/*     */   
/*     */   public void visitParameter(Parameter p) {
/* 238 */     this.os.println("(FormalParameter " + p.getElementID());
/* 239 */     printVariableCharacteristics(p);
/* 240 */     this.os.println("\t(belongsTo \"" + p.getScope().getFullName() + "\")");
/* 241 */     this.os.println(")");
/* 242 */     this.os.println();
/*     */   }
/*     */   
/*     */   public void visitLocalVar(LocalVariable lv) {
/* 246 */     this.os.println("(LocalVariable " + lv.getElementID());
/* 247 */     printVariableCharacteristics(lv);
/* 248 */     this.os.println("\t(belongsTo \"" + lv.getScope().getElementID() + "\")");
/* 249 */     this.os.println("\t(isBlock \"" + (new Boolean(lv.isBlock())).toString().toUpperCase() + "\")");
/* 250 */     this.os.println(")");
/* 251 */     this.os.println();
/*     */   }
/*     */   
/*     */   private void printVariableCharacteristics(Variable var) {
/* 255 */     this.os.println("\t(getName \"" + var.getName() + "\")");
/* 256 */     printVariableFullName(var);
/* 257 */     this.os.println("\t(type \"" + var.getType().getFullName() + "\")");
/* 258 */     this.os.println("\t(isFinal -" + (new Boolean(var.isFinal())).toString().toUpperCase() + "- )");
/*     */     
/* 260 */     this.os.println("\t(statute " + var.getStatute() + " )");
/* 261 */     Location loc = var.getLocation();
/* 262 */     printLocation(loc);
/*     */   }
/*     */   
/*     */   private void printVariableFullName(Variable var) {
/* 266 */     if (var instanceof Parameter)
/* 267 */       this.os.println("\t(uniqueName \"" + ((Parameter)var).getScope().getFullName() + "." + var.getName() + "\")"); 
/* 268 */     if (var instanceof LocalVariable) {
/* 269 */       Body scope = ((LocalVariable)var).getScope();
/* 270 */       if (scope instanceof FunctionBody) {
/* 271 */         this.os.println("\t(uniqueName \"" + ((FunctionBody)scope).getScope().getFullName() + "." + var.getName() + var.getLocation().getStartLine() + "\")");
/*     */       } else {
/* 273 */         this.os.println("\t(uniqueName \"" + ((InitializerBody)scope).getScope().getFullName() + "." + var.getName() + var.getLocation().getStartLine() + "\")");
/*     */       } 
/* 275 */     }  if (var instanceof Attribute)
/* 276 */       this.os.println("\t(uniqueName \"" + ((Attribute)var).getScope().getFullName() + "." + var.getName() + "\")"); 
/*     */   }
/*     */   
/*     */   public void visitAccess(Access a) {
/* 280 */     this.os.println("(Access " + a.getElementID());
/* 281 */     Variable accessedVariable = a.getVariable();
/* 282 */     printVariableFullName(accessedVariable);
/* 283 */     Body body = a.getScope();
/* 284 */     this.os.println("\t(accessedIn " + body.getElementID() + ")");
/* 285 */     this.os.println("\t(accesses_number " + a.getCount() + ")");
/* 286 */     this.os.println(")");
/* 287 */     this.os.println();
/*     */   }
/*     */   
/*     */   public void visitCall(Call c) {
/* 291 */     this.os.println("(Call " + c.getElementID());
/* 292 */     this.os.println("\t(uniqueName \"" + c.getFunction().getFullName() + "\")");
/* 293 */     this.os.println("\t(invokedIn " + c.getScope().getElementID() + ")");
/* 294 */     this.os.println("\t(invocations_number " + c.getCount() + ")");
/* 295 */     this.os.println(")");
/* 296 */     this.os.println();
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 300 */     if (args.length != 1) {
/* 301 */       System.out.println("Usage: java MemoriaCDIFExporter config_file");
/* 302 */       System.exit(1);
/*     */     } 
/*     */     try {
/* 305 */       ConfigFileReader cfr = new ConfigFileReader(args[0]);
/*     */       
/* 307 */       String sources = cfr.readLine();
/* 308 */       while (sources != null) {
/* 309 */         String cache = cfr.readLine();
/* 310 */         String libraries = cfr.readLine();
/* 311 */         String cdif_file = cfr.readLine();
/* 312 */         String error_file = cfr.readLine();
/* 313 */         File err = new File(error_file);
/* 314 */         err.createNewFile();
/* 315 */         Logger errorLogger = new Logger(new FileOutputStream(err));
/* 316 */         System.setOut(errorLogger);
/* 317 */         System.setErr(errorLogger);
/* 318 */         makeOneCDIF(sources, cache, libraries, cdif_file);
/* 319 */         errorLogger.close();
/* 320 */         sources = cfr.readLine();
/*     */       } 
/* 322 */       cfr.close();
/* 323 */     } catch (Exception e) {
/* 324 */       e.printStackTrace();
/* 325 */       System.out.println(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void makeOneCDIF(String source_path, String cache_path, String additional_library_path, String cdif_file_name) {
/*     */     try {
/* 331 */       JavaModelLoader model = new JavaModelLoader(source_path, cache_path, additional_library_path, null);
/*     */       
/* 333 */       System mySystem = model.getSystem();
/* 334 */       MemoriaCDIFExporter exporter = new MemoriaCDIFExporter(mySystem);
/*     */       try {
/* 336 */         File file = new File(cdif_file_name);
/* 337 */         System.out.println("Writing the CDIF file for the path: " + source_path);
/* 338 */         exporter.exportToStream(new PrintStream(new FileOutputStream(file)));
/* 339 */       } catch (Exception e) {
/* 340 */         e.printStackTrace();
/* 341 */         System.out.println(e);
/*     */       }
/*     */     
/* 344 */     } catch (Exception pex) {
/* 345 */       System.out.println("Error while parsing the sources !!!\nTable generation aborted !!!");
/* 346 */       System.exit(6);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\exporter\cdif\MemoriaCDIFExporter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */