/*    */ package classes.lrg.insider.plugins.tools.memoria.graphgen.strategies;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.strategies.UsersOfMembersByFieldType;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.strategies.UsersOfMembersByFieldTypeBalanced;
/*    */ 
/*    */ public class UsersOfMembersByFieldTypeBalanced extends UsersOfMembersByFieldType {
/*  8 */   public UsersOfMembersByFieldTypeBalanced(AbstractEntityInterface aei, String fieldType) { super(aei, fieldType); }
/*    */ 
/*    */ 
/*    */   
/*    */   public GroupEntity methodRelated() {
/* 13 */     GroupEntity myInterestingMethods = getAttributesWithSpecificTypeName().isUsed("methods accessing variable")
/* 14 */       .intersect(this.theCurrentEntity.contains("method group"));
/*    */ 
/*    */     
/* 17 */     GroupEntity ownCallers = myInterestingMethods.isUsed("operations calling me").intersect(this.theCurrentEntity.contains("method group"));
/* 18 */     return myInterestingMethods.union(ownCallers).isUsed("operations calling me");
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\memoria\graphgen\strategies\UsersOfMembersByFieldTypeBalanced.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */