/*      */ package com.birosoft.liquid;
/*      */ 
/*      */ import java.awt.BorderLayout;
/*      */ import java.awt.Component;
/*      */ import java.awt.ComponentOrientation;
/*      */ import java.awt.Container;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Font;
/*      */ import java.awt.Graphics;
/*      */ import java.awt.Insets;
/*      */ import java.awt.LayoutManager;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.ComponentAdapter;
/*      */ import java.awt.event.ComponentEvent;
/*      */ import java.awt.event.FocusAdapter;
/*      */ import java.awt.event.FocusEvent;
/*      */ import java.awt.event.FocusListener;
/*      */ import java.awt.event.KeyEvent;
/*      */ import java.awt.event.MouseAdapter;
/*      */ import java.awt.event.MouseEvent;
/*      */ import java.awt.event.MouseListener;
/*      */ import java.beans.PropertyChangeEvent;
/*      */ import java.beans.PropertyChangeListener;
/*      */ import java.io.File;
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.IOException;
/*      */ import java.text.DateFormat;
/*      */ import java.util.Arrays;
/*      */ import java.util.Date;
/*      */ import java.util.EventObject;
/*      */ import java.util.Locale;
/*      */ import java.util.Vector;
/*      */ import javax.swing.AbstractAction;
/*      */ import javax.swing.AbstractListModel;
/*      */ import javax.swing.Action;
/*      */ import javax.swing.ActionMap;
/*      */ import javax.swing.Box;
/*      */ import javax.swing.BoxLayout;
/*      */ import javax.swing.ButtonGroup;
/*      */ import javax.swing.ComboBoxModel;
/*      */ import javax.swing.DefaultCellEditor;
/*      */ import javax.swing.DefaultListCellRenderer;
/*      */ import javax.swing.Icon;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JComboBox;
/*      */ import javax.swing.JComponent;
/*      */ import javax.swing.JFileChooser;
/*      */ import javax.swing.JLabel;
/*      */ import javax.swing.JList;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JScrollPane;
/*      */ import javax.swing.JTable;
/*      */ import javax.swing.JTextField;
/*      */ import javax.swing.JToggleButton;
/*      */ import javax.swing.KeyStroke;
/*      */ import javax.swing.ListModel;
/*      */ import javax.swing.ListSelectionModel;
/*      */ import javax.swing.LookAndFeel;
/*      */ import javax.swing.SwingUtilities;
/*      */ import javax.swing.TransferHandler;
/*      */ import javax.swing.UIManager;
/*      */ import javax.swing.border.EmptyBorder;
/*      */ import javax.swing.event.ListDataEvent;
/*      */ import javax.swing.event.ListDataListener;
/*      */ import javax.swing.event.ListSelectionEvent;
/*      */ import javax.swing.event.ListSelectionListener;
/*      */ import javax.swing.filechooser.FileFilter;
/*      */ import javax.swing.filechooser.FileSystemView;
/*      */ import javax.swing.plaf.ActionMapUIResource;
/*      */ import javax.swing.plaf.ComponentUI;
/*      */ import javax.swing.plaf.basic.BasicFileChooserUI;
/*      */ import javax.swing.table.AbstractTableModel;
/*      */ import javax.swing.table.DefaultTableCellRenderer;
/*      */ import javax.swing.table.TableCellRenderer;
/*      */ import javax.swing.table.TableColumn;
/*      */ import javax.swing.table.TableColumnModel;
/*      */ import javax.swing.table.TableModel;
/*      */ import javax.swing.text.Position;
/*      */ import sun.awt.shell.ShellFolder;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class FileChooserBasicUI
/*      */   extends BasicFileChooserUI
/*      */ {
/*      */   private JPanel centerPanel;
/*      */   private JLabel lookInLabel;
/*      */   private JComboBox directoryComboBox;
/*      */   private DirectoryComboBoxModel directoryComboBoxModel;
/*  108 */   private Action directoryComboBoxAction = new DirectoryComboBoxAction(this);
/*      */   
/*      */   private FilterComboBoxModel filterComboBoxModel;
/*      */   
/*      */   private JTextField fileNameTextField;
/*      */   
/*      */   private JToggleButton listViewButton;
/*      */   private JToggleButton detailsViewButton;
/*      */   private JPanel listViewPanel;
/*      */   private JPanel detailsViewPanel;
/*      */   private JPanel currentViewPanel;
/*  119 */   private FocusListener editorFocusListener = new FocusAdapter(this) { private final FileChooserBasicUI this$0;
/*      */       public void focusLost(FocusEvent e) {
/*  121 */         if (!e.isTemporary()) {
/*  122 */           this.this$0.applyEdit();
/*      */         }
/*      */       } }
/*      */   ;
/*      */   
/*      */   private boolean useShellFolder;
/*      */   
/*      */   private ListSelectionModel listSelectionModel;
/*      */   
/*      */   private JList list;
/*      */   
/*      */   private JTable detailsTable;
/*      */   
/*      */   private JButton approveButton;
/*      */   
/*      */   private JButton cancelButton;
/*      */   private JPanel buttonPanel;
/*      */   private JPanel bottomPanel;
/*      */   private JComboBox filterComboBox;
/*  141 */   private static final Dimension hstrut5 = new Dimension(5, 1);
/*  142 */   private static final Dimension hstrut11 = new Dimension(11, 1);
/*      */   
/*  144 */   private static final Dimension vstrut5 = new Dimension(1, 5);
/*      */   
/*  146 */   private static final Insets shrinkwrap = new Insets(2, 2, 2, 2);
/*      */ 
/*      */   
/*  149 */   private static int PREF_WIDTH = 500;
/*  150 */   private static int PREF_HEIGHT = 326;
/*  151 */   private static Dimension PREF_SIZE = new Dimension(PREF_WIDTH, PREF_HEIGHT);
/*      */   
/*  153 */   private static int MIN_WIDTH = 500;
/*  154 */   private static int MIN_HEIGHT = 326;
/*  155 */   private static Dimension MIN_SIZE = new Dimension(MIN_WIDTH, MIN_HEIGHT);
/*      */   
/*  157 */   private static int LIST_PREF_WIDTH = 405;
/*  158 */   private static int LIST_PREF_HEIGHT = 135;
/*  159 */   private static Dimension LIST_PREF_SIZE = new Dimension(LIST_PREF_WIDTH, LIST_PREF_HEIGHT);
/*      */   
/*      */   private static final int COLUMN_FILENAME = 0;
/*      */   
/*      */   private static final int COLUMN_FILESIZE = 1;
/*      */   private static final int COLUMN_FILETYPE = 2;
/*      */   private static final int COLUMN_FILEDATE = 3;
/*      */   private static final int COLUMN_FILEATTR = 4;
/*      */   private static final int COLUMN_COLCOUNT = 5;
/*  168 */   private int[] COLUMN_WIDTHS = { 150, 75, 130, 130, 40 };
/*      */ 
/*      */   
/*  171 */   private int lookInLabelMnemonic = 0;
/*  172 */   private String lookInLabelText = null;
/*  173 */   private String saveInLabelText = null;
/*      */   
/*  175 */   private int fileNameLabelMnemonic = 0;
/*  176 */   private String fileNameLabelText = null;
/*      */   
/*  178 */   private int filesOfTypeLabelMnemonic = 0;
/*  179 */   private String filesOfTypeLabelText = null;
/*      */   
/*  181 */   private String upFolderToolTipText = null;
/*  182 */   private String upFolderAccessibleName = null;
/*      */   
/*  184 */   private String homeFolderToolTipText = null;
/*  185 */   private String homeFolderAccessibleName = null;
/*      */   
/*  187 */   private String newFolderToolTipText = null;
/*  188 */   private String newFolderAccessibleName = null;
/*      */   
/*  190 */   private String listViewButtonToolTipText = null;
/*  191 */   private String listViewButtonAccessibleName = null;
/*      */   
/*  193 */   private String detailsViewButtonToolTipText = null;
/*  194 */   private String detailsViewButtonAccessibleName = null;
/*      */   
/*  196 */   private String fileNameHeaderText = null;
/*  197 */   private String fileSizeHeaderText = null;
/*  198 */   private String fileTypeHeaderText = null;
/*  199 */   private String fileDateHeaderText = null;
/*  200 */   private String fileAttrHeaderText = null;
/*      */   int lastIndex; File editFile; int editX; JTextField editCell; static final int space = 10; public void installUI(JComponent c) { super.installUI(c); } public void uninstallComponents(JFileChooser fc) { fc.removeAll(); this.bottomPanel = null; this.buttonPanel = null; } public void installComponents(JFileChooser fc) { FileSystemView fsv = fc.getFileSystemView(); fc.setBorder(new EmptyBorder(12, 12, 11, 11)); fc.setLayout(new BorderLayout(0, 11)); JPanel topPanel = new JPanel(new BorderLayout(11, 0)); JPanel topButtonPanel = new JPanel(); topButtonPanel.setLayout(new BoxLayout(topButtonPanel, 2)); topPanel.add(topButtonPanel, "After"); fc.add(topPanel, "North"); this.lookInLabel = new JLabel(this.lookInLabelText); this.lookInLabel.setDisplayedMnemonic(this.lookInLabelMnemonic); topPanel.add(this.lookInLabel, "Before"); this.directoryComboBox = new JComboBox(); this.directoryComboBox.putClientProperty("JComboBox.lightweightKeyboardNavigation", "Lightweight"); this.lookInLabel.setLabelFor(this.directoryComboBox); this.directoryComboBoxModel = createDirectoryComboBoxModel(fc); this.directoryComboBox.setModel(this.directoryComboBoxModel); this.directoryComboBox.addActionListener(this.directoryComboBoxAction); this.directoryComboBox.setRenderer(createDirectoryComboBoxRenderer(fc)); this.directoryComboBox.setAlignmentX(0.0F); this.directoryComboBox.setAlignmentY(0.0F); this.directoryComboBox.setMaximumRowCount(8); topPanel.add(this.directoryComboBox, "Center"); JButton upFolderButton = new JButton(getChangeToParentDirectoryAction()); upFolderButton.putClientProperty("JToolBar.isToolbarButton", Boolean.TRUE); upFolderButton.setOpaque(false); upFolderButton.setText(null); upFolderButton.setIcon(this.upFolderIcon); upFolderButton.setToolTipText(this.upFolderToolTipText); upFolderButton.getAccessibleContext().setAccessibleName(this.upFolderAccessibleName); upFolderButton.setAlignmentX(0.0F); upFolderButton.setAlignmentY(0.5F); upFolderButton.setMargin(new Insets(5, 5, 5, 5)); topButtonPanel.add(upFolderButton); topButtonPanel.add(Box.createRigidArea(hstrut5)); File homeDir = fsv.getHomeDirectory(); String toolTipText = this.homeFolderToolTipText; if (fsv.isRoot(homeDir)) toolTipText = getFileView(fc).getName(homeDir);  JButton b = new JButton(this.homeFolderIcon); b.putClientProperty("JToolBar.isToolbarButton", Boolean.TRUE); b.setToolTipText(toolTipText); b.getAccessibleContext().setAccessibleName(this.homeFolderAccessibleName); b.setAlignmentX(0.0F); b.setAlignmentY(0.5F); b.setMargin(new Insets(5, 5, 5, 5)); b.addActionListener(getGoHomeAction()); topButtonPanel.add(b); topButtonPanel.add(Box.createRigidArea(hstrut5)); b = new JButton(getNewFolderAction()); b.putClientProperty("JToolBar.isToolbarButton", Boolean.TRUE); b.setText(null); b.setIcon(this.newFolderIcon); b.setToolTipText(this.newFolderToolTipText); b.getAccessibleContext().setAccessibleName(this.newFolderAccessibleName); b.setAlignmentX(0.0F); b.setAlignmentY(0.5F); b.setMargin(new Insets(5, 5, 5, 5)); topButtonPanel.add(b); topButtonPanel.add(Box.createRigidArea(hstrut5)); ButtonGroup viewButtonGroup = new ButtonGroup(); class ViewButtonListener implements ActionListener {
/*      */       JFileChooser fc; private final FileChooserBasicUI this$0; ViewButtonListener(FileChooserBasicUI this$0, JFileChooser fc) { this.this$0 = this$0; this.fc = fc; } public void actionPerformed(ActionEvent e) { JToggleButton b = (JToggleButton)e.getSource(); JPanel oldViewPanel = this.this$0.currentViewPanel; if (b == this.this$0.detailsViewButton) { if (this.this$0.detailsViewPanel == null) { this.this$0.detailsViewPanel = this.this$0.createDetailsView(this.fc); this.this$0.detailsViewPanel.setPreferredSize(LIST_PREF_SIZE); }  this.this$0.currentViewPanel = this.this$0.detailsViewPanel; } else { this.this$0.currentViewPanel = this.this$0.listViewPanel; }  if (this.this$0.currentViewPanel != oldViewPanel) { this.this$0.centerPanel.remove(oldViewPanel); this.this$0.centerPanel.add(this.this$0.currentViewPanel, "Center"); this.this$0.centerPanel.revalidate(); this.this$0.centerPanel.repaint(); }  }
/*      */     }; ViewButtonListener viewButtonListener = new ViewButtonListener(this, fc); this.listViewButton = new JToggleButton(this.listViewIcon); this.listViewButton.putClientProperty("JToolBar.isToolbarButton", Boolean.TRUE); this.listViewButton.setToolTipText(this.listViewButtonToolTipText); this.listViewButton.getAccessibleContext().setAccessibleName(this.listViewButtonAccessibleName); this.listViewButton.setSelected(true); this.listViewButton.setAlignmentX(0.0F); this.listViewButton.setAlignmentY(0.5F); this.listViewButton.setMargin(shrinkwrap); this.listViewButton.addActionListener(viewButtonListener); topButtonPanel.add(this.listViewButton); viewButtonGroup.add(this.listViewButton); this.detailsViewButton = new JToggleButton(this.detailsViewIcon); this.detailsViewButton.putClientProperty("JToolBar.isToolbarButton", Boolean.TRUE); this.detailsViewButton.setToolTipText(this.detailsViewButtonToolTipText); this.detailsViewButton.getAccessibleContext().setAccessibleName(this.detailsViewButtonAccessibleName); this.detailsViewButton.setAlignmentX(0.0F); this.detailsViewButton.setAlignmentY(0.5F); this.detailsViewButton.setMargin(shrinkwrap); this.detailsViewButton.addActionListener(viewButtonListener); topButtonPanel.add(this.detailsViewButton); viewButtonGroup.add(this.detailsViewButton); this.useShellFolder = false; File[] roots = fsv.getRoots(); if (roots != null && roots.length == 1) { File[] cbFolders = (File[])ShellFolder.get("fileChooserComboBoxFolders"); if (cbFolders != null && cbFolders.length > 0 && roots[false] == cbFolders[false]) this.useShellFolder = true;  }  this.centerPanel = new JPanel(new BorderLayout()); this.listViewPanel = createList(fc); this.listSelectionModel = this.list.getSelectionModel(); this.listViewPanel.setPreferredSize(LIST_PREF_SIZE); this.centerPanel.add(this.listViewPanel, "Center"); this.currentViewPanel = this.listViewPanel; this.centerPanel.add(getAccessoryPanel(), "After"); JComponent accessory = fc.getAccessory(); if (accessory != null) getAccessoryPanel().add(accessory);  fc.add(this.centerPanel, "Center"); JPanel bottomPanel = getBottomPanel(); bottomPanel.setLayout(new BoxLayout(bottomPanel, 1)); fc.add(bottomPanel, "South"); JPanel fileNamePanel = new JPanel(); fileNamePanel.setLayout(new BoxLayout(fileNamePanel, 2)); bottomPanel.add(fileNamePanel); bottomPanel.add(Box.createRigidArea(vstrut5)); AlignedLabel fileNameLabel = new AlignedLabel(this, this.fileNameLabelText); fileNameLabel.setDisplayedMnemonic(this.fileNameLabelMnemonic); fileNamePanel.add(fileNameLabel); this.fileNameTextField = new JTextField(this) { private final FileChooserBasicUI this$0; public Dimension getMaximumSize() { return new Dimension(32767, (getPreferredSize()).height); } }
/*      */       ; fileNamePanel.add(this.fileNameTextField); fileNameLabel.setLabelFor(this.fileNameTextField); this.fileNameTextField.addFocusListener(new FocusAdapter(this) {
/*      */           private final FileChooserBasicUI this$0; public void focusGained(FocusEvent e) { if (!this.this$0.getFileChooser().isMultiSelectionEnabled()) this.this$0.listSelectionModel.clearSelection();  }
/*  206 */         }); if (fc.isMultiSelectionEnabled()) { setFileName(fileNameString(fc.getSelectedFiles())); } else { setFileName(fileNameString(fc.getSelectedFile())); }  JPanel filesOfTypePanel = new JPanel(); filesOfTypePanel.setLayout(new BoxLayout(filesOfTypePanel, 2)); bottomPanel.add(filesOfTypePanel); AlignedLabel filesOfTypeLabel = new AlignedLabel(this, this.filesOfTypeLabelText); filesOfTypeLabel.setDisplayedMnemonic(this.filesOfTypeLabelMnemonic); filesOfTypePanel.add(filesOfTypeLabel); this.filterComboBoxModel = createFilterComboBoxModel(); fc.addPropertyChangeListener(this.filterComboBoxModel); this.filterComboBox = new JComboBox(this.filterComboBoxModel); filesOfTypeLabel.setLabelFor(this.filterComboBox); this.filterComboBox.setRenderer(createFilterComboBoxRenderer()); filesOfTypePanel.add(this.filterComboBox); getButtonPanel().setLayout(new ButtonAreaLayout(null)); this.approveButton = new JButton(getApproveButtonText(fc)); this.approveButton.addActionListener(getApproveSelectionAction()); this.approveButton.setToolTipText(getApproveButtonToolTipText(fc)); getButtonPanel().add(this.approveButton); this.cancelButton = new JButton(this.cancelButtonText); this.cancelButton.setToolTipText(this.cancelButtonToolTipText); this.cancelButton.addActionListener(getCancelSelectionAction()); getButtonPanel().add(this.cancelButton); if (fc.getControlButtonsAreShown()) addControlButtons();  groupLabels(new AlignedLabel[] { fileNameLabel, filesOfTypeLabel }); } protected JPanel getButtonPanel() { if (this.buttonPanel == null) this.buttonPanel = new JPanel();  return this.buttonPanel; } protected JPanel getBottomPanel() { if (this.bottomPanel == null) this.bottomPanel = new JPanel();  return this.bottomPanel; } protected void installStrings(JFileChooser fc) { super.installStrings(fc); Locale l = fc.getLocale(); this.lookInLabelMnemonic = UIManager.getInt("FileChooser.lookInLabelMnemonic"); this.lookInLabelText = UIManager.getString("FileChooser.lookInLabelText", l); this.saveInLabelText = UIManager.getString("FileChooser.saveInLabelText", l); this.fileNameLabelMnemonic = UIManager.getInt("FileChooser.fileNameLabelMnemonic"); this.fileNameLabelText = UIManager.getString("FileChooser.fileNameLabelText", l); this.filesOfTypeLabelMnemonic = UIManager.getInt("FileChooser.filesOfTypeLabelMnemonic"); this.filesOfTypeLabelText = UIManager.getString("FileChooser.filesOfTypeLabelText", l); this.upFolderToolTipText = UIManager.getString("FileChooser.upFolderToolTipText", l); this.upFolderAccessibleName = UIManager.getString("FileChooser.upFolderAccessibleName", l); this.homeFolderToolTipText = UIManager.getString("FileChooser.homeFolderToolTipText", l); this.homeFolderAccessibleName = UIManager.getString("FileChooser.homeFolderAccessibleName", l); this.newFolderToolTipText = UIManager.getString("FileChooser.newFolderToolTipText", l); this.newFolderAccessibleName = UIManager.getString("FileChooser.newFolderAccessibleName", l); this.listViewButtonToolTipText = UIManager.getString("FileChooser.listViewButtonToolTipText", l); this.listViewButtonAccessibleName = UIManager.getString("FileChooser.listViewButtonAccessibleName", l); this.detailsViewButtonToolTipText = UIManager.getString("FileChooser.detailsViewButtonToolTipText", l); this.detailsViewButtonAccessibleName = UIManager.getString("FileChooser.detailsViewButtonAccessibleName", l); this.fileNameHeaderText = UIManager.getString("FileChooser.fileNameHeaderText", l); this.fileSizeHeaderText = UIManager.getString("FileChooser.fileSizeHeaderText", l); this.fileTypeHeaderText = UIManager.getString("FileChooser.fileTypeHeaderText", l); this.fileDateHeaderText = UIManager.getString("FileChooser.fileDateHeaderText", l); this.fileAttrHeaderText = UIManager.getString("FileChooser.fileAttrHeaderText", l); } public static ComponentUI createUI(JComponent c) { return new FileChooserBasicUI((JFileChooser)c); } protected void installListeners(JFileChooser fc) { super.installListeners(fc); ActionMap actionMap = getActionMap(); SwingUtilities.replaceUIActionMap(fc, actionMap); } protected ActionMap getActionMap() { return createActionMap(); } protected ActionMap createActionMap() { AbstractAction escAction = new AbstractAction(this) { private final FileChooserBasicUI this$0; public void actionPerformed(ActionEvent e) { if (this.this$0.editFile != null) { this.this$0.cancelEdit(); } else { this.this$0.getFileChooser().cancelSelection(); }  } public boolean isEnabled() { return this.this$0.getFileChooser().isEnabled(); } }
/*      */       ; ActionMap map = new ActionMapUIResource(); map.put("approveSelection", getApproveSelectionAction()); map.put("cancelSelection", escAction); map.put("Go Up", getChangeToParentDirectoryAction()); return map; } protected JPanel createList(JFileChooser fc) { JPanel p = new JPanel(new BorderLayout()); JFileChooser fileChooser = fc; this.list = new JList(this, fileChooser) { private final JFileChooser val$fileChooser; private final FileChooserBasicUI this$0; public int getNextMatch(String prefix, int startIndex, Position.Bias bias) { ListModel model = getModel(); int max = model.getSize(); if (prefix == null || startIndex < 0 || startIndex >= max) throw new IllegalArgumentException();  boolean backwards = (bias == Position.Bias.Backward); int i; for (i = startIndex; backwards ? (i >= 0) : (i < max); i += (backwards ? -1 : 1)) { String filename = this.val$fileChooser.getName((File)model.getElementAt(i)); if (filename.regionMatches(true, 0, prefix, 0, prefix.length())) return i;  }  return -1; } }
/*      */       ; this.list.setCellRenderer(new FileRenderer(this)); this.list.setLayoutOrientation(1); this.list.setVisibleRowCount(-1); if (fc.isMultiSelectionEnabled()) { this.list.setSelectionMode(2); } else { this.list.setSelectionMode(0); }  this.list.setModel(getModel()); this.list.addListSelectionListener(createListSelectionListener(fc)); this.list.addMouseListener(createDoubleClickListener(fc, this.list)); this.list.addMouseListener(createSingleClickListener(fc, this.list)); getModel().addListDataListener(new ListDataListener(this) { private final FileChooserBasicUI this$0; public void contentsChanged(ListDataEvent e) { new FileChooserBasicUI.DelayedSelectionUpdater(this.this$0); } public void intervalAdded(ListDataEvent e) { new FileChooserBasicUI.DelayedSelectionUpdater(this.this$0); } public void intervalRemoved(ListDataEvent e) {} }
/*      */       ); JScrollPane scrollpane = new JScrollPane(this.list); p.add(scrollpane, "Center"); return p; } class DetailsTableModel extends AbstractTableModel implements ListDataListener {
/*  210 */     String[] columnNames; JFileChooser chooser; ListModel listModel; private final FileChooserBasicUI this$0; DetailsTableModel(FileChooserBasicUI this$0, JFileChooser fc) { this.this$0 = this$0; this.columnNames = new String[] { FileChooserBasicUI.access$1000(this.this$0), FileChooserBasicUI.access$1100(this.this$0), FileChooserBasicUI.access$1200(this.this$0), FileChooserBasicUI.access$1300(this.this$0), FileChooserBasicUI.access$1400(this.this$0) }; this.chooser = fc; this.listModel = this$0.getModel(); this.listModel.addListDataListener(this); } public int getRowCount() { return this.listModel.getSize(); } public int getColumnCount() { return 5; } public String getColumnName(int column) { return this.columnNames[column]; } public Class getColumnClass(int column) { switch (column) { case 0: return (FileChooserBasicUI.class$java$io$File == null) ? (FileChooserBasicUI.class$java$io$File = FileChooserBasicUI.class$("java.io.File")) : FileChooserBasicUI.class$java$io$File;case 3: return (FileChooserBasicUI.class$java$util$Date == null) ? (FileChooserBasicUI.class$java$util$Date = FileChooserBasicUI.class$("java.util.Date")) : FileChooserBasicUI.class$java$util$Date; }  return super.getColumnClass(column); } public Object getValueAt(int row, int col) { String attributes; long time, len; File f = (File)this.listModel.getElementAt(row); switch (col) { case 0: return f;case 1: if (!f.exists() || f.isDirectory()) return null;  len = f.length() / 1024L; if (len < 1024L) return ((len == 0L) ? 1L : len) + " KB";  len /= 1024L; if (len < 1024L) return len + " MB";  len /= 1024L; return len + " GB";case 2: if (!f.exists()) return null;  return this.chooser.getFileSystemView().getSystemTypeDescription(f);case 3: if (!f.exists() || this.chooser.getFileSystemView().isFileSystemRoot(f)) return null;  time = f.lastModified(); return (time == 0L) ? null : new Date(time);case 4: if (!f.exists() || this.chooser.getFileSystemView().isFileSystemRoot(f)) return null;  attributes = ""; if (!f.canWrite()) attributes = attributes + "R";  if (f.isHidden()) attributes = attributes + "H";  return attributes; }  return null; } public void setValueAt(Object value, int row, int col) { if (col == 0) { JFileChooser chooser = this.this$0.getFileChooser(); File f = (File)getValueAt(row, col); if (f != null) { String oldDisplayName = chooser.getName(f); String oldFileName = f.getName(); String newDisplayName = ((String)value).trim(); if (!newDisplayName.equals(oldDisplayName)) { String newFileName = newDisplayName; int i1 = oldFileName.length(); int i2 = oldDisplayName.length(); if (i1 > i2 && oldFileName.charAt(i2) == '.') newFileName = newDisplayName + oldFileName.substring(i2);  FileSystemView fsv = chooser.getFileSystemView(); File f2 = fsv.createFileObject(f.getParentFile(), newFileName); if (!f2.exists() && this.this$0.getModel().renameFile(f, f2) && fsv.isParent(chooser.getCurrentDirectory(), f2)) if (chooser.isMultiSelectionEnabled()) { chooser.setSelectedFiles(new File[] { f2 }); } else { chooser.setSelectedFile(f2); }   }  }  }  } public boolean isCellEditable(int row, int column) { return (column == 0); } public void contentsChanged(ListDataEvent e) { fireTableDataChanged(); } public void intervalAdded(ListDataEvent e) { fireTableDataChanged(); } public void intervalRemoved(ListDataEvent e) { fireTableDataChanged(); } } public FileChooserBasicUI(JFileChooser filechooser) { super(filechooser);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1032 */     this.lastIndex = -1;
/* 1033 */     this.editFile = null;
/* 1034 */     this.editX = 20;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1058 */     this.editCell = null; } class DetailsTableCellRenderer extends DefaultTableCellRenderer {
/*      */     JFileChooser chooser; DateFormat df; private final FileChooserBasicUI this$0; DetailsTableCellRenderer(FileChooserBasicUI this$0, JFileChooser chooser) { this.this$0 = this$0; this.chooser = chooser; this.df = DateFormat.getDateTimeInstance(3, 3, chooser.getLocale()); } public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) { if (column == 1 || column == 4) { setHorizontalAlignment(11); } else { setHorizontalAlignment(10); }  return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); } public void setValue(Object value) { setIcon(null); if (value instanceof File) { File file = (File)value; String fileName = this.chooser.getName(file); setText(fileName); Icon icon = this.chooser.getIcon(file); setIcon(icon); } else if (value instanceof Date) { setText((value == null) ? "" : this.df.format((Date)value)); } else { super.setValue(value); }  } } protected JPanel createDetailsView(JFileChooser fc) { JFileChooser chooser = fc; JPanel p = new JPanel(new BorderLayout()); DetailsTableModel detailsTableModel = new DetailsTableModel(this, chooser); this.detailsTable = new JTable(this, detailsTableModel, chooser) { private final JFileChooser val$chooser; private final FileChooserBasicUI this$0; protected boolean processKeyBinding(KeyStroke ks, KeyEvent e, int condition, boolean pressed) { if (e.getKeyCode() == 27 && getCellEditor() == null) { this.val$chooser.dispatchEvent(e); return true; }  return super.processKeyBinding(ks, e, condition, pressed); } }
/*      */       ; this.detailsTable.setComponentOrientation(chooser.getComponentOrientation()); this.detailsTable.setAutoResizeMode(0); this.detailsTable.setShowGrid(false); this.detailsTable.setSelectionModel(this.listSelectionModel); this.detailsTable.putClientProperty("JTable.autoStartsEdit", Boolean.FALSE); Font font = this.detailsTable.getFont(); this.detailsTable.setRowHeight(Math.max(font.getSize(), 19) + 3); TableColumnModel columnModel = this.detailsTable.getColumnModel(); TableColumn[] columns = new TableColumn[5]; for (int i = 0; i < 5; i++) { columns[i] = columnModel.getColumn(i); columns[i].setPreferredWidth(this.COLUMN_WIDTHS[i]); }  if (!System.getProperty("os.name").startsWith("Windows")) { columnModel.removeColumn(columns[2]); columnModel.removeColumn(columns[4]); }  TableCellRenderer cellRenderer = new DetailsTableCellRenderer(this, chooser); this.detailsTable.setDefaultRenderer(File.class, cellRenderer); this.detailsTable.setDefaultRenderer(Date.class, cellRenderer); this.detailsTable.setDefaultRenderer(Object.class, cellRenderer); JTextField tf = new JTextField(); tf.addFocusListener(this.editorFocusListener); columns[0].setCellEditor(new DefaultCellEditor(this, tf, tf, chooser) { private final JTextField val$tf; private final JFileChooser val$chooser; private final FileChooserBasicUI this$0; public boolean isCellEditable(EventObject e) { if (e instanceof MouseEvent) { MouseEvent me = (MouseEvent)e; int index = this.this$0.detailsTable.rowAtPoint(me.getPoint()); return (me.getClickCount() == 1 && this.this$0.detailsTable.isRowSelected(index)); }  return super.isCellEditable(e); } public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) { Component comp = super.getTableCellEditorComponent(table, value, isSelected, row, column); if (value instanceof File) { this.val$tf.setText(this.val$chooser.getName((File)value)); this.val$tf.requestFocus(); this.val$tf.selectAll(); }  return comp; } }); JList fakeList = new JList(this, detailsTableModel.listModel) { JTable table; private final FileChooserBasicUI this$0; public int locationToIndex(Point location) { return this.table.rowAtPoint(location); } public Rectangle getCellBounds(int index0, int index1) { Rectangle r0 = this.table.getCellRect(index0, 0, false); Rectangle r1 = this.table.getCellRect(index1, 0, false); return r0.union(r1); } public Object getSelectedValue() { return this.table.getValueAt(this.table.getSelectedRow(), 0); } public Component add(Component comp) { if (comp instanceof JTextField) return this.table.add(comp);  return super.add(comp); } public void repaint() { if (this.table != null) this.table.repaint();  } public TransferHandler getTransferHandler() { if (this.table != null) return this.table.getTransferHandler();  return super.getTransferHandler(); } public void setTransferHandler(TransferHandler newHandler) { if (this.table != null) { this.table.setTransferHandler(newHandler); } else { super.setTransferHandler(newHandler); }  } public boolean getDragEnabled() { if (this.table != null) return this.table.getDragEnabled();  return super.getDragEnabled(); } public void setDragEnabled(boolean b) { if (this.table != null) { this.table.setDragEnabled(b); } else { super.setDragEnabled(b); }  } }; fakeList.setSelectionModel(this.listSelectionModel); this.detailsTable.addMouseListener(createDoubleClickListener(chooser, fakeList)); JScrollPane scrollpane = new JScrollPane(this.detailsTable); scrollpane.setComponentOrientation(chooser.getComponentOrientation()); LookAndFeel.installColors(scrollpane.getViewport(), "Table.background", "Table.foreground"); scrollpane.addComponentListener(new ComponentAdapter(this) { private final FileChooserBasicUI this$0; public void componentResized(ComponentEvent e) { JScrollPane sp = (JScrollPane)e.getComponent(); this.this$0.fixNameColumnWidth((sp.getViewport().getSize()).width); sp.removeComponentListener(this); } }); p.add(scrollpane, "Center"); return p; } private void fixNameColumnWidth(int viewWidth) { TableColumn nameCol = this.detailsTable.getColumnModel().getColumn(0); int tableWidth = (this.detailsTable.getPreferredSize()).width; if (tableWidth < viewWidth) nameCol.setPreferredWidth(nameCol.getPreferredWidth() + viewWidth - tableWidth);  } private class DelayedSelectionUpdater implements Runnable {
/* 1061 */     private final FileChooserBasicUI this$0; DelayedSelectionUpdater(FileChooserBasicUI this$0) { this.this$0 = this$0; SwingUtilities.invokeLater(this); } public void run() { this.this$0.setFileSelected(); } } public ListSelectionListener createListSelectionListener(JFileChooser fc) { return new BasicFileChooserUI.SelectionListener(this) { private final FileChooserBasicUI this$0; public void valueChanged(ListSelectionEvent e) { if (!e.getValueIsAdjusting()) { JFileChooser chooser = this.this$0.getFileChooser(); FileSystemView fsv = chooser.getFileSystemView(); JList list = (JList)e.getSource(); if (chooser.isMultiSelectionEnabled()) { File[] files = null; Object[] objects = list.getSelectedValues(); if (objects != null) { chooser; if (objects.length == 1 && ((File)objects[0]).isDirectory() && chooser.isTraversable((File)objects[0]) && (chooser.getFileSelectionMode() == 0 || !fsv.isFileSystem((File)objects[0]))) { this.this$0.setDirectorySelected(true); this.this$0.setDirectory((File)objects[0]); } else { files = new File[objects.length]; int j = 0; for (int i = 0; i < objects.length; i++) { File f = (File)objects[i]; if ((chooser.isFileSelectionEnabled() && f.isFile()) || (chooser.isDirectorySelectionEnabled() && fsv.isFileSystem(f) && f.isDirectory())) files[j++] = f;  }  if (j == 0) { files = null; } else if (j < objects.length) { File[] tmpFiles = new File[j]; System.arraycopy(files, 0, tmpFiles, 0, j); files = tmpFiles; }  this.this$0.setDirectorySelected(false); }  }  chooser.setSelectedFiles(files); } else { File file = (File)list.getSelectedValue(); chooser; if (file != null && file.isDirectory() && chooser.isTraversable(file) && (chooser.getFileSelectionMode() == 0 || !fsv.isFileSystem(file))) { this.this$0.setDirectorySelected(true); this.this$0.setDirectory(file); chooser.setSelectedFile(null); } else { this.this$0.setDirectorySelected(false); if (file != null) chooser.setSelectedFile(file);  }  }  }  } }; } private MouseListener createSingleClickListener(JFileChooser fc, JList list) { return new SingleClickListener(this, list); } private int getEditIndex() { return this.lastIndex; } private void setEditIndex(int i) { this.lastIndex = i; } private void resetEditIndex() { this.lastIndex = -1; } private void cancelEdit() { if (this.editFile != null) { this.editFile = null; this.list.remove(this.editCell); this.centerPanel.repaint(); } else if (this.detailsTable != null && this.detailsTable.isEditing()) { this.detailsTable.getCellEditor().cancelCellEditing(); }  } private void editFileName(int index) { ensureIndexIsVisible(index);
/* 1062 */     if (this.listViewPanel.isVisible()) {
/* 1063 */       this.editFile = (File)getModel().getElementAt(index);
/* 1064 */       Rectangle r = this.list.getCellBounds(index, index);
/* 1065 */       if (this.editCell == null) {
/* 1066 */         this.editCell = new JTextField();
/* 1067 */         this.editCell.addActionListener(new EditActionListener(this));
/* 1068 */         this.editCell.addFocusListener(this.editorFocusListener);
/* 1069 */         this.editCell.setNextFocusableComponent(this.list);
/*      */       } 
/* 1071 */       this.list.add(this.editCell);
/* 1072 */       this.editCell.setText(getFileChooser().getName(this.editFile));
/* 1073 */       if (this.list.getComponentOrientation().isLeftToRight()) {
/* 1074 */         this.editCell.setBounds(this.editX + r.x, r.y, r.width - this.editX, r.height);
/*      */       } else {
/* 1076 */         this.editCell.setBounds(r.x, r.y, r.width - this.editX, r.height);
/*      */       } 
/*      */       
/* 1079 */       this.editCell.requestFocus();
/* 1080 */       this.editCell.selectAll();
/* 1081 */     } else if (this.detailsViewPanel.isVisible()) {
/* 1082 */       this.detailsTable.editCellAt(index, 0);
/*      */     }  }
/*      */   
/*      */   protected class SingleClickListener extends MouseAdapter { JList list;
/*      */     private final FileChooserBasicUI this$0;
/*      */     
/*      */     public SingleClickListener(FileChooserBasicUI this$0, JList list) {
/* 1089 */       this.this$0 = this$0;
/* 1090 */       this.list = list;
/*      */     }
/*      */     
/*      */     public void mouseClicked(MouseEvent e) {
/* 1094 */       if (SwingUtilities.isLeftMouseButton(e))
/* 1095 */         if (e.getClickCount() == 1) {
/* 1096 */           JFileChooser fc = this.this$0.getFileChooser();
/* 1097 */           int index = this.list.locationToIndex(e.getPoint());
/* 1098 */           if ((!fc.isMultiSelectionEnabled() || fc.getSelectedFiles().length <= 1) && index >= 0 && this.list.isSelectedIndex(index) && this.this$0.getEditIndex() == index && this.this$0.editFile == null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1104 */             this.this$0.editFileName(index);
/*      */           }
/* 1106 */           else if (index >= 0) {
/* 1107 */             this.this$0.setEditIndex(index);
/*      */           } else {
/* 1109 */             this.this$0.resetEditIndex();
/*      */           }
/*      */         
/*      */         }
/*      */         else {
/*      */           
/* 1115 */           this.this$0.resetEditIndex();
/*      */         }  
/*      */     } }
/*      */   
/*      */   class EditActionListener implements ActionListener { private final FileChooserBasicUI this$0;
/*      */     
/* 1121 */     EditActionListener(FileChooserBasicUI this$0) { this.this$0 = this$0; }
/*      */     
/* 1123 */     public void actionPerformed(ActionEvent e) { this.this$0.applyEdit(); } }
/*      */ 
/*      */ 
/*      */   
/*      */   private void applyEdit() {
/* 1128 */     if (this.editFile != null && this.editFile.exists()) {
/* 1129 */       JFileChooser chooser = getFileChooser();
/* 1130 */       String oldDisplayName = chooser.getName(this.editFile);
/* 1131 */       String oldFileName = this.editFile.getName();
/* 1132 */       String newDisplayName = this.editCell.getText().trim();
/*      */ 
/*      */       
/* 1135 */       if (!newDisplayName.equals(oldDisplayName)) {
/* 1136 */         String newFileName = newDisplayName;
/*      */         
/* 1138 */         int i1 = oldFileName.length();
/* 1139 */         int i2 = oldDisplayName.length();
/* 1140 */         if (i1 > i2 && oldFileName.charAt(i2) == '.') {
/* 1141 */           newFileName = newDisplayName + oldFileName.substring(i2);
/*      */         }
/*      */ 
/*      */         
/* 1145 */         FileSystemView fsv = chooser.getFileSystemView();
/* 1146 */         File f2 = fsv.createFileObject(this.editFile.getParentFile(), newFileName);
/* 1147 */         if (!f2.exists() && getModel().renameFile(this.editFile, f2) && 
/* 1148 */           fsv.isParent(chooser.getCurrentDirectory(), f2)) {
/* 1149 */           if (chooser.isMultiSelectionEnabled()) {
/* 1150 */             chooser.setSelectedFiles(new File[] { f2 });
/*      */           } else {
/* 1152 */             chooser.setSelectedFile(f2);
/*      */           } 
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1163 */     if (this.detailsTable != null && this.detailsTable.isEditing()) {
/* 1164 */       this.detailsTable.getCellEditor().stopCellEditing();
/*      */     }
/* 1166 */     cancelEdit();
/*      */   }
/*      */   protected class FileRenderer extends DefaultListCellRenderer { private final FileChooserBasicUI this$0;
/* 1169 */     protected FileRenderer(FileChooserBasicUI this$0) { this.this$0 = this$0; }
/*      */ 
/*      */     
/*      */     public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
/* 1173 */       super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
/* 1174 */       File file = (File)value;
/* 1175 */       String fileName = this.this$0.getFileChooser().getName(file);
/* 1176 */       setText(fileName);
/*      */       
/* 1178 */       Icon icon = this.this$0.getFileChooser().getIcon(file);
/* 1179 */       setIcon(icon);
/*      */       
/* 1181 */       if (isSelected)
/*      */       {
/* 1183 */         this.this$0.editX = icon.getIconWidth() + 4;
/*      */       }
/*      */       
/* 1186 */       return this;
/*      */     } }
/*      */ 
/*      */ 
/*      */   
/*      */   public void uninstallUI(JComponent c) {
/* 1192 */     c.removePropertyChangeListener(this.filterComboBoxModel);
/* 1193 */     this.cancelButton.removeActionListener(getCancelSelectionAction());
/* 1194 */     this.approveButton.removeActionListener(getApproveSelectionAction());
/* 1195 */     this.fileNameTextField.removeActionListener(getApproveSelectionAction());
/*      */     
/* 1197 */     super.uninstallUI(c);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Dimension getPreferredSize(JComponent c) {
/* 1213 */     int prefWidth = PREF_SIZE.width;
/* 1214 */     Dimension d = c.getLayout().preferredLayoutSize(c);
/* 1215 */     if (d != null) {
/* 1216 */       return new Dimension((d.width < prefWidth) ? prefWidth : d.width, (d.height < PREF_SIZE.height) ? PREF_SIZE.height : d.height);
/*      */     }
/* 1218 */     return new Dimension(prefWidth, PREF_SIZE.height);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1230 */   public Dimension getMinimumSize(JComponent c) { return MIN_SIZE; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1241 */   public Dimension getMaximumSize(JComponent c) { return new Dimension(2147483647, 2147483647); }
/*      */ 
/*      */   
/*      */   void setFileSelected() {
/* 1245 */     if (getFileChooser().isMultiSelectionEnabled() && !isDirectorySelected()) {
/* 1246 */       File[] files = getFileChooser().getSelectedFiles();
/* 1247 */       Object[] selectedObjects = this.list.getSelectedValues();
/*      */ 
/*      */       
/* 1250 */       for (int j = 0; j < selectedObjects.length; j++) {
/* 1251 */         boolean found = false;
/* 1252 */         for (int i = 0; i < files.length; i++) {
/* 1253 */           if (files[i].equals(selectedObjects[j])) {
/* 1254 */             found = true;
/*      */             break;
/*      */           } 
/*      */         } 
/* 1258 */         if (!found) {
/* 1259 */           int index = getModel().indexOf(selectedObjects[j]);
/* 1260 */           if (index >= 0) {
/* 1261 */             this.listSelectionModel.removeSelectionInterval(index, index);
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/* 1266 */       for (int i = 0; i < files.length; i++) {
/* 1267 */         boolean found = false;
/* 1268 */         for (int j = 0; j < selectedObjects.length; j++) {
/* 1269 */           if (files[i].equals(selectedObjects[j])) {
/* 1270 */             found = true;
/*      */             break;
/*      */           } 
/*      */         } 
/* 1274 */         if (!found) {
/* 1275 */           int index = getModel().indexOf(files[i]);
/* 1276 */           if (index >= 0) {
/* 1277 */             this.listSelectionModel.addSelectionInterval(index, index);
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } else {
/* 1282 */       JFileChooser chooser = getFileChooser();
/* 1283 */       File f = null;
/* 1284 */       if (isDirectorySelected()) {
/* 1285 */         f = getDirectory();
/*      */       } else {
/* 1287 */         f = chooser.getSelectedFile();
/*      */       } 
/*      */       int i;
/* 1290 */       if (f != null && (i = getModel().indexOf(f)) >= 0) {
/* 1291 */         this.listSelectionModel.setSelectionInterval(i, i);
/* 1292 */         ensureIndexIsVisible(i);
/*      */       } else {
/* 1294 */         this.listSelectionModel.clearSelection();
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private String fileNameString(File file) {
/* 1300 */     if (file == null) {
/* 1301 */       return null;
/*      */     }
/* 1303 */     JFileChooser fc = getFileChooser();
/* 1304 */     if (fc.isDirectorySelectionEnabled() && !fc.isFileSelectionEnabled()) {
/* 1305 */       return file.getPath();
/*      */     }
/* 1307 */     return file.getName();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private String fileNameString(File[] files) {
/* 1313 */     StringBuffer buf = new StringBuffer();
/* 1314 */     for (int i = 0; files != null && i < files.length; i++) {
/* 1315 */       if (i > 0) {
/* 1316 */         buf.append(" ");
/*      */       }
/* 1318 */       if (files.length > 1) {
/* 1319 */         buf.append("\"");
/*      */       }
/* 1321 */       buf.append(fileNameString(files[i]));
/* 1322 */       if (files.length > 1) {
/* 1323 */         buf.append("\"");
/*      */       }
/*      */     } 
/* 1326 */     return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void doSelectedFileChanged(PropertyChangeEvent e) {
/* 1332 */     applyEdit();
/* 1333 */     File f = (File)e.getNewValue();
/* 1334 */     JFileChooser fc = getFileChooser();
/* 1335 */     if (f != null && ((fc.isFileSelectionEnabled() && !f.isDirectory()) || (f.isDirectory() && fc.isDirectorySelectionEnabled()))) {
/*      */       
/* 1337 */       setFileName(fileNameString(f));
/* 1338 */       setFileSelected();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void doSelectedFilesChanged(PropertyChangeEvent e) {
/* 1343 */     applyEdit();
/* 1344 */     File[] files = (File[])e.getNewValue();
/* 1345 */     JFileChooser fc = getFileChooser();
/* 1346 */     if (files != null && files.length > 0 && (files.length > 1 || fc.isDirectorySelectionEnabled() || !files[0].isDirectory())) {
/* 1347 */       setFileName(fileNameString(files));
/* 1348 */       setFileSelected();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void doDirectoryChanged(PropertyChangeEvent e) {
/* 1353 */     JFileChooser fc = getFileChooser();
/* 1354 */     FileSystemView fsv = fc.getFileSystemView();
/*      */     
/* 1356 */     applyEdit();
/* 1357 */     resetEditIndex();
/* 1358 */     clearIconCache();
/* 1359 */     this.listSelectionModel.clearSelection();
/* 1360 */     ensureIndexIsVisible(0);
/* 1361 */     File currentDirectory = fc.getCurrentDirectory();
/* 1362 */     if (currentDirectory != null) {
/* 1363 */       this.directoryComboBoxModel.addItem(currentDirectory);
/* 1364 */       getNewFolderAction().setEnabled(currentDirectory.canWrite());
/* 1365 */       getChangeToParentDirectoryAction().setEnabled(!fsv.isRoot(currentDirectory));
/*      */       
/* 1367 */       if (fc.isDirectorySelectionEnabled() && !fc.isFileSelectionEnabled()) {
/* 1368 */         if (fsv.isFileSystem(currentDirectory)) {
/* 1369 */           setFileName(currentDirectory.getPath());
/*      */         } else {
/* 1371 */           setFileName(null);
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   private void doFilterChanged(PropertyChangeEvent e) {
/* 1378 */     applyEdit();
/* 1379 */     resetEditIndex();
/* 1380 */     clearIconCache();
/* 1381 */     this.listSelectionModel.clearSelection();
/*      */   }
/*      */   
/*      */   private void doFileSelectionModeChanged(PropertyChangeEvent e) {
/* 1385 */     applyEdit();
/* 1386 */     resetEditIndex();
/* 1387 */     clearIconCache();
/* 1388 */     this.listSelectionModel.clearSelection();
/*      */     
/* 1390 */     JFileChooser fc = getFileChooser();
/* 1391 */     File currentDirectory = fc.getCurrentDirectory();
/* 1392 */     if (currentDirectory != null && fc.isDirectorySelectionEnabled() && !fc.isFileSelectionEnabled() && fc.getFileSystemView().isFileSystem(currentDirectory)) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1397 */       setFileName(currentDirectory.getPath());
/*      */     } else {
/* 1399 */       setFileName(null);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void doMultiSelectionChanged(PropertyChangeEvent e) {
/* 1404 */     if (getFileChooser().isMultiSelectionEnabled()) {
/* 1405 */       this.listSelectionModel.setSelectionMode(2);
/*      */     } else {
/* 1407 */       this.listSelectionModel.setSelectionMode(0);
/* 1408 */       this.listSelectionModel.clearSelection();
/* 1409 */       getFileChooser().setSelectedFiles(null);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void doAccessoryChanged(PropertyChangeEvent e) {
/* 1414 */     if (getAccessoryPanel() != null) {
/* 1415 */       if (e.getOldValue() != null) {
/* 1416 */         getAccessoryPanel().remove((JComponent)e.getOldValue());
/*      */       }
/* 1418 */       JComponent accessory = (JComponent)e.getNewValue();
/* 1419 */       if (accessory != null) {
/* 1420 */         getAccessoryPanel().add(accessory, "Center");
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   private void doApproveButtonTextChanged(PropertyChangeEvent e) {
/* 1426 */     JFileChooser chooser = getFileChooser();
/* 1427 */     this.approveButton.setText(getApproveButtonText(chooser));
/* 1428 */     this.approveButton.setToolTipText(getApproveButtonToolTipText(chooser));
/*      */   }
/*      */   
/*      */   private void doDialogTypeChanged(PropertyChangeEvent e) {
/* 1432 */     JFileChooser chooser = getFileChooser();
/* 1433 */     this.approveButton.setText(getApproveButtonText(chooser));
/* 1434 */     this.approveButton.setToolTipText(getApproveButtonToolTipText(chooser));
/* 1435 */     if (chooser.getDialogType() == 1) {
/* 1436 */       this.lookInLabel.setText(this.saveInLabelText);
/*      */     } else {
/* 1438 */       this.lookInLabel.setText(this.lookInLabelText);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void doApproveButtonMnemonicChanged(PropertyChangeEvent e) {}
/*      */ 
/*      */   
/*      */   private void doControlButtonsChanged(PropertyChangeEvent e) {
/* 1447 */     if (getFileChooser().getControlButtonsAreShown()) {
/* 1448 */       addControlButtons();
/*      */     } else {
/* 1450 */       removeControlButtons();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PropertyChangeListener createPropertyChangeListener(JFileChooser fc) {
/* 1459 */     return new PropertyChangeListener(this) { private final FileChooserBasicUI this$0;
/*      */         public void propertyChange(PropertyChangeEvent e) {
/* 1461 */           String s = e.getPropertyName();
/* 1462 */           if (s.equals("SelectedFileChangedProperty")) {
/* 1463 */             this.this$0.doSelectedFileChanged(e);
/* 1464 */           } else if (s.equals("SelectedFilesChangedProperty")) {
/* 1465 */             this.this$0.doSelectedFilesChanged(e);
/* 1466 */           } else if (s.equals("directoryChanged")) {
/* 1467 */             this.this$0.doDirectoryChanged(e);
/* 1468 */           } else if (s.equals("fileFilterChanged")) {
/* 1469 */             this.this$0.doFilterChanged(e);
/* 1470 */           } else if (s.equals("fileSelectionChanged")) {
/* 1471 */             this.this$0.doFileSelectionModeChanged(e);
/* 1472 */           } else if (s.equals("MultiSelectionEnabledChangedProperty")) {
/* 1473 */             this.this$0.doMultiSelectionChanged(e);
/* 1474 */           } else if (s.equals("AccessoryChangedProperty")) {
/* 1475 */             this.this$0.doAccessoryChanged(e);
/* 1476 */           } else if (s.equals("ApproveButtonTextChangedProperty") || s.equals("ApproveButtonToolTipTextChangedProperty")) {
/*      */ 
/*      */             
/* 1479 */             this.this$0.doApproveButtonTextChanged(e);
/* 1480 */           } else if (s.equals("DialogTypeChangedProperty")) {
/* 1481 */             this.this$0.doDialogTypeChanged(e);
/* 1482 */           } else if (s.equals("ApproveButtonMnemonicChangedProperty")) {
/* 1483 */             this.this$0.doApproveButtonMnemonicChanged(e);
/* 1484 */           } else if (s.equals("ControlButtonsAreShownChangedProperty")) {
/* 1485 */             this.this$0.doControlButtonsChanged(e);
/* 1486 */           } else if (s.equals("CancelSelection")) {
/* 1487 */             this.this$0.applyEdit();
/* 1488 */           } else if (s.equals("componentOrientation")) {
/* 1489 */             ComponentOrientation o = (ComponentOrientation)e.getNewValue();
/* 1490 */             JFileChooser cc = (JFileChooser)e.getSource();
/* 1491 */             if (o != (ComponentOrientation)e.getOldValue()) {
/* 1492 */               cc.applyComponentOrientation(o);
/*      */             }
/* 1494 */             if (this.this$0.detailsTable != null) {
/* 1495 */               this.this$0.detailsTable.setComponentOrientation(o);
/* 1496 */               this.this$0.detailsTable.getParent().getParent().setComponentOrientation(o);
/*      */             } 
/* 1498 */           } else if (s.equals("ancestor") && 
/* 1499 */             e.getOldValue() == null && e.getNewValue() != null) {
/*      */             
/* 1501 */             this.this$0.fileNameTextField.selectAll();
/* 1502 */             this.this$0.fileNameTextField.requestFocus();
/*      */           } 
/*      */         } }
/*      */       ;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/* 1510 */   protected void removeControlButtons() { getBottomPanel().remove(getButtonPanel()); }
/*      */ 
/*      */ 
/*      */   
/* 1514 */   protected void addControlButtons() { getBottomPanel().add(getButtonPanel()); }
/*      */ 
/*      */   
/*      */   private void ensureIndexIsVisible(int i) {
/* 1518 */     if (i >= 0) {
/* 1519 */       this.list.ensureIndexIsVisible(i);
/* 1520 */       if (this.detailsTable != null) {
/* 1521 */         this.detailsTable.scrollRectToVisible(this.detailsTable.getCellRect(i, 0, true));
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/* 1527 */   public void ensureFileIsVisible(JFileChooser fc, File f) { ensureIndexIsVisible(getModel().indexOf(f)); }
/*      */ 
/*      */ 
/*      */   
/* 1531 */   public void rescanCurrentDirectory(JFileChooser fc) { getModel().validateFileCache(); }
/*      */ 
/*      */   
/*      */   public String getFileName() {
/* 1535 */     if (this.fileNameTextField != null) {
/* 1536 */       return this.fileNameTextField.getText();
/*      */     }
/* 1538 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setFileName(String filename) {
/* 1543 */     if (this.fileNameTextField != null) {
/* 1544 */       this.fileNameTextField.setText(filename);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setDirectorySelected(boolean directorySelected) {
/* 1556 */     super.setDirectorySelected(directorySelected);
/* 1557 */     JFileChooser chooser = getFileChooser();
/* 1558 */     if (directorySelected) {
/* 1559 */       this.approveButton.setText(this.directoryOpenButtonText);
/* 1560 */       this.approveButton.setToolTipText(this.directoryOpenButtonToolTipText);
/*      */     } else {
/* 1562 */       this.approveButton.setText(getApproveButtonText(chooser));
/* 1563 */       this.approveButton.setToolTipText(getApproveButtonToolTipText(chooser));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/* 1569 */   public String getDirectoryName() { return null; }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDirectoryName(String dirname) {}
/*      */ 
/*      */ 
/*      */   
/* 1577 */   protected DirectoryComboBoxRenderer createDirectoryComboBoxRenderer(JFileChooser fc) { return new DirectoryComboBoxRenderer(this); }
/*      */   
/*      */   class DirectoryComboBoxRenderer extends DefaultListCellRenderer { FileChooserBasicUI.IndentIcon ii;
/*      */     private final FileChooserBasicUI this$0;
/*      */     
/*      */     DirectoryComboBoxRenderer(FileChooserBasicUI this$0) {
/* 1583 */       this.this$0 = this$0;
/* 1584 */       this.ii = new FileChooserBasicUI.IndentIcon(this.this$0);
/*      */     }
/*      */     public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
/* 1587 */       super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
/*      */       
/* 1589 */       if (value == null) {
/* 1590 */         setText("");
/* 1591 */         return this;
/*      */       } 
/* 1593 */       File directory = (File)value;
/* 1594 */       setText(this.this$0.getFileChooser().getName(directory));
/* 1595 */       Icon icon = this.this$0.getFileChooser().getIcon(directory);
/* 1596 */       this.ii.icon = icon;
/* 1597 */       this.ii.depth = this.this$0.directoryComboBoxModel.getDepth(index);
/* 1598 */       setIcon(this.ii);
/*      */       
/* 1600 */       return this;
/*      */     } }
/*      */   class IndentIcon implements Icon { Icon icon; int depth; private final FileChooserBasicUI this$0;
/*      */     
/*      */     IndentIcon(FileChooserBasicUI this$0) {
/* 1605 */       this.this$0 = this$0;
/*      */       
/* 1607 */       this.icon = null;
/* 1608 */       this.depth = 0;
/*      */     }
/*      */     public void paintIcon(Component c, Graphics g, int x, int y) {
/* 1611 */       if (c.getComponentOrientation().isLeftToRight()) {
/* 1612 */         this.icon.paintIcon(c, g, x + this.depth * 10, y);
/*      */       } else {
/* 1614 */         this.icon.paintIcon(c, g, x, y);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 1619 */     public int getIconWidth() { return this.icon.getIconWidth() + this.depth * 10; }
/*      */ 
/*      */ 
/*      */     
/* 1623 */     public int getIconHeight() { return this.icon.getIconHeight(); } }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1632 */   protected DirectoryComboBoxModel createDirectoryComboBoxModel(JFileChooser fc) { return new DirectoryComboBoxModel(this); }
/*      */   
/*      */   protected class DirectoryComboBoxModel
/*      */     extends AbstractListModel
/*      */     implements ComboBoxModel {
/*      */     Vector directories;
/*      */     int[] depths;
/*      */     File selectedDirectory;
/*      */     JFileChooser chooser;
/*      */     FileSystemView fsv;
/*      */     private final FileChooserBasicUI this$0;
/*      */     
/*      */     public DirectoryComboBoxModel(FileChooserBasicUI this$0) {
/* 1645 */       this.this$0 = this$0; this.directories = new Vector(); this.depths = null; this.selectedDirectory = null;
/*      */       this.chooser = this.this$0.getFileChooser();
/*      */       this.fsv = this.chooser.getFileSystemView();
/* 1648 */       File dir = this$0.getFileChooser().getCurrentDirectory();
/* 1649 */       if (dir != null) {
/* 1650 */         addItem(dir);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void addItem(File directory) {
/*      */       File[] baseFolders;
/* 1661 */       if (directory == null) {
/*      */         return;
/*      */       }
/*      */       
/* 1665 */       this.directories.clear();
/*      */ 
/*      */       
/* 1668 */       if (this.this$0.useShellFolder) {
/* 1669 */         baseFolders = (File[])ShellFolder.get("fileChooserComboBoxFolders");
/*      */       } else {
/* 1671 */         baseFolders = this.fsv.getRoots();
/*      */       } 
/* 1673 */       this.directories.addAll(Arrays.asList(baseFolders));
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1678 */       File canonical = null;
/*      */       try {
/* 1680 */         canonical = directory.getCanonicalFile();
/* 1681 */       } catch (IOException e) {
/*      */         
/* 1683 */         canonical = directory;
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/* 1688 */         File sf = ShellFolder.getShellFolder(canonical);
/* 1689 */         File f = sf;
/* 1690 */         Vector path = new Vector(10);
/*      */         do {
/* 1692 */           path.addElement(f);
/* 1693 */         } while ((f = f.getParentFile()) != null);
/*      */         
/* 1695 */         int pathCount = path.size();
/*      */         
/* 1697 */         for (int i = 0; i < pathCount; i++) {
/* 1698 */           f = (File)path.get(i);
/* 1699 */           if (this.directories.contains(f)) {
/* 1700 */             int topIndex = this.directories.indexOf(f);
/* 1701 */             for (int j = i - 1; j >= 0; j--) {
/* 1702 */               this.directories.insertElementAt(path.get(j), topIndex + i - j);
/*      */             }
/*      */             break;
/*      */           } 
/*      */         } 
/* 1707 */         calculateDepths();
/* 1708 */         setSelectedItem(sf);
/* 1709 */       } catch (FileNotFoundException ex) {
/* 1710 */         calculateDepths();
/*      */       } 
/*      */     }
/*      */     
/*      */     private void calculateDepths() {
/* 1715 */       this.depths = new int[this.directories.size()];
/* 1716 */       for (int i = 0; i < this.depths.length; i++) {
/* 1717 */         File dir = (File)this.directories.get(i);
/* 1718 */         File parent = dir.getParentFile();
/* 1719 */         this.depths[i] = 0;
/* 1720 */         if (parent != null) {
/* 1721 */           for (int j = i - 1; j >= 0; j--) {
/* 1722 */             if (parent.equals((File)this.directories.get(j))) {
/* 1723 */               this.depths[i] = this.depths[j] + 1;
/*      */               break;
/*      */             } 
/*      */           } 
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 1732 */     public int getDepth(int i) { return (this.depths != null && i >= 0 && i < this.depths.length) ? this.depths[i] : 0; }
/*      */ 
/*      */     
/*      */     public void setSelectedItem(Object selectedDirectory) {
/* 1736 */       this.selectedDirectory = (File)selectedDirectory;
/* 1737 */       fireContentsChanged(this, -1, -1);
/*      */     }
/*      */ 
/*      */     
/* 1741 */     public Object getSelectedItem() { return this.selectedDirectory; }
/*      */ 
/*      */ 
/*      */     
/* 1745 */     public int getSize() { return this.directories.size(); }
/*      */ 
/*      */ 
/*      */     
/* 1749 */     public Object getElementAt(int index) { return this.directories.elementAt(index); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1757 */   protected FilterComboBoxRenderer createFilterComboBoxRenderer() { return new FilterComboBoxRenderer(this); }
/*      */   
/*      */   public class FilterComboBoxRenderer
/*      */     extends DefaultListCellRenderer {
/*      */     private final FileChooserBasicUI this$0;
/*      */     
/* 1763 */     public FilterComboBoxRenderer(FileChooserBasicUI this$0) { this.this$0 = this$0; }
/*      */     
/*      */     public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
/* 1766 */       super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
/*      */       
/* 1768 */       if (value != null && value instanceof FileFilter) {
/* 1769 */         setText(((FileFilter)value).getDescription());
/*      */       }
/*      */       
/* 1772 */       return this;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1780 */   protected FilterComboBoxModel createFilterComboBoxModel() { return new FilterComboBoxModel(this); }
/*      */   
/*      */   protected class FilterComboBoxModel
/*      */     extends AbstractListModel implements ComboBoxModel, PropertyChangeListener {
/*      */     protected FileFilter[] filters;
/*      */     private final FileChooserBasicUI this$0;
/*      */     
/*      */     protected FilterComboBoxModel(FileChooserBasicUI this$0) {
/* 1788 */       this.this$0 = this$0;
/*      */       
/* 1790 */       this.filters = this$0.getFileChooser().getChoosableFileFilters();
/*      */     }
/*      */     
/*      */     public void propertyChange(PropertyChangeEvent e) {
/* 1794 */       String prop = e.getPropertyName();
/* 1795 */       if (prop == "ChoosableFileFilterChangedProperty") {
/* 1796 */         this.filters = (FileFilter[])e.getNewValue();
/* 1797 */         fireContentsChanged(this, -1, -1);
/* 1798 */       } else if (prop == "fileFilterChanged") {
/* 1799 */         fireContentsChanged(this, -1, -1);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void setSelectedItem(Object filter) {
/* 1804 */       if (filter != null) {
/* 1805 */         this.this$0.getFileChooser().setFileFilter((FileFilter)filter);
/* 1806 */         this.this$0.setFileName(null);
/* 1807 */         fireContentsChanged(this, -1, -1);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Object getSelectedItem() {
/* 1817 */       FileFilter currentFilter = this.this$0.getFileChooser().getFileFilter();
/* 1818 */       boolean found = false;
/* 1819 */       if (currentFilter != null) {
/* 1820 */         for (int i = 0; i < this.filters.length; i++) {
/* 1821 */           if (this.filters[i] == currentFilter) {
/* 1822 */             found = true;
/*      */           }
/*      */         } 
/* 1825 */         if (!found) {
/* 1826 */           this.this$0.getFileChooser().addChoosableFileFilter(currentFilter);
/*      */         }
/*      */       } 
/* 1829 */       return this.this$0.getFileChooser().getFileFilter();
/*      */     }
/*      */     
/*      */     public int getSize() {
/* 1833 */       if (this.filters != null) {
/* 1834 */         return this.filters.length;
/*      */       }
/* 1836 */       return 0;
/*      */     }
/*      */ 
/*      */     
/*      */     public Object getElementAt(int index) {
/* 1841 */       if (index > getSize() - 1)
/*      */       {
/* 1843 */         return this.this$0.getFileChooser().getFileFilter();
/*      */       }
/* 1845 */       if (this.filters != null) {
/* 1846 */         return this.filters[index];
/*      */       }
/* 1848 */       return null;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void valueChanged(ListSelectionEvent e) {
/* 1854 */     JFileChooser fc = getFileChooser();
/* 1855 */     File f = fc.getSelectedFile();
/* 1856 */     if (!e.getValueIsAdjusting() && f != null && !getFileChooser().isTraversable(f))
/* 1857 */       setFileName(fileNameString(f)); 
/*      */   }
/*      */   
/*      */   protected class DirectoryComboBoxAction
/*      */     extends AbstractAction
/*      */   {
/*      */     private final FileChooserBasicUI this$0;
/*      */     
/*      */     protected DirectoryComboBoxAction(FileChooserBasicUI this$0) {
/* 1866 */       super("DirectoryComboBoxAction");
/*      */       this.this$0 = this$0;
/*      */     }
/*      */     public void actionPerformed(ActionEvent e) {
/* 1870 */       File f = (File)this.this$0.directoryComboBox.getSelectedItem();
/* 1871 */       this.this$0.getFileChooser().setCurrentDirectory(f);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/* 1876 */   protected JButton getApproveButton(JFileChooser fc) { return this.approveButton; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static class ButtonAreaLayout
/*      */     implements LayoutManager
/*      */   {
/* 1886 */     private int hGap = 5;
/* 1887 */     private int topMargin = 17;
/*      */ 
/*      */     
/*      */     public void addLayoutComponent(String string, Component comp) {}
/*      */     
/*      */     public void layoutContainer(Container container) {
/* 1893 */       Component[] children = container.getComponents();
/*      */       
/* 1895 */       if (children != null && children.length > 0) {
/* 1896 */         int xOffset, numChildren = children.length;
/* 1897 */         Dimension[] sizes = new Dimension[numChildren];
/* 1898 */         Insets insets = container.getInsets();
/* 1899 */         int yLocation = insets.top + this.topMargin;
/* 1900 */         int maxWidth = 0;
/*      */         int xLocation;
/* 1902 */         for (xLocation = 0; xLocation < numChildren; xLocation++) {
/* 1903 */           sizes[xLocation] = children[xLocation].getPreferredSize();
/* 1904 */           maxWidth = Math.max(maxWidth, (sizes[xLocation]).width);
/*      */         } 
/*      */         
/* 1907 */         if (container.getComponentOrientation().isLeftToRight()) {
/* 1908 */           int xLocation = (container.getSize()).width - insets.left - maxWidth;
/* 1909 */           xOffset = this.hGap + maxWidth;
/*      */         } else {
/* 1911 */           xLocation = insets.left;
/* 1912 */           xOffset = -(this.hGap + maxWidth);
/*      */         } 
/* 1914 */         for (int counter = numChildren - 1; counter >= 0; counter--) {
/* 1915 */           children[counter].setBounds(xLocation, yLocation, maxWidth, (sizes[counter]).height);
/* 1916 */           xLocation -= xOffset;
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     public Dimension minimumLayoutSize(Container c) {
/* 1922 */       if (c != null) {
/* 1923 */         Component[] children = c.getComponents();
/*      */         
/* 1925 */         if (children != null && children.length > 0) {
/* 1926 */           int numChildren = children.length;
/* 1927 */           int height = 0;
/* 1928 */           Insets cInsets = c.getInsets();
/* 1929 */           int extraHeight = this.topMargin + cInsets.top + cInsets.bottom;
/* 1930 */           int extraWidth = cInsets.left + cInsets.right;
/* 1931 */           int maxWidth = 0;
/*      */           
/* 1933 */           for (int counter = 0; counter < numChildren; counter++) {
/* 1934 */             Dimension aSize = children[counter].getPreferredSize();
/* 1935 */             height = Math.max(height, aSize.height);
/* 1936 */             maxWidth = Math.max(maxWidth, aSize.width);
/*      */           } 
/* 1938 */           return new Dimension(extraWidth + numChildren * maxWidth + (numChildren - 1) * this.hGap, extraHeight + height);
/*      */         } 
/*      */       } 
/* 1941 */       return new Dimension(0, 0);
/*      */     }
/*      */ 
/*      */     
/* 1945 */     public Dimension preferredLayoutSize(Container c) { return minimumLayoutSize(c); }
/*      */     
/*      */     public void removeLayoutComponent(Component c) {}
/*      */     
/*      */     private ButtonAreaLayout() {}
/*      */   }
/*      */   
/*      */   private static void groupLabels(AlignedLabel[] group) {
/* 1953 */     for (int i = 0; i < group.length; i++)
/* 1954 */       (group[i]).group = group; 
/*      */   }
/*      */   
/*      */   private class AlignedLabel extends JLabel {
/*      */     private AlignedLabel[] group;
/*      */     private int maxWidth;
/*      */     private final FileChooserBasicUI this$0;
/*      */     
/*      */     AlignedLabel(FileChooserBasicUI this$0, String text) {
/* 1963 */       super(text); this.this$0 = this$0; this.maxWidth = 0;
/* 1964 */       setAlignmentX(0.0F);
/*      */     }
/*      */     
/*      */     public Dimension getPreferredSize() {
/* 1968 */       Dimension d = super.getPreferredSize();
/*      */       
/* 1970 */       return new Dimension(getMaxWidth() + 11, d.height);
/*      */     }
/*      */     
/*      */     private int getMaxWidth() {
/* 1974 */       if (this.maxWidth == 0 && this.group != null) {
/* 1975 */         int max = 0;
/* 1976 */         for (int i = 0; i < this.group.length; i++) {
/* 1977 */           max = Math.max(this.group[i].getSuperPreferredWidth(), max);
/*      */         }
/* 1979 */         for (int i = 0; i < this.group.length; i++) {
/* 1980 */           (this.group[i]).maxWidth = max;
/*      */         }
/*      */       } 
/* 1983 */       return this.maxWidth;
/*      */     }
/*      */ 
/*      */     
/* 1987 */     private int getSuperPreferredWidth() { return (super.getPreferredSize()).width; }
/*      */   }
/*      */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\FileChooserBasicUI.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */