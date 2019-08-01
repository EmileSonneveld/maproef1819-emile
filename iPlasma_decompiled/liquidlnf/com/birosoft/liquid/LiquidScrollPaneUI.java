/*     */ package com.birosoft.liquid;
/*     */ 
/*     */ import java.awt.event.MouseWheelEvent;
/*     */ import java.awt.event.MouseWheelListener;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JScrollBar;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.basic.BasicScrollPaneUI;
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
/*     */ public class LiquidScrollPaneUI
/*     */   extends BasicScrollPaneUI
/*     */   implements PropertyChangeListener
/*     */ {
/*  38 */   public static ComponentUI createUI(JComponent c) { return new LiquidScrollPaneUI(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class MouseWheelHandler
/*     */     implements MouseWheelListener
/*     */   {
/*     */     private final LiquidScrollPaneUI this$0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  55 */     protected MouseWheelHandler(LiquidScrollPaneUI this$0) { this.this$0 = this$0; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void mouseWheelMoved(MouseWheelEvent e) {
/*  66 */       if (this.this$0.scrollpane.isWheelScrollingEnabled() && e.getScrollAmount() != 0) {
/*     */         
/*  68 */         JScrollBar toScroll = this.this$0.scrollpane.getVerticalScrollBar();
/*  69 */         int direction = 0;
/*  70 */         int length = toScroll.getHeight();
/*     */         
/*  72 */         e; if (toScroll == null || !toScroll.isVisible() || e.getModifiers() == 8) {
/*     */           
/*  74 */           toScroll = this.this$0.scrollpane.getHorizontalScrollBar();
/*  75 */           if (toScroll == null || !toScroll.isVisible()) {
/*     */             return;
/*     */           }
/*     */           
/*  79 */           length = toScroll.getWidth();
/*     */         } 
/*  81 */         direction = (e.getWheelRotation() < 0) ? -1 : 1;
/*  82 */         if (e.getScrollType() == 0) {
/*     */           
/*  84 */           int newValue = toScroll.getValue() + e.getWheelRotation() * length / toScroll.getUnitIncrement() * 2;
/*  85 */           toScroll.setValue(newValue);
/*     */         }
/*  87 */         else if (e.getScrollType() == 1) {
/*     */           
/*  89 */           int newValue = toScroll.getValue() + e.getWheelRotation() * length / toScroll.getBlockIncrement() * 2;
/*  90 */           toScroll.setValue(newValue);
/*     */         } 
/*     */       } 
/*     */     }
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
/*     */ 
/*     */   
/* 108 */   protected MouseWheelListener createMouseWheelListener() { return new MouseWheelHandler(this); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void installUI(JComponent c) {
/* 119 */     super.installUI(c);
/*     */     
/* 121 */     this.scrollpane.getHorizontalScrollBar().putClientProperty("JScrollBar.isFreeStanding", Boolean.FALSE);
/*     */     
/* 123 */     this.scrollpane.getVerticalScrollBar().putClientProperty("JScrollBar.isFreeStanding", Boolean.FALSE);
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
/* 135 */   protected PropertyChangeListener createScrollBarSwapListener() { return this; }
/*     */   
/*     */   public void propertyChange(PropertyChangeEvent event) {}
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\LiquidScrollPaneUI.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */