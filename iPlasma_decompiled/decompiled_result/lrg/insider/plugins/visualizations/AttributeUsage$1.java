/*     */ package classes.lrg.insider.plugins.visualizations;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*     */ import lrg.common.abstractions.entities.GroupEntity;
/*     */ import lrg.insider.plugins.groups.memoria.CallRelation;
/*     */ import lrg.insider.plugins.visualizations.AttributeUsage;
/*     */ import lrg.insider.util.Visualization;
/*     */ import lrg.jMondrian.commands.AbstractFigureDescriptionCommand;
/*     */ import lrg.jMondrian.figures.Figure;
/*     */ import lrg.jMondrian.layouts.TreeLayout;
/*     */ import lrg.jMondrian.painters.LineEdgePainter;
/*     */ import lrg.jMondrian.painters.RectangleNodePainter;
/*     */ import lrg.jMondrian.util.CommandColor;
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
/*  62 */     Figure fig = new Figure();
/*  63 */     GroupEntity receiverLayer = (GroupEntity)this.receiver;
/*  64 */     fig.nodesUsingForEach(receiverLayer.getElements(), 
/*  65 */         new RectangleNodePainter(false), 
/*  66 */         new Object(this, containerClass, clientMethods, theAttribute));
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
/* 137 */     ArrayList<CallRelation> layerEdges = new ArrayList<CallRelation>();
/* 138 */     if (clientClassesLayer.size() > 0) layerEdges.add(new CallRelation(clientClassesLayer, attributeClassLayer)); 
/* 139 */     fig.edgesUsing(layerEdges, (
/* 140 */         new LineEdgePainter(
/* 141 */           Visualization.entityCommand("getIsCalledNode"), 
/* 142 */           Visualization.entityCommand("getCallsNode")))
/* 143 */         .color(CommandColor.INVISIBLE));
/*     */ 
/*     */     
/* 146 */     fig.layout(new TreeLayout(2.0D, 45.0D));
/* 147 */     return fig;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\visualizations\AttributeUsage$1.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */