/*    */ package classes.lrg.insider.plugins.filters.memoria.variables;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IsParameter
/*    */   extends FilteringRule
/*    */ {
/* 16 */   public IsParameter() { super(new Descriptor("is parameter", new String[] { "global variable", "attribute", "local variable", "parameter" })); }
/*    */ 
/*    */ 
/*    */   
/* 20 */   public boolean applyFilter(AbstractEntityInterface anEntity) { return anEntity instanceof lrg.memoria.core.Parameter; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\variables\IsParameter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */