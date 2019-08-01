/*    */ package classes.lrg.insider.plugins.visualizations;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntity;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.visualization.AbstractVisualization;
/*    */ import lrg.insider.plugins.visualizations.AllDescendantsWithName;
/*    */ import lrg.insider.util.Visualization;
/*    */ import lrg.jMondrian.figures.Figure;
/*    */ import lrg.jMondrian.layouts.CrossReductionTreeLayout;
/*    */ import lrg.jMondrian.painters.LineEdgePainter;
/*    */ import lrg.jMondrian.painters.RectangleNodePainter;
/*    */ import lrg.jMondrian.view.ViewRenderer;
/*    */ import lrg.memoria.core.InheritanceRelation;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AllDescendantsWithName
/*    */   extends AbstractVisualization
/*    */ {
/* 23 */   public AllDescendantsWithName() { super("All Descendants With Name", "All Descendants With Name", "class"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public void view(AbstractEntityInterface entity) {
/* 28 */     GroupEntity allClasses = entity.getGroup("all descendants");
/* 29 */     allClasses = allClasses.union((AbstractEntity)entity).distinct();
/*    */     
/* 31 */     GroupEntity allEdges = entity.belongsTo("system").getGroup("all inheritance relations");
/* 32 */     ArrayList edges = new ArrayList();
/* 33 */     Iterator<InheritanceRelation> it = allEdges.iterator();
/* 34 */     while (it.hasNext()) {
/* 35 */       InheritanceRelation rel = (InheritanceRelation)it.next();
/* 36 */       if (allClasses.isInGroup(rel.getSubClass()) && allClasses.isInGroup(rel.getSuperClass())) {
/* 37 */         edges.add(rel);
/*    */       }
/*    */     } 
/* 40 */     Figure f = new Figure();
/* 41 */     f.nodesUsing(allClasses.getElements(), (
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
/* 53 */         new RectangleNodePainter(20.0D, 20.0D, true)).color(new Object(this)).name(Visualization.stringCommand("Name")));
/* 54 */     f.edgesUsing(edges, new LineEdgePainter(Visualization.entityCommand("getSubClass"), Visualization.entityCommand("getSuperClass")));
/* 55 */     f.layout(new CrossReductionTreeLayout(20.0D, 50.0D, false));
/*    */     
/* 57 */     ViewRenderer r = new ViewRenderer("All Descendants");
/* 58 */     f.renderOn(r);
/* 59 */     r.open();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\visualizations\AllDescendantsWithName.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */