/*    */ package classes.lrg.insider.plugins.visualizations;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.insider.plugins.visualizations.Call;
/*    */ 
/*    */ public class Call {
/*    */   private AbstractEntityInterface caller;
/*    */   
/*    */   public Call(AbstractEntityInterface caller, AbstractEntityInterface callee) {
/* 10 */     this.caller = caller;
/* 11 */     this.callee = callee;
/*    */   }
/*    */   
/*    */   private AbstractEntityInterface callee;
/*    */   
/* 16 */   public AbstractEntityInterface getCaller() { return this.caller; }
/*    */ 
/*    */ 
/*    */   
/* 20 */   public AbstractEntityInterface getCallee() { return this.callee; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\visualizations\Call.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */