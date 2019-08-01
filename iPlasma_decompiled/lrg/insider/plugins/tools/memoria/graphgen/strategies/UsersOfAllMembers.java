/*    */ package classes.lrg.insider.plugins.tools.memoria.graphgen.strategies;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.strategies.InterestingMembersProvider;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.strategies.UsersOfAllMembers;
/*    */ 
/*    */ public class UsersOfAllMembers extends InterestingMembersProvider {
/*  8 */   public UsersOfAllMembers(AbstractEntityInterface aei) { super(aei); }
/*    */ 
/*    */ 
/*    */   
/* 12 */   public GroupEntity methodRelated() { return this.theCurrentEntity.isUsed("operations calling me"); }
/*    */ 
/*    */ 
/*    */   
/* 16 */   public GroupEntity attributeRelated() { return this.theCurrentEntity.isUsed("methods accessing variable"); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\memoria\graphgen\strategies\UsersOfAllMembers.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */