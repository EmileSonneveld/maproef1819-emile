/*     */ package com.birosoft.liquid;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Insets;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import javax.swing.ComboBoxEditor;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.KeyStroke;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.basic.BasicComboBoxUI;
/*     */ import javax.swing.plaf.basic.BasicComboPopup;
/*     */ import javax.swing.plaf.basic.ComboPopup;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LiquidComboBoxUI
/*     */   extends BasicComboBoxUI
/*     */ {
/*  41 */   static int comboBoxButtonSize = 18;
/*     */   
/*     */   private int prevSelectedItem;
/*     */ 
/*     */   
/*     */   public static ComponentUI createUI(JComponent c) {
/*  47 */     c.setOpaque(false);
/*  48 */     c.addPropertyChangeListener("opaque", new PropertyChangeListener(c) { private final JComponent val$c;
/*     */           
/*  50 */           public void propertyChange(PropertyChangeEvent evt) { this.val$c.setOpaque(false); }
/*     */            }
/*     */       );
/*  53 */     return new LiquidComboBoxUI();
/*     */   }
/*     */   
/*     */   public void installUI(JComponent c) {
/*  57 */     super.installUI(c);
/*  58 */     addKeyboardActions((JComboBox)c);
/*     */   }
/*     */ 
/*     */   
/*  62 */   public void uninstallUI(JComponent c) { super.uninstallUI(c); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void paint(Graphics g, JComponent c) {}
/*     */ 
/*     */   
/*  69 */   protected ComboBoxEditor createEditor() { return new LiquidComboBoxEditor.UIResource(); }
/*     */ 
/*     */ 
/*     */   
/*  73 */   protected ComboPopup createPopup() { return new LiquidComboPopup(this, this.comboBox); }
/*     */ 
/*     */   
/*     */   protected JButton createArrowButton() {
/*  77 */     JButton button = new LiquidComboBoxButton(this.comboBox, new LiquidCheckBoxIcon(), this.comboBox.isEditable(), this.currentValuePane, this.listBox);
/*  78 */     button.setMargin(new Insets(0, 0, 0, 0));
/*  79 */     button.setFocusable(false);
/*  80 */     return button;
/*     */   }
/*     */ 
/*     */   
/*  84 */   public PropertyChangeListener createPropertyChangeListener() { return new LiquidPropertyChangeListener(this); }
/*     */ 
/*     */   
/*     */   public class LiquidPropertyChangeListener
/*     */     extends BasicComboBoxUI.PropertyChangeHandler
/*     */   {
/*     */     private final LiquidComboBoxUI this$0;
/*     */     
/*  92 */     public LiquidPropertyChangeListener(LiquidComboBoxUI this$0) { super(this$0); this.this$0 = this$0; }
/*     */     public void propertyChange(PropertyChangeEvent e) {
/*  94 */       super.propertyChange(e);
/*  95 */       String propertyName = e.getPropertyName();
/*     */       
/*  97 */       if (propertyName.equals("editable")) {
/*  98 */         LiquidComboBoxButton button = (LiquidComboBoxButton)this.this$0.arrowButton;
/*  99 */         button.setIconOnly(this.this$0.comboBox.isEditable());
/* 100 */         this.this$0.comboBox.repaint();
/* 101 */       } else if (propertyName.equals("background")) {
/* 102 */         Color color = (Color)e.getNewValue();
/* 103 */         this.this$0.listBox.setBackground(color);
/*     */       }
/* 105 */       else if (propertyName.equals("foreground")) {
/* 106 */         Color color = (Color)e.getNewValue();
/* 107 */         this.this$0.listBox.setForeground(color);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addKeyboardActions(JComboBox cb) {
/* 118 */     KeyStroke ksEnter = KeyStroke.getKeyStroke(10, 0);
/* 119 */     cb.registerKeyboardAction(new ActionListener(this, cb) { private final JComboBox val$cb;
/*     */           public void actionPerformed(ActionEvent e) {
/* 121 */             this.this$0.prevSelectedItem = this.val$cb.getSelectedIndex();
/* 122 */             if (this.val$cb.isPopupVisible()) {
/* 123 */               this.val$cb.hidePopup();
/*     */             } else {
/* 125 */               this.val$cb.showPopup();
/*     */             } 
/*     */           }
/*     */           
/*     */           private final LiquidComboBoxUI this$0; }
/*     */         ksEnter, 0);
/* 131 */     KeyStroke ksESC = KeyStroke.getKeyStroke(27, 0);
/* 132 */     cb.registerKeyboardAction(new ActionListener(this, cb) {
/*     */           public void actionPerformed(ActionEvent e) {
/* 134 */             this.val$cb.hidePopup();
/* 135 */             this.val$cb.setSelectedIndex(this.this$0.prevSelectedItem);
/*     */           }
/*     */           
/*     */           private final JComboBox val$cb;
/*     */           private final LiquidComboBoxUI this$0;
/*     */         }ksESC, 0);
/* 141 */     KeyStroke ksDown = KeyStroke.getKeyStroke(40, 0);
/* 142 */     cb.registerKeyboardAction(new ActionListener(this, cb) { private final JComboBox val$cb;
/*     */           public void actionPerformed(ActionEvent e) {
/* 144 */             if (this.val$cb.getSelectedIndex() <= this.val$cb.getItemCount() - 2) {
/* 145 */               this.val$cb.setSelectedIndex(this.val$cb.getSelectedIndex() + 1);
/* 146 */             } else if (this.val$cb.getSelectedIndex() == -1 && this.val$cb.getItemCount() > 0) {
/* 147 */               this.val$cb.setSelectedIndex(0);
/*     */             } else {
/* 149 */               Toolkit.getDefaultToolkit().beep();
/*     */             } 
/*     */           }
/*     */           
/*     */           private final LiquidComboBoxUI this$0; }
/*     */         ksDown, 0);
/* 155 */     KeyStroke ksRight = KeyStroke.getKeyStroke(39, 0);
/* 156 */     cb.registerKeyboardAction(new ActionListener(this, cb) { private final JComboBox val$cb;
/*     */           public void actionPerformed(ActionEvent e) {
/* 158 */             if (this.val$cb.getSelectedIndex() <= this.val$cb.getItemCount() - 2) {
/* 159 */               this.val$cb.setSelectedIndex(this.val$cb.getSelectedIndex() + 1);
/* 160 */             } else if (this.val$cb.getSelectedIndex() == -1 && this.val$cb.getItemCount() > 0) {
/* 161 */               this.val$cb.setSelectedIndex(0);
/*     */             } else {
/* 163 */               Toolkit.getDefaultToolkit().beep();
/*     */             } 
/*     */           }
/*     */           
/*     */           private final LiquidComboBoxUI this$0; }
/*     */         ksRight, 0);
/* 169 */     KeyStroke ksUp = KeyStroke.getKeyStroke(38, 0);
/* 170 */     cb.registerKeyboardAction(new ActionListener(this, cb) { private final JComboBox val$cb;
/*     */           public void actionPerformed(ActionEvent e) {
/* 172 */             if (this.val$cb.getSelectedIndex() > 0) {
/* 173 */               this.val$cb.setSelectedIndex(this.val$cb.getSelectedIndex() - 1);
/* 174 */             } else if (this.val$cb.getSelectedIndex() == -1 && this.val$cb.getItemCount() > 0) {
/* 175 */               this.val$cb.setSelectedIndex(0);
/*     */             } else {
/* 177 */               Toolkit.getDefaultToolkit().beep();
/*     */             } 
/*     */           }
/*     */ 
/*     */           
/*     */           private final LiquidComboBoxUI this$0; }
/*     */         ksUp, 0);
/* 184 */     KeyStroke ksLeft = KeyStroke.getKeyStroke(37, 0);
/* 185 */     cb.registerKeyboardAction(new ActionListener(this, cb) { private final JComboBox val$cb;
/*     */           public void actionPerformed(ActionEvent e) {
/* 187 */             if (this.val$cb.getSelectedIndex() > 0) {
/* 188 */               this.val$cb.setSelectedIndex(this.val$cb.getSelectedIndex() - 1);
/* 189 */             } else if (this.val$cb.getSelectedIndex() == -1 && this.val$cb.getItemCount() > 0) {
/* 190 */               this.val$cb.setSelectedIndex(0);
/*     */             } else {
/* 192 */               Toolkit.getDefaultToolkit().beep();
/*     */             } 
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           private final LiquidComboBoxUI this$0; }
/*     */         ksLeft, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void editablePropertyChanged(PropertyChangeEvent e) {}
/*     */ 
/*     */ 
/*     */   
/* 210 */   protected LayoutManager createLayoutManager() { return new MetouiaComboBoxLayoutManager(this); }
/*     */ 
/*     */   
/*     */   public class MetouiaComboBoxLayoutManager
/*     */     implements LayoutManager
/*     */   {
/*     */     private final LiquidComboBoxUI this$0;
/*     */     
/* 218 */     public MetouiaComboBoxLayoutManager(LiquidComboBoxUI this$0) { this.this$0 = this$0; }
/*     */ 
/*     */     
/*     */     public void addLayoutComponent(String name, Component comp) {}
/*     */     
/*     */     public void removeLayoutComponent(Component comp) {}
/*     */     
/*     */     public Dimension preferredLayoutSize(Container parent) {
/* 226 */       JComboBox cb = (JComboBox)parent;
/* 227 */       return parent.getPreferredSize();
/*     */     }
/*     */     
/*     */     public Dimension minimumLayoutSize(Container parent) {
/* 231 */       JComboBox cb = (JComboBox)parent;
/* 232 */       return parent.getMinimumSize();
/*     */     }
/*     */     
/*     */     public void layoutContainer(Container parent) {
/* 236 */       JComboBox cb = (JComboBox)parent;
/* 237 */       int width = cb.getWidth();
/* 238 */       int height = cb.getHeight();
/*     */ 
/*     */ 
/*     */       
/* 242 */       if (this.this$0.comboBox.isEditable()) {
/* 243 */         if (this.this$0.arrowButton != null) {
/* 244 */           this.this$0.arrowButton.setBounds(0, 0, width, height);
/*     */         }
/* 246 */         if (this.this$0.editor != null) {
/* 247 */           Rectangle cvb = this.this$0.rectangleForCurrentValue2();
/* 248 */           this.this$0.editor.setBounds(cvb);
/*     */         } 
/*     */       } else {
/* 251 */         this.this$0.arrowButton.setBounds(0, 0, width, height);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   protected Rectangle rectangleForCurrentValue2() {
/* 257 */     int width = this.comboBox.getWidth();
/* 258 */     int height = this.comboBox.getHeight();
/* 259 */     Insets insets = getInsets();
/* 260 */     int buttonSize = height - insets.top + insets.bottom;
/* 261 */     if (this.arrowButton != null) {
/* 262 */       buttonSize = comboBoxButtonSize;
/*     */     }
/* 264 */     if (this.comboBox.getComponentOrientation().isLeftToRight())
/*     */     {
/* 266 */       return new Rectangle(insets.left + 8, insets.top, width - insets.left + insets.right + buttonSize - 7, height - insets.top + insets.bottom - 2);
/*     */     }
/* 268 */     return new Rectangle(insets.left + buttonSize, insets.top, width - insets.left + insets.right + buttonSize, height - insets.top + insets.bottom);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void removeListeners() {
/* 279 */     if (this.propertyChangeListener != null) {
/* 280 */       this.comboBox.removePropertyChangeListener(this.propertyChangeListener);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 290 */   public void configureEditor() { super.configureEditor(); }
/*     */ 
/*     */ 
/*     */   
/* 294 */   public void unconfigureEditor() { super.unconfigureEditor(); }
/*     */ 
/*     */   
/*     */   public Dimension getMinimumSize(JComponent c) {
/* 298 */     if (!this.isMinimumSizeDirty) {
/* 299 */       return new Dimension(this.cachedMinimumSize);
/*     */     }
/*     */     
/* 302 */     Dimension size = null;
/*     */     
/* 304 */     if (!this.comboBox.isEditable() && this.arrowButton != null && this.arrowButton instanceof LiquidComboBoxButton) {
/*     */       
/* 306 */       LiquidComboBoxButton button = (LiquidComboBoxButton)this.arrowButton;
/* 307 */       Insets buttonInsets = new Insets(0, 0, 0, 0);
/* 308 */       Insets insets = this.comboBox.getInsets();
/*     */       
/* 310 */       size = getDisplaySize();
/* 311 */       size.width += comboBoxButtonSize + insets.left + insets.right;
/* 312 */       size.width += buttonInsets.left + buttonInsets.right;
/* 313 */       size.width += buttonInsets.right + button.getComboIcon().getIconWidth();
/* 314 */       size.height += insets.top + insets.bottom;
/* 315 */       size.height += buttonInsets.top + buttonInsets.bottom;
/* 316 */       size.height = Math.max(21, size.height);
/* 317 */     } else if (this.comboBox.isEditable() && this.arrowButton != null && this.editor != null) {
/* 318 */       size = super.getMinimumSize(c);
/* 319 */       Insets margin = this.arrowButton.getMargin();
/* 320 */       Insets insets = this.comboBox.getInsets();
/* 321 */       if (this.editor instanceof JComponent) {
/* 322 */         Insets editorInsets = ((JComponent)this.editor).getInsets();
/*     */       }
/* 324 */       size.height += margin.top + margin.bottom;
/* 325 */       size.height += insets.top + insets.bottom;
/*     */     }
/*     */     else {
/*     */       
/* 329 */       size = super.getMinimumSize(c);
/*     */     } 
/*     */     
/* 332 */     this.cachedMinimumSize.setSize(size.width, size.height);
/* 333 */     this.isMinimumSizeDirty = false;
/*     */     
/* 335 */     return new Dimension(this.cachedMinimumSize);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class LiquidComboPopup
/*     */     extends BasicComboPopup
/*     */   {
/*     */     private final LiquidComboBoxUI this$0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public LiquidComboPopup(LiquidComboBoxUI this$0, JComboBox cBox) {
/* 352 */       super(cBox);
/*     */       this.this$0 = this$0;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 361 */     public void delegateFocus(MouseEvent e) { super.delegateFocus(e); }
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\LiquidComboBoxUI.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */