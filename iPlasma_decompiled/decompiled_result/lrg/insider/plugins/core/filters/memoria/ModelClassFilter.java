/*    */ package classes.lrg.insider.plugins.core.filters.memoria;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.memoria.core.DataAbstraction;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelClassFilter
/*    */   extends FilteringRule
/*    */ {
/* 13 */   public ModelClassFilter() { super(new Descriptor("model class", "class")); }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface anEntity) {
/* 18 */     if (!(anEntity instanceof lrg.memoria.core.Class)) return false;
/*    */     
/* 20 */     return (((DataAbstraction)anEntity).getStatute() == 1);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\core\filters\memoria\ModelClassFilter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */