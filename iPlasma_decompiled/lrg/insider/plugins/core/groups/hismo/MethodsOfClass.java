/*    */ package classes.lrg.insider.plugins.core.groups.hismo;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.memoria.hismo.core.ClassHistory;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MethodsOfClass
/*    */   extends GroupBuilder
/*    */ {
/* 13 */   public MethodsOfClass() { super("method group", "", "class history"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface anEntity) {
/* 18 */     if (!(anEntity instanceof ClassHistory)) {
/* 19 */       return null;
/*    */     }
/* 21 */     return ((ClassHistory)anEntity).getMethodHistories().getHistoriesArrayList();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\groups\hismo\MethodsOfClass.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */