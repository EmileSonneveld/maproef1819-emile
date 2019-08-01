/*    */ package classes.lrg.jMondrian.layouts;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import lrg.jMondrian.commands.AbstractNumericalCommand;
/*    */ import lrg.jMondrian.figures.EdgeFigure;
/*    */ import lrg.jMondrian.figures.Node;
/*    */ import lrg.jMondrian.layouts.AbstractLayout;
/*    */ import lrg.jMondrian.layouts.Checker;
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
/*    */ public class Checker
/*    */   extends AbstractLayout
/*    */ {
/*    */   private int prefferedWidth;
/*    */   private final AbstractNumericalCommand measure;
/*    */   private int xDist;
/*    */   private int yDist;
/*    */   
/*    */   public Checker(AbstractNumericalCommand measure) {
/* 31 */     this.prefferedWidth = 800;
/*    */     
/* 33 */     this.xDist = 5; this.yDist = 5;
/*    */ 
/*    */     
/* 36 */     this.measure = measure;
/*    */   }
/*    */   
/*    */   public Checker(AbstractNumericalCommand measure, int maxWidth) {
/* 40 */     this(measure);
/* 41 */     this.prefferedWidth = maxWidth;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public double[] distributeNodes(List<Node> nodeList, List<EdgeFigure> edgeList) {
/* 47 */     List<Node> tmpList = new ArrayList<Node>();
/* 48 */     tmpList.addAll(nodeList);
/* 49 */     Collections.sort(tmpList, new Object(this));
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
/* 60 */     double nextX = this.xDist;
/* 61 */     double nextY = this.yDist;
/*    */     
/* 63 */     double maxHeight = 0.0D;
/* 64 */     double maxWidth = 0.0D;
/*    */     
/* 66 */     AbstractLayout.ControlXY xCmd = new AbstractLayout.ControlXY();
/* 67 */     AbstractLayout.ControlXY yCmd = new AbstractLayout.ControlXY();
/*    */     
/* 69 */     Iterator<Node> it = tmpList.iterator();
/* 70 */     while (it.hasNext()) {
/*    */       
/* 72 */       Node figure = (Node)it.next();
/* 73 */       xCmd.link(figure, nextX);
/* 74 */       yCmd.link(figure, nextY);
/*    */       
/* 76 */       nextX += this.xDist + figure.getWidth();
/* 77 */       if (nextX >= maxWidth) {
/* 78 */         maxWidth = nextX;
/*    */       }
/* 80 */       double lastHeight = figure.getHeight();
/* 81 */       if (maxHeight < lastHeight) {
/* 82 */         maxHeight = lastHeight;
/*    */       }
/* 84 */       if (nextX > this.prefferedWidth) {
/* 85 */         nextY += maxHeight + this.yDist;
/* 86 */         nextX = this.xDist;
/* 87 */         maxHeight = 0.0D;
/*    */       } 
/*    */     } 
/*    */     
/* 91 */     for (int i = 0; i < nodeList.size(); i++) {
/* 92 */       ((Node)nodeList.get(i)).translateTo(xCmd, yCmd);
/*    */     }
/*    */     
/* 95 */     double[] rez = new double[2];
/* 96 */     rez[0] = maxWidth;
/* 97 */     rez[1] = maxHeight + nextY + this.yDist;
/*    */     
/* 99 */     return rez;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\jMondrian\layouts\Checker.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */