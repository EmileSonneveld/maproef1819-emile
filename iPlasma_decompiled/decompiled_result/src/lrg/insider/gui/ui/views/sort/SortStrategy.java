/*    */ package classes.lrg.insider.gui.ui.views.sort;
/*    */ 
/*    */ import java.util.ArrayList;
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
/*    */ public abstract class SortStrategy
/*    */ {
/*    */   public abstract void sort(ArrayList paramArrayList, int[] paramArrayOfInt, boolean paramBoolean);
/*    */   
/*    */   protected void swap(int i, int j) {
/* 20 */     int temp = i;
/* 21 */     i = j;
/* 22 */     j = temp;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\gu\\ui\views\sort\SortStrategy.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */