/*    */ package classes.lrg.jMondrian.layouts;
/*    */ 
/*    */ import lrg.jMondrian.figures.Node;
/*    */ import lrg.jMondrian.layouts.CrossReductionTreeLayout;
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
/*    */ class MNode
/*    */ {
/* 44 */   private Node n = null;
/*    */   
/*    */   private boolean dummy = false;
/*    */   
/*    */   public MNode(boolean dummy) {
/* 49 */     this();
/* 50 */     this.dummy = dummy;
/*    */   }
/*    */   public MNode() {}
/*    */   public MNode(Node n) {
/* 54 */     this();
/* 55 */     this.n = n;
/*    */   }
/*    */ 
/*    */   
/* 59 */   public boolean isDummy() { return this.dummy; }
/*    */ 
/*    */ 
/*    */   
/* 63 */   public Node getNode() { return this.n; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\jMondrian\layouts\CrossReductionTreeLayout$MNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */