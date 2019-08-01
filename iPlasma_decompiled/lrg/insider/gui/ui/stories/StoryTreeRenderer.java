/*    */ package classes.lrg.insider.gui.ui.stories;
/*    */ 
/*    */ import java.awt.Component;
/*    */ import javax.swing.JTree;
/*    */ import javax.swing.tree.DefaultTreeCellRenderer;
/*    */ import lrg.insider.gui.ui.stories.StoryTreeRenderer;
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
/*    */ public class StoryTreeRenderer
/*    */   extends DefaultTreeCellRenderer
/*    */ {
/*    */   public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
/* 26 */     super.getTreeCellRendererComponent(
/* 27 */         tree, value, sel, 
/* 28 */         expanded, leaf, row, 
/* 29 */         hasFocus);
/*    */     
/* 31 */     String nodeName = getText();
/*    */ 
/*    */     
/* 34 */     setToolTipText(nodeName);
/*    */     int pos;
/* 36 */     if (nodeName != null && (pos = nodeName.indexOf(" filter on (")) != -1) {
/* 37 */       setText(nodeName.substring(0, pos + 7));
/*    */     }
/*    */ 
/*    */     
/* 41 */     return this;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\gu\\ui\stories\StoryTreeRenderer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */