/*    */ package classes.lrg.insider.gui.ui.utils;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JProgressBar;
/*    */ import lrg.insider.gui.ui.utils.ProgressBar;
/*    */ 
/*    */ public class ProgressBar implements ProgressObserver {
/*    */   private JFrame frame;
/*    */   
/*    */   public ProgressBar(String label) {
/* 10 */     this.insiderProgress = new JProgressBar();
/* 11 */     this.contor = 0;
/*    */ 
/*    */     
/* 14 */     this.insiderProgress.setString(label);
/* 15 */     this.insiderProgress.setStringPainted(true);
/*    */ 
/*    */     
/* 18 */     JPanel panel = new JPanel();
/* 19 */     panel.setLayout(new BorderLayout());
/* 20 */     panel.add(this.insiderProgress, "Center");
/*    */     
/* 22 */     this.frame = new JFrame();
/* 23 */     this.frame.getContentPane().add(panel, "Center");
/* 24 */     this.frame.setLocation(400, 300);
/* 25 */     this.frame.setSize(250, 60);
/* 26 */     this.frame.setVisible(true);
/* 27 */     this.frame.setResizable(false);
/*    */   }
/*    */   private JProgressBar insiderProgress; private int contor;
/*    */   
/* 31 */   public void setMaxValue(int max) { this.insiderProgress.setMaximum(max); }
/*    */ 
/*    */   
/*    */   public void increment() {
/* 35 */     this.contor++;
/* 36 */     this.insiderProgress.setValue(this.contor);
/*    */   }
/*    */ 
/*    */   
/* 40 */   public void close() { this.frame.setVisible(false); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\gu\\u\\utils\ProgressBar.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */