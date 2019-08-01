/*    */ package classes.lrg.insider.plugins.filters.memoria.methods;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntity;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.insider.plugins.filters.memoria.methods.IsPublicMethod;
/*    */ import lrg.insider.plugins.filters.memoria.methods.ShouldBePrivate;
/*    */ 
/*    */ public class ShouldBeProtected extends FilteringRule {
/* 12 */   public ShouldBeProtected() { super(new Descriptor("should be protected", "method")); }
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface aMethod) {
/* 16 */     if (!(new IsPublicMethod()).applyFilter(aMethod))
/* 17 */       return false; 
/* 18 */     if ((new ShouldBePrivate()).applyFilter(aMethod)) {
/* 19 */       return false;
/*    */     }
/* 21 */     AbstractEntity containerClass = aMethod.belongsTo("class");
/*    */     
/* 23 */     GroupEntity methodsFromMyClass = containerClass.contains("method group");
/* 24 */     GroupEntity methodsFromMyDescendants = containerClass.getGroup("all descendants").contains("method group");
/* 25 */     GroupEntity methodsFromMyAncestors = containerClass.getGroup("all ancestors").contains("method group");
/* 26 */     GroupEntity allRelatedMethods = methodsFromMyClass.union(methodsFromMyAncestors).union(methodsFromMyDescendants);
/*    */ 
/*    */     
/* 29 */     GroupEntity directExternalCallers = aMethod.getGroup("operations calling me").exclude(methodsFromMyClass).exclude(methodsFromMyDescendants);
/* 30 */     GroupEntity indirectExternalCallers = aMethod.getGroup("methods overriden").getGroup("operations calling me").exclude(allRelatedMethods);
/*    */ 
/*    */     
/* 33 */     return (directExternalCallers.size() + indirectExternalCallers.size() == 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\methods\ShouldBeProtected.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */