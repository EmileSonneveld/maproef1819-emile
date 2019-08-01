/*    */ package classes.lrg.insider.util;
/*    */ 
/*    */ import lrg.jMondrian.commands.AbstractEntityCommand;
/*    */ import lrg.jMondrian.commands.AbstractNumericalCommand;
/*    */ import lrg.jMondrian.commands.AbstractStringCommand;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Visualization
/*    */ {
/* 11 */   public static AbstractNumericalCommand metricCommand(String prop, double offset) { return new Object(prop, prop, offset); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 19 */   public static AbstractNumericalCommand metricCommand(String prop) { return new Object(prop, prop); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 27 */   public static AbstractEntityCommand entityCommand(String prop) { return new Object(prop, prop); }
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
/* 40 */   public static AbstractStringCommand stringCommand(String prop) { return new Object(prop, prop); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 48 */   public static AbstractEntityCommand indirectionCommand(String prop, AbstractEntityCommand command) { return new Object(prop, command, prop); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\inside\\util\Visualization.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */