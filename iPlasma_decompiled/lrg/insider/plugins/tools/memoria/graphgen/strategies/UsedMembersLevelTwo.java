/*    */ package classes.lrg.insider.plugins.tools.memoria.graphgen.strategies;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.strategies.InterestingMembersProvider;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.strategies.UsedMembersLevelTwo;
/*    */ 
/*    */ public class UsedMembersLevelTwo
/*    */   extends InterestingMembersProvider
/*    */ {
/* 12 */   public UsedMembersLevelTwo(AbstractEntityInterface aei) { super(aei); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 18 */   public GroupEntity attributeRelated() { return new GroupEntity("empty group", new ArrayList()); }
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
/*    */ 
/*    */ 
/*    */   
/*    */   public GroupEntity methodRelated() {
/* 36 */     AbstractEntityInterface entity = this.theCurrentEntity;
/*    */     
/* 38 */     return entity.uses("operations called");
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\memoria\graphgen\strategies\UsedMembersLevelTwo.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */