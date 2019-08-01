/*     */ package com.birosoft.liquid.skin;
/*     */ 
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
/*     */ public class SkinMenuItem
/*     */   extends SkinElement
/*     */ {
/*     */   private int leftOffset;
/*     */   private int leftRolloverOffset;
/*     */   private int rightOffset;
/*     */   private int rightRolloverOffset;
/*     */   private int hsize;
/*     */   private int vsize;
/*     */   private int roundedSize;
/*     */   private boolean useDefaultButton = false;
/*     */   private boolean useSelectedButton = false;
/*     */   private boolean doneAllCalculations = false;
/*     */   
/*     */   public SkinMenuItem(String fileName, int leftOffset, int leftRolloverOffset, int rightOffset, int rightRolloverOffset, int roundedSize) {
/*  32 */     super(fileName, false);
/*  33 */     this.leftOffset = leftOffset;
/*  34 */     this.leftRolloverOffset = leftRolloverOffset;
/*  35 */     this.rightOffset = rightOffset;
/*  36 */     this.rightRolloverOffset = rightRolloverOffset;
/*  37 */     this.roundedSize = roundedSize;
/*  38 */     calculateSizes();
/*     */   }
/*     */ 
/*     */   
/*     */   public void draw(Graphics g, boolean isEnabled, boolean isSelected, boolean isPushed, boolean isRollover, int pSizeX, int leftSize, int sizeY) {
/*  43 */     int offsetL = 0, offsetR = 0;
/*     */     
/*  45 */     if (isSelected) {
/*     */       
/*  47 */       offsetL = this.leftRolloverOffset;
/*  48 */       offsetR = this.rightRolloverOffset;
/*     */     } else {
/*     */       
/*  51 */       offsetL = this.leftOffset;
/*  52 */       offsetR = this.rightOffset;
/*     */     } 
/*     */     
/*  55 */     offsetL = this.hsize * offsetL;
/*  56 */     offsetR = this.hsize * offsetR;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  61 */     int offset = offsetL;
/*  62 */     int sizeX = leftSize;
/*  63 */     if (this.roundedSize > 0) {
/*     */ 
/*     */       
/*  66 */       g.drawImage(getImage(), 0, 0, this.roundedSize, this.roundedSize, offset + 0, 0, offset + this.roundedSize, this.roundedSize, null);
/*     */ 
/*     */       
/*  69 */       g.drawImage(getImage(), this.roundedSize, 0, sizeX - this.roundedSize, this.roundedSize, offset + this.roundedSize, 0, offset + this.hsize - this.roundedSize, this.roundedSize, null);
/*     */ 
/*     */       
/*  72 */       g.drawImage(getImage(), sizeX - this.roundedSize, 0, sizeX, this.roundedSize, offset + this.hsize - this.roundedSize, 0, offset + this.hsize, this.roundedSize, null);
/*     */ 
/*     */       
/*  75 */       g.drawImage(getImage(), 0, this.roundedSize, this.roundedSize, sizeY - this.roundedSize, offset + 0, this.roundedSize, offset + this.roundedSize, this.vsize - this.roundedSize, null);
/*     */ 
/*     */       
/*  78 */       g.drawImage(getImage(), sizeX - this.roundedSize, this.roundedSize, sizeX, sizeY - this.roundedSize, offset + this.hsize - this.roundedSize, this.roundedSize, offset + this.hsize, this.vsize - this.roundedSize, null);
/*     */ 
/*     */       
/*  81 */       g.drawImage(getImage(), 0, sizeY - this.roundedSize, this.roundedSize, sizeY, offset + 0, this.vsize - this.roundedSize, offset + this.roundedSize, this.vsize, null);
/*     */ 
/*     */       
/*  84 */       g.drawImage(getImage(), this.roundedSize, sizeY - this.roundedSize, sizeX - this.roundedSize, sizeY, offset + this.roundedSize, this.vsize - this.roundedSize, offset + this.hsize - this.roundedSize, this.vsize, null);
/*     */ 
/*     */       
/*  87 */       g.drawImage(getImage(), sizeX - this.roundedSize, sizeY - this.roundedSize, sizeX, sizeY, offset + this.hsize - this.roundedSize, this.vsize - this.roundedSize, offset + this.hsize, this.vsize, null);
/*     */     } 
/*  89 */     g.drawImage(getImage(), this.roundedSize, this.roundedSize, sizeX - this.roundedSize, sizeY - this.roundedSize, offset + this.roundedSize, this.roundedSize, offset + this.hsize - this.roundedSize, this.vsize - this.roundedSize, null);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  94 */     int offset = offsetR;
/*  95 */     int sizeX = pSizeX - leftSize;
/*  96 */     g.translate(leftSize, 0);
/*  97 */     if (this.roundedSize > 0) {
/*     */ 
/*     */       
/* 100 */       g.drawImage(getImage(), 0, 0, this.roundedSize, this.roundedSize, offset + 0, 0, offset + this.roundedSize, this.roundedSize, null);
/*     */ 
/*     */       
/* 103 */       g.drawImage(getImage(), this.roundedSize, 0, sizeX - this.roundedSize, this.roundedSize, offset + this.roundedSize, 0, offset + this.hsize - this.roundedSize, this.roundedSize, null);
/*     */ 
/*     */       
/* 106 */       g.drawImage(getImage(), sizeX - this.roundedSize, 0, sizeX, this.roundedSize, offset + this.hsize - this.roundedSize, 0, offset + this.hsize, this.roundedSize, null);
/*     */ 
/*     */       
/* 109 */       g.drawImage(getImage(), 0, this.roundedSize, this.roundedSize, sizeY - this.roundedSize, offset + 0, this.roundedSize, offset + this.roundedSize, this.vsize - this.roundedSize, null);
/*     */ 
/*     */       
/* 112 */       g.drawImage(getImage(), sizeX - this.roundedSize, this.roundedSize, sizeX, sizeY - this.roundedSize, offset + this.hsize - this.roundedSize, this.roundedSize, offset + this.hsize, this.vsize - this.roundedSize, null);
/*     */ 
/*     */       
/* 115 */       g.drawImage(getImage(), 0, sizeY - this.roundedSize, this.roundedSize, sizeY, offset + 0, this.vsize - this.roundedSize, offset + this.roundedSize, this.vsize, null);
/*     */ 
/*     */       
/* 118 */       g.drawImage(getImage(), this.roundedSize, sizeY - this.roundedSize, sizeX - this.roundedSize, sizeY, offset + this.roundedSize, this.vsize - this.roundedSize, offset + this.hsize - this.roundedSize, this.vsize, null);
/*     */ 
/*     */       
/* 121 */       g.drawImage(getImage(), sizeX - this.roundedSize, sizeY - this.roundedSize, sizeX, sizeY, offset + this.hsize - this.roundedSize, this.vsize - this.roundedSize, offset + this.hsize, this.vsize, null);
/*     */     } 
/* 123 */     g.drawImage(getImage(), this.roundedSize, this.roundedSize, sizeX - this.roundedSize, sizeY - this.roundedSize, offset + this.roundedSize, this.roundedSize, offset + this.hsize - this.roundedSize, this.vsize - this.roundedSize, null);
/*     */     
/* 125 */     g.translate(-leftSize, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHsize() {
/* 131 */     if (!this.doneAllCalculations) {
/*     */       
/* 133 */       calculateSizes();
/* 134 */       this.doneAllCalculations = true;
/*     */     } 
/* 136 */     return this.hsize;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getVsize() {
/* 141 */     if (!this.doneAllCalculations) {
/*     */       
/* 143 */       calculateSizes();
/* 144 */       this.doneAllCalculations = true;
/*     */     } 
/* 146 */     return this.vsize;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void calculateSizes() {
/* 151 */     int maxOffset = 0;
/* 152 */     if (this.leftOffset > maxOffset)
/* 153 */       maxOffset = this.leftOffset; 
/* 154 */     if (this.leftRolloverOffset > maxOffset)
/* 155 */       maxOffset = this.leftRolloverOffset; 
/* 156 */     if (this.rightOffset > maxOffset)
/* 157 */       maxOffset = this.rightOffset; 
/* 158 */     if (this.rightRolloverOffset > maxOffset) {
/* 159 */       maxOffset = this.rightRolloverOffset;
/*     */     }
/* 161 */     this.hsize = getImage().getWidth(null) / (maxOffset + 1);
/* 162 */     this.vsize = getImage().getHeight(null);
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\skin\SkinMenuItem.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */