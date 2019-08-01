/*     */ package lrg.memoria.importer.recoder;
/*     */ 
/*     */ import lrg.memoria.core.Body;
/*     */ import lrg.memoria.core.CodeStripe;
/*     */ import lrg.memoria.core.Method;
/*     */ import recoder.java.NonTerminalProgramElement;
/*     */ import recoder.java.ProgramElement;
/*     */ import recoder.java.StatementBlock;
/*     */ 
/*     */ public class StatementBlockListener
/*     */   extends StripeContainerListener {
/*     */   static  {
/*  13 */     ModelConstructor.addFactory("lrg.memoria.importer.recoder.StatementBlockListener", new Factory());
/*     */   }
/*     */   private static Listener listener;
/*     */   private StatementBlockListener() {}
/*     */   
/*     */   static class Factory implements IFactory { public Listener getListener() {
/*  19 */       if (
/*     */ 
/*     */ 
/*     */         
/*  23 */         listener != null) {
/*  24 */         return listener;
/*     */       }
/*  26 */       listener = new StatementBlockListener(null); return new StatementBlockListener(null);
/*     */     }
/*     */ 
/*     */     
/*  30 */     public void cleanUp() { listener = null; } }
/*     */ 
/*     */   
/*     */   public void enterModelComponent(ProgramElement pe) {
/*     */     CodeStripe cs;
/*  35 */     StatementBlock sb = (StatementBlock)pe;
/*  36 */     MetricsRepository mer = DefaultMetricRepository.getMetricRepository();
/*     */     
/*  38 */     mer.addStatements(sb.getStatementCount());
/*     */     
/*  40 */     ModelRepository mr = DefaultModelRepository.getModelRepository(null);
/*     */ 
/*     */     
/*  43 */     if (mr.getCurrentStripe() == null) {
/*  44 */       cs = new CodeStripe(mr.getCurrentBody());
/*  45 */       mr.getCurrentBody().setCodeStripe(cs);
/*  46 */       cs.addAtomicCyclo(1);
/*  47 */       cs.addAtomicStatements(1);
/*  48 */       cs.setAccess(2);
/*     */     } else {
/*  50 */       cs = new CodeStripe(mr.getCurrentStripe());
/*  51 */       cs.setAccess(0);
/*     */     } 
/*     */     
/*  54 */     cs.setSignature("[stmnt_block]");
/*  55 */     cs.addAtomicStatements(sb.getStatementCount());
/*     */     
/*  57 */     CodeStripe old = mr.getCurrentStripe();
/*     */     
/*  59 */     if (sb.getStatementCount() > 0) {
/*  60 */       setActiveStripe(cs, mr, pe, sb.getStatementAt(0), sb.getStatementAt(sb.getStatementCount() - 1));
/*     */     } else {
/*  62 */       cs.setAccess(1);
/*  63 */       setActiveStripe(cs, mr, pe, null, null);
/*     */     } 
/*     */     
/*  66 */     if (old == null) {
/*     */       
/*  68 */       CodeStripeSourcePrinter mpp = CodeStripeSourcePrinter.instance();
/*     */ 
/*     */       
/*  71 */       String src = mpp.getSource(sb);
/*     */       
/*     */       try {
/*  74 */         cs.setSourceCode(src);
/*  75 */       } catch (IllegalArgumentException e) {
/*  76 */         if (sb.getStatementCount() > 0) throw e; 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void leaveModelComponent(ProgramElement pe) {
/*  82 */     NonTerminalProgramElement parent = pe.getASTParent();
/*  83 */     ModelRepository mr = DefaultModelRepository.getModelRepository(null);
/*  84 */     Method mmm = mr.getCurrentMethod();
/*  85 */     if (parent instanceof recoder.java.declaration.ClassInitializer || (parent instanceof recoder.java.declaration.MethodDeclaration && !mmm.isAbstract())) {
/*  86 */       Body mmmb = mr.getCurrentBody();
/*  87 */       MetricsRepository mer = DefaultMetricRepository.getMetricRepository();
/*     */       
/*  89 */       mmmb.setNumberOfStatements(mer.getNumberOfStatements());
/*     */       
/*  91 */       StatementBlock sb = (StatementBlock)pe;
/*  92 */       int spos = sb.getStartPosition().getLine();
/*  93 */       int epos = sb.getEndPosition().getLine();
/*     */       
/*  95 */       mmmb.setNumberOfLines(epos - spos + 1);
/*  96 */       mmmb.setNumberOfComments(mer.getCommentsNumber());
/*  97 */       mmmb.setNumberOfDecisions(mer.getDecisions());
/*  98 */       mmmb.setNumberOfLoops(mer.getLoops());
/*  99 */       mmmb.setNumberOfExits(mer.getNumberOfExits());
/* 100 */       mmmb.setNumberOfExceptions(mer.getExceptions());
/* 101 */       mmmb.setMaxNestingLevel(mer.getMaxNestingLevel());
/* 102 */       mmmb.setCyclomaticNumber(mer.getCyclomatic());
/*     */     } 
/*     */     
/* 105 */     StatementBlock sb = (StatementBlock)pe;
/* 106 */     restoreStripe(mr);
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\StatementBlockListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */