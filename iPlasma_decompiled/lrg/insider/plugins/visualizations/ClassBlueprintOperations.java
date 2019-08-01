/*     */ package classes.lrg.insider.plugins.visualizations;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*     */ import lrg.common.abstractions.entities.EntityType;
/*     */ import lrg.common.abstractions.entities.GroupEntity;
/*     */ import lrg.insider.plugins.filters.memoria.methods.IsAccessor;
/*     */ import lrg.insider.plugins.filters.memoria.methods.IsConstructor;
/*     */ import lrg.insider.plugins.visualizations.Access;
/*     */ import lrg.insider.plugins.visualizations.Call;
/*     */ import lrg.insider.plugins.visualizations.ClassBlueprintOperations;
/*     */ import lrg.insider.util.Visualization;
/*     */ import lrg.jMondrian.figures.Figure;
/*     */ import lrg.jMondrian.layouts.FlowLayout;
/*     */ import lrg.jMondrian.painters.LineEdgePainter;
/*     */ import lrg.jMondrian.painters.RectangleNodePainter;
/*     */ import lrg.jMondrian.util.CommandColor;
/*     */ 
/*     */ 
/*     */ public class ClassBlueprintOperations
/*     */ {
/*  22 */   private GroupEntity allLayers = new GroupEntity("class layer", new EntityType(""));
/*  23 */   private GroupEntity initLayer = new GroupEntity("Initialization", new EntityType(""));
/*  24 */   private GroupEntity interfaceLayer = new GroupEntity("Interface", new EntityType(""));
/*  25 */   private GroupEntity implementationLayer = new GroupEntity("Implementation", new EntityType(""));
/*  26 */   private GroupEntity accessorLayer = new GroupEntity("Accessors", new EntityType(""));
/*  27 */   private GroupEntity attributeLayer = new GroupEntity("Attributes", new EntityType(""));
/*     */   
/*     */   private Figure entireBlueprintFigure;
/*     */   
/*     */   Figure addMethodCallsEdges(Figure figure, GroupEntity allMethods) {
/*  32 */     ArrayList<Call> edges = new ArrayList<Call>();
/*  33 */     ArrayList<AbstractEntityInterface> methodsList = allMethods.getElements();
/*     */     
/*  35 */     for (AbstractEntityInterface crtMethod : methodsList) {
/*  36 */       ArrayList<AbstractEntityInterface> methodAccessing = crtMethod.getGroup("operations called").intersect(allMethods).distinct().getElements();
/*  37 */       for (AbstractEntityInterface calledMethod : methodAccessing) edges.add(new Call(crtMethod, calledMethod));
/*     */     
/*     */     } 
/*  40 */     figure.edgesUsing(edges, (
/*     */ 
/*     */         
/*  43 */         new LineEdgePainter(Visualization.entityCommand("getCallee"), Visualization.entityCommand("getCaller"))).color(new Object(this)));
/*     */     
/*  45 */     return figure;
/*     */   }
/*     */   
/*     */   Figure addAttributeAccessEdges(Figure figure, GroupEntity allMethods, GroupEntity allAttributes) {
/*  49 */     ArrayList<Access> edges = new ArrayList<Access>();
/*  50 */     ArrayList<AbstractEntityInterface> methodsList = allMethods.getElements();
/*     */     
/*  52 */     for (AbstractEntityInterface aMethod : methodsList) {
/*  53 */       ArrayList<AbstractEntityInterface> fieldsAccessed = aMethod.getGroup("fields accessed").intersect(allAttributes).distinct().getElements();
/*  54 */       for (AbstractEntityInterface crtAttribute : fieldsAccessed) edges.add(new Access(aMethod, crtAttribute));
/*     */     
/*     */     } 
/*     */     
/*  58 */     figure.edgesUsing(edges, (
/*     */ 
/*     */         
/*  61 */         new LineEdgePainter(Visualization.entityCommand("getMethod"), Visualization.entityCommand("getAttribute"))).color(new Object(this)));
/*     */     
/*  63 */     return figure;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  68 */   private boolean isInterfaceMethod(AbstractEntityInterface crtMethod, GroupEntity methods) { return (crtMethod.getGroup("operations calling me").intersect(methods).size() == 0); }
/*     */ 
/*     */   
/*     */   private ArrayList<AbstractEntityInterface> createClassBluePrintNodes(GroupEntity allMethods, GroupEntity allAttributes) {
/*  72 */     ArrayList<AbstractEntityInterface> methodEntities = allMethods.getElements();
/*  73 */     ArrayList<AbstractEntityInterface> classBlueprint = new ArrayList<AbstractEntityInterface>();
/*     */     
/*  75 */     for (AbstractEntityInterface crtMethod : methodEntities) {
/*  76 */       if ((new IsConstructor()).applyFilter(crtMethod) || crtMethod.getName().contains("init")) { this.initLayer.add(crtMethod); continue; }
/*  77 */        if ((new IsAccessor()).applyFilter(crtMethod)) { this.accessorLayer.add(crtMethod); continue; }
/*  78 */        if (isInterfaceMethod(crtMethod, allMethods)) { this.interfaceLayer.add(crtMethod); continue; }
/*  79 */        this.implementationLayer.add(crtMethod);
/*     */     } 
/*     */     
/*  82 */     this.attributeLayer.addAll(allAttributes);
/*     */     
/*  84 */     this.allLayers.add(this.initLayer);
/*  85 */     this.allLayers.add(this.interfaceLayer);
/*  86 */     this.allLayers.add(this.implementationLayer);
/*  87 */     this.allLayers.add(this.accessorLayer);
/*  88 */     this.allLayers.add(this.attributeLayer);
/*  89 */     classBlueprint.add(this.allLayers);
/*  90 */     return classBlueprint;
/*     */   }
/*     */   
/*     */   public void buildFigure(AbstractEntityInterface entity) {
/*  94 */     GroupEntity allMethods = entity.getGroup("method group");
/*  95 */     GroupEntity allAttributes = entity.getGroup("attribute group");
/*  96 */     this.entireBlueprintFigure = new Figure();
/*  97 */     ArrayList<AbstractEntityInterface> classBluePrintNodes = createClassBluePrintNodes(allMethods, allAttributes);
/*  98 */     this.entireBlueprintFigure.nodesUsingForEach(
/*  99 */         classBluePrintNodes, (
/* 100 */         new RectangleNodePainter(true))
/* 101 */         .frameColor(CommandColor.LIGHT_GRAY), 
/* 102 */         new Object(this, entity, allMethods, allAttributes));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 116 */     this.entireBlueprintFigure.layout(new FlowLayout());
/*     */   }
/*     */ 
/*     */   
/* 120 */   public Figure getFigure() { return this.entireBlueprintFigure; }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\visualizations\ClassBlueprintOperations.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */