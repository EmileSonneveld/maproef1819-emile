/*    */ package com.birosoft.liquid.borders;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Insets;
/*    */ import javax.swing.border.AbstractBorder;
/*    */ import javax.swing.plaf.UIResource;
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
/*    */ public class LiquidPopupMenuBorder
/*    */   extends AbstractBorder
/*    */   implements UIResource
/*    */ {
/* 34 */   protected static Insets insets = new Insets(2, 2, 2, 2);
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
/*    */   public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
/* 48 */     g.setColor(new Color(255, 255, 255));
/* 49 */     g.drawRect(1, 1, w - 3, h - 3);
/* 50 */     g.setColor(new Color(175, 174, 174));
/* 51 */     g.drawRect(0, 0, w - 1, h - 1);
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
/* 62 */   public Insets getBorderInsets(Component c) { return insets; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\borders\LiquidPopupMenuBorder.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */