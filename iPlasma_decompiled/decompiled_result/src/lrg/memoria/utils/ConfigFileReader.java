/*    */ package lrg.memoria.utils;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.File;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.FileReader;
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ConfigFileReader
/*    */ {
/*    */   private BufferedReader br;
/*    */   private String fileName;
/*    */   
/*    */   public ConfigFileReader(String fileName) {
/* 17 */     this.fileName = fileName;
/*    */     try {
/* 19 */       this.br = new BufferedReader(new FileReader(new File(fileName)));
/* 20 */     } catch (FileNotFoundException e) {
/* 21 */       System.out.println("ERROR: File \"" + fileName + "\" does not exist !!!");
/* 22 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String readLine() {
/* 33 */     String line = null;
/*    */     try {
/*    */       boolean bol;
/*    */       do {
/* 37 */         bol = false;
/* 38 */         line = this.br.readLine();
/* 39 */         if (line != null && line.startsWith("-"))
/* 40 */           bol = true; 
/* 41 */         if (line == null || !line.startsWith("#"))
/* 42 */           continue;  bol = true;
/* 43 */         System.out.println("Echo > " + line);
/*    */       }
/* 45 */       while (bol);
/*    */     }
/* 47 */     catch (IOException e) {
/* 48 */       System.out.println("ERROR: reading from " + this.fileName);
/* 49 */       System.out.println(e);
/*    */     } 
/* 51 */     return line;
/*    */   }
/*    */   
/*    */   public void close() {
/*    */     try {
/* 56 */       this.br.close();
/* 57 */     } catch (IOException e) {
/* 58 */       System.out.println("ERROR: Unable to close file: " + this.fileName);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\memoria.jar!\lrg\memori\\utils\ConfigFileReader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */