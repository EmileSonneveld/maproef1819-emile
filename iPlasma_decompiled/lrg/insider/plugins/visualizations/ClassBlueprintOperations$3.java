/*     */ package classes.lrg.insider.plugins.visualizations;
/*     */ 
/*     */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*     */ import lrg.common.abstractions.entities.GroupEntity;
/*     */ import lrg.insider.plugins.visualizations.BlueprintFigureDescriptionCommand;
/*     */ import lrg.insider.plugins.visualizations.ClassBlueprintOperations;
/*     */ import lrg.jMondrian.commands.AbstractFigureDescriptionCommand;
/*     */ import lrg.jMondrian.figures.Figure;
/*     */ import lrg.jMondrian.layouts.FlowLayout;
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
/* 104 */     Figure figure = new Figure();
/* 105 */     figure.nodesUsingForEach((
/* 106 */         (GroupEntity)this.receiver).getElements(), (
/* 107 */         new RectangleNodePainter(true)).frameColor(CommandColor.LIGHT_GRAY), 
/* 108 */         new BlueprintFigureDescriptionCommand(entity));
/* 109 */     figure = ClassBlueprintOperations.this.addAttributeAccessEdges(figure, allMethods, allAttributes);
/* 110 */     figure = ClassBlueprintOperations.this.addMethodCallsEdges(figure, allMethods);
/* 111 */     figure.layout(new FlowLayout());
/* 112 */     return figure;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\visualizations\ClassBlueprintOperations$3.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */