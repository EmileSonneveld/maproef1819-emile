/*    */ package classes.lrg.insider.plugins.filters.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.common.abstractions.plugins.filters.composed.NotComposedFilteringRule;
/*    */ import lrg.insider.plugins.filters.memoria.classes.IsInterface;
/*    */ import lrg.memoria.core.DataAbstraction;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IsRootClass
/*    */   extends FilteringRule
/*    */ {
/* 20 */   public IsRootClass() { super(new Descriptor("is root-class", "class")); }
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface anEntity) {
/* 24 */     if (!(anEntity instanceof lrg.memoria.core.Class)) return false;
/*    */     
/* 26 */     String address = (String)anEntity.getProperty("Address").getValue();
/* 27 */     if (address.indexOf("$") >= 0) return false;
/*    */     
/* 29 */     NotComposedFilteringRule notComposedFilteringRule = new NotComposedFilteringRule(new IsInterface());
/*    */     
/* 31 */     if (((DataAbstraction)anEntity).isInterface()) return false; 
/* 32 */     if (((DataAbstraction)anEntity).getStatute() != 1) return false;
/*    */     
/* 34 */     GroupEntity classAncestors = anEntity.uses("base classes");
/* 35 */     GroupEntity modelClassAncestors = classAncestors.applyFilter("model class").applyFilter(notComposedFilteringRule);
/* 36 */     if (modelClassAncestors.size() != 0) return false; 
/* 37 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\classes\IsRootClass.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */