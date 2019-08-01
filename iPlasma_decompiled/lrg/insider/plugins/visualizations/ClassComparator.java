/*    */ package classes.lrg.insider.plugins.visualizations;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
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
/*    */ class ClassComparator
/*    */   implements Comparator
/*    */ {
/*    */   public int compare(Object o1, Object o2) {
/* 34 */     AbstractEntityInterface e1 = (AbstractEntityInterface)o1;
/* 35 */     AbstractEntityInterface e2 = (AbstractEntityInterface)o2;
/*    */     
/* 37 */     double e1wmc = ((Double)e1.getProperty("WMC").getValue()).doubleValue() + 3.0D;
/* 38 */     double e2wmc = ((Double)e2.getProperty("WMC").getValue()).doubleValue() + 3.0D;
/*    */     
/* 40 */     return (int)(e2wmc - e1wmc);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\visualizations\ClassComparator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */