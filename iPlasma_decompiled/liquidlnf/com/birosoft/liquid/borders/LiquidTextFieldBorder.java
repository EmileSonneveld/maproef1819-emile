/*    */ package com.birosoft.liquid.borders;
/*    */ 
/*    */ import com.birosoft.liquid.skin.Skin;
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Insets;
/*    */ import javax.swing.border.AbstractBorder;
/*    */ import javax.swing.plaf.UIResource;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LiquidTextFieldBorder
/*    */   extends AbstractBorder
/*    */   implements UIResource
/*    */ {
/* 28 */   private static final Insets defaultInsets = new Insets(3, 5, 3, 5);
/*    */ 
/*    */   
/*    */   private Insets insets;
/*    */   
/*    */   static Skin skin;
/*    */ 
/*    */   
/* 36 */   public LiquidTextFieldBorder() { this.insets = defaultInsets; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 41 */   public LiquidTextFieldBorder(Insets insets) { this.insets = insets; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 52 */   public Insets getBorderInsets(Component c) { return this.insets; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Skin getSkin() {
/* 60 */     if (skin == null)
/*    */     {
/* 62 */       skin = new Skin("textbox.png", 2, 3);
/*    */     }
/*    */     
/* 65 */     return skin;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
/* 74 */     int index = c.isEnabled() ? 0 : 1;
/* 75 */     getSkin().draw(g, index, w, h);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\borders\LiquidTextFieldBorder.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */