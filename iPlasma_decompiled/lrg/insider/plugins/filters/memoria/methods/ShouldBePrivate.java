/*    */ package classes.lrg.insider.plugins.filters.memoria.methods;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntity;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.insider.plugins.filters.memoria.methods.IsPrivate;
/*    */ 
/*    */ public class ShouldBePrivate extends FilteringRule {
/* 11 */   public ShouldBePrivate() { super(new Descriptor("should be private", "method")); }
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface aMethod) {
/* 15 */     if ((new IsPrivate()).applyFilter(aMethod)) return false; 
/* 16 */     AbstractEntity containerClass = aMethod.belongsTo("class");
/*    */     
/* 18 */     GroupEntity methodsFromMyClass = containerClass.contains("method group");
/*    */     
/* 20 */     GroupEntity directExternalCallers = aMethod.getGroup("operations calling me").exclude(methodsFromMyClass);
/* 21 */     GroupEntity indirectCallers = aMethod.getGroup("methods overriden").getGroup("operations calling me");
/*    */ 
/*    */     
/* 24 */     return (directExternalCallers.size() + indirectCallers.size() == 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\methods\ShouldBePrivate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */