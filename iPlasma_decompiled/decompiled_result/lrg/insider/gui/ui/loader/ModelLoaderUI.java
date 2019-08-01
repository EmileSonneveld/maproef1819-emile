/*     */ package classes.lrg.insider.gui.ui.loader;
/*     */ import com.intellij.uiDesigner.core.GridConstraints;
/*     */ import com.intellij.uiDesigner.core.GridLayoutManager;
/*     */ import com.intellij.uiDesigner.core.Spacer;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ActionEvent;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ import lrg.insider.gui.ui.loader.ModelLoaderUI;
/*     */ 
/*     */ public class ModelLoaderUI implements ActionListener {
/*     */   private JButton okButton;
/*     */   private JButton cancelButton;
/*     */   private JButton cacheButton;
/*     */   private JButton sourcesButton;
/*  21 */   static JDialog frame = new JDialog(); private JTextField sourcesPath; private JTextField cacheFile; private JPanel panel; private boolean accept;
/*  22 */   static ModelLoaderUI mlu = new ModelLoaderUI();
/*     */ 
/*     */   
/*     */   public static boolean show() {
/*  26 */     frame.setTitle("Model loader");
/*  27 */     frame.setContentPane(mlu.getTopComponent());
/*  28 */     frame.setModal(true);
/*  29 */     frame.pack();
/*  30 */     frame.setDefaultCloseOperation(2);
/*  31 */     frame.setLocation(150, 150);
/*  32 */     frame.setSize(350, 150);
/*  33 */     frame.setVisible(true);
/*     */     
/*  35 */     return mlu.isAccepted();
/*     */   }
/*     */ 
/*     */   
/*  39 */   public static String getSourcePath() { return mlu.sourcesPath.getText(); }
/*     */ 
/*     */   
/*     */   public static String getCachePath() {
/*  43 */     if (mlu.cacheFile.getText().equals(""))
/*  44 */       return null; 
/*  45 */     return mlu.cacheFile.getText();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ModelLoaderUI() {
/*     */     this.accept = false;
/* 105 */     $$$setupUI$$$();
/*     */     this.okButton.addActionListener(this);
/*     */     this.cancelButton.addActionListener(this);
/*     */     this.cacheButton.addActionListener(this);
/*     */     this.sourcesButton.addActionListener(this);
/*     */   }
/*     */   
/*     */   public Container getTopComponent() { return this.panel; }
/*     */   
/*     */   private void $$$setupUI$$$() {
/* 115 */     JPanel _1 = new JPanel();
/* 116 */     this.panel = _1;
/* 117 */     _1.setLayout(new GridLayoutManager(7, 4, new Insets(0, 0, 0, 0), -1, -1));
/*     */     
/* 119 */     JButton _2 = new JButton();
/* 120 */     this.sourcesButton = _2;
/* 121 */     _2.setToolTipText("Open a directory chooser");
/* 122 */     _2.setText("...");
/* 123 */     _1.add(_2, new GridConstraints(1, 3, 3, 1, 0, 1, 3, 0, null, new Dimension(20, 20), new Dimension(20, 20)));
/*     */     
/* 125 */     JLabel _3 = new JLabel();
/* 126 */     _3.setText("Cache file name");
/* 127 */     _1.add(_3, new GridConstraints(4, 1, 1, 1, 8, 0, 0, 0, null, null, null));
/*     */     
/* 129 */     JButton _4 = new JButton();
/* 130 */     this.cacheButton = _4;
/* 131 */     _4.setToolTipText("Open a file chooser");
/* 132 */     _4.setText("...");
/* 133 */     _1.add(_4, new GridConstraints(4, 3, 1, 1, 0, 1, 3, 0, null, new Dimension(20, 20), new Dimension(20, 20)));
/*     */     
/* 135 */     JTextField _5 = new JTextField();
/* 136 */     this.cacheFile = _5;
/* 137 */     _1.add(_5, new GridConstraints(4, 2, 1, 1, 8, 1, 6, 0, null, new Dimension(150, -1), null));
/*     */     
/* 139 */     JPanel _6 = new JPanel();
/* 140 */     _6.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
/* 141 */     _1.add(_6, new GridConstraints(1, 2, 3, 1, 0, 3, 3, 3, null, null, null));
/*     */     
/* 143 */     JTextField _7 = new JTextField();
/* 144 */     this.sourcesPath = _7;
/* 145 */     _6.add(_7, new GridConstraints(0, 0, 1, 1, 8, 1, 6, 0, null, new Dimension(150, -1), null));
/*     */     
/* 147 */     JLabel _8 = new JLabel();
/* 148 */     _8.setText("Source path");
/* 149 */     _1.add(_8, new GridConstraints(1, 1, 3, 1, 8, 0, 0, 0, null, null, null));
/*     */     
/* 151 */     JButton _9 = new JButton();
/* 152 */     this.okButton = _9;
/* 153 */     _9.setText("Ok");
/* 154 */     _1.add(_9, new GridConstraints(5, 1, 1, 1, 0, 1, 3, 0, null, null, null));
/*     */     
/* 156 */     JButton _10 = new JButton();
/* 157 */     this.cancelButton = _10;
/* 158 */     _10.setText("Cancel");
/* 159 */     _1.add(_10, new GridConstraints(5, 2, 1, 1, 0, 1, 3, 0, null, null, null));
/*     */     
/* 161 */     Spacer _11 = new Spacer();
/* 162 */     _1.add(_11, new GridConstraints(1, 0, 1, 1, 0, 1, 6, 1, new Dimension(15, 1), new Dimension(15, 1), new Dimension(15, 1)));
/*     */     
/* 164 */     Spacer _12 = new Spacer();
/* 165 */     _1.add(_12, new GridConstraints(6, 2, 1, 1, 0, 2, 1, 6, new Dimension(1, 10), new Dimension(1, 10), new Dimension(1, 10)));
/*     */     
/* 167 */     Spacer _13 = new Spacer();
/* 168 */     _1.add(_13, new GridConstraints(0, 1, 1, 1, 0, 2, 1, 6, null, new Dimension(-1, 10), new Dimension(-1, 10)));
/*     */   }
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/*     */     JFileChooser fc = new JFileChooser();
/*     */     if (e.getSource() == this.sourcesButton) {
/*     */       fc.setFileSelectionMode(1);
/*     */       fc.setFileFilter(new Object(this));
/*     */       if (fc.showOpenDialog(this.panel) == 0)
/*     */         this.sourcesPath.setText(fc.getSelectedFile().getPath()); 
/*     */     } 
/*     */     if (e.getSource() == this.cacheButton) {
/*     */       fc.setFileSelectionMode(2);
/*     */       if (fc.showOpenDialog(this.panel) == 0)
/*     */         this.cacheFile.setText(fc.getSelectedFile().getPath()); 
/*     */     } 
/*     */     if (e.getSource() == this.okButton) {
/*     */       this.accept = true;
/*     */       frame.setVisible(false);
/*     */       frame.dispose();
/*     */     } 
/*     */     if (e.getSource() == this.cancelButton) {
/*     */       this.accept = false;
/*     */       frame.setVisible(false);
/*     */       frame.dispose();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isAccepted() { return this.accept; }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\gu\\ui\loader\ModelLoaderUI.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */