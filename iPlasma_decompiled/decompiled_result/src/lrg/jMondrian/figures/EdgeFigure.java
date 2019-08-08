/*    */ package classes.lrg.jMondrian.figures;
/*    */ 
/*    */ import lrg.jMondrian.figures.AbstractFigure;
/*    */ import lrg.jMondrian.figures.EdgeFigure;
/*    */ import lrg.jMondrian.figures.Node;
/*    */ import lrg.jMondrian.painters.AbstractEdgePainter;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EdgeFigure
/*    */   extends AbstractFigure
/*    */ {
/* 33 */   public static final Connection MIDDLE = new Object();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 42 */   public static final Connection UP_MIDDLE = new Object();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 51 */   public static final Connection DOWN_MIDDLE = new Object();
/*    */ 
/*    */   
/*    */   private Node from;
/*    */ 
/*    */   
/*    */   private Node to;
/*    */   
/*    */   private AbstractEdgePainter painter;
/*    */   
/*    */   private Connection fromType;
/*    */   
/*    */   private Connection toType;
/*    */ 
/*    */   
/*    */   public EdgeFigure(Object entity, AbstractEdgePainter painter, Node from, Node to) {
/* 67 */     super(entity);
/* 68 */     this.painter = painter;
/* 69 */     this.from = from;
/* 70 */     this.to = to;
/* 71 */     this.toType = this.fromType = MIDDLE;
/*    */   }
/*    */ 
/*    */   
/* 75 */   public Node getFrom() { return this.from; }
/*    */ 
/*    */ 
/*    */   
/* 79 */   public Node getTo() { return this.to; }
/*    */ 
/*    */   
/*    */   public void setConnectionStyle(Connection type, boolean select) {
/* 83 */     if (select) {
/* 84 */       this.toType = type;
/*    */     } else {
/* 86 */       this.fromType = type;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/* 91 */   public void show(ViewRendererInterface renderer) { this.painter.paint(renderer, this.entity, this.fromType.computeX(this.from), this.fromType.computeY(this.from), this.toType.computeX(this.to), this.toType.computeY(this.to)); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\jMondrian\figures\EdgeFigure.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */