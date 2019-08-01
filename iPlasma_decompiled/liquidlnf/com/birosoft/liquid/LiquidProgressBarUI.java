/*     */ package com.birosoft.liquid;
/*     */ 
/*     */ import com.birosoft.liquid.skin.Skin;
/*     */ import com.birosoft.liquid.skin.SkinImageCache;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.TexturePaint;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.basic.BasicProgressBarUI;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LiquidProgressBarUI
/*     */   extends BasicProgressBarUI
/*     */ {
/*     */   static Skin skinHorizontal;
/*     */   static Skin skinVertical;
/*  51 */   int offset = 3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   public static ComponentUI createUI(JComponent c) { return new LiquidProgressBarUI(); }
/*     */ 
/*     */   
/*     */   protected void paintDeterminate(Graphics g, JComponent c) {
/*  64 */     if (!(g instanceof Graphics2D)) {
/*     */       return;
/*     */     }
/*     */     
/*  68 */     Insets b = this.progressBar.getInsets();
/*  69 */     int barRectWidth = this.progressBar.getWidth() - b.right + b.left;
/*  70 */     int barRectHeight = this.progressBar.getHeight() - b.top + b.bottom;
/*     */     
/*  72 */     int cellLength = getCellLength();
/*  73 */     int cellSpacing = getCellSpacing();
/*     */     
/*  75 */     Graphics2D g2 = (Graphics2D)g;
/*     */     
/*  77 */     if (this.progressBar.getOrientation() == 0) {
/*  78 */       int amountFull = getAmountFull(b, barRectWidth - this.offset * 2, barRectHeight);
/*     */       
/*  80 */       getSkinHorizontal().draw(g, 0, barRectWidth, barRectHeight);
/*     */       
/*  82 */       g.translate(this.offset - 1, 0);
/*  83 */       BufferedImage img = SkinImageCache.getInstance().getBufferedImage("hprogressbar.png");
/*  84 */       TexturePaint tp = new TexturePaint(img, new Rectangle2D.Float(0.0F, 0.0F, img.getWidth(), img.getHeight()));
/*     */       
/*  86 */       Paint p = g2.getPaint();
/*     */       
/*  88 */       g2.setPaint(tp);
/*  89 */       g2.fillRect(1, 3, amountFull, barRectHeight - 6);
/*     */       
/*  91 */       g2.setPaint(p);
/*  92 */       if (amountFull > 0) {
/*  93 */         g2.setColor(new Color(179, 192, 207));
/*  94 */         g2.drawLine(0, 2, 0, barRectHeight - 4);
/*  95 */         g2.drawLine(0, 2, amountFull, 2);
/*  96 */         g2.setColor(new Color(195, 209, 226));
/*  97 */         g2.drawLine(amountFull + 1, 2, amountFull + 1, barRectHeight - 3);
/*  98 */         g2.drawLine(0, barRectHeight - 3, amountFull + 1, barRectHeight - 3);
/*     */       } 
/*     */       
/* 101 */       g.translate(-this.offset + 1, 0);
/*     */ 
/*     */       
/* 104 */       if (this.progressBar.isStringPainted()) {
/* 105 */         g.setColor(Color.black);
/* 106 */         paintString(g, b.left, b.top, barRectWidth, barRectHeight, amountFull, b);
/*     */       } 
/*     */     } else {
/*     */       
/* 110 */       int amountFull = getAmountFull(b, barRectWidth, barRectHeight - 2 * this.offset);
/*     */       
/* 112 */       getSkinVertical().draw(g, 0, barRectWidth, barRectHeight);
/* 113 */       BufferedImage img = SkinImageCache.getInstance().getBufferedImage("XPProgressIndicatorVert.res");
/* 114 */       amountFull = amountFull / img.getHeight() * img.getHeight();
/* 115 */       g.translate(0, barRectHeight - this.offset);
/*     */       
/* 117 */       BufferedImage imgL = SkinImageCache.getInstance().getBufferedImage("XPLeftProgressBar.res");
/* 118 */       BufferedImage imgM = SkinImageCache.getInstance().getBufferedImage("XPCenterProgressBar.res");
/* 119 */       BufferedImage imgR = SkinImageCache.getInstance().getBufferedImage("XPRightProgressBar.res");
/*     */       
/* 121 */       TexturePaint tpL = new TexturePaint(imgL, new Rectangle2D.Float(0.0F, 0.0F, imgL.getWidth(), imgL.getHeight()));
/* 122 */       TexturePaint tpM = new TexturePaint(imgM, new Rectangle2D.Float(imgL.getWidth(), 0.0F, imgM.getWidth(), imgM.getHeight()));
/* 123 */       TexturePaint tpR = new TexturePaint(imgR, new Rectangle2D.Float((barRectWidth - imgR.getWidth()), 0.0F, imgR.getWidth(), imgR.getHeight()));
/*     */       
/* 125 */       Paint p = g2.getPaint();
/* 126 */       g2.setPaint(tpL);
/* 127 */       g2.fillRect(0, -amountFull, imgL.getWidth(), amountFull);
/*     */       
/* 129 */       g2.setPaint(tpM);
/* 130 */       g2.fillRect(imgL.getWidth(), -amountFull, barRectWidth - imgR.getWidth() - imgL.getWidth(), amountFull);
/*     */       
/* 132 */       g2.setPaint(tpR);
/* 133 */       g2.fillRect(barRectWidth - imgR.getWidth(), -amountFull, imgR.getWidth(), amountFull);
/*     */       
/* 135 */       g2.setPaint(p);
/* 136 */       g.translate(0, -(barRectHeight - this.offset));
/*     */ 
/*     */       
/* 139 */       if (this.progressBar.isStringPainted()) {
/* 140 */         g.setColor(Color.black);
/* 141 */         paintString(g, b.left, b.top, barRectWidth, barRectHeight, amountFull, b);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void paintIndeterminate(Graphics g, JComponent c) {
/* 147 */     if (!(g instanceof Graphics2D)) {
/*     */       return;
/*     */     }
/* 150 */     Graphics2D g2 = (Graphics2D)g;
/*     */     
/* 152 */     Insets b = this.progressBar.getInsets();
/* 153 */     int barRectWidth = this.progressBar.getWidth() - b.right + b.left;
/* 154 */     int barRectHeight = this.progressBar.getHeight() - b.top + b.bottom;
/*     */     
/* 156 */     Rectangle boxRect = getBox(null);
/* 157 */     if (this.progressBar.getOrientation() == 0) {
/*     */       
/* 159 */       getSkinHorizontal().draw(g, 0, barRectWidth, barRectHeight);
/* 160 */       g.translate(boxRect.x + this.offset, boxRect.y);
/* 161 */       BufferedImage img = SkinImageCache.getInstance().getBufferedImage("hprogressbar.png");
/* 162 */       TexturePaint tp = new TexturePaint(img, new Rectangle2D.Float(0.0F, 0.0F, img.getWidth(), img.getHeight()));
/*     */       
/* 164 */       Paint p = g2.getPaint();
/*     */       
/* 166 */       g2.setPaint(tp);
/* 167 */       g2.fillRect(1, 3, 20, barRectHeight - 6);
/*     */       
/* 169 */       g2.setPaint(p);
/* 170 */       g2.setColor(new Color(195, 209, 226));
/* 171 */       g2.drawLine(20, 2, 20, barRectHeight - 4);
/* 172 */       g2.drawLine(0, barRectHeight - 3, 20, barRectHeight - 3);
/* 173 */       g2.setColor(new Color(179, 192, 207));
/* 174 */       g2.drawLine(0, 2, 0, barRectHeight - 4);
/* 175 */       g2.drawLine(0, 2, 20, 2);
/*     */       
/* 177 */       g.translate(-boxRect.x - this.offset, -boxRect.y);
/*     */     } else {
/*     */       
/* 180 */       getSkinVertical().draw(g, 0, barRectWidth, barRectHeight);
/*     */ 
/*     */       
/* 183 */       g.translate(boxRect.x, boxRect.y + this.offset);
/*     */       
/* 185 */       BufferedImage imgL = SkinImageCache.getInstance().getBufferedImage("XPLeftProgressBar.res");
/* 186 */       BufferedImage imgM = SkinImageCache.getInstance().getBufferedImage("XPCenterProgressBar.res");
/* 187 */       BufferedImage imgR = SkinImageCache.getInstance().getBufferedImage("XPRightProgressBar.res");
/*     */       
/* 189 */       TexturePaint tpL = new TexturePaint(imgL, new Rectangle2D.Float(0.0F, 0.0F, imgL.getWidth(), imgL.getHeight()));
/* 190 */       TexturePaint tpM = new TexturePaint(imgM, new Rectangle2D.Float(imgL.getWidth(), 0.0F, imgM.getWidth(), imgM.getHeight()));
/* 191 */       TexturePaint tpR = new TexturePaint(imgR, new Rectangle2D.Float((barRectWidth - imgR.getWidth()), 0.0F, imgR.getWidth(), imgR.getHeight()));
/*     */       
/* 193 */       int h = (boxRect.height - 2 * this.offset) / imgM.getHeight() * imgM.getHeight();
/*     */       
/* 195 */       Paint p = g2.getPaint();
/* 196 */       g2.setPaint(tpL);
/* 197 */       g2.fillRect(0, 0, imgL.getWidth(), h);
/*     */       
/* 199 */       g2.setPaint(tpM);
/* 200 */       g2.fillRect(imgL.getWidth(), 0, barRectWidth - imgR.getWidth() - imgL.getWidth(), h);
/*     */       
/* 202 */       g2.setPaint(tpR);
/* 203 */       g2.fillRect(barRectWidth - imgR.getWidth(), 0, imgR.getWidth(), h);
/*     */       
/* 205 */       g2.setPaint(p);
/*     */       
/* 207 */       g.translate(-boxRect.x, -boxRect.y - this.offset);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 216 */   public void update(Graphics g, JComponent c) { paint(g, c); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void installDefaults() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public static Skin getSkinHorizontal() {
/* 226 */     if (skinHorizontal == null) {
/* 227 */       skinHorizontal = new Skin("progressborderhoriz.png", 1, 1, 1, 1, 1);
/*     */     }
/*     */     
/* 230 */     return skinHorizontal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Skin getSkinVertical() {
/* 238 */     if (skinVertical == null) {
/* 239 */       skinVertical = new Skin("progressbordervert.png", 1, 1, 1, 1, 1);
/*     */     }
/*     */     
/* 242 */     return skinVertical;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\LiquidProgressBarUI.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */