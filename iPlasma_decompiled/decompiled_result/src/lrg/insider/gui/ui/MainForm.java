/*     */ package classes.lrg.insider.gui.ui;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuItem;
/*     */ import lrg.insider.gui.ui.MainForm;
/*     */ 
/*     */ public class MainForm implements ActionListener {
/*     */   private static MainForm theMainForm;
/*     */   public JMenu groupBuildersMenu;
/*     */   public JMenu propertiesMenu;
/*     */   public JMenu filtersMenu;
/*     */   private JLabel statusBar;
/*     */   private JPanel topComponent;
/*     */   private JSplitPane splitPane;
/*     */   JMenuBar menuBar;
/*     */   JMenuItem openJavaSources;
/*     */   JMenuItem openCppSources;
/*     */   JMenuItem exitItem;
/*     */   JMenuItem openCachedModel;
/*     */   JMenuItem openExtendedJavaSources;
/*     */   JMenuItem closeMetamodel;
/*     */   JMenuItem staticEntityTypeCache;
/*     */   JMenuItem dynamicEntityTypeCache;
/*     */   JMenuItem clearEntityTypeCache;
/*     */   JMenuItem openHisMo;
/*     */   
/*     */   public static MainForm instance() {
/*  27 */     if (theMainForm == null) {
/*  28 */       theMainForm = new MainForm();
/*     */     }
/*  30 */     return theMainForm;
/*     */   }
/*     */   
/*     */   private MainForm() {
/*  34 */     this.groupBuildersMenu = new JMenu("Group");
/*  35 */     this.propertiesMenu = new JMenu("Property");
/*  36 */     this.filtersMenu = new JMenu("Filter");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 260 */     $$$setupUI$$$(); setStatusBarText("Ready to go."); this.menuBar = new JMenuBar(); JMenu loadMenu = new JMenu("Load"); this.openJavaSources = new JMenuItem("Java sources"); this.openJavaSources.addActionListener(this); loadMenu.add(this.openJavaSources); this.openCppSources = new JMenuItem("C++ (from McC tables)"); this.openCppSources.addActionListener(this); loadMenu.add(this.openCppSources); this.openCachedModel = new JMenuItem("Memoria cached model"); this.openCachedModel.addActionListener(this); loadMenu.add(this.openCachedModel); loadMenu.addSeparator(); this.closeMetamodel = new JMenuItem("Close meta-model"); this.closeMetamodel.addActionListener(this); loadMenu.add(this.closeMetamodel); loadMenu.addSeparator(); this.exitItem = new JMenuItem("Exit"); this.exitItem.addActionListener(this); loadMenu.add(this.exitItem); JMenu saveMenu = new JMenu("Cache"); this.staticEntityTypeCache = new JMenuItem("Save Entity Types Cache"); this.staticEntityTypeCache.addActionListener(this); saveMenu.add(this.staticEntityTypeCache); this.clearEntityTypeCache = new JMenuItem("Delete EntityTypes Cache"); this.clearEntityTypeCache.addActionListener(this); saveMenu.add(this.clearEntityTypeCache); this.menuBar.add(loadMenu); this.groupBuildersMenu.getPopupMenu().setLayout(new GridLayout(0, 2)); this.propertiesMenu.getPopupMenu().setLayout(new GridLayout(0, 2));
/*     */     this.filtersMenu.getPopupMenu().setLayout(new GridLayout(0, 2));
/*     */     this.groupBuildersMenu.setEnabled(false);
/*     */     this.propertiesMenu.setEnabled(false);
/*     */     this.filtersMenu.setEnabled(false);
/*     */     this.menuBar.add(this.groupBuildersMenu);
/*     */     this.menuBar.add(this.propertiesMenu);
/*     */     this.menuBar.add(this.filtersMenu);
/*     */     this.splitPane.setTopComponent(StoryTreeUI.instance().getTopComponent());
/*     */     this.splitPane.setBottomComponent(BrowserUI.instance().getTopComponent());
/* 270 */     this.splitPane.setDividerLocation(200); } private void $$$setupUI$$$() { JPanel _1 = new JPanel();
/* 271 */     this.topComponent = _1;
/* 272 */     _1.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
/*     */     
/* 274 */     JLabel _2 = new JLabel();
/* 275 */     this.statusBar = _2;
/* 276 */     _2.setText("Label");
/* 277 */     _1.add(_2, new GridConstraints(1, 0, 1, 1, 8, 1, 0, 0, null, null, null));
/*     */     
/* 279 */     JSplitPane _3 = new JSplitPane();
/* 280 */     this.splitPane = _3;
/* 281 */     _3.setOneTouchExpandable(true);
/* 282 */     _3.setDividerLocation(35);
/* 283 */     _3.setOrientation(0);
/* 284 */     _1.add(_3, new GridConstraints(0, 0, 1, 1, 0, 3, 3, 3, null, null, null)); }
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/*     */     if (e.getSource() == this.openCppSources || e.getSource() == this.openJavaSources || e.getSource() == this.openExtendedJavaSources) {
/*     */       this.filtersMenu.setEnabled(true);
/*     */       if (ModelLoaderUI.show())
/*     */         (new Object(this, e)).start(); 
/*     */     } 
/*     */     if (e.getSource() == this.openCachedModel || e.getSource() == this.openHisMo) {
/*     */       JFileChooser fc = new JFileChooser();
/*     */       if (e.getSource() == this.openCachedModel) {
/*     */         fc.setFileSelectionMode(2);
/*     */         fc.setFileFilter(new Object(this));
/*     */       } 
/*     */       if (e.getSource() == this.openHisMo)
/*     */         fc.setFileSelectionMode(1); 
/*     */       if (fc.showOpenDialog(this.topComponent) == 0)
/*     */         (new Object(this, e, fc)).start(); 
/*     */     } 
/*     */     if (e.getSource() == this.closeMetamodel) {
/*     */       MetaModel.closeMetaModel();
/*     */       BrowserUI.instance().metaModelUnloaded();
/*     */       StoryTreeUI.instance().unloadMetaModel();
/*     */       System.gc();
/*     */       setStatusBarText("Model was succesfully unloaded ! ");
/*     */     } 
/*     */     if (e.getSource() == this.exitItem)
/*     */       System.exit(0); 
/*     */     if (e.getSource() == this.staticEntityTypeCache) {
/*     */       EntityTypeManager.writeStaticEntityTypesToCache();
/*     */       setStatusBarText("EntityTypes cache saved.");
/*     */     } 
/*     */     if (e.getSource() == this.clearEntityTypeCache) {
/*     */       CacheManager.getStaticETCache().delete();
/*     */       setStatusBarText("EntityTypes cache deleted!");
/*     */     } 
/*     */   }
/*     */   
/*     */   public Container getTopComponent() { return this.topComponent; }
/*     */   
/*     */   public JMenuBar getMenuBar() { return this.menuBar; }
/*     */   
/*     */   public void setStatusBarText(String text) {
/*     */     if (text.length() > 100)
/*     */       text = String.valueOf(text.substring(0, 100)) + " [...]"; 
/*     */     this.statusBar.setText(text);
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\gu\\ui\MainForm.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */