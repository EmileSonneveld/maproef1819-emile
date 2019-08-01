/*     */ package com.birosoft.liquid;
/*     */ 
/*     */ import com.birosoft.liquid.skin.Skin;
/*     */ import com.birosoft.liquid.skin.SkinSimpleButtonIndexModel;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import javax.swing.AbstractButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.plaf.ComponentUI;
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
/*     */ public class LiquidSpinnerButtonUI
/*     */   extends LiquidButtonUI
/*     */ {
/*     */   int type;
/*  36 */   private static final String[] files = { "spinnerup.png", "spinnerdown.png" };
/*     */   
/*  38 */   private static final String[] arrowfiles = { "spinneruparrows.png", "spinnerdownarrows.png" };
/*     */ 
/*     */   
/*  41 */   private static SkinSimpleButtonIndexModel indexModel = new SkinSimpleButtonIndexModel(0, 1, 2, 4);
/*     */   
/*  43 */   static Skin[] skins = new Skin[2];
/*  44 */   static Skin[] arrowSkins = new Skin[2];
/*     */ 
/*     */ 
/*     */   
/*  48 */   public static ComponentUI createUI(JComponent c) { throw new IllegalStateException("Must not be used this way."); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   LiquidSpinnerButtonUI(int type) { this.type = type; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void paint(Graphics g, JComponent c) {
/*  64 */     AbstractButton button = (AbstractButton)c;
/*     */     
/*  66 */     indexModel.setButton(button);
/*  67 */     int index = indexModel.getIndexForState();
/*     */     
/*  69 */     getSkin(this.type).draw(g, index, button.getWidth(), button.getHeight());
/*     */     
/*  71 */     getArrowSkin(this.type).drawCentered(g, index, button.getWidth(), button.getHeight());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Skin getSkin(int type) {
/*  82 */     switch (type) {
/*     */       
/*     */       case 1:
/*  85 */         skins[0] = new Skin(files[0], 4, 2);
/*  86 */         return skins[0];
/*     */       case 5:
/*  88 */         skins[1] = new Skin(files[1], 4, 2);
/*  89 */         return skins[1];
/*     */     } 
/*  91 */     throw new IllegalStateException("type must be either SwingConstants.SOUTH or SwingConstants.NORTH for XPSpinnerButton");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Skin getArrowSkin(int type) {
/* 102 */     switch (type) {
/*     */       
/*     */       case 1:
/* 105 */         arrowSkins[0] = new Skin(arrowfiles[0], 4, 2);
/* 106 */         return arrowSkins[0];
/*     */       case 5:
/* 108 */         arrowSkins[1] = new Skin(arrowfiles[1], 4, 2);
/* 109 */         return arrowSkins[1];
/*     */     } 
/* 111 */     throw new IllegalStateException("type must be either SwingConstants.SOUTH or SwingConstants.NORTH for XPSpinnerButton");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 120 */   public Dimension getPreferredSize(JComponent c) { return new Dimension(getSkin(this.type).getHsize(), getSkin(this.type).getVsize()); }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\LiquidSpinnerButtonUI.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */