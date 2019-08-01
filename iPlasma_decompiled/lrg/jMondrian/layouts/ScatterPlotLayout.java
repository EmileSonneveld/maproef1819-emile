/*    */ package classes.lrg.jMondrian.layouts;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import lrg.jMondrian.figures.EdgeFigure;
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
/*    */ public class ScatterPlotLayout
/*    */   extends AbstractLayout
/*    */ {
/*    */   public double[] distributeNodes(List<Node> nodeList, List<EdgeFigure> edgeList) {
/* 33 */     double maxX = 0.0D;
/* 34 */     double maxY = 0.0D;
/*    */     
/* 36 */     Iterator<Node> it = nodeList.iterator();
/* 37 */     while (it.hasNext()) {
/*    */       
/* 39 */       Node figure = (Node)it.next();
/* 40 */       double auxX = figure.getWidth() + figure.getRelativeX();
/* 41 */       double auxY = figure.getHeight() + figure.getRelativeY();
/* 42 */       if (auxX >= maxX) maxX = auxX; 
/* 43 */       if (auxY >= maxY) maxY = auxY;
/*    */     
/*    */     } 
/*    */     
/* 47 */     double[] rez = new double[2];
/* 48 */     rez[0] = maxX;
/* 49 */     rez[1] = maxY;
/*    */     
/* 51 */     return rez;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\jMondrian\layouts\ScatterPlotLayout.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */