/*    */ package classes.lrg.insider.plugins.filters.memoria.methods;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.Descriptor;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.insider.plugins.filters.memoria.methods.IsAccessor;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class EncapsulationTraitors
/*    */   extends FilteringRule
/*    */ {
/* 17 */   public EncapsulationTraitors() { super(new Descriptor("Encapsulation Traitors", "method")); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean applyFilter(AbstractEntityInterface aMethod) {
/* 23 */     if (!(new IsAccessor()).applyFilter(aMethod)) return false;
/*    */     
/* 25 */     GroupEntity attributesAccessed = aMethod.getGroup("variables accessed").applyFilter("is attribute").applyFilter("is protected");
/*    */     
/* 27 */     GroupEntity scopesOfAccessedAttributes = (GroupEntity)attributesAccessed.belongsTo("class");
/*    */     
/* 29 */     return (scopesOfAccessedAttributes.intersect(aMethod.belongsTo("class").uses("all ancestors")).size() > 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\filters\memoria\methods\EncapsulationTraitors.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */