/*    */ package lrg.dude.gui;
/*    */ 
/*    */ import javax.swing.JDialog;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.SwingUtilities;
/*    */ import javax.swing.UIManager;
/*    */ import javax.swing.UnsupportedLookAndFeelException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GUIStarter
/*    */ {
/*    */   public static void main(String[] args) {
/* 16 */     SwingUtilities.invokeLater(new Runnable()
/*    */         {
/*    */ 
/*    */ 
/*    */           
/*    */           public void run()
/*    */           {
/* 23 */             GUIStarter.createAndShowGUI(); }
/*    */         }); } private static void createAndShowGUI() {
/* 25 */     JFrame.setDefaultLookAndFeelDecorated(true);
/* 26 */     JDialog.setDefaultLookAndFeelDecorated(true);
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 31 */     lnfName = "com.birosoft.liquid.LiquidLookAndFeel";
/*    */     try {
/* 33 */       UIManager.setLookAndFeel(lnfName);
/*    */     
/*    */     }
/* 36 */     catch (UnsupportedLookAndFeelException ex1) {
/* 37 */       System.err.println("Unsupported LookAndFeel: " + lnfName);
/*    */     }
/* 39 */     catch (ClassNotFoundException ex2) {
/* 40 */       System.err.println("LookAndFeel class not found: " + lnfName);
/*    */     }
/* 42 */     catch (InstantiationException ex3) {
/* 43 */       System.err.println("Could not load LookAndFeel: " + lnfName);
/*    */     }
/* 45 */     catch (IllegalAccessException ex4) {
/* 46 */       System.err.println("Cannot use LookAndFeel: " + lnfName);
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 51 */     JFrame frame = new JFrame("DuDe");
/*    */     
/* 53 */     frame.setDefaultCloseOperation(3);
/*    */ 
/*    */     
/* 56 */     GUI gui = new GUI();
/* 57 */     gui.setOpaque(true);
/* 58 */     frame.setContentPane(gui);
/*    */ 
/*    */     
/* 61 */     frame.pack();
/* 62 */     frame.setVisible(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\dude.jar!\lrg\dude\gui\GUIStarter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */