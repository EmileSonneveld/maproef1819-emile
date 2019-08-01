/*     */ package classes.lrg.insider.gui.ui.stories;
/*     */ import java.awt.Component;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JPopupMenu;
/*     */ import javax.swing.JTabbedPane;
/*     */ import javax.swing.event.ChangeEvent;
/*     */ import javax.swing.event.ChangeListener;
/*     */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*     */ import lrg.insider.gui.ui.filter.CustomFilterUI;
/*     */ import lrg.insider.gui.ui.stories.SelectColumnsUI;
/*     */ import lrg.insider.gui.ui.stories.StoryUI;
/*     */ import lrg.insider.gui.ui.utils.ToolsStarter;
/*     */ import lrg.insider.gui.ui.views.ViewUI;
/*     */ import lrg.insider.metamodel.Address;
/*     */ 
/*     */ public class StoryUI extends MouseAdapter implements ActionListener, ChangeListener {
/*     */   JTabbedPane viewTabs;
/*     */   String name;
/*     */   ArrayList viewUIs;
/*     */   JPopupMenu popup;
/*     */   
/*     */   public StoryUI(ViewUI aViewUI) {
/*  32 */     this.viewTabs = new JTabbedPane();
/*  33 */     this.viewTabs.addMouseListener(this);
/*  34 */     this.viewTabs.addChangeListener(this);
/*  35 */     this.viewUIs = new ArrayList();
/*     */     
/*  37 */     this.name = aViewUI.getGroupEntity().getName();
/*     */ 
/*     */     
/*  40 */     addViewUI(aViewUI);
/*     */     
/*  42 */     this.popup = new JPopupMenu();
/*     */     
/*  44 */     this.filteringRulesMenu = new JMenu("Apply Filtering Rule");
/*  45 */     this.applyCustomFilter = new JMenuItem("Custom");
/*  46 */     this.applyCustomFilter.addActionListener(this);
/*  47 */     this.selectColumnsMenuItem = new JMenuItem("Select Columns");
/*  48 */     this.selectColumnsMenuItem.addActionListener(this);
/*     */     
/*  50 */     this.printColumns = new JMenuItem("Print Columns");
/*  51 */     this.printColumns.addActionListener(this);
/*     */     
/*  53 */     this.popup.add(this.selectColumnsMenuItem);
/*  54 */     this.popup.addSeparator();
/*  55 */     this.popup.add(this.printColumns);
/*  56 */     this.popup.addSeparator();
/*  57 */     this.popup.add(this.filteringRulesMenu);
/*     */   }
/*     */   JMenuItem selectColumnsMenuItem; JMenuItem printColumns; JMenu filteringRulesMenu; JMenuItem applyCustomFilter;
/*     */   
/*  61 */   public Component getTopComponent() { return this.viewTabs; }
/*     */ 
/*     */   
/*     */   public void addViewUI(ViewUI aViewUI) {
/*  65 */     this.viewUIs.add(aViewUI);
/*  66 */     this.viewTabs.add(aViewUI.getTopComponent());
/*  67 */     this.viewTabs.setSelectedComponent(aViewUI.getTopComponent());
/*  68 */     aViewUI.setStoryUI(this);
/*     */   }
/*     */ 
/*     */   
/*  72 */   public void setName(String newName) { this.name = newName; }
/*     */ 
/*     */ 
/*     */   
/*     */   private void deleteViewUI() {
/*  77 */     if (okToDelete()) {
/*  78 */       this.viewUIs.remove(this.viewTabs.getSelectedIndex());
/*  79 */       this.viewTabs.remove(this.viewTabs.getSelectedIndex());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*  84 */   private boolean okToDelete() { return (this.viewUIs.size() > 1 && getSelectedViewUI().getGroupEntity().getName().compareTo(Address.buildForRoot()) != 0); }
/*     */ 
/*     */ 
/*     */   
/*  88 */   public String toString() { return this.name; }
/*     */ 
/*     */ 
/*     */   
/*  92 */   public void stateChanged(ChangeEvent e) { ((ViewUI)this.viewUIs.get(0)).updateGroupMenu(); }
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/*  96 */     if (e.getSource() == this.applyCustomFilter) {
/*  97 */       ViewUI selectedViewUI = getSelectedViewUI();
/*  98 */       CustomFilterUI cfUI = new CustomFilterUI(selectedViewUI);
/*  99 */       JDialog customFilterDialog = new JDialog(InsiderGUIMain.getFrame(), "Custom filter editor", false);
/*     */       
/* 101 */       customFilterDialog.setContentPane(cfUI.getTopComponent());
/* 102 */       customFilterDialog.setSize(600, 400);
/* 103 */       customFilterDialog.setLocationRelativeTo(null);
/* 104 */       customFilterDialog.show();
/*     */       
/*     */       return;
/*     */     } 
/* 108 */     if (e.getSource() == this.printColumns) {
/* 109 */       ViewUI selectedViewUI = getSelectedViewUI();
/* 110 */       ArrayList<String> paramNames = new ArrayList<String>(); paramNames.add("Filename");
/* 111 */       ArrayList<String> paramDescription = new ArrayList<String>(); paramDescription.add("File where table will be stored");
/* 112 */       ArrayList<String> initialValues = new ArrayList<String>(); initialValues.add("");
/*     */       
/* 114 */       ToolsStarter filenameDialog = new ToolsStarter("Table Printer", paramNames, paramDescription, initialValues);
/* 115 */       filenameDialog.dislay();
/* 116 */       String filename = (String)filenameDialog.getParameterValues().get(0);
/* 117 */       if (filename.equals("")) { System.out.println(selectedViewUI); }
/*     */       else { try {
/* 119 */           PrintStream out_stream = new PrintStream(new FileOutputStream(filename));
/* 120 */           out_stream.print(selectedViewUI);
/* 121 */           out_stream.close();
/* 122 */         } catch (Exception exception) {} }
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/* 127 */     if (e.getSource() == this.selectColumnsMenuItem) {
/* 128 */       ViewUI selectedViewUI = getSelectedViewUI();
/* 129 */       SelectColumnsUI x = new SelectColumnsUI(selectedViewUI);
/* 130 */       x.pack();
/* 131 */       x.show();
/* 132 */       x = null;
/*     */       
/*     */       return;
/*     */     } 
/* 136 */     JMenuItem menuItem = (JMenuItem)e.getSource();
/* 137 */     ViewUI selectedViewUI = getSelectedViewUI();
/* 138 */     FilteringRule aRule = (FilteringRule)selectedViewUI.getGroupEntity().getEntityTypeOfElements().findFilteringRule(menuItem.getText());
/* 139 */     selectedViewUI.applyFilter(aRule);
/*     */   }
/*     */ 
/*     */   
/* 143 */   public ViewUI getSelectedViewUI() { return (ViewUI)this.viewUIs.get(0); }
/*     */ 
/*     */ 
/*     */   
/* 147 */   public void mousePressed(MouseEvent e) { maybeShowPopup(e); }
/*     */ 
/*     */ 
/*     */   
/* 151 */   public void mouseReleased(MouseEvent e) { maybeShowPopup(e); }
/*     */ 
/*     */   
/*     */   private void maybeShowPopup(MouseEvent e) {
/* 155 */     if (e.isPopupTrigger()) {
/* 156 */       ViewUI selectedViewUI = getSelectedViewUI();
/* 157 */       this.filteringRulesMenu.removeAll();
/* 158 */       this.filteringRulesMenu.add(this.applyCustomFilter);
/* 159 */       this.applyCustomFilter.setEnabled((selectedViewUI.getGroupEntity().size() > 1));
/* 160 */       this.filteringRulesMenu.addSeparator();
/*     */       
/* 162 */       Iterator it = selectedViewUI.getAllFilteringRules().iterator();
/* 163 */       while (it.hasNext()) {
/*     */         
/* 165 */         JMenuItem menuItem = new JMenuItem(it.next().toString());
/* 166 */         menuItem.addActionListener(this);
/* 167 */         this.filteringRulesMenu.add(menuItem);
/*     */       } 
/*     */       
/* 170 */       this.popup.show(e.getComponent(), e.getX(), e.getY());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\gu\\ui\stories\StoryUI.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */