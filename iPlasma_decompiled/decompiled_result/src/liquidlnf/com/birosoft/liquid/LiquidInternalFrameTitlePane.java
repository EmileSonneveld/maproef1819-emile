/*     */ package com.birosoft.liquid;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import javax.swing.Action;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JInternalFrame;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
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
/*     */ public class LiquidInternalFrameTitlePane
/*     */   extends BasicInternalFrameTitlePane
/*     */   implements LayoutManager
/*     */ {
/*     */   protected boolean isPalette = false;
/*     */   protected Icon paletteCloseIcon;
/*     */   protected int paletteTitleHeight;
/*  50 */   Color normalTitleColor = Color.white;
/*     */ 
/*     */ 
/*     */   
/*  54 */   Color shadowColor = new Color(10, 24, 131);
/*     */ 
/*     */ 
/*     */   
/*  58 */   Color disabledTitleColor = new Color(64, 63, 63);
/*     */ 
/*     */   
/*     */   protected int frameTitleHeight;
/*     */ 
/*     */   
/*     */   private int buttonsWidth;
/*     */ 
/*     */   
/*     */   static LiquidWindowButtonUI iconButtonUI;
/*     */ 
/*     */   
/*     */   static LiquidWindowButtonUI maxButtonUI;
/*     */ 
/*     */   
/*     */   static LiquidWindowButtonUI closeButtonUI;
/*     */ 
/*     */   
/*     */   protected void installDefaults() {
/*  77 */     super.installDefaults();
/*  78 */     this.frameTitleHeight = UIManager.getInt("InternalFrame.frameTitleHeight");
/*  79 */     setFont(UIManager.getFont("InternalFrame.titleFont"));
/*  80 */     this.paletteTitleHeight = UIManager.getInt("InternalFrame.paletteTitleHeight");
/*  81 */     this.paletteCloseIcon = UIManager.getIcon("InternalFrame.paletteCloseIcon");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   protected void uninstallDefaults() { super.uninstallDefaults(); }
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
/* 101 */   public LiquidInternalFrameTitlePane(JInternalFrame frame) { super(frame); }
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
/*     */   protected void paintTitleBackground(Graphics g) {}
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
/*     */   public void paintComponent(Graphics g) {
/* 127 */     boolean leftToRight = this.frame.getComponentOrientation().isLeftToRight();
/* 128 */     boolean isSelected = this.frame.isSelected();
/*     */     
/* 130 */     int width = getWidth();
/* 131 */     int height = getHeight();
/*     */     
/* 133 */     Color foreground = LiquidLookAndFeel.getWindowTitleInactiveForeground();
/* 134 */     drawLiquidCaption(g, isSelected, width, height);
/* 135 */     int titleLength = 0;
/* 136 */     int xOffset = leftToRight ? 2 : (width - 2);
/* 137 */     String frameTitle = this.frame.getTitle();
/*     */     
/* 139 */     Icon icon = this.frame.getFrameIcon();
/* 140 */     if (icon != null) {
/*     */       
/* 142 */       if (!leftToRight)
/* 143 */         xOffset -= icon.getIconWidth(); 
/* 144 */       int iconY = height / 2 - icon.getIconHeight() / 2;
/* 145 */       icon.paintIcon(this.frame, g, xOffset, iconY);
/* 146 */       xOffset += (leftToRight ? (icon.getIconWidth() + 2) : -2);
/*     */     } 
/*     */ 
/*     */     
/* 150 */     if (frameTitle != null) {
/*     */       
/* 152 */       Font f = getFont();
/* 153 */       g.setFont(f);
/* 154 */       FontMetrics fm = g.getFontMetrics();
/* 155 */       titleLength = fm.stringWidth(frameTitle);
/* 156 */       if (this.isPalette) {
/*     */ 
/*     */         
/* 159 */         if (isSelected) {
/*     */           
/* 161 */           g.setColor(Color.white);
/*     */         } else {
/*     */           
/* 164 */           g.setColor(Color.black);
/*     */         } 
/*     */         
/* 167 */         int yOffset = (height - fm.getHeight()) / 2 + fm.getAscent();
/* 168 */         if (!leftToRight)
/* 169 */           xOffset -= titleLength; 
/* 170 */         g.drawString(frameTitle, xOffset, yOffset);
/* 171 */         xOffset += (leftToRight ? (titleLength + 2) : -2);
/*     */       }
/*     */       else {
/*     */         
/* 175 */         int yOffset = (height - fm.getHeight()) / 2 + fm.getAscent() + 1;
/* 176 */         if (!leftToRight) {
/* 177 */           xOffset -= titleLength;
/*     */         }
/* 179 */         xOffset = width / 2 - titleLength / 2;
/*     */         
/* 181 */         if (isSelected) {
/*     */ 
/*     */           
/* 184 */           Graphics2D g2 = (Graphics2D)g;
/* 185 */           GradientPaint grad = new GradientPaint((xOffset + titleLength / 2), (yOffset - 15), new Color(60, 144, 233), (xOffset + titleLength / 2), (fm.getHeight() + 6), new Color(102, 186, 255));
/* 186 */           g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 187 */           g2.setPaint(grad);
/* 188 */           g2.fillRoundRect(xOffset - 8, yOffset - 15, titleLength + 15, fm.getHeight() + 1, 18, 18);
/* 189 */           g.setColor(new Color(0, 78, 167));
/* 190 */           g2.drawRoundRect(xOffset - 8, yOffset - 15, titleLength + 15, fm.getHeight() + 1, 18, 18);
/*     */           
/* 192 */           g.setColor(Color.black);
/* 193 */           g.drawString(frameTitle, xOffset + 1, yOffset);
/*     */           
/* 195 */           g.setColor(this.normalTitleColor);
/* 196 */           g.drawString(frameTitle, xOffset, yOffset - 1);
/*     */ 
/*     */           
/* 199 */           xOffset += (leftToRight ? (titleLength + 2) : -2);
/*     */         }
/*     */         else {
/*     */           
/* 203 */           Graphics2D g2 = (Graphics2D)g;
/* 204 */           GradientPaint grad = new GradientPaint((xOffset + titleLength / 2), (yOffset - 15), new Color(191, 211, 233), (xOffset + titleLength / 2), (fm.getHeight() + 6), new Color(233, 253, 255));
/* 205 */           g2.setPaint(grad);
/* 206 */           g2.fillRoundRect(xOffset - 8, yOffset - 15, titleLength + 15, fm.getHeight() + 1, 18, 18);
/* 207 */           g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 208 */           g.setColor(new Color(125, 145, 167));
/* 209 */           g2.drawRoundRect(xOffset - 8, yOffset - 15, titleLength + 15, fm.getHeight() + 1, 18, 18);
/*     */           
/* 211 */           g.setColor(Color.black);
/* 212 */           g.drawString(frameTitle, xOffset, yOffset - 1);
/*     */           
/* 214 */           xOffset += (leftToRight ? (titleLength + 2) : -2);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawLiquidCaption(Graphics g, boolean isSelected, int w, int h) {
/* 222 */     Color c = isSelected ? new Color(62, 145, 235) : new Color(175, 214, 255);
/* 223 */     g.setColor(c);
/* 224 */     g.fillRect(0, 0, w, h - 1);
/* 225 */     c = isSelected ? new Color(94, 172, 255) : new Color(226, 240, 255);
/* 226 */     g.setColor(c);
/* 227 */     g.drawLine(0, 0, w, 0);
/* 228 */     c = isSelected ? new Color(60, 141, 228) : new Color(170, 207, 247);
/* 229 */     g.setColor(c);
/* 230 */     g.drawLine(0, 1, w, 1);
/* 231 */     for (int i = 4; i < h - 1; i += 4) {
/*     */       
/* 233 */       c = isSelected ? new Color(59, 138, 223) : new Color(166, 203, 242);
/* 234 */       g.setColor(c);
/* 235 */       g.drawLine(0, i, w, i);
/* 236 */       c = isSelected ? new Color(60, 141, 228) : new Color(170, 207, 247);
/* 237 */       g.setColor(c);
/* 238 */       g.drawLine(0, i + 1, w, i + 1);
/*     */     } 
/* 240 */     c = isSelected ? new Color(47, 111, 180) : new Color(135, 164, 196);
/* 241 */     g.setColor(c);
/* 242 */     g.drawLine(0, h - 1, w, h - 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 252 */   protected LayoutManager createLayout() { return this; }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addSubComponents() {
/* 257 */     add(this.iconButton);
/* 258 */     add(this.maxButton);
/* 259 */     add(this.closeButton);
/* 260 */     if (this.menuBar != null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setButtonIcons() {}
/*     */ 
/*     */ 
/*     */   
/*     */   class RolloverListener
/*     */     implements MouseListener
/*     */   {
/*     */     JButton button;
/*     */     
/*     */     Action action;
/*     */     
/*     */     private final LiquidInternalFrameTitlePane this$0;
/*     */ 
/*     */     
/*     */     public RolloverListener(LiquidInternalFrameTitlePane this$0, JButton b, Action a) {
/* 280 */       this.this$0 = this$0;
/* 281 */       this.button = b;
/* 282 */       this.action = a;
/*     */     }
/*     */ 
/*     */     
/* 286 */     public void mouseClicked(MouseEvent e) { this.action.actionPerformed(new ActionEvent(this, 1001, this.button.getText())); }
/*     */ 
/*     */ 
/*     */     
/*     */     public void mousePressed(MouseEvent e) {}
/*     */ 
/*     */ 
/*     */     
/*     */     public void mouseReleased(MouseEvent e) {}
/*     */ 
/*     */ 
/*     */     
/*     */     public void mouseEntered(MouseEvent e) {
/* 299 */       this.button.getModel().setRollover(true);
/* 300 */       if (!this.button.isEnabled())
/*     */       {
/* 302 */         this.button.setEnabled(true);
/*     */       }
/* 304 */       this.button.repaint();
/*     */     }
/*     */ 
/*     */     
/*     */     public void mouseExited(MouseEvent e) {
/* 309 */       this.button.getModel().setRollover(false);
/* 310 */       if (!this.this$0.frame.isSelected())
/*     */       {
/* 312 */         this.button.setEnabled(false);
/*     */       }
/* 314 */       this.button.repaint();
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
/*     */   protected void createButtons() {
/* 328 */     if (iconButtonUI == null) {
/*     */       
/* 330 */       iconButtonUI = LiquidWindowButtonUI.createButtonUIForType(2);
/* 331 */       maxButtonUI = LiquidWindowButtonUI.createButtonUIForType(1);
/* 332 */       closeButtonUI = LiquidWindowButtonUI.createButtonUIForType(0);
/*     */     } 
/*     */     
/* 335 */     this.iconButton = new SpecialUIButton(iconButtonUI, this.frame);
/* 336 */     this.iconButton.addActionListener(this.iconifyAction);
/* 337 */     this.iconButton.setRolloverEnabled(true);
/* 338 */     this.iconButton.addMouseListener(new RolloverListener(this, this.iconButton, this.iconifyAction));
/*     */     
/* 340 */     this.maxButton = new SpecialUIButton(maxButtonUI, this.frame);
/* 341 */     this.maxButton.addActionListener(this.maximizeAction);
/* 342 */     this.maxButton.setRolloverEnabled(true);
/* 343 */     this.maxButton.addMouseListener(new RolloverListener(this, this.maxButton, this.maximizeAction));
/*     */     
/* 345 */     this.closeButton = new SpecialUIButton(closeButtonUI, this.frame);
/* 346 */     this.closeButton.addActionListener(this.closeAction);
/* 347 */     this.closeButton.setRolloverEnabled(true);
/* 348 */     this.closeButton.addMouseListener(new RolloverListener(this, this.closeButton, this.closeAction));
/*     */     
/* 350 */     this.iconButton.getAccessibleContext().setAccessibleName(UIManager.getString("InternalFrameTitlePane.iconifyButtonAccessibleName"));
/*     */ 
/*     */     
/* 353 */     this.maxButton.getAccessibleContext().setAccessibleName(UIManager.getString("InternalFrameTitlePane.maximizeButtonAccessibleName"));
/*     */ 
/*     */     
/* 356 */     this.closeButton.getAccessibleContext().setAccessibleName(UIManager.getString("InternalFrameTitlePane.closeButtonAccessibleName"));
/*     */ 
/*     */     
/* 359 */     if (this.frame.isSelected()) {
/*     */       
/* 361 */       activate();
/*     */     } else {
/*     */       
/* 364 */       deactivate();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void paintPalette(Graphics g) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPalette(boolean b) {
/* 381 */     this.isPalette = b;
/*     */     
/* 383 */     if (this.isPalette) {
/*     */       
/* 385 */       this.closeButton.setIcon(this.paletteCloseIcon);
/* 386 */       if (this.frame.isMaximizable())
/* 387 */         remove(this.maxButton); 
/* 388 */       if (this.frame.isIconifiable()) {
/* 389 */         remove(this.iconButton);
/*     */       }
/*     */     } else {
/* 392 */       this.closeButton.setIcon(this.closeIcon);
/* 393 */       if (this.frame.isMaximizable())
/* 394 */         add(this.maxButton); 
/* 395 */       if (this.frame.isIconifiable())
/* 396 */         add(this.iconButton); 
/*     */     } 
/* 398 */     revalidate();
/* 399 */     repaint();
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
/*     */   public void addLayoutComponent(String name, Component c) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeLayoutComponent(Component c) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 430 */   public Dimension preferredLayoutSize(Container c) { return getPreferredSize(c); }
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
/* 441 */   public Dimension getPreferredSize(Container c) { return new Dimension((c.getSize()).width, this.isPalette ? this.paletteTitleHeight : this.frameTitleHeight); }
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
/* 459 */   public Dimension getMinimumSize() { return new Dimension(70, 25); }
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
/* 470 */   public Dimension minimumLayoutSize(Container c) { return preferredLayoutSize(c); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void layoutContainer(Container c) {
/* 480 */     boolean leftToRight = this.frame.getComponentOrientation().isLeftToRight();
/*     */     
/* 482 */     int buttonHeight = (this.closeButton.getPreferredSize()).height;
/*     */     
/* 484 */     int w = getWidth();
/* 485 */     int x = leftToRight ? w : 0;
/* 486 */     int y = (getHeight() - buttonHeight) / 2 + 1;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 491 */     int buttonWidth = 18;
/*     */     
/* 493 */     if (this.frame.isClosable())
/*     */     {
/* 495 */       if (this.isPalette) {
/*     */         
/* 497 */         int spacing = 0;
/* 498 */         x += (leftToRight ? (-spacing - buttonWidth) : spacing);
/* 499 */         this.closeButton.setBounds(x, y, buttonWidth, buttonHeight);
/* 500 */         if (!leftToRight) {
/* 501 */           x += buttonWidth;
/*     */         }
/*     */       } else {
/*     */         
/* 505 */         int spacing = 0;
/* 506 */         x += (leftToRight ? (-spacing - buttonWidth) : spacing);
/* 507 */         this.closeButton.setBounds(x, y, buttonWidth, buttonHeight);
/* 508 */         if (!leftToRight) {
/* 509 */           x += buttonWidth;
/*     */         }
/*     */       } 
/*     */     }
/* 513 */     if (this.frame.isMaximizable() && !this.isPalette) {
/*     */       
/* 515 */       int spacing = 0;
/* 516 */       x += (leftToRight ? (-spacing - buttonWidth) : spacing);
/* 517 */       this.maxButton.setBounds(x, y, buttonWidth, buttonHeight);
/* 518 */       if (!leftToRight) {
/* 519 */         x += buttonWidth;
/*     */       }
/*     */     } 
/* 522 */     if (this.frame.isIconifiable() && !this.isPalette) {
/*     */       
/* 524 */       int spacing = 0;
/* 525 */       x += (leftToRight ? (-spacing - buttonWidth) : spacing);
/* 526 */       this.iconButton.setBounds(x, y, buttonWidth, buttonHeight);
/* 527 */       if (!leftToRight) {
/* 528 */         x += buttonWidth;
/*     */       }
/*     */     } 
/* 531 */     this.buttonsWidth = leftToRight ? (w - x) : x;
/*     */   }
/*     */ 
/*     */   
/*     */   public void activate() {
/* 536 */     this.closeButton.setEnabled(true);
/* 537 */     this.iconButton.setEnabled(true);
/* 538 */     this.maxButton.setEnabled(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void deactivate() {
/* 543 */     this.closeButton.setEnabled(false);
/* 544 */     this.iconButton.setEnabled(false);
/* 545 */     this.maxButton.setEnabled(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 553 */   public Font getFont() { return this.isPalette ? super.getFont() : UIManager.getFont("InternalFrame.normalTitleFont"); }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\LiquidInternalFrameTitlePane.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */