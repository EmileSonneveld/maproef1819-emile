/*    */ package classes.lrg.insider.plugins.core.filters.memoria;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.memoria.core.Function;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelFunctionFilter
/*    */   extends FilteringRule
/*    */ {
/* 13 */   public ModelFunctionFilter() { super(new Descriptor("model function", "", new String[] { "method", "global function" })); }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface anEntity) {
/* 18 */     if (!(anEntity instanceof Function)) return false;
/*    */     
/* 20 */     return (((Function)anEntity).getStatute() == 1);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\filters\memoria\ModelFunctionFilter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */