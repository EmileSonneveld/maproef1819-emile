/*     */ package classes.lrg.insider.gui.ui.stories;
/*     */ 
/*     */ import com.intellij.uiDesigner.core.GridConstraints;
/*     */ import com.intellij.uiDesigner.core.GridLayoutManager;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.util.Iterator;
/*     */ import javax.swing.DefaultListModel;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextField;
/*     */ import lrg.common.abstractions.entities.EntityType;
/*     */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*     */ import lrg.insider.gui.ui.stories.SelectColumnsUI;
/*     */ 
/*     */ public class SelectColumnsUI extends JDialog implements ActionListener, ListSelectionListener, KeyListener {
/*     */   private ViewUI myViewUI;
/*     */   private JPanel topComponent;
/*     */   private JButton okButton;
/*     */   private JButton addDirectButton;
/*     */   private JButton removeDirectButton;
/*     */   private JList availableDirectList;
/*     */   private DefaultListModel availableDirectModel;
/*     */   private JList currentDirectList;
/*     */   private DefaultListModel currentDirectModel;
/*     */   
/*  29 */   public SelectColumnsUI(ViewUI aViewUI) { super(InsiderGUIMain.getFrame(), true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 236 */     $$$setupUI$$$(); this.myViewUI = aViewUI; setContentPane(this.topComponent); this.okButton.addActionListener(this); this.searchEditField.addKeyListener(this); this.availableDirectModel = new DefaultListModel(); this.currentDirectModel = new DefaultListModel(); this.currentAggregatePropertiesModel = new DefaultListModel(); this.aggregatesModel = new DefaultListModel(); this.availableAggregatePropertiesModel = new DefaultListModel(); this.currentAggregatePropertiesList.setModel(this.currentAggregatePropertiesModel); this.aggregatesList.setModel(this.aggregatesModel); this.availableAggregatePropertiesList.setModel(this.availableAggregatePropertiesModel);
/*     */     this.availableDirectList.setModel(this.availableDirectModel);
/*     */     this.currentDirectList.setModel(this.currentDirectModel);
/*     */     this.addDirectButton.addActionListener(this);
/*     */     this.removeDirectButton.addActionListener(this);
/*     */     this.addAggregateButton.addActionListener(this);
/*     */     this.removeAggregateButton.addActionListener(this);
/*     */     this.availableAggregatePropertiesList.addListSelectionListener(this);
/*     */     addDirectPropertyComputers("");
/*     */     pack();
/* 246 */     setLocationRelativeTo(null); } private JList currentAggregatePropertiesList; private DefaultListModel currentAggregatePropertiesModel; private JButton removeAggregateButton; private JButton addAggregateButton; private JList aggregatesList; private DefaultListModel aggregatesModel; private JList availableAggregatePropertiesList; private DefaultListModel availableAggregatePropertiesModel; private JTextField searchEditField; private boolean nameIsValid(String name, String info, String filter) { return !(name.toLowerCase().indexOf(filter.toLowerCase()) == -1 && info.toLowerCase().indexOf(filter.toLowerCase()) == -1); } private void $$$setupUI$$$() { JPanel _1 = new JPanel();
/* 247 */     this.topComponent = _1;
/* 248 */     _1.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
/*     */     
/* 250 */     JPanel _2 = new JPanel();
/* 251 */     _2.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
/* 252 */     _1.add(_2, new GridConstraints(1, 0, 1, 1, 0, 3, 0, 0, null, null, null));
/* 253 */     _2.setBorder(BorderFactory.createTitledBorder("Aggregate Properties"));
/*     */     
/* 255 */     JScrollPane _3 = new JScrollPane();
/* 256 */     _2.add(_3, new GridConstraints(0, 0, 1, 1, 0, 3, 6, 0, null, null, null));
/*     */     
/* 258 */     JList _4 = new JList();
/* 259 */     this.availableAggregatePropertiesList = _4;
/* 260 */     _4.setSelectionMode(0);
/* 261 */     _3.setViewportView(_4);
/*     */     
/* 263 */     JScrollPane _5 = new JScrollPane();
/* 264 */     _2.add(_5, new GridConstraints(0, 1, 1, 1, 0, 3, 6, 0, null, null, null));
/*     */     
/* 266 */     JList _6 = new JList();
/* 267 */     this.aggregatesList = _6;
/* 268 */     _6.setSelectionMode(0);
/* 269 */     _5.setViewportView(_6);
/*     */     
/* 271 */     JPanel _7 = new JPanel();
/* 272 */     _7.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
/* 273 */     _2.add(_7, new GridConstraints(0, 2, 1, 1, 0, 0, 0, 0, null, null, null));
/*     */     
/* 275 */     JButton _8 = new JButton();
/* 276 */     this.addAggregateButton = _8;
/* 277 */     _8.setText(">>");
/* 278 */     _7.add(_8, new GridConstraints(0, 0, 1, 1, 0, 1, 3, 0, null, null, null));
/*     */     
/* 280 */     JButton _9 = new JButton();
/* 281 */     this.removeAggregateButton = _9;
/* 282 */     _9.setText("<<");
/* 283 */     _7.add(_9, new GridConstraints(1, 0, 1, 1, 0, 1, 3, 0, null, null, null));
/*     */     
/* 285 */     JScrollPane _10 = new JScrollPane();
/* 286 */     _2.add(_10, new GridConstraints(0, 3, 1, 1, 0, 3, 6, 0, null, null, null));
/*     */     
/* 288 */     JList _11 = new JList();
/* 289 */     this.currentAggregatePropertiesList = _11;
/* 290 */     _10.setViewportView(_11);
/*     */     
/* 292 */     JButton _12 = new JButton();
/* 293 */     this.okButton = _12;
/* 294 */     _12.setText("OK");
/* 295 */     _1.add(_12, new GridConstraints(3, 0, 1, 1, 0, 3, 3, 0, null, null, null));
/*     */     
/* 297 */     JPanel _13 = new JPanel();
/* 298 */     _13.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
/* 299 */     _1.add(_13, new GridConstraints(0, 0, 1, 1, 0, 3, 0, 0, null, null, null));
/* 300 */     _13.setBorder(BorderFactory.createTitledBorder("Direct Properties"));
/*     */     
/* 302 */     JScrollPane _14 = new JScrollPane();
/* 303 */     _13.add(_14, new GridConstraints(0, 0, 1, 1, 0, 3, 7, 7, null, null, null));
/*     */     
/* 305 */     JList _15 = new JList();
/* 306 */     this.availableDirectList = _15;
/* 307 */     _14.setViewportView(_15);
/*     */     
/* 309 */     JPanel _16 = new JPanel();
/* 310 */     _16.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
/* 311 */     _13.add(_16, new GridConstraints(0, 1, 1, 1, 0, 0, 0, 3, null, null, null));
/*     */     
/* 313 */     JButton _17 = new JButton();
/* 314 */     this.addDirectButton = _17;
/* 315 */     _17.setText(">>");
/* 316 */     _16.add(_17, new GridConstraints(0, 0, 1, 1, 0, 1, 3, 0, null, null, null));
/*     */     
/* 318 */     JButton _18 = new JButton();
/* 319 */     this.removeDirectButton = _18;
/* 320 */     _18.setText("<<");
/* 321 */     _16.add(_18, new GridConstraints(1, 0, 1, 1, 0, 1, 3, 0, null, null, null));
/*     */     
/* 323 */     JScrollPane _19 = new JScrollPane();
/* 324 */     _13.add(_19, new GridConstraints(0, 2, 1, 1, 0, 3, 7, 7, null, null, null));
/*     */     
/* 326 */     JList _20 = new JList();
/* 327 */     this.currentDirectList = _20;
/* 328 */     _19.setViewportView(_20);
/*     */     
/* 330 */     JPanel _21 = new JPanel();
/* 331 */     _21.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
/* 332 */     _1.add(_21, new GridConstraints(2, 0, 1, 1, 0, 3, 3, 3, null, null, null));
/*     */     
/* 334 */     JLabel _22 = new JLabel();
/* 335 */     _22.setText("Search:");
/* 336 */     _21.add(_22, new GridConstraints(0, 0, 1, 1, 8, 0, 0, 0, null, null, null));
/*     */     
/* 338 */     JTextField _23 = new JTextField();
/* 339 */     this.searchEditField = _23;
/* 340 */     _23.setEnabled(true);
/* 341 */     _21.add(_23, new GridConstraints(0, 1, 1, 1, 0, 3, 6, 0, null, new Dimension(150, -1), null)); }
/*     */ 
/*     */   
/*     */   private void addDirectPropertyComputers(String filter) {
/*     */     this.currentDirectModel.clear();
/*     */     this.availableDirectModel.clear();
/*     */     ArrayList availableProperties = this.myViewUI.getGroupEntity().getEntityTypeOfElements().nameAllPropertyComputers();
/*     */     Iterator availablePropertiesIterator = availableProperties.iterator();
/*     */     while (availablePropertiesIterator.hasNext()) {
/*     */       PropertyComputer aPC = this.myViewUI.getGroupEntity().getEntityTypeOfElements().findPropertyComputer(availablePropertiesIterator.next().toString());
/*     */       String propertyName = aPC.getDescriptorObject().getName();
/*     */       String propertyInfo = aPC.getDescriptorObject().getInfo();
/*     */       if (nameIsValid(propertyName, propertyInfo, filter)) {
/*     */         if (this.myViewUI.hasPropertyCalled(propertyName)) {
/*     */           this.currentDirectModel.addElement(propertyName);
/*     */           continue;
/*     */         } 
/*     */         this.availableDirectModel.addElement(propertyName);
/*     */       } 
/*     */     } 
/*     */     this.availableAggregatePropertiesModel.clear();
/*     */     addPropertyComputersFromSubTypesOf(this.myViewUI.getGroupEntity().getEntityTypeOfElements(), filter);
/*     */   }
/*     */   
/*     */   private void addPropertyComputersFromSubTypesOf(EntityType entityType, String filter) {
/*     */     Iterator subTypesIt = EntityTypeManager.getAllSubtypesForName(entityType.getName()).iterator();
/*     */     while (subTypesIt.hasNext()) {
/*     */       EntityType subType = (EntityType)subTypesIt.next();
/*     */       Iterator subTypePropertyIterator = subType.nameAllPropertyComputers().iterator();
/*     */       while (subTypePropertyIterator.hasNext()) {
/*     */         PropertyComputer aPC = subType.findPropertyComputer(subTypePropertyIterator.next().toString());
/*     */         String propertyName = String.valueOf(subType.getName()) + "_" + aPC.getDescriptorObject().getName();
/*     */         String propertyInfo = aPC.getDescriptorObject().getInfo();
/*     */         if (nameIsValid(propertyName, propertyInfo, filter))
/*     */           this.availableAggregatePropertiesModel.addElement(propertyName); 
/*     */       } 
/*     */       addPropertyComputersFromSubTypesOf(subType, filter);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/*     */     if (e.getSource() == this.okButton) {
/*     */       dispose();
/*     */       return;
/*     */     } 
/*     */     if (e.getSource() == this.addDirectButton) {
/*     */       Object[] selectedPropertyNames = this.availableDirectList.getSelectedValues();
/*     */       for (int i = 0; i < selectedPropertyNames.length; i++) {
/*     */         this.myViewUI.addColumnForPropertyCalled((String)selectedPropertyNames[i]);
/*     */         this.availableDirectModel.removeElement(selectedPropertyNames[i]);
/*     */         this.currentDirectModel.addElement(selectedPropertyNames[i]);
/*     */       } 
/*     */       return;
/*     */     } 
/*     */     if (e.getSource() == this.removeDirectButton) {
/*     */       Object[] selectedPropertyNames = this.currentDirectList.getSelectedValues();
/*     */       for (int i = 0; i < selectedPropertyNames.length; i++) {
/*     */         this.myViewUI.removeColumnForPropertyCalled((String)selectedPropertyNames[i]);
/*     */         this.currentDirectModel.removeElement(selectedPropertyNames[i]);
/*     */         this.availableDirectModel.addElement(selectedPropertyNames[i]);
/*     */       } 
/*     */       return;
/*     */     } 
/*     */     if (e.getSource() == this.addAggregateButton) {
/*     */       if (this.availableAggregatePropertiesList.getSelectedIndex() != -1) {
/*     */         if (this.aggregatesList.getSelectedIndex() != -1) {
/*     */           String selectedValue = (String)this.availableAggregatePropertiesList.getSelectedValue();
/*     */           String propertyComputerName = selectedValue.substring(selectedValue.indexOf("_") + 1);
/*     */           String groupName = selectedValue.substring(0, selectedValue.indexOf("_"));
/*     */           String aggregatorName = (String)this.aggregatesList.getSelectedValue();
/*     */           String aggregatedPropertyName = String.valueOf(groupName) + "." + aggregatorName + "_" + propertyComputerName;
/*     */           this.currentAggregatePropertiesModel.addElement(aggregatedPropertyName);
/*     */           this.myViewUI.addColumnForPropertyCalled(aggregatedPropertyName);
/*     */         } else {
/*     */           JOptionPane.showMessageDialog(this.topComponent, "No AggregationOperator selected.");
/*     */         } 
/*     */       } else {
/*     */         JOptionPane.showMessageDialog(this.topComponent, "No subtype PropertyComputer selected.");
/*     */       } 
/*     */       return;
/*     */     } 
/*     */     if (e.getSource() == this.removeAggregateButton) {
/*     */       Object[] selectedPropertyNames = this.currentAggregatePropertiesList.getSelectedValues();
/*     */       for (int i = 0; i < selectedPropertyNames.length; i++) {
/*     */         this.myViewUI.removeColumnForPropertyCalled((String)selectedPropertyNames[i]);
/*     */         this.currentAggregatePropertiesModel.removeElement(selectedPropertyNames[i]);
/*     */       } 
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void valueChanged(ListSelectionEvent e) {
/*     */     if (e.getSource() == this.availableAggregatePropertiesList && !e.getValueIsAdjusting() && this.availableAggregatePropertiesList.getSelectedIndex() != -1) {
/*     */       String selectedValue = (String)this.availableAggregatePropertiesList.getSelectedValue();
/*     */       String subEntityTypeName = selectedValue.substring(0, selectedValue.indexOf("_"));
/*     */       String propertyComputerName = selectedValue.substring(selectedValue.indexOf("_") + 1);
/*     */       this.aggregatesModel.clear();
/*     */       EntityTypeManager.getEntityTypeForName(subEntityTypeName).findPropertyComputer(propertyComputerName).getResultEntityTypeName();
/*     */       Iterator it = EntityTypeManager.getAllFiltersForName(EntityTypeManager.getEntityTypeForName(subEntityTypeName).findPropertyComputer(propertyComputerName).getResultEntityTypeName()).iterator();
/*     */       while (it.hasNext())
/*     */         this.aggregatesModel.addElement(it.next().toString()); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void keyPressed(KeyEvent e) {}
/*     */   
/*     */   public void keyReleased(KeyEvent e) { addDirectPropertyComputers(this.searchEditField.getText()); }
/*     */   
/*     */   public void keyTyped(KeyEvent e) {}
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\gu\\ui\stories\SelectColumnsUI.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */