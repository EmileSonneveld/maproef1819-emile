/*    */ package classes.lrg.insider.gui;
/*    */ 
/*    */ import javax.swing.JDialog;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.SwingUtilities;
/*    */ import javax.swing.UIManager;
/*    */ import lrg.insider.gui.InsiderGUIMain;
/*    */ 
/*    */ public class InsiderGUIMain
/*    */ {
/*    */   private static boolean useSAIL = false;
/* 12 */   private static String additionalClassPath = "";
/*    */   
/* 14 */   public static String getAdditioanClassPath() { return additionalClassPath; }
/*    */   private static JFrame frame;
/* 16 */   public static boolean withSAIL() { return useSAIL; }
/*    */   
/*    */   public static void main(String[] args) {
/* 19 */     if (args.length > 0 && args[0].compareTo("useSAIL") == 0) { useSAIL = true; }
/* 20 */     else if (args.length > 0) { additionalClassPath = args[0]; }
/*    */     
/* 22 */     JDialog.setDefaultLookAndFeelDecorated(true);
/* 23 */     JFrame.setDefaultLookAndFeelDecorated(true);
/*    */     try {
/* 25 */       UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
/* 26 */     } catch (Exception e) {
/* 27 */       e.printStackTrace();
/*    */     } 
/*    */     
/* 30 */     SwingUtilities.invokeLater(new Object());
/*    */   }
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
/* 52 */   public static JFrame getFrame() { return frame; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\gui\InsiderGUIMain.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */