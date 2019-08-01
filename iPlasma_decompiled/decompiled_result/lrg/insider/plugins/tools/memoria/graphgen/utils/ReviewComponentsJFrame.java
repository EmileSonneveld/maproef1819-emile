/*     */ package classes.lrg.insider.plugins.tools.memoria.graphgen.utils;
/*     */ 
/*     */ import cdc.clusters.ExtendedWeightedEdge;
/*     */ import cdc.clusters.Node;
/*     */ import com.cloudgarden.layout.AnchorConstraint;
/*     */ import com.cloudgarden.layout.AnchorLayout;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.swing.DefaultListModel;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JSplitPane;
/*     */ import javax.swing.ListModel;
/*     */ import lrg.insider.plugins.tools.memoria.graphgen.SystemGraph;
/*     */ import lrg.insider.plugins.tools.memoria.graphgen.SystemSubgraph;
/*     */ import lrg.insider.plugins.tools.memoria.graphgen.utils.ReviewComponentsJFrame;
/*     */ import lrg.memoria.core.Component;
/*     */ import lrg.memoria.core.DataAbstraction;
/*     */ import lrg.memoria.core.ModelElementList;
/*     */ import lrg.memoria.core.System;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ReviewComponentsJFrame
/*     */   extends JFrame
/*     */ {
/*     */   private JPanel jPanel1;
/*     */   private JPanel jPanel9;
/*     */   private JLabel jLabel1;
/*     */   private JButton jButtonRemove;
/*     */   private JButton jButtonAdd;
/*     */   private JPanel jPanel8;
/*     */   private JButton jButtonPrev;
/*     */   private JList jListAvailableClasses;
/*     */   private JScrollPane jScrollPane1;
/*     */   private JPanel jPanel12;
/*     */   private JScrollPane jScrollPane2;
/*     */   private JButton jButtonOK;
/*     */   private JLabel jLabel3;
/*     */   private JList jListComponentClasses;
/*     */   private JPanel jPanel11;
/*     */   private JButton jButtonNext;
/*     */   private JLabel jLabelComponentName;
/*     */   private JLabel jLabel2;
/*     */   private JPanel jPanel10;
/*     */   private JPanel jPanel7;
/*     */   private JPanel jPanel6;
/*     */   private JPanel jPanel5;
/*     */   private JPanel jPanel4;
/*     */   private JSplitPane jSplitPane1;
/*     */   private JPanel jPanel3;
/*     */   private JPanel jPanel2;
/*     */   private ArrayList<Node> availableList;
/*     */   private List<SystemSubgraph<Node, ExtendedWeightedEdge>> componentList;
/*     */   private int crtComponentIndex;
/*     */   
/*     */   public static void main(String[] args) {
/*  87 */     ArrayList<String> l1 = new ArrayList<String>();
/*  88 */     ArrayList<String> l2 = new ArrayList<String>();
/*  89 */     for (int i = 0; i < 30; i++) {
/*     */       
/*  91 */       l1.add("Available" + i);
/*  92 */       l2.add("ComponentClass" + i);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initLists(SystemGraph<Node, ExtendedWeightedEdge> graph, List<SystemSubgraph<Node, ExtendedWeightedEdge>> componentList) {
/* 100 */     this.componentList = componentList;
/*     */     
/* 102 */     ArrayList<Node> notInComponents = new ArrayList<Node>(graph.vertexSet());
/* 103 */     for (SystemSubgraph<Node, ExtendedWeightedEdge> subgraph : componentList) {
/* 104 */       for (Node node : subgraph.vertexSet())
/* 105 */         notInComponents.remove(node); 
/* 106 */     }  this.availableList = notInComponents;
/*     */     
/* 108 */     DefaultListModel availableModel = (DefaultListModel)this.jListAvailableClasses.getModel();
/*     */     
/* 110 */     for (Node n : this.availableList) {
/* 111 */       availableModel.addElement(n);
/*     */     }
/* 113 */     this.crtComponentIndex = 0;
/* 114 */     fillCurrentComponentListModel();
/*     */   }
/*     */ 
/*     */   
/*     */   private void fillCurrentComponentListModel() {
/* 119 */     DefaultListModel componentModel = (DefaultListModel)this.jListComponentClasses.getModel();
/* 120 */     SystemSubgraph<Node, ExtendedWeightedEdge> crtComponent = (SystemSubgraph)this.componentList.get(this.crtComponentIndex);
/* 121 */     this.jLabelComponentName.setText(crtComponent.getCorrespondingComponent().getName());
/* 122 */     for (Node n : crtComponent.vertexSet()) {
/* 123 */       componentModel.addElement(n);
/*     */     }
/*     */   }
/*     */   
/*     */   private void switchToComponent(int newComponentIndex) {
/* 128 */     DefaultListModel componentModel = (DefaultListModel)this.jListComponentClasses.getModel();
/*     */     
/* 130 */     componentModel.removeAllElements();
/* 131 */     this.crtComponentIndex = newComponentIndex;
/* 132 */     fillCurrentComponentListModel();
/*     */   }
/*     */ 
/*     */   
/*     */   public ReviewComponentsJFrame(System system) {
/* 137 */     initGUI();
/*     */     
/* 139 */     SystemGraph<Node, ExtendedWeightedEdge> graph = (SystemGraph)system.getAnnotation("system_jgraphtgraph");
/* 140 */     ModelElementList<Component> listOfComponents = (ModelElementList)system.getAnnotation("system_allComponents");
/* 141 */     ArrayList<SystemSubgraph<Node, ExtendedWeightedEdge>> subgraphList = new ArrayList<SystemSubgraph<Node, ExtendedWeightedEdge>>();
/*     */     
/* 143 */     for (Component c : listOfComponents)
/* 144 */       subgraphList.add((SystemSubgraph)c.getAnnotation("component_jgraphtsubgraph")); 
/* 145 */     initLists(graph, subgraphList);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void startUI(ArrayList<String> availableList, ArrayList<String> initialComponentList) {}
/*     */ 
/*     */ 
/*     */   
/*     */   private void initGUI() {
/*     */     try {
/* 156 */       BorderLayout thisLayout = new BorderLayout();
/* 157 */       getContentPane().setLayout(thisLayout);
/* 158 */       setDefaultCloseOperation(2);
/* 159 */       setTitle("Review components");
/*     */       
/* 161 */       this.jPanel1 = new JPanel();
/* 162 */       getContentPane().add(this.jPanel1, "North");
/* 163 */       this.jPanel1.setPreferredSize(new Dimension(583, 32));
/*     */       
/* 165 */       this.jLabel3 = new JLabel();
/* 166 */       this.jPanel1.add(this.jLabel3);
/* 167 */       this.jLabel3.setText("Review the component content by moving classes at will. Note: all changes in the model are made on the fly.");
/* 168 */       this.jLabel3.setFont(new Font("Tahoma", 2, 14));
/* 169 */       this.jLabel3.setPreferredSize(new Dimension(696, 17));
/* 170 */       this.jLabel3.setHorizontalAlignment(0);
/*     */ 
/*     */ 
/*     */       
/* 174 */       this.jPanel2 = new JPanel();
/* 175 */       BorderLayout jPanel2Layout = new BorderLayout();
/* 176 */       this.jPanel2.setLayout(jPanel2Layout);
/* 177 */       getContentPane().add(this.jPanel2, "Center");
/*     */       
/* 179 */       this.jSplitPane1 = new JSplitPane();
/* 180 */       this.jPanel2.add(this.jSplitPane1, "Center");
/* 181 */       this.jSplitPane1.setPreferredSize(new Dimension(185, 92));
/*     */       
/* 183 */       this.jPanel4 = new JPanel();
/* 184 */       this.jSplitPane1.add(this.jPanel4, "left");
/* 185 */       BorderLayout jPanel4Layout = new BorderLayout();
/* 186 */       this.jPanel4.setLayout(jPanel4Layout);
/* 187 */       this.jPanel4.setPreferredSize(new Dimension(400, 533));
/*     */       
/* 189 */       this.jPanel5 = new JPanel();
/* 190 */       BorderLayout jPanel5Layout = new BorderLayout();
/* 191 */       this.jPanel5.setLayout(jPanel5Layout);
/* 192 */       this.jPanel4.add(this.jPanel5, "North");
/* 193 */       this.jPanel5.setPreferredSize(new Dimension(183, 47));
/*     */       
/* 195 */       this.jLabel1 = new JLabel();
/* 196 */       this.jPanel5.add(this.jLabel1, "Center");
/* 197 */       this.jLabel1.setText("Available classes");
/* 198 */       this.jLabel1.setPreferredSize(new Dimension(316, 14));
/* 199 */       this.jLabel1.setFont(new Font("Tahoma", 0, 16));
/* 200 */       this.jLabel1.setHorizontalAlignment(0);
/*     */ 
/*     */ 
/*     */       
/* 204 */       this.jPanel6 = new JPanel();
/* 205 */       BorderLayout jPanel6Layout = new BorderLayout();
/* 206 */       this.jPanel6.setLayout(jPanel6Layout);
/* 207 */       this.jPanel4.add(this.jPanel6, "Center");
/* 208 */       this.jPanel6.setPreferredSize(new Dimension(
/* 209 */             296, 
/* 210 */             344));
/*     */       
/* 212 */       this.jScrollPane1 = new JScrollPane();
/* 213 */       this.jPanel6.add(this.jScrollPane1, "Center");
/*     */       
/* 215 */       ListModel jListAvailableClassesModel = new DefaultListModel();
/* 216 */       this.jListAvailableClasses = new JList();
/* 217 */       this.jScrollPane1.setViewportView(this.jListAvailableClasses);
/* 218 */       this.jListAvailableClasses
/* 219 */         .setModel(jListAvailableClassesModel);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 228 */       this.jPanel8 = new JPanel();
/* 229 */       BorderLayout jPanel8Layout = new BorderLayout();
/* 230 */       this.jPanel4.add(this.jPanel8, "East");
/* 231 */       this.jPanel8.setLayout(jPanel8Layout);
/* 232 */       this.jPanel8.setFocusable(false);
/* 233 */       this.jPanel8.setAutoscrolls(true);
/* 234 */       this.jPanel8.setPreferredSize(new Dimension(98, 483));
/*     */       
/* 236 */       this.jPanel12 = new JPanel();
/* 237 */       AnchorLayout jPanel12Layout = new AnchorLayout();
/* 238 */       this.jPanel8.add(this.jPanel12, "South");
/* 239 */       this.jPanel12.setPreferredSize(new Dimension(98, 133));
/* 240 */       this.jPanel12.setLayout(jPanel12Layout);
/*     */       
/* 242 */       this.jButtonAdd = new JButton();
/* 243 */       this.jPanel12.add(this.jButtonAdd, new AnchorConstraint(53, 1005, 35, 5, 0, 0, 2, 0));
/* 244 */       this.jButtonAdd.setText("Add   >>");
/* 245 */       this.jButtonAdd
/* 246 */         .setHorizontalAlignment(10);
/* 247 */       this.jButtonAdd
/* 248 */         .setHorizontalTextPosition(10);
/* 249 */       this.jButtonAdd.setPreferredSize(new Dimension(98, 21));
/* 250 */       this.jButtonAdd
/* 251 */         .addActionListener(new Object(this));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 259 */       this.jButtonRemove = new JButton();
/* 260 */       this.jPanel12.add(this.jButtonRemove, new AnchorConstraint(255, 1005, 7, 5, 0, 0, 2, 0));
/* 261 */       this.jButtonRemove.setText("<< Remove");
/* 262 */       this.jButtonRemove
/* 263 */         .setHorizontalTextPosition(10);
/* 264 */       this.jButtonRemove
/* 265 */         .setHorizontalAlignment(10);
/* 266 */       this.jButtonRemove.setPreferredSize(new Dimension(98, 21));
/* 267 */       this.jButtonRemove
/* 268 */         .addActionListener(new Object(this));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 279 */       this.jPanel7 = new JPanel();
/* 280 */       BorderLayout jPanel7Layout = new BorderLayout();
/* 281 */       this.jPanel7.setLayout(jPanel7Layout);
/* 282 */       this.jSplitPane1.add(this.jPanel7, "right");
/* 283 */       this.jPanel7.setPreferredSize(new Dimension(145, 533));
/*     */       
/* 285 */       this.jPanel9 = new JPanel();
/* 286 */       BorderLayout jPanel9Layout = new BorderLayout();
/* 287 */       this.jPanel9.setLayout(jPanel9Layout);
/* 288 */       this.jPanel7.add(this.jPanel9, "North");
/* 289 */       this.jPanel9.setPreferredSize(new Dimension(394, 78));
/*     */       
/* 291 */       this.jLabel2 = new JLabel();
/* 292 */       this.jPanel9.add(this.jLabel2, "North");
/* 293 */       this.jLabel2.setText("Component:");
/* 294 */       this.jLabel2.setFont(new Font("Tahoma", 0, 16));
/*     */ 
/*     */       
/* 297 */       this.jLabelComponentName = new JLabel();
/* 298 */       this.jPanel9.add(this.jLabelComponentName, "Center");
/* 299 */       this.jLabelComponentName.setText("Component_1");
/* 300 */       this.jLabelComponentName.setFont(new Font("Tahoma", 1, 14));
/* 301 */       this.jLabelComponentName.setHorizontalAlignment(0);
/* 302 */       this.jLabelComponentName.setPreferredSize(new Dimension(394, 16));
/*     */ 
/*     */       
/* 305 */       this.jPanel11 = new JPanel();
/* 306 */       this.jPanel9.add(this.jPanel11, "South");
/*     */       
/* 308 */       this.jButtonPrev = new JButton();
/* 309 */       this.jPanel11.add(this.jButtonPrev);
/* 310 */       this.jButtonPrev.setText("Previous");
/* 311 */       this.jButtonPrev
/* 312 */         .addActionListener(new Object(this));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 320 */       this.jButtonNext = new JButton();
/* 321 */       this.jPanel11.add(this.jButtonNext);
/* 322 */       this.jButtonNext.setText("Next");
/* 323 */       this.jButtonNext
/* 324 */         .addActionListener(new Object(this));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 334 */       this.jPanel10 = new JPanel();
/* 335 */       BorderLayout jPanel10Layout = new BorderLayout();
/* 336 */       this.jPanel10.setLayout(jPanel10Layout);
/* 337 */       this.jPanel7.add(this.jPanel10, "Center");
/*     */       
/* 339 */       this.jScrollPane2 = new JScrollPane();
/* 340 */       this.jPanel10.add(this.jScrollPane2, "Center");
/*     */       
/* 342 */       ListModel jListComponentClassesModel = new DefaultListModel();
/* 343 */       this.jListComponentClasses = new JList();
/* 344 */       this.jScrollPane2.setViewportView(this.jListComponentClasses);
/* 345 */       this.jListComponentClasses
/* 346 */         .setModel(jListComponentClassesModel);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 354 */       this.jPanel3 = new JPanel();
/* 355 */       getContentPane().add(this.jPanel3, "South");
/*     */       
/* 357 */       this.jButtonOK = new JButton();
/* 358 */       this.jPanel3.add(this.jButtonOK);
/* 359 */       this.jButtonOK.setText("Close");
/* 360 */       this.jButtonOK.addActionListener(new Object(this));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 368 */       pack();
/* 369 */       setSize(734, 633);
/* 370 */     } catch (Exception e) {
/* 371 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void jButtonAddActionPerformed(ActionEvent evt) {
/* 376 */     DefaultListModel componentModel = (DefaultListModel)this.jListComponentClasses.getModel();
/* 377 */     DefaultListModel availableModel = (DefaultListModel)this.jListAvailableClasses.getModel();
/*     */ 
/*     */     
/* 380 */     int selectedIndex = this.jListAvailableClasses.getSelectedIndex();
/* 381 */     if (selectedIndex < 0) {
/*     */       return;
/*     */     }
/* 384 */     Node nodeToAdd = (Node)availableModel.getElementAt(selectedIndex);
/* 385 */     componentModel.addElement(nodeToAdd);
/* 386 */     availableModel.removeElementAt(selectedIndex);
/*     */ 
/*     */     
/* 389 */     DataAbstraction classToAdd = (DataAbstraction)nodeToAdd.getObject();
/* 390 */     SystemSubgraph<Node, ExtendedWeightedEdge> newSubgraph = (SystemSubgraph)this.componentList.get(this.crtComponentIndex);
/* 391 */     newSubgraph.addVertex(nodeToAdd);
/* 392 */     Component newComponent = newSubgraph.getCorrespondingComponent();
/* 393 */     newComponent.addScopedElement(classToAdd);
/* 394 */     classToAdd.putAnnotation("component", newComponent);
/*     */   }
/*     */   
/*     */   private void jButtonRemoveActionPerformed(ActionEvent evt) {
/* 398 */     DefaultListModel componentModel = (DefaultListModel)this.jListComponentClasses.getModel();
/* 399 */     DefaultListModel availableModel = (DefaultListModel)this.jListAvailableClasses.getModel();
/*     */ 
/*     */     
/* 402 */     int selectedIndex = this.jListComponentClasses.getSelectedIndex();
/* 403 */     if (selectedIndex < 0) {
/*     */       return;
/*     */     }
/* 406 */     Node nodeToRemove = (Node)componentModel.getElementAt(selectedIndex);
/* 407 */     availableModel.addElement(nodeToRemove);
/* 408 */     componentModel.removeElementAt(selectedIndex);
/*     */ 
/*     */     
/* 411 */     DataAbstraction classToRemove = (DataAbstraction)nodeToRemove.getObject();
/* 412 */     Component oldComponent = (Component)classToRemove.getAnnotation("component");
/*     */     
/* 414 */     classToRemove.removeAnnotation("component");
/* 415 */     oldComponent.removeScopedElement(classToRemove);
/* 416 */     ((SystemSubgraph)this.componentList.get(this.crtComponentIndex)).removeVertex(nodeToRemove);
/*     */   }
/*     */   
/*     */   private void jButtonPrevActionPerformed(ActionEvent evt) {
/* 420 */     if (this.crtComponentIndex == 0)
/*     */       return; 
/* 422 */     switchToComponent(--this.crtComponentIndex);
/*     */   }
/*     */   
/*     */   private void jButtonNextActionPerformed(ActionEvent evt) {
/* 426 */     if (this.crtComponentIndex == this.componentList.size() - 1)
/*     */       return; 
/* 428 */     switchToComponent(++this.crtComponentIndex);
/*     */   }
/*     */ 
/*     */   
/* 432 */   private void jButtonOKActionPerformed(ActionEvent evt) { setVisible(false); }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\memoria\graphge\\utils\ReviewComponentsJFrame.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */