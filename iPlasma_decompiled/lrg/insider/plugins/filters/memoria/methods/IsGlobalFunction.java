/*    */ package classes.lrg.insider.plugins.filters.memoria.methods;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ 
/*    */ public class IsGlobalFunction
/*    */   extends FilteringRule
/*    */ {
/* 10 */   public IsGlobalFunction() { super(new Descriptor("is global", new String[] { "method", "global function" })); }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface anEntity) {
/* 15 */     if (anEntity instanceof lrg.memoria.core.GlobalFunction) return true; 
/* 16 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\methods\IsGlobalFunction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */