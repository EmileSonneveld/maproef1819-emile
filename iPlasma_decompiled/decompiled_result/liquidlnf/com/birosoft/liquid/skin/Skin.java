/*     */ package com.birosoft.liquid.skin;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Skin
/*     */   extends SkinElement
/*     */ {
/*     */   private int nrImages;
/*     */   private int hsize;
/*     */   private int vsize;
/*     */   private int ulX;
/*     */   private int ulY;
/*     */   private int lrX;
/*     */   private int lrY;
/*     */   private boolean noBorder = false;
/*     */   
/*     */   public Skin(String fileName, int nrImages, int ulX, int ulY, int lrX, int lrY) {
/*  80 */     super(fileName, true);
/*  81 */     this.nrImages = nrImages;
/*  82 */     this.ulX = ulX;
/*  83 */     this.ulY = ulY;
/*  84 */     this.lrX = lrX;
/*  85 */     this.lrY = lrY;
/*  86 */     calculateSizes();
/*     */   }
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
/*     */   public Skin(String fileName, int nrImages, int roundedSize) {
/*  99 */     this(fileName, nrImages, roundedSize, roundedSize, roundedSize, roundedSize);
/* 100 */     if (roundedSize == 0) {
/* 101 */       this.noBorder = true;
/*     */     }
/*     */   }
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
/*     */   public void draw(Graphics g, int index, int sizeX, int sizeY) {
/* 115 */     int offset = index * getHsize();
/* 116 */     if (!this.noBorder) {
/*     */ 
/*     */       
/* 119 */       g.drawImage(getImage(), 0, 0, this.ulX, this.ulY, offset + 0, 0, offset + this.ulX, this.ulY, null);
/*     */ 
/*     */       
/* 122 */       g.drawImage(getImage(), this.ulX, 0, sizeX - this.lrX, this.ulY, offset + this.ulX, 0, offset + this.hsize - this.lrX, this.ulY, null);
/*     */ 
/*     */       
/* 125 */       g.drawImage(getImage(), sizeX - this.lrX, 0, sizeX, this.ulY, offset + this.hsize - this.lrX, 0, offset + this.hsize, this.ulY, null);
/*     */ 
/*     */       
/* 128 */       g.drawImage(getImage(), 0, this.ulY, this.ulX, sizeY - this.lrY, offset + 0, this.ulY, offset + this.ulX, this.vsize - this.lrY, null);
/*     */ 
/*     */       
/* 131 */       g.drawImage(getImage(), sizeX - this.lrX, this.ulY, sizeX, sizeY - this.lrY, offset + this.hsize - this.lrX, this.ulY, offset + this.hsize, this.vsize - this.lrY, null);
/*     */ 
/*     */       
/* 134 */       g.drawImage(getImage(), 0, sizeY - this.lrY, this.ulX, sizeY, offset + 0, this.vsize - this.lrY, offset + this.ulX, this.vsize, null);
/*     */ 
/*     */       
/* 137 */       g.drawImage(getImage(), this.ulX, sizeY - this.lrY, sizeX - this.lrX, sizeY, offset + this.ulX, this.vsize - this.lrY, offset + this.hsize - this.lrX, this.vsize, null);
/*     */ 
/*     */       
/* 140 */       g.drawImage(getImage(), sizeX - this.lrX, sizeY - this.lrY, sizeX, sizeY, offset + this.hsize - this.lrX, this.vsize - this.lrY, offset + this.hsize, this.vsize, null);
/*     */       
/* 142 */       g.drawImage(getImage(), this.ulX, this.ulY, sizeX - this.lrX, sizeY - this.lrY, offset + this.ulX, this.ulY, offset + this.hsize - this.lrX, this.vsize - this.lrY, null);
/*     */     } else {
/*     */       
/* 145 */       g.drawImage(getImage(), 0, 0, sizeX, sizeY, offset, 0, offset + this.hsize, this.vsize, null);
/*     */     } 
/*     */   }
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
/*     */   public void draw(Graphics g, int index, int x, int y, int sizeX, int sizeY) {
/* 164 */     int offset = index * getHsize();
/* 165 */     if (!this.noBorder) {
/*     */ 
/*     */ 
/*     */       
/* 169 */       g.drawImage(getImage(), x + 0, y + 0, x + this.ulX, y + this.ulY, offset + 0, 0, offset + this.ulX, this.ulY, null);
/*     */ 
/*     */       
/* 172 */       g.drawImage(getImage(), x + this.ulX, y + 0, x + sizeX - this.lrX, y + this.ulY, offset + this.ulX, 0, offset + this.hsize - this.lrX, this.ulY, null);
/*     */ 
/*     */       
/* 175 */       g.drawImage(getImage(), x + sizeX - this.lrX, y + 0, x + sizeX, y + this.ulY, offset + this.hsize - this.lrX, 0, offset + this.hsize, this.ulY, null);
/*     */ 
/*     */       
/* 178 */       g.drawImage(getImage(), x + 0, y + this.ulY, x + this.ulX, y + sizeY - this.lrY, offset + 0, this.ulY, offset + this.ulX, this.vsize - this.lrY, null);
/*     */ 
/*     */       
/* 181 */       g.drawImage(getImage(), x + sizeX - this.lrX, y + this.ulY, x + sizeX, y + sizeY - this.lrY, offset + this.hsize - this.lrX, this.ulY, offset + this.hsize, this.vsize - this.lrY, null);
/*     */ 
/*     */       
/* 184 */       g.drawImage(getImage(), x + 0, y + sizeY - this.lrY, x + this.ulX, y + sizeY, offset + 0, this.vsize - this.lrY, offset + this.ulX, this.vsize, null);
/*     */ 
/*     */       
/* 187 */       g.drawImage(getImage(), x + this.ulX, y + sizeY - this.lrY, x + sizeX - this.lrX, y + sizeY, offset + this.ulX, this.vsize - this.lrY, offset + this.hsize - this.lrX, this.vsize, null);
/*     */ 
/*     */       
/* 190 */       g.drawImage(getImage(), x + sizeX - this.lrX, y + sizeY - this.lrY, x + sizeX, y + sizeY, offset + this.hsize - this.lrX, this.vsize - this.lrY, offset + this.hsize, this.vsize, null);
/* 191 */       g.drawImage(getImage(), x + this.ulX, y + this.ulY, x + sizeX - this.lrX, y + sizeY - this.lrY, offset + this.ulX, this.ulY, offset + this.hsize - this.lrX, this.vsize - this.lrY, null);
/*     */     } else {
/*     */       
/* 194 */       g.drawImage(getImage(), x, y, x + sizeX, y + sizeY, offset, 0, offset + this.hsize, this.vsize, null);
/*     */     } 
/*     */   }
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
/*     */   public void drawCentered(Graphics g, int index, int sizeX, int sizeY) {
/* 209 */     int offset = index * getHsize();
/*     */     
/* 211 */     int w = getHsize();
/* 212 */     int h = getVsize();
/*     */     
/* 214 */     int sx = (sizeX - w) / 2;
/* 215 */     int sy = (sizeY - h) / 2;
/*     */     
/* 217 */     g.drawImage(getImage(), sx, sy, sx + w, sy + h, offset, 0, offset + w, h, null);
/*     */   }
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
/*     */   public void drawCentered(Graphics g, int index, int x, int y, int sizeX, int sizeY) {
/* 234 */     int offset = index * getHsize();
/*     */     
/* 236 */     int w = getHsize();
/* 237 */     int h = getVsize();
/*     */     
/* 239 */     int sx = (sizeX - w) / 2;
/* 240 */     int sy = (sizeY - h) / 2;
/*     */     
/* 242 */     g.drawImage(getImage(), x + sx, y + sy, x + sx + w, y + sy + h, offset, 0, offset + w, h, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 252 */   public int getHsize() { return this.hsize; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 261 */   public int getVsize() { return this.vsize; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 270 */   public Dimension getSize() { return new Dimension(this.hsize, this.vsize); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void calculateSizes() {
/* 278 */     this.hsize = getImage().getWidth(null) / this.nrImages;
/* 279 */     this.vsize = getImage().getHeight(null);
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\skin\Skin.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */