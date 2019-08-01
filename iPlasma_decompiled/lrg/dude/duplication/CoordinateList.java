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
/*    */ public class CoordinateList
/*    */ {
/* 14 */   private List list = new ArrayList();
/*    */ 
/*    */   
/* 17 */   public void add(Coordinate c) { this.list.add(c); }
/*    */ 
/*    */ 
/*    */   
/* 21 */   public Coordinate get(int index) { return (Coordinate)this.list.get(index); }
/*    */ 
/*    */ 
/*    */   
/* 25 */   public int size() { return this.list.size(); }
/*    */ 
/*    */ 
/*    */   
/* 29 */   public void remove(int index) { this.list.remove(index); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\dude.jar!\lrg\dude\duplication\CoordinateList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */