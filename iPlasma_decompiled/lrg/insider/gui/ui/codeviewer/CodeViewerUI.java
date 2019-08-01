/*    */ package classes.lrg.insider.gui.ui.codeviewer;
/*    */ 
/*    */ import de.java2html.Java2Html;
/*    */ import de.java2html.options.Java2HtmlConversionOptions;
/*    */ import de.java2html.options.JavaSourceStyleTable;
/*    */ import java.awt.Toolkit;
/*    */ import java.awt.event.MouseEvent;
/*    */ import java.awt.event.MouseListener;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import javax.swing.JDialog;
/*    */ import javax.swing.JEditorPane;
/*    */ import javax.swing.JScrollPane;
/*    */ import lrg.insider.gui.InsiderGUIMain;
/*    */ import lrg.insider.gui.ui.codeviewer.CodeViewerUI;
/*    */ import lrg.memoria.core.Method;
/*    */ 
/*    */ public class CodeViewerUI
/*    */   extends JDialog implements MouseListener {
/*    */   private JEditorPane sourceEditorPane;
/*    */   private ArrayList styles;
/*    */   private Iterator stylesIt;
/*    */   private String sourceCode;
/*    */   
/*    */   public CodeViewerUI(Method aMethod) {
/* 26 */     super(InsiderGUIMain.getFrame(), aMethod.getProperty("Address").getValue().toString(), false);
/* 27 */     setLocationRelativeTo(InsiderGUIMain.getFrame());
/*    */     
/* 29 */     this.sourceEditorPane = new JEditorPane();
/* 30 */     this.sourceEditorPane.setEditable(false);
/* 31 */     this.sourceEditorPane.addMouseListener(this);
/* 32 */     this.sourceEditorPane.setContentType("text/html");
/*    */     
/* 34 */     JScrollPane scrollPane = new JScrollPane(this.sourceEditorPane);
/* 35 */     setContentPane(scrollPane);
/*    */     
/* 37 */     this.styles = new ArrayList(3);
/* 38 */     Java2HtmlConversionOptions x = Java2HtmlConversionOptions.getDefault();
/* 39 */     x.setStyleTable(JavaSourceStyleTable.getDefaultKawaStyleTable());
/* 40 */     this.styles.add(x);
/* 41 */     x = Java2HtmlConversionOptions.getDefault();
/* 42 */     x.setStyleTable(JavaSourceStyleTable.getDefaultEclipseStyleTable());
/* 43 */     this.styles.add(x);
/* 44 */     x = Java2HtmlConversionOptions.getDefault();
/* 45 */     x.setStyleTable(JavaSourceStyleTable.getDefaultMonochromeStyleTable());
/* 46 */     this.styles.add(x);
/*    */     
/* 48 */     this.stylesIt = this.styles.iterator();
/*    */     
/* 50 */     if (aMethod.getBody() != null && aMethod.getBody().getSourceCode() != null) {
/* 51 */       this.sourceCode = aMethod.getBody().getSourceCode().trim();
/*    */     } else {
/* 53 */       this.sourceCode = "(oops!) No body found.";
/*    */     } 
/* 55 */     syntaxHighlight(this.sourceCode);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setVisible(boolean b) {
/* 60 */     if (b) {
/* 61 */       int sx = getWidth(), sy = getHeight();
/* 62 */       if (sx > 900) { sx = 900; sy += 40; }
/* 63 */        if (sy > 500) { sy = 500; sx += 40; }
/* 64 */        setSize(sx, sy);
/* 65 */       sx /= 2; sy /= 2;
/* 66 */       setLocation((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - sx, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - sy);
/*    */     } 
/* 68 */     super.setVisible(b);
/*    */   }
/*    */   
/*    */   public void mouseReleased(MouseEvent e) {
/* 72 */     if (e.getSource() == this.sourceEditorPane && 
/* 73 */       e.getButton() == 2) {
/* 74 */       syntaxHighlight(this.sourceCode);
/*    */     }
/*    */   }
/*    */   
/*    */   private void syntaxHighlight(String sourceCode) {
/* 79 */     int caretPos = this.sourceEditorPane.getCaretPosition();
/*    */     
/* 81 */     if (!this.stylesIt.hasNext()) this.stylesIt = this.styles.iterator(); 
/* 82 */     String htmlified = Java2Html.convertToHtml(sourceCode, (Java2HtmlConversionOptions)this.stylesIt.next());
/* 83 */     this.sourceEditorPane.setText(htmlified.replaceAll("/>", ">"));
/* 84 */     this.sourceEditorPane.setCaretPosition(caretPos);
/*    */   }
/*    */   
/*    */   public void mouseClicked(MouseEvent e) {}
/*    */   
/*    */   public void mouseEntered(MouseEvent e) {}
/*    */   
/*    */   public void mouseExited(MouseEvent e) {}
/*    */   
/*    */   public void mousePressed(MouseEvent e) {}
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\gu\\ui\codeviewer\CodeViewerUI.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */