/*    */ package classes.lrg.insider.plugins.groups.memoria;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.filters.composed.NotComposedFilteringRule;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.insider.plugins.filters.memoria.methods.IsConstructor;
/*    */ import lrg.insider.plugins.filters.memoria.methods.IsProtected;
/*    */ import lrg.insider.plugins.filters.memoria.methods.IsStatic;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ExternalServiceProviders
/*    */   extends GroupBuilder
/*    */ {
/* 23 */   public ExternalServiceProviders() { super("External Service Providers (methods)", "", "method"); }
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface aMethod) {
/* 27 */     NotComposedFilteringRule notComposedFilteringRule1 = new NotComposedFilteringRule(new IsStatic());
/* 28 */     NotComposedFilteringRule notComposedFilteringRule2 = new NotComposedFilteringRule(new IsConstructor());
/* 29 */     NotComposedFilteringRule notComposedFilteringRule3 = new NotComposedFilteringRule(new IsProtected());
/* 30 */     GroupEntity callees = aMethod.uses("operations called").distinct();
/*    */     
/* 32 */     callees = callees.applyFilter("model function").applyFilter(notComposedFilteringRule3).applyFilter(notComposedFilteringRule1).applyFilter(notComposedFilteringRule2);
/*    */     
/* 34 */     GroupEntity relatedClasses = aMethod.belongsTo("class").getGroup("all ancestors").union(aMethod.belongsTo("class"));
/*    */     
/* 36 */     return callees.exclude(relatedClasses.getGroup("method group")).getElements();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\memoria\ExternalServiceProviders.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */