/*    */ package classes.lrg.insider.plugins.filters.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.common.abstractions.plugins.filters.composed.NotComposedFilteringRule;
/*    */ import lrg.insider.plugins.filters.Threshold;
/*    */ import lrg.insider.plugins.filters.memoria.classes.IsInterface;
/*    */ import lrg.insider.plugins.filters.memoria.classes.IsRootClass;
/*    */ import lrg.insider.plugins.filters.memoria.classes.RefusedParentBequest2;
/*    */ import lrg.insider.plugins.filters.memoria.methods.IsConstructor;
/*    */ 
/*    */ public class RefusedParentBequest2
/*    */   extends FilteringRule
/*    */ {
/*    */   private double NOM;
/*    */   
/*    */   public RefusedParentBequest2() {
/* 20 */     super(new Descriptor("Refused Parent Bequest 2", "class"));
/*    */ 
/*    */     
/* 23 */     this.NOM = 0.0D;
/*    */   }
/*    */   private double BOvR(AbstractEntityInterface derivedClass) {
/* 26 */     GroupEntity baseclassMethods = derivedClass.uses("base classes").applyFilter("model class")
/* 27 */       .contains("method group");
/* 28 */     double nrOfOverridenMethods = derivedClass.uses("methods overriden").intersect(baseclassMethods).size();
/* 29 */     return nrOfOverridenMethods / this.NOM;
/*    */   }
/*    */ 
/*    */   
/*    */   private double NumberOfProtectedMembersinBaseClass(AbstractEntityInterface derivedClass) {
/* 34 */     GroupEntity baseClasses = derivedClass.uses("base classes").applyFilter("model class");
/* 35 */     baseClasses.applyFilter(new NotComposedFilteringRule(new IsInterface()));
/* 36 */     if (baseClasses.size() == 0) return 0.0D;
/*    */     
/* 38 */     GroupEntity protectedMethods = baseClasses.contains("method group").applyFilter("is protected");
/* 39 */     protectedMethods.applyFilter(new NotComposedFilteringRule(new IsConstructor()));
/* 40 */     GroupEntity protectedAttributes = baseClasses.contains("attribute group").applyFilter("is protected");
/*    */     
/* 42 */     return (protectedAttributes.size() + protectedMethods.size());
/*    */   }
/*    */   
/*    */   private boolean childClassIgnoresBequest(AbstractEntityInterface derivedClass) {
/* 46 */     double NProtMInBase = NumberOfProtectedMembersinBaseClass(derivedClass);
/* 47 */     boolean lowUsageRatio = (new FilteringRule("BUR", "<", "class", 0.333D))
/* 48 */       .applyFilter(derivedClass);
/* 49 */     boolean lowOverridingRatio = (BOvR(derivedClass) < 0.333D);
/*    */     
/* 51 */     GroupEntity calledClasses = (GroupEntity)derivedClass.getGroup("operations called").belongsTo("class");
/*    */     
/* 53 */     boolean usesbaseclass = (calledClasses.applyFilter("model class").intersect(derivedClass.getGroup("base classes")).size() > 0);
/*    */     
/* 55 */     return (usesbaseclass && BOvR(derivedClass) == 0.0D);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private boolean childClassIsNotDwarf(AbstractEntityInterface derivedClass) {
/* 61 */     double amw = ((Double)derivedClass.getProperty("AMW").getValue()).doubleValue();
/* 62 */     double wmc = ((Double)derivedClass.getProperty("WMC").getValue()).doubleValue();
/*    */     
/* 64 */     if (amw > Threshold.AMW_AVG && 
/* 65 */       wmc > Threshold.WMC_AVG && 
/* 66 */       this.NOM > Threshold.NOM_AVG) return true; 
/*    */     return false;
/*    */   }
/*    */   public boolean applyFilter(AbstractEntityInterface derivedClass) {
/* 70 */     NotComposedFilteringRule notComposedFilteringRule = new NotComposedFilteringRule(new IsConstructor());
/* 71 */     this.NOM = derivedClass.contains("method group").applyFilter(notComposedFilteringRule).size();
/* 72 */     if (this.NOM == 0.0D) return false;
/*    */     
/* 74 */     if (!(new IsRootClass()).applyFilter(derivedClass) && 
/* 75 */       childClassIgnoresBequest(derivedClass) && 
/* 76 */       childClassIsNotDwarf(derivedClass)) return true; 
/*    */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\classes\RefusedParentBequest2.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */