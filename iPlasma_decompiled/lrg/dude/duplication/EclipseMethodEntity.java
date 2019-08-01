/*    */ package lrg.dude.duplication;
/*    */ public class EclipseMethodEntity implements IMethodEntity {
/*    */   private int noOfRelevantLines;
/*    */   
/*    */   public EclipseMethodEntity(AbstractEntity anEntity) {
/*  6 */     this.noOfRelevantLines = 0;
/*    */ 
/*    */ 
/*    */     
/* 10 */     this.anEclipseMethodWrapper = anEntity;
/*    */   }
/*    */   AbstractEntity anEclipseMethodWrapper;
/*    */   
/* 14 */   public AbstractEntity getMethod() { return this.anEclipseMethodWrapper; }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 18 */     if (this.anEclipseMethodWrapper != null)
/* 19 */       return this.anEclipseMethodWrapper.getName(); 
/* 20 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public StringList getCode() {
/* 25 */     String theSourceCode = (String)this.anEclipseMethodWrapper.getProperty(
/* 26 */         "SourceCode").getValue();
/*    */     
/* 28 */     if (theSourceCode != null) {
/* 29 */       return new StringList(theSourceCode.split("\n"));
/*    */     }
/*    */     
/* 32 */     return new StringList();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 37 */   public int getNoOfRelevantLines() { return this.noOfRelevantLines; }
/*    */ 
/*    */ 
/*    */   
/* 41 */   public void setNoOfRelevantLines(int norl) { this.noOfRelevantLines = norl; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\dude.jar!\lrg\dude\duplication\EclipseMethodEntity.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */