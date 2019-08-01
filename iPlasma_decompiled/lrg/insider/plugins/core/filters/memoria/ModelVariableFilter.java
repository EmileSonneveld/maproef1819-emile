/*    */ package classes.lrg.insider.plugins.core.filters.memoria;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.memoria.core.Variable;
/*    */ 
/*    */ public class ModelVariableFilter
/*    */   extends FilteringRule {
/* 10 */   public ModelVariableFilter() { super(new Descriptor("model variable", "", new String[] { "attribute", "global variable" })); }
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface anEntity) {
/* 14 */     if (!(anEntity instanceof Variable)) return false;
/*    */     
/* 16 */     return (((Variable)anEntity).getStatute() == 1);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\filters\memoria\ModelVariableFilter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */