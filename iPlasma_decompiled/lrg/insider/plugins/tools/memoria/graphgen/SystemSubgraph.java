/*    */ package classes.lrg.insider.plugins.tools.memoria.graphgen;
/*    */ 
/*    */ import cdc.clusters.ExtendedWeightedEdge;
/*    */ import cdc.clusters.Node;
/*    */ import java.util.Set;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.GraphvizExporter;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.SystemSubgraph;
/*    */ import lrg.memoria.core.Component;
/*    */ import org.jgrapht.WeightedGraph;
/*    */ import org.jgrapht.graph.DirectedWeightedSubgraph;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SystemSubgraph<V, E>
/*    */   extends DirectedWeightedSubgraph<Node, ExtendedWeightedEdge>
/*    */ {
/*    */   private Component correspondingComponent;
/*    */   private String name;
/*    */   private static final long serialVersionUID = -5751731533337902378L;
/*    */   
/* 25 */   public SystemSubgraph(WeightedGraph<Node, ExtendedWeightedEdge> arg0, Set<Node> arg1, Set<ExtendedWeightedEdge> arg2) { super(arg0, arg1, arg2); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 31 */   public void exportAsGraphviz(String graphName, String dirName, String fileName) { GraphvizExporter.exportGraph(this, graphName, dirName, fileName); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 36 */   public Component getCorrespondingComponent() { return this.correspondingComponent; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 41 */   public void setCorrespondingComponent(Component correspondingComponent) { this.correspondingComponent = correspondingComponent; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 46 */   public String getName() { return this.name; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 51 */   public void setName(String name) { this.name = name; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\memoria\graphgen\SystemSubgraph.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */