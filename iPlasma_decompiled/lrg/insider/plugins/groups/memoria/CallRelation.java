/*    */ package classes.lrg.insider.plugins.groups.memoria;
/*    */ 
/*    */ import lrg.common.abstractions.entities.AbstractEntity;
/*    */ import lrg.insider.plugins.groups.memoria.CallRelation;
/*    */ 
/*    */ public class CallRelation {
/*    */   private AbstractEntity callsNode;
/*    */   private AbstractEntity isCalledNode;
/*    */   
/* 10 */   public CallRelation(AbstractEntity calls, AbstractEntity isCalled) { this.callsNode = calls; this.isCalledNode = isCalled; }
/*    */   
/* 12 */   public AbstractEntity getCallsNode() { return this.callsNode; }
/*    */   
/* 14 */   public AbstractEntity getIsCalledNode() { return this.isCalledNode; }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\memoria\CallRelation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */