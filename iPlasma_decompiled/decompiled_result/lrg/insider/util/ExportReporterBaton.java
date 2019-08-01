/*    */ package classes.lrg.insider.util;
/*    */ 
/*    */ import lrg.insider.util.ExportReporterBaton;
/*    */ import org.tmatesoft.svn.core.SVNException;
/*    */ import org.tmatesoft.svn.core.io.ISVNReporter;
/*    */ import org.tmatesoft.svn.core.io.ISVNReporterBaton;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class ExportReporterBaton
/*    */   implements ISVNReporterBaton
/*    */ {
/*    */   private long exportRevision;
/*    */   
/* 27 */   public ExportReporterBaton(long revision) { this.exportRevision = revision; }
/*    */ 
/*    */   
/*    */   public void report(ISVNReporter reporter) throws SVNException {
/*    */     try {
/* 32 */       reporter.setPath("", null, this.exportRevision, true);
/* 33 */       reporter.finishReport();
/* 34 */     } catch (SVNException svne) {
/* 35 */       reporter.abortReport();
/* 36 */       System.out.println("Report failed");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\inside\\util\ExportReporterBaton.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */