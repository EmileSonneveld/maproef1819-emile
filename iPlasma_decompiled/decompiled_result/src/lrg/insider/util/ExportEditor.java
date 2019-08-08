/*     */ package classes.lrg.insider.util;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import lrg.insider.util.ExportEditor;
/*     */ import org.tmatesoft.svn.core.SVNCommitInfo;
/*     */ import org.tmatesoft.svn.core.SVNErrorCode;
/*     */ import org.tmatesoft.svn.core.SVNErrorMessage;
/*     */ import org.tmatesoft.svn.core.SVNException;
/*     */ import org.tmatesoft.svn.core.io.ISVNEditor;
/*     */ import org.tmatesoft.svn.core.io.diff.SVNDeltaProcessor;
/*     */ import org.tmatesoft.svn.core.io.diff.SVNDiffWindow;
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
/*     */ class ExportEditor
/*     */   implements ISVNEditor
/*     */ {
/*     */   private File myRootDirectory;
/*     */   private SVNDeltaProcessor myDeltaProcessor;
/*     */   
/*     */   public ExportEditor(File root) {
/*  48 */     this.myRootDirectory = root;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  54 */     this.myDeltaProcessor = new SVNDeltaProcessor();
/*     */   }
/*     */ 
/*     */   
/*     */   public void targetRevision(long revision) throws SVNException {}
/*     */ 
/*     */   
/*     */   public void openRoot(long revision) throws SVNException {}
/*     */   
/*     */   public void addDir(String path, String copyFromPath, long copyFromRevision) throws SVNException {
/*  64 */     File newDir = new File(this.myRootDirectory, path);
/*  65 */     if (!newDir.exists() && 
/*  66 */       !newDir.mkdirs()) {
/*  67 */       SVNErrorMessage err = SVNErrorMessage.create(SVNErrorCode.IO_ERROR, "error: failed to add the directory ''{0}''.", newDir);
/*  68 */       throw new SVNException(err);
/*     */     } 
/*     */     
/*  71 */     System.out.println("dir added: " + path);
/*     */   }
/*     */ 
/*     */   
/*     */   public void openDir(String path, long revision) throws SVNException {}
/*     */ 
/*     */   
/*     */   public void changeDirProperty(String name, String value) throws SVNException {}
/*     */   
/*     */   public void addFile(String path, String copyFromPath, long copyFromRevision) throws SVNException {
/*  81 */     File file = new File(this.myRootDirectory, path);
/*  82 */     if (file.exists()) {
/*  83 */       SVNErrorMessage err = SVNErrorMessage.create(SVNErrorCode.IO_ERROR, "error: exported file ''{0}'' already exists!", file);
/*  84 */       throw new SVNException(err);
/*     */     } 
/*     */     
/*     */     try {
/*  88 */       file.createNewFile();
/*  89 */     } catch (IOException e) {
/*  90 */       SVNErrorMessage err = SVNErrorMessage.create(SVNErrorCode.IO_ERROR, "error: cannot create new  file ''{0}''", file);
/*  91 */       throw new SVNException(err);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void openFile(String path, long revision) throws SVNException {}
/*     */ 
/*     */   
/*     */   public void changeFileProperty(String path, String name, String value) throws SVNException {}
/*     */   
/* 101 */   public void applyTextDelta(String path, String baseChecksum) throws SVNException { this.myDeltaProcessor.applyTextDelta(null, new File(this.myRootDirectory, path), false); }
/*     */ 
/*     */ 
/*     */   
/* 105 */   public OutputStream textDeltaChunk(String path, SVNDiffWindow diffWindow) throws SVNException { return this.myDeltaProcessor.textDeltaChunk(diffWindow); }
/*     */ 
/*     */ 
/*     */   
/* 109 */   public void textDeltaEnd(String path) throws SVNException { this.myDeltaProcessor.textDeltaEnd(); }
/*     */ 
/*     */ 
/*     */   
/* 113 */   public void closeFile(String path, String textChecksum) throws SVNException { System.out.println("file added: " + path); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void closeDir() throws SVNException {}
/*     */ 
/*     */   
/*     */   public void deleteEntry(String path, long revision) throws SVNException {}
/*     */ 
/*     */   
/*     */   public void absentDir(String path) throws SVNException {}
/*     */ 
/*     */   
/*     */   public void absentFile(String path) throws SVNException {}
/*     */ 
/*     */   
/* 129 */   public SVNCommitInfo closeEdit() throws SVNException { return null; }
/*     */   
/*     */   public void abortEdit() throws SVNException {}
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\inside\\util\ExportEditor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */