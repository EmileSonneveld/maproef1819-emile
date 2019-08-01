/*    */ package classes.lrg.jMondrian.layouts;
/*    */ 
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractLayout
/*    */ {
/* 33 */   public final double[] applyLayout(List<Node> nodeList, List<EdgeFigure> edgeList) { return distributeNodes(nodeList, edgeList); }
/*    */   
/*    */   protected abstract double[] distributeNodes(List<Node> paramList1, List<EdgeFigure> paramList2);
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\jMondrian\layouts\AbstractLayout.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */