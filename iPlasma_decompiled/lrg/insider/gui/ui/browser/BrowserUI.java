/*     */ package classes.lrg.insider.gui.ui.browser;
/*     */ import com.intellij.uiDesigner.core.GridConstraints;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.io.PrintStream;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTabbedPane;
/*     */ import javax.swing.JTextField;
/*     */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*     */ import lrg.insider.gui.ui.browser.BrowserPageUI;
/*     */ import lrg.insider.gui.ui.browser.BrowserUI;
/*     */ 
/*     */ public class BrowserUI extends MouseAdapter implements ActionListener, KeyListener, ChangeListener {
/*     */   public static BrowserUI instance() {
/*  22 */     if (theBrowserUI == null) {
/*  23 */       theBrowserUI = new BrowserUI();
/*     */     }
/*  25 */     return theBrowserUI;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private JTabbedPane tabs;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static BrowserUI theBrowserUI;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private JButton backButton;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private JButton forwardButton;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private JButton rootButton;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private JButton searchButton;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private JComboBox addressComboBox;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private DefaultComboBoxModel addressComboBoxModel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private JPanel topComponent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private JPopupMenu popup;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private JMenuItem closeTabMenuItem;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ArrayList browsers;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private BrowserUI() {
/* 293 */     $$$setupUI$$$(); this.rootButton.addActionListener(this); this.backButton.addActionListener(this); this.forwardButton.addActionListener(this); this.rootButton.setEnabled(false); this.backButton.setEnabled(false); this.forwardButton.setEnabled(false); this.searchButton.setEnabled(false); this.addressComboBox.setEnabled(false); this.addressComboBox.addActionListener(this); this.addressComboBox.getEditor().getEditorComponent().addKeyListener(this); Dimension d = this.addressComboBox.getSize(); this.addressComboBox.setMaximumSize(d);
/*     */     this.addressComboBox.getEditor().getEditorComponent().setMaximumSize(d);
/*     */     this.addressComboBoxModel = new DefaultComboBoxModel();
/*     */     this.addressComboBox.setModel(this.addressComboBoxModel);
/*     */     this.browsers = new ArrayList();
/*     */     this.popup = new JPopupMenu();
/*     */     this.closeTabMenuItem = new JMenuItem("Close This Tab");
/*     */     this.closeTabMenuItem.addActionListener(this);
/*     */     this.popup.add(this.closeTabMenuItem);
/*     */     this.tabs.addMouseListener(this);
/* 303 */     this.tabs.addChangeListener(this); } private void $$$setupUI$$$() { JPanel _1 = new JPanel();
/* 304 */     this.topComponent = _1;
/* 305 */     _1.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
/*     */     
/* 307 */     JPanel _2 = new JPanel();
/* 308 */     _2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
/* 309 */     _1.add(_2, new GridConstraints(0, 0, 1, 1, 8, 1, 3, 0, null, null, null));
/*     */     
/* 311 */     JPanel _3 = new JPanel();
/* 312 */     _3.setLayout(new GridLayoutManager(1, 6, new Insets(0, 0, 0, 0), -1, -1));
/* 313 */     _2.add(_3, new GridConstraints(0, 0, 1, 1, 8, 1, 0, 0, null, null, null));
/*     */     
/* 315 */     JButton _4 = new JButton();
/* 316 */     this.backButton = _4;
/* 317 */     _4.setToolTipText("Go back one location");
/* 318 */     _4.setText("Back");
/* 319 */     _4.setHorizontalAlignment(0);
/* 320 */     _4.setHorizontalTextPosition(11);
/* 321 */     _3.add(_4, new GridConstraints(0, 1, 1, 1, 0, 0, 3, 0, null, null, null));
/*     */     
/* 323 */     Spacer _5 = new Spacer();
/* 324 */     _3.add(_5, new GridConstraints(0, 0, 1, 1, 0, 1, 0, 1, new Dimension(10, -1), null, null));
/*     */     
/* 326 */     JButton _6 = new JButton();
/* 327 */     this.forwardButton = _6;
/* 328 */     _6.setToolTipText("Go forward one location");
/* 329 */     _6.setText("Forward");
/* 330 */     _6.setHorizontalAlignment(0);
/* 331 */     _6.setHorizontalTextPosition(11);
/* 332 */     _3.add(_6, new GridConstraints(0, 2, 1, 1, 8, 0, 3, 0, null, null, null));
/*     */     
/* 334 */     JButton _7 = new JButton();
/* 335 */     this.rootButton = _7;
/* 336 */     _7.setToolTipText("Save Detail as HTML");
/* 337 */     _7.setText("Save");
/* 338 */     _3.add(_7, new GridConstraints(0, 3, 1, 1, 0, 0, 3, 0, null, null, null));
/*     */     
/* 340 */     JComboBox _8 = new JComboBox();
/* 341 */     this.addressComboBox = _8;
/* 342 */     _8.setToolTipText("for simplicity the valid syntax for addresses is like this: package.package::class.class::member");
/* 343 */     _8.setEditable(true);
/* 344 */     _3.add(_8, new GridConstraints(0, 4, 1, 1, 8, 1, 7, 0, null, null, null));
/*     */     
/* 346 */     JButton _9 = new JButton();
/* 347 */     this.searchButton = _9;
/* 348 */     _9.setToolTipText("search not implemented yet! :(");
/* 349 */     _9.setText("Search");
/* 350 */     _3.add(_9, new GridConstraints(0, 5, 1, 1, 4, 0, 3, 0, null, null, null));
/*     */     
/* 352 */     JTabbedPane _10 = new JTabbedPane();
/* 353 */     this.tabs = _10;
/* 354 */     _1.add(_10, new GridConstraints(1, 0, 1, 1, 0, 3, 3, 7, null, new Dimension(200, 200), null)); }
/*     */ 
/*     */   
/*     */   public void newMetaModelLoaded() {
/*     */     this.rootButton.setEnabled(true);
/*     */     this.backButton.setEnabled(false);
/*     */     this.forwardButton.setEnabled(false);
/*     */     this.searchButton.setEnabled(false);
/*     */     this.addressComboBox.setEnabled(true);
/*     */     pointToInNewTab(Address.buildForRoot(), "Detail");
/*     */   }
/*     */   
/*     */   public void metaModelUnloaded() {
/*     */     this.rootButton.setEnabled(false);
/*     */     this.backButton.setEnabled(false);
/*     */     this.forwardButton.setEnabled(false);
/*     */     this.searchButton.setEnabled(false);
/*     */     this.addressComboBox.setEnabled(false);
/*     */     this.tabs.removeChangeListener(this);
/*     */     this.tabs.removeAll();
/*     */     this.browsers.clear();
/*     */     this.tabs.addChangeListener(this);
/*     */   }
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/*     */     if (e.getSource() == this.addressComboBox && e.getActionCommand().compareTo("comboBoxEdited") == 0) {
/*     */       String tempAddress = (String)this.addressComboBox.getSelectedItem();
/*     */       if (MetaModel.instance().findEntityByAddress(tempAddress) != null) {
/*     */         getSelectedBrowser().goTo(tempAddress, "Detail");
/*     */       } else {
/*     */         JOptionPane.showMessageDialog(this.topComponent, "The address you entered could not be found.", "Invalid Address!", 2);
/*     */       } 
/*     */     } 
/*     */     if (e.getSource() == this.backButton)
/*     */       getSelectedBrowser().goBack(); 
/*     */     if (e.getSource() == this.forwardButton)
/*     */       getSelectedBrowser().goForward(); 
/*     */     if (e.getSource() == this.rootButton) {
/*     */       String filename = "../../results/";
/*     */       if (getSelectedBrowser().getCurrentEntity() instanceof lrg.memoria.core.System) {
/*     */         filename = String.valueOf(filename) + "~root";
/*     */       } else {
/*     */         filename = String.valueOf(filename) + getSelectedBrowser().getCurrentEntity().getName();
/*     */       } 
/*     */       filename = String.valueOf(filename) + "_" + getSelectedBrowser().getDetailName() + ".html";
/*     */       System.out.println(filename);
/*     */       try {
/*     */         PrintStream out_stream = new PrintStream(new FileOutputStream(filename));
/*     */         out_stream.print(getSelectedBrowser().getCurrentDetailText());
/*     */         out_stream.close();
/*     */       } catch (Exception exception) {}
/*     */     } 
/*     */     if (e.getSource() == this.closeTabMenuItem) {
/*     */       BrowserPageUI toBeRemoved = getSelectedBrowser();
/*     */       this.tabs.remove(toBeRemoved.getTopComponent());
/*     */       this.browsers.remove(toBeRemoved);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void update(BrowserPageUI browser) {
/*     */     AbstractEntityInterface currentEntity = browser.getCurrentEntity();
/*     */     if (currentEntity == null)
/*     */       return; 
/*     */     if (this.tabs.getSelectedIndex() >= 0) {
/*     */       String title = currentEntity.getProperty("Address").toString();
/*     */       if (title.length() > 80)
/*     */         title = String.valueOf(title.substring(0, 80)) + "(...)"; 
/*     */       this.tabs.setTitleAt(this.tabs.indexOfComponent(browser.getTopComponent()), title);
/*     */     } 
/*     */     JTextField jtf = (JTextField)this.addressComboBox.getEditor().getEditorComponent();
/*     */     jtf.setText(currentEntity.getProperty("Address").toString());
/*     */     this.backButton.setEnabled((browser.history().numberOfBackLocations() > 0));
/*     */     this.forwardButton.setEnabled((browser.history().numberOfForwardLocations() > 0));
/*     */   }
/*     */   
/*     */   public void keyPressed(KeyEvent e) {
/*     */     int selectedIndex;
/*     */     int selectedIndex;
/*     */     JTextField jtf;
/*     */     JTextField jtf;
/*     */     if (!this.addressComboBox.isPopupVisible())
/*     */       this.addressComboBox.setPopupVisible(true); 
/*     */     switch (e.getKeyCode()) {
/*     */       case 40:
/*     */         jtf = (JTextField)this.addressComboBox.getEditor().getEditorComponent();
/*     */         selectedIndex = this.addressComboBox.getSelectedIndex();
/*     */         if (selectedIndex < this.addressComboBox.getItemCount() - 1 && jtf.getText().compareTo(this.addressComboBox.getSelectedItem().toString()) == 0)
/*     */           selectedIndex++; 
/*     */         this.addressComboBox.setSelectedIndex(selectedIndex);
/*     */         jtf.setText((String)this.addressComboBox.getSelectedItem());
/*     */         e.consume();
/*     */         break;
/*     */       case 38:
/*     */         jtf = (JTextField)this.addressComboBox.getEditor().getEditorComponent();
/*     */         selectedIndex = this.addressComboBox.getSelectedIndex();
/*     */         if (selectedIndex > 0 && jtf.getText().compareTo(this.addressComboBox.getSelectedItem().toString()) == 0)
/*     */           selectedIndex--; 
/*     */         this.addressComboBox.setSelectedIndex(selectedIndex);
/*     */         jtf.setText((String)this.addressComboBox.getSelectedItem());
/*     */         e.consume();
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void keyReleased(KeyEvent e) {}
/*     */   
/*     */   public void keyTyped(KeyEvent e) {
/*     */     JTextField jtf = (JTextField)this.addressComboBox.getEditor().getEditorComponent();
/*     */     String prefix = jtf.getText().substring(0, jtf.getSelectionStart());
/*     */     switch (e.getKeyChar()) {
/*     */       case '\b':
/*     */         if (prefix.length() > 2) {
/*     */           prefix = jtf.getText().substring(0, jtf.getSelectionStart() - 1);
/*     */           break;
/*     */         } 
/*     */         prefix = "";
/*     */         break;
/*     */       case '\n':
/*     */         break;
/*     */       default:
/*     */         prefix = String.valueOf(prefix) + e.getKeyChar();
/*     */         break;
/*     */     } 
/*     */     this.addressComboBoxModel.removeAllElements();
/*     */     ArrayList matches = MetaModel.instance().findAddressesThatStartWith(prefix);
/*     */     for (int i = 0; i < matches.size(); i++)
/*     */       this.addressComboBoxModel.addElement(matches.get(i)); 
/*     */     if (matches.size() > 0 && prefix.compareTo(matches.get(0).toString()) != 0) {
/*     */       jtf.setText(matches.get(0).toString());
/*     */       jtf.setCaretPosition(matches.get(0).toString().length());
/*     */       jtf.moveCaretPosition(prefix.length());
/*     */     } else {
/*     */       jtf.setText(prefix);
/*     */     } 
/*     */     e.consume();
/*     */   }
/*     */   
/*     */   public void pointTo(String externalAddressAsString, String detailName) { getSelectedBrowser().goTo(externalAddressAsString, detailName); }
/*     */   
/*     */   public void pointToInNewTab(String address, String detailName) {}
/*     */   
/*     */   public Component getTopComponent() { return this.topComponent; }
/*     */   
/*     */   public void stateChanged(ChangeEvent e) { update(getSelectedBrowser()); }
/*     */   
/*     */   public void mousePressed(MouseEvent e) { maybeShowPopup(e); }
/*     */   
/*     */   public void mouseReleased(MouseEvent e) { maybeShowPopup(e); }
/*     */   
/*     */   private void maybeShowPopup(MouseEvent e) {
/*     */     if (e.isPopupTrigger() && this.browsers.size() > 1)
/*     */       this.popup.show(e.getComponent(), e.getX(), e.getY()); 
/*     */   }
/*     */   
/*     */   void addBrowser(BrowserPageUI b) {
/*     */     this.browsers.add(b);
/*     */     this.tabs.add(b.getTopComponent());
/*     */     this.tabs.setSelectedComponent(b.getTopComponent());
/*     */   }
/*     */   
/*     */   private BrowserPageUI getSelectedBrowser() { return (BrowserPageUI)this.browsers.get(this.tabs.getSelectedIndex()); }
/*     */   
/*     */   private static void addIconToButton(JButton button, String imageName, String altText) {
/*     */     String imgLocation = "file://" + System.getProperty("user.dir") + System.getProperty("file.separator") + imageName;
/*     */     URL imageURL = null;
/*     */     try {
/*     */       imageURL = new URL(imgLocation);
/*     */     } catch (MalformedURLException e) {
/*     */       e.printStackTrace();
/*     */     } 
/*     */     if (imageURL != null) {
/*     */       button.setIcon(new ImageIcon(imageURL, altText));
/*     */       button.setText(altText);
/*     */     } else {
/*     */       button.setText(altText);
/*     */       System.err.println("Resource not found: " + imgLocation);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\gu\\ui\browser\BrowserUI.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */