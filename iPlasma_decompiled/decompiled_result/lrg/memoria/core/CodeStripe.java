/*      */ package lrg.memoria.core;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
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
/*      */ public class CodeStripe
/*      */   extends ModelElement
/*      */   implements Scopable, Scope
/*      */ {
/*   55 */   private int linesOfComment = 0;
/*   56 */   private int numberOfStatements = 0;
/*   57 */   private int bodyExits = 0;
/*   58 */   private int atomicCyclo = 0;
/*      */ 
/*      */   
/*   61 */   private String signature = "[empty_stripe]";
/*   62 */   private int access = 3;
/*   63 */   private Location contentLocation = Location.getUnknownLocation();
/*   64 */   private Location location = Location.getUnknownLocation();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CodeStripe(Scope scope) {
/*   70 */     this();
/*   71 */     if (!(scope instanceof Body))
/*   72 */       scope.addScopedElement(this); 
/*   73 */     this.scope = scope;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*   78 */   private ModelElementList<Access> accesses = new ModelElementList();
/*   79 */   private ModelElementList<Call> calls = new ModelElementList();
/*   80 */   private ModelElementList<CodeStripe> stripes = new ModelElementList();
/*   81 */   private ModelElementList<LocalVariable> localVariables = new ModelElementList();
/*   82 */   private ModelElementList<Type> innerTypes = new ModelElementList();
/*   83 */   private String[] source = null;
/*   84 */   private Scope scope = null; public static final int NONSTATIC_STRIPE = 0; public static final int STATIC_CONTENT = 1; public static final int STATIC_SCOPE = 2; public static final int STATIC_STRIPE = 3; private static final String EMPTY_NAME = "[empty_stripe]"; public static final String TEMP = "[temp_code]"; public static final String NEW = "[new_stmnt]"; public static final String STATEMENT_BLOCK = "[stmnt_block]"; public static final String SWITCH = "[switch_code]";
/*      */   public static final String CASE = "[case_code]";
/*      */   
/*      */   public void addScopedElement(Scopable element) {
/*   88 */     if (element instanceof CodeStripe) {
/*   89 */       this.stripes.add((CodeStripe)element);
/*      */     }
/*   91 */     if (element instanceof Call) {
/*   92 */       this.calls.add((Call)element);
/*      */     }
/*   94 */     if (element instanceof Access) {
/*   95 */       this.accesses.add((Access)element);
/*      */     }
/*   97 */     if (element instanceof LocalVariable) {
/*   98 */       this.localVariables.add((LocalVariable)element);
/*      */     }
/*  100 */     if (element instanceof Type)
/*  101 */       this.innerTypes.add((Type)element); 
/*      */   } public static final String DEFAULT = "[default_case_code]"; public static final String FOR = "[for_code]"; public static final String WHILE = "[while_code]"; public static final String DO = "[do_while_code]"; public static final String IF = "[if_code]"; public static final String ELSE = "[else_code]"; public static final String TRY = "[try_code]"; public static final String CATCH = "[catch_code]"; public static final String FINALLY = "[finally_code]";
/*      */   private CodeStripe() {}
/*      */   public ModelElementList getScopedElements() {
/*  105 */     ModelElementList scoped = new ModelElementList();
/*  106 */     scoped.addAll(this.stripes);
/*  107 */     scoped.addAll(this.localVariables);
/*  108 */     scoped.addAll(this.innerTypes);
/*  109 */     return scoped;
/*      */   }
/*      */ 
/*      */   
/*  113 */   public ModelElementList<CodeStripe> getScopedStripes() { return this.stripes; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  121 */   public ModelElementList<Call> getCallList() { return this.calls; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  128 */   public void addCall(Call c) { this.calls.add(c); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  136 */   public ModelElementList<Access> getAccessList() { return this.accesses; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  144 */   public void addAccess(Access c) { this.accesses.add(c); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  151 */   public ModelElementList<LocalVariable> getLocalVarList() { return this.localVariables; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  158 */   public void addLocalVar(LocalVariable var) { this.localVariables.add(var); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  165 */   public ModelElementList<Type> getInnerTypesList() { return this.innerTypes; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  172 */   public void addInnerType(Type type) { this.innerTypes.add(type); }
/*      */ 
/*      */ 
/*      */   
/*  176 */   public Scope getScope() { return this.scope; }
/*      */ 
/*      */ 
/*      */   
/*  180 */   public void setScope(Scope scope) { this.scope = scope; }
/*      */ 
/*      */ 
/*      */   
/*  184 */   public void setSignature(String signature) { this.signature = signature; }
/*      */ 
/*      */ 
/*      */   
/*  188 */   public String getName() { return this.signature; }
/*      */ 
/*      */ 
/*      */   
/*  192 */   public boolean isEmpty() { return "[empty_stripe]".equals(this.signature); }
/*      */ 
/*      */ 
/*      */   
/*  196 */   public String getFullName() { return String.valueOf(getScope().getFullName()) + "." + this.signature; }
/*      */ 
/*      */ 
/*      */   
/*  200 */   public int getAccess() { return this.access; }
/*      */ 
/*      */ 
/*      */   
/*  204 */   public void setAccess(int access) { this.access = access; }
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
/*  215 */   public void setLocation(Location location) { this.location = location; }
/*      */ 
/*      */ 
/*      */   
/*  219 */   public Location getLocation() { return this.location; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  225 */   public Location getContentLocation() { return this.contentLocation; }
/*      */ 
/*      */ 
/*      */   
/*  229 */   public void setContentLocation(Location contentLocation) { this.contentLocation = contentLocation; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Location getRelPosOf(int absLine, int absCol) {
/*  240 */     if (getScope() instanceof CodeStripe) {
/*  241 */       Location l = ((CodeStripe)getScope()).getRelPosOf(absLine, absCol);
/*  242 */       return getLocation().relativeOf(l.getStartLine(), l.getStartChar());
/*      */     } 
/*  244 */     return getLocation().relativeOf(absLine, absCol);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Location getRelPosOf(Location abs) {
/*  253 */     if (getScope() instanceof CodeStripe) {
/*  254 */       Location l = ((CodeStripe)getScope()).getRelPosOf(abs);
/*  255 */       return getLocation().relativeOf(l);
/*      */     } 
/*  257 */     return getLocation().relativeOf(abs);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Location getAbsPosOf(Location rel) {
/*  266 */     if (getScope() instanceof CodeStripe) {
/*  267 */       Location l = ((CodeStripe)getScope()).getAbsPosOf(rel);
/*  268 */       return getLocation().absoluteOf(l);
/*      */     } 
/*  270 */     return getLocation().absoluteOf(rel);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*  275 */   public boolean hasStaticScope() { return ((this.access & 0x2) != 0); }
/*      */ 
/*      */ 
/*      */   
/*  279 */   public boolean hasStaticContent() { return ((this.access & true) != 0); }
/*      */ 
/*      */   
/*      */   private static boolean checkAndAdd(Location toCheck, Location objLoc, ArrayList<Location> list) {
/*  283 */     if (objLoc.startsAfter(toCheck.getStartLine(), toCheck.getStartChar()))
/*  284 */     { list.add(objLoc); }
/*  285 */     else if (objLoc.contains(toCheck)) { return true; }
/*  286 */      return false;
/*      */   }
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
/*      */   public int absorbStripe(int startLine, int startCol, CodeStripe what) {
/*  301 */     Location rel = this.location.relativeOf(startLine, startCol);
/*  302 */     rel.setEndLine(rel.getStartLine());
/*  303 */     rel.setEndChar(rel.getStartChar());
/*  304 */     if (!this.contentLocation.includes(rel)) return -1;
/*      */     
/*  306 */     ArrayList<Location> shifted = new ArrayList<Location>();
/*      */     
/*  308 */     for (Access a : this.accesses) {
/*  309 */       for (Location l : a.getInstanceList()) {
/*  310 */         if (checkAndAdd(rel, l, shifted)) return -1; 
/*      */       } 
/*  312 */     }  for (Call c : this.calls) {
/*  313 */       for (Location l : c.getInstanceList()) {
/*  314 */         if (checkAndAdd(rel, l, shifted)) return -1; 
/*      */       } 
/*  316 */     }  for (LocalVariable lv : this.localVariables) {
/*  317 */       if (checkAndAdd(rel, lv.getLocation(), shifted)) return -1;
/*      */     
/*      */     } 
/*  320 */     for (Type t : this.innerTypes) {
/*  321 */       if (checkAndAdd(rel, ((ExplicitlyDefinedType)t).getLocation(), shifted)) return -1;
/*      */     
/*      */     } 
/*      */     
/*  325 */     CodeStripe delegated = null;
/*      */     
/*  327 */     for (CodeStripe cs : this.stripes) {
/*  328 */       if (checkAndAdd(rel, cs.getLocation(), shifted)) {
/*  329 */         if (delegated != null) {
/*  330 */           throw new RuntimeException("Some CodeStripes are overlapping! The model might be corrupted!");
/*      */         }
/*  332 */         delegated = cs;
/*      */       } 
/*      */     } 
/*  335 */     if (delegated != null) {
/*  336 */       return delegated.absorbStripe(rel.getStartLine(), rel.getStartChar(), what);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  345 */     int lineCount = what.location.getEndLine() - what.location.getStartLine();
/*  346 */     Location.shiftLocations(lineCount + 2, shifted);
/*      */ 
/*      */     
/*  349 */     String[] newSource = new String[this.source.length + lineCount + 2];
/*      */     
/*      */     int i;
/*      */     
/*  353 */     for (i = 0; i < rel.getStartLine(); i++) {
/*  354 */       newSource[i] = this.source[i];
/*      */     }
/*  356 */     newSource[i] = blankChars(this.source[i], rel.getStartChar(), this.source[i].length() - 1, ' ');
/*  357 */     i++;
/*      */     
/*  359 */     for (; i <= rel.getStartLine() + 1 + lineCount; i++) {
/*  360 */       newSource[i] = what.source[i - rel.getStartLine() - 1];
/*      */     }
/*  362 */     newSource[i] = blankChars(this.source[i - lineCount - 2], 0, rel.getStartChar() - 1, ' ');
/*  363 */     i++;
/*      */     
/*  365 */     for (; i < newSource.length; i++) {
/*  366 */       newSource[i] = this.source[i - lineCount - 2];
/*      */     }
/*  368 */     this.source = newSource;
/*  369 */     rel.setStartLine(rel.getStartLine() + 1);
/*      */     
/*  371 */     for (Access a : what.accesses) {
/*  372 */       Access x = null;
/*  373 */       for (Access a1 : this.accesses) {
/*  374 */         if (a1.getVariable() == a.getVariable()) {
/*  375 */           x = a1;
/*      */           break;
/*      */         } 
/*      */       } 
/*  379 */       if (x == null) {
/*  380 */         x = new Access(a.getVariable(), this);
/*  381 */         this.accesses.add(x);
/*  382 */         a.getVariable().addAccess(x);
/*      */       } 
/*  384 */       for (Location l : a.getReadInstanceList())
/*  385 */         x.addInstance(rel.absoluteOf(l), 1); 
/*  386 */       for (Location l : a.getWriteInstanceList()) {
/*  387 */         x.addInstance(rel.absoluteOf(l), 2);
/*      */       }
/*      */     } 
/*  390 */     for (Call c : what.calls) {
/*  391 */       Call x = null;
/*  392 */       for (Call c1 : this.calls) {
/*  393 */         if (c1.getFunction() == c.getFunction()) {
/*  394 */           x = c1;
/*      */           break;
/*      */         } 
/*      */       } 
/*  398 */       if (x == null) {
/*  399 */         x = new Call(c.getFunction(), this);
/*  400 */         this.calls.add(x);
/*  401 */         c.getFunction().addCall(x);
/*      */       } 
/*  403 */       for (Location l : c.getInstanceList()) {
/*  404 */         x.addInstance(rel.absoluteOf(l));
/*      */       }
/*      */     } 
/*  407 */     for (LocalVariable lv : what.localVariables) {
/*  408 */       lv.setScope(this);
/*  409 */       lv.setLocation(rel.absoluteOf(lv.getLocation()));
/*  410 */       this.localVariables.add(lv);
/*      */     } 
/*  412 */     what.localVariables.clear();
/*      */     
/*  414 */     for (Type t : what.innerTypes) {
/*  415 */       ExplicitlyDefinedType edt = (ExplicitlyDefinedType)t;
/*  416 */       edt.setScope(this);
/*  417 */       edt.setLocation(rel.absoluteOf(edt.getLocation()));
/*  418 */       this.innerTypes.add(edt);
/*      */     } 
/*  420 */     what.innerTypes.clear();
/*      */     
/*  422 */     for (CodeStripe cs : what.stripes) {
/*  423 */       cs.setScope(this);
/*  424 */       cs.setLocation(rel.absoluteOf(cs.getLocation()));
/*  425 */       this.stripes.add(cs);
/*      */     } 
/*  427 */     what.stripes.clear();
/*      */ 
/*      */     
/*  430 */     return 0;
/*      */   }
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
/*      */   public CodeStripe extractStripe(Location range) {
/*  445 */     if (!range.intersects(this.location))
/*      */     {
/*  447 */       return new CodeStripe();
/*      */     }
/*      */     
/*  450 */     if (range.contains(this.location)) {
/*      */       
/*  452 */       if (hasStaticScope())
/*      */       {
/*  454 */         return null;
/*      */       }
/*      */ 
/*      */       
/*  458 */       return this;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  463 */     if (hasStaticContent())
/*      */     {
/*  465 */       return null;
/*      */     }
/*  467 */     Location relRange = this.location.relativeOf(range);
/*  468 */     if (!this.contentLocation.contains(relRange))
/*      */     {
/*  470 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  476 */     CodeStripe newStripe = new CodeStripe(Body.getUnkonwnBody());
/*      */     
/*  478 */     newStripe.setSignature("[temp_code]");
/*  479 */     newStripe.setAccess(0);
/*      */     
/*  481 */     CodeStripe delegatedExtraction = null;
/*  482 */     for (CodeStripe cs : this.stripes) {
/*  483 */       CodeStripe extracted = cs.extractStripe(relRange);
/*      */ 
/*      */       
/*  486 */       if (extracted == cs) {
/*  487 */         newStripe.addScopedElement(cs); continue;
/*  488 */       }  if (extracted == null)
/*      */       {
/*  490 */         return null; } 
/*  491 */       if (!extracted.isEmpty()) {
/*      */ 
/*      */ 
/*      */         
/*  495 */         if (delegatedExtraction != null) {
/*  496 */           throw new RuntimeException("Some CodeStripes are overlapping! The model might be corrupted!");
/*      */         }
/*  498 */         delegatedExtraction = extracted;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  506 */     if (delegatedExtraction != null) {
/*  507 */       if (newStripe.stripes.size() == 0) {
/*  508 */         return delegatedExtraction;
/*      */       }
/*  510 */       throw new RuntimeException("Some CodeStripes are overlapping! The model might be corrupted!");
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  515 */     for (Iterator<Type> it = this.innerTypes.iterator(); it.hasNext(); ) {
/*  516 */       Type t = (Type)it.next();
/*  517 */       if (!(t instanceof ExplicitlyDefinedType)) {
/*      */         break;
/*      */       }
/*  520 */       ExplicitlyDefinedType edt = (ExplicitlyDefinedType)t;
/*      */       
/*  522 */       if (relRange.contains(edt.getLocation())) {
/*  523 */         newStripe.addInnerType(edt); continue;
/*  524 */       }  if (relRange.intersects(edt.getLocation())) {
/*  525 */         return null;
/*      */       }
/*      */     } 
/*      */     
/*  529 */     for (Iterator<LocalVariable> it = this.localVariables.iterator(); it.hasNext(); ) {
/*  530 */       LocalVariable lv = (LocalVariable)it.next();
/*      */       
/*  532 */       if (relRange.contains(lv.getLocation())) {
/*  533 */         newStripe.addLocalVar(lv); continue;
/*  534 */       }  if (relRange.intersects(lv.getLocation())) {
/*  535 */         return null;
/*      */       }
/*      */     } 
/*      */     
/*  539 */     for (Call c : this.calls) {
/*  540 */       int r = Location.checkLocationsWithin(relRange, c.getInstanceList());
/*      */       
/*  542 */       if (r == -1) return null; 
/*  543 */       if (r == 1) newStripe.addCall(c);
/*      */     
/*      */     } 
/*      */     
/*  547 */     for (Access a : this.accesses) {
/*  548 */       int r = Location.checkLocationsWithin(relRange, a.getInstanceList());
/*      */       
/*  550 */       if (r == -1) return null; 
/*  551 */       if (r == 1) newStripe.addAccess(a);
/*      */     
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  557 */     newStripe.setLocation(relRange);
/*  558 */     newStripe.setContentLocation(relRange.relativeOf(relRange));
/*  559 */     newStripe.getSourceCodeFromStripe(this);
/*      */ 
/*      */     
/*  562 */     this.stripes.removeAll(newStripe.stripes);
/*  563 */     for (CodeStripe cs : newStripe.stripes) {
/*  564 */       cs.setScope(newStripe);
/*  565 */       cs.setLocation(relRange.relativeOf(cs.getLocation()));
/*      */     } 
/*      */ 
/*      */     
/*  569 */     this.innerTypes.removeAll(newStripe.innerTypes);
/*  570 */     for (Type t : newStripe.innerTypes) {
/*  571 */       ExplicitlyDefinedType edt = (ExplicitlyDefinedType)t;
/*  572 */       edt.setScope(newStripe);
/*  573 */       edt.setLocation(relRange.relativeOf(edt.getLocation()));
/*      */     } 
/*      */ 
/*      */     
/*  577 */     this.localVariables.removeAll(newStripe.localVariables);
/*  578 */     for (LocalVariable lv : newStripe.localVariables) {
/*  579 */       lv.setScope(newStripe);
/*  580 */       lv.setLocation(relRange.relativeOf(lv.getLocation()));
/*      */     } 
/*      */ 
/*      */     
/*  584 */     ModelElementList<Call> ec = newStripe.calls;
/*  585 */     newStripe.calls = new ModelElementList();
/*  586 */     for (Call c : ec) {
/*  587 */       Call newCall = new Call(c.getFunction(), newStripe);
/*      */       
/*  589 */       newCall.getInstanceList().addAll(c.extractInstancesWithin(relRange));
/*  590 */       newStripe.addCall(newCall);
/*  591 */       if (c.getInstanceList().size() == 0) this.calls.remove(c);
/*      */     
/*      */     } 
/*      */     
/*  595 */     ModelElementList<Access> ea = newStripe.accesses;
/*  596 */     newStripe.accesses = new ModelElementList();
/*  597 */     for (Access a : ea) {
/*  598 */       Access newAccess = new Access(a.getVariable(), newStripe);
/*      */       
/*  600 */       newAccess.getReadInstanceList().addAll(a.extractReadInstancesWithin(relRange));
/*  601 */       newAccess.getWriteInstanceList().addAll(a.extractWriteInstancesWithin(relRange));
/*      */       
/*  603 */       newStripe.addAccess(newAccess);
/*  604 */       if (a.getInstanceList().size() == 0) this.accesses.remove(a);
/*      */     
/*      */     } 
/*  607 */     return newStripe;
/*      */   }
/*      */   
/*      */   public Body getParentBody() {
/*  611 */     if (getScope() instanceof CodeStripe) {
/*  612 */       return ((CodeStripe)getScope()).getParentBody();
/*      */     }
/*  614 */     return (Body)getScope();
/*      */   }
/*      */   
/*      */   ModelElementList<Call> flattenCalls() {
/*  618 */     ModelElementList<Call> allCalls = new ModelElementList<Call>();
/*  619 */     allCalls.addAll(this.calls);
/*      */     
/*  621 */     for (CodeStripe cs : this.stripes) {
/*  622 */       allCalls.addAll(cs.flattenCalls());
/*      */     }
/*  624 */     return allCalls;
/*      */   }
/*      */   
/*      */   ModelElementList<Access> flattenAccesses() {
/*  628 */     ModelElementList<Access> allAccesses = new ModelElementList<Access>();
/*  629 */     allAccesses.addAll(this.accesses);
/*      */     
/*  631 */     for (CodeStripe cs : this.stripes) {
/*  632 */       allAccesses.addAll(cs.flattenAccesses());
/*      */     }
/*  634 */     return allAccesses;
/*      */   }
/*      */   
/*      */   ModelElementList<LocalVariable> flattenLocalVariables() {
/*  638 */     ModelElementList<LocalVariable> allLocalVariables = new ModelElementList<LocalVariable>();
/*  639 */     allLocalVariables.addAll(this.localVariables);
/*      */     
/*  641 */     for (CodeStripe cs : this.stripes) {
/*  642 */       allLocalVariables.addAll(cs.flattenLocalVariables());
/*      */     }
/*  644 */     return allLocalVariables;
/*      */   }
/*      */   
/*      */   ModelElementList<Type> flattenInnerTypes() {
/*  648 */     ModelElementList<Type> allInnerTypes = new ModelElementList<Type>();
/*  649 */     allInnerTypes.addAll(this.innerTypes);
/*      */     
/*  651 */     for (CodeStripe cs : this.stripes) {
/*  652 */       allInnerTypes.addAll(cs.flattenInnerTypes());
/*      */     }
/*  654 */     return allInnerTypes;
/*      */   }
/*      */   
/*      */   public void getSourceCodeFromStripe(CodeStripe parent) {
/*  658 */     int sl = this.location.getStartLine(), el = this.location.getEndLine();
/*      */ 
/*      */     
/*  661 */     int sc = this.location.getStartChar() - 1 + ((sl != 0) ? 0 : parent.getLocation().getStartChar());
/*  662 */     int ec = this.location.getEndChar() - 1 + ((el != 0) ? 0 : parent.getLocation().getEndChar());
/*      */     
/*  664 */     this.source = new String[el - sl + 1];
/*  665 */     if (parent.source.length <= el) {
/*  666 */       throw new IllegalArgumentException("Source code is too short for my specified position & size\nme: start@" + 
/*  667 */           sl + " end@" + el + " src: " + parent.source.length + " lines");
/*      */     }
/*  669 */     sc = Math.min(sc, parent.source[sl].length() - 1);
/*      */     
/*  671 */     if (sc >= 0) {
/*  672 */       int fle = (el != sl) ? (parent.source[sl].length() - 1) : ec;
/*  673 */       this.source[0] = blankChars(parent.source[sl], 0, sc - 1, ' ');
/*  674 */       parent.source[sl] = blankChars(parent.source[sl], sc, fle, ' ');
/*      */     } else {
/*  676 */       this.source[0] = "";
/*      */     } 
/*  678 */     for (int i = 1; i < el - sl; i++) {
/*  679 */       this.source[i] = parent.source[sl + i];
/*  680 */       parent.source[sl + i] = "";
/*      */     } 
/*      */ 
/*      */     
/*  684 */     if (ec >= 0) {
/*  685 */       String lastl = (el != sl) ? parent.source[el] : this.source[el - sl];
/*      */       
/*  687 */       this.source[el - sl] = blankChars(lastl, ec + 1, lastl.length() - 1, ' ');
/*  688 */       if (el != sl)
/*  689 */         parent.source[el] = blankChars(parent.source[el], 0, ec, ' '); 
/*      */     } else {
/*  691 */       this.source[el - sl] = "";
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setSourceCode(String sourceCode) {
/*  696 */     this.source = sourceCode.concat("").split("\n");
/*  697 */     if (this.source.length < this.location.getEndLine() - this.location.getStartLine() + 1) {
/*  698 */       throw new IllegalArgumentException("Source code is to short for my specified size\n" + 
/*  699 */           getFullName() + "\n" + 
/*  700 */           "me: " + (this.location.getEndLine() - this.location.getStartLine() + 1) + " src:" + this.source.length + "\n");
/*      */     }
/*      */   }
/*      */   
/*      */   public String getSourceCode() {
/*  705 */     String sourceCode = "";
/*  706 */     String[] lines = fill();
/*  707 */     for (int i = 0; i < lines.length; i++) {
/*  708 */       if (lines[i] != null)
/*  709 */         sourceCode = String.valueOf(sourceCode) + lines[i]; 
/*  710 */       sourceCode = String.valueOf(sourceCode) + "\n";
/*      */     } 
/*  712 */     return sourceCode;
/*      */   }
/*      */ 
/*      */   
/*      */   private String[] fill() {
/*  717 */     if (this.source == null) return new String[0];
/*      */     
/*  719 */     String[] src = new String[this.source.length];
/*  720 */     for (int i = 0; i < this.source.length; i++) {
/*  721 */       if (this.source[i] != null) {
/*  722 */         src[i] = new String(this.source[i]);
/*      */       } else {
/*  724 */         src[i] = null;
/*      */       } 
/*      */     } 
/*  727 */     for (CodeStripe cs : this.stripes) {
/*  728 */       String[] csrc = cs.fill();
/*  729 */       int sl = cs.getLocation().getStartLine(), el = cs.getLocation().getEndLine();
/*      */ 
/*      */       
/*  732 */       int sc = cs.getLocation().getStartChar() - 1 + ((sl != 0) ? 0 : getLocation().getStartChar());
/*  733 */       int ec = cs.getLocation().getEndChar() - 1 + ((el != 0) ? 0 : getLocation().getEndChar());
/*      */       
/*  735 */       src[sl] = 
/*      */         
/*  737 */         fillBlanks(src[sl], csrc[0], sc, (sl != el) ? (csrc[0].length() - 1) : ec);
/*      */       
/*  739 */       for (int i = 1; i < csrc.length - 1; i++) {
/*  740 */         src[sl + i] = csrc[i];
/*      */       }
/*  742 */       if (sl != el)
/*  743 */         src[sl + csrc.length - 1] = 
/*  744 */           fillBlanks(src[sl + csrc.length - 1], csrc[csrc.length - 1], 0, ec); 
/*      */     } 
/*  746 */     return src;
/*      */   }
/*      */   
/*      */   private static String blankChars(String s, int begin, int end, char c) {
/*  750 */     if (end < begin || end < 0 || begin < 0) return s; 
/*  751 */     String sb = (begin > 0) ? s.substring(0, begin) : "";
/*  752 */     String se = (end < s.length() - 1) ? s.substring(end + 1) : "";
/*  753 */     for (int i = begin; i <= end; ) { sb = String.valueOf(sb) + c; i++; }
/*  754 */      return String.valueOf(sb) + se;
/*      */   }
/*      */   
/*      */   private static String fillBlanks(String dest, String src, int begin, int end) {
/*  758 */     if (end < begin || end < 0 || begin < 0) return dest;
/*      */     
/*  760 */     String t = (begin > 0) ? ((begin < dest.length()) ? dest.substring(0, begin) : dest) : "";
/*      */     
/*  762 */     String t1 = (end < src.length() - 1) ? src.substring(begin, end + 1) : (
/*  763 */       (begin <= src.length() - 1) ? src.substring(begin) : "");
/*      */     
/*  765 */     String t2 = (end < dest.length() - 1) ? dest.substring(end + 1) : "";
/*      */     
/*  767 */     return String.valueOf(t) + t1 + t2;
/*      */   }
/*      */ 
/*      */   
/*  771 */   public void accept(ModelVisitor v) { v.visitCodeStripe(this); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int getAtomicCyclo() {
/*  780 */     if (("[case_code]".equals(this.signature) && this.access != 3) || 
/*  781 */       "[do_while_code]".equals(this.signature) || 
/*  782 */       "[for_code]".equals(this.signature) || 
/*  783 */       "[if_code]".equals(this.signature) || 
/*  784 */       "[while_code]".equals(this.signature))
/*      */     {
/*  786 */       return this.atomicCyclo + 1;
/*      */     }
/*  788 */     return this.atomicCyclo;
/*      */   }
/*      */ 
/*      */   
/*  792 */   public void addAtomicCyclo(int cn) { this.atomicCyclo += cn; }
/*      */ 
/*      */ 
/*      */   
/*  796 */   private int getAtomicExceptions() { return "[catch_code]".equals(this.signature) ? 1 : 0; }
/*      */ 
/*      */ 
/*      */   
/*      */   private int getAtomicNestingLevel() {
/*  801 */     if (("[case_code]".equals(this.signature) && this.access != 3) || 
/*  802 */       "[catch_code]".equals(this.signature) || 
/*  803 */       "[do_while_code]".equals(this.signature) || 
/*  804 */       "[for_code]".equals(this.signature) || 
/*  805 */       "[if_code]".equals(this.signature) || 
/*  806 */       "[switch_code]".equals(this.signature) || 
/*  807 */       "[try_code]".equals(this.signature) || 
/*  808 */       "[while_code]".equals(this.signature))
/*      */     {
/*  810 */       return 1;
/*      */     }
/*  812 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*  817 */   public void addBodyExitPoints(int ep) { this.bodyExits += ep; }
/*      */ 
/*      */ 
/*      */   
/*      */   private int getAtomicLoops() {
/*  822 */     if ("[do_while_code]".equals(this.signature) || 
/*  823 */       "[for_code]".equals(this.signature) || 
/*  824 */       "[while_code]".equals(this.signature))
/*      */     {
/*  826 */       return 1;
/*      */     }
/*  828 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   private int getAtomicDecisions() {
/*  833 */     if (("[case_code]".equals(this.signature) && this.access != 3) || 
/*  834 */       "[do_while_code]".equals(this.signature) || 
/*  835 */       "[for_code]".equals(this.signature) || 
/*  836 */       "[if_code]".equals(this.signature) || 
/*  837 */       "[while_code]".equals(this.signature))
/*      */     {
/*  839 */       return 1;
/*      */     }
/*  841 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*  845 */   public void addAtomicCommentLines(int n) { this.linesOfComment += n; }
/*      */ 
/*      */ 
/*      */   
/*  849 */   public void addAtomicStatements(int n) { this.numberOfStatements += n; }
/*      */ 
/*      */ 
/*      */   
/*      */   private int getAtomicStatements() {
/*  854 */     if (("[else_code]".equals(this.signature) && this.access != 3) || 
/*  855 */       "[do_while_code]".equals(this.signature) || 
/*  856 */       "[for_code]".equals(this.signature) || 
/*  857 */       "[if_code]".equals(this.signature) || 
/*  858 */       "[while_code]".equals(this.signature))
/*      */     {
/*  860 */       return this.numberOfStatements + 1;
/*      */     }
/*  862 */     return this.numberOfStatements;
/*      */   }
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
/*      */   public int getCyclomaticNumber() {
/*  877 */     int cyclo = getAtomicCyclo();
/*      */     
/*  879 */     for (CodeStripe cs : this.stripes) {
/*  880 */       cyclo += cs.getCyclomaticNumber();
/*      */     }
/*  882 */     return cyclo;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxNestingLevel() {
/*  889 */     int nl = getAtomicNestingLevel(), mnl = nl;
/*      */     
/*  891 */     for (CodeStripe cs : this.stripes) {
/*  892 */       int cnl = cs.getMaxNestingLevel() + nl;
/*  893 */       if (cnl > mnl) mnl = cnl;
/*      */     
/*      */     } 
/*  896 */     return mnl;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNumberOfExceptions() {
/*  905 */     int ne = getAtomicExceptions();
/*      */     
/*  907 */     for (CodeStripe cs : this.stripes) {
/*  908 */       ne += cs.getNumberOfExceptions();
/*      */     }
/*      */     
/*  911 */     return ne;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNumberOfExits() {
/*  918 */     int ne = this.bodyExits;
/*      */     
/*  920 */     for (CodeStripe cs : this.stripes) {
/*  921 */       ne += cs.getNumberOfExits();
/*      */     }
/*      */     
/*  924 */     return ne;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNumberOfDecisions() {
/*  931 */     int nd = getAtomicDecisions();
/*      */     
/*  933 */     for (CodeStripe cs : this.stripes) {
/*  934 */       nd += cs.getNumberOfDecisions();
/*      */     }
/*      */     
/*  937 */     return nd;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNumberOfLoops() {
/*  944 */     int nl = getAtomicLoops();
/*      */     
/*  946 */     for (CodeStripe cs : this.stripes) {
/*  947 */       nl += cs.getNumberOfLoops();
/*      */     }
/*      */     
/*  950 */     return nl;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  957 */   public int getNumberOfLines() { return getLocation().getEndLine() - getLocation().getStartLine() + 1; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNumberOfComments() {
/*  964 */     int nc = this.linesOfComment;
/*      */     
/*  966 */     for (CodeStripe cs : this.stripes) {
/*  967 */       nc += cs.getNumberOfComments();
/*      */     }
/*      */     
/*  970 */     return nc;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNumberOfStatements() {
/*  980 */     int ns = getAtomicStatements();
/*      */     
/*  982 */     for (CodeStripe cs : this.stripes) {
/*  983 */       ns += cs.getNumberOfStatements();
/*      */     }
/*      */     
/*  986 */     return ns;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   boolean restore() {
/*  992 */     if (super.restore()) {
/*  993 */       if (this.stripes != null)
/*  994 */         this.stripes.restore(); 
/*  995 */       if (this.accesses != null)
/*  996 */         this.accesses.restore(); 
/*  997 */       if (this.calls != null)
/*  998 */         this.calls.restore(); 
/*  999 */       if (this.innerTypes != null)
/* 1000 */         this.innerTypes.restore(); 
/* 1001 */       if (this.localVariables != null)
/* 1002 */         this.localVariables.restore(); 
/* 1003 */       return true;
/*      */     } 
/* 1005 */     return false;
/*      */   }
/*      */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\core\CodeStripe.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */