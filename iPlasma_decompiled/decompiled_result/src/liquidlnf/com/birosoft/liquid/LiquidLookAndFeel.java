/*     */ package com.birosoft.liquid;
/*     */ 
/*     */ import com.birosoft.liquid.borders.LiquidFocusCellHighlightBorder;
/*     */ import com.birosoft.liquid.borders.LiquidFrameBorder;
/*     */ import com.birosoft.liquid.borders.LiquidListBorder;
/*     */ import com.birosoft.liquid.borders.LiquidPopupMenuBorder;
/*     */ import com.birosoft.liquid.borders.LiquidTextFieldBorder;
/*     */ import com.birosoft.liquid.skin.SkinImageCache;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Image;
/*     */ import java.awt.Insets;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.UIDefaults;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.border.CompoundBorder;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ import javax.swing.border.LineBorder;
/*     */ import javax.swing.plaf.ColorUIResource;
/*     */ import javax.swing.plaf.InsetsUIResource;
/*     */ import javax.swing.plaf.basic.BasicBorders;
/*     */ import javax.swing.plaf.basic.BasicLookAndFeel;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LiquidLookAndFeel
/*     */   extends BasicLookAndFeel
/*     */ {
/*     */   protected static UIDefaults uiDefaults;
/*     */   protected static boolean defaultRowBackgroundMode = true;
/*     */   private static boolean isInstalled = false;
/*     */   Border focusCellHighlightBorder;
/*     */   Border listBorder;
/*     */   Border zeroEmptyBorder;
/*     */   Object fieldInputMap;
/*     */   Object multilineInputMap;
/*     */   Object formattedInputMap;
/*     */   private boolean windowslfforfilechooser;
/*     */   
/*     */   public LiquidLookAndFeel() {
/*  88 */     this.focusCellHighlightBorder = new LiquidFocusCellHighlightBorder(new Color(86, 46, 0));
/*     */     
/*  90 */     this.listBorder = new LiquidListBorder();
/*  91 */     this.zeroEmptyBorder = new EmptyBorder(0, 0, 0, 0);
/*  92 */     this.fieldInputMap = new UIDefaults.LazyInputMap(new Object[] { "ctrl C", "copy-to-clipboard", "ctrl V", "paste-from-clipboard", "ctrl X", "cut-to-clipboard", "COPY", "copy-to-clipboard", "PASTE", "paste-from-clipboard", "CUT", "cut-to-clipboard", "shift LEFT", "selection-backward", "shift KP_LEFT", "selection-backward", "shift RIGHT", "selection-forward", "shift KP_RIGHT", "selection-forward", "ctrl LEFT", "caret-previous-word", "ctrl KP_LEFT", "caret-previous-word", "ctrl RIGHT", "caret-next-word", "ctrl KP_RIGHT", "caret-next-word", "ctrl shift LEFT", "selection-previous-word", "ctrl shift KP_LEFT", "selection-previous-word", "ctrl shift RIGHT", "selection-next-word", "ctrl shift KP_RIGHT", "selection-next-word", "ctrl A", "select-all", "HOME", "caret-begin-line", "END", "caret-end-line", "shift HOME", "selection-begin-line", "shift END", "selection-end-line", "typed \b", "delete-previous", "DELETE", "delete-next", "RIGHT", "caret-forward", "LEFT", "caret-backward", "KP_RIGHT", "caret-forward", "KP_LEFT", "caret-backward", "ENTER", "notify-field-accept", "ctrl BACK_SLASH", "unselect", "control shift O", "toggle-componentOrientation" });
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
/* 127 */     this.multilineInputMap = new UIDefaults.LazyInputMap(new Object[] { "ctrl C", "copy-to-clipboard", "ctrl V", "paste-from-clipboard", "ctrl X", "cut-to-clipboard", "COPY", "copy-to-clipboard", "PASTE", "paste-from-clipboard", "CUT", "cut-to-clipboard", "shift LEFT", "selection-backward", "shift KP_LEFT", "selection-backward", "shift RIGHT", "selection-forward", "shift KP_RIGHT", "selection-forward", "ctrl LEFT", "caret-previous-word", "ctrl KP_LEFT", "caret-previous-word", "ctrl RIGHT", "caret-next-word", "ctrl KP_RIGHT", "caret-next-word", "ctrl shift LEFT", "selection-previous-word", "ctrl shift KP_LEFT", "selection-previous-word", "ctrl shift RIGHT", "selection-next-word", "ctrl shift KP_RIGHT", "selection-next-word", "ctrl A", "select-all", "HOME", "caret-begin-line", "END", "caret-end-line", "shift HOME", "selection-begin-line", "shift END", "selection-end-line", "UP", "caret-up", "KP_UP", "caret-up", "DOWN", "caret-down", "KP_DOWN", "caret-down", "PAGE_UP", "page-up", "PAGE_DOWN", "page-down", "shift PAGE_UP", "selection-page-up", "shift PAGE_DOWN", "selection-page-down", "ctrl shift PAGE_UP", "selection-page-left", "ctrl shift PAGE_DOWN", "selection-page-right", "shift UP", "selection-up", "shift KP_UP", "selection-up", "shift DOWN", "selection-down", "shift KP_DOWN", "selection-down", "ENTER", "insert-break", "typed \b", "delete-previous", "DELETE", "delete-next", "RIGHT", "caret-forward", "LEFT", "caret-backward", "KP_RIGHT", "caret-forward", "KP_LEFT", "caret-backward", "TAB", "insert-tab", "ctrl BACK_SLASH", "unselect", "ctrl HOME", "caret-begin", "ctrl END", "caret-end", "ctrl shift HOME", "selection-begin", "ctrl shift END", "selection-end", "ctrl T", "next-link-action", "ctrl shift T", "previous-link-action", "ctrl SPACE", "activate-link-action", "control shift O", "toggle-componentOrientation" });
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
/* 181 */     this.formattedInputMap = new UIDefaults.LazyInputMap(new Object[] { "ctrl C", "copy-to-clipboard", "ctrl V", "paste-from-clipboard", "ctrl X", "cut-to-clipboard", "COPY", "copy-to-clipboard", "PASTE", "paste-from-clipboard", "CUT", "cut-to-clipboard", "shift LEFT", "selection-backward", "shift KP_LEFT", "selection-backward", "shift RIGHT", "selection-forward", "shift KP_RIGHT", "selection-forward", "ctrl LEFT", "caret-previous-word", "ctrl KP_LEFT", "caret-previous-word", "ctrl RIGHT", "caret-next-word", "ctrl KP_RIGHT", "caret-next-word", "ctrl shift LEFT", "selection-previous-word", "ctrl shift KP_LEFT", "selection-previous-word", "ctrl shift RIGHT", "selection-next-word", "ctrl shift KP_RIGHT", "selection-next-word", "ctrl A", "select-all", "HOME", "caret-begin-line", "END", "caret-end-line", "shift HOME", "selection-begin-line", "shift END", "selection-end-line", "typed \b", "delete-previous", "DELETE", "delete-next", "RIGHT", "caret-forward", "LEFT", "caret-backward", "KP_RIGHT", "caret-forward", "KP_LEFT", "caret-backward", "ENTER", "notify-field-accept", "ctrl BACK_SLASH", "unselect", "control shift O", "toggle-componentOrientation", "ESCAPE", "reset-field-edit", "UP", "increment", "KP_UP", "increment", "DOWN", "decrement", "KP_DOWN", "decrement" });
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
/* 216 */     this.windowslfforfilechooser = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 223 */     this.windowslfforfilechooser = false;
/*     */     
/* 225 */     if (!isInstalled) {
/* 226 */       isInstalled = true;
/*     */ 
/*     */       
/* 229 */       UIManager.installLookAndFeel(new UIManager.LookAndFeelInfo("LiquidLookAndFeel", "com.birosoft.liquid.LiquidLookAndFeel"));
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
/*     */   
/* 246 */   public String getID() { return "Liquid"; }
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
/* 261 */   public String getName() { return "Liquid"; }
/*     */ 
/*     */ 
/*     */   
/* 265 */   public static ColorUIResource getControl() { return (ColorUIResource)uiDefaults.get("control"); }
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
/* 276 */   public String getDescription() { return "Liquid: Mosfet's KDE 3.x Liquid look and feel for Java."; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 286 */   public boolean isNativeLookAndFeel() { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 296 */   public final boolean isSupportedLookAndFeel() { return true; }
/*     */ 
/*     */ 
/*     */   
/* 300 */   public boolean getSupportsWindowDecorations() { return true; }
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
/*     */   protected void initClassDefaults(UIDefaults table) {
/* 312 */     uiDefaults = table;
/* 313 */     super.initClassDefaults(table);
/*     */     
/* 315 */     table.putDefaults(new Object[] { "ButtonUI", "com.birosoft.liquid.LiquidButtonUI", "CheckBoxUI", "com.birosoft.liquid.LiquidCheckBoxUI", "TextFieldUI", "com.birosoft.liquid.LiquidTextFieldUI", "FormattedTextFieldUI", "com.birosoft.liquid.LiquidTextFieldUI", "PasswordTextFieldUI", "com.birosoft.liquid.LiquidTextFieldUI", "SliderUI", "com.birosoft.liquid.LiquidSliderUI", "SpinnerUI", "com.birosoft.liquid.LiquidSpinnerUI", "ToolBarUI", "com.birosoft.liquid.LiquidToolBarUI", "MenuBarUI", "com.birosoft.liquid.LiquidMenuBarUI", "MenuUI", "com.birosoft.liquid.LiquidMenuUI", "PanelUI", "com.birosoft.liquid.LiquidPanelUI", "MenuItemUI", "com.birosoft.liquid.LiquidMenuItemUI", "CheckBoxMenuItemUI", "com.birosoft.liquid.LiquidCheckBoxMenuItemUI", "RadioButtonMenuItemUI", "com.birosoft.liquid.LiquidRadioButtonMenuItemUI", "TableUI", "com.birosoft.liquid.LiquidTableUI", "TableHeaderUI", "com.birosoft.liquid.LiquidTableHeaderUI", "ScrollBarUI", "com.birosoft.liquid.LiquidScrollBarUI", "TabbedPaneUI", "com.birosoft.liquid.LiquidTabbedPaneUI", "ToggleButtonUI", "com.birosoft.liquid.LiquidToggleButtonUI", "ScrollPaneUI", "com.birosoft.liquid.LiquidScrollPaneUI", "ProgressBarUI", "com.birosoft.liquid.LiquidProgressBarUI", "InternalFrameUI", "com.birosoft.liquid.LiquidInternalFrameUI", "RadioButtonUI", "com.birosoft.liquid.LiquidRadioButtonUI", "ComboBoxUI", "com.birosoft.liquid.LiquidComboBoxUI", "ListUI", "com.birosoft.liquid.LiquidListUI", "SeparatorUI", "com.birosoft.liquid.LiquidSeparatorUI", "PopupMenuSeparatorUI", "com.birosoft.liquid.LiquidPopupMenuSeparatorUI", "SplitPaneUI", "com.birosoft.liquid.LiquidSplitPaneUI", "FileChooserUI", "com.birosoft.liquid.FileChooserBasicUI", "RootPaneUI", "com.birosoft.liquid.LiquidRootPaneUI" });
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
/*     */   protected void initSystemColorDefaults(UIDefaults table) {
/* 356 */     String[] defaultSystemColors = { "desktop", "#EEF6FF", "activeCaption", "#3E91EB", "activeCaptionText", "#FFFFFF", "activeCaptionBorder", "#3E91EB", "inactiveCaption", "#AFD6FF", "inactiveCaptionText", "#000000", "inactiveCaptionBorder", "#AFD6FF", "window", "#F6F5F4", "windowBorder", "#F6F5F4", "windowText", "#000000", "menu", "#F6F5F4", "menuText", "#000000", "text", "#FFFFFF", "textText", "#000000", "textHighlight", "#A9D1FF", "textHighlightText", "#030303", "textInactiveText", "#A7A5A3", "control", "#F6F5F4", "controlText", "#000000", "controlHighlight", "#A9D1FF", "controlLtHighlight", "#A9D1FF", "controlShadow", "#BBBBBB", "controlLightShadow", "#000000", "controlDkShadow", "#000000", "scrollbar", "#000000", "info", "#000000", "infoText", "#000000" };
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
/* 385 */     loadSystemColors(table, defaultSystemColors, false);
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
/*     */   protected void initComponentDefaults(UIDefaults table) {
/* 399 */     super.initComponentDefaults(table);
/*     */ 
/*     */     
/* 402 */     Border border = new EmptyBorder(0, 0, 0, 0);
/*     */ 
/*     */ 
/*     */     
/* 406 */     table.put("Button.margin", new InsetsUIResource(4, 16, 4, 16));
/* 407 */     table.put("Button.border", new BasicBorders.MarginBorder());
/* 408 */     table.put("ToggleButton.margin", new InsetsUIResource(4, 16, 4, 16));
/* 409 */     table.put("ToggleButton.border", new BasicBorders.MarginBorder());
/* 410 */     table.put("ToggleButton.background", table.get("window"));
/* 411 */     table.put("TextField.border", new LiquidTextFieldBorder());
/* 412 */     table.put("PasswordField.border", new LiquidTextFieldBorder());
/* 413 */     table.put("Spinner.border", new LiquidTextFieldBorder(new Insets(2, 2, 2, 2)));
/*     */ 
/*     */ 
/*     */     
/* 417 */     table.put("ToolBar.background", table.get("window"));
/* 418 */     table.put("MenuBar.background", table.get("window"));
/* 419 */     border = new EmptyBorder(2, 2, 2, 2);
/* 420 */     table.put("InternalFrame.border", border);
/* 421 */     table.put("InternalFrame.paletteBorder", border);
/* 422 */     table.put("InternalFrame.optionDialogBorder", new LiquidInternalFrameBorder());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 427 */     border = new EmptyBorder(3, 1, 3, 1);
/* 428 */     table.put("Menu.border", border);
/* 429 */     table.put("MenuItem.border", border);
/* 430 */     table.put("CheckBoxMenuItem.border", border);
/* 431 */     table.put("RadioButtonMenuItem.border", border);
/* 432 */     table.put("CheckBoxMenuItem.checkIcon", loadIcon("menucheck.png", this));
/* 433 */     table.put("CheckBoxMenuItem.checkedIcon", loadIcon("menuchecked.png", this));
/*     */     
/* 435 */     table.put("RadioButtonMenuItem.checkIcon", loadIcon("menuradio.png", this));
/*     */     
/* 437 */     table.put("RadioButtonMenuItem.checkedIcon", loadIcon("menuradio_down.png", this));
/*     */     
/* 439 */     table.put("MenuItem.arrowIcon", loadIcon("menuarrow.png", this));
/* 440 */     table.put("MenuItem.selArrowIcon", loadIcon("menuarrowsel.png", this));
/* 441 */     table.put("Menu.submenuPopupOffsetX", new Integer(-3));
/* 442 */     table.put("Menu.submenuPopupOffsetY", new Integer(4));
/* 443 */     LiquidPopupMenuBorder liquidPopupMenuBorder = new LiquidPopupMenuBorder();
/* 444 */     table.put("PopupMenu.border", liquidPopupMenuBorder);
/*     */ 
/*     */     
/* 447 */     table.put("SplitPane.dividerSize", new Integer(7));
/* 448 */     table.put("InternalFrame.paletteTitleHeight", new Integer(14));
/* 449 */     table.put("InternalFrame.frameTitleHeight", new Integer(24));
/* 450 */     table.put("InternalFrame.normalTitleFont", new Font("Helvetica", 1, 14));
/*     */     
/* 452 */     table.put("Panel.background", table.get("window"));
/*     */     
/* 454 */     table.put("TabbedPane.selectedTabPadInsets", new Insets(1, 1, 1, 1));
/* 455 */     table.put("TabbedPane.tabAreaInsets", new Insets(4, 2, 0, 0));
/* 456 */     table.put("TabbedPane.contentBorderInsets", new Insets(5, 0, 0, 0));
/* 457 */     table.put("TabbedPane.unselected", table.get("shadow"));
/*     */     
/* 459 */     table.put("Checkbox.select", table.get("shadow"));
/* 460 */     table.put("PopupMenu.background", table.get("window"));
/* 461 */     table.put("PopupMenu.foreground", Color.black);
/*     */     
/* 463 */     table.put("TextField.selectionForeground", table.get("textHighlightText"));
/*     */     
/* 465 */     table.put("TextField.selectionBackground", table.get("textHighlight"));
/* 466 */     table.put("TextField.background", table.get("text"));
/* 467 */     table.put("TextField.disabledBackground", table.get("window"));
/*     */     
/* 469 */     table.put("TextField.focusInputMap", this.fieldInputMap);
/* 470 */     table.put("PasswordField.focusInputMap", this.fieldInputMap);
/* 471 */     table.put("TextArea.focusInputMap", this.multilineInputMap);
/* 472 */     table.put("TextPane.focusInputMap", this.multilineInputMap);
/* 473 */     table.put("TextPane.background", table.get("text"));
/* 474 */     table.put("EditorPane.focusInputMap", this.multilineInputMap);
/* 475 */     table.put("FormattedTextField.focusInputMap", this.formattedInputMap);
/*     */     
/* 477 */     table.put("List.background", table.get("text"));
/* 478 */     table.put("List.border", this.zeroEmptyBorder);
/* 479 */     table.put("List.selectionForeground", table.get("textHighlightText"));
/* 480 */     table.put("List.selectionBackground", table.get("textHighlight"));
/* 481 */     table.put("List.focusCellHighlightBorder", this.focusCellHighlightBorder);
/*     */     
/* 483 */     table.put("ScrollPane.border", this.listBorder);
/*     */     
/* 485 */     table.put("ComboBox.border", new EmptyBorder(1, 1, 1, 1));
/* 486 */     table.put("ComboBox.foreground", table.get("textHighlightText"));
/* 487 */     table.put("ComboBox.background", table.get("text"));
/* 488 */     table.put("ComboBox.selectionForeground", table.get("textHighlightText"));
/* 489 */     table.put("ComboBox.selectionBackground", table.get("textHighlight"));
/* 490 */     table.put("ComboBox.ancestorInputMap", new UIDefaults.LazyInputMap(new Object[] { "ESCAPE", "hidePopup", "PAGE_UP", "pageUpPassThrough", "PAGE_DOWN", "pageDownPassThrough", "HOME", "homePassThrough", "END", "endPassThrough", "ENTER", "enterPressed" }));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 499 */     table.put("InternalFrame.paletteCloseIcon", loadIcon("closebutton.png", this));
/*     */     
/* 501 */     table.put("InternalFrame.closeIcon", loadIcon("close.png", this));
/* 502 */     table.put("InternalFrame.maximizeIcon", loadIcon("maximize.png", this));
/* 503 */     table.put("InternalFrame.iconifyIcon", loadIcon("minimize.png", this));
/* 504 */     table.put("InternalFrame.minimizeIcon", loadIcon("restore.png", this));
/* 505 */     table.put("InternalFrame.frameTitleHeight", new Integer(25));
/* 506 */     table.put("InternalFrame.paletteTitleHeight", new Integer(16));
/* 507 */     table.put("InternalFrame.icon", loadIcon("internalframeicon.png", this));
/*     */ 
/*     */     
/* 510 */     table.put("Table.background", table.get("text"));
/* 511 */     table.put("Table.foreground", table.get("controlText"));
/* 512 */     table.put("Table.selectionBackground", table.get("textHighlight"));
/* 513 */     table.put("Table.selectionForeground", table.get("textHighlightText"));
/* 514 */     table.put("Table.focusCellBackground", table.get("textHighlight"));
/* 515 */     table.put("Table.focusCellForeground", table.get("textHighlightText"));
/* 516 */     table.put("Table.focusCellHighlightBorder", this.focusCellHighlightBorder);
/* 517 */     table.put("Table.scrollPaneBorder", this.listBorder);
/*     */     
/* 519 */     table.put("TableHeader.font", new Font("Helvetica", 0, 12));
/* 520 */     table.put("TableHeader.foreground", table.get("textText"));
/* 521 */     table.put("TableHeader.background", table.get("window"));
/* 522 */     table.put("TableHeader.cellBorder", this.zeroEmptyBorder);
/*     */     
/* 524 */     table.put("ToolTip.background", new Color(255, 255, 225));
/* 525 */     table.put("ToolTip.foreground", new Color(0, 0, 0));
/* 526 */     table.put("ToolTip.font", new Font("Helvetica", 0, 12));
/* 527 */     table.put("ToolTip.border", new CompoundBorder(new LineBorder(Color.black, 1), new EmptyBorder(2, 2, 2, 2)));
/*     */ 
/*     */ 
/*     */     
/* 531 */     table.put("Tree.font", new Font("Helvetica", 0, 12));
/* 532 */     table.put("Tree.selectionForeground", table.get("textHighlightText"));
/* 533 */     table.put("Tree.selectionBackground", table.get("textHighlight"));
/* 534 */     table.put("Tree.foreground", table.get("textText"));
/* 535 */     table.put("Tree.background", table.get("text"));
/* 536 */     table.put("Tree.expandedIcon", loadIcon("treeminus.png", this));
/* 537 */     table.put("Tree.collapsedIcon", loadIcon("treeplus.png", this));
/* 538 */     table.put("Tree.openIcon", loadIcon("treefolderopened.png", this));
/* 539 */     table.put("Tree.closedIcon", loadIcon("treefolderclosed.png", this));
/* 540 */     table.put("Tree.leafIcon", loadIcon("treeleaf.png", this));
/* 541 */     table.put("Tree.rowHeight", new Integer(18));
/* 542 */     table.put("Tree.selectionBorderColor", new Color(86, 46, 0));
/*     */     
/* 544 */     table.put("SplitPane.background", table.get("text"));
/* 545 */     table.put("SplitPane.border", this.listBorder);
/* 546 */     table.put("SplitPaneDivider.border", this.zeroEmptyBorder);
/*     */     
/* 548 */     table.put("FileView.directoryIcon", loadIcon("treefolderclosed.png", this));
/*     */     
/* 550 */     table.put("FileView.computerIcon", loadIcon("computericon.png", this));
/* 551 */     table.put("FileView.fileIcon", loadIcon("document.png", this));
/* 552 */     table.put("FileView.floppyDriveIcon", loadIcon("floppy.png", this));
/* 553 */     table.put("FileView.hardDriveIcon", loadIcon("harddisk.png", this));
/*     */     
/* 555 */     table.put("FileChooser.detailsViewIcon", loadIcon("filedetails.png", this));
/*     */     
/* 557 */     table.put("FileChooser.homeFolderIcon", loadIcon("desktopicon.png", this));
/*     */     
/* 559 */     table.put("FileChooser.listViewIcon", loadIcon("filelist.png", this));
/* 560 */     table.put("FileChooser.newFolderIcon", loadIcon("newfolder.png", this));
/* 561 */     table.put("FileChooser.upFolderIcon", loadIcon("parentdirectory.png", this));
/*     */     
/* 563 */     table.put("FileChooser.upFolderIcon", loadIcon("parentdirectory.png", this));
/*     */ 
/*     */     
/* 566 */     table.put("OptionPane.errorIcon", loadIcon("error.png", this));
/* 567 */     table.put("OptionPane.informationIcon", loadIcon("information.png", this));
/*     */     
/* 569 */     table.put("OptionPane.warningIcon", loadIcon("warning.png", this));
/* 570 */     table.put("OptionPane.questionIcon", loadIcon("question.png", this));
/*     */     
/* 572 */     table.put("RootPane.colorChooserDialogBorder", LiquidFrameBorder.getInstance());
/*     */     
/* 574 */     table.put("RootPane.errorDialogBorder", LiquidFrameBorder.getInstance());
/* 575 */     table.put("RootPane.fileChooserDialogBorder", LiquidFrameBorder.getInstance());
/*     */     
/* 577 */     table.put("RootPane.frameBorder", LiquidFrameBorder.getInstance());
/* 578 */     table.put("RootPane.informationDialogBorder", LiquidFrameBorder.getInstance());
/*     */     
/* 580 */     table.put("RootPane.plainDialogBorder", LiquidFrameBorder.getInstance());
/* 581 */     table.put("RootPane.questionDialogBorder", LiquidFrameBorder.getInstance());
/*     */     
/* 583 */     table.put("RootPane.warningDialogBorder", LiquidFrameBorder.getInstance());
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
/* 597 */   public static ImageIcon loadIcon(String file, Object invoker) { return loadIconImmediately(file, invoker); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ImageIcon loadIconImmediately(String file, Object invoker) {
/*     */     try {
/* 609 */       Image img = SkinImageCache.getInstance().getImage(file);
/* 610 */       ImageIcon icon = new ImageIcon(img);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 615 */       if (icon.getIconWidth() <= 0) {
/* 616 */         System.out.println("******************** File " + file + " not found. Exiting");
/*     */         
/* 618 */         System.exit(1);
/*     */       } 
/*     */       
/* 621 */       return icon;
/* 622 */     } catch (Exception exception) {
/* 623 */       exception.printStackTrace();
/* 624 */       System.out.println("Error getting resource " + file);
/*     */       
/* 626 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 631 */   public static Color getLightControl() { return (Color)uiDefaults.get("control"); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 638 */   public static Color getDarkControl() { return (Color)uiDefaults.get("control"); }
/*     */ 
/*     */ 
/*     */   
/* 642 */   public static Color getBackgroundColor() { return (Color)uiDefaults.get("window"); }
/*     */ 
/*     */ 
/*     */   
/* 646 */   public static Color getDesktopColor() { return (Color)uiDefaults.get("desktop"); }
/*     */ 
/*     */ 
/*     */   
/* 650 */   protected static Color getWindowTitleInactiveForeground() { return (Color)uiDefaults.get("inactiveCaptionText"); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 659 */   public static void setDefaultTableBackgroundMode(boolean b) { setDefaultRowBackgroundMode(b); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 668 */   public static void setDefaultRowBackgroundMode(boolean b) { defaultRowBackgroundMode = b; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setLiquidDecorations(boolean b) {
/* 676 */     JFrame.setDefaultLookAndFeelDecorated(b);
/* 677 */     JDialog.setDefaultLookAndFeelDecorated(b);
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\LiquidLookAndFeel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */