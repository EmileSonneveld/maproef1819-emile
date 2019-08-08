/*     */ package com.birosoft.liquid;
/*     */ 
/*     */ import com.birosoft.liquid.skin.Skin;
/*     */ import com.birosoft.liquid.skin.SkinSimpleButtonIndexModel;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.MouseEvent;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JScrollBar;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LiquidScrollBarUI
/*     */   extends BasicScrollBarUI
/*     */ {
/*  37 */   private int orientation = -1;
/*     */   private int minExtent;
/*  39 */   private final int MIN_THUMB_SIZE = 14;
/*     */ 
/*     */   
/*     */   private int minValue;
/*     */ 
/*     */   
/*     */   public static final String FREE_STANDING_PROP = "JScrollBar.isFreeStanding";
/*     */ 
/*     */   
/*     */   private static Color highlightColor;
/*     */ 
/*     */   
/*     */   private static Color darkShadowColor;
/*     */ 
/*     */   
/*     */   private static Color thumbShadow;
/*     */ 
/*     */   
/*     */   private static Color thumbHighlightColor;
/*     */ 
/*     */   
/*     */   protected boolean isRollover = false;
/*     */ 
/*     */   
/*     */   protected boolean wasRollover = false;
/*     */ 
/*     */   
/*     */   private boolean freeStanding = false;
/*     */ 
/*     */   
/*     */   int scrollBarWidth;
/*     */ 
/*     */   
/*     */   private static Skin skinTrackVert;
/*     */ 
/*     */   
/*     */   private static Skin skinTrackHoriz;
/*     */ 
/*     */   
/*     */   private Skin skinTrack;
/*     */ 
/*     */   
/*     */   private static Skin skinThumbVert;
/*     */ 
/*     */   
/*     */   private static Skin skinThumbHoriz;
/*     */ 
/*     */   
/*     */   private Skin skinThumb;
/*     */ 
/*     */   
/*  90 */   private SkinSimpleButtonIndexModel skinThumbIndexModel = new SkinSimpleButtonIndexModel();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   JButton decreaseButton;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   JButton increaseButton;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void installDefaults() {
/* 111 */     this.scrollBarWidth = LiquidScrollButton.getSkinUp().getHsize();
/* 112 */     super.installDefaults();
/* 113 */     this.scrollbar.setBorder(null);
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
/* 125 */   public static ComponentUI createUI(JComponent c) { return new LiquidScrollBarUI(); }
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
/*     */   protected JButton createDecreaseButton(int orientation) {
/* 137 */     this.decreaseButton = new LiquidScrollButton(orientation, this.scrollBarWidth, this.freeStanding);
/* 138 */     return this.decreaseButton;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected JButton createIncreaseButton(int orientation) {
/* 149 */     this.increaseButton = new LiquidScrollButton(orientation, this.scrollBarWidth, this.freeStanding);
/* 150 */     return this.increaseButton;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize(JComponent c) {
/* 157 */     if (this.scrollbar.getOrientation() == 1)
/*     */     {
/* 159 */       return new Dimension(this.scrollBarWidth, this.scrollBarWidth * 3 + 10);
/*     */     }
/*     */     
/* 162 */     return new Dimension(this.scrollBarWidth * 3 + 10, this.scrollBarWidth);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void layoutVScrollbar(JScrollBar sb) {
/* 169 */     Dimension sbSize = sb.getSize();
/* 170 */     Insets sbInsets = sb.getInsets();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 175 */     int itemW = sbSize.width - sbInsets.left + sbInsets.right;
/* 176 */     int itemX = sbInsets.left;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 181 */     int decrButtonH = (this.decrButton.getPreferredSize()).height;
/* 182 */     int decrButtonY = sbInsets.top;
/*     */     
/* 184 */     int incrButtonH = (this.incrButton.getPreferredSize()).height;
/* 185 */     int incrButtonY = sbSize.height - sbInsets.bottom + incrButtonH;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 190 */     int sbInsetsH = sbInsets.top + sbInsets.bottom;
/* 191 */     int sbButtonsH = decrButtonH + incrButtonH;
/* 192 */     float trackH = (sbSize.height - sbInsetsH + sbButtonsH);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 200 */     float min = sb.getMinimum();
/* 201 */     float extent = sb.getVisibleAmount();
/* 202 */     float range = sb.getMaximum() - min;
/* 203 */     float value = sb.getValue();
/*     */     
/* 205 */     int thumbH = (range <= 0.0F) ? (getMaximumThumbSize()).height : (int)(trackH * extent / range);
/*     */     
/* 207 */     thumbH = Math.max(thumbH, (getMinimumThumbSize()).height);
/* 208 */     thumbH = Math.min(thumbH, (getMaximumThumbSize()).height);
/*     */     
/* 210 */     if (thumbH < 14) {
/* 211 */       thumbH = 14;
/*     */     }
/* 213 */     int thumbY = incrButtonY - thumbH;
/* 214 */     if (sb.getValue() < sb.getMaximum() - sb.getVisibleAmount()) {
/*     */       
/* 216 */       float thumbRange = trackH - thumbH;
/* 217 */       thumbY = (int)(0.5F + thumbRange * (value - min) / (range - extent));
/* 218 */       thumbY += decrButtonY + decrButtonH;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 224 */     int sbAvailButtonH = sbSize.height - sbInsetsH;
/* 225 */     if (sbAvailButtonH < sbButtonsH) {
/*     */       
/* 227 */       incrButtonH = decrButtonH = sbAvailButtonH / 2;
/* 228 */       incrButtonY = sbSize.height - sbInsets.bottom + incrButtonH;
/*     */     } 
/* 230 */     this.decrButton.setBounds(itemX, decrButtonY, itemW, decrButtonH);
/* 231 */     this.incrButton.setBounds(itemX, incrButtonY, itemW, incrButtonH);
/*     */ 
/*     */ 
/*     */     
/* 235 */     int itrackY = decrButtonY + decrButtonH;
/* 236 */     int itrackH = incrButtonY - itrackY;
/* 237 */     this.trackRect.setBounds(itemX, itrackY, itemW, itrackH);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 243 */     if (thumbH >= (int)trackH) {
/*     */       
/* 245 */       setThumbBounds(0, 0, 0, 0);
/*     */     }
/*     */     else {
/*     */       
/* 249 */       if (thumbY + thumbH > incrButtonY)
/*     */       {
/* 251 */         thumbY = incrButtonY - thumbH;
/*     */       }
/* 253 */       if (thumbY < decrButtonY + decrButtonH)
/*     */       {
/* 255 */         thumbY = decrButtonY + decrButtonH + 1;
/*     */       }
/* 257 */       setThumbBounds(itemX, thumbY, itemW, thumbH);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void layoutHScrollbar(JScrollBar sb) {
/* 263 */     Dimension sbSize = sb.getSize();
/* 264 */     Insets sbInsets = sb.getInsets();
/*     */ 
/*     */ 
/*     */     
/* 268 */     int itemH = sbSize.height - sbInsets.top + sbInsets.bottom;
/* 269 */     int itemY = sbInsets.top;
/*     */     
/* 271 */     boolean ltr = sb.getComponentOrientation().isLeftToRight();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 276 */     int leftButtonW = ((ltr ? this.decrButton : this.incrButton).getPreferredSize()).width;
/* 277 */     int rightButtonW = ((ltr ? this.incrButton : this.decrButton).getPreferredSize()).width;
/* 278 */     int leftButtonX = sbInsets.left;
/* 279 */     int rightButtonX = sbSize.width - sbInsets.right + rightButtonW;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 284 */     int sbInsetsW = sbInsets.left + sbInsets.right;
/* 285 */     int sbButtonsW = leftButtonW + rightButtonW;
/* 286 */     float trackW = (sbSize.width - sbInsetsW + sbButtonsW);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 294 */     float min = sb.getMinimum();
/* 295 */     float max = sb.getMaximum();
/* 296 */     float extent = sb.getVisibleAmount();
/* 297 */     float range = max - min;
/* 298 */     float value = sb.getValue();
/*     */     
/* 300 */     int thumbW = (range <= 0.0F) ? (getMaximumThumbSize()).width : (int)(trackW * extent / range);
/*     */     
/* 302 */     thumbW = Math.max(thumbW, (getMinimumThumbSize()).width);
/* 303 */     thumbW = Math.min(thumbW, (getMaximumThumbSize()).width);
/*     */     
/* 305 */     if (thumbW < 14) {
/* 306 */       thumbW = 14;
/*     */     }
/* 308 */     int thumbX = ltr ? (rightButtonX - thumbW) : (leftButtonX + leftButtonW);
/* 309 */     if (sb.getValue() < max - sb.getVisibleAmount()) {
/* 310 */       float thumbRange = trackW - thumbW;
/* 311 */       if (ltr) {
/* 312 */         thumbX = (int)(0.5F + thumbRange * (value - min) / (range - extent));
/*     */       } else {
/* 314 */         thumbX = (int)(0.5F + thumbRange * (max - extent - value) / (range - extent));
/*     */       } 
/* 316 */       thumbX += leftButtonX + leftButtonW;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 322 */     int sbAvailButtonW = sbSize.width - sbInsetsW;
/* 323 */     if (sbAvailButtonW < sbButtonsW) {
/* 324 */       rightButtonW = leftButtonW = sbAvailButtonW / 2;
/* 325 */       rightButtonX = sbSize.width - sbInsets.right + rightButtonW;
/*     */     } 
/*     */     
/* 328 */     (ltr ? this.decrButton : this.incrButton).setBounds(leftButtonX, itemY, leftButtonW, itemH);
/* 329 */     (ltr ? this.incrButton : this.decrButton).setBounds(rightButtonX, itemY, rightButtonW, itemH);
/*     */ 
/*     */ 
/*     */     
/* 333 */     int itrackX = leftButtonX + leftButtonW;
/* 334 */     int itrackW = rightButtonX - itrackX;
/* 335 */     this.trackRect.setBounds(itrackX, itemY, itrackW, itemH);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 340 */     if (thumbW >= (int)trackW) {
/* 341 */       setThumbBounds(0, 0, 0, 0);
/*     */     } else {
/*     */       
/* 344 */       if (thumbX + thumbW > rightButtonX) {
/* 345 */         thumbX = rightButtonX - thumbW;
/*     */       }
/* 347 */       if (thumbX < leftButtonX + leftButtonW) {
/* 348 */         thumbX = leftButtonX + leftButtonW + 1;
/*     */       }
/* 350 */       setThumbBounds(thumbX, itemY, thumbW, itemH);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void paint(Graphics g, JComponent c) {
/* 356 */     if (this.orientation == -1) this.orientation = this.scrollbar.getOrientation(); 
/* 357 */     Rectangle trackBounds = getTrackBounds();
/* 358 */     getSkinTrack().draw(g, 0, trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
/*     */     
/* 360 */     Rectangle thumbBounds = getThumbBounds();
/* 361 */     int index = this.skinThumbIndexModel.getIndexForState(c.isEnabled(), this.isRollover, this.isDragging);
/*     */     
/* 363 */     int x = (this.orientation == 1) ? (thumbBounds.x + 1) : thumbBounds.x;
/* 364 */     int y = (this.orientation == 1) ? thumbBounds.y : (thumbBounds.y + 1);
/* 365 */     int width = (this.orientation == 1) ? (thumbBounds.width - 2) : thumbBounds.width;
/* 366 */     int height = (this.orientation == 1) ? thumbBounds.height : (thumbBounds.height - 2);
/* 367 */     getSkinThumb().draw(g, index, x, y, width, height);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isThumbVisible() {
/* 372 */     if (this.scrollbar.getOrientation() == 1) {
/*     */       
/* 374 */       if ((getThumbBounds()).height == 0) {
/* 375 */         return false;
/*     */       }
/* 377 */       return true;
/*     */     } 
/*     */     
/* 380 */     if ((getThumbBounds()).width == 0) {
/* 381 */       return false;
/*     */     }
/* 383 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 390 */   protected BasicScrollBarUI.TrackListener createTrackListener() { return new MyTrackListener(this); }
/*     */   
/*     */   protected class MyTrackListener
/*     */     extends BasicScrollBarUI.TrackListener
/*     */   {
/*     */     private final LiquidScrollBarUI this$0;
/*     */     
/* 397 */     protected MyTrackListener(LiquidScrollBarUI this$0) { super(this$0); this.this$0 = this$0; }
/*     */ 
/*     */     
/*     */     public void mouseReleased(MouseEvent e) {
/* 401 */       super.mouseReleased(e);
/* 402 */       this.this$0.scrollbar.repaint();
/*     */     }
/*     */ 
/*     */     
/*     */     public void mousePressed(MouseEvent e) {
/* 407 */       super.mousePressed(e);
/* 408 */       this.this$0.scrollbar.repaint();
/*     */     }
/*     */     
/*     */     public void mouseEntered(MouseEvent e) {
/* 412 */       this.this$0.isRollover = false;
/* 413 */       this.this$0.wasRollover = false;
/* 414 */       if (this.this$0.getThumbBounds().contains(e.getX(), e.getY()))
/*     */       {
/* 416 */         this.this$0.isRollover = true;
/*     */       }
/*     */     }
/*     */     
/*     */     public void mouseExited(MouseEvent e) {
/* 421 */       this.this$0.isRollover = false;
/* 422 */       if (this.this$0.isRollover != this.this$0.wasRollover) {
/*     */         
/* 424 */         this.this$0.scrollbar.repaint();
/* 425 */         this.this$0.wasRollover = this.this$0.isRollover;
/*     */       } 
/*     */     }
/*     */     
/*     */     public void mouseDragged(MouseEvent e) {
/* 430 */       if (this.this$0.getThumbBounds().contains(e.getX(), e.getY()))
/*     */       {
/* 432 */         this.this$0.isRollover = true;
/*     */       }
/* 434 */       super.mouseDragged(e);
/*     */     }
/*     */     
/*     */     public void mouseMoved(MouseEvent e) {
/* 438 */       if (this.this$0.getThumbBounds().contains(e.getX(), e.getY())) {
/*     */         
/* 440 */         this.this$0.isRollover = true;
/* 441 */         if (this.this$0.isRollover != this.this$0.wasRollover) {
/*     */           
/* 443 */           this.this$0.scrollbar.repaint();
/* 444 */           this.this$0.wasRollover = this.this$0.isRollover;
/*     */         } 
/*     */       } else {
/*     */         
/* 448 */         this.this$0.isRollover = false;
/* 449 */         if (this.this$0.isRollover != this.this$0.wasRollover) {
/*     */           
/* 451 */           this.this$0.scrollbar.repaint();
/* 452 */           this.this$0.wasRollover = this.this$0.isRollover;
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
/*     */   public static Skin getSkinThumbHoriz() {
/* 464 */     if (skinThumbHoriz == null)
/*     */     {
/* 466 */       skinThumbHoriz = new Skin("scrollbarthumbhoriz.png", 4, 8, 6, 8, 8);
/*     */     }
/* 468 */     return skinThumbHoriz;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Skin getSkinThumbVert() {
/* 477 */     if (skinThumbVert == null)
/*     */     {
/* 479 */       skinThumbVert = new Skin("scrollbarthumbvert.png", 4, 6, 8, 8, 7);
/*     */     }
/* 481 */     return skinThumbVert;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Skin getSkinTrackHoriz() {
/* 490 */     if (skinTrackHoriz == null)
/*     */     {
/* 492 */       skinTrackHoriz = new Skin("scrollbartrackhoriz.png", 1, 7);
/*     */     }
/* 494 */     return skinTrackHoriz;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Skin getSkinTrackVert() {
/* 503 */     if (skinTrackVert == null)
/*     */     {
/* 505 */       skinTrackVert = new Skin("scrollbartrackvert.png", 1, 7);
/*     */     }
/* 507 */     return skinTrackVert;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Skin getSkinTrack() {
/* 516 */     if (this.skinTrack == null)
/*     */     {
/* 518 */       this.skinTrack = (this.scrollbar.getOrientation() == 1) ? getSkinTrackVert() : getSkinTrackHoriz();
/*     */     }
/* 520 */     return this.skinTrack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Skin getSkinThumb() {
/* 529 */     if (this.skinThumb == null)
/*     */     {
/* 531 */       this.skinThumb = (this.scrollbar.getOrientation() == 1) ? getSkinThumbVert() : getSkinThumbHoriz();
/*     */     }
/* 533 */     return this.skinThumb;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\LiquidScrollBarUI.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */