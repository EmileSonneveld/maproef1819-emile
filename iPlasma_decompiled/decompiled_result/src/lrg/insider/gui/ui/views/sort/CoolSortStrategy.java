/*    */ package classes.lrg.insider.gui.ui.views.sort;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import lrg.insider.gui.ui.views.sort.ResultEntityComparator;
/*    */ import lrg.insider.gui.ui.views.sort.SortStrategy;
/*    */ 
/*    */ public class CoolSortStrategy
/*    */   extends SortStrategy {
/*    */   public void sort(ArrayList unsortedColumnValues, int[] indexMap, boolean ascending) {
/* 11 */     ArrayList indexList = new ArrayList();
/* 12 */     for (int i = 0; i < indexMap.length; ) { indexList.add(new Integer(indexMap[i])); i++; }
/* 13 */      Collections.sort(indexList, new ResultEntityComparator(unsortedColumnValues));
/* 14 */     if (!ascending) Collections.reverse(indexList);
/*    */     
/* 16 */     for (int i = 0; i < indexList.size(); i++)
/* 17 */       indexMap[i] = ((Integer)indexList.get(i)).intValue(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\gu\\ui\views\sort\CoolSortStrategy.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */