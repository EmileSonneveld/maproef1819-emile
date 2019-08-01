/*    */ package classes.lrg.insider.plugins.filters.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.common.abstractions.plugins.filters.composed.AndComposedFilteringRule;
/*    */ import lrg.insider.plugins.filters.Threshold;
/*    */ 
/*    */ public class GodClass
/*    */   extends FilteringRule {
/* 11 */   public GodClass() { super(new Descriptor("God Class", "class")); }
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface anEntity) {
/* 15 */     FilteringRule highATFD = new FilteringRule("ATFD", ">", "class", 2.0D);
/* 16 */     FilteringRule lowTCC = new FilteringRule("TCC", "<", "class", 0.333D);
/* 17 */     FilteringRule highWMC = new FilteringRule("WMC", ">=", "class", Threshold.WMC_VERYHIGH);
/*    */     
/* 19 */     return (new AndComposedFilteringRule(highWMC, 
/* 20 */         new AndComposedFilteringRule(highATFD, lowTCC))).applyFilter(anEntity);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\classes\GodClass.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */