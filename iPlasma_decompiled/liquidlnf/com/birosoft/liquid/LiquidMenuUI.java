/*     */ package com.birosoft.liquid;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Point;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import javax.swing.AbstractAction;
/*     */ import javax.swing.ActionMap;
/*     */ import javax.swing.InputMap;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JPopupMenu;
/*     */ import javax.swing.KeyStroke;
/*     */ import javax.swing.MenuElement;
/*     */ import javax.swing.MenuSelectionManager;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.Timer;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.event.ChangeEvent;
/*     */ import javax.swing.event.ChangeListener;
/*     */ import javax.swing.event.MenuDragMouseEvent;
/*     */ import javax.swing.event.MenuDragMouseListener;
/*     */ import javax.swing.event.MenuEvent;
/*     */ import javax.swing.event.MenuKeyEvent;
/*     */ import javax.swing.event.MenuKeyListener;
/*     */ import javax.swing.event.MenuListener;
/*     */ import javax.swing.event.MouseInputListener;
/*     */ import javax.swing.plaf.ComponentUI;
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
/*     */ public class LiquidMenuUI
/*     */   extends LiquidMenuItemUI
/*     */ {
/*     */   protected ChangeListener changeListener;
/*     */   protected PropertyChangeListener propertyChangeListener;
/*     */   protected MenuListener menuListener;
/*     */   private static MenuListener sharedMenuListener;
/*  66 */   private int lastMnemonic = 0;
/*     */ 
/*     */   
/*     */   private InputMap selectedWindowInputMap;
/*     */   
/*     */   private static final boolean TRACE = false;
/*     */   
/*     */   private static final boolean VERBOSE = false;
/*     */   
/*     */   private static final boolean DEBUG = false;
/*     */   
/*     */   private static boolean crossMenuMnemonic = true;
/*     */ 
/*     */   
/*  80 */   public static ComponentUI createUI(JComponent x) { return new LiquidMenuUI(); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void installUI(JComponent c) {
/*  85 */     super.installUI(c);
/*  86 */     JMenu m = (JMenu)c;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void installDefaults() {
/*  92 */     super.installDefaults();
/*  93 */     ((JMenu)this.menuItem).setDelay(200);
/*  94 */     crossMenuMnemonic = UIManager.getBoolean("Menu.crossMenuMnemonic");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  99 */   protected String getPropertyPrefix() { return "Menu"; }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void installListeners() {
/* 104 */     super.installListeners();
/*     */     
/* 106 */     if (this.changeListener == null) {
/* 107 */       this.changeListener = createChangeListener(this.menuItem);
/*     */     }
/* 109 */     if (this.changeListener != null) {
/* 110 */       this.menuItem.addChangeListener(this.changeListener);
/*     */     }
/* 112 */     if (this.propertyChangeListener == null) {
/* 113 */       this.propertyChangeListener = createPropertyChangeListener(this.menuItem);
/*     */     }
/* 115 */     if (this.propertyChangeListener != null) {
/* 116 */       this.menuItem.addPropertyChangeListener(this.propertyChangeListener);
/*     */     }
/* 118 */     if (this.menuListener == null) {
/* 119 */       this.menuListener = createMenuListener(this.menuItem);
/*     */     }
/* 121 */     if (this.menuListener != null) {
/* 122 */       ((JMenu)this.menuItem).addMenuListener(this.menuListener);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void installKeyboardActions() {
/* 127 */     super.installKeyboardActions();
/* 128 */     updateMnemonicBinding();
/*     */   }
/*     */ 
/*     */   
/*     */   void updateMnemonicBinding() {
/* 133 */     int mnemonic = this.menuItem.getModel().getMnemonic();
/* 134 */     int[] shortcutKeys = (int[])UIManager.get("Menu.shortcutKeys");
/* 135 */     if (mnemonic == this.lastMnemonic || shortcutKeys == null) {
/*     */       return;
/*     */     }
/*     */     
/* 139 */     if (this.lastMnemonic != 0 && this.windowInputMap != null)
/*     */     {
/* 141 */       for (int i = 0; i < shortcutKeys.length; i++)
/*     */       {
/* 143 */         this.windowInputMap.remove(KeyStroke.getKeyStroke(this.lastMnemonic, shortcutKeys[i], false));
/*     */       }
/*     */     }
/* 146 */     if (mnemonic != 0) {
/*     */       
/* 148 */       if (this.windowInputMap == null) {
/*     */         
/* 150 */         this.windowInputMap = createInputMap(2);
/* 151 */         SwingUtilities.replaceUIInputMap(this.menuItem, 2, this.windowInputMap);
/*     */       } 
/* 153 */       for (int i = 0; i < shortcutKeys.length; i++)
/*     */       {
/* 155 */         this.windowInputMap.put(KeyStroke.getKeyStroke(mnemonic, shortcutKeys[i], false), "selectMenu");
/*     */       }
/*     */     } 
/* 158 */     this.lastMnemonic = mnemonic;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 163 */   protected void uninstallKeyboardActions() { super.uninstallKeyboardActions(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 172 */   ActionMap getActionMap() { return createActionMap(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ActionMap createActionMap() {
/* 180 */     ActionMap am = super.createActionMap();
/* 181 */     if (am != null)
/*     */     {
/* 183 */       am.put("selectMenu", new PostAction((JMenu)this.menuItem, true));
/*     */     }
/* 185 */     return am;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 190 */   protected MouseInputListener createMouseInputListener(JComponent c) { return new MouseInputHandler(this); }
/*     */ 
/*     */ 
/*     */   
/*     */   protected MenuListener createMenuListener(JComponent c) {
/* 195 */     if (sharedMenuListener == null)
/*     */     {
/* 197 */       sharedMenuListener = new MenuHandler(null);
/*     */     }
/* 199 */     return sharedMenuListener;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 204 */   protected ChangeListener createChangeListener(JComponent c) { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 209 */   protected PropertyChangeListener createPropertyChangeListener(JComponent c) { return new PropertyChangeHandler(this, null); }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void uninstallDefaults() {
/* 214 */     this.menuItem.setArmed(false);
/* 215 */     this.menuItem.setSelected(false);
/* 216 */     this.menuItem.resetKeyboardActions();
/* 217 */     super.uninstallDefaults();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void uninstallListeners() {
/* 222 */     super.uninstallListeners();
/*     */     
/* 224 */     if (this.changeListener != null) {
/* 225 */       this.menuItem.removeChangeListener(this.changeListener);
/*     */     }
/* 227 */     if (this.propertyChangeListener != null) {
/* 228 */       this.menuItem.removePropertyChangeListener(this.propertyChangeListener);
/*     */     }
/* 230 */     if (this.menuListener != null) {
/* 231 */       ((JMenu)this.menuItem).removeMenuListener(this.menuListener);
/*     */     }
/* 233 */     this.changeListener = null;
/* 234 */     this.propertyChangeListener = null;
/* 235 */     this.menuListener = null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 240 */   protected MenuDragMouseListener createMenuDragMouseListener(JComponent c) { return new MenuDragMouseHandler(this, null); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 245 */   protected MenuKeyListener createMenuKeyListener(JComponent c) { return new MenuKeyHandler(this, null); }
/*     */ 
/*     */ 
/*     */   
/*     */   public Dimension getMaximumSize(JComponent c) {
/* 250 */     if (((JMenu)this.menuItem).isTopLevelMenu() == true) {
/*     */       
/* 252 */       Dimension d = c.getPreferredSize();
/* 253 */       return new Dimension(d.width, 32767);
/*     */     } 
/* 255 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setupPostTimer(JMenu menu) {
/* 260 */     Timer timer = new Timer(menu.getDelay(), new PostAction(menu, false));
/* 261 */     timer.setRepeats(false);
/* 262 */     timer.start();
/*     */   }
/*     */ 
/*     */   
/*     */   private static void appendPath(MenuElement[] path, MenuElement elem) {
/* 267 */     MenuElement[] newPath = new MenuElement[path.length + 1];
/* 268 */     System.arraycopy(path, 0, newPath, 0, path.length);
/* 269 */     newPath[path.length] = elem;
/* 270 */     MenuSelectionManager.defaultManager().setSelectedPath(newPath);
/*     */   }
/*     */   
/*     */   private static class PostAction extends AbstractAction { JMenu menu;
/*     */     
/*     */     PostAction(JMenu menu, boolean shouldForce) {
/* 276 */       this.force = false;
/*     */ 
/*     */ 
/*     */       
/* 280 */       this.menu = menu;
/* 281 */       this.force = shouldForce;
/*     */     }
/*     */     boolean force;
/*     */     
/*     */     public void actionPerformed(ActionEvent e) {
/* 286 */       if (!crossMenuMnemonic) {
/*     */         
/* 288 */         JPopupMenu pm = LiquidMenuUI.getActivePopupMenu();
/* 289 */         if (pm != null && pm != this.menu.getParent()) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 295 */       MenuSelectionManager defaultManager = MenuSelectionManager.defaultManager();
/* 296 */       if (this.force) {
/*     */         
/* 298 */         Container cnt = this.menu.getParent();
/* 299 */         if (cnt != null && cnt instanceof javax.swing.JMenuBar) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 304 */           MenuElement[] me, subElements = this.menu.getPopupMenu().getSubElements();
/* 305 */           if (subElements.length > 0) {
/*     */             
/* 307 */             me = new MenuElement[4];
/* 308 */             me[0] = (MenuElement)cnt;
/* 309 */             me[1] = this.menu;
/* 310 */             me[2] = this.menu.getPopupMenu();
/* 311 */             me[3] = subElements[0];
/*     */           } else {
/*     */             
/* 314 */             me = new MenuElement[3];
/* 315 */             me[0] = (MenuElement)cnt;
/* 316 */             me[1] = this.menu;
/* 317 */             me[2] = this.menu.getPopupMenu();
/*     */           } 
/* 319 */           defaultManager.setSelectedPath(me);
/*     */         } 
/*     */       } else {
/*     */         
/* 323 */         MenuElement[] path = defaultManager.getSelectedPath();
/* 324 */         if (path.length > 0 && path[path.length - true] == this.menu)
/*     */         {
/* 326 */           LiquidMenuUI.appendPath(path, this.menu.getPopupMenu());
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 333 */     public boolean isEnabled() { return this.menu.getModel().isEnabled(); } }
/*     */   
/*     */   private class PropertyChangeHandler implements PropertyChangeListener { private final LiquidMenuUI this$0;
/*     */     
/* 337 */     private PropertyChangeHandler(LiquidMenuUI this$0) { this.this$0 = this$0; }
/*     */ 
/*     */     
/*     */     public void propertyChange(PropertyChangeEvent e) {
/* 341 */       String prop = e.getPropertyName();
/* 342 */       if (prop.equals("mnemonic"))
/*     */       {
/* 344 */         this.this$0.updateMnemonicBinding();
/*     */       }
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class MouseInputHandler
/*     */     implements MouseInputListener
/*     */   {
/*     */     private final LiquidMenuUI this$0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 362 */     protected MouseInputHandler(LiquidMenuUI this$0) { this.this$0 = this$0; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void mouseClicked(MouseEvent e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void mousePressed(MouseEvent e) {
/* 377 */       JMenu menu = (JMenu)this.this$0.menuItem;
/* 378 */       if (!menu.isEnabled()) {
/*     */         return;
/*     */       }
/* 381 */       MenuSelectionManager manager = MenuSelectionManager.defaultManager();
/* 382 */       if (menu.isTopLevelMenu())
/*     */       {
/* 384 */         if (menu.isSelected()) {
/*     */           
/* 386 */           manager.clearSelectedPath();
/*     */         } else {
/*     */           
/* 389 */           Container cnt = menu.getParent();
/* 390 */           if (cnt != null && cnt instanceof javax.swing.JMenuBar) {
/*     */             
/* 392 */             MenuElement[] me = new MenuElement[2];
/* 393 */             me[0] = (MenuElement)cnt;
/* 394 */             me[1] = menu;
/* 395 */             manager.setSelectedPath(me);
/*     */           } 
/*     */         } 
/*     */       }
/*     */       
/* 400 */       MenuElement[] selectedPath = manager.getSelectedPath();
/* 401 */       if (selectedPath.length > 0 && selectedPath[selectedPath.length - true] != menu.getPopupMenu())
/*     */       {
/*     */         
/* 404 */         if (menu.isTopLevelMenu() || menu.getDelay() == 0) {
/*     */           
/* 406 */           LiquidMenuUI.appendPath(selectedPath, menu.getPopupMenu());
/*     */         } else {
/*     */           
/* 409 */           this.this$0.setupPostTimer(menu);
/*     */         } 
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void mouseReleased(MouseEvent e) {
/* 422 */       JMenu menu = (JMenu)this.this$0.menuItem;
/* 423 */       if (!menu.isEnabled())
/*     */         return; 
/* 425 */       MenuSelectionManager manager = MenuSelectionManager.defaultManager();
/* 426 */       manager.processMouseEvent(e);
/* 427 */       if (!e.isConsumed()) {
/* 428 */         manager.clearSelectedPath();
/*     */       }
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
/*     */     public void mouseEntered(MouseEvent e) {
/* 441 */       JMenu menu = (JMenu)this.this$0.menuItem;
/* 442 */       if (!menu.isEnabled()) {
/*     */         return;
/*     */       }
/* 445 */       menu.putClientProperty("rollover", Boolean.TRUE);
/* 446 */       menu.repaint();
/* 447 */       MenuSelectionManager manager = MenuSelectionManager.defaultManager();
/* 448 */       MenuElement[] selectedPath = manager.getSelectedPath();
/* 449 */       if (!menu.isTopLevelMenu()) {
/*     */         
/* 451 */         if (selectedPath.length <= 0 || selectedPath[selectedPath.length - true] != menu.getPopupMenu())
/*     */         {
/* 453 */           if (menu.getDelay() == 0) {
/*     */             
/* 455 */             LiquidMenuUI.appendPath(this.this$0.getPath(), menu.getPopupMenu());
/*     */           } else {
/*     */             
/* 458 */             manager.setSelectedPath(this.this$0.getPath());
/* 459 */             this.this$0.setupPostTimer(menu);
/*     */           }
/*     */         
/*     */         }
/*     */       }
/* 464 */       else if (selectedPath.length > 0 && selectedPath[false] == menu.getParent()) {
/*     */         
/* 466 */         MenuElement[] newPath = new MenuElement[3];
/*     */ 
/*     */         
/* 469 */         newPath[0] = (MenuElement)menu.getParent();
/* 470 */         newPath[1] = menu;
/* 471 */         newPath[2] = menu.getPopupMenu();
/* 472 */         manager.setSelectedPath(newPath);
/*     */       } 
/*     */       
/* 475 */       menu.repaint();
/*     */     }
/*     */ 
/*     */     
/*     */     public void mouseExited(MouseEvent e) {
/* 480 */       JMenu menu = (JMenu)this.this$0.menuItem;
/* 481 */       menu.putClientProperty("rollover", Boolean.FALSE);
/* 482 */       menu.repaint();
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
/*     */     public void mouseDragged(MouseEvent e) {
/* 494 */       JMenu menu = (JMenu)this.this$0.menuItem;
/* 495 */       if (!menu.isEnabled())
/*     */         return; 
/* 497 */       MenuSelectionManager.defaultManager().processMouseEvent(e);
/*     */     }
/*     */ 
/*     */     
/*     */     public void mouseMoved(MouseEvent e) {}
/*     */   }
/*     */   
/*     */   private static class MenuHandler
/*     */     implements MenuListener
/*     */   {
/*     */     private MenuHandler() {}
/*     */     
/*     */     public void menuSelected(MenuEvent e) {}
/*     */     
/*     */     public void menuDeselected(MenuEvent e) {}
/*     */     
/*     */     public void menuCanceled(MenuEvent e) {
/* 514 */       JMenu m = (JMenu)e.getSource();
/* 515 */       MenuSelectionManager manager = MenuSelectionManager.defaultManager();
/* 516 */       if (manager.isComponentPartOfCurrentMenu(m)) {
/* 517 */         MenuSelectionManager.defaultManager().clearSelectedPath();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public class ChangeHandler
/*     */     implements ChangeListener
/*     */   {
/*     */     public JMenu menu;
/*     */     
/*     */     public LiquidMenuUI ui;
/*     */     public boolean isSelected;
/*     */     public Component wasFocused;
/*     */     private final LiquidMenuUI this$0;
/*     */     
/*     */     public ChangeHandler(LiquidMenuUI this$0, JMenu m, LiquidMenuUI ui) {
/* 534 */       this.this$0 = this$0; this.isSelected = false;
/* 535 */       this.menu = m;
/* 536 */       this.ui = ui;
/*     */     }
/*     */     
/*     */     public void stateChanged(ChangeEvent e) {} }
/*     */   
/*     */   private class MenuDragMouseHandler implements MenuDragMouseListener {
/*     */     private final LiquidMenuUI this$0;
/*     */     
/* 544 */     private MenuDragMouseHandler(LiquidMenuUI this$0) { this.this$0 = this$0; }
/*     */ 
/*     */     
/*     */     public void menuDragMouseEntered(MenuDragMouseEvent e) {}
/*     */ 
/*     */     
/*     */     public void menuDragMouseDragged(MenuDragMouseEvent e) {
/* 551 */       if (!this.this$0.menuItem.isEnabled()) {
/*     */         return;
/*     */       }
/* 554 */       MenuSelectionManager manager = e.getMenuSelectionManager();
/* 555 */       MenuElement[] path = e.getPath();
/*     */       
/* 557 */       Point p = e.getPoint();
/* 558 */       if (p.x >= 0 && p.x < this.this$0.menuItem.getWidth() && p.y >= 0 && p.y < this.this$0.menuItem.getHeight()) {
/*     */         
/* 560 */         JMenu menu = (JMenu)this.this$0.menuItem;
/* 561 */         MenuElement[] selectedPath = manager.getSelectedPath();
/* 562 */         if (selectedPath.length <= 0 || selectedPath[selectedPath.length - true] != menu.getPopupMenu())
/*     */         {
/* 564 */           if (menu.isTopLevelMenu() || menu.getDelay() == 0 || e.getID() == 506) {
/*     */             
/* 566 */             LiquidMenuUI.appendPath(path, menu.getPopupMenu());
/*     */           } else {
/*     */             
/* 569 */             manager.setSelectedPath(path);
/* 570 */             this.this$0.setupPostTimer(menu);
/*     */           } 
/*     */         }
/* 573 */       } else if (e.getID() == 502) {
/*     */         
/* 575 */         Component comp = manager.componentForPoint(e.getComponent(), e.getPoint());
/* 576 */         if (comp == null) {
/* 577 */           manager.clearSelectedPath();
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void menuDragMouseExited(MenuDragMouseEvent e) {}
/*     */ 
/*     */     
/*     */     public void menuDragMouseReleased(MenuDragMouseEvent e) {}
/*     */   }
/*     */ 
/*     */   
/*     */   static JPopupMenu getActivePopupMenu() {
/* 591 */     path = MenuSelectionManager.defaultManager().getSelectedPath();
/* 592 */     for (int i = path.length - 1; i >= 0; i--) {
/*     */       
/* 594 */       MenuElement elem = path[i];
/* 595 */       if (elem instanceof JPopupMenu)
/*     */       {
/* 597 */         return (JPopupMenu)elem;
/*     */       }
/*     */     } 
/* 600 */     return null;
/*     */   }
/*     */   
/*     */   private class MenuKeyHandler implements MenuKeyListener { private int[] indexes;
/*     */     private char lastMnemonic;
/*     */     
/* 606 */     private MenuKeyHandler(LiquidMenuUI this$0) { this.this$0 = this$0; }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private int lastIndex;
/*     */ 
/*     */ 
/*     */     
/*     */     private int matches;
/*     */ 
/*     */ 
/*     */     
/*     */     private final LiquidMenuUI this$0;
/*     */ 
/*     */ 
/*     */     
/*     */     public void menuKeyTyped(MenuKeyEvent e) {
/* 624 */       if (!crossMenuMnemonic) {
/*     */         
/* 626 */         JPopupMenu pm = LiquidMenuUI.getActivePopupMenu();
/* 627 */         if (pm != null && pm != this.this$0.menuItem.getParent()) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 633 */       int key = this.this$0.menuItem.getMnemonic();
/* 634 */       if (key == 0)
/*     */         return; 
/* 636 */       MenuElement[] path = e.getPath();
/* 637 */       if (lower((char)key) == lower(e.getKeyChar())) {
/*     */         
/* 639 */         JPopupMenu popupMenu = ((JMenu)this.this$0.menuItem).getPopupMenu();
/* 640 */         MenuElement[] sub = popupMenu.getSubElements();
/* 641 */         if (sub.length > 0) {
/*     */           
/* 643 */           MenuSelectionManager manager = e.getMenuSelectionManager();
/* 644 */           MenuElement[] newPath = new MenuElement[path.length + 2];
/* 645 */           System.arraycopy(path, 0, newPath, 0, path.length);
/* 646 */           newPath[path.length] = popupMenu;
/* 647 */           newPath[path.length + 1] = sub[0];
/* 648 */           manager.setSelectedPath(newPath);
/*     */         } 
/* 650 */         e.consume();
/*     */       } 
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
/*     */     public void menuKeyPressed(MenuKeyEvent e) {
/* 665 */       char keyChar = e.getKeyChar();
/* 666 */       if (!Character.isLetterOrDigit(keyChar)) {
/*     */         return;
/*     */       }
/* 669 */       MenuSelectionManager manager = e.getMenuSelectionManager();
/* 670 */       MenuElement[] path = e.getPath();
/* 671 */       MenuElement[] selectedPath = manager.getSelectedPath();
/*     */       
/* 673 */       for (int i = selectedPath.length - 1; i >= 0; i--) {
/*     */         
/* 675 */         if (selectedPath[i] == this.this$0.menuItem) {
/*     */           
/* 677 */           JPopupMenu popupMenu = ((JMenu)this.this$0.menuItem).getPopupMenu();
/* 678 */           MenuElement[] items = popupMenu.getSubElements();
/*     */           
/* 680 */           if (this.indexes == null || this.lastMnemonic != keyChar) {
/*     */             
/* 682 */             this.matches = 0;
/* 683 */             this.lastIndex = 0;
/* 684 */             this.indexes = new int[items.length];
/* 685 */             for (int j = 0; j < items.length; j++) {
/*     */               
/* 687 */               int key = ((JMenuItem)items[j]).getMnemonic();
/* 688 */               if (lower((char)key) == lower(keyChar))
/*     */               {
/* 690 */                 this.indexes[this.matches++] = j;
/*     */               }
/*     */             } 
/* 693 */             this.lastMnemonic = keyChar;
/*     */           } 
/* 695 */           if (this.matches != 0)
/*     */           {
/*     */             
/* 698 */             if (this.matches == 1) {
/*     */ 
/*     */               
/* 701 */               JMenuItem item = (JMenuItem)items[this.indexes[0]];
/* 702 */               if (!(item instanceof JMenu))
/*     */               {
/*     */                 
/* 705 */                 manager.clearSelectedPath();
/* 706 */                 item.doClick();
/*     */               
/*     */               }
/*     */             
/*     */             }
/*     */             else {
/*     */               
/* 713 */               if (this.lastIndex == this.matches)
/*     */               {
/*     */ 
/*     */                 
/* 717 */                 this.lastIndex = 0;
/*     */               }
/* 719 */               MenuElement menuItem = items[this.indexes[this.lastIndex++]];
/*     */               
/* 721 */               MenuElement[] newPath = new MenuElement[path.length + 2];
/* 722 */               System.arraycopy(path, 0, newPath, 0, path.length);
/* 723 */               newPath[path.length] = popupMenu;
/* 724 */               newPath[path.length + 1] = menuItem;
/* 725 */               manager.setSelectedPath(newPath);
/*     */             }  } 
/* 727 */           e.consume();
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void menuKeyReleased(MenuKeyEvent e) {}
/*     */ 
/*     */ 
/*     */     
/* 739 */     private char lower(char keyChar) { return Character.toLowerCase(keyChar); } }
/*     */ 
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\LiquidMenuUI.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */