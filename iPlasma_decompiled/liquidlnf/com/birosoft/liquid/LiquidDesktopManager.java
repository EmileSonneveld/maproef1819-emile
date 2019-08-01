/*    */ package com.birosoft.liquid;
/*    */ 
/*    */ import javax.swing.DefaultDesktopManager;
/*    */ import javax.swing.JComponent;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LiquidDesktopManager
/*    */   extends DefaultDesktopManager
/*    */ {
/*    */   public void beginDraggingFrame(JComponent f) {}
/*    */   
/* 31 */   public void dragFrame(JComponent f, int newX, int newY) { setBoundsForFrame(f, newX, newY, f.getWidth(), f.getHeight()); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void endDraggingFrame(JComponent f) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 41 */   public void resizeFrame(JComponent f, int newX, int newY, int newWidth, int newHeight) { setBoundsForFrame(f, newX, newY, newWidth, newHeight); }
/*    */   
/*    */   public void endResizingFrame(JComponent f) {}
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\LiquidDesktopManager.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */