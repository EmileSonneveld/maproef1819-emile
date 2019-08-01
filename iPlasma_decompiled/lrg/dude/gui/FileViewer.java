/*    */ package lrg.dude.gui;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.GridLayout;
/*    */ import java.io.File;
/*    */ import java.io.FileReader;
/*    */ import javax.swing.BorderFactory;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JScrollPane;
/*    */ import javax.swing.JTextArea;
/*    */ import javax.swing.text.BadLocationException;
/*    */ import javax.swing.text.DefaultHighlighter;
/*    */ import javax.swing.text.Highlighter;
/*    */ import lrg.dude.duplication.CodeFragment;
/*    */ 
/*    */ public class FileViewer
/*    */   extends JPanel {
/*    */   private JTextArea file;
/*    */   private Highlighter highlighter;
/*    */   
/*    */   public FileViewer() {
/* 23 */     this.highlighter = new DefaultHighlighter();
/*    */ 
/*    */     
/* 26 */     setLayout(new GridLayout(1, 1));
/* 27 */     this.file = new JTextArea();
/* 28 */     this.file.setEditable(false);
/* 29 */     JScrollPane file1Pane = new JScrollPane(this.file, 
/* 30 */         22, 
/* 31 */         30);
/* 32 */     setBorder(BorderFactory.createTitledBorder("<empty>"));
/* 33 */     setMinimumSize(new Dimension(200, 75));
/* 34 */     setPreferredSize(new Dimension(400, 150));
/* 35 */     add(file1Pane);
/*    */   }
/*    */   
/*    */   public void viewFile(String startingPath, String fileName, CodeFragment code) {
/*    */     try {
/* 40 */       FileReader fr = new FileReader(String.valueOf(startingPath) + File.separator + code.getEntity().getName());
/* 41 */       this.file.read(fr, null);
/* 42 */       setBorder(BorderFactory.createTitledBorder(code.getEntity().getName()));
/* 43 */       fr.close();
/* 44 */     } catch (Exception ex) {
/* 45 */       System.err.println("Could not load page files");
/*    */     } 
/*    */     try {
/* 48 */       int offsetStart = this.file.getLineStartOffset((int)code.getBeginLine() - 1);
/* 49 */       int offsetEnd = this.file.getLineEndOffset((int)code.getEndLine() - 1);
/*    */       
/* 51 */       this.highlighter.install(this.file);
/*    */       
/* 53 */       Highlighter.HighlightPainter painter = 
/* 54 */         new DefaultHighlighter.DefaultHighlightPainter(new Color(16777113));
/*    */       
/* 56 */       this.file.getHighlighter().removeAllHighlights();
/*    */       
/* 58 */       this.file.getHighlighter().addHighlight(offsetStart, offsetEnd, painter);
/* 59 */       this.file.setCaretPosition(offsetStart);
/* 60 */     } catch (BadLocationException e1) {
/* 61 */       e1.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   public void clear() {
/* 66 */     this.file.setText("");
/* 67 */     setBorder(BorderFactory.createTitledBorder("<empty>"));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\dude.jar!\lrg\dude\gui\FileViewer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */