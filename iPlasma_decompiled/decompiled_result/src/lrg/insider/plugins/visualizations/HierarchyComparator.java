/*    */ package classes.lrg.insider.plugins.visualizations;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
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
/*    */ class HierarchyComparator
/*    */   implements Comparator
/*    */ {
/* 46 */   public int compare(Object o1, Object o2) { return ((GroupEntity)o1).size() - ((GroupEntity)o2).size(); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\visualizations\HierarchyComparator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */