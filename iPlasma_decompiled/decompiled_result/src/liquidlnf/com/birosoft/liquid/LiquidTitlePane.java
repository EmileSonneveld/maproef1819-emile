/*      */ package com.birosoft.liquid;
/*      */ import java.awt.Color;
/*      */ import java.awt.Component;
/*      */ import java.awt.Container;
/*      */ import java.awt.Dialog;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Font;
/*      */ import java.awt.FontMetrics;
/*      */ import java.awt.Frame;
/*      */ import java.awt.GradientPaint;
/*      */ import java.awt.Graphics;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Image;
/*      */ import java.awt.Insets;
/*      */ import java.awt.LayoutManager;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.RenderingHints;
/*      */ import java.awt.Toolkit;
/*      */ import java.awt.Window;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ComponentAdapter;
/*      */ import java.awt.event.ComponentEvent;
/*      */ import java.awt.event.ComponentListener;
/*      */ import java.awt.event.MouseEvent;
/*      */ import java.awt.event.WindowAdapter;
/*      */ import java.awt.event.WindowEvent;
/*      */ import java.awt.event.WindowListener;
/*      */ import java.beans.PropertyChangeEvent;
/*      */ import java.beans.PropertyChangeListener;
/*      */ import java.util.Locale;
/*      */ import javax.swing.AbstractAction;
/*      */ import javax.swing.Action;
/*      */ import javax.swing.Icon;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JComponent;
/*      */ import javax.swing.JMenuBar;
/*      */ import javax.swing.JMenuItem;
/*      */ import javax.swing.JPopupMenu;
/*      */ import javax.swing.JRootPane;
/*      */ import javax.swing.SwingUtilities;
/*      */ import javax.swing.UIManager;
/*      */ import javax.swing.border.Border;
/*      */ import javax.swing.border.EmptyBorder;
/*      */ 
/*      */ class LiquidTitlePane extends JComponent {
/*      */   private static final int IMAGE_HEIGHT = 16;
/*      */   private static final int IMAGE_WIDTH = 16;
/*      */   private static LiquidWindowButtonUI iconButtonUI;
/*      */   private static LiquidWindowButtonUI maxButtonUI;
/*      */   private static LiquidWindowButtonUI closeButtonUI;
/*      */   private boolean prevState;
/*   52 */   private static final Border handyEmptyBorder = new EmptyBorder(0, 0, 0, 0); Color normalTitleColor; Color shadowColor; Color disabledTitleColor;
/*      */   private PropertyChangeListener propertyChangeListener;
/*      */   private JMenuBar menuBar;
/*      */   private Action closeAction;
/*      */   
/*      */   public LiquidTitlePane(JRootPane root, LiquidRootPaneUI ui) {
/*   58 */     this.prevState = false;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   63 */     this.normalTitleColor = Color.white;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   68 */     this.shadowColor = new Color(10, 24, 131);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   73 */     this.disabledTitleColor = new Color(216, 228, 244);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  154 */     this.rootPane = root;
/*  155 */     this.rootPaneUI = ui;
/*      */     
/*  157 */     this.state = -1;
/*      */     
/*  159 */     installSubcomponents();
/*  160 */     installDefaults();
/*      */     
/*  162 */     setLayout(createLayout());
/*      */   }
/*      */   private Action iconifyAction;
/*      */   private Action restoreAction;
/*      */   private Action maximizeAction;
/*      */   
/*      */   private void uninstall() {
/*  169 */     uninstallListeners();
/*  170 */     this.window = null;
/*  171 */     removeAll();
/*      */   }
/*      */   private JButton toggleButton; private JButton iconifyButton; private JButton closeButton; private WindowListener windowListener;
/*      */   private ComponentListener windowMoveListener;
/*      */   private Window window;
/*      */   
/*      */   private void installListeners() {
/*  178 */     if (this.window != null) {
/*  179 */       this.windowListener = createWindowListener();
/*  180 */       this.window.addWindowListener(this.windowListener);
/*  181 */       this.propertyChangeListener = createWindowPropertyChangeListener();
/*  182 */       this.window.addPropertyChangeListener(this.propertyChangeListener);
/*  183 */       this.windowMoveListener = new WindowMoveListener(this);
/*  184 */       this.window.addComponentListener(this.windowMoveListener);
/*      */     } 
/*      */   }
/*      */   private JRootPane rootPane; private int buttonsWidth;
/*      */   private int state;
/*      */   private LiquidRootPaneUI rootPaneUI;
/*      */   
/*      */   private void uninstallListeners() {
/*  192 */     if (this.window != null) {
/*  193 */       this.window.removeWindowListener(this.windowListener);
/*  194 */       this.window.removePropertyChangeListener(this.propertyChangeListener);
/*  195 */       this.window.removeComponentListener(this.windowMoveListener);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  204 */   private WindowListener createWindowListener() { return new WindowHandler(this, null); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  212 */   private PropertyChangeListener createWindowPropertyChangeListener() { return new PropertyChangeHandler(this, null); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  219 */   public JRootPane getRootPane() { return this.rootPane; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  226 */   private int getWindowDecorationStyle() { return getRootPane().getWindowDecorationStyle(); }
/*      */ 
/*      */   
/*      */   public void addNotify() {
/*  230 */     super.addNotify();
/*      */     
/*  232 */     uninstallListeners();
/*      */     
/*  234 */     this.window = SwingUtilities.getWindowAncestor(this);
/*      */     
/*  236 */     if (this.window != null) {
/*  237 */       if (this.window instanceof Frame) {
/*  238 */         setState(((Frame)this.window).getExtendedState());
/*      */       } else {
/*  240 */         setState(0);
/*      */       } 
/*      */       
/*  243 */       setActive(this.window.isActive());
/*  244 */       installListeners();
/*      */     } 
/*      */   }
/*      */   
/*      */   public void removeNotify() {
/*  249 */     super.removeNotify();
/*      */     
/*  251 */     uninstallListeners();
/*  252 */     this.window = null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void installSubcomponents() {
/*  259 */     if (getWindowDecorationStyle() == 1) {
/*  260 */       createActions();
/*  261 */       this.menuBar = createMenuBar();
/*  262 */       add(this.menuBar);
/*  263 */       createButtons();
/*  264 */       add(this.iconifyButton);
/*  265 */       add(this.toggleButton);
/*  266 */       add(this.closeButton);
/*  267 */       this.iconifyButton.putClientProperty("externalFrameButton", Boolean.TRUE);
/*  268 */       this.toggleButton.putClientProperty("externalFrameButton", Boolean.TRUE);
/*  269 */       this.closeButton.putClientProperty("externalFrameButton", Boolean.TRUE);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  275 */     else if (getWindowDecorationStyle() != 0) {
/*  276 */       createActions();
/*  277 */       createButtons();
/*  278 */       add(this.closeButton);
/*  279 */       this.closeButton.putClientProperty("externalFrameButton", Boolean.FALSE);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  287 */   private void installDefaults() { setFont(UIManager.getFont("InternalFrame.titleFont", getLocale())); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void uninstallDefaults() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected JMenuBar createMenuBar() {
/*  301 */     this.menuBar = new SystemMenuBar(this, createMenu());
/*  302 */     this.menuBar.setFocusable(false);
/*  303 */     this.menuBar.setBorderPainted(true);
/*      */ 
/*      */     
/*  306 */     return this.menuBar;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void close() {
/*  313 */     Window window = getWindow();
/*      */     
/*  315 */     if (window != null) {
/*  316 */       window.dispatchEvent(new WindowEvent(window, 201));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void iconify() {
/*  325 */     Frame frame = getFrame();
/*      */     
/*  327 */     if (frame != null) {
/*  328 */       frame.setExtendedState(frame.getExtendedState() | true);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void maximize() {
/*  336 */     Frame frame = getFrame();
/*      */     
/*  338 */     if (frame != null) {
/*  339 */       setMaximizeBounds(frame);
/*  340 */       frame.setExtendedState(frame.getExtendedState() | 0x6);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void setMaximizeBounds(Frame frame) {
/*  346 */     if (frame.getMaximizedBounds() != null) {
/*      */       return;
/*      */     }
/*      */     
/*  350 */     Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(frame.getGraphicsConfiguration());
/*  351 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*      */ 
/*      */     
/*  354 */     int x = screenInsets.top;
/*  355 */     int y = screenInsets.left;
/*  356 */     int w = screenSize.width - x - screenInsets.right;
/*  357 */     int h = screenSize.height - y - screenInsets.bottom;
/*  358 */     Rectangle maxBounds = new Rectangle(x, y, w, h);
/*  359 */     frame.setMaximizedBounds(maxBounds);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void restore() {
/*  366 */     Frame frame = getFrame();
/*      */     
/*  368 */     if (frame == null) {
/*      */       return;
/*      */     }
/*      */     
/*  372 */     if ((frame.getExtendedState() & true) == 1) {
/*  373 */       frame.setExtendedState(this.state & 0xFFFFFFFE);
/*      */     } else {
/*  375 */       frame.setExtendedState(this.state & 0xFFFFFFF9);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void createActions() {
/*  384 */     this.closeAction = new CloseAction(this);
/*  385 */     this.iconifyAction = new IconifyAction(this);
/*  386 */     this.restoreAction = new RestoreAction(this);
/*  387 */     this.maximizeAction = new MaximizeAction(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private JPopupMenu createMenu() {
/*  395 */     JPopupMenu menu = new JPopupMenu();
/*      */     
/*  397 */     if (getWindowDecorationStyle() == 1) {
/*  398 */       addMenuItems(menu);
/*      */ 
/*      */       
/*  401 */       menu.putClientProperty("isSystemMenu", Boolean.TRUE);
/*      */     } 
/*      */     
/*  404 */     return menu;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void addMenuItems(JPopupMenu menu) {
/*  411 */     Locale locale = getRootPane().getLocale();
/*  412 */     JMenuItem mi = menu.add(this.restoreAction);
/*  413 */     mi.setMnemonic('r');
/*      */     
/*  415 */     mi = menu.add(this.iconifyAction);
/*  416 */     mi.setMnemonic('e');
/*      */     
/*  418 */     if (Toolkit.getDefaultToolkit().isFrameStateSupported(6)) {
/*  419 */       mi = menu.add(this.maximizeAction);
/*  420 */       mi.setMnemonic('x');
/*      */     } 
/*      */     
/*  423 */     menu.addSeparator();
/*      */     
/*  425 */     mi = menu.add(this.closeAction);
/*  426 */     mi.setMnemonic('c');
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void createButtons() {
/*  433 */     if (iconButtonUI == null) {
/*  434 */       iconButtonUI = LiquidWindowButtonUI.createButtonUIForType(2);
/*  435 */       maxButtonUI = LiquidWindowButtonUI.createButtonUIForType(1);
/*  436 */       closeButtonUI = LiquidWindowButtonUI.createButtonUIForType(0);
/*      */     } 
/*      */     
/*  439 */     this.iconifyButton = new SpecialUIButton(iconButtonUI, getFrame());
/*      */     
/*  441 */     this.iconifyButton.setAction(this.iconifyAction);
/*  442 */     this.iconifyButton.setRolloverEnabled(true);
/*      */     
/*  444 */     this.toggleButton = new SpecialUIButton(maxButtonUI, getFrame());
/*      */     
/*  446 */     this.toggleButton.setAction(this.maximizeAction);
/*  447 */     this.toggleButton.setRolloverEnabled(true);
/*      */     
/*  449 */     this.closeButton = new SpecialUIButton(closeButtonUI, getFrame());
/*      */     
/*  451 */     this.closeButton.setAction(this.closeAction);
/*  452 */     this.closeButton.setRolloverEnabled(true);
/*      */     
/*  454 */     this.closeButton.getAccessibleContext().setAccessibleName("Close");
/*  455 */     this.iconifyButton.getAccessibleContext().setAccessibleName("Iconify");
/*  456 */     this.toggleButton.getAccessibleContext().setAccessibleName("Maximize");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  464 */   private LayoutManager createLayout() { return new TitlePaneLayout(this, null); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setActive(boolean isActive) {
/*  471 */     if (getWindowDecorationStyle() == 1) {
/*  472 */       Boolean activeB = isActive ? Boolean.TRUE : Boolean.FALSE;
/*      */       
/*  474 */       this.iconifyButton.putClientProperty("paintActive", activeB);
/*  475 */       this.closeButton.putClientProperty("paintActive", activeB);
/*  476 */       this.toggleButton.putClientProperty("paintActive", activeB);
/*      */       
/*  478 */       this.iconifyButton.setEnabled(isActive);
/*  479 */       this.closeButton.setEnabled(isActive);
/*  480 */       this.toggleButton.setEnabled(isActive);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  485 */     getRootPane().repaint();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  492 */   private void setState(int state) { setState(state, false); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setState(int state, boolean updateRegardless) {
/*  500 */     Window w = getWindow();
/*      */     
/*  502 */     if (w != null && getWindowDecorationStyle() == 1) {
/*  503 */       if (this.state == state && !updateRegardless) {
/*      */         return;
/*      */       }
/*      */       
/*  507 */       Frame frame = getFrame();
/*      */       
/*  509 */       if (frame != null) {
/*  510 */         JRootPane rootPane = getRootPane();
/*      */         
/*  512 */         if ((state & 0x6) != 6 || (rootPane.getBorder() != null && !(rootPane.getBorder() instanceof javax.swing.plaf.UIResource)) || !frame.isShowing())
/*      */         {
/*      */ 
/*      */ 
/*      */           
/*  517 */           if ((state & 0x6) != 6);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  523 */         if (frame.isResizable()) {
/*  524 */           if ((state & 0x4) == 4 || (state & 0x2) == 2) {
/*      */             
/*  526 */             updateToggleButton(this.restoreAction);
/*  527 */             this.maximizeAction.setEnabled(false);
/*  528 */             this.restoreAction.setEnabled(true);
/*      */           } else {
/*  530 */             updateToggleButton(this.maximizeAction);
/*  531 */             this.maximizeAction.setEnabled(true);
/*  532 */             this.restoreAction.setEnabled(false);
/*      */           } 
/*      */           
/*  535 */           if (this.toggleButton.getParent() == null || this.iconifyButton.getParent() == null) {
/*      */             
/*  537 */             add(this.toggleButton);
/*  538 */             add(this.iconifyButton);
/*  539 */             revalidate();
/*  540 */             repaint();
/*      */           } 
/*      */           
/*  543 */           this.toggleButton.setText(null);
/*      */         } else {
/*  545 */           this.maximizeAction.setEnabled(false);
/*  546 */           this.restoreAction.setEnabled(false);
/*      */           
/*  548 */           if (this.toggleButton.getParent() != null) {
/*  549 */             remove(this.toggleButton);
/*  550 */             revalidate();
/*  551 */             repaint();
/*      */           } 
/*      */         } 
/*      */       } else {
/*      */         
/*  556 */         this.maximizeAction.setEnabled(false);
/*  557 */         this.restoreAction.setEnabled(false);
/*  558 */         this.iconifyAction.setEnabled(false);
/*  559 */         remove(this.toggleButton);
/*  560 */         remove(this.iconifyButton);
/*  561 */         revalidate();
/*  562 */         repaint();
/*      */       } 
/*      */       
/*  565 */       this.closeAction.setEnabled(true);
/*  566 */       this.state = state;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void updateToggleButton(Action action) {
/*  575 */     this.toggleButton.setAction(action);
/*  576 */     this.toggleButton.setText(null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Frame getFrame() {
/*  584 */     Window window = getWindow();
/*      */     
/*  586 */     if (window instanceof Frame) {
/*  587 */       return (Frame)window;
/*      */     }
/*      */     
/*  590 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  599 */   private Window getWindow() { return this.window; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String getTitle() {
/*  606 */     Window w = getWindow();
/*      */     
/*  608 */     if (w instanceof Frame)
/*  609 */       return ((Frame)w).getTitle(); 
/*  610 */     if (w instanceof Dialog) {
/*  611 */       return ((Dialog)w).getTitle();
/*      */     }
/*      */     
/*  614 */     return null;
/*      */   }
/*      */   
/*      */   public boolean isSelected() {
/*  618 */     Window window = getWindow();
/*      */     
/*  620 */     return (window == null) ? true : window.isActive();
/*      */   }
/*      */   
/*      */   public boolean isFrameMaximized() {
/*  624 */     Frame frame = getFrame();
/*      */     
/*  626 */     if (frame != null) {
/*  627 */       return ((frame.getExtendedState() & 0x6) == 6);
/*      */     }
/*      */     
/*  630 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void paintComponent(Graphics g) {
/*  637 */     if (getFrame() != null) {
/*  638 */       setState(getFrame().getExtendedState());
/*      */     }
/*      */     
/*  641 */     Window frame = getWindow();
/*      */     
/*  643 */     boolean leftToRight = frame.getComponentOrientation().isLeftToRight();
/*  644 */     boolean isSelected = isSelected();
/*      */     
/*  646 */     if (isSelected) {
/*  647 */       this.prevState = true;
/*      */     }
/*      */     
/*  650 */     if (!this.prevState && !isSelected) {
/*  651 */       isSelected = true;
/*      */     }
/*      */     
/*  654 */     int width = getWidth();
/*  655 */     int height = getHeight();
/*      */     
/*  657 */     Color foreground = LiquidLookAndFeel.getWindowTitleInactiveForeground();
/*  658 */     drawLiquidCaption(g, isSelected, width, height);
/*      */     
/*  660 */     int titleLength = 0;
/*  661 */     int xOffset = leftToRight ? 2 : (width - 2);
/*  662 */     String frameTitle = getTitle();
/*      */     
/*  664 */     if (frameTitle != null) {
/*  665 */       Font f = getFont();
/*  666 */       g.setFont(f);
/*      */       
/*  668 */       FontMetrics fm = g.getFontMetrics();
/*  669 */       titleLength = fm.stringWidth(frameTitle);
/*      */ 
/*      */       
/*  672 */       int yOffset = (height - fm.getHeight()) / 2 + fm.getAscent() + 1;
/*      */       
/*  674 */       if (!leftToRight) {
/*  675 */         xOffset -= titleLength;
/*      */       }
/*      */       
/*  678 */       xOffset = width / 2 - titleLength / 2;
/*      */       
/*  680 */       if (isSelected) {
/*      */         
/*  682 */         Graphics2D g2 = (Graphics2D)g;
/*  683 */         GradientPaint grad = new GradientPaint((xOffset + titleLength / 2), (yOffset - 15), new Color(60, 144, 233), (xOffset + titleLength / 2), (fm.getHeight() + 6), new Color(102, 186, 255));
/*      */ 
/*      */ 
/*      */         
/*  687 */         g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*      */         
/*  689 */         g2.setPaint(grad);
/*      */ 
/*      */         
/*  692 */         g2.fillRoundRect(xOffset - 8, yOffset - 15, titleLength + 15, height - 6, 18, 18);
/*      */         
/*  694 */         g.setColor(new Color(0, 78, 167));
/*      */ 
/*      */         
/*  697 */         g2.drawRoundRect(xOffset - 8, yOffset - 15, titleLength + 15, height - 6, 18, 18);
/*      */ 
/*      */         
/*  700 */         g.setColor(Color.black);
/*  701 */         g.drawString(frameTitle, xOffset + 1, yOffset);
/*      */         
/*  703 */         g.setColor(this.normalTitleColor);
/*  704 */         g.drawString(frameTitle, xOffset, yOffset - 1);
/*      */         
/*  706 */         xOffset += (leftToRight ? (titleLength + 2) : -2);
/*      */       } else {
/*      */         
/*  709 */         Graphics2D g2 = (Graphics2D)g;
/*  710 */         GradientPaint grad = new GradientPaint((xOffset + titleLength / 2), (yOffset - 15), new Color(191, 211, 233), (xOffset + titleLength / 2), (fm.getHeight() + 6), new Color(233, 253, 255));
/*      */ 
/*      */ 
/*      */         
/*  714 */         g2.setPaint(grad);
/*      */ 
/*      */         
/*  717 */         g2.fillRoundRect(xOffset - 8, yOffset - 15, titleLength + 15, height - 6, 18, 18);
/*      */         
/*  719 */         g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*      */         
/*  721 */         g.setColor(new Color(125, 145, 167));
/*      */ 
/*      */         
/*  724 */         g2.drawRoundRect(xOffset - 8, yOffset - 15, titleLength + 15, height - 6, 18, 18);
/*      */ 
/*      */         
/*  727 */         g.setColor(Color.black);
/*  728 */         g.drawString(frameTitle, xOffset, yOffset - 1);
/*      */         
/*  730 */         xOffset += (leftToRight ? (titleLength + 2) : -2);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void drawLiquidCaption(Graphics g, boolean isSelected, int w, int h) {
/*  736 */     Color c = isSelected ? new Color(62, 145, 235) : new Color(175, 214, 255);
/*  737 */     g.setColor(c);
/*  738 */     g.fillRect(0, 0, w, h - 1);
/*  739 */     c = isSelected ? new Color(94, 172, 255) : new Color(226, 240, 255);
/*  740 */     g.setColor(c);
/*  741 */     g.drawLine(0, 0, w, 0);
/*  742 */     c = isSelected ? new Color(60, 141, 228) : new Color(170, 207, 247);
/*  743 */     g.setColor(c);
/*  744 */     g.drawLine(0, 1, w, 1);
/*      */     
/*  746 */     for (int i = 4; i < h - 1; i += 4) {
/*  747 */       c = isSelected ? new Color(59, 138, 223) : new Color(166, 203, 242);
/*  748 */       g.setColor(c);
/*  749 */       g.drawLine(0, i, w, i);
/*  750 */       c = isSelected ? new Color(60, 141, 228) : new Color(170, 207, 247);
/*  751 */       g.setColor(c);
/*  752 */       g.drawLine(0, i + 1, w, i + 1);
/*      */     } 
/*      */     
/*  755 */     c = isSelected ? new Color(47, 111, 180) : new Color(135, 164, 196);
/*  756 */     g.setColor(c);
/*  757 */     g.drawLine(0, h - 1, w, h - 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String clippedText(String text, FontMetrics fm, int availTextWidth) {
/*  765 */     if (text == null || text.equals("")) {
/*  766 */       return "";
/*      */     }
/*      */     
/*  769 */     int textWidth = SwingUtilities.computeStringWidth(fm, text);
/*  770 */     String clipString = "...";
/*      */     
/*  772 */     if (textWidth > availTextWidth) {
/*  773 */       int totalWidth = SwingUtilities.computeStringWidth(fm, clipString);
/*      */       
/*      */       int nChars;
/*  776 */       for (nChars = 0; nChars < text.length(); nChars++) {
/*  777 */         totalWidth += fm.charWidth(text.charAt(nChars));
/*      */         
/*  779 */         if (totalWidth > availTextWidth) {
/*      */           break;
/*      */         }
/*      */       } 
/*      */       
/*  784 */       text = text.substring(0, nChars) + clipString;
/*      */     } 
/*      */     
/*  787 */     return text;
/*      */   }
/*      */   
/*      */   private int getInt(Object key, int defaultValue) {
/*  791 */     Object value = UIManager.get(key);
/*      */     
/*  793 */     if (value instanceof Integer) {
/*  794 */       return ((Integer)value).intValue();
/*      */     }
/*      */     
/*  797 */     if (value instanceof String) {
/*      */       try {
/*  799 */         return Integer.parseInt((String)value);
/*  800 */       } catch (NumberFormatException nfe) {}
/*      */     }
/*      */ 
/*      */     
/*  804 */     return defaultValue;
/*      */   }
/*      */   
/*      */   private class CloseAction
/*      */     extends AbstractAction {
/*      */     private final LiquidTitlePane this$0;
/*      */     
/*      */     public CloseAction(LiquidTitlePane this$0) {
/*  812 */       super("Close");
/*      */       this.this$0 = this$0;
/*      */     }
/*      */     
/*  816 */     public void actionPerformed(ActionEvent e) { this.this$0.close(); }
/*      */   }
/*      */   
/*      */   private class IconifyAction
/*      */     extends AbstractAction
/*      */   {
/*      */     private final LiquidTitlePane this$0;
/*      */     
/*      */     public IconifyAction(LiquidTitlePane this$0) {
/*  825 */       super("Minimize");
/*      */       this.this$0 = this$0;
/*      */     }
/*      */     
/*  829 */     public void actionPerformed(ActionEvent e) { this.this$0.iconify(); }
/*      */   }
/*      */   
/*      */   private class RestoreAction
/*      */     extends AbstractAction
/*      */   {
/*      */     private final LiquidTitlePane this$0;
/*      */     
/*      */     public RestoreAction(LiquidTitlePane this$0) {
/*  838 */       super("Restore");
/*      */       this.this$0 = this$0;
/*      */     }
/*      */     
/*  842 */     public void actionPerformed(ActionEvent e) { this.this$0.restore(); }
/*      */   }
/*      */   
/*      */   private class MaximizeAction
/*      */     extends AbstractAction
/*      */   {
/*      */     private final LiquidTitlePane this$0;
/*      */     
/*      */     public MaximizeAction(LiquidTitlePane this$0) {
/*  851 */       super("Maximize");
/*      */       this.this$0 = this$0;
/*      */     }
/*      */     
/*  855 */     public void actionPerformed(ActionEvent e) { this.this$0.maximize(); }
/*      */   }
/*      */ 
/*      */   
/*      */   private class SystemMenuBar
/*      */     extends JMenuBar
/*      */     implements MouseListener
/*      */   {
/*      */     private JPopupMenu systemMenu;
/*      */     private boolean isShowed;
/*      */     private final LiquidTitlePane this$0;
/*      */     
/*      */     public SystemMenuBar(LiquidTitlePane this$0, JPopupMenu menu) {
/*  868 */       this.this$0 = this$0;
/*      */       this.isShowed = false;
/*  870 */       this.systemMenu = menu;
/*  871 */       addMouseListener(this);
/*      */     }
/*      */ 
/*      */     
/*  875 */     protected void setSystemMenuVisible(boolean b) { this.isShowed = b; }
/*      */ 
/*      */     
/*      */     public void paint(Graphics g) {
/*  879 */       Frame frame = this.this$0.getFrame();
/*  880 */       Image image = (frame != null) ? frame.getIconImage() : null;
/*      */       
/*  882 */       if (image != null) {
/*  883 */         g.drawImage(image, 0, 0, 16, 16, null);
/*      */       } else {
/*  885 */         Icon icon = UIManager.getIcon("InternalFrame.icon");
/*      */         
/*  887 */         if (icon != null) {
/*  888 */           icon.paintIcon(this, g, 0, 0);
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  894 */     public Dimension getMinimumSize() { return getPreferredSize(); }
/*      */ 
/*      */     
/*      */     public Dimension getPreferredSize() {
/*  898 */       Icon icon = UIManager.getIcon("InternalFrame.icon");
/*      */       
/*  900 */       if (icon != null) {
/*  901 */         return new Dimension(icon.getIconWidth(), icon.getIconHeight());
/*      */       }
/*      */       
/*  904 */       Dimension size = super.getPreferredSize();
/*      */       
/*  906 */       return new Dimension(Math.max(16, size.width), Math.max(size.height, 16));
/*      */     }
/*      */ 
/*      */     
/*      */     public void mouseClicked(MouseEvent e) {
/*  911 */       if (!this.isShowed) {
/*  912 */         this.systemMenu.show(this, 0, 18);
/*  913 */         this.isShowed = true;
/*      */       } else {
/*  915 */         this.isShowed = false;
/*  916 */         this.systemMenu.setVisible(this.isShowed);
/*      */       } 
/*      */     }
/*      */     
/*      */     public void mouseEntered(MouseEvent e) {
/*  921 */       if (!this.systemMenu.isVisible()) {
/*  922 */         this.isShowed = false;
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     public void mouseExited(MouseEvent e) {}
/*      */ 
/*      */     
/*      */     public void mousePressed(MouseEvent e) {}
/*      */ 
/*      */     
/*      */     public void mouseReleased(MouseEvent e) {}
/*      */   }
/*      */   
/*      */   private class TitlePaneLayout
/*      */     implements LayoutManager
/*      */   {
/*      */     private final LiquidTitlePane this$0;
/*      */     
/*  941 */     private TitlePaneLayout(LiquidTitlePane this$0) { this.this$0 = this$0; }
/*      */ 
/*      */     
/*      */     public void addLayoutComponent(String name, Component c) {}
/*      */     
/*      */     public void removeLayoutComponent(Component c) {}
/*      */     
/*      */     public Dimension preferredLayoutSize(Container c) {
/*  949 */       int height = computeHeight();
/*      */       
/*  951 */       return new Dimension(height, height);
/*      */     }
/*      */ 
/*      */     
/*  955 */     public Dimension minimumLayoutSize(Container c) { return preferredLayoutSize(c); }
/*      */ 
/*      */     
/*      */     private int computeHeight() {
/*  959 */       if (this.this$0.getFrame() instanceof javax.swing.JFrame) {
/*  960 */         this.this$0.setMaximizeBounds(this.this$0.getFrame());
/*      */         
/*  962 */         return 25;
/*      */       } 
/*  964 */       return 25;
/*      */     }
/*      */     
/*      */     public void layoutContainer(Container c) {
/*      */       int buttonWidth, buttonHeight;
/*  969 */       if (this.this$0.getWindowDecorationStyle() == 0) {
/*  970 */         this.this$0.buttonsWidth = 0;
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/*  975 */       boolean leftToRight = (this.this$0.window == null) ? this.this$0.getRootPane().getComponentOrientation().isLeftToRight() : this.this$0.window.getComponentOrientation().isLeftToRight();
/*      */ 
/*      */ 
/*      */       
/*  979 */       int w = this.this$0.getWidth();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  985 */       if (this.this$0.closeButton != null) {
/*  986 */         buttonHeight = (this.this$0.closeButton.getPreferredSize()).height;
/*  987 */         buttonWidth = (this.this$0.closeButton.getPreferredSize()).width;
/*      */       } else {
/*  989 */         buttonHeight = 16;
/*  990 */         buttonWidth = 16;
/*      */       } 
/*      */       
/*  993 */       int y = (this.this$0.getHeight() - buttonHeight) / 2 + 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1000 */       int x = leftToRight ? w : 0;
/*      */       
/* 1002 */       int spacing = 0;
/* 1003 */       x = leftToRight ? spacing : (w - buttonWidth - spacing);
/*      */       
/* 1005 */       if (this.this$0.menuBar != null)
/*      */       {
/* 1007 */         this.this$0.menuBar.setBounds(x, y, buttonWidth, buttonHeight);
/*      */       }
/*      */       
/* 1010 */       x = leftToRight ? w : 0;
/* 1011 */       x += (leftToRight ? (-spacing - buttonWidth) : spacing);
/*      */       
/* 1013 */       if (this.this$0.closeButton != null) {
/* 1014 */         this.this$0.closeButton.setBounds(x, y, buttonWidth, buttonHeight);
/*      */       }
/*      */       
/* 1017 */       if (!leftToRight) {
/* 1018 */         x += buttonWidth;
/*      */       }
/*      */       
/* 1021 */       if (Toolkit.getDefaultToolkit().isFrameStateSupported(6) && 
/* 1022 */         this.this$0.toggleButton.getParent() != null) {
/* 1023 */         x += (leftToRight ? (-spacing - buttonWidth) : spacing);
/* 1024 */         this.this$0.toggleButton.setBounds(x, y, buttonWidth, buttonHeight);
/*      */         
/* 1026 */         if (!leftToRight) {
/* 1027 */           x += buttonWidth;
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 1032 */       if (this.this$0.iconifyButton != null && this.this$0.iconifyButton.getParent() != null) {
/* 1033 */         x += (leftToRight ? (-spacing - buttonWidth) : spacing);
/* 1034 */         this.this$0.iconifyButton.setBounds(x, y, buttonWidth, buttonHeight);
/*      */         
/* 1036 */         if (!leftToRight) {
/* 1037 */           x += buttonWidth;
/*      */         }
/*      */       } 
/*      */       
/* 1041 */       this.this$0.buttonsWidth = leftToRight ? (w - x) : x;
/*      */     }
/*      */   }
/*      */   
/*      */   private class PropertyChangeHandler
/*      */     implements PropertyChangeListener {
/*      */     private final LiquidTitlePane this$0;
/*      */     
/* 1049 */     private PropertyChangeHandler(LiquidTitlePane this$0) { this.this$0 = this$0; }
/*      */     public void propertyChange(PropertyChangeEvent pce) {
/* 1051 */       String name = pce.getPropertyName();
/*      */ 
/*      */       
/* 1054 */       if ("resizable".equals(name) || "state".equals(name)) {
/* 1055 */         Frame frame = this.this$0.getFrame();
/*      */         
/* 1057 */         if (frame != null) {
/* 1058 */           this.this$0.setState(frame.getExtendedState(), true);
/*      */         }
/*      */         
/* 1061 */         if ("resizable".equals(name)) {
/* 1062 */           this.this$0.getRootPane().repaint();
/*      */         }
/* 1064 */       } else if ("title".equals(name)) {
/* 1065 */         this.this$0.repaint();
/* 1066 */       } else if ("componentOrientation".equals(name)) {
/* 1067 */         this.this$0.revalidate();
/* 1068 */         this.this$0.repaint();
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   private class WindowHandler extends WindowAdapter {
/*      */     private final LiquidTitlePane this$0;
/*      */     
/* 1076 */     private WindowHandler(LiquidTitlePane this$0) { this.this$0 = this$0; }
/*      */     
/* 1078 */     public void windowActivated(WindowEvent ev) { this.this$0.setActive(true); }
/*      */ 
/*      */ 
/*      */     
/* 1082 */     public void windowDeactivated(WindowEvent ev) { this.this$0.setActive(false); } }
/*      */   
/*      */   class WindowMoveListener extends ComponentAdapter { private final LiquidTitlePane this$0;
/*      */     
/* 1086 */     WindowMoveListener(LiquidTitlePane this$0) { this.this$0 = this$0; }
/*      */ 
/*      */ 
/*      */     
/*      */     public void componentMoved(ComponentEvent e) {
/* 1091 */       if (this.this$0.getWindowDecorationStyle() == 0) {
/*      */         return;
/*      */       }
/*      */       
/* 1095 */       Window w = this.this$0.getWindow();
/* 1096 */       w.repaint(0, 0, w.getWidth(), 5);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void componentResized(ComponentEvent e) {
/* 1103 */       if (this.this$0.getWindowDecorationStyle() == 0) {
/*      */         return;
/*      */       }
/*      */       
/* 1107 */       Window w = this.this$0.getWindow();
/* 1108 */       w.repaint(0, 0, w.getWidth(), 5);
/*      */     } }
/*      */ 
/*      */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\LiquidTitlePane.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */