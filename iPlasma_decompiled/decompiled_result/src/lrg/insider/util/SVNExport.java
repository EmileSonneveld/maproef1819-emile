/*     */ package classes.lrg.insider.util;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.Properties;
/*     */ import lrg.common.metamodel.MetaModel;
/*     */ import lrg.insider.metamodel.MemoriaJavaModelBuilder;
/*     */ import lrg.insider.util.ExportEditor;
/*     */ import lrg.insider.util.ExportReporterBaton;
/*     */ import lrg.insider.util.MooseMSEExporter;
/*     */ import lrg.insider.util.SVNExport;
/*     */ import org.tmatesoft.svn.core.SVNErrorCode;
/*     */ import org.tmatesoft.svn.core.SVNErrorMessage;
/*     */ import org.tmatesoft.svn.core.SVNException;
/*     */ import org.tmatesoft.svn.core.SVNNodeKind;
/*     */ import org.tmatesoft.svn.core.SVNURL;
/*     */ import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
/*     */ import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
/*     */ import org.tmatesoft.svn.core.io.SVNRepository;
/*     */ import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
/*     */ import org.tmatesoft.svn.core.wc.SVNWCUtil;
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
/*     */ public class SVNExport
/*     */ {
/* 138 */   private static int endDay = 31;
/* 139 */   private static int beginDay = 1;
/* 140 */   private static int beginYear = 2004;
/* 141 */   private static int endYear = 2006;
/* 142 */   private static int beginMonth = 1;
/* 143 */   private static int endMonth = 12;
/* 144 */   private static String folder = "../../jedit";
/* 145 */   private static String SVNURI = "https://jedit.svn.sourceforge.net/svnroot/jedit/jEdit/trunk/org/gjt/sp/jedit/gui";
/* 146 */   private static int stepInDays = 240;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean deleteDir(File dir) {
/* 153 */     if (dir.isDirectory()) {
/* 154 */       String[] children = dir.list();
/* 155 */       for (int i = 0; i < children.length; i++) {
/* 156 */         boolean success = deleteDir(new File(dir, children[i]));
/* 157 */         if (!success) {
/* 158 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 164 */     return dir.delete();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) throws Exception {
/* 170 */     String configFileName = "./svnconfig.txt";
/*     */     
/* 172 */     if (args.length != 0 && args.length != 1) {
/* 173 */       System.err.println("Usage: svn2mse [configfile]");
/* 174 */       System.exit(1);
/*     */     } 
/*     */     
/* 177 */     if (args.length == 1) configFileName = args[0];
/*     */     
/* 179 */     processConfigFile(configFileName);
/* 180 */     Calendar calendar = Calendar.getInstance();
/* 181 */     Calendar endCalendar = Calendar.getInstance();
/* 182 */     endCalendar.set(endYear, endMonth - 1, endDay);
/* 183 */     calendar.set(beginYear, beginMonth - 1, beginDay); for (; calendar.before(endCalendar); calendar.add(6, stepInDays)) {
/* 184 */       Date date = calendar.getTime();
/* 185 */       int month = calendar.get(2) + 1;
/* 186 */       int day = calendar.get(5);
/* 187 */       long revisionNumber = exportVersionFromSVN(date);
/* 188 */       String dateLiniute = String.valueOf(calendar.get(1)) + "-" + (
/* 189 */         (month < 9) ? "0" : "") + month + "-" + (
/* 190 */         (day > 9) ? "" : "0") + day + 
/* 191 */         "-r" + revisionNumber;
/* 192 */       MemoriaJavaModelBuilder memoriaJavaModelBuilder = new MemoriaJavaModelBuilder("../../svn-export", "model-" + dateLiniute + ".dat", "", null);
/* 193 */       MetaModel.createFrom(memoriaJavaModelBuilder, folder);
/* 194 */       File outputFile = new File(String.valueOf(folder) + "/" + dateLiniute + ".mse");
/*     */       
/* 196 */       try { (new MooseMSEExporter(memoriaJavaModelBuilder.getCurrentSystem())).exportToStream(new PrintStream(new FileOutputStream(outputFile))); }
/* 197 */       catch (FileNotFoundException e) { throw new RuntimeException(e.getMessage()); }
/* 198 */        MetaModel.closeMetaModel();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void processConfigFile(String configFileName) {
/*     */     try {
/* 205 */       Properties svnConfig = new Properties();
/* 206 */       svnConfig.load(new FileInputStream(configFileName));
/* 207 */       SVNURI = svnConfig.getProperty("SVNURL");
/* 208 */       beginYear = Integer.parseInt(svnConfig.getProperty("BEGIN_YEAR"));
/* 209 */       beginMonth = Integer.parseInt(svnConfig.getProperty("BEGIN_MONTH"));
/* 210 */       beginDay = Integer.parseInt(svnConfig.getProperty("BEGIN_DAY"));
/*     */       
/* 212 */       endYear = Integer.parseInt(svnConfig.getProperty("END_YEAR"));
/* 213 */       endMonth = Integer.parseInt(svnConfig.getProperty("END_MONTH"));
/* 214 */       endDay = Integer.parseInt(svnConfig.getProperty("END_DAY"));
/*     */       
/* 216 */       stepInDays = Integer.parseInt(svnConfig.getProperty("DAYS_BETWEEN_SAMPLES"));
/* 217 */       folder = svnConfig.getProperty("EXPORT_FOLDER");
/* 218 */     } catch (IOException e) {
/* 219 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static long exportVersionFromSVN(Date versionDate) throws Exception {
/* 225 */     DAVRepositoryFactory.setup();
/*     */ 
/*     */     
/* 228 */     SVNURL url = SVNURL.parseURIEncoded(SVNURI);
/* 229 */     String userName = "guest";
/* 230 */     String userPassword = "";
/*     */ 
/*     */     
/* 233 */     File exportDir = new File("../../svn-export");
/* 234 */     if (exportDir.exists()) {
/* 235 */       System.out.println("Delete export folder");
/* 236 */       boolean deleted = deleteDir(exportDir);
/* 237 */       if (deleted) {
/* 238 */         System.out.println("Export folder deleted");
/*     */       } else {
/* 240 */         System.out.println("Export folder NOT deleted");
/* 241 */         SVNErrorMessage err = SVNErrorMessage.create(SVNErrorCode.IO_ERROR, "Path ''{0}'' already exists", exportDir);
/* 242 */         throw new SVNException(err);
/*     */       } 
/*     */     } 
/*     */     
/* 246 */     exportDir.mkdirs();
/*     */     
/* 248 */     SVNRepository repository = SVNRepositoryFactory.create(url);
/*     */     
/* 250 */     ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(userName, userPassword);
/* 251 */     repository.setAuthenticationManager(authManager);
/*     */     
/* 253 */     SVNNodeKind nodeKind = repository.checkPath("", -1L);
/* 254 */     if (nodeKind == SVNNodeKind.NONE) {
/* 255 */       SVNErrorMessage err = SVNErrorMessage.create(SVNErrorCode.UNKNOWN, "No entry at URL ''{0}''", url);
/* 256 */       throw new SVNException(err);
/* 257 */     }  if (nodeKind == SVNNodeKind.FILE) {
/* 258 */       SVNErrorMessage err = SVNErrorMessage.create(SVNErrorCode.UNKNOWN, "Entry at URL ''{0}'' is a file while directory was expected", url);
/* 259 */       throw new SVNException(err);
/*     */     } 
/*     */ 
/*     */     
/* 263 */     long latestRevision = repository.getDatedRevision(versionDate);
/* 264 */     System.out.println(String.valueOf(latestRevision) + " " + versionDate);
/* 265 */     ExportReporterBaton exportReporterBaton = new ExportReporterBaton(latestRevision);
/*     */     
/* 267 */     ExportEditor exportEditor1 = new ExportEditor(exportDir);
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
/* 280 */     repository.update(latestRevision, null, true, exportReporterBaton, exportEditor1);
/*     */     
/* 282 */     System.out.println("Exported revision: " + latestRevision);
/* 283 */     return latestRevision;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\inside\\util\SVNExport.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */