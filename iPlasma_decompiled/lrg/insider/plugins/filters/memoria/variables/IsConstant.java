/*    */ package classes.lrg.insider.plugins.filters.memoria.variables;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.memoria.core.Attribute;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IsConstant
/*    */   extends FilteringRule
/*    */ {
/* 17 */   public IsConstant() { super(new Descriptor("is constant", "attribute")); }
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface anEntity) {
/* 21 */     if (!(anEntity instanceof Attribute)) return false;
/*    */     
/* 23 */     return (((Attribute)anEntity).isStatic() && ((Attribute)anEntity).isFinal());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\variables\IsConstant.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */