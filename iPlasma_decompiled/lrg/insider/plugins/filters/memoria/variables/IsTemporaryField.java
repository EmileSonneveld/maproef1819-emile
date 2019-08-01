/*    */ package classes.lrg.insider.plugins.filters.memoria.variables;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.common.abstractions.plugins.filters.composed.AndComposedFilteringRule;
/*    */ import lrg.common.abstractions.plugins.filters.composed.NotComposedFilteringRule;
/*    */ import lrg.insider.plugins.core.filters.memoria.ModelVariableFilter;
/*    */ import lrg.insider.plugins.filters.memoria.variables.IsStatic;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IsTemporaryField
/*    */   extends FilteringRule
/*    */ {
/* 19 */   public IsTemporaryField() { super(new Descriptor("temporary field", new String[] { "global variable", "attribute", "local variable", "parameter" })); }
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface anEntity) {
/* 23 */     if (!(anEntity instanceof lrg.memoria.core.Attribute)) return false; 
/* 24 */     if (!(new ModelVariableFilter()).applyFilter(anEntity)) return false;
/*    */     
/* 26 */     FilteringRule isTemporaryField = new FilteringRule("NMAV", "==", "attribute", 1.0D);
/* 27 */     NotComposedFilteringRule notComposedFilteringRule = new NotComposedFilteringRule(new IsStatic());
/*    */     
/* 29 */     return (new AndComposedFilteringRule(isTemporaryField, notComposedFilteringRule)).applyFilter(anEntity);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\variables\IsTemporaryField.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */