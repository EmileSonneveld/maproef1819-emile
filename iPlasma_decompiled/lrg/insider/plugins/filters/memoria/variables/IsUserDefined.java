/*    */ package classes.lrg.insider.plugins.filters.memoria.variables;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.memoria.core.Variable;
/*    */ 
/*    */ public class IsUserDefined
/*    */   extends FilteringRule
/*    */ {
/* 11 */   public IsUserDefined() { super(new Descriptor("is user defined", "attribute")); }
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface anEntity) {
/* 15 */     if (!(anEntity instanceof Variable))
/* 16 */       return false; 
/* 17 */     Variable theVariable = (Variable)anEntity;
/*    */     
/* 19 */     if (!theVariable.hasPrimitiveType() && 
/* 20 */       theVariable.getStatute() == 1) return true; 
/*    */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\variables\IsUserDefined.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */