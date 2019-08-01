/*     */ package com.birosoft.liquid;
/*     */ 
/*     */ import com.birosoft.liquid.skin.Skin;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Insets;
/*     */ import javax.swing.AbstractButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.border.Border;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ import javax.swing.plaf.ComponentUI;
/*     */ import javax.swing.plaf.basic.BasicToolBarUI;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LiquidToolBarUI
/*     */   extends BasicToolBarUI
/*     */ {
/*  37 */   private Border border = new EmptyBorder(4, 4, 4, 4);
/*  38 */   private int orientation = -1;
/*     */   
/*     */   private boolean changeBorder = true;
/*     */   
/*  42 */   private Skin vbarHandler = new Skin("vtoolbarhandler.png", 1, 8, 3, 8, 3);
/*  43 */   private Skin hbarHandler = new Skin("htoolbarhandler.png", 1, 3, 8, 3, 8);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   private Insets insets = new Insets(2, 2, 2, 2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   public static ComponentUI createUI(JComponent c) { return new LiquidToolBarUI(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void installUI(JComponent c) {
/*  69 */     super.installUI(c);
/*  70 */     c.putClientProperty("JToolBar.isRollover", Boolean.TRUE);
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
/*     */   public void paint(Graphics g, JComponent c) {
/*  82 */     g.setColor(new Color(238, 237, 236));
/*  83 */     int i = 0;
/*  84 */     int height = c.getHeight();
/*  85 */     while (i < height) {
/*     */       
/*  87 */       g.drawLine(0, i, c.getWidth() - 1, i);
/*  88 */       i++;
/*  89 */       g.drawLine(0, i, c.getWidth() - 1, i);
/*  90 */       i += 3;
/*     */     } 
/*     */     
/*  93 */     if (!isFloating()) {
/*     */       
/*  95 */       if (this.toolBar.getOrientation() != this.orientation) {
/*     */         
/*  97 */         if (this.toolBar.getOrientation() == 0) {
/*     */           
/*  99 */           if (this.toolBar.isFloatable()) {
/*     */             
/* 101 */             this.toolBar.setBorder(new EmptyBorder(2, 11, 2, 2));
/*     */           } else {
/*     */             
/* 104 */             this.toolBar.setBorder(new EmptyBorder(2, 2, 2, 2));
/*     */           }
/*     */         
/*     */         }
/* 108 */         else if (this.toolBar.isFloatable()) {
/*     */           
/* 110 */           this.toolBar.setBorder(new EmptyBorder(12, 2, 2, 2));
/*     */         } else {
/*     */           
/* 113 */           this.toolBar.setBorder(new EmptyBorder(2, 2, 2, 2));
/*     */         } 
/*     */         
/* 116 */         this.orientation = this.toolBar.getOrientation();
/* 117 */         this.changeBorder = true;
/*     */       } 
/* 119 */       if (this.toolBar.getOrientation() == 0)
/*     */       {
/* 121 */         if (this.toolBar.isFloatable())
/*     */         {
/* 123 */           this.vbarHandler.draw(g, 0, 1, 2, 8, c.getHeight() - 4);
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 128 */       else if (this.toolBar.isFloatable())
/*     */       {
/* 130 */         this.hbarHandler.draw(g, 0, 1, 2, c.getWidth() - 4, 8);
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/* 136 */     else if (this.changeBorder) {
/*     */       
/* 138 */       this.toolBar.setBorder(new EmptyBorder(1, 1, 1, 1));
/* 139 */       this.changeBorder = false;
/* 140 */       this.orientation = -1;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setBorderToRollover(Component c) {
/* 207 */     if (c instanceof AbstractButton) {
/*     */       
/* 209 */       AbstractButton b = (AbstractButton)c;
/* 210 */       b.setBorder(this.border);
/* 211 */       b.putClientProperty("JToolBar.isToolbarButton", Boolean.TRUE);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setBorderToNormal(Component c) {
/* 217 */     if (c instanceof AbstractButton) {
/*     */       
/* 219 */       AbstractButton b = (AbstractButton)c;
/* 220 */       b.setBorder(this.border);
/* 221 */       b.putClientProperty("JToolBar.isToolbarButton", Boolean.TRUE);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\LiquidToolBarUI.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */