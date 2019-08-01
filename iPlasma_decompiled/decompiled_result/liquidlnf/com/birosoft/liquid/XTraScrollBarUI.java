/*     */ package com.birosoft.liquid;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.MouseEvent;
/*     */ import javax.swing.BoundedRangeModel;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JScrollBar;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.basic.BasicScrollBarUI;
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
/*     */ public class XTraScrollBarUI
/*     */   extends BasicScrollBarUI
/*     */ {
/*  27 */   protected boolean useAlternateLayout = Boolean.TRUE.equals(UIManager.get("ScrollBar.alternateLayout"));
/*     */ 
/*     */   
/*  30 */   public static ComponentUI createUI(JComponent x) { return new XTraScrollBarUI(); }
/*     */ 
/*     */   
/*     */   protected void layoutVScrollbar(JScrollBar sb) {
/*  34 */     if (this.useAlternateLayout) {
/*  35 */       alternateLayoutVScrollbar(sb);
/*     */     } else {
/*  37 */       super.layoutVScrollbar(sb);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void alternateLayoutVScrollbar(JScrollBar sb) {
/*  42 */     Dimension sbSize = sb.getSize();
/*  43 */     Insets sbInsets = sb.getInsets();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  48 */     int itemW = sbSize.width - sbInsets.left + sbInsets.right;
/*  49 */     int itemX = sbInsets.left;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  54 */     int incrButtonH = (this.incrButton.getPreferredSize()).height;
/*  55 */     int incrButtonY = sbSize.height - sbInsets.bottom + incrButtonH;
/*     */     
/*  57 */     int decrButtonH = (this.decrButton.getPreferredSize()).height;
/*  58 */     int decrButtonY = incrButtonY - decrButtonH;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  63 */     int sbInsetsH = sbInsets.top + sbInsets.bottom;
/*  64 */     int sbButtonsH = decrButtonH + incrButtonH;
/*  65 */     float trackH = (sbSize.height - sbInsetsH + sbButtonsH);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  73 */     float min = sb.getMinimum();
/*  74 */     float extent = sb.getVisibleAmount();
/*  75 */     float range = sb.getMaximum() - min;
/*  76 */     float value = sb.getValue();
/*     */     
/*  78 */     int thumbH = (range <= 0.0F) ? (getMaximumThumbSize()).height : (int)(trackH * extent / range);
/*     */     
/*  80 */     thumbH = Math.max(thumbH, (getMinimumThumbSize()).height);
/*  81 */     thumbH = Math.min(thumbH, (getMaximumThumbSize()).height);
/*     */     
/*  83 */     int thumbY = decrButtonY - thumbH;
/*  84 */     if (sb.getValue() < sb.getMaximum() - sb.getVisibleAmount()) {
/*  85 */       float thumbRange = trackH - thumbH;
/*  86 */       thumbY = (int)(0.5F + thumbRange * (value - min) / (range - extent));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  93 */     int sbAvailButtonH = sbSize.height - sbInsetsH;
/*  94 */     if (sbAvailButtonH < sbButtonsH) {
/*  95 */       incrButtonH = decrButtonH = sbAvailButtonH / 2;
/*  96 */       incrButtonY = sbSize.height - sbInsets.bottom + incrButtonH;
/*     */     } 
/*  98 */     this.decrButton.setBounds(itemX, decrButtonY, itemW, decrButtonH);
/*  99 */     this.incrButton.setBounds(itemX, incrButtonY, itemW, incrButtonH);
/*     */ 
/*     */ 
/*     */     
/* 103 */     int itrackY = sbInsets.top;
/* 104 */     int itrackH = decrButtonY - itrackY;
/* 105 */     this.trackRect.setBounds(itemX, itrackY, itemW, itrackH);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 111 */     if (thumbH >= (int)trackH) {
/* 112 */       setThumbBounds(0, 0, 0, 0);
/*     */     } else {
/*     */       
/* 115 */       if (thumbY + thumbH > decrButtonY) {
/* 116 */         thumbY = decrButtonY - thumbH;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 121 */       setThumbBounds(itemX, thumbY, itemW, thumbH);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void layoutHScrollbar(JScrollBar sb) {
/* 126 */     if (this.useAlternateLayout) {
/* 127 */       alternateLayoutHScrollbar(sb);
/*     */     } else {
/* 129 */       super.layoutHScrollbar(sb);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void alternateLayoutHScrollbar(JScrollBar sb) {
/* 134 */     Dimension sbSize = sb.getSize();
/* 135 */     Insets sbInsets = sb.getInsets();
/*     */ 
/*     */ 
/*     */     
/* 139 */     int itemH = sbSize.height - sbInsets.top + sbInsets.bottom;
/* 140 */     int itemY = sbInsets.top;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 145 */     int incrButtonW = (this.incrButton.getPreferredSize()).width;
/* 146 */     int incrButtonX = sbSize.width - sbInsets.right + incrButtonW;
/*     */     
/* 148 */     int decrButtonW = (this.decrButton.getPreferredSize()).width;
/* 149 */     int decrButtonX = incrButtonX - decrButtonW;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 155 */     int sbInsetsW = sbInsets.left + sbInsets.right;
/* 156 */     int sbButtonsW = decrButtonW + incrButtonW;
/* 157 */     float trackW = (sbSize.width - sbInsetsW + sbButtonsW);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 165 */     float min = sb.getMinimum();
/* 166 */     float extent = sb.getVisibleAmount();
/* 167 */     float range = sb.getMaximum() - min;
/* 168 */     float value = sb.getValue();
/*     */     
/* 170 */     int thumbW = (range <= 0.0F) ? (getMaximumThumbSize()).width : (int)(trackW * extent / range);
/*     */     
/* 172 */     thumbW = Math.max(thumbW, (getMinimumThumbSize()).width);
/* 173 */     thumbW = Math.min(thumbW, (getMaximumThumbSize()).width);
/*     */     
/* 175 */     int thumbX = decrButtonX - thumbW;
/* 176 */     if (sb.getValue() < sb.getMaximum() - sb.getVisibleAmount()) {
/* 177 */       float thumbRange = trackW - thumbW;
/* 178 */       thumbX = (int)(0.5F + thumbRange * (value - min) / (range - extent));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 185 */     int sbAvailButtonW = sbSize.width - sbInsetsW;
/* 186 */     if (sbAvailButtonW < sbButtonsW) {
/* 187 */       incrButtonW = decrButtonW = sbAvailButtonW / 2;
/* 188 */       incrButtonX = sbSize.width - sbInsets.right + incrButtonW;
/*     */     } 
/*     */     
/* 191 */     this.decrButton.setBounds(decrButtonX, itemY, decrButtonW, itemH);
/* 192 */     this.incrButton.setBounds(incrButtonX, itemY, incrButtonW, itemH);
/*     */ 
/*     */ 
/*     */     
/* 196 */     int itrackX = sbInsets.left;
/* 197 */     int itrackW = decrButtonX - itrackX;
/* 198 */     this.trackRect.setBounds(itrackX, itemY, itrackW, itemH);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 203 */     if (thumbW >= (int)trackW) {
/* 204 */       setThumbBounds(0, 0, 0, 0);
/*     */     } else {
/*     */       
/* 207 */       if (thumbX + thumbW > incrButtonX) {
/* 208 */         thumbX = incrButtonX - thumbW;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 213 */       setThumbBounds(thumbX, itemY, thumbW, itemH);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected BasicScrollBarUI.TrackListener createTrackListener() {
/* 218 */     if (this.useAlternateLayout) {
/* 219 */       return new MyTrackListener(this);
/*     */     }
/* 221 */     return super.createTrackListener();
/*     */   }
/*     */ 
/*     */   
/* 225 */   public JButton decrButton() { return this.decrButton; }
/*     */ 
/*     */ 
/*     */   
/* 229 */   public JButton incrButton() { return this.incrButton; }
/*     */ 
/*     */ 
/*     */   
/* 233 */   public JScrollBar scrollbar() { return this.scrollbar; }
/*     */ 
/*     */ 
/*     */   
/* 237 */   public boolean isDragging() { return this.isDragging; }
/*     */ 
/*     */ 
/*     */   
/* 241 */   public Rectangle getThumbBounds() { return super.getThumbBounds(); }
/*     */ 
/*     */ 
/*     */   
/* 245 */   public Rectangle getTrackBounds() { return super.getTrackBounds(); }
/*     */ 
/*     */ 
/*     */   
/* 249 */   public void setThumbBounds(int x, int y, int w, int h) { super.setThumbBounds(x, y, w, h); }
/*     */   
/*     */   public class MyTrackListener
/*     */     extends BasicScrollBarUI.TrackListener {
/*     */     private final XTraScrollBarUI this$0;
/*     */     
/* 255 */     public MyTrackListener(XTraScrollBarUI this$0) { super(this$0); this.this$0 = this$0; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void mouseDragged(MouseEvent e) {
/*     */       int thumbPos, thumbMax, thumbMin;
/* 263 */       if (!this.this$0.scrollbar().isEnabled() || !this.this$0.isDragging()) {
/*     */         return;
/*     */       }
/*     */       
/* 267 */       Insets sbInsets = this.this$0.scrollbar().getInsets();
/*     */       
/* 269 */       BoundedRangeModel model = this.this$0.scrollbar().getModel();
/* 270 */       Rectangle thumbR = this.this$0.getThumbBounds();
/*     */ 
/*     */ 
/*     */       
/* 274 */       if (this.this$0.scrollbar().getOrientation() == 1) {
/* 275 */         thumbMin = sbInsets.top;
/* 276 */         thumbMax = this.this$0.decrButton().getY() - (this.this$0.getThumbBounds()).height;
/* 277 */         thumbPos = Math.min(thumbMax, Math.max(thumbMin, e.getY() - this.offset));
/* 278 */         this.this$0.setThumbBounds(thumbR.x, thumbPos, thumbR.width, thumbR.height);
/* 279 */         float trackLength = (this.this$0.getTrackBounds()).height;
/*     */       } else {
/*     */         
/* 282 */         thumbMin = sbInsets.left;
/* 283 */         thumbMax = this.this$0.decrButton().getX() - (this.this$0.getThumbBounds()).width;
/* 284 */         thumbPos = Math.min(thumbMax, Math.max(thumbMin, e.getX() - this.offset));
/* 285 */         this.this$0.setThumbBounds(thumbPos, thumbR.y, thumbR.width, thumbR.height);
/* 286 */         float trackLength = (this.this$0.getTrackBounds()).width;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 293 */       if (thumbPos == thumbMax) {
/* 294 */         this.this$0.scrollbar().setValue(model.getMaximum() - model.getExtent());
/*     */       } else {
/*     */         
/* 297 */         float valueMax = (model.getMaximum() - model.getExtent());
/* 298 */         float valueRange = valueMax - model.getMinimum();
/* 299 */         float thumbValue = (thumbPos - thumbMin);
/* 300 */         float thumbRange = (thumbMax - thumbMin);
/* 301 */         int value = (int)(0.5D + (thumbValue / thumbRange * valueRange));
/* 302 */         this.this$0.scrollbar().setValue(value + model.getMinimum());
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\XTraScrollBarUI.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */