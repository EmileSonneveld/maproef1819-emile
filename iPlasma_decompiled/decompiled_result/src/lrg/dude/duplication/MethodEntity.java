/*    */ package lrg.dude.duplication;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntity;
/*    */ import lrg.memoria.core.Method;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MethodEntity
/*    */   implements IMethodEntity
/*    */ {
/*    */   private int noOfRelevantLines;
/*    */   Method aMethod;
/*    */   
/*    */   public MethodEntity(Method anEntity) {
/* 16 */     this.noOfRelevantLines = 0;
/*    */ 
/*    */ 
/*    */     
/* 20 */     this.aMethod = anEntity;
/*    */   }
/*    */ 
/*    */   
/* 24 */   public AbstractEntity getMethod() { return this.aMethod; }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 29 */     if (this.aMethod != null)
/* 30 */       return this.aMethod.getName(); 
/* 31 */     return null;
/*    */   }
/*    */   
/*    */   public StringList getCode() {
/* 35 */     if (this.aMethod != null && this.aMethod.getBody() != null) {
/* 36 */       String theSourceCode = this.aMethod.getBody().getSourceCode();
/* 37 */       if (theSourceCode != null) {
/* 38 */         return new StringList(this.aMethod.getBody().getSourceCode().split("\n"));
/*    */       }
/*    */     } 
/*    */     
/* 42 */     return new StringList();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 47 */   public int getNoOfRelevantLines() { return this.noOfRelevantLines; }
/*    */ 
/*    */ 
/*    */   
/* 51 */   public void setNoOfRelevantLines(int norl) { this.noOfRelevantLines = norl; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\dude.jar!\lrg\dude\duplication\MethodEntity.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */