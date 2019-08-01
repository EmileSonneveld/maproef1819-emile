/*    */ package classes.lrg.jMondrian.util;
/*    */ 
/*    */ import java.awt.Color;
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
/*    */ class MondrianColor
/*    */   extends AbstractNumericalCommand
/*    */ {
/*    */   private Color c;
/*    */   
/* 30 */   public MondrianColor(Color c) { this.c = c; }
/*    */ 
/*    */   
/* 33 */   public double execute() { return this.c.getRGB(); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\jMondria\\util\CommandColor$MondrianColor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */