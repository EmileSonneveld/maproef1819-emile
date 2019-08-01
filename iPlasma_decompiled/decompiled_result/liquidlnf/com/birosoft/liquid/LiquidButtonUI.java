/*     */ package com.birosoft.liquid;
/*     */ 
/*     */ import com.birosoft.liquid.skin.Skin;
/*     */ import com.birosoft.liquid.skin.SkinToggleButtonIndexModel;
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Rectangle;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import javax.swing.AbstractButton;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.basic.BasicButtonUI;
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
/*     */ public class LiquidButtonUI
/*     */   extends BasicButtonUI
/*     */ {
/*     */   public static final boolean HINT_DO_NOT_PAINT_TOOLBARBUTTON_IF_NO_MOUSE_OVER = true;
/*  43 */   private static final LiquidButtonUI buttonUI = new LiquidButtonUI();
/*     */ 
/*     */   
/*     */   static Skin skinButton;
/*     */ 
/*     */   
/*     */   private static Skin skinToolbar;
/*     */   
/*  51 */   SkinToggleButtonIndexModel buttonIndexModel = new SkinToggleButtonIndexModel(true);
/*     */   
/*  53 */   SkinToggleButtonIndexModel toolbarIndexModel = new SkinToggleButtonIndexModel();
/*     */ 
/*     */   
/*  56 */   static BasicStroke focusStroke = new BasicStroke(1.0F, 0, 2, 1.0F, new float[] { 1.0F, 1.0F }, 1.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   public void installDefaults(AbstractButton b) { super.installDefaults(b); }
/*     */ 
/*     */ 
/*     */   
/*  67 */   public void uninstallDefaults(AbstractButton b) { super.uninstallDefaults(b); }
/*     */ 
/*     */   
/*     */   protected void paintFocus(Graphics g, AbstractButton b, Rectangle viewRect, Rectangle textRect, Rectangle iconRect) {
/*  71 */     Rectangle bounds = b.getBounds();
/*  72 */     int offset = bounds.height / 2 - 5;
/*     */     
/*  74 */     if (this.buttonIndexModel.getIndexForState() == 2)
/*     */       return; 
/*  76 */     if (b.getClientProperty("JToolBar.isToolbarButton") != Boolean.TRUE && b.isFocusPainted()) {
/*  77 */       paintFocus(g, offset);
/*     */     }
/*     */   }
/*     */   
/*     */   public void paintFocus(Graphics g, int offset) {
/*  82 */     Graphics2D g2d = (Graphics2D)g;
/*  83 */     g.setColor(new Color(196, 195, 194));
/*  84 */     g2d.drawLine(6, offset, 11, offset + 5);
/*  85 */     g.setColor(new Color(175, 174, 174));
/*  86 */     g2d.drawLine(6, offset + 1, 6, offset + 11);
/*  87 */     g2d.drawLine(6, offset + 11, 11, offset + 6);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ComponentUI createUI(JComponent c) {
/*  97 */     JButton b = (JButton)c;
/*  98 */     b.setRolloverEnabled(true);
/*     */ 
/*     */     
/* 101 */     c.setOpaque(false);
/* 102 */     c.addPropertyChangeListener("opaque", new PropertyChangeListener(c)
/*     */         {
/* 104 */           public void propertyChange(PropertyChangeEvent evt) { this.val$c.setOpaque(false); }
/*     */           private final JComponent val$c;
/*     */         });
/* 107 */     return buttonUI;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void paintButtonPressed(Graphics g, AbstractButton b) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void paint(Graphics g, JComponent c) {
/* 118 */     if (c instanceof javax.swing.JToggleButton) {
/* 119 */       super.paint(g, c);
/*     */       
/*     */       return;
/*     */     } 
/* 123 */     AbstractButton button = (AbstractButton)c;
/*     */     
/* 125 */     if (button.getClientProperty("JToolBar.isToolbarButton") == Boolean.TRUE) {
/* 126 */       this.toolbarIndexModel.setButton(button);
/* 127 */       int index = this.toolbarIndexModel.getIndexForState();
/* 128 */       if (button.hasFocus() && index == 0) index = 1; 
/* 129 */       getSkinToolbar().draw(g, index, button.getWidth(), button.getHeight());
/*     */     } else {
/*     */       
/* 132 */       this.buttonIndexModel.setButton(button);
/* 133 */       this.buttonIndexModel.setCheckForDefaultButton(button instanceof JButton);
/* 134 */       int index = this.buttonIndexModel.getIndexForState();
/* 135 */       if (index > 3) index -= 4; 
/* 136 */       if (button.hasFocus() && index == 0) index = 1; 
/* 137 */       if (button.getHeight() < 21 || button.getWidth() < 21) {
/* 138 */         getSkinToolbar().draw(g, index, button.getWidth(), button.getHeight());
/*     */         
/* 140 */         button.setFocusPainted(false);
/*     */       } else {
/* 142 */         getSkinButton().draw(g, index, button.getWidth(), button.getHeight());
/*     */       } 
/* 144 */       if (index == 4 && button.isFocusPainted()) {
/* 145 */         Rectangle bounds = button.getBounds();
/* 146 */         paintFocus(g, bounds.height / 2 - 5);
/*     */       } 
/*     */     } 
/* 149 */     super.paint(g, c);
/*     */   }
/*     */   
/*     */   public Skin getSkinButton() {
/* 153 */     if (skinButton == null)
/* 154 */       skinButton = new Skin("button.png", 5, 10, 11, 11, 13); 
/* 155 */     return skinButton;
/*     */   }
/*     */   
/*     */   public Skin getSkinToolbar() {
/* 159 */     if (skinToolbar == null)
/* 160 */       skinToolbar = new Skin("toolbar.png", 8, 4, 13, 4, 10); 
/* 161 */     return skinToolbar;
/*     */   }
/*     */ 
/*     */   
/* 165 */   public void update(Graphics g, JComponent c) { paint(g, c); }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\LiquidButtonUI.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */