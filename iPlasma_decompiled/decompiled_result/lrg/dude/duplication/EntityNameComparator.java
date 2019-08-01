/*    */ package lrg.dude.duplication;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityNameComparator
/*    */   implements Comparator
/*    */ {
/*    */   public int compare(Object o1, Object o2) {
/* 14 */     String entityName1 = ((Duplication)o1).getReferenceCode().getEntityName();
/* 15 */     String entityName2 = ((Duplication)o2).getReferenceCode().getEntityName();
/* 16 */     return entityName1.compareTo(entityName2);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\dude.jar!\lrg\dude\duplication\EntityNameComparator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */