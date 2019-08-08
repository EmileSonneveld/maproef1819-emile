/*    */ package classes.lrg.insider.plugins.visualizations.comparators;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import lrg.common.abstractions.entities.AbstractEntity;
/*    */ import lrg.insider.plugins.filters.memoria.methods.IsAccessor;
/*    */ 
/*    */ public class MethodLOCComparator
/*    */   implements Comparator {
/*    */   public int compare(Object o1, Object o2) {
/* 10 */     AbstractEntity meth1 = (AbstractEntity)o1;
/* 11 */     AbstractEntity meth2 = (AbstractEntity)o2;
/* 12 */     Double value1 = null, value2 = null;
/*    */     
/* 14 */     if ((new IsAccessor()).applyFilter(meth1))
/* 15 */     { value1 = (Double)meth1.getProperty("CM").getValue(); }
/* 16 */     else { value1 = (Double)meth1.getProperty("LOC").getValue(); }
/*    */     
/* 18 */     if ((new IsAccessor()).applyFilter(meth2)) { value2 = (Double)meth2.getProperty("CM").getValue(); }
/* 19 */     else { value2 = (Double)meth2.getProperty("LOC").getValue(); }
/*    */     
/* 21 */     return (int)(value2.doubleValue() - value1.doubleValue());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\visualizations\comparators\MethodLOCComparator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */