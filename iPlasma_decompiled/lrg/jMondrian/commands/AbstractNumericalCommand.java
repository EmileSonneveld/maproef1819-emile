/*    */ package classes.lrg.jMondrian.commands;
/*    */ 
/*    */ import lrg.jMondrian.commands.AbstractNumericalCommand;
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
/*    */ public abstract class AbstractNumericalCommand
/*    */ {
/*    */   protected Object receiver;
/*    */   private String name;
/*    */   
/* 29 */   public AbstractNumericalCommand() { this(""); }
/*    */ 
/*    */ 
/*    */   
/* 33 */   public AbstractNumericalCommand(String prop) { this.name = prop; }
/*    */ 
/*    */   
/*    */   public final AbstractNumericalCommand setReceiver(Object receiver) {
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
/*    */   public abstract double execute();
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\jMondrian\commands\AbstractNumericalCommand.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */