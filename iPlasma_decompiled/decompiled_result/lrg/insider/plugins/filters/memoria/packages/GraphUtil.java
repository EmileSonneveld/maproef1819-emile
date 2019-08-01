/*    */ package classes.lrg.insider.plugins.filters.memoria.packages;
/*    */ 
/*    */ import graph.AdjacencyList;
/*    */ import graph.Graph;
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.insider.plugins.filters.memoria.packages.Node;
/*    */ 
/*    */ 
/*    */ public class GraphUtil
/*    */ {
/*    */   public static Graph buildGraph(AbstractEntityInterface entity, String belongsTo, String getGroup, String relationship) {
/* 14 */     AdjacencyList list = new AdjacencyList();
/* 15 */     GroupEntity packages = entity.belongsTo(belongsTo).getGroup(getGroup);
/* 16 */     Iterator<AbstractEntityInterface> iterator = packages.iterator();
/* 17 */     while (iterator.hasNext()) {
/* 18 */       AbstractEntityInterface packageWrapper = (AbstractEntityInterface)iterator.next();
/* 19 */       list.addNode(new Node(packageWrapper));
/* 20 */       GroupEntity efferentPackages = packageWrapper.getGroup(relationship);
/* 21 */       Iterator<AbstractEntityInterface> efferentIterator = efferentPackages.iterator();
/* 22 */       while (efferentIterator.hasNext()) {
/* 23 */         AbstractEntityInterface efferentPackage = (AbstractEntityInterface)efferentIterator.next();
/* 24 */         list.addEdge(new Node(packageWrapper), new Node(efferentPackage));
/*    */       } 
/*    */     } 
/* 27 */     Graph graph = new Graph(list);
/* 28 */     System.out.println("the graph->" + graph);
/* 29 */     return graph;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\packages\GraphUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */