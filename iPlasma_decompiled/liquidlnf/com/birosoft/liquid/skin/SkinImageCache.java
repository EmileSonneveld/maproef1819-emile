/*     */ package com.birosoft.liquid.skin;
/*     */ 
/*     */ import java.awt.Graphics;
/*     */ import java.awt.GraphicsConfiguration;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.awt.Image;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.util.HashMap;
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
/*     */ public class SkinImageCache
/*     */ {
/*  36 */   private static SkinImageCache instance = new SkinImageCache();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static  {
/*  44 */     ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
/*  45 */     conf = ge.getDefaultScreenDevice().getDefaultConfiguration();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  50 */   private HashMap map = new HashMap();
/*  51 */   private HashMap iconMap = new HashMap();
/*  52 */   private HashMap bufferedMap = new HashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static GraphicsConfiguration conf;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Image getAutomaticImage(String fileName) {
/*  64 */     Image ret = (Image)this.map.get(fileName);
/*  65 */     if (ret == null) {
/*     */       
/*  67 */       Image img = SecretLoader.loadImage(fileName);
/*  68 */       this.map.put(fileName, img);
/*  69 */       return img;
/*     */     } 
/*  71 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   public Image getImage(String fileName) { return getAutomaticImage(fileName); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage getBufferedImage(String fileName) {
/*  91 */     BufferedImage b = (BufferedImage)this.bufferedMap.get(fileName);
/*  92 */     if (b != null) {
/*  93 */       return b;
/*     */     }
/*  95 */     Image img = getImage(fileName);
/*     */     
/*  97 */     if (img instanceof BufferedImage)
/*     */     {
/*  99 */       return (BufferedImage)img;
/*     */     }
/*     */     
/* 102 */     int w = img.getWidth(null);
/* 103 */     int h = img.getHeight(null);
/*     */     
/* 105 */     BufferedImage img2 = conf.createCompatibleImage(w, h);
/* 106 */     Graphics g = img2.getGraphics();
/* 107 */     g.drawImage(img, 0, 0, w, h, 0, 0, w, h, null);
/* 108 */     this.bufferedMap.put(fileName, img2);
/* 109 */     return img2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 118 */   public static SkinImageCache getInstance() { return instance; }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\skin\SkinImageCache.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */