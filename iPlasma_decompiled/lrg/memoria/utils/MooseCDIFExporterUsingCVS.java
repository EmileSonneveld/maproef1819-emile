/*     */ package lrg.memoria.utils;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.text.DateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.Locale;
/*     */ import lrg.memoria.core.System;
/*     */ import lrg.memoria.exporter.cdif.MooseCDIFExporter;
/*     */ import lrg.memoria.importer.recoder.JavaModelLoader;
/*     */ import org.netbeans.lib.cvsclient.Client;
/*     */ import org.netbeans.lib.cvsclient.admin.StandardAdminHandler;
/*     */ import org.netbeans.lib.cvsclient.command.CommandException;
/*     */ import org.netbeans.lib.cvsclient.command.GlobalOptions;
/*     */ import org.netbeans.lib.cvsclient.command.checkout.CheckoutCommand;
/*     */ import org.netbeans.lib.cvsclient.connection.AuthenticationException;
/*     */ import org.netbeans.lib.cvsclient.connection.PServerConnection;
/*     */ import org.netbeans.lib.cvsclient.connection.StandardScrambler;
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
/*     */ public class MooseCDIFExporterUsingCVS
/*     */ {
/*     */   private Client client;
/*     */   private GlobalOptions globalOptions;
/*     */   private String cvs_address;
/*  62 */   private static int beginning_day = -1;
/*  63 */   private static int beginning_mounth = -1;
/*  64 */   private static int beginning_year = -1;
/*     */   
/*     */   private int sampling_rate;
/*     */   
/*     */   private String working_dir;
/*     */   
/*  70 */   private static String cvsRoot = null; public MooseCDIFExporterUsingCVS(String cvs_address, String beginning_date, String sampling_rate, String working_dir) { this.client = null; this.globalOptions = null; this.cvs_address = null; this.sampling_rate = -1;
/*     */     this.working_dir = null;
/*  72 */     this.todayDate = null;
/*     */ 
/*     */     
/*  75 */     this.cvs_address = cvs_address;
/*     */     
/*     */     try {
/*  78 */       int firstDateSeparatorPosition = beginning_date.indexOf('/');
/*  79 */       int seccondDateSeparatorPosition = beginning_date.indexOf('/', firstDateSeparatorPosition + 1);
/*     */       
/*  81 */       beginning_mounth = Integer.parseInt(beginning_date.substring(0, firstDateSeparatorPosition));
/*     */       
/*  83 */       beginning_mounth--;
/*     */ 
/*     */       
/*  86 */       beginning_day = Integer.parseInt(beginning_date.substring(firstDateSeparatorPosition + 1, seccondDateSeparatorPosition));
/*     */       
/*  88 */       if (beginning_date.length() - seccondDateSeparatorPosition + 1 == 4) {
/*  89 */         beginning_year = Integer.parseInt(beginning_date.substring(seccondDateSeparatorPosition + 1, beginning_date.length()));
/*     */       } else {
/*  91 */         throw new Exception("year");
/*     */       } 
/*  93 */     } catch (Exception excDate) {
/*  94 */       System.err.println("[MooseCDIFExporterUsingCVS] Bad date!\nExample: mm/dd/yyyy");
/*  95 */       System.exit(1);
/*     */     } 
/*     */     
/*  98 */     this.sampling_rate = Integer.parseInt(sampling_rate);
/*     */     
/* 100 */     this.working_dir = working_dir;
/*     */     
/* 102 */     this.todayDate = new Date(); }
/*     */   
/*     */   private Date todayDate;
/*     */   private String getTodayDate() {
/* 106 */     DateFormat todayDateFormat = DateFormat.getDateInstance(3);
/*     */     
/* 108 */     return todayDateFormat.format(this.todayDate);
/*     */   }
/*     */   
/*     */   public void computeData(int beginning_year, int beginning_mounth, int beginning_day) {
/* 112 */     GregorianCalendar cvs_co_evidence = new GregorianCalendar(beginning_year, beginning_mounth, beginning_day);
/*     */     
/* 114 */     Date cvs_co_evidence_date = cvs_co_evidence.getTime();
/*     */     
/* 116 */     while (cvs_co_evidence_date.before(this.todayDate)) {
/* 117 */       DateFormat df = DateFormat.getDateInstance(3, Locale.US);
/*     */       
/* 119 */       if (checkOutFromCVS(this.cvs_address, this.working_dir, df.format(cvs_co_evidence_date))) {
/* 120 */         convertProjectToCDIF(this.cvs_address, this.working_dir, String.valueOf(cvs_co_evidence.get(1)) + "-" + (cvs_co_evidence.get(2) + 1) + "-" + cvs_co_evidence.get(5));
/* 121 */         if (!delete(this.working_dir)) {
/* 122 */           System.out.println("[MooseCDIFExporterUsingCVS] Directory " + this.working_dir + " was not successfully deleted !");
/* 123 */           System.exit(1);
/*     */         } 
/* 125 */         System.out.println();
/*     */       } 
/*     */       
/* 128 */       cvs_co_evidence.add(5, this.sampling_rate);
/* 129 */       cvs_co_evidence_date = cvs_co_evidence.getTime();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private PServerConnection establishConnection() throws AuthenticationException {
/* 135 */     PServerConnection connection = new PServerConnection();
/* 136 */     connection.setUserName("guest");
/* 137 */     connection.setEncodedPassword(StandardScrambler.getInstance().scramble("guest"));
/* 138 */     connection.setHostName("cvs.tigris.org");
/* 139 */     connection.setRepository("/cvs");
/* 140 */     connection.setPort(2401);
/*     */     try {
/* 142 */       connection.open();
/* 143 */     } catch (Exception exception) {}
/* 144 */     return connection;
/*     */   }
/*     */   
/*     */   private void createClient(PServerConnection connection) {
/* 148 */     this.client = new Client(connection, new StandardAdminHandler());
/* 149 */     this.client.getEventManager().addCVSListener(new BasicListener());
/*     */   }
/*     */   
/*     */   public boolean setCVSROOT(String cvsRoot) {
/* 153 */     System.out.println("Setting the CVSROOT=" + cvsRoot + " ...");
/* 154 */     this.globalOptions = new GlobalOptions();
/* 155 */     this.globalOptions.setCVSRoot(cvsRoot);
/* 156 */     return true;
/*     */   }
/*     */   
/*     */   private boolean checkOutFromCVS(String cvs_address, String working_dir, String dateString) {
/* 160 */     System.out.println("Checking out from cvs the version from " + dateString + " ...");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 169 */     System.out.println(dateString);
/*     */     
/* 171 */     CheckoutCommand checkoutCommand = new CheckoutCommand(true, cvs_address);
/* 172 */     checkoutCommand.setRecursive(true);
/* 173 */     checkoutCommand.setCheckoutByDate(dateString);
/* 174 */     checkoutCommand.setPruneDirectories(true);
/*     */     
/*     */     try {
/* 177 */       createClient(establishConnection());
/* 178 */       this.client.setLocalPath(working_dir);
/* 179 */       this.client.executeCommand(checkoutCommand, this.globalOptions);
/* 180 */     } catch (CommandException e) {
/* 181 */       e.printStackTrace();
/* 182 */       return false;
/*     */     }
/* 184 */     catch (AuthenticationException e) {
/* 185 */       e.printStackTrace();
/* 186 */       return false;
/*     */     } 
/*     */     
/* 189 */     return true;
/*     */   }
/*     */   
/*     */   private void convertProjectToCDIF(String path, String working_dir, String time_stamp) {
/* 193 */     System.out.println("Export " + working_dir + " from " + time_stamp + " to cdif format ...");
/*     */     
/* 195 */     String cache_path = String.valueOf(working_dir) + "_" + time_stamp + ".dat";
/*     */     
/* 197 */     String additional_library_path = "";
/*     */     
/* 199 */     String cdif_file_name = String.valueOf(working_dir) + "_" + time_stamp + ".cdif";
/*     */ 
/*     */     
/* 202 */     System.setOut(System.err);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 213 */       System.err.println("Building: JavaModelLoader for source_path = " + working_dir);
/* 214 */       JavaModelLoader model = new JavaModelLoader(working_dir, cache_path, additional_library_path, null);
/*     */       
/* 216 */       System mySystem = model.getSystem();
/* 217 */       MooseCDIFExporter exporter = new MooseCDIFExporter(mySystem);
/* 218 */       File file = new File(cdif_file_name);
/* 219 */       System.out.println("Writing the CDIF file for the path: " + working_dir);
/*     */       
/* 221 */       exporter.exportToStream(new PrintStream(new FileOutputStream(file)));
/*     */       
/* 223 */       System.out.println("Unloading System from Memory!");
/* 224 */       System.unloadSystemFromMemory(mySystem);
/*     */     
/*     */     }
/* 227 */     catch (Exception pex) {
/* 228 */       System.out.println("[MooseCDIFExporterUsingCVS] Error !!!\nCDIF file generation aborted !!!");
/* 229 */       pex.printStackTrace();
/* 230 */       System.exit(6);
/*     */     } 
/*     */     
/* 233 */     System.out.println("Done!");
/*     */   }
/*     */   
/*     */   private boolean delete(String working_dir) {
/* 237 */     System.out.println("Deleting the " + working_dir + " directory ...");
/*     */     
/* 239 */     return delete(new File(working_dir));
/*     */   }
/*     */   
/*     */   private boolean delete(File dir) {
/* 243 */     boolean success = true;
/*     */     
/* 245 */     File[] files = dir.listFiles();
/* 246 */     if (files != null) {
/* 247 */       for (int i = 0; i < files.length; i++) {
/* 248 */         File f = files[i];
/* 249 */         if (f.isDirectory()) {
/*     */           
/* 251 */           if (!delete(f)) {
/* 252 */             success = false;
/* 253 */             System.out.println("[MooseCDIFExporterUsingCVS] Failed to delete dir: " + f.getAbsolutePath());
/*     */           }
/*     */         
/*     */         }
/* 257 */         else if (!f.delete()) {
/* 258 */           success = false;
/* 259 */           System.out.println("[MooseCDIFExporterUsingCVS] Failed to delete file: " + f.getAbsolutePath());
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 265 */     if (!dir.delete()) {
/* 266 */       success = false;
/* 267 */       System.out.println("Failed to delete dir: " + dir.getAbsolutePath());
/*     */     } 
/*     */     
/* 270 */     return success;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 275 */     if (args.length != 4) {
/* 276 */       System.err.println("Usage: MooseCDIFExporterUsingCVS cvs_address beginning_date sampling_rate working_dir");
/* 277 */       System.exit(1);
/*     */     } 
/*     */     
/* 280 */     MooseCDIFExporterUsingCVS mooseCDIFExporterUsingCVS = new MooseCDIFExporterUsingCVS(args[0], args[1], args[2], args[3]);
/*     */     
/* 282 */     System.out.println("Today is " + mooseCDIFExporterUsingCVS.getTodayDate() + ".\n");
/*     */     
/* 284 */     cvsRoot = new String(":pserver:guest@cvs.tigris.org:/cvs");
/*     */     
/* 286 */     if (mooseCDIFExporterUsingCVS.setCVSROOT(cvsRoot)) {
/* 287 */       mooseCDIFExporterUsingCVS.computeData(beginning_year, beginning_mounth, beginning_day);
/*     */     } else {
/* 289 */       System.out.println("[MooseCDIFExporterUsingCVS] Error while setting CVSROOT!\n");
/* 290 */       System.exit(1);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memori\\utils\MooseCDIFExporterUsingCVS.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */