/*    */ package classes.lrg.insider.plugins.filters.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class HighCoupling
/*    */   extends FilteringRule
/*    */ {
/* 16 */   public HighCoupling() { super(new Descriptor("High Coupling", "class")); }
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface anEntity) {
/* 20 */     double nom = ((Double)anEntity.getProperty("NOM").getValue()).doubleValue();
/* 21 */     double fanout = ((Double)anEntity.getProperty("FANOUT").getValue()).doubleValue();
/* 22 */     double cbo = ((Double)anEntity.getProperty("CBO").getValue()).doubleValue();
/* 23 */     double amw = ((Double)anEntity.getProperty("AMW").getValue()).doubleValue();
/*    */     
/* 25 */     if (cbo == 0.0D || nom == 0.0D) return false;
/*    */ 
/*    */     
/* 28 */     if (cbo / fanout >= 0.67D) return false;
/*    */     
/* 30 */     double fanoutNomRatio = fanout / nom;
/* 31 */     FilteringRule highFanout = new FilteringRule("FANOUT", ">=", "class", 24.0D);
/*    */     
/* 33 */     return !(fanoutNomRatio < 7.0D && !highFanout.applyFilter(anEntity));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\classes\HighCoupling.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */