/*    */ package classes.lrg.insider.plugins.visualizations;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.visualization.AbstractVisualization;
/*    */ import lrg.insider.plugins.visualizations.ClassBlueprintOperations;
/*    */ import lrg.jMondrian.figures.Figure;
/*    */ import lrg.jMondrian.view.ViewRenderer;
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
/*    */ public class ClassBlueprint
/*    */   extends AbstractVisualization
/*    */ {
/* 69 */   public ClassBlueprint() { super("Class Blueprint", "Class Blueprint", "class"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public void view(AbstractEntityInterface entity) {
/* 74 */     ClassBlueprintOperations operations = new ClassBlueprintOperations();
/* 75 */     operations.buildFigure(entity);
/* 76 */     Figure f = operations.getFigure();
/*    */     
/* 78 */     ViewRenderer r = new ViewRenderer("Class Blueprint of " + entity.getName());
/* 79 */     f.renderOn(r);
/* 80 */     r.open();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\visualizations\ClassBlueprint.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */