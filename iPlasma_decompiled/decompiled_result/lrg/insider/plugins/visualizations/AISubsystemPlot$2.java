/*    */ package classes.lrg.insider.plugins.visualizations;
/*    */ 
/*    */ import java.util.List;
/*    */ import lrg.insider.plugins.visualizations.AISubsystemPlot;
/*    */ import lrg.insider.util.Visualization;
/*    */ import lrg.jMondrian.commands.AbstractFigureDescriptionCommand;
/*    */ import lrg.jMondrian.figures.Figure;
/*    */ import lrg.jMondrian.layouts.ScatterPlotLayout;
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
/*    */ class null
/*    */   extends AbstractFigureDescriptionCommand
/*    */ {
/*    */   public Figure describe() {
/* 36 */     Figure v = new Figure();
/* 37 */     List theNodes = (List)this.receiver;
/* 38 */     v.nodesUsing(theNodes, (
/* 39 */         new RectangleNodePainter(3.0D, 3.0D, true)).name(Visualization.stringCommand("Name"))
/* 40 */         .color(new Object(this))
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 48 */         .x(new Object(this))
/*    */ 
/*    */ 
/*    */         
/* 52 */         .y(new Object(this)));
/*    */ 
/*    */ 
/*    */     
/* 56 */     v.layout(new ScatterPlotLayout());
/* 57 */     return v;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\visualizations\AISubsystemPlot$2.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */