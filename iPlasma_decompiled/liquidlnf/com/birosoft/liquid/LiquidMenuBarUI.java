/*    */ package com.birosoft.liquid;
/*    */ 
/*    */ import com.birosoft.liquid.skin.Skin;
/*    */ import java.awt.Color;
/*    */ import java.awt.Graphics;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.border.EmptyBorder;
/*    */ import javax.swing.plaf.ComponentUI;
/*    */ import javax.swing.plaf.basic.BasicMenuBarUI;
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
/*    */ 
/*    */ 
/*    */ public class LiquidMenuBarUI
/*    */   extends BasicMenuBarUI
/*    */ {
/*    */   static Skin skin;
/*    */   
/*    */   public static ComponentUI createUI(JComponent c) {
/* 41 */     c.setBorder(new EmptyBorder(0, 5, 2, 0));
/* 42 */     return new LiquidMenuBarUI();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void paint(Graphics g, JComponent c) {
/* 53 */     int width = c.getWidth();
/* 54 */     int height = c.getHeight();
/* 55 */     getSkin().draw(g, 2, width, height - 2);
/* 56 */     g.setColor(new Color(238, 237, 236));
/* 57 */     for (int i = 2; i < height; ) {
/*    */       
/* 59 */       g.drawLine(0, i, width, i);
/* 60 */       g.drawLine(0, i + 1, width, i + 1);
/* 61 */       i += 4;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public Skin getSkin() {
/* 67 */     if (skin == null)
/*    */     {
/* 69 */       skin = new Skin("menu_top.png", 3, 0);
/*    */     }
/*    */     
/* 72 */     return skin;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\LiquidMenuBarUI.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */