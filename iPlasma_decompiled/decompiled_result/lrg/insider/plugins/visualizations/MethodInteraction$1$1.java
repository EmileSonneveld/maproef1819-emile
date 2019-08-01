/*     */ package classes.lrg.insider.plugins.visualizations;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import lrg.common.abstractions.entities.AbstractEntity;
/*     */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*     */ import lrg.common.abstractions.entities.GroupEntity;
/*     */ import lrg.insider.plugins.visualizations.MethodInteraction;
/*     */ import lrg.insider.plugins.visualizations.comparators.ClassLOCComparator;
/*     */ import lrg.insider.util.Visualization;
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
/*     */ class null
/*     */   extends AbstractFigureDescriptionCommand
/*     */ {
/*     */   public Figure describe() {
/*  80 */     Figure classFig = new Figure();
/*  81 */     GroupEntity classList = (GroupEntity)this.receiver;
/*  82 */     Collections.sort(classList.getElements(), new ClassLOCComparator(allInvolvedMethods));
/*  83 */     classFig.nodesUsingForEach(classList.getElements(), (
/*  84 */         new RectangleNodePainter(20.0D, 30.0D, true))
/*  85 */         .name(Visualization.stringCommand("Name"))
/*  86 */         .frameColor(CommandColor.LIGHT_GRAY)
/*  87 */         .color(new Object(this, containerClass)), 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  96 */         new Object(this, theMethod, allInvolvedMethods, theyCallMe, overridenMethodCalls, iCallThem));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 160 */     classFig.layout(new FlowLayout(20.0D, 5.0D, 400.0D));
/* 161 */     return classFig;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\visualizations\MethodInteraction$1$1.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */