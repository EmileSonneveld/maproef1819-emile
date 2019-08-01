/*     */ package com.birosoft.liquid;
/*     */ 
/*     */ import com.birosoft.liquid.skin.Skin;
/*     */ import com.birosoft.liquid.skin.SkinSimpleButtonIndexModel;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JTabbedPane;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.basic.BasicGraphicsUtils;
/*     */ import javax.swing.plaf.basic.BasicTabbedPaneUI;
/*     */ import javax.swing.text.View;
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
/*     */ public class LiquidTabbedPaneUI
/*     */   extends BasicTabbedPaneUI
/*     */ {
/*     */   static Skin skinTop;
/*     */   static Skin skinLeft;
/*     */   static Skin skinRight;
/*     */   static Skin skinBottom;
/*     */   static Skin skinBorder;
/*     */   static Skin skinBorderRight;
/*  47 */   SkinSimpleButtonIndexModel indexModel = new SkinSimpleButtonIndexModel();
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
/*  68 */   Color componentBackground = LiquidLookAndFeel.getControl();
/*  69 */   Color tabBackground = LiquidLookAndFeel.getLightControl();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  79 */   public static ComponentUI createUI(JComponent c) { return new LiquidTabbedPaneUI(); }
/*     */ 
/*     */   
/*  82 */   int rollover = -1;
/*     */ 
/*     */   
/*     */   protected void installListeners() {
/*  86 */     super.installListeners();
/*  87 */     this.tabPane.addMouseMotionListener((MouseMotionListener)this.mouseListener);
/*     */   }
/*     */ 
/*     */   
/*  91 */   protected MouseListener createMouseListener() { return new MyMouseHandler(this); }
/*     */ 
/*     */   
/*     */   private void ensureCurrentLayout() {
/*  95 */     if (!this.tabPane.isValid())
/*     */     {
/*  97 */       this.tabPane.validate();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 103 */     if (!this.tabPane.isValid()) {
/*     */       
/* 105 */       BasicTabbedPaneUI.TabbedPaneLayout layout = (BasicTabbedPaneUI.TabbedPaneLayout)this.tabPane.getLayout();
/* 106 */       layout.calculateLayoutInfo();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private int getTabAtLocation(int x, int y) {
/* 112 */     ensureCurrentLayout();
/*     */     
/* 114 */     int tabCount = this.tabPane.getTabCount();
/* 115 */     for (int i = 0; i < tabCount; i++) {
/*     */       
/* 117 */       if (this.rects[i].contains(x, y))
/*     */       {
/* 119 */         return i;
/*     */       }
/*     */     } 
/* 122 */     return -1;
/*     */   }
/*     */   
/* 125 */   public class MyMouseHandler implements MouseListener, MouseMotionListener { public MyMouseHandler(LiquidTabbedPaneUI this$0) { this.this$0 = this$0; }
/*     */     private final LiquidTabbedPaneUI this$0;
/*     */     
/*     */     public void mousePressed(MouseEvent e) {
/* 129 */       if (!this.this$0.tabPane.isEnabled()) {
/*     */         return;
/*     */       }
/*     */       
/* 133 */       int tabIndex = this.this$0.getTabAtLocation(e.getX(), e.getY());
/* 134 */       if (tabIndex >= 0 && this.this$0.tabPane.isEnabledAt(tabIndex))
/*     */       {
/* 136 */         if (tabIndex == this.this$0.tabPane.getSelectedIndex()) {
/*     */           
/* 138 */           if (this.this$0.tabPane.isRequestFocusEnabled()) {
/*     */             
/* 140 */             this.this$0.tabPane.requestFocus();
/* 141 */             this.this$0.tabPane.repaint(this.this$0.getTabBounds(this.this$0.tabPane, tabIndex));
/*     */           } 
/*     */         } else {
/*     */           
/* 145 */           this.this$0.tabPane.setSelectedIndex(tabIndex);
/*     */         } 
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public void mouseEntered(MouseEvent e) {}
/*     */ 
/*     */     
/*     */     public void mouseExited(MouseEvent e) {
/* 155 */       if (this.this$0.rollover != -1 && this.this$0.rollover < this.this$0.tabPane.getTabCount()) {
/*     */         
/* 157 */         this.this$0.tabPane.repaint(this.this$0.getTabBounds(this.this$0.tabPane, this.this$0.rollover));
/* 158 */         this.this$0.rollover = -1;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void mouseClicked(MouseEvent e) {}
/*     */ 
/*     */ 
/*     */     
/*     */     public void mouseReleased(MouseEvent e) {}
/*     */ 
/*     */ 
/*     */     
/*     */     public void mouseDragged(MouseEvent e) {}
/*     */ 
/*     */     
/*     */     public void mouseMoved(MouseEvent e) {
/* 176 */       if (this.this$0.tabPane == null)
/*     */         return; 
/* 178 */       if (!this.this$0.tabPane.isEnabled()) {
/*     */         return;
/*     */       }
/*     */       
/* 182 */       int tabIndex = this.this$0.getTabAtLocation(e.getX(), e.getY());
/* 183 */       if (tabIndex >= 0 && tabIndex != this.this$0.rollover && this.this$0.rollover != -1) {
/*     */         
/* 185 */         if (this.this$0.rollover >= 0 && this.this$0.rollover < this.this$0.tabPane.getTabCount())
/* 186 */           this.this$0.tabPane.repaint(this.this$0.getTabBounds(this.this$0.tabPane, this.this$0.rollover)); 
/* 187 */         if (tabIndex == -1)
/* 188 */           this.this$0.rollover = -1; 
/*     */       } 
/* 190 */       if (tabIndex >= 0 && this.this$0.tabPane.isEnabledAt(tabIndex) && tabIndex < this.this$0.tabPane.getTabCount())
/*     */       {
/* 192 */         if (tabIndex != this.this$0.rollover) {
/*     */ 
/*     */ 
/*     */           
/* 196 */           this.this$0.rollover = tabIndex;
/* 197 */           this.this$0.tabPane.repaint(this.this$0.getTabBounds(this.this$0.tabPane, tabIndex));
/*     */         } 
/*     */       }
/*     */     } }
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
/*     */   protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {}
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
/*     */   protected void paintFocusIndicator(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect, boolean isSelected) {}
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
/*     */   protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {}
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
/*     */   protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
/* 280 */     Insets contentBorderInsets = getContentBorderInsets(tabPlacement);
/* 281 */     if (this.tabPane.getTabPlacement() == 3 && contentBorderInsets.top == 5) {
/*     */       
/* 283 */       contentBorderInsets.top = 0;
/* 284 */       contentBorderInsets.bottom = 5;
/* 285 */       this.tabPane.revalidate();
/* 286 */     } else if (this.tabPane.getTabPlacement() == 1 && contentBorderInsets.top == 0) {
/*     */       
/* 288 */       contentBorderInsets.top = 5;
/* 289 */       contentBorderInsets.bottom = 0;
/* 290 */       this.tabPane.revalidate();
/*     */     } 
/*     */ 
/*     */     
/* 294 */     int index = this.indexModel.getIndexForState(this.tabPane.isEnabledAt(tabIndex), (this.rollover == tabIndex), isSelected);
/* 295 */     switch (tabPlacement) {
/*     */       
/*     */       case 2:
/* 298 */         getSkinLeft().draw(g, index, x, y, w, h - 1);
/*     */         return;
/*     */       
/*     */       case 4:
/* 302 */         getSkinRight().draw(g, index, x - 2, y, w, h - 1);
/*     */         return;
/*     */       case 3:
/* 305 */         getSkinBottom().draw(g, index, x, y, w, h);
/*     */         return;
/*     */     } 
/*     */     
/* 309 */     getSkinTop().draw(g, index, x, y, w, h);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex, String title, Rectangle textRect, boolean isSelected) {
/* 315 */     int yOffset = 0;
/* 316 */     if (tabPlacement == 1 && isSelected) yOffset = 1; 
/* 317 */     if (tabPlacement == 3) yOffset = isSelected ? -2 : -1;
/*     */     
/* 319 */     g.setFont(font);
/*     */     
/* 321 */     View v = getTextViewForTab(tabIndex);
/* 322 */     if (v != null) {
/*     */ 
/*     */       
/* 325 */       textRect.y += yOffset;
/* 326 */       v.paint(g, textRect);
/*     */     }
/*     */     else {
/*     */       
/* 330 */       int mnemIndex = this.tabPane.getDisplayedMnemonicIndexAt(tabIndex);
/*     */       
/* 332 */       if (this.tabPane.isEnabled() && this.tabPane.isEnabledAt(tabIndex)) {
/*     */         
/* 334 */         g.setColor(this.tabPane.getForegroundAt(tabIndex));
/* 335 */         BasicGraphicsUtils.drawStringUnderlineCharAt(g, title, mnemIndex, textRect.x, textRect.y + metrics.getAscent() + yOffset);
/*     */       }
/*     */       else {
/*     */         
/* 339 */         g.setColor(this.tabPane.getBackgroundAt(tabIndex).brighter());
/* 340 */         BasicGraphicsUtils.drawStringUnderlineCharAt(g, title, mnemIndex, textRect.x, textRect.y + metrics.getAscent());
/* 341 */         g.setColor(this.tabPane.getBackgroundAt(tabIndex).darker());
/* 342 */         BasicGraphicsUtils.drawStringUnderlineCharAt(g, title, mnemIndex, textRect.x - 1, textRect.y + metrics.getAscent() - 1);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void update(Graphics g, JComponent c) {
/* 350 */     Insets contentBorderInsets = getContentBorderInsets(this.tabPane.getTabPlacement());
/* 351 */     if (this.tabPane.getTabPlacement() == 3 && contentBorderInsets.top == 5) {
/*     */       
/* 353 */       contentBorderInsets.top = 0;
/* 354 */       contentBorderInsets.bottom = 5;
/* 355 */       this.tabPane.revalidate();
/* 356 */     } else if (this.tabPane.getTabPlacement() == 1 && contentBorderInsets.top == 0) {
/*     */       
/* 358 */       contentBorderInsets.top = 5;
/* 359 */       contentBorderInsets.bottom = 0;
/* 360 */       this.tabPane.revalidate();
/*     */     } 
/*     */     
/* 363 */     int width = this.tabPane.getWidth();
/* 364 */     int height = this.tabPane.getHeight();
/* 365 */     Insets insets = this.tabPane.getInsets();
/*     */     
/* 367 */     int x = insets.left;
/* 368 */     int y = insets.top;
/* 369 */     int w = width - insets.right - insets.left;
/* 370 */     int h = height - insets.top - insets.bottom;
/* 371 */     if (c.isOpaque()) {
/*     */       
/* 373 */       g.setColor(this.componentBackground);
/* 374 */       g.fillRect(0, 0, c.getWidth(), c.getHeight());
/*     */     } 
/* 376 */     int tabPlacement = this.tabPane.getTabPlacement();
/* 377 */     switch (tabPlacement) {
/*     */       
/*     */       case 2:
/* 380 */         x += calculateTabAreaWidth(tabPlacement, this.runCount, this.maxTabWidth);
/* 381 */         w -= x - insets.left;
/*     */         break;
/*     */       case 4:
/* 384 */         w -= calculateTabAreaWidth(tabPlacement, this.runCount, this.maxTabWidth);
/*     */         break;
/*     */ 
/*     */       
/*     */       default:
/* 389 */         y += calculateTabAreaHeight(tabPlacement, this.runCount, this.maxTabHeight);
/* 390 */         h -= y - insets.top;
/*     */         break;
/*     */     } 
/* 393 */     g.setColor(this.tabBackground);
/* 394 */     g.fillRect(x, y, w, h);
/*     */ 
/*     */     
/* 397 */     g.drawLine(x, y, x, y + h - 3);
/* 398 */     g.drawLine(x, y, x + w - 3, y);
/*     */ 
/*     */ 
/*     */     
/* 402 */     if (tabPlacement == 3)
/* 403 */       getSkinBorder().draw(g, 0, x, h - 5, w, 5); 
/* 404 */     if (tabPlacement == 1)
/* 405 */       getSkinBorder().draw(g, 0, x, y, w, 5); 
/* 406 */     paint(g, c);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getTabLabelShiftX(int tabPlacement, int tabIndex, boolean isSelected) {
/* 412 */     Rectangle tabRect = this.rects[tabIndex];
/* 413 */     nudge = 0;
/* 414 */     switch (tabPlacement) {
/*     */       
/*     */       case 2:
/* 417 */         return isSelected ? -1 : 1;
/*     */       
/*     */       case 4:
/* 420 */         return isSelected ? 1 : -1;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 425 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getTabLabelShiftY(int tabPlacement, int tabIndex, boolean isSelected) {
/* 432 */     Rectangle tabRect = this.rects[tabIndex];
/* 433 */     nudge = 0;
/* 434 */     switch (tabPlacement) {
/*     */       
/*     */       case 3:
/* 437 */         return isSelected ? 1 : -1;
/*     */       
/*     */       case 2:
/*     */       case 4:
/* 441 */         return tabRect.height % 2;
/*     */     } 
/*     */ 
/*     */     
/* 445 */     return isSelected ? -1 : 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Skin getSkinTop() {
/* 452 */     if (skinTop == null)
/*     */     {
/* 454 */       skinTop = new Skin("tabtop.png", 4, 7, 6, 7, 2);
/*     */     }
/* 456 */     return skinTop;
/*     */   }
/*     */ 
/*     */   
/*     */   public Skin getSkinLeft() {
/* 461 */     if (skinLeft == null)
/*     */     {
/* 463 */       skinLeft = new Skin("tableft.png", 4, 6, 7, 2, 7);
/*     */     }
/* 465 */     return skinLeft;
/*     */   }
/*     */ 
/*     */   
/*     */   public Skin getSkinRight() {
/* 470 */     if (skinRight == null)
/*     */     {
/* 472 */       skinRight = new Skin("tabright.png", 4, 2, 7, 6, 7);
/*     */     }
/* 474 */     return skinRight;
/*     */   }
/*     */ 
/*     */   
/*     */   public Skin getSkinBottom() {
/* 479 */     if (skinBottom == null)
/*     */     {
/* 481 */       skinBottom = new Skin("tabbottom.png", 4, 6, 7, 6, 7);
/*     */     }
/* 483 */     return skinBottom;
/*     */   }
/*     */ 
/*     */   
/*     */   public Skin getSkinBorder() {
/* 488 */     if (skinBorder == null)
/*     */     {
/* 490 */       skinBorder = new Skin("tabborderh.png", 1, 5, 2, 5, 2);
/*     */     }
/* 492 */     return skinBorder;
/*     */   }
/*     */ 
/*     */   
/*     */   public Skin getSkinBorderRight() {
/* 497 */     if (skinBorderRight == null)
/*     */     {
/* 499 */       skinBorderRight = new Skin("tabborderright.png", 1, 0, 5, 0, 5);
/*     */     }
/* 501 */     return skinBorderRight;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\LiquidTabbedPaneUI.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */