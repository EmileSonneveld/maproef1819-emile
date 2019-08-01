/*    */ package classes.lrg.insider.plugins.filters.memoria.methods;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.memoria.core.Method;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IsStatic
/*    */   extends FilteringRule
/*    */ {
/* 16 */   public IsStatic() { super(new Descriptor("is static", "method")); }
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface anEntity) {
/* 20 */     if (!(anEntity instanceof Method)) return false;
/*    */     
/* 22 */     return ((Method)anEntity).isStatic();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\methods\IsStatic.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */