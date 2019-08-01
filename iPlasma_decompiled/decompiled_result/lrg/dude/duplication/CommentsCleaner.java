/*     */ package lrg.dude.duplication;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CommentsCleaner
/*     */   extends CleaningDecorator
/*     */ {
/*  13 */   private final String sStartComment = "/*";
/*  14 */   private final String sEndComment = "*/";
/*  15 */   private final String sLineComments = "//";
/*     */ 
/*     */   
/*  18 */   public CommentsCleaner(CleaningDecorator next) { super(next); }
/*     */ 
/*     */   
/*     */   protected StringList specificClean(StringList text) {
/*  22 */     boolean openComment = false;
/*  23 */     for (int i = 0; i < text.size(); i++) {
/*     */       
/*  25 */       if (openComment) {
/*  26 */         int iEndComment = getIndexOf("*/", text.get(i));
/*  27 */         if (iEndComment >= 0) {
/*     */           
/*  29 */           text.set(i, text.get(i).substring(iEndComment + "*/".length()));
/*  30 */           openComment = false;
/*     */         } else {
/*  32 */           text.set(i, "");
/*     */         } 
/*  34 */       }  while (containsComments(text.get(i), openComment)) {
/*  35 */         int iStartComment = getIndexOf("/*", text.get(i));
/*  36 */         int iLineComment = getIndexOf("//", text.get(i));
/*     */         
/*  38 */         if (iStartComment >= 0) {
/*  39 */           if (iLineComment >= 0 && iLineComment < iStartComment) {
/*     */             
/*  41 */             text.set(i, text.get(i).substring(0, iLineComment));
/*     */             continue;
/*     */           } 
/*  44 */           int iEndComment = getIndexOf("*/", text.get(i));
/*  45 */           if (iEndComment >= 0) {
/*  46 */             text.set(i, String.valueOf(text.get(i).substring(0, iStartComment)) + 
/*  47 */                 text.get(i).substring(iEndComment + "*/".length()));
/*     */             continue;
/*     */           } 
/*  50 */           openComment = true;
/*  51 */           text.set(i, text.get(i).substring(0, iStartComment));
/*     */           continue;
/*     */         } 
/*  54 */         if (iLineComment >= 0)
/*  55 */           text.set(i, text.get(i).substring(0, iLineComment)); 
/*     */       } 
/*     */     } 
/*  58 */     return text;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean containsComments(String s, boolean openComment) {
/*  63 */     String temp = new String(s);
/*  64 */     temp = temp.replaceAll("\".*[^\\\\]\"", "");
/*  65 */     if (temp.indexOf("//") >= 0)
/*  66 */       return true; 
/*  67 */     if (temp.indexOf("/*") >= 0)
/*  68 */       return true; 
/*  69 */     if (temp.indexOf("*/") >= 0 && openComment)
/*  70 */       return true; 
/*  71 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private int getIndexOf(String substr, String line) {
/*  76 */     boolean finished = false;
/*  77 */     int lastIndex = -1;
/*     */     
/*  79 */     while (!finished) {
/*  80 */       int index = line.indexOf(substr, lastIndex + 1);
/*  81 */       if (index == -1) {
/*  82 */         finished = true; continue;
/*     */       } 
/*  84 */       if (!insideString(line, index, substr.length())) {
/*  85 */         return index;
/*     */       }
/*  87 */       lastIndex = index;
/*     */     } 
/*     */     
/*  90 */     return -1;
/*     */   }
/*     */   
/*     */   private boolean insideString(String line, int index, int length) {
/*  94 */     String front = line.substring(0, index);
/*  95 */     String back = line.substring(index + length);
/*  96 */     front = front.replaceAll("\".*[^\\\\]\"", "");
/*  97 */     back = back.replaceAll("\".*[^\\\\]\"", "");
/*  98 */     if (front.indexOf("\"") > -1 && back.indexOf("\"") > -1)
/*  99 */       return true; 
/* 100 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\dude.jar!\lrg\dude\duplication\CommentsCleaner.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */