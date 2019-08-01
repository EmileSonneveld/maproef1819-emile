/*    */ package com.birosoft.liquid;
/*    */ 
/*    */ import com.birosoft.liquid.skin.Skin;
/*    */ import com.birosoft.liquid.skin.SkinToggleButtonIndexModel;
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics;
/*    */ import java.io.Serializable;
/*    */ import javax.swing.AbstractButton;
/*    */ import javax.swing.Icon;
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
/*    */ 
/*    */ 
/*    */ public class LiquidCheckBoxIcon
/*    */   implements Icon, UIResource, Serializable
/*    */ {
/*    */   protected static Skin skin;
/* 32 */   private SkinToggleButtonIndexModel indexModel = new SkinToggleButtonIndexModel();
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
/* 43 */   protected int getControlSize() { return getIconWidth(); }
/*    */ 
/*    */   
/*    */   public void paintIcon(Component c, Graphics g, int x, int y) {
/* 47 */     AbstractButton button = (AbstractButton)c;
/*    */     
/* 49 */     this.indexModel.setButton(button);
/*    */     
/* 51 */     int index = this.indexModel.getIndexForState();
/* 52 */     getSkin().draw(g, index, x, y, getSkin().getHsize(), getSkin().getVsize());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 57 */   public int getIconWidth() { return getSkin().getHsize(); }
/*    */ 
/*    */ 
/*    */   
/* 61 */   public int getIconHeight() { return getSkin().getVsize(); }
/*    */ 
/*    */   
/*    */   public Skin getSkin() {
/* 65 */     if (skin == null) {
/* 66 */       skin = new Skin("checkbox.png", 8, 0);
/*    */     }
/*    */     
/* 69 */     return skin;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\LiquidCheckBoxIcon.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */