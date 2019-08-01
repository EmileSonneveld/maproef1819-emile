/*    */ package com.birosoft.liquid;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Rectangle;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.JSeparator;
/*    */ import javax.swing.plaf.ComponentUI;
/*    */ import javax.swing.plaf.basic.BasicSeparatorUI;
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
/*    */ public class LiquidSeparatorUI
/*    */   extends BasicSeparatorUI
/*    */ {
/* 26 */   public static ComponentUI createUI(JComponent c) { return new LiquidSeparatorUI(); }
/*    */ 
/*    */ 
/*    */   
/*    */   public void paint(Graphics g, JComponent c) {
/* 31 */     Dimension s = c.getSize();
/*    */     
/* 33 */     if (((JSeparator)c).getOrientation() == 1) {
/* 34 */       g.setColor(new Color(189, 188, 188));
/* 35 */       g.drawLine(0, 0, 0, s.height);
/*    */       
/* 37 */       g.setColor(new Color(255, 255, 255));
/* 38 */       g.drawLine(1, 0, 1, s.height);
/*    */     } else {
/* 40 */       JComponent p = (JComponent)c.getParent();
/*    */       
/* 42 */       Integer maxValueInt = (Integer)p.getClientProperty("maxIconWidth");
/* 43 */       int maxValue = (maxValueInt == null) ? 16 : maxValueInt.intValue();
/*    */       
/* 45 */       Rectangle rect = new Rectangle(0, 0, maxValue + LiquidMenuItemUI.defaultTextIconGap, s.height);
/* 46 */       g.setColor(new Color(189, 188, 188));
/* 47 */       g.drawLine(rect.x, 0, s.width, 0);
/*    */       
/* 49 */       g.setColor(new Color(255, 255, 255));
/* 50 */       g.drawLine(rect.x, 1, s.width, 1);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\LiquidSeparatorUI.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */