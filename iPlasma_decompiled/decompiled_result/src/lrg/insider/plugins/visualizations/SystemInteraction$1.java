/*    */ package classes.lrg.insider.plugins.visualizations;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.insider.plugins.visualizations.SystemInteraction;
/*    */ import lrg.jMondrian.commands.AbstractFigureDescriptionCommand;
/*    */ import lrg.jMondrian.figures.Figure;
/*    */ import lrg.jMondrian.layouts.FlowLayout;
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
/* 61 */     Figure fig = new Figure();
/* 62 */     fig.nodesUsing(((AbstractEntityInterface)this.receiver).getGroup("method group").getElements(), new RectangleNodePainter(10.0D, 10.0D, true));
/* 63 */     fig.layout(new FlowLayout(50.0D));
/* 64 */     return fig;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\visualizations\SystemInteraction$1.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */