/*    */ package classes.lrg.insider.plugins.properties.membrain.pitfalls;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.memoria.core.Method;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SimilarNameDifferentManipulation
/*    */   extends FilteringRule
/*    */ {
/* 15 */   public SimilarNameDifferentManipulation() { super(new Descriptor("Similar Name Different Manipulation", "method")); }
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface anEntity) {
/* 19 */     GroupEntity allMethods = anEntity.getGroup("similar methods");
/* 20 */     for (Method aMethod : allMethods.getElements()) {
/* 21 */       double aMethodValue = ((Double)aMethod.getProperty("#TotalUniform#").getValue()).doubleValue();
/* 22 */       double anEntityValue = ((Double)anEntity.getProperty("#TotalUniform#").getValue()).doubleValue();
/* 23 */       if (anEntityValue >= 0.0D && aMethodValue >= 0.0D && 
/* 24 */         aMethodValue > 0.75D && aMethodValue - anEntityValue > 0.25D) return true; 
/*    */     } 
/* 26 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\membrain\pitfalls\SimilarNameDifferentManipulation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */