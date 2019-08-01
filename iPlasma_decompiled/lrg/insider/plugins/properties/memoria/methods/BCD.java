/*    */ package classes.lrg.insider.plugins.properties.memoria.methods;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.entities.ResultEntity;
/*    */ import lrg.common.abstractions.plugins.properties.PropertyComputer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class BCD
/*    */   extends PropertyComputer
/*    */ {
/* 17 */   public BCD() { super("BCD", "BaseClass Dependencies", "method", "numerical"); }
/*    */ 
/*    */   
/*    */   public ResultEntity compute(AbstractEntityInterface anEntity) {
/* 21 */     GroupEntity baseClasses = anEntity.belongsTo("class").uses("base classes").applyFilter("model class");
/* 22 */     GroupEntity protecteMethodsCalled = anEntity.uses("operations called").applyFilter("is protected").distinct();
/* 23 */     GroupEntity protecteAttributesUsed = anEntity.uses("variables accessed").applyFilter("is attribute").applyFilter("is protected").distinct();
/*    */     
/* 25 */     GroupEntity membersUsed = anEntity.uses("methods overriden").union(protecteMethodsCalled);
/* 26 */     membersUsed = membersUsed.union(protecteAttributesUsed);
/*    */     
/* 28 */     return new ResultEntity(((GroupEntity)membersUsed.belongsTo("class")).intersect(baseClasses).size());
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\properties\memoria\methods\BCD.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */