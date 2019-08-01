/*    */ package com.birosoft.liquid;
/*    */ 
/*    */ import java.awt.BasicStroke;
/*    */ import java.awt.Color;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.Rectangle;
/*    */ import javax.swing.AbstractButton;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.plaf.ComponentUI;
/*    */ import javax.swing.plaf.basic.BasicCheckBoxUI;
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
/*    */ public class LiquidCheckBoxUI
/*    */   extends BasicCheckBoxUI
/*    */ {
/* 37 */   private static final LiquidCheckBoxUI checkBoxUI = new LiquidCheckBoxUI();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 45 */   static LiquidCheckBoxIcon skinnedIcon = new LiquidCheckBoxIcon();
/* 46 */   static BasicStroke focusStroke = new BasicStroke(1.0F, 0, 2, 1.0F, new float[] { 1.0F }, 1.0F);
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
/* 65 */   public static ComponentUI createUI(JComponent c) { return checkBoxUI; }
/*    */ 
/*    */   
/*    */   public void installDefaults(AbstractButton button) {
/* 69 */     super.installDefaults(button);
/* 70 */     this.icon = skinnedIcon;
/* 71 */     button.setRolloverEnabled(true);
/*    */   }
/*    */   
/*    */   protected void paintFocus(Graphics g, Rectangle t, Dimension arg2) {
/* 75 */     Graphics2D g2d = (Graphics2D)g;
/* 76 */     g2d.setColor(Color.black);
/* 77 */     g2d.setStroke(focusStroke);
/*    */ 
/*    */     
/* 80 */     g2d.drawLine(t.x - 1, t.y - 1, t.x - 1 + t.width + 1, t.y - 1);
/* 81 */     g2d.drawLine(t.x - 1, t.y - 1 + t.height + 1, t.x - 1 + t.width + 1, t.y - 1 + t.height + 1);
/*    */     
/* 83 */     g2d.drawLine(t.x - 1, t.y - 1, t.x - 1, t.y - 1 + t.height + 1);
/* 84 */     g2d.drawLine(t.x - 1 + t.width + 1, t.y - 1, t.x - 1 + t.width + 1, t.y - 1 + t.height + 1);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\LiquidCheckBoxUI.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */