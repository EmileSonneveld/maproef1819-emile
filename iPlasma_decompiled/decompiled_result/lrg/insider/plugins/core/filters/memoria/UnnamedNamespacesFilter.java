/*    */ package classes.lrg.insider.plugins.core.filters.memoria;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ 
/*    */ 
/*    */ public class UnnamedNamespacesFilter
/*    */   extends FilteringRule
/*    */ {
/* 11 */   public UnnamedNamespacesFilter() { super(new Descriptor("unnamed namespaces", "namespace")); }
/*    */ 
/*    */ 
/*    */   
/* 15 */   public boolean applyFilter(AbstractEntityInterface anEntity) { return anEntity instanceof lrg.memoria.core.UnnamedNamespace; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\filters\memoria\UnnamedNamespacesFilter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */