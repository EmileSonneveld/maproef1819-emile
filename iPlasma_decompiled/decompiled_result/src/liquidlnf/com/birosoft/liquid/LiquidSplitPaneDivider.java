/*     */ package com.birosoft.liquid;
/*     */ 
/*     */ import com.birosoft.liquid.skin.Skin;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Insets;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.Rectangle;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JSplitPane;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.plaf.basic.BasicSplitPaneDivider;
/*     */ import javax.swing.plaf.basic.BasicSplitPaneUI;
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
/*     */ class LiquidSplitPaneDivider
/*     */   extends BasicSplitPaneDivider
/*     */ {
/*  50 */   private int inset = 0;
/*     */   
/*  52 */   Skin skinVertSplitter = null;
/*  53 */   Skin skinHorzSplitter = null;
/*     */ 
/*     */   
/*     */   public LiquidSplitPaneDivider(BasicSplitPaneUI ui) {
/*  57 */     super(ui);
/*  58 */     setLayout(new LiquidDividerLayout(this));
/*     */   }
/*     */ 
/*     */   
/*     */   Skin getHorizontalSplitter() {
/*  63 */     if (this.skinHorzSplitter == null)
/*  64 */       this.skinHorzSplitter = new Skin("horizsplitter.png", 1, 5, 5, 5, 5); 
/*  65 */     return this.skinHorzSplitter;
/*     */   }
/*     */ 
/*     */   
/*     */   Skin getVerticalSplitter() {
/*  70 */     if (this.skinVertSplitter == null)
/*  71 */       this.skinVertSplitter = new Skin("vertsplitter.png", 1, 5, 5, 5, 5); 
/*  72 */     return this.skinVertSplitter;
/*     */   }
/*     */ 
/*     */   
/*     */   public void paint(Graphics g) {
/*  77 */     Dimension size = getSize();
/*  78 */     Rectangle clip = new Rectangle(size.width, size.height);
/*  79 */     Insets insets = getInsets();
/*  80 */     if (this.orientation == 1) {
/*     */       
/*  82 */       int xOffset = (clip.width - 5) / 2;
/*  83 */       getHorizontalSplitter().draw(g, 0, clip.x + xOffset, clip.y + 1, 5, clip.height - 1);
/*     */     } else {
/*     */       
/*  86 */       int yOffset = (clip.height - 5) / 2;
/*  87 */       getVerticalSplitter().draw(g, 0, clip.x + 1, clip.y + yOffset, clip.width - 1, 5);
/*     */     } 
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
/* 103 */     super.paint(g);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected JButton createLeftOneTouchButton() {
/* 114 */     JButton b = new JButton(this)
/*     */       {
/*     */         int[][] buffer;
/*     */ 
/*     */ 
/*     */         
/*     */         private final LiquidSplitPaneDivider this$0;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public void setBorder(Border b) {}
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public void paint(Graphics g) {
/* 131 */           JSplitPane splitPane = this.this$0.getSplitPaneFromSuper();
/* 132 */           if (splitPane != null) {
/*     */             
/* 134 */             int oneTouchSize = this.this$0.getOneTouchSizeFromSuper();
/* 135 */             int orientation = this.this$0.getOrientationFromSuper();
/* 136 */             int blockSize = Math.min(this.this$0.getDividerSize(), oneTouchSize);
/*     */ 
/*     */             
/* 139 */             Color[] colors = { getBackground(), (Color)LiquidLookAndFeel.uiDefaults.get("controlDkShadow"), (Color)LiquidLookAndFeel.uiDefaults.get("info"), (Color)LiquidLookAndFeel.uiDefaults.get("controlHighlight") };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 147 */             g.setColor(getBackground());
/*     */ 
/*     */ 
/*     */             
/* 151 */             if (getModel().isPressed())
/*     */             {
/*     */               
/* 154 */               colors[1] = colors[2];
/*     */             }
/* 156 */             if (orientation == 0) {
/*     */ 
/*     */               
/* 159 */               for (int i = 1; i <= this.buffer[0].length; i++)
/*     */               {
/* 161 */                 for (int j = 1; j < blockSize; j++)
/*     */                 {
/* 163 */                   if (this.buffer[j - 1][i - 1] != 0)
/*     */                   {
/*     */ 
/*     */ 
/*     */                     
/* 168 */                     g.setColor(colors[this.buffer[j - 1][i - 1]]);
/*     */                     
/* 170 */                     g.drawLine(i, j, i, j);
/*     */                   
/*     */                   }
/*     */                 
/*     */                 }
/*     */               
/*     */               }
/*     */             
/*     */             }
/*     */             else {
/*     */               
/* 181 */               for (int i = 1; i <= this.buffer[0].length; i++) {
/*     */                 
/* 183 */                 for (int j = 1; j < blockSize; j++) {
/*     */                   
/* 185 */                   if (this.buffer[j - 1][i - 1] != 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                     
/* 194 */                     g.setColor(colors[this.buffer[j - 1][i - 1]]);
/*     */ 
/*     */                     
/* 197 */                     g.drawLine(j, i, j, i);
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         }
/*     */ 
/*     */         
/*     */         public boolean isFocusTraversable() {
/* 207 */           return false;
/*     */         }
/*     */       };
/* 210 */     b.setRequestFocusEnabled(false);
/* 211 */     b.setCursor(Cursor.getPredefinedCursor(0));
/* 212 */     b.setFocusPainted(false);
/* 213 */     b.setBorderPainted(false);
/* 214 */     return b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected JButton createRightOneTouchButton() {
/* 224 */     JButton b = new JButton(this)
/*     */       {
/*     */         int[][] buffer;
/*     */ 
/*     */ 
/*     */         
/*     */         private final LiquidSplitPaneDivider this$0;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public void setBorder(Border border) {}
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public void paint(Graphics g) {
/* 241 */           JSplitPane splitPane = this.this$0.getSplitPaneFromSuper();
/* 242 */           if (splitPane != null) {
/*     */             
/* 244 */             int oneTouchSize = this.this$0.getOneTouchSizeFromSuper();
/* 245 */             int orientation = this.this$0.getOrientationFromSuper();
/* 246 */             int blockSize = Math.min(this.this$0.getDividerSize(), oneTouchSize);
/*     */ 
/*     */             
/* 249 */             Color[] colors = { getBackground(), (Color)LiquidLookAndFeel.uiDefaults.get("controlDkShadow"), (Color)LiquidLookAndFeel.uiDefaults.get("info"), (Color)LiquidLookAndFeel.uiDefaults.get("controlHighlight") };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 257 */             g.setColor(getBackground());
/*     */ 
/*     */ 
/*     */             
/* 261 */             if (getModel().isPressed())
/*     */             {
/*     */               
/* 264 */               colors[1] = colors[2];
/*     */             }
/* 266 */             if (orientation == 0) {
/*     */ 
/*     */               
/* 269 */               for (int i = 1; i <= this.buffer[0].length; i++)
/*     */               {
/* 271 */                 for (int j = 1; j < blockSize; j++)
/*     */                 {
/* 273 */                   if (this.buffer[j - 1][i - 1] != 0)
/*     */                   {
/*     */ 
/*     */ 
/*     */                     
/* 278 */                     g.setColor(colors[this.buffer[j - 1][i - 1]]);
/*     */                     
/* 280 */                     g.drawLine(i, j, i, j);
/*     */                   
/*     */                   }
/*     */                 
/*     */                 }
/*     */               
/*     */               }
/*     */             
/*     */             }
/*     */             else {
/*     */               
/* 291 */               for (int i = 1; i <= this.buffer[0].length; i++) {
/*     */                 
/* 293 */                 for (int j = 1; j < blockSize; j++) {
/*     */                   
/* 295 */                   if (this.buffer[j - 1][i - 1] != 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                     
/* 304 */                     g.setColor(colors[this.buffer[j - 1][i - 1]]);
/*     */ 
/*     */                     
/* 307 */                     g.drawLine(j, i, j, i);
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         }
/*     */ 
/*     */         
/*     */         public boolean isFocusTraversable() {
/* 317 */           return false;
/*     */         }
/*     */       };
/* 320 */     b.setCursor(Cursor.getPredefinedCursor(0));
/* 321 */     b.setFocusPainted(false);
/* 322 */     b.setBorderPainted(false);
/* 323 */     b.setRequestFocusEnabled(false);
/* 324 */     return b;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public class LiquidDividerLayout
/*     */     implements LayoutManager
/*     */   {
/*     */     private final LiquidSplitPaneDivider this$0;
/*     */ 
/*     */ 
/*     */     
/* 336 */     public LiquidDividerLayout(LiquidSplitPaneDivider this$0) { this.this$0 = this$0; }
/*     */ 
/*     */     
/*     */     public void layoutContainer(Container c) {
/* 340 */       JButton leftButton = this.this$0.getLeftButtonFromSuper();
/* 341 */       JButton rightButton = this.this$0.getRightButtonFromSuper();
/* 342 */       JSplitPane splitPane = this.this$0.getSplitPaneFromSuper();
/* 343 */       int orientation = this.this$0.getOrientationFromSuper();
/* 344 */       int oneTouchSize = this.this$0.getOneTouchSizeFromSuper();
/* 345 */       int oneTouchOffset = this.this$0.getOneTouchOffsetFromSuper();
/* 346 */       Insets insets = this.this$0.getInsets();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 352 */       if (leftButton != null && rightButton != null && c == this.this$0)
/*     */       {
/* 354 */         if (splitPane.isOneTouchExpandable()) {
/*     */           
/* 356 */           if (orientation == 0) {
/*     */             
/* 358 */             int extraY = (insets != null) ? insets.top : 0;
/* 359 */             int blockSize = this.this$0.getDividerSize();
/*     */             
/* 361 */             if (insets != null)
/*     */             {
/* 363 */               blockSize -= insets.top + insets.bottom;
/*     */             }
/* 365 */             blockSize = Math.min(blockSize, oneTouchSize);
/* 366 */             leftButton.setBounds(oneTouchOffset, extraY, blockSize * 2, blockSize);
/* 367 */             rightButton.setBounds(oneTouchOffset + oneTouchSize * 2, extraY, blockSize * 2, blockSize);
/*     */           } else {
/*     */             
/* 370 */             int blockSize = this.this$0.getDividerSize();
/* 371 */             int extraX = (insets != null) ? insets.left : 0;
/*     */             
/* 373 */             if (insets != null)
/*     */             {
/* 375 */               blockSize -= insets.left + insets.right;
/*     */             }
/* 377 */             blockSize = Math.min(blockSize, oneTouchSize);
/* 378 */             leftButton.setBounds(extraX, oneTouchOffset, blockSize, blockSize * 2);
/* 379 */             rightButton.setBounds(extraX, oneTouchOffset + oneTouchSize * 2, blockSize, blockSize * 2);
/*     */           } 
/*     */         } else {
/*     */           
/* 383 */           leftButton.setBounds(-5, -5, 1, 1);
/* 384 */           rightButton.setBounds(-5, -5, 1, 1);
/*     */         } 
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 391 */     public Dimension minimumLayoutSize(Container c) { return new Dimension(0, 0); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 396 */     public Dimension preferredLayoutSize(Container c) { return new Dimension(0, 0); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void removeLayoutComponent(Component c) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void addLayoutComponent(String string, Component c) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 416 */   int getOneTouchSizeFromSuper() { return 6; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 421 */   int getOneTouchOffsetFromSuper() { return 2; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 426 */   int getOrientationFromSuper() { return this.orientation; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 431 */   JSplitPane getSplitPaneFromSuper() { return this.splitPane; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 436 */   JButton getLeftButtonFromSuper() { return this.leftButton; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 441 */   JButton getRightButtonFromSuper() { return this.rightButton; }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\LiquidSplitPaneDivider.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */