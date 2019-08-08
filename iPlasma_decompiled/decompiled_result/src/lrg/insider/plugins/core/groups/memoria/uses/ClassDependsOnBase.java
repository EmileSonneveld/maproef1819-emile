/*    */ package classes.lrg.insider.plugins.core.groups.memoria.uses;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.memoria.core.DataAbstraction;
/*    */ import lrg.memoria.core.ModelElementList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClassDependsOnBase
/*    */   extends GroupBuilder
/*    */ {
/* 18 */   public ClassDependsOnBase() { super("base classes", "", "class"); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface anEntity) {
/* 25 */     ArrayList resultList = new ArrayList();
/* 26 */     if (!(anEntity instanceof lrg.memoria.core.Class)) {
/* 27 */       return resultList;
/*    */     }
/* 29 */     ModelElementList modelElementList = ((DataAbstraction)anEntity).getAncestorsList();
/* 30 */     modelElementList.remove(anEntity);
/* 31 */     return modelElementList;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\groups\memori\\uses\ClassDependsOnBase.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */