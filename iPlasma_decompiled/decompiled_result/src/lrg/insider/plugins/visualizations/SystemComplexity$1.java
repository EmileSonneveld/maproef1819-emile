/*     */ package classes.lrg.insider.plugins.visualizations;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import lrg.common.abstractions.entities.GroupEntity;
/*     */ import lrg.insider.plugins.groups.memoria.InheritanceRelation;
/*     */ import lrg.insider.plugins.visualizations.SystemComplexity;
/*     */ import lrg.insider.util.Visualization;
/*     */ import lrg.jMondrian.commands.AbstractFigureDescriptionCommand;
/*     */ import lrg.jMondrian.figures.Figure;
/*     */ import lrg.jMondrian.layouts.FlowLayout;
/*     */ import lrg.jMondrian.layouts.TreeLayout;
/*     */ import lrg.jMondrian.painters.LineEdgePainter;
/*     */ import lrg.jMondrian.painters.RectangleNodePainter;
/*     */ import lrg.jMondrian.util.LinearNormalizerColor;
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
/*  77 */     Figure f = new Figure();
/*  78 */     GroupEntity aLayer = (GroupEntity)this.receiver;
/*  79 */     f.nodesUsing(aLayer.getElements(), (
/*  80 */         new RectangleNodePainter(true)).width(Visualization.metricCommand("NOA", 5.0D))
/*  81 */         .height(Visualization.metricCommand("NOM", 5.0D))
/*  82 */         .name(Visualization.stringCommand("Name"))
/*  83 */         .color(new LinearNormalizerColor(allClasses.getElements(), Visualization.metricCommand("WMC"))));
/*     */     
/*  85 */     if (aLayer.getName().equals("tree")) {
/*  86 */       Iterator<InheritanceRelation> it = allEdges.iterator();
/*  87 */       ArrayList edges = new ArrayList();
/*  88 */       while (it.hasNext()) {
/*  89 */         InheritanceRelation rel = (InheritanceRelation)it.next();
/*  90 */         if (aLayer.isInGroup(rel.getSubClass()) && aLayer.isInGroup(rel.getSuperClass())) {
/*  91 */           edges.add(rel);
/*     */         }
/*     */       } 
/*  94 */       f.edgesUsing(edges, (
/*     */ 
/*     */           
/*  97 */           new LineEdgePainter(Visualization.entityCommand("getSubClass"), Visualization.entityCommand("getSuperClass"))).color(new Object(this)));
/*     */ 
/*     */       
/* 100 */       f.layout(new TreeLayout(5.0D, 10.0D));
/*     */     } else {
/* 102 */       f.layout(new FlowLayout(10.0D, 10.0D, 300.0D));
/* 103 */     }  return f;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\visualizations\SystemComplexity$1.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */