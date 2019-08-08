/*    */ package com.birosoft.liquid;
/*    */ 
/*    */ import com.birosoft.liquid.skin.Skin;
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
/*    */ class LiquidRadioButtonIcon
/*    */   extends LiquidCheckBoxIcon
/*    */ {
/*    */   static Skin skin;
/*    */   
/*    */   public Skin getSkin() {
/* 23 */     if (skin == null)
/* 24 */       skin = new Skin("radiobutton.png", 8, 0); 
/* 25 */     return skin;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\LiquidRadioButtonIcon.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */