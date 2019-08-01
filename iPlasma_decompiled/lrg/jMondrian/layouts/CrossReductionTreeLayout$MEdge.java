/*     */ package classes.lrg.jMondrian.layouts;
/*     */ 
/*     */ import lrg.jMondrian.figures.EdgeFigure;
/*     */ import lrg.jMondrian.layouts.CrossReductionTreeLayout;
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
/*     */ class MEdge
/*     */ {
/*     */   private CrossReductionTreeLayout.MNode from;
/*     */   private CrossReductionTreeLayout.MNode to;
/*     */   private EdgeFigure edge;
/*     */   private boolean reverse;
/*     */   
/*     */   public MEdge(CrossReductionTreeLayout.MNode from, CrossReductionTreeLayout.MNode to) {
/*  70 */     this.reverse = false;
/*     */ 
/*     */     
/*  73 */     this.from = from;
/*  74 */     this.to = to;
/*  75 */     this.edge = null;
/*     */   }
/*     */   
/*     */   public MEdge(CrossReductionTreeLayout.MNode from, CrossReductionTreeLayout.MNode to, EdgeFigure e) {
/*  79 */     this(from, to);
/*  80 */     this.edge = e;
/*     */   }
/*     */ 
/*     */   
/*  84 */   public void setReverse(boolean value) { this.reverse = value; }
/*     */ 
/*     */ 
/*     */   
/*  88 */   public void reverse() { this.reverse = !this.reverse; }
/*     */ 
/*     */ 
/*     */   
/*  92 */   public CrossReductionTreeLayout.MNode getFrom() { return !this.reverse ? this.from : this.to; }
/*     */ 
/*     */ 
/*     */   
/*  96 */   public CrossReductionTreeLayout.MNode getTo() { return !this.reverse ? this.to : this.from; }
/*     */ 
/*     */ 
/*     */   
/* 100 */   public EdgeFigure getEdge() { return this.edge; }
/*     */ 
/*     */ 
/*     */   
/* 104 */   public boolean isDummy() { return (this.edge == null); }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\jMondrian\layouts\CrossReductionTreeLayout$MEdge.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */