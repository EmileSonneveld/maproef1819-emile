/*     */ package com.birosoft.liquid;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Rectangle;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.ListCellRenderer;
/*     */ import javax.swing.ListModel;
/*     */ import javax.swing.ListSelectionModel;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.basic.BasicListUI;
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
/*     */ public class LiquidListUI
/*     */   extends BasicListUI
/*     */ {
/*     */   private Color defaultBackground;
/*     */   
/*     */   protected void paintCell(Graphics g, int row, Rectangle rowBounds, ListCellRenderer cellRenderer, ListModel dataModel, ListSelectionModel selModel, int leadIndex) {
/*  62 */     Object value = dataModel.getElementAt(row);
/*  63 */     boolean cellHasFocus = (this.list.hasFocus() && row == leadIndex);
/*  64 */     boolean isSelected = selModel.isSelectedIndex(row);
/*     */     
/*  66 */     Component rendererComponent = cellRenderer.getListCellRendererComponent(this.list, value, row, isSelected, cellHasFocus);
/*     */ 
/*     */     
/*  69 */     if (LiquidLookAndFeel.defaultRowBackgroundMode) {
/*  70 */       if (row % 2 == 0) {
/*  71 */         if (this.defaultBackground.equals(rendererComponent.getBackground())) {
/*  72 */           rendererComponent.setBackground(LiquidLookAndFeel.getDesktopColor());
/*     */         }
/*     */       }
/*  75 */       else if (LiquidLookAndFeel.getDesktopColor().equals(rendererComponent.getBackground())) {
/*  76 */         rendererComponent.setBackground(this.defaultBackground);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*  81 */     int cx = rowBounds.x;
/*  82 */     int cy = rowBounds.y;
/*  83 */     int cw = rowBounds.width;
/*  84 */     int ch = rowBounds.height;
/*  85 */     this.rendererPane.paintComponent(g, rendererComponent, this.list, cx, cy, cw, ch, true);
/*     */   }
/*     */   
/*     */   public void paint(Graphics g, JComponent c) {
/*  89 */     if (LiquidLookAndFeel.defaultRowBackgroundMode & ((this.defaultBackground == null) ? 1 : 0))
/*     */     {
/*  91 */       this.defaultBackground = c.getBackground();
/*     */     }
/*  93 */     super.paint(g, c);
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
/* 104 */   public static ComponentUI createUI(JComponent list) { return new LiquidListUI(); }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\LiquidListUI.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */