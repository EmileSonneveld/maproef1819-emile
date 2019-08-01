/*    */ package classes.lrg.insider.plugins.filters.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.common.abstractions.plugins.filters.composed.NotComposedFilteringRule;
/*    */ import lrg.insider.plugins.core.filters.memoria.ModelClassFilter;
/*    */ import lrg.insider.plugins.filters.Threshold;
/*    */ import lrg.insider.plugins.filters.memoria.classes.FutileHierarchy;
/*    */ import lrg.insider.plugins.filters.memoria.classes.IsInterface;
/*    */ 
/*    */ public class FutileHierarchy extends FilteringRule {
/* 14 */   public FutileHierarchy() { super(new Descriptor("Futile Hierarchy", "class")); }
/*    */ 
/*    */   
/*    */   private boolean isSpecializingBaseclass(AbstractEntityInterface derivedClass, AbstractEntityInterface baseClass) {
/* 18 */     GroupEntity specializationMethods = derivedClass.getGroup("method group").applyFilter("is specialization");
/*    */     
/* 20 */     return (specializationMethods.size() > 0);
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean classIsNotInterfaceAndBelongsToModel(AbstractEntityInterface baseClass) {
/* 25 */     NotComposedFilteringRule notComposedFilteringRule = new NotComposedFilteringRule(new IsInterface());
/* 26 */     if (!(new IsInterface()).applyFilter(baseClass) && (
/* 27 */       new ModelClassFilter()).applyFilter(baseClass)) return true; 
/*    */     return false;
/*    */   }
/*    */   private boolean classHasOneSingleChild(AbstractEntityInterface baseClass) {
/* 31 */     NotComposedFilteringRule notComposedFilteringRule = new NotComposedFilteringRule(new IsInterface());
/* 32 */     if (baseClass.uses("all ancestors").applyFilter("model class").applyFilter(notComposedFilteringRule).size() > 0) {
/* 33 */       return false;
/*    */     }
/* 35 */     return (baseClass.isUsed("all descendants").size() == 1);
/*    */   }
/*    */   
/*    */   private boolean baseClassAbstractOrSignificantBehavior(AbstractEntityInterface baseClass) {
/* 39 */     double NAbsM = ((Double)baseClass.getProperty("NAbsM").getValue()).doubleValue();
/* 40 */     double AMW = ((Double)baseClass.getProperty("AMW").getValue()).doubleValue();
/* 41 */     double NOM = ((Double)baseClass.getProperty("NOM").getValue()).doubleValue();
/*    */     
/* 43 */     if (NAbsM <= 0.0D && (
/* 44 */       AMW <= Threshold.AMW_AVG || NOM <= Threshold.NOM_AVG)) return false; 
/*    */     return true;
/*    */   }
/*    */   
/*    */   private boolean childDefinesSignificantBehaviourWithoutSpecialization(AbstractEntityInterface baseClass) {
/* 49 */     AbstractEntityInterface derivedClass = baseClass.isUsed("all descendants").getElementAt(0);
/*    */     
/* 51 */     boolean noSpecializationFromBaseClass = isSpecializingBaseclass(derivedClass, baseClass);
/*    */     
/* 53 */     double AMWDerived = ((Double)derivedClass.getProperty("AMW").getValue()).doubleValue();
/* 54 */     double PNASDerived = ((Double)derivedClass.getProperty("PNAS").getValue()).doubleValue();
/* 55 */     double NOMDerived = ((Double)derivedClass.getProperty("NOM").getValue()).doubleValue();
/*    */     
/* 57 */     if ((!noSpecializationFromBaseClass || 
/* 58 */       NOMDerived <= Threshold.NOM_AVG || 
/* 59 */       PNASDerived <= 0.5D) && AMWDerived <= Threshold.AMW_AVG) return false; 
/*    */     return true;
/*    */   }
/*    */   public boolean applyFilter(AbstractEntityInterface baseClass) {
/* 63 */     if (classIsNotInterfaceAndBelongsToModel(baseClass) && 
/* 64 */       classHasOneSingleChild(baseClass) && 
/* 65 */       baseClassAbstractOrSignificantBehavior(baseClass) && 
/* 66 */       childDefinesSignificantBehaviourWithoutSpecialization(baseClass)) return true; 
/*    */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\classes\FutileHierarchy.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */