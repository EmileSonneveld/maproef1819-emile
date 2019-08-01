/*    */ package com.birosoft.liquid;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Graphics;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.plaf.ComponentUI;
/*    */ import javax.swing.plaf.basic.BasicPanelUI;
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
/*    */ public class LiquidPanelUI
/*    */   extends BasicPanelUI
/*    */ {
/*    */   private static LiquidPanelUI panelUI;
/*    */   
/*    */   public static ComponentUI createUI(JComponent c) {
/* 28 */     if (panelUI == null) {
/* 29 */       panelUI = new LiquidPanelUI();
/*    */     }
/* 31 */     return panelUI;
/*    */   }
/*    */   
/*    */   public void installUI(JComponent c) {
/* 35 */     JPanel p = (JPanel)c;
/* 36 */     super.installUI(p);
/* 37 */     installDefaults(p);
/*    */   }
/*    */ 
/*    */   
/* 41 */   public void uninstallUI(JComponent c) { super.uninstallUI(c); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void paint(Graphics g, JComponent c) {
/* 48 */     if (g.getColor().equals(LiquidLookAndFeel.getBackgroundColor())) {
/*    */       
/* 50 */       g.setColor(new Color(238, 237, 236));
/* 51 */       int i = 0;
/* 52 */       int height = c.getHeight();
/* 53 */       while (i < height) {
/*    */         
/* 55 */         g.drawLine(0, i, c.getWidth() - 1, i);
/* 56 */         i++;
/* 57 */         g.drawLine(0, i, c.getWidth() - 1, i);
/* 58 */         i += 3;
/*    */       } 
/*    */     } 
/* 61 */     super.paint(g, c);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\LiquidPanelUI.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */