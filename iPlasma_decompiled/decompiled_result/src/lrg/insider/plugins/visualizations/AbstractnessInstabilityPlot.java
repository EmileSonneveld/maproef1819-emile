/*    */ package classes.lrg.insider.plugins.visualizations;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.common.abstractions.plugins.visualization.AbstractVisualization;
/*    */ import lrg.insider.plugins.visualizations.AbstractnessInstabilityPlot;
/*    */ import lrg.jMondrian.figures.Figure;
/*    */ import lrg.jMondrian.painters.RectangleNodePainter;
/*    */ import lrg.jMondrian.view.ViewRenderer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AbstractnessInstabilityPlot
/*    */   extends AbstractVisualization
/*    */ {
/* 22 */   public AbstractnessInstabilityPlot() { super("Abstractness Instability Plot", "Abstractness Instability Plot", "system"); }
/*    */ 
/*    */   
/*    */   public void view(AbstractEntityInterface entity) {
/* 26 */     FilteringRule significantPackages = new FilteringRule("TOTALDEP", ">", "package", 10.0D);
/* 27 */     List set = entity.getGroup("package group").applyFilter("model package").applyFilter(significantPackages).getElements();
/* 28 */     ArrayList container = new ArrayList(); container.add(set);
/*    */     
/* 30 */     Figure bigFigure = new Figure();
/* 31 */     bigFigure.nodesUsingForEach(container, (
/* 32 */         new RectangleNodePainter(310.0D, 310.0D, false))
/* 33 */         .color(new Object(this)), 
/* 34 */         new Object(this));
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
/* 61 */     ViewRenderer r = new ViewRenderer("Abstractness Instability Plot");
/* 62 */     bigFigure.renderOn(r);
/* 63 */     r.open();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\visualizations\AbstractnessInstabilityPlot.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */