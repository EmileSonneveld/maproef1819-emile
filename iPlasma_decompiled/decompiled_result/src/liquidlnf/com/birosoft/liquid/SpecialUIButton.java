/*    */ package com.birosoft.liquid;
/*    */ 
/*    */ import java.awt.Frame;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.JInternalFrame;
/*    */ import javax.swing.plaf.ButtonUI;
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
/*    */ public class SpecialUIButton
/*    */   extends JButton
/*    */ {
/*    */   ButtonUI myUI;
/*    */   JInternalFrame frame;
/*    */   Frame window;
/*    */   
/*    */   public SpecialUIButton(ButtonUI ui) {
/* 29 */     this.ui = ui;
/* 30 */     this.myUI = ui;
/* 31 */     ui.installUI(this);
/*    */   }
/*    */   
/*    */   public SpecialUIButton(ButtonUI ui, JInternalFrame frame) {
/* 35 */     this.ui = ui;
/* 36 */     this.myUI = ui;
/* 37 */     ui.installUI(this);
/* 38 */     this.frame = frame;
/* 39 */     setOpaque(false);
/* 40 */     setFocusPainted(false);
/*    */   }
/*    */   
/*    */   public SpecialUIButton(ButtonUI ui, Frame frame) {
/* 44 */     this.ui = ui;
/* 45 */     this.myUI = ui;
/* 46 */     ui.installUI(this);
/* 47 */     this.window = frame;
/* 48 */     setOpaque(false);
/* 49 */     setFocusPainted(false);
/*    */   }
/*    */   
/*    */   public void setUI(ButtonUI ui) {}
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\SpecialUIButton.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */