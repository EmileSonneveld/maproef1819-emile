/*      */ package com.birosoft.liquid;
/*      */ 
/*      */ import java.awt.Component;
/*      */ import java.awt.Container;
/*      */ import java.awt.Cursor;
/*      */ import java.awt.Dialog;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Frame;
/*      */ import java.awt.Insets;
/*      */ import java.awt.LayoutManager;
/*      */ import java.awt.LayoutManager2;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.Toolkit;
/*      */ import java.awt.Window;
/*      */ import java.awt.event.MouseEvent;
/*      */ import java.beans.PropertyChangeEvent;
/*      */ import javax.swing.JComponent;
/*      */ import javax.swing.JLayeredPane;
/*      */ import javax.swing.JRootPane;
/*      */ import javax.swing.LookAndFeel;
/*      */ import javax.swing.SwingUtilities;
/*      */ import javax.swing.event.MouseInputListener;
/*      */ import javax.swing.plaf.ComponentUI;
/*      */ import javax.swing.plaf.basic.BasicRootPaneUI;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class LiquidRootPaneUI
/*      */   extends BasicRootPaneUI
/*      */ {
/*   66 */   private static final String[] borderKeys = { null, "RootPane.frameBorder", "RootPane.plainDialogBorder", "RootPane.informationDialogBorder", "RootPane.errorDialogBorder", "RootPane.colorChooserDialogBorder", "RootPane.fileChooserDialogBorder", "RootPane.questionDialogBorder", "RootPane.warningDialogBorder" };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int CORNER_DRAG_WIDTH = 16;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int BORDER_DRAG_THICKNESS = 5;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int[] cursorMapping = { 
/*   88 */       6, 6, 8, 7, 7, 6, 0, 0, 0, 7, 10, 0, 0, 0, 11, 4, 0, 0, 0, 5, 4, 4, 9, 5, 5 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Window window;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private JComponent titlePane;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private MouseInputListener mouseInputListener;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private LayoutManager layoutManager;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private LayoutManager savedOldLayout;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private JRootPane root;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  137 */   private Cursor lastCursor = Cursor.getPredefinedCursor(0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  146 */   public static ComponentUI createUI(JComponent c) { return new LiquidRootPaneUI(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void installUI(JComponent c) {
/*  164 */     super.installUI(c);
/*  165 */     this.root = (JRootPane)c;
/*      */     
/*  167 */     int style = this.root.getWindowDecorationStyle();
/*      */     
/*  169 */     if (style != 0) {
/*  170 */       installClientDecorations(this.root);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void uninstallUI(JComponent c) {
/*  186 */     super.uninstallUI(c);
/*  187 */     uninstallClientDecorations(this.root);
/*      */     
/*  189 */     this.layoutManager = null;
/*  190 */     this.mouseInputListener = null;
/*  191 */     this.root = null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void installBorder(JRootPane root) {
/*  199 */     int style = root.getWindowDecorationStyle();
/*      */     
/*  201 */     if (style == 0) {
/*  202 */       LookAndFeel.uninstallBorder(root);
/*      */     } else {
/*      */       
/*  205 */       LookAndFeel.installBorder(root, borderKeys[style]);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  213 */   private void uninstallBorder(JRootPane root) { LookAndFeel.uninstallBorder(root); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void installWindowListeners(JRootPane root, Component parent) {
/*  227 */     if (parent instanceof Window) {
/*  228 */       this.window = (Window)parent;
/*      */     } else {
/*  230 */       this.window = SwingUtilities.getWindowAncestor(parent);
/*      */     } 
/*      */     
/*  233 */     if (this.window != null) {
/*  234 */       if (this.mouseInputListener == null) {
/*  235 */         this.mouseInputListener = createWindowMouseInputListener(root);
/*      */       }
/*      */       
/*  238 */       this.window.addMouseListener(this.mouseInputListener);
/*  239 */       this.window.addMouseMotionListener(this.mouseInputListener);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void uninstallWindowListeners(JRootPane root) {
/*  248 */     if (this.window != null) {
/*  249 */       this.window.removeMouseListener(this.mouseInputListener);
/*  250 */       this.window.removeMouseMotionListener(this.mouseInputListener);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void installLayout(JRootPane root) {
/*  259 */     if (this.layoutManager == null) {
/*  260 */       this.layoutManager = createLayoutManager();
/*      */     }
/*      */     
/*  263 */     this.savedOldLayout = root.getLayout();
/*  264 */     root.setLayout(this.layoutManager);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void uninstallLayout(JRootPane root) {
/*  271 */     if (this.savedOldLayout != null) {
/*  272 */       root.setLayout(this.savedOldLayout);
/*  273 */       this.savedOldLayout = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void installClientDecorations(JRootPane root) {
/*  283 */     installBorder(root);
/*      */     
/*  285 */     JComponent titlePane = createTitlePane(root);
/*      */     
/*  287 */     setTitlePane(root, titlePane);
/*  288 */     installWindowListeners(root, root.getParent());
/*  289 */     installLayout(root);
/*      */     
/*  291 */     if (this.window != null) {
/*  292 */       root.revalidate();
/*  293 */       root.repaint();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void uninstallClientDecorations(JRootPane root) {
/*  305 */     uninstallBorder(root);
/*  306 */     uninstallWindowListeners(root);
/*  307 */     setTitlePane(root, null);
/*  308 */     uninstallLayout(root);
/*  309 */     root.repaint();
/*  310 */     root.revalidate();
/*      */ 
/*      */     
/*  313 */     if (this.window != null) {
/*  314 */       this.window.setCursor(Cursor.getPredefinedCursor(0));
/*      */     }
/*      */     
/*  317 */     this.window = null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  325 */   private JComponent createTitlePane(JRootPane root) { return new LiquidTitlePane(root, this); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  333 */   private MouseInputListener createWindowMouseInputListener(JRootPane root) { return new MouseInputHandler(this, null); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  341 */   private LayoutManager createLayoutManager() { return new MetalRootLayout(null); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setTitlePane(JRootPane root, JComponent titlePane) {
/*  354 */     JLayeredPane layeredPane = root.getLayeredPane();
/*  355 */     JComponent oldTitlePane = getTitlePane();
/*      */     
/*  357 */     if (oldTitlePane != null) {
/*  358 */       oldTitlePane.setVisible(false);
/*  359 */       layeredPane.remove(oldTitlePane);
/*      */     } 
/*      */     
/*  362 */     if (titlePane != null) {
/*  363 */       layeredPane.add(titlePane, JLayeredPane.FRAME_CONTENT_LAYER);
/*  364 */       titlePane.setVisible(true);
/*      */     } 
/*      */     
/*  367 */     this.titlePane = titlePane;
/*  368 */     root.validate();
/*  369 */     root.repaint();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  380 */   private JComponent getTitlePane() { return this.titlePane; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  388 */   private JRootPane getRootPane() { return this.root; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void propertyChange(PropertyChangeEvent e) {
/*  410 */     super.propertyChange(e);
/*      */     
/*  412 */     String propertyName = e.getPropertyName();
/*      */     
/*  414 */     if (propertyName == null) {
/*      */       return;
/*      */     }
/*      */     
/*  418 */     if (propertyName.equals("windowDecorationStyle")) {
/*  419 */       JRootPane root = (JRootPane)e.getSource();
/*  420 */       int style = root.getWindowDecorationStyle();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  426 */       uninstallClientDecorations(root);
/*      */       
/*  428 */       if (style != 0) {
/*  429 */         installClientDecorations(root);
/*      */       }
/*  431 */     } else if (propertyName.equals("ancestor")) {
/*  432 */       uninstallWindowListeners(this.root);
/*      */       
/*  434 */       if (((JRootPane)e.getSource()).getWindowDecorationStyle() != 0) {
/*  435 */         installWindowListeners(this.root, this.root.getParent());
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static class MetalRootLayout
/*      */     implements LayoutManager2
/*      */   {
/*      */     private MetalRootLayout() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Dimension preferredLayoutSize(Container parent) {
/*      */       Dimension cpd;
/*  461 */       int cpWidth = 0;
/*  462 */       int cpHeight = 0;
/*  463 */       int mbWidth = 0;
/*  464 */       int mbHeight = 0;
/*  465 */       int tpWidth = 0;
/*  466 */       int tpHeight = 0;
/*  467 */       Insets i = parent.getInsets();
/*  468 */       JRootPane root = (JRootPane)parent;
/*      */       
/*  470 */       if (root.getContentPane() != null) {
/*  471 */         cpd = root.getContentPane().getPreferredSize();
/*      */       } else {
/*  473 */         cpd = root.getSize();
/*      */       } 
/*      */       
/*  476 */       if (cpd != null) {
/*  477 */         cpWidth = cpd.width;
/*  478 */         cpHeight = cpd.height;
/*      */       } 
/*      */       
/*  481 */       if (root.getJMenuBar() != null) {
/*  482 */         Dimension mbd = root.getJMenuBar().getPreferredSize();
/*      */         
/*  484 */         if (mbd != null) {
/*  485 */           mbWidth = mbd.width;
/*  486 */           mbHeight = mbd.height;
/*      */         } 
/*      */       } 
/*      */       
/*  490 */       if (root.getWindowDecorationStyle() != 0 && root.getUI() instanceof LiquidRootPaneUI) {
/*      */         
/*  492 */         JComponent titlePane = ((LiquidRootPaneUI)root.getUI()).getTitlePane();
/*      */         
/*  494 */         if (titlePane != null) {
/*  495 */           Dimension tpd = titlePane.getPreferredSize();
/*      */           
/*  497 */           if (tpd != null) {
/*  498 */             tpWidth = tpd.width;
/*  499 */             tpHeight = tpd.height;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  504 */       return new Dimension(Math.max(Math.max(cpWidth, mbWidth), tpWidth) + i.left + i.right, cpHeight + mbHeight + tpWidth + i.top + i.bottom);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Dimension minimumLayoutSize(Container parent) {
/*      */       Dimension cpd;
/*  519 */       int cpWidth = 0;
/*  520 */       int cpHeight = 0;
/*  521 */       int mbWidth = 0;
/*  522 */       int mbHeight = 0;
/*  523 */       int tpWidth = 0;
/*  524 */       int tpHeight = 0;
/*  525 */       Insets i = parent.getInsets();
/*  526 */       JRootPane root = (JRootPane)parent;
/*      */       
/*  528 */       if (root.getContentPane() != null) {
/*  529 */         cpd = root.getContentPane().getMinimumSize();
/*      */       } else {
/*  531 */         cpd = root.getSize();
/*      */       } 
/*      */       
/*  534 */       if (cpd != null) {
/*  535 */         cpWidth = cpd.width;
/*  536 */         cpHeight = cpd.height;
/*      */       } 
/*      */       
/*  539 */       if (root.getJMenuBar() != null) {
/*  540 */         Dimension mbd = root.getJMenuBar().getMinimumSize();
/*      */         
/*  542 */         if (mbd != null) {
/*  543 */           mbWidth = mbd.width;
/*  544 */           mbHeight = mbd.height;
/*      */         } 
/*      */       } 
/*      */       
/*  548 */       if (root.getWindowDecorationStyle() != 0 && root.getUI() instanceof LiquidRootPaneUI) {
/*      */         
/*  550 */         JComponent titlePane = ((LiquidRootPaneUI)root.getUI()).getTitlePane();
/*      */         
/*  552 */         if (titlePane != null) {
/*  553 */           Dimension tpd = titlePane.getMinimumSize();
/*      */           
/*  555 */           if (tpd != null) {
/*  556 */             tpWidth = tpd.width;
/*  557 */             tpHeight = tpd.height;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  562 */       return new Dimension(Math.max(Math.max(cpWidth, mbWidth), tpWidth) + i.left + i.right, cpHeight + mbHeight + tpWidth + i.top + i.bottom);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Dimension maximumLayoutSize(Container target) {
/*  577 */       int cpWidth = Integer.MAX_VALUE;
/*  578 */       int cpHeight = Integer.MAX_VALUE;
/*  579 */       int mbWidth = Integer.MAX_VALUE;
/*  580 */       int mbHeight = Integer.MAX_VALUE;
/*  581 */       int tpWidth = Integer.MAX_VALUE;
/*  582 */       int tpHeight = Integer.MAX_VALUE;
/*  583 */       Insets i = target.getInsets();
/*  584 */       JRootPane root = (JRootPane)target;
/*      */       
/*  586 */       if (root.getContentPane() != null) {
/*  587 */         Dimension cpd = root.getContentPane().getMaximumSize();
/*      */         
/*  589 */         if (cpd != null) {
/*  590 */           cpWidth = cpd.width;
/*  591 */           cpHeight = cpd.height;
/*      */         } 
/*      */       } 
/*      */       
/*  595 */       if (root.getJMenuBar() != null) {
/*  596 */         Dimension mbd = root.getJMenuBar().getMaximumSize();
/*      */         
/*  598 */         if (mbd != null) {
/*  599 */           mbWidth = mbd.width;
/*  600 */           mbHeight = mbd.height;
/*      */         } 
/*      */       } 
/*      */       
/*  604 */       if (root.getWindowDecorationStyle() != 0 && root.getUI() instanceof LiquidRootPaneUI) {
/*      */         
/*  606 */         JComponent titlePane = ((LiquidRootPaneUI)root.getUI()).getTitlePane();
/*      */         
/*  608 */         if (titlePane != null) {
/*  609 */           Dimension tpd = titlePane.getMaximumSize();
/*      */           
/*  611 */           if (tpd != null) {
/*  612 */             tpWidth = tpd.width;
/*  613 */             tpHeight = tpd.height;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  618 */       int maxHeight = Math.max(Math.max(cpHeight, mbHeight), tpHeight);
/*      */ 
/*      */ 
/*      */       
/*  622 */       if (maxHeight != Integer.MAX_VALUE) {
/*  623 */         maxHeight = cpHeight + mbHeight + tpHeight + i.top + i.bottom;
/*      */       }
/*      */       
/*  626 */       int maxWidth = Math.max(Math.max(cpWidth, mbWidth), tpWidth);
/*      */ 
/*      */       
/*  629 */       if (maxWidth != Integer.MAX_VALUE) {
/*  630 */         maxWidth += i.left + i.right;
/*      */       }
/*      */       
/*  633 */       return new Dimension(maxWidth, maxHeight);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void layoutContainer(Container parent) {
/*  643 */       JRootPane root = (JRootPane)parent;
/*  644 */       Rectangle b = root.getBounds();
/*  645 */       Insets i = root.getInsets();
/*  646 */       int nextY = 0;
/*  647 */       int w = b.width - i.right - i.left;
/*  648 */       int h = b.height - i.top - i.bottom;
/*      */       
/*  650 */       if (root.getLayeredPane() != null) {
/*  651 */         root.getLayeredPane().setBounds(i.left, i.top, w, h);
/*      */       }
/*      */       
/*  654 */       if (root.getGlassPane() != null) {
/*  655 */         root.getGlassPane().setBounds(i.left, i.top, w, h);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  660 */       if (root.getWindowDecorationStyle() != 0 && root.getUI() instanceof LiquidRootPaneUI) {
/*      */         
/*  662 */         JComponent titlePane = ((LiquidRootPaneUI)root.getUI()).getTitlePane();
/*      */         
/*  664 */         if (titlePane != null) {
/*  665 */           Dimension tpd = titlePane.getPreferredSize();
/*      */           
/*  667 */           if (tpd != null) {
/*  668 */             int tpHeight = tpd.height;
/*  669 */             titlePane.setBounds(0, 0, w, tpHeight);
/*  670 */             nextY += tpHeight;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  675 */       if (root.getJMenuBar() != null) {
/*  676 */         Dimension mbd = root.getJMenuBar().getPreferredSize();
/*  677 */         root.getJMenuBar().setBounds(0, nextY, w, mbd.height);
/*  678 */         nextY += mbd.height;
/*      */       } 
/*      */       
/*  681 */       if (root.getContentPane() != null) {
/*  682 */         Dimension cpd = root.getContentPane().getPreferredSize();
/*  683 */         root.getContentPane().setBounds(0, nextY, w, (h < nextY) ? 0 : (h - nextY));
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void addLayoutComponent(String name, Component comp) {}
/*      */ 
/*      */     
/*      */     public void removeLayoutComponent(Component comp) {}
/*      */ 
/*      */     
/*      */     public void addLayoutComponent(Component comp, Object constraints) {}
/*      */ 
/*      */     
/*  698 */     public float getLayoutAlignmentX(Container target) { return 0.0F; }
/*      */ 
/*      */ 
/*      */     
/*  702 */     public float getLayoutAlignmentY(Container target) { return 0.0F; }
/*      */     
/*      */     public void invalidateLayout(Container target) {}
/*      */   }
/*      */   
/*      */   private class MouseInputHandler
/*      */     implements MouseInputListener
/*      */   {
/*      */     private boolean isMovingWindow;
/*      */     private int dragCursor;
/*      */     private int dragOffsetX;
/*      */     
/*  714 */     private MouseInputHandler(LiquidRootPaneUI this$0) { this.this$0 = this$0; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private int dragOffsetY;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private int dragWidth;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private int dragHeight;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private final LiquidRootPaneUI this$0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void mousePressed(MouseEvent ev) {
/*  746 */       JRootPane rootPane = this.this$0.getRootPane();
/*      */       
/*  748 */       if (rootPane.getWindowDecorationStyle() == 0) {
/*      */         return;
/*      */       }
/*      */       
/*  752 */       Point dragWindowOffset = ev.getPoint();
/*  753 */       Window w = (Window)ev.getSource();
/*  754 */       Point convertedDragWindowOffset = SwingUtilities.convertPoint(w, dragWindowOffset, this.this$0.getTitlePane());
/*      */ 
/*      */       
/*  757 */       Frame f = null;
/*  758 */       Dialog d = null;
/*      */       
/*  760 */       if (w instanceof Frame) {
/*  761 */         f = (Frame)w;
/*  762 */       } else if (w instanceof Dialog) {
/*  763 */         d = (Dialog)w;
/*      */       } 
/*      */       
/*  766 */       int frameState = (f != null) ? f.getExtendedState() : 0;
/*      */       
/*  768 */       if (this.this$0.getTitlePane() != null && this.this$0.getTitlePane().contains(convertedDragWindowOffset)) {
/*      */         
/*  770 */         if (ev.getClickCount() == 2 && 
/*  771 */           f != null && f.isResizable()) {
/*  772 */           if ((frameState & 0x2) == 2 || (frameState & 0x4) == 4) {
/*      */             
/*  774 */             f.setExtendedState(frameState & 0xFFFFFFF9);
/*      */           } else {
/*      */             
/*  777 */             f.setExtendedState(frameState | 0x6);
/*      */           } 
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/*      */ 
/*      */         
/*  785 */         if (((f != null && (frameState & 0x2) != 2 && (frameState & 0x4) != 4) || d != null) && dragWindowOffset.y >= 5 && dragWindowOffset.x >= 5 && dragWindowOffset.x < w.getWidth() - 5) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  793 */           this.isMovingWindow = true;
/*  794 */           this.dragOffsetX = dragWindowOffset.x;
/*  795 */           this.dragOffsetY = dragWindowOffset.y;
/*      */         } 
/*  797 */       } else if ((f != null && f.isResizable() && (frameState & 0x2) != 2 && (frameState & 0x4) != 4) || (d != null && d.isResizable())) {
/*      */ 
/*      */ 
/*      */         
/*  801 */         this.dragOffsetX = dragWindowOffset.x;
/*  802 */         this.dragOffsetY = dragWindowOffset.y;
/*  803 */         this.dragWidth = w.getWidth();
/*  804 */         this.dragHeight = w.getHeight();
/*  805 */         this.dragCursor = getCursor(calculateCorner(w, dragWindowOffset.x, dragWindowOffset.y));
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void mouseReleased(MouseEvent ev) {
/*  811 */       if (this.dragCursor != 0 && this.this$0.window != null && !this.this$0.window.isValid()) {
/*      */ 
/*      */         
/*  814 */         this.this$0.window.validate();
/*  815 */         this.this$0.getRootPane().repaint();
/*      */       } 
/*      */       
/*  818 */       this.isMovingWindow = false;
/*  819 */       this.dragCursor = 0;
/*      */     }
/*      */     
/*      */     public void mouseMoved(MouseEvent ev) {
/*  823 */       JRootPane root = this.this$0.getRootPane();
/*      */       
/*  825 */       if (root.getWindowDecorationStyle() == 0) {
/*      */         return;
/*      */       }
/*      */       
/*  829 */       Window w = (Window)ev.getSource();
/*      */       
/*  831 */       Frame f = null;
/*  832 */       Dialog d = null;
/*      */       
/*  834 */       if (w instanceof Frame) {
/*  835 */         f = (Frame)w;
/*  836 */       } else if (w instanceof Dialog) {
/*  837 */         d = (Dialog)w;
/*      */       } 
/*      */ 
/*      */       
/*  841 */       int cursor = getCursor(calculateCorner(w, ev.getX(), ev.getY()));
/*      */       
/*  843 */       if (cursor != 0 && ((f != null && f.isResizable() && (f.getExtendedState() & 0x4) != 4 && (f.getExtendedState() & 0x2) != 2) || (d != null && d.isResizable()))) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  849 */         w.setCursor(Cursor.getPredefinedCursor(cursor));
/*      */       } else {
/*  851 */         w.setCursor(this.this$0.lastCursor);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     private void adjust(Rectangle bounds, Dimension min, int deltaX, int deltaY, int deltaWidth, int deltaHeight) {
/*  857 */       bounds.x += deltaX;
/*  858 */       bounds.y += deltaY;
/*  859 */       bounds.width += deltaWidth;
/*  860 */       bounds.height += deltaHeight;
/*      */       
/*  862 */       if (min != null) {
/*  863 */         if (bounds.width < min.width) {
/*  864 */           int correction = min.width - bounds.width;
/*      */           
/*  866 */           if (deltaX != 0) {
/*  867 */             bounds.x -= correction;
/*      */           }
/*      */           
/*  870 */           bounds.width = min.width;
/*      */         } 
/*      */         
/*  873 */         if (bounds.height < min.height) {
/*  874 */           int correction = min.height - bounds.height;
/*      */           
/*  876 */           if (deltaY != 0) {
/*  877 */             bounds.y -= correction;
/*      */           }
/*      */           
/*  880 */           bounds.height = min.height;
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     public void mouseDragged(MouseEvent ev) {
/*  886 */       Window w = (Window)ev.getSource();
/*  887 */       Point pt = ev.getPoint();
/*      */       
/*  889 */       if (this.isMovingWindow) {
/*  890 */         Point windowPt = w.getLocationOnScreen();
/*      */         
/*  892 */         windowPt.x += pt.x - this.dragOffsetX;
/*  893 */         windowPt.y += pt.y - this.dragOffsetY;
/*  894 */         w.setLocation(windowPt);
/*  895 */       } else if (this.dragCursor != 0) {
/*  896 */         Rectangle r = w.getBounds();
/*  897 */         Rectangle startBounds = new Rectangle(r);
/*  898 */         Dimension min = w.getMinimumSize();
/*      */         
/*  900 */         switch (this.dragCursor) {
/*      */           case 11:
/*  902 */             adjust(r, min, 0, 0, pt.x + this.dragWidth - this.dragOffsetX - r.width, 0);
/*      */             break;
/*      */ 
/*      */ 
/*      */           
/*      */           case 9:
/*  908 */             adjust(r, min, 0, 0, 0, pt.y + this.dragHeight - this.dragOffsetY - r.height);
/*      */             break;
/*      */ 
/*      */ 
/*      */           
/*      */           case 8:
/*  914 */             adjust(r, min, 0, pt.y - this.dragOffsetY, 0, -(pt.y - this.dragOffsetY));
/*      */             break;
/*      */ 
/*      */ 
/*      */           
/*      */           case 10:
/*  920 */             adjust(r, min, pt.x - this.dragOffsetX, 0, -(pt.x - this.dragOffsetX), 0);
/*      */             break;
/*      */ 
/*      */ 
/*      */           
/*      */           case 7:
/*  926 */             adjust(r, min, 0, pt.y - this.dragOffsetY, pt.x + this.dragWidth - this.dragOffsetX - r.width, -(pt.y - this.dragOffsetY));
/*      */             break;
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case 5:
/*  933 */             adjust(r, min, 0, 0, pt.x + this.dragWidth - this.dragOffsetX - r.width, pt.y + this.dragHeight - this.dragOffsetY - r.height);
/*      */             break;
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case 6:
/*  940 */             adjust(r, min, pt.x - this.dragOffsetX, pt.y - this.dragOffsetY, -(pt.x - this.dragOffsetX), -(pt.y - this.dragOffsetY));
/*      */             break;
/*      */ 
/*      */ 
/*      */           
/*      */           case 4:
/*  946 */             adjust(r, min, pt.x - this.dragOffsetX, 0, -(pt.x - this.dragOffsetX), pt.y + this.dragHeight - this.dragOffsetY - r.height);
/*      */             break;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  956 */         if (!r.equals(startBounds)) {
/*  957 */           w.setBounds(r);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  963 */           if (Toolkit.getDefaultToolkit().isDynamicLayoutActive()) {
/*  964 */             w.validate();
/*  965 */             this.this$0.getRootPane().repaint();
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     public void mouseEntered(MouseEvent ev) {
/*  972 */       Window w = (Window)ev.getSource();
/*  973 */       this.this$0.lastCursor = w.getCursor();
/*  974 */       mouseMoved(ev);
/*      */     }
/*      */     
/*      */     public void mouseExited(MouseEvent ev) {
/*  978 */       Window w = (Window)ev.getSource();
/*  979 */       w.setCursor(this.this$0.lastCursor);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void mouseClicked(MouseEvent ev) {}
/*      */ 
/*      */ 
/*      */     
/*      */     private int calculateCorner(Component c, int x, int y) {
/*  990 */       int xPosition = calculatePosition(x, c.getWidth());
/*  991 */       int yPosition = calculatePosition(y, c.getHeight());
/*      */       
/*  993 */       if (xPosition == -1 || yPosition == -1) {
/*  994 */         return -1;
/*      */       }
/*      */       
/*  997 */       return yPosition * 5 + xPosition;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private int getCursor(int corner) {
/* 1005 */       if (corner == -1) {
/* 1006 */         return 0;
/*      */       }
/*      */       
/* 1009 */       return cursorMapping[corner];
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private int calculatePosition(int spot, int width) {
/* 1023 */       if (spot < 5) {
/* 1024 */         return 0;
/*      */       }
/*      */       
/* 1027 */       if (spot < 16) {
/* 1028 */         return 1;
/*      */       }
/*      */       
/* 1031 */       if (spot >= width - 5) {
/* 1032 */         return 4;
/*      */       }
/*      */       
/* 1035 */       if (spot >= width - 16) {
/* 1036 */         return 3;
/*      */       }
/*      */       
/* 1039 */       return 2;
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\LiquidRootPaneUI.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */