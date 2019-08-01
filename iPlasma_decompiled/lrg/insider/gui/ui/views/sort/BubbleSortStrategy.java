/*    */ package classes.lrg.insider.gui.ui.views.sort;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.insider.gui.ui.views.sort.SortStrategy;
/*    */ 
/*    */ public class BubbleSortStrategy
/*    */   extends SortStrategy
/*    */ {
/*    */   public void sort(ArrayList unsortedColumnValues, int[] indexMap, boolean ascending) {
/* 11 */     for (int i = 0; i < unsortedColumnValues.size(); i++) {
/*    */       
/* 13 */       for (int j = 0; j < unsortedColumnValues.size(); j++) {
/*    */         
/* 15 */         ResultEntity ri = (ResultEntity)unsortedColumnValues.get(indexMap[i]);
/* 16 */         ResultEntity rj = (ResultEntity)unsortedColumnValues.get(indexMap[j]);
/*    */         
/* 18 */         if (ascending) {
/*    */           
/* 20 */           if (ri.compareTo(rj) > 0)
/*    */           {
/* 22 */             int temp = indexMap[i];
/* 23 */             indexMap[i] = indexMap[j];
/* 24 */             indexMap[j] = temp;
/*    */           
/*    */           }
/*    */         
/*    */         }
/* 29 */         else if (ri.compareTo(rj) < 0) {
/*    */           
/* 31 */           int temp = indexMap[i];
/* 32 */           indexMap[i] = indexMap[j];
/* 33 */           indexMap[j] = temp;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\gu\\ui\views\sort\BubbleSortStrategy.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */