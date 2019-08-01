/*    */ package classes.lrg.jMondrian.layouts;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import lrg.jMondrian.commands.AbstractNumericalCommand;
/*    */ import lrg.jMondrian.figures.Node;
/*    */ import lrg.jMondrian.layouts.AbstractLayout;
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
/*    */ public class ControlXY
/*    */   extends AbstractNumericalCommand
/*    */ {
/* 39 */   private HashMap<Object, Double> m = new HashMap();
/*    */   
/* 41 */   public double execute() { return ((Double)this.m.get(this.receiver)).doubleValue(); }
/*    */ 
/*    */   
/* 44 */   public void link(Node n, double pos) { this.m.put(n.getEntity(), Double.valueOf(pos)); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\jMondrian\layouts\AbstractLayout$ControlXY.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */