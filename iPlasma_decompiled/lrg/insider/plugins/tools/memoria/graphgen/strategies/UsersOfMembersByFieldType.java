/*    */ package classes.lrg.insider.plugins.tools.memoria.graphgen.strategies;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.filters.FilteringRule;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.strategies.UsersOfMembersByFieldType;
/*    */ 
/*    */ public class UsersOfMembersByFieldType extends InterestingMembersProvider {
/*    */   protected String nameOfFieldType;
/*    */   
/*    */   public UsersOfMembersByFieldType(AbstractEntityInterface aei, String fieldType) {
/* 11 */     super(aei);
/* 12 */     this.nameOfFieldType = fieldType;
/* 13 */     this.attributesWithSpecificTypeName = null;
/*    */   }
/*    */   private GroupEntity attributesWithSpecificTypeName;
/*    */   protected GroupEntity getAttributesWithSpecificTypeName() {
/* 17 */     if (this.attributesWithSpecificTypeName == null) {
/* 18 */       FilteringRule hasTypeName = new FilteringRule("Type", "contain", "attribute", this.nameOfFieldType);
/* 19 */       this.attributesWithSpecificTypeName = this.theCurrentEntity.contains("attribute group").applyFilter(hasTypeName);
/*    */     } 
/* 21 */     return this.attributesWithSpecificTypeName;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 26 */   public GroupEntity methodRelated() { return getAttributesWithSpecificTypeName().isUsed("methods accessing variable")
/* 27 */       .intersect(this.theCurrentEntity.contains("method group"))
/* 28 */       .isUsed("operations calling me"); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 42 */   public GroupEntity attributeRelated() { return getAttributesWithSpecificTypeName().isUsed("methods accessing variable")
/* 43 */       .exclude(this.theCurrentEntity.contains("method group")); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\memoria\graphgen\strategies\UsersOfMembersByFieldType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */