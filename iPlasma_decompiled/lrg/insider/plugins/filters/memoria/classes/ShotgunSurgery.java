/*    */ package classes.lrg.insider.plugins.filters.memoria.classes;
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
/*    */ class ShotgunSurgery
/*    */   extends FilteringRule
/*    */ {
/* 17 */   public ShotgunSurgery() { super(new Descriptor("Shotgun Surgery", "class")); }
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface anEntity) {
/* 21 */     FilteringRule highCM = new FilteringRule("CM", ">", "class", 20.0D);
/* 22 */     FilteringRule highCC = new FilteringRule("CC", ">", "class", 10.0D);
/*    */     
/* 24 */     return (new AndComposedFilteringRule(highCM, highCC)).applyFilter(anEntity);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\classes\ShotgunSurgery.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */