/*    */ package com.birosoft.liquid.borders;
/*    */ 
/*    */ import java.awt.BasicStroke;
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
/*    */ import javax.swing.border.LineBorder;
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
/*    */ public class LiquidFocusCellHighlightBorder
/*    */   extends LineBorder
/*    */   implements UIResource
/*    */ {
/* 28 */   static final float[] dash1 = { 1.0F };
/* 29 */   static final BasicStroke dashed = new BasicStroke(1.0F, 0, 0, 10.0F, dash1, 0.0F);
/*    */ 
/*    */ 
/*    */   
/*    */   private Color color;
/*    */ 
/*    */ 
/*    */   
/*    */   public LiquidFocusCellHighlightBorder(Color c) {
/* 38 */     super(c);
/* 39 */     this.color = c;
/*    */   }
/*    */ 
/*    */   
/*    */   public LiquidFocusCellHighlightBorder(Color c, int thickness) {
/* 44 */     super(c, thickness);
/* 45 */     this.color = c;
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
/*    */   public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
/* 65 */     Graphics2D g2 = (Graphics2D)g;
/* 66 */     g2.setColor(this.color);
/* 67 */     g2.setStroke(dashed);
/* 68 */     g2.drawRect(x, y, w - 1, h - 1);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\borders\LiquidFocusCellHighlightBorder.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */