/*    */ package classes.lrg.insider.plugins.groups.memoria;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import lrg.common.abstractions.entities.AbstractEntityInterface;
/*    */ import lrg.common.abstractions.entities.GroupEntity;
/*    */ import lrg.common.abstractions.plugins.groups.GroupBuilder;
/*    */ 
/*    */ public class SubclassesDependedOn
/*    */   extends GroupBuilder
/*    */ {
/* 11 */   public SubclassesDependedOn() { super("subclasses dependencies", "", "method"); }
/*    */ 
/*    */   
/*    */   public ArrayList buildGroup(AbstractEntityInterface aMethod) {
/* 15 */     GroupEntity allDescendants = aMethod.belongsTo("class").isUsed("all descendants");
/* 16 */     GroupEntity allUsages = aMethod.uses("operations called").union(aMethod.uses("variables accessed"));
/*    */     
/* 18 */     return ((GroupEntity)allUsages.belongsTo("class")).intersect(allDescendants).getElements();
/*    */   }
/*    */ }


/* Location:              C:\Users\emill\Dropbox\slimmerWorden\2018-2019-Semester2\THESIS\iPlasma6\tools\iPlasma\classes.zip!\classes\lrg\insider\plugins\groups\memoria\SubclassesDependedOn.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.7
 */