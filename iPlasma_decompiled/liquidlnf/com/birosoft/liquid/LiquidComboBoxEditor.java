/*    */ package com.birosoft.liquid;
/*    */ 
/*    */ import com.birosoft.liquid.skin.Skin;
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Insets;
/*    */ import javax.swing.border.AbstractBorder;
/*    */ import javax.swing.plaf.UIResource;
/*    */ import javax.swing.plaf.basic.BasicComboBoxEditor;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LiquidComboBoxEditor
/*    */   extends BasicComboBoxEditor
/*    */ {
/*    */   static Skin skin;
/*    */   
/*    */   public LiquidComboBoxEditor() {
/* 33 */     this.editor.setBorder(new AbstractBorder(this)
/*    */         {
/*    */           private final LiquidComboBoxEditor this$0;
/*    */ 
/*    */ 
/*    */ 
/*    */           
/* 40 */           public Insets getBorderInsets(Component c) { return new Insets(0, 3, 0, 3); }
/*    */           
/*    */           public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {}
/*    */         });
/*    */   }
/*    */   
/*    */   public static class UIResource extends LiquidComboBoxEditor implements UIResource {}
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\LiquidComboBoxEditor.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */