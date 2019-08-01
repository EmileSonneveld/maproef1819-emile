/*    */ package lrg.dude.duplication;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStreamReader;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SourceFile
/*    */   implements Entity
/*    */ {
/*    */   private String fileName;
/*    */   private StringList codelines;
/*    */   private int noOfRelevantLines;
/*    */   
/*    */   public SourceFile(File file, String shortName) {
/* 21 */     this.noOfRelevantLines = 0;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 29 */     this.fileName = shortName;
/* 30 */     this.codelines = new StringList();
/* 31 */     int i = 0;
/*    */     try {
/* 33 */       BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
/* 34 */       String linie = null;
/* 35 */       while ((linie = in.readLine()) != null) {
/* 36 */         this.codelines.add(linie);
/*    */       }
/* 38 */       in.close();
/* 39 */     } catch (FileNotFoundException fe) {
/* 40 */       System.out.println("Nu exista fisierul " + file + ": " + fe);
/* 41 */     } catch (IOException ioe) {
/* 42 */       System.out.println("Eroare citire fisier : " + ioe);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/* 47 */   public String getName() { return this.fileName; }
/*    */ 
/*    */ 
/*    */   
/* 51 */   public StringList getCode() { return this.codelines; }
/*    */ 
/*    */ 
/*    */   
/* 55 */   public int getNoOfRelevantLines() { return this.noOfRelevantLines; }
/*    */ 
/*    */ 
/*    */   
/* 59 */   public void setNoOfRelevantLines(int norl) { this.noOfRelevantLines = norl; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\dude.jar!\lrg\dude\duplication\SourceFile.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */