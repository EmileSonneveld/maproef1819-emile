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
/*    */ public class IsProtected
/*    */   extends FilteringRule
/*    */ {
/* 16 */   public IsProtected() { super(new Descriptor("is protected", "attribute")); }
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface anEntity) {
/* 20 */     if (!(anEntity instanceof Attribute)) return false;
/*    */     
/* 22 */     return ((Attribute)anEntity).isProtected();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\variables\IsProtected.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */