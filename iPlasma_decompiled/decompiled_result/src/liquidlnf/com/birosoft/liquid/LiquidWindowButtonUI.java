/*     */ package com.birosoft.liquid;
/*     */ 
/*     */ import com.birosoft.liquid.skin.Skin;
/*     */ import com.birosoft.liquid.skin.SkinSimpleButtonIndexModel;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Rectangle;
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
/*     */ public class LiquidWindowButtonUI
/*     */   extends LiquidButtonUI
/*     */ {
/*     */   boolean isRestore;
/*     */   int type;
/*     */   public static final int CLOSE = 0;
/*     */   public static final int MAXIMIZE = 1;
/*     */   public static final int MINIMIZE = 2;
/*     */   public static final int RESTORE = 3;
/*  42 */   private static final String[] files = { "closebutton.png", "maximizebutton.png", "minimizebutton.png", "restorebutton.png" };
/*     */ 
/*     */   
/*  45 */   private static SkinSimpleButtonIndexModel indexModel = new SkinSimpleButtonIndexModel(0, 1, 2, 4);
/*     */   
/*  47 */   static Skin[] skins = new Skin[4];
/*     */ 
/*     */   
/*  50 */   public static ComponentUI createUI(JComponent c) { throw new IllegalStateException("Must not be used this way."); }
/*     */   
/*     */   LiquidWindowButtonUI(int type) {
/*     */     this.isRestore = true;
/*  54 */     this.type = type;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintFocus(Graphics g, AbstractButton b, Rectangle viewRect, Rectangle textRect, Rectangle iconRect) {}
/*     */   
/*     */   public void paint(Graphics g, JComponent c) {
/*  61 */     SpecialUIButton button = (SpecialUIButton)c;
/*     */     
/*  63 */     indexModel.setButton(button);
/*  64 */     int index = indexModel.getIndexForState();
/*     */     
/*  66 */     if (button.frame != null) {
/*  67 */       if (button.frame.isMaximum() && this.type == 1) {
/*  68 */         getSkin(3).draw(g, index, button.getWidth(), button.getHeight());
/*     */       } else {
/*     */         
/*  71 */         getSkin(this.type).draw(g, index, button.getWidth(), button.getHeight());
/*     */       
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/*  78 */       getSkin(this.type).draw(g, index, button.getWidth(), button.getHeight());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected static Skin getSkin(int type) {
/*  84 */     if (skins[type] == null) {
/*  85 */       skins[type] = new Skin(files[type], 5, 2);
/*     */     }
/*     */ 
/*     */     
/*  89 */     return skins[type];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  98 */   public static LiquidWindowButtonUI createButtonUIForType(int type) { return new LiquidWindowButtonUI(type); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   public Dimension getPreferredSize(JComponent c) { return new Dimension(getSkin(this.type).getHsize(), getSkin(this.type).getVsize()); }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\LiquidWindowButtonUI.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */