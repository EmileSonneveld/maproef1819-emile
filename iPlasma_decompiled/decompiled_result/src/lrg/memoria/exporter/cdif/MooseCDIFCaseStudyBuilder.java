/*    */ package lrg.memoria.exporter.cdif;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.InputStream;
/*    */ import java.io.InputStreamReader;
/*    */ import lrg.memoria.utils.ConfigFileReader;
/*    */ 
/*    */ 
/*    */ public class MooseCDIFCaseStudyBuilder
/*    */ {
/*    */   public static void main(String[] args) {
/* 12 */     if (args.length != 1) {
/* 13 */       System.out.println("Usage: java MooseCDIFCaseStudyBuilder config_file");
/* 14 */       System.exit(1);
/*    */     } 
/*    */     try {
/* 17 */       ConfigFileReader cfr = new ConfigFileReader(args[0]);
/*    */       
/* 19 */       String mainClass = cfr.readLine();
/* 20 */       String classPath = cfr.readLine();
/* 21 */       String sources = cfr.readLine();
/* 22 */       while (sources != null) {
/* 23 */         String cache = cfr.readLine();
/* 24 */         String libraries = cfr.readLine();
/* 25 */         String cdif_file = cfr.readLine();
/* 26 */         String error_file = cfr.readLine();
/*    */         
/* 28 */         System.out.println("Running java -classpath " + classPath + " " + mainClass + " " + sources + " " + cache + " " + libraries + " " + cdif_file + error_file);
/*    */         try {
/* 30 */           Process currentCDIFExporter = Runtime.getRuntime().exec("java -Xmx350M -Xss16m -classpath " + classPath + " " + mainClass + " " + sources + " " + cache + " " + libraries + " " + cdif_file + " " + error_file);
/* 31 */           InputStream stderr = currentCDIFExporter.getErrorStream();
/* 32 */           BufferedReader br = new BufferedReader(new InputStreamReader(stderr));
/* 33 */           String line = null;
/* 34 */           while ((line = br.readLine()) != null)
/* 35 */             System.out.println(line); 
/* 36 */           InputStream stdout = currentCDIFExporter.getInputStream();
/* 37 */           br = new BufferedReader(new InputStreamReader(stdout));
/* 38 */           line = null;
/* 39 */           while ((line = br.readLine()) != null)
/* 40 */             System.out.println(line); 
/* 41 */           currentCDIFExporter.waitFor();
/* 42 */         } catch (Throwable t) {
/* 43 */           System.out.println("\n\nbububububub\n\n\n");
/*    */         } 
/* 45 */         sources = cfr.readLine();
/*    */       } 
/* 47 */       cfr.close();
/* 48 */     } catch (Exception e) {
/* 49 */       e.printStackTrace();
/* 50 */       System.out.println(e);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memoria\exporter\cdif\MooseCDIFCaseStudyBuilder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */