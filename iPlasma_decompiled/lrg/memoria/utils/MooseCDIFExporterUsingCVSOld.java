/*     */ package lrg.memoria.utils;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.text.DateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import lrg.memoria.core.System;
/*     */ import lrg.memoria.exporter.cdif.MooseCDIFExporter;
/*     */ import lrg.memoria.importer.recoder.JavaModelLoader;
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
/*     */ public class MooseCDIFExporterUsingCVSOld
/*     */ {
/*     */   private String cvs_address;
/*  71 */   private static int beginning_day = -1;
/*  72 */   private static int beginning_mounth = -1;
/*  73 */   private static int beginning_year = -1;
/*     */   
/*     */   private int sampling_rate;
/*     */   
/*     */   private String working_dir;
/*     */   
/*  79 */   private static String cvsRoot = null; public MooseCDIFExporterUsingCVSOld(String cvs_address, String beginning_date, String sampling_rate, String working_dir) { this.cvs_address = null; this.sampling_rate = -1;
/*     */     this.working_dir = null;
/*  81 */     this.todayDate = null;
/*     */ 
/*     */     
/*  84 */     this.cvs_address = cvs_address;
/*     */     
/*     */     try {
/*  87 */       int firstDateSeparatorPosition = beginning_date.indexOf('/');
/*  88 */       int seccondDateSeparatorPosition = beginning_date.indexOf('/', firstDateSeparatorPosition + 1);
/*     */       
/*  90 */       beginning_mounth = Integer.parseInt(beginning_date.substring(0, firstDateSeparatorPosition));
/*     */       
/*  92 */       beginning_mounth--;
/*     */ 
/*     */       
/*  95 */       beginning_day = Integer.parseInt(beginning_date.substring(firstDateSeparatorPosition + 1, seccondDateSeparatorPosition));
/*     */       
/*  97 */       if (beginning_date.length() - seccondDateSeparatorPosition + 1 == 4) {
/*  98 */         beginning_year = Integer.parseInt(beginning_date.substring(seccondDateSeparatorPosition + 1, beginning_date.length()));
/*     */       } else {
/* 100 */         throw new Exception("year");
/*     */       } 
/* 102 */     } catch (Exception excDate) {
/* 103 */       System.err.println("[MooseCDIFExporterUsingCVS] Bad date!\nExample: mm/dd/yyyy");
/* 104 */       System.exit(1);
/*     */     } 
/*     */     
/* 107 */     this.sampling_rate = Integer.parseInt(sampling_rate);
/*     */     
/* 109 */     this.working_dir = working_dir;
/*     */     
/* 111 */     this.todayDate = new Date(); }
/*     */   
/*     */   private Date todayDate;
/*     */   private String getTodayDate() {
/* 115 */     DateFormat todayDateFormat = DateFormat.getDateInstance(3);
/*     */     
/* 117 */     return todayDateFormat.format(this.todayDate);
/*     */   }
/*     */   
/*     */   public void computeData(int beginning_year, int beginning_mounth, int beginning_day) {
/* 121 */     GregorianCalendar cvs_co_evidence = new GregorianCalendar(beginning_year, beginning_mounth, beginning_day);
/*     */     
/* 123 */     Date cvs_co_evidence_date = cvs_co_evidence.getTime();
/*     */     
/* 125 */     while (cvs_co_evidence_date.before(this.todayDate)) {
/* 126 */       DateFormat df = DateFormat.getDateInstance(3);
/*     */       
/* 128 */       if (checkOutFromCVS(this.cvs_address, this.working_dir, df.format(cvs_co_evidence_date))) {
/* 129 */         convertProjectToCDIF(this.cvs_address, this.working_dir, String.valueOf(cvs_co_evidence.get(1)) + "-" + (cvs_co_evidence.get(2) + 1) + "-" + cvs_co_evidence.get(5));
/* 130 */         if (!delete(this.working_dir)) {
/* 131 */           System.out.println("[MooseCDIFExporterUsingCVS] Directory " + this.working_dir + " was not successfully deleted !");
/* 132 */           System.exit(1);
/*     */         } 
/* 134 */         System.out.println();
/*     */       } 
/*     */       
/* 137 */       cvs_co_evidence.add(5, this.sampling_rate);
/* 138 */       cvs_co_evidence_date = cvs_co_evidence.getTime();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setCVSROOT(String cvsRoot) {
/* 144 */     System.out.println("Setting the CVSROOT=" + cvsRoot + " ...");
/*     */     
/*     */     try {
/* 147 */       Process cvsRootSetProcess = Runtime.getRuntime().exec("cvs -d " + cvsRoot + " login");
/*     */       
/* 149 */       StringBuffer inBuffer = new StringBuffer();
/* 150 */       InputStream inStream = cvsRootSetProcess.getInputStream();
/*     */ 
/*     */       
/* 153 */       StringBuffer errBuffer = new StringBuffer();
/* 154 */       InputStream errStream = cvsRootSetProcess.getErrorStream();
/*     */ 
/*     */       
/* 157 */       if (cvsRootSetProcess.waitFor() == 0) {
/* 158 */         inStream.close();
/* 159 */         errStream.close();
/* 160 */         return true;
/*     */       } 
/* 162 */       return false;
/*     */     }
/* 164 */     catch (Throwable t) {
/* 165 */       System.out.println("\n\n[MooseCDIFExporterUsingCVS] Error when setting CVSROOT!\n");
/* 166 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean checkOutFromCVS(String cvs_address, String working_dir, String dateString) {
/* 172 */     System.out.println("Checking out from cvs the version from " + dateString + " ...");
/*     */     
/*     */     try {
/* 175 */       Process currentCVSCheckout = Runtime.getRuntime().exec("cvs -d " + cvsRoot + " checkout -D " + dateString + " -f -P -d " + working_dir + " " + cvs_address);
/*     */       
/* 177 */       StringBuffer inBuffer = new StringBuffer();
/* 178 */       InputStream inStream = currentCVSCheckout.getInputStream();
/*     */ 
/*     */       
/* 181 */       StringBuffer errBuffer = new StringBuffer();
/* 182 */       InputStream errStream = currentCVSCheckout.getErrorStream();
/*     */ 
/*     */       
/* 185 */       if (currentCVSCheckout.waitFor() == 0) {
/* 186 */         inStream.close();
/* 187 */         errStream.close();
/* 188 */         return true;
/*     */       } 
/* 190 */       return false;
/*     */     }
/* 192 */     catch (Throwable t) {
/* 193 */       System.out.println("\n\n[MooseCDIFExporterUsingCVS] Error when executing: cvs co for the " + cvs_address + " (" + dateString + ") !!!\n");
/* 194 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void convertProjectToCDIF(String path, String working_dir, String time_stamp) {
/* 200 */     System.out.println("Export " + working_dir + " from " + time_stamp + " to cdif format ...");
/*     */     
/* 202 */     String cache_path = String.valueOf(working_dir) + "_" + time_stamp + ".tempCache";
/*     */     
/* 204 */     String additional_library_path = "";
/*     */     
/* 206 */     String cdif_file_name = String.valueOf(working_dir) + "_" + time_stamp + ".cdif";
/*     */ 
/*     */     
/* 209 */     System.setOut(System.err);
/*     */     
/* 211 */     String error_file = String.valueOf(working_dir) + "_" + time_stamp + ".errLog";
/* 212 */     File err = new File(error_file);
/*     */     
/*     */     try {
/* 215 */       err.createNewFile();
/* 216 */       Logger errorLogger = new Logger(new FileOutputStream(err));
/* 217 */       System.setOut(errorLogger);
/* 218 */       System.setErr(errorLogger);
/*     */       
/* 220 */       System.err.println("Building: JavaModelLoader for source_path = " + working_dir);
/* 221 */       JavaModelLoader model = new JavaModelLoader(working_dir, cache_path, additional_library_path, null);
/*     */       
/* 223 */       System mySystem = model.getSystem();
/* 224 */       MooseCDIFExporter exporter = new MooseCDIFExporter(mySystem);
/* 225 */       File file = new File(cdif_file_name);
/* 226 */       System.out.println("Writing the CDIF file for the path: " + working_dir);
/* 227 */       System.out.println("For further details please consult: " + error_file);
/* 228 */       exporter.exportToStream(new PrintStream(new FileOutputStream(file)));
/*     */       
/* 230 */       errorLogger.close();
/*     */     }
/* 232 */     catch (Exception pex) {
/* 233 */       System.out.println("[MooseCDIFExporterUsingCVS] Error !!!\nCDIF file generation aborted !!!");
/* 234 */       pex.printStackTrace();
/* 235 */       System.exit(6);
/*     */     } 
/*     */     
/* 238 */     System.out.println("Done!");
/*     */   }
/*     */   
/*     */   private boolean delete(String working_dir) {
/* 242 */     System.out.println("Deleting the " + working_dir + " directory ...");
/*     */     
/* 244 */     return delete(new File(working_dir));
/*     */   }
/*     */   
/*     */   private boolean delete(File dir) {
/* 248 */     boolean success = true;
/*     */     
/* 250 */     File[] files = dir.listFiles();
/* 251 */     if (files != null) {
/* 252 */       for (int i = 0; i < files.length; i++) {
/* 253 */         File f = files[i];
/* 254 */         if (f.isDirectory()) {
/*     */           
/* 256 */           if (!delete(f)) {
/* 257 */             success = false;
/* 258 */             System.out.println("[MooseCDIFExporterUsingCVS] Failed to delete dir: " + f.getAbsolutePath());
/*     */           }
/*     */         
/*     */         }
/* 262 */         else if (!f.delete()) {
/* 263 */           success = false;
/* 264 */           System.out.println("[MooseCDIFExporterUsingCVS] Failed to delete file: " + f.getAbsolutePath());
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 270 */     if (!dir.delete()) {
/* 271 */       success = false;
/* 272 */       System.out.println("Failed to delete dir: " + dir.getAbsolutePath());
/*     */     } 
/*     */     
/* 275 */     return success;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 280 */     if (args.length != 4) {
/* 281 */       System.err.println("Usage: MooseCDIFExporterUsingCVS cvs_address beginning_date sampling_rate working_dir");
/* 282 */       System.exit(1);
/*     */     } 
/*     */     
/* 285 */     MooseCDIFExporterUsingCVSOld mooseCDIFExporterUsingCVS = new MooseCDIFExporterUsingCVSOld(args[0], args[1], args[2], args[3]);
/*     */     
/* 287 */     System.out.println("Today is " + mooseCDIFExporterUsingCVS.getTodayDate() + ".\n");
/*     */     
/* 289 */     cvsRoot = new String(":pserver:guest@cvs.tigris.org:/cvs");
/*     */     
/* 291 */     if (mooseCDIFExporterUsingCVS.setCVSROOT(cvsRoot)) {
/* 292 */       mooseCDIFExporterUsingCVS.computeData(beginning_year, beginning_mounth, beginning_day);
/*     */     } else {
/* 294 */       System.out.println("[MooseCDIFExporterUsingCVS] Error while setting CVSROOT!\n");
/* 295 */       System.exit(1);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memori\\utils\MooseCDIFExporterUsingCVSOld.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */