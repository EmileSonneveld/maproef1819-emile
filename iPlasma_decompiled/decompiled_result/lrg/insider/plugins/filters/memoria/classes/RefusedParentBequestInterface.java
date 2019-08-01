/*    */ package classes.lrg.insider.plugins.filters.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.common.abstractions.plugins.filters.composed.NotComposedFilteringRule;
/*    */ import lrg.insider.plugins.filters.Threshold;
/*    */ import lrg.insider.plugins.filters.memoria.classes.RefusedParentBequestInterface;
/*    */ import lrg.insider.plugins.filters.memoria.methods.IsConstructor;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RefusedParentBequestInterface
/*    */   extends FilteringRule
/*    */ {
/*    */   private double NOM;
/*    */   
/*    */   public RefusedParentBequestInterface() {
/* 19 */     super(new Descriptor("Refused Parent Bequest Interface", "class"));
/*    */ 
/*    */     
/* 22 */     this.NOM = 0.0D;
/*    */   }
/*    */   private boolean childClassIgnoresBequest(AbstractEntityInterface derivedClass) {
/* 25 */     FilteringRule manyOverwritingMethods = new FilteringRule("BOvM", ">", "class", 4.0D);
/* 26 */     FilteringRule fewSpecializingMethods = new FilteringRule("NSPECM", "<", "class", 1.0D);
/*    */     
/* 28 */     if (manyOverwritingMethods.applyFilter(derivedClass) && 
/* 29 */       fewSpecializingMethods.applyFilter(derivedClass)) return true; 
/*    */     return false;
/*    */   }
/*    */   private boolean childClassIsNotDwarf(AbstractEntityInterface derivedClass) {
/* 33 */     double amw = ((Double)derivedClass.getProperty("AMW").getValue()).doubleValue();
/* 34 */     double wmc = ((Double)derivedClass.getProperty("WMC").getValue()).doubleValue();
/*    */     
/* 36 */     if (amw > Threshold.AMW_AVG && 
/* 37 */       wmc > Threshold.WMC_AVG && 
/* 38 */       this.NOM > Threshold.NOM_AVG) return true; 
/*    */     return false;
/*    */   }
/*    */   public boolean applyFilter(AbstractEntityInterface derivedClass) {
/* 42 */     NotComposedFilteringRule notComposedFilteringRule = new NotComposedFilteringRule(new IsConstructor());
/* 43 */     this.NOM = derivedClass.contains("method group").applyFilter(notComposedFilteringRule).size();
/* 44 */     if (this.NOM == 0.0D) return false;
/*    */     
/* 46 */     return (childClassIgnoresBequest(derivedClass) && childClassIsNotDwarf(derivedClass));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\classes\RefusedParentBequestInterface.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */