/*    */ package classes.lrg.insider.plugins.filters.memoria.methods;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.memoria.core.Method;
/*    */ 
/*    */ public class IsPrivate
/*    */   extends FilteringRule {
/* 10 */   public IsPrivate() { super(new Descriptor("is private", "method")); }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface anEntity) {
/* 15 */     if (!(anEntity instanceof Method)) return false; 
/* 16 */     return ((Method)anEntity).isPrivate();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\methods\IsPrivate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */