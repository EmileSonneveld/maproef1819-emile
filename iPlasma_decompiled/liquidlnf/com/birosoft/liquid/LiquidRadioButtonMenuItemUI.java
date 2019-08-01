/*    */ package com.birosoft.liquid;
/*    */ 
/*    */ import java.awt.Point;
/*    */ import java.awt.event.MouseEvent;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.JMenuItem;
/*    */ import javax.swing.MenuElement;
/*    */ import javax.swing.MenuSelectionManager;
/*    */ import javax.swing.plaf.ComponentUI;
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
/*    */ public class LiquidRadioButtonMenuItemUI
/*    */   extends LiquidMenuItemUI
/*    */ {
/* 27 */   public static ComponentUI createUI(JComponent b) { return new LiquidRadioButtonMenuItemUI(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 32 */   protected String getPropertyPrefix() { return "RadioButtonMenuItem"; }
/*    */ 
/*    */ 
/*    */   
/*    */   public void processMouseEvent(JMenuItem item, MouseEvent e, MenuElement[] path, MenuSelectionManager manager) {
/* 37 */     Point p = e.getPoint();
/* 38 */     if (p.x >= 0 && p.x < item.getWidth() && p.y >= 0 && p.y < item.getHeight()) {
/*    */       
/* 40 */       if (e.getID() == 502)
/*    */       
/* 42 */       { manager.clearSelectedPath();
/* 43 */         item.doClick(0);
/* 44 */         item.setArmed(false); }
/*    */       else
/* 46 */       { manager.setSelectedPath(path); } 
/* 47 */     } else if (item.getModel().isArmed()) {
/*    */       
/* 49 */       MenuElement[] newPath = new MenuElement[path.length - 1];
/*    */       
/* 51 */       for (int i = 0, c = path.length - 1; i < c; i++)
/* 52 */         newPath[i] = path[i]; 
/* 53 */       manager.setSelectedPath(newPath);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\LiquidRadioButtonMenuItemUI.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */