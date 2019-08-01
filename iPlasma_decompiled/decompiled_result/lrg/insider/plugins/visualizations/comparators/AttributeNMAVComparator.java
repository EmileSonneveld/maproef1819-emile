/*    */ package classes.lrg.insider.plugins.visualizations.comparators;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import lrg.common.abstractions.entities.AbstractEntity;
/*    */ 
/*    */ public class AttributeNMAVComparator
/*    */   implements Comparator {
/*    */   public int compare(Object o1, Object o2) {
/*  9 */     Double value1 = (Double)((AbstractEntity)o1).getProperty("NMAV").getValue();
/* 10 */     Double value2 = (Double)((AbstractEntity)o2).getProperty("NMAV").getValue();
/* 11 */     return (int)(value2.doubleValue() - value1.doubleValue());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\visualizations\comparators\AttributeNMAVComparator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */