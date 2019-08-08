/*    */ package lrg.dude.duplication;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LevenshteinDistanceStrategy
/*    */   implements StringCompareStrategy
/*    */ {
/*    */   private double threshold;
/*    */   
/* 14 */   public LevenshteinDistanceStrategy(double threshold) { this.threshold = threshold; }
/*    */ 
/*    */   
/*    */   public boolean similar(String source, String target) {
/* 18 */     if (normalizedDistance(source, target) <= this.threshold)
/* 19 */       return true; 
/* 20 */     return false;
/*    */   }
/*    */ 
/*    */   
/* 24 */   private double normalizedDistance(String source, String target) { return levenshteinDistance(source, target) / Math.max(source.length(), target.length()); }
/*    */ 
/*    */   
/*    */   private int levenshteinDistance(String source, String target) {
/* 28 */     int m = source.length();
/* 29 */     int n = target.length();
/* 30 */     int[][] T = new int[m + 1][n + 1];
/*    */     
/* 32 */     T[0][0] = 0;
/* 33 */     for (int j = 0; j < n; j++) {
/* 34 */       T[0][j + 1] = T[0][j] + ins(target, j);
/*    */     }
/* 36 */     for (int i = 0; i < m; i++) {
/* 37 */       T[i + 1][0] = T[i][0] + del(source, i);
/* 38 */       for (int j = 0; j < n; j++) {
/* 39 */         T[i + 1][j + 1] = 
/*    */           
/* 41 */           min(T[i][j] + sub(source, i, target, j), T[i][j + 1] + del(source, i), T[i + 1][j] + ins(target, j));
/*    */       }
/*    */     } 
/* 44 */     return T[m - 1][n - 1];
/*    */   }
/*    */ 
/*    */   
/* 48 */   private int sub(String x, int xi, String y, int yi) { return (x.charAt(xi) == y.charAt(yi)) ? 0 : 1; }
/*    */ 
/*    */ 
/*    */   
/* 52 */   private int ins(String x, int xi) { return 1; }
/*    */ 
/*    */ 
/*    */   
/* 56 */   private int del(String x, int xi) { return 1; }
/*    */ 
/*    */ 
/*    */   
/* 60 */   private int min(int a, int b, int c) { return Math.min(Math.min(a, b), c); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\dude.jar!\lrg\dude\duplication\LevenshteinDistanceStrategy.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */