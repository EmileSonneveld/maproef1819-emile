/*     */ package classes.lrg.insider.gui.ui;
/*     */ import java.awt.Component;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JOptionPane;
/*     */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*     */ import lrg.common.abstractions.entities.GroupEntity;
/*     */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*     */ import lrg.common.abstractions.plugins.tools.AbstractEntityTool;
/*     */ import lrg.insider.gui.ui.EntityMouseAction;
/*     */ import lrg.insider.gui.ui.MainForm;
/*     */ import lrg.insider.gui.ui.browser.BrowserUI;
/*     */ import lrg.insider.gui.ui.codeviewer.CodeViewerUI;
/*     */ import lrg.insider.gui.ui.stories.StoryTreeUI;
/*     */ import lrg.insider.gui.ui.utils.ToolsStarter;
/*     */ import lrg.insider.gui.ui.views.ViewUI;
/*     */ import lrg.memoria.core.Method;
/*     */ 
/*     */ public class EntityMouseAction implements MenuReaction, ActionListener {
/*     */   private static EntityMouseAction theInstance;
/*     */   private AbstractEntityInterface selectedEntity;
/*     */   
/*     */   public static EntityMouseAction instance() {
/*  28 */     if (theInstance == null) {
/*  29 */       theInstance = new EntityMouseAction();
/*  30 */       ViewRenderer.setMenuReaction(theInstance);
/*     */     } 
/*  32 */     return theInstance;
/*     */   }
/*     */   private JPopupMenu popup; private JMenu groupBuildersMenu; private JMenu toolsMenu; private JMenu vizualizationsMenu; private JMenu detailsMenu;
/*     */   private JMenu detailsMenuInNewBrowser;
/*     */   
/*     */   private EntityMouseAction() {
/*  38 */     this.popup = new JPopupMenu();
/*     */     
/*  40 */     this.groupBuildersMenu = new JMenu("Open group...");
/*  41 */     this.groupBuildersMenu.getPopupMenu().setLayout(new GridLayout(0, 2));
/*  42 */     this.toolsMenu = new JMenu("Run tool...");
/*  43 */     this.vizualizationsMenu = new JMenu("Visualizing...");
/*  44 */     this.detailsMenu = new JMenu("Open detail in current browser...");
/*  45 */     this.detailsMenuInNewBrowser = new JMenu("Open detail in new browser...");
/*     */     
/*  47 */     this.popup.add(this.groupBuildersMenu);
/*  48 */     this.popup.addSeparator();
/*  49 */     this.popup.add(this.toolsMenu);
/*  50 */     this.popup.addSeparator();
/*  51 */     this.popup.add(this.vizualizationsMenu);
/*  52 */     this.popup.addSeparator();
/*  53 */     this.popup.add(this.detailsMenu);
/*  54 */     this.popup.add(this.detailsMenuInNewBrowser);
/*     */   }
/*     */   
/*     */   public void addFiltersAndPropertiesToMenu() {
/*  58 */     (MainForm.instance()).propertiesMenu.removeAll();
/*  59 */     (MainForm.instance()).filtersMenu.removeAll();
/*  60 */     (StoryTreeUI.instance()).filteringRulesMenu.removeAll();
/*  61 */     (StoryTreeUI.instance()).filteringRulesMenu.add((StoryTreeUI.instance()).applyCustomFilter);
/*  62 */     (StoryTreeUI.instance()).filteringRulesMenu.addSeparator();
/*     */     
/*  64 */     GroupEntity crtGroup = StoryTreeUI.instance().getSelectedView().getSelectedViewUI()
/*  65 */       .getGroupEntity();
/*  66 */     if (crtGroup.getElements().size() < 1) {
/*     */       return;
/*     */     }
/*  69 */     ArrayList propertyList = crtGroup.getEntityTypeOfElements().nameAllPropertyComputers();
/*  70 */     (MainForm.instance()).propertiesMenu.setEnabled((propertyList.size() > 0));
/*  71 */     Iterator<String> it = propertyList.iterator();
/*  72 */     while (it.hasNext()) {
/*  73 */       String currentGroupBuilderName = (String)it.next();
/*  74 */       JMenuItem menuItem = new JMenuItem(currentGroupBuilderName);
/*  75 */       (MainForm.instance()).propertiesMenu.add(menuItem);
/*  76 */       menuItem.addActionListener(this);
/*     */     } 
/*     */     
/*  79 */     ArrayList filterList = crtGroup.getEntityTypeOfElements().nameAllFilteringRules();
/*  80 */     (MainForm.instance()).filtersMenu.setEnabled((filterList.size() > 0));
/*  81 */     (StoryTreeUI.instance()).filteringRulesMenu.setEnabled((filterList.size() > 0));
/*  82 */     it = filterList.iterator();
/*  83 */     while (it.hasNext()) {
/*  84 */       String currentFilterName = (String)it.next();
/*  85 */       JMenuItem menuItem = new JMenuItem(currentFilterName);
/*  86 */       (MainForm.instance()).filtersMenu.add(menuItem);
/*  87 */       menuItem.addActionListener(this);
/*     */       
/*  89 */       menuItem = new JMenuItem(currentFilterName);
/*  90 */       (StoryTreeUI.instance()).filteringRulesMenu.add(menuItem);
/*  91 */       menuItem.addActionListener(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*  96 */   public void buildFor(Object anEntity, Object mouseEvent) { buildFor((AbstractEntityInterface)anEntity, (MouseEvent)mouseEvent); }
/*     */ 
/*     */   
/*     */   public void buildFor(AbstractEntityInterface anEntity, MouseEvent e) {
/* 100 */     this.selectedEntity = anEntity;
/*     */ 
/*     */     
/* 103 */     if (e.getClickCount() == 1) {
/* 104 */       if (e.getButton() == 1) {
/* 105 */         (MainForm.instance()).groupBuildersMenu.removeAll();
/*     */         
/* 107 */         if (this.selectedEntity != null) {
/* 108 */           (MainForm.instance()).groupBuildersMenu.setEnabled((this.selectedEntity.getEntityType().nameAllGroupBuilders().size() > 0));
/* 109 */           Iterator<String> it = this.selectedEntity.getEntityType().nameAllGroupBuilders().iterator();
/* 110 */           while (it.hasNext()) {
/* 111 */             String currentGroupBuilderName = (String)it.next();
/* 112 */             JMenuItem menuItem = new JMenuItem(currentGroupBuilderName);
/* 113 */             (MainForm.instance()).groupBuildersMenu.add(menuItem);
/*     */             
/* 115 */             menuItem.addActionListener(this);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 120 */       if (e.getButton() == 2) {
/* 121 */         if (this.selectedEntity instanceof Method) {
/* 122 */           Method aMethod = (Method)this.selectedEntity;
/* 123 */           CodeViewerUI codeViewer = new CodeViewerUI(aMethod);
/* 124 */           codeViewer.pack();
/* 125 */           codeViewer.setVisible(true);
/*     */         } else {
/* 127 */           JOptionPane.showMessageDialog(e.getComponent(), "CodeViewer supports only methods so far.", "oops!!!", 1);
/*     */         } 
/*     */         
/*     */         return;
/*     */       } 
/* 132 */       if (e.isPopupTrigger() || e.getButton() == 3) {
/* 133 */         this.toolsMenu.removeAll();
/* 134 */         if (this.selectedEntity != null) {
/* 135 */           this.toolsMenu.setEnabled((this.selectedEntity.getEntityType().nameAllTools().size() > 0));
/* 136 */           Iterator<String> it = this.selectedEntity.getEntityType().nameAllTools().iterator();
/* 137 */           while (it.hasNext()) {
/* 138 */             String toolName = (String)it.next();
/* 139 */             JMenuItem menuItem = new JMenuItem(toolName);
/* 140 */             this.toolsMenu.add(menuItem);
/* 141 */             menuItem.addActionListener(this);
/*     */           } 
/*     */         } 
/*     */         
/* 145 */         this.groupBuildersMenu.removeAll();
/* 146 */         if (this.selectedEntity != null) {
/* 147 */           this.groupBuildersMenu.setEnabled((this.selectedEntity.getEntityType().nameAllGroupBuilders().size() > 0));
/* 148 */           Iterator<String> it = this.selectedEntity.getEntityType().nameAllGroupBuilders().iterator();
/* 149 */           while (it.hasNext()) {
/* 150 */             String currentGroupBuilderName = (String)it.next();
/* 151 */             JMenuItem menuItem = new JMenuItem(currentGroupBuilderName);
/* 152 */             this.groupBuildersMenu.add(menuItem);
/*     */             
/* 154 */             menuItem.addActionListener(this);
/*     */           } 
/*     */         } 
/*     */         
/* 158 */         this.vizualizationsMenu.removeAll();
/* 159 */         if (this.selectedEntity != null) {
/* 160 */           this.vizualizationsMenu.setEnabled((this.selectedEntity.getEntityType().nameAllVisualizations().size() > 0));
/* 161 */           Iterator<String> it = this.selectedEntity.getEntityType().nameAllVisualizations().iterator();
/* 162 */           while (it.hasNext()) {
/* 163 */             String currentVisualizationsName = (String)it.next();
/* 164 */             JMenuItem menuItem = new JMenuItem(currentVisualizationsName);
/* 165 */             this.vizualizationsMenu.add(menuItem);
/* 166 */             menuItem.addActionListener(this);
/*     */           } 
/*     */         } 
/*     */         
/* 170 */         this.detailsMenu.removeAll();
/* 171 */         this.detailsMenuInNewBrowser.removeAll();
/* 172 */         if (this.selectedEntity != null) {
/* 173 */           this.detailsMenu.setEnabled((this.selectedEntity.getEntityType().nameAllDetails().size() > 0));
/* 174 */           this.detailsMenuInNewBrowser.setEnabled((this.selectedEntity.getEntityType().nameAllDetails().size() > 0));
/* 175 */           Iterator<String> it = this.selectedEntity.getEntityType().nameAllDetails().iterator();
/* 176 */           while (it.hasNext()) {
/* 177 */             String currentDetailName = (String)it.next();
/* 178 */             JMenuItem menuItem = new JMenuItem(currentDetailName);
/* 179 */             JMenuItem menuItemInNewBrowser = new JMenuItem(currentDetailName);
/* 180 */             this.detailsMenu.add(menuItem);
/* 181 */             this.detailsMenuInNewBrowser.add(menuItemInNewBrowser);
/* 182 */             menuItem.addActionListener(this);
/* 183 */             menuItemInNewBrowser.addActionListener(this);
/*     */           } 
/*     */         } 
/*     */         
/* 187 */         this.popup.show(e.getComponent(), e.getX(), e.getY());
/*     */       } 
/*     */     } 
/*     */     
/* 191 */     if (e.getClickCount() == 2)
/*     */     {
/* 193 */       BrowserUI.instance().pointTo(this.selectedEntity.getProperty("Address").toString(), "Detail");
/*     */     }
/*     */   }
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 198 */     Component[] menuItems = (MainForm.instance()).groupBuildersMenu.getPopupMenu().getComponents();
/*     */     
/* 200 */     for (int i = 0; i < menuItems.length; i++) {
/* 201 */       if (e.getSource() == menuItems[i]) {
/* 202 */         JMenuItem menuItem = (JMenuItem)e.getSource();
/* 203 */         GroupEntity newGroup = this.selectedEntity.getGroup(menuItem.getText());
/* 204 */         if (newGroup != null) {
/* 205 */           if (newGroup.getElements().size() > 0) {
/* 206 */             StoryTreeUI.instance().addStoryUI(new StoryUI(new ViewUI(newGroup)), 
/* 207 */                 1);
/*     */           } else {
/* 209 */             JOptionPane.showMessageDialog(null, "There is no entity in this group.\n", 
/* 210 */                 "Empty group", 0);
/*     */           } 
/*     */         }
/*     */         return;
/*     */       } 
/*     */     } 
/* 216 */     Component[] filtersMenuItems = (MainForm.instance()).filtersMenu.getPopupMenu().getComponents();
/* 217 */     for (int i = 0; i < filtersMenuItems.length; i++) {
/* 218 */       if (e.getSource() == filtersMenuItems[i]) {
/* 219 */         JMenuItem menuItem = (JMenuItem)e.getSource();
/*     */         
/* 221 */         ViewUI selectedViewUI = StoryTreeUI.instance().getSelectedView().getSelectedViewUI();
/* 222 */         FilteringRule aRule = (FilteringRule)selectedViewUI.getGroupEntity().getEntityTypeOfElements().findFilteringRule(menuItem.getText());
/* 223 */         selectedViewUI.applyFilter(aRule);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 228 */     Component[] propertiesMenuItems = (MainForm.instance()).propertiesMenu.getPopupMenu().getComponents();
/* 229 */     for (int i = 0; i < propertiesMenuItems.length; i++) {
/* 230 */       if (e.getSource() == propertiesMenuItems[i]) {
/* 231 */         JMenuItem menuItem = (JMenuItem)e.getSource();
/*     */         
/* 233 */         ViewUI selectedViewUI = StoryTreeUI.instance().getSelectedView().getSelectedViewUI();
/*     */         
/* 235 */         if (selectedViewUI.hasPropertyCalled(menuItem.getText())) {
/* 236 */           selectedViewUI.removeColumnForPropertyCalled(menuItem.getText());
/*     */         } else {
/* 238 */           selectedViewUI.addColumnForPropertyCalled(menuItem.getText());
/*     */         } 
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*     */     
/* 245 */     menuItems = this.groupBuildersMenu.getPopupMenu().getComponents();
/* 246 */     for (int i = 0; i < menuItems.length; i++) {
/* 247 */       if (e.getSource() == menuItems[i]) {
/* 248 */         JMenuItem menuItem = (JMenuItem)e.getSource();
/* 249 */         GroupEntity newGroup = this.selectedEntity.getGroup(menuItem.getText());
/* 250 */         if (newGroup != null) {
/* 251 */           if (newGroup.getElements().size() > 0) {
/* 252 */             StoryTreeUI.instance().addStoryUI(new StoryUI(new ViewUI(newGroup)), 
/* 253 */                 1);
/*     */           } else {
/* 255 */             JOptionPane.showMessageDialog(null, "There is no entity in this group.\n", 
/* 256 */                 "Empty group", 0);
/*     */           } 
/*     */         }
/*     */         return;
/*     */       } 
/*     */     } 
/* 262 */     menuItems = this.toolsMenu.getPopupMenu().getComponents();
/* 263 */     for (int i = 0; i < menuItems.length; i++) {
/* 264 */       if (e.getSource() == menuItems[i]) {
/* 265 */         JMenuItem menuItem = (JMenuItem)e.getSource();
/* 266 */         AbstractEntityTool aEntityTool = this.selectedEntity.getTool(menuItem.getText());
/* 267 */         ToolsStarter starter = new ToolsStarter(aEntityTool.getToolName(), aEntityTool.getParameterList(), aEntityTool.getParameterExplanations(), aEntityTool.getParameterInitialValue());
/*     */         try {
/* 269 */           if (starter.dislay())
/* 270 */             aEntityTool.run(this.selectedEntity, starter.getParameterValues()); 
/* 271 */         } catch (RuntimeException exc) {
/* 272 */           System.err.println(String.valueOf(aEntityTool.getToolName()) + " could not be run !");
/* 273 */           exc.printStackTrace();
/*     */         } 
/*     */         return;
/*     */       } 
/*     */     } 
/* 278 */     menuItems = this.vizualizationsMenu.getPopupMenu().getComponents();
/* 279 */     for (int i = 0; i < menuItems.length; i++) {
/* 280 */       if (e.getSource() == menuItems[i]) {
/* 281 */         JMenuItem menuItem = (JMenuItem)e.getSource();
/* 282 */         this.selectedEntity.getEntityType().findVisualizations(menuItem.getText()).view(this.selectedEntity);
/*     */         return;
/*     */       } 
/*     */     } 
/* 286 */     menuItems = this.detailsMenu.getPopupMenu().getComponents();
/* 287 */     for (int i = 0; i < menuItems.length; i++) {
/* 288 */       if (e.getSource() == menuItems[i]) {
/* 289 */         JMenuItem menuItem = (JMenuItem)e.getSource();
/* 290 */         BrowserUI.instance().pointTo(this.selectedEntity.getProperty("Address").toString(), menuItem.getText());
/*     */         return;
/*     */       } 
/*     */     } 
/* 294 */     menuItems = this.detailsMenuInNewBrowser.getPopupMenu().getComponents();
/* 295 */     for (int i = 0; i < menuItems.length; i++) {
/* 296 */       if (e.getSource() == menuItems[i]) {
/* 297 */         JMenuItem menuItem = (JMenuItem)e.getSource();
/* 298 */         BrowserUI.instance().pointToInNewTab(this.selectedEntity.getProperty("Address").toString(), menuItem.getText());
/*     */         return;
/*     */       } 
/*     */     } 
/* 302 */     JMenuItem menuItem = (JMenuItem)e.getSource();
/* 303 */     ViewUI selectedViewUI = StoryTreeUI.instance().getSelectedView().getSelectedViewUI();
/* 304 */     FilteringRule aRule = (FilteringRule)selectedViewUI.getGroupEntity().getEntityTypeOfElements().findFilteringRule(menuItem.getText());
/* 305 */     selectedViewUI.applyFilter(aRule);
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\gu\\ui\EntityMouseAction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */