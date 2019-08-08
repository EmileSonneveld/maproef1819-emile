/*     */ package classes.lrg.insider.plugins.tools.memoria.graphgen;
/*     */ 
/*     */ import cdc.clusters.ExtendedWeightedEdge;
/*     */ import cdc.clusters.Node;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import lrg.insider.plugins.tools.memoria.graphgen.GraphvizExporter;
/*     */ import lrg.insider.plugins.tools.memoria.graphgen.SystemGraph;
/*     */ import lrg.insider.plugins.tools.memoria.graphgen.SystemSubgraph;
/*     */ import lrg.insider.plugins.tools.memoria.graphgen.utils.StringUtils;
/*     */ import lrg.memoria.core.Component;
/*     */ import lrg.memoria.core.DataAbstraction;
/*     */ import org.jgrapht.Graph;
/*     */ 
/*     */ public class GraphvizExporter
/*     */ {
/*     */   private static final double minHue = 0.15D;
/*  23 */   private static double crtHue = 0.15D;
/*     */   private static final double maxHue = 1.0D;
/*     */   private static final double hueStep = 0.2D;
/*     */   private static final double fixedSat = 0.941176D;
/*     */   private static final double fixedLum = 0.9375D;
/*     */   
/*     */   public static void exportGraph(Graph<Node, ExtendedWeightedEdge> graph, String graphName, String dirName, String fileName) {
/*     */     try {
/*  31 */       FileWriter writer = new FileWriter(String.valueOf(dirName) + "/" + fileName);
/*     */       
/*  33 */       writeHeader(graphName, writer);
/*     */       
/*  35 */       writeGraphContent(graph, writer);
/*     */       
/*  37 */       writeFooter(writer);
/*     */       
/*  39 */       writer.close();
/*  40 */     } catch (IOException e) {
/*     */       
/*  42 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   private static void writeFooter(FileWriter writer) throws IOException { writer.write("}\n"); }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void writeHeader(String graphName, FileWriter writer) throws IOException {
/*  54 */     String text = "// Generated at " + (new GregorianCalendar()).getTime().toString() + " by GraphGenerator, \n// (c) Dan Cosma & LRG, 2006\n\n";
/*  55 */     text = String.valueOf(text) + "digraph " + graphName + "\n{\n";
/*  56 */     text = String.valueOf(text) + "node [color=green];\n";
/*  57 */     writer.write(text);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void exportClusters(SystemGraph<Node, ExtendedWeightedEdge> graph, List<SystemSubgraph<Node, ExtendedWeightedEdge>> clusters, String graphName, String dirName, String fileName, boolean colouredClusters) {
/*     */     try {
/*  64 */       FileWriter writer = new FileWriter(String.valueOf(dirName) + "/" + fileName);
/*     */       
/*  66 */       writeHeader(graphName, writer);
/*     */       
/*  68 */       String text = "";
/*  69 */       text = String.valueOf(text) + "compound=true;\n";
/*  70 */       writer.write(text);
/*     */       
/*  72 */       for (SystemSubgraph<Node, ExtendedWeightedEdge> subgraph : clusters) {
/*     */         
/*  74 */         text = "subgraph cluster_" + subgraph.getName() + "{\n";
/*     */         
/*  76 */         if (colouredClusters)
/*     */         {
/*  78 */           text = String.valueOf(text) + "bgcolor =\"#" + hslToRGB(crtHue, 0.941176D, 0.9375D) + "\"";
/*     */         }
/*  80 */         text = String.valueOf(text) + "label=\"" + subgraph.getName() + "\";\n";
/*  81 */         text = String.valueOf(text) + "fontsize=24;\n";
/*     */         
/*  83 */         writer.write(text);
/*     */         
/*  85 */         writeGraphContent(subgraph, writer);
/*     */         
/*  87 */         text = "}\n";
/*  88 */         writer.write(text);
/*     */         
/*  90 */         crtHue += 0.2D;
/*  91 */         if (crtHue > 1.0D) {
/*  92 */           crtHue = 0.15D;
/*     */         }
/*     */       } 
/*  95 */       for (SystemSubgraph<Node, ExtendedWeightedEdge> subgraph : clusters) {
/*     */         
/*  97 */         Component component = subgraph.getCorrespondingComponent();
/*  98 */         ArrayList<ExtendedWeightedEdge> remoteEdges = (ArrayList)component.getAnnotation("component_remoteEdges");
/*     */         
/* 100 */         for (ExtendedWeightedEdge edge : remoteEdges)
/*     */         {
/* 102 */           writeEdge(graph, writer, edge);
/*     */         }
/*     */       } 
/*     */       
/* 106 */       writeFooter(writer);
/*     */       
/* 108 */       writer.close();
/*     */     }
/* 110 */     catch (IOException e) {
/*     */       
/* 112 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void writeGraphContent(Graph<Node, ExtendedWeightedEdge> graph, FileWriter writer) throws IOException {
/* 119 */     Set<Node> nodes = graph.vertexSet();
/* 120 */     Iterator<Node> it1 = nodes.iterator();
/* 121 */     while (it1.hasNext()) {
/*     */       
/* 123 */       Node node = (Node)it1.next();
/*     */       
/* 125 */       String color = "blue";
/*     */       
/* 127 */       if (((DataAbstraction)node.getObject()).getAnnotation("class_isFrontier") != null) {
/* 128 */         color = "red";
/*     */       }
/* 130 */       String name = node.getName();
/* 131 */       String label = name.substring(name.lastIndexOf('.') + 1);
/*     */       
/* 133 */       String comment = "";
/*     */ 
/*     */ 
/*     */       
/* 137 */       String fontcolor = "black";
/* 138 */       if (((DataAbstraction)node.getObject()).isInterface()) {
/* 139 */         fontcolor = "forestgreen";
/*     */       }
/* 141 */       writer.write(String.valueOf(StringUtils.noDots(name)) + " [color=" + color + ", label=\"" + comment + label + "\", fontcolor=" + fontcolor + "];\n");
/*     */     } 
/*     */     
/* 144 */     Set<ExtendedWeightedEdge> edges = graph.edgeSet();
/*     */     
/* 146 */     Iterator<ExtendedWeightedEdge> it2 = edges.iterator();
/* 147 */     while (it2.hasNext()) {
/*     */       
/* 149 */       ExtendedWeightedEdge edge = (ExtendedWeightedEdge)it2.next();
/* 150 */       writeEdge(graph, writer, edge);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void writeEdge(Graph<Node, ExtendedWeightedEdge> graph, FileWriter writer, ExtendedWeightedEdge edge) throws IOException {
/* 156 */     String color = edge.getProperty("colour");
/* 157 */     if (color == null) {
/* 158 */       color = "green";
/*     */     }
/* 160 */     String label = "";
/* 161 */     if (edge.getProperty("linksToFrontier") != null && 
/* 162 */       edge.getProperty("inheritance") == null) {
/*     */       
/* 164 */       label = "remote";
/* 165 */       color = "red";
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 174 */     String arrow = "normal";
/* 175 */     if (edge.getProperty("inheritance") != null) {
/* 176 */       arrow = "empty";
/*     */     }
/* 178 */     writer.write(String.valueOf(StringUtils.noDots(((Node)graph.getEdgeSource(edge)).getName())) + " -> " + StringUtils.noDots(((Node)graph.getEdgeTarget(edge)).getName()) + " [color=" + color + ", label=\"" + label + "\", arrowhead=" + arrow + "];\n");
/*     */   }
/*     */ 
/*     */   
/*     */   private static String hslToRGB(double h, double s, double l) {
/* 183 */     double[] color = new double[3];
/*     */     
/* 185 */     if (s == 0.0D) {
/*     */       
/* 187 */       for (int i = 0; i < 3; i++) {
/* 188 */         color[i] = l;
/*     */       
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 195 */       double temp2, temp3[] = new double[3];
/*     */       
/* 197 */       if (l < 0.5D) {
/* 198 */         temp2 = l * (1.0D + s);
/*     */       } else {
/* 200 */         temp2 = l + s - l * s;
/*     */       } 
/* 202 */       double temp1 = 2.0D * l - temp2;
/*     */       
/* 204 */       temp3[0] = h + 0.3333333333333333D;
/* 205 */       temp3[1] = h;
/* 206 */       temp3[2] = h - 0.3333333333333333D;
/*     */       
/* 208 */       for (int i = 0; i < 3; i++) {
/*     */         
/* 210 */         if (temp3[i] < 0.0D) temp3[i] = temp3[i] + 1.0D; 
/* 211 */         if (temp3[i] > 1.0D) temp3[i] = temp3[i] - 1.0D;
/*     */       
/*     */       } 
/* 214 */       for (int i = 0; i < 3; i++) {
/*     */         
/* 216 */         if (temp3[i] < 0.16666666666666666D) {
/* 217 */           color[i] = temp1 + (temp2 - temp1) * 6.0D * temp3[i];
/*     */         }
/* 219 */         else if (temp3[i] >= 0.16666666666666666D && temp3[i] < 0.5D) {
/* 220 */           color[i] = temp2;
/*     */         }
/* 222 */         else if (temp3[i] >= 0.5D && temp3[i] < 0.6666666666666666D) {
/* 223 */           color[i] = temp1 + (temp2 - temp1) * (0.6666666666666666D - temp3[i]) * 6.0D;
/*     */         } else {
/* 225 */           color[i] = temp1;
/*     */         } 
/*     */       } 
/*     */     } 
/* 229 */     String hex = "";
/* 230 */     for (int i = 0; i < 3; i++) {
/*     */       
/* 232 */       int c = (int)(255.0D * color[i]);
/* 233 */       hex = String.valueOf(hex) + Integer.toHexString(c);
/*     */     } 
/*     */     
/* 236 */     return hex;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\memoria\graphgen\GraphvizExporter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */