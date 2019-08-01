/*     */ package classes.lrg.insider.plugins.visualizations;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import lrg.common.abstractions.entities.AbstractEntity;
/*     */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*     */ import lrg.common.abstractions.entities.EntityType;
/*     */ import lrg.common.abstractions.entities.GroupEntity;
/*     */ import lrg.common.abstractions.plugins.visualization.AbstractVisualization;
/*     */ import lrg.insider.plugins.groups.memoria.CallRelation;
/*     */ import lrg.insider.plugins.visualizations.MethodInteraction;
/*     */ import lrg.insider.util.Visualization;
/*     */ import lrg.jMondrian.figures.Figure;
/*     */ import lrg.jMondrian.layouts.FlowLayout;
/*     */ import lrg.jMondrian.painters.LineEdgePainter;
/*     */ import lrg.jMondrian.painters.RectangleNodePainter;
/*     */ import lrg.jMondrian.util.CommandColor;
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
/*     */ public class MethodInteraction
/*     */   extends AbstractVisualization
/*     */ {
/*     */   private Figure f;
/*     */   
/*  35 */   public MethodInteraction() { super("Method Interaction", "Method Interaction", "method"); }
/*     */ 
/*     */   
/*     */   public void view(AbstractEntityInterface theMethod) {
/*  39 */     GroupEntity clientClassesLayer = new GroupEntity("Client Classes", new EntityType(""));
/*  40 */     GroupEntity serverClassesLayer = new GroupEntity("Server Classes", new EntityType(""));
/*  41 */     GroupEntity methodsClassLayer = new GroupEntity("Method's Class", new EntityType(""));
/*  42 */     GroupEntity allLayers = new GroupEntity(String.valueOf(theMethod.getName()) + " layer", new EntityType(""));
/*     */     
/*  44 */     AbstractEntity containerClass = theMethod.belongsTo("class");
/*  45 */     GroupEntity iCallThem = theMethod.getGroup("operations called").applyFilter("model function")
/*  46 */       .union(theMethod.getGroup("variables accessed").applyFilter("is attribute"));
/*  47 */     GroupEntity overridenMethodCalls = theMethod.getGroup("methods overriden").getGroup("operations calling me");
/*     */     
/*  49 */     GroupEntity theyCallMe = theMethod.getGroup("operations calling me").union(overridenMethodCalls).applyFilter("model function");
/*  50 */     GroupEntity classesThatCallMe = ((GroupEntity)theyCallMe.belongsTo("class")).distinct().exclude(containerClass);
/*  51 */     GroupEntity classesThatICall = ((GroupEntity)iCallThem.belongsTo("class")).distinct().exclude(containerClass);
/*  52 */     GroupEntity allInvolvedMethods = iCallThem.union(theyCallMe).union((AbstractEntity)theMethod);
/*  53 */     ArrayList<AbstractEntityInterface> allClasses = classesThatCallMe.union(classesThatICall).getElements();
/*  54 */     for (AbstractEntityInterface crtClass : allClasses) {
/*  55 */       GroupEntity members = crtClass.contains("method group").union(crtClass.contains("attribute group"));
/*  56 */       if (members.intersect(iCallThem).size() > members.intersect(theyCallMe).size()) {
/*  57 */         serverClassesLayer.add(crtClass); continue;
/*  58 */       }  clientClassesLayer.add(crtClass);
/*     */     } 
/*  60 */     methodsClassLayer.add(containerClass);
/*  61 */     if (clientClassesLayer.size() > 0) allLayers.add(clientClassesLayer); 
/*  62 */     allLayers.add(methodsClassLayer);
/*  63 */     if (serverClassesLayer.size() > 0) allLayers.add(serverClassesLayer); 
/*  64 */     ArrayList<AbstractEntityInterface> globalLayer = new ArrayList<AbstractEntityInterface>();
/*  65 */     globalLayer.add(allLayers);
/*     */     
/*  67 */     this.f = new Figure();
/*     */     
/*  69 */     this.f.nodesUsingForEach(globalLayer, 
/*  70 */         new RectangleNodePainter(false), 
/*  71 */         new Object(this, clientClassesLayer, methodsClassLayer, serverClassesLayer, allInvolvedMethods, containerClass, theMethod, theyCallMe, overridenMethodCalls, iCallThem));
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
/* 181 */     ArrayList<CallRelation> outEdges = new ArrayList<CallRelation>();
/*     */     
/* 183 */     ArrayList<AbstractEntity> callThemMethods = iCallThem.getElements();
/* 184 */     for (AbstractEntity calledMth : callThemMethods) {
/* 185 */       if (!calledMth.equals(theMethod)) outEdges.add(new CallRelation((AbstractEntity)theMethod, calledMth));
/*     */     
/*     */     } 
/* 188 */     this.f.edgesUsing(outEdges, (
/* 189 */         new LineEdgePainter(
/* 190 */           Visualization.entityCommand("getIsCalledNode"), 
/* 191 */           Visualization.entityCommand("getCallsNode")))
/* 192 */         .color(CommandColor.INVISIBLE));
/*     */     
/* 194 */     ArrayList<CallRelation> inEdges = new ArrayList<CallRelation>();
/* 195 */     ArrayList<AbstractEntity> callMeMethods = theyCallMe.getElements();
/* 196 */     for (AbstractEntity callerdMth : callMeMethods) {
/* 197 */       if (!callerdMth.equals(theMethod)) inEdges.add(new CallRelation(callerdMth, (AbstractEntity)theMethod));
/*     */     
/*     */     } 
/*     */     
/* 201 */     this.f.edgesUsing(inEdges, (
/* 202 */         new LineEdgePainter(
/* 203 */           Visualization.entityCommand("getIsCalledNode"), 
/* 204 */           Visualization.entityCommand("getCallsNode")))
/* 205 */         .color(CommandColor.INVISIBLE));
/*     */     
/* 207 */     this.f.layout(new FlowLayout());
/*     */ 
/*     */     
/* 210 */     ViewRenderer r = new ViewRenderer("Method Interaction on " + theMethod.getName());
/* 211 */     this.f.renderOn(r);
/* 212 */     r.open();
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\visualizations\MethodInteraction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */