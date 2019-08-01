/*     */ package classes.lrg.insider.gui.ui.filter;
/*     */ 
/*     */ import com.intellij.uiDesigner.core.GridConstraints;
/*     */ import com.intellij.uiDesigner.core.GridLayoutManager;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.Iterator;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.DefaultListModel;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.event.ListSelectionEvent;
/*     */ import javax.swing.event.ListSelectionListener;
/*     */ import lrg.common.abstractions.entities.EntityType;
/*     */ import lrg.common.abstractions.managers.EntityTypeManager;
/*     */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*     */ import lrg.insider.gui.ui.filter.CustomFilterUI;
/*     */ import lrg.insider.gui.ui.filter.FilteringRuleBuilderUI;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FilteringRuleBuilderUI
/*     */   implements ListSelectionListener, ActionListener
/*     */ {
/*     */   private CustomFilterUI myCustomFilterUI;
/*     */   private JPanel topComponent;
/*     */   private JList propertyNameList;
/*     */   private DefaultListModel propertyNameListModel;
/*     */   private JList operatorNameList;
/*     */   private DefaultListModel operatorNameListModel;
/*     */   private EntityType myEntityType;
/*     */   private String selectedPCName;
/*     */   private JButton deleteButton;
/*     */   
/*     */   public FilteringRuleBuilderUI(EntityType entityType, CustomFilterUI aCustomFilterUI) {
/* 146 */     $$$setupUI$$$(); this.myCustomFilterUI = aCustomFilterUI; this.propertyNameListModel = new DefaultListModel(); this.propertyNameList.setModel(this.propertyNameListModel); this.propertyNameList.addListSelectionListener(this); this.operatorNameListModel = new DefaultListModel(); this.operatorNameList.setModel(this.operatorNameListModel); this.operatorNameList.addListSelectionListener(this); this.deleteButton.addActionListener(this); this.deleteButton.setEnabled(false); this.topComponent.setName(entityType.getName()); Iterator propertyIterator = entityType.nameAllPropertyComputers().iterator();
/*     */     while (propertyIterator.hasNext()) {
/*     */       String currentPCName = (String)propertyIterator.next();
/*     */       this.propertyNameListModel.addElement(currentPCName);
/*     */     } 
/*     */     Iterator filterIterator = entityType.nameAllFilteringRules().iterator();
/*     */     while (filterIterator.hasNext()) {
/*     */       String currentFilterName = (String)filterIterator.next();
/*     */       this.propertyNameListModel.addElement(currentFilterName);
/*     */     } 
/* 156 */     this.myEntityType = entityType; } private void $$$setupUI$$$() { JPanel _1 = new JPanel();
/* 157 */     this.topComponent = _1;
/* 158 */     _1.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
/*     */     
/* 160 */     JTextField _2 = new JTextField();
/* 161 */     _1.add(_2, new GridConstraints(2, 0, 1, 1, 8, 1, 6, 0, null, new Dimension(150, -1), null));
/*     */     
/* 163 */     JScrollPane _3 = new JScrollPane();
/* 164 */     _1.add(_3, new GridConstraints(0, 0, 1, 1, 0, 3, 7, 7, null, null, null));
/* 165 */     _3.setBorder(BorderFactory.createTitledBorder("Available Properties"));
/*     */     
/* 167 */     JList _4 = new JList();
/* 168 */     this.propertyNameList = _4;
/* 169 */     _4.setSelectionMode(0);
/* 170 */     _3.setViewportView(_4);
/*     */     
/* 172 */     JScrollPane _5 = new JScrollPane();
/* 173 */     _1.add(_5, new GridConstraints(1, 0, 1, 1, 0, 3, 7, 7, null, null, null));
/* 174 */     _5.setBorder(BorderFactory.createTitledBorder("Available Operators"));
/*     */     
/* 176 */     JList _6 = new JList();
/* 177 */     this.operatorNameList = _6;
/* 178 */     _6.setSelectionMode(0);
/* 179 */     _5.setViewportView(_6);
/*     */     
/* 181 */     JButton _7 = new JButton();
/* 182 */     this.deleteButton = _7;
/* 183 */     _7.setText("Delete");
/* 184 */     _1.add(_7, new GridConstraints(3, 0, 1, 1, 0, 1, 3, 0, null, null, null)); }
/*     */ 
/*     */   
/*     */   public Container getTopComponent() { return this.topComponent; }
/*     */   
/*     */   public void valueChanged(ListSelectionEvent e) {
/*     */     if (e.getSource() == this.propertyNameList && !e.getValueIsAdjusting() && this.propertyNameList.getSelectedIndex() != -1) {
/*     */       this.selectedPCName = (String)this.propertyNameList.getSelectedValue();
/*     */       if (this.myEntityType.findPropertyComputer(this.selectedPCName) != null) {
/*     */         String entityTypeNameOfResultEntity = this.myEntityType.findPropertyComputer(this.selectedPCName).getResultEntityTypeName();
/*     */         EntityType eType = EntityTypeManager.getEntityTypeForName(entityTypeNameOfResultEntity);
/*     */         Iterator it = eType.nameAllFilteringOperators().iterator();
/*     */         this.operatorNameListModel.clear();
/*     */         while (it.hasNext())
/*     */           this.operatorNameListModel.addElement(it.next().toString()); 
/*     */         this.deleteButton.setEnabled(false);
/*     */       } else if (this.myEntityType.findFilteringRule(this.selectedPCName) != null) {
/*     */         FilteringRule aRule = (FilteringRule)this.myEntityType.findFilteringRule(this.selectedPCName);
/*     */         this.operatorNameListModel.clear();
/*     */         this.myCustomFilterUI.setFilter(aRule);
/*     */         this.deleteButton.setEnabled(true);
/*     */       } 
/*     */     } 
/*     */     if (e.getSource() == this.operatorNameList && !e.getValueIsAdjusting() && this.operatorNameList.getSelectedIndex() != -1) {
/*     */       FilteringRule aRule;
/*     */       if (this.myEntityType.findPropertyComputer(this.selectedPCName) != null) {
/*     */         String entityTypeNameOfResultEntity = this.myEntityType.findPropertyComputer(this.selectedPCName).getResultEntityTypeName();
/*     */         String selectedOperatorName = (String)this.operatorNameList.getSelectedValue();
/*     */         if (EntityTypeManager.getEntityTypeForName(entityTypeNameOfResultEntity).findFilteringOperator(selectedOperatorName) instanceof lrg.common.abstractions.plugins.operators.FilteringOperatorWithThresholds) {
/*     */           aRule = new FilteringRule(this.selectedPCName, selectedOperatorName, this.myEntityType.getName(), getThresholdFromUser());
/*     */         } else {
/*     */           aRule = new FilteringRule(this.selectedPCName, selectedOperatorName, this.myEntityType.getName());
/*     */         } 
/*     */       } else {
/*     */         aRule = (FilteringRule)this.myEntityType.findFilteringRule(this.selectedPCName);
/*     */       } 
/*     */       this.myCustomFilterUI.setFilter(aRule);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/*     */     if (e.getSource() == this.deleteButton) {
/*     */       FilteringRule aRule = (FilteringRule)this.myEntityType.findFilteringRule(this.selectedPCName);
/*     */       this.propertyNameListModel.removeElement(this.selectedPCName);
/*     */       EntityTypeManager.unAttach(aRule);
/*     */     } 
/*     */   }
/*     */   
/*     */   private Object getThresholdFromUser() {
/*     */     boolean userInputOK = false;
/*     */     Double threshold = new Double(0.0D);
/*     */     while (!userInputOK) {
/*     */       String userInput = JOptionPane.showInputDialog(getTopComponent(), "What's the threshold ?");
/*     */       if (userInput == null) {
/*     */         threshold = new Double(0.0D);
/*     */         userInputOK = true;
/*     */         continue;
/*     */       } 
/*     */       try {
/*     */         threshold = new Double(userInput);
/*     */         userInputOK = true;
/*     */       } catch (NumberFormatException nfe) {
/*     */         return userInput;
/*     */       } 
/*     */     } 
/*     */     return threshold;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\gu\\ui\filter\FilteringRuleBuilderUI.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */