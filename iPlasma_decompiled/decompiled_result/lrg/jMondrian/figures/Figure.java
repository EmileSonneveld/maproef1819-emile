/*     */ package classes.lrg.jMondrian.figures;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import lrg.jMondrian.commands.AbstractFigureDescriptionCommand;
/*     */ import lrg.jMondrian.figures.EdgeFigure;
/*     */ import lrg.jMondrian.figures.Figure;
/*     */ import lrg.jMondrian.figures.Node;
/*     */ import lrg.jMondrian.layouts.AbstractLayout;
/*     */ import lrg.jMondrian.layouts.FlowLayout;
/*     */ import lrg.jMondrian.painters.AbstractEdgePainter;
/*     */ import lrg.jMondrian.painters.AbstractNodePainter;
/*     */ import lrg.jMondrian.view.ViewRendererInterface;
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
/*     */ 
/*     */ public class Figure
/*     */   extends Node
/*     */ {
/*  39 */   private double[] dimension = null; private int minimumW; private int minimumH; private HashMap<Object, Node> nodeLookUp;
/*     */   
/*     */   public double getWidth() {
/*  42 */     if (this.dimension == null) {
/*  43 */       this.dimension = this.layout.applyLayout(this.nodes, this.edges);
/*     */     }
/*  45 */     return (this.dimension[0] < this.minimumW) ? this.minimumW : this.dimension[0];
/*     */   }
/*     */   private List<Node> nodes; private List<EdgeFigure> edges; private AbstractLayout layout;
/*     */   public double getHeight() {
/*  49 */     if (this.dimension == null) {
/*  50 */       this.dimension = this.layout.applyLayout(this.nodes, this.edges);
/*     */     }
/*  52 */     return (this.dimension[1] < this.minimumH) ? this.minimumH : this.dimension[1];
/*     */   }
/*     */   
/*     */   public void show(ViewRendererInterface window, double x1Bias, double y1Bias, boolean last) {
/*  56 */     if (this.dimension == null) {
/*  57 */       this.dimension = this.layout.applyLayout(this.nodes, this.edges);
/*     */     }
/*  59 */     if (this.painter != null) {
/*  60 */       this.painter.width(new Object(this));
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  65 */       this.painter.height(new Object(this));
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  70 */       super.show(window, x1Bias, y1Bias, last);
/*     */     } 
/*  72 */     for (int i = 0; i < this.nodes.size(); i++) {
/*  73 */       ((Node)this.nodes.get(i)).show(window, getAbsoluteX(), getAbsoluteY(), (i == this.nodes.size() - 1));
/*     */     }
/*  75 */     for (int j = 0; j < this.edges.size(); j++) {
/*  76 */       ((EdgeFigure)this.edges.get(j)).show(window);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  82 */   public Figure() { this(10, 10); }
/*     */ 
/*     */ 
/*     */   
/*     */   public Figure(int minimumW, int minimumH)
/*     */   {
/*  88 */     super(null, null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  96 */     this.nodes = new ArrayList();
/*  97 */     this.edges = new ArrayList();
/*     */     this.layout = new FlowLayout();
/*     */     this.minimumW = minimumW;
/*     */     this.minimumH = minimumH;
/* 101 */     this.nodeLookUp = new HashMap(); } public void nodesUsing(List nodes, AbstractNodePainter painter) { Iterator it = nodes.iterator();
/* 102 */     while (it.hasNext()) {
/* 103 */       Object anEntity = it.next();
/* 104 */       Node aNode = new Node(anEntity, painter);
/* 105 */       this.nodeLookUp.put(anEntity, aNode);
/* 106 */       this.nodes.add(aNode);
/*     */     }  }
/*     */ 
/*     */   
/*     */   public void nodesUsingForEach(List nodes, AbstractNodePainter painter, AbstractFigureDescriptionCommand cmd) {
/* 111 */     Iterator it = nodes.iterator();
/* 112 */     while (it.hasNext()) {
/* 113 */       Object anEntity = it.next();
/* 114 */       Figure aNode = cmd.setReceiver(anEntity).describe();
/* 115 */       aNode.entity = anEntity;
/* 116 */       aNode.painter = painter;
/* 117 */       this.nodeLookUp.putAll(aNode.nodeLookUp);
/* 118 */       this.nodeLookUp.put(anEntity, aNode);
/* 119 */       this.nodes.add(aNode);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void edgesUsing(List edges, AbstractEdgePainter painter) {
/* 124 */     Iterator it = edges.iterator();
/* 125 */     while (it.hasNext()) {
/* 126 */       Object anEntity = it.next();
/* 127 */       Object f = painter.getFrom(anEntity);
/* 128 */       Object t = painter.getTo(anEntity);
/* 129 */       Node ff = (Node)this.nodeLookUp.get(f);
/* 130 */       Node tt = (Node)this.nodeLookUp.get(t);
/* 131 */       EdgeFigure edge = new EdgeFigure(anEntity, painter, ff, tt);
/* 132 */       this.edges.add(edge);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 137 */   public void layout(AbstractLayout layout) { this.layout = layout; }
/*     */ 
/*     */   
/*     */   public void renderOn(ViewRendererInterface renderer) {
/* 141 */     show(renderer, 0.0D, 0.0D, false);
/* 142 */     renderer.setPreferredSize((int)getWidth(), (int)getHeight());
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\jMondrian\figures\Figure.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */