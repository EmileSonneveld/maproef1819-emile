/*    */ package com.birosoft.liquid;
/*    */ 
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.plaf.ComponentUI;
/*    */ import javax.swing.plaf.basic.BasicSplitPaneDivider;
/*    */ import javax.swing.plaf.basic.BasicSplitPaneUI;
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
/*    */ public class LiquidSplitPaneUI
/*    */   extends BasicSplitPaneUI
/*    */ {
/* 25 */   public static ComponentUI createUI(JComponent x) { return new LiquidSplitPaneUI(); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 33 */   public BasicSplitPaneDivider createDefaultDivider() { return new LiquidSplitPaneDivider(this); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\liquidlnf.jar!\com\birosoft\liquid\LiquidSplitPaneUI.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.0.7
 */