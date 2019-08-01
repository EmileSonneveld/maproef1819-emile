/*    */ package lrg.dude.duplication;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Parameters
/*    */ {
/*    */   private int minLength;
/*    */   private int maxLineBias;
/*    */   private int minExactChunk;
/*    */   private boolean considerComments;
/*    */   
/*    */   public Parameters(int minLength, int maxLineBias, int minExactChunk, boolean considerComments) {
/* 17 */     this.minLength = minLength;
/* 18 */     this.maxLineBias = maxLineBias;
/* 19 */     this.minExactChunk = minExactChunk;
/* 20 */     this.considerComments = considerComments;
/*    */   }
/*    */ 
/*    */   
/* 24 */   public int getMinLength() { return this.minLength; }
/*    */ 
/*    */ 
/*    */   
/* 28 */   public int getMaxLineBias() { return this.maxLineBias; }
/*    */ 
/*    */ 
/*    */   
/* 32 */   public int getMinExactChunk() { return this.minExactChunk; }
/*    */ 
/*    */ 
/*    */   
/* 36 */   public boolean isConsiderComments() { return this.considerComments; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\dude.jar!\lrg\dude\duplication\Parameters.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */