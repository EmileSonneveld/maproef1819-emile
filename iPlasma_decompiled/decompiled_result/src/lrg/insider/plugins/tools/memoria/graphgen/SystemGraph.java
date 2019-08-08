/*    */ package classes.lrg.insider.plugins.tools.memoria.graphgen;
/*    */ 
/*    */ import cdc.clusters.ExtendedWeightedEdge;
/*    */ import cdc.clusters.Node;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.GraphvizExporter;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.SystemGraph;
/*    */ import org.jgrapht.EdgeFactory;
/*    */ import org.jgrapht.graph.DirectedWeightedMultigraph;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SystemGraph<V, E>
/*    */   extends DirectedWeightedMultigraph<Node, ExtendedWeightedEdge>
/*    */ {
/*    */   private static final long serialVersionUID = -8496579278148059150L;
/*    */   
/* 20 */   public SystemGraph(Class<? extends ExtendedWeightedEdge> arg0) { super(arg0); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 25 */   public SystemGraph(EdgeFactory<Node, ExtendedWeightedEdge> arg0) { super(arg0); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 30 */   public void exportAsGraphviz(String graphName, String dirName, String fileName) { GraphvizExporter.exportGraph(this, graphName, dirName, fileName); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\memoria\graphgen\SystemGraph.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */