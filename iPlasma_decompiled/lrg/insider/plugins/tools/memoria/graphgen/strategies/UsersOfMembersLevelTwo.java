/*    */ package classes.lrg.insider.plugins.tools.memoria.graphgen.strategies;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.strategies.InterestingMembersProvider;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.strategies.UsersOfMembersLevelTwo;
/*    */ 
/*    */ public class UsersOfMembersLevelTwo extends InterestingMembersProvider {
/* 10 */   public UsersOfMembersLevelTwo(AbstractEntityInterface aei) { super(aei); }
/*    */ 
/*    */ 
/*    */   
/* 14 */   public GroupEntity methodRelated() { return this.theCurrentEntity.isUsed("operations calling me"); }
/*    */ 
/*    */ 
/*    */   
/* 18 */   public GroupEntity attributeRelated() { return new GroupEntity("void group", new ArrayList()); }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\memoria\graphgen\strategies\UsersOfMembersLevelTwo.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */