/*     */ package classes.lrg.insider.gui.ui.stories;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JSplitPane;
/*     */ import javax.swing.JTree;
/*     */ import javax.swing.event.TreeModelEvent;
/*     */ import javax.swing.tree.DefaultMutableTreeNode;
/*     */ import javax.swing.tree.DefaultTreeModel;
/*     */ import javax.swing.tree.TreePath;
/*     */ import lrg.insider.gui.ui.filter.CustomFilterUI;
/*     */ import lrg.insider.gui.ui.stories.SelectColumnsUI;
/*     */ import lrg.insider.gui.ui.stories.StoryTreeUI;
/*     */ import lrg.insider.gui.ui.stories.StoryUI;
/*     */ import lrg.insider.gui.ui.views.ViewUI;
/*     */ import lrg.insider.metamodel.Address;
/*     */ 
/*     */ public class StoryTreeUI extends MouseAdapter implements TreeSelectionListener, TreeModelListener, ActionListener, KeyListener {
/*     */   public static final int BROTHER = 1;
/*     */   public static final int CHILD = 2;
/*     */   public static final int FROM_ROOT = 3;
/*     */   
/*     */   public static StoryTreeUI instance() {
/*  29 */     if (theStoryTreeUI == null) {
/*  30 */       theStoryTreeUI = new StoryTreeUI();
/*     */     }
/*  32 */     return theStoryTreeUI;
/*     */   }
/*     */   private static StoryTreeUI theStoryTreeUI; private JSplitPane splitPane; private JScrollPane treeScrollPane; private JTree storyTree; private DefaultTreeModel storyTreeModel; private DefaultMutableTreeNode storyTreeRoot; private StoryUI selectedStoryUI; private JPopupMenu popup; private JMenuItem deleteStoryMenuItem; private JMenuItem selectColumnsMenuItem; public JMenu filteringRulesMenu;
/*     */   public JMenuItem applyCustomFilter;
/*     */   
/*     */   private StoryTreeUI() {
/*  38 */     this.storyTree = new JTree();
/*     */     
/*  40 */     ToolTipManager.sharedInstance().registerComponent(this.storyTree);
/*  41 */     this.storyTree.setCellRenderer(new StoryTreeRenderer());
/*     */     
/*  43 */     this.storyTree.setEditable(false);
/*  44 */     this.storyTree.addTreeSelectionListener(this);
/*     */     
/*  46 */     this.storyTreeRoot = new DefaultMutableTreeNode("Use File->Open to load your sources");
/*  47 */     this.storyTreeModel = new DefaultTreeModel(this.storyTreeRoot);
/*  48 */     this.storyTreeModel.addTreeModelListener(this);
/*  49 */     this.storyTree.addMouseListener(this);
/*  50 */     this.storyTree.addKeyListener(this);
/*  51 */     this.storyTree.addTreeSelectionListener(this);
/*  52 */     this.storyTree.setModel(this.storyTreeModel);
/*  53 */     this.storyTree.setEnabled(false);
/*  54 */     this.treeScrollPane = new JScrollPane(this.storyTree);
/*     */     
/*  56 */     this.splitPane = new JSplitPane();
/*  57 */     this.splitPane.setDividerLocation(520);
/*  58 */     this.splitPane.setOrientation(1);
/*  59 */     this.splitPane.setLeftComponent(this.treeScrollPane);
/*  60 */     this.splitPane.setRightComponent(null);
/*     */     
/*  62 */     this.popup = new JPopupMenu();
/*     */     
/*  64 */     this.deleteStoryMenuItem = new JMenuItem("Delete This Story");
/*  65 */     this.deleteStoryMenuItem.addActionListener(this);
/*     */     
/*  67 */     this.filteringRulesMenu = new JMenu("Apply Filtering Rule");
/*  68 */     this.applyCustomFilter = new JMenuItem("Custom");
/*  69 */     this.applyCustomFilter.addActionListener(this);
/*  70 */     this.selectColumnsMenuItem = new JMenuItem("Select Columns");
/*  71 */     this.selectColumnsMenuItem.addActionListener(this);
/*     */ 
/*     */ 
/*     */     
/*  75 */     this.popup.add(this.selectColumnsMenuItem);
/*  76 */     this.popup.add(this.filteringRulesMenu);
/*  77 */     this.popup.addSeparator();
/*     */     
/*  79 */     this.popup.add(this.deleteStoryMenuItem);
/*     */   }
/*     */ 
/*     */   
/*  83 */   public Component getTopComponent() { return this.splitPane; }
/*     */ 
/*     */   
/*     */   public void setMetaModel() {
/*  87 */     ArrayList dummyList = new ArrayList();
/*  88 */     dummyList.add(MetaModel.instance().findEntityByAddress(Address.buildForRoot()));
/*     */     
/*  90 */     StoryUI dummyStoryUI = new StoryUI(new ViewUI(new GroupEntity(Address.buildForRoot(), dummyList)));
/*  91 */     dummyStoryUI.setName(Address.buildForRoot());
/*  92 */     this.storyTreeRoot = new DefaultMutableTreeNode(dummyStoryUI);
/*  93 */     this.storyTreeModel.setRoot(this.storyTreeRoot);
/*  94 */     setSelectedNode(this.storyTreeRoot);
/*  95 */     this.storyTree.setEnabled(true);
/*     */   }
/*     */   
/*     */   public void unloadMetaModel() {
/*  99 */     this.storyTree = new JTree();
/*     */     
/* 101 */     this.storyTree.addTreeSelectionListener(this);
/* 102 */     this.storyTreeRoot = new DefaultMutableTreeNode("Use File->Open to load your sources");
/* 103 */     this.storyTreeModel = new DefaultTreeModel(this.storyTreeRoot);
/* 104 */     this.storyTreeModel.addTreeModelListener(this);
/* 105 */     this.storyTree.addMouseListener(this);
/* 106 */     this.storyTree.setModel(this.storyTreeModel);
/* 107 */     this.storyTree.setEnabled(false);
/*     */     
/* 109 */     this.treeScrollPane = new JScrollPane(this.storyTree);
/* 110 */     this.splitPane.setLeftComponent(this.treeScrollPane);
/* 111 */     this.splitPane.setRightComponent(null);
/* 112 */     this.selectedStoryUI = null;
/*     */   }
/*     */   
/*     */   public void addStoryUI(StoryUI aStoryUI, int parent) {
/* 116 */     DefaultMutableTreeNode parentNode = null;
/* 117 */     TreePath selectedPath = this.storyTree.getSelectionPath();
/*     */     
/* 119 */     if (selectedPath == null) {
/* 120 */       parentNode = this.storyTreeRoot;
/*     */     } else {
/* 122 */       switch (parent) {
/*     */         case 2:
/* 124 */           parentNode = (DefaultMutableTreeNode)selectedPath.getLastPathComponent();
/*     */           break;
/*     */         case 1:
/* 127 */           parentNode = 
/* 128 */             (DefaultMutableTreeNode)((DefaultMutableTreeNode)selectedPath.getLastPathComponent()).getParent();
/*     */           break;
/*     */         default:
/* 131 */           parentNode = this.storyTreeRoot;
/*     */           break;
/*     */       } 
/*     */     } 
/* 135 */     if (parentNode == null) {
/* 136 */       parentNode = this.storyTreeRoot;
/*     */     }
/*     */     
/* 139 */     DefaultMutableTreeNode anotherNode = new DefaultMutableTreeNode(aStoryUI);
/*     */ 
/*     */ 
/*     */     
/* 143 */     this.storyTreeModel.insertNodeInto(anotherNode, parentNode, parentNode.getChildCount());
/* 144 */     setSelectedNode(anotherNode);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void valueChanged(TreeSelectionEvent e) {
/* 150 */     DefaultMutableTreeNode node = (DefaultMutableTreeNode)this.storyTree.getLastSelectedPathComponent();
/*     */     
/* 152 */     if (node == null) {
/*     */       return;
/*     */     }
/* 155 */     setSelectedNode(node);
/* 156 */     this.selectedStoryUI.getSelectedViewUI().updateGroupMenu();
/* 157 */     EntityMouseAction.instance().addFiltersAndPropertiesToMenu();
/*     */   }
/*     */   
/*     */   private void deleteSelctedTreeNode() {
/* 161 */     DefaultMutableTreeNode node = (DefaultMutableTreeNode)this.storyTree.getLastSelectedPathComponent();
/* 162 */     if (node == null || node == this.storyTreeRoot) {
/*     */       return;
/*     */     }
/* 165 */     setSelectedNode((DefaultMutableTreeNode)node.getParent());
/* 166 */     this.storyTreeModel.removeNodeFromParent(node);
/*     */   }
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 170 */     if (e.getSource() == this.deleteStoryMenuItem) {
/* 171 */       deleteSelctedTreeNode();
/*     */       
/*     */       return;
/*     */     } 
/* 175 */     if (e.getSource() == this.applyCustomFilter) {
/* 176 */       ViewUI selectedViewUI = this.selectedStoryUI.getSelectedViewUI();
/* 177 */       CustomFilterUI cfUI = new CustomFilterUI(selectedViewUI);
/* 178 */       JDialog customFilterDialog = new JDialog(InsiderGUIMain.getFrame(), "Custom filter editor", false);
/*     */       
/* 180 */       customFilterDialog.setContentPane(cfUI.getTopComponent());
/* 181 */       customFilterDialog.setSize(600, 400);
/* 182 */       customFilterDialog.setLocationRelativeTo(null);
/* 183 */       customFilterDialog.show();
/*     */       
/*     */       return;
/*     */     } 
/* 187 */     if (e.getSource() == this.selectColumnsMenuItem) {
/* 188 */       ViewUI selectedViewUI = this.selectedStoryUI.getSelectedViewUI();
/* 189 */       SelectColumnsUI x = new SelectColumnsUI(selectedViewUI);
/* 190 */       x.pack();
/* 191 */       x.show();
/* 192 */       x = null;
/*     */       return;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 199 */   public void mousePressed(MouseEvent e) { maybeShowPopup(e); }
/*     */ 
/*     */ 
/*     */   
/* 203 */   public void mouseReleased(MouseEvent e) { maybeShowPopup(e); }
/*     */ 
/*     */   
/*     */   private void maybeShowPopup(MouseEvent e) {
/* 207 */     if (e.isPopupTrigger() && e.getComponent().isEnabled()) {
/* 208 */       DefaultMutableTreeNode node = (DefaultMutableTreeNode)this.storyTree.getPathForLocation(e.getX(), e.getY()).getLastPathComponent();
/* 209 */       setSelectedNode(node);
/*     */       
/* 211 */       this.deleteStoryMenuItem.setEnabled((node != this.storyTreeRoot));
/*     */       
/* 213 */       this.popup.show(e.getComponent(), e.getX(), e.getY());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setSelectedNode(DefaultMutableTreeNode theNode) {
/* 218 */     this.selectedStoryUI = (StoryUI)theNode.getUserObject();
/* 219 */     this.splitPane.setRightComponent(this.selectedStoryUI.getTopComponent());
/* 220 */     this.splitPane.setDividerLocation(0.25D);
/* 221 */     this.storyTree.setSelectionPath(new TreePath(theNode.getPath()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void treeNodesChanged(TreeModelEvent e) {
/* 233 */     if (e.getSource() == this.storyTreeModel) {
/*     */       
/* 235 */       DefaultMutableTreeNode node = 
/* 236 */         (DefaultMutableTreeNode)e.getTreePath().getLastPathComponent();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 245 */         int index = e.getChildIndices()[0];
/* 246 */         node = 
/* 247 */           (DefaultMutableTreeNode)node.getChildAt(index);
/* 248 */       } catch (NullPointerException nullPointerException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 253 */       this.selectedStoryUI.setName(node.getUserObject().toString());
/* 254 */       node.setUserObject(this.selectedStoryUI);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void treeNodesInserted(TreeModelEvent e) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void treeNodesRemoved(TreeModelEvent e) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void treeStructureChanged(TreeModelEvent e) {}
/*     */ 
/*     */ 
/*     */   
/* 272 */   public StoryUI getSelectedView() { return this.selectedStoryUI; }
/*     */ 
/*     */   
/*     */   public void keyReleased(KeyEvent e) {}
/*     */   
/*     */   public void keyPressed(KeyEvent e) {
/* 278 */     if (e.getKeyCode() == 127)
/* 279 */       deleteSelctedTreeNode(); 
/*     */   }
/*     */   
/*     */   public void keyTyped(KeyEvent e) {}
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\gu\\ui\stories\StoryTreeUI.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */