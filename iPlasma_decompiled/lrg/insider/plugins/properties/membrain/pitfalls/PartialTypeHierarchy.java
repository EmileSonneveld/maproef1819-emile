/*    */ package classes.lrg.insider.plugins.properties.membrain.pitfalls;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ 
/*    */ 
/*    */ public class PartialTypeHierarchy
/*    */   extends FilteringRule
/*    */ {
/* 12 */   public PartialTypeHierarchy() { super(new Descriptor("Partial Type Hierarchy", "class")); }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface anEntity) {
/* 17 */     GroupEntity allMethods = anEntity.getGroup("method group");
/* 18 */     if (allMethods.applyFilter("is abstract").size() != allMethods.size()) return false; 
/* 19 */     return (((Double)anEntity.getProperty("#AVG_PartialUniform#").getValue()).doubleValue() > 0.666D);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\membrain\pitfalls\PartialTypeHierarchy.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */