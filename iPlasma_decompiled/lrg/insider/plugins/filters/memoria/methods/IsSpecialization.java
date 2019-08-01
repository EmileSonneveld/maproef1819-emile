/*    */ package classes.lrg.insider.plugins.filters.memoria.methods;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ 
/*    */ public class IsSpecialization
/*    */   extends FilteringRule {
/* 10 */   public IsSpecialization() { super(new Descriptor("is specialization", "method")); }
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface anEntity) {
/* 14 */     if (!(anEntity instanceof lrg.memoria.core.Method)) return false;
/*    */     
/* 16 */     GroupEntity methodsOverriden = anEntity.uses("methods overriden");
/*    */     
/* 18 */     double countMethodsOverriden = methodsOverriden.size();
/*    */     
/* 20 */     if (countMethodsOverriden == 0.0D) return false;
/*    */     
/* 22 */     return (anEntity.uses("operations called").intersect(methodsOverriden).size() > 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\methods\IsSpecialization.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */