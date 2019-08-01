/*     */ package classes.lrg.jMondrian.layouts;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import lrg.jMondrian.figures.EdgeFigure;
/*     */ import lrg.jMondrian.figures.Node;
/*     */ import lrg.jMondrian.layouts.AbstractLayout;
/*     */ import lrg.jMondrian.layouts.CrossReductionTreeLayout;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CrossReductionTreeLayout
/*     */   extends AbstractLayout
/*     */ {
/*  32 */   private int xDist = 20, yDist = 20;
/*  33 */   private int maxX = 0; private int maxY = 0;
/*  34 */   private final int dummyWidth = 20;
/*  35 */   private CrossReduction cr = CrossReduction.MEDIAN_ADJACENT;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean DEBUG = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean reverse = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CrossReductionTreeLayout(boolean reverse) {
/* 111 */     this(20.0D, 20.0D);
/* 112 */     this.reverse = reverse;
/*     */   }
/*     */   
/*     */   public CrossReductionTreeLayout(double xDist, double yDist) {
/* 116 */     this.xDist = (int)xDist;
/* 117 */     this.yDist = (int)yDist;
/*     */   }
/*     */ 
/*     */   
/* 121 */   public CrossReductionTreeLayout(CrossReduction cr) { this.cr = cr; }
/*     */ 
/*     */   
/*     */   public CrossReductionTreeLayout(boolean DEBUG, boolean reverse) {
/* 125 */     this.DEBUG = DEBUG;
/* 126 */     this.reverse = reverse;
/*     */   }
/*     */   
/*     */   public CrossReductionTreeLayout(double xDist, double yDist, boolean reverse) {
/* 130 */     this.xDist = (int)xDist;
/* 131 */     this.yDist = (int)yDist;
/* 132 */     this.reverse = reverse;
/*     */   }
/*     */   
/*     */   public CrossReductionTreeLayout(double xDist, double yDist, CrossReduction cr, boolean DEBUG) {
/* 136 */     this(xDist, yDist);
/* 137 */     this.cr = cr;
/* 138 */     this.DEBUG = DEBUG;
/*     */   }
/*     */   
/*     */   private List<MNode> getSinkNodes(List<MNode> mNodes, List<MEdge> mEdges) {
/* 142 */     List<MNode> sinkNodes = new ArrayList<MNode>();
/* 143 */     Iterator<MNode> itNode = mNodes.iterator();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 149 */     while (itNode.hasNext()) {
/* 150 */       MNode node = (MNode)itNode.next();
/* 151 */       boolean isSink = true;
/* 152 */       Iterator<MEdge> itEdge = mEdges.iterator();
/* 153 */       while (itEdge.hasNext()) {
/* 154 */         MEdge edge = (MEdge)itEdge.next();
/* 155 */         if (edge.getTo().equals(node)) {
/* 156 */           isSink = false;
/*     */         }
/*     */       } 
/* 159 */       if (isSink) {
/* 160 */         sinkNodes.add(node);
/*     */       }
/*     */     } 
/*     */     
/* 164 */     return sinkNodes;
/*     */   }
/*     */   
/*     */   private List<MNode> getParents(MNode node, List<MEdge> edgeList) {
/* 168 */     List<MNode> parentNodes = new ArrayList<MNode>();
/* 169 */     Iterator<MEdge> itEdge = edgeList.iterator();
/*     */ 
/*     */     
/* 172 */     while (itEdge.hasNext()) {
/* 173 */       MEdge edge = (MEdge)itEdge.next();
/* 174 */       if (edge.getFrom().equals(node)) {
/* 175 */         if (!edge.isDummy()) {
/* 176 */           edge.getEdge().setConnectionStyle(!this.reverse ? EdgeFigure.UP_MIDDLE : EdgeFigure.DOWN_MIDDLE, false);
/* 177 */           edge.getEdge().setConnectionStyle(!this.reverse ? EdgeFigure.DOWN_MIDDLE : EdgeFigure.UP_MIDDLE, true);
/*     */         } 
/* 179 */         parentNodes.add(edge.getTo());
/*     */       } 
/*     */     } 
/*     */     
/* 183 */     return parentNodes;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int findNodeInLayers(MNode node, List<List<MNode>> layers) {
/* 189 */     for (int i = layers.size() - 1; i >= 0; i--) {
/* 190 */       List<MNode> l = (List)layers.get(i);
/*     */       
/* 192 */       if (l.contains(node)) {
/*     */         break;
/*     */       }
/*     */     } 
/* 196 */     return i;
/*     */   }
/*     */   
/*     */   private void printTreeInfo(List<List<MNode>> layers, List<MEdge> mEdges) {
/* 200 */     int i = 0;
/* 201 */     for (List<MNode> layer : layers) {
/* 202 */       System.out.println("ii layer " + i++ + ":");
/* 203 */       for (MNode n : layer) {
/* 204 */         System.out.print("ii  [parents:");
/* 205 */         for (MEdge e : mEdges) {
/* 206 */           if (e.getFrom().equals(n)) {
/* 207 */             printNodeInfo(e.getTo());
/*     */           }
/*     */         } 
/* 210 */         System.out.print("]");
/*     */         
/* 212 */         printNodeInfo(n);
/*     */         
/* 214 */         System.out.print(" [children:");
/* 215 */         for (MEdge e : mEdges) {
/* 216 */           if (e.getTo().equals(n)) {
/* 217 */             printNodeInfo(e.getFrom());
/*     */           }
/*     */         } 
/* 220 */         System.out.println("]");
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void printNodeInfo(MNode n) {
/* 226 */     if (n.isDummy()) {
/* 227 */       System.out.print(" (dummy)");
/*     */     } else {
/* 229 */       System.out.print(" (" + n.getNode().getWidth() + ", " + n.getNode().getHeight() + ")");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void generateTree(List<Node> nodeList, List<EdgeFigure> edgeList, AbstractLayout.ControlXY xCmd, AbstractLayout.ControlXY yCmd) {
/* 234 */     List<MNode> mNodes = new ArrayList<MNode>();
/* 235 */     List<MEdge> mEdges = new ArrayList<MEdge>();
/*     */     
/* 237 */     Map<Node, MNode> hash = new HashMap<Node, MNode>();
/*     */ 
/*     */     
/* 240 */     List<List<MNode>> layers = new ArrayList<List<MNode>>();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 245 */     for (Node n : nodeList) {
/* 246 */       MNode m = new MNode(this, n);
/* 247 */       mNodes.add(m);
/* 248 */       hash.put(n, m);
/*     */     } 
/*     */     
/* 251 */     for (EdgeFigure e : edgeList) {
/* 252 */       MEdge tmpEdge = new MEdge(this, (MNode)hash.get(e.getFrom()), (MNode)hash.get(e.getTo()), e);
/* 253 */       tmpEdge.setReverse(this.reverse);
/* 254 */       mEdges.add(tmpEdge);
/*     */     } 
/*     */ 
/*     */     
/* 258 */     List<MNode> mSinkNodes = getSinkNodes(mNodes, mEdges);
/*     */ 
/*     */     
/* 261 */     assignLayers(layers, mSinkNodes, mEdges, 0);
/* 262 */     if (this.DEBUG) {
/* 263 */       printTreeInfo(layers, mEdges);
/*     */     }
/* 265 */     addDummyVertices(layers, mNodes, mEdges);
/* 266 */     if (this.DEBUG) {
/* 267 */       printTreeInfo(layers, mEdges);
/*     */     }
/*     */     
/* 270 */     switch ($SWITCH_TABLE$lrg$jMondrian$layouts$CrossReductionTreeLayout$CrossReduction()[this.cr.ordinal()]) {
/*     */       case 1:
/* 272 */         adjacentExchange(layers, mEdges);
/*     */         break;
/*     */       case 3:
/* 275 */         barycenterMethod(layers, mEdges);
/*     */         break;
/*     */       case 2:
/* 278 */         medianMethod(layers, mEdges);
/*     */         break;
/*     */       case 4:
/* 281 */         medianMethod(layers, mEdges);
/* 282 */         adjacentExchange(layers, mEdges);
/*     */         break;
/*     */     } 
/*     */     
/* 286 */     if (this.DEBUG) {
/* 287 */       printTreeInfo(layers, mEdges);
/*     */     }
/*     */     
/* 290 */     Collections.reverse(layers);
/*     */ 
/*     */     
/* 293 */     plotTree(layers, mNodes, mEdges, xCmd, yCmd);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void assignLayers(List<List<MNode>> layers, List<MNode> mNodes, List<MEdge> mEdges, int level) {
/* 301 */     if (mNodes.size() > 0 && layers.size() < level + 1) {
/* 302 */       layers.add(level, new ArrayList());
/*     */     }
/*     */     
/* 305 */     for (MNode n : mNodes) {
/* 306 */       boolean exists = false;
/*     */       int i;
/* 308 */       for (i = 0; i < layers.size(); i++) {
/* 309 */         List<MNode> l = (List)layers.get(i);
/* 310 */         if (l.contains(n)) {
/* 311 */           if (this.DEBUG) {
/* 312 */             System.out.print("!! found (" + n.getNode().getWidth() + 
/* 313 */                 ", " + n.getNode().getHeight() + ") on level " + i + "...");
/*     */           }
/* 315 */           if (i < level) {
/* 316 */             if (this.DEBUG)
/* 317 */               System.out.println("removing"); 
/* 318 */             l.remove(n); break;
/*     */           } 
/* 320 */           if (this.DEBUG)
/* 321 */             System.out.println("keeping"); 
/* 322 */           exists = true;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */       
/* 328 */       if (i == layers.size() && 
/* 329 */         this.DEBUG) {
/* 330 */         System.out.println("!! not found (" + 
/* 331 */             n.getNode().getWidth() + ", " + 
/* 332 */             n.getNode().getHeight() + ")...");
/*     */       }
/*     */       
/* 335 */       if (!exists) {
/* 336 */         if (this.DEBUG)
/* 337 */           System.out.println("!! adding " + 
/* 338 */               n.getNode().getWidth() + ", " + 
/* 339 */               n.getNode().getHeight() + " to level " + level); 
/* 340 */         ((List)layers.get(level)).add(n);
/*     */       } 
/*     */       
/* 343 */       List<MNode> parents = getParents(n, mEdges);
/* 344 */       assignLayers(layers, parents, mEdges, level + 1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addDummyVertices(List<List<MNode>> layers, List<MNode> mNodes, List<MEdge> mEdges) {
/* 351 */     List<MEdge> tempAddEdges = new ArrayList<MEdge>(), tempDelEdges = new ArrayList<MEdge>();
/* 352 */     List<MNode> tempAddNodes = new ArrayList<MNode>();
/* 353 */     List<List<MNode>> tempAddLayers = new ArrayList<List<MNode>>();
/*     */     
/* 355 */     for (List<MNode> layer : layers) {
/* 356 */       tempAddLayers.add(new ArrayList());
/*     */     }
/*     */     
/* 359 */     for (int i = 0; i < layers.size(); i++) {
/* 360 */       List<MNode> list = (List)layers.get(i);
/*     */       
/* 362 */       for (MNode node : list) {
/* 363 */         for (MEdge edge : mEdges) {
/* 364 */           if (edge.getFrom().equals(node)) {
/* 365 */             if (this.DEBUG) {
/* 366 */               System.out.println("## found edge (" + node.getNode().getWidth() + 
/* 367 */                   ", " + 
/* 368 */                   node.getNode().getHeight() + 
/* 369 */                   ") <- (" + 
/* 370 */                   edge.getTo().getNode().getWidth() + 
/* 371 */                   ", " + 
/* 372 */                   edge.getTo().getNode().getHeight() + 
/* 373 */                   ")");
/*     */             }
/* 375 */             int parentLevel = findNodeInLayers(edge.getTo(), layers);
/* 376 */             int childLevel = i;
/*     */ 
/*     */             
/* 379 */             if (parentLevel - childLevel > 1) {
/* 380 */               if (this.DEBUG) {
/* 381 */                 System.out.println("## insert dummy vertices from (" + 
/* 382 */                     node.getNode().getWidth() + ", " + 
/* 383 */                     node.getNode().getHeight() + ", level " + 
/* 384 */                     childLevel + ") to (" + 
/* 385 */                     edge.getTo().getNode().getWidth() + 
/* 386 */                     ", " + 
/* 387 */                     edge.getTo().getNode().getHeight() + 
/* 388 */                     ", level " + parentLevel + 
/* 389 */                     ")");
/*     */               }
/* 391 */               MNode prevDummy = node;
/*     */ 
/*     */               
/* 394 */               for (int j = childLevel; j < parentLevel - 1; j++) {
/* 395 */                 if (this.DEBUG) {
/* 396 */                   System.out.println("  ## dummyNode (" + (j + 1) + ")");
/*     */                 }
/* 398 */                 MNode dummyNode = new MNode(this, true);
/* 399 */                 MEdge dummyEdge = new MEdge(this, prevDummy, dummyNode);
/*     */                 
/* 401 */                 tempAddNodes.add(dummyNode);
/* 402 */                 ((List)tempAddLayers.get(j + 1)).add(dummyNode);
/*     */                 
/* 404 */                 tempAddEdges.add(dummyEdge);
/*     */                 
/* 406 */                 prevDummy = dummyNode;
/*     */               } 
/*     */               
/* 409 */               tempAddEdges.add(new MEdge(this, prevDummy, edge.getTo()));
/* 410 */               tempDelEdges.add(edge);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 417 */     mNodes.addAll(tempAddNodes);
/* 418 */     mEdges.addAll(tempAddEdges);
/*     */     
/* 420 */     mEdges.removeAll(tempDelEdges);
/*     */     
/* 422 */     for (int i = 0; i < layers.size(); i++) {
/* 423 */       ((List)layers.get(i)).addAll((Collection)tempAddLayers.get(i));
/*     */     }
/*     */   }
/*     */   
/*     */   private void medianMethod(List<List<MNode>> layers, List<MEdge> mEdges) {
/* 428 */     for (int i = 0; i < layers.size() - 1; i++) {
/* 429 */       medianMethodInternal((List)layers.get(i + 1), (List)layers.get(i), mEdges);
/*     */     }
/*     */   }
/*     */   
/*     */   private void medianMethodInternal(List<MNode> parentLayer, List<MNode> childLayer, List<MEdge> mEdges) {
/* 434 */     List<MEdge> edges = new ArrayList<MEdge>();
/*     */     
/* 436 */     for (MEdge e : mEdges) {
/* 437 */       if (parentLayer.contains(e.getTo()) && childLayer.contains(e.getFrom())) {
/* 438 */         edges.add(e);
/*     */       }
/*     */     } 
/*     */     
/* 442 */     int sum = 0;
/* 443 */     List<Integer> med = new ArrayList<Integer>(), bar = new ArrayList<Integer>();
/* 444 */     for (int i = 0; i < parentLayer.size(); i++) {
/* 445 */       MNode p = (MNode)parentLayer.get(i);
/* 446 */       List<Integer> xpos = new ArrayList<Integer>();
/*     */       
/* 448 */       for (int j = 0; j < childLayer.size(); j++) {
/* 449 */         for (MEdge e : edges) {
/* 450 */           if (e.getFrom().equals(childLayer.get(j)) && e.getTo().equals(p)) {
/* 451 */             xpos.add(Integer.valueOf(j));
/* 452 */             sum += j * 10;
/*     */           } 
/*     */         } 
/*     */         
/* 456 */         bar.add(Integer.valueOf(sum));
/*     */       } 
/*     */       
/* 459 */       if (this.DEBUG) {
/* 460 */         System.out.print("(node " + i);
/* 461 */         printNodeInfo(p);
/* 462 */         System.out.print(", " + childLayer.size() + " children) xpos[ ");
/* 463 */         for (Integer x : xpos) {
/* 464 */           System.out.print(x + " ");
/*     */         }
/*     */       } 
/*     */       
/* 468 */       int medval = ((Integer)xpos.get(xpos.size() / 2)).intValue();
/*     */       
/* 470 */       if (this.DEBUG) {
/* 471 */         System.out.println("] (m " + medval + ") ");
/*     */       }
/* 473 */       med.add(Integer.valueOf(medval));
/*     */     } 
/*     */     
/* 476 */     if (this.DEBUG) {
/* 477 */       System.out.print("2) layer = " + parentLayer.size() + " med[]: ");
/* 478 */       for (Integer m : med) {
/* 479 */         System.out.print(m + " ");
/*     */       }
/* 481 */       System.out.println();
/*     */     } 
/*     */     
/* 484 */     boolean changed = true;
/* 485 */     while (changed) {
/* 486 */       changed = false;
/*     */       
/* 488 */       for (int i = 0; i < parentLayer.size() - 1; i++) {
/* 489 */         if (((Integer)med.get(i)).intValue() > ((Integer)med.get(i + 1)).intValue()) {
/* 490 */           Collections.swap(parentLayer, i, i + 1);
/* 491 */           Collections.swap(med, i, i + 1);
/* 492 */           Collections.swap(bar, i, i + 1);
/* 493 */           changed = true;
/* 494 */         } else if (med.get(i) == med.get(i + true) && (
/* 495 */           (Integer)bar.get(i)).intValue() > ((Integer)bar.get(i + 1)).intValue()) {
/* 496 */           Collections.swap(parentLayer, i, i + 1);
/* 497 */           Collections.swap(med, i, i + 1);
/* 498 */           Collections.swap(bar, i, i + 1);
/* 499 */           changed = true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void barycenterMethod(List<List<MNode>> layers, List<MEdge> mEdges) {
/* 507 */     for (int i = 0; i < layers.size() - 1; i++) {
/* 508 */       barycenterMethodInternal((List)layers.get(i + 1), (List)layers.get(i), mEdges);
/*     */     }
/*     */   }
/*     */   
/*     */   private void barycenterMethodInternal(List<MNode> parentLayer, List<MNode> childLayer, List<MEdge> mEdges) {
/* 513 */     List<MEdge> edges = new ArrayList<MEdge>();
/*     */     
/* 515 */     for (MEdge e : mEdges) {
/* 516 */       if (parentLayer.contains(e.getTo()) && childLayer.contains(e.getFrom())) {
/* 517 */         edges.add(e);
/*     */       }
/*     */     } 
/*     */     
/* 521 */     int sum = 0;
/* 522 */     List<Integer> bar = new ArrayList<Integer>();
/* 523 */     for (int i = 0; i < parentLayer.size(); i++) {
/* 524 */       MNode p = (MNode)parentLayer.get(i);
/*     */       
/* 526 */       for (int j = 0; j < childLayer.size(); j++) {
/* 527 */         for (MEdge e : edges) {
/* 528 */           if (e.getFrom().equals(childLayer.get(j)) && e.getTo().equals(p)) {
/* 529 */             sum += j * 10;
/*     */           }
/*     */         } 
/*     */         
/* 533 */         bar.add(Integer.valueOf(sum / childLayer.size()));
/*     */       } 
/*     */     } 
/*     */     
/* 537 */     if (this.DEBUG) {
/* 538 */       for (Integer b : bar) {
/* 539 */         System.out.print(b + " ");
/*     */       }
/* 541 */       System.out.println();
/*     */     } 
/*     */     
/* 544 */     boolean changed = true;
/* 545 */     while (changed) {
/* 546 */       changed = false;
/*     */       
/* 548 */       for (int i = 0; i < parentLayer.size() - 1; i++) {
/* 549 */         if (((Integer)bar.get(i)).intValue() > ((Integer)bar.get(i + 1)).intValue()) {
/* 550 */           Collections.swap(parentLayer, i, i + 1);
/* 551 */           Collections.swap(bar, i, i + 1);
/* 552 */           changed = true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void adjacentExchange(List<List<MNode>> layers, List<MEdge> mEdges) {
/* 559 */     for (int i = layers.size() - 2; i >= 0; i--) {
/* 560 */       if (this.DEBUG) {
/* 561 */         System.out.println("reduce: processing L" + i + "-L" + (i + 1));
/*     */       }
/* 563 */       adjacentExchangeInternal((List)layers.get(i + 1), (List)layers.get(i), mEdges);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void adjacentExchangeInternal(List<MNode> parentLayer, List<MNode> childLayer, List<MEdge> mEdges) {
/*     */     boolean changed;
/* 570 */     List<MEdge> edges = new ArrayList<MEdge>();
/*     */     
/* 572 */     for (MEdge e : mEdges) {
/* 573 */       if (parentLayer.contains(e.getTo()) && childLayer.contains(e.getFrom())) {
/* 574 */         if (this.DEBUG) {
/* 575 */           System.out.print("reduce: added ");
/* 576 */           printNodeInfo(e.getFrom());
/* 577 */           System.out.print("->");
/* 578 */           printNodeInfo(e.getTo());
/* 579 */           System.out.println(" to edgelist");
/*     */         } 
/*     */         
/* 582 */         edges.add(e);
/*     */       } 
/*     */     } 
/*     */     
/*     */     do {
/* 587 */       changed = false;
/*     */ 
/*     */       
/* 590 */       for (int i = 0; i < childLayer.size() - 1; i++) {
/* 591 */         int cross1 = countCrossings(parentLayer, childLayer, edges, i, i + 1);
/*     */         
/* 593 */         Collections.swap(childLayer, i, i + 1);
/* 594 */         int cross2 = countCrossings(parentLayer, childLayer, edges, i, i + 1);
/*     */         
/* 596 */         if (cross1 > cross2) {
/* 597 */           changed = true;
/* 598 */           if (this.DEBUG) {
/* 599 */             System.out.print("$$ swapping ");
/* 600 */             printNodeInfo((MNode)childLayer.get(i));
/* 601 */             System.out.print(" with ");
/* 602 */             printNodeInfo((MNode)childLayer.get(i + 1));
/* 603 */             System.out.println();
/* 604 */             System.out.println("$$ changed: reduced crossings from " + cross1 + " to " + cross2);
/*     */           } 
/*     */         } else {
/* 607 */           if (this.DEBUG)
/* 608 */             System.out.println("$$ swapping back"); 
/* 609 */           Collections.swap(childLayer, i, i + 1);
/*     */         } 
/*     */       } 
/* 612 */     } while (changed);
/*     */   }
/*     */   
/*     */   private int countCrossings(List<MNode> parentLayer, List<MNode> childLayer, List<MEdge> edges, int u, int v) {
/* 616 */     int cr = 0;
/*     */     
/* 618 */     for (MEdge e : edges) {
/* 619 */       if (e.getFrom().equals(childLayer.get(u))) {
/* 620 */         for (MEdge f : edges) {
/* 621 */           if (f.getFrom().equals(childLayer.get(v))) {
/* 622 */             if (u < v && parentLayer.indexOf(e.getTo()) > parentLayer.indexOf(f.getTo())) {
/* 623 */               cr++; continue;
/* 624 */             }  if (u > v && parentLayer.indexOf(e.getFrom()) < parentLayer.indexOf(f.getFrom())) {
/* 625 */               if (this.DEBUG)
/* 626 */                 System.out.println("count: (shouldn't happen)"); 
/* 627 */               cr++; continue;
/* 628 */             }  if (u == v) {
/* 629 */               if (this.DEBUG)
/* 630 */                 System.out.println("count: Cuu == 0"); 
/* 631 */               return 0;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 638 */     if (this.DEBUG) {
/* 639 */       System.out.println("count: returning " + cr + " on (" + 
/* 640 */           u + ": " + (
/* 641 */           ((MNode)childLayer.get(u)).isDummy() ? "dummy" : (
/* 642 */           String.valueOf(((MNode)childLayer.get(u)).getNode().getWidth()) + ", " + ((MNode)childLayer.get(u)).getNode().getHeight())) + 
/* 643 */           ") vs (" + 
/* 644 */           v + ": " + (
/* 645 */           ((MNode)childLayer.get(v)).isDummy() ? "dummy" : (
/* 646 */           String.valueOf(((MNode)childLayer.get(v)).getNode().getWidth()) + ", " + ((MNode)childLayer.get(v)).getNode().getHeight())) + 
/* 647 */           ")");
/*     */     }
/* 649 */     return cr;
/*     */   }
/*     */   
/*     */   private void plotTree(List<List<MNode>> layers, List<MNode> mNodes, List<MEdge> mEdges, AbstractLayout.ControlXY xCmd, AbstractLayout.ControlXY yCmd) {
/* 653 */     int maxwidth = 0;
/* 654 */     List<Integer> heights = new ArrayList<Integer>(), widths = new ArrayList<Integer>();
/*     */     
/* 656 */     for (List<MNode> layer : layers) {
/* 657 */       int h = 0, w = 0;
/*     */       
/* 659 */       for (MNode n : layer) {
/* 660 */         if (!n.isDummy()) {
/* 661 */           if (n.getNode().getHeight() > h) {
/* 662 */             h = (int)n.getNode().getHeight();
/*     */           }
/* 664 */           w = (int)(w + this.xDist + n.getNode().getWidth()); continue;
/*     */         } 
/* 666 */         w += this.xDist + 20;
/*     */       } 
/*     */ 
/*     */       
/* 670 */       w -= this.xDist;
/*     */       
/* 672 */       heights.add(Integer.valueOf(h));
/* 673 */       widths.add(Integer.valueOf(w));
/*     */       
/* 675 */       if (w > maxwidth) maxwidth = w;
/*     */     
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 684 */     if (this.DEBUG) {
/* 685 */       System.out.println("MAXWIDTH: " + maxwidth);
/*     */     }
/* 687 */     int yPos = this.yDist;
/*     */     
/* 689 */     this.maxX = 0;
/* 690 */     for (int i = 0; i < layers.size(); i++) {
/* 691 */       List<MNode> l = (List)layers.get(i);
/*     */       
/* 693 */       int xPos = this.xDist;
/*     */       
/* 695 */       for (MNode n : l) {
/* 696 */         int nodeWidth = (maxwidth - ((Integer)widths.get(i)).intValue()) / l.size();
/*     */         
/* 698 */         if (this.DEBUG) {
/* 699 */           System.out.println("width = " + nodeWidth);
/*     */         }
/* 701 */         if (!n.isDummy()) {
/* 702 */           xCmd.link(n.getNode(), (xPos + nodeWidth / 2));
/* 703 */           yCmd.link(n.getNode(), yPos);
/*     */           
/* 705 */           if (this.DEBUG) {
/* 706 */             System.out.println("link(" + (xPos + nodeWidth / 2) + ", " + yPos + ")");
/*     */           }
/* 708 */           xPos = (int)(xPos + nodeWidth + n.getNode().getWidth() + this.xDist);
/*     */           
/* 710 */           if (this.DEBUG)
/* 711 */             System.out.println("Next node will be placed at: " + xPos);  continue;
/*     */         } 
/* 713 */         xPos += nodeWidth + 20 + this.xDist;
/*     */       } 
/*     */       
/* 716 */       if (xPos > this.maxX) this.maxX = xPos;
/*     */       
/* 718 */       yPos += ((Integer)heights.get(i)).intValue() + this.yDist;
/*     */     } 
/*     */     
/* 721 */     this.maxY = yPos;
/*     */     
/* 723 */     if (this.DEBUG)
/* 724 */       System.out.println("max = " + this.maxX + ", " + this.maxY); 
/*     */   }
/*     */   
/*     */   public double[] distributeNodes(List<Node> nodeList, List<EdgeFigure> edgeList) {
/* 728 */     this.maxX = 0;
/* 729 */     this.maxY = 0;
/* 730 */     AbstractLayout.ControlXY xCmd = new AbstractLayout.ControlXY();
/* 731 */     AbstractLayout.ControlXY yCmd = new AbstractLayout.ControlXY();
/*     */     
/* 733 */     generateTree(nodeList, edgeList, xCmd, yCmd);
/*     */     
/* 735 */     for (Node aNodeList : nodeList) {
/* 736 */       aNodeList.translateTo(xCmd, yCmd);
/*     */     }
/*     */     
/* 739 */     double[] rez = new double[2];
/* 740 */     rez[0] = this.maxX;
/* 741 */     rez[1] = this.maxY;
/*     */     
/* 743 */     return rez;
/*     */   }
/*     */   
/*     */   public CrossReductionTreeLayout() {}
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\jMondrian\layouts\CrossReductionTreeLayout.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */