/*    */ package lrg.dude.duplication;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.TreeSet;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class VirtualMatrix
/*    */ {
/* 16 */   private ArrayList list = new ArrayList(); public VirtualMatrix(int size) {
/* 17 */     for (int i = 0; i < size; i++) {
/* 18 */       this.list.add(new HashMap());
/*    */     }
/*    */   }
/*    */   
/*    */   public Boolean get(int x, int y) {
/* 23 */     HashMap map = (HashMap)this.list.get(x);
/* 24 */     return (Boolean)map.get(new Integer(y));
/*    */   }
/*    */   
/*    */   public void set(int x, int y, Boolean element) {
/* 28 */     HashMap map = (HashMap)this.list.get(x);
/* 29 */     map.put(new Integer(y), element);
/*    */   }
/*    */   
/*    */   public Iterator iterator(int i) {
/* 33 */     HashMap map = (HashMap)this.list.get(i);
/* 34 */     TreeSet set = new TreeSet(map.keySet());
/* 35 */     return set.iterator();
/*    */   }
/*    */   
/*    */   public void freeLines(int start, int end) {
/* 39 */     for (int i = start; i < end; i++)
/* 40 */       ((HashMap)this.list.get(i)).clear(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\dude.jar!\lrg\dude\duplication\VirtualMatrix.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */