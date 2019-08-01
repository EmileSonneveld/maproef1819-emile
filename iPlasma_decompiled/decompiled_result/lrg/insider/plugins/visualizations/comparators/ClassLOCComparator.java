/*    */ package classes.lrg.insider.plugins.visualizations.comparators;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.Comparator;
/*    */ import lrg.common.abstractions.entities.AbstractEntity;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.insider.plugins.visualizations.comparators.ClassLOCComparator;
/*    */ import lrg.insider.plugins.visualizations.comparators.MethodLOCComparator;
/*    */ 
/*    */ public class ClassLOCComparator implements Comparator {
/*    */   private GroupEntity interestingMethods;
/*    */   
/* 13 */   public ClassLOCComparator(GroupEntity methods) { this.interestingMethods = methods; }
/*    */ 
/*    */   
/*    */   public int compare(Object o1, Object o2) {
/* 17 */     AbstractEntity minElem1 = null;
/* 18 */     AbstractEntity minElem2 = null;
/*    */ 
/*    */     
/* 21 */     GroupEntity methods1 = ((AbstractEntity)o1).getGroup("method group").intersect(this.interestingMethods);
/* 22 */     GroupEntity methods2 = ((AbstractEntity)o2).getGroup("method group").intersect(this.interestingMethods);
/*    */     
/* 24 */     if (methods1.size() > 0) minElem1 = (AbstractEntity)Collections.min(methods1.getElements(), new MethodLOCComparator()); 
/* 25 */     if (methods2.size() > 0) minElem2 = (AbstractEntity)Collections.min(methods2.getElements(), new MethodLOCComparator());
/*    */ 
/*    */     
/* 28 */     Double value1 = Double.valueOf((minElem1 != null) ? ((Double)minElem1.getProperty("LOC").getValue()).doubleValue() : 0.0D);
/* 29 */     Double value2 = Double.valueOf((minElem2 != null) ? ((Double)minElem2.getProperty("LOC").getValue()).doubleValue() : 0.0D);
/*    */     
/* 31 */     return (int)(value2.doubleValue() - value1.doubleValue());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\visualizations\comparators\ClassLOCComparator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */