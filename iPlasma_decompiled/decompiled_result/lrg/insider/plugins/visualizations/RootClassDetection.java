/*    */ package classes.lrg.insider.plugins.visualizations;
/*    */ 
/*    */ import java.util.List;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.visualization.AbstractVisualization;
/*    */ import lrg.insider.util.Visualization;
/*    */ import lrg.jMondrian.figures.Figure;
/*    */ import lrg.jMondrian.layouts.ScatterPlotLayout;
/*    */ import lrg.jMondrian.painters.RectangleNodePainter;
/*    */ import lrg.jMondrian.view.ViewRenderer;
/*    */ 
/*    */ 
/*    */ public class RootClassDetection
/*    */   extends AbstractVisualization
/*    */ {
/* 16 */   public RootClassDetection() { super("Root Class Detection", "Root Class Detection", "system"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public void view(AbstractEntityInterface entity) {
/* 21 */     List set = entity.getGroup("class group").applyFilter("model classes").getElements();
/*    */     
/* 23 */     Figure v = new Figure();
/* 24 */     v.nodesUsing(set, (new RectangleNodePainter(10.0D, 10.0D, true)).x(Visualization.metricCommand("NOD")).y(Visualization.metricCommand("NODD")));
/* 25 */     v.layout(new ScatterPlotLayout());
/*    */     
/* 27 */     ViewRenderer r = new ViewRenderer("RootClassDetection");
/* 28 */     v.renderOn(r);
/* 29 */     r.open();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\visualizations\RootClassDetection.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */