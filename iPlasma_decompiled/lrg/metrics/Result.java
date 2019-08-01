/*    */ package lrg.metrics;
/*    */ 
/*    */ import java.util.SortedSet;
/*    */ import java.util.TreeSet;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class Result
/*    */ {
/*    */   public void add(Result res) {}
/*    */   
/* 13 */   public void saveResults() { System.out.println("ERROR: only instances of \"CollectionResult\" can be saved"); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 18 */   protected SortedSet getResultsSet() { return new TreeSet(); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\metrics.jar!\lrg\metrics\Result.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */