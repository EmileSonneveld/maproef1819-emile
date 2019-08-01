/*    */ package classes.lrg.insider.plugins.core.filters.memoria;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ 
/*    */ public class ModelTypeFilter
/*    */   extends FilteringRule {
/*  9 */   public ModelTypeFilter() { super(new Descriptor("model type", "type")); }
/*    */ 
/*    */ 
/*    */   
/* 13 */   public boolean applyFilter(AbstractEntityInterface anEntity) { return anEntity instanceof lrg.memoria.core.Class; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\filters\memoria\ModelTypeFilter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */