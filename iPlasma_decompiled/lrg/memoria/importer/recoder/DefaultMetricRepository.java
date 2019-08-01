/*     */ package lrg.memoria.importer.recoder;
/*     */ 
/*     */ import lrg.memoria.core.CodeStripe;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefaultMetricRepository
/*     */   implements MetricsRepository
/*     */ {
/*     */   private int statements;
/*     */   private int lines;
/*     */   private int commentsNumber;
/*     */   private int decisions;
/*     */   private int loops;
/*     */   private int exits;
/*     */   private int exceptions;
/*     */   private int currentNestingLevel;
/*     */   private int maxNestingLevel;
/*     */   private int cyclomaticNumber;
/*     */   private static DefaultMetricRepository instance;
/*     */   
/*  24 */   static void cleanUp() { instance = null; }
/*     */ 
/*     */ 
/*     */   
/*  28 */   private DefaultMetricRepository() { resetAll(); }
/*     */ 
/*     */   
/*     */   public static MetricsRepository getMetricRepository() {
/*  32 */     if (instance == null) {
/*  33 */       instance = new DefaultMetricRepository();
/*     */     }
/*  35 */     return instance;
/*     */   }
/*     */   
/*     */   public void resetAll() {
/*  39 */     this.statements = 1;
/*  40 */     this.lines = 0;
/*  41 */     this.commentsNumber = 0;
/*  42 */     this.decisions = 0;
/*  43 */     this.loops = 0;
/*  44 */     this.exits = 0;
/*  45 */     this.exceptions = 0;
/*  46 */     this.currentNestingLevel = 0;
/*  47 */     this.maxNestingLevel = 0;
/*  48 */     this.cyclomaticNumber = 1;
/*     */   }
/*     */   
/*     */   public void addComments(int n) {
/*  52 */     this.commentsNumber += n;
/*  53 */     CodeStripe cs = DefaultModelRepository.getModelRepository(null).getCurrentStripe();
/*  54 */     if (cs != null) cs.addAtomicCommentLines(n);
/*     */   
/*     */   }
/*     */   
/*  58 */   public int getCommentsNumber() { return this.commentsNumber; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addDecision() {
/*  63 */     this.decisions++;
/*  64 */     this.cyclomaticNumber++;
/*     */   }
/*     */ 
/*     */   
/*  68 */   public int getDecisions() { return this.decisions; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addLoop() {
/*  73 */     this.loops++;
/*  74 */     this.cyclomaticNumber++;
/*  75 */     this.decisions++;
/*     */   }
/*     */ 
/*     */   
/*  79 */   public int getLoops() { return this.loops; }
/*     */ 
/*     */ 
/*     */   
/*  83 */   public void addException() { this.exceptions++; }
/*     */ 
/*     */ 
/*     */   
/*  87 */   public int getExceptions() { return this.exceptions; }
/*     */ 
/*     */   
/*     */   public void updateNestingLevel(int n) {
/*  91 */     this.currentNestingLevel += n;
/*  92 */     if (this.currentNestingLevel > this.maxNestingLevel) {
/*  93 */       this.maxNestingLevel = this.currentNestingLevel;
/*     */     }
/*     */   }
/*     */   
/*  97 */   public int getMaxNestingLevel() { return this.maxNestingLevel; }
/*     */ 
/*     */ 
/*     */   
/* 101 */   public int getCyclomatic() { return this.cyclomaticNumber; }
/*     */ 
/*     */   
/*     */   public void addLogicalAnd() {
/* 105 */     this.cyclomaticNumber++;
/* 106 */     CodeStripe cs = DefaultModelRepository.getModelRepository(null).getCurrentStripe();
/* 107 */     if (cs != null) cs.addAtomicCyclo(1); 
/*     */   }
/*     */   
/*     */   public void addLogicalOr() {
/* 111 */     this.cyclomaticNumber++;
/* 112 */     CodeStripe cs = DefaultModelRepository.getModelRepository(null).getCurrentStripe();
/* 113 */     if (cs != null) cs.addAtomicCyclo(1);
/*     */   
/*     */   }
/*     */   
/* 117 */   public void addStatements(int n) { this.statements += n; }
/*     */ 
/*     */ 
/*     */   
/* 121 */   public int getNumberOfStatements() { return this.statements; }
/*     */ 
/*     */ 
/*     */   
/* 125 */   public void addExit() { this.exits++; }
/*     */ 
/*     */ 
/*     */   
/* 129 */   public int getNumberOfExits() { return this.exits; }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\DefaultMetricRepository.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */