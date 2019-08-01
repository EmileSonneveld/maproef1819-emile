/*     */ package com.birosoft.liquid;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import javax.swing.DesktopManager;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JDesktopPane;
/*     */ import javax.swing.JInternalFrame;
/*     */ import javax.swing.LookAndFeel;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.basic.BasicInternalFrameUI;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LiquidInternalFrameUI
/*     */   extends BasicInternalFrameUI
/*     */ {
/*  38 */   protected static String IS_PALETTE = "JInternalFrame.isPalette";
/*  39 */   private static String FRAME_TYPE = "JInternalFrame.frameType";
/*  40 */   private static String NORMAL_FRAME = "normal";
/*  41 */   private static String PALETTE_FRAME = "palette";
/*  42 */   private static String OPTION_DIALOG = "optionDialog";
/*  43 */   private static final PropertyChangeListener liquidPropertyChangeListener = new LiquidPropertyChangeHandler(null);
/*     */ 
/*     */ 
/*     */   
/*     */   static boolean allowRoundedWindows = false;
/*     */ 
/*     */ 
/*     */   
/*     */   private static DesktopManager sharedDesktopManager;
/*     */ 
/*     */   
/*  54 */   LiquidInternalFrameBorder frameBorder = new LiquidInternalFrameBorder();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private LiquidInternalFrameTitlePane titlePane;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  69 */   public LiquidInternalFrameUI(JInternalFrame frame) { super(frame); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  79 */   public static ComponentUI createUI(JComponent c) { return new LiquidInternalFrameUI((JInternalFrame)c); }
/*     */ 
/*     */   
/*     */   JDesktopPane getDesktopPane(JComponent frame) {
/*  83 */     JDesktopPane pane = null;
/*  84 */     Component c = frame.getParent();
/*     */ 
/*     */     
/*  87 */     while (pane == null) {
/*  88 */       if (c instanceof JDesktopPane) {
/*  89 */         pane = (JDesktopPane)c; continue;
/*  90 */       }  if (c == null) {
/*     */         break;
/*     */       }
/*  93 */       c = c.getParent();
/*     */     } 
/*     */ 
/*     */     
/*  97 */     return pane;
/*     */   }
/*     */   
/*     */   protected DesktopManager getDesktopManager() {
/* 101 */     if (!allowRoundedWindows) {
/* 102 */       return super.getDesktopManager();
/*     */     }
/*     */     
/* 105 */     if (sharedDesktopManager == null) {
/* 106 */       sharedDesktopManager = createDesktopManager();
/*     */     }
/*     */     
/* 109 */     return sharedDesktopManager;
/*     */   }
/*     */   
/*     */   protected DesktopManager createDesktopManager() {
/* 113 */     if (!allowRoundedWindows) {
/* 114 */       return super.createDesktopManager();
/*     */     }
/* 116 */     return new LiquidDesktopManager();
/*     */   }
/*     */ 
/*     */   
/*     */   public void installUI(JComponent c) {
/* 121 */     super.installUI(c);
/*     */     
/* 123 */     if (allowRoundedWindows) {
/* 124 */       this.frame.setOpaque(false);
/*     */     }
/*     */     
/* 127 */     this.frame.setBorder(this.frameBorder);
/* 128 */     this.frame.addPropertyChangeListener(liquidPropertyChangeListener);
/*     */ 
/*     */     
/* 131 */     Object paletteProp = c.getClientProperty(IS_PALETTE);
/*     */     
/* 133 */     if (paletteProp != null) {
/* 134 */       setPalette(((Boolean)paletteProp).booleanValue());
/*     */     }
/*     */   }
/*     */   
/*     */   public void uninstallUI(JComponent c) {
/* 139 */     this.frame.removePropertyChangeListener(liquidPropertyChangeListener);
/* 140 */     super.uninstallUI(c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected JComponent createNorthPane(JInternalFrame frame) {
/* 149 */     super.createNorthPane(frame);
/* 150 */     this.titlePane = new LiquidInternalFrameTitlePane(frame);
/*     */     
/* 152 */     return this.titlePane;
/*     */   }
/*     */   
/*     */   protected void activateFrame(JInternalFrame f) {
/* 156 */     super.activateFrame(f);
/* 157 */     this.frameBorder.setActive(true);
/* 158 */     this.titlePane.activate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void deactivateFrame(JInternalFrame f) {
/* 165 */     super.deactivateFrame(f);
/* 166 */     this.frameBorder.setActive(false);
/* 167 */     this.titlePane.deactivate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPalette(boolean isPalette) {
/* 177 */     if (isPalette) {
/* 178 */       LookAndFeel.installBorder(this.frame, "InternalFrame.paletteBorder");
/*     */     } else {
/* 180 */       LookAndFeel.installBorder(this.frame, "InternalFrame.border");
/*     */     } 
/*     */     
/* 183 */     this.titlePane.setPalette(isPalette);
/* 184 */     this.frameBorder.isPalette = isPalette;
/* 185 */     this.frame.setBorder(this.frameBorder);
/*     */   }
/*     */   
/*     */   private void stripContentBorder(Object c) {
/* 189 */     if (c instanceof JComponent) {
/* 190 */       JComponent contentComp = (JComponent)c;
/* 191 */       Border contentBorder = contentComp.getBorder();
/*     */       
/* 193 */       if (contentBorder == null || contentBorder instanceof javax.swing.plaf.UIResource) {
/* 194 */         contentComp.setBorder(new EmptyBorder(0, 0, 0, 0));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setFrameType(String frameType) {
/* 200 */     if (frameType.equals(OPTION_DIALOG)) {
/* 201 */       LookAndFeel.installBorder(this.frame, "InternalFrame.optionDialogBorder");
/* 202 */       this.titlePane.setPalette(false);
/* 203 */     } else if (frameType.equals(PALETTE_FRAME)) {
/* 204 */       LookAndFeel.installBorder(this.frame, "InternalFrame.paletteBorder");
/* 205 */       this.titlePane.setPalette(true);
/*     */     } else {
/* 207 */       LookAndFeel.installBorder(this.frame, "InternalFrame.border");
/* 208 */       this.titlePane.setPalette(false);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static class LiquidPropertyChangeHandler implements PropertyChangeListener { private LiquidPropertyChangeHandler() {}
/*     */     
/*     */     public void propertyChange(PropertyChangeEvent e) {
/* 215 */       String name = e.getPropertyName();
/* 216 */       JInternalFrame jif = (JInternalFrame)e.getSource();
/*     */       
/* 218 */       if (!(jif.getUI() instanceof LiquidInternalFrameUI)) {
/*     */         return;
/*     */       }
/*     */       
/* 222 */       LiquidInternalFrameUI ui = (LiquidInternalFrameUI)jif.getUI();
/*     */       
/* 224 */       if (name.equals(FRAME_TYPE)) {
/* 225 */         if (e.getNewValue() instanceof String) {
/* 226 */           ui.setFrameType((String)e.getNewValue());
/*     */         }
/* 228 */       } else if (name.equals(LiquidInternalFrameUI.IS_PALETTE)) {
/* 229 */         if (e.getNewValue() != null) {
/* 230 */           ui.setPalette(((Boolean)e.getNewValue()).booleanValue());
/*     */         } else {
/* 232 */           ui.setPalette(false);
/*     */         } 
/* 234 */       } else if (name.equals("contentPane")) {
/* 235 */         ui.stripContentBorder(e.getNewValue());
/*     */       } 
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\LiquidInternalFrameUI.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */