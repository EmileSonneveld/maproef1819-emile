/*     */ package com.birosoft.liquid.borders;
/*     */ 
/*     */ import java.awt.AWTException;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Robot;
/*     */ import java.awt.Window;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.border.AbstractBorder;
/*     */ import javax.swing.plaf.UIResource;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LiquidFrameBorder
/*     */   extends AbstractBorder
/*     */   implements UIResource
/*     */ {
/*     */   private static LiquidFrameBorder onlyInstance;
/*     */   private static Robot robot;
/*     */   private static boolean robotsSupported = true;
/*  29 */   private static final Insets insets = new Insets(0, 4, 4, 4);
/*     */   
/*     */   private boolean prevState = false;
/*     */   
/*     */   private Window window;
/*     */   private int titleHeight;
/*     */   private boolean isActive = true;
/*     */   
/*     */   public static LiquidFrameBorder getInstance() {
/*  38 */     if (onlyInstance == null) {
/*  39 */       onlyInstance = new LiquidFrameBorder();
/*     */       
/*  41 */       if (robot == null && robotsSupported) {
/*     */         try {
/*  43 */           robot = new Robot();
/*  44 */         } catch (AWTException e) {
/*  45 */           robotsSupported = false;
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/*  50 */     return onlyInstance;
/*     */   }
/*     */   
/*     */   private void isWindowRealActive(Window window) {
/*  54 */     this.isActive = window.isActive();
/*     */     
/*  56 */     if (this.isActive) {
/*  57 */       this.prevState = true;
/*     */     }
/*     */     
/*  60 */     if (!this.prevState && !this.isActive) {
/*  61 */       this.isActive = true;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
/*  70 */     this.window = SwingUtilities.getWindowAncestor(c);
/*     */     
/*  72 */     if (this.window instanceof JDialog) {
/*  73 */       JDialog d = (JDialog)this.window;
/*     */       
/*  75 */       if (d.isModal()) {
/*  76 */         this.isActive = true;
/*     */       } else {
/*  78 */         isWindowRealActive(this.window);
/*     */       } 
/*     */     } else {
/*  81 */       isWindowRealActive(this.window);
/*     */     } 
/*     */     
/*  84 */     int frameTitleHeight = UIManager.getInt("InternalFrame.frameTitleHeight");
/*     */ 
/*     */     
/*  87 */     int index = this.isActive ? 0 : 1;
/*     */     
/*  89 */     drawLeftTop(g, this.isActive, 4, frameTitleHeight);
/*     */     
/*  91 */     g.translate(0, frameTitleHeight);
/*  92 */     drawLeft(g, this.isActive, 4, h - frameTitleHeight - 4);
/*  93 */     g.translate(0, -frameTitleHeight);
/*     */     
/*  95 */     g.translate(0, h - 4);
/*     */ 
/*     */     
/*  98 */     drawBottom(g, this.isActive, w, 4);
/*  99 */     g.translate(0, -(h - 4));
/*     */     
/* 101 */     g.translate(w - 4, 0);
/*     */     
/* 103 */     drawRightTop(g, this.isActive, 4, frameTitleHeight);
/*     */     
/* 105 */     g.translate(0, frameTitleHeight);
/* 106 */     drawRight(g, this.isActive, 4, h - frameTitleHeight - 4);
/* 107 */     g.translate(0, -frameTitleHeight);
/*     */     
/* 109 */     g.translate(-(w - 4), 0);
/*     */   }
/*     */   
/*     */   private void drawLeftTop(Graphics g, boolean isSelected, int w, int h) {
/* 113 */     Color c = isSelected ? new Color(62, 145, 235) : new Color(175, 214, 255);
/* 114 */     g.setColor(c);
/* 115 */     g.fillRect(0, 0, w, h);
/* 116 */     c = isSelected ? new Color(94, 172, 255) : new Color(226, 240, 255);
/* 117 */     g.setColor(c);
/* 118 */     g.drawLine(0, 0, w, 0);
/* 119 */     g.drawLine(0, 0, 0, h);
/* 120 */     c = isSelected ? new Color(60, 141, 228) : new Color(170, 207, 247);
/* 121 */     g.setColor(c);
/* 122 */     g.drawLine(1, 1, w, 1);
/*     */     
/* 124 */     for (int i = 4; i < h; i += 4) {
/* 125 */       c = isSelected ? new Color(59, 138, 223) : new Color(166, 203, 242);
/* 126 */       g.setColor(c);
/* 127 */       g.drawLine(1, i, w, i);
/* 128 */       c = isSelected ? new Color(60, 141, 228) : new Color(170, 207, 247);
/* 129 */       g.setColor(c);
/* 130 */       g.drawLine(1, i + 1, w, i + 1);
/*     */     } 
/*     */     
/* 133 */     c = isSelected ? new Color(47, 111, 180) : new Color(135, 164, 196);
/* 134 */     g.setColor(c);
/* 135 */     g.drawLine(w - 1, h - 1, w - 1, h - 1);
/*     */   }
/*     */   
/*     */   private void drawLeft(Graphics g, boolean isSelected, int w, int h) {
/* 139 */     Color c = isSelected ? new Color(62, 145, 235) : new Color(175, 214, 255);
/* 140 */     g.setColor(c);
/* 141 */     g.fillRect(0, 0, w, h);
/* 142 */     c = isSelected ? new Color(94, 172, 255) : new Color(226, 240, 255);
/* 143 */     g.setColor(c);
/* 144 */     g.drawLine(0, 0, 0, h);
/* 145 */     c = isSelected ? new Color(59, 138, 223) : new Color(166, 203, 242);
/* 146 */     g.setColor(c);
/* 147 */     g.drawLine(1, 0, w, 0);
/*     */     
/* 149 */     for (int i = 3; i < h; i += 4) {
/* 150 */       c = isSelected ? new Color(59, 138, 223) : new Color(166, 203, 242);
/* 151 */       g.setColor(c);
/* 152 */       g.drawLine(1, i, w, i);
/* 153 */       c = isSelected ? new Color(60, 141, 228) : new Color(170, 207, 247);
/* 154 */       g.setColor(c);
/* 155 */       g.drawLine(1, i + 1, w, i + 1);
/*     */     } 
/*     */     
/* 158 */     c = isSelected ? new Color(47, 111, 180) : new Color(135, 164, 196);
/* 159 */     g.setColor(c);
/* 160 */     g.drawLine(w - 1, 0, w - 1, h);
/*     */   }
/*     */   
/*     */   private void drawRightTop(Graphics g, boolean isSelected, int w, int h) {
/* 164 */     Color c = isSelected ? new Color(62, 145, 235) : new Color(175, 214, 255);
/* 165 */     g.setColor(c);
/* 166 */     g.fillRect(0, 0, w, h);
/* 167 */     c = isSelected ? new Color(94, 172, 255) : new Color(226, 240, 255);
/* 168 */     g.setColor(c);
/* 169 */     g.drawLine(0, 0, w - 2, 0);
/* 170 */     c = isSelected ? new Color(60, 141, 228) : new Color(170, 207, 247);
/* 171 */     g.setColor(c);
/* 172 */     g.drawLine(0, 1, w - 2, 1);
/*     */     
/* 174 */     for (int i = 4; i < h; i += 4) {
/* 175 */       c = isSelected ? new Color(59, 138, 223) : new Color(166, 203, 242);
/* 176 */       g.setColor(c);
/* 177 */       g.drawLine(0, i, w - 2, i);
/* 178 */       c = isSelected ? new Color(60, 141, 228) : new Color(170, 207, 247);
/* 179 */       g.setColor(c);
/* 180 */       g.drawLine(0, i + 1, w - 2, i + 1);
/*     */     } 
/*     */     
/* 183 */     c = isSelected ? new Color(94, 172, 255) : new Color(226, 240, 255);
/* 184 */     g.setColor(c);
/* 185 */     g.drawLine(0, h - 1, 0, h - 1);
/* 186 */     c = isSelected ? new Color(47, 111, 180) : new Color(135, 164, 196);
/* 187 */     g.setColor(c);
/* 188 */     g.drawLine(w - 1, 0, w - 1, h);
/*     */   }
/*     */   
/*     */   private void drawRight(Graphics g, boolean isSelected, int w, int h) {
/* 192 */     Color c = isSelected ? new Color(62, 145, 235) : new Color(175, 214, 255);
/* 193 */     g.setColor(c);
/* 194 */     g.fillRect(0, 0, w, h);
/* 195 */     c = isSelected ? new Color(94, 172, 255) : new Color(226, 240, 255);
/* 196 */     g.setColor(c);
/* 197 */     g.drawLine(0, 0, 0, h);
/* 198 */     c = isSelected ? new Color(59, 138, 223) : new Color(166, 203, 242);
/* 199 */     g.setColor(c);
/* 200 */     g.drawLine(1, 0, w, 0);
/*     */     
/* 202 */     for (int i = 3; i < h; i += 4) {
/* 203 */       c = isSelected ? new Color(59, 138, 223) : new Color(166, 203, 242);
/* 204 */       g.setColor(c);
/* 205 */       g.drawLine(1, i, w, i);
/* 206 */       c = isSelected ? new Color(60, 141, 228) : new Color(170, 207, 247);
/* 207 */       g.setColor(c);
/* 208 */       g.drawLine(1, i + 1, w, i + 1);
/*     */     } 
/*     */     
/* 211 */     c = isSelected ? new Color(47, 111, 180) : new Color(135, 164, 196);
/* 212 */     g.setColor(c);
/* 213 */     g.drawLine(w - 1, 0, w - 1, h);
/*     */   }
/*     */   
/*     */   private void drawBottom(Graphics g, boolean isSelected, int w, int h) {
/* 217 */     Color c = isSelected ? new Color(62, 145, 235) : new Color(175, 214, 255);
/* 218 */     g.setColor(c);
/* 219 */     g.fillRect(1, 0, w - 1, h - 1);
/* 220 */     c = isSelected ? new Color(94, 172, 255) : new Color(226, 240, 255);
/* 221 */     g.setColor(c);
/* 222 */     g.drawLine(3, 0, w - 4, 0);
/* 223 */     g.drawLine(0, 0, 0, h - 2);
/* 224 */     c = isSelected ? new Color(47, 111, 180) : new Color(135, 164, 196);
/* 225 */     g.setColor(c);
/* 226 */     g.drawLine(0, h - 1, w, h - 1);
/* 227 */     g.drawLine(w - 1, 0, w - 1, h - 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 235 */   public Insets getBorderInsets(Component c) { return new Insets(0, 4, 4, 4); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 243 */   public void setActive(boolean isActive) { this.isActive = isActive; }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\borders\LiquidFrameBorder.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */