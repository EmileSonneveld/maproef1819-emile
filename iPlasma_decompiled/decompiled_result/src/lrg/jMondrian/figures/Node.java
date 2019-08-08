/*    */ package classes.lrg.jMondrian.figures;
/*    */ 
/*    */ import lrg.jMondrian.commands.AbstractNumericalCommand;
/*    */ import lrg.jMondrian.figures.AbstractFigure;
/*    */ import lrg.jMondrian.figures.Node;
/*    */ import lrg.jMondrian.painters.AbstractNodePainter;
/*    */ import lrg.jMondrian.view.ViewRendererInterface;
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
/*    */ public class Node
/*    */   extends AbstractFigure
/*    */ {
/* 29 */   private double absoluteX = 0.0D; private double absoluteY = 0.0D;
/*    */   protected AbstractNodePainter painter;
/*    */   
/*    */   public Node(Object entity, AbstractNodePainter painter) {
/* 33 */     super(entity);
/* 34 */     this.painter = painter;
/*    */   }
/*    */ 
/*    */   
/* 38 */   public double getWidth() { return this.painter.getWidth(this.entity); }
/*    */ 
/*    */ 
/*    */   
/* 42 */   public double getHeight() { return this.painter.getHeight(this.entity); }
/*    */ 
/*    */ 
/*    */   
/* 46 */   public double getRelativeX() { return this.painter.getX(this.entity); }
/*    */ 
/*    */ 
/*    */   
/* 50 */   public double getRelativeY() { return this.painter.getY(this.entity); }
/*    */ 
/*    */ 
/*    */   
/* 54 */   public double getAbsoluteX() { return this.absoluteX; }
/*    */ 
/*    */ 
/*    */   
/* 58 */   public double getAbsoluteY() { return this.absoluteY; }
/*    */ 
/*    */   
/*    */   public void show(ViewRendererInterface renderer, double xBias, double yBias, boolean last) {
/* 62 */     this.absoluteX = getRelativeX() + xBias;
/* 63 */     this.absoluteY = getRelativeY() + yBias;
/* 64 */     this.painter.paint(renderer, this.entity, xBias, yBias, last);
/*    */   }
/*    */ 
/*    */   
/* 68 */   public void translateTo(AbstractNumericalCommand xCmd, AbstractNumericalCommand yCmd) { this.painter.x(xCmd).y(yCmd); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\jMondrian\figures\Node.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */