/*     */ package com.birosoft.liquid;
/*     */ 
/*     */ import com.birosoft.liquid.skin.Skin;
/*     */ import com.birosoft.liquid.skin.SkinSimpleButtonIndexModel;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Insets;
/*     */ import javax.swing.plaf.basic.BasicArrowButton;
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
/*     */ public class LiquidScrollButton
/*     */   extends BasicArrowButton
/*     */ {
/*     */   private boolean isFreeStanding = false;
/*     */   private int buttonWidth;
/*  32 */   private static Skin skinUp = new Skin("scrollbuttonsup.png", 4, 0);
/*     */   
/*  34 */   private static Skin skinDown = new Skin("scrollbuttonsdown.png", 4, 0);
/*     */   
/*  36 */   private static Skin skinLeft = new Skin("scrollbuttonsleft.png", 4, 0);
/*     */   
/*  38 */   private static Skin skinRight = new Skin("scrollbuttonsright.png", 4, 0);
/*     */ 
/*     */   
/*     */   private Skin skin;
/*     */   
/*  43 */   private SkinSimpleButtonIndexModel indexModel = new SkinSimpleButtonIndexModel();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LiquidScrollButton(int orientation, int width, boolean freeStanding) {
/*  51 */     super(orientation);
/*  52 */     this.buttonWidth = width;
/*  53 */     this.isFreeStanding = freeStanding;
/*  54 */     setBorder(null);
/*  55 */     setRolloverEnabled(true);
/*  56 */     setMargin(new Insets(0, 0, 0, 0));
/*  57 */     setBorder(null);
/*  58 */     this.indexModel.setButton(this);
/*     */     
/*  60 */     switch (orientation) {
/*     */       
/*     */       case 1:
/*  63 */         this.skin = skinUp;
/*     */         break;
/*     */       case 5:
/*  66 */         this.skin = skinDown;
/*     */         break;
/*     */       case 3:
/*  69 */         this.skin = skinRight;
/*     */         break;
/*     */       case 7:
/*  72 */         this.skin = skinLeft;
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  79 */   public void setFreeStanding(boolean freeStanding) { this.isFreeStanding = freeStanding; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  88 */   public void paint(Graphics g) { this.skin.draw(g, this.indexModel.getIndexForState(), getWidth(), getHeight()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   public Dimension getPreferredSize() { return new Dimension(skinUp.getHsize(), skinUp.getVsize()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 106 */   public static Skin getSkinUp() { return skinUp; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 115 */   public static Skin getSkinDown() { return skinDown; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 124 */   public static Skin getSkinLeft() { return skinLeft; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 133 */   public static Skin getSkinRight() { return skinRight; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 142 */   public Skin getSkin() { return this.skin; }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\LiquidScrollButton.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */