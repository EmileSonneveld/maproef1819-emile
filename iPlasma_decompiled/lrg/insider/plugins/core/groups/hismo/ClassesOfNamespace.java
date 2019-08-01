/*    */ package classes.lrg.insider.plugins.core.groups.hismo;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.memoria.hismo.core.NamespaceHistory;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClassesOfNamespace
/*    */   extends GroupBuilder
/*    */ {
/* 13 */   public ClassesOfNamespace() { super("class history group", "", "namespace history"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface anEntity) {
/* 18 */     if (!(anEntity instanceof NamespaceHistory)) {
/* 19 */       return new ArrayList();
/*    */     }
/* 21 */     return ((NamespaceHistory)anEntity).getClassHistories().getHistoriesArrayList();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\groups\hismo\ClassesOfNamespace.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */