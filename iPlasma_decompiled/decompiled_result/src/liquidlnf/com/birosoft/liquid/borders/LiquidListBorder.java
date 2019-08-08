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
/*    */ public class LiquidListBorder
/*    */   extends AbstractBorder
/*    */   implements UIResource
/*    */ {
/* 27 */   private static final Insets defaultInsets = new Insets(1, 1, 1, 1);
/*    */ 
/*    */   
/*    */   private Insets insets;
/*    */ 
/*    */   
/* 33 */   public LiquidListBorder() { this.insets = defaultInsets; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 38 */   public LiquidListBorder(Insets insets) { this.insets = insets; }
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
/* 49 */   public Insets getBorderInsets(Component c) { return this.insets; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
/* 58 */     g.setColor(new Color(189, 188, 188));
/* 59 */     g.drawLine(x, y, w - 1, y);
/* 60 */     g.drawLine(x, y, x, h - 1);
/* 61 */     g.setColor(Color.white);
/* 62 */     g.drawLine(w - 2, y + 1, w - 2, h - 1);
/* 63 */     g.drawLine(w - 1, y + 1, w - 1, h - 1);
/* 64 */     g.drawLine(x + 1, h - 2, w - 1, h - 2);
/* 65 */     g.drawLine(x + 1, h - 1, w - 1, h - 1);
/* 66 */     g.setColor(new Color(223, 222, 221));
/* 67 */     g.drawLine(x + 1, y + 1, w - 3, y + 1);
/* 68 */     g.drawLine(x + 1, y + 1, x + 1, h - 3);
/* 69 */     g.drawLine(x, h - 1, x, h - 1);
/* 70 */     g.setColor(new Color(213, 212, 211));
/* 71 */     g.drawLine(x, y, x, y);
/* 72 */     g.drawLine(w - 1, y, w - 1, y);
/* 73 */     g.setColor(new Color(248, 247, 246));
/* 74 */     g.drawLine(w - 1, h - 1, w - 1, h - 1);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\borders\LiquidListBorder.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */