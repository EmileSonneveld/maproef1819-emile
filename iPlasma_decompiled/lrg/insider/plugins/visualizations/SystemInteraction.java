/*    */ package classes.lrg.insider.plugins.visualizations;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.common.abstractions.plugins.filters.composed.NotComposedFilteringRule;
/*    */ import lrg.common.abstractions.plugins.visualization.AbstractVisualization;
/*    */ import lrg.insider.plugins.visualizations.SystemInteraction;
/*    */ import lrg.insider.util.Visualization;
/*    */ import lrg.jMondrian.figures.Figure;
/*    */ import lrg.jMondrian.layouts.TreeLayout;
/*    */ import lrg.jMondrian.painters.LineEdgePainter;
/*    */ import lrg.jMondrian.painters.RectangleNodePainter;
/*    */ import lrg.jMondrian.util.CommandColor;
/*    */ import lrg.jMondrian.view.ViewRenderer;
/*    */ import lrg.memoria.core.Call;
/*    */ import lrg.memoria.core.InheritanceRelation;
/*    */ import lrg.memoria.core.Method;
/*    */ import lrg.memoria.core.ModelElementList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SystemInteraction
/*    */   extends AbstractVisualization
/*    */ {
/* 29 */   public SystemInteraction() { super("System Interaction", "System Interaction", "system"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public void view(AbstractEntityInterface entity) {
/* 34 */     GroupEntity allClasses = entity.getGroup("class group").applyFilter("model class")
/* 35 */       .applyFilter(new NotComposedFilteringRule(new FilteringRule("is interface", "is true", "class")));
/* 36 */     GroupEntity allEdges = entity.getGroup("all inheritance relations");
/* 37 */     ArrayList edges = new ArrayList();
/* 38 */     Iterator<InheritanceRelation> it = allEdges.iterator();
/* 39 */     while (it.hasNext()) {
/* 40 */       InheritanceRelation rel = (InheritanceRelation)it.next();
/* 41 */       if (allClasses.isInGroup(rel.getSubClass()) && allClasses.isInGroup(rel.getSuperClass())) {
/* 42 */         edges.add(rel);
/*    */       }
/*    */     } 
/* 45 */     GroupEntity allMethods = allClasses.getGroup("method group");
/* 46 */     ArrayList calls = new ArrayList();
/* 47 */     Iterator it1 = allMethods.iterator();
/* 48 */     while (it1.hasNext()) {
/* 49 */       ModelElementList modelElementList = ((Method)it1.next()).getCallList();
/* 50 */       for (int i = 0; i < modelElementList.size(); i++) {
/* 51 */         if (allMethods.getElements().contains(((Call)modelElementList.get(i)).getBody().getScope()) && allMethods.getElements().contains(((Call)modelElementList.get(i)).getFunction())) {
/* 52 */           calls.add(modelElementList.get(i));
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 57 */     Figure f = new Figure();
/* 58 */     f.nodesUsingForEach(allClasses.getElements(), new RectangleNodePainter(true), new Object(this));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 68 */     f.edgesUsing(edges, new LineEdgePainter(Visualization.entityCommand("getSubClass"), Visualization.entityCommand("getSuperClass")));
/* 69 */     f.edgesUsing(calls, (new LineEdgePainter(Visualization.indirectionCommand("getScope", Visualization.entityCommand("getBody")), Visualization.entityCommand("getFunction"), true)).color(CommandColor.RED));
/* 70 */     f.layout(new TreeLayout(20.0D, 50.0D));
/*    */     
/* 72 */     ViewRenderer r = new ViewRenderer("SystemInteraction");
/* 73 */     f.renderOn(r);
/* 74 */     r.open();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\visualizations\SystemInteraction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */