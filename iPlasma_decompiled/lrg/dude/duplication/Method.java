/*    */ package lrg.dude.duplication;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Method
/*    */   implements Entity
/*    */ {
/*    */   private String methodName;
/*    */   private StringList methodBody;
/*    */   private int noOfRelevantLines;
/*    */   
/*    */   public Method(String name) {
/* 17 */     this.noOfRelevantLines = 0;
/*    */ 
/*    */     
/* 20 */     this.methodName = name;
/*    */   }
/*    */   public Method(String name, StringList code) {
/*    */     this.noOfRelevantLines = 0;
/* 24 */     this.methodName = name;
/* 25 */     this.methodBody = code;
/*    */   }
/*    */ 
/*    */   
/* 29 */   public String getName() { return this.methodName; }
/*    */ 
/*    */ 
/*    */   
/* 33 */   public StringList getCode() { return this.methodBody; }
/*    */ 
/*    */ 
/*    */   
/* 37 */   public int getNoOfRelevantLines() { return this.noOfRelevantLines; }
/*    */ 
/*    */ 
/*    */   
/* 41 */   public void setNoOfRelevantLines(int norl) { this.noOfRelevantLines = norl; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\dude.jar!\lrg\dude\duplication\Method.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */