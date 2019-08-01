/*     */ package classes.lrg.jMondrian.layouts;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import lrg.jMondrian.figures.EdgeFigure;
/*     */ import lrg.jMondrian.figures.Node;
/*     */ import lrg.jMondrian.layouts.AbstractLayout;
/*     */ import lrg.jMondrian.layouts.TreeLayout;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TreeLayout
/*     */   extends AbstractLayout
/*     */ {
/*     */   private double xDist;
/*     */   private double yDist;
/*     */   private int maxX;
/*     */   private int maxY;
/*     */   
/*  36 */   public TreeLayout() { this(5.0D, 5.0D); }
/*     */   public TreeLayout(double xDist, double yDist) {
/*     */     this.maxX = 0;
/*     */     this.maxY = 0;
/*  40 */     this.xDist = xDist;
/*  41 */     this.yDist = yDist;
/*     */   }
/*     */   
/*     */   private List<Node> getRootNodes(List<Node> nodeList, List<EdgeFigure> edgeList) {
/*  45 */     List<Node> rootNodes = new ArrayList<Node>();
/*  46 */     Iterator<Node> itNode = nodeList.iterator();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  52 */     while (itNode.hasNext()) {
/*  53 */       Node node = (Node)itNode.next();
/*  54 */       boolean isRoot = true;
/*  55 */       Iterator<EdgeFigure> itEdge = edgeList.iterator();
/*  56 */       while (itEdge.hasNext()) {
/*  57 */         EdgeFigure edge = (EdgeFigure)itEdge.next();
/*  58 */         if (edge.getFrom().equals(node)) {
/*  59 */           isRoot = false;
/*     */         }
/*     */       } 
/*  62 */       if (isRoot) {
/*  63 */         rootNodes.add(node);
/*     */       }
/*     */     } 
/*     */     
/*  67 */     return rootNodes;
/*     */   }
/*     */   
/*     */   private List<Node> getChildren(Node node, List<EdgeFigure> edgeList) {
/*  71 */     List<Node> childrenNodes = new ArrayList<Node>();
/*  72 */     Iterator<EdgeFigure> itEdge = edgeList.iterator();
/*     */ 
/*     */     
/*  75 */     while (itEdge.hasNext()) {
/*  76 */       EdgeFigure edge = (EdgeFigure)itEdge.next();
/*  77 */       if (edge.getTo().equals(node)) {
/*  78 */         edge.setConnectionStyle(EdgeFigure.UP_MIDDLE, false);
/*  79 */         edge.setConnectionStyle(EdgeFigure.DOWN_MIDDLE, true);
/*  80 */         childrenNodes.add(edge.getFrom());
/*     */       } 
/*     */     } 
/*     */     
/*  84 */     return childrenNodes;
/*     */   }
/*     */ 
/*     */   
/*     */   private double layoutLayer(List<Node> nodeList, List<EdgeFigure> edgeList, double xTo, double yTo, double betweenX, double betweenY, AbstractLayout.ControlXY xCmd, AbstractLayout.ControlXY yCmd) {
/*  89 */     Iterator<Node> itNode = nodeList.iterator();
/*     */ 
/*     */     
/*  92 */     double treeWidth = 0.0D, childrenY = 0.0D, childrenMiddle = 0.0D, width = 0.0D;
/*     */     
/*  94 */     double xPoz = betweenX;
/*  95 */     double yPoz = betweenY;
/*     */     
/*  97 */     while (itNode.hasNext()) {
/*  98 */       Node node = (Node)itNode.next();
/*  99 */       List<Node> children = getChildren(node, edgeList);
/* 100 */       childrenY = yPoz + node.getHeight() + this.yDist;
/* 101 */       if (childrenY >= this.maxY) this.maxY = (int)childrenY; 
/* 102 */       treeWidth = layoutLayer(children, edgeList, node.getWidth() / 2.0D, yPoz + node.getHeight(), xPoz, childrenY, xCmd, yCmd);
/* 103 */       if (node.getWidth() > treeWidth) treeWidth = node.getWidth(); 
/* 104 */       childrenMiddle = xPoz + treeWidth / 2.0D;
/*     */       
/* 106 */       xCmd.link(node, childrenMiddle - node.getWidth() / 2.0D);
/* 107 */       yCmd.link(node, yPoz);
/* 108 */       node.translateTo(xCmd, yCmd);
/*     */       
/* 110 */       xPoz += treeWidth + this.xDist;
/* 111 */       if (xPoz >= this.maxX) this.maxX = (int)xPoz;
/*     */     
/*     */     } 
/*     */     
/* 115 */     width = xPoz - betweenX - this.xDist;
/* 116 */     if (width / 2.0D >= xTo) { xTo += width / 2.0D + betweenX - xTo; }
/* 117 */     else { xTo += betweenX; }
/*     */     
/* 119 */     return width;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] distributeNodes(List<Node> nodeList, List<EdgeFigure> edgeList) {
/* 125 */     this.maxX = 0;
/* 126 */     this.maxY = 0;
/* 127 */     AbstractLayout.ControlXY xCmd = new AbstractLayout.ControlXY();
/* 128 */     AbstractLayout.ControlXY yCmd = new AbstractLayout.ControlXY();
/*     */     
/* 130 */     List<Node> rootNodes = getRootNodes(nodeList, edgeList);
/* 131 */     layoutLayer(rootNodes, edgeList, 0.0D, 0.0D, this.xDist, this.yDist, xCmd, yCmd);
/*     */     
/* 133 */     for (int i = 0; i < nodeList.size(); i++) {
/* 134 */       ((Node)nodeList.get(i)).translateTo(xCmd, yCmd);
/*     */     }
/*     */     
/* 137 */     double[] rez = new double[2];
/* 138 */     rez[0] = this.maxX;
/* 139 */     rez[1] = this.maxY;
/*     */     
/* 141 */     return rez;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\jMondrian\layouts\TreeLayout.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */