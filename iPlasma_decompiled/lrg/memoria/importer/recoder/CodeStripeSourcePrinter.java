/*      */ package lrg.memoria.importer.recoder;
/*      */ import recoder.java.Comment;
/*      */ import recoder.java.CompilationUnit;
/*      */ import recoder.java.ProgramElement;
/*      */ import recoder.java.SourceElement;
/*      */ import recoder.java.StatementBlock;
/*      */ import recoder.java.declaration.ClassDeclaration;
/*      */ import recoder.java.declaration.FieldDeclaration;
/*      */ import recoder.java.declaration.InterfaceDeclaration;
/*      */ import recoder.java.declaration.LocalVariableDeclaration;
/*      */ import recoder.java.declaration.MethodDeclaration;
/*      */ import recoder.java.declaration.VariableDeclaration;
/*      */ import recoder.java.expression.Assignment;
/*      */ import recoder.java.expression.Operator;
/*      */ import recoder.java.expression.operator.Conditional;
/*      */ import recoder.java.expression.operator.New;
/*      */ import recoder.java.expression.operator.NewArray;
/*      */ import recoder.java.expression.operator.TypeCast;
/*      */ import recoder.java.reference.ArrayReference;
/*      */ import recoder.java.reference.FieldReference;
/*      */ import recoder.java.reference.MethodReference;
/*      */ import recoder.java.reference.SuperConstructorReference;
/*      */ import recoder.java.reference.TypeReference;
/*      */ import recoder.java.statement.Assert;
/*      */ import recoder.java.statement.Case;
/*      */ import recoder.java.statement.Catch;
/*      */ import recoder.java.statement.Do;
/*      */ import recoder.java.statement.For;
/*      */ import recoder.java.statement.If;
/*      */ import recoder.java.statement.Switch;
/*      */ import recoder.java.statement.SynchronizedBlock;
/*      */ import recoder.java.statement.Try;
/*      */ import recoder.java.statement.While;
/*      */ 
/*      */ public class CodeStripeSourcePrinter extends SourceVisitor {
/*      */   public static CodeStripeSourcePrinter instance() {
/*   37 */     if (instance == null) instance = new CodeStripeSourcePrinter(null); 
/*   38 */     instance.reset();
/*   39 */     return instance;
/*      */   }
/*      */   
/*      */   private static CodeStripeSourcePrinter instance;
/*   43 */   private static char[] BLANKS = new char[128];
/*   44 */   private static char[] FEEDS = new char[8]; private Writer out; private int line; private int column; private String laterText;
/*      */   
/*      */   static  {
/*   47 */     for (i = 0; i < FEEDS.length; i++) {
/*   48 */       FEEDS[i] = '\n';
/*      */     }
/*   50 */     for (i = 0; i < BLANKS.length; i++) {
/*   51 */       BLANKS[i] = ' ';
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getSource(SourceElement e) {
/*   61 */     SourceElement.Position st = e.getStartPosition();
/*   62 */     SourceElement.Position ed = e.getEndPosition();
/*      */     
/*   64 */     e.accept(this);
/*   65 */     String r = this.out.toString();
/*      */     
/*   67 */     int sl = st.getLine();
/*   68 */     while (sl > 1) {
/*   69 */       r = r.substring(r.indexOf("\n") + "\n".length());
/*   70 */       sl--;
/*      */     } 
/*      */     
/*   73 */     int el = ed.getLine() - st.getLine() - (String.valueOf(r) + "\n").split("\n").length;
/*   74 */     while (el > 0) {
/*   75 */       r = String.valueOf(r) + "\n";
/*   76 */       el--;
/*      */     } 
/*      */     
/*   79 */     return r;
/*      */   }
/*      */   
/*      */   public void reset() {
/*   83 */     this.out = new StringWriter();
/*   84 */     this.line = 1;
/*   85 */     this.column = 1;
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setWriter(Writer out) { this.out = out; }
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
/*      */   public Writer getWriter() { return this.out; }
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
/*      */   protected static String encodeUnicodeChars(String str) {
/*      */     int len = str.length();
/*      */     StringBuffer buf = new StringBuffer(len + 4);
/*      */     for (int i = 0; i < len; i++) {
/*      */       char c = str.charAt(i);
/*      */       if (c >= 'Ā') {
/*      */         if (c < 'က') {
/*      */           buf.append("\\u0" + Integer.toString(c, 16));
/*      */         } else {
/*      */           buf.append("\\u" + Integer.toString(c, 16));
/*      */         } 
/*      */       } else {
/*      */         buf.append(c);
/*      */       } 
/*      */     } 
/*      */     return buf.toString();
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
/*      */ 
/*      */ 
/*      */   
/*      */   protected void print(int c) {
/*      */     if (this.laterText != null) {
/*      */       String later = this.laterText;
/*      */       this.laterText = null;
/*      */       print(later);
/*      */     } 
/*      */     if (c == 10) {
/*      */       this.column = 1;
/*      */       this.line++;
/*      */     } else {
/*      */       this.column++;
/*      */     } 
/*      */     try {
/*      */       this.out.write(c);
/*      */     } catch (IOException ioe) {
/*      */       throw new PrettyPrintingException(ioe);
/*      */     } 
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
/*      */ 
/*      */ 
/*      */   
/*      */   protected CodeStripeSourcePrinter(Writer out) {
/*  203 */     this.laterText = null; this.out = out;
/*      */   } protected void print(String str) { if (str.length() == 0) return;  if (this.laterText != null) { String later = this.laterText; this.laterText = null; print(later); }  int i = str.lastIndexOf('\n'); if (i >= 0) { this.column = str.length() - i + 1 + 1; do { this.line++; i = str.lastIndexOf('\n', i - 1); } while (i >= 0); } else { this.column += str.length(); }  try { this.out.write(str); } catch (IOException ioe) { throw new PrettyPrintingException(ioe); }  } private void print(char[] cbuf, int off, int len) { for (i = off; i < off + len; i++) { if (cbuf[i] == '\n') { this.line++; this.column = 1; } else { this.column++; }  }  try { this.out.write(cbuf, off, len); } catch (IOException i) { IOException ioe; throw new PrettyPrintingException(ioe); }  }
/*      */   private void printIndentation(int lf, int blanks) { if (lf > 0) do { int n = Math.min(lf, FEEDS.length); print(FEEDS, 0, n); lf -= n; } while (lf > 0);  while (blanks > 0) { int n = Math.min(blanks, BLANKS.length); print(BLANKS, 0, n); blanks -= n; }  }
/*  206 */   private void latePrint(String s) { if (this.laterText == null) {
/*  207 */       this.laterText = s;
/*      */     } else {
/*  209 */       this.laterText = String.valueOf(this.laterText) + s;
/*      */     }  }
/*      */   
/*      */   private void advanceTo(int line, int col) {
/*  213 */     int il = line - this.line, ic = 0, laterLen = 0;
/*  214 */     String later = "";
/*  215 */     if (this.laterText != null) {
/*  216 */       later = this.laterText;
/*  217 */       laterLen = later.length();
/*  218 */       this.laterText = null;
/*      */     } 
/*      */     
/*  221 */     if (il == 0) {
/*  222 */       ic = (col > this.column) ? (col - this.column) : 0;
/*  223 */       if (ic >= laterLen) {
/*  224 */         ic -= laterLen;
/*      */ 
/*      */       
/*      */       }
/*  228 */       else if (this.column < 255) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  235 */         System.out.println("Warning (CodeStripeSourcePrinter:215): Source reconstruction may be corrupted: current_file(" + this.line + "," + this.column + ").");
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  240 */       printIndentation(il, ic);
/*  241 */       print(later);
/*      */     } else {
/*  243 */       ic = col - 1;
/*  244 */       if (ic >= laterLen) {
/*  245 */         ic -= laterLen;
/*  246 */         printIndentation(il, ic);
/*  247 */         print(later);
/*      */       } else {
/*  249 */         printIndentation(il - 1, 0);
/*  250 */         print(later);
/*  251 */         if (il > 0) printIndentation(1, ic);
/*      */       
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*  257 */   private void advanceTo(SourceElement.Position p) { advanceTo(p.getLine(), p.getColumn()); }
/*      */ 
/*      */   
/*      */   private void printPreCommentsOf(ProgramElement x) {
/*  261 */     int s = (x.getComments() != null) ? x.getComments().size() : 0;
/*  262 */     for (int i = 0; i < s; i++) {
/*  263 */       Comment c = (Comment)x.getComments().get(i);
/*  264 */       if (c.isPrefixed()) {
/*  265 */         c.accept(this);
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   private void printPostCommentsOf(ProgramElement x) {
/*  271 */     int s = (x.getComments() != null) ? x.getComments().size() : 0;
/*  272 */     for (int i = 0; i < s; i++) {
/*  273 */       Comment c = (Comment)x.getComments().get(i);
/*  274 */       if (!c.isPrefixed()) {
/*  275 */         c.accept(this);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*  281 */   private void printElement(ProgramElement x) { x.accept(this); }
/*      */ 
/*      */   
/*      */   private void printElements(ASTList<ProgramElement> list, String separationSymbol) {
/*  285 */     int s = list.size();
/*  286 */     if (s == 0)
/*      */       return; 
/*  288 */     printElement((ProgramElement)list.get(0));
/*  289 */     for (int i = 1; i < s; i++) {
/*  290 */       print(separationSymbol);
/*  291 */       printElement((ProgramElement)list.get(i));
/*      */     } 
/*      */   }
/*      */   
/*      */   private void printElements(List<ProgramElement> list, String separationSymbol) {
/*  296 */     int s = list.size();
/*  297 */     if (s == 0)
/*      */       return; 
/*  299 */     printElement((ProgramElement)list.get(0));
/*  300 */     for (int i = 1; i < s; i++) {
/*  301 */       print(separationSymbol);
/*  302 */       printElement((ProgramElement)list.get(i));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void visitIdentifier(Identifier x) {
/*  308 */     printPreCommentsOf(x);
/*  309 */     advanceTo(x.getStartPosition());
/*  310 */     print(x.getText());
/*  311 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitIntLiteral(IntLiteral x) {
/*  315 */     printPreCommentsOf(x);
/*  316 */     advanceTo(x.getStartPosition());
/*  317 */     print(x.getValue());
/*  318 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitBooleanLiteral(BooleanLiteral x) {
/*  322 */     printPreCommentsOf(x);
/*  323 */     advanceTo(x.getStartPosition());
/*  324 */     print(x.getValue() ? "true" : "false");
/*  325 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitStringLiteral(StringLiteral x) {
/*  329 */     printPreCommentsOf(x);
/*  330 */     advanceTo(x.getStartPosition());
/*  331 */     print(encodeUnicodeChars(x.getValue()));
/*  332 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitNullLiteral(NullLiteral x) {
/*  336 */     printPreCommentsOf(x);
/*  337 */     advanceTo(x.getStartPosition());
/*  338 */     print("null");
/*  339 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitCharLiteral(CharLiteral x) {
/*  343 */     printPreCommentsOf(x);
/*  344 */     advanceTo(x.getStartPosition());
/*  345 */     print(encodeUnicodeChars(x.getValue()));
/*  346 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitDoubleLiteral(DoubleLiteral x) {
/*  350 */     printPreCommentsOf(x);
/*  351 */     advanceTo(x.getStartPosition());
/*  352 */     print(x.getValue());
/*  353 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitLongLiteral(LongLiteral x) {
/*  357 */     printPreCommentsOf(x);
/*  358 */     advanceTo(x.getStartPosition());
/*  359 */     print(x.getValue());
/*  360 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitFloatLiteral(FloatLiteral x) {
/*  364 */     printPreCommentsOf(x);
/*  365 */     advanceTo(x.getStartPosition());
/*  366 */     print(x.getValue());
/*  367 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitTypeReference(TypeReference x) {
/*  371 */     printPreCommentsOf(x);
/*  372 */     if (x.getReferencePrefix() != null) {
/*  373 */       printElement(x.getReferencePrefix());
/*  374 */       print(46);
/*      */     } 
/*  376 */     if (x.getIdentifier() != null) {
/*  377 */       printElement(x.getIdentifier());
/*      */     }
/*  379 */     for (int i = 0; i < x.getDimensions(); i++) {
/*  380 */       print("[]");
/*      */     }
/*  382 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitPackageReference(PackageReference x) {
/*  386 */     printPreCommentsOf(x);
/*  387 */     if (x.getReferencePrefix() != null) {
/*  388 */       printElement(x.getReferencePrefix());
/*  389 */       print(46);
/*      */     } 
/*  391 */     if (x.getIdentifier() != null) {
/*  392 */       printElement(x.getIdentifier());
/*      */     }
/*  394 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitArrayReference(ArrayReference x) {
/*  398 */     printPreCommentsOf(x);
/*  399 */     if (x.getReferencePrefix() != null) {
/*  400 */       printElement(x.getReferencePrefix());
/*      */     }
/*  402 */     if (x.getDimensionExpressions() != null) {
/*  403 */       int s = x.getDimensionExpressions().size();
/*  404 */       for (int i = 0; i < s; i++) {
/*  405 */         print(91);
/*  406 */         printElement((ProgramElement)x.getDimensionExpressions().get(i));
/*  407 */         print(93);
/*      */       } 
/*      */     } 
/*  410 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitFieldReference(FieldReference x) {
/*  414 */     printPreCommentsOf(x);
/*  415 */     if (x.getReferencePrefix() != null) {
/*  416 */       printElement(x.getReferencePrefix());
/*  417 */       print(46);
/*      */     } 
/*  419 */     if (x.getIdentifier() != null) {
/*  420 */       printElement(x.getIdentifier());
/*      */     }
/*  422 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitVariableReference(VariableReference x) {
/*  426 */     printPreCommentsOf(x);
/*  427 */     if (x.getIdentifier() != null) {
/*  428 */       printElement(x.getIdentifier());
/*      */     }
/*  430 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitMetaClassReference(MetaClassReference x) {
/*  434 */     printPreCommentsOf(x);
/*  435 */     if (x.getTypeReference() != null) {
/*  436 */       printElement(x.getTypeReference());
/*  437 */       print(46);
/*      */     } 
/*  439 */     advanceTo(x.getStartPosition());
/*  440 */     print("class");
/*  441 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitMethodReference(MethodReference x) {
/*  445 */     printPreCommentsOf(x);
/*  446 */     if (x.getReferencePrefix() != null) {
/*  447 */       printElement(x.getReferencePrefix());
/*  448 */       print(46);
/*      */     } 
/*  450 */     if (x.getIdentifier() != null) {
/*  451 */       printElement(x.getIdentifier());
/*      */     }
/*  453 */     latePrint("(");
/*  454 */     if (x.getArguments() != null) {
/*  455 */       printElements(x.getArguments(), ",");
/*      */     }
/*  457 */     printPostCommentsOf(x);
/*  458 */     print(41);
/*  459 */     if (x.getStatementContainer() != null) {
/*  460 */       advanceTo(x.getEndPosition());
/*  461 */       print(59);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void visitSuperConstructorReference(SuperConstructorReference x) {
/*  466 */     printPreCommentsOf(x);
/*  467 */     if (x.getReferencePrefix() != null) {
/*  468 */       printElement(x.getReferencePrefix());
/*  469 */       print(46);
/*      */     } 
/*  471 */     advanceTo(x.getStartPosition());
/*  472 */     print("super");
/*  473 */     latePrint("(");
/*  474 */     if (x.getArguments() != null) {
/*  475 */       printElements(x.getArguments(), ",");
/*      */     }
/*  477 */     printPostCommentsOf(x);
/*  478 */     print(41);
/*  479 */     advanceTo(x.getEndPosition());
/*  480 */     print(59);
/*      */   }
/*      */   
/*      */   public void visitThisConstructorReference(ThisConstructorReference x) {
/*  484 */     printPreCommentsOf(x);
/*  485 */     advanceTo(x.getStartPosition());
/*  486 */     print("this");
/*  487 */     latePrint("(");
/*  488 */     if (x.getArguments() != null) {
/*  489 */       printElements(x.getArguments(), ",");
/*      */     }
/*  491 */     printPostCommentsOf(x);
/*  492 */     print(41);
/*  493 */     advanceTo(x.getEndPosition());
/*  494 */     print(59);
/*      */   }
/*      */   
/*      */   public void visitSuperReference(SuperReference x) {
/*  498 */     printPreCommentsOf(x);
/*  499 */     if (x.getReferencePrefix() != null) {
/*  500 */       printElement(x.getReferencePrefix());
/*  501 */       print(46);
/*      */     } 
/*  503 */     advanceTo(x.getStartPosition());
/*  504 */     print("super");
/*  505 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitThisReference(ThisReference x) {
/*  509 */     printPreCommentsOf(x);
/*  510 */     if (x.getReferencePrefix() != null) {
/*  511 */       printElement(x.getReferencePrefix());
/*  512 */       print(46);
/*      */     } 
/*  514 */     advanceTo(x.getStartPosition());
/*  515 */     print("this");
/*  516 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitArrayLengthReference(FieldReference x) {
/*  520 */     printPreCommentsOf(x);
/*  521 */     if (x.getReferencePrefix() != null) {
/*  522 */       printElement(x.getReferencePrefix());
/*  523 */       print(46);
/*      */     } 
/*  525 */     advanceTo(x.getStartPosition());
/*  526 */     print("length");
/*  527 */     printPostCommentsOf(x);
/*      */   }
/*      */ 
/*      */   
/*      */   public void visitCompilationUnit(CompilationUnit x) {
/*  532 */     printPreCommentsOf(x);
/*  533 */     if (x.getPackageSpecification() != null) {
/*  534 */       printElement(x.getPackageSpecification());
/*      */     }
/*  536 */     if (x.getImports() != null) {
/*  537 */       printElements(x.getImports(), "\n");
/*      */     }
/*  539 */     if (x.getDeclarations() != null) {
/*  540 */       printElements(x.getDeclarations(), "");
/*      */     }
/*  542 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitImport(Import x) {
/*  546 */     printPreCommentsOf(x);
/*  547 */     advanceTo(x.getStartPosition());
/*  548 */     print("import ");
/*  549 */     printElement(x.getReference());
/*  550 */     if (x.isMultiImport()) {
/*  551 */       print(".*");
/*      */     }
/*  553 */     printPostCommentsOf(x);
/*  554 */     advanceTo(x.getEndPosition());
/*  555 */     print(59);
/*      */   }
/*      */   
/*      */   public void visitPackageSpecification(PackageSpecification x) {
/*  559 */     printPreCommentsOf(x);
/*  560 */     advanceTo(x.getStartPosition());
/*  561 */     print("package ");
/*  562 */     printElement(x.getPackageReference());
/*  563 */     printPostCommentsOf(x);
/*  564 */     advanceTo(x.getEndPosition());
/*  565 */     print(59);
/*      */   }
/*      */   
/*      */   public void visitClassDeclaration(ClassDeclaration x) {
/*  569 */     printPreCommentsOf(x);
/*  570 */     if (x.getModifiers() != null) {
/*  571 */       printElements(x.getModifiers(), " ");
/*  572 */       print(32);
/*      */     } 
/*  574 */     if (x.getIdentifier() != null) {
/*  575 */       advanceTo(x.getStartPosition());
/*  576 */       print("class");
/*  577 */       printElement(x.getIdentifier());
/*      */     } 
/*  579 */     if (x.getExtendedTypes() != null) {
/*  580 */       print(32);
/*  581 */       printElement(x.getExtendedTypes());
/*      */     } 
/*  583 */     if (x.getImplementedTypes() != null) {
/*  584 */       print(32);
/*  585 */       printElement(x.getImplementedTypes());
/*      */     } 
/*      */     
/*  588 */     print(123);
/*  589 */     if (x.getMembers() != null && !x.getMembers().isEmpty()) {
/*  590 */       printElements(x.getMembers(), "");
/*      */     }
/*  592 */     printPostCommentsOf(x);
/*  593 */     advanceTo(x.getEndPosition());
/*  594 */     print(125);
/*      */   }
/*      */   
/*      */   public void visitInterfaceDeclaration(InterfaceDeclaration x) {
/*  598 */     printPreCommentsOf(x);
/*  599 */     if (x.getModifiers() != null) {
/*  600 */       printElements(x.getModifiers(), " ");
/*  601 */       print(32);
/*      */     } 
/*  603 */     if (x.getIdentifier() != null) {
/*  604 */       advanceTo(x.getStartPosition());
/*  605 */       print("interface");
/*  606 */       printElement(x.getIdentifier());
/*      */     } 
/*  608 */     if (x.getExtendedTypes() != null) {
/*  609 */       print(32);
/*  610 */       printElement(x.getExtendedTypes());
/*      */     } 
/*      */     
/*  613 */     print("{");
/*  614 */     if (x.getMembers() != null && !x.getMembers().isEmpty()) {
/*  615 */       printElements(x.getMembers(), "");
/*      */     }
/*  617 */     printPostCommentsOf(x);
/*  618 */     advanceTo(x.getEndPosition());
/*  619 */     print(125);
/*      */   }
/*      */   
/*      */   public void visitFieldDeclaration(FieldDeclaration x) {
/*  623 */     printPreCommentsOf(x);
/*  624 */     if (x.getModifiers() != null) {
/*  625 */       printElements(x.getModifiers(), " ");
/*  626 */       print(32);
/*      */     } 
/*  628 */     printElement(x.getTypeReference());
/*  629 */     print(32);
/*      */     
/*  631 */     if (x.getVariables() != null) {
/*  632 */       printElements(x.getVariables(), ",");
/*      */     }
/*  634 */     printPostCommentsOf(x);
/*  635 */     advanceTo(x.getEndPosition());
/*  636 */     print(59);
/*      */   }
/*      */   
/*      */   public void visitLocalVariableDeclaration(LocalVariableDeclaration x) {
/*  640 */     printPreCommentsOf(x);
/*  641 */     if (x.getModifiers() != null) {
/*  642 */       printElements(x.getModifiers(), " ");
/*  643 */       print(32);
/*      */     } 
/*  645 */     printElement(x.getTypeReference());
/*  646 */     print(32);
/*      */     
/*  648 */     if (x.getVariables() != null) {
/*  649 */       printElements(x.getVariables(), ",");
/*      */     }
/*  651 */     printPostCommentsOf(x);
/*  652 */     if (!(x.getStatementContainer() instanceof recoder.java.statement.LoopStatement)) {
/*  653 */       advanceTo(x.getEndPosition());
/*  654 */       print(59);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void visitVariableDeclaration(VariableDeclaration x) {
/*  659 */     printPreCommentsOf(x);
/*  660 */     if (x.getModifiers() != null) {
/*  661 */       printElements(x.getModifiers(), " ");
/*  662 */       print(32);
/*      */     } 
/*  664 */     printElement(x.getTypeReference());
/*  665 */     print(32);
/*      */     
/*  667 */     if (x.getVariables() != null) {
/*  668 */       printElements(x.getVariables(), ",");
/*      */     }
/*  670 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitExtends(Extends x) {
/*  674 */     printPreCommentsOf(x);
/*  675 */     if (x.getSupertypes() != null) {
/*  676 */       advanceTo(x.getStartPosition());
/*  677 */       print("extends ");
/*  678 */       printElements(x.getSupertypes(), ",");
/*      */     } 
/*  680 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitImplements(Implements x) {
/*  684 */     printPreCommentsOf(x);
/*  685 */     if (x.getSupertypes() != null) {
/*  686 */       advanceTo(x.getStartPosition());
/*  687 */       print("implements ");
/*  688 */       printElements(x.getSupertypes(), ",");
/*      */     } 
/*  690 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitThrows(Throws x) {
/*  694 */     printPreCommentsOf(x);
/*  695 */     if (x.getExceptions() != null) {
/*  696 */       advanceTo(x.getStartPosition());
/*  697 */       print("throws ");
/*  698 */       printElements(x.getExceptions(), ",");
/*      */     } 
/*  700 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitMethodDeclaration(MethodDeclaration x) {
/*  704 */     printPreCommentsOf(x);
/*  705 */     if (x.getModifiers() != null) {
/*  706 */       printElements(x.getModifiers(), " ");
/*  707 */       print(32);
/*      */     } 
/*  709 */     if (x.getTypeReference() != null) {
/*  710 */       printElement(x.getTypeReference());
/*  711 */       print(32);
/*      */     } 
/*      */     
/*  714 */     printElement(x.getIdentifier());
/*  715 */     latePrint("(");
/*  716 */     if (x.getParameters() != null) {
/*  717 */       printElements(x.getParameters(), ",");
/*      */     }
/*  719 */     print(41);
/*  720 */     if (x.getThrown() != null) {
/*  721 */       printElement(x.getThrown());
/*      */     }
/*  723 */     if (x.getBody() != null) {
/*  724 */       printElement(x.getBody());
/*  725 */       printPostCommentsOf(x);
/*      */     } else {
/*  727 */       printPostCommentsOf(x);
/*  728 */       advanceTo(x.getEndPosition());
/*  729 */       print(59);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void visitClassInitializer(ClassInitializer x) {
/*  734 */     printPreCommentsOf(x);
/*  735 */     if (x.getModifiers() != null) {
/*  736 */       printElements(x.getModifiers(), " ");
/*      */     }
/*  738 */     if (x.getBody() != null) {
/*  739 */       printElement(x.getBody());
/*      */     }
/*  741 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitVariableSpecification(VariableSpecification x) {
/*  745 */     printElement(x.getIdentifier());
/*  746 */     for (int i = 0; i < x.getDimensions(); i++) {
/*  747 */       print("[]");
/*      */     }
/*  749 */     if (x.getInitializer() != null) {
/*  750 */       print("=");
/*  751 */       printElement(x.getInitializer());
/*      */     } 
/*  753 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitArrayInitializer(ArrayInitializer x) {
/*  757 */     printPreCommentsOf(x);
/*  758 */     advanceTo(x.getStartPosition());
/*  759 */     print(123);
/*  760 */     if (x.getArguments() != null) {
/*  761 */       printElements(x.getArguments(), ",");
/*      */     }
/*  763 */     printPostCommentsOf(x);
/*  764 */     advanceTo(x.getEndPosition());
/*  765 */     print(125);
/*      */   }
/*      */   
/*      */   public void visitNewArray(NewArray x) {
/*  769 */     printPreCommentsOf(x);
/*  770 */     advanceTo(x.getStartPosition());
/*  771 */     print("new");
/*  772 */     printElement(x.getTypeReference());
/*  773 */     int i = 0;
/*  774 */     if (x.getArguments() != null) {
/*  775 */       for (; i < x.getArguments().size(); i++) {
/*  776 */         print(91);
/*  777 */         printElement((ProgramElement)x.getArguments().get(i));
/*  778 */         print(93);
/*      */       } 
/*      */     }
/*  781 */     for (; i < x.getDimensions(); i++) {
/*  782 */       print("[]");
/*      */     }
/*  784 */     if (x.getArrayInitializer() != null) {
/*  785 */       printElement(x.getArrayInitializer());
/*      */     }
/*  787 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitNew(New x) {
/*  791 */     if (x.getReferencePrefix() != null) {
/*  792 */       printElement(x.getReferencePrefix());
/*  793 */       print(46);
/*      */     } 
/*  795 */     advanceTo(x.getStartPosition());
/*  796 */     print("new");
/*  797 */     printElement(x.getTypeReference());
/*  798 */     latePrint("(");
/*  799 */     if (x.getArguments() != null) {
/*  800 */       printElements(x.getArguments(), ",");
/*      */     }
/*  802 */     print(41);
/*  803 */     if (x.getClassDeclaration() != null) {
/*  804 */       printElement(x.getClassDeclaration());
/*      */     }
/*  806 */     printPostCommentsOf(x);
/*  807 */     if (x.getStatementContainer() != null) {
/*  808 */       advanceTo(x.getEndPosition());
/*  809 */       print(59);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void visitStatementBlock(StatementBlock x) {
/*  815 */     printPreCommentsOf(x);
/*  816 */     advanceTo(x.getStartPosition());
/*  817 */     print(123);
/*  818 */     if (x.getBody() != null && x.getBody().size() > 0) {
/*  819 */       printElements(x.getBody(), "");
/*      */     }
/*  821 */     printPostCommentsOf(x);
/*  822 */     advanceTo(x.getEndPosition());
/*  823 */     print(125);
/*      */   }
/*      */   
/*      */   public void visitBreak(Break x) {
/*  827 */     printPreCommentsOf(x);
/*  828 */     advanceTo(x.getStartPosition());
/*  829 */     print("break");
/*  830 */     if (x.getIdentifier() != null) {
/*  831 */       print(" ");
/*  832 */       printElement(x.getIdentifier());
/*      */     } 
/*  834 */     printPostCommentsOf(x);
/*  835 */     advanceTo(x.getEndPosition());
/*  836 */     print(59);
/*      */   }
/*      */   
/*      */   public void visitContinue(Continue x) {
/*  840 */     printPreCommentsOf(x);
/*  841 */     advanceTo(x.getStartPosition());
/*  842 */     print("continue");
/*  843 */     if (x.getIdentifier() != null) {
/*  844 */       print(" ");
/*  845 */       printElement(x.getIdentifier());
/*      */     } 
/*  847 */     printPostCommentsOf(x);
/*  848 */     advanceTo(x.getEndPosition());
/*  849 */     print(59);
/*      */   }
/*      */   
/*      */   public void visitLabeledStatement(LabeledStatement x) {
/*  853 */     printPreCommentsOf(x);
/*  854 */     if (x.getIdentifier() != null) {
/*  855 */       printElement(x.getIdentifier());
/*  856 */       print(58);
/*      */     } 
/*  858 */     if (x.getBody() != null) {
/*  859 */       printElement(x.getBody());
/*      */     }
/*  861 */     printPostCommentsOf(x);
/*      */   }
/*      */ 
/*      */   
/*      */   public void visitReturn(Return x) {
/*  866 */     printPreCommentsOf(x);
/*  867 */     advanceTo(x.getStartPosition());
/*  868 */     print("return");
/*  869 */     if (x.getExpression() != null) {
/*  870 */       print(" ");
/*  871 */       printElement(x.getExpression());
/*      */     } 
/*  873 */     printPostCommentsOf(x);
/*  874 */     advanceTo(x.getEndPosition());
/*  875 */     print(59);
/*      */   }
/*      */   
/*      */   public void visitThrow(Throw x) {
/*  879 */     printPreCommentsOf(x);
/*  880 */     advanceTo(x.getStartPosition());
/*  881 */     print("throw");
/*  882 */     if (x.getExpression() != null) {
/*  883 */       print(" ");
/*  884 */       printElement(x.getExpression());
/*      */     } 
/*  886 */     printPostCommentsOf(x);
/*  887 */     advanceTo(x.getEndPosition());
/*  888 */     print(59);
/*      */   }
/*      */   
/*      */   public void visitDo(Do x) {
/*  892 */     printPreCommentsOf(x);
/*  893 */     advanceTo(x.getStartPosition());
/*  894 */     print("do ");
/*  895 */     if (x.getBody() == null || x.getBody() instanceof EmptyStatement) {
/*  896 */       print(59);
/*      */     } else {
/*  898 */       printElement(x.getBody());
/*      */     } 
/*  900 */     latePrint("while(");
/*  901 */     if (x.getGuard() != null) {
/*  902 */       printElement(x.getGuard());
/*      */     }
/*  904 */     printPostCommentsOf(x);
/*  905 */     print(41);
/*  906 */     advanceTo(x.getEndPosition());
/*  907 */     print(59);
/*      */   }
/*      */   
/*      */   public void visitFor(For x) {
/*  911 */     printPreCommentsOf(x);
/*  912 */     advanceTo(x.getStartPosition());
/*  913 */     print("for");
/*  914 */     latePrint("(");
/*      */     
/*  916 */     if (x.getInitializers() != null) {
/*  917 */       printElements(x.getInitializers(), ",");
/*      */     }
/*  919 */     print(59);
/*  920 */     if (x.getGuard() != null) {
/*  921 */       printElement(x.getGuard());
/*      */     }
/*  923 */     print(59);
/*  924 */     if (x.getUpdates() != null) {
/*  925 */       printElements(x.getUpdates(), ",");
/*      */     }
/*  927 */     print(41);
/*  928 */     if (x.getBody() == null || x.getBody() instanceof EmptyStatement) {
/*  929 */       printPostCommentsOf(x);
/*      */       
/*  931 */       advanceTo(x.getEndPosition());
/*  932 */       print(59);
/*      */     } else {
/*  934 */       printElement(x.getBody());
/*  935 */       printPostCommentsOf(x);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void visitWhile(While x) {
/*  940 */     printPreCommentsOf(x);
/*  941 */     advanceTo(x.getStartPosition());
/*  942 */     print("while");
/*  943 */     latePrint("(");
/*  944 */     if (x.getGuard() != null) {
/*  945 */       printElement(x.getGuard());
/*      */     }
/*  947 */     print(41);
/*  948 */     if (x.getBody() == null || x.getBody() instanceof EmptyStatement) {
/*  949 */       printPostCommentsOf(x);
/*      */       
/*  951 */       advanceTo(x.getEndPosition());
/*  952 */       print(59);
/*      */     } else {
/*  954 */       printElement(x.getBody());
/*  955 */       printPostCommentsOf(x);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void visitAssert(Assert x) {
/*  960 */     printPreCommentsOf(x);
/*  961 */     advanceTo(x.getStartPosition());
/*  962 */     print("assert");
/*  963 */     if (x.getCondition() != null) {
/*  964 */       print(32);
/*  965 */       printElement(x.getCondition());
/*      */     } 
/*  967 */     if (x.getMessage() != null) {
/*  968 */       print(":");
/*  969 */       printElement(x.getMessage());
/*      */     } 
/*  971 */     printPostCommentsOf(x);
/*      */     
/*  973 */     advanceTo(x.getEndPosition());
/*  974 */     print(59);
/*      */   }
/*      */   
/*      */   public void visitIf(If x) {
/*  978 */     printPreCommentsOf(x);
/*  979 */     advanceTo(x.getStartPosition());
/*  980 */     print("if");
/*  981 */     latePrint("(");
/*  982 */     if (x.getExpression() != null)
/*  983 */       printElement(x.getExpression()); 
/*  984 */     print(41);
/*  985 */     if (x.getThen() != null)
/*  986 */       printElement(x.getThen()); 
/*  987 */     if (x.getElse() != null)
/*  988 */       printElement(x.getElse()); 
/*  989 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitThen(Then x) {
/*  993 */     printPreCommentsOf(x);
/*  994 */     if (x.getBody() != null) {
/*  995 */       printElement(x.getBody());
/*      */     }
/*  997 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitElse(Else x) {
/* 1001 */     printPreCommentsOf(x);
/* 1002 */     advanceTo(x.getStartPosition());
/* 1003 */     print("else ");
/* 1004 */     if (x.getBody() != null) {
/* 1005 */       printElement(x.getBody());
/*      */     }
/* 1007 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitSwitch(Switch x) {
/* 1011 */     printPreCommentsOf(x);
/* 1012 */     advanceTo(x.getStartPosition());
/* 1013 */     print("switch");
/* 1014 */     latePrint("(");
/* 1015 */     if (x.getExpression() != null)
/* 1016 */       printElement(x.getExpression()); 
/* 1017 */     print("){");
/* 1018 */     if (x.getBranchList() != null && x.getBranchCount() > 0) {
/* 1019 */       printElements(x.getBranchList(), "");
/*      */     }
/* 1021 */     printPostCommentsOf(x);
/* 1022 */     advanceTo(x.getEndPosition());
/* 1023 */     print(125);
/*      */   }
/*      */   
/*      */   public void visitCase(Case x) {
/* 1027 */     printPreCommentsOf(x);
/* 1028 */     advanceTo(x.getStartPosition());
/* 1029 */     print("case");
/* 1030 */     if (x.getExpression() != null) {
/* 1031 */       print(32);
/* 1032 */       printElement(x.getExpression());
/*      */     } 
/* 1034 */     print(58);
/* 1035 */     if (x.getBody() != null && x.getBody().size() > 0)
/* 1036 */       printElements(x.getBody(), ""); 
/* 1037 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitDefault(Default x) {
/* 1041 */     printPreCommentsOf(x);
/* 1042 */     advanceTo(x.getStartPosition());
/* 1043 */     print("default:");
/* 1044 */     if (x.getBody() != null && x.getBody().size() > 0)
/* 1045 */       printElements(x.getBody(), ""); 
/* 1046 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitTry(Try x) {
/* 1050 */     printPreCommentsOf(x);
/* 1051 */     advanceTo(x.getStartPosition());
/* 1052 */     print("try ");
/* 1053 */     if (x.getBody() != null)
/* 1054 */       printElement(x.getBody()); 
/* 1055 */     if (x.getBranchList() != null)
/* 1056 */       printElements(x.getBranchList(), ""); 
/* 1057 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitCatch(Catch x) {
/* 1061 */     printPreCommentsOf(x);
/* 1062 */     advanceTo(x.getStartPosition());
/* 1063 */     print("catch");
/* 1064 */     latePrint("(");
/* 1065 */     if (x.getParameterDeclaration() != null)
/* 1066 */       printElement(x.getParameterDeclaration()); 
/* 1067 */     print(41);
/* 1068 */     if (x.getBody() != null)
/* 1069 */       printElement(x.getBody()); 
/* 1070 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitFinally(Finally x) {
/* 1074 */     printPreCommentsOf(x);
/* 1075 */     advanceTo(x.getStartPosition());
/* 1076 */     print("finally ");
/* 1077 */     if (x.getBody() != null)
/* 1078 */       printElement(x.getBody()); 
/* 1079 */     printPostCommentsOf(x);
/*      */   }
/*      */ 
/*      */   
/*      */   public void visitSynchronizedBlock(SynchronizedBlock x) {
/* 1084 */     printPreCommentsOf(x);
/* 1085 */     advanceTo(x.getStartPosition());
/* 1086 */     print("synchronized");
/*      */     
/* 1088 */     if (x.getExpression() != null) {
/* 1089 */       latePrint("(");
/* 1090 */       printElement(x.getExpression());
/* 1091 */       print(41);
/*      */     } else {
/* 1093 */       print(32);
/*      */     } 
/* 1095 */     if (x.getBody() != null) {
/* 1096 */       printElement(x.getBody());
/*      */     }
/* 1098 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitEmptyStatement(EmptyStatement x) {
/* 1102 */     printPreCommentsOf(x);
/* 1103 */     advanceTo(x.getEndPosition());
/* 1104 */     printPostCommentsOf(x);
/* 1105 */     print(59);
/*      */   }
/*      */   
/*      */   public void visitInstanceof(Instanceof x) {
/* 1109 */     if (x.getArguments() != null) {
/* 1110 */       printElement((ProgramElement)x.getArguments().get(0));
/*      */     }
/*      */     
/* 1113 */     print(" instanceof");
/* 1114 */     if (x.getTypeReference() != null) {
/* 1115 */       printElement(x.getTypeReference());
/*      */     }
/* 1117 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitTypeCast(TypeCast x) {
/* 1121 */     advanceTo(x.getStartPosition());
/* 1122 */     print(40);
/* 1123 */     if (x.getTypeReference() != null) {
/* 1124 */       printElement(x.getTypeReference());
/*      */     }
/* 1126 */     print(41);
/* 1127 */     if (x.getArguments() != null) {
/* 1128 */       printElement((ProgramElement)x.getArguments().get(0));
/*      */     }
/* 1130 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitUncollatedReferenceQualifier(UncollatedReferenceQualifier x) {
/* 1134 */     printPreCommentsOf(x);
/* 1135 */     if (x.getReferencePrefix() != null) {
/* 1136 */       printElement(x.getReferencePrefix());
/* 1137 */       print(46);
/*      */     } 
/* 1139 */     if (x.getIdentifier() != null)
/*      */     {
/*      */ 
/*      */ 
/*      */       
/* 1144 */       latePrint(x.getIdentifier().getText());
/*      */     }
/* 1146 */     printPostCommentsOf(x);
/*      */   }
/*      */ 
/*      */   
/*      */   private void printBinaryOp(Operator x, String symbol) {
/* 1151 */     printPreCommentsOf(x);
/* 1152 */     printElement((ProgramElement)x.getArguments().get(0));
/* 1153 */     advanceTo(x.getStartPosition());
/* 1154 */     print(symbol);
/* 1155 */     printElement((ProgramElement)x.getArguments().get(1));
/* 1156 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   private void printAssignement(Assignment x, String symbol) {
/* 1160 */     printPreCommentsOf(x);
/* 1161 */     printElement((ProgramElement)x.getArguments().get(0));
/* 1162 */     advanceTo(x.getStartPosition());
/* 1163 */     print(symbol);
/* 1164 */     printElement((ProgramElement)x.getArguments().get(1));
/* 1165 */     if (x.getStatementContainer() != null) {
/* 1166 */       advanceTo(x.getEndPosition());
/* 1167 */       print(59);
/*      */     } 
/* 1169 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   private void printPrefixUnaryOp(Operator x, String symbol) {
/* 1173 */     printPreCommentsOf(x);
/* 1174 */     advanceTo(x.getStartPosition());
/* 1175 */     print(symbol);
/* 1176 */     printElement((ProgramElement)x.getArguments().get(0));
/*      */   }
/*      */   
/*      */   private void printPostfixUnaryOp(Operator x, String symbol) {
/* 1180 */     printPreCommentsOf(x);
/* 1181 */     printElement((ProgramElement)x.getArguments().get(0));
/* 1182 */     advanceTo(x.getStartPosition());
/* 1183 */     print(symbol);
/*      */   }
/*      */ 
/*      */   
/* 1187 */   public void visitBinaryAndAssignment(BinaryAndAssignment x) { printAssignement(x, "&="); }
/*      */ 
/*      */ 
/*      */   
/* 1191 */   public void visitBinaryOrAssignment(BinaryOrAssignment x) { printAssignement(x, "|="); }
/*      */ 
/*      */ 
/*      */   
/* 1195 */   public void visitBinaryXOrAssignment(BinaryXOrAssignment x) { printAssignement(x, "^="); }
/*      */ 
/*      */ 
/*      */   
/* 1199 */   public void visitCopyAssignment(CopyAssignment x) { printAssignement(x, "="); }
/*      */ 
/*      */ 
/*      */   
/* 1203 */   public void visitDivideAssignment(DivideAssignment x) { printAssignement(x, "/="); }
/*      */ 
/*      */ 
/*      */   
/* 1207 */   public void visitMinusAssignment(MinusAssignment x) { printAssignement(x, "-="); }
/*      */ 
/*      */ 
/*      */   
/* 1211 */   public void visitModuloAssignment(ModuloAssignment x) { printAssignement(x, "%="); }
/*      */ 
/*      */ 
/*      */   
/* 1215 */   public void visitPlusAssignment(PlusAssignment x) { printAssignement(x, "+="); }
/*      */ 
/*      */ 
/*      */   
/* 1219 */   public void visitShiftLeftAssignment(ShiftLeftAssignment x) { printAssignement(x, "<<="); }
/*      */ 
/*      */ 
/*      */   
/* 1223 */   public void visitShiftRightAssignment(ShiftRightAssignment x) { printAssignement(x, ">>="); }
/*      */ 
/*      */ 
/*      */   
/* 1227 */   public void visitTimesAssignment(TimesAssignment x) { printAssignement(x, "*="); }
/*      */ 
/*      */ 
/*      */   
/* 1231 */   public void visitUnsignedShiftRightAssignment(UnsignedShiftRightAssignment x) { printAssignement(x, ">>>="); }
/*      */ 
/*      */ 
/*      */   
/* 1235 */   public void visitBinaryAnd(BinaryAnd x) { printBinaryOp(x, "&"); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitBinaryNot(BinaryNot x) {
/* 1241 */     printPrefixUnaryOp(x, "~");
/* 1242 */     printPostCommentsOf(x);
/*      */   }
/*      */ 
/*      */   
/* 1246 */   public void visitBinaryOr(BinaryOr x) { printBinaryOp(x, "|"); }
/*      */ 
/*      */ 
/*      */   
/* 1250 */   public void visitBinaryXOr(BinaryXOr x) { printBinaryOp(x, "^"); }
/*      */ 
/*      */ 
/*      */   
/* 1254 */   public void visitDivide(Divide x) { printBinaryOp(x, "/"); }
/*      */ 
/*      */ 
/*      */   
/* 1258 */   public void visitEquals(Equals x) { printBinaryOp(x, "=="); }
/*      */ 
/*      */ 
/*      */   
/* 1262 */   public void visitGreaterOrEquals(GreaterOrEquals x) { printBinaryOp(x, ">="); }
/*      */ 
/*      */ 
/*      */   
/* 1266 */   public void visitGreaterThan(GreaterThan x) { printBinaryOp(x, ">"); }
/*      */ 
/*      */ 
/*      */   
/* 1270 */   public void visitLessOrEquals(LessOrEquals x) { printBinaryOp(x, "<="); }
/*      */ 
/*      */ 
/*      */   
/* 1274 */   public void visitLessThan(LessThan x) { printBinaryOp(x, "<"); }
/*      */ 
/*      */ 
/*      */   
/* 1278 */   public void visitNotEquals(NotEquals x) { printBinaryOp(x, "!="); }
/*      */ 
/*      */ 
/*      */   
/* 1282 */   public void visitLogicalAnd(LogicalAnd x) { printBinaryOp(x, "&&"); }
/*      */ 
/*      */ 
/*      */   
/* 1286 */   public void visitLogicalOr(LogicalOr x) { printBinaryOp(x, "||"); }
/*      */ 
/*      */ 
/*      */   
/* 1290 */   public void visitMinus(Minus x) { printBinaryOp(x, "-"); }
/*      */ 
/*      */ 
/*      */   
/* 1294 */   public void visitModulo(Modulo x) { printBinaryOp(x, "%"); }
/*      */ 
/*      */ 
/*      */   
/* 1298 */   public void visitPlus(Plus x) { printBinaryOp(x, "+"); }
/*      */ 
/*      */ 
/*      */   
/* 1302 */   public void visitShiftLeft(ShiftLeft x) { printBinaryOp(x, "<<"); }
/*      */ 
/*      */ 
/*      */   
/* 1306 */   public void visitShiftRight(ShiftRight x) { printBinaryOp(x, ">>"); }
/*      */ 
/*      */ 
/*      */   
/* 1310 */   public void visitTimes(Times x) { printBinaryOp(x, "*"); }
/*      */ 
/*      */ 
/*      */   
/* 1314 */   public void visitUnsignedShiftRight(UnsignedShiftRight x) { printBinaryOp(x, ">>>"); }
/*      */ 
/*      */ 
/*      */   
/*      */   public void visitPostDecrement(PostDecrement x) {
/* 1319 */     printPostfixUnaryOp(x, "--");
/* 1320 */     printPostCommentsOf(x);
/* 1321 */     if (x.getStatementContainer() != null) {
/* 1322 */       advanceTo(x.getEndPosition());
/* 1323 */       print(59);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void visitPostIncrement(PostIncrement x) {
/* 1328 */     printPostfixUnaryOp(x, "++");
/* 1329 */     printPostCommentsOf(x);
/* 1330 */     if (x.getStatementContainer() != null) {
/* 1331 */       advanceTo(x.getEndPosition());
/* 1332 */       print(59);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void visitPreDecrement(PreDecrement x) {
/* 1337 */     printPrefixUnaryOp(x, "--");
/* 1338 */     printPostCommentsOf(x);
/* 1339 */     if (x.getStatementContainer() != null) {
/* 1340 */       advanceTo(x.getEndPosition());
/* 1341 */       print(59);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void visitPreIncrement(PreIncrement x) {
/* 1346 */     printPrefixUnaryOp(x, "++");
/* 1347 */     printPostCommentsOf(x);
/* 1348 */     if (x.getStatementContainer() != null) {
/* 1349 */       advanceTo(x.getEndPosition());
/* 1350 */       print(59);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void visitLogicalNot(LogicalNot x) {
/* 1355 */     printPrefixUnaryOp(x, "!");
/* 1356 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitNegative(Negative x) {
/* 1360 */     printPrefixUnaryOp(x, "-");
/* 1361 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitPositive(Positive x) {
/* 1365 */     printPrefixUnaryOp(x, "+");
/* 1366 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitConditional(Conditional x) {
/* 1370 */     printPreCommentsOf(x);
/* 1371 */     if (x.getArguments() != null) {
/* 1372 */       printElement((ProgramElement)x.getArguments().get(0));
/* 1373 */       print("?");
/* 1374 */       printElement((ProgramElement)x.getArguments().get(1));
/* 1375 */       print(":");
/* 1376 */       printElement((ProgramElement)x.getArguments().get(2));
/*      */     } 
/* 1378 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitParenthesizedExpression(ParenthesizedExpression x) {
/* 1382 */     printPreCommentsOf(x);
/* 1383 */     advanceTo(x.getStartPosition());
/* 1384 */     print(40);
/* 1385 */     if (x.getArguments() != null) {
/* 1386 */       printElement((ProgramElement)x.getArguments().get(0));
/*      */     }
/* 1388 */     printPostCommentsOf(x);
/* 1389 */     print(41);
/*      */   }
/*      */ 
/*      */   
/*      */   public void visitAbstract(Abstract x) {
/* 1394 */     printPreCommentsOf(x);
/* 1395 */     advanceTo(x.getStartPosition());
/* 1396 */     print("abstract");
/* 1397 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitFinal(Final x) {
/* 1401 */     printPreCommentsOf(x);
/* 1402 */     advanceTo(x.getStartPosition());
/* 1403 */     print("final");
/* 1404 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitNative(Native x) {
/* 1408 */     printPreCommentsOf(x);
/* 1409 */     advanceTo(x.getStartPosition());
/* 1410 */     print("native");
/* 1411 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitPrivate(Private x) {
/* 1415 */     printPreCommentsOf(x);
/* 1416 */     advanceTo(x.getStartPosition());
/* 1417 */     print("private");
/* 1418 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitProtected(Protected x) {
/* 1422 */     printPreCommentsOf(x);
/* 1423 */     advanceTo(x.getStartPosition());
/* 1424 */     print("protected");
/* 1425 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitPublic(Public x) {
/* 1429 */     printPreCommentsOf(x);
/* 1430 */     advanceTo(x.getStartPosition());
/* 1431 */     print("public");
/* 1432 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitStatic(Static x) {
/* 1436 */     printPreCommentsOf(x);
/* 1437 */     advanceTo(x.getStartPosition());
/* 1438 */     print("static");
/* 1439 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitStrictFp(StrictFp x) {
/* 1443 */     printPreCommentsOf(x);
/* 1444 */     advanceTo(x.getStartPosition());
/* 1445 */     print("strictfp");
/* 1446 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitSynchronized(Synchronized x) {
/* 1450 */     printPreCommentsOf(x);
/* 1451 */     advanceTo(x.getStartPosition());
/* 1452 */     print("synchronized");
/* 1453 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitTransient(Transient x) {
/* 1457 */     printPreCommentsOf(x);
/* 1458 */     advanceTo(x.getStartPosition());
/* 1459 */     print("transient");
/* 1460 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitVolatile(Volatile x) {
/* 1464 */     printPreCommentsOf(x);
/* 1465 */     advanceTo(x.getStartPosition());
/* 1466 */     print("volatile");
/* 1467 */     printPostCommentsOf(x);
/*      */   }
/*      */   
/*      */   public void visitComment(Comment x) {
/* 1471 */     advanceTo(x.getStartPosition());
/* 1472 */     print(x.getText());
/*      */   }
/*      */ 
/*      */   
/* 1476 */   public void visitSingleLineComment(SingleLineComment x) { visitComment(x); }
/*      */ 
/*      */ 
/*      */   
/* 1480 */   public void visitDocComment(DocComment x) { visitComment(x); }
/*      */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\CodeStripeSourcePrinter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */