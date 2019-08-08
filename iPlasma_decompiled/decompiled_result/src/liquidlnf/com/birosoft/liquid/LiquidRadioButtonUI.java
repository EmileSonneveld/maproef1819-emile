/*    */ package com.birosoft.liquid;
/*    */ 
/*    */ import java.awt.BasicStroke;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Rectangle;
/*    */ import java.beans.PropertyChangeEvent;
/*    */ import java.beans.PropertyChangeListener;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.JRadioButton;
/*    */ import javax.swing.plaf.ComponentUI;
/*    */ import javax.swing.plaf.basic.BasicRadioButtonUI;
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
/*    */ public class LiquidRadioButtonUI
/*    */   extends BasicRadioButtonUI
/*    */ {
/* 30 */   private static final LiquidRadioButtonUI metouiaRadioButtonUI = new LiquidRadioButtonUI();
/*    */   
/* 32 */   private static BasicStroke focusStroke = new BasicStroke(1.0F, 0, 2, 1.0F, new float[] { 1.0F, 1.0F }, 0.0F);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static LiquidRadioButtonIcon skinnedIcon;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static ComponentUI createUI(JComponent c) {
/* 43 */     if (c instanceof JRadioButton) {
/*    */       
/* 45 */       JRadioButton jb = (JRadioButton)c;
/* 46 */       jb.setRolloverEnabled(true);
/* 47 */       jb.setOpaque(false);
/* 48 */       jb.addPropertyChangeListener("opaque", new PropertyChangeListener(jb) {
/*    */             private final JRadioButton val$jb;
/*    */             
/*    */             public void propertyChange(PropertyChangeEvent evt) {
/* 52 */               this.val$jb.setOpaque(false);
/*    */             }
/*    */           });
/*    */     } 
/* 56 */     return metouiaRadioButtonUI;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void installUI(JComponent arg0) {
/* 65 */     super.installUI(arg0);
/* 66 */     this.icon = getSkinnedIcon();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected LiquidRadioButtonIcon getSkinnedIcon() {
/* 75 */     if (skinnedIcon == null)
/* 76 */       skinnedIcon = new LiquidRadioButtonIcon(); 
/* 77 */     return skinnedIcon;
/*    */   }
/*    */   
/*    */   protected void paintFocus(Graphics g, Rectangle t, Dimension arg2) {}
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\LiquidRadioButtonUI.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */