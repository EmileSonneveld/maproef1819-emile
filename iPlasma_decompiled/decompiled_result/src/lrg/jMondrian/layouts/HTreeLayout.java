/*     */ package classes.lrg.jMondrian.layouts;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import lrg.jMondrian.figures.EdgeFigure;
/*     */ import lrg.jMondrian.figures.Node;
/*     */ import lrg.jMondrian.layouts.AbstractLayout;
/*     */ import lrg.jMondrian.layouts.HTreeLayout;
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
/*     */ 
/*     */ public class HTreeLayout
/*     */   extends AbstractLayout
/*     */ {
/*     */   private double xDist;
/*     */   private double yDist;
/*     */   private int maxX;
/*     */   private int maxY;
/*     */   
/*  37 */   public HTreeLayout() { this(5.0D, 5.0D); }
/*     */   public HTreeLayout(double xDist, double yDist) {
/*     */     this.maxX = 0;
/*     */     this.maxY = 0;
/*  41 */     this.xDist = xDist;
/*  42 */     this.yDist = yDist;
/*     */   }
/*     */   
/*     */   private List<Node> getRootNodes(List<Node> nodeList, List<EdgeFigure> edgeList) {
/*  46 */     List<Node> rootNodes = new ArrayList<Node>();
/*  47 */     Iterator<Node> itNode = nodeList.iterator();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  53 */     while (itNode.hasNext()) {
/*  54 */       Node node = (Node)itNode.next();
/*  55 */       boolean isRoot = true;
/*  56 */       Iterator<EdgeFigure> itEdge = edgeList.iterator();
/*  57 */       while (itEdge.hasNext()) {
/*  58 */         EdgeFigure edge = (EdgeFigure)itEdge.next();
/*  59 */         if (edge.getFrom().equals(node)) {
/*  60 */           isRoot = false;
/*     */         }
/*     */       } 
/*  63 */       if (isRoot) {
/*  64 */         rootNodes.add(node);
/*     */       }
/*     */     } 
/*     */     
/*  68 */     return rootNodes;
/*     */   }
/*     */   
/*     */   private List<Node> getChildren(Node node, List<EdgeFigure> edgeList) {
/*  72 */     List<Node> childrenNodes = new ArrayList<Node>();
/*  73 */     Iterator<EdgeFigure> itEdge = edgeList.iterator();
/*     */ 
/*     */     
/*  76 */     while (itEdge.hasNext()) {
/*  77 */       EdgeFigure edge = (EdgeFigure)itEdge.next();
/*  78 */       if (edge.getTo().equals(node)) {
/*     */ 
/*     */         
/*  81 */         edge.setConnectionStyle(EdgeFigure.MIDDLE, true);
/*  82 */         childrenNodes.add(edge.getFrom());
/*     */       } 
/*     */     } 
/*     */     
/*  86 */     return childrenNodes;
/*     */   }
/*     */ 
/*     */   
/*     */   private double layoutLayer(List<Node> nodeList, List<EdgeFigure> edgeList, double xTo, double yTo, double betweenX, double betweenY, AbstractLayout.ControlXY xCmd, AbstractLayout.ControlXY yCmd) {
/*  91 */     Iterator<Node> itNode = nodeList.iterator();
/*     */ 
/*     */     
/*  94 */     double treeHeight = 0.0D, childrenX = 0.0D, childrenMiddle = 0.0D, height = 0.0D;
/*     */     
/*  96 */     double xPoz = betweenX;
/*  97 */     double yPoz = betweenY;
/*     */     
/*  99 */     while (itNode.hasNext()) {
/* 100 */       Node node = (Node)itNode.next();
/* 101 */       List<Node> children = getChildren(node, edgeList);
/* 102 */       childrenX = xPoz + node.getWidth() + this.xDist;
/* 103 */       if (childrenX >= this.maxX) this.maxX = (int)childrenX; 
/* 104 */       treeHeight = layoutLayer(children, edgeList, xPoz + node.getWidth(), yPoz / 2.0D, childrenX, yPoz, xCmd, yCmd);
/* 105 */       if (node.getHeight() > treeHeight) treeHeight = node.getHeight(); 
/* 106 */       childrenMiddle = yPoz + treeHeight / 2.0D;
/*     */       
/* 108 */       xCmd.link(node, xPoz);
/* 109 */       yCmd.link(node, childrenMiddle - node.getHeight() / 2.0D);
/* 110 */       node.translateTo(xCmd, yCmd);
/*     */       
/* 112 */       yPoz += treeHeight + this.yDist;
/* 113 */       if (yPoz >= this.maxY) this.maxY = (int)yPoz;
/*     */     
/*     */     } 
/*     */     
/* 117 */     height = yPoz - betweenY - this.yDist;
/* 118 */     if (height / 2.0D >= yTo) { yTo += height / 2.0D + betweenY - yTo; }
/* 119 */     else { yTo += betweenY; }
/*     */     
/* 121 */     return height;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] distributeNodes(List<Node> nodeList, List<EdgeFigure> edgeList) {
/* 127 */     this.maxX = 0;
/* 128 */     this.maxY = 0;
/* 129 */     AbstractLayout.ControlXY xCmd = new AbstractLayout.ControlXY();
/* 130 */     AbstractLayout.ControlXY yCmd = new AbstractLayout.ControlXY();
/*     */     
/* 132 */     List<Node> rootNodes = getRootNodes(nodeList, edgeList);
/* 133 */     layoutLayer(rootNodes, edgeList, 0.0D, 0.0D, this.xDist, this.yDist, xCmd, yCmd);
/*     */     
/* 135 */     if (nodeList.size() > 0) {
/* 136 */       ((Node)nodeList.get(0)).translateTo(xCmd, yCmd);
/*     */     }
/*     */     
/* 139 */     double[] rez = new double[2];
/* 140 */     rez[0] = this.maxX;
/* 141 */     rez[1] = this.maxY;
/*     */     
/* 143 */     return rez;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\jMondrian\layouts\HTreeLayout.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */