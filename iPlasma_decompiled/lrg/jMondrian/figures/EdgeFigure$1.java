/*    */ package classes.lrg.jMondrian.figures;
/*    */ 
/*    */ import lrg.jMondrian.figures.EdgeFigure;
/*    */ import lrg.jMondrian.figures.Node;
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
/*    */ final class null
/*    */   extends EdgeFigure.Connection
/*    */ {
/* 35 */   public double computeX(Node n) { return n.getAbsoluteX() + n.getWidth() / 2.0D; }
/*    */ 
/*    */   
/* 38 */   public double computeY(Node n) { return n.getAbsoluteY() + n.getHeight() / 2.0D; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\jMondrian\figures\EdgeFigure$1.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */