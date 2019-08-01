/*    */ package classes.lrg.jMondrian.layouts;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import lrg.jMondrian.figures.EdgeFigure;
/*    */ import lrg.jMondrian.figures.Node;
/*    */ import lrg.jMondrian.layouts.AbstractLayout;
/*    */ import lrg.jMondrian.layouts.FlowLayout;
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
/*    */ public class FlowLayout
/*    */   extends AbstractLayout
/*    */ {
/*    */   private double prefferedWidth;
/*    */   private double xDist;
/*    */   private double yDist;
/*    */   
/* 34 */   public FlowLayout() { this(5.0D, 5.0D, 0.0D); }
/*    */ 
/*    */   
/*    */   public FlowLayout(double maxWidth) {
/* 38 */     this.prefferedWidth = maxWidth;
/* 39 */     this.xDist = 5.0D;
/* 40 */     this.yDist = 5.0D;
/*    */   }
/*    */   
/*    */   public FlowLayout(double xDist, double yDist, double maxWidth) {
/* 44 */     this.prefferedWidth = maxWidth;
/* 45 */     this.xDist = xDist;
/* 46 */     this.yDist = yDist;
/*    */   }
/*    */   
/*    */   public double[] distributeNodes(List<Node> nodeList, List<EdgeFigure> edgeList) {
/* 50 */     double nextX = this.xDist;
/* 51 */     double nextY = this.yDist;
/*    */     
/* 53 */     double maxHeight = 0.0D;
/* 54 */     double maxWidth = 0.0D;
/*    */     
/* 56 */     AbstractLayout.ControlXY xCmd = new AbstractLayout.ControlXY();
/* 57 */     AbstractLayout.ControlXY yCmd = new AbstractLayout.ControlXY();
/*    */     
/* 59 */     Iterator<Node> it = nodeList.iterator();
/* 60 */     while (it.hasNext()) {
/*    */       
/* 62 */       Node figure = (Node)it.next();
/* 63 */       xCmd.link(figure, nextX);
/* 64 */       yCmd.link(figure, nextY);
/*    */       
/* 66 */       nextX += this.xDist + figure.getWidth();
/* 67 */       if (nextX >= maxWidth) {
/* 68 */         maxWidth = nextX;
/*    */       }
/* 70 */       double lastHeight = figure.getHeight();
/* 71 */       if (maxHeight < lastHeight) {
/* 72 */         maxHeight = lastHeight;
/*    */       }
/* 74 */       if (this.prefferedWidth != 0.0D && nextX > this.prefferedWidth) {
/* 75 */         nextY += maxHeight + this.yDist;
/* 76 */         nextX = this.xDist;
/* 77 */         maxHeight = 0.0D;
/*    */       } 
/*    */     } 
/*    */     
/* 81 */     for (int i = 0; i < nodeList.size(); i++) {
/* 82 */       ((Node)nodeList.get(i)).translateTo(xCmd, yCmd);
/*    */     }
/*    */     
/* 85 */     double[] rez = new double[2];
/* 86 */     rez[0] = maxWidth;
/* 87 */     rez[1] = maxHeight + nextY + this.yDist;
/*    */     
/* 89 */     return rez;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\jMondrian\layouts\FlowLayout.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */