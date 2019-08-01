/*     */ package classes.lrg.insider.gui.ui.utils;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.GridLayout;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.Box;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ import lrg.insider.gui.ui.utils.ToolsStarter;
/*     */ 
/*     */ public class ToolsStarter extends JDialog implements ActionListener {
/*     */   public static ArrayList kindOfButtons;
/*     */   JButton okButton;
/*     */   JButton cancelButton;
/*     */   JButton[] fileChoosers;
/*     */   ArrayList<String> parameterNames;
/*     */   
/*     */   public ToolsStarter(String toolName, ArrayList<String> parameterNames, ArrayList<String> parameterToolTips, ArrayList<String> parameterInitialValues) {
/*  18 */     this.validated = false;
/*     */ 
/*     */     
/*  21 */     this.toolName = toolName;
/*  22 */     this.parameterNames = parameterNames;
/*  23 */     this.parameterToolTips = parameterToolTips;
/*  24 */     this.parameterInitialValues = parameterInitialValues;
/*     */   } ArrayList<String> parameterToolTips; ArrayList<String> parameterInitialValues; ArrayList<String> params; ArrayList<JTextField> textFields; String toolName; private boolean validated;
/*     */   public boolean dislay() {
/*     */     GridLayout gl;
/*  28 */     setTitle(this.toolName);
/*     */     
/*  30 */     if (kindOfButtons == null) {
/*  31 */       gl = new GridLayout(this.parameterNames.size() + 1, 2);
/*     */     } else {
/*  33 */       gl = new GridLayout(this.parameterNames.size() + 1, 3);
/*  34 */     }  gl.setHgap(5); gl.setVgap(5);
/*  35 */     JPanel utilPanel = new JPanel(gl);
/*     */     
/*  37 */     int max = -1;
/*  38 */     for (String param : this.parameterNames) {
/*  39 */       if (param.length() > max) {
/*  40 */         max = param.length();
/*     */       }
/*     */     } 
/*     */     
/*  44 */     this.fileChoosers = new JButton[this.parameterNames.size()];
/*  45 */     this.textFields = new ArrayList();
/*  46 */     for (int i = 0; i < this.parameterNames.size(); i++) {
/*  47 */       JTextField current; String param = (String)this.parameterNames.get(i);
/*  48 */       JLabel label = new JLabel(param);
/*  49 */       label.setSize(max * 10, 25);
/*  50 */       utilPanel.add(label);
/*     */ 
/*     */       
/*  53 */       if (this.parameterInitialValues.size() > 0) {
/*  54 */         current = new JTextField((String)this.parameterInitialValues.get(i));
/*     */       } else {
/*  56 */         current = new JTextField();
/*     */       } 
/*  58 */       this.textFields.add(current);
/*  59 */       label.setSize(100, 20);
/*  60 */       utilPanel.add(current);
/*  61 */       current.setToolTipText((String)this.parameterToolTips.get(i));
/*  62 */       if (kindOfButtons != null && i < kindOfButtons.size()) {
/*  63 */         if (((Integer)kindOfButtons.get(i)).intValue() == 1) {
/*  64 */           this.fileChoosers[i] = new JButton("...");
/*  65 */           this.fileChoosers[i].addActionListener(this);
/*  66 */           this.fileChoosers[i].setSize(10, 10);
/*  67 */           this.fileChoosers[i].setSize(10, 10);
/*  68 */           utilPanel.add(this.fileChoosers[i]);
/*     */         } else {
/*  70 */           utilPanel.add(new JLabel(""));
/*     */         } 
/*     */       }
/*     */     } 
/*  74 */     kindOfButtons = null;
/*     */     
/*  76 */     this.okButton = new JButton("Ok");
/*  77 */     this.okButton.addActionListener(this);
/*  78 */     utilPanel.add(this.okButton);
/*     */     
/*  80 */     this.cancelButton = new JButton("Cancel");
/*  81 */     this.cancelButton.addActionListener(this);
/*  82 */     utilPanel.add(this.cancelButton);
/*     */     
/*  84 */     BorderLayout bl = new BorderLayout();
/*  85 */     JPanel main = new JPanel(bl);
/*     */     
/*  87 */     Dimension d = new Dimension(10, 10);
/*  88 */     Box.Filler dummyNorth = new Box.Filler(d, d, d);
/*  89 */     Box.Filler dummySouth = new Box.Filler(d, d, d);
/*  90 */     Box.Filler dummyEast = new Box.Filler(d, d, d);
/*  91 */     Box.Filler dummyWest = new Box.Filler(d, d, d);
/*  92 */     main.add(dummyEast, "East");
/*  93 */     main.add(dummyWest, "West");
/*  94 */     main.add(dummyNorth, "North");
/*  95 */     main.add(dummySouth, "South");
/*  96 */     main.add(utilPanel, "Center");
/*     */     
/*  98 */     getContentPane().add(main);
/*  99 */     setResizable(false);
/* 100 */     setModal(true);
/* 101 */     pack();
/* 102 */     setLocation(
/* 103 */         (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2.0D - getPreferredSize().getWidth() / 2.0D), 
/* 104 */         (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2.0D - getPreferredSize().getHeight() / 2.0D) - 50);
/*     */     
/* 106 */     setVisible(true);
/*     */     
/* 108 */     return this.validated;
/*     */   }
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 112 */     for (int i = 0; i < this.fileChoosers.length; i++) {
/* 113 */       if (e.getSource() == this.fileChoosers[i]) {
/* 114 */         JFileChooser jfc = new JFileChooser();
/* 115 */         jfc.setFileSelectionMode(2);
/* 116 */         if (jfc.showOpenDialog(this) == 0)
/* 117 */           ((JTextField)this.textFields.get(i)).setText(jfc.getSelectedFile().getPath()); 
/*     */       } 
/*     */     } 
/* 120 */     if (e.getSource() == this.okButton) {
/* 121 */       this.validated = true;
/* 122 */       this.params = new ArrayList();
/* 123 */       for (JTextField jtf : this.textFields)
/* 124 */         this.params.add(jtf.getText()); 
/* 125 */       dispose();
/*     */     } 
/* 127 */     if (e.getSource() == this.cancelButton) {
/* 128 */       this.validated = false;
/* 129 */       dispose();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 134 */   public ArrayList<String> getParameterValues() { return this.params; }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\gu\\u\\utils\ToolsStarter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */