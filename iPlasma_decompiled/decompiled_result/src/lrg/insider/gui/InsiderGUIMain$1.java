/*    */ package classes.lrg.insider.gui;
/*    */ 
/*    */ import java.awt.Toolkit;
/*    */ import javax.swing.JFrame;
/*    */ import lrg.insider.gui.InsiderGUIMain;
/*    */ import lrg.insider.gui.ui.MainForm;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class null
/*    */   implements Runnable
/*    */ {
/*    */   public void run() {
/* 34 */     MainForm mainForm = MainForm.instance();
/*    */ 
/*    */ 
/*    */     
/* 38 */     InsiderGUIMain.access$0(new JFrame("iPlasma 6.1"));
/* 39 */     InsiderGUIMain.access$1().setContentPane(mainForm.getTopComponent());
/* 40 */     InsiderGUIMain.access$1().setJMenuBar(mainForm.getMenuBar());
/* 41 */     InsiderGUIMain.access$1().pack();
/* 42 */     InsiderGUIMain.access$1().setDefaultCloseOperation(3);
/* 43 */     InsiderGUIMain.access$1().setLocation((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - 450, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - 300);
/* 44 */     InsiderGUIMain.access$1().setSize(900, 600);
/* 45 */     InsiderGUIMain.access$1().show();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\gui\InsiderGUIMain$1.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */