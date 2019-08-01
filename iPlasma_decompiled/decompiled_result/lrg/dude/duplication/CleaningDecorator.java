/*    */ package lrg.dude.duplication;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class CleaningDecorator
/*    */ {
/*    */   protected CleaningDecorator nextComponent;
/*    */   
/* 14 */   public CleaningDecorator(CleaningDecorator next) { this.nextComponent = next; }
/*    */ 
/*    */   
/*    */   public StringList clean(StringList str) {
/* 18 */     StringList newStr = specificClean(str);
/* 19 */     if (this.nextComponent != null)
/* 20 */       return this.nextComponent.clean(newStr); 
/* 21 */     return newStr;
/*    */   }
/*    */   
/*    */   protected abstract StringList specificClean(StringList paramStringList);
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\dude.jar!\lrg\dude\duplication\CleaningDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */