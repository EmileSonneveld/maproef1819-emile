/*     */ package classes.lrg.insider.plugins.visualizations;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import lrg.common.abstractions.entities.AbstractEntity;
/*     */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*     */ import lrg.common.abstractions.entities.GroupEntity;
/*     */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*     */ import lrg.common.abstractions.plugins.filters.composed.NotComposedFilteringRule;
/*     */ import lrg.common.abstractions.plugins.visualization.AbstractVisualization;
/*     */ import lrg.insider.plugins.visualizations.ClassComparator;
/*     */ import lrg.insider.plugins.visualizations.DesignFlawsOverview;
/*     */ import lrg.insider.plugins.visualizations.HierarchyComparator;
/*     */ import lrg.jMondrian.figures.Figure;
/*     */ import lrg.jMondrian.layouts.FlowLayout;
/*     */ import lrg.jMondrian.painters.RectangleNodePainter;
/*     */ import lrg.jMondrian.view.ViewRenderer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DesignFlawsOverview
/*     */   extends AbstractVisualization
/*     */ {
/*     */   private Figure theFigure;
/*     */   
/*  54 */   public DesignFlawsOverview() { super("Design Flaws Overview", "System Complexity", new String[] { "system", "package" }); }
/*     */ 
/*     */   
/*     */   public void view(AbstractEntityInterface entity) {
/*  58 */     GroupEntity allClasses = entity.getGroup("class group").applyFilter("model class").applyFilter(new NotComposedFilteringRule(new FilteringRule("is interface", "is true", "class")));
/*     */     
/*  60 */     GroupEntity packageGroup = new GroupEntity("package group", new ArrayList());
/*  61 */     if (entity.getEntityType().getName().compareTo("package") == 0) {
/*  62 */       packageGroup.add(entity);
/*     */     } else {
/*     */       
/*  65 */       packageGroup.addAll(entity.getGroup("package group"));
/*     */     } 
/*  67 */     GroupEntity allEdges = new GroupEntity("all inheritance relations", new ArrayList());
/*  68 */     Iterator<AbstractEntity> iterator = packageGroup.iterator();
/*  69 */     while (iterator.hasNext()) {
/*  70 */       AbstractEntity packageEntity = (AbstractEntity)iterator.next();
/*  71 */       allEdges.addAll(packageEntity.getGroup("all inheritance relations"));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  77 */     ArrayList theLayers = new ArrayList();
/*     */     
/*  79 */     GroupEntity standaloneClasses = new GroupEntity("standalone", new ArrayList());
/*  80 */     ArrayList<AbstractEntityInterface> theRoots = allClasses.getElements();
/*  81 */     for (AbstractEntityInterface aRoot : theRoots) {
/*  82 */       if (aRoot.getGroup("all ancestors").intersect(allClasses).size() > 0)
/*  83 */         continue;  GroupEntity descendants = aRoot.getGroup("all descendants").intersect(allClasses);
/*  84 */       if (descendants.size() == 0) { standaloneClasses.add(aRoot); continue; }
/*     */       
/*  86 */       GroupEntity aTree = new GroupEntity("tree", new ArrayList());
/*  87 */       aTree.add(aRoot); aTree.addAll(descendants);
/*  88 */       theLayers.add(aTree);
/*     */     } 
/*     */ 
/*     */     
/*  92 */     Collections.sort(theLayers, new HierarchyComparator());
/*  93 */     Collections.sort(standaloneClasses.getElements(), new ClassComparator());
/*  94 */     theLayers.add(standaloneClasses);
/*     */     
/*  96 */     this.theFigure = new Figure();
/*     */     
/*  98 */     this.theFigure.nodesUsingForEach(theLayers, new RectangleNodePainter(false), new Object(this, allEdges));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 145 */     ViewRenderer r = new ViewRenderer("Design Flaws Overview on " + entity.getName());
/* 146 */     this.theFigure.layout(new FlowLayout(20.0D, 20.0D, 500.0D));
/* 147 */     this.theFigure.renderOn(r);
/* 148 */     r.open();
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\visualizations\DesignFlawsOverview.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */