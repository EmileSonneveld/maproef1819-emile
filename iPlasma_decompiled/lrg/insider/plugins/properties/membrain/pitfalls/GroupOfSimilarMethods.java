/*    */ package classes.lrg.insider.plugins.properties.membrain.pitfalls;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.insider.plugins.properties.membrain.pitfalls.GroupOfSimilarMethods;
/*    */ import lrg.memoria.core.Method;
/*    */ 
/*    */ 
/*    */ public class GroupOfSimilarMethods
/*    */   extends GroupBuilder
/*    */ {
/* 14 */   public GroupOfSimilarMethods() { super("similar methods", "similar methods", "method"); }
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface anEntity) {
/* 18 */     ArrayList result = new ArrayList();
/* 19 */     if (((Method)anEntity).isConstructor() || ((Method)anEntity).isStatic()) return result; 
/* 20 */     GroupEntity allMethods = anEntity.belongsTo("class").getGroup("method group");
/* 21 */     for (Method aMethod : allMethods.getElements()) {
/* 22 */       if (aMethod != anEntity && 
/* 23 */         !aMethod.isStatic() && !aMethod.isConstructor() && 
/* 24 */         normalizedDistance(aMethod.getName(), anEntity.getName()) >= 0.75D) result.add(aMethod); 
/*    */     } 
/* 26 */     return result;
/*    */   }
/*    */ 
/*    */   
/* 30 */   private static double normalizedDistance(String source, String target) { return 1.0D - levenshteinDistance(source, target) / Math.max(source.length(), target.length()); }
/*    */ 
/*    */   
/*    */   private static int levenshteinDistance(String source, String target) {
/* 34 */     int m = source.length();
/* 35 */     int n = target.length();
/* 36 */     int[][] T = new int[m + 1][n + 1];
/*    */     
/* 38 */     T[0][0] = 0;
/* 39 */     for (int j = 0; j < n; j++) {
/* 40 */       T[0][j + 1] = T[0][j] + ins(target, j);
/*    */     }
/* 42 */     for (int i = 0; i < m; i++) {
/* 43 */       T[i + 1][0] = T[i][0] + del(source, i);
/* 44 */       for (int j = 0; j < n; j++) {
/* 45 */         T[i + 1][j + 1] = 
/*    */           
/* 47 */           min(T[i][j] + sub(source, i, target, j), T[i][j + 1] + del(source, i), T[i + 1][j] + ins(target, j));
/*    */       }
/*    */     } 
/* 50 */     return T[m - 1][n - 1];
/*    */   }
/*    */ 
/*    */   
/* 54 */   private static int sub(String x, int xi, String y, int yi) { return (x.charAt(xi) == y.charAt(yi)) ? 0 : 1; }
/*    */ 
/*    */ 
/*    */   
/* 58 */   private static int ins(String x, int xi) { return 1; }
/*    */ 
/*    */ 
/*    */   
/* 62 */   private static int del(String x, int xi) { return 1; }
/*    */ 
/*    */ 
/*    */   
/* 66 */   private static int min(int a, int b, int c) { return Math.min(Math.min(a, b), c); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\membrain\pitfalls\GroupOfSimilarMethods.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */