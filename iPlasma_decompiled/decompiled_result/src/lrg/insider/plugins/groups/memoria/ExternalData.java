/*    */ package classes.lrg.insider.plugins.groups.memoria;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntity;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.filters.composed.NotComposedFilteringRule;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.insider.plugins.core.filters.memoria.ModelClassFilter;
/*    */ import lrg.insider.plugins.filters.memoria.variables.IsConstant;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ExternalData
/*    */   extends GroupBuilder
/*    */ {
/* 24 */   public ExternalData() { super("foreign data", "", "class"); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface measuredClass) {
/* 30 */     NotComposedFilteringRule notComposedFilteringRule = new NotComposedFilteringRule(new IsConstant());
/*    */     
/* 32 */     GroupEntity currentClassAndAncestors = measuredClass.uses("all ancestors").union((AbstractEntity)measuredClass);
/*    */     
/* 34 */     GroupEntity allAccesses = measuredClass.uses("variables accessed").applyFilter("is attribute").applyFilter(notComposedFilteringRule);
/* 35 */     allAccesses = allAccesses.union(measuredClass.uses("operations called").applyFilter("is accessor")).distinct();
/*    */     
/* 37 */     if (allAccesses.size() == 0) return new ArrayList();
/*    */     
/* 39 */     Iterator it = allAccesses.iterator();
/*    */     
/* 41 */     while (it.hasNext()) {
/* 42 */       AbstractEntity crtEntity = (AbstractEntity)it.next();
/* 43 */       AbstractEntity scope = crtEntity.belongsTo("class");
/* 44 */       if (!(new ModelClassFilter()).applyFilter(scope)) {
/* 45 */         allAccesses = allAccesses.exclude(crtEntity); continue;
/* 46 */       }  if (currentClassAndAncestors.isInGroup(scope)) allAccesses = allAccesses.exclude(crtEntity);
/*    */     
/*    */     } 
/* 49 */     return allAccesses.getElements();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\memoria\ExternalData.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */