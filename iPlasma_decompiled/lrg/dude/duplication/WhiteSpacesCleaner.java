/*    */ package lrg.dude.duplication;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WhiteSpacesCleaner
/*    */   extends CleaningDecorator
/*    */ {
/* 13 */   public WhiteSpacesCleaner(CleaningDecorator next) { super(next); }
/*    */ 
/*    */   
/*    */   public StringList specificClean(StringList text) {
/* 17 */     String[] whitespace = getWhiteSpace();
/* 18 */     String currentLine = null;
/* 19 */     for (int i = 0; i < text.size(); i++) {
/* 20 */       for (int w = 0; w < whitespace.length; w++) {
/* 21 */         text.set(i, text.get(i).replaceAll(whitespace[w], ""));
/*    */       }
/*    */     } 
/* 24 */     return text;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private String[] getWhiteSpace() {
/* 35 */     String[] whitespace = new String[3];
/* 36 */     whitespace[0] = " ";
/* 37 */     whitespace[1] = "\t";
/* 38 */     whitespace[2] = "\n";
/*    */     
/* 40 */     return whitespace;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\dude.jar!\lrg\dude\duplication\WhiteSpacesCleaner.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */