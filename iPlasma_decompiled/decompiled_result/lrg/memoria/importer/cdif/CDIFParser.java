/*      */ package lrg.memoria.importer.cdif;public class CDIFParser implements CDIFParserConstants { private FileInputStream cdifFile;
/*      */   private String cdifFileName;
/*      */   private static Class currentClass;
/*      */   private static Class classScope;
/*      */   private static Package packageScope;
/*      */   private static Namespace namespaceScope;
/*      */   private static Method currentMethod;
/*      */   private static Method methodScope;
/*      */   private static Body bodyScope;
/*      */   private static Type currentMethodReturnType;
/*      */   private static Type dataType;
/*      */   private static String currentEntityName;
/*      */   private static String currentEntityUniqueName;
/*      */   private static String currentEntityFileName;
/*      */   private static String subClassUniqueName;
/*      */   private static String superClassUniqueName;
/*      */   private static Location currentEntityLocation;
/*      */   private static boolean currentEntityIsAbstract;
/*      */   private static boolean currentEntityIsFinal;
/*      */   private static boolean currentClassIsInterface;
/*      */   private static boolean currentEntityIsStatic;
/*      */   private static boolean currentLocalVarIsBlock;
/*      */   private static boolean currentParameterIsExParam;
/*      */   private static int currentEntityStatute;
/*      */   private static int currentEntityAccessMode;
/*      */   private static int currentEntityStartLine;
/*      */   private static int currentEntityStartChar;
/*      */   private static int currentEntityEndLine;
/*      */   private static int currentEntityEndChar;
/*      */   private static int currentNumber;
/*      */   private static int currentMethodKindOf;
/*      */   private static int currentParameterPosition;
/*      */   private static int currentLOC;
/*      */   private static int currentCYCLO;
/*      */   private static int currentNOS;
/*      */   private static int currentNODec;
/*      */   private static int currentNOCmt;
/*      */   private static int currentNOExc;
/*      */   private static int currentNOExits;
/*      */   private static int currentNOL;
/*   41 */   private static int lazyBodyID = -1;
/*      */   private static System currentSystem;
/*      */   private static int currentEntityID;
/*   44 */   private static Hashtable variablesMap = new Hashtable();
/*   45 */   private static Hashtable methodsMap = new Hashtable();
/*   46 */   private static Hashtable classesMap = new Hashtable();
/*   47 */   private static Hashtable packagesMap = new Hashtable();
/*   48 */   private static Hashtable namespacesMap = new Hashtable();
/*   49 */   private static Hashtable bodiesMap = new Hashtable();
/*   50 */   private static Hashtable lazyBodyScopesForClasses = new Hashtable();
/*      */   
/*      */   public CDIFParser(String fileName) {
/*   53 */     this.cdifFileName = fileName;
/*   54 */     currentSystem = new System(fileName);
/*      */     try {
/*   56 */       this.cdifFile = new FileInputStream(new File(this.cdifFileName));
/*   57 */     } catch (FileNotFoundException e) {
/*   58 */       System.out.println("Error: file " + this.cdifFileName + " was not found !!");
/*   59 */       System.exit(2);
/*      */     } 
/*      */ 
/*      */     
/*   63 */     if (jj_initialized_once) {
/*   64 */       System.out.println("ERROR: Second call to constructor of static parser.  You must");
/*   65 */       System.out.println("       either use ReInit() or set the JavaCC option STATIC to false");
/*   66 */       System.out.println("       during parser generation.");
/*   67 */       throw new Error();
/*      */     } 
/*   69 */     jj_initialized_once = true;
/*   70 */     jj_input_stream = new SimpleCharStream(this.cdifFile, 1, 1);
/*   71 */     token_source = new CDIFParserTokenManager(jj_input_stream);
/*   72 */     token = new Token();
/*   73 */     jj_ntk = -1;
/*   74 */     jj_gen = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   79 */     for (int i = 0; i < 0; ) { jj_la1[i] = -1; i++; }
/*   80 */      for (int i = 0; i < jj_2_rtns.length; ) { jj_2_rtns[i] = new JJCalls(); i++; }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public System parse() throws ParseException {
/*   88 */     namespacesMap.put("_ANONYMOUS_NAMESPACE_", Namespace.getAnonymousNamespace());
/*   89 */     System.out.print("Parsing file " + this.cdifFileName);
/*   90 */     parseCDIFFile();
/*   91 */     createLazyLinks();
/*   92 */     return currentSystem;
/*      */   }
/*      */   
/*      */   private static void createLazyLinks() {
/*   96 */     for (en = lazyBodyScopesForClasses.keys(); en.hasMoreElements(); ) {
/*   97 */       Long bid = (Long)en.nextElement();
/*   98 */       DataAbstraction cls = (DataAbstraction)lazyBodyScopesForClasses.get(bid);
/*   99 */       Body body = (Body)bodiesMap.get(bid);
/*  100 */       cls.setScope(body);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static Type addType(String uniqueName) {
/*  105 */     Class clazz = (Type)classesMap.get(uniqueName);
/*  106 */     if (clazz == null) {
/*  107 */       String fullScopableName = uniqueName.substring(uniqueName.lastIndexOf(".") + 1);
/*  108 */       if (fullScopableName.indexOf("$") >= 0) {
/*  109 */         clazz = new Class(fullScopableName.substring(fullScopableName.lastIndexOf("$") + 1));
/*      */       } else {
/*  111 */         clazz = new Class(fullScopableName);
/*  112 */       }  classesMap.put(uniqueName, clazz);
/*      */     } 
/*  114 */     return clazz;
/*      */   }
/*      */   
/*      */   private static Type addArrayDecorator(String uniqueName, Type decorated) {
/*  118 */     ArrayDecorator arrayDecorator = (Type)classesMap.get(uniqueName);
/*  119 */     if (arrayDecorator == null) {
/*  120 */       arrayDecorator = new ArrayDecorator(decorated);
/*  121 */       classesMap.put(uniqueName, arrayDecorator);
/*      */     } 
/*  123 */     return arrayDecorator;
/*      */   }
/*      */   
/*      */   private static Primitive addPrimitiveType(String uniqueName) {
/*  127 */     Primitive tempType = (Primitive)classesMap.get(uniqueName);
/*  128 */     if (tempType == null) {
/*  129 */       tempType = new Primitive(uniqueName.substring(uniqueName.lastIndexOf(".") + 1));
/*  130 */       classesMap.put(uniqueName, tempType);
/*      */     } 
/*  132 */     return tempType;
/*      */   }
/*      */   
/*      */   private static Method addMethod(String uniqueMethodName) {
/*  136 */     Method tempMethod = (Method)methodsMap.get(uniqueMethodName);
/*  137 */     if (tempMethod == null) {
/*  138 */       String temp = uniqueMethodName.substring(0, uniqueMethodName.indexOf("("));
/*  139 */       tempMethod = new Method(temp.substring(temp.lastIndexOf(".") + 1));
/*  140 */       methodsMap.put(uniqueMethodName, tempMethod);
/*      */     } 
/*  142 */     return tempMethod;
/*      */   }
/*      */   
/*      */   public static final void parseCDIFFile() {
/*  146 */     parseHeader();
/*  147 */     jj_consume_token(10);
/*      */ 
/*      */     
/*  150 */     while (jj_2_1(4)) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  155 */       if (jj_2_2(4)) {
/*  156 */         parsePackage(); continue;
/*  157 */       }  if (jj_2_3(4)) {
/*  158 */         parseNamespace(); continue;
/*  159 */       }  if (jj_2_4(4)) {
/*  160 */         parsePrimitiveType(); continue;
/*  161 */       }  if (jj_2_5(4)) {
/*  162 */         parseClass(); continue;
/*  163 */       }  if (jj_2_6(4)) {
/*  164 */         parseArrayDecorator(); continue;
/*  165 */       }  if (jj_2_7(4)) {
/*  166 */         parseInitializerBody(); continue;
/*  167 */       }  if (jj_2_8(4)) {
/*  168 */         parseInheritanceDefinition(); continue;
/*  169 */       }  if (jj_2_9(4)) {
/*  170 */         parseImplementsDefinition(); continue;
/*  171 */       }  if (jj_2_10(4)) {
/*  172 */         parseAttribute(); continue;
/*  173 */       }  if (jj_2_11(4)) {
/*  174 */         parseMethod(); continue;
/*  175 */       }  if (jj_2_12(4)) {
/*  176 */         parseThrowsException(); continue;
/*  177 */       }  if (jj_2_13(4)) {
/*  178 */         parseMethodBody(); continue;
/*  179 */       }  if (jj_2_14(4)) {
/*  180 */         parseFormalParameter(); continue;
/*  181 */       }  if (jj_2_15(4)) {
/*  182 */         parseLocalVariable(); continue;
/*  183 */       }  if (jj_2_16(4)) {
/*  184 */         parseAccess(); continue;
/*  185 */       }  if (jj_2_17(4)) {
/*  186 */         parseInvocation(); continue;
/*      */       } 
/*  188 */       jj_consume_token(-1);
/*  189 */       throw new ParseException();
/*      */     } 
/*      */     
/*  192 */     jj_consume_token(11);
/*  193 */     jj_consume_token(0);
/*      */   }
/*      */   
/*      */   public static final void parseHeader() {
/*  197 */     jj_consume_token(6);
/*  198 */     jj_consume_token(6);
/*  199 */     jj_consume_token(6);
/*  200 */     jj_consume_token(12);
/*  201 */     jj_consume_token(13);
/*  202 */     jj_consume_token(6);
/*  203 */     jj_consume_token(6);
/*  204 */     jj_consume_token(14);
/*  205 */     jj_consume_token(15);
/*  206 */     jj_consume_token(16);
/*  207 */     jj_consume_token(17);
/*  208 */     jj_consume_token(6);
/*  209 */     jj_consume_token(11);
/*  210 */     jj_consume_token(18);
/*  211 */     jj_consume_token(19);
/*  212 */     jj_consume_token(11);
/*  213 */     jj_consume_token(20);
/*  214 */     jj_consume_token(9);
/*  215 */     jj_consume_token(11);
/*  216 */     jj_consume_token(21);
/*  217 */     jj_consume_token(9);
/*  218 */     jj_consume_token(11);
/*  219 */     jj_consume_token(22);
/*  220 */     jj_consume_token(6);
/*  221 */     jj_consume_token(11);
/*  222 */     jj_consume_token(11);
/*  223 */     jj_consume_token(11);
/*  224 */     jj_consume_token(23);
/*  225 */     jj_consume_token(24);
/*  226 */     jj_consume_token(6);
/*  227 */     jj_consume_token(25);
/*  228 */     jj_consume_token(26);
/*  229 */     jj_consume_token(11);
/*  230 */     jj_consume_token(11);
/*  231 */     jj_consume_token(24);
/*  232 */     jj_consume_token(6);
/*  233 */     jj_consume_token(25);
/*  234 */     jj_consume_token(27);
/*  235 */     jj_consume_token(11);
/*  236 */     jj_consume_token(11);
/*  237 */     jj_consume_token(11);
/*      */   }
/*      */   
/*      */   public static final void parsePackage() {
/*  241 */     jj_consume_token(28);
/*  242 */     jj_consume_token(5);
/*  243 */     setID();
/*  244 */     jj_consume_token(29);
/*  245 */     jj_consume_token(6);
/*  246 */     setName();
/*  247 */     jj_consume_token(11);
/*  248 */     jj_consume_token(30);
/*  249 */     jj_consume_token(5);
/*  250 */     setStatute();
/*  251 */     jj_consume_token(11);
/*  252 */     jj_consume_token(11);
/*  253 */     packageAdd();
/*      */   }
/*      */   
/*      */   static void setID() {
/*  257 */     currentEntityID = Integer.parseInt((getToken(0)).image);
/*  258 */     classScope = null;
/*  259 */     bodyScope = null;
/*  260 */     packageScope = null;
/*  261 */     namespaceScope = null;
/*      */   }
/*      */ 
/*      */   
/*  265 */   static void setName() { currentEntityName = (getToken(0)).image; }
/*      */ 
/*      */ 
/*      */   
/*  269 */   static void setStatute() { currentEntityStatute = Integer.parseInt((getToken(0)).image); }
/*      */ 
/*      */   
/*      */   static void packageAdd() {
/*  273 */     pack = new Package(currentEntityName);
/*  274 */     pack.setElementID(new Long(currentEntityID));
/*  275 */     pack.setStatute(currentEntityStatute);
/*  276 */     currentSystem.addPackage(pack);
/*  277 */     packagesMap.put(currentEntityName, pack);
/*      */   }
/*      */   
/*      */   public static final void parseNamespace() {
/*  281 */     jj_consume_token(31);
/*  282 */     jj_consume_token(5);
/*  283 */     setID();
/*  284 */     jj_consume_token(29);
/*  285 */     jj_consume_token(6);
/*  286 */     setName();
/*  287 */     jj_consume_token(11);
/*  288 */     jj_consume_token(30);
/*  289 */     jj_consume_token(5);
/*  290 */     setStatute();
/*  291 */     jj_consume_token(11);
/*  292 */     jj_consume_token(11);
/*  293 */     namespaceAdd();
/*      */   }
/*      */   
/*      */   static void namespaceAdd() {
/*  297 */     nsp = new Namespace(currentEntityName);
/*  298 */     nsp.setElementID(new Long(currentEntityID));
/*  299 */     nsp.setStatute(currentEntityStatute);
/*  300 */     currentSystem.addNamespace(nsp);
/*  301 */     namespacesMap.put(currentEntityName, nsp);
/*      */   }
/*      */   
/*      */   public static final void parsePrimitiveType() {
/*  305 */     jj_consume_token(32);
/*  306 */     jj_consume_token(5);
/*  307 */     setID();
/*  308 */     jj_consume_token(29);
/*  309 */     jj_consume_token(6);
/*  310 */     setName();
/*  311 */     jj_consume_token(11);
/*  312 */     jj_consume_token(33);
/*  313 */     jj_consume_token(6);
/*  314 */     setUniqueName();
/*  315 */     jj_consume_token(11);
/*  316 */     jj_consume_token(34);
/*  317 */     jj_consume_token(6);
/*  318 */     classBelongsToNamespace();
/*  319 */     jj_consume_token(11);
/*  320 */     jj_consume_token(11);
/*  321 */     primitiveAdd();
/*      */   }
/*      */   
/*      */   static void primitiveAdd() {
/*  325 */     pr = addPrimitiveType(currentEntityUniqueName);
/*  326 */     pr.setElementID(new Long(currentEntityID));
/*  327 */     Namespace.getGlobalNamespace().addType(pr);
/*      */   }
/*      */   
/*      */   public static final void parseClass() {
/*  331 */     jj_consume_token(35);
/*  332 */     jj_consume_token(5);
/*  333 */     setID();
/*  334 */     jj_consume_token(29);
/*  335 */     if (jj_2_18(4)) {
/*  336 */       jj_consume_token(8);
/*  337 */     } else if (jj_2_19(4)) {
/*  338 */       jj_consume_token(6);
/*  339 */     } else if (jj_2_20(4)) {
/*  340 */       jj_consume_token(5);
/*      */     } else {
/*  342 */       jj_consume_token(-1);
/*  343 */       throw new ParseException();
/*      */     } 
/*  345 */     setName();
/*  346 */     jj_consume_token(11);
/*  347 */     jj_consume_token(33);
/*  348 */     jj_consume_token(6);
/*  349 */     setUniqueName();
/*  350 */     jj_consume_token(11);
/*  351 */     jj_consume_token(36);
/*  352 */     jj_consume_token(6);
/*  353 */     classBelongsToPackage();
/*  354 */     jj_consume_token(11);
/*  355 */     jj_consume_token(34);
/*  356 */     jj_consume_token(6);
/*  357 */     classBelongsToNamespace();
/*  358 */     jj_consume_token(11);
/*      */ 
/*      */     
/*  361 */     while (jj_2_21(4)) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  366 */       jj_consume_token(37);
/*  367 */       jj_consume_token(6);
/*  368 */       belongsToClass();
/*  369 */       jj_consume_token(11);
/*      */     } 
/*      */ 
/*      */     
/*  373 */     while (jj_2_22(4)) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  378 */       jj_consume_token(38);
/*  379 */       jj_consume_token(5);
/*  380 */       belongsToBody();
/*  381 */       jj_consume_token(11);
/*      */     } 
/*  383 */     jj_consume_token(39);
/*  384 */     jj_consume_token(6);
/*  385 */     setFileName();
/*  386 */     jj_consume_token(11);
/*  387 */     jj_consume_token(40);
/*  388 */     jj_consume_token(5);
/*  389 */     setStartLine();
/*  390 */     jj_consume_token(11);
/*  391 */     jj_consume_token(41);
/*  392 */     jj_consume_token(5);
/*  393 */     setStartChar();
/*  394 */     jj_consume_token(11);
/*  395 */     jj_consume_token(42);
/*  396 */     jj_consume_token(5);
/*  397 */     setEndLine();
/*  398 */     jj_consume_token(11);
/*  399 */     jj_consume_token(43);
/*  400 */     jj_consume_token(5);
/*  401 */     setEndChar();
/*  402 */     jj_consume_token(11);
/*  403 */     jj_consume_token(44);
/*  404 */     jj_consume_token(6);
/*  405 */     isAbstract();
/*  406 */     jj_consume_token(11);
/*  407 */     jj_consume_token(45);
/*  408 */     jj_consume_token(6);
/*  409 */     isFinal();
/*  410 */     jj_consume_token(11);
/*  411 */     jj_consume_token(46);
/*  412 */     jj_consume_token(6);
/*  413 */     isStatic();
/*  414 */     jj_consume_token(11);
/*  415 */     jj_consume_token(47);
/*  416 */     jj_consume_token(6);
/*  417 */     classIsInterface();
/*  418 */     jj_consume_token(11);
/*  419 */     jj_consume_token(30);
/*  420 */     jj_consume_token(5);
/*  421 */     setStatute();
/*  422 */     jj_consume_token(11);
/*  423 */     jj_consume_token(48);
/*  424 */     jj_consume_token(5);
/*  425 */     setAccessMode();
/*  426 */     jj_consume_token(11);
/*  427 */     jj_consume_token(11);
/*  428 */     classAdd();
/*      */   }
/*      */ 
/*      */   
/*  432 */   static void setUniqueName() { currentEntityUniqueName = (getToken(0)).image; }
/*      */ 
/*      */   
/*      */   static void classBelongsToPackage() {
/*  436 */     packageName = (getToken(0)).image;
/*  437 */     packageScope = (Package)packagesMap.get(packageName);
/*      */   }
/*      */   
/*      */   static void classBelongsToNamespace() {
/*  441 */     namespaceName = (getToken(0)).image;
/*  442 */     namespaceScope = (Namespace)namespacesMap.get(namespaceName);
/*      */   }
/*      */   
/*      */   static void belongsToClass() {
/*  446 */     classUniqueName = (getToken(0)).image;
/*  447 */     classScope = (Class)addType(classUniqueName);
/*      */   }
/*      */ 
/*      */   
/*  451 */   static void belongsToBody() { lazyBodyID = Integer.parseInt((getToken(0)).image); }
/*      */ 
/*      */   
/*      */   static void isAbstract() {
/*  455 */     isAbstract = (getToken(0)).image;
/*  456 */     if (isAbstract.indexOf("FALSE") > 0) {
/*  457 */       currentEntityIsAbstract = false;
/*      */     } else {
/*  459 */       currentEntityIsAbstract = true;
/*      */     } 
/*      */   }
/*      */   static void isFinal() {
/*  463 */     isFinal = (getToken(0)).image;
/*  464 */     if (isFinal.indexOf("FALSE") > 0) {
/*  465 */       currentEntityIsFinal = false;
/*      */     } else {
/*  467 */       currentEntityIsFinal = true;
/*      */     } 
/*      */   }
/*      */   static void isStatic() {
/*  471 */     isStatic = (getToken(0)).image;
/*  472 */     if (isStatic.indexOf("FALSE") > 0) {
/*  473 */       currentEntityIsStatic = false;
/*      */     } else {
/*  475 */       currentEntityIsStatic = true;
/*      */     } 
/*      */   }
/*      */   static void classIsInterface() {
/*  479 */     isInterface = (getToken(0)).image;
/*  480 */     if (isInterface.indexOf("FALSE") > 0) {
/*  481 */       currentClassIsInterface = false;
/*      */     } else {
/*  483 */       currentClassIsInterface = true;
/*      */     } 
/*      */   }
/*      */   
/*  487 */   static void setAccessMode() { currentEntityAccessMode = Integer.parseInt((getToken(0)).image); }
/*      */ 
/*      */ 
/*      */   
/*  491 */   static void setFileName() { currentEntityFileName = (getToken(0)).image; }
/*      */ 
/*      */ 
/*      */   
/*  495 */   static void setStartLine() { currentEntityStartLine = Integer.parseInt((getToken(0)).image); }
/*      */ 
/*      */ 
/*      */   
/*  499 */   static void setStartChar() { currentEntityStartChar = Integer.parseInt((getToken(0)).image); }
/*      */ 
/*      */ 
/*      */   
/*  503 */   static void setEndLine() { currentEntityEndLine = Integer.parseInt((getToken(0)).image); }
/*      */ 
/*      */   
/*      */   static void setEndChar() {
/*  507 */     currentEntityEndChar = Integer.parseInt((getToken(0)).image);
/*  508 */     currentEntityLocation = new Location(new File(ImporterTools.getPathName(currentEntityFileName), ImporterTools.getFileName(currentEntityFileName)));
/*  509 */     currentEntityLocation.setStartLine(currentEntityStartLine);
/*  510 */     currentEntityLocation.setStartChar(currentEntityStartChar);
/*  511 */     currentEntityLocation.setEndLine(currentEntityEndLine);
/*  512 */     currentEntityLocation.setEndChar(currentEntityEndChar);
/*      */   }
/*      */   
/*      */   static void classAdd() {
/*  516 */     currentClass = (Class)addType(currentEntityUniqueName);
/*  517 */     currentClass.setElementID(new Long(currentEntityID));
/*  518 */     packageScope.addType(currentClass);
/*  519 */     currentClass.setPackage(packageScope);
/*  520 */     namespaceScope.addType(currentClass);
/*  521 */     currentClass.setScope(namespaceScope);
/*  522 */     if (classScope != null)
/*  523 */       currentClass.setScope(classScope); 
/*  524 */     if (currentEntityIsAbstract)
/*  525 */       currentClass.setAbstract(); 
/*  526 */     if (currentEntityIsFinal)
/*  527 */       currentClass.setFinal(); 
/*  528 */     if (currentEntityIsStatic)
/*  529 */       currentClass.setStatic(); 
/*  530 */     if (currentClassIsInterface)
/*  531 */       currentClass.setInterface(); 
/*  532 */     currentClass.setStatute(currentEntityStatute);
/*  533 */     currentClass.setAccessMode(currentEntityAccessMode);
/*  534 */     currentClass.setLocation(currentEntityLocation);
/*  535 */     if (lazyBodyID >= 0) {
/*  536 */       lazyBodyScopesForClasses.put(new Long(lazyBodyID), currentClass);
/*  537 */       lazyBodyID = -1;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static final void parseArrayDecorator() {
/*  542 */     jj_consume_token(49);
/*  543 */     jj_consume_token(5);
/*  544 */     setID();
/*  545 */     jj_consume_token(29);
/*  546 */     jj_consume_token(6);
/*  547 */     setName();
/*  548 */     jj_consume_token(11);
/*  549 */     jj_consume_token(33);
/*  550 */     jj_consume_token(6);
/*  551 */     setUniqueName();
/*  552 */     jj_consume_token(11);
/*  553 */     jj_consume_token(50);
/*  554 */     jj_consume_token(6);
/*  555 */     decoratedType();
/*  556 */     jj_consume_token(11);
/*  557 */     jj_consume_token(11);
/*  558 */     arrayDecoratorAdd();
/*      */   }
/*      */ 
/*      */   
/*  562 */   static void decoratedType() { dataType = (Type)classesMap.get((getToken(0)).image); }
/*      */ 
/*      */   
/*      */   static void arrayDecoratorAdd() {
/*  566 */     dataType = addArrayDecorator(currentEntityUniqueName, dataType);
/*  567 */     ((ArrayDecorator)dataType).setElementID(new Long(currentEntityID));
/*      */   }
/*      */   
/*      */   public static final void parseInheritanceDefinition() {
/*  571 */     jj_consume_token(51);
/*  572 */     jj_consume_token(5);
/*  573 */     jj_consume_token(52);
/*  574 */     jj_consume_token(6);
/*  575 */     subClassUniqueName();
/*  576 */     jj_consume_token(11);
/*  577 */     jj_consume_token(53);
/*  578 */     jj_consume_token(6);
/*  579 */     superClassUniqueName();
/*  580 */     jj_consume_token(11);
/*  581 */     jj_consume_token(11);
/*  582 */     inheritanceDefinitionAdd();
/*      */   }
/*      */ 
/*      */   
/*  586 */   static void subClassUniqueName() { subClassUniqueName = (getToken(0)).image; }
/*      */ 
/*      */ 
/*      */   
/*  590 */   static void superClassUniqueName() { superClassUniqueName = (getToken(0)).image; }
/*      */ 
/*      */   
/*      */   static void inheritanceDefinitionAdd() {
/*  594 */     subClass = (DataAbstraction)addType(subClassUniqueName);
/*  595 */     if (superClassUniqueName.indexOf("_NULL_") < 0) {
/*      */ 
/*      */       
/*  598 */       DataAbstraction superClass = (DataAbstraction)addType(superClassUniqueName);
/*  599 */       subClass.addAncestor(superClass);
/*  600 */       superClass.addDescendant(subClass);
/*      */     } 
/*      */   }
/*      */   
/*      */   public static final void parseImplementsDefinition() {
/*  605 */     jj_consume_token(54);
/*  606 */     jj_consume_token(5);
/*  607 */     jj_consume_token(52);
/*  608 */     jj_consume_token(6);
/*  609 */     subClassUniqueName();
/*  610 */     jj_consume_token(11);
/*  611 */     jj_consume_token(55);
/*  612 */     jj_consume_token(6);
/*  613 */     superClassUniqueName();
/*  614 */     jj_consume_token(11);
/*  615 */     jj_consume_token(11);
/*  616 */     implementsDefinitionAdd();
/*      */   }
/*      */   
/*      */   static void implementsDefinitionAdd() {
/*  620 */     subClass = (Class)addType(subClassUniqueName);
/*  621 */     DataAbstraction superClass = (DataAbstraction)addType(superClassUniqueName);
/*  622 */     subClass.addInterface(superClass);
/*  623 */     superClass.addDescendant(subClass);
/*      */   }
/*      */   
/*      */   public static final void parseInitializerBody() {
/*  627 */     jj_consume_token(56);
/*  628 */     jj_consume_token(5);
/*  629 */     setID();
/*  630 */     jj_consume_token(57);
/*  631 */     jj_consume_token(6);
/*  632 */     belongsToClass();
/*  633 */     jj_consume_token(11);
/*  634 */     jj_consume_token(58);
/*  635 */     jj_consume_token(5);
/*  636 */     setLOC();
/*  637 */     jj_consume_token(11);
/*  638 */     jj_consume_token(59);
/*  639 */     jj_consume_token(5);
/*  640 */     setCYCLO();
/*  641 */     jj_consume_token(11);
/*  642 */     jj_consume_token(60);
/*  643 */     jj_consume_token(5);
/*  644 */     setNOS();
/*  645 */     jj_consume_token(11);
/*  646 */     jj_consume_token(61);
/*  647 */     jj_consume_token(5);
/*  648 */     setNODec();
/*  649 */     jj_consume_token(11);
/*  650 */     jj_consume_token(62);
/*  651 */     jj_consume_token(5);
/*  652 */     setNOCmt();
/*  653 */     jj_consume_token(11);
/*  654 */     jj_consume_token(63);
/*  655 */     jj_consume_token(5);
/*  656 */     setNOExc();
/*  657 */     jj_consume_token(11);
/*  658 */     jj_consume_token(64);
/*  659 */     jj_consume_token(5);
/*  660 */     setNOExits();
/*  661 */     jj_consume_token(11);
/*  662 */     jj_consume_token(65);
/*  663 */     jj_consume_token(5);
/*  664 */     setNOL();
/*  665 */     jj_consume_token(11);
/*  666 */     jj_consume_token(11);
/*  667 */     initializerBodyAdd();
/*      */   }
/*      */ 
/*      */   
/*  671 */   static void setLOC() { currentLOC = Integer.parseInt((getToken(0)).image); }
/*      */ 
/*      */ 
/*      */   
/*  675 */   static void setCYCLO() { currentCYCLO = Integer.parseInt((getToken(0)).image); }
/*      */ 
/*      */ 
/*      */   
/*  679 */   static void setNOS() { currentNOS = Integer.parseInt((getToken(0)).image); }
/*      */ 
/*      */ 
/*      */   
/*  683 */   static void setNODec() { currentNODec = Integer.parseInt((getToken(0)).image); }
/*      */ 
/*      */ 
/*      */   
/*  687 */   static void setNOCmt() { currentNOCmt = Integer.parseInt((getToken(0)).image); }
/*      */ 
/*      */ 
/*      */   
/*  691 */   static void setNOExc() { currentNOExc = Integer.parseInt((getToken(0)).image); }
/*      */ 
/*      */ 
/*      */   
/*  695 */   static void setNOExits() { currentNOExits = Integer.parseInt((getToken(0)).image); }
/*      */ 
/*      */ 
/*      */   
/*  699 */   static void setNOL() { currentNOL = Integer.parseInt((getToken(0)).image); }
/*      */ 
/*      */   
/*      */   static void initializerBodyAdd() {
/*  703 */     ib = new InitializerBody(classScope);
/*  704 */     classScope.addInitializer(ib);
/*  705 */     ib.setElementID(new Long(currentEntityID));
/*  706 */     ib.setNumberOfLines(currentLOC);
/*  707 */     ib.setCyclomaticNumber(currentCYCLO);
/*  708 */     ib.setNumberOfStatements(currentNOS);
/*  709 */     ib.setNumberOfDecisions(currentNODec);
/*  710 */     ib.setNumberOfComments(currentNOCmt);
/*  711 */     ib.setNumberOfExceptions(currentNOExc);
/*  712 */     ib.setNumberOfExits(currentNOExits);
/*  713 */     ib.setNumberOfLoops(currentNOL);
/*  714 */     bodiesMap.put(new Long(currentEntityID), ib);
/*      */   }
/*      */   
/*      */   public static final void parseAttribute() {
/*  718 */     jj_consume_token(66);
/*  719 */     jj_consume_token(5);
/*  720 */     setID();
/*  721 */     jj_consume_token(29);
/*  722 */     jj_consume_token(6);
/*  723 */     setName();
/*  724 */     jj_consume_token(11);
/*  725 */     jj_consume_token(33);
/*  726 */     jj_consume_token(6);
/*  727 */     setUniqueName();
/*  728 */     jj_consume_token(11);
/*  729 */     jj_consume_token(67);
/*  730 */     jj_consume_token(6);
/*  731 */     setType();
/*  732 */     jj_consume_token(11);
/*  733 */     jj_consume_token(45);
/*  734 */     jj_consume_token(6);
/*  735 */     isFinal();
/*  736 */     jj_consume_token(11);
/*  737 */     jj_consume_token(46);
/*  738 */     jj_consume_token(6);
/*  739 */     isStatic();
/*  740 */     jj_consume_token(11);
/*  741 */     jj_consume_token(30);
/*  742 */     jj_consume_token(5);
/*  743 */     setStatute();
/*  744 */     jj_consume_token(11);
/*  745 */     jj_consume_token(39);
/*  746 */     jj_consume_token(6);
/*  747 */     setFileName();
/*  748 */     jj_consume_token(11);
/*  749 */     jj_consume_token(40);
/*  750 */     jj_consume_token(5);
/*  751 */     setStartLine();
/*  752 */     jj_consume_token(11);
/*  753 */     jj_consume_token(41);
/*  754 */     jj_consume_token(5);
/*  755 */     setStartChar();
/*  756 */     jj_consume_token(11);
/*  757 */     jj_consume_token(42);
/*  758 */     jj_consume_token(5);
/*  759 */     setEndLine();
/*  760 */     jj_consume_token(11);
/*  761 */     jj_consume_token(43);
/*  762 */     jj_consume_token(5);
/*  763 */     setEndChar();
/*  764 */     jj_consume_token(11);
/*  765 */     jj_consume_token(57);
/*  766 */     jj_consume_token(6);
/*  767 */     belongsToClass();
/*  768 */     jj_consume_token(11);
/*  769 */     jj_consume_token(48);
/*  770 */     jj_consume_token(5);
/*  771 */     setAccessMode();
/*  772 */     jj_consume_token(11);
/*  773 */     jj_consume_token(11);
/*  774 */     attributeAdd();
/*      */   }
/*      */   
/*      */   static void attributeAdd() {
/*  778 */     currentAttribute = new Attribute(currentEntityName);
/*  779 */     classScope.addAttribute(currentAttribute);
/*  780 */     currentAttribute.setScope(classScope);
/*  781 */     if (currentEntityIsFinal)
/*  782 */       currentAttribute.setFinal(); 
/*  783 */     if (currentEntityIsStatic)
/*  784 */       currentAttribute.setStatic(); 
/*  785 */     currentAttribute.setStatute(currentEntityStatute);
/*  786 */     currentAttribute.setLocation(currentEntityLocation);
/*  787 */     currentAttribute.setAccessMode(currentEntityAccessMode);
/*  788 */     variablesMap.put(currentEntityUniqueName, currentAttribute);
/*      */   }
/*      */   
/*      */   public static final void parseMethod() {
/*  792 */     jj_consume_token(68);
/*  793 */     jj_consume_token(5);
/*  794 */     setID();
/*  795 */     jj_consume_token(29);
/*  796 */     jj_consume_token(6);
/*  797 */     setName();
/*  798 */     jj_consume_token(11);
/*  799 */     jj_consume_token(33);
/*  800 */     jj_consume_token(7);
/*  801 */     setUniqueName();
/*  802 */     jj_consume_token(11);
/*  803 */     jj_consume_token(39);
/*  804 */     jj_consume_token(6);
/*  805 */     setFileName();
/*  806 */     jj_consume_token(11);
/*  807 */     jj_consume_token(40);
/*  808 */     jj_consume_token(5);
/*  809 */     setStartLine();
/*  810 */     jj_consume_token(11);
/*  811 */     jj_consume_token(41);
/*  812 */     jj_consume_token(5);
/*  813 */     setStartChar();
/*  814 */     jj_consume_token(11);
/*  815 */     jj_consume_token(42);
/*  816 */     jj_consume_token(5);
/*  817 */     setEndLine();
/*  818 */     jj_consume_token(11);
/*  819 */     jj_consume_token(43);
/*  820 */     jj_consume_token(5);
/*  821 */     setEndChar();
/*  822 */     jj_consume_token(11);
/*  823 */     jj_consume_token(57);
/*  824 */     jj_consume_token(6);
/*  825 */     belongsToClass();
/*  826 */     jj_consume_token(11);
/*      */ 
/*      */     
/*  829 */     while (jj_2_23(4)) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  834 */       jj_consume_token(69);
/*  835 */       jj_consume_token(6);
/*  836 */       methodReturnType();
/*  837 */       jj_consume_token(11);
/*      */     } 
/*  839 */     jj_consume_token(48);
/*  840 */     jj_consume_token(5);
/*  841 */     setAccessMode();
/*  842 */     jj_consume_token(11);
/*  843 */     jj_consume_token(44);
/*  844 */     jj_consume_token(6);
/*  845 */     isAbstract();
/*  846 */     jj_consume_token(11);
/*  847 */     jj_consume_token(45);
/*  848 */     jj_consume_token(6);
/*  849 */     isFinal();
/*  850 */     jj_consume_token(11);
/*  851 */     jj_consume_token(46);
/*  852 */     jj_consume_token(6);
/*  853 */     isStatic();
/*  854 */     jj_consume_token(11);
/*  855 */     jj_consume_token(30);
/*  856 */     jj_consume_token(5);
/*  857 */     setStatute();
/*  858 */     jj_consume_token(11);
/*  859 */     jj_consume_token(70);
/*  860 */     jj_consume_token(5);
/*  861 */     methodKindOf();
/*  862 */     jj_consume_token(11);
/*  863 */     jj_consume_token(11);
/*  864 */     methodAdd();
/*      */   }
/*      */   
/*      */   static void methodReturnType() {
/*  868 */     currentReturnTypeName = (getToken(0)).image;
/*  869 */     if (currentReturnTypeName.indexOf("_NULL_") >= 0) {
/*  870 */       currentMethodReturnType = null;
/*      */     } else {
/*  872 */       currentMethodReturnType = addType(currentReturnTypeName);
/*      */     } 
/*      */   }
/*      */   
/*  876 */   static void methodKindOf() { currentMethodKindOf = Integer.parseInt((getToken(0)).image); }
/*      */ 
/*      */   
/*      */   static void methodAdd() {
/*  880 */     m = addMethod(currentEntityUniqueName);
/*  881 */     m.setLocation(currentEntityLocation);
/*  882 */     m.setScope(classScope);
/*  883 */     classScope.addMethod(m);
/*  884 */     m.setReturnType(currentMethodReturnType);
/*  885 */     if (currentEntityIsAbstract)
/*  886 */       m.setAbstract(); 
/*  887 */     if (currentEntityIsFinal)
/*  888 */       m.setFinal(); 
/*  889 */     if (currentEntityIsStatic)
/*  890 */       m.setStatic(); 
/*  891 */     m.setStatute(currentEntityStatute);
/*  892 */     m.setAccessMode(currentEntityAccessMode);
/*  893 */     m.setKindOf(currentMethodKindOf);
/*      */   }
/*      */   
/*      */   public static final void parseThrowsException() {
/*  897 */     jj_consume_token(71);
/*  898 */     jj_consume_token(5);
/*  899 */     setID();
/*  900 */     jj_consume_token(72);
/*  901 */     jj_consume_token(7);
/*  902 */     belongsToMethod();
/*  903 */     jj_consume_token(11);
/*  904 */     jj_consume_token(73);
/*  905 */     jj_consume_token(6);
/*  906 */     setUniqueName();
/*  907 */     jj_consume_token(11);
/*  908 */     jj_consume_token(11);
/*  909 */     throwsExceptionAdd();
/*      */   }
/*      */   
/*      */   static void belongsToMethod() {
/*  913 */     methodName = (getToken(0)).image;
/*  914 */     methodScope = (Method)methodsMap.get(methodName);
/*      */   }
/*      */   
/*      */   static void throwsExceptionAdd() {
/*  918 */     e = (Class)addType(currentEntityUniqueName);
/*  919 */     methodScope.addException(e);
/*      */   }
/*      */   
/*      */   public static final void parseMethodBody() {
/*  923 */     jj_consume_token(74);
/*  924 */     jj_consume_token(5);
/*  925 */     setID();
/*  926 */     jj_consume_token(57);
/*  927 */     jj_consume_token(7);
/*  928 */     belongsToMethod();
/*  929 */     jj_consume_token(11);
/*  930 */     jj_consume_token(58);
/*  931 */     jj_consume_token(5);
/*  932 */     setLOC();
/*  933 */     jj_consume_token(11);
/*  934 */     jj_consume_token(59);
/*  935 */     jj_consume_token(5);
/*  936 */     setCYCLO();
/*  937 */     jj_consume_token(11);
/*  938 */     jj_consume_token(60);
/*  939 */     jj_consume_token(5);
/*  940 */     setNOS();
/*  941 */     jj_consume_token(11);
/*  942 */     jj_consume_token(61);
/*  943 */     jj_consume_token(5);
/*  944 */     setNODec();
/*  945 */     jj_consume_token(11);
/*  946 */     jj_consume_token(62);
/*  947 */     jj_consume_token(5);
/*  948 */     setNOCmt();
/*  949 */     jj_consume_token(11);
/*  950 */     jj_consume_token(63);
/*  951 */     jj_consume_token(5);
/*  952 */     setNOExc();
/*  953 */     jj_consume_token(11);
/*  954 */     jj_consume_token(64);
/*  955 */     jj_consume_token(5);
/*  956 */     setNOExits();
/*  957 */     jj_consume_token(11);
/*  958 */     jj_consume_token(65);
/*  959 */     jj_consume_token(5);
/*  960 */     setNOL();
/*  961 */     jj_consume_token(11);
/*  962 */     jj_consume_token(11);
/*  963 */     methodBodyAdd();
/*      */   }
/*      */   
/*      */   static void methodBodyAdd() {
/*  967 */     mb = new FunctionBody(methodScope);
/*  968 */     methodScope.setFunctionBody(mb);
/*  969 */     mb.setElementID(new Long(currentEntityID));
/*  970 */     mb.setNumberOfLines(currentLOC);
/*  971 */     mb.setCyclomaticNumber(currentCYCLO);
/*  972 */     mb.setNumberOfStatements(currentNOS);
/*  973 */     mb.setNumberOfDecisions(currentNODec);
/*  974 */     mb.setNumberOfComments(currentNOCmt);
/*  975 */     mb.setNumberOfExceptions(currentNOExc);
/*  976 */     mb.setNumberOfExits(currentNOExits);
/*  977 */     mb.setNumberOfLoops(currentNOL);
/*  978 */     bodiesMap.put(new Long(currentEntityID), mb);
/*      */   }
/*      */   
/*      */   public static final void parseFormalParameter() {
/*  982 */     jj_consume_token(75);
/*  983 */     jj_consume_token(5);
/*  984 */     setID();
/*  985 */     jj_consume_token(29);
/*  986 */     jj_consume_token(6);
/*  987 */     setName();
/*  988 */     jj_consume_token(11);
/*  989 */     jj_consume_token(33);
/*  990 */     jj_consume_token(7);
/*  991 */     setUniqueName();
/*  992 */     jj_consume_token(11);
/*  993 */     jj_consume_token(67);
/*  994 */     jj_consume_token(6);
/*  995 */     setType();
/*  996 */     jj_consume_token(11);
/*  997 */     jj_consume_token(45);
/*  998 */     jj_consume_token(6);
/*  999 */     isFinal();
/* 1000 */     jj_consume_token(11);
/* 1001 */     jj_consume_token(46);
/* 1002 */     jj_consume_token(6);
/* 1003 */     isStatic();
/* 1004 */     jj_consume_token(11);
/* 1005 */     jj_consume_token(30);
/* 1006 */     jj_consume_token(5);
/* 1007 */     setStatute();
/* 1008 */     jj_consume_token(11);
/* 1009 */     jj_consume_token(39);
/* 1010 */     jj_consume_token(6);
/* 1011 */     setFileName();
/* 1012 */     jj_consume_token(11);
/* 1013 */     jj_consume_token(40);
/* 1014 */     jj_consume_token(5);
/* 1015 */     setStartLine();
/* 1016 */     jj_consume_token(11);
/* 1017 */     jj_consume_token(41);
/* 1018 */     jj_consume_token(5);
/* 1019 */     setStartChar();
/* 1020 */     jj_consume_token(11);
/* 1021 */     jj_consume_token(42);
/* 1022 */     jj_consume_token(5);
/* 1023 */     setEndLine();
/* 1024 */     jj_consume_token(11);
/* 1025 */     jj_consume_token(43);
/* 1026 */     jj_consume_token(5);
/* 1027 */     setEndChar();
/* 1028 */     jj_consume_token(11);
/* 1029 */     jj_consume_token(57);
/* 1030 */     jj_consume_token(7);
/* 1031 */     parameterBelongsTo();
/* 1032 */     jj_consume_token(11);
/* 1033 */     jj_consume_token(76);
/* 1034 */     jj_consume_token(6);
/* 1035 */     isExParam();
/* 1036 */     jj_consume_token(11);
/* 1037 */     jj_consume_token(11);
/* 1038 */     parameterAdd();
/*      */   }
/*      */   
/*      */   static void setType() {
/* 1042 */     currentTypeName = (getToken(0)).image;
/* 1043 */     dataType = (Type)classesMap.get(currentTypeName);
/*      */   }
/*      */   
/*      */   static void parameterBelongsTo() {
/* 1047 */     currentMethodScopeName = (getToken(0)).image;
/* 1048 */     methodScope = (Method)methodsMap.get(currentMethodScopeName);
/*      */   }
/*      */   
/*      */   static void isExParam() {
/* 1052 */     if ((getToken(0)).image.toUpperCase().indexOf("TRUE") >= 0) {
/* 1053 */       currentParameterIsExParam = true;
/*      */     } else {
/* 1055 */       currentParameterIsExParam = false;
/*      */     } 
/*      */   }
/*      */   static void parameterAdd() {
/* 1059 */     par = new Parameter(currentEntityName, dataType, methodScope);
/* 1060 */     par.setElementID(new Long(currentEntityID));
/* 1061 */     if (currentEntityIsFinal) {
/* 1062 */       par.setFinal();
/*      */     }
/*      */     
/* 1065 */     par.setStatute(currentEntityStatute);
/* 1066 */     par.setLocation(currentEntityLocation);
/*      */ 
/*      */     
/* 1069 */     methodScope.addParameter(par);
/*      */   }
/*      */   
/*      */   public static final void parseLocalVariable() {
/* 1073 */     jj_consume_token(77);
/* 1074 */     jj_consume_token(5);
/* 1075 */     setID();
/* 1076 */     jj_consume_token(29);
/* 1077 */     jj_consume_token(6);
/* 1078 */     setName();
/* 1079 */     jj_consume_token(11);
/* 1080 */     jj_consume_token(33);
/* 1081 */     jj_consume_token(7);
/* 1082 */     setUniqueName();
/* 1083 */     jj_consume_token(11);
/* 1084 */     jj_consume_token(67);
/* 1085 */     jj_consume_token(6);
/* 1086 */     setType();
/* 1087 */     jj_consume_token(11);
/* 1088 */     jj_consume_token(45);
/* 1089 */     jj_consume_token(6);
/* 1090 */     isFinal();
/* 1091 */     jj_consume_token(11);
/* 1092 */     jj_consume_token(46);
/* 1093 */     jj_consume_token(6);
/* 1094 */     isStatic();
/* 1095 */     jj_consume_token(11);
/* 1096 */     jj_consume_token(30);
/* 1097 */     jj_consume_token(5);
/* 1098 */     setStatute();
/* 1099 */     jj_consume_token(11);
/* 1100 */     jj_consume_token(39);
/* 1101 */     jj_consume_token(6);
/* 1102 */     setFileName();
/* 1103 */     jj_consume_token(11);
/* 1104 */     jj_consume_token(40);
/* 1105 */     jj_consume_token(5);
/* 1106 */     setStartLine();
/* 1107 */     jj_consume_token(11);
/* 1108 */     jj_consume_token(41);
/* 1109 */     jj_consume_token(5);
/* 1110 */     setStartChar();
/* 1111 */     jj_consume_token(11);
/* 1112 */     jj_consume_token(42);
/* 1113 */     jj_consume_token(5);
/* 1114 */     setEndLine();
/* 1115 */     jj_consume_token(11);
/* 1116 */     jj_consume_token(43);
/* 1117 */     jj_consume_token(5);
/* 1118 */     setEndChar();
/* 1119 */     jj_consume_token(11);
/* 1120 */     jj_consume_token(57);
/* 1121 */     jj_consume_token(5);
/* 1122 */     setBodyScope();
/* 1123 */     jj_consume_token(11);
/* 1124 */     jj_consume_token(78);
/* 1125 */     jj_consume_token(6);
/* 1126 */     isBlock();
/* 1127 */     jj_consume_token(11);
/* 1128 */     jj_consume_token(11);
/* 1129 */     localVarAdd();
/*      */   }
/*      */   
/*      */   static void setBodyScope() {
/* 1133 */     bodyID = Integer.parseInt((getToken(0)).image);
/* 1134 */     bodyScope = (Body)bodiesMap.get(new Long(bodyID));
/*      */   }
/*      */   
/*      */   static void isBlock() {
/* 1138 */     if ((getToken(0)).image.toUpperCase().indexOf("TRUE") >= 0) {
/* 1139 */       currentLocalVarIsBlock = true;
/*      */     } else {
/* 1141 */       currentLocalVarIsBlock = false;
/*      */     } 
/*      */   }
/*      */   static void localVarAdd() {
/* 1145 */     lv = new LocalVariable(currentEntityName, bodyScope.getCodeStripe());
/* 1146 */     lv.setElementID(new Long(currentEntityID));
/*      */     
/* 1148 */     if (currentEntityIsFinal)
/* 1149 */       lv.setFinal(); 
/* 1150 */     if (currentEntityIsStatic)
/* 1151 */       lv.setStatic(); 
/* 1152 */     lv.setStatute(currentEntityStatute);
/* 1153 */     lv.setLocation(currentEntityLocation);
/* 1154 */     if (currentLocalVarIsBlock)
/* 1155 */       lv.setBlock(); 
/*      */   }
/*      */   
/*      */   public static final void parseAccess() {
/* 1159 */     jj_consume_token(79);
/* 1160 */     jj_consume_token(5);
/* 1161 */     setID();
/* 1162 */     jj_consume_token(33);
/* 1163 */     if (jj_2_24(4)) {
/* 1164 */       jj_consume_token(7);
/* 1165 */     } else if (jj_2_25(4)) {
/* 1166 */       jj_consume_token(6);
/*      */     } else {
/* 1168 */       jj_consume_token(-1);
/* 1169 */       throw new ParseException();
/*      */     } 
/* 1171 */     setUniqueName();
/* 1172 */     jj_consume_token(11);
/* 1173 */     jj_consume_token(80);
/* 1174 */     jj_consume_token(5);
/* 1175 */     setBodyScope();
/* 1176 */     jj_consume_token(11);
/* 1177 */     jj_consume_token(81);
/* 1178 */     jj_consume_token(5);
/* 1179 */     setNumber();
/* 1180 */     jj_consume_token(11);
/* 1181 */     jj_consume_token(11);
/* 1182 */     accessAdd();
/*      */   }
/*      */ 
/*      */   
/* 1186 */   static void setNumber() { currentNumber = Integer.parseInt((getToken(0)).image); }
/*      */ 
/*      */   
/*      */   static void accessAdd() {
/* 1190 */     var = (Variable)variablesMap.get(currentEntityUniqueName);
/* 1191 */     Access acc = new Access(var, bodyScope);
/* 1192 */     bodyScope.addAccess(acc);
/* 1193 */     acc.setCount(currentNumber);
/*      */   }
/*      */   
/*      */   public static final void parseInvocation() {
/* 1197 */     jj_consume_token(82);
/* 1198 */     jj_consume_token(5);
/* 1199 */     setID();
/* 1200 */     jj_consume_token(33);
/* 1201 */     jj_consume_token(7);
/* 1202 */     setUniqueName();
/* 1203 */     jj_consume_token(11);
/* 1204 */     jj_consume_token(83);
/* 1205 */     jj_consume_token(5);
/* 1206 */     setBodyScope();
/* 1207 */     jj_consume_token(11);
/* 1208 */     jj_consume_token(84);
/* 1209 */     jj_consume_token(5);
/* 1210 */     setNumber();
/* 1211 */     jj_consume_token(11);
/* 1212 */     jj_consume_token(11);
/* 1213 */     invocationAdd();
/*      */   }
/*      */   
/*      */   static void invocationAdd() {
/* 1217 */     met = (Method)methodsMap.get(currentEntityUniqueName);
/* 1218 */     Call call = new Call(met, bodyScope);
/* 1219 */     bodyScope.addCall(call);
/* 1220 */     call.setCount(currentNumber);
/*      */   }
/*      */   
/*      */   private static final boolean jj_2_1(int xla) {
/* 1224 */     jj_la = xla;
/* 1225 */     jj_lastpos = jj_scanpos = token;
/*      */     try {
/* 1227 */       return !jj_3_1();
/* 1228 */     } catch (LookaheadSuccess ls) {
/* 1229 */       return true;
/*      */     } finally {
/* 1231 */       jj_save(0, xla);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static final boolean jj_2_2(int xla) {
/* 1236 */     jj_la = xla;
/* 1237 */     jj_lastpos = jj_scanpos = token;
/*      */     try {
/* 1239 */       return !jj_3_2();
/* 1240 */     } catch (LookaheadSuccess ls) {
/* 1241 */       return true;
/*      */     } finally {
/* 1243 */       jj_save(1, xla);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static final boolean jj_2_3(int xla) {
/* 1248 */     jj_la = xla;
/* 1249 */     jj_lastpos = jj_scanpos = token;
/*      */     try {
/* 1251 */       return !jj_3_3();
/* 1252 */     } catch (LookaheadSuccess ls) {
/* 1253 */       return true;
/*      */     } finally {
/* 1255 */       jj_save(2, xla);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static final boolean jj_2_4(int xla) {
/* 1260 */     jj_la = xla;
/* 1261 */     jj_lastpos = jj_scanpos = token;
/*      */     try {
/* 1263 */       return !jj_3_4();
/* 1264 */     } catch (LookaheadSuccess ls) {
/* 1265 */       return true;
/*      */     } finally {
/* 1267 */       jj_save(3, xla);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static final boolean jj_2_5(int xla) {
/* 1272 */     jj_la = xla;
/* 1273 */     jj_lastpos = jj_scanpos = token;
/*      */     try {
/* 1275 */       return !jj_3_5();
/* 1276 */     } catch (LookaheadSuccess ls) {
/* 1277 */       return true;
/*      */     } finally {
/* 1279 */       jj_save(4, xla);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static final boolean jj_2_6(int xla) {
/* 1284 */     jj_la = xla;
/* 1285 */     jj_lastpos = jj_scanpos = token;
/*      */     try {
/* 1287 */       return !jj_3_6();
/* 1288 */     } catch (LookaheadSuccess ls) {
/* 1289 */       return true;
/*      */     } finally {
/* 1291 */       jj_save(5, xla);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static final boolean jj_2_7(int xla) {
/* 1296 */     jj_la = xla;
/* 1297 */     jj_lastpos = jj_scanpos = token;
/*      */     try {
/* 1299 */       return !jj_3_7();
/* 1300 */     } catch (LookaheadSuccess ls) {
/* 1301 */       return true;
/*      */     } finally {
/* 1303 */       jj_save(6, xla);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static final boolean jj_2_8(int xla) {
/* 1308 */     jj_la = xla;
/* 1309 */     jj_lastpos = jj_scanpos = token;
/*      */     try {
/* 1311 */       return !jj_3_8();
/* 1312 */     } catch (LookaheadSuccess ls) {
/* 1313 */       return true;
/*      */     } finally {
/* 1315 */       jj_save(7, xla);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static final boolean jj_2_9(int xla) {
/* 1320 */     jj_la = xla;
/* 1321 */     jj_lastpos = jj_scanpos = token;
/*      */     try {
/* 1323 */       return !jj_3_9();
/* 1324 */     } catch (LookaheadSuccess ls) {
/* 1325 */       return true;
/*      */     } finally {
/* 1327 */       jj_save(8, xla);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static final boolean jj_2_10(int xla) {
/* 1332 */     jj_la = xla;
/* 1333 */     jj_lastpos = jj_scanpos = token;
/*      */     try {
/* 1335 */       return !jj_3_10();
/* 1336 */     } catch (LookaheadSuccess ls) {
/* 1337 */       return true;
/*      */     } finally {
/* 1339 */       jj_save(9, xla);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static final boolean jj_2_11(int xla) {
/* 1344 */     jj_la = xla;
/* 1345 */     jj_lastpos = jj_scanpos = token;
/*      */     try {
/* 1347 */       return !jj_3_11();
/* 1348 */     } catch (LookaheadSuccess ls) {
/* 1349 */       return true;
/*      */     } finally {
/* 1351 */       jj_save(10, xla);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static final boolean jj_2_12(int xla) {
/* 1356 */     jj_la = xla;
/* 1357 */     jj_lastpos = jj_scanpos = token;
/*      */     try {
/* 1359 */       return !jj_3_12();
/* 1360 */     } catch (LookaheadSuccess ls) {
/* 1361 */       return true;
/*      */     } finally {
/* 1363 */       jj_save(11, xla);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static final boolean jj_2_13(int xla) {
/* 1368 */     jj_la = xla;
/* 1369 */     jj_lastpos = jj_scanpos = token;
/*      */     try {
/* 1371 */       return !jj_3_13();
/* 1372 */     } catch (LookaheadSuccess ls) {
/* 1373 */       return true;
/*      */     } finally {
/* 1375 */       jj_save(12, xla);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static final boolean jj_2_14(int xla) {
/* 1380 */     jj_la = xla;
/* 1381 */     jj_lastpos = jj_scanpos = token;
/*      */     try {
/* 1383 */       return !jj_3_14();
/* 1384 */     } catch (LookaheadSuccess ls) {
/* 1385 */       return true;
/*      */     } finally {
/* 1387 */       jj_save(13, xla);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static final boolean jj_2_15(int xla) {
/* 1392 */     jj_la = xla;
/* 1393 */     jj_lastpos = jj_scanpos = token;
/*      */     try {
/* 1395 */       return !jj_3_15();
/* 1396 */     } catch (LookaheadSuccess ls) {
/* 1397 */       return true;
/*      */     } finally {
/* 1399 */       jj_save(14, xla);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static final boolean jj_2_16(int xla) {
/* 1404 */     jj_la = xla;
/* 1405 */     jj_lastpos = jj_scanpos = token;
/*      */     try {
/* 1407 */       return !jj_3_16();
/* 1408 */     } catch (LookaheadSuccess ls) {
/* 1409 */       return true;
/*      */     } finally {
/* 1411 */       jj_save(15, xla);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static final boolean jj_2_17(int xla) {
/* 1416 */     jj_la = xla;
/* 1417 */     jj_lastpos = jj_scanpos = token;
/*      */     try {
/* 1419 */       return !jj_3_17();
/* 1420 */     } catch (LookaheadSuccess ls) {
/* 1421 */       return true;
/*      */     } finally {
/* 1423 */       jj_save(16, xla);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static final boolean jj_2_18(int xla) {
/* 1428 */     jj_la = xla;
/* 1429 */     jj_lastpos = jj_scanpos = token;
/*      */     try {
/* 1431 */       return !jj_3_18();
/* 1432 */     } catch (LookaheadSuccess ls) {
/* 1433 */       return true;
/*      */     } finally {
/* 1435 */       jj_save(17, xla);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static final boolean jj_2_19(int xla) {
/* 1440 */     jj_la = xla;
/* 1441 */     jj_lastpos = jj_scanpos = token;
/*      */     try {
/* 1443 */       return !jj_3_19();
/* 1444 */     } catch (LookaheadSuccess ls) {
/* 1445 */       return true;
/*      */     } finally {
/* 1447 */       jj_save(18, xla);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static final boolean jj_2_20(int xla) {
/* 1452 */     jj_la = xla;
/* 1453 */     jj_lastpos = jj_scanpos = token;
/*      */     try {
/* 1455 */       return !jj_3_20();
/* 1456 */     } catch (LookaheadSuccess ls) {
/* 1457 */       return true;
/*      */     } finally {
/* 1459 */       jj_save(19, xla);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static final boolean jj_2_21(int xla) {
/* 1464 */     jj_la = xla;
/* 1465 */     jj_lastpos = jj_scanpos = token;
/*      */     try {
/* 1467 */       return !jj_3_21();
/* 1468 */     } catch (LookaheadSuccess ls) {
/* 1469 */       return true;
/*      */     } finally {
/* 1471 */       jj_save(20, xla);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static final boolean jj_2_22(int xla) {
/* 1476 */     jj_la = xla;
/* 1477 */     jj_lastpos = jj_scanpos = token;
/*      */     try {
/* 1479 */       return !jj_3_22();
/* 1480 */     } catch (LookaheadSuccess ls) {
/* 1481 */       return true;
/*      */     } finally {
/* 1483 */       jj_save(21, xla);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static final boolean jj_2_23(int xla) {
/* 1488 */     jj_la = xla;
/* 1489 */     jj_lastpos = jj_scanpos = token;
/*      */     try {
/* 1491 */       return !jj_3_23();
/* 1492 */     } catch (LookaheadSuccess ls) {
/* 1493 */       return true;
/*      */     } finally {
/* 1495 */       jj_save(22, xla);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static final boolean jj_2_24(int xla) {
/* 1500 */     jj_la = xla;
/* 1501 */     jj_lastpos = jj_scanpos = token;
/*      */     try {
/* 1503 */       return !jj_3_24();
/* 1504 */     } catch (LookaheadSuccess ls) {
/* 1505 */       return true;
/*      */     } finally {
/* 1507 */       jj_save(23, xla);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static final boolean jj_2_25(int xla) {
/* 1512 */     jj_la = xla;
/* 1513 */     jj_lastpos = jj_scanpos = token;
/*      */     try {
/* 1515 */       return !jj_3_25();
/* 1516 */     } catch (LookaheadSuccess ls) {
/* 1517 */       return true;
/*      */     } finally {
/* 1519 */       jj_save(24, xla);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static final boolean jj_3_20() {
/* 1524 */     if (jj_scan_token(5)) return true; 
/* 1525 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jj_3_5() {
/* 1529 */     if (jj_3R_8()) return true; 
/* 1530 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jj_3_9() {
/* 1534 */     if (jj_3R_12()) return true; 
/* 1535 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jj_3_23() {
/* 1539 */     if (jj_scan_token(69)) return true; 
/* 1540 */     if (jj_scan_token(6)) return true; 
/* 1541 */     if (jj_scan_token(11)) return true; 
/* 1542 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jj_3_13() {
/* 1546 */     if (jj_3R_16()) return true; 
/* 1547 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jj_3_19() {
/* 1551 */     if (jj_scan_token(6)) return true; 
/* 1552 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jj_3R_14() {
/* 1556 */     if (jj_scan_token(68)) return true; 
/* 1557 */     if (jj_scan_token(5)) return true; 
/* 1558 */     if (jj_scan_token(29)) return true; 
/* 1559 */     if (jj_scan_token(6)) return true; 
/* 1560 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jj_3_17() {
/* 1564 */     if (jj_3R_20()) return true; 
/* 1565 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jj_3_4() {
/* 1569 */     if (jj_3R_7()) return true; 
/* 1570 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jj_3R_10() {
/* 1574 */     if (jj_scan_token(56)) return true; 
/* 1575 */     if (jj_scan_token(5)) return true; 
/* 1576 */     if (jj_scan_token(57)) return true; 
/* 1577 */     if (jj_scan_token(6)) return true; 
/* 1578 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jj_3R_16() {
/* 1582 */     if (jj_scan_token(74)) return true; 
/* 1583 */     if (jj_scan_token(5)) return true; 
/* 1584 */     if (jj_scan_token(57)) return true; 
/* 1585 */     if (jj_scan_token(7)) return true; 
/* 1586 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jj_3R_5() {
/* 1590 */     if (jj_scan_token(28)) return true; 
/* 1591 */     if (jj_scan_token(5)) return true; 
/* 1592 */     if (jj_scan_token(29)) return true; 
/* 1593 */     if (jj_scan_token(6)) return true; 
/* 1594 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jj_3_12() {
/* 1598 */     if (jj_3R_15()) return true; 
/* 1599 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jj_3_8() {
/* 1603 */     if (jj_3R_11()) return true; 
/* 1604 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jj_3_18() {
/* 1608 */     if (jj_scan_token(8)) return true; 
/* 1609 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jj_3_16() {
/* 1613 */     if (jj_3R_19()) return true; 
/* 1614 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jj_3R_18() {
/* 1618 */     if (jj_scan_token(77)) return true; 
/* 1619 */     if (jj_scan_token(5)) return true; 
/* 1620 */     if (jj_scan_token(29)) return true; 
/* 1621 */     if (jj_scan_token(6)) return true; 
/* 1622 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jj_3_25() {
/* 1626 */     if (jj_scan_token(6)) return true; 
/* 1627 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jj_3R_12() {
/* 1631 */     if (jj_scan_token(54)) return true; 
/* 1632 */     if (jj_scan_token(5)) return true; 
/* 1633 */     if (jj_scan_token(52)) return true; 
/* 1634 */     if (jj_scan_token(6)) return true; 
/* 1635 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jj_3_3() {
/* 1639 */     if (jj_3R_6()) return true; 
/* 1640 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jj_3_22() {
/* 1644 */     if (jj_scan_token(38)) return true; 
/* 1645 */     if (jj_scan_token(5)) return true; 
/* 1646 */     if (jj_scan_token(11)) return true; 
/* 1647 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jj_3_11() {
/* 1651 */     if (jj_3R_14()) return true; 
/* 1652 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jj_3R_13() {
/* 1656 */     if (jj_scan_token(66)) return true; 
/* 1657 */     if (jj_scan_token(5)) return true; 
/* 1658 */     if (jj_scan_token(29)) return true; 
/* 1659 */     if (jj_scan_token(6)) return true; 
/* 1660 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jj_3_21() {
/* 1664 */     if (jj_scan_token(37)) return true; 
/* 1665 */     if (jj_scan_token(6)) return true; 
/* 1666 */     if (jj_scan_token(11)) return true; 
/* 1667 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jj_3R_15() {
/* 1671 */     if (jj_scan_token(71)) return true; 
/* 1672 */     if (jj_scan_token(5)) return true; 
/* 1673 */     if (jj_scan_token(72)) return true; 
/* 1674 */     if (jj_scan_token(7)) return true; 
/* 1675 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jj_3R_8() {
/* 1679 */     if (jj_scan_token(35)) return true; 
/* 1680 */     if (jj_scan_token(5)) return true; 
/* 1681 */     if (jj_scan_token(29)) return true;
/*      */     
/* 1683 */     xsp = jj_scanpos;
/* 1684 */     if (jj_3_18()) {
/* 1685 */       jj_scanpos = xsp;
/* 1686 */       if (jj_3_19()) {
/* 1687 */         jj_scanpos = xsp;
/* 1688 */         if (jj_3_20()) return true; 
/*      */       } 
/*      */     } 
/* 1691 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jj_3_24() {
/* 1695 */     if (jj_scan_token(7)) return true; 
/* 1696 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jj_3_15() {
/* 1700 */     if (jj_3R_18()) return true; 
/* 1701 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jj_3_7() {
/* 1705 */     if (jj_3R_10()) return true; 
/* 1706 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   private static final boolean jj_3_1() {
/* 1711 */     xsp = jj_scanpos;
/* 1712 */     if (jj_3_2()) {
/* 1713 */       jj_scanpos = xsp;
/* 1714 */       if (jj_3_3()) {
/* 1715 */         jj_scanpos = xsp;
/* 1716 */         if (jj_3_4()) {
/* 1717 */           jj_scanpos = xsp;
/* 1718 */           if (jj_3_5()) {
/* 1719 */             jj_scanpos = xsp;
/* 1720 */             if (jj_3_6()) {
/* 1721 */               jj_scanpos = xsp;
/* 1722 */               if (jj_3_7()) {
/* 1723 */                 jj_scanpos = xsp;
/* 1724 */                 if (jj_3_8()) {
/* 1725 */                   jj_scanpos = xsp;
/* 1726 */                   if (jj_3_9()) {
/* 1727 */                     jj_scanpos = xsp;
/* 1728 */                     if (jj_3_10()) {
/* 1729 */                       jj_scanpos = xsp;
/* 1730 */                       if (jj_3_11()) {
/* 1731 */                         jj_scanpos = xsp;
/* 1732 */                         if (jj_3_12()) {
/* 1733 */                           jj_scanpos = xsp;
/* 1734 */                           if (jj_3_13()) {
/* 1735 */                             jj_scanpos = xsp;
/* 1736 */                             if (jj_3_14()) {
/* 1737 */                               jj_scanpos = xsp;
/* 1738 */                               if (jj_3_15()) {
/* 1739 */                                 jj_scanpos = xsp;
/* 1740 */                                 if (jj_3_16()) {
/* 1741 */                                   jj_scanpos = xsp;
/* 1742 */                                   if (jj_3_17()) return true; 
/*      */                                 } 
/*      */                               } 
/*      */                             } 
/*      */                           } 
/*      */                         } 
/*      */                       } 
/*      */                     } 
/*      */                   } 
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1758 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jj_3_2() {
/* 1762 */     if (jj_3R_5()) return true; 
/* 1763 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jj_3R_20() {
/* 1767 */     if (jj_scan_token(82)) return true; 
/* 1768 */     if (jj_scan_token(5)) return true; 
/* 1769 */     if (jj_scan_token(33)) return true; 
/* 1770 */     if (jj_scan_token(7)) return true; 
/* 1771 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jj_3_10() {
/* 1775 */     if (jj_3R_13()) return true; 
/* 1776 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jj_3R_7() {
/* 1780 */     if (jj_scan_token(32)) return true; 
/* 1781 */     if (jj_scan_token(5)) return true; 
/* 1782 */     if (jj_scan_token(29)) return true; 
/* 1783 */     if (jj_scan_token(6)) return true; 
/* 1784 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jj_3R_11() {
/* 1788 */     if (jj_scan_token(51)) return true; 
/* 1789 */     if (jj_scan_token(5)) return true; 
/* 1790 */     if (jj_scan_token(52)) return true; 
/* 1791 */     if (jj_scan_token(6)) return true; 
/* 1792 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jj_3_6() {
/* 1796 */     if (jj_3R_9()) return true; 
/* 1797 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jj_3_14() {
/* 1801 */     if (jj_3R_17()) return true; 
/* 1802 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jj_3R_19() {
/* 1806 */     if (jj_scan_token(79)) return true; 
/* 1807 */     if (jj_scan_token(5)) return true; 
/* 1808 */     if (jj_scan_token(33)) return true;
/*      */     
/* 1810 */     xsp = jj_scanpos;
/* 1811 */     if (jj_3_24()) {
/* 1812 */       jj_scanpos = xsp;
/* 1813 */       if (jj_3_25()) return true; 
/*      */     } 
/* 1815 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jj_3R_6() {
/* 1819 */     if (jj_scan_token(31)) return true; 
/* 1820 */     if (jj_scan_token(5)) return true; 
/* 1821 */     if (jj_scan_token(29)) return true; 
/* 1822 */     if (jj_scan_token(6)) return true; 
/* 1823 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jj_3R_17() {
/* 1827 */     if (jj_scan_token(75)) return true; 
/* 1828 */     if (jj_scan_token(5)) return true; 
/* 1829 */     if (jj_scan_token(29)) return true; 
/* 1830 */     if (jj_scan_token(6)) return true; 
/* 1831 */     return false;
/*      */   }
/*      */   
/*      */   private static final boolean jj_3R_9() {
/* 1835 */     if (jj_scan_token(49)) return true; 
/* 1836 */     if (jj_scan_token(5)) return true; 
/* 1837 */     if (jj_scan_token(29)) return true; 
/* 1838 */     if (jj_scan_token(6)) return true; 
/* 1839 */     return false;
/*      */   }
/*      */   private static boolean jj_initialized_once = false; public static CDIFParserTokenManager token_source;
/*      */   static SimpleCharStream jj_input_stream;
/*      */   public static Token token;
/*      */   public static Token jj_nt;
/*      */   private static int jj_ntk;
/*      */   private static Token jj_scanpos;
/*      */   private static Token jj_lastpos;
/*      */   private static int jj_la;
/*      */   public static boolean lookingAhead = false;
/*      */   private static boolean jj_semLA;
/*      */   private static int jj_gen;
/* 1852 */   private static final int[] jj_la1 = new int[0]; private static int[] jj_la1_0; private static int[] jj_la1_1; private static int[] jj_la1_2; private static final JJCalls[] jj_2_rtns; private static boolean jj_rescan; private static int jj_gc; private static final LookaheadSuccess jj_ls; private static Vector jj_expentries;
/*      */   private static int[] jj_expentry;
/*      */   private static int jj_kind;
/*      */   private static int[] jj_lasttokens;
/*      */   private static int jj_endpos;
/*      */   
/* 1858 */   static  { jj_la1_0();
/* 1859 */     jj_la1_1();
/* 1860 */     jj_la1_2();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1875 */     jj_2_rtns = new JJCalls[25];
/* 1876 */     jj_rescan = false;
/* 1877 */     jj_gc = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1987 */     jj_ls = new LookaheadSuccess(null);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2042 */     jj_expentries = new Vector();
/*      */     
/* 2044 */     jj_kind = -1;
/* 2045 */     jj_lasttokens = new int[100]; }
/*      */   private static void jj_la1_0() { jj_la1_0 = new int[0]; }
/*      */   private static void jj_la1_1() { jj_la1_1 = new int[0]; }
/*      */   private static void jj_la1_2() { jj_la1_2 = new int[0]; }
/* 2049 */   public CDIFParser(InputStream stream) { if (jj_initialized_once) { System.out.println("ERROR: Second call to constructor of static parser.  You must"); System.out.println("       either use ReInit() or set the JavaCC option STATIC to false"); System.out.println("       during parser generation."); throw new Error(); }  jj_initialized_once = true; jj_input_stream = new SimpleCharStream(stream, 1, 1); token_source = new CDIFParserTokenManager(jj_input_stream); token = new Token(); jj_ntk = -1; jj_gen = 0; for (int i = 0; i < 0; ) { jj_la1[i] = -1; i++; }  for (int i = 0; i < jj_2_rtns.length; ) { jj_2_rtns[i] = new JJCalls(); i++; }  } public static void ReInit(InputStream stream) { jj_input_stream.ReInit(stream, 1, 1); CDIFParserTokenManager.ReInit(jj_input_stream); token = new Token(); jj_ntk = -1; jj_gen = 0; for (int i = 0; i < 0; ) { jj_la1[i] = -1; i++; }  for (int i = 0; i < jj_2_rtns.length; ) { jj_2_rtns[i] = new JJCalls(); i++; }  } public CDIFParser(Reader stream) { if (jj_initialized_once) { System.out.println("ERROR: Second call to constructor of static parser.  You must"); System.out.println("       either use ReInit() or set the JavaCC option STATIC to false"); System.out.println("       during parser generation."); throw new Error(); }  jj_initialized_once = true; jj_input_stream = new SimpleCharStream(stream, 1, 1); token_source = new CDIFParserTokenManager(jj_input_stream); token = new Token(); jj_ntk = -1; jj_gen = 0; for (int i = 0; i < 0; ) { jj_la1[i] = -1; i++; }  for (int i = 0; i < jj_2_rtns.length; ) { jj_2_rtns[i] = new JJCalls(); i++; }  } public static void ReInit(Reader stream) { jj_input_stream.ReInit(stream, 1, 1); CDIFParserTokenManager.ReInit(jj_input_stream); token = new Token(); jj_ntk = -1; jj_gen = 0; for (int i = 0; i < 0; ) { jj_la1[i] = -1; i++; }  for (int i = 0; i < jj_2_rtns.length; ) { jj_2_rtns[i] = new JJCalls(); i++; }  } private static void jj_add_error_token(int kind, int pos) { if (pos >= 100)
/* 2050 */       return;  if (pos == jj_endpos + 1)
/* 2051 */     { jj_lasttokens[jj_endpos++] = kind; }
/* 2052 */     else if (jj_endpos != 0)
/* 2053 */     { jj_expentry = new int[jj_endpos];
/* 2054 */       for (int i = 0; i < jj_endpos; i++) {
/* 2055 */         jj_expentry[i] = jj_lasttokens[i];
/*      */       }
/* 2057 */       boolean exists = false;
/* 2058 */       for (Enumeration e = jj_expentries.elements(); e.hasMoreElements(); ) {
/* 2059 */         int[] oldentry = (int[])e.nextElement();
/* 2060 */         if (oldentry.length == jj_expentry.length) {
/* 2061 */           exists = true;
/* 2062 */           for (int i = 0; i < jj_expentry.length; i++) {
/* 2063 */             if (oldentry[i] != jj_expentry[i]) {
/* 2064 */               exists = false;
/*      */               break;
/*      */             } 
/*      */           } 
/* 2068 */           if (exists)
/*      */             break; 
/*      */         } 
/* 2071 */       }  if (!exists) jj_expentries.addElement(jj_expentry); 
/* 2072 */       if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;  }  }
/*      */   public CDIFParser(CDIFParserTokenManager tm) { if (jj_initialized_once) { System.out.println("ERROR: Second call to constructor of static parser.  You must"); System.out.println("       either use ReInit() or set the JavaCC option STATIC to false"); System.out.println("       during parser generation."); throw new Error(); }  jj_initialized_once = true; token_source = tm; token = new Token(); jj_ntk = -1; jj_gen = 0; for (int i = 0; i < 0; ) { jj_la1[i] = -1; i++; }  for (int i = 0; i < jj_2_rtns.length; ) { jj_2_rtns[i] = new JJCalls(); i++; }  }
/*      */   public void ReInit(CDIFParserTokenManager tm) { token_source = tm; token = new Token(); jj_ntk = -1; jj_gen = 0; for (int i = 0; i < 0; ) { jj_la1[i] = -1; i++; }  for (int i = 0; i < jj_2_rtns.length; ) { jj_2_rtns[i] = new JJCalls(); i++; }  }
/*      */   private static final Token jj_consume_token(int kind) throws ParseException { Token oldToken; if ((oldToken = token).next != null) { token = token.next; } else { token = token.next = CDIFParserTokenManager.getNextToken(); }  jj_ntk = -1; if (token.kind == kind) { jj_gen++; if (++jj_gc > 100) { jj_gc = 0; for (int i = 0; i < jj_2_rtns.length; i++) { JJCalls c = jj_2_rtns[i]; while (c != null) { if (c.gen < jj_gen) c.first = null;  c = c.next; }  }  }  return token; }  token = oldToken; jj_kind = kind; throw generateParseException(); }
/*      */   private static final class LookaheadSuccess extends Error {
/* 2077 */     private LookaheadSuccess() {} } private static final boolean jj_scan_token(int kind) { if (jj_scanpos == jj_lastpos) { jj_la--; if (jj_scanpos.next == null) { jj_lastpos = jj_scanpos = jj_scanpos.next = CDIFParserTokenManager.getNextToken(); } else { jj_lastpos = jj_scanpos = jj_scanpos.next; }  } else { jj_scanpos = jj_scanpos.next; }  if (jj_rescan) { int i = 0; Token tok = token; while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }  if (tok != null) jj_add_error_token(kind, i);  }  if (jj_scanpos.kind != kind) return true;  if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;  return false; } public static final Token getNextToken() { if (token.next != null) { token = token.next; } else { token = token.next = CDIFParserTokenManager.getNextToken(); }  jj_ntk = -1; jj_gen++; return token; } public static final Token getToken(int index) throws ParseException { Token t = lookingAhead ? jj_scanpos : token; for (int i = 0; i < index; i++) { if (t.next != null) { t = t.next; } else { t = t.next = CDIFParserTokenManager.getNextToken(); }  }  return t; } private static final int jj_ntk() { if ((jj_nt = token.next) == null) return jj_ntk = (token.next = CDIFParserTokenManager.getNextToken()).kind;  return jj_ntk = jj_nt.kind; } public static ParseException generateParseException() { jj_expentries.removeAllElements();
/* 2078 */     la1tokens = new boolean[85];
/* 2079 */     for (int i = 0; i < 85; i++) {
/* 2080 */       la1tokens[i] = false;
/*      */     }
/* 2082 */     if (jj_kind >= 0) {
/* 2083 */       la1tokens[jj_kind] = true;
/* 2084 */       jj_kind = -1;
/*      */     } 
/* 2086 */     for (int i = 0; i < 0; i++) {
/* 2087 */       if (jj_la1[i] == jj_gen) {
/* 2088 */         for (int j = 0; j < 32; j++) {
/* 2089 */           if ((jj_la1_0[i] & 1 << j) != 0) {
/* 2090 */             la1tokens[j] = true;
/*      */           }
/* 2092 */           if ((jj_la1_1[i] & 1 << j) != 0) {
/* 2093 */             la1tokens[32 + j] = true;
/*      */           }
/* 2095 */           if ((jj_la1_2[i] & 1 << j) != 0) {
/* 2096 */             la1tokens[64 + j] = true;
/*      */           }
/*      */         } 
/*      */       }
/*      */     } 
/* 2101 */     for (int i = 0; i < 85; i++) {
/* 2102 */       if (la1tokens[i]) {
/* 2103 */         jj_expentry = new int[1];
/* 2104 */         jj_expentry[0] = i;
/* 2105 */         jj_expentries.addElement(jj_expentry);
/*      */       } 
/*      */     } 
/* 2108 */     jj_endpos = 0;
/* 2109 */     jj_rescan_token();
/* 2110 */     jj_add_error_token(0, 0);
/* 2111 */     int[][] exptokseq = new int[jj_expentries.size()][];
/* 2112 */     for (int i = 0; i < jj_expentries.size(); i++) {
/* 2113 */       exptokseq[i] = (int[])jj_expentries.elementAt(i);
/*      */     }
/* 2115 */     return new ParseException(token, exptokseq, tokenImage); }
/*      */ 
/*      */ 
/*      */   
/*      */   public static final void enable_tracing() {}
/*      */ 
/*      */   
/*      */   public static final void disable_tracing() {}
/*      */   
/*      */   private static final void jj_rescan_token() {
/* 2125 */     jj_rescan = true;
/* 2126 */     for (i = 0; i < 25; i++) {
/* 2127 */       JJCalls p = jj_2_rtns[i];
/*      */       do {
/* 2129 */         if (p.gen > jj_gen) {
/* 2130 */           jj_la = p.arg;
/* 2131 */           jj_lastpos = jj_scanpos = p.first;
/* 2132 */           switch (i) {
/*      */             case 0:
/* 2134 */               jj_3_1();
/*      */               break;
/*      */             case 1:
/* 2137 */               jj_3_2();
/*      */               break;
/*      */             case 2:
/* 2140 */               jj_3_3();
/*      */               break;
/*      */             case 3:
/* 2143 */               jj_3_4();
/*      */               break;
/*      */             case 4:
/* 2146 */               jj_3_5();
/*      */               break;
/*      */             case 5:
/* 2149 */               jj_3_6();
/*      */               break;
/*      */             case 6:
/* 2152 */               jj_3_7();
/*      */               break;
/*      */             case 7:
/* 2155 */               jj_3_8();
/*      */               break;
/*      */             case 8:
/* 2158 */               jj_3_9();
/*      */               break;
/*      */             case 9:
/* 2161 */               jj_3_10();
/*      */               break;
/*      */             case 10:
/* 2164 */               jj_3_11();
/*      */               break;
/*      */             case 11:
/* 2167 */               jj_3_12();
/*      */               break;
/*      */             case 12:
/* 2170 */               jj_3_13();
/*      */               break;
/*      */             case 13:
/* 2173 */               jj_3_14();
/*      */               break;
/*      */             case 14:
/* 2176 */               jj_3_15();
/*      */               break;
/*      */             case 15:
/* 2179 */               jj_3_16();
/*      */               break;
/*      */             case 16:
/* 2182 */               jj_3_17();
/*      */               break;
/*      */             case 17:
/* 2185 */               jj_3_18();
/*      */               break;
/*      */             case 18:
/* 2188 */               jj_3_19();
/*      */               break;
/*      */             case 19:
/* 2191 */               jj_3_20();
/*      */               break;
/*      */             case 20:
/* 2194 */               jj_3_21();
/*      */               break;
/*      */             case 21:
/* 2197 */               jj_3_22();
/*      */               break;
/*      */             case 22:
/* 2200 */               jj_3_23();
/*      */               break;
/*      */             case 23:
/* 2203 */               jj_3_24();
/*      */               break;
/*      */             case 24:
/* 2206 */               jj_3_25();
/*      */               break;
/*      */           } 
/*      */         } 
/* 2210 */         p = p.next;
/* 2211 */       } while (p != null);
/*      */     } 
/* 2213 */     jj_rescan = false;
/*      */   }
/*      */   
/*      */   private static final void jj_save(int index, int xla) {
/* 2217 */     JJCalls p = jj_2_rtns[index];
/* 2218 */     while (p.gen > jj_gen) {
/* 2219 */       if (p.next == null) {
/* 2220 */         p = p.next = new JJCalls();
/*      */         break;
/*      */       } 
/* 2223 */       p = p.next;
/*      */     } 
/* 2225 */     p.gen = jj_gen + xla - jj_la;
/* 2226 */     p.first = token;
/* 2227 */     p.arg = xla;
/*      */   }
/*      */   
/*      */   static final class JJCalls {
/*      */     int gen;
/*      */     Token first;
/*      */     int arg;
/*      */     JJCalls next;
/*      */   } }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\cdif\CDIFParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */