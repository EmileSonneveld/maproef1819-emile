/*     */ package classes.lrg.insider.gui.ui.filter;
/*     */ 
/*     */ import com.intellij.uiDesigner.core.GridConstraints;
/*     */ import com.intellij.uiDesigner.core.GridLayoutManager;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JPopupMenu;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JSplitPane;
/*     */ import javax.swing.JTabbedPane;
/*     */ import javax.swing.JTree;
/*     */ import javax.swing.event.TreeSelectionEvent;
/*     */ import javax.swing.event.TreeSelectionListener;
/*     */ import javax.swing.tree.DefaultMutableTreeNode;
/*     */ import javax.swing.tree.DefaultTreeModel;
/*     */ import javax.swing.tree.TreePath;
/*     */ import lrg.common.abstractions.entities.EntityType;
/*     */ import lrg.common.abstractions.managers.EntityTypeManager;
/*     */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*     */ import lrg.common.abstractions.plugins.filters.composed.AndComposedFilteringRule;
/*     */ import lrg.common.abstractions.plugins.filters.composed.NotComposedFilteringRule;
/*     */ import lrg.common.abstractions.plugins.filters.composed.OrComposedFilteringRule;
/*     */ import lrg.insider.gui.ui.filter.CustomFilterUI;
/*     */ import lrg.insider.gui.ui.filter.FilteringRuleBuilderUI;
/*     */ import lrg.insider.gui.ui.filter.WannabeFilteringRule;
/*     */ import lrg.insider.gui.ui.views.ViewUI;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CustomFilterUI
/*     */   extends MouseAdapter
/*     */   implements ActionListener, TreeSelectionListener
/*     */ {
/*     */   private JTabbedPane validPropertiesTabs;
/*     */   private HashMap buildersMap;
/*     */   private JPanel topComponent;
/*     */   private JButton andButton;
/*     */   private JButton notButton;
/*     */   private JButton orButton;
/*     */   private JButton applyButton;
/*     */   private JButton highlightButton;
/*     */   private JButton unHighlightButton;
/*     */   private JSplitPane splitPane;
/*     */   private JTree filterTree;
/*     */   private DefaultTreeModel filterTreeModel;
/*     */   private ViewUI myViewUI;
/*     */   private DefaultMutableTreeNode selectedNode;
/*     */   private JPopupMenu popup;
/*     */   private JMenuItem deleteFilteringRule;
/*     */   
/*     */   public CustomFilterUI(ViewUI aViewUI) {
/* 277 */     $$$setupUI$$$(); this.andButton.addActionListener(this); this.orButton.addActionListener(this); this.notButton.addActionListener(this); this.filterTree.addTreeSelectionListener(this); this.filterTree.addMouseListener(this); this.myViewUI = aViewUI; this.applyButton.addActionListener(this); this.highlightButton.addActionListener(this); this.unHighlightButton.addActionListener(this); this.validPropertiesTabs.setTabPlacement(1); populateValidFilterBuilders();
/*     */     this.splitPane.setDividerLocation(400);
/*     */     DefaultMutableTreeNode filterTreeRoot = new DefaultMutableTreeNode(WannabeFilteringRule.instance());
/*     */     this.filterTreeModel = new DefaultTreeModel(filterTreeRoot);
/*     */     this.filterTree.setModel(this.filterTreeModel);
/*     */     this.filterTree.setSelectionPath(new TreePath(filterTreeRoot.getPath()));
/*     */     this.selectedNode = filterTreeRoot;
/*     */     this.popup = new JPopupMenu();
/*     */     this.deleteFilteringRule = new JMenuItem("Delete");
/*     */     this.deleteFilteringRule.addActionListener(this);
/* 287 */     this.popup.add(this.deleteFilteringRule); } private void $$$setupUI$$$() { JPanel _1 = new JPanel();
/* 288 */     this.topComponent = _1;
/* 289 */     _1.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
/*     */     
/* 291 */     JSplitPane _2 = new JSplitPane();
/* 292 */     this.splitPane = _2;
/* 293 */     _1.add(_2, new GridConstraints(0, 0, 1, 1, 0, 3, 3, 3, null, new Dimension(200, 200), null));
/*     */     
/* 295 */     JTabbedPane _3 = new JTabbedPane();
/* 296 */     this.validPropertiesTabs = _3;
/* 297 */     _2.setRightComponent(_3);
/*     */     
/* 299 */     JPanel _4 = new JPanel();
/* 300 */     _4.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
/* 301 */     _2.setLeftComponent(_4);
/*     */     
/* 303 */     JScrollPane _5 = new JScrollPane();
/* 304 */     _4.add(_5, new GridConstraints(0, 0, 1, 1, 0, 3, 7, 3, null, null, null));
/*     */     
/* 306 */     JTree _6 = new JTree();
/* 307 */     this.filterTree = _6;
/* 308 */     _6.setShowsRootHandles(true);
/* 309 */     _6.setRootVisible(true);
/* 310 */     _5.setViewportView(_6);
/*     */     
/* 312 */     JPanel _7 = new JPanel();
/* 313 */     _7.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
/* 314 */     _4.add(_7, new GridConstraints(1, 0, 1, 1, 0, 1, 3, 1, null, null, null));
/*     */     
/* 316 */     JButton _8 = new JButton();
/* 317 */     this.andButton = _8;
/* 318 */     _8.setText("And");
/* 319 */     _7.add(_8, new GridConstraints(0, 0, 1, 1, 0, 1, 3, 0, null, null, null));
/*     */     
/* 321 */     JButton _9 = new JButton();
/* 322 */     this.orButton = _9;
/* 323 */     _9.setText("Or");
/* 324 */     _7.add(_9, new GridConstraints(0, 1, 1, 1, 0, 1, 3, 0, null, null, null));
/*     */     
/* 326 */     JButton _10 = new JButton();
/* 327 */     this.notButton = _10;
/* 328 */     _10.setText("Not");
/* 329 */     _7.add(_10, new GridConstraints(0, 2, 1, 1, 0, 1, 3, 0, null, null, null));
/*     */     
/* 331 */     JButton _11 = new JButton();
/* 332 */     this.applyButton = _11;
/* 333 */     _11.setText("Apply");
/* 334 */     _1.add(_11, new GridConstraints(2, 0, 1, 1, 0, 1, 3, 0, null, null, null));
/*     */     
/* 336 */     JPanel _12 = new JPanel();
/* 337 */     _12.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
/* 338 */     _1.add(_12, new GridConstraints(1, 0, 1, 1, 0, 3, 3, 1, null, null, null));
/*     */     
/* 340 */     JButton _13 = new JButton();
/* 341 */     this.unHighlightButton = _13;
/* 342 */     _13.setText("Unhighlight");
/* 343 */     _12.add(_13, new GridConstraints(0, 1, 1, 1, 0, 1, 3, 0, null, null, null));
/*     */     
/* 345 */     JButton _14 = new JButton();
/* 346 */     this.highlightButton = _14;
/* 347 */     _14.setText("Highlight");
/* 348 */     _12.add(_14, new GridConstraints(0, 0, 1, 1, 0, 1, 3, 0, null, null, null)); }
/*     */ 
/*     */   
/*     */   public Container getTopComponent() { return this.topComponent; }
/*     */   
/*     */   private void addValidFilteringRuleTabs(EntityType entityType) {
/*     */     Iterator it = EntityTypeManager.getAllSubtypesForName(entityType.getName()).iterator();
/*     */     while (it.hasNext()) {
/*     */       EntityType currentEntityType = (EntityType)it.next();
/*     */       this.buildersMap.put(currentEntityType.getName(), new FilteringRuleBuilderUI(currentEntityType, this));
/*     */       this.validPropertiesTabs.add(((FilteringRuleBuilderUI)this.buildersMap.get(currentEntityType.getName())).getTopComponent());
/*     */       addValidFilteringRuleTabs(currentEntityType);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void populateValidFilterBuilders() {
/*     */     this.validPropertiesTabs.removeAll();
/*     */     EntityType eType = this.myViewUI.getGroupEntity().getEntityTypeOfElements();
/*     */     this.buildersMap = new HashMap();
/*     */     this.buildersMap.put(eType.getName(), new FilteringRuleBuilderUI(eType, this));
/*     */     this.validPropertiesTabs.add(((FilteringRuleBuilderUI)this.buildersMap.get(eType.getName())).getTopComponent());
/*     */     addValidFilteringRuleTabs(eType);
/*     */   }
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/*     */     if (e.getSource() == this.applyButton) {
/*     */       this.myViewUI.unHighlight();
/*     */       FilteringRule aRule = (FilteringRule)((DefaultMutableTreeNode)this.filterTreeModel.getRoot()).getUserObject();
/*     */       if (EntityTypeManager.getEntityTypeForName(aRule.getDescriptorObject().getEntityTypeName()).findFilteringRule(aRule.getDescriptorObject().getName()) == null) {
/*     */         String userSelection = JOptionPane.showInputDialog(getTopComponent(), "Do you want to save this filter ?", aRule.getDescriptorObject().getName());
/*     */         if (userSelection != null) {
/*     */           aRule.getDescriptorObject().setName(userSelection);
/*     */           EntityTypeManager.attach(aRule);
/*     */         } 
/*     */       } 
/*     */       JDialog x = (JDialog)this.topComponent.getParent().getParent().getParent();
/*     */       x.dispose();
/*     */       this.myViewUI.applyFilter(aRule);
/*     */       populateValidFilterBuilders();
/*     */     } 
/*     */     if (e.getSource() == this.highlightButton) {
/*     */       FilteringRule aRule = (FilteringRule)((DefaultMutableTreeNode)this.filterTreeModel.getRoot()).getUserObject();
/*     */       this.myViewUI.highlightFilter(aRule);
/*     */     } 
/*     */     if (e.getSource() == this.unHighlightButton)
/*     */       this.myViewUI.unHighlight(); 
/*     */     if (e.getSource() == this.deleteFilteringRule) {
/*     */       DefaultMutableTreeNode node = (DefaultMutableTreeNode)this.filterTree.getLastSelectedPathComponent();
/*     */       if (node == null || node == this.filterTreeModel.getRoot())
/*     */         return; 
/*     */       this.filterTree.setSelectionPath(new TreePath(((DefaultMutableTreeNode)this.filterTreeModel.getRoot()).getPath()));
/*     */       DefaultMutableTreeNode parent = (DefaultMutableTreeNode)node.getParent();
/*     */       this.filterTreeModel.removeNodeFromParent(node);
/*     */       parent.add(new DefaultMutableTreeNode(WannabeFilteringRule.instance()));
/*     */       maybeUpdateParentOf(parent);
/*     */     } 
/*     */     if (e.getSource() == this.orButton) {
/*     */       DefaultMutableTreeNode parentOfSelectedNode = (DefaultMutableTreeNode)this.selectedNode.getParent();
/*     */       DefaultMutableTreeNode orNode = null;
/*     */       if (this.selectedNode.getChildCount() == 2) {
/*     */         FilteringRule fr0 = (FilteringRule)((DefaultMutableTreeNode)this.selectedNode.getChildAt(0)).getUserObject();
/*     */         FilteringRule fr1 = (FilteringRule)((DefaultMutableTreeNode)this.selectedNode.getChildAt(1)).getUserObject();
/*     */         OrComposedFilteringRule orComposedFilteringRule = new OrComposedFilteringRule(fr0, fr1);
/*     */         orNode = new DefaultMutableTreeNode(orComposedFilteringRule);
/*     */         orNode.add((DefaultMutableTreeNode)this.selectedNode.getChildAt(0));
/*     */         orNode.add((DefaultMutableTreeNode)this.selectedNode.getChildAt(0));
/*     */         this.selectedNode.removeFromParent();
/*     */       } else {
/*     */         OrComposedFilteringRule orComposedFilteringRule = new OrComposedFilteringRule((FilteringRule)this.selectedNode.getUserObject(), WannabeFilteringRule.instance());
/*     */         orNode = new DefaultMutableTreeNode(orComposedFilteringRule);
/*     */         orNode.add(this.selectedNode);
/*     */         orNode.add(new DefaultMutableTreeNode(WannabeFilteringRule.instance()));
/*     */       } 
/*     */       if (parentOfSelectedNode == null) {
/*     */         this.filterTreeModel.setRoot(orNode);
/*     */       } else {
/*     */         parentOfSelectedNode.add(orNode);
/*     */       } 
/*     */     } 
/*     */     if (e.getSource() == this.andButton) {
/*     */       DefaultMutableTreeNode parentOfSelectedNode = (DefaultMutableTreeNode)this.selectedNode.getParent();
/*     */       DefaultMutableTreeNode andNode = null;
/*     */       if (this.selectedNode.getChildCount() == 2) {
/*     */         FilteringRule fr0 = (FilteringRule)((DefaultMutableTreeNode)this.selectedNode.getChildAt(0)).getUserObject();
/*     */         FilteringRule fr1 = (FilteringRule)((DefaultMutableTreeNode)this.selectedNode.getChildAt(1)).getUserObject();
/*     */         AndComposedFilteringRule andComposedFilteringRule = new AndComposedFilteringRule(fr0, fr1);
/*     */         andNode = new DefaultMutableTreeNode(andComposedFilteringRule);
/*     */         andNode.add((DefaultMutableTreeNode)this.selectedNode.getChildAt(0));
/*     */         andNode.add((DefaultMutableTreeNode)this.selectedNode.getChildAt(0));
/*     */         this.selectedNode.removeFromParent();
/*     */       } else {
/*     */         AndComposedFilteringRule andComposedFilteringRule = new AndComposedFilteringRule((FilteringRule)this.selectedNode.getUserObject(), WannabeFilteringRule.instance());
/*     */         andNode = new DefaultMutableTreeNode(andComposedFilteringRule);
/*     */         andNode.add(this.selectedNode);
/*     */         andNode.add(new DefaultMutableTreeNode(WannabeFilteringRule.instance()));
/*     */       } 
/*     */       if (parentOfSelectedNode == null) {
/*     */         this.filterTreeModel.setRoot(andNode);
/*     */       } else {
/*     */         parentOfSelectedNode.add(andNode);
/*     */       } 
/*     */     } 
/*     */     if (e.getSource() == this.notButton) {
/*     */       DefaultMutableTreeNode parentOfSelectedNode = (DefaultMutableTreeNode)this.selectedNode.getParent();
/*     */       NotComposedFilteringRule notComposedFilteringRule = new NotComposedFilteringRule((FilteringRule)this.selectedNode.getUserObject());
/*     */       DefaultMutableTreeNode notNode = new DefaultMutableTreeNode(notComposedFilteringRule);
/*     */       notNode.add(this.selectedNode);
/*     */       if (parentOfSelectedNode == null) {
/*     */         this.filterTreeModel.setRoot(notNode);
/*     */       } else {
/*     */         parentOfSelectedNode.add(notNode);
/*     */       } 
/*     */     } 
/*     */     this.filterTreeModel.reload();
/*     */     this.filterTree.setSelectionPath(new TreePath(this.selectedNode.getPath()));
/*     */     this.filterTree.expandPath(new TreePath(this.selectedNode.getPath()));
/*     */   }
/*     */   
/*     */   public void setFilter(FilteringRule aRule) {
/*     */     this.selectedNode.removeAllChildren();
/*     */     this.selectedNode.setUserObject(aRule);
/*     */     maybeUpdateParentOf(this.selectedNode);
/*     */     this.filterTreeModel.reload();
/*     */     this.filterTree.setSelectionPath(new TreePath(this.selectedNode.getPath()));
/*     */   }
/*     */   
/*     */   private void maybeUpdateParentOf(DefaultMutableTreeNode node) {
/*     */     DefaultMutableTreeNode parent = (DefaultMutableTreeNode)node.getParent();
/*     */     if (parent != null) {
/*     */       if (parent.getChildCount() == 2) {
/*     */         FilteringRule fr0 = (FilteringRule)((DefaultMutableTreeNode)parent.getChildAt(0)).getUserObject();
/*     */         FilteringRule fr1 = (FilteringRule)((DefaultMutableTreeNode)parent.getChildAt(1)).getUserObject();
/*     */         if (parent.getUserObject() instanceof AndComposedFilteringRule)
/*     */           parent.setUserObject(new AndComposedFilteringRule(fr0, fr1)); 
/*     */         if (parent.getUserObject() instanceof OrComposedFilteringRule)
/*     */           parent.setUserObject(new OrComposedFilteringRule(fr0, fr1)); 
/*     */       } 
/*     */       if (parent.getChildCount() == 1) {
/*     */         FilteringRule fr = (FilteringRule)((DefaultMutableTreeNode)parent.getChildAt(0)).getUserObject();
/*     */         if (parent.getUserObject() instanceof NotComposedFilteringRule)
/*     */           parent.setUserObject(new NotComposedFilteringRule(fr)); 
/*     */       } 
/*     */       maybeUpdateParentOf(parent);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void valueChanged(TreeSelectionEvent e) {
/*     */     DefaultMutableTreeNode node = (DefaultMutableTreeNode)this.filterTree.getLastSelectedPathComponent();
/*     */     if (node == null)
/*     */       return; 
/*     */     this.selectedNode = node;
/*     */     FilteringRule selectedRule = (FilteringRule)node.getUserObject();
/*     */     if (selectedRule instanceof WannabeFilteringRule)
/*     */       return; 
/*     */     String title = selectedRule.getDescriptorObject().getEntityTypeName();
/*     */     FilteringRuleBuilderUI x = (FilteringRuleBuilderUI)this.buildersMap.get(title);
/*     */     if (x == null)
/*     */       return; 
/*     */     this.validPropertiesTabs.setSelectedComponent(x.getTopComponent());
/*     */   }
/*     */   
/*     */   public void mousePressed(MouseEvent e) { maybeShowPopup(e); }
/*     */   
/*     */   public void mouseReleased(MouseEvent e) { maybeShowPopup(e); }
/*     */   
/*     */   private void maybeShowPopup(MouseEvent e) {
/*     */     if (e.isPopupTrigger()) {
/*     */       DefaultMutableTreeNode node = (DefaultMutableTreeNode)this.filterTree.getPathForLocation(e.getX(), e.getY()).getLastPathComponent();
/*     */       if (node == this.filterTreeModel.getRoot())
/*     */         return; 
/*     */       this.popup.show(e.getComponent(), e.getX(), e.getY());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\gu\\ui\filter\CustomFilterUI.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */