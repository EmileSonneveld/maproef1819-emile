/*    */ package classes.lrg.insider.plugins.core.properties.memoria.component;
/*    */ 
/*    */ import cdc.clusters.ExtendedWeightedEdge;
/*    */ import cdc.clusters.Node;
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.insider.plugins.core.properties.AbstractDetail;
/*    */ import lrg.insider.plugins.core.properties.memoria.component.Detail;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.SystemGraph;
/*    */ import lrg.memoria.core.Component;
/*    */ import lrg.memoria.core.DataAbstraction;
/*    */ import lrg.memoria.core.System;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Detail
/*    */   extends AbstractDetail
/*    */ {
/* 26 */   public Detail() { super("Detail", "Constructs a detailed HTML String to be shown in the browser.", "component", "string"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface aComponent) {
/* 30 */     String text = "";
/* 31 */     text = String.valueOf(text) + "<h1>Component " + linkTo(aComponent) + "</h1><hr><br>";
/*    */     
/* 33 */     GroupEntity classes = aComponent.getGroup("class group");
/*    */     
/* 35 */     text = String.valueOf(text) + "<b>Component Summary</b><br>";
/* 36 */     text = String.valueOf(text) + "Classes (" + linkToNumber(classes) + ") <br><br>";
/* 37 */     text = String.valueOf(text) + bulletedLinkList(classes.getElements());
/*    */     
/* 39 */     ArrayList<ExtendedWeightedEdge> remoteEdges = (ArrayList)aComponent.getAnnotation("component_remoteEdges");
/* 40 */     System system = (System)aComponent.getAnnotation("component_system");
/* 41 */     SystemGraph<Node, ExtendedWeightedEdge> graph = (SystemGraph)system.getAnnotation("system_jgraphtgraph");
/*    */     
/* 43 */     text = String.valueOf(text) + "<p>";
/* 44 */     text = String.valueOf(text) + "<b>Remote edges:</b><br><br>";
/* 45 */     for (ExtendedWeightedEdge edge : remoteEdges) {
/*    */       
/* 47 */       Node source = (Node)graph.getEdgeSource(edge);
/* 48 */       Node target = (Node)graph.getEdgeTarget(edge);
/* 49 */       DataAbstraction targetClass = (DataAbstraction)target.getObject();
/* 50 */       text = String.valueOf(text) + linkTo((DataAbstraction)source.getObject()) + " -> " + linkTo(targetClass) + "  in component " + linkTo((Component)targetClass.getAnnotation("component")) + "<br>";
/*    */     } 
/*    */ 
/*    */     
/* 54 */     text = String.valueOf(text) + "<br>";
/* 55 */     return new ResultEntity(text);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\properties\memoria\component\Detail.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */