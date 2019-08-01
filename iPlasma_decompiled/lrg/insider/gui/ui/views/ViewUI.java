/*     */ package classes.lrg.insider.gui.ui.views;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Point;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTabbedPane;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.ListSelectionModel;
/*     */ import javax.swing.event.ListSelectionEvent;
/*     */ import javax.swing.event.ListSelectionListener;
/*     */ import javax.swing.table.JTableHeader;
/*     */ import javax.swing.table.TableColumnModel;
/*     */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*     */ import lrg.common.abstractions.entities.GroupEntity;
/*     */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*     */ import lrg.insider.gui.ui.EntityMouseAction;
/*     */ import lrg.insider.gui.ui.MainForm;
/*     */ import lrg.insider.gui.ui.stories.StoryTreeUI;
/*     */ import lrg.insider.gui.ui.stories.StoryUI;
/*     */ import lrg.insider.gui.ui.views.InsiderViewTable;
/*     */ import lrg.insider.gui.ui.views.InsiderViewTableModel;
/*     */ import lrg.insider.gui.ui.views.ViewUI;
/*     */ 
/*     */ public class ViewUI extends MouseAdapter implements ListSelectionListener {
/*     */   private ArrayList columnNamesToBeDisplayed;
/*     */   private StoryUI myStoryUI;
/*  34 */   private InsiderViewTableModel model = new InsiderViewTableModel(); private GroupEntity myGroupEntity; private AbstractEntityInterface selectedEntity;
/*  35 */   private JTable table = new InsiderViewTable(this.model, this);
/*  36 */   private JScrollPane topComponent = new JScrollPane(this.table); private Color oldColor;
/*     */   public ViewUI(GroupEntity aGroupEntity) {
/*  38 */     JTableHeader th = this.table.getTableHeader();
/*  39 */     th.addMouseListener(this);
/*     */     
/*  41 */     this.table.getSelectionModel().addListSelectionListener(this);
/*  42 */     this.table.addMouseListener(this);
/*     */     
/*  44 */     this.columnNamesToBeDisplayed = new ArrayList();
/*  45 */     this.columnNamesToBeDisplayed.add("Name");
/*     */     
/*  47 */     setGroupEntity(aGroupEntity);
/*  48 */     this.ascending = true;
/*  49 */     this.oldColor = this.table.getSelectionBackground();
/*  50 */     this.lastHighlightFilterName = null;
/*     */   }
/*     */   private FilteringRule lastHighlightFilterName;
/*     */   private boolean ascending;
/*     */   
/*  55 */   public GroupEntity getGroupEntity() { return this.myGroupEntity; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void highlightFilter(FilteringRule highlightRule) {
/*  60 */     this.lastHighlightFilterName = highlightRule;
/*  61 */     this.table.getSelectionModel().removeListSelectionListener(this);
/*  62 */     this.table.setSelectionMode(2);
/*  63 */     this.table.setSelectionBackground(new Color(1.0F, 0.75F, 0.75F));
/*  64 */     for (int i = 0; i < this.myGroupEntity.size(); i++) {
/*     */       
/*  66 */       AbstractEntityInterface currentEntity = this.myGroupEntity.getElementAt(this.model.mapToGroupElementIndex(i));
/*  67 */       if (highlightRule.applyFilter(currentEntity))
/*     */       {
/*  69 */         this.table.getSelectionModel().addSelectionInterval(i, i);
/*     */       }
/*     */     } 
/*  72 */     this.table.getSelectionModel().addListSelectionListener(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void unHighlight() {
/*  77 */     this.table.getSelectionModel().clearSelection();
/*  78 */     this.table.setSelectionBackground(this.oldColor);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void applyFilter(FilteringRule aFilteringRule) {
/*  84 */     GroupEntity filteredGroupEntity = this.myGroupEntity.applyFilter(aFilteringRule);
/*  85 */     if (filteredGroupEntity.size() < 1) {
/*  86 */       JOptionPane.showMessageDialog(getTopComponent(), "There is no entity matching the filter criteria\nFilter was not applied", 
/*  87 */           "Filter to restrictive", 0);
/*     */     } else {
/*     */       
/*  90 */       StoryTreeUI.instance().addStoryUI(new StoryUI(new ViewUI(filteredGroupEntity)), 
/*  91 */           2);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection getAllFilteringRules() {
/*  98 */     if (this.myGroupEntity == null || this.myGroupEntity.getEntityTypeOfElements() == null)
/*  99 */       return new ArrayList(); 
/* 100 */     return this.myGroupEntity.getEntityTypeOfElements().nameAllFilteringRules();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 105 */   public Component getTopComponent() { return this.topComponent; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void valueChanged(ListSelectionEvent e) {
/* 111 */     if (e.getValueIsAdjusting())
/*     */       return; 
/* 113 */     ListSelectionModel lsm = (ListSelectionModel)e.getSource();
/* 114 */     if (lsm.isSelectionEmpty()) {
/* 115 */       this.selectedEntity = null;
/* 116 */       (MainForm.instance()).groupBuildersMenu.setEnabled(false);
/*     */     } else {
/* 118 */       int selectedRow = lsm.getMinSelectionIndex();
/* 119 */       this.table.setSelectionBackground(this.oldColor);
/* 120 */       this.table.getSelectionModel().removeListSelectionListener(this);
/*     */       
/* 122 */       this.table.getSelectionModel().setLeadSelectionIndex(selectedRow);
/* 123 */       this.table.getSelectionModel().addListSelectionListener(this);
/* 124 */       this.selectedEntity = this.myGroupEntity.getElementAt(this.model.mapToGroupElementIndex(selectedRow));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateGroupMenu() {
/* 130 */     if (this.selectedEntity != null) {
/* 131 */       (MainForm.instance()).groupBuildersMenu.setEnabled(true);
/* 132 */       EntityMouseAction.instance().buildFor(this.selectedEntity, 
/* 133 */           new MouseEvent(this.table, 0, 0L, 0, 0, 0, 1, false, 1));
/*     */     } else {
/* 135 */       (MainForm.instance()).groupBuildersMenu.setEnabled(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void mouseClicked(MouseEvent e) {
/* 141 */     if (e.getSource() == this.table.getTableHeader()) {
/*     */       
/* 143 */       TableColumnModel columnModel = this.table.getColumnModel();
/* 144 */       int viewColumn = columnModel.getColumnIndexAtX(e.getX());
/* 145 */       int column = this.table.convertColumnIndexToModel(viewColumn);
/* 146 */       if (e.getClickCount() == 1 && column != -1) {
/*     */ 
/*     */         
/* 149 */         this.ascending = !this.ascending;
/* 150 */         this.model.sortByColumn(column, this.ascending);
/* 151 */         this.table.clearSelection();
/* 152 */         if (this.table.getSelectionBackground() != this.oldColor) {
/*     */           
/* 154 */           unHighlight();
/* 155 */           highlightFilter(this.lastHighlightFilterName);
/*     */         } 
/*     */       } 
/*     */     } 
/* 159 */     if (e.getSource() == this.table) {
/*     */ 
/*     */       
/* 162 */       Point p = new Point(e.getX(), e.getY());
/* 163 */       this.table.getSelectionModel().setLeadSelectionIndex(this.table.rowAtPoint(p));
/*     */       
/* 165 */       if (this.selectedEntity != null) {
/* 166 */         EntityMouseAction.instance().buildFor(this.selectedEntity, e);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 173 */   public void setStoryUI(StoryUI storyUI) { this.myStoryUI = storyUI; }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setGroupEntity(GroupEntity aGroupEntity) {
/* 178 */     this.model.feedWithData(this.columnNamesToBeDisplayed, aGroupEntity.iterator());
/* 179 */     this.myGroupEntity = aGroupEntity;
/* 180 */     String name = aGroupEntity.getName();
/* 181 */     if (name.length() > 80) name = String.valueOf(name.substring(0, 80)) + " (...) "; 
/* 182 */     this.topComponent.setName(String.valueOf(name) + " [" + aGroupEntity.size() + "]");
/* 183 */     if (this.myStoryUI != null) {
/*     */       
/* 185 */       JTabbedPane myStorysTabbedPane = (JTabbedPane)this.myStoryUI.getTopComponent();
/* 186 */       myStorysTabbedPane.setTitleAt(myStorysTabbedPane.getSelectedIndex(), this.topComponent.getName());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasPropertyCalled(String propertyName) {
/* 192 */     for (int i = 0; i < this.model.getColumnCount(); i++) {
/* 193 */       if (this.model.getColumnName(i).compareTo(propertyName) == 0)
/* 194 */         return true; 
/*     */     } 
/* 196 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addColumnForPropertyCalled(String propertyName) {
/* 201 */     if (hasPropertyCalled(propertyName)) {
/*     */       return;
/*     */     }
/* 204 */     this.columnNamesToBeDisplayed.add(propertyName);
/* 205 */     this.model.feedWithData(this.columnNamesToBeDisplayed, this.myGroupEntity.iterator());
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeColumnForPropertyCalled(String propertyName) {
/* 210 */     if (hasPropertyCalled(propertyName)) {
/*     */       
/* 212 */       this.columnNamesToBeDisplayed.remove(propertyName);
/* 213 */       this.model.feedWithData(this.columnNamesToBeDisplayed, this.myGroupEntity.iterator());
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 218 */     StringBuffer temp = new StringBuffer("");
/* 219 */     DecimalFormat twoDecimals = new DecimalFormat("#0.00");
/* 220 */     for (Object crtColumn : this.columnNamesToBeDisplayed) {
/* 221 */       temp.append(crtColumn + "\t");
/*     */     }
/* 223 */     temp.append("\n");
/* 224 */     for (int i = 0; i < this.model.getRowCount(); i++) {
/* 225 */       for (int j = 0; j < this.model.getColumnCount(); j++) {
/* 226 */         temp.append(this.model.getValueAt(i, j));
/* 227 */         if (j < this.model.getColumnCount() - 1) temp.append("\t"); 
/*     */       } 
/* 229 */       temp.append("\n");
/*     */     } 
/*     */     
/* 232 */     return temp.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\gu\\ui\views\ViewUI.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */