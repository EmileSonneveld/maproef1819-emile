/*    */ package classes.lrg.insider.gui.ui.views.sort;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Comparator;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.insider.gui.ui.views.sort.ResultEntityComparator;
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
/*    */ class ResultEntityComparator
/*    */   implements Comparator
/*    */ {
/*    */   private ArrayList unsortedColumnValues;
/*    */   
/* 27 */   public ResultEntityComparator(ArrayList columnValues) { this.unsortedColumnValues = columnValues; }
/*    */ 
/*    */   
/*    */   public int compare(Object o, Object o1) {
/* 31 */     int i = ((Integer)o).intValue();
/* 32 */     int j = ((Integer)o1).intValue();
/*    */     
/* 34 */     ResultEntity ri = (ResultEntity)this.unsortedColumnValues.get(i);
/* 35 */     ResultEntity rj = (ResultEntity)this.unsortedColumnValues.get(j);
/*    */     
/* 37 */     if (ri == null) return 1; 
/* 38 */     return -ri.compareTo(rj);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\gu\\ui\views\sort\ResultEntityComparator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */