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
/*     */ import lrg.insider.plugins.visualizations.HierarchyComparator;
/*     */ import lrg.insider.plugins.visualizations.SystemComplexity;
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
/*     */ public class SystemComplexity
/*     */   extends AbstractVisualization
/*     */ {
/*     */   private Figure theFigure;
/*     */   
/*  31 */   public SystemComplexity() { super("System Complexity Overview", "System Complexity", new String[] { "system", "package" }); }
/*     */ 
/*     */   
/*     */   public void view(AbstractEntityInterface entity) {
/*  35 */     GroupEntity allClasses = entity.getGroup("class group").applyFilter("model class").applyFilter(new NotComposedFilteringRule(new FilteringRule("is interface", "is true", "class")));
/*     */     
/*  37 */     GroupEntity packageGroup = new GroupEntity("package group", new ArrayList());
/*  38 */     if (entity.getEntityType().getName().compareTo("package") == 0) {
/*  39 */       packageGroup.add(entity);
/*     */     } else {
/*     */       
/*  42 */       packageGroup.addAll(entity.getGroup("package group"));
/*     */     } 
/*  44 */     GroupEntity allEdges = new GroupEntity("all inheritance relations", new ArrayList());
/*  45 */     Iterator<AbstractEntity> iterator = packageGroup.iterator();
/*  46 */     while (iterator.hasNext()) {
/*  47 */       AbstractEntity packageEntity = (AbstractEntity)iterator.next();
/*  48 */       allEdges.addAll(packageEntity.getGroup("all inheritance relations"));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  54 */     ArrayList theLayers = new ArrayList();
/*     */     
/*  56 */     GroupEntity standaloneClasses = new GroupEntity("standalone", new ArrayList());
/*  57 */     ArrayList<AbstractEntityInterface> theRoots = allClasses.getElements();
/*  58 */     for (AbstractEntityInterface aRoot : theRoots) {
/*  59 */       if (aRoot.getGroup("all ancestors").intersect(allClasses).size() > 0)
/*  60 */         continue;  GroupEntity descendants = aRoot.getGroup("all descendants").intersect(allClasses);
/*  61 */       if (descendants.size() == 0) { standaloneClasses.add(aRoot); continue; }
/*     */       
/*  63 */       GroupEntity aTree = new GroupEntity("tree", new ArrayList());
/*  64 */       aTree.add(aRoot); aTree.addAll(descendants);
/*  65 */       theLayers.add(aTree);
/*     */     } 
/*     */ 
/*     */     
/*  69 */     Collections.sort(theLayers, new HierarchyComparator());
/*  70 */     Collections.sort(standaloneClasses.getElements(), new ClassComparator());
/*  71 */     theLayers.add(standaloneClasses);
/*     */     
/*  73 */     this.theFigure = new Figure();
/*     */     
/*  75 */     this.theFigure.nodesUsingForEach(theLayers, new RectangleNodePainter(false), new Object(this, allClasses, allEdges));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 106 */     ViewRenderer r = new ViewRenderer("System Complexity Overview on " + entity.getName());
/* 107 */     this.theFigure.layout(new FlowLayout(20.0D, 20.0D, 500.0D));
/* 108 */     this.theFigure.renderOn(r);
/* 109 */     r.open();
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\visualizations\SystemComplexity.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */