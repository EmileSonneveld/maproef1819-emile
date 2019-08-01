/*     */ package classes.lrg.insider.plugins.visualizations;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import lrg.common.abstractions.entities.GroupEntity;
/*     */ import lrg.insider.plugins.groups.memoria.InheritanceRelation;
/*     */ import lrg.insider.plugins.visualizations.DesignFlawsOverview;
/*     */ import lrg.insider.util.Visualization;
/*     */ import lrg.jMondrian.commands.AbstractFigureDescriptionCommand;
/*     */ import lrg.jMondrian.figures.Figure;
/*     */ import lrg.jMondrian.layouts.FlowLayout;
/*     */ import lrg.jMondrian.layouts.TreeLayout;
/*     */ import lrg.jMondrian.painters.LineEdgePainter;
/*     */ import lrg.jMondrian.painters.RectangleNodePainter;
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
/*     */ class null
/*     */   extends AbstractFigureDescriptionCommand
/*     */ {
/*     */   public Figure describe() {
/* 100 */     Figure f = new Figure();
/* 101 */     GroupEntity aLayer = (GroupEntity)this.receiver;
/* 102 */     f.nodesUsing(aLayer.getElements(), (
/* 103 */         new RectangleNodePainter(true)).width(Visualization.metricCommand("NOA", 5.0D))
/* 104 */         .height(Visualization.metricCommand("NOM", 5.0D))
/* 105 */         .name(Visualization.stringCommand("Name"))
/* 106 */         .color(new Object(this)));
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
/* 124 */     if (aLayer.getName().equals("tree")) {
/* 125 */       Iterator<InheritanceRelation> it = allEdges.iterator();
/* 126 */       ArrayList edges = new ArrayList();
/* 127 */       while (it.hasNext()) {
/* 128 */         InheritanceRelation rel = (InheritanceRelation)it.next();
/* 129 */         if (aLayer.isInGroup(rel.getSubClass()) && aLayer.isInGroup(rel.getSuperClass())) {
/* 130 */           edges.add(rel);
/*     */         }
/*     */       } 
/* 133 */       f.edgesUsing(edges, (
/*     */ 
/*     */           
/* 136 */           new LineEdgePainter(Visualization.entityCommand("getSubClass"), Visualization.entityCommand("getSuperClass"))).color(new Object(this)));
/*     */ 
/*     */       
/* 139 */       f.layout(new TreeLayout(5.0D, 10.0D));
/*     */     } else {
/* 141 */       f.layout(new FlowLayout(10.0D, 10.0D, 300.0D));
/* 142 */     }  return f;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\visualizations\DesignFlawsOverview$1.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */