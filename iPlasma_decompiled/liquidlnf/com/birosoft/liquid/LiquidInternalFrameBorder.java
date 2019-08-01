/*     */ package com.birosoft.liquid;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Insets;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LiquidInternalFrameBorder
/*     */   extends AbstractBorder
/*     */   implements UIResource
/*     */ {
/*     */   boolean isActive;
/*     */   boolean isPalette = false;
/*  39 */   private static final Insets insets = new Insets(0, 4, 4, 4);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
/*  47 */     int frameTitleHeight = this.isPalette ? UIManager.getInt("InternalFrame.paletteTitleHeight") : UIManager.getInt("InternalFrame.frameTitleHeight");
/*     */     
/*  49 */     int index = this.isActive ? 0 : 1;
/*     */     
/*  51 */     drawLeftTop(g, this.isActive, 4, frameTitleHeight);
/*     */     
/*  53 */     g.translate(0, frameTitleHeight);
/*  54 */     drawLeft(g, this.isActive, 4, h - frameTitleHeight - 4);
/*  55 */     g.translate(0, -frameTitleHeight);
/*     */     
/*  57 */     g.translate(0, h - 4);
/*     */     
/*  59 */     drawBottom(g, this.isActive, w, 4);
/*  60 */     g.translate(0, -(h - 4));
/*     */     
/*  62 */     g.translate(w - 4, 0);
/*     */     
/*  64 */     drawRightTop(g, this.isActive, 4, frameTitleHeight);
/*     */     
/*  66 */     g.translate(0, frameTitleHeight);
/*  67 */     drawRight(g, this.isActive, 4, h - frameTitleHeight - 4);
/*  68 */     g.translate(0, -frameTitleHeight);
/*     */     
/*  70 */     g.translate(-(w - 4), 0);
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawLeftTop(Graphics g, boolean isSelected, int w, int h) {
/*  75 */     Color c = isSelected ? new Color(62, 145, 235) : new Color(175, 214, 255);
/*  76 */     g.setColor(c);
/*  77 */     g.fillRect(0, 0, w, h);
/*  78 */     c = isSelected ? new Color(94, 172, 255) : new Color(226, 240, 255);
/*  79 */     g.setColor(c);
/*  80 */     g.drawLine(0, 0, w, 0);
/*  81 */     g.drawLine(0, 0, 0, h);
/*  82 */     c = isSelected ? new Color(60, 141, 228) : new Color(170, 207, 247);
/*  83 */     g.setColor(c);
/*  84 */     g.drawLine(1, 1, w, 1);
/*  85 */     for (int i = 4; i < h; i += 4) {
/*     */       
/*  87 */       c = isSelected ? new Color(59, 138, 223) : new Color(166, 203, 242);
/*  88 */       g.setColor(c);
/*  89 */       g.drawLine(1, i, w, i);
/*  90 */       c = isSelected ? new Color(60, 141, 228) : new Color(170, 207, 247);
/*  91 */       g.setColor(c);
/*  92 */       g.drawLine(1, i + 1, w, i + 1);
/*     */     } 
/*  94 */     c = isSelected ? new Color(47, 111, 180) : new Color(135, 164, 196);
/*  95 */     g.setColor(c);
/*  96 */     g.drawLine(w - 1, h - 1, w - 1, h - 1);
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawLeft(Graphics g, boolean isSelected, int w, int h) {
/* 101 */     Color c = isSelected ? new Color(62, 145, 235) : new Color(175, 214, 255);
/* 102 */     g.setColor(c);
/* 103 */     g.fillRect(0, 0, w, h);
/* 104 */     c = isSelected ? new Color(94, 172, 255) : new Color(226, 240, 255);
/* 105 */     g.setColor(c);
/* 106 */     g.drawLine(0, 0, 0, h);
/* 107 */     c = isSelected ? new Color(59, 138, 223) : new Color(166, 203, 242);
/* 108 */     g.setColor(c);
/* 109 */     g.drawLine(1, 0, w, 0);
/* 110 */     for (int i = 3; i < h; i += 4) {
/*     */       
/* 112 */       c = isSelected ? new Color(59, 138, 223) : new Color(166, 203, 242);
/* 113 */       g.setColor(c);
/* 114 */       g.drawLine(1, i, w, i);
/* 115 */       c = isSelected ? new Color(60, 141, 228) : new Color(170, 207, 247);
/* 116 */       g.setColor(c);
/* 117 */       g.drawLine(1, i + 1, w, i + 1);
/*     */     } 
/* 119 */     c = isSelected ? new Color(47, 111, 180) : new Color(135, 164, 196);
/* 120 */     g.setColor(c);
/* 121 */     g.drawLine(w - 1, 0, w - 1, h);
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawRightTop(Graphics g, boolean isSelected, int w, int h) {
/* 126 */     Color c = isSelected ? new Color(62, 145, 235) : new Color(175, 214, 255);
/* 127 */     g.setColor(c);
/* 128 */     g.fillRect(0, 0, w, h);
/* 129 */     c = isSelected ? new Color(94, 172, 255) : new Color(226, 240, 255);
/* 130 */     g.setColor(c);
/* 131 */     g.drawLine(0, 0, w - 2, 0);
/* 132 */     c = isSelected ? new Color(60, 141, 228) : new Color(170, 207, 247);
/* 133 */     g.setColor(c);
/* 134 */     g.drawLine(0, 1, w - 2, 1);
/* 135 */     for (int i = 4; i < h; i += 4) {
/*     */       
/* 137 */       c = isSelected ? new Color(59, 138, 223) : new Color(166, 203, 242);
/* 138 */       g.setColor(c);
/* 139 */       g.drawLine(0, i, w - 2, i);
/* 140 */       c = isSelected ? new Color(60, 141, 228) : new Color(170, 207, 247);
/* 141 */       g.setColor(c);
/* 142 */       g.drawLine(0, i + 1, w - 2, i + 1);
/*     */     } 
/* 144 */     c = isSelected ? new Color(94, 172, 255) : new Color(226, 240, 255);
/* 145 */     g.setColor(c);
/* 146 */     g.drawLine(0, h - 1, 0, h - 1);
/* 147 */     c = isSelected ? new Color(47, 111, 180) : new Color(135, 164, 196);
/* 148 */     g.setColor(c);
/* 149 */     g.drawLine(w - 1, 0, w - 1, h);
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawRight(Graphics g, boolean isSelected, int w, int h) {
/* 154 */     Color c = isSelected ? new Color(62, 145, 235) : new Color(175, 214, 255);
/* 155 */     g.setColor(c);
/* 156 */     g.fillRect(0, 0, w, h);
/* 157 */     c = isSelected ? new Color(94, 172, 255) : new Color(226, 240, 255);
/* 158 */     g.setColor(c);
/* 159 */     g.drawLine(0, 0, 0, h);
/* 160 */     c = isSelected ? new Color(59, 138, 223) : new Color(166, 203, 242);
/* 161 */     g.setColor(c);
/* 162 */     g.drawLine(1, 0, w, 0);
/* 163 */     for (int i = 3; i < h; i += 4) {
/*     */       
/* 165 */       c = isSelected ? new Color(59, 138, 223) : new Color(166, 203, 242);
/* 166 */       g.setColor(c);
/* 167 */       g.drawLine(1, i, w, i);
/* 168 */       c = isSelected ? new Color(60, 141, 228) : new Color(170, 207, 247);
/* 169 */       g.setColor(c);
/* 170 */       g.drawLine(1, i + 1, w, i + 1);
/*     */     } 
/* 172 */     c = isSelected ? new Color(47, 111, 180) : new Color(135, 164, 196);
/* 173 */     g.setColor(c);
/* 174 */     g.drawLine(w - 1, 0, w - 1, h);
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawBottom(Graphics g, boolean isSelected, int w, int h) {
/* 179 */     Color c = isSelected ? new Color(62, 145, 235) : new Color(175, 214, 255);
/* 180 */     g.setColor(c);
/* 181 */     g.fillRect(1, 0, w - 1, h - 1);
/* 182 */     c = isSelected ? new Color(94, 172, 255) : new Color(226, 240, 255);
/* 183 */     g.setColor(c);
/* 184 */     g.drawLine(3, 0, w - 4, 0);
/* 185 */     g.drawLine(0, 0, 0, h - 2);
/* 186 */     c = isSelected ? new Color(47, 111, 180) : new Color(135, 164, 196);
/* 187 */     g.setColor(c);
/* 188 */     g.drawLine(0, h - 1, w, h - 1);
/* 189 */     g.drawLine(w - 1, 0, w - 1, h - 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 198 */   public Insets getBorderInsets(Component c) { return insets; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 207 */   public void setActive(boolean isActive) { this.isActive = isActive; }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\LiquidInternalFrameBorder.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */