/*    */ package classes.lrg.insider.plugins.tools.memoria.graphgen;
/*    */ 
/*    */ import cdc.clusters.ExtendedWeightedEdge;
/*    */ import cdc.clusters.Node;
/*    */ import cdc.clusters.NodeFactory;
/*    */ import java.io.FileWriter;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.insider.plugins.core.properties.AbstractDetail;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.Element;
/*    */ import org.jgrapht.Graph;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class Element
/*    */ {
/*    */   protected AbstractEntityInterface theEntity;
/*    */   protected int depth;
/*    */   protected Node graphNode;
/*    */   
/* 23 */   public String HTMLExport() { return String.valueOf(AbstractDetail.linkTo(this.theEntity)) + "\n"; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void dotFormatExport(FileWriter writer, String edgeColor, boolean isEdgeDirectedOutward) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void jgraphtExport(Graph<Node, ExtendedWeightedEdge> graph, boolean isEdgeDirectedOutward) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Element(AbstractEntityInterface anEntity, int depth, Graph<Node, ExtendedWeightedEdge> graph) {
/* 40 */     this.theEntity = anEntity;
/* 41 */     this.depth = depth;
/* 42 */     this.graphNode = NodeFactory.getInstance().getOrCreateNode(this.theEntity.getProperty("Address").toString(), this.theEntity);
/* 43 */     graph.addVertex(this.graphNode);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\memoria\graphgen\Element.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */