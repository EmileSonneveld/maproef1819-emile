/*    */ package classes.lrg.insider.plugins.core.filters.memoria;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.memoria.core.Type;
/*    */ 
/*    */ 
/*    */ public class InnerTypesFilter
/*    */   extends FilteringRule
/*    */ {
/* 12 */   public InnerTypesFilter() { super(new Descriptor("inner types", "type")); }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface anEntity) {
/* 17 */     if (!(anEntity instanceof Type)) return false;
/*    */     
/* 19 */     return !(((Type)anEntity).getScope() instanceof lrg.memoria.core.Namespace);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\filters\memoria\InnerTypesFilter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */