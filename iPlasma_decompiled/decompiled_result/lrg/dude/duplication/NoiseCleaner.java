/*    */ package lrg.dude.duplication;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStreamReader;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NoiseCleaner
/*    */   extends CleaningDecorator
/*    */ {
/* 16 */   private final String noiseFile = "noise.dat";
/*    */ 
/*    */   
/* 19 */   public NoiseCleaner(CleaningDecorator next) { super(next); }
/*    */ 
/*    */   
/*    */   protected StringList specificClean(StringList text) {
/* 23 */     String[] noise = getNoise();
/* 24 */     for (int i = 0; i < text.size(); i++) {
/* 25 */       for (int n = 0; n < noise.length; n++)
/* 26 */       { if (text.get(i).equals(noise[n]))
/* 27 */           text.set(i, "");  } 
/* 28 */     }  return text;
/*    */   }
/*    */   
/*    */   private String[] getNoise() {
/* 32 */     ArrayList lines = new ArrayList();
/*    */     try {
/* 34 */       BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("noise.dat")));
/* 35 */       String linie = null;
/* 36 */       while ((linie = in.readLine()) != null) {
/* 37 */         lines.add(linie);
/*    */       }
/* 39 */       in.close();
/* 40 */     } catch (FileNotFoundException fe) {
/* 41 */       System.out.println("Nu exista fisierul noise.dat: " + fe);
/* 42 */     } catch (IOException ioe) {
/* 43 */       System.out.println("Eroare citire fisier : " + ioe);
/*    */     } 
/* 45 */     String[] sintacticElements = new String[lines.size()];
/* 46 */     for (int i = 0; i < sintacticElements.length; i++)
/* 47 */       sintacticElements[i] = (String)lines.get(i); 
/* 48 */     return sintacticElements;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\dude.jar!\lrg\dude\duplication\NoiseCleaner.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */