/*     */ package com.birosoft.liquid;
/*     */ 
/*     */ import com.birosoft.liquid.skin.Skin;
/*     */ import com.birosoft.liquid.skin.SkinSimpleButtonIndexModel;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.TexturePaint;
/*     */ import java.awt.image.BufferedImage;
/*     */ import javax.swing.ButtonModel;
/*     */ import javax.swing.CellRendererPane;
/*     */ import javax.swing.DefaultButtonModel;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.ListCellRenderer;
/*     */ import javax.swing.UIManager;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LiquidComboBoxButton
/*     */   extends JButton
/*     */ {
/*     */   protected JComboBox comboBox;
/*     */   protected JList listBox;
/*     */   protected CellRendererPane rendererPane;
/*     */   protected Icon comboIcon;
/*     */   protected boolean iconOnly = false;
/*     */   BufferedImage focusImg;
/*     */   Skin skinCombo;
/*     */   SkinSimpleButtonIndexModel indexModel;
/*     */   Skin skinArrow;
/*     */   Skin skinButton;
/*     */   
/*  49 */   public final JComboBox getComboBox() { return this.comboBox; }
/*     */ 
/*     */ 
/*     */   
/*  53 */   public final void setComboBox(JComboBox cb) { this.comboBox = cb; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   public final Icon getComboIcon() { return this.comboIcon; }
/*     */ 
/*     */ 
/*     */   
/*  62 */   public final void setComboIcon(Icon i) { this.comboIcon = i; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   public final boolean isIconOnly() { return this.iconOnly; }
/*     */   public LiquidComboBoxButton(JComboBox cb, Icon i, CellRendererPane pane, JList list) { this(); this.comboBox = cb; this.comboIcon = i;
/*     */     this.rendererPane = pane;
/*     */     this.listBox = list;
/*  71 */     setEnabled(this.comboBox.isEnabled()); } public final void setIconOnly(boolean isIconOnly) { this.iconOnly = isIconOnly; }
/*     */ 
/*     */ 
/*     */   
/*     */   LiquidComboBoxButton() {
/*  76 */     super("");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 114 */     this.indexModel = new SkinSimpleButtonIndexModel();
/*     */     DefaultButtonModel model = new DefaultButtonModel(this) { private final LiquidComboBoxButton this$0; public void setArmed(boolean armed) { super.setArmed(isPressed() ? true : armed); } }
/*     */       ;
/*     */     setModel(model);
/*     */     setBackground(UIManager.getColor("ComboBox.background"));
/*     */     setForeground(UIManager.getColor("ComboBox.foreground"));
/*     */     ImageIcon icon = LiquidLookAndFeel.loadIcon("comboboxfocus.png", this);
/*     */     this.focusImg = new BufferedImage(2, 2, 1);
/*     */     Graphics g3 = this.focusImg.getGraphics();
/*     */     icon.paintIcon(this, g3, 0, 0);
/*     */   } public void paintComponent(Graphics g) {
/* 125 */     boolean leftToRight = getComponentOrientation().isLeftToRight();
/*     */     
/* 127 */     int index = this.indexModel.getIndexForState(this.model.isEnabled(), this.model.isRollover(), (this.model.isArmed() && this.model.isPressed() | this.model.isSelected()));
/*     */ 
/*     */     
/* 130 */     if (this.iconOnly) {
/*     */ 
/*     */       
/* 133 */       ButtonModel model = getModel();
/*     */       
/* 135 */       boolean selected = (model.isArmed() && model.isPressed() | model.isSelected());
/* 136 */       getSkinCombo().draw(g, index, getWidth(), getHeight());
/*     */       
/* 138 */       int amnt = getWidth() - getSkinArrow().getHsize();
/* 139 */       int middle = (getHeight() - getSkinArrow().getVsize()) / 2;
/* 140 */       getSkinArrow().draw(g, index, getWidth() - getSkinArrow().getHsize() - 6, middle, getSkinArrow().getHsize(), getSkinArrow().getVsize());
/*     */       
/* 142 */       this.comboBox.getEditor().getEditorComponent().repaint();
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 147 */       ButtonModel model = getModel();
/*     */       
/* 149 */       boolean selected = (model.isArmed() && model.isPressed() | model.isSelected());
/* 150 */       getSkinCombo().draw(g, index, getWidth(), getHeight());
/*     */       
/* 152 */       int middle = (getHeight() - getSkinArrow().getVsize()) / 2;
/* 153 */       getSkinArrow().draw(g, index, getWidth() - getSkinArrow().getHsize() - 6, middle, getSkinArrow().getHsize(), getSkinArrow().getVsize());
/*     */     } 
/*     */ 
/*     */     
/* 157 */     Insets insets = new Insets(0, 12, 2, 2);
/*     */     
/* 159 */     int width = getWidth() - insets.left + insets.right;
/* 160 */     int widthFocus = width;
/* 161 */     int height = getHeight() - insets.top + insets.bottom;
/*     */     
/* 163 */     if (height <= 0 || width <= 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 168 */     int left = insets.left;
/* 169 */     int top = insets.top;
/* 170 */     int right = left + width - 1;
/* 171 */     int bottom = top + height - 1;
/*     */     
/* 173 */     int iconWidth = LiquidComboBoxUI.comboBoxButtonSize;
/* 174 */     int iconLeft = leftToRight ? right : left;
/*     */ 
/*     */     
/* 177 */     Component c = null;
/* 178 */     boolean mustResetOpaque = false;
/* 179 */     boolean savedOpaque = false;
/* 180 */     boolean paintFocus = false;
/* 181 */     if (!this.iconOnly && this.comboBox != null) {
/*     */       
/* 183 */       ListCellRenderer renderer = this.comboBox.getRenderer();
/* 184 */       boolean renderPressed = getModel().isPressed();
/* 185 */       c = renderer.getListCellRendererComponent(this.listBox, this.comboBox.getSelectedItem(), -1, renderPressed, false);
/* 186 */       c.setFont(this.rendererPane.getFont());
/*     */       
/* 188 */       if (this.model.isArmed() && this.model.isPressed()) {
/*     */         
/* 190 */         if (isOpaque())
/*     */         {
/* 192 */           c.setBackground(UIManager.getColor("Button.select"));
/*     */         }
/* 194 */         c.setForeground(this.comboBox.getForeground());
/* 195 */       } else if (!this.comboBox.isEnabled()) {
/*     */         
/* 197 */         if (isOpaque())
/*     */         {
/* 199 */           c.setBackground(UIManager.getColor("ComboBox.disabledBackground"));
/*     */         }
/* 201 */         c.setForeground(UIManager.getColor("ComboBox.disabledForeground"));
/*     */       } else {
/*     */         
/* 204 */         c.setForeground(this.comboBox.getForeground());
/* 205 */         c.setBackground(this.comboBox.getBackground());
/*     */       } 
/* 207 */       if (!mustResetOpaque && c instanceof JComponent) {
/*     */         
/* 209 */         mustResetOpaque = true;
/* 210 */         JComponent jc = (JComponent)c;
/* 211 */         savedOpaque = jc.isOpaque();
/* 212 */         jc.setOpaque(false);
/*     */       } 
/*     */       
/* 215 */       int cWidth = width - insets.right + iconWidth;
/*     */ 
/*     */       
/* 218 */       boolean shouldValidate = false;
/* 219 */       if (c instanceof javax.swing.JPanel)
/*     */       {
/* 221 */         shouldValidate = true;
/*     */       }
/*     */       
/* 224 */       if (leftToRight) {
/*     */         
/* 226 */         this.rendererPane.paintComponent(g, c, this, left, top, cWidth, height, shouldValidate);
/*     */       } else {
/*     */         
/* 229 */         this.rendererPane.paintComponent(g, c, this, left + iconWidth, top, cWidth, height, shouldValidate);
/*     */       } 
/* 231 */       if (paintFocus) {
/*     */         
/* 233 */         g.setColor(Color.black);
/* 234 */         Graphics2D g2d = (Graphics2D)g;
/*     */         
/* 236 */         Rectangle r = new Rectangle(left, top, 2, 2);
/* 237 */         TexturePaint tp = new TexturePaint(this.focusImg, r);
/*     */         
/* 239 */         g2d.setPaint(tp);
/* 240 */         g2d.draw(new Rectangle(left, top, cWidth, height));
/*     */       } 
/*     */     } 
/* 243 */     if (mustResetOpaque) {
/*     */       
/* 245 */       JComponent jc = (JComponent)c;
/* 246 */       jc.setOpaque(savedOpaque);
/*     */     } 
/*     */   } public LiquidComboBoxButton(JComboBox cb, Icon i, boolean onlyIcon, CellRendererPane pane, JList list) {
/*     */     this(cb, i, pane, list);
/*     */     this.iconOnly = onlyIcon;
/*     */   } public Skin getSkinCombo() {
/* 252 */     if (this.skinCombo == null)
/*     */     {
/* 254 */       this.skinCombo = new Skin("combobox.png", 4, 10, 6, 18, 4);
/*     */     }
/* 256 */     return this.skinCombo;
/*     */   }
/*     */ 
/*     */   
/*     */   public Skin getSkinArrow() {
/* 261 */     if (this.skinArrow == null)
/*     */     {
/* 263 */       this.skinArrow = new Skin("comboboxarrow.png", 4, 0);
/*     */     }
/*     */     
/* 266 */     return this.skinArrow;
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\LiquidComboBoxButton.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */