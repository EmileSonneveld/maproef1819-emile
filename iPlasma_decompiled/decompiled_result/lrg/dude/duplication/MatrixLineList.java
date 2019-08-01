/*    */ package lrg.dude.duplication;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MatrixLineList
/*    */ {
/* 15 */   private List list = new ArrayList();
/*    */ 
/*    */   
/* 18 */   public void add(MatrixLine ml) { this.list.add(ml); }
/*    */ 
/*    */   
/*    */   public void addAll(MatrixLineList aList) {
/* 22 */     int size = aList.size();
/* 23 */     for (int i = 0; i < size; i++) {
/* 24 */       this.list.add(aList.get(i));
/*    */     }
/*    */   }
/*    */   
/* 28 */   public MatrixLine get(int index) { return (MatrixLine)this.list.get(index); }
/*    */ 
/*    */ 
/*    */   
/* 32 */   public int size() { return this.list.size(); }
/*    */ 
/*    */   
/*    */   public long countDuplicatedLines() {
/* 36 */     long counter = 0L;
/* 37 */     for (int i = 0; i < this.list.size(); i++) {
/* 38 */       if (!get(i).isUnique())
/* 39 */         counter++; 
/* 40 */     }  return counter;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\dude.jar!\lrg\dude\duplication\MatrixLineList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */