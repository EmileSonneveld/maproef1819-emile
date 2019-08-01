/*    */ package com.birosoft.liquid;
/*    */ 
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Rectangle;
/*    */ import java.beans.PropertyChangeEvent;
/*    */ import java.beans.PropertyChangeListener;
/*    */ import javax.swing.AbstractButton;
/*    */ import javax.swing.ButtonModel;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.JToggleButton;
/*    */ import javax.swing.plaf.ComponentUI;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LiquidToggleButtonUI
/*    */   extends LiquidButtonUI
/*    */ {
/* 42 */   private static final LiquidToggleButtonUI toggleButtonUI = new LiquidToggleButtonUI();
/*    */   
/*    */   private static final String propertyPrefix = "ToggleButton.";
/*    */ 
/*    */   
/* 47 */   protected String getPropertyPrefix() { return "ToggleButton."; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static ComponentUI createUI(JComponent c) {
/* 57 */     JToggleButton b = (JToggleButton)c;
/* 58 */     b.setRolloverEnabled(true);
/*    */ 
/*    */     
/* 61 */     c.setOpaque(false);
/* 62 */     c.addPropertyChangeListener("opaque", new PropertyChangeListener(c)
/*    */         {
/* 64 */           public void propertyChange(PropertyChangeEvent evt) { this.val$c.setOpaque(false); }
/*    */           private final JComponent val$c;
/*    */         });
/* 67 */     return toggleButtonUI;
/*    */   }
/*    */ 
/*    */   
/*    */   public void paint(Graphics g, JComponent c) {
/* 72 */     AbstractButton button = (AbstractButton)c;
/* 73 */     ButtonModel model = button.getModel();
/*    */     
/* 75 */     this.buttonIndexModel.setButton(button);
/* 76 */     this.buttonIndexModel.setCheckForDefaultButton(false);
/* 77 */     int index = this.buttonIndexModel.getIndexForState();
/* 78 */     if (index > 3) index -= 4; 
/* 79 */     if ((model.isArmed() && model.isPressed()) || model.isSelected())
/* 80 */       index = 2; 
/* 81 */     if (button.hasFocus() && index == 0) index = 1; 
/* 82 */     if (button.getHeight() < 21 || button.getWidth() < 21) {
/* 83 */       getSkinToolbar().draw(g, index, button.getWidth(), button.getHeight());
/*    */       
/* 85 */       button.setFocusPainted(false);
/*    */     }
/* 87 */     else if (button.getClientProperty("JToolBar.isToolbarButton") == Boolean.TRUE) {
/* 88 */       getSkinToolbar().draw(g, index, button.getWidth(), button.getHeight());
/*    */     } else {
/* 90 */       getSkinButton().draw(g, index, button.getWidth(), button.getHeight());
/*    */     } 
/*    */     
/* 93 */     if (index == 4 && button.isFocusPainted()) {
/* 94 */       Rectangle bounds = button.getBounds();
/* 95 */       paintFocus(g, bounds.height / 2 - 5);
/*    */     } 
/* 97 */     super.paint(g, c);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\LiquidToggleButtonUI.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */