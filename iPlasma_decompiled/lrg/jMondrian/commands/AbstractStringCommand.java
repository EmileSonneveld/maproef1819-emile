/*    */ package classes.lrg.jMondrian.commands;
/*    */ 
/*    */ import lrg.jMondrian.commands.AbstractStringCommand;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractStringCommand
/*    */ {
/*    */   protected Object receiver;
/*    */   private String name;
/*    */   
/* 29 */   public AbstractStringCommand() { this(""); }
/*    */ 
/*    */ 
/*    */   
/* 33 */   public AbstractStringCommand(String prop) { this.name = prop; }
/*    */ 
/*    */   
/*    */   public final AbstractStringCommand setReceiver(Object receiver) {
/* 37 */     this.receiver = receiver;
/* 38 */     return this;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 42 */     if (this.name.equals("")) {
/* 43 */       return "";
/*    */     }
/* 45 */     return String.valueOf(this.name) + ":" + execute();
/*    */   }
/*    */   
/*    */   public abstract String execute();
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\jMondrian\commands\AbstractStringCommand.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */