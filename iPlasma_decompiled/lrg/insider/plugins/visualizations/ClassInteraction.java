/*    */ package classes.lrg.insider.plugins.visualizations;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntity;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.common.abstractions.plugins.filters.composed.NotComposedFilteringRule;
/*    */ import lrg.common.abstractions.plugins.visualization.AbstractVisualization;
/*    */ import lrg.insider.plugins.visualizations.ClassInteraction;
/*    */ import lrg.insider.util.Visualization;
/*    */ import lrg.jMondrian.figures.Figure;
/*    */ import lrg.jMondrian.layouts.TreeLayout;
/*    */ import lrg.jMondrian.painters.LineEdgePainter;
/*    */ import lrg.jMondrian.painters.RectangleNodePainter;
/*    */ import lrg.jMondrian.util.CommandColor;
/*    */ import lrg.jMondrian.view.ViewRenderer;
/*    */ import lrg.memoria.core.Call;
/*    */ import lrg.memoria.core.Function;
/*    */ import lrg.memoria.core.InheritanceRelation;
/*    */ import lrg.memoria.core.Method;
/*    */ import lrg.memoria.core.ModelElementList;
/*    */ 
/*    */ 
/*    */ public class ClassInteraction
/*    */   extends AbstractVisualization
/*    */ {
/* 29 */   public ClassInteraction() { super("Class Interaction", "Class Interaction", "class"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public void view(AbstractEntityInterface entity) {
/* 34 */     GroupEntity allCalee = entity.uses("operations calling me").applyFilter("model function").applyFilter(new NotComposedFilteringRule(new FilteringRule("is global", "is true", "method")));
/* 35 */     GroupEntity allCalled = entity.uses("operations called").applyFilter("model function").applyFilter(new NotComposedFilteringRule(new FilteringRule("is global", "is true", "method")));
/* 36 */     GroupEntity myMethods = entity.getGroup("method group").applyFilter("model function");
/* 37 */     GroupEntity allRepresentedMethods = allCalee.union(allCalled).union(myMethods).distinct();
/*    */     
/* 39 */     GroupEntity allCaleeClasses = ((GroupEntity)allCalee.belongsTo("class")).distinct();
/* 40 */     GroupEntity allCalledClasses = ((GroupEntity)allCalled.belongsTo("class")).distinct();
/* 41 */     allCalledClasses = allCalledClasses.union(allCalledClasses.getGroup("all descendants")).distinct();
/* 42 */     GroupEntity allClasses = allCaleeClasses.union(allCalledClasses).union((AbstractEntity)entity).union(entity.getGroup("all descendants")).distinct();
/*    */     
/* 44 */     GroupEntity allEdges = entity.belongsTo("system").getGroup("all inheritance relations");
/* 45 */     ArrayList edges = new ArrayList();
/* 46 */     Iterator<InheritanceRelation> it = allEdges.iterator();
/* 47 */     while (it.hasNext()) {
/* 48 */       InheritanceRelation rel = (InheritanceRelation)it.next();
/* 49 */       if (allClasses.isInGroup(rel.getSubClass()) && allClasses.isInGroup(rel.getSuperClass())) {
/* 50 */         edges.add(rel);
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 55 */     GroupEntity methods = entity.getGroup("method group");
/* 56 */     ArrayList calls = new ArrayList();
/* 57 */     Iterator it1 = methods.iterator();
/* 58 */     while (it1.hasNext()) {
/* 59 */       ModelElementList modelElementList = ((Function)it1.next()).getBody().getCallList();
/* 60 */       for (int i = 0; i < modelElementList.size(); i++) {
/* 61 */         Call x = (Call)modelElementList.get(i);
/* 62 */         if (allRepresentedMethods.isInGroup(x.getScope().getScope()) && allCalled.isInGroup(x.getFunction())) {
/* 63 */           calls.add(modelElementList.get(i));
/*    */         }
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 69 */     methods = allClasses.getGroup("method group");
/* 70 */     Iterator it2 = methods.iterator();
/* 71 */     while (it2.hasNext()) {
/* 72 */       ModelElementList modelElementList = ((Method)it2.next()).getCallList();
/* 73 */       for (int i = 0; i < modelElementList.size(); i++) {
/* 74 */         Call x = (Call)modelElementList.get(i);
/* 75 */         if (allRepresentedMethods.isInGroup(x.getScope().getScope()) && entity.getGroup("method group").isInGroup(x.getFunction())) {
/* 76 */           calls.add(modelElementList.get(i));
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 81 */     Figure f = new Figure();
/* 82 */     f.nodesUsingForEach(allClasses.getElements(), new RectangleNodePainter(true), new Object(this));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 92 */     f.edgesUsing(edges, new LineEdgePainter(Visualization.entityCommand("getSubClass"), Visualization.entityCommand("getSuperClass")));
/* 93 */     f.edgesUsing(calls, (new LineEdgePainter(Visualization.indirectionCommand("getScope", Visualization.entityCommand("getScope")), Visualization.entityCommand("getFunction"), true)).color(CommandColor.RED));
/* 94 */     f.layout(new TreeLayout(20.0D, 50.0D));
/*    */     
/* 96 */     ViewRenderer r = new ViewRenderer("ClassInteraction");
/* 97 */     f.renderOn(r);
/* 98 */     r.open();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\visualizations\ClassInteraction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */