/*    */ package classes.lrg.jMondrian.commands;
/*    */ 
/*    */ import lrg.jMondrian.commands.AbstractEntityCommand;
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
/*    */ public abstract class AbstractEntityCommand
/*    */ {
/*    */   protected Object receiver;
/*    */   private String name;
/*    */   
/* 29 */   public AbstractEntityCommand() { this(""); }
/*    */ 
/*    */ 
/*    */   
/* 33 */   public AbstractEntityCommand(String prop) { this.name = prop; }
/*    */ 
/*    */   
/*    */   public final AbstractEntityCommand setReceiver(Object receiver) {
/* 37 */     this.receiver = receiver;
/* 38 */     return this;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 42 */     if (this.name.equals("")) {
/* 43 */       return "";
/*    */     }
/* 45 */     return String.valueOf(this.name) + ":" + execute().toString();
/*    */   }
/*    */   
/*    */   public abstract Object execute();
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\jMondrian\commands\AbstractEntityCommand.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */