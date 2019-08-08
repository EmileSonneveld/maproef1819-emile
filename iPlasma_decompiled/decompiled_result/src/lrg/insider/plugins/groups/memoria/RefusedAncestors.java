/*    */ package classes.lrg.insider.plugins.groups.memoria;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.filters.composed.NotComposedFilteringRule;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ import lrg.insider.plugins.filters.memoria.classes.IsInterface;
/*    */ import lrg.insider.plugins.filters.memoria.methods.IsConstructor;
/*    */ import lrg.insider.plugins.groups.memoria.RefusedAncestors;
/*    */ 
/*    */ public class RefusedAncestors
/*    */   extends GroupBuilder {
/* 15 */   public RefusedAncestors() { super("refused ancestors", "", "class"); }
/*    */ 
/*    */   
/*    */   private double usageRatio(GroupEntity usedprotectedMethods, GroupEntity usedprotectedAttributes, AbstractEntityInterface ancestorClass) {
/* 19 */     GroupEntity protectedMethods = ancestorClass.contains("method group").applyFilter("is protected");
/* 20 */     protectedMethods.applyFilter(new NotComposedFilteringRule(new IsConstructor()));
/* 21 */     GroupEntity protectedAttributes = ancestorClass.contains("attribute group").applyFilter("is protected");
/*    */     
/* 23 */     double totalProtected = (protectedAttributes.size() + protectedMethods.size());
/* 24 */     if (totalProtected < 3.0D) return 1.0D;
/*    */     
/* 26 */     double usedProtected = (protectedAttributes.intersect(usedprotectedAttributes).size() + 
/* 27 */       protectedMethods.intersect(usedprotectedMethods).size());
/*    */     
/* 29 */     return usedProtected / totalProtected;
/*    */   }
/*    */ 
/*    */   
/* 33 */   private double countOverridenMethods(GroupEntity overridenMethods, AbstractEntityInterface ancestorClass) { return overridenMethods.intersect(ancestorClass.contains("method group")).size(); }
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface aClass) {
/* 37 */     ArrayList resultList = new ArrayList();
/* 38 */     GroupEntity usedprotectedMethods = aClass.uses("operations called").distinct().applyFilter("is protected");
/* 39 */     GroupEntity usedprotectedAttributes = aClass.uses("variables accessed").distinct().applyFilter("is protected");
/* 40 */     GroupEntity overridenMethods = aClass.uses("methods overriden");
/*    */     
/* 42 */     GroupEntity allAncestors = aClass.uses("all ancestors").applyFilter("model class");
/* 43 */     allAncestors.applyFilter(new NotComposedFilteringRule(new IsInterface()));
/* 44 */     if (allAncestors.size() == 0) return resultList;
/*    */     
/* 46 */     for (Iterator it = allAncestors.iterator(); it.hasNext(); ) {
/* 47 */       AbstractEntityInterface crtAncestor = (AbstractEntityInterface)it.next();
/* 48 */       double ur = usageRatio(usedprotectedMethods, usedprotectedAttributes, crtAncestor);
/* 49 */       double ovr = countOverridenMethods(overridenMethods, crtAncestor);
/* 50 */       if (ur < 0.33D && ovr < 1.0D) resultList.add(crtAncestor); 
/*    */     } 
/* 52 */     return resultList;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\memoria\RefusedAncestors.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */