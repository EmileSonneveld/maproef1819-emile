/*     */ package lrg.memoria.importer.recoder;
/*     */ 
/*     */ import java.util.Stack;
/*     */ import lrg.memoria.core.CodeStripe;
/*     */ import lrg.memoria.core.File;
/*     */ import lrg.memoria.core.Location;
/*     */ import recoder.java.JavaSourceElement;
/*     */ import recoder.java.ProgramElement;
/*     */ import recoder.java.SourceElement;
/*     */ import recoder.java.statement.Branch;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class StripeContainerListener
/*     */   implements Listener
/*     */ {
/*  22 */   private Stack oldStripes = new Stack();
/*     */ 
/*     */   
/*     */   private static Location startRelativeOf(CodeStripe cs, ProgramElement elem, File f) {
/*  26 */     Location l = new Location(f);
/*  27 */     if (elem != null) {
/*  28 */       SourceElement jse = ((JavaSourceElement)elem).getFirstElement();
/*  29 */       if (cs != null) {
/*  30 */         Location rs = cs.getRelPosOf(
/*  31 */             jse.getStartPosition().getLine(), 
/*  32 */             jse.getStartPosition().getColumn());
/*  33 */         l.setStartLine(rs.getStartLine());
/*  34 */         l.setStartChar(rs.getStartChar());
/*     */       } else {
/*  36 */         l.setStartLine(jse.getStartPosition().getLine());
/*  37 */         l.setStartChar(jse.getStartPosition().getColumn());
/*     */       } 
/*     */     } else {
/*  40 */       l.setStartLine(0);
/*  41 */       l.setStartChar(0);
/*     */     } 
/*  43 */     return l;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setActiveStripe(CodeStripe cs, ModelRepository mr, ProgramElement stripe, ProgramElement firstInStripe, ProgramElement lastInStripe) {
/*  50 */     CodeStripe os = mr.getCurrentStripe();
/*  51 */     if (cs != null) {
/*  52 */       cs.setLocation(startRelativeOf(os, stripe, mr.getCurrentFile()));
/*     */       
/*  54 */       cs.setContentLocation(startRelativeOf(cs, firstInStripe, mr.getCurrentFile()));
/*     */       
/*  56 */       endRelativeOf(os, stripe, cs.getLocation());
/*     */       
/*  58 */       endRelativeOf(cs, lastInStripe, cs.getContentLocation());
/*  59 */       if (os != null)
/*  60 */         cs.getSourceCodeFromStripe(os); 
/*     */     } 
/*  62 */     this.oldStripes.push(os);
/*  63 */     mr.setCurrentStripe(cs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static SourceElement getLastOf(SourceElement elem) {
/*  70 */     if (elem instanceof recoder.java.statement.Case || elem instanceof recoder.java.statement.Default) {
/*  71 */       Branch b = (Branch)elem;
/*  72 */       if (b.getStatementCount() > 0) {
/*  73 */         return getLastOf(b.getStatementAt(b.getStatementCount() - 1));
/*     */       }
/*  75 */       return b;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  83 */     return elem.getLastElement();
/*     */   }
/*     */ 
/*     */   
/*     */   private static void endRelativeOf(CodeStripe cs, ProgramElement elem, Location l) {
/*  88 */     if (elem != null) {
/*  89 */       SourceElement jse = ((JavaSourceElement)elem).getLastElement();
/*     */ 
/*     */ 
/*     */       
/*  93 */       jse = getLastOf(elem);
/*     */ 
/*     */       
/*  96 */       if (cs != null) {
/*  97 */         Location rs = cs.getRelPosOf(
/*  98 */             jse.getEndPosition().getLine(), 
/*  99 */             jse.getEndPosition().getColumn());
/* 100 */         l.setEndLine(rs.getStartLine());
/* 101 */         l.setEndChar(rs.getStartChar());
/*     */       } else {
/* 103 */         l.setEndLine(jse.getEndPosition().getLine());
/* 104 */         l.setEndChar(jse.getEndPosition().getColumn());
/*     */       } 
/*     */     } else {
/* 107 */       l.setEndLine(0);
/* 108 */       l.setEndChar(0);
/*     */     } 
/*     */   }
/*     */   
/* 112 */   protected void restoreStripe(ModelRepository mr) { mr.setCurrentStripe((CodeStripe)this.oldStripes.pop()); }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\importer\recoder\StripeContainerListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */