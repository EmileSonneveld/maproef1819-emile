/*    */ package classes.lrg.insider.plugins.filters.memoria.classes;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.common.abstractions.plugins.filters.composed.NotComposedFilteringRule;
/*    */ import lrg.insider.plugins.filters.Threshold;
/*    */ import lrg.insider.plugins.filters.memoria.classes.IsInterface;
/*    */ import lrg.insider.plugins.filters.memoria.classes.TraditionBreaker;
/*    */ 
/*    */ public class TraditionBreaker extends FilteringRule {
/* 12 */   public TraditionBreaker() { super(new Descriptor("Tradition Breaker", "class")); }
/*    */ 
/*    */   
/*    */   public boolean excessiveIncreaseOfInterfaceSizeInChild(AbstractEntityInterface childClass) {
/* 16 */     boolean highNASValue = (new FilteringRule("NAS", ">=", "class", Threshold.NOM_AVG)).applyFilter(childClass);
/* 17 */     boolean highPNASRatio = (new FilteringRule("PNAS", ">=", "class", 0.666D)).applyFilter(childClass);
/*    */     
/* 19 */     return (highNASValue && highPNASRatio);
/*    */   }
/*    */   
/*    */   public boolean substantialComplexityAndSizeInChild(AbstractEntityInterface childClass) {
/* 23 */     boolean amwhigherAverage = (new FilteringRule("AMW", ">=", "class", Threshold.AMW_AVG)).applyFilter(childClass);
/* 24 */     boolean veryHighWMC = (new FilteringRule("WMC", ">=", "class", Threshold.WMC_VERYHIGH)).applyFilter(childClass);
/* 25 */     boolean highNOM = (new FilteringRule("NOM", ">=", "class", Threshold.NOM_HIGH)).applyFilter(childClass);
/*    */     
/* 27 */     return ((amwhigherAverage || veryHighWMC) && highNOM);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean parentClassNeitherSmallNorDumb(AbstractEntityInterface childClass) {
/* 32 */     NotComposedFilteringRule notComposedFilteringRule = new NotComposedFilteringRule(new IsInterface());
/* 33 */     GroupEntity modelBaseClasses = childClass.uses("base classes").applyFilter("model class").applyFilter(notComposedFilteringRule);
/* 34 */     if (modelBaseClasses.size() == 0) return false;
/*    */     
/* 36 */     double nomAncestors = ((Double)modelBaseClasses.getProperty("NOM").aggregate("sum").getValue()).doubleValue();
/* 37 */     double wmcAncestors = ((Double)modelBaseClasses.getProperty("WMC").aggregate("sum").getValue()).doubleValue();
/* 38 */     double amwAncestors = ((Double)modelBaseClasses.getProperty("AMW").aggregate("sum").getValue()).doubleValue();
/*    */     
/* 40 */     if (nomAncestors >= Threshold.NOM_HIGH / 2.0D && 
/* 41 */       amwAncestors > Threshold.AMW_AVG && 
/* 42 */       wmcAncestors >= Threshold.WMC_VERYHIGH / 2.0D) return true; 
/*    */     return false;
/*    */   }
/*    */   public boolean applyFilter(AbstractEntityInterface childClass) {
/* 46 */     if (excessiveIncreaseOfInterfaceSizeInChild(childClass) && 
/* 47 */       substantialComplexityAndSizeInChild(childClass) && 
/* 48 */       parentClassNeitherSmallNorDumb(childClass)) return true; 
/*    */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\classes\TraditionBreaker.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */