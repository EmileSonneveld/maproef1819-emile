/*    */ package classes.lrg.insider.plugins.filters.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.insider.plugins.filters.Threshold;
/*    */ 
/*    */ public class DataClass
/*    */   extends FilteringRule
/*    */ {
/* 11 */   public DataClass() { super(new Descriptor("Data Class", "class")); }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface anEntity) {
/* 16 */     if (!(new FilteringRule("WOC", "<", "class", 0.333D)).applyFilter(anEntity)) {
/* 17 */       return false;
/*    */     }
/* 19 */     double totalNumberOfPublicData = (
/* 20 */       (Double)anEntity.getProperty("NOPA").getValue()).doubleValue() + (
/* 21 */       (Double)anEntity.getProperty("NOAM").getValue()).doubleValue();
/*    */     
/* 23 */     double wmc = ((Double)anEntity.getProperty("WMC").getValue()).doubleValue();
/*    */ 
/*    */     
/* 26 */     boolean smallandDataClasss = (totalNumberOfPublicData > 2.0D && 
/* 27 */       wmc < Threshold.WMC_HIGH);
/* 28 */     boolean largerButDataClass = (totalNumberOfPublicData > 4.0D && 
/* 29 */       wmc < Threshold.WMC_VERYHIGH);
/*    */     
/* 31 */     return !(!smallandDataClasss && !largerButDataClass);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\classes\DataClass.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */