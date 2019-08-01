/*    */ package classes.lrg.insider.plugins.core.groups.memoria.uses;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.memoria.core.DataAbstraction;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClassUsedByDerived
/*    */   extends GroupBuilder
/*    */ {
/* 18 */   public ClassUsedByDerived() { super("derived classes", "", "class"); }
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface anEntity) {
/* 22 */     ArrayList resultList = new ArrayList();
/* 23 */     if (!(anEntity instanceof lrg.memoria.core.Class)) {
/* 24 */       return resultList;
/*    */     }
/* 26 */     return ((DataAbstraction)anEntity).getDescendants();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\groups\memori\\uses\ClassUsedByDerived.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */