/*    */ package classes.lrg.insider.plugins.filters.memoria.classes;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ 
/*    */ public class IsLeafClass
/*    */   extends FilteringRule {
/*  9 */   public IsLeafClass() { super(new Descriptor("is leaf-class", "class")); }
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface anEntity) {
/* 13 */     if (!(anEntity instanceof lrg.memoria.core.Class)) return false;
/*    */     
/* 15 */     if (anEntity.uses("base classes").applyFilter("model class").size() == 0) return false; 
/* 16 */     return (anEntity.isUsed("derived classes").size() == 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\classes\IsLeafClass.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */