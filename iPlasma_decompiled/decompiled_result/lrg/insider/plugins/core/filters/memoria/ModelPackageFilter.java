/*    */ package classes.lrg.insider.plugins.core.filters.memoria;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.memoria.core.Package;
/*    */ 
/*    */ 
/*    */ public class ModelPackageFilter
/*    */   extends FilteringRule
/*    */ {
/* 12 */   public ModelPackageFilter() { super(new Descriptor("model package", "package")); }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface anEntity) {
/* 17 */     if (!(anEntity instanceof Package)) return false;
/*    */     
/* 19 */     return (((Package)anEntity).getStatute() == 1);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\filters\memoria\ModelPackageFilter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */