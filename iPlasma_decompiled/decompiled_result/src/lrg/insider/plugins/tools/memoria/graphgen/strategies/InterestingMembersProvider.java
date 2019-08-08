/*    */ package classes.lrg.insider.plugins.tools.memoria.graphgen.strategies;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.strategies.InterestingMembersProvider;
/*    */ 
/*    */ public abstract class InterestingMembersProvider
/*    */ {
/*    */   protected AbstractEntityInterface theCurrentEntity;
/*    */   
/* 11 */   public InterestingMembersProvider(AbstractEntityInterface aei) { this.theCurrentEntity = aei; }
/*    */   
/*    */   public abstract GroupEntity methodRelated();
/*    */   
/*    */   public abstract GroupEntity attributeRelated();
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\memoria\graphgen\strategies\InterestingMembersProvider.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */