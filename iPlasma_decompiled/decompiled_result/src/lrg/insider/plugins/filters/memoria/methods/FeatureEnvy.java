/*    */ package classes.lrg.insider.plugins.filters.memoria.methods;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.common.abstractions.plugins.filters.composed.AndComposedFilteringRule;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FeatureEnvy
/*    */   extends FilteringRule
/*    */ {
/* 18 */   public FeatureEnvy() { super(new Descriptor("Feature Envy", "method")); }
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface anEntity) {
/* 22 */     FilteringRule highAID = new FilteringRule("ATFD", ">", "method", 2.0D);
/* 23 */     FilteringRule lowDispersion = new FilteringRule("FDP", "<=", "method", 2.0D);
/* 24 */     FilteringRule lowLocality = new FilteringRule("LAA", "<", "method", 0.333D);
/*    */     
/* 26 */     return (new AndComposedFilteringRule(lowLocality, 
/* 27 */         new AndComposedFilteringRule(highAID, lowDispersion))).applyFilter(anEntity);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\methods\FeatureEnvy.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */