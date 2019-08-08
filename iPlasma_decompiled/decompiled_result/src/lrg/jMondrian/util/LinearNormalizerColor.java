/*    */ package classes.lrg.jMondrian.util;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.util.List;
/*    */ import lrg.jMondrian.commands.AbstractNumericalCommand;
/*    */ import lrg.jMondrian.util.LinearNormalizerColor;
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
/*    */ public class LinearNormalizerColor
/*    */   extends AbstractNumericalCommand
/*    */ {
/*    */   private double min;
/*    */   private double max;
/*    */   private AbstractNumericalCommand command;
/*    */   
/*    */   public LinearNormalizerColor(List context, AbstractNumericalCommand cmd) {
/* 35 */     this.command = cmd;
/* 36 */     cmd.setReceiver(context.get(0));
/* 37 */     this.min = this.max = cmd.execute();
/* 38 */     int i = 1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public double execute() {
/* 47 */     this.command.setReceiver(this.receiver);
/* 48 */     double aux = this.command.execute();
/* 49 */     double pas = this.max - this.min;
/*    */     
/* 51 */     int b = (int)(255.0D - (aux - this.min) * 255.0D / pas), g = b, r = g;
/* 52 */     return (new Color(r, g, b)).getRGB();
/*    */   }
/*    */   
/*    */   public String toString() {
/* 56 */     this.command.setReceiver(this.receiver);
/* 57 */     return this.command.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\jMondria\\util\LinearNormalizerColor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */