/*    */ package classes.lrg.insider.plugins.filters.memoria.methods;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ 
/*    */ public class IsOverriden
/*    */   extends FilteringRule {
/*  9 */   public IsOverriden() { super(new Descriptor("is overriding", "method")); }
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface anEntity) {
/* 13 */     if (!(anEntity instanceof lrg.memoria.core.Method)) return false;
/*    */     
/* 15 */     return (anEntity.uses("methods overriden").size() > 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\methods\IsOverriden.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */