/*     */ package com.birosoft.liquid.skin;
/*     */ 
/*     */ import javax.swing.AbstractButton;
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
/*     */ public class SkinSimpleButtonIndexModel
/*     */ {
/*     */   private AbstractButton button;
/*     */   private int normal;
/*     */   private int rollover;
/*     */   private int pressed;
/*     */   private int disabled;
/*     */   
/*     */   public SkinSimpleButtonIndexModel() {
/*  43 */     this.normal = 0;
/*  44 */     this.rollover = 1;
/*  45 */     this.pressed = 2;
/*  46 */     this.disabled = 3;
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
/*     */   public SkinSimpleButtonIndexModel(int normal, int rollover, int pressed, int disabled) {
/*  59 */     this.normal = normal;
/*  60 */     this.rollover = rollover;
/*  61 */     this.pressed = pressed;
/*  62 */     this.disabled = disabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getIndexForState() {
/*  73 */     if (!this.button.isEnabled())
/*  74 */       return this.disabled; 
/*  75 */     if (this.button.getModel().isPressed())
/*  76 */       return this.pressed; 
/*  77 */     if (this.button.getModel().isRollover()) {
/*  78 */       return this.rollover;
/*     */     }
/*  80 */     return this.normal;
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
/*     */   public int getIndexForState(boolean isEnabled, boolean isRollover, boolean isPressed) {
/*  94 */     if (!isEnabled)
/*  95 */       return this.disabled; 
/*  96 */     if (isPressed)
/*  97 */       return this.pressed; 
/*  98 */     if (isRollover) {
/*  99 */       return this.rollover;
/*     */     }
/* 101 */     return this.normal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 109 */   public AbstractButton getButton() { return this.button; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 117 */   public void setButton(AbstractButton button) { this.button = button; }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\skin\SkinSimpleButtonIndexModel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */