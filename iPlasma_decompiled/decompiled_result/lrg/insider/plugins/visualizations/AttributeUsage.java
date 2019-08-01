/*     */ package classes.lrg.insider.plugins.visualizations;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import lrg.common.abstractions.entities.AbstractEntity;
/*     */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*     */ import lrg.common.abstractions.entities.EntityType;
/*     */ import lrg.common.abstractions.entities.GroupEntity;
/*     */ import lrg.common.abstractions.plugins.visualization.AbstractVisualization;
/*     */ import lrg.insider.plugins.groups.memoria.CallRelation;
/*     */ import lrg.insider.plugins.visualizations.AttributeUsage;
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
/*     */ public class AttributeUsage
/*     */   extends AbstractVisualization
/*     */ {
/*     */   private Figure f;
/*     */   
/*  33 */   public AttributeUsage() { super("Attribute Usage", "Attribute Usage", "attribute"); }
/*     */ 
/*     */   
/*     */   public void view(AbstractEntityInterface theAttribute) {
/*  37 */     GroupEntity clientClassesLayer = new GroupEntity("Client Classes", new EntityType(""));
/*  38 */     GroupEntity attributeClassLayer = new GroupEntity("Attribute's Class", new EntityType(""));
/*  39 */     GroupEntity allLayers = new GroupEntity(String.valueOf(theAttribute.getName()) + " layer", new EntityType(""));
/*  40 */     AbstractEntity abstractEntity = theAttribute.belongsTo("class");
/*     */     
/*  42 */     GroupEntity clientMethods = theAttribute.getGroup("methods accessing variable").applyFilter("model function");
/*     */     
/*  44 */     clientClassesLayer.addAll(((GroupEntity)clientMethods.belongsTo("class")).distinct().exclude((AbstractEntity)abstractEntity));
/*     */ 
/*     */     
/*  47 */     attributeClassLayer.add(abstractEntity);
/*     */ 
/*     */     
/*  50 */     if (clientClassesLayer.size() > 0) allLayers.add(clientClassesLayer); 
/*  51 */     allLayers.add(attributeClassLayer);
/*     */     
/*  53 */     ArrayList<AbstractEntityInterface> globalLayer = new ArrayList<AbstractEntityInterface>();
/*  54 */     globalLayer.add(allLayers);
/*     */     
/*  56 */     this.f = new Figure();
/*     */     
/*  58 */     this.f.nodesUsingForEach(globalLayer, 
/*  59 */         new RectangleNodePainter(false), 
/*  60 */         new Object(this, clientClassesLayer, attributeClassLayer, abstractEntity, clientMethods, theAttribute));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 152 */     ArrayList<CallRelation> inEdges = new ArrayList<CallRelation>();
/* 153 */     ArrayList<AbstractEntity> accessMeMethods = clientMethods.getElements();
/* 154 */     for (AbstractEntity userMth : accessMeMethods) {
/* 155 */       inEdges.add(new CallRelation((AbstractEntity)theAttribute, userMth));
/*     */     }
/*     */     
/* 158 */     this.f.edgesUsing(inEdges, (
/* 159 */         new LineEdgePainter(
/* 160 */           Visualization.entityCommand("getCallsNode"), 
/* 161 */           Visualization.entityCommand("getIsCalledNode")))
/* 162 */         .color(CommandColor.INVISIBLE));
/*     */     
/* 164 */     this.f.layout(new FlowLayout());
/*     */     
/* 166 */     ViewRenderer r = new ViewRenderer("Attribute Usage on " + theAttribute.getName());
/* 167 */     this.f.renderOn(r);
/* 168 */     r.open();
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\visualizations\AttributeUsage.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */