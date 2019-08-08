/*    */ package classes.lrg.insider.plugins.visualizations;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.insider.plugins.visualizations.BlueprintFigureDescriptionCommand;
/*    */ import lrg.insider.plugins.visualizations.ColorNumericalCommand;
/*    */ import lrg.insider.plugins.visualizations.HeightNumericalCommand;
/*    */ import lrg.insider.plugins.visualizations.WidthNumericalCommand;
/*    */ import lrg.insider.util.Visualization;
/*    */ import lrg.jMondrian.commands.AbstractFigureDescriptionCommand;
/*    */ import lrg.jMondrian.figures.Figure;
/*    */ import lrg.jMondrian.layouts.HTreeLayout;
/*    */ import lrg.jMondrian.painters.RectangleNodePainter;
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
/*    */ class BlueprintFigureDescriptionCommand
/*    */   extends AbstractFigureDescriptionCommand
/*    */ {
/*    */   AbstractEntityInterface entity;
/*    */   
/* 51 */   public BlueprintFigureDescriptionCommand(AbstractEntityInterface theEntity) { this.entity = theEntity; }
/*    */   
/*    */   public Figure describe() {
/* 54 */     Figure fig = new Figure();
/* 55 */     fig.nodesUsing(((GroupEntity)this.receiver).getElements(), (
/* 56 */         new RectangleNodePainter(true))
/* 57 */         .name(Visualization.stringCommand("Name"))
/* 58 */         .width(new WidthNumericalCommand(this.entity))
/* 59 */         .height(new HeightNumericalCommand(this.entity))
/* 60 */         .color(new ColorNumericalCommand(this.entity)));
/*    */     
/* 62 */     fig.layout(new HTreeLayout(10.0D, 10.0D));
/* 63 */     return fig;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\visualizations\BlueprintFigureDescriptionCommand.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */