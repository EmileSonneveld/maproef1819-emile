/*    */ package com.birosoft.liquid;
/*    */ 
/*    */ import java.awt.Graphics;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.UIManager;
/*    */ import javax.swing.plaf.ComponentUI;
/*    */ import javax.swing.plaf.basic.BasicTextFieldUI;
/*    */ import javax.swing.text.JTextComponent;
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
/*    */ 
/*    */ 
/*    */ public class LiquidTextFieldUI
/*    */   extends BasicTextFieldUI
/*    */ {
/* 31 */   public static ComponentUI createUI(JComponent c) { return new LiquidTextFieldUI(); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 38 */   public void installUI(JComponent c) { super.installUI(c); }
/*    */ 
/*    */   
/*    */   protected void paintBackground(Graphics g) {
/* 42 */     JTextComponent editor = getComponent();
/* 43 */     if (editor.isEnabled()) {
/* 44 */       g.setColor(editor.getBackground());
/*    */     } else {
/* 46 */       g.setColor(UIManager.getDefaults().getColor("TextField.disabledBackground"));
/*    */     } 
/* 48 */     g.fillRect(0, 0, editor.getWidth(), editor.getHeight());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\LiquidTextFieldUI.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */