/*    */ package classes.lrg.insider.plugins.filters.memoria.variables;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.memoria.core.Attribute;
/*    */ import lrg.memoria.core.GlobalVariable;
/*    */ import lrg.memoria.core.LocalVariable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IsStatic
/*    */   extends FilteringRule
/*    */ {
/* 17 */   public IsStatic() { super(new Descriptor("is static", "attribute")); }
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface anEntity) {
/* 21 */     if (anEntity instanceof Attribute) return ((Attribute)anEntity).isStatic();
/*    */     
/* 23 */     if (anEntity instanceof GlobalVariable) return ((GlobalVariable)anEntity).isStatic(); 
/* 24 */     if (anEntity instanceof LocalVariable) {
/* 25 */       return ((LocalVariable)anEntity).isStatic();
/*    */     }
/*    */     
/* 28 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\variables\IsStatic.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */