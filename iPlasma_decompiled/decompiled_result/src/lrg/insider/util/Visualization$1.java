/*    */ package classes.lrg.insider.util;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.insider.util.Visualization;
/*    */ import lrg.jMondrian.commands.AbstractNumericalCommand;
/*    */ 
/*    */ 
/*    */ class null
/*    */   extends AbstractNumericalCommand
/*    */ {
/* 11 */   null(String $anonymous0) { super($anonymous0); }
/*    */   
/* 13 */   public double execute() { return ((Double)((AbstractEntityInterface)this.receiver).getProperty(prop).getValue()).doubleValue() + offset; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\inside\\util\Visualization$1.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */