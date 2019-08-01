/*    */ package classes.lrg.insider.plugins.core.filters.hismo;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.memoria.hismo.core.AbstractHistory;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelHistoriesFilter
/*    */   extends FilteringRule
/*    */ {
/* 13 */   public ModelHistoriesFilter() { super(new Descriptor("model histories", "", new String[] { "class history", "method history", "attribute history", "global function history", "global variable history", "namespace history", "package history" })); }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface anEntity) {
/* 18 */     if (!(anEntity instanceof AbstractHistory)) return false;
/*    */     
/* 20 */     return !((AbstractHistory)anEntity).isLibrary();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\filters\hismo\ModelHistoriesFilter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */