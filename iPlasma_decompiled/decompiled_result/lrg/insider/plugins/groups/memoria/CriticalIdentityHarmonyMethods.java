/*    */ package classes.lrg.insider.plugins.groups.memoria;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.common.abstractions.plugins.filters.composed.OrComposedFilteringRule;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CriticalIdentityHarmonyMethods
/*    */   extends GroupBuilder
/*    */ {
/* 20 */   public CriticalIdentityHarmonyMethods() { super("Identity Harmony (critical methods)", "", "class"); }
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface measuredClass) {
/* 24 */     FilteringRule isUsignForeignData = new FilteringRule("ATFD", ">", "method", 0.0D);
/* 25 */     FilteringRule intraDuplication = new FilteringRule("IDUPLINES", ">", "method", 0.0D);
/* 26 */     OrComposedFilteringRule orComposedFilteringRule = new OrComposedFilteringRule(isUsignForeignData, intraDuplication);
/* 27 */     GroupEntity resultGroup = measuredClass.getGroup("method group").applyFilter("Brain Method");
/*    */     
/* 29 */     return resultGroup.union(measuredClass.getGroup("method group").applyFilter(orComposedFilteringRule)).distinct().getElements();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\memoria\CriticalIdentityHarmonyMethods.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */