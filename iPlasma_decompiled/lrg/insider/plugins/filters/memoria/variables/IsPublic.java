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
/*    */ public class IsPublic
/*    */   extends FilteringRule
/*    */ {
/* 16 */   public IsPublic() { super(new Descriptor("not encapsulated", "attribute")); }
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface anEntity) {
/* 20 */     if (!(anEntity instanceof Attribute)) return false;
/*    */     
/* 22 */     if (!((Attribute)anEntity).isPublic() && 
/* 23 */       !((Attribute)anEntity).isPackage()) return false; 
/*    */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\variables\IsPublic.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */