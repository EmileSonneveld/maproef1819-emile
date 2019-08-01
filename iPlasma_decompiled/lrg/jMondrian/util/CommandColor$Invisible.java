/*    */ package classes.lrg.jMondrian.util;
/*    */ 
/*    */ import lrg.jMondrian.commands.AbstractNumericalCommand;
/*    */ import lrg.jMondrian.util.CommandColor;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class Invisible
/*    */   extends AbstractNumericalCommand
/*    */ {
/*    */   private Invisible() {}
/*    */   
/* 39 */   public double execute() { throw new CommandColor.InvisibleException(); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\jMondria\\util\CommandColor$Invisible.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */