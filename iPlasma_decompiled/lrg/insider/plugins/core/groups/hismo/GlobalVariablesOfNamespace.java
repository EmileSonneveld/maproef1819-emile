/*    */ package classes.lrg.insider.plugins.core.groups.hismo;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.memoria.hismo.core.NamespaceHistory;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GlobalVariablesOfNamespace
/*    */   extends GroupBuilder
/*    */ {
/* 13 */   public GlobalVariablesOfNamespace() { super("global variables group", "", "namespace history"); }
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface anEntity) {
/* 18 */     if (!(anEntity instanceof NamespaceHistory)) {
/* 19 */       return null;
/*    */     }
/* 21 */     return ((NamespaceHistory)anEntity).getGlobalVariableHistories().getHistoriesArrayList();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\groups\hismo\GlobalVariablesOfNamespace.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */