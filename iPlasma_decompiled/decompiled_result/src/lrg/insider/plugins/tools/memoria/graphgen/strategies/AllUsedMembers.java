/*    */ package classes.lrg.insider.plugins.tools.memoria.graphgen.strategies;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.strategies.AllUsedMembers;
/*    */ import lrg.insider.plugins.tools.memoria.graphgen.strategies.InterestingMembersProvider;
/*    */ import lrg.memoria.core.DataAbstraction;
/*    */ 
/*    */ 
/*    */ public class AllUsedMembers
/*    */   extends InterestingMembersProvider
/*    */ {
/* 14 */   public AllUsedMembers(AbstractEntityInterface aei) { super(aei); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 20 */   public GroupEntity attributeRelated() { return new GroupEntity("empty group", new ArrayList()); }
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
/* 38 */     GroupEntity groupEntity = this.theCurrentEntity;
/*    */     
/* 40 */     if (this.theCurrentEntity instanceof lrg.memoria.core.Class) {
/*    */       
/* 42 */       DataAbstraction theClass = (DataAbstraction)this.theCurrentEntity;
/*    */       
/* 44 */       if (theClass.isInterface())
/*    */       {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 51 */         groupEntity = theClass.getGroup("derived classes");
/*    */       }
/*    */     } 
/*    */     
/* 55 */     return groupEntity.uses("operations called").distinct().exclude(this.theCurrentEntity.contains("method group"));
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\tools\memoria\graphgen\strategies\AllUsedMembers.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */