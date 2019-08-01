/*    */ package classes.lrg.jMondrian.commands;
/*    */ 
/*    */ import lrg.jMondrian.commands.AbstractFigureDescriptionCommand;
/*    */ import lrg.jMondrian.figures.Figure;
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
/*    */ public abstract class AbstractFigureDescriptionCommand
/*    */ {
/*    */   protected Object receiver;
/*    */   
/*    */   public final AbstractFigureDescriptionCommand setReceiver(Object receiver) {
/* 30 */     this.receiver = receiver;
/* 31 */     return this;
/*    */   }
/*    */   
/*    */   public abstract Figure describe();
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\jMondrian\commands\AbstractFigureDescriptionCommand.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */