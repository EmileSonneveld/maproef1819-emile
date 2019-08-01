/*     */ package lrg.dude.gui;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JPanel;
/*     */ import lrg.dude.duplication.Duplication;
/*     */ 
/*     */ public class GUI extends JPanel implements Observer, ProgressObserver {
/*     */   private String startingPath;
/*     */   private Processor processor;
/*     */   private Parameters params;
/*     */   private StringCompareStrategy compareStrategy;
/*     */   private Duplication[] duplicationFound;
/*     */   private long startTime;
/*     */   private long lastElapsedTime;
/*     */   private JSpinner spinnerMinL;
/*     */   private JSpinner spinnerMaxLB;
/*     */   private JSpinner spinnerMinEC;
/*     */   private JComboBox comparisonStrategyCB;
/*     */   private JSpinner similarityThreshold;
/*     */   private JCheckBox cboxIgnoreComments;
/*     */   
/*     */   public GUI() {
/*  24 */     this.startingPath = null;
/*  25 */     this.processor = null;
/*     */ 
/*     */     
/*  28 */     this.duplicationFound = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  55 */     setLayout(new BorderLayout(10, 10));
/*  56 */     JPanel upperPanel = new JPanel();
/*  57 */     upperPanel.setLayout(new BoxLayout(upperPanel, 2));
/*  58 */     JPanel middlePanel = new JPanel(new GridLayout(1, 1));
/*  59 */     middlePanel.setBorder(BorderFactory.createTitledBorder("Results"));
/*  60 */     JPanel lowerPanel = new JPanel(new GridLayout(1, 2));
/*     */ 
/*     */ 
/*     */     
/*  64 */     ButtonAction buttonAction = new ButtonAction(this);
/*  65 */     this.pathButton = new JButton("Set path", new ImageIcon("res/graphics/Open.png"));
/*  66 */     this.pathButton.setActionCommand("path");
/*  67 */     this.pathButton.addActionListener(buttonAction);
/*  68 */     this.pathButton.setToolTipText("Sets the starting path");
/*     */     
/*  70 */     this.saveButton = new JButton("Save results", new ImageIcon("res/graphics/Save.png"));
/*  71 */     this.saveButton.setActionCommand("save");
/*  72 */     this.saveButton.addActionListener(buttonAction);
/*  73 */     this.saveButton.setToolTipText("Save the results list (in the sorted order) to a file");
/*  74 */     this.saveButton.setEnabled(false);
/*     */     
/*  76 */     this.computeButton = new JButton("Find duplicates", new ImageIcon("res/graphics/Find.png"));
/*  77 */     this.computeButton.setActionCommand("compute");
/*  78 */     this.computeButton.addActionListener(buttonAction);
/*  79 */     this.computeButton.setToolTipText("Search for duplication chains");
/*  80 */     this.computeButton.setEnabled(false);
/*     */     
/*  82 */     this.statsButton = new JButton("Statistics", new ImageIcon("res/graphics/Statistics.png"));
/*  83 */     this.statsButton.setActionCommand("statistics");
/*  84 */     this.statsButton.addActionListener(buttonAction);
/*  85 */     this.statsButton.setToolTipText("Show statistics on the last search");
/*  86 */     this.statsButton.setEnabled(false);
/*     */     
/*  88 */     this.helpButton = new JButton("Help", new ImageIcon("res/graphics/Help.png"));
/*  89 */     this.helpButton.setActionCommand("help");
/*  90 */     this.helpButton.addActionListener(buttonAction);
/*  91 */     this.helpButton.setToolTipText("Help documentation");
/*     */     
/*  93 */     this.aboutButton = new JButton("About", new ImageIcon("res/graphics/About.png"));
/*  94 */     this.aboutButton.setActionCommand("about");
/*  95 */     this.aboutButton.addActionListener(buttonAction);
/*  96 */     this.aboutButton.setToolTipText("Info about this program");
/*     */     
/*  98 */     JPanel controlPanel = new JPanel(new GridLayout(2, 3));
/*  99 */     controlPanel.add(this.pathButton);
/* 100 */     controlPanel.add(this.computeButton);
/* 101 */     controlPanel.add(this.helpButton);
/* 102 */     controlPanel.add(this.saveButton);
/* 103 */     controlPanel.add(this.statsButton);
/* 104 */     controlPanel.add(this.aboutButton);
/*     */     
/* 106 */     controlPanel.setBorder(BorderFactory.createTitledBorder("Controls"));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 111 */     this.spinnerMinL = new JSpinner(new SpinnerNumberModel(7, 1, 999, 1));
/* 112 */     this.spinnerMaxLB = new JSpinner(new SpinnerNumberModel(2, 0, 10, 1));
/* 113 */     this.spinnerMinEC = new JSpinner(new SpinnerNumberModel(2, 1, 10, 1));
/*     */     
/* 115 */     this.cboxIgnoreComments = new JCheckBox("Ignore Comments", true);
/* 116 */     this.comparisonStrategyCB = new JComboBox(new String[] { "compareTo", "Levenshtein Distance" });
/* 117 */     this.similarityThreshold = new JSpinner(new SpinnerNumberModel(0.2D, 0.0D, 1.0D, 0.05D));
/*     */ 
/*     */     
/* 120 */     JPanel parametersPanel = new JPanel(new GridLayout(0, 2));
/* 121 */     parametersPanel.add(new JLabel("MinLength"));
/* 122 */     parametersPanel.add(this.spinnerMinL);
/* 123 */     parametersPanel.add(new JLabel("MaxLineBias"));
/* 124 */     parametersPanel.add(this.spinnerMaxLB);
/* 125 */     parametersPanel.add(new JLabel("MinExactChunk"));
/* 126 */     parametersPanel.add(this.spinnerMinEC);
/* 127 */     parametersPanel.add(this.cboxIgnoreComments);
/* 128 */     parametersPanel.add(new JLabel());
/* 129 */     parametersPanel.add(new JLabel("Comparison Strategy"));
/* 130 */     parametersPanel.add(this.comparisonStrategyCB);
/* 131 */     parametersPanel.add(new JLabel("LD/length Threshold"));
/* 132 */     parametersPanel.add(this.similarityThreshold);
/* 133 */     parametersPanel.setBorder(BorderFactory.createTitledBorder("Parameters"));
/*     */ 
/*     */     
/* 136 */     upperPanel.add(controlPanel);
/* 137 */     upperPanel.add(Box.createHorizontalGlue());
/* 138 */     upperPanel.add(parametersPanel);
/*     */ 
/*     */     
/* 141 */     this.sorter = new TableSorter(new MyTableModel());
/* 142 */     this.table = new JTable(this.sorter);
/* 143 */     this.table.setPreferredScrollableViewportSize(new Dimension(500, 70));
/* 144 */     this.sorter.setTableHeader(this.table.getTableHeader());
/*     */ 
/*     */ 
/*     */     
/* 148 */     this.table.getTableHeader().setToolTipText("Click to specify sorting; Control-Click to specify secondary sorting");
/* 149 */     this.table.getTableHeader().setBorder(BorderFactory.createEtchedBorder(1));
/* 150 */     this.table.getTableHeader().setVisible(false);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 155 */     this.table.setSelectionMode(0);
/*     */     
/* 157 */     ListSelectionModel rowSM = this.table.getSelectionModel();
/* 158 */     rowSM.addListSelectionListener(new ListSelectionListener()
/*     */         {
/*     */           public void valueChanged(ListSelectionEvent e) {
/* 161 */             if (e.getValueIsAdjusting())
/* 162 */               return;  ListSelectionModel lsm = (ListSelectionModel)e.getSource();
/* 163 */             if (!lsm.isSelectionEmpty()) {
/* 164 */               int realSelectedRow = GUI.this.sorter.modelIndex(lsm.getMinSelectionIndex());
/* 165 */               GUI.this.viewDuplicate(realSelectedRow);
/*     */             } 
/*     */           }
/*     */         });
/*     */     
/* 170 */     this.tablePane = new JScrollPane(this.table);
/* 171 */     this.tablePane.setBorder(BorderFactory.createTitledBorder("Duplicates found"));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 176 */     this.fileViewer1 = new FileViewer();
/* 177 */     this.fileViewer2 = new FileViewer();
/*     */     
/* 179 */     JPanel visualizationContainer = new JPanel(new GridLayout(1, 2));
/* 180 */     visualizationContainer.add(this.fileViewer1);
/* 181 */     visualizationContainer.add(this.fileViewer2);
/* 182 */     visualizationContainer.setBorder(BorderFactory.createTitledBorder("Duplicate viewer"));
/*     */     
/* 184 */     JSplitPane resultsPane = new JSplitPane(0, 
/* 185 */         this.tablePane, visualizationContainer);
/*     */     
/* 187 */     middlePanel.add(resultsPane);
/*     */     
/* 189 */     this.statusLabel = new JLabel("<status bar>");
/*     */     
/* 191 */     lowerPanel.add(this.statusLabel);
/* 192 */     this.progressBar = new JProgressBar(0, 100);
/* 193 */     this.progressBar.setStringPainted(true);
/* 194 */     this.progressBar.setBorderPainted(true);
/* 195 */     this.progressBar.setVisible(false);
/* 196 */     lowerPanel.add(this.progressBar);
/*     */     
/* 198 */     add(upperPanel, "First");
/* 199 */     add(middlePanel, "Center");
/* 200 */     add(lowerPanel, "Last");
/*     */   }
/*     */   private JButton pathButton; private JButton saveButton; private JButton computeButton; private JButton statsButton; private JButton helpButton; private JButton aboutButton; private JTable table; private TableSorter sorter; private JScrollPane tablePane; private FileViewer fileViewer1; private FileViewer fileViewer2; private JLabel statusLabel; private JProgressBar progressBar; private int progressValue;
/*     */   
/*     */   public void viewDuplicate(int index) {
/* 205 */     CodeFragment code1 = this.duplicationFound[index].getReferenceCode();
/* 206 */     CodeFragment code2 = this.duplicationFound[index].getDuplicateCode();
/* 207 */     this.fileViewer1.viewFile(this.startingPath, code1.getEntityName(), this.duplicationFound[index].getReferenceCode());
/* 208 */     this.fileViewer2.viewFile(this.startingPath, code2.getEntityName(), this.duplicationFound[index].getDuplicateCode());
/*     */   }
/*     */   
/*     */   public void setPathAction() {
/* 212 */     JFileChooser chooser = new JFileChooser();
/* 213 */     chooser.setFileSelectionMode(1);
/* 214 */     chooser.setCurrentDirectory(new File("."));
/* 215 */     int returnVal = chooser.showDialog(this, "Set path");
/* 216 */     if (returnVal == 0) {
/* 217 */       this.startingPath = chooser.getSelectedFile().getAbsolutePath();
/* 218 */       this.statusLabel.setText("Starting path set to: " + this.startingPath);
/* 219 */       this.computeButton.setEnabled(true);
/*     */     } else {
/* 221 */       this.statusLabel.setText("Path setting operation cancelled");
/*     */     } 
/*     */   }
/*     */   public void computeAction() {
/* 225 */     this.computeButton.setEnabled(false);
/* 226 */     this.pathButton.setEnabled(false);
/* 227 */     this.saveButton.setEnabled(false);
/* 228 */     this.statsButton.setEnabled(false);
/* 229 */     this.progressBar.setString("Reading files...");
/* 230 */     this.progressBar.setIndeterminate(true);
/* 231 */     this.progressBar.setVisible(true);
/*     */     
/* 233 */     if (this.startingPath != null) {
/* 234 */       switch (this.comparisonStrategyCB.getSelectedIndex()) {
/*     */         case 0:
/* 236 */           this.processor = new Processor(this.startingPath, this, 
/* 237 */               new IdenticalCompareStrategy());
/*     */           break;
/*     */         case 1:
/* 240 */           this.processor = new Processor(this.startingPath, this, 
/* 241 */               new LevenshteinDistanceStrategy(((Double)this.similarityThreshold.getValue()).doubleValue()));
/*     */           break;
/*     */         default:
/* 244 */           System.out.println("No such comparison strategy"); break;
/*     */       } 
/* 246 */       this.params = new Parameters(((Integer)this.spinnerMinL.getValue()).intValue(), (
/* 247 */           (Integer)this.spinnerMaxLB.getValue()).intValue(), (
/* 248 */           (Integer)this.spinnerMinEC.getValue()).intValue(), 
/* 249 */           !this.cboxIgnoreComments.isSelected());
/* 250 */       this.processor.setParams(this.params);
/*     */       
/* 252 */       this.processor.attach(this);
/* 253 */       this.statusLabel.setText("Please wait...");
/* 254 */       this.startTime = System.currentTimeMillis();
/* 255 */       this.processor.start();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void saveAction() {
/* 260 */     if (this.duplicationFound.length > 0) {
/* 261 */       JFileChooser chooser = new JFileChooser();
/* 262 */       chooser.setCurrentDirectory(new File("."));
/* 263 */       int returnVal = chooser.showSaveDialog(this);
/* 264 */       if (returnVal == 0) {
/* 265 */         File file = chooser.getSelectedFile();
/* 266 */         if (file.exists()) {
/* 267 */           Object[] options = { "OK", "CANCEL" };
/* 268 */           int n = JOptionPane.showOptionDialog(this, 
/* 269 */               "The file already exists. Overwrite?", 
/* 270 */               "Overwrite existing file?", 
/* 271 */               -1, 
/* 272 */               3, 
/* 273 */               null, 
/* 274 */               options, 
/* 275 */               options[0]);
/* 276 */           if (n == 1) {
/* 277 */             this.statusLabel.setText("Save operation cancelled");
/*     */             return;
/*     */           } 
/*     */         } 
/* 281 */         this.statusLabel.setText("Saving to file: " + file.getName());
/* 282 */         saveFormattedToFile(file);
/* 283 */         this.statusLabel.setText("Results saved to file: " + file.getName());
/*     */       } else {
/* 285 */         this.statusLabel.setText("Save operation cancelled");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void saveFormattedToFile(File file) {
/* 292 */     BufferedWriter out = null;
/*     */     try {
/* 294 */       out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
/* 295 */     } catch (FileNotFoundException fnfe) {
/* 296 */       this.statusLabel.setText("ERROR: no such file " + file.getName());
/*     */       return;
/*     */     } 
/*     */     try {
/* 300 */       out.write("************** Starting path & Parameters ***************");
/* 301 */       out.newLine();
/* 302 */       out.write("Date: " + new Date(System.currentTimeMillis()));
/* 303 */       out.newLine();
/* 304 */       out.write("Starting path: " + this.startingPath);
/* 305 */       out.newLine();
/* 306 */       out.write("Search parameters: ");
/* 307 */       out.newLine();
/* 308 */       out.write("\tMinimum length: " + this.params.getMinLength());
/* 309 */       out.newLine();
/* 310 */       out.write("\tMaximum line bias: " + this.params.getMaxLineBias());
/* 311 */       out.newLine();
/* 312 */       out.write("\tMinimum exact chunk: " + this.params.getMinExactChunk());
/* 313 */       out.newLine();
/* 314 */       out.write("\tIgnore comments: " + (this.params.isConsiderComments() ? 0 : 1));
/* 315 */       out.newLine();
/* 316 */       out.newLine();
/* 317 */       for (int i = 0; i < this.duplicationFound.length; i++) {
/* 318 */         for (int j = 0; j < this.sorter.getColumnCount(); j++) {
/* 319 */           switch (j) {
/*     */             case 0:
/* 321 */               out.newLine();
/* 322 */               out.write("************** " + (i + 1) + " ***************");
/* 323 */               out.newLine();
/* 324 */               out.newLine();
/* 325 */               out.write("file: ");
/*     */               break;
/*     */             case 1:
/* 328 */               out.write(", start line: ");
/*     */               break;
/*     */             case 2:
/* 331 */               out.write(", end line: ");
/*     */               break;
/*     */             case 3:
/* 334 */               out.newLine();
/* 335 */               out.write("file: ");
/*     */               break;
/*     */             case 4:
/* 338 */               out.write(", start line: ");
/*     */               break;
/*     */             case 5:
/* 341 */               out.write(", end line: ");
/*     */               break;
/*     */             case 6:
/* 344 */               out.newLine();
/* 345 */               out.write("Lines of copied code: ");
/*     */               break;
/*     */             case 7:
/* 348 */               out.newLine();
/* 349 */               out.write("Lines in file: ");
/*     */               break;
/*     */             case 8:
/* 352 */               out.newLine();
/* 353 */               out.write("Duplication chain type: ");
/*     */               break;
/*     */             case 9:
/* 356 */               out.newLine();
/* 357 */               out.write("Duplication chain Signature: ");
/*     */               break;
/*     */           } 
/* 360 */           out.write(this.sorter.getValueAt(i, j).toString());
/*     */         } 
/* 362 */         for (int k = 0; k < 3; k++)
/* 363 */           out.newLine(); 
/*     */       } 
/* 365 */     } catch (IOException ioe) {
/* 366 */       this.statusLabel.setText("ERROR: could not write to file " + file.getName());
/*     */       return;
/*     */     } 
/*     */     try {
/* 370 */       out.close();
/* 371 */     } catch (IOException ioe) {
/* 372 */       this.statusLabel.setText("ERROR: could not close the file " + file.getName());
/*     */       return;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void helpAction() {
/* 379 */     JEditorPane editorPane = new JEditorPane();
/* 380 */     editorPane.setEditable(false);
/* 381 */     String page = "res/helpfiles/help.html";
/* 382 */     File localFile = new File(page);
/* 383 */     page = "file:///" + localFile.getAbsolutePath();
/* 384 */     URL helpURL = null;
/*     */     try {
/* 386 */       helpURL = new URL(page);
/* 387 */     } catch (MalformedURLException e) {
/* 388 */       e.printStackTrace();
/*     */     } 
/* 390 */     if (helpURL != null) {
/*     */       try {
/* 392 */         editorPane.setPage(helpURL);
/* 393 */       } catch (IOException e) {
/* 394 */         System.err.println("Attempted to read a bad URL: " + helpURL);
/*     */       } 
/*     */     } else {
/* 397 */       System.err.println("Couldn't find file: " + page);
/*     */     } 
/*     */ 
/*     */     
/* 401 */     JScrollPane editorScrollPane = new JScrollPane(editorPane);
/* 402 */     editorScrollPane.setVerticalScrollBarPolicy(22);
/* 403 */     editorScrollPane.setPreferredSize(new Dimension(800, 600));
/* 404 */     editorScrollPane.setMinimumSize(new Dimension(640, 480));
/*     */     
/* 406 */     JFrame helpFrame = new JFrame("Help on DuDe");
/* 407 */     helpFrame.setContentPane(editorScrollPane);
/*     */     
/* 409 */     helpFrame.pack();
/* 410 */     helpFrame.setVisible(true);
/*     */   }
/*     */   
/*     */   public void aboutAction() {
/* 414 */     StringBuffer about = new StringBuffer();
/* 415 */     about.append("DuDe, build 0.83\n");
/* 416 */     about.append("Author: Richard Wettel\n");
/* 417 */     about.append("wettel@cs.utt.ro\n");
/* 418 */     about.append("LOOSE Research Group\n");
/* 419 */     about.append("http://loose.utt.ro\n");
/* 420 */     about.append("All rights reserved, 2004\n");
/* 421 */     JOptionPane.showConfirmDialog(this, about.toString(), "About Dude", 
/* 422 */         -1, -1, new ImageIcon("res/graphics/Icon.gif"));
/*     */   }
/*     */   
/*     */   public void showStatisticsAction() {
/* 426 */     StringBuffer stats = new StringBuffer("Statistical data:\n\n");
/* 427 */     if (this.processor != null) {
/* 428 */       long noOfRelevantLines = this.processor.getMatrixLinesLength();
/* 429 */       long noOfDuplicatedLines = this.processor.getNumberOfDuplicatedLines();
/* 430 */       double percentage = noOfDuplicatedLines * 100.0D / noOfRelevantLines;
/* 431 */       stats.append("Starting path: " + this.startingPath + "\n");
/* 432 */       stats.append("Number of analyzed files: " + this.processor.getNumberOfEntities() + "\n");
/* 433 */       stats.append("Total number of lines: " + this.processor.getNumberOfRawLines() + "\n");
/* 434 */       stats.append("Number of relevant lines: " + noOfRelevantLines + "\n");
/* 435 */       stats.append("Number of duplicated lines: " + noOfDuplicatedLines + 
/* 436 */           " (" + Math.round(percentage) + "%)\n");
/* 437 */       stats.append("Number of matrix cells: " + this.processor.getNumberOfDots() + "\n");
/* 438 */       stats.append("Number of duplication chains: " + this.duplicationFound.length + "\n");
/* 439 */       stats.append("Elapsed time: " + TimeMeasurer.convertTimeToString(this.lastElapsedTime) + "\n");
/*     */     } else {
/* 441 */       stats.append("No statistics data available");
/*     */     } 
/* 443 */     JOptionPane.showConfirmDialog(this, stats.toString(), "Statistics", 
/* 444 */         -1, -1, new ImageIcon("res/graphics/Statistics.png"));
/*     */   }
/*     */   
/*     */   public void getDuplication(Subject source) {
/* 448 */     this.lastElapsedTime = System.currentTimeMillis() - this.startTime;
/* 449 */     this.duplicationFound = ((Processor)source).getSearchResults();
/* 450 */     updateResultsTable();
/* 451 */     Toolkit.getDefaultToolkit().beep();
/* 452 */     this.computeButton.setEnabled(true);
/* 453 */     this.progressBar.setValue(this.progressBar.getMinimum());
/* 454 */     this.progressBar.setString("");
/*     */   }
/*     */   
/*     */   private void updateResultsTable() {
/* 458 */     this.statsButton.setEnabled(true);
/* 459 */     this.pathButton.setEnabled(true);
/*     */     
/* 461 */     this.fileViewer1.clear();
/* 462 */     this.fileViewer2.clear();
/*     */     
/* 464 */     int numberOfDuplicationChains = this.duplicationFound.length;
/*     */     
/* 466 */     if (this.duplicationFound != null && this.duplicationFound.length > 0) {
/*     */       
/* 468 */       Object[][] data = new Object[numberOfDuplicationChains][10];
/* 469 */       Duplication currentDuplication = null;
/* 470 */       for (int i = 0; i < numberOfDuplicationChains; i++) {
/* 471 */         currentDuplication = this.duplicationFound[i];
/* 472 */         data[i][0] = currentDuplication.getReferenceCode().getEntityName();
/* 473 */         data[i][1] = new Integer((int)currentDuplication.getReferenceCode().getBeginLine());
/* 474 */         data[i][2] = new Integer((int)currentDuplication.getReferenceCode().getEndLine());
/* 475 */         data[i][3] = currentDuplication.getDuplicateCode().getEntityName();
/* 476 */         data[i][4] = new Integer((int)currentDuplication.getDuplicateCode().getBeginLine());
/* 477 */         data[i][5] = new Integer((int)currentDuplication.getDuplicateCode().getEndLine());
/* 478 */         data[i][6] = new Integer((int)currentDuplication.copiedLength());
/* 479 */         data[i][7] = new Integer((int)currentDuplication.realLength());
/* 480 */         data[i][8] = currentDuplication.getType();
/* 481 */         data[i][9] = currentDuplication.getSignature();
/*     */       } 
/*     */ 
/*     */       
/* 485 */       this.table.getTableHeader().setVisible(true);
/* 486 */       this.table.setAutoResizeMode(3);
/* 487 */       this.sorter.setTableModel(new MyTableModel(data));
/*     */       
/* 489 */       TableColumn column = null;
/* 490 */       for (int i = 0; i < 6; i++) {
/* 491 */         column = this.table.getColumnModel().getColumn(i);
/* 492 */         switch (i) {
/*     */           case 0:
/*     */           case 3:
/* 495 */             column.setPreferredWidth(200);
/*     */             break;
/*     */           case 1:
/*     */           case 2:
/*     */           case 4:
/*     */           case 5:
/* 501 */             column.setPreferredWidth(30);
/*     */             break;
/*     */           case 6:
/*     */           case 7:
/* 505 */             column.setPreferredWidth(40);
/*     */             break;
/*     */           case 8:
/* 508 */             column.setPreferredWidth(50);
/*     */             break;
/*     */           case 9:
/* 511 */             column.setPreferredWidth(200);
/*     */             break;
/*     */         } 
/*     */       } 
/* 515 */       this.saveButton.setEnabled(true);
/*     */     } else {
/* 517 */       this.sorter.setTableModel(new MyTableModel());
/* 518 */       this.table.getTableHeader().setVisible(false);
/* 519 */       this.saveButton.setEnabled(false);
/*     */     } 
/* 521 */     this.progressBar.setVisible(false);
/* 522 */     this.statusLabel.setText("Found " + numberOfDuplicationChains + " duplication chain" + (
/* 523 */         (numberOfDuplicationChains == 1) ? "" : "s") + " in " + 
/* 524 */         TimeMeasurer.convertTimeToString(this.lastElapsedTime));
/*     */   }
/*     */   
/*     */   public void setMaxValue(int i) {
/* 528 */     this.progressBar.setMaximum(i);
/* 529 */     this.progressValue = 0;
/* 530 */     this.progressBar.setIndeterminate(false);
/* 531 */     this.progressBar.setString(null);
/*     */   }
/*     */ 
/*     */   
/* 535 */   public void increment() { this.progressBar.setValue(++this.progressValue); }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\dude.jar!\lrg\dude\gui\GUI.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */